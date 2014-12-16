package io.github.cavweb20.xml.sax;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import io.github.cavweb20.xml.util.XercesConstants;

/**
 * First basic SAX program. Set to ignore external DTDs.
 * Usage:
 *   java BasicSAX http://www.w3.org/
 * 
 * @author cavweb20
 * @since  2004-05-15
 */
public class BasicSAX
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(BasicSAX.class);

    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled()) LOG.debug("##### Start #####");

        if (args.length <= 0)
        {
            if (LOG.isInfoEnabled())
                LOG.info("Usage: java BasicSAX URL1 [URL2 URL3 ...]");
            if (LOG.isDebugEnabled()) LOG.debug("##### End #####");
            return;
        }

        for (int i = 0; i < args.length; i++)
        {
            try
            {
                XMLReader parser = XMLReaderFactory.createXMLReader();
                parser.setFeature(
                        XercesConstants.FEATURE_LOAD_EXTERNAL_DTD, false);
                parser.parse(args[i]);
                if (LOG.isInfoEnabled()) LOG.info(args[i] + " is well-formed.");
            }
            catch (SAXException e)
            {
                LOG.error(args[i] + " is not well-formed.");
                LOG.error(e.getLocalizedMessage());
            }
            catch (IOException e)
            {
                LOG.error("IOException in the SAX parser: " + args[i]);
                LOG.error(e.getLocalizedMessage());
            }
        }

        if (LOG.isDebugEnabled()) LOG.debug("##### End #####");
    }
}