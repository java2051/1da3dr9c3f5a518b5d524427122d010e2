package com.honey.mysql5.introspect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.core.dbmapping.structure.Schema;
import com.honey.core.dbmapping.structure.StructureType;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.introspected.DatabaseIntrospector;
import com.honey.general.databases.introspected.GeneralProcedureParameter;
import com.honey.general.databases.introspected.GeneralTableColumn;

/**
 * 通过查询mysql的information_schema库获取更多的信息.基础信息依然通过jdbc获取.
 * @author Administrator
 *
 */
public class Mysql5AllColumns {
	
	private DatabaseConnection databaseConnection;
	
	private ActualSchema actualTableName;

	private static final String SELECT_COLUMN="SELECT `COLUMN_TYPE` AS ENUMVALUE FROM information_schema.COLUMNS ";
	
	private static final String SELECT_PARAMETER="SELECT DTD_IDENTIFIER AS ENUMVALUE FROM information_schema.PARAMETERS ";
	

	public Mysql5AllColumns(DatabaseConnection databaseConnection, ActualSchema actualTableName) {
		this.databaseConnection = databaseConnection;
		this.actualTableName = actualTableName;
	}
	
	@Deprecated
	public List<Column> introspectAllColumns() {
		List<Column> answer = null;
		DatabaseIntrospector  databaseIntrospector = new DatabaseIntrospector(this.databaseConnection);
		answer = databaseIntrospector.introspectedAllColumns(actualTableName);
		Schema schema = databaseIntrospector.introspectedSchemaInformation(actualTableName);
		if(schema.getSchemaType()== StructureType.TABLE ||
		   schema.getSchemaType()== StructureType.VIEW ||
		   schema.getSchemaType()== StructureType.TEMPORARY){
			answer = introspectMysql5ColumnEnumValue(answer);
		}else{
			answer = introspectMysql5ParameterEnumValue(answer);
		}
		return answer;
	}
	public List<Column> introspectProcedureParameters() {
		List<Column> answer = null;
		DatabaseIntrospector  databaseIntrospector = new DatabaseIntrospector(this.databaseConnection);
		answer = databaseIntrospector.introspectedAllColumns(actualTableName);
		Schema schema = databaseIntrospector.introspectedSchemaInformation(actualTableName);
		if(schema.getSchemaType()== StructureType.PROCEDURE){
				answer = introspectMysql5ParameterEnumValue(answer);
		}
		return answer;
	}
	
	public List<Column> introspectAllTableColumns() {
		List<Column> answer = null;
		DatabaseIntrospector  databaseIntrospector = new DatabaseIntrospector(this.databaseConnection);
		answer = databaseIntrospector.introspectedAllColumns(actualTableName);
		Schema schema = databaseIntrospector.introspectedSchemaInformation(actualTableName);
		if(schema.getSchemaType()== StructureType.TABLE ||
		   schema.getSchemaType()== StructureType.VIEW ||
		   schema.getSchemaType()== StructureType.TEMPORARY){
			answer = introspectMysql5ColumnEnumValue(answer);
		}
		return answer;
	}
	
	/**
	 * 读取表中列包含枚举类型的列<br />
	 * 注意<br />
	 * mysql下 枚举值是都是字符串类型,不能是其它类型值.
	 * @param columns
	 * @return
	 */
	private  List<Column> introspectMysql5ColumnEnumValue(List<Column> columns){
		StringBuilder sql = new StringBuilder(SELECT_COLUMN).append(" WHERE ");

		Connection connetcion=  databaseConnection.getConnection() ;
		String tableSchema =getDatabasesNameFrom(connetcion);
		for(Column column :  columns ){
			if("ENUM".equalsIgnoreCase(column.getDbTypeName()) ){
				sql.append("`TABLE_SCHEMA`='") //定位数据库
				.append(tableSchema)
				.append("' AND ")
				.append("`TABLE_NAME`='")	//定位表
				.append(actualTableName.getTableMapping().getName())
				.append("' AND ")
				.append("`COLUMN_NAME`='")  //定位列名称
				.append(column.getName())
				.append("' AND ")
				.append("`Data_TYPE`='enum' ;") //定位枚举类型
				;
				if(column instanceof GeneralTableColumn ){
					String comment = compileEnum(column, connetcion, sql.toString());
					((GeneralTableColumn)column).setComment(comment);
				}

				sql.setLength(0);
				sql .append(SELECT_COLUMN).append(" WHERE ");
			}
		}

		sql = null;
		return columns ;
	}
	
