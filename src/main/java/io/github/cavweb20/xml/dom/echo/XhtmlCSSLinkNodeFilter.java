package io.github.cavweb20.xml.dom.echo;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

public class XhtmlCSSLinkNodeFilter implements NodeFilter
{

    /**
     * @see org.w3c.dom.traversal.NodeFilter#acceptNode(org.w3c.dom.Node)
     */
    public short acceptNode(Node n)
    {
        Element elem = (Element)n;
        if(elem.getLocalName().equals("link") &&
           elem.getAttribute("type").equals("text/css"))
            return FILTER_ACCEPT;
        return FILTER_SKIP;
    }
}
