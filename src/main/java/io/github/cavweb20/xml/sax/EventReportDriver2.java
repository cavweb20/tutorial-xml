package io.github.cavweb20.xml.sax;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import io.github.cavweb20.xml.util.XercesConstants;

/**
 * 
 * @author cavweb20
 * @since  2004-05-15
 */
public class EventReportDriver2
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(EventReportDriver2.class);

    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled()) LOG.debug("##### Start #####");

        if (args.length <= 0)
        {
            if (LOG.isInfoEnabled()) LOG.info("Usage: java EventReportDriver2 URL");
            return;
        }

        try
        {
            XMLReader parser = XMLReaderFactory.createXMLReader();
            parser.setContentHandler(new EventReport2());
            parser.setFeature(
                    XercesConstants.FEATURE_LOAD_EXTERNAL_DTD, false);
            parser.parse(args[0]);
        }
        catch (SAXException e)
        {
            LOG.error(args[0] + " is not well-formed.");
            LOG.error(e.getLocalizedMessage());
        }
        catch (IOException e)
        {
            LOG.error("IOException in the SAX parser: " + args[0]);
            LOG.error(e.getLocalizedMessage());
        }

        if (LOG.isDebugEnabled()) LOG.debug("##### End #####");
    }
}