package io.github.cavweb20.xml.stax.validator;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import io.github.cavweb20.xml.stax.error.StAXErrorReporter;
import io.github.cavweb20.xml.stax.resolver.Resolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StAXValidator
{
    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(StAXValidator.class);

    /**
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        InputStream is;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        StAXErrorReporter xrep = new StAXErrorReporter();
        XMLEventReader parser;

        if (LOG.isDebugEnabled())
            LOG.debug("##### Start #####");

        try
        {
            if (args.length < 1)
            {
                if (LOG.isInfoEnabled())
                    LOG.info("Usage: java StAXValidator URL");
                if (LOG.isDebugEnabled())
                    LOG.debug("##### End #####");
                return;
            }

            is = (new URL(args[0])).openStream();
            factory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.FALSE);
            factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);
            factory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.TRUE);
            factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.TRUE);
            factory.setXMLReporter(xrep);
            factory.setXMLResolver(new Resolver());
            parser = factory.createXMLEventReader(is);
            while (parser.hasNext())
            {
                parser.nextEvent();
            }
            parser.close();
            if(xrep.getNErrors() == 0)
                LOG.info("Document is valid.");
            else
                LOG.info("Document is invalid.");
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
