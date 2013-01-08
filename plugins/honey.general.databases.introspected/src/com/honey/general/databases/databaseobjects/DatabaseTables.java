package com.honey.general.databases.databaseobjects;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.honey.core.dbmapping.structure.StructureType;
import com.honey.core.types.Vendor;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.DataSourceException;
import com.honey.general.databases.introspected.GeneralTable;

/**
 * 获取表信息
 * @author Administrator
 *
 */
class DatabaseTables extends DatabaseHost{

	/**
	 * 构造函数
	 * @param databaseConnection 数据库配置信息
	 */
	public DatabaseTables(DatabaseConnection databaseConnection) {
		super(databaseConnection);
	}

	@Override
	public Object introspected(String tableName,
			String columnName) {
		try {
			return getTables(tableName);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object introspected( String tableName) {
		try {
			return getTables( tableName);
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
	private Object getTables(String tableName) throws DataSourceException {
		ResultSet rs = null;
		List<GeneralTable>  answer = new ArrayList<GeneralTable>(); 
		if( !StringUtility.stringHasValue(tableName)){
			tableName = null;
		}
		
		String localCatalog = super.getDatabaseConnection().getCatalog();
		String localSchema = super.getDatabaseConnection().getSchema();
		
		try {
			DatabaseMetaData databaseMetaData = getDatabaseMetaData();
			
			rs = databaseMetaData.getTables(
					super.getCatalog( localCatalog ), 
					super.getSchema( localSchema ),
					tableName, 
					new String[] { 
							"TABLE", 
							"VIEW", 
							"SYSTEM TABLE", 
							"GLOBAL TEMPORARY", 
							"LOCAL TEMPORARY", 
							"ALIAS",
							"SYNONYM"
					}
			);
			while(rs.next()){
				GeneralTable gts = new GeneralTable();
				gts.setCatalog(localCatalog);
				gts.setSchema(localSchema);
				gts.setVendor(new Vendor(databaseMetaData.getDatabaseProductName() ,databaseMetaData.getDatabaseMajorVersion(),databaseMetaData.getDatabaseMinorVersion()));
				
				gts.setName(rs.getString(3));//TABLE_NAME
				if("VIEW".equalsIgnoreCase(rs.getString(4))) //TABLE_TYPE
					gts.setSchemaType( StructureType.VIEW );
        		else
        			gts.setSchemaType( StructureType.TABLE );
				
				gts.setComment( rs.getString(5) ); //REMARKS
				
				gts.setMoreInformation(super.getDatabaseProperties());
				answer.add(gts);
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
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
}
