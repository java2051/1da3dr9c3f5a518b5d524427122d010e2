package com.honey.general.databases.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;

import javax.sql.DataSource;

import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;

/**
 * 数据源
 * 
 * @author Administrator
 * 
 */
public class ConnectionDataSource implements DataSource {

	private static final DriverLoader DRIVER_LOADER = new DriverLoader();

	private Properties properties = new Properties();

	private final Driver driver;

	private final DatabaseConnection databaseConnection;

	public ConnectionDataSource(DatabaseConnection databaseConnection)
			throws DataSourceException {
		this.databaseConnection = databaseConnection;
		driver = DRIVER_LOADER.loadDriver(databaseConnection);
		if (driver == null) {
			throw new DataSourceException("加载数据库驱动器失败");
		}
		populateAdvancedProperties();
	}

	@Override
	public Connection getConnection() throws SQLException {

		return getConnection(databaseConnection.getUserName(),
				databaseConnection.getPassword());
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		Properties advancedProperties = buildAdvancedProperties();
		if (StringUtility.stringHasValue(username)) {
			advancedProperties.put("user", username);
		}
		if (StringUtility.stringHasValue(password)) {
			advancedProperties.put("password", password);
		}
		return driver.connect(databaseConnection.getUrl(), advancedProperties);
	}

	private Properties buildAdvancedProperties() {
		Properties advancedProperties = new Properties();
		for (Iterator<?> i = properties.keySet().iterator(); i.hasNext();) {
			String key = i.next().toString();
			advancedProperties.put(key, properties.get(key));
		}
		return advancedProperties;
	}

	private void populateAdvancedProperties() {
		Properties advancedProperties = databaseConnection.getJdbcProperties();
		for (Iterator i = advancedProperties.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			properties.put(key, advancedProperties.getProperty(key));
		}
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return DriverManager.getLoginTimeout();
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return DriverManager.getLogWriter();
	}

	@Override
	public void setLoginTimeout(int timeout) throws SQLException {
		DriverManager.setLoginTimeout(timeout);
	}
	
	@Override
	public void setLogWriter(PrintWriter writer) throws SQLException {	
		DriverManager.setLogWriter(writer);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

}
