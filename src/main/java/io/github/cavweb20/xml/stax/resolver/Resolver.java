package io.github.cavweb20.xml.stax.resolver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.xml.resolver.tools.CatalogResolver;

/**
 * @author carlos
 *
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
        String uri = resolver.getResolvedEntity(publicID, systemID);
        Source source = null;
        InputStream is = null;
        try
        {
            source = resolver.resolve(uri, baseURI);
            String path = source.getSystemId().replaceAll("%20", " ");
            is = new FileInputStream(path.substring(5));
        }
        catch (TransformerException e)
        {
            LOG.error(e.getLocalizedMessage());
            throw new XMLStreamException(e);
        }
        catch (FileNotFoundException e)
        {
            LOG.error(e.getLocalizedMessage());
            throw new XMLStreamException(e);
        }

        return is;
    }

}
