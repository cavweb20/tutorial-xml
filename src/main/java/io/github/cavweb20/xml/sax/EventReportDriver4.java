package io.github.cavweb20.xml.sax;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import io.github.cavweb20.xml.util.XercesConstants;

/**
 * 
 * @author velasco
 * @since  2004-05-15
 */
public class EventReportDriver4
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(EventReportDriver4.class);

    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled()) LOG.debug("##### Start #####");

        if (args.length <= 0)
        {
            if (LOG.isInfoEnabled())
                LOG.info("Usage: java EventReportDriver4 URL");
            return;
        }

        try
        {
            XMLReader parser = XMLReaderFactory.createXMLReader();
            InputSource is = new InputSource(args[0]);
            parser.setContentHandler(new EventReport4());
            parser.setFeature(
                    XercesConstants.FEATURE_LOAD_EXTERNAL_DTD, false);
            parser.parse(is);
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