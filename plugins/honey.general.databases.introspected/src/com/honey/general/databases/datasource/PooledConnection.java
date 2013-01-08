package com.honey.general.databases.datasource;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * 重写Connection
 * 
 * @author Administrator
 * 
 */
public class PooledConnection implements Connection {

	private String id = UUID.randomUUID().toString();

	/** 连接使用的次数 */
	private int useCount;

	/** 当前的连接是否在使用中 */
	private boolean inUse;

	/** 是否强制关闭数据库连接 */
	private boolean coerceCloseConnetcion;

	/** auto-commit 提交状体 */
	private boolean originalAutoCommit;

	/** JDBC 连接 */
	private Connection realConnection;

	private List<PooledConnectionListener> listeners;

	/**
	 * 创建一个连接
	 * 
	 * @param realConnection
	 */
	public PooledConnection(Connection realConnection) {
		this(realConnection, false);
	}

	/**
	 * 创建连接
	 * 
	 * @param realConnection
	 * @param coerceCloseConnetcion
	 *            是否强制关闭数据库连接 true(是) | false(否)
	 */
	public PooledConnection(Connection realConnection,
			boolean coerceCloseConnetcion) {
		useCount = 0;
		this.realConnection = realConnection;
		this.coerceCloseConnetcion = coerceCloseConnetcion;
		try {
			originalAutoCommit = realConnection.getAutoCommit();
		} catch (SQLException e) {
			originalAutoCommit = true;
		}
	}

	public String getId() {
		return id;
	}

	public void addPooledConnectionListener(
			PooledConnectionListener pooledConnectionListener) {
		if (listeners == null) {
			listeners = new ArrayList<PooledConnectionListener>();
		}
		listeners.add(pooledConnectionListener);
	}

