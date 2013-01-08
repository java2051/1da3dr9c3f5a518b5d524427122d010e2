package com.honey.general.databases.databaseobjects;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.honey.core.dbmapping.structure.Schema;
import com.honey.core.dbmapping.structure.StructureType;
import com.honey.core.types.Vendor;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.DataSourceException;

/**
 * 获取数据所有的schema
 * @author Administrator
 *
 */
class DatabaseSchemas extends DatabaseHost{

	/**
	 * 构造函数
	 * @param databaseConnection 数据连接配置信息
	 */
	public DatabaseSchemas(DatabaseConnection databaseConnection) {
		super(databaseConnection);
	}

	@Override
	public Object introspected( String tableName,
			String columnName) {
		try {
			return getSchemas( tableName);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Object introspected( String tableName ) {
		try {
			return getSchemas( tableName );
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	/**
	 * 返回数据库表信息
	 * @param tableName 如果指定表名称返回表的信息;如果为null返回所有表的信息
	 * @param types
	 * @throws DataSourceException
	 */
	private Object getSchemas( String tableName )
			throws DataSourceException {
		ResultSet rs = null;
		List<SchemasDatabaseStructure> answer = new ArrayList<SchemasDatabaseStructure>();
		
		String localCatalog = super.getDatabaseConnection().getCatalog();
		String localSchema = super.getDatabaseConnection().getSchema();
		
		try {
			DatabaseMetaData databaseMetaData = getDatabaseMetaData();
			
			rs = databaseMetaData.getSchemas(
					super.getCatalog( localCatalog), 
					super.getSchema( localSchema)
			)
			;
			while(rs.next() ){
				SchemasDatabaseStructure sds = new SchemasDatabaseStructure();
				sds.setName(rs.getString(1)); //TABLE_SCHEM 
				sds.setComment(sds.getName());
				sds.setVendor(new Vendor(databaseMetaData.getDatabaseProductName() ,databaseMetaData.getDatabaseMajorVersion(),databaseMetaData.getDatabaseMinorVersion()));
				answer.add(sds);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(rs);
			close();
		}
		return answer;
	}

	public class SchemasDatabaseStructure implements Schema{
		
		private String name ;
		
		private String javaProperty;
		
		private String comment ;
		
		private Vendor vendor;
		
		@Override
		public Map<String, String> getMoreInformation() {
			return new HashMap<String, String>();
		}

		@Override
		public StructureType getSchemaType() {
			return StructureType.OTHER;
		}

		@Override
		public String getSqlScipt() {
			return null;
		}

		@Override
		public Vendor getVendor() {
			return this.vendor;
		}

		@Override
		public String getComment() {
			return this.comment;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public void setVendor(Vendor vendor) {
			this.vendor = vendor;
		}

		@Override
		public String getJavaProperty() {
			return javaProperty;
		}

		public void setJavaProperty(String javaProperty) {
			this.javaProperty = javaProperty;
		}

		@Override
		public boolean isIncludeBlobColumn() {
			return false;
		}

		@Override
		public boolean isIncludeEnumColumn() {
			return false;
		}

		@Override
		public boolean isIncludePrimaryKeyColumn() {
			return false;
		}
		
	} 
}
