package com.honey.general.databases.databaseobjects;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.honey.core.dbmapping.structure.Procedure;
import com.honey.core.dbmapping.structure.StructureType;
import com.honey.core.types.Vendor;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.DataSourceException;
import com.honey.general.databases.introspected.GeneralProcedure;

/**
 * 获取存储过程
 * @author Administrator
 *
 */
class DatabaseProcedures extends DatabaseHost {

	/**
	 * 构造函数
	 * @param databaseConnection 数据库连接配置信息
	 */
	public DatabaseProcedures(DatabaseConnection databaseConnection) {
		super(databaseConnection);
		
	}

	@Override
	public Object introspected(String procedureName,
			String columnName) {
		try {
			return getProcedures(procedureName);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Object introspected( String procedureName) {
		try {
			return getProcedures( procedureName);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 
	 * 返回存储过程信息
	 * @param procedureName 如果指定存储名称返回存储过程信息;如果为null返回所有的存储过程
	 * @throws DataSourceException
	 */
	private Object getProcedures(String procedureName ) throws DataSourceException{
		
		List<GeneralProcedure>  answer = new ArrayList<GeneralProcedure>();
		
		DatabaseMetaData databaseMetaData = getDatabaseMetaData();
		ResultSet rs = null;
		
		if( !StringUtility.stringHasValue(procedureName) ){
			procedureName = null ;
		}
		
		String localCatalog = super.getDatabaseConnection().getCatalog();
		String localSchema = super.getDatabaseConnection().getSchema();
		
		try {
			rs = databaseMetaData.getProcedures(
					super.getCatalog( localCatalog), 
					super.getSchema( localSchema), 
					procedureName);
			Map<String, GeneralProcedure> map = new HashMap<String, GeneralProcedure>();
			while(rs.next()){
				String name = rs.getString(3);
				if(map.containsKey(name))
					continue;
				
				GeneralProcedure gps = new GeneralProcedure();
				gps.setVendor(new Vendor(databaseMetaData.getDatabaseProductName() ,databaseMetaData.getDatabaseMajorVersion(),databaseMetaData.getDatabaseMinorVersion()));
				gps.setName(name);//PROCEDURE_NAME 
				gps.setComment( rs.getString(5) ); //REMARKS 
				gps.setSchemaType(StructureType.PROCEDURE );
				gps.setProcedureResultType( Procedure.ProcedureResultType.getProcedureResultType(
						rs.getShort(8)
				));
				gps.setMoreInformation(super.getDatabaseProperties());
				answer.add(gps);
				map.put(gps.getName(), gps);
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
}
