package com.honey.configureation;

import java.io.InputStream;

import org.java.plugin.Plugin;
import org.java.plugin.PluginLifecycleException;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.Extension.Parameter;
import org.java.plugin.util.ExtendedProperties;
import org.w3c.dom.Element;

import com.honey.configureation.holder.ConfigurationHolder;
import com.honey.configureation.xmlparser.ConfigurationParser;
import com.honey.core.EmptyPlugin;
import com.honey.core.utils.ObjectFactory;

public class ConfigurationPlugin extends EmptyPlugin {
	
	private Context context = null;
	
	/**
	 * 由application启动时调用,初始化配置文件
	 * @param properties
	 * @param arg
	 * @throws PluginLifecycleException 
	 */
	public void applicationInitConfiguration(ExtendedProperties  properties ,String[] arg) throws PluginLifecycleException {
		context = new Context();
		context.setSystemArg(arg);
		context.setSystemProperties(properties);
		
		ConfigurationParser configurationParser = new ConfigurationParser(properties , arg);
		configurationParser.parseConfiguration((InputStream)properties.get("application.configuration.inputstream")) ;
		
		//系统插件放入context中
		context.setJdbcConnectionHolder(configurationParser.getJdbcConnectionHolder());
		context.setProjectEnvironmentHolder(configurationParser.getProjectEnvironmentHolder());
		context.setMappingEnvironmentHolder(configurationParser.getMappingEnvironmentHolder());
		
		//处理插件的配置
		ExtensionPoint extensionPoint = ObjectFactory.getExtensionPoint(this,"ConfigurationHolder");
		for ( Extension extension : extensionPoint.getConnectedExtensions() ) {
			Plugin extensionPlugin = ObjectFactory.getExtensionPlugin(this, extension);
			Element element = configurationParser.getPluginConfigurationElement( extensionPlugin.getDescriptor().getId() );
			ConfigurationHolder  holder =(ConfigurationHolder)extensionPlugin ; 
			holder.parserElement(element, configurationParser.getPropertiesElement());
			holder.validate();
		}
	}
	
	public Context getContextInstant(){
		return context;
	}
}
