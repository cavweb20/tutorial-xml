package io.github.cavweb20.xml.stax.error;

import javax.xml.stream.Location;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLStreamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author velasco
 *
 */
public class StAXErrorReporter implements XMLReporter
{
    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(StAXErrorReporter.class);

    // Setting validity property
    private int nerrors = 0;

    /**
     * @see javax.xml.stream.XMLReporter#report(java.lang.String, java.lang.String, java.lang.Object, javax.xml.stream.Location)
     */
    public void report(String message, String errorType,
            Object relatedInformation, Location loc) throws XMLStreamException
    {
        if (LOG.isInfoEnabled())
            LOG.info(" [" + loc.getLineNumber() + ":" + loc.getColumnNumber() +
                     "] " + errorType + ": " + message);
        this.nerrors++;
    }

    /**
     * @return
     */
    public int getNErrors()
    {
        return nerrors;
    }

}
