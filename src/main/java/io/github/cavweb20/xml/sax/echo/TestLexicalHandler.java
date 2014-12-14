package io.github.cavweb20.xml.sax.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/**
 * <p>Description: Class to handle events when parsing SAX lexical events.</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: FIT</p>
 * @author velasco A. Velasco
 * @version 1.0
 * @since 08/09/2002
 */
public class TestLexicalHandler implements LexicalHandler
{

    /**
     * Private variables. Setting up the logging properties.
     */
    private static Logger LOG = LoggerFactory.getLogger(TestLexicalHandler.class);

    /**
     * Function to handle DTDs.
     */
    public void startDTD(String name, String publicID, String systemID)
        throws SAXException
    {
        LOG.info("  DTD name: " + name);
        LOG.info("  DTD public ID: " + publicID);
        LOG.info("  DTD system ID: " + systemID);
    }

    /**
     * Function to finish handling DTDs.
     */
    public void endDTD() throws SAXException
    {
        // Do nothing
    }

    /**
     * Function to handle entity names.
     */
    public void startEntity(String name) throws SAXException
    {
        // Skip the DOCTYPE DTD (to be handled by startDTD()
        if (!name.equals("[dtd]"))
            LOG.info("  ENTITY: " + name);
    }

    /**
     * Function to handle end of entities.
     */
    public void endEntity(String name) throws SAXException
    {
        // Do nothing
    }

    /**
     * Function to handle CDATA.
     */
    public void startCDATA() throws SAXException
    {
        LOG.info("START CDATA:");
    }

    /**
     * Function to handle end of CDATA.
     */
    public void endCDATA() throws SAXException
    {
        LOG.info("END CDATA:");
    }

    /**
     * Function to handle comments.
     */
    public void comment(char[] ch, int start, int length) throws SAXException
    {
        String s = new String(ch, start, length);
        if (!s.trim().equals(""))
            LOG.info("  COMMENT: |" + s + "|");
    }

}
