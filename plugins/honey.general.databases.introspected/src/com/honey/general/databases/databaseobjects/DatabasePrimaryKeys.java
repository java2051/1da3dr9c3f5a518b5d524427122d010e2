package com.honey.general.databases.databaseobjects;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.honey.core.dbmapping.structure.TableColumn;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.DataSourceException;
import com.honey.general.databases.introspected.GeneralTableColumn;

/**
 * 获取数据库主键信息
 * @author Administrator
 *
 */
class DatabasePrimaryKeys extends DatabaseHost {

	private DatabaseTableColumns databaseTableColumns;

	/**
	 * 构造函数
	 * @param databaseConnection 数据库连接配置信息
	 */
	public DatabasePrimaryKeys(DatabaseConnection databaseConnection) {
		super(databaseConnection);
		databaseTableColumns = new DatabaseTableColumns(databaseConnection);
	}

	@Override
	public Object introspected(String table, String columnName) {
		try {
			return getPrimaryKeys(table);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object introspected(String table) {
		try {
			return getPrimaryKeys(table);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回表的主键
	 * 
	 * @param table
	 * @throws DataSourceException
	 */
	private Object getPrimaryKeys(String table)
			throws DataSourceException {

		List<TableColumn> result = new ArrayList<TableColumn>();

		DatabaseMetaData databaseMetaData = getDatabaseMetaData();
		ResultSet rs = null;
		if (!StringUtility.stringHasValue(table)) {
			table = null;
		}

		String localCatalog = super.getDatabaseConnection().getCatalog();
		String localSchema = super.getDatabaseConnection().getSchema();
		
		try {
			rs = databaseMetaData.getPrimaryKeys(
					super.getCatalog( localCatalog ),
					super.getSchema( localSchema ), 
					table
			);

			while (rs.next()) {
				String columnName = rs.getString(4);//COLUMN_NAME
				GeneralTableColumn gTableColumn = (GeneralTableColumn) databaseTableColumns
				.introspected(
					table, 
					columnName
				);
				gTableColumn.setPrimaryKey(true);
				result.add( gTableColumn);
			}
		} catch (SQLException e) {
			
		} finally {
			releaseResources(rs);
			close();
		}
		return result;
	}

}