	/**
	 * 读取存储过程和函数中包含枚举类型的列<br />
	 * 注意<br />
	 * mysql下 枚举值是都是字符串类型,不能是其它类型值.
	 * @param columns
	 * @return
	 */
	private  List<Column> introspectMysql5ParameterEnumValue(List<Column> columns){
		StringBuilder sql = new StringBuilder(SELECT_PARAMETER).append(" WHERE ");
		Connection connetcion=  databaseConnection.getConnection() ;
		String tableSchema =getDatabasesNameFrom(connetcion);
		for(Column column :  columns ){
			if("ENUM".equalsIgnoreCase(column.getDbTypeName()) ){
				sql.append("`SPECIFIC_SCHEMA`='") //定位数据库
				.append(tableSchema)
				.append("' AND ")
				.append("`SPECIFIC_NAME`='")	//函数或者存储过程
				.append(actualTableName.getTableMapping().getName())
				.append("' AND ")
				.append("`PARAMETER_NAME`='")	//定位参数名称
				.append(column.getName())
				.append("' AND ")
				.append("`Data_TYPE`='enum' ;") //定位枚举类型
				;
				String comment = compileEnum(column, connetcion, sql.toString());
				if(column instanceof GeneralProcedureParameter ){
					((GeneralProcedureParameter)column).setComment(comment);
				}
				sql.setLength(0);
				sql .append(SELECT_PARAMETER).append(" WHERE ");
			}
		}
		return columns ;
	}

	/**
	 * 从数据库中获取枚举的值然后,重新编译枚举语法.
	 * @param column
	 * @param connection
	 * @param sql
	 * @return
	 */
	private String compileEnum(Column column, Connection connection, String sql){
		String answer = null;
		ResultSet rs=null;
		try {
			rs = connection.createStatement().executeQuery(sql);
			String enumValue = null; //enum('value1','value2')
			if(rs.next()){
				enumValue = rs.getString("ENUMVALUE");
				answer = column.getComment();
				String grammar = parseEnumValue(enumValue);
				if( StringUtility .stringHasValue(answer)){
					answer = answer+ grammar;
				}else{
					answer =grammar;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!= null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection!= null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return answer;
	}

	/**
	 * 获取连接数据库
	 * @param connection
	 * @return
	 */
	private String  getDatabasesNameFrom( Connection connection){
		String answer = null;
		try {
			String url = connection.getMetaData().getURL();
			int i = url.indexOf('?')  ; 
			if( i > 0 ){
				url = url.substring(0, i);
			}
			answer = url.substring( url.lastIndexOf('/')+1 , url.length()) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer ;
	}
	
	/**
	 * 解析mysql枚举类型格式
	 * @param enumValue
	 * @return
	 */
	private String parseEnumValue(String enumValue){
		if(StringUtility.stringHasValue(enumValue) ){
			StringBuilder sb = new StringBuilder();
			char values[] = enumValue.toCharArray();
			int flag =-1;
			for(int i=5,size=values.length;i < size; ){
				if('\'' == values[i] &&  flag == -1){
					flag = i+1;
				}else if ('\''==values[i] &&  flag > 0){
					String value = enumValue.substring(flag, i);
					sb.append(" ").append(value).append(":").append(value).append(":").append(value);
					flag =-1;i++;
				}
				i++; 
			}
			return sb.toString();
		}
	
		
		return enumValue;
	}
}
