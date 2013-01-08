package com.honey.general.databases.datasource;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.sql.DataSource;

import com.honey.core.utils.HoneyClassLoader;
import com.honey.core.utils.ReflectUtility;
import com.honey.general.databases.DatabaseConnection;

/**
 * C3p0 数据库链接
 * @author Administrator
 *
 */
public class C3p0ConnectionPool implements ConnectionPool {

	private static final String USE_POOL_BACKED_DATA_SOURCE="usePoolBackedDataSource";
	
    private static final String ACQUIRE_INCREMENT_KEY = "acquireIncrement";

    private static final String INITIAL_POOL_SIZE_KEY = "initialPoolSize";

    private static final String MAX_POOL_SIZE_KEY = "maxPoolSize";

    private static final String MIN_POOL_SIZE_KEY = "minPoolSize";

    private static final int ACQUIRE_INCREMENT = 1;

    private final List<Connection> activeConnections = new Vector<Connection>();
    
    private final DatabaseConnection databaseConnection;

    private final Properties c3poPoolProperties;

    private int defaultTxIsolation = -1;

    private DataSource c3p0DataSource;
    
    public C3p0ConnectionPool(DatabaseConnection databaseConnection) {
    	this.databaseConnection = databaseConnection;
        c3poPoolProperties = new Properties();
        
        Properties pro = databaseConnection.getJdbcProperties();
    	Iterator<Object> it = pro.keySet().iterator();
		String key=null;
		while (it.hasNext()){
			key = it.next().toString();
			c3poPoolProperties.put(key.replaceAll("c3p0.", ""), pro.get(key));
		}
		if( ! pro.contains(MIN_POOL_SIZE_KEY)  ){
			setMinimumConnections(MIN_POOL_SIZE);
		}
		if( ! pro.contains(MAX_POOL_SIZE_KEY)  ){
			setMaximumConnections(MAX_POOL_SIZE);
		}
		if( ! pro.contains(INITIAL_POOL_SIZE_KEY)  ){
			setInitialConnections(INITIAL_POOL_SIZE);
		}
		if( ! pro.contains(ACQUIRE_INCREMENT_KEY)  ){
			c3poPoolProperties.put(ACQUIRE_INCREMENT_KEY, asString(ACQUIRE_INCREMENT));
		}
		c3poPoolProperties.setProperty("user", databaseConnection.getUserName());
		c3poPoolProperties.setProperty("password", databaseConnection.getPassword());
    }
   
    
    public DatabaseConnection getDatabaseConnection() {
    	return databaseConnection;
    }
    
    @Override
    public void close(Connection connection) throws DataSourceException {
    	try {
    		activeConnections.remove(connection);
            connection.close();
        } catch (SQLException e) {	
        	throw new DataSourceException(e);
        }
    }

    @Override
    public void close() throws DataSourceException {
    	try {
    		//c3p0DataSource.close();
    		ReflectUtility.invoke(c3p0DataSource,"close");
		} catch (Exception e) {
			e.printStackTrace();
			 throw new DataSourceException(e);
		} 
    	
    }

    public Connection getConnection() throws DataSourceException {
    	Connection connection = null;
        try {
        	if (c3p0DataSource == null) {
        		initialiseC3p0DataSource(databaseConnection);
            }
        	connection = c3p0DataSource.getConnection();

            if (defaultTxIsolation == -1) {
            	defaultTxIsolation = connection.getTransactionIsolation();
            }
            
            activeConnections.add(connection);
            
        } catch (SQLException e) {
        	throw new DataSourceException (e);
        }
        return connection;
    }
    
