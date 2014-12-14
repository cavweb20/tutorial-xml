package io.github.cavweb20.xml.dom.parser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.xml.resolver.tools.CatalogResolver;
import org.xml.sax.SAXException;

/**
 * @author velasco
 * @since  2003-07-01
 */
public class SimpleDOM2Parser
{
    
    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(SimpleDOM2Parser.class);

    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled()) LOG.debug("##### Start #####");

        if (args.length < 1)
        {
            LOG.error("Usage: java SimpleDOM2Parser URL");
            System.exit(-1);
        }

        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = dbf.newDocumentBuilder();
            parser.setEntityResolver(new CatalogResolver());
            parser.parse(args[0]);
            if (LOG.isInfoEnabled()) LOG.info("Document is well-formed.");
        }
        catch (FactoryConfigurationError e)
        {
            LOG.error("Error: Cannot create factory.\n" + e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (ParserConfigurationException e)
        {
            LOG.error("Error: Parser configuration problem.\n" + e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (SAXException e)
        {
            LOG.error("Error: SAX exception." + e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (IOException e)
        {
            LOG.error("Error: I/O exception." + e.getLocalizedMessage());
            System.exit(-1);
        }
        if (LOG.isDebugEnabled()) LOG.debug("##### End #####");
    }

}