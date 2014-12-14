package io.github.cavweb20.xml.sax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ext.Locator2;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * SAX Content Handler
 * 
 * @author velasco
 * @since  2006-05-15
 */
public class EventReport4 extends DefaultHandler
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(EventReport4.class);

    // Locating events
    private Locator2 loc;

    public void setDocumentLocator(Locator loc)
    {
        this.loc = (Locator2) loc;
    }

    public void startDocument() throws SAXException
    {
        if (LOG.isInfoEnabled())
        {
            LOG.info("XML Version = " + loc.getXMLVersion());
            LOG.info("Document Encoding = " + loc.getEncoding());
            LOG.info("Start Document (" + loc.getLineNumber() + ","
                    + loc.getColumnNumber() + ")");
        }
    }

    public void endDocument() throws SAXException
    {
        if (LOG.isInfoEnabled())
            LOG.info("End Document (" + loc.getLineNumber() + ","
                    + loc.getColumnNumber() + ")");
    }

    public void startPrefixMapping(String prefix, String uri)
            throws SAXException
    {
        if (LOG.isInfoEnabled())
            LOG.info("Start Prefix Mapping - prefix: " + prefix + " - URI: "
                    + uri);
    }

    public void endPrefixMapping(String prefix) throws SAXException
    {
        if (LOG.isInfoEnabled())
            LOG.info("End Prefix Mapping - prefix: " + prefix);
    }

    public void endElement(String nsuri, String localname, String qname)
            throws SAXException
    {
        if (LOG.isInfoEnabled())
            LOG.info("Element end (" + loc.getLineNumber() + ","
                    + loc.getColumnNumber() + "): " + qname);
    }

    public void startElement(String nsuri, String localname, String qname,
            Attributes arg3) throws SAXException
    {
        if (LOG.isInfoEnabled())
            LOG.info("Element (" + loc.getLineNumber() + ","
                    + loc.getColumnNumber() + "): " + qname);
    }
}