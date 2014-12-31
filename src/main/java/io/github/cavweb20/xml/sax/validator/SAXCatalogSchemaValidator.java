package io.github.cavweb20.xml.sax.validator;

import io.github.cavweb20.xml.sax.error.CustomErrorHandler;
import io.github.cavweb20.xml.util.SAXConstants;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.xml.resolver.tools.CatalogResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Typical example of SAX validator with a DTD read from a catalog.
 * Test without network connection.
 * 
 * Usage:
 * - java SAXCatalogSchemaValidator http://bentoweb.org/refs/rulesets.xml
 * 
 * @author cavweb20
 * @since  2004
 */
public class SAXCatalogSchemaValidator extends DefaultHandler
{

    // Setting up the logging properties
    private static final Logger LOG = LoggerFactory.getLogger(SAXCatalogSchemaValidator.class);

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
                LOG.info("Usage: java SAXCatalogSchemaValidator URL1 [URL2 URL3 ...]");
            return;
        }

        for (String arg : args)
        {
            try
            {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                factory.setNamespaceAware(true);
                factory.setValidating(true);
                XMLReader parser = factory.newSAXParser().getXMLReader();
                parser.setProperty(SAXConstants.PROPERTY_SCHEMA_LANGUAGE,
                        XMLConstants.W3C_XML_SCHEMA_NS_URI);
                parser.setEntityResolver(new CatalogResolver());
                parser.setErrorHandler(new CustomErrorHandler());
                parser.parse(arg);
                if (LOG.isInfoEnabled())
                {
                    LOG.info(arg + " is valid.");
                }
            }
            catch (SAXNotRecognizedException | SAXNotSupportedException | ParserConfigurationException | IOException e)
            {
                LOG.error("Error: " + e.getLocalizedMessage());
            }
            catch (SAXException e)
            {
                LOG.error("Error: " + e.getLocalizedMessage());
                LOG.error(arg + " is invalid.");
            }
        }

        if (LOG.isDebugEnabled()) LOG.debug("##### End #####");
    }

}