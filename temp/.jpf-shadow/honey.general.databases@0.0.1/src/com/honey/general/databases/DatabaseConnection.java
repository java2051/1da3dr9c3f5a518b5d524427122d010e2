package com.honey.general.databases;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

import com.honey.general.databases.datasource.ConnectionManager;
import com.honey.general.databases.datasource.DataSourceException;

/**
 * 数据库连接信息
 * @author Administrator
 *
 */
public class DatabaseConnection {
	
	/** 数据库驱动 */
	private String driver = null;

	/** 数据库连接 */
	private String url = null;

	/** 数据库名称 */
	private String userName = null;

	/** 数据库密码 */
	private String password = null;

	/** 数据库jar包 */
	private String library = null;

	/**  */
	private String catalog = null;

	/**  */
	private String schema= null;

	/** 数据库连接配置项 */
	private Properties jdbcProperties = new Properties();

	/** 数据库信息 */
	private DatabaseMetaData databaseMetaData = null;
	
	/**
	 * 构造函数 
	 * @param catalog 
	 * @param schema 
	 * @param driver 数据库驱动
	 * @param url 数据库连接
	 * @param userName 数据库用户名
	 * @param password 数据库密码
	 * @param library 数据库jdbc jar路径
	 * @param jdbcProperties 数据连接配置项
	 */
	public DatabaseConnection(String catalog,String schema,String driver, String url, String userName,
			String password, String library,Properties jdbcProperties) {
		this.catalog = catalog;
		this.schema = schema;
		this.driver = driver;
		this.url = url;
		this.userName = userName;
		this.password = password;
		this.library = library;
		if(jdbcProperties != null)
			this.jdbcProperties = jdbcProperties;
	}
	
	/**
	 * 获取数据库的 DatabaseMetaData
	 * @return
	 */
	public DatabaseMetaData getDatabaseMetaData() {
		if (databaseMetaData == null) {
			try {
				this.databaseMetaData = this.getConnection().getMetaData();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.databaseMetaData;
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection() {
		try {
			return ConnectionManager.getConnection(this);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取数据驱动
	 * @return
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * 数据库的连接
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * 获取数据库用户名
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 获取数据库密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 获取jdbc jar 路径
	 * @return
	 */
	public String getLibrary() {
		return library;
	}

	/**
	 * jdbc其它的配置
	 * @return
	 */
	public Properties getJdbcProperties() {
		return jdbcProperties;
	}

	/**
	 * 设定数据库配置
	 * @param jdbcProperties
	 */
	public void setJdbcProperties(Properties jdbcProperties) {
		this.jdbcProperties = jdbcProperties;
	}

	public String getCatalog() {
		return catalog;
	}

	public String getSchema() {
		return schema;
	}
}
