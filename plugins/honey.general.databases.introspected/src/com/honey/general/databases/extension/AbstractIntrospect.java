package com.honey.general.databases.extension;

import org.java.plugin.Plugin;
import org.java.plugin.PluginLifecycleException;

import com.honey.configureation.ConfigurationPlugin;
import com.honey.configureation.holder.JDBCConnectionHolder;
import com.honey.core.Extension;
import com.honey.core.utils.ObjectFactory;
import com.honey.general.databases.DatabaseConnection;

/**
 * 基础插件扩展
 * @author Administrator
 *
 */
 abstract class AbstractIntrospect extends Extension<Plugin> {

	/** 数据库连接信息 */
	protected DatabaseConnection databaseConnection;
	
	public AbstractIntrospect(Plugin belongPlugin) {
		super(belongPlugin);
	}
	
	/**
	 * 获取数据库连接
	 */
	protected DatabaseConnection getDatabaseConnection()   {
		if(databaseConnection == null ){
			try {
				ConfigurationPlugin configurationPlugin = (ConfigurationPlugin)(ObjectFactory.getRequestPlugin(this.getBelongPlugin(), "honey.configuration.plugin"));
				JDBCConnectionHolder connectionHolder = configurationPlugin.getContextInstant().getJdbcConnectionHolder();
				databaseConnection = new DatabaseConnection(
						connectionHolder.getCatalog(),
						connectionHolder.getSchema(),
						connectionHolder.getDriver(),
						connectionHolder.getUrl(),
						connectionHolder.getUsername(),
						connectionHolder.getPassword(),
						connectionHolder.getLibrary(),
						connectionHolder.getProperties()
				);
			} catch (PluginLifecycleException e) {
				e.printStackTrace();
			}
		}
		return databaseConnection;
	}
}
