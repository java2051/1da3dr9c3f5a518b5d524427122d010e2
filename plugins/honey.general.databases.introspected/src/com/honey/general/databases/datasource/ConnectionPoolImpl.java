
package com.honey.general.databases.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.honey.general.databases.DatabaseConnection;


/**
 * 数据库连接池的实现类
 * @author Administrator
 *
 */
public class ConnectionPoolImpl  implements ConnectionPool,PooledConnectionListener {

    private int maximumConnections = MAX_POOL_SIZE;
    
    private int minimumConnections = MIN_POOL_SIZE;
    
    private int initialConnections = INITIAL_POOL_SIZE;

    private final List<PooledConnection> openConnections = new Vector<PooledConnection>();
    
    private final List<PooledConnection> activeConnections = new Vector<PooledConnection>();
    
    private final DatabaseConnection databaseConnection;

    private int defaultTxIsolation = -1;

    private DataSource dataSource;
    
    public ConnectionPoolImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    
    public DatabaseConnection getDatabaseConnection() {
    	return databaseConnection;
    }
    
    @Override
    public void connectionClosed(PooledConnection pooledConnection) throws DataSourceException {
    	activeConnections.remove(pooledConnection);
        reduceCapacity(minimumConnections);
    }

    public void close(Connection connection) throws DataSourceException {
    	if (connection != null) {
    		activeConnections.remove(connection);
    		PooledConnection pooledConnection = (PooledConnection) connection;
            pooledConnection.destroy();
            openConnections.remove(pooledConnection);
        }
        ensureCapacity(minimumConnections);
    }

    @Override
    public synchronized void close() {
        for (Connection connection : openConnections) {
            PooledConnection pooledConnection = (PooledConnection) connection;
            pooledConnection.destroy();
        }
        activeConnections.clear();
        openConnections.clear();
    }

    @Override
    public synchronized Connection getConnection() throws DataSourceException {
        if (openConnections.size() < minimumConnections) {
            ensureCapacity(minimumConnections);
        }
        PooledConnection connection = getNextOpenAvailable();
        if (connection != null) {
            connection.setInUse(true);
            activeConnections.add(connection);
        } else if (openConnections.size() < maximumConnections) {
            createConnection();
            return getConnection();
        } else {
            throw new DataSourceException("Maximum open connection count exceeded");
        }
        return connection;
    }

    private void ensureCapacity(int capacity) throws DataSourceException {
        while (openConnections.size() < capacity) {
            createConnection();
        }
    }
    
    private void reduceCapacity(int capacity) throws DataSourceException {
        while (openConnections.size() > capacity) {
            PooledConnection connection = getNextOpenAvailable();
            if (connection != null) {
                close(connection);
            } else {
                break;
            }
        }
    }

    /**
     * 创建新的连接
     * @return
     * @throws DataSourceException
     */
    private PooledConnection createConnection() throws DataSourceException {
        PooledConnection connection = null;
        try {
            if (dataSource == null) {
                dataSource = new ConnectionDataSource(databaseConnection);
            }
            Connection realConnection = dataSource.getConnection();
            if (realConnection == null) {
                throw new DataSourceException("无法获取数据库连接");
            }
            if (defaultTxIsolation == -1) {
            	defaultTxIsolation = realConnection.getTransactionIsolation();
            }
            connection = new PooledConnection(realConnection);
            connection.addPooledConnectionListener(this);
            openConnections.add(connection);
        } catch (SQLException e) {
            throw new DataSourceException (e);
        }
        return connection;
    }
    
    /**
     * 获取下一个连接
     * @return
     */
    private PooledConnection getNextOpenAvailable() {
        for (PooledConnection pooledConnection : openConnections) {
            if (pooledConnection.isAvailable()) {
                return pooledConnection;
            }
        }
        return null;
    }

    @Override
    public int getMaximumConnections() {
    	return maximumConnections;
    }
    
    @Override
    public int getMaximumUseCount() {
    	return 0;
    }

    @Override
    public int getMinimumConnections() {
        return minimumConnections;
    }

    @Override
    public int getPoolActiveSize() {
    	return activeConnections.size();
    }
    @Override
    public int getSize() {
    	return openConnections.size();
    }

    @Override
    public boolean isTransactionSupported() {
    	return false;
    }

    @Override
    public int getInitialConnections() {
    	return initialConnections;
    }
    
    @Override
    public void setInitialConnections(int initialConnections) {
    	if (initialConnections < 1) {
            throw new IllegalArgumentException("Initial connection count must be at least 1");
        }
    	this.initialConnections = initialConnections;
    }
    
    @Override
    public void setMaximumConnections(int maximumConnections) {
    	if (maximumConnections < 1) {
            throw new IllegalArgumentException("Maximum connection count must be at least 1");
        }
    	this.maximumConnections = maximumConnections;
    }
    
    @Override
    public void setMaximumUseCount(int maximumUseCount) {

    }

    @Override
    public void setMinimumConnections(int minimumConnections) throws DataSourceException {
    	if (minimumConnections < 1) {
            throw new IllegalArgumentException("Minimum connection count must be at least 1");
        }
        this.minimumConnections = minimumConnections;
        ensureCapacity(minimumConnections);
    }

    @Override
    public void setTransactionIsolationLevel(int isolationLevel) throws DataSourceException {
    	if (!isTransactionSupported()) {
    		return;
        }
        if (isolationLevel == -1) {
        	isolationLevel = defaultTxIsolation;
        }
        try {
        	for (Connection connection : openConnections) {
        		if (!connection.isClosed()) {
        			connection.setTransactionIsolation(isolationLevel);
                }
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }   
    }

	@Override
	public boolean isEnable() {

		return true;
	}
}
