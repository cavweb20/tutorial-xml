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

public class SimpleEventListener
{
    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(SimpleEventListener.class);

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
                    LOG.info("Usage: java SimpleEventListener URL");
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
                if (event == XMLStreamConstants.START_DOCUMENT)
                {
                    if (LOG.isInfoEnabled())
                    {
                        LOG.info("Start document");
                        LOG.info("XML Version: " + parser.getVersion());
                        LOG.info("XML Encoding: " + parser.getCharacterEncodingScheme());
                    }
                }
                else if (event == XMLStreamConstants.START_ELEMENT)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Start tag");
                }
                else if (event == XMLStreamConstants.END_ELEMENT)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("End tag");
                }
                else if (event == XMLStreamConstants.CHARACTERS)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Text");
                }
                else if (event == XMLStreamConstants.CDATA)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("CDATA Section");
                }
                else if (event == XMLStreamConstants.COMMENT)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Comment");
                }
                else if (event == XMLStreamConstants.DTD)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Document type declaration");
                }
                else if (event == XMLStreamConstants.ENTITY_REFERENCE)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Entity Reference");
                }
                else if (event == XMLStreamConstants.SPACE)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Ignorable white space");
                }
                else if (event == XMLStreamConstants.NOTATION_DECLARATION)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Notation Declaration");
                }
                else if (event == XMLStreamConstants.ENTITY_DECLARATION)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Entity Declaration");
                }
                else if (event == XMLStreamConstants.PROCESSING_INSTRUCTION)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("Processing Instruction");
                }
                else if (event == XMLStreamConstants.END_DOCUMENT)
                {
                    if (LOG.isInfoEnabled())
                        LOG.info("End Document");
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
