package com.honey.configureation.xmlparser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.java.plugin.boot.Boot;
import org.java.plugin.util.ExtendedProperties;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.honey.configureation.holder.ConfigurationHolder;
import com.honey.configureation.holder.ImportHolder;
import com.honey.configureation.holder.JDBCConnectionHolder;
import com.honey.configureation.holder.MappingEnvironmentHolder;
import com.honey.configureation.holder.PluginHolder;
import com.honey.configureation.holder.ProjectEnvironmentHolder;
import com.honey.configureation.holder.PropertiesHolder;
import com.honey.core.utils.Constant;
import com.honey.core.utils.StringUtility;

/**
 * 解析系统配置文件
 * @author Administrator
 */
public class ConfigurationParser {
	
	private Properties  propertiesElement= null;
	
	/**key: 插件ID   value:xml节点 */
	private Map<String, Element> pluginElements= null;

	private ProjectEnvironmentHolder projectEnvironmentHolder = null;
	
	private JDBCConnectionHolder jdbcConnectionHolder = null; 

	private MappingEnvironmentHolder mappingEnvironmentHolder = null; 

	public ConfigurationParser(ExtendedProperties  properties ,String[] arg){
		pluginElements = new HashMap<String, Element>();
		propertiesElement = new Properties();
		
		String localPath  = properties.getProperty(Boot.APPLICATION_LOCAL_PATH);
		propertiesElement.setProperty("localpath", localPath) ;
		
		String localPluginsPath  = properties.getProperty("org.java.plugin.boot.pluginsRepositories");
		propertiesElement.setProperty("localPluginsPath", localPluginsPath) ;
		
		String configPath  = properties.getProperty(Boot.CONFIG_FILE_PATH);
		propertiesElement.setProperty("configpath", configPath) ;
	}
	
	/**
	 * 解析配置文件
	 * @param inputStream
	 */
	public void parseConfiguration( InputStream inputStream){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			Element rootNode = document.getDocumentElement();
			String version =  ParseElement.getConfiguration(rootNode, "version", propertiesElement);
			if(! Constant.VERSION.equals(version) ){
				return ;
			}
			
			NodeList nodeList = rootNode.getChildNodes();
			String nodeName = null;
			Element element = null;
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node  childNode = nodeList.item(i);
				if (childNode.getNodeType() != Node.ELEMENT_NODE) {
					continue;
			    }
				element =(Element)childNode;
				nodeName = element.getNodeName();
			    if ("properties".equalsIgnoreCase(nodeName)) {
			    	parsePropertiesElement(element);
			    }else if ("jdbcConnection".equalsIgnoreCase(nodeName)) {
			    	parseJdbcConnectionElement(element);
			    }else if ("context".equalsIgnoreCase(nodeName)) {
			    	parseContextElement(element);
			    }else if ("context-environment".equalsIgnoreCase(nodeName)) {
			    	parseContextElement(element);
			    }else if("import".equalsIgnoreCase(nodeName) ){
					parseImportElement(element);
				}else if("include".equalsIgnoreCase(nodeName) ){
					parseImportElement(element);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void parseContextElement(Element  element){
		NodeList nodeList = element.getChildNodes();
		String nodeName = null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node  childNode = nodeList.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE) {
				continue;
		    }
			Element ele =(Element)childNode;
			nodeName = ele.getNodeName();
			if( "plugin-environment".equalsIgnoreCase(nodeName) || "plugin".equalsIgnoreCase(nodeName) ||  "plugin-context".equalsIgnoreCase(nodeName)){
				parsePluginElement(ele);
			}else if("mapping-environment".equalsIgnoreCase(nodeName) || "mapping".equalsIgnoreCase(nodeName)  ){
				parseMappingElement(ele);
			}else if("project-environment".equalsIgnoreCase(nodeName) || "project".equalsIgnoreCase(nodeName)  ){
				parseProjectEnvironmentElement(ele);
			}else if("import".equalsIgnoreCase(nodeName) || "include".equalsIgnoreCase(nodeName)){
				parseImportElement(ele);
			}
		}
	}
	
	/**
	 * jdbcConnection
	 * @param element
	 */
	private void parseJdbcConnectionElement(Element  element){
		jdbcConnectionHolder = new JDBCConnectionHolder();
		runConfigurationHolder(element, jdbcConnectionHolder);
	}
	
	/**
	 * plugin
	 * @param element
	 */
	private void parsePluginElement(Element  element){
		PluginHolder holder = new PluginHolder();
		runConfigurationHolder(element, holder);
		String pluginid = holder.getPluginid();
		if(StringUtility.stringHasValue(pluginid) ){
			pluginElements.put(pluginid, holder.getPluginElement());
		}
	}
	
	private void parseProjectEnvironmentElement(Element  element){
		projectEnvironmentHolder = new ProjectEnvironmentHolder();
		runConfigurationHolder(element, projectEnvironmentHolder);
	}
	
	/**
	 * import 节点
	 * @param element
	 */
	private void parseImportElement(Element  element){
		ImportHolder importHolder =  new ImportHolder(); 
		runConfigurationHolder(element,importHolder) ;
		List<ConfigurationHolder> list = importHolder.getImportHolders();
		for(ConfigurationHolder holder : list ){
			Element ele = importHolder.getImportElement(holder);
			if(holder instanceof PluginHolder ){
				parsePluginElement(ele);
			}else if(holder instanceof JDBCConnectionHolder ){
				parseJdbcConnectionElement(ele);
			}else if(holder instanceof PropertiesHolder){
				parsePropertiesElement(ele);
			}
		}
	}
	
	/**
	 * properties
	 * @param element
	 */
	private void parsePropertiesElement(Element element){
		runConfigurationHolder(element, new PropertiesHolder());
	}
	
	/**
	 * mapping
	 * @param element
	 */
	private void parseMappingElement(Element element){
		mappingEnvironmentHolder = new MappingEnvironmentHolder();
		runConfigurationHolder(element, mappingEnvironmentHolder);
	}
	
	/**
	 * 执行holder
	 * @param element
	 * @param holder
	 */
	private void runConfigurationHolder(Element  element ,ConfigurationHolder holder ){
		holder.parserElement(element, propertiesElement);
		holder.validate();
	}

	/**
	 * 获取注册过的配置文件的插件配置
	 * @param pluginid
	 * @return
	 */
	public Element getPluginConfigurationElement(String pluginid) {
		return pluginElements.get(pluginid);
	}

	public Properties getPropertiesElement() {
		return propertiesElement;
	}

	public ProjectEnvironmentHolder getProjectEnvironmentHolder() {
		return projectEnvironmentHolder;
	}

	public JDBCConnectionHolder getJdbcConnectionHolder() {
		return jdbcConnectionHolder;
	}

	public MappingEnvironmentHolder getMappingEnvironmentHolder() {
		return mappingEnvironmentHolder;
	}
}
