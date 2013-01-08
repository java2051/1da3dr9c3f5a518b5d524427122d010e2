package com.honey.general.databases.datasource;

import java.sql.Connection;

/**
 * 数据库连接池的接口类
 * @author Administrator
 *
 */
interface ConnectionPool {
	
	/** 数据库连接池最大连接数*/
    int MAX_POOL_SIZE = 4;
    
    /** 数据库连接池最小连接数 */
    int MIN_POOL_SIZE = 1;
    
    /** 初始连接数量*/
    int INITIAL_POOL_SIZE = 1;

    
    /**
     * 是否可用 true:是 false:否
     * @return
     */
    boolean isEnable();
    
    
    /**
     * 返回最大的连接数
     * @return
     */
    int getMaximumConnections();
    
    /**
     * 设定最大的连接数
     * @param maximumConnections
     */
    void setMaximumConnections(int maximumConnections);

    /**
     * 返回最小的连接数
     * @return
     */
    int getMinimumConnections();    
    
    /**
     * 设定最小连接数
     * @param minimumConnections
     * @throws DataSourceException
     */
    void setMinimumConnections(int minimumConnections) throws DataSourceException;

    /**
     * 获取最大使用连接数
     * @return
     */
    int getMaximumUseCount();
    
    /**
     * 设定最大使用的连接数
     * @param maximumUseCount
     */
    void setMaximumUseCount(int maximumUseCount);

    /**
     * 是否支持事务
     * @return
     */
    boolean isTransactionSupported();

    /**
     * 设定事务隔离级别
     * @param isolationLevel
     * @throws DataSourceException
     */
    void setTransactionIsolationLevel(int isolationLevel) throws DataSourceException;

    /**
     * 获取一个连接
     * @return
     * @throws DataSourceException
     */
    Connection getConnection() throws DataSourceException;

    /**
     * 关闭所有的连接
     * @throws DataSourceException
     */
    void close()  throws DataSourceException;
    
    /**
     * 关闭指定连接
     * @param connection
     * @throws DataSourceException
     */
    void close(Connection connection) throws DataSourceException;

    /**
     * 获取连接池的大小
     * @return
     */
    int getSize();

    /**
     * 获取活动连接大小
     * @return
     */
    int getPoolActiveSize();
    
    /**
     * 获取初始化连接数量
     * @return
     */
    int getInitialConnections();
    
    /**
     * 设定初始化连接数量
     * @param initialConnections
     */
    void setInitialConnections(int initialConnections);

}


