package io.github.cavweb20.xml.sax.validator;

import io.github.cavweb20.xml.sax.error.CustomErrorHandler;
import io.github.cavweb20.xml.util.SAXConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger LOG = LoggerFactory.getLogger(SAXValidator.class);

    /**
     * Main executable program
     */
    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled()) LOG.debug("##### Start #####");

        if (args.length != 1)
        {
            if (LOG.isInfoEnabled())
                LOG.info("Usage: java SAXValidator URL");
            return;
        }

        try
        {
            XMLReader parser = XMLReaderFactory.createXMLReader();
            parser.setErrorHandler(new CustomErrorHandler());
            parser.setFeature(SAXConstants.FEATURE_VALIDATION, Boolean.TRUE);
            // crashes w/o catalog: no longer possible to load DTD from the Internet
            parser.parse(args[0]);
            if(LOG.isInfoEnabled()) 
                LOG.info(args[0] + " is valid.");
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
        catch (Exception e)
        {
            LOG.error("Error: Not possible to create the parser.\n" + e);
            System.exit(-1);
        }

        if (LOG.isDebugEnabled()) LOG.debug("##### End #####");
    }

}