package io.github.cavweb20.xml.sax.validator;

import io.github.cavweb20.xml.sax.error.CustomErrorHandler;
import io.github.cavweb20.xml.util.SAXConstants;
import java.io.IOException;
import org.apache.xml.resolver.tools.CatalogResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Typical example of SAX validator with a DTD.
 * 
 * Usage:
 * - java SAXValidator src/main/resources/META-INF/lesson002/greeting.xml
 * - java SAXValidator src/main/resources/META-INF/lesson002/greeting_invalid.xml
 * - java SAXValidator http://imergo.de/
 * 
 * @author cavweb20
 * @since  2004
 */
public class SAXValidator extends DefaultHandler
{

    // Setting up the logging properties
    private static final Logger LOG = LoggerFactory.getLogger(SAXValidator.class);

    /**
     * Main executable program
     * @param args
     */
    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled()) LOG.debug("##### Start #####");

        if (args.length < 1)
        {
            if (LOG.isInfoEnabled())
                LOG.info("Usage: java SAXValidator URL");
            return;
        }

        try
        {
            CustomErrorHandler eh = new CustomErrorHandler();
            XMLReader parser = XMLReaderFactory.createXMLReader();
            parser.setErrorHandler(eh);
            // crashes w/o catalog: no longer possible to load DTD from the Internet
            parser.setEntityResolver(new CatalogResolver());
            parser.setFeature(SAXConstants.FEATURE_VALIDATION, Boolean.TRUE);
            // Dangerous! Use with caution
            parser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);
            parser.parse(args[0]);
            if(eh.getErrorMessages() == 0) LOG.info(args[0] + " is valid.");
            else LOG.info(args[0] + " is invalid.");
        }
        catch (SAXNotRecognizedException e)
        {
            LOG.error("Error: Not recognized feature.\n" + e);
            System.exit(-1);
        }
        catch (SAXNotSupportedException e)
        {
            LOG.error("Error: Not possible to set up parser feature.\n" + e);
            System.exit(-1);
        }
        catch (SAXException | IOException e)
        {
            LOG.error("Error: Not possible to create the parser.\n" + e);
            System.exit(-1);
        }

        if (LOG.isDebugEnabled()) LOG.debug("##### End #####");
    }

}