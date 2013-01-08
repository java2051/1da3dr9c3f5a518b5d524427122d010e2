package com.honey.general.databases.datasource;

/**
 * 连接监听器 
 * @author Administrator
 *
 */
public interface PooledConnectionListener {
	
	/**
	 * 清除活动连接
	 * @param pooledConnection
	 * @throws DataSourceException
	 */
    void connectionClosed(PooledConnection pooledConnection) throws DataSourceException;
    
}


