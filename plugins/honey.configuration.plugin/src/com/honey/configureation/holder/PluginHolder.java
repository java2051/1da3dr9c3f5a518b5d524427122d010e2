package com.honey.configureation.holder;

import java.util.Properties;

import org.w3c.dom.Element;

import com.honey.configureation.xmlparser.ParseElement;
import com.honey.core.utils.StringUtility;

public class PluginHolder implements ConfigurationHolder{

	private String pluginid ; 
	
	private Element pluginElement ;
		
	@Override
	public void parserElement(Element element, Properties properties) {
		this.pluginid = ParseElement.getConfiguration(element, "id", properties);
		if(! StringUtility.stringHasValue(pluginid) ){
			this.pluginid = ParseElement.getConfiguration(element, "name", properties);
		}
		this.pluginElement = element; 
	}

	public String getPluginid() {
		return pluginid;
	}

	public Element getPluginElement() {
		return pluginElement;
	}

	@Override
	public String toXmlElement() {
		
		return null;
	}

	@Override
	public void validate() {
		
	}
}
