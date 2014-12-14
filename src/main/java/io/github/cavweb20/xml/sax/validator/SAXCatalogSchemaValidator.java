package io.github.cavweb20.xml.sax.validator;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import io.github.cavweb20.xml.sax.error.CustomErrorHandler;
import io.github.cavweb20.xml.util.SAXConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.xml.resolver.tools.CatalogResolver;
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
 * @author velasco
 * @since  2004
 */
public class SAXCatalogSchemaValidator extends DefaultHandler
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(SAXCatalogSchemaValidator.class);

    /**
     * Main executable program
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

        for (int i = 0; i < args.length; i++)
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
                parser.parse(args[i]);
                if (LOG.isInfoEnabled())
                    LOG.info(args[i] + " is valid.");
            }
            catch (SAXNotRecognizedException e)
            {
                LOG.error("Error: " + e.getLocalizedMessage());
            }
            catch (SAXNotSupportedException e)
            {
                LOG.error("Error: " + e.getLocalizedMessage());
            }
            catch (SAXException e)
            {
                LOG.error("Error: " + e.getLocalizedMessage());
                LOG.error(args[i] + " is invalid.");
            }
            catch (ParserConfigurationException e)
            {
                LOG.error("Error: " + e.getLocalizedMessage());
            }
            catch (IOException e)
            {
                LOG.error("Error: " + e.getLocalizedMessage());
            }
        }

        if (LOG.isDebugEnabled()) LOG.debug("##### End #####");
    }

}