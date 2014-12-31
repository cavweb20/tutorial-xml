package io.github.cavweb20.xml.dom.validator;

import io.github.cavweb20.xml.sax.error.CustomErrorHandler;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.resolver.tools.CatalogResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * @author cavweb20
 * @since  2003-07-01
 */
public class SimpleDOM2Validator
{
    
    // Setting up the logging properties
    private static final Logger LOG = LoggerFactory.getLogger(SimpleDOM2Validator.class);

    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled()) LOG.debug("##### Start #####");

        if (args.length < 1)
        {
            LOG.error("Usage: java SimpleDOM2Validator URL");
            System.exit(-1);
        }

        try
        {
            CustomErrorHandler eh = new CustomErrorHandler();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            dbf.setValidating(true);
            dbf.setExpandEntityReferences(false);
            dbf.setFeature("http://xml.org/sax/features/use-entity-resolver2", true);
            // Dangerous! Use with caution
            dbf.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);
            DocumentBuilder parser = dbf.newDocumentBuilder();
            parser.setEntityResolver(new CatalogResolver());
            parser.setErrorHandler(eh);
            parser.parse(args[0]);
            if (LOG.isInfoEnabled())
            {
                if (eh.getErrorMessages() == 0)
                {
                    LOG.info("Document is valid.");
                }
                else
                {
                    LOG.info("Document is invalid.");
                }
            }
        }
        catch (FactoryConfigurationError e)
        {
            LOG.error("Error: Cannot create factory.\n" + e.getLocalizedMessage());
            System.exit(1);
        }
        catch (ParserConfigurationException e)
        {
            LOG.error("Error: Parser configuration problem.\n" + e.getLocalizedMessage());
            System.exit(2);
        }
        catch (SAXException e)
        {
            LOG.error("Error: SAX exception." + e.getLocalizedMessage());
            System.exit(3);
        }
        catch (IOException e)
        {
            LOG.error("Error: I/O exception." + e.getLocalizedMessage());
            System.exit(4);
        }
        if (LOG.isDebugEnabled()) LOG.debug("##### End #####");
    }

}