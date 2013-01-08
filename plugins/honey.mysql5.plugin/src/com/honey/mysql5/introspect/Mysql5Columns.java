package com.honey.mysql5.introspect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JavaTypeResolver;
import com.honey.core.types.translator.MysqlJdbcTypeNameTranslator;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.introspected.GeneralTableColumn;


@Deprecated
public class Mysql5Columns {
	
	private static final String TABLE_CATALOG = "TABLE_CATALOG";
	private static final String TABLE_SCHEMA = "TABLE_SCHEMA";
	private static final String TABLE_NAME = "TABLE_NAME";
	private static final String COLUMN_NAME = "COLUMN_NAME";
	private static final String ORDINAL_POSITION = "ORDINAL_POSITION";
	private static final String COLUMN_DEFAULT = "COLUMN_DEFAULT";
	private static final String IS_NULLABLE = "IS_NULLABLE";
	private static final String DATA_TYPE = "DATA_TYPE";
	private static final String CHARACTER_MAXIMUM_LENGTH = "CHARACTER_MAXIMUM_LENGTH";
	private static final String CHARACTER_OCTET_LENGTH = "CHARACTER_OCTET_LENGTH";
	private static final String NUMERIC_PRECISION = "NUMERIC_PRECISION";
	private static final String NUMERIC_SCALE = "NUMERIC_SCALE";
	private static final String CHARACTER_SET_NAME = "CHARACTER_SET_NAME";
	private static final String COLLATION_NAME = "COLLATION_NAME";
	private static final String COLUMN_TYPE = "COLUMN_TYPE";
	private static final String COLUMN_KEY = "COLUMN_KEY";
	private static final String EXTRA = "EXTRA";
	private static final String PRIVILEGES = "PRIVILEGES";
	private static final String COLUMN_COMMENT = "COLUMN_COMMENT";
	
	private Connection connection;
	private DatabaseMetaData databaseMetaData; 
	private ActualSchema actualTableName;
	
	public Mysql5Columns( Connection connection, DatabaseMetaData databaseMetaData, ActualSchema actualTableName){
		this.connection = connection;
		this.databaseMetaData = databaseMetaData;
		this.actualTableName = actualTableName;
	}
	
