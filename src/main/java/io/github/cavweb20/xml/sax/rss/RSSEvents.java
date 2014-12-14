package io.github.cavweb20.xml.sax.rss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author velasco
 * @since 2006-05-15
 *  
 */
public class RSSEvents extends DefaultHandler
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(RSSEvents.class);
    
    private boolean print = false;

    public void startDocument() throws SAXException
    {
        if (LOG.isInfoEnabled())
            LOG.info("### Starting RSS feed parsing ###");
    }

    public void endDocument() throws SAXException
    {
        if (LOG.isInfoEnabled())
            LOG.info("### Ending RSS feed parsing ###");
    }

    public void startElement(String nsuri, String localname, String qname,
            Attributes arg3) throws SAXException
    {
        if(localname.equals("link") || localname.equals("description"))
            print = true;
    }

    public void characters(char[] text, int start, int length)
            throws SAXException
    {
        if (LOG.isInfoEnabled() && print)
        {
            LOG.info(new String(text, start, length));
            print = false;
        }
    }
}