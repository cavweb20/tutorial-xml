package io.github.cavweb20.xml.sax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * SAX Content Handler
 * 
 * @author velasco
 * @since  2004-05-15
 */
public class EventReport1 implements ContentHandler
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(EventReport1.class);

    /**
     * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
     */
    public void setDocumentLocator(Locator arg0)
    {
        // Do Nothing
    }

    /**
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    public void startDocument() throws SAXException
    {
        if(LOG.isInfoEnabled()) LOG.info("Start Document");
    }

    /**
     * @see org.xml.sax.ContentHandler#endDocument()
     */
    public void endDocument() throws SAXException
    {
        if(LOG.isInfoEnabled()) LOG.info("End Document");
    }

    /**
     * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
     */
    public void startPrefixMapping(String prefix, String uri) throws SAXException
    {
        if(LOG.isInfoEnabled()) LOG.info("Start Prefix Mapping - prefix: " + prefix + " - URI: " + uri);

    }

    /**
     * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
     */
    public void endPrefixMapping(String prefix) throws SAXException
    {
        if(LOG.isInfoEnabled()) LOG.info("End Prefix Mapping - prefix: " + prefix);
    }

    /**
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String arg0, String arg1, String arg2, Attributes arg3)
                    throws SAXException
    {
        // Do Nothing
    }

    /**
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String arg0, String arg1, String arg2) throws SAXException
    {
        // Do Nothing
    }

    /**
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    public void characters(char[] text, int start, int length) throws SAXException
    {
        System.out.println(new String(text, start, length));
    }

    /**
     * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
     */
    public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException
    {
        // Do Nothing
    }

    /**
     * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
     */
    public void processingInstruction(String arg0, String arg1) throws SAXException
    {
        // Do Nothing
    }

    /**
     * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
     */
    public void skippedEntity(String arg0) throws SAXException
    {
        // Do Nothing
    }

}