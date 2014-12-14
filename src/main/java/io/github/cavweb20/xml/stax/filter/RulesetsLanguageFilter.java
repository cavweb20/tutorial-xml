package io.github.cavweb20.xml.stax.filter;

import java.io.StringWriter;

import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RulesetsLanguageFilter
{
    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(RulesetsLanguageFilter.class);

    private final static String ruleRegistryPath =
        "/META-INF/lesson012/ruleRegistry.xml";

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String lang = "de";
        StreamSource reader = null;
        StringWriter sw = new StringWriter();
        XMLInputFactory factory = null;
        XMLOutputFactory ofactory = null;
        XMLStreamReader parser = null;
        XMLStreamWriter serializer = null;

        try
        {
            reader = new StreamSource(RulesetsLanguageFilter.class.getResourceAsStream(ruleRegistryPath));
            StreamFilter rsf = new RulesetStreamFilter(lang);
            factory = XMLInputFactory.newInstance();
            factory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
            factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
            parser = factory.createFilteredReader(
                    factory.createXMLStreamReader(reader), rsf);
        }
        catch (XMLStreamException e)
        {
            LOG.error(e.getLocalizedMessage());
            System.exit(-1);
        }

        try
        {
            ofactory = XMLOutputFactory.newInstance();
            serializer = ofactory.createXMLStreamWriter(sw);
            while(parser.hasNext())
            {
                switch(parser.getEventType())
                {
                case XMLEvent.START_DOCUMENT:
                    serializer.writeStartDocument();
                    break;
                case XMLEvent.START_ELEMENT:
                    if (parser.getNamespaceURI() == null)
                    {
                        serializer.writeStartElement(parser.getLocalName());
                    }
                    else
                    {
                        serializer.writeStartElement(
                                parser.getNamespaceURI(), parser.getLocalName());
                    }
                    for(int i = 0; i < parser.getAttributeCount(); i++)
                    {
                        if(parser.getAttributePrefix(i) == null)
                            serializer.writeAttribute(
                                    parser.getAttributeLocalName(i),
                                    parser.getAttributeValue(i));
                        else
                            serializer.writeAttribute(
                                    parser.getAttributePrefix(i),
                                    parser.getAttributeNamespace(i),
                                    parser.getAttributeLocalName(i),
                                    parser.getAttributeValue(i));
                    }
                    for (int i = 0; i < parser.getNamespaceCount(); i++)
                    {
                        serializer.writeNamespace(
                                parser.getNamespacePrefix(i),
                                parser.getNamespaceURI(i));
                    }
                    break;
                case XMLEvent.CHARACTERS:
                    serializer.writeCharacters(parser.getTextCharacters(),
                          parser.getTextStart(), parser.getTextLength());
                    break;
                case XMLEvent.END_ELEMENT:
                    serializer.writeEndElement();
                    break;
                case XMLEvent.END_DOCUMENT:
                    serializer.writeEndDocument();
                    break;
                default:
                }
                // Move to next event
                parser.next();
            }
            serializer.flush();
            System.out.println(sw.toString());
        }
        catch (XMLStreamException e)
        {
            LOG.error("here"+e.getLocalizedMessage());
            System.exit(-1);
        }
    }
}
