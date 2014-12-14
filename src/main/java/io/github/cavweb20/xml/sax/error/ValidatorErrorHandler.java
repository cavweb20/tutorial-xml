package io.github.cavweb20.xml.sax.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidatorErrorHandler implements ErrorHandler
{
    private static Logger LOG = LoggerFactory.getLogger(ValidatorErrorHandler.class);
    private int i = 0;

    /**
     * Report warnings, and continue parsing.
     * @param e SAXParseException variable.
     */
    public void warning(SAXParseException e) throws SAXException
    {
        LOG.warn("[" + e.getLineNumber() + ":" + e.getColumnNumber() + "] " +
                e.getSystemId() + "- " + e.getLocalizedMessage());
    }

    /**
     * Report recoverable errors, and continue parsing.
     * @param e SAXParseException variable.
     */
    public void error(SAXParseException e) throws SAXException
    {
        LOG.error("[" + e.getLineNumber() + ":" + e.getColumnNumber() + "] " +
                  e.getSystemId() + "- " + e.getLocalizedMessage());
        i++;
    }

    /**
     * Report fatal errors, and continue parsing.
     * Note: results are no longer reliable once a fatal error has
     * been reported.
     * @param e SAXParseException variable.
     */
    public void fatalError(SAXParseException e) throws SAXException
    {
        LOG.error("[" + e.getLineNumber() + ":" + e.getColumnNumber() + "] " +
                e.getSystemId() + "- " + e.getLocalizedMessage());
        i++;
    }

    public int getErrorMessages()
    {
        return this.i;
    }
}
