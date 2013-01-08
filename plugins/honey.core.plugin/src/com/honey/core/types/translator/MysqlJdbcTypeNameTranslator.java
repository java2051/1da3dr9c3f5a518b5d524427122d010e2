package com.honey.core.types.translator;


import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.honey.core.types.DataType;

/**
 * mysql 数据库类型转换
 * @author Administrator
 *
 */
public final class MysqlJdbcTypeNameTranslator  extends AbstractJdbcTypeNameTranslator {
	
	/**数据库类型到java.sql.Types类型映射  */
	private static Map<String, DataType> nameToType = new HashMap<String, DataType>();
	
	/**java.sql.Types类型到数据库类型映射  */
	private static Map<Integer, DataType> typeToName = new HashMap<Integer, DataType>();;
	
	private final static DataType BIT =  new DataType("bit",Types.BIT, true , false, false );
	private final static DataType TINYINT =  new DataType("tinyint", Types.TINYINT, true , false, true );
	private final static DataType SMALLINT =  new DataType("smallint",Types.SMALLINT, true , false, true );
	private final static DataType MEDIUMINT =  new DataType("mediumint",Types.INTEGER, true , false, true );
	private final static DataType INT =  new DataType("int",Types.INTEGER, false , true, true );
	private final static DataType INTEGER =  new DataType("integer",Types.INTEGER, true , false, true );
	private final static DataType BIGINT =  new DataType("bigint",Types.BIGINT, true , false, true );
	private final static DataType INT24 =  new DataType("int24",Types.INTEGER, false , false, false );
	private final static DataType REAL =  new DataType("real",Types.DOUBLE, true , true, true );
	private final static DataType FLOAT =  new DataType("float",Types.REAL, true , true, true );
	private final static DataType DECIMAL =  new DataType("decimal",Types.DECIMAL, true , true, true );
	private final static DataType NUMERIC =  new DataType("numeric",Types.DECIMAL, true , true, true );
	private final static DataType DOUBLE =  new DataType("double",Types.DOUBLE, true , true, true );
	private final static DataType CHAR =  new DataType("char",Types.CHAR, true , false, false );
	private final static DataType VARCHAR =  new DataType("varchar",Types.VARCHAR, true , false, false );
	private final static DataType DATE =  new DataType("date",Types.DATE, false , false, false );
	private final static DataType TIME =  new DataType("time",Types.TIME, false , false, false );
	private final static DataType YEAR =  new DataType("year",Types.DATE, false , false, false );
	private final static DataType TIMESTAMP =  new DataType("timestamp",Types.TIMESTAMP, true , false, false );
	private final static DataType DATETIME =  new DataType("datetime",Types.TIMESTAMP, true , false, false );
	private final static DataType TINYBLOB =  new DataType("tinyblob", java.sql.Types.BINARY, false , false, false );
	private final static DataType BLOB =  new DataType("blob",java.sql.Types.LONGVARBINARY, false , false, false );
	private final static DataType MEDIUMBLOB =  new DataType("mediumblob",java.sql.Types.LONGVARBINARY, false , false, false );
	private final static DataType LONGBLOB =  new DataType("longblob",java.sql.Types.LONGVARBINARY, false , false, false );
	private final static DataType TINYTEXT =  new DataType("tinytext", java.sql.Types.VARCHAR, false , false, false );
	private final static DataType TEXT =  new DataType("text", java.sql.Types.LONGVARCHAR, false , false, false );
	private final static DataType MEDIUMTEXT =  new DataType("mediumtext",java.sql.Types.LONGVARCHAR, false , false, false );
	private final static DataType LONGTEXT =  new DataType("longtext",java.sql.Types.LONGVARCHAR, false , false, false );
	private final static DataType ENUM =  new DataType("enum",Types.CHAR, false , false, false );
	private final static DataType SET =  new DataType("set",Types.CHAR, false , false, false );
	private final static DataType GEOMETRY =  new DataType("geometry",Types.BINARY, false , false, false );
	
	static {
		
		nameToType.put("bit",BIT);
		nameToType.put("tinyint", TINYINT);
		nameToType.put("smallint",SMALLINT);
		nameToType.put("mediumint",MEDIUMINT);
		nameToType.put("int",INT);
		nameToType.put("integer",INTEGER);
		nameToType.put("bigint",BIGINT);
		nameToType.put("int24",INT24);
		nameToType.put("real",REAL);
		nameToType.put("float",FLOAT);
		nameToType.put("decimal",DECIMAL);
		nameToType.put("numeric",NUMERIC);
		nameToType.put("double",DOUBLE);
		nameToType.put("char",CHAR);
		nameToType.put("varchar",VARCHAR);
		nameToType.put("date",DATE);
		nameToType.put("time",TIME);
		nameToType.put("year",YEAR);
		nameToType.put("timestamp",TIMESTAMP);
		nameToType.put("datetime",DATETIME);
		nameToType.put("tinyblob", TINYBLOB);
		nameToType.put("blob",BLOB);
		nameToType.put("mediumblob",MEDIUMBLOB);
		nameToType.put("longblob",LONGBLOB);
		nameToType.put("tinytext", TINYTEXT);
		nameToType.put("text", TEXT);
		nameToType.put("mediumtext",MEDIUMTEXT);
		nameToType.put("longtext",LONGTEXT);
		nameToType.put("enum",ENUM);
		nameToType.put("set",SET);
		nameToType.put("geometry",GEOMETRY);

		typeToName.put(Types.BIT, BIT);
		typeToName.put(Types.TINYINT, TINYINT);
		typeToName.put(Types.SMALLINT, SMALLINT);
		typeToName.put(Types.INTEGER, INTEGER);
		typeToName.put(Types.BIGINT, BIGINT);
		typeToName.put(Types.REAL, REAL);
		typeToName.put(Types.DECIMAL, DECIMAL);
		typeToName.put(Types.DOUBLE, DOUBLE);
		typeToName.put(Types.CHAR, CHAR);
		typeToName.put(Types.VARCHAR, VARCHAR);
		typeToName.put(Types.DATE, DATE);
		typeToName.put(Types.TIMESTAMP, TIMESTAMP);
		typeToName.put(Types.BINARY, TINYBLOB);
		typeToName.put(Types.LONGVARBINARY, BLOB);
		typeToName.put(Types.BLOB, BLOB);
		typeToName.put(Types.LONGVARCHAR, TEXT);
			
	}
	
	@Override
	public Map<String, DataType> getNameToType(){
		return nameToType;
	}
	
	@Override
	public Map<Integer, DataType> getTypeToName(){
		return typeToName;
	}
	
}

