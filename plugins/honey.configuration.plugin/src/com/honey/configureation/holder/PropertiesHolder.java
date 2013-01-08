package com.honey.configureation.holder;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.w3c.dom.Element;

import com.honey.configureation.xmlparser.ParseElement;
import com.honey.core.utils.StringUtility;

/**
 * 配置文件中Properties节点解析
 * @author Administrator
 *
 */
public class PropertiesHolder implements ConfigurationHolder {

	public void parserElement(Element element, Properties systemProperties) {
		String  sLocations = ParseElement.getConfiguration(element, "locations", systemProperties);
		if( sLocations != null ){
			String []locations = StringUtility.parseStringsplit(sLocations);
			for(String location : locations){
				loadFile(location, systemProperties);
			}
		}
		
		ParseElement.parseAttributesForProperties(element, systemProperties);
		ParseElement.parseElementForProperties(element,systemProperties);
	}
	
	private void loadFile(String path, Properties  prop){
		BufferedInputStream inBuff = null;
		try {
			inBuff = new BufferedInputStream(new FileInputStream(path));
			if (path.endsWith(".xml"))
				prop.loadFromXML(inBuff);
			else
				prop.load(inBuff);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inBuff != null) {
				try {
					inBuff.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
}
