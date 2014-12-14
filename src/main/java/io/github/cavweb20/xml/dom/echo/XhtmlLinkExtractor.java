package io.github.cavweb20.xml.dom.echo;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.xml.resolver.tools.CatalogResolver;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author cavweb20
 * @since  2004-04-01
 */
public class XhtmlLinkExtractor
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(XhtmlLinkExtractor.class);

    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("##### Start #####");

        if (args.length != 1)
        {
            LOG.error("Usage: java XhtmlLinkExtractor URL");
            System.exit(-1);
        }

        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder parser = factory.newDocumentBuilder();
            parser.setEntityResolver(new CatalogResolver());
            Document doc = parser.parse(args[0]);
            NodeList nl = doc.getElementsByTagNameNS("http://www.w3.org/1999/xhtml", "a");
            for(int i = 0; i < nl.getLength(); i++)
            {
                NamedNodeMap atts = nl.item(i).getAttributes();
                for (int j = 0; j < atts.getLength(); j++)
                {
                    Attr attribute = (Attr) atts.item(j);
                    if(attribute.getNodeName().equals("href"))
                        System.out.println(attribute.getNodeValue());
                }
            }
        }
        catch (FactoryConfigurationError e)
        {
            LOG.error("Error: Cannot create factory.\n" + e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (ParserConfigurationException e)
        {
            LOG.error("Error: Parser configuration problem.\n" + e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (SAXException e)
        {
            LOG.error("Error: SAX exception." + e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (IOException e)
        {
            LOG.error("Error: I/O exception." + e.getLocalizedMessage());
            System.exit(-1);
        }
        if (LOG.isDebugEnabled())
            LOG.debug("##### End #####");
    }

}