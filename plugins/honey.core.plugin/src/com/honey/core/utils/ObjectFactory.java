package com.honey.core.utils;

import java.util.Collection;

import org.java.plugin.Plugin;
import org.java.plugin.PluginLifecycleException;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.Extension.Parameter;

import com.honey.core.session.ApplicationSession;

public class ObjectFactory {
	
	/**
	 * 获取一组插入点
	 * @param plugin 
	 * @param extensionId
	 * @return
	 * @throws PluginLifecycleException
	 */
	public static final com.honey.core.Extension[] getExtensionClasses( Plugin plugin, String extensionId ) throws PluginLifecycleException{
		com.honey.core.Extension[] answer = null;
		ExtensionPoint extensionPoint  = getExtensionPoint(plugin, extensionId);
		Collection<Extension> extensions= extensionPoint.getConnectedExtensions();
		answer = new com.honey.core.Extension[extensions.size()];
		if(! EmptyUtility.isListEmpty(extensions) ){
			int i = 0 ;
			com.honey.core.Extension ext = null;
			for(Extension extension : extensions  ){
				ext = getNewExtension(plugin,  extension);
				answer[i] =ext;
				i++;
			}
		}
		return answer;
	}
	
	/**
	 * 获取一个插入点
	 * @param plugin
	 * @param extension
	 * @return
	 * @throws PluginLifecycleException
	 */
	private static final com.honey.core.Extension getNewExtension(Plugin plugin ,Extension extension) throws PluginLifecycleException{
		com.honey.core.Extension answer = null;
		if( extension == null ) return answer;
		Plugin belongPlugin =  getExtensionPlugin(plugin, extension);
		Parameter parameter =  extension.getParameter("class") ;
		if(parameter == null ){
			parameter = extension.getParameter("Class") ;
		}
		if(parameter != null ){
			String sClass = parameter.valueAsString();
			if(parameter != null && sClass.length()  > 0 ){
				try{
					answer = (com.honey.core.Extension)(
								HoneyClassLoader.getSystemClassLoader().loadClass(sClass)
								//.getDeclaredConstructor(belongPlugin.getClass())
								.getDeclaredConstructor(Plugin.class)
								.newInstance(belongPlugin)
							);
					answer.clean();
				}catch (Exception e) {
					e.printStackTrace();
					answer = null;
				}
			}
		}
		return answer ;	
	}
	/**
	 * 获取一个插入点
	 * @param plugin
	 * @param extensionId
	 * @return
	 * @throws PluginLifecycleException
	 */
	public static final com.honey.core.Extension getExtensionClass(Plugin plugin ,String extensionId ) throws PluginLifecycleException{
		ExtensionPoint extensionPoint  = getExtensionPoint(plugin, extensionId);
		Extension extension = extensionPoint.getConnectedExtension();
		return getNewExtension(plugin,extension) ;	
	}
	
	
	/**
	 * 请求指定的插件
	 * @param plugin
	 * @param pluginId
	 * @return
	 * @throws PluginLifecycleException
	 */
	public static final Plugin getRequestPlugin(Plugin plugin ,String pluginId) throws PluginLifecycleException{
		return plugin.getManager().getPlugin(pluginId); 
		
	}
	
	/**
	 * 获取插件的扩展点
	 * @param plugin
	 * @param extensionPointId
	 * @return
	 */
	public static final  ExtensionPoint getExtensionPoint(Plugin plugin ,String extensionPointId){
		return  plugin.getManager().getRegistry().getExtensionPoint( plugin.getDescriptor().getId(), extensionPointId);
	} 
	
	/**
	 * 获取一个扩展插件
	 * @param plugin
	 * @param extension
	 * @return
	 * @throws PluginLifecycleException
	 */
	public static final Plugin getExtensionPlugin(Plugin plugin ,Extension extension ) throws PluginLifecycleException{
		String  pluginid = extension.getDeclaringPluginDescriptor().getId();
		return plugin.getManager().getPlugin( pluginid );
	}
	
	/**
	 * 获取session
	 * @return
	 */
	public static final ApplicationSession getApplicationSession(){
		
		return new ApplicationSession();
	}
	
	
}
