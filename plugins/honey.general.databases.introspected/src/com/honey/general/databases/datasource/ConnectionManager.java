package com.honey.general.databases.datasource;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.ConnectionPoolFactory.PoolType;

/**
 * 数据库连接管理,对外获取数据库连接
 * 
 * @author Administrator
 * 
 */
public final class ConnectionManager {

	/** 连接容器 'container' */
	private static Map<DatabaseConnection, ConnectionPool> connectionPools;

	private ConnectionManager() {
	}

	
	private static final int MAX_CONNECTION_USE_COUNT = 5;

	public static int getMaxUseCount() {

		return MAX_CONNECTION_USE_COUNT;
	}

	
	/**
	 * 创建新的连接
	 * 
	 * @param databaseConnection
	 * @throws DataSourceException
	 */
	private static synchronized void createDataSource(
			DatabaseConnection databaseConnection) throws DataSourceException {
			
		ConnectionPool pool = ConnectionPoolFactory.getConnectionPool(databaseConnection, PoolType.AUTO_CHOOSE_CONNECTION_POOL);
		if (connectionPools == null) {
			connectionPools = new HashMap<DatabaseConnection, ConnectionPool>();
		}
		connectionPools.put(databaseConnection, pool);
	}

	/**
	 * 获取连接
	 * 
	 * @param databaseConnection
	 *            数据库连接配置
	 * @return
	 * @throws DataSourceException
	 */
	public static Connection getConnection(DatabaseConnection databaseConnection)
			throws DataSourceException {
		if (databaseConnection == null) {
			return null;
		}
		synchronized (databaseConnection) {

			if (connectionPools == null
					|| !connectionPools.containsKey(databaseConnection)) {

				createDataSource(databaseConnection);
			}

			ConnectionPool pool = connectionPools.get(databaseConnection);
			Connection connection = pool.getConnection();

			return connection;
		}

	}

	/**
	 * 关闭指定的连接
	 * 
	 * @param databaseConnection
	 * @throws DataSourceException
	 */
	public static synchronized void closeConnection(
			DatabaseConnection databaseConnection) throws DataSourceException {

		synchronized (databaseConnection) {
			if (connectionPools.containsKey(databaseConnection)) {
				ConnectionPool pool = connectionPools.get(databaseConnection);
				pool.close();
				connectionPools.remove(databaseConnection);
			}
		}
	}

	/**
	 * 关闭所有的连接
	 * 
	 * @throws DataSourceException
	 */
	public static void close() throws DataSourceException {
		if (connectionPools == null || connectionPools.isEmpty()) 
			return;

		for (Iterator<DatabaseConnection> i = connectionPools.keySet()
				.iterator(); i.hasNext();) {
			ConnectionPool pool = connectionPools.get(i.next());
			pool.close();
		}
		connectionPools.clear();
	}

	/**
	 * 设定事务的级别 <table cellspacing="0" cellpadding="0" border="1"> <tbody>
	 * <tr>
	 * <td>JDBC</td>
	 * <td>数据库隔离级别</td>
	 * <td>数据访问情况</td>
	 * </tr>
	 * <tr>
	 * <td>TRANSACTION_READ_UNCOMMITTED</td>
	 * <td>ur</td>
	 * <td>就是俗称“脏读”（<span>dirty read），在没有提交数据时能够读到已经更新的数据</td>
	 * </tr>
	 * <tr>
	 * <td>TRANSACTION_READ_COMMITTED</td>
	 * <td>cs</td>
	 * <td>在一个事务中进行查询时，允许读取提交前的数据，数据提交后，当前查询就可以读取到数据。update数据时候并不锁住表</td>
	 * </tr>
	 * <tr>
	 * <td>TRANSACTION_REPEATABLE_READ</td>
	 * <td>rs</td>
	 * <td>在一个事务中进行查询时，不允许读取其他事务update的数据，允许读取到其他事务提交的新增数据</td>
	 * </tr>
	 * <tr>
	 * <td>TRANSACTION_SERIALIZABLE</td>
	 * <td>rr</td>
	 * <td>在一个事务中进行查询时，不允许任何对这个查询表的数据修改。</td>
	 * </tr>
	 * </tbody> </table>
	 * 
	 * @param databaseConnection
	 * @param isolationLevel
	 * @throws DataSourceException
	 */
	public static void setTransactionIsolationLevel(
			DatabaseConnection databaseConnection, int isolationLevel)
			throws DataSourceException {
		if (connectionPools != null
				|| connectionPools.containsKey(databaseConnection)) {
			ConnectionPool pool = connectionPools.get(databaseConnection);
			pool.setTransactionIsolationLevel(isolationLevel);
		}
	}

	/**
	 * 强制关闭指定连接 
	 * 
	 * @param the
	 *            connection be closed
	 * @throws DataSourceException
	 */
	public static void close(DatabaseConnection databaseConnection,
			Connection connection) throws DataSourceException {

		if (connectionPools == null || connectionPools.isEmpty()) {

			return;
		}

		if (connectionPools.containsKey(databaseConnection)) {

			ConnectionPool pool = connectionPools.get(databaseConnection);
			pool.close(connection);
		}
	}

	/**
	 * 是否支持事务
	 * 
	 * @param databaseConnection
	 *            the connection to be polled
	 * @return true | false
	 */
	public static boolean isTransactionSupported(
			DatabaseConnection databaseConnection) {
		if (connectionPools.containsKey(databaseConnection)) {
			ConnectionPool pool = connectionPools.get(databaseConnection);
			return pool.isTransactionSupported();
		}
		return false;
	}	
}
