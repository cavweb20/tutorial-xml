package io.github.cavweb20.xml.dom.parser;

import io.github.cavweb20.xml.dom.util.PropertyPrinter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.resolver.tools.CatalogResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * @author cavweb20
 *  
 */
public class TreeReporter
{
    
    // Setting up the logging properties
    private static final Logger LOG = LoggerFactory.getLogger(TreeReporter.class);

    private final PropertyPrinter printer = new PropertyPrinter();

    public void followNode(Node node) throws IOException
    {
        printer.writeNode(node);
        if (node.hasChildNodes())
        {
            Node firstChild = node.getFirstChild();
            followNode(firstChild);
        }
        Node nextNode = node.getNextSibling();
        if (nextNode != null) followNode(nextNode);
    }

    public static void main(String[] args)
    {

        if (args.length < 1)
        {
            LOG.error("Usage: java TreeReporter URL");
            System.exit(-1);
        }

        TreeReporter iterator = new TreeReporter();
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder parser = factory.newDocumentBuilder();
            parser.setEntityResolver(new CatalogResolver());
            Document document = parser.parse(args[0]);

            // Process it starting at the root
            iterator.followNode(document);

        }
        catch (SAXException e)
        {
            LOG.error(args[0] + " is not well-formed.");
            LOG.error(e.getLocalizedMessage());
        }
        catch (IOException e)
        {
            LOG.error(e.getLocalizedMessage());
        }
        catch (ParserConfigurationException e)
        {
            LOG.error("Could not locate a JAXP parser");
            LOG.error(e.getLocalizedMessage());
        }
    }
}