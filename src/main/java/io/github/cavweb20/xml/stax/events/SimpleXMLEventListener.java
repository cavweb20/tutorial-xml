package io.github.cavweb20.xml.stax.events;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleXMLEventListener
{
    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(SimpleXMLEventListener.class);

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        InputStream is;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser;

        if (LOG.isDebugEnabled())
            LOG.debug("##### Start #####");

        try
        {
            if (args.length < 1)
            {
                if (LOG.isInfoEnabled())
                    LOG.info("Usage: java SimpleXMLEventListener URL");
                if (LOG.isDebugEnabled())
                    LOG.debug("##### End #####");
                return;
            }

            factory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
            factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
            is = (new URL(args[0])).openStream();
            parser = factory.createXMLEventReader(is);
            while(true)
            {
                XMLEvent event = parser.peek();
                if(event.isStartDocument())
                {
                    if (LOG.isInfoEnabled())
                    {
                        LOG.info("Start document");
                        LOG.info("XML Version: " +
                                ((StartDocument)event).getVersion());
                        LOG.info("XML Encoding: " +
                                ((StartDocument)event).getCharacterEncodingScheme());
                    }
                }
                else if(event.isStartElement())
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Start tag");
                }
                else if(event.isEndElement())
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("End tag");
                }
                else if(event.isCharacters())
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Text");
                }
                else if (event.getEventType() == XMLEvent.CDATA)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Comment");
                }
                else if (event.getEventType() == XMLEvent.DTD)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Document type declaration");
                }
                else if (event.isEntityReference())
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Entity Reference");
                }
                else if (event.getEventType() == XMLEvent.SPACE)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Ignorable white space");
                }
                else if (event.getEventType() == XMLEvent.NOTATION_DECLARATION)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Notation Declaration");
                }
                else if (event.getEventType() == XMLEvent.ENTITY_DECLARATION)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Entity Declaration");
                }
                else if (event.isProcessingInstruction())
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Processing Instruction");
                }
                else if (event.isEndDocument())
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("End Document");
                    break;
                }
                if(parser.hasNext()) parser.nextEvent();
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
