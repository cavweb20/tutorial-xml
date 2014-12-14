package io.github.cavweb20.xml.neko;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * @author cavweb20
 * @since 2003-07-30
 */
public class RemoveAttrByName implements NodeFilter
{

    /**
     * 
     * @see org.w3c.dom.traversal.NodeFilter#acceptNode(org.w3c.dom.Node)
     */
    public short acceptNode(Node node)
    {
        Element candidate = (Element) node;
        short temp = FILTER_SKIP;

        for (int i = 0; i < XHTMLConstants.ATTRIBUTES_TO_BE_REMOVED.length; i++)
        {
            if (candidate.hasAttribute(XHTMLConstants.ATTRIBUTES_TO_BE_REMOVED[i]))
                temp = FILTER_ACCEPT;
        }

        return temp;
    }

}
