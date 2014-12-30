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

public class EchoLocatorEventName
{
    // Setting up the logging properties
    private static final Logger LOG = LoggerFactory.getLogger(EchoLocatorEventName.class);

    /**
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
                    LOG.info("Usage: java EchoLocatorEventName URL");
                if (LOG.isDebugEnabled())
                    LOG.debug("##### End #####");
                return;
            }

            factory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
            factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
            factory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.FALSE);
            is = (new URL(args[0])).openStream();
            parser = factory.createXMLStreamReader(is);
            while(true)
            {
                int event = parser.getEventType();
                switch(event)
                {
                case XMLStreamConstants.START_ELEMENT:
                    if (LOG.isInfoEnabled())
                    {
                        LOG.info("[" + parser.getLocation().getLineNumber() +
                                 ":" + parser.getLocation().getColumnNumber() +
                                 "] ");
                        LOG.info("  Local name: " + parser.getLocalName());
                        LOG.info("  Namespace URI: " + parser.getNamespaceURI());
                        LOG.info("  Namespace prefix: " + parser.getPrefix());
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (LOG.isInfoEnabled())
                    {
                        LOG.info("[" + parser.getLocation().getLineNumber() +
                                 ":" + parser.getLocation().getColumnNumber() +
                                 "] " + parser.getName().toString());
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
