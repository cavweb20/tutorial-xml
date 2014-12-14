package io.github.cavweb20.xml.sax.validator;

import java.io.IOException;

import io.github.cavweb20.xml.sax.error.ValidatorErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SimpleSAXValidator
{
    private static Logger LOG = LoggerFactory.getLogger(SimpleSAXValidator.class);

    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled()) LOG.debug("##### Start #####");

        if (args.length <= 0)
        {
            if (LOG.isInfoEnabled()) LOG.info("Usage: java SimpleSAXValidator URL");
            return;
        }

        XMLReader parser;
        ValidatorErrorHandler eh = new ValidatorErrorHandler();
        try
        {
            parser = XMLReaderFactory.createXMLReader();
            parser.setErrorHandler(eh);
            parser.setFeature("http://xml.org/sax/features/validation", true);
            parser.setFeature("http://apache.org/xml/features/validation/schema", true);
            // crashes w/o catalog: no longer possible to load DTD from the Internet
            parser.parse(args[0]);
            if(eh.getErrorMessages() == 0) LOG.info(args[0] + " is valid.");
            else LOG.info(args[0] + " is invalid.");
        }
        catch (SAXException e)
        {
            LOG.error(e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (IOException e)
        {
            LOG.error(e.getLocalizedMessage());
            System.exit(-1);
        }

        if (LOG.isDebugEnabled()) LOG.debug("##### End #####");
    }

}
