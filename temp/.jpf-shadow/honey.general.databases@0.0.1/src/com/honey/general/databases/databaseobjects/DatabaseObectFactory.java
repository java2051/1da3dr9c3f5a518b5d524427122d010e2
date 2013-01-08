package com.honey.general.databases.databaseobjects;

import java.util.List;

import com.honey.core.dbmapping.structure.Schema;
import com.honey.general.databases.DatabaseConnection;

/**
 * 数据库对象工厂
 * @author Administrator
 *
 */
public class DatabaseObectFactory {
	
	private DatabaseHost databaseHost;
	
	private DatabaseObectFactory(DatabaseConnection databaseConnection ,DatabaseObectType databaseObectType ){
		databaseHost = getDatabaseHost(databaseConnection, databaseObectType);
	}

	/**
	 * 获取工厂实例
	 * @param databaseConnection
	 * @param databaseObectType
	 * @return
	 */
	public static DatabaseObectFactory  getInstance(DatabaseConnection databaseConnection ,DatabaseObectType databaseObectType  ){
		
		return new DatabaseObectFactory(databaseConnection, databaseObectType);	
	}
	
	/**
	 * 获取数据库对象
	 * @param schemaName
	 */
	@Deprecated
	public final Object getDatabaseObject( String schemaName){
		Object answer = databaseHost.introspected( schemaName); 
		databaseHost.close();
		return 	answer;
	}
	
	
	/**
	 * 获取数据库对象
	 * @param schemaName
	 */
	public final List getSchemaColumns( String schemaName){
		Object answer = databaseHost.introspected( schemaName); 
		databaseHost.close();
		return 	(List)answer;
	}
	
	/**
	 * 获取数据库对象
	 * @param schemaName
	 */
	public final Schema getSchema( String schemaName){
		Object answer = databaseHost.introspected( schemaName); 
		databaseHost.close();
		return 	(Schema)answer;
	}
	
	/**
	 * 根据对象类型构造host
	 * @param databaseConnection
	 * @param databaseObectType
	 * @return
	 */
	private final DatabaseHost getDatabaseHost(DatabaseConnection databaseConnection,DatabaseObectType databaseObectType){
		switch(databaseObectType.getType()){
			case  0 : //schema 对象
				return new DatabaseSchemas(databaseConnection);
		
			case  1 : //数据库对象
				return new DatabaseTables(databaseConnection);
		
			case  2 : //数据库表字段对象
				return new DatabaseTableColumns(databaseConnection);
		 
			case  3 : //数据库表权限对象
				return new DatabaseTablePrivileges(databaseConnection);
		
			case  4 : //外键关联对象
				return new DatabaseExportedKeys(databaseConnection);
				
			case  5 : //外键关联对象
				return new DatabaseImportedKeys(databaseConnection);
				

			case  6 : //主键对象
				return new DatabasePrimaryKeys(databaseConnection);
		
			case  7 : //存储过程对象
				return new DatabaseProcedures(databaseConnection);
		
			case  8 : //存储过程参数对象
				return new DatabaseProcedureParameters(databaseConnection);
		
			case  9 : //函数对象
				return new DatabaseFunctions(databaseConnection);
				
			case  10 : //函数参数对象
				return new DatabaseFunctionParameters(databaseConnection);
		
			default:
				return null;
		}
	}
	
	public enum DatabaseObectType{
		/**schema 对象 */
		SCHEMAS ( (short)0 ),
		
		/**数据库对象*/
		TABLES ( (short)1 ),
		
		/**数据库表字段对象*/
		TABLE_COLUMNS ( (short)2 ),
		
		/**数据库表权限对象 */
		TABLE_PRIVILEGES ( (short)3 ),
		
		/**外键关联对象*/
		EXPORTED_KEYS ( (short)4 ),
		
		/**外键关联对象*/
		IMPORTED_KEYS ( (short)5 ),
		
		
		/**主键对象*/
		PRIMARY_KEYS ( (short)6 ),
		
		/**存储过程对象*/
		PROCEDURES ( (short)7 ),
		
		/**存储过程参数对象*/
		PROCEDURE_COLUMNS ( (short)8 ),
		
		/**函数对象*/
		FUNCTIONS ( (short)9 ),
		
		/**函数参数对象*/
		FUNCTION_COLUMNS ( (short)10 ),
		;
		
		private short type;
		
		private DatabaseObectType(short type){
			this.type = type;
		}

		public short getType() {
			return type;
		}
	}
}
