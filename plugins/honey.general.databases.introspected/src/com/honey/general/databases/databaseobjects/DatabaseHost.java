package com.honey.general.databases.databaseobjects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.ConnectionManager;
import com.honey.general.databases.datasource.DataSourceException;

/**
 * 从jdbc中获取数据库的各种信息
 * 
 * @author Administrator
 * 
 */
abstract class DatabaseHost {

	/**
	 * 数据库连接配置
	 */
	private transient DatabaseConnection databaseConnection;

	/**
	 * 数据库连接
	 */
	private transient Connection connection;

	/**
	 * jdbc的metadata对象
	 */
	private transient DatabaseMetaData databaseMetaData;

	/**
	 * 构造函数
	 * 
	 * @param databaseConnection
	 *            数据库连接配置
	 */
	public DatabaseHost(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws DataSourceException
	 */
	protected final Connection getConnection() throws DataSourceException {
		try {
			if (connection == null || connection.isClosed()) {
				connection = ConnectionManager
						.getConnection(getDatabaseConnection());
			}
		} catch (SQLException e) {
			throw new DataSourceException(e);
		}
		return connection;
	}

	/**
	 * 获取数据库描述
	 * 
	 * @return
	 * @throws DataSourceException
	 */
	protected final DatabaseMetaData getDatabaseMetaData()
			throws DataSourceException {
		if (databaseMetaData == null) {
			try {
				databaseMetaData = getConnection().getMetaData();
			} catch (SQLException e) {
				throw new DataSourceException(e);
			}
		}
		return databaseMetaData;
	}

	/**
	 * 获取信息
	 * 
	 * @param schemaName
	 *            是数据库结构名称在DatabaseSchema类中已经详细的描述了这些结构.例如:<br />
	 */
	public abstract Object introspected(String schemaName);

	/**
	 * 获取信息
	 * 
	 * @param schemaName
	 *            是数据库结构名称在DatabaseSchema类中已经详细的描述了这些结构.例如:<br />
	 *            表名称 <br />
	 *            视图名称<br />
	 *            函数名称<br />
	 *            存储过程名称<br />
	 * 
	 * @param columnName
	 *            数据结构列的名称
	 */
	public abstract Object introspected(String schemaName, String columnName);

	/**
	 * 获取jdbc的所有信息
	 * 
	 * @return
	 * @throws DataSourceException
	 */
	public final Map<String, String> getDatabaseProperties()
			throws DataSourceException {
		
		Map<String, String> answer = new HashMap<String, String>();
		
		DatabaseMetaData dmd = getDatabaseMetaData();
		Object[] defaultArg = new Object[] {};
		
		String STRING = "String";
		String GET = "get";
		Class<?> metaClass = dmd.getClass();
		Method[] metaMethods = metaClass.getMethods();
		for (int i = 0; i < metaMethods.length; i++) {
			Class<?> clazz = metaMethods[i].getReturnType();
			String methodName = metaMethods[i].getName();
			if (methodName == null || clazz == null) {
				continue;
			}
			if (clazz.isPrimitive() || clazz.getName().endsWith(STRING)) {
				if (methodName.startsWith(GET)) {
					methodName = methodName.substring(3);
				}
				try {
					Object res = metaMethods[i].invoke(dmd, defaultArg);
					if (res != null) {
						answer.put(methodName, res.toString());
					}
				} catch (AbstractMethodError e) {
					continue;
				} catch (IllegalArgumentException e) {
					continue;
				} catch (IllegalAccessException e) {
					continue;
				} catch (InvocationTargetException e) {
					continue;
				}
			}
		}
		return answer;
	}

	/**
	 * 
	 * 部分数据库的catalog schema名称需要转义,本方法转义catalog schema.
	 * 
	 * @param str
	 * @return
	 * @throws SQLException
	 */
	protected final String wildcardEscapingEnabled(String str)
			throws SQLException {
		if (!StringUtility.stringHasValue(str))
			return str;

		String escapeString = databaseConnection.getDatabaseMetaData()
				.getSearchStringEscape();
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		if (str != null) {
			st = new StringTokenizer(str, "_%", true); //$NON-NLS-1$
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				if (token.equals("_") //$NON-NLS-1$
						|| token.equals("%")) { //$NON-NLS-1$
					sb.append(escapeString);
				}
				sb.append(token);
			}
		}
		return sb.toString();
	}

	/**
	 * 判定数据库知否支持catalog和转义catalog,下面是常用数据库Catalog和Schema对照表 <table border="1"
	 * cellspacing="0" cellpadding="0">
	 * <tr>
	 * <td>供应商</td>
	 * <td >Catalog支持</td>
	 * <td >Schema支持</td>
	 * </tr>
	 * <tr>
	 * <td >Oracle</td>
	 * <td >不支持</td>
	 * <td >Oracle User ID</td>
	 * </tr>
	 * 
	 * <tr>
	 * <td >MySQL</td>
	 * <td >不支持</td>
	 * <td >数据库名</td>
	 * </tr>
	 * <tr>
	 * <td >MS SQL Server</td>
	 * <td >数据库名</td>
	 * <td >对象属主名，2005版开始有变</td>
	 * </tr>
	 * <tr>
	 * <td>DB2</td>
	 * <td >指定数据库对象时，Catalog部分省略</td>
	 * <td >Catalog属主名</td>
	 * </tr>
	 * <tr>
	 * <td >Sybase</td>
	 * <td >数据库名</td>
	 * <td >数据库属主名</td>
	 * </tr>
	 * <tr>
	 * <td>Informix</td>
	 * <td >不支持</td>
	 * <td >不需要</td>
	 * </tr>
	 * <tr>
	 * <td >PointBase</td>
	 * <td >不支持</td>
	 * <td >数据库名</td>
	 * </tr>
	 * </table>
	 * 
	 * @param databaseMetaData
	 * @param catalog
	 * @return
	 * @throws SQLException
	 */
	protected final String getCatalog(String catalog) throws SQLException {
		if (!databaseMetaData.supportsCatalogsInTableDefinitions()) {
			catalog = null;
		}
		catalog = wildcardEscapingEnabled(catalog);

		return catalog;
	}

	/**
	 * 判定数据库是否支持schema和转义schema ,下面是常用数据库Catalog和Schema对照表 <table border="1"
	 * cellspacing="0" cellpadding="0">
	 * <tr>
	 * <td>供应商</td>
	 * <td >Catalog支持</td>
	 * <td >Schema支持</td>
	 * </tr>
	 * <tr>
	 * <td >Oracle</td>
	 * <td >不支持</td>
	 * <td >Oracle User ID</td>
	 * </tr>
	 * 
	 * <tr>
	 * <td >MySQL</td>
	 * <td >不支持</td>
	 * <td >数据库名</td>
	 * </tr>
	 * <tr>
	 * <td >MS SQL Server</td>
	 * <td >数据库名</td>
	 * <td >对象属主名，2005版开始有变</td>
	 * </tr>
	 * <tr>
	 * <td>DB2</td>
	 * <td >指定数据库对象时，Catalog部分省略</td>
	 * <td >Catalog属主名</td>
	 * </tr>
	 * <tr>
	 * <td >Sybase</td>
	 * <td >数据库名</td>
	 * <td >数据库属主名</td>
	 * </tr>
	 * <tr>
	 * <td>Informix</td>
	 * <td >不支持</td>
	 * <td >不需要</td>
	 * </tr>
	 * <tr>
	 * <td >PointBase</td>
	 * <td >不支持</td>
	 * <td >数据库名</td>
	 * </tr>
	 * </table>
	 * 
	 * @param schema
	 * @return
	 * @throws SQLException
	 */
	protected final String getSchema(String schema) throws SQLException {
		if (!databaseMetaData.supportsSchemasInTableDefinitions()) {
			schema = null;
		}
		schema = wildcardEscapingEnabled(schema);

		return schema;
	}

	/**
	 * 关闭数据库连接
	 */
	protected final void close() {
		if (connection != null) {
			try {
				//
				//ConnectionManager.close(getDatabaseConnection(), connection);
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			databaseMetaData = null;
			connection = null;
		}
	}

	/**
	 * 关闭ResultSet
	 * 
	 * @param rs
	 */
	protected final void releaseResources(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException sqlExc) {
		}
	}

	protected final DatabaseConnection getDatabaseConnection() {
		return databaseConnection;
	}
}