	/**
	 * 当前的连接是否正在活动中
	 * 
	 * @return
	 */
	public boolean isAvailable() {
		try {
			if (realConnection != null) {
				if (!inUse && !realConnection.isClosed()) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * 设定当前连接正在使用
	 * 
	 * @param inUse
	 */
	public void setInUse(boolean inUse) {
		if (inUse) {
			useCount++;
		}
		this.inUse = inUse;
	}

	/**
	 * 销毁连接,(真正的关闭连接)
	 */
	protected void destroy() {
		try {
			if (realConnection != null) {
				realConnection.close();
			}
		} catch (SQLException e) {
		}
		realConnection = null;
	}

	/**
	 * 重写了关闭连接方法,用户调用close方法时把连接换回数据库连接池中,并没有真正的关闭连接
	 */
	@Override
	public void close() throws SQLException {
		inUse = false;
		if (realConnection != null) {
			if (coerceCloseConnetcion) {
				realConnection.close();
				realConnection = null;
			} else {
				try {
					realConnection.setAutoCommit(originalAutoCommit);
				} catch (SQLException e) {
				}
			}
			try {
				fireConnectionClosed();
			} catch (DataSourceException e) {
				throw new SQLException(e);
			}
		}
	}

	/**
	 * 从数据库连接池中清除活动连接中活动连接
	 * 
	 * @throws DataSourceException
	 */
	private void fireConnectionClosed() throws DataSourceException {
		for (PooledConnectionListener listener : listeners) {
			listener.connectionClosed(this);
		}
	}

	@Override
	public Statement createStatement() throws SQLException {
		checkOpen();
		return realConnection.createStatement();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		checkOpen();
		return realConnection.createStatement(resultSetType,
				resultSetConcurrency);
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		checkOpen();
		return realConnection.prepareStatement(sql);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		checkOpen();
		return realConnection.prepareStatement(sql, resultSetType,
				resultSetConcurrency);
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		checkOpen();
		return realConnection.prepareCall(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		checkOpen();
		return realConnection.prepareCall(sql, resultSetType,
				resultSetConcurrency);
	}

	@Override
	public void clearWarnings() throws SQLException {
		checkOpen();
		realConnection.clearWarnings();
	}

	@Override
	public void commit() throws SQLException {
		checkOpen();
		realConnection.commit();
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		checkOpen();
		return realConnection.getAutoCommit();
	}

	@Override
	public String getCatalog() throws SQLException {
		checkOpen();
		return realConnection.getCatalog();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		checkOpen();
		return realConnection.getMetaData();
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		checkOpen();
		return realConnection.getTransactionIsolation();
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		checkOpen();
		return realConnection.getTypeMap();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		checkOpen();
		return realConnection.getWarnings();
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		checkOpen();
		return realConnection.isReadOnly();
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		checkOpen();
		return realConnection.nativeSQL(sql);
	}

	@Override
	public void rollback() throws SQLException {
		checkOpen();
		realConnection.rollback();
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		checkOpen();
		realConnection.setAutoCommit(autoCommit);
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		checkOpen();
		realConnection.setCatalog(catalog);
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		checkOpen();
		realConnection.setReadOnly(readOnly);
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		checkOpen();
		realConnection.setTransactionIsolation(level);
	}

	@Override
	public boolean isClosed() throws SQLException {
		if (realConnection == null) {
			return true;
		}
		return realConnection.isClosed();
	}

	protected void checkOpen() throws SQLException {
		if (realConnection != null && realConnection.isClosed()) {
			throw new SQLException("Connection is closed.");
		}
		if (realConnection == null) {
			throw new SQLException("Connection is closed.");
		}
	}

	@Override
	public int getHoldability() throws SQLException {
		checkOpen();
		return realConnection.getHoldability();
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		checkOpen();
		realConnection.setHoldability(holdability);
	}

	@Override
	public java.sql.Savepoint setSavepoint() throws SQLException {
		checkOpen();
		return realConnection.setSavepoint();
	}

	@Override
	public java.sql.Savepoint setSavepoint(String name) throws SQLException {
		checkOpen();
		return realConnection.setSavepoint(name);
	}

	@Override
	public void rollback(java.sql.Savepoint savepoint) throws SQLException {
		checkOpen();
		realConnection.rollback(savepoint);
	}

	@Override
	public void releaseSavepoint(java.sql.Savepoint savepoint)
			throws SQLException {
		checkOpen();
		realConnection.releaseSavepoint(savepoint);
	}

	@Override
	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		checkOpen();
		return realConnection.createStatement(resultSetType,
				resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		checkOpen();
		return realConnection.prepareStatement(sql, resultSetType,
				resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		checkOpen();
		return realConnection.prepareCall(sql, resultSetType,
				resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		checkOpen();
		return realConnection.prepareStatement(sql, autoGeneratedKeys);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int columnIndexes[])
			throws SQLException {
		checkOpen();
		return realConnection.prepareStatement(sql, columnIndexes);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String columnNames[])
			throws SQLException {
		checkOpen();
		return realConnection.prepareStatement(sql, columnNames);
	}

	public Connection getRealConnection() {
		return realConnection;
	}

	public void setRealConnection(Connection realConnection) {
		this.realConnection = realConnection;
	}

	/**
	 * 是否强制关闭数据库连接
	 * 
	 * @return true(是) | false(否)
	 */
	public boolean isCoerceCloseConnetcion() {
		return coerceCloseConnetcion;
	}

	/**
	 * 设定是否强制关闭连接
	 * 
	 * @param coerceCloseConnetcion
	 */
	public void setCoerceCloseConnetcion(boolean coerceCloseConnetcion) {
		this.coerceCloseConnetcion = coerceCloseConnetcion;
	}

	public int getUseCount() {
		return useCount;
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		checkOpen();
		realConnection.setTypeMap(map);
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		checkOpen();
		return realConnection.createArrayOf(typeName, elements);
	}

	@Override
	public Blob createBlob() throws SQLException {
		checkOpen();
		return realConnection.createBlob();
	}

	@Override
	public Clob createClob() throws SQLException {
		checkOpen();
		return realConnection.createClob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		checkOpen();
		return realConnection.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		checkOpen();
		return realConnection.createSQLXML();
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		checkOpen();
		return realConnection.createStruct(typeName, attributes);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		checkOpen();
		return realConnection.getClientInfo();
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		checkOpen();
		return realConnection.getClientInfo(name);
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		checkOpen();
		return realConnection.isValid(timeout);
	}

	@Override
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		try {
			checkOpen();
			realConnection.setClientInfo(properties);
		} catch (SQLException e) {
			throw new SQLClientInfoException(e.getMessage(), null);
		}
	}

	@Override
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		try {
			checkOpen();
			realConnection.setClientInfo(name, value);
		} catch (SQLException e) {
			throw new SQLClientInfoException(e.getMessage(), null);
		}
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		checkOpen();
		return realConnection.isWrapperFor(iface);
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		checkOpen();
		return realConnection.unwrap(iface);
	}

}
