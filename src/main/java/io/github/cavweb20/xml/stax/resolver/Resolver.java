package io.github.cavweb20.xml.stax.resolver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.xml.resolver.tools.CatalogResolver;
import org.xml.sax.InputSource;

/**
 * @author cavweb20
 */
public class Resolver implements XMLResolver
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(Resolver.class);

    // Setting up the catalog resolver
    private static final CatalogResolver resolver = new CatalogResolver();

    /**
     * 
     * @see javax.xml.stream.XMLResolver#resolveEntity(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Object resolveEntity(String publicID, String systemID, String baseURI, String namespace)
        throws XMLStreamException
    {
        InputSource source = resolver.resolveEntity(publicID, systemID);

        return source.getByteStream();
    }
}
