package io.github.cavweb20.xml.sax.rss;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import io.github.cavweb20.xml.util.XercesConstants;


/**
 * @author cavweb20
 * @since 2006-05-15
 *  
 */
public class ParsingRSSEvents
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(ParsingRSSEvents.class);
    private static String MyURL =
        "http://rss.slashdot.org/Slashdot/slashdot";

    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled()) LOG.debug("##### Start #####");

        try
        {
            XMLReader parser = XMLReaderFactory.createXMLReader();
            InputSource is = new InputSource(MyURL);
            parser.setFeature(
                    XercesConstants.FEATURE_LOAD_EXTERNAL_DTD, Boolean.FALSE);
            parser.setContentHandler(new RSSEvents());
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