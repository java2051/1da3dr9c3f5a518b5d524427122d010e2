package com.honey.calculator;

import java.util.Properties;

import org.w3c.dom.Element;

import com.honey.configureation.holder.ConfigurationHolder;
import com.honey.configureation.xmlparser.ParseElement;
import com.honey.core.EmptyPlugin;

public class CalculatorPlugin extends EmptyPlugin  implements ConfigurationHolder{
	
	private boolean isObfuscate = false ;
	
	private String replaceRegex =null;
	
	private String replacement = "";

	@Override
	public void parserElement(Element element, Properties properties) {
		String tmp  = ParseElement.getConfiguration(element, "useModel", properties);
		if("Obfuscate".equalsIgnoreCase( tmp )){
			isObfuscate = true ;
		}
		tmp  = ParseElement.getConfiguration(element, "replaceRegex", properties);
		if( tmp != null){
			replaceRegex = tmp;
			tmp  = ParseElement.getConfiguration(element, "replacement", properties);
			if( tmp != null){
				replacement = tmp;
			}
		}
	}
	
	@Override
	public String toXmlElement() {
		return null;
	}

	@Override
	public void validate() {
	}

	public boolean isObfuscate() {
		return isObfuscate;
	}

	public String getReplaceRegex() {
		return replaceRegex;
	}

	public String getReplacement() {
		return replacement;
	}
	
	
}
