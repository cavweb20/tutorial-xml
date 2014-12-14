package io.github.cavweb20.xml.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMImplementation;

/**
 * @author cavweb20
 * @since  2003-07-01
 */
public class DOMModulesChecker
{
    
    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(DOMModulesChecker.class);
    
    /**
     * Array with module names to check.
     */
    private static final String[] moduleNames = {
        "Core", "XML", "LS", "Validation", "XPath",
        "Events", "MutationEvents",
        "Range", "Traversal", "Views", "StyleSheets", "CSS", "CSS2",
        "HTML", "UIEvents", "MouseEvents", "HTMLEvents" };
    
    /**
     * Checks whether some features are implemented.
     * 
     * @param di DOMImplementation to be checked.
     */
    public static void check(DOMImplementation di)
    {
        for (int i = 0; i < moduleNames.length; i++)
        {
            if (di.hasFeature(moduleNames[i], "3.0"))
            {
                if (LOG.isInfoEnabled())
                    LOG.info("Support for " + moduleNames[i]
                        + " is included in the DOM 3.0 implementation.");
            }
            else if (di.hasFeature(moduleNames[i], "2.0"))
            {
                if (LOG.isInfoEnabled())
                    LOG.info("Support for " + moduleNames[i]
                             + " is included in the DOM 2.0 implementation.");
            }
            else if (di.hasFeature(moduleNames[i], "1.0"))
            {
                if (LOG.isInfoEnabled())
                    LOG.info("Support for " + moduleNames[i]
                             + " is included in the DOM 1.0 implementation.");
            }
            else
            {
                if (LOG.isInfoEnabled())
                    LOG.info("Support for " + moduleNames[i]
                             + " is not included in any DOM implementation.");
            }
        }
    }
    
    public static void main(String[] args)
    {
        
        if (LOG.isDebugEnabled())
            LOG.debug("##### Start #####");
        
        if (args.length > 0)
        {
            LOG.error("Usage: java DOMModulesChecker");
            System.exit(-1);
        }
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docbuild = null;
        DOMImplementation domImpl = null;
        
        try
        {
            docbuild = dbf.newDocumentBuilder();
        }
        catch (ParserConfigurationException pce)
        {
            LOG.error("Error: Exception in parser configuration");
            pce.printStackTrace();
        }
        domImpl = docbuild.getDOMImplementation();
        
        check(domImpl);
        
        if (LOG.isDebugEnabled())
            LOG.debug("##### End #####");
    }
}