package com.honey.general.databases.databaseobjects;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JavaTypeResolver;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.DataSourceException;
import com.honey.general.databases.introspected.GeneralTableColumn;

/**
 * 获取数据列信息
 * @author Administrator
 *
 */
class DatabaseTableColumns  extends DatabaseHost{

	/**
	 * 构造函数
	 * @param databaseConnection 数据库配置信息
	 */
	public DatabaseTableColumns(DatabaseConnection databaseConnection) {
		super(databaseConnection);
	
	}

	@Override
	public Object introspected( String table,
			String columnName) {
		try {
			return getColumns( table, columnName);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object introspected( String table) {
		try {
			return getColumns( table, null);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取数据库列
	 * @param table 表名称
	 * @param columnName 如果指定列的名称返回列的信息;如果为null返回所有列的信息
	 * @throws DataSourceException
	 */
	private Object getColumns( String table,String columnName) throws  DataSourceException{
		List<GeneralTableColumn>  answer = new ArrayList<GeneralTableColumn>(); 
		
		DatabaseMetaData databaseMetaData = getDatabaseMetaData();
		ResultSet rs = null;
		
		if( !StringUtility.stringHasValue(columnName) ){
			columnName = null ;
		}
		
		String localCatalog = super.getDatabaseConnection().getCatalog();
		String localSchema = super.getDatabaseConnection().getSchema();
		
		try {
			rs = databaseMetaData.getColumns(
					super.getCatalog( localCatalog), 
					super.getSchema( localSchema), 
					table, 
					columnName
			);
			while (rs.next()) {
				GeneralTableColumn introspectedColumn = getTableColumnFromRs(databaseMetaData, rs) ;
				answer.add(introspectedColumn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(rs);
			close();
		}
		
		//返回值处理
		switch(answer.size()){
			case 0 :
				return null;
			case 1 : 
				return answer.get(0);
			default :
				return answer;	
		}
	}
	
	
	
	/**
	 * jdbc描述
     * Retrieves a description of table columns available in 
     * the specified catalog.
     *
     * <P>Only column descriptions matching the catalog, schema, table
     * and column name criteria are returned.  They are ordered by
     * <code>TABLE_CAT</code>,<code>TABLE_SCHEM</code>, 
     * <code>TABLE_NAME</code>, and <code>ORDINAL_POSITION</code>.
     *
     * <P>Each column description has the following columns:
     *  <OL>
     *	<LI><B>TABLE_CAT</B> String => table catalog (may be <code>null</code>)
     *	<LI><B>TABLE_SCHEM</B> String => table schema (may be <code>null</code>)
     *	<LI><B>TABLE_NAME</B> String => table name
     *	<LI><B>COLUMN_NAME</B> String => column name
     *	<LI><B>DATA_TYPE</B> int => SQL type from java.sql.Types
     *	<LI><B>TYPE_NAME</B> String => Data source dependent type name,
     *  for a UDT the type name is fully qualified
     *	<LI><B>COLUMN_SIZE</B> int => column size.  
     *	<LI><B>BUFFER_LENGTH</B> is not used.
     *	<LI><B>DECIMAL_DIGITS</B> int => the number of fractional digits. Null is returned for data types where  
     * DECIMAL_DIGITS is not applicable.
     *	<LI><B>NUM_PREC_RADIX</B> int => Radix (typically either 10 or 2)
     *	<LI><B>NULLABLE</B> int => is NULL allowed.
     *      <UL>
     *      <LI> columnNoNulls - might not allow <code>NULL</code> values
     *      <LI> columnNullable - definitely allows <code>NULL</code> values
     *      <LI> columnNullableUnknown - nullability unknown
     *      </UL>
     *	<LI><B>REMARKS</B> String => comment describing column (may be <code>null</code>)
     * 	<LI><B>COLUMN_DEF</B> String => default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be <code>null</code>)
     *	<LI><B>SQL_DATA_TYPE</B> int => unused
     *	<LI><B>SQL_DATETIME_SUB</B> int => unused
     *	<LI><B>CHAR_OCTET_LENGTH</B> int => for char types the 
     *       maximum number of bytes in the column
     *	<LI><B>ORDINAL_POSITION</B> int	=> index of column in table 
     *      (starting at 1)
     *	<LI><B>IS_NULLABLE</B> String  => ISO rules are used to determine the nullability for a column.
     *       <UL>
     *       <LI> YES           --- if the parameter can include NULLs
     *       <LI> NO            --- if the parameter cannot include NULLs
     *       <LI> empty string  --- if the nullability for the 
     * parameter is unknown
     *       </UL>
     *  <LI><B>SCOPE_CATLOG</B> String => catalog of table that is the scope
     *      of a reference attribute (<code>null</code> if DATA_TYPE isn't REF)
     *  <LI><B>SCOPE_SCHEMA</B> String => schema of table that is the scope
     *      of a reference attribute (<code>null</code> if the DATA_TYPE isn't REF)
     *  <LI><B>SCOPE_TABLE</B> String => table name that this the scope
     *      of a reference attribure (<code>null</code> if the DATA_TYPE isn't REF)
     *  <LI><B>SOURCE_DATA_TYPE</B> short => source type of a distinct type or user-generated
     *      Ref type, SQL type from java.sql.Types (<code>null</code> if DATA_TYPE 
     *      isn't DISTINCT or user-generated REF)
     *   <LI><B>IS_AUTOINCREMENT</B> String  => Indicates whether this column is auto incremented
     *       <UL>
     *       <LI> YES           --- if the column is auto incremented
     *       <LI> NO            --- if the column is not auto incremented
     *       <LI> empty string  --- if it cannot be determined whether the column is auto incremented
     * parameter is unknown
     *       </UL>
     *  </OL>
     *    
     * <p>The COLUMN_SIZE column the specified column size for the given column. 
     * For numeric data, this is the maximum precision.  For character data, this is the length in characters. 
     * For datetime datatypes, this is the length in characters of the String representation (assuming the 
     * maximum allowed precision of the fractional seconds component). For binary data, this is the length in bytes.  For the ROWID datatype, 
     * this is the length in bytes. Null is returned for data types where the
     * column size is not applicable.
     *
     */
	private GeneralTableColumn getTableColumnFromRs(DatabaseMetaData databaseMetaData, ResultSet rs) throws SQLException{
		GeneralTableColumn introspectedColumn = new GeneralTableColumn();
		introspectedColumn.setJdbcType(rs.getInt("DATA_TYPE"));
		introspectedColumn.setDbTypeName(rs.getString("TYPE_NAME"));
        introspectedColumn.setLength(rs.getInt("COLUMN_SIZE"));
        introspectedColumn.setName(rs.getString("COLUMN_NAME"));
        introspectedColumn.setNullable(rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
        introspectedColumn.setScale(rs.getInt("DECIMAL_DIGITS"));
        introspectedColumn.setComment(rs.getString("REMARKS"));
        introspectedColumn.setDefaultValue(rs.getString("COLUMN_DEF"));
        try{//postgre 不支持IS_AUTOINCREMENT
        	introspectedColumn.setAutoIncrement( "YES".equalsIgnoreCase(rs.getString("IS_AUTOINCREMENT"))) ;
        }catch(Exception e){
        	
        }
        //处理类型映射
        JavaTypeResolver javaTypeResolver = new JavaTypeResolver(false);
        FullyQualifiedJavaType fullyQualifiedJavaType = javaTypeResolver .calculateJavaType(introspectedColumn);
		if (fullyQualifiedJavaType != null) {
			introspectedColumn .setFullyQualifiedJavaType(fullyQualifiedJavaType);
			//introspectedColumn.setDbTypeName(javaTypeResolver .calculateJdbcTypeName(introspectedColumn));
        }
		
//		for(int i =1;i<=23;i++){
//			System.out.println(rs.getObject(i));
//		}
		
		
       return introspectedColumn;
	}
	
}
