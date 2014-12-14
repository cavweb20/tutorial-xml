package io.github.cavweb20.xml.xpath;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import io.github.cavweb20.xml.util.XercesConstants;

/**
 * XPath example. Usage: java TestXpath META-INF/lesson08/shakespeare/hamlet.xml
 * 
 * @author velasco
 * @since  2008-05-25
 */
public class TestXpath
{
    
    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(TestXpath.class);
    
    public static void main(String[] args)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("##### Start #####");
        
        if (args.length != 1)
        {
            LOG.error("Usage: java TestXpath URL");
            System.exit(-1);
        }
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XercesConstants.FEATURE_LOAD_EXTERNAL_DTD, false);
            DocumentBuilder parser = factory.newDocumentBuilder();
            XPath xpath = XPathFactory.newInstance().newXPath();
            Node root = null;
            if(args[0].startsWith("http:"))
                root = parser.parse(args[0]);
            else
                root = parser.parse(new InputSource(
                        TestXpath.class.getClassLoader().getResourceAsStream(args[0])));
            XPathExpression xcharacters = xpath.compile("count(//PERSONA)");
            XPathExpression xlines = xpath.compile(
                    "count(//LINE[preceding-sibling::SPEAKER='HAMLET'])");
            if (LOG.isInfoEnabled())
                LOG.info("URL: " + args[0] + " has");
            if (LOG.isInfoEnabled())
                LOG.info(xcharacters.evaluate(root, XPathConstants.STRING)
                        + " characters");
            if (LOG.isInfoEnabled())
                LOG.info("Hamlet actor must learn "
                        + xlines.evaluate(root, XPathConstants.STRING)
                        + " lines");
        }
        catch (ParserConfigurationException e)
        {
            LOG.error("Error: Parser configuration problem.\n"
                    + e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (SAXException e)
        {
            LOG.error("Error: SAX Exception.\n" + e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (IOException e)
        {
            LOG.error("Error: File does not exist.\n" + e.getLocalizedMessage());
            System.exit(-1);
        }
        catch (XPathExpressionException e)
        {
            LOG.error("Error: XPath problem.\n" + e.getLocalizedMessage());
            System.exit(-1);
        }
        if (LOG.isDebugEnabled())
            LOG.debug("##### End #####");
        
    }
}