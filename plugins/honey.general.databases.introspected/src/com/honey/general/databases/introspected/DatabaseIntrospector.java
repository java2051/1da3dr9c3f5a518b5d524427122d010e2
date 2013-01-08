package com.honey.general.databases.introspected;

import java.util.List;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.core.dbmapping.structure.ExportedKey;
import com.honey.core.dbmapping.structure.ForeignKey;
import com.honey.core.dbmapping.structure.Function;
import com.honey.core.dbmapping.structure.Procedure;
import com.honey.core.dbmapping.structure.Schema;
import com.honey.core.dbmapping.structure.StructureType;
import com.honey.core.dbmapping.structure.Table;
import com.honey.core.mapping.TableMapping;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.databaseobjects.DatabaseObectFactory;

/**
 * 使用标准的JDBC获取数据的信息
 * @author Administrator
 */
public class DatabaseIntrospector {
	
	/** 数据库连接 */
	private DatabaseConnection databaseConnection;
	
	/** 数据库表列中的主键列 */
	private List<Column> primaryKeyColumns = null;

	/** 外键 */
	private List<ExportedKey> exportedKeyColumns = null;

	/** 外键 */
	private List<ForeignKey> foreignKeyColumns = null;

	/** 数据库表所有列 */
	private List<Column> allColumns = null;

	/** 数据库结构 */
	private Schema schema = null;
	
	private ActualSchema actualTableName;
	/**
	 * 构造函数
	 * @param databaseConnection
	 */
	public DatabaseIntrospector(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	/**
	 * 初始化过程必须先初始化 主键列 外键列 等等,最后初始化所有列
	 * 
	 * @param actualTableName
	 */
	private void initDatabaseIntrospector(ActualSchema actualTableName) {
		TableMapping tableMapping = actualTableName.getTableMapping();
		if (tableMapping.getMappingKind() ==TableMapping.MappingKind.DATABASE_TABLE &&(  this.actualTableName == null  || !this.actualTableName.getTableMapping().getName().equals(tableMapping.getName()) )) {
			clean();this.actualTableName = actualTableName;
			
			if (actualTableName.getSchema() != null) {
				StructureType structureType = actualTableName.getSchema().getSchemaType();
				if ( StructureType.TABLE == structureType|| 
					 StructureType.VIEW == structureType ||
					 StructureType.TEMPORARY == structureType
				) {
					schema = initSchemaInformation(actualTableName);
				} else if (StructureType.PROCEDURE == structureType) {
					schema = initProcedure(actualTableName);
				} else if (StructureType.FUNCTION  == structureType) {
					schema = initFunction(actualTableName);
				}
			} else {
				schema = initSchemaInformation(actualTableName);
				if (schema == null) {
					schema = initProcedure(actualTableName);
					if (schema == null) {
						schema = initFunction(actualTableName);
					}
				}
			}
			
			StructureType structureType = schema.getSchemaType();
			// 必须先初始化 主键 外键 列
			//if( StructureType.TABLE.equals(actualTableName.getStructureType()) ){
			if( StructureType.TABLE == structureType ){
				primaryKeyColumns = initPrimaryKeyColumns(actualTableName);
				exportedKeyColumns = initIntrospectedExportedKeyColumns(actualTableName);
				foreignKeyColumns = initIntrospectedForeignKeyColumns(actualTableName);
			}
			
			//初始化列
			if( StructureType.TABLE == structureType||
				StructureType.VIEW ==structureType ||
				StructureType.TEMPORARY ==structureType
			 ){// 最后初始所有列
				allColumns = initTableAllColumns(actualTableName);
			}else if (StructureType.PROCEDURE == structureType) {
				allColumns =initProcedureParameters(actualTableName);
			}else if (StructureType.FUNCTION == structureType) {
				allColumns =initFunctionParameters(actualTableName);
			}
		}
	}
	
	private void clean(){
		if(primaryKeyColumns != null ) primaryKeyColumns.clear();
		if(exportedKeyColumns != null ) exportedKeyColumns.clear();
		if(foreignKeyColumns != null ) foreignKeyColumns.clear();
		if(allColumns != null ) allColumns.clear();
		schema = null;
	}
	/**
	 * 获取存储过程所有的参数
	 * @param actualTableName
	 * @return
	 */
	private List<Column> initProcedureParameters(ActualSchema actualTableName){
		DatabaseObectFactory factory = DatabaseObectFactory.getInstance(
				databaseConnection,
				DatabaseObectFactory.DatabaseObectType.PROCEDURE_COLUMNS);
		List<Column> answer = factory.getSchemaColumns(actualTableName.getTableMapping().getName());
		return answer;
	}
	
	/**
	 * 获取函数所有的参数
	 * @param actualTableName
	 * @return
	 */
	private List<Column> initFunctionParameters(ActualSchema actualTableName){
		DatabaseObectFactory factory = DatabaseObectFactory.getInstance(
				databaseConnection,
				DatabaseObectFactory.DatabaseObectType.FUNCTION_COLUMNS);
		List<Column> answer = factory.getSchemaColumns(actualTableName.getTableMapping().getName());
		return answer;
	}
	
	/**
	 * 初始化主键列
	 * 
	 * @param actualTableName
	 * @return
	 */
	private List<Column> initPrimaryKeyColumns( ActualSchema actualTableName) {
		DatabaseObectFactory factory = DatabaseObectFactory.getInstance(
				databaseConnection,
				DatabaseObectFactory.DatabaseObectType.PRIMARY_KEYS);
		List<Column> answer = factory.getSchemaColumns(actualTableName.getTableMapping().getName());
		return  answer;
	}

	/**
	 * 初始化外键列
	 * 
	 * @param actualTableName
	 * @return
	 */
	private List<ExportedKey> initIntrospectedExportedKeyColumns( ActualSchema actualTableName) {
		DatabaseObectFactory factory = DatabaseObectFactory.getInstance(
				databaseConnection,
				DatabaseObectFactory.DatabaseObectType.EXPORTED_KEYS);
		List<ExportedKey> answer = factory.getSchemaColumns(actualTableName.getTableMapping().getName());
		return answer;
	}
	
	/**
	 * 初始化外键列(也是import key )
	 * 
	 * @param actualTableName
	 * @return
	 */
	private List<ForeignKey> initIntrospectedForeignKeyColumns( ActualSchema actualTableName) {
		DatabaseObectFactory factory = DatabaseObectFactory.getInstance(
				databaseConnection,
				DatabaseObectFactory.DatabaseObectType.IMPORTED_KEYS);
		List<ForeignKey> answer = factory.getSchemaColumns(actualTableName.getTableMapping().getName());
		return answer;
	}
	
	/**
	 * 初始化所有列
	 * 
	 * @param actualTableName
	 * @return
	 */
	private List<Column> initTableAllColumns( ActualSchema actualTableName) {
		DatabaseObectFactory factory = DatabaseObectFactory.getInstance(
				databaseConnection,
				DatabaseObectFactory.DatabaseObectType.TABLE_COLUMNS);
		List<Column> introspectedColumns = factory.getSchemaColumns(actualTableName.getTableMapping().getName());
		
		return introspectedColumns;
	}

	/**
	 * 获取存储过程
	 * 
	 * @param actualTableName
	 * @return
	 */
	private Procedure initProcedure( ActualSchema actualTableName) {
		DatabaseObectFactory factory = DatabaseObectFactory.getInstance(
				databaseConnection,
				DatabaseObectFactory.DatabaseObectType.PROCEDURES);
		Object obj = factory.getSchema(actualTableName.getTableMapping().getName());
		return (Procedure) obj;
	}

	/**
	 * 获取函数信息
	 * @param actualTableName
	 * @return
	 */
	private Function initFunction( ActualSchema actualTableName) {
		DatabaseObectFactory factory = DatabaseObectFactory.getInstance(
				databaseConnection,
				DatabaseObectFactory.DatabaseObectType.FUNCTIONS);
		Object obj = factory.getSchema(actualTableName.getTableMapping().getName());
		return (Function) obj;
	}

	/**
	 * 初始化表信息
	 * 
	 * @param actualTableName
	 * @return
	 */
	private Table initSchemaInformation( ActualSchema actualTableName) {
		DatabaseObectFactory factory = DatabaseObectFactory.getInstance(
				databaseConnection,
				DatabaseObectFactory.DatabaseObectType.TABLES
		);
		
		Table answer = (Table) factory.getSchema(actualTableName.getTableMapping().getName());
		return answer;
	}

	public Schema introspectedSchemaInformation( ActualSchema actualTableName) {
		initDatabaseIntrospector(actualTableName);
		return schema;
	}

	/**
	 * 获取所有的数据库列
	 * @param actualTableName
	 * @return
	 */
	public List<Column> introspectedAllColumns( ActualSchema actualTableName) {
		initDatabaseIntrospector(actualTableName);
		return allColumns;
	}

	/**
	 * 获取主键列
	 * @param actualTableName
	 * @return
	 */
	public List<Column> introspectedPrimaryKeyColumns( ActualSchema actualTableName) {
		initDatabaseIntrospector(actualTableName);
		return primaryKeyColumns;
	}
	
	/**
	 * 获取导出键列
	 * @param actualTableName
	 * @return
	 */
	public List<ExportedKey> introspectedExportedKeyColumns( ActualSchema actualTableName) {
		initDatabaseIntrospector(actualTableName);
		return exportedKeyColumns;
	}
	
	/**
	 * 获取外键列(导入键列)
	 * @param actualTableName
	 * @return
	 */
	public List<ForeignKey> introspectedForeignKeyColumns( ActualSchema actualTableName) {
		initDatabaseIntrospector(actualTableName);
		return foreignKeyColumns;
	}
	

//	public List<Column> introspectedExportedKeyColumns(ActualSchemaName actualTableName) {
//		initDatabaseIntrospector(actualTableName);
//		return exportedKeyColumns;
//	}
//
//	public List<Column> getForeignKeyColumns(ActualSchemaName actualTableName) {
//		initDatabaseIntrospector(actualTableName);
//		return foreignKeyColumns;
//	}
	
}
