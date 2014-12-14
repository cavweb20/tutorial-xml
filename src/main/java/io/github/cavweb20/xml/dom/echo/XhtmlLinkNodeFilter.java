package io.github.cavweb20.xml.dom.echo;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

public class XhtmlLinkNodeFilter implements NodeFilter
{

    /**
     * @see org.w3c.dom.traversal.NodeFilter#acceptNode(org.w3c.dom.Node)
     */
    public short acceptNode(Node n)
    {
        if(n.getLocalName().equals("a"))
            return FILTER_ACCEPT;
        return FILTER_SKIP;
    }
}
