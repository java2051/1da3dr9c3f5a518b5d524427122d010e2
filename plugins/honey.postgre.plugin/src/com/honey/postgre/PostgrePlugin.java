package com.honey.postgre;

import com.honey.core.EmptyPlugin;


/**
 * Postgre 插件,用于读取postgre数据库表和列的信息
 * @author Administrator
 *
 */
public class PostgrePlugin extends EmptyPlugin{
	
	
//	public Schema introspectedSchemaInformation(ActualSchemaName actualTableName) {
//		return new PostgreTableInformation(
//				getDatabaseConnection(),
//				actualTableName
//		).introspectedSchemaInformation();
//	}
	
//	public List<Column> introspectedAllColumns(ActualSchemaName actualTableName) {
//		
//		return new PostgreAllColumns(
//				getDatabaseConnection(),
//				actualTableName
//		).introspectAllColumns();
//	}

//	public List<Column> introspectedPrimaryKeyColumns(
//			ActualSchemaName actualTableName) {
//		
//		return new PostgrePrimaryKeyColumns(
//				getDatabaseConnection(),
//				actualTableName
//		).introspectedPrimaryKeyColumns();
//	}
//
//
//	private DatabaseConnection getDatabaseConnection()   {
//		DatabaseConnection databaseConnection = null;
//		try {//
//			ConfigurationPlugin configurationPlugin = (ConfigurationPlugin)(ObjectFactory.getRequestPlugin(this, "honey.configuration.plugin"));
//			DatabasesConfiguration databasesConfiguration = configurationPlugin.getDatabasesConfiguration();
//			databaseConnection = new DatabaseConnection(
//					databasesConfiguration.getCatalog(),
//					databasesConfiguration.getSchema(),
//					databasesConfiguration.getDriver(),
//					databasesConfiguration.getUrl(),
//					databasesConfiguration.getUserName(),
//					databasesConfiguration.getPassword(),
//					databasesConfiguration.getLibrary(),
//					databasesConfiguration.getProperties()
//			);
//		} catch (PluginLifecycleException e) {
//			e.printStackTrace();
//		}
//		
//		return databaseConnection;
//	}
}
