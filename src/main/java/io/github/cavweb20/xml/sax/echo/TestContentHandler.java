package io.github.cavweb20.xml.sax.echo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 *
 * @author cavweb20
 * @version 1.0
 * @since 08/09/2002
 */
public class TestContentHandler implements ContentHandler
{

    /**
     * Private variables. locator: Variable to save the document locator for
     * later use. namespaceMappings: Map to store URI to prefix mappings.
     */
    private static Logger LOG = LoggerFactory.getLogger(TestContentHandler.class);

    private Locator locator;

    private Map<String, String> namespaceMappings;

    /**
     * Default constructor. Assigns the Logger
     */
    public TestContentHandler()
    {
        super();
        namespaceMappings = new HashMap<String, String>();
    }

    /**
     * Function to handle the beginning of the document.
     */
    public void startDocument() throws SAXException
    {
        LOG.info("START DOCUMENT:");
    }

    /**
     * Function to handle the beginning of an element.
     */
    public void startElement(String namespaceURI, String localName,
            String qName, Attributes atts) throws SAXException
    {
        // Print element name and locator
        LOG.info("START ELEMENT(L" + locator.getLineNumber() + ",C"
                + locator.getColumnNumber() + "):");

        // Determine namespace
        if (namespaceURI.length() > 0)
        {
            String namePrefix = (String) namespaceMappings.get(namespaceURI);
            if (namePrefix.equals(""))
            {
                namePrefix = "[default]";
            }
            LOG.info("  namespace URI: " + namespaceURI);
            LOG.info("  namespace PREFIX: " + namePrefix);
        }
        LOG.info("  QUALIFIEDNAME: " + qName);
        LOG.info("  LOCALNAME: " + localName);

        for (int i = 0; i < atts.getLength(); i++)
        {
            String attLName = atts.getLocalName(i); // Local attribute name
            String attQName = atts.getQName(i); // Qualified attribute name
            String attURI = atts.getURI(i); // URI attribute namespace

            String attPrefix = (String) namespaceMappings.get(namespaceURI);
            if (attURI.equals(""))
            {
                attURI = "[empty]";
            }
            if (attPrefix.equals(""))
            {
                attPrefix = "[default]";
            }
            LOG.info("  ATTR| namespace URI: " + attURI
                    + " - namespace PREFIX: " + attPrefix);
            LOG.info("  ATTR| QUALIFIEDNAME: " + attQName + " - LOCALNAME: "
                    + attLName + " - VALUE[" + atts.getValue(attQName) + "]");
        }
    }

    /**
     * Function to indicate the beginning of an XML Namespace prefix apping.
     * Although this typically occurs within the root element of an XML
     * document, it can occur at any point within the document. Note that a
     * prefix mapping on an element triggers this callback <em>before</em> the
     * callback for the actual element itself (<code>{@link #startElement}</code>)
     * occurs.
     */
    public void startPrefixMapping(String prefix, String uri)
            throws SAXException
    {
        namespaceMappings.put(uri, prefix);
    }

    /**
     * Function to handle characters.
     */
    public void characters(char[] ch, int start, int length)
            throws SAXException
    {
        String s = new String(ch, start, length);
        if (!s.trim().equals(""))
            LOG.info("  CHARS|" + s + "|");
    }

    /**
     * Function to handle the end of an element.
     */
    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException
    {
        LOG.info("END ELEMENT(L" + locator.getLineNumber() + ",C"
                + locator.getColumnNumber() + "):");
    }

    /**
     * Function to indicate the end of a prefix mapping, when the namespace
     * reported in a <code>{@link #startPrefixMapping}</code> callback is no
     * longer available.
     */
    public void endPrefixMapping(String prefix) throws SAXException
    {
        for (Iterator<String> i = namespaceMappings.keySet().iterator(); i.hasNext();)
        {
            String uri = i.next();
            String thisPrefix = namespaceMappings.get(uri);
            if (prefix.equals(thisPrefix))
            {
                namespaceMappings.remove(uri);
                break;
            }
        }
    }

    /**
     * Function to handle the end of the document.
     */
    public void endDocument() throws SAXException
    {
        LOG.info("END DOCUMENT:");
    }

    /**
     * Function to handle processing instructions.
     */
    public void processingInstruction(String target, String data)
            throws SAXException
    {
        LOG.info("  Target = |" + target + "|");
        LOG.info("  Data = |" + data + "|");
    }

    /**
     * Function to process ignorable white spaces.
     */
    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException
    {
        // Do nothing
    }

    /**
     * Function to report an entity that is skipped by the parser. This should
     * only occur for non-validating parsers, and then is still
     * implementation-dependent behavior.
     */
    public void skippedEntity(String name) throws SAXException
    {
        LOG.error(name);
        LOG.info("SKIPPED ENTITY: " + name);
    }

    /**
     * Function to set the Document Locator.
     */
    public void setDocumentLocator(Locator l)
    {
        LOG.info("LOCATOR:");
        LOG.info("  SYS ID: " + l.getSystemId());
        LOG.info("  PUB ID: " + l.getPublicId());
        this.locator = l; // Save the locator for later use
    }

}