	/**
	 * 按照column_key获取相应的字段列表
	 *  mysql column_key的定义</br>
	 *  	PRI 是主键
	 *  	MUL 是外键
	 * @param column_key 如果为空获取全部列
	 * @return
	 */
	public List<Column> introspectedColumnsFromColumnKey(String column_key ) {
		List<Column> answer = new ArrayList<Column> ();
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ").
			append(TABLE_CATALOG).append(',').
			append(TABLE_SCHEMA).append(',').
			append(TABLE_NAME).append(',').
			append(COLUMN_NAME).append(',').
			append(ORDINAL_POSITION).append(',').
			append(COLUMN_DEFAULT).append(',').
			append(IS_NULLABLE).append(',').
			append(DATA_TYPE).append(',').
			append(CHARACTER_MAXIMUM_LENGTH).append(',').
			append(CHARACTER_OCTET_LENGTH).append(',').
			append(NUMERIC_PRECISION).append(',').
			append(NUMERIC_SCALE).append(',').
			append(CHARACTER_SET_NAME).append(',').
			append(COLLATION_NAME).append(',').
			append(COLUMN_TYPE).append(',').
			append(COLUMN_KEY).append(',').
			append(EXTRA).append(',').
			append(PRIVILEGES).append(',').
			append(COLUMN_COMMENT).
			append(" FROM information_schema.`COLUMNS` WHERE  TABLE_SCHEMA='")
			.append(getDatabasesNameFrom(connection))
			.append("' AND TABLE_NAME='").append(actualTableName.getTableMapping().getName()).append("'");
			if( StringUtility.stringHasValue(column_key) ){
				sb.append(" AND ").append(COLUMN_KEY).append("='").append(column_key).append("'");
			}
			
			sb.append(" ORDER BY " ).append(ORDINAL_POSITION) .append(" ASC");
			System.out.println(sb.toString());
			ResultSet rs =  connection.createStatement().executeQuery(sb.toString()) ;
			
			GeneralTableColumn column = null;
			
			while(rs.next()){
				column = new GeneralTableColumn();
				column.setDbTypeName(rs.getString(DATA_TYPE).toUpperCase()); //$NON-NLS-1$
				//column.setJdbcType( MysqlJdbcTypeNameTranslator.getJdbcType(column.getDbTypeName()) ) ; //$NON-NLS-1$
				if(rs.getObject(CHARACTER_MAXIMUM_LENGTH)== null){
					column.setLength(rs.getInt(NUMERIC_PRECISION)); //$NON-NLS-1$
				}else{
					column.setLength(rs.getLong(CHARACTER_MAXIMUM_LENGTH)); //$NON-NLS-1$
				}
				column.setName(rs.getString(COLUMN_NAME)); //$NON-NLS-1$
		        column.setNullable( "YES".equalsIgnoreCase(rs.getString(IS_NULLABLE))); //$NON-NLS-1$
		        column.setScale(rs.getInt(NUMERIC_SCALE)); //$NON-NLS-1$
		        column.setComment(rs.getString(COLUMN_COMMENT)); //$NON-NLS-1$
		        column.setDefaultValue(rs.getString(COLUMN_DEFAULT)); //$NON-NLS-1$
		        column.setPrimaryKey("PRI".equalsIgnoreCase((rs.getString(COLUMN_KEY)))); //$NON-NLS-1$
		        
		        if( databaseMetaData.getJDBCMajorVersion() >= 3 ){//只有jdbc版本号高于3.0才支持getGeneratedKeys方法(获取自增列最后插入的值)
		        	column.setAutoIncrement( "auto_increment".equalsIgnoreCase(rs.getString(EXTRA))) ;
		        }
		        
		        //处理类型映射
		        JavaTypeResolver javaTypeResolver = new JavaTypeResolver(false);
		        FullyQualifiedJavaType fullyQualifiedJavaType = javaTypeResolver .calculateJavaType(column);
				if (fullyQualifiedJavaType != null) {
					column .setFullyQualifiedJavaType(fullyQualifiedJavaType);
					//introspectedColumn.setDbTypeName(javaTypeResolver .calculateJdbcTypeName(introspectedColumn));
		        }
		        
				answer.add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		return answer;
	}
		
	/*private List<TableColumn> introspectedAllColumnsFromJDBC() {
		List<TableColumn> answer = new ArrayList<TableColumn> ();
		
		DatabaseIntrospector databaseIntrospector = new DatabaseIntrospector(connection,databaseMetaData,actualTableName);
		String localCatalog = actualTableName.getCatalog();
        String localSchema = actualTableName.getSchema();
        String localTableName = actualTableName.getTableName();
        try {
			if (actualTableName.isWildcardEscapingEnabled()) {
				localSchema = databaseIntrospector.wildcardEscapingEnabled(localSchema);
				localTableName = databaseIntrospector.wildcardEscapingEnabled(localTableName);			    
			}
			ResultSet rs = databaseMetaData.getColumns(localCatalog, localSchema,  localTableName, null);
			while (rs.next()) {
				TableColumn introspectedColumn = databaseIntrospector.getTableColumnFromRs(databaseMetaData, rs) ;
				introspectedColumn.setAutoIncrement( isAutoIncrement(connection, databaseMetaData, actualTableName, introspectedColumn) );
				answer.add(introspectedColumn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;	
	}
	
	private boolean isAutoIncrement( Connection connection, DatabaseMetaData databaseMetaData, ActualTableName actualTableName,TableColumn introspectedColumn) {
		boolean answer = false;
		String dbname = "" ;
		try {
			dbname = getDatabasesNameFrom(connection);
			
			String sql = "select EXTRA from information_schema.`COLUMNS` where  TABLE_SCHEMA='"+dbname+"' AND TABLE_NAME='"+actualTableName.getTableName()+"' AND COLUMN_NAME='"+introspectedColumn.getFieldName()+"'" ;
			ResultSet res =  connection.createStatement().executeQuery(sql) ;
			while(res.next()){
				answer = "auto_increment".equalsIgnoreCase(res.getString("Extra"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
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
