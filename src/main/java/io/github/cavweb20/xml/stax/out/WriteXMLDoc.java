package io.github.cavweb20.xml.stax.out;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class WriteXMLDoc
{
    private final static String NSURI = "http://imergo.com/ns/2006/books";
    private final static String NSPREFIX = "b";

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        StringWriter sw = new StringWriter();
        XMLOutputFactory ofactory;
        XMLStreamWriter serializer;

        try
        {
            ofactory = XMLOutputFactory.newInstance();
            ofactory.setProperty("javax.xml.stream.isRepairingNamespaces", Boolean.TRUE);
            serializer = ofactory.createXMLStreamWriter(sw);
            
            serializer.writeStartDocument("utf-8", "1.0");
            serializer.setPrefix(NSPREFIX, NSURI);
            serializer.setDefaultNamespace(NSURI);

            serializer.writeStartElement(NSPREFIX, "books", NSURI);
            serializer.writeNamespace(NSPREFIX, NSURI);
            serializer.writeStartElement(NSURI, "book");
            serializer.writeStartElement(NSURI, "title");
            serializer.writeCharacters("Hamlet");
            serializer.writeEndElement();
            serializer.writeStartElement(NSURI, "author");
            serializer.writeCharacters("William Shakespeare");
            serializer.writeEndElement();
            serializer.writeEndElement();
            serializer.writeStartElement(NSURI, "book");
            serializer.writeStartElement(NSURI, "title");
            serializer.writeCharacters("El Quijote");
            serializer.writeEndElement();
            serializer.writeStartElement(NSURI, "author");
            serializer.writeCharacters("Miguel de Cervantes");
            serializer.writeEndElement();
            serializer.writeEndElement();
            serializer.writeEndElement();

            serializer.writeEndDocument();
            
            System.out.println(sw.toString());
        }
        catch (XMLStreamException e)
        {
        }
    }
}
