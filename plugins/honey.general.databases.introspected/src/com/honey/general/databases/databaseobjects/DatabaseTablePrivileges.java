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
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.DataSourceException;

/**
 * 获取数据库表权限信息
 * @author Administrator
 *
 */
@Deprecated
class DatabaseTablePrivileges  extends DatabaseHost {

	/**
	 * 构造函数
	 * @param databaseConnection 数据库配置信息
	 */
	public DatabaseTablePrivileges(DatabaseConnection databaseConnection) {
		super(databaseConnection);
		
	}

	@Override
	public Object introspected( String table,
			String columnName) {
		try {
			return getTablePrivileges( table);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object introspected(String table) {
		try {
			return getTablePrivileges(table);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回当前的对于当前用户的所有权限
	 * @param table
	 * @throws DataSourceException
	 */
	private Object getTablePrivileges( String table) throws DataSourceException{
		
		List<TablePrivilegeDatabaseStructure> answer = new ArrayList<TablePrivilegeDatabaseStructure>();
		
		
		DatabaseMetaData databaseMetaData = getDatabaseMetaData();
		ResultSet rs = null;
		if( !StringUtility.stringHasValue(table) ){
			table = null ;
		}
		
		String localCatalog = super.getDatabaseConnection().getCatalog();
		String localSchema = super.getDatabaseConnection().getSchema();
		
		try {
			rs = databaseMetaData.getTablePrivileges(
					getCatalog( localCatalog), 
					getSchema( localSchema), 
					table
			);
			while(rs.next() ){
				TablePrivilegeDatabaseStructure tpds = new TablePrivilegeDatabaseStructure();
				tpds.setName(rs.getString(6)  );//PRIVILEGE
				tpds.setVendor(new Vendor(databaseMetaData.getDatabaseProductName() ,databaseMetaData.getDatabaseMajorVersion(),databaseMetaData.getDatabaseMinorVersion()));
			}
		} catch (SQLException e) {
			
		} finally {
			releaseResources(rs);
			close();
		}
		return answer;
	}
	
	public class TablePrivilegeDatabaseStructure implements Schema{

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
