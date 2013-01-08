package com.honey.configureation.holder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.honey.configureation.xmlparser.ParseElement;
import com.honey.core.utils.Constant;
import com.honey.core.utils.StringUtility;

/**
 * 配置文件中import节点解析
 * @author Administrator
 *
 */
public class ImportHolder implements ConfigurationHolder {
	private static Map<String, Class<?>> holders =new HashMap<String,  Class<?>>();
	static{
		holders.put("JDBCCONNECTION",JDBCConnectionHolder.class);
		holders.put("PLUGIN",PluginHolder.class);
		holders.put("PROPERTIES",PropertiesHolder.class);
	}
	
	private List<ConfigurationHolder> importHolders ;
	
	private Map<ConfigurationHolder, Element>  elements ;
	
	public ImportHolder(){
		importHolders = new ArrayList<ConfigurationHolder>();
		elements = new  HashMap<ConfigurationHolder, Element>();
	}

	public void parserElement(Element element, Properties properties) {
		String sImportFiles = ParseElement.getConfiguration(element, "resource", properties);
		if( sImportFiles == null ){
			sImportFiles  = ParseElement.getTextContent(element, properties);
		} 
		if( sImportFiles != null ){
			String []importFiles = StringUtility.parseStringsplit(sImportFiles);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				for(String importFile : importFiles ){
					System.out.println("Loading Config file : " + importFile);
					File file = new File(importFile);
					Document  document  =  builder.parse( file );
					Element rootNode = document.getDocumentElement();
					String version =  ParseElement.getConfiguration(rootNode, "version", properties);
					if(! Constant.VERSION.equals(version) ){
						continue ;
					}
					if(rootNode != null ) {
						NodeList nodeList = rootNode.getChildNodes();
				        for (int i = 0; i < nodeList.getLength(); i++) {
				        	Node  childNode = nodeList.item(i);
				        	if (childNode.getNodeType() != Node.ELEMENT_NODE) {
				        		continue;
				            }
				        	String nodeName =  childNode.getNodeName().toUpperCase();
				        	Class holderClass = holders.get(nodeName);
				        	if(holderClass != null){
				        		ConfigurationHolder holder = (ConfigurationHolder)(holderClass.newInstance());
				        		importHolders.add(holder);
				        		elements.put( holder , (Element)childNode);
				        	}
				        }
					}
					builder.reset();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<ConfigurationHolder> getImportHolders() {
		return importHolders;
	}

	public Element getImportElement( ConfigurationHolder holder ){
		return elements.get(holder);
		
	}
	
	@Override
	public String toXmlElement() {
		
		return null;
	}

	@Override
	public void validate() {
		
	}
}
