package com.honey.mysql5.introspect;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Procedure;
import com.honey.core.dbmapping.structure.StructureType;
import com.honey.core.dbmapping.structure.Table;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.introspected.GeneralProcedure;
import com.honey.general.databases.introspected.GeneralTable;

/**
 * 
 * 使用mysql 数据库语句获取数据库信息
 * @author Administrator
 *	
 */
public class MySql5SchemaInformation {
	
	private DatabaseConnection databaseConnection;
	
	private ActualSchema actualTableName;
	
	private static final String STATUS_SQL ="SHOW TABLE STATUS WHERE `Name`='";
	
	private static final String CREATE_TABLE_SQL = "SHOW CREATE TABLE "; 
	
	private static final String MYSQL_ENGINE="Engine" ;
	
	private static final String CREATE_TIME="Create_time" ;
	
	private static final String COMMENT="Comment";
	
	private static final String COLLATION="Collation";
	
	
	private static final String CREATE_TABLE="Create Table";
	
	public MySql5SchemaInformation( DatabaseConnection databaseConnection, ActualSchema actualTableName){
		this.databaseConnection = databaseConnection;
		this.actualTableName = actualTableName;
	}

	/**
	 * 表信息
	 * @return
	 */
	public Table introspectedTableInformation (Table schema) {
		Connection connection = databaseConnection.getConnection();
		GeneralTable table = (GeneralTable)schema;
		
		String sql =STATUS_SQL+actualTableName.getTableMapping().getName()+"';" ;
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(sql);
			if(resultSet.next()){
				table.addInformation(MYSQL_ENGINE ,resultSet.getString(MYSQL_ENGINE) );
				table.addInformation(CREATE_TIME,StringUtility.format( resultSet.getDate(CREATE_TIME)));
				
				table.setComment( resultSet.getString(COMMENT) );
				
				table.addInformation(COLLATION, resultSet.getString(COLLATION));
				//判定视图
				//SHOW TABLE STATUS命令显示数据库信息
				// 如果是视图 Engine 为空  并且Comment 值是VIEW
				if("VIEW".equalsIgnoreCase(table.getComment()) && resultSet.getString(MYSQL_ENGINE)  == null){
					table.setSchemaType(StructureType.VIEW);
				}else{
					table.setSchemaType(StructureType.TABLE);
				}
			}
			resultSet.close();
			sql =CREATE_TABLE_SQL +"`"+ actualTableName.getTableMapping().getName()+"`";
			resultSet = connection.createStatement().executeQuery(sql);
			if(resultSet.next()){
				table.setSqlScipt(resultSet.getString(CREATE_TABLE));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return schema ;
	}
	
	private static final String PROCEDURE_COMMANT = "SELECT ROUTINE_COMMENT AS COMMENT FROM information_schema.ROUTINES ";
	
	private static final String PROCEDURE_SQL = "SELECT type, param_list, returns ,body FROM mysql.proc ";
	
	
	public Procedure introspectedProcedureInformation(Procedure schema){
		StringBuilder sql = new StringBuilder(PROCEDURE_COMMANT).append(" WHERE ");
		Connection connection = databaseConnection.getConnection();
		sql.append("ROUTINE_SCHEMA='")
		.append(getDatabasesNameFrom(connection))
		.append("' AND ")
		.append("SPECIFIC_NAME='")
		.append(actualTableName.getTableMapping().getName())
		.append("';")
		;
		ResultSet rs = null;
		try {
			rs = connection.createStatement().executeQuery(sql.toString());
			if(rs.next()){
				String comment = rs.getString("COMMENT");
				if(schema instanceof GeneralProcedure ){
					((GeneralProcedure)schema).setComment(comment);
				}
			}
			rs.close();
			
			sql.setLength(0);
			sql.append(PROCEDURE_SQL)
			.append("WHERE ")
			.append("db='")
			.append(getDatabasesNameFrom(connection))
			.append("' AND ")
			.append("name='")
			.append( actualTableName.getTableMapping().getName())
			.append("';");

			rs = connection.createStatement().executeQuery(sql.toString());
			if(rs.next()){
				Blob blob = null; 
				
				blob = rs.getBlob("param_list");
				String params = StringUtility.convertStreamToString(blob.getBinaryStream());
				blob = rs.getBlob("returns");
				String returns = StringUtility.convertStreamToString(blob.getBinaryStream());
				blob = rs.getBlob("body");
				String body = StringUtility.convertStreamToString(blob.getBinaryStream());
				String type =  rs.getString("type");
				sql.setLength(0);
				if("FUNCTION".equals(type)){
					sql.append("set global log_bin_trust_function_creators=1;\n");
				}
				sql.append("DROP "+type+" IF EXISTS `")
				.append(actualTableName.getTableMapping().getName())
				.append("`;\n")
				.append("CREATE ").append( type ).append(" `")
				.append(actualTableName.getTableMapping().getName())
				.append("`(");
				if(StringUtility.stringHasValue(params)){
					sql.append("\n    ").append(params.trim()).append("\n");
				}
				sql.append(")\n");
				if(StringUtility.stringHasValue(returns)){
					sql.append("    RETURNS ").append(returns.trim())
					.append("\n");
				}
				if(StringUtility.stringHasValue(schema.getComment())){
					sql.append("COMMENT '").append(schema.getComment()).append("'\n");
				}
				sql.append(body.trim()).append("\n")
				;
				if(schema instanceof GeneralProcedure ){
					((GeneralProcedure)schema).setSqlScipts(sql.toString());
				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
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
		return schema;
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
}
