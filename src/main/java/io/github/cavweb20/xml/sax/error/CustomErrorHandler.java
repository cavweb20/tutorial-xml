package io.github.cavweb20.xml.sax.error;

import java.util.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Custom SAX Error Handler.
 * 
 * @author cavweb20
 * @since  2004
 */

public class CustomErrorHandler implements ErrorHandler
{
    /**
     * Private variables.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CustomErrorHandler.class);
    private int i = 0;

    /**
     * Report warnings, and continue parsing.
     * @param e SAXParseException variable.
     */
    @Override
    public void warning(SAXParseException e) throws SAXException
    {
        LOG.warn(this.format(e));
        i++;
    }

    /**
     * Report recoverable errors, and continue parsing.
     * @param e SAXParseException variable.
     */
    @Override
    public void error(SAXParseException e) throws SAXException
    {
        LOG.error(this.format(e));
        i++;
    }

    /**
     * Report fatal errors, and continue parsing.
     * Note: results are no longer reliable once a fatal error has
     * been reported.
     * @param e SAXParseException variable.
     */
    @Override
    public void fatalError(SAXParseException e) throws SAXException
    {
        LOG.error(this.format(e));
        i++;
    }

    public int getErrorMessages()
    {
        return this.i;
    }

    private String format(SAXParseException e) throws SAXException
    {
        return new Formatter().format("[%d, %d] %s (%s)", e.getLineNumber(), e.getColumnNumber(),
                e.getLocalizedMessage(), e.getSystemId()).toString();
    }
}
