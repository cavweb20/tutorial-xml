package io.github.cavweb20.xml.sax.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author cavweb20
 *
 */

public class TestErrorHandler implements ErrorHandler
{

    /**
     * Private variables. Setting up the logging properties.
     */
    private static Logger LOG = LoggerFactory.getLogger(TestErrorHandler.class);

    /**
     * Report warnings, and continue parsing.
     * @param e SAXParseException variable.
     */
    public void warning(SAXParseException e) throws SAXException
    {
        LOG.warn(e.getMessage());
        LOG.warn(e.getSystemId() + " - Line: " + e.getLineNumber()
                + " - Column: " + e.getColumnNumber());
    }

    /**
     * Report recoverable errors, and continue parsing.
     * @param e SAXParseException variable.
     */
    public void error(SAXParseException e) throws SAXException
    {
        LOG.error(e.getMessage());
        LOG.error(e.getSystemId() + " - Line: " + e.getLineNumber()
                + " - Column: " + e.getColumnNumber());
    }

    /**
     * Report fatal errors, and continue parsing.
     * Note: results are no longer reliable once a fatal error has
     * been reported.
     * @param e SAXParseException variable.
     */
    public void fatalError(SAXParseException e) throws SAXException
    {
        LOG.error(e.getMessage());
        LOG.error(e.getSystemId() + " - Line: " + e.getLineNumber()
                + " - Column: " + e.getColumnNumber());
    }

}