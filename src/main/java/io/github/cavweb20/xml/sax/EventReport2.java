package io.github.cavweb20.xml.sax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

/**
 * SAX Content Handler
 * 
 * @author cavweb20
 * @since  2004-05-15
 */
public class EventReport2 extends DefaultHandler
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(EventReport2.class);

    public void startDocument() throws SAXException
    {
        if (LOG.isInfoEnabled())
            LOG.info("Start Document");
    }

    public void endDocument() throws SAXException
    {
        if (LOG.isInfoEnabled())
            LOG.info("End Document");
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

    public void characters(char[] text, int start, int length)
            throws SAXException
    {
        System.err.println(new String(text, start, length));
    }

}