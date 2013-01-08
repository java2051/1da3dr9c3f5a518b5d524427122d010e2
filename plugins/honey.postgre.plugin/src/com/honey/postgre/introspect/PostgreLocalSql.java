package com.honey.postgre.introspect;

import java.util.ArrayList;
import java.util.List;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.core.dbmapping.structure.Schema;
import com.honey.core.dbmapping.structure.TableColumn;
import com.honey.core.utils.EmptyUtility;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;

/**
 * Postgre 数据获取表相关信息的sql语句
 * @author Administrator
 *
 */
class PostgreLocalSql {
	
	/**
	 * 获取postgre数据库结构comment的sql语句
	 * @param databaseConnection 数据库连接
	 * @param actualTableName postgre数据库结构 
	 * @return
	 */
	static String getSchemaComment(DatabaseConnection databaseConnection,ActualSchema actualTableName ){
		//String sql ="SELECT obj_description('"+actualTableName.getTableMapping().getName()+"'::regclass) as comment";
		//String sql ="SELECT  COALESCE(description,'') AS comment FROM pg_description WHERE objoid='"+actualTableName.getTableMapping().getName()+"'::regclass and objsubid=0;" ;
		// select * from pg_description  where  description like '%存储%'
		return new StringBuilder()
		.append("SELECT obj_description('")
		.append(getWholeSchemaName(databaseConnection,actualTableName.getTableMapping().getName()))
		.append("'::regclass) as comment")
		.toString()
		;
	}
	
	/**
	 * 查询列的comment
	 * @param databaseConnection 数据库连接
	 * @param tableName 表
	 * @param columnName 列名称
	 * @return
	 */
	static String getTableCloumnComment(DatabaseConnection databaseConnection,String tableName ,String columnName){
		StringBuilder answer= new StringBuilder();
		String databaseSchema =databaseConnection.getSchema();
		answer.append("SELECT COALESCE(a.description,'') AS comment FROM pg_catalog.pg_description a,pg_catalog.pg_attribute b WHERE objoid='");
		if(StringUtility.stringHasValue(databaseSchema)){
			answer.append("\"").append(databaseSchema).append("\".");
		}
		answer.append("\"").append(tableName).append("\"")
		.append("'::regclass AND a.objoid=b.attrelid AND a.objsubid=b.attnum AND b.attname='")
		.append(columnName).append("'");
		return answer.toString();
	}
	
	/**
	 * 获取postgre数据视图的查询语句
	 * @param databaseConnection  数据库连接
	 * @param actualTableName 数据库结构 
	 * @return
	 */
	static String getViewSelect(DatabaseConnection databaseConnection,ActualSchema actualTableName ){
		return new StringBuilder()
		.append("SELECT pg_get_viewdef('")
		.append(getWholeSchemaName(databaseConnection,actualTableName.getTableMapping().getName()))
		.append("',true); ")
		.toString();
	}
	
	/**
	 * 创建postgre数据库视图
	 * @param databaseConnection 数据库连接
	 * @param actualTableName 视图名称
	 * @param viewSelect 视图查询语句
	 * @param comment 视图的comment
	 * @return
	 */
	static String makePostgresqlView(DatabaseConnection databaseConnection,ActualSchema actualTableName,String viewSelect,String comment ){
		String wholeSchemaName=getWholeSchemaName(databaseConnection,actualTableName.getTableMapping().getName());
		StringBuilder answer = new StringBuilder()
		.append("DROP VIEW IF EXISTS ")
		.append(wholeSchemaName)
		.append(";\n")
		.append("CREATE VIEW ")
		.append(wholeSchemaName)
		.append(" AS \n")
		.append(viewSelect);
		if(StringUtility.stringHasValue(comment)){
			answer.append("\nCOMMENT ON VIEW ").append(wholeSchemaName).append(" IS '").append(comment).append("';");
		}
		return answer.toString();
	}
	
	/**
	 * 创建postgre数据库表
	 * @param databaseConnection 数据库连接
	 * @param actualTableName 名称
	 * @param schema 表名称
	 * @param columns 列
	 * @param PkColumns 主键
	 * @return
	 */
	static String makePostgreTable(DatabaseConnection databaseConnection,ActualSchema actualTableName,Schema schema ,List<Column> columns,List<Column> PkColumns){
		String wholeSchemaName=getWholeSchemaName(databaseConnection,actualTableName.getTableMapping().getName());
		List<TableColumn> comments = new ArrayList<TableColumn>();
		StringBuilder answer = new StringBuilder()
		.append("DROP TABLE IF EXISTS ").append(wholeSchemaName).append(";\n")
		.append("CREATE TABLE ").append(wholeSchemaName).append("(\n");
        boolean comma = false;
        for(Column column : columns ){
			TableColumn tc= (TableColumn) column;
			if (comma)answer.append(",\n"); else comma = true;
			answer.append("    \"")
			.append(column.getName())
			.append("\" ")
			.append(column.getDbTypeName());
			if(column.getLength() > 0  ){
				answer.append("(");
				answer.append(column.getLength());
				if(column.getScale()>0 ){
					answer.append(",").append(column.getScale());
				}
				answer.append(")");
			}
			if(StringUtility.stringHasValue(tc.getDefaultValue())){
				answer.append(" ")
				.append("DEFAULT ")
				.append(tc.getDefaultValue())
				;
			}
			if( !column.isNullable()){
				answer.append(" ").append("NOT NULL");
			}
			
			if(StringUtility.stringHasValue(column.getComment())){
				comments.add(tc);
			}
		}
		if(! EmptyUtility.isListEmpty(PkColumns) ){
			answer.append(" ,\n    PRIMARY KEY (");
			comma = false;
			for(Column column :  PkColumns){
				if (comma)answer.append(","); else comma = true;
				answer.append("\"").append(column.getName() ).append("\"");
			}
			answer.append(")");
		}
		answer.append("\n);");
		if(StringUtility.stringHasValue( schema.getComment() )){
			answer.append("\n")
			.append("COMMENT ON TABLE ")
			.append(wholeSchemaName).append(" IS '")
			.append(schema.getComment())
			.append("' ;");
			;
		}
		
		if(! EmptyUtility.isListEmpty(comments) ){
			answer.append("\n");
			comma = false;
			for(TableColumn tc : comments ){
				if (comma)answer.append("\n"); else comma = true;
				answer.append("COMMENT ON COLUMN ").append(wholeSchemaName)
				.append(".\"")
				.append(tc.getName())
				.append("\" IS '")
				.append(tc.getComment())
				.append("' ;");
			}
		}
		return answer.toString();
	}

	/**
	 * 获取数据库结构全部名称(数据库的schema+数据库名称)
	 * @param databaseConnection
	 * @param schemaName
	 * @return
	 */
	static String getWholeSchemaName(DatabaseConnection databaseConnection,String schemaName ){
		StringBuilder answer= new StringBuilder();
		String databaseSchema =databaseConnection.getSchema();
		if(StringUtility.stringHasValue(databaseSchema)){
			answer.append("\"").append(databaseSchema).append("\".");
		}
		answer.append("\"").append(schemaName).append("\"");
		return answer.toString();
	}

}
