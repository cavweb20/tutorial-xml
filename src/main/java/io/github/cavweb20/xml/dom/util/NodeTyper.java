package io.github.cavweb20.xml.dom.util;

import org.w3c.dom.Node;

public class NodeTyper
{

    public static String getTypeName(Node node)
    {

        int type = node.getNodeType();

        switch (type)
        {
            case Node.ELEMENT_NODE:
                return "Element";
            case Node.ATTRIBUTE_NODE:
                return "Attribute";
            case Node.TEXT_NODE:
                return "Text";
            case Node.CDATA_SECTION_NODE:
                return "CDATA Section";
            case Node.ENTITY_REFERENCE_NODE:
                return "Entity Reference";
            case Node.ENTITY_NODE:
                return "Entity";
            case Node.PROCESSING_INSTRUCTION_NODE:
                return "Processing Instruction";
            case Node.COMMENT_NODE:
                return "Comment";
            case Node.DOCUMENT_NODE:
                return "Document";
            case Node.DOCUMENT_TYPE_NODE:
                return "Document Type Declaration";
            case Node.DOCUMENT_FRAGMENT_NODE:
                return "Document Fragment";
            case Node.NOTATION_NODE:
                return "Notation";
            default:
                return "Unknown Type";
        }

    }

}