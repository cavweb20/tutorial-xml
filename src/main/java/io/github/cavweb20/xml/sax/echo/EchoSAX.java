package io.github.cavweb20.xml.sax.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import io.github.cavweb20.xml.util.XercesConstants;

/**
 *
 * @author cavweb20
 * @version 1.0
 * @since 08/09/2002
 */
public class EchoSAX
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(EchoSAX.class);

    /**
     * Main executable program
     */
    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("##### Start #####");

        if (args.length <= 0)
        {
            if (LOG.isInfoEnabled())
                LOG.info("Usage: java EchoSAX URL");
            return;
        }

        try
        {
            XMLReader parser = XMLReaderFactory.createXMLReader();
            parser.setProperty("http://xml.org/sax/properties/lexical-handler",
                    new TestLexicalHandler());
            parser.setFeature(
                    XercesConstants.FEATURE_LOAD_EXTERNAL_DTD, false);
            parser.setContentHandler(new TestContentHandler());
            parser.setErrorHandler(new TestErrorHandler());
            parser.setEntityResolver(new TestEntityResolver());
            parser.parse(args[0]);
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

        if (LOG.isDebugEnabled())
            LOG.debug("##### End #####");
    }
}
