package io.github.cavweb20.xml.stax.resolver;

import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;
import org.apache.xml.resolver.tools.CatalogResolver;
import org.xml.sax.InputSource;

/**
 * @author cavweb20
 */
public class Resolver implements XMLResolver
{

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
