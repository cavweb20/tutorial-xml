package io.github.cavweb20.xml.neko;

import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

/**
 * Class to read an HTML file, run it through Neko and output an
 * XHTML file.
 * 
 * @version 1.0
 * @since   2003-03-22
 */
public class Tidy
{
    
    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(Tidy.class);

    /**
     * Function to clean attributes by their name.
     */
    private static void cleanAttributesByName(Document doc)
    {
        // Create the NodeIterator without expanding entities
        DocumentTraversal traversal = (DocumentTraversal)doc;
        NodeIterator iterator =
            traversal.createNodeIterator(
                doc.getDocumentElement(),
                NodeFilter.SHOW_ELEMENT,
                new RemoveAttrByName(),
                false);

        // Remove the adequate attributes
        Element element;
        while((element = (Element) (iterator.nextNode())) != null)
        {
            for(int i = 0; i < XHTMLConstants.ATTRIBUTES_TO_BE_REMOVED.length;
                i++)
            {
                if (element.hasAttribute(XHTMLConstants.ATTRIBUTES_TO_BE_REMOVED[i]))
                    element.removeAttribute(
                        XHTMLConstants.ATTRIBUTES_TO_BE_REMOVED[i]);
            }

        }
        iterator.detach();
    }

    public static void main(String[] args)
    {
        DOMParser parser = new DOMParser();
        Transformer transformer = null;
        Source input = null;
        Result output = null;

        LOG.debug("##### Starting application #####");

        if (args.length != 1)
        {
            LOG.error("Usage: java Tidy filename");
            System.exit(-1);
        }

        try
        {
            parser.setProperty("http://cyberneko.org/html/properties/names/elems", "lower");
            parser.parse(args[0]);
            cleanAttributesByName(parser.getDocument());
            transformer = TransformerFactory.newInstance().newTransformer();
            input = new DOMSource(parser.getDocument());
            output = new StreamResult(System.out);
            transformer.transform(input, output);
        }
        catch (SAXException e)
        {
            LOG.error("Error: SAX exception.");
            LOG.error(e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (IOException e)
        {
            LOG.error("Error: I/O exception.");
            LOG.error(e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (TransformerException e)
        {
            LOG.error("Error: transformer exception.");
            LOG.error(e.getLocalizedMessage());
            System.exit(-1);
        }

        LOG.debug("##### Ending application #####");
    }
}