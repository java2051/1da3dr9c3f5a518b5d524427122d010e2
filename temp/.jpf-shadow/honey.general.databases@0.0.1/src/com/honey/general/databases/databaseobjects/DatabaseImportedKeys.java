package com.honey.general.databases.databaseobjects;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.honey.core.dbmapping.structure.Table;
import com.honey.core.dbmapping.structure.TableColumn;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.DataSourceException;
import com.honey.general.databases.introspected.GeneralForeignKey;

/**
 * 获取表的所有导入的键(外键关联)
 * 
 * @author Administrator
 * 
 */
class DatabaseImportedKeys extends DatabaseHost {
	
	private DatabaseTables databaseTables;
	
	private DatabaseTableColumns databaseTableColumns;
	
	/**
	 * 构造函数
	 * 
	 * @param databaseConnection
	 *            数据库链接配置
	 */
	public DatabaseImportedKeys(DatabaseConnection databaseConnection) {
		super(databaseConnection);
		databaseTables = new DatabaseTables(databaseConnection);
		databaseTableColumns = new DatabaseTableColumns(databaseConnection);
	}

	@Override
	public Object introspected(String table, String columnName) {
		try {
			return getImportedKeys(table);
		} catch (DataSourceException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object introspected(String table) {
		try {
			return getImportedKeys(table);
		} catch (DataSourceException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回表的外键
	 * @param table
	 * @throws DataSourceException
	 */
	private Object getImportedKeys(String table) throws DataSourceException {
		List<GeneralForeignKey> answer = new ArrayList<GeneralForeignKey>();
		DatabaseMetaData databaseMetaData = getDatabaseMetaData();
		
		ResultSet rs = null;
		if (!StringUtility.stringHasValue(table)) {
			table = null;
		}
		
		String localCatalog = super.getDatabaseConnection().getCatalog();
		String localSchema = super.getDatabaseConnection().getSchema();
		
		try {
			rs = databaseMetaData.getImportedKeys(
					super.getCatalog(localCatalog),
					super.getSchema(localSchema), 
					table
			);
			 
			while (rs.next()) {
				GeneralForeignKey gfks = new GeneralForeignKey();
				gfks.setTableStructure(
					(Table)databaseTables.introspected(rs.getString(3 )) //PKTABLE_NAME
				);
				gfks.setTableStructureColumn(
					(TableColumn)databaseTableColumns.introspected(gfks.getTable().getName(), rs.getString(4))//PKCOLUMN_NAME
				);
				gfks.setName( rs.getString(8) );//FKCOLUMN_NAME
				gfks.setComment( gfks.getTable().getComment() );
				
				answer.add(gfks);
			}
 		} catch (SQLException e) {

		} finally {
			releaseResources(rs);
			close();
		}
		
		return answer;
	}
}
