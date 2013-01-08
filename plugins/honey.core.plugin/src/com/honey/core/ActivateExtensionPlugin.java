package com.honey.core;

import org.java.plugin.Plugin;
import org.java.plugin.PluginLifecycleException;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.util.ExtendedProperties;

import com.honey.configureation.ConfigurationPlugin;
import com.honey.core.session.AddListener;
import com.honey.core.session.ApplicationSession;
import com.honey.core.session.RemoveListener;
import com.honey.core.utils.ObjectFactory;

/**
 * 激活CorePlugin的所有扩展点
 * @author Administrator
 *
 */
public class ActivateExtensionPlugin {

	/**
	 * 初始化系统配置
	 * @param properties
	 * @param arg
	 * @throws PluginLifecycleException
	 */
	public static ConfigurationPlugin activateConfigurationPlugin(Plugin plugin , ExtendedProperties  properties ,String[] arg) throws PluginLifecycleException {
		Plugin p = ObjectFactory.getRequestPlugin( plugin , "honey.configuration.plugin");
		if(p instanceof ConfigurationPlugin ){
			ConfigurationPlugin configurationPlugin = (ConfigurationPlugin)p;
			configurationPlugin.applicationInitConfiguration(properties, arg);
			return configurationPlugin;
		}
		return null;
	}
	
	
	/**
	 * 激活session监听器
	 * @param plugin
	 * @throws PluginLifecycleException 
	 */
	public static void activateSessionListenerPlugin(Plugin plugin ) throws PluginLifecycleException{
		ExtensionPoint extPoint = ObjectFactory.getExtensionPoint( plugin, "AddSessionListener");
		for (Extension extension : extPoint.getConnectedExtensions()) {
			ApplicationSession.addListener(
				(AddListener)(ObjectFactory.getExtensionPlugin(plugin, extension))
			);
		}
		extPoint = ObjectFactory.getExtensionPoint(plugin, "RemoveSessionListener");
		for (Extension extension : extPoint.getConnectedExtensions()) {
			ApplicationSession.addListener(
				(RemoveListener)(ObjectFactory.getExtensionPlugin(plugin, extension))
			);
		}
	}
	
}
