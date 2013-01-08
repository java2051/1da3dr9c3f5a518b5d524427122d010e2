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
import com.honey.general.databases.introspected.GeneralExportedKey;

/**
 * 获取表的所有导出的键(外键关联)
 * 
 * @author Administrator
 * 
 */
 class DatabaseExportedKeys extends DatabaseHost {
	
	private DatabaseTables databaseTables;
	
	private DatabaseTableColumns databaseTableColumns;
	
	/**
	 * 构造函数
	 * 
	 * @param databaseConnection
	 *            数据库链接配置
	 */
	public DatabaseExportedKeys(DatabaseConnection databaseConnection) {
		super(databaseConnection);
		databaseTables = new DatabaseTables(databaseConnection);
		databaseTableColumns = new DatabaseTableColumns(databaseConnection);
	}

	@Override
	public Object introspected(String table, String columnName) {
		try {
			return getExportedKeys(table);
		} catch (DataSourceException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object introspected(String table) {
		try {
			return getExportedKeys(table);
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
	private Object getExportedKeys(String table) throws DataSourceException {
		
		List<GeneralExportedKey> answer = new ArrayList<GeneralExportedKey>();
		
		DatabaseMetaData databaseMetaData = getDatabaseMetaData();
		
		ResultSet rs = null;
		if (!StringUtility.stringHasValue(table)) {
			table = null;
		}
		
		String localCatalog = super.getDatabaseConnection().getCatalog();
		String localSchema = super.getDatabaseConnection().getSchema();
		
		try {
			rs = databaseMetaData.getExportedKeys(
					super.getCatalog(localCatalog),
					super.getSchema(localSchema), 
					table
			);
			
			while (rs.next()) {
				GeneralExportedKey geks = new GeneralExportedKey();
				geks.setName( rs.getString(4) );//PKCOLUMN_NAME
				geks.setTable(
					(Table)databaseTables.introspected(rs.getString(7 )) //FKTABLE_NAME
				);
				geks.setTableColumn(
					(TableColumn)databaseTableColumns.introspected(geks.getTable().getName(), rs.getString(8))//FKCOLUMN_NAME
				);
				
				geks.setComment( geks.getTable().getComment() );
				answer.add(geks);
			}
		} catch (SQLException e) {

		} finally {
			releaseResources(rs);
			close();
		}

		return answer;
	}

}
