package com.honey.postgre;

import org.java.plugin.Plugin;
import org.java.plugin.PluginLifecycleException;

import com.honey.configureation.ConfigurationPlugin;
import com.honey.configureation.holder.JDBCConnectionHolder;
import com.honey.core.Extension;
import com.honey.core.utils.ObjectFactory;
import com.honey.general.databases.DatabaseConnection;

public abstract class AbstractIntrospect extends Extension<Plugin> {

	public AbstractIntrospect(Plugin belongPlugin) {
		super(belongPlugin);
	}
	
	DatabaseConnection getDatabaseConnection()   {
		DatabaseConnection databaseConnection = null;
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
		
		return databaseConnection;
	}
}
