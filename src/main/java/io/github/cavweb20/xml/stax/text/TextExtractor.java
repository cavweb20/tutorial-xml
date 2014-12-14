package io.github.cavweb20.xml.stax.text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextExtractor
{
    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(TextExtractor.class);

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        InputStream is;
        Writer out = new OutputStreamWriter(System.out);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser;

        if (LOG.isDebugEnabled())
            LOG.debug("##### Start #####");

        try
        {
            if (args.length < 1)
            {
                if (LOG.isInfoEnabled())
                    LOG.info("Usage: java TextExtractor URL");
                if (LOG.isDebugEnabled())
                    LOG.debug("##### End #####");
                return;
            }

            factory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
            factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
            is = (new URL(args[0])).openStream();
            parser = factory.createXMLStreamReader(is);
            for (parser.next(); parser.hasNext(); parser.next())
            {
                if (parser.isCharacters() || parser.isWhiteSpace())
                    out.write(parser.getTextCharacters(),
                            parser.getTextStart(), parser.getTextLength());
            }
            parser.close();
            out.flush();

            if (LOG.isDebugEnabled())
                LOG.debug("##### End #####");
            out.close();
        }
        catch (XMLStreamException e)
        {
            LOG.error(args[0] + " is not well-formed.");
            LOG.error(e.getLocalizedMessage());
        }
        catch (MalformedURLException e)
        {
            LOG.error(args[0] + " is not a valid URL.");
            LOG.error(e.getLocalizedMessage());
        }
        catch (IOException e)
        {
            LOG.error(args[0] + " cannot be open.");
            LOG.error(e.getLocalizedMessage());
        }
    }

}
