package io.github.cavweb20.xml.dom.echo;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.xml.resolver.tools.CatalogResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;
import org.xml.sax.SAXException;

/**
 * @author velasco
 * @since  2006-04-01
 */
public class XhtmlCSSLinkExtractor
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(XhtmlCSSLinkExtractor.class);

    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("##### Start #####");

        if (args.length != 1)
        {
            LOG.error("Usage: java XhtmlCSSLinkExtractor URL");
            System.exit(-1);
        }

        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder parser = factory.newDocumentBuilder();
            parser.setEntityResolver(new CatalogResolver());
            Document doc = parser.parse(args[0]);
            DocumentTraversal traversable = (DocumentTraversal) doc;
            TreeWalker walker = traversable.createTreeWalker(doc,
                    NodeFilter.SHOW_ELEMENT, new XhtmlCSSLinkNodeFilter(), true);
            
            // Extract the text
            while (walker.nextNode() != null)
            {
                System.out.println(((Element)walker.getCurrentNode()).
                        getAttribute("href"));
            }
            
        }
        catch (FactoryConfigurationError e)
        {
            LOG.error("Error: Cannot create factory.\n"
                    + e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (ParserConfigurationException e)
        {
            LOG.error("Error: Parser configuration problem.\n"
                    + e.getLocalizedMessage());
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