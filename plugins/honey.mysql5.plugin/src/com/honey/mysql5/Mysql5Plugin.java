package com.honey.mysql5;

import java.util.Properties;

import org.java.plugin.Plugin;
import org.java.plugin.PluginLifecycleException;
import org.w3c.dom.Element;

import com.honey.configureation.ConfigurationPlugin;
import com.honey.configureation.holder.ConfigurationHolder;
import com.honey.configureation.holder.JDBCConnectionHolder;
import com.honey.core.utils.ObjectFactory;
import com.honey.mysql5.config.MysqlConfigurationHolder;

public class Mysql5Plugin  extends Plugin implements ConfigurationHolder{
	
	private MysqlConfigurationHolder configurationHolder ;
	
	public Mysql5Plugin(){
		configurationHolder = new MysqlConfigurationHolder(); 
	}
	
	@Override
	protected void doStart() throws Exception {
		//插件启动时候装载配置文件
		configurationHolder.holderPluginDescriptorAttribute( getDescriptor());
		try {
			ConfigurationPlugin configurationPlugin= (ConfigurationPlugin)(ObjectFactory.getRequestPlugin(this, "honey.configuration.plugin"));
			this.configurationHolder.holderDatabasesConfiguration(configurationPlugin.getContextInstant().getJdbcConnectionHolder());
		} catch (PluginLifecycleException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doStop() throws Exception {
		//nop
	}

	@Override
	public void parserElement(Element element, Properties systemProperties) {
		configurationHolder.parserElement(element, systemProperties);
	}

	@Override
	public String toXmlElement() {
		return configurationHolder.toXmlElement();
	}

	@Override
	public void validate() {
		configurationHolder.validate();
	}
}
