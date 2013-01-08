
package com.honey.general.databases.datasource;

import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

import com.honey.core.utils.HoneyClassLoader;

import com.honey.general.databases.DatabaseConnection;

/**
 * 默认的数据库驱动器
 * @author Administrator
 *
 */
public class DriverLoader {

    private static final Map<DatabaseConnection, Driver> drivers = new HashMap<DatabaseConnection, Driver>();
    

    public Driver loadDriver(DatabaseConnection databaseConnection) throws DataSourceException {
        Driver driver = null;
        if (drivers.containsKey(databaseConnection)) {
            return drivers.get(databaseConnection);
        }
        try {
        	Class<?> clazz = null;
            String driverName = databaseConnection.getDriver();
			if(databaseConnection.getLibrary() != null){
				HoneyClassLoader.loadLibraryToDefaultClassLoader(databaseConnection.getLibrary());
			}
            clazz = Class.forName(driverName);
			Object object = clazz.newInstance();
            driver = (Driver) object;
            drivers.put(databaseConnection, driver);
        } catch (ClassNotFoundException e) {
            throw new DataSourceException("没有找到数据库驱动器类");
        } catch (InstantiationException e) {
			throw new DataSourceException(e);
		} catch (IllegalAccessException e) {
			throw new DataSourceException(e);
		} 
        return driver;
    }
}


