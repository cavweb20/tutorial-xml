package io.github.cavweb20.xml.sax.validator;

import io.github.cavweb20.xml.sax.error.CustomErrorHandler;
import java.io.IOException;
import org.apache.xml.resolver.tools.CatalogResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SimpleSAXValidator
{
    private static final Logger LOG = LoggerFactory.getLogger(SimpleSAXValidator.class);

    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled()) LOG.debug("##### Start #####");

        if (args.length <= 0)
        {
            if (LOG.isInfoEnabled()) LOG.info("Usage: java SimpleSAXValidator URL");
            return;
        }

        XMLReader parser;
        CustomErrorHandler eh = new CustomErrorHandler();
        try
        {
            parser = XMLReaderFactory.createXMLReader();
            parser.setErrorHandler(eh);
            // crashes w/o catalog: no longer possible to load DTD from the Internet
            parser.setEntityResolver(new CatalogResolver());
            parser.setFeature("http://xml.org/sax/features/validation", true);
            // Dangerous! Use with caution
            parser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);
            parser.parse(args[0]);
            if(eh.getErrorMessages() == 0) LOG.info(args[0] + " is valid.");
            else LOG.info(args[0] + " is invalid.");
        }
        catch (SAXException | IOException e)
        {
            LOG.error(e.getLocalizedMessage());
            System.exit(1);
        }

        if (LOG.isDebugEnabled()) LOG.debug("##### End #####");
    }

}
