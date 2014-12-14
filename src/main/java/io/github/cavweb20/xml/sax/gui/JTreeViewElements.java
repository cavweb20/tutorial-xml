package io.github.cavweb20.xml.sax.gui;

/**
 * @author velasco
 * @since May 17, 2004
 *  
 */
import java.awt.Font;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import io.github.cavweb20.xml.util.XercesConstants;

public class JTreeViewElements extends DefaultHandler
{

    // Setting up the logging properties
    private static Logger LOG = LoggerFactory.getLogger(JTreeViewElements.class);

    private Stack<MutableTreeNode> nodes;

    // Initialize the per-document data structures
    public void startDocument() throws SAXException
    {

        // The stack needs to be reinitialized for each document
        // because an exception might have interrupted parsing of a
        // previous document, leaving an unempty stack.
        nodes = new Stack<MutableTreeNode>();

    }

    // Make sure we always have the root element
    private TreeNode root;

    // Initialize the per-element data structures
    public void startElement(String namespaceURI, String localName, String qualifiedName,
                    Attributes atts)
    {

        String data;
        if (namespaceURI.equals(""))
            data = localName;
        else
        {
            data = '{' + namespaceURI + "} " + qualifiedName;
        }
        MutableTreeNode node = new DefaultMutableTreeNode(data);
        try
        {
            MutableTreeNode parent = (MutableTreeNode) nodes.peek();
            parent.insert(node, parent.getChildCount());
        }
        catch (EmptyStackException e)
        {
            root = node;
        }
        nodes.push(node);

    }

    public void endElement(String namespaceURI, String localName, String qualifiedName)
    {
        nodes.pop();
    }

    // Flush and commit the per-document data structures
    public void endDocument()
    {

        JTree tree = new JTree(root);
        JScrollPane treeView = new JScrollPane(tree);
        JFrame f = new JFrame("XML Tree");
        Font font = new Font("Tahoma", Font.PLAIN, 28);

        tree.setFont(font);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(treeView);
        f.pack();
        f.setVisible(true);

    }

    public static void main(String[] args)
    {
        if (args.length <= 0)
        {
            if (LOG.isInfoEnabled()) LOG.info("Usage: java EventReportDriver2 URL");
            return;
        }

        for (int i = 0; i < args.length; i++)
        {
            try
            {
                XMLReader parser = XMLReaderFactory.createXMLReader();
                parser.setFeature(
                        XercesConstants.FEATURE_LOAD_EXTERNAL_DTD, Boolean.FALSE);
                ContentHandler handler = new JTreeViewElements();
                parser.setContentHandler(handler);
                parser.parse(args[i]);
            }
            catch (SAXException e)
            {
                LOG.error(args[i] + " is not well-formed.");
                LOG.error(e.getLocalizedMessage());
            }
            catch (IOException e)
            {
                LOG.error("IOException in the SAX parser: " + args[i]);
                LOG.error(e.getLocalizedMessage());
            }
        }

    }
}