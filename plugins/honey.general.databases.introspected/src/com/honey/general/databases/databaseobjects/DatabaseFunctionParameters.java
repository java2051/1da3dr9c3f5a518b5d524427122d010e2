package com.honey.general.databases.databaseobjects;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.DataSourceException;

/**
 * 获取函数的参数信息(传入参数 传出参数)
 * @author Administrator
 *
 */
@Deprecated
class DatabaseFunctionParameters extends DatabaseHost {
	
	/**
	 * 构造函数
	 * @param databaseConnection 数据库链接配置
	 */
	public DatabaseFunctionParameters(DatabaseConnection databaseConnection) {
		super(databaseConnection);

	}

	@Override
	public Object introspected(String functionName,
			String columnName) {
		try {
			return getFunctionColumns( functionName, columnName);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Object introspected(String functionName) {
		try {
			return getFunctionColumns( functionName, null);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 返回函数传入和传出参数信息
	 * @param functionName 函数名称
	 * @param columnName 如果指定函数参数名称返回参数信息;如果为null返回所有函数参数信息
	 * @throws DataSourceException
	 */
	private Object getFunctionColumns(String functionName,String columnName ) throws DataSourceException{
		DatabaseMetaData databaseMetaData = getDatabaseMetaData();
		ResultSet rs = null;
		if( !StringUtility.stringHasValue(columnName) ){
			columnName = null ;
		}
		
		String localCatalog = super.getDatabaseConnection().getCatalog();
		String localSchema = super.getDatabaseConnection().getSchema();
		
		try {
			rs = databaseMetaData.getFunctionColumns(
					super.getCatalog(localCatalog), 
					super.getSchema( localSchema),
					functionName, 
					columnName
			);
		} catch (SQLException e) {
			
		} finally {
			releaseResources(rs);
			close();
		}
		
		return null;
	}
}
