package io.github.cavweb20.xml.stax.events;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RSSFilter
{
    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(RSSFilter.class);

    /**
     * Example URL: http://feeds.feedburner.com/oreillynet/articles http://feeds.wired.com/wired/index
     * @param args
     */
    public static void main(String[] args)
    {
        InputStream is;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser;

        if (LOG.isDebugEnabled())
            LOG.debug("##### Start #####");

        try
        {
            if (args.length < 1)
            {
                if (LOG.isInfoEnabled())
                    LOG.info("Usage: java RSSFilter URL");
                if (LOG.isDebugEnabled())
                    LOG.debug("##### End #####");
                return;
            }

            factory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
            factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
            is = (new URL(args[0])).openStream();
            parser = factory.createXMLStreamReader(is);
            while(true)
            {
                int event = parser.getEventType();
                switch(event)
                {
                case XMLStreamConstants.START_ELEMENT:
                    if(parser.getLocalName().equals("title") ||
                            parser.getLocalName().equals("description"))
                    {
                        System.out.print(parser.getLocalName() + ": ");
                        System.out.println(parser.getElementText());
                    }
                    break;
                default:
                    break;
                }
                if(parser.hasNext()) parser.next();
                else break;
            }
            parser.close();
        }
        catch (XMLStreamException e)
        {
            LOG.error(args[0] + " is not well-formed.");
            LOG.error(e.getLocalizedMessage());
        }
        catch (MalformedURLException e)
        {
            LOG.error(args[0] + " is not a valid URL.");
            LOG.error(e.getLocalizedMessage());
        }
        catch (IOException e)
        {
            LOG.error(args[0] + " cannot be open.");
            LOG.error(e.getLocalizedMessage());
        }

        if (LOG.isDebugEnabled())
            LOG.debug("##### End #####");
    }
}
