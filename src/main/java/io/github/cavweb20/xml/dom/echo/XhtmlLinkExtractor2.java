package io.github.cavweb20.xml.dom.echo;

import io.github.cavweb20.xml.dom.echo.filters.XhtmlLinkNodeFilter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.resolver.tools.CatalogResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

/**
 * @author cavweb20
 * @since  2006-04-01
 */
public class XhtmlLinkExtractor2
{

    // Setting up the logging properties
    private static final Logger LOG = LoggerFactory.getLogger(XhtmlLinkExtractor2.class);

    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("##### Start #####");

        if (args.length < 1)
        {
            LOG.error("Usage: java XhtmlLinkExtractor2 URL");
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
            NodeIterator iterator = traversable.createNodeIterator(doc,
                NodeFilter.SHOW_ELEMENT, new XhtmlLinkNodeFilter(), true);
            
            // Extract the text
            Element current;
            while ((current = (Element)iterator.nextNode()) != null)
            {
                System.out.println(current.getAttribute("href"));
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