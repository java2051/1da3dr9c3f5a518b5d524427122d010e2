package com.honey.general.databases.databaseobjects;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.honey.core.dbmapping.structure.Function;
import com.honey.core.dbmapping.structure.StructureType;
import com.honey.core.types.Vendor;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.datasource.DataSourceException;
import com.honey.general.databases.introspected.GeneralFunction;

/**
 * 获取数据库函数
 * @author Administrator
 *
 */
@Deprecated
class DatabaseFunctions extends DatabaseHost{
	
	/**
	 * 构造函数
	 * @param databaseConnection 数据库链接配置
	 */
	public DatabaseFunctions(DatabaseConnection databaseConnection) {
		super(databaseConnection);		
	}

	@Override
	public Object introspected(String functionName,String columnName) {
		try {
			return getFunctions(functionName);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object introspected(String functionName) {
		try {
			return getFunctions( functionName);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回数据的函数
	 * @param functionName 如果指定函数名称,函信息;如果为null返回所有函数
	 * @throws DataSourceException
	 */
	private Object getFunctions( String functionName) throws DataSourceException{

		List<GeneralFunction>  answer = new ArrayList<GeneralFunction>();
		
		DatabaseMetaData databaseMetaData = getDatabaseMetaData();
		ResultSet rs = null;
		
		if( !StringUtility.stringHasValue(functionName) ){
			functionName = null ;
		}
		
		String localCatalog = super.getDatabaseConnection().getCatalog();
		String localSchema = super.getDatabaseConnection().getSchema();
		
		try {
			rs = databaseMetaData.getFunctions(
					super.getCatalog( localCatalog ), 
					super.getSchema( localSchema ), 
					functionName
			);
			
			while(rs.next()){
				GeneralFunction gfs = new GeneralFunction();
				gfs.setVendor(new Vendor(databaseMetaData.getDatabaseProductName() ,databaseMetaData.getDatabaseMajorVersion(),databaseMetaData.getDatabaseMinorVersion()));
				gfs.setName(rs.getString(3));//FUNCTION_NAME 
				gfs.setComment( rs.getString(4) ); //REMARKS 
				gfs.setSchemaType(StructureType.FUNCTION);
				gfs.setFunctionResultType( Function.FunctionResultType.getFunctionResultType(
						rs.getShort(5)//FUNCTION_TYPE
				));
				gfs.setMoreInformation(super.getDatabaseProperties());
				answer.add(gfs);
			}
		} catch (Exception e) {
			
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
