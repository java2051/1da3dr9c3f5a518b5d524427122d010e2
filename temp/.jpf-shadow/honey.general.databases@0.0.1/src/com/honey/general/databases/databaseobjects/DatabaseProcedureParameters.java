package com.honey.general.databases.databaseobjects;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.honey.core.dbmapping.structure.ProcedureParameter.ProcedureColumnType;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JavaTypeResolver;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.DataSourceException;
import com.honey.general.databases.introspected.GeneralProcedureParameter;

/**
 * 获取存储过程输入 输出信息
 * @author Administrator
 *
 */
class DatabaseProcedureParameters extends DatabaseHost{

	/**
	 * 构造函数
	 * @param databaseConnection 数据库连接配置信息
	 */
	public DatabaseProcedureParameters(DatabaseConnection databaseConnection) {
		super(databaseConnection);

	}

	@Override
	public Object introspected(String procedureName,
			String columnName) {
		try {
			return getProcedureColumns(procedureName, columnName);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Object introspected(String procedureName) {
		try {
			return getProcedureColumns( procedureName, null); 
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;	
	}

	/**
	 * 返回存储过程输入和输出参数
	 * @param procedureName 存储过程的名称
	 * @param columnName 如果指定存储参数名称返回参数信息;如果为null返回所有存储过程参数信息
	 * @throws DataSourceException
	 */
	private Object getProcedureColumns(String procedureName,String columnName ) throws DataSourceException{
		
		List<GeneralProcedureParameter> answer = new ArrayList<GeneralProcedureParameter>();
		
		DatabaseMetaData databaseMetaData = getDatabaseMetaData();
		ResultSet rs = null;
		if( !StringUtility.stringHasValue(procedureName) ){
			procedureName = null ;
		}
		
		String localCatalog = super.getDatabaseConnection().getCatalog();
		String localSchema = super.getDatabaseConnection().getSchema();
		
		
		try {
			rs = databaseMetaData.getProcedureColumns(
					super.getCatalog( localCatalog ), 
					super.getSchema( localSchema ), 
					procedureName, 
					columnName
			) ;
			while(rs.next()){
				GeneralProcedureParameter gpsc = new GeneralProcedureParameter();
				gpsc.setName( rs.getString(4) );//COLUMN_NAME 
				gpsc.setProcedureColumnType(ProcedureColumnType.getProcedureColumnType(
						rs.getShort(5)//COLUMN_TYPE
				));
				gpsc.setJdbcType(rs.getInt(6 ));//DATA_TYPE
				gpsc.setDbTypeName(rs.getString(7)  ); //TYPE_NAME
				gpsc.setLength(rs.getLong(9)) ;//LENGTH 
				gpsc.setScale(rs.getInt(10));//SCALE
				gpsc.setNullable(rs.getInt(12) == DatabaseMetaData.columnNullable); //NULLABLE
				gpsc.setComment(rs.getString(13));//REMARKS 
				//gpsc.setDefaultValue(rs.getString( "COLUMN_DEF" ));//COLUMN_DEF 
				JavaTypeResolver javaTypeResolver = new JavaTypeResolver(false);
		        FullyQualifiedJavaType fullyQualifiedJavaType = javaTypeResolver .calculateJavaType(gpsc);
				if (fullyQualifiedJavaType != null) {
					gpsc .setFullyQualifiedJavaType(fullyQualifiedJavaType);
					//gpsc.setDbTypeName(javaTypeResolver .calculateJdbcTypeName(introspectedColumn));
		        }
				
				answer.add(gpsc);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(rs);
			close();
		}
		
		return answer;
	}
}
