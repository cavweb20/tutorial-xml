package io.github.cavweb20.xml.stax.filter;

import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamReader;

/**
 * Stream filter to remove <rules> from the rule registry.
 * 
 * @author velasco
 * @since  2006-05-26
 */
public class RulesetStreamFilter implements StreamFilter
{
    
    // Private components
    private String lang;
    private boolean filtered, accept;

    /**
     * Default constructor with a given language for filtering
     * Rulesets names.
     * 
     * @param lang Language that needs to be filtered.
     */
    public RulesetStreamFilter(String lang)
    {
        this.lang = lang;
        this.accept = false;
        this.filtered = false;
    }

    /**
     * accept method for the filter, allowing only rulesets, ruleset
     * and name elements.
     * 
     * @see javax.xml.stream.StreamFilter#accept(javax.xml.stream.XMLStreamReader)
     */
    public boolean accept(XMLStreamReader reader)
    {
        String name = null;
        if(reader.hasName()) name = reader.getLocalName();
        
        if(reader.getEventType() == XMLStreamReader.START_DOCUMENT ||
                reader.getEventType() == XMLStreamReader.END_DOCUMENT)
            return true;

        if(reader.isStartElement() &&
           (name.equals("rulesets") || name.equals("ruleset")))
        {
            this.accept = true;
            return true;
        }
        if(reader.isEndElement() &&
           (name.equals("rulesets") || name.equals("ruleset")))
        {
            this.accept = false;
            return true;
        }

        if(reader.isStartElement() && name.equals("name"))
        {
            int att = reader.getAttributeCount();
            for(int i = 0; i < att; i++)
            {
                if(reader.getAttributeLocalName(i).equals("lang") &&
                   reader.getAttributeValue(i).equals(this.lang))
                {
                    this.filtered = true;
                    return true;
                }
            }
        }
        else if(reader.isEndElement() && name.equals("name") && this.filtered)
        {
            this.filtered = false;
            return true;
        }

        if(reader.isCharacters() && (this.filtered && this.accept))
        {
            return true;
        }
        return false;
    }
}