    private void initialiseC3p0DataSource(DatabaseConnection databaseConnection) throws  DataSourceException {
    	if(databaseConnection.getLibrary() != null){
			HoneyClassLoader.loadLibraryToDefaultClassLoader(databaseConnection.getLibrary());
		}
    	try{
        	if(c3poPoolProperties.get(USE_POOL_BACKED_DATA_SOURCE) != null){
        		DataSource dataSource = new ConnectionDataSource(databaseConnection); 
	    		Class<?> clazz = Class.forName("com.mchange.v2.c3p0.DataSources") ;
	        	c3p0DataSource =(DataSource) ReflectUtility.invoke(
	        			clazz, 
	        			"pooledDataSource", 
	        			new Class<?>[]{DataSource.class,Properties.class}, 
	        			new Object[]{dataSource,c3poPoolProperties});
        	}else{
        		Class<?> clazz = Class.forName("com.mchange.v2.c3p0.ComboPooledDataSource") ;
        		c3p0DataSource =(DataSource)clazz.newInstance();
        		ReflectUtility.invoke(
        				c3p0DataSource,
        				"setDriverClass",
	        			new Object[]{databaseConnection.getDriver()});
        		ReflectUtility.invoke(
        				c3p0DataSource, 
	        			"setJdbcUrl", 
	        			new Object[]{databaseConnection.getUrl()});
        		ReflectUtility.invoke(
        				c3p0DataSource,
	        			"setProperties",
	        			new Object[]{c3poPoolProperties});
        	}
        }catch (Exception e) {
        	throw new DataSourceException(e);
		}
    }

    public int getMaximumConnections() {
        return ((Integer) c3poPoolProperties.get(MAX_POOL_SIZE_KEY)).intValue();
    }
    
    public int getMaximumUseCount() {
    	return 0;
    }

    public int getMinimumConnections() {
        return ((Integer) c3poPoolProperties.get(MIN_POOL_SIZE_KEY)).intValue();
    }

    public int getPoolActiveSize() {
    	return activeConnections.size();
    }

    public int getSize() {
    	int size = 0;
        
        //c3p0DataSource.getNumConnectionsDefaultUser();
        Object result;
		try {
			result = ReflectUtility.invoke(c3p0DataSource,"getNumConnectionsDefaultUser");
			size = (Integer)  result;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return size;
    }

    public boolean isTransactionSupported() {
    	return false;
    }

    public int getInitialConnections() {
        return ((Integer) c3poPoolProperties.get(INITIAL_POOL_SIZE_KEY)).intValue();
    }
    
    public void setInitialConnections(int initialConnections) {
    	if (initialConnections < 1) {
            throw new IllegalArgumentException("Initial connection count must be at least 1");
        }
        c3poPoolProperties.put(INITIAL_POOL_SIZE_KEY, asString(initialConnections));
    }
    
    public void setMaximumConnections(int maximumConnections) {
        if (maximumConnections < 1) {
            throw new IllegalArgumentException("Maximum connection count must be at least 1");
        }
        c3poPoolProperties.put(MAX_POOL_SIZE_KEY, asString(maximumConnections));
    }
    
    public void setMaximumUseCount(int maximumUseCount) {

    }

    public void setMinimumConnections(int minimumConnections) {
    	if (minimumConnections < 1) {
            throw new IllegalArgumentException("Minimum connection count must be at least 1");
        }
        c3poPoolProperties.put(MIN_POOL_SIZE_KEY, asString(minimumConnections));
    }

    public void setTransactionIsolationLevel(int isolationLevel) throws DataSourceException {
    	if (!isTransactionSupported()) {
    		return;
        }
        if (isolationLevel == -1) {
        	isolationLevel = defaultTxIsolation;
        }
        try {
        	for (Connection connection : activeConnections) {
        		if (!connection.isClosed()) {
                
                    connection.setTransactionIsolation( isolationLevel );
                }

            }

        } catch (SQLException e) {
            
            throw new DataSourceException(e);
        }
        
    }

    private Object asString(int value) {

        return String.valueOf(value);
    }

	@Override
	public boolean isEnable() {
		boolean b = false;
		try{
			Class.forName("com.mchange.v2.c3p0.DataSources") ;
			b = true ;
		}catch (Exception e) {
			b = false ;
		}
		return  b;
	}
}