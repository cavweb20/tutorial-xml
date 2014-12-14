package io.github.cavweb20.xml.sax.echo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author cavweb20
 * @since 08/09/2002
 */
public class TestEntityResolver implements EntityResolver
{

    /**
     * Private variables. Setting up the logging properties.
     */
    public static Logger LOG = LoggerFactory.getLogger(TestEntityResolver.class);

    /**
     * Function to map public IDs to system IDs.
     */
    public InputSource resolveEntity(String publicID, String systemID)
            throws SAXException, IOException
    {
        return null;
    }

}