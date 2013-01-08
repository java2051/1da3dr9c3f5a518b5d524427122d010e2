package com.honey.postgre.introspect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Procedure;
import com.honey.core.dbmapping.structure.Schema;
import com.honey.core.dbmapping.structure.StructureType;
import com.honey.core.dbmapping.structure.Table;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.introspected.DatabaseIntrospector;
import com.honey.general.databases.introspected.GeneralProcedure;
import com.honey.general.databases.introspected.GeneralTable;

/**
 * 
 * 使用Postgre 数据库语句获取数据库信息
 * @author Administrator
 *	
 */
public class PostgreSchemaInformation {
	
	private DatabaseConnection databaseConnection;
	
	private ActualSchema actualTableName;
	
	public PostgreSchemaInformation( DatabaseConnection databaseConnection, ActualSchema actualTableName){
		this.databaseConnection = databaseConnection;
		this.actualTableName = actualTableName;
	}
	
//	public Table introspectedTableInformation ( Table table ) {
//		//Schema answer = null ;
//		DatabaseIntrospector databaseIntrospector = new DatabaseIntrospector(this.databaseConnection);
//		if(StructureType.TABLE  == table.getSchemaType())
//			((GeneralTableStructure)table).setSqlScipt(
//				PostgreLocalSql.makePostgreTable(databaseConnection, actualTableName, 
//					table,
//					databaseIntrospector.introspectedAllColumns(actualTableName),
//					databaseIntrospector.introspectedPrimaryKeyColumns(actualTableName)
//				)
//			)
//		;
//		return table;
//	}

	public Procedure introspectedProcedureInformation (Procedure schema) {
		Procedure answer = null ;
		DatabaseIntrospector databaseIntrospector = new DatabaseIntrospector(this.databaseConnection);
		answer =(GeneralProcedure)databaseIntrospector.introspectedSchemaInformation(actualTableName);
//		ResultSet resultSet = null;
//		Connection connection = databaseConnection.getConnection();
//		try {
//			resultSet = connection.createStatement().executeQuery(PostgreLocalSql.getSchemaComment(databaseConnection, actualTableName));
//			if(resultSet.next()){
//				String comment = resultSet.getString(1);
////			if(StringUtility.stringHasValue(comment)){
////				table.setComment(comment);
////			}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
		return answer;
	}
	
	/**
	 * 获取表和视图信息
	 * @param schema
	 * @return
	 */
	public Table introspectedTableInformation (Table schema) {
		Connection connection = databaseConnection.getConnection();
		GeneralTable table = (GeneralTable)schema;
		ResultSet resultSet = null;
		try {
//			resultSet = connection.createStatement().executeQuery(PostgreLocalSql.getSchemaComment(databaseConnection, actualTableName));
//			if(resultSet.next()){
//				String comment = resultSet.getString(1);
//				if(StringUtility.stringHasValue(comment)){
//					table.setComment(comment);
//				}
//			}
//			resultSet.close();
			if(StructureType.TABLE  == table.getSchemaType()){				
				DatabaseIntrospector databaseIntrospector = new DatabaseIntrospector(this.databaseConnection);
				((GeneralTable)table).setSqlScipt(
						PostgreLocalSql.makePostgreTable(databaseConnection, actualTableName, 
						table,
						databaseIntrospector.introspectedAllColumns(actualTableName),
						databaseIntrospector.introspectedPrimaryKeyColumns(actualTableName)
						)
				);
			}
			if( StructureType.VIEW  == schema.getSchemaType() ){
				//StringBuilder sql = new StringBuilder();
				resultSet = connection.createStatement().executeQuery(PostgreLocalSql.getViewSelect(databaseConnection, actualTableName) );
				if(resultSet.next()){
					table.setSqlScipt(PostgreLocalSql.makePostgresqlView(databaseConnection, actualTableName, resultSet.getString(1), table.getComment()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if( resultSet != null ){
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if( connection != null ){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return schema ;
	}
}
