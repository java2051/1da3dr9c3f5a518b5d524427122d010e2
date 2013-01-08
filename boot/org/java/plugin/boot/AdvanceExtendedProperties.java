package org.java.plugin.boot;

import java.util.Properties;

import org.java.plugin.util.ExtendedProperties;

public class AdvanceExtendedProperties extends ExtendedProperties{
	 
	 /**
     * @see java.util.Properties#Properties()
     */
    public AdvanceExtendedProperties() {
        super();
    }

    /**
     * @see java.util.Properties#Properties(java.util.Properties)
     */
    public AdvanceExtendedProperties(Properties defs) {
        super(defs);
    }
	public synchronized Object setProperty(String key, String value) {
		if (value == null )
			return null;
		value = value.trim();
		return super.setProperty(key, value) ;
	 }
	
	
}
