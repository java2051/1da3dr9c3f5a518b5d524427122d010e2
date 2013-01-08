package com.honey.core.types.translator;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.honey.core.types.DataType;

/**
 * 
 * sql 92 数据类型转换
 * @author Administrator
 *
 */
public class SQL92JdbcTypeNameTranslator extends AbstractJdbcTypeNameTranslator{
	/**java.sql.Types类型到数据库类型映射  */
	private static Map<Integer, DataType> typeToName;
	
	/**数据库类型到java.sql.Types类型映射  */
	private static Map<String, DataType> nameToType;
	
	private final static DataType  CHARACTER = new DataType("character", Types.CHAR, true, false, false );
	private final static DataType  CHAR = new DataType("char", Types.CHAR, true, false, false );
	private final static DataType  CHARACTER_VARYING = new DataType("character varying", Types.VARCHAR, true, false, false );
	private final static DataType  CHAR_VARYING = new DataType("char varying", Types.CHAR, true, false, false );
	private final static DataType  VARCHAR = new DataType("varchar", Types.VARCHAR, true, false, false );
	private final static DataType  NATIONAL_CHARACTER = new DataType("national character", Types.CHAR, true, false, false );
	private final static DataType  NATIONAL_CHAR = new DataType("national char", Types.CHAR, true, false, false );
	private final static DataType  NCHAR = new DataType("nchar", Types.NCHAR, true, false, false );
	private final static DataType  NATIONAL_CHARACTER_VARYING = new DataType("national character varying", Types.VARCHAR, true, false, false );
	private final static DataType  NATIONAL_CHAR_VARYING = new DataType("national char varying", Types.VARCHAR, true, false, false );
	private final static DataType  NCHAR_VARYING = new DataType("nchar varying", Types.NCHAR, true, false, false );
	private final static DataType  BIT = new DataType("bit", Types.BIT, false, false, false );
	private final static DataType  BIT_VARYING = new DataType("bit varying", Types.BINARY, false, false, false );
	private final static DataType  NUMERIC = new DataType("numeric", Types.NUMERIC, true, true, false );
	private final static DataType  DECIMAL = new DataType("DECIMAL", Types.DECIMAL, true, true, false );
	private final static DataType  DEC = new DataType("dec", Types.DECIMAL, true, true, false );
	private final static DataType  INTEGER = new DataType("integer", Types.INTEGER, false, false, false );
	private final static DataType  INT = new DataType("int", Types.INTEGER, false, false, false );
	private final static DataType  SMALLINT = new DataType("smallint", Types.SMALLINT, false, false, false );
	private final static DataType  FLOAT = new DataType("float", Types.FLOAT, false, false, false );
	private final static DataType  REAL = new DataType("real", Types.REAL, false, false, false );
	private final static DataType  DOUBLE_PRECISION = new DataType("double precision", Types.DOUBLE, false, false, false );
	private final static DataType  DOUBLE = new DataType("double", Types.DOUBLE, false, false, false );
	private final static DataType  DATE = new DataType("date", Types.DATE, false, false, false );
	private final static DataType  TIME = new DataType("time", Types.TIME, false, false, false );
	private final static DataType  TIMESTAMP = new DataType("timestamp", Types.TIMESTAMP, false, false, false );

	private final static DataType  INTERVAL = new DataType("INTERVAL", Types.OTHER, false, false, false );

	//扩充sql92 数据类型
	private final static DataType  TINYINT = new DataType("tinyint", Types. TINYINT, false, false, false );
	private final static DataType  BIGINT = new DataType("bigint",  Types.BIGINT, false, false, false );
	private final static DataType  BOOLEAN = new DataType("boolean",  Types.BOOLEAN, false, false, false );
	private final static DataType  BOOL = new DataType("bool",  Types.BOOLEAN, false, false, false );
	private final static DataType  NVARCHAR = new DataType("nvarchar",  Types.NVARCHAR, false, false, false );
	private final static DataType  TEXT = new DataType("text",  Types.VARCHAR, false, false, false );
	
	static {
		typeToName = new HashMap<Integer, DataType>();
		
		//sql 92 的数据类型
		nameToType = new HashMap<String, DataType>();
		nameToType.put("character", CHARACTER);
		nameToType.put("char", CHAR);
		nameToType.put("character varying", CHARACTER_VARYING);
		nameToType.put("char varying", CHAR_VARYING);
		nameToType.put("varchar", VARCHAR);
		nameToType.put("national character", NATIONAL_CHARACTER);
		nameToType.put("national char", NATIONAL_CHAR);
		nameToType.put("nchar", NCHAR);
		nameToType.put("national character varying", NATIONAL_CHARACTER_VARYING );
		nameToType.put("national char varying", NATIONAL_CHAR_VARYING);
		nameToType.put("nchar varying", NCHAR_VARYING);
		nameToType.put("bit", BIT);
		nameToType.put("bit varying", BIT_VARYING);
		nameToType.put("numeric", NUMERIC);
		nameToType.put("DECIMAL", DECIMAL);
		nameToType.put("DEC", DEC);
		nameToType.put("INTEGER", INTEGER);
		nameToType.put("INT", INT);
		nameToType.put("SMALLINT", SMALLINT);
		nameToType.put("FLOAT", FLOAT);
		nameToType.put("REAL", REAL);
		nameToType.put("DOUBLE PRECISION", DOUBLE_PRECISION);
		nameToType.put("DOUBLE", DOUBLE);
		nameToType.put("DATE", DATE);
		nameToType.put("TIME", TIME);
		nameToType.put("TIMESTAMP", TIMESTAMP);
		
		nameToType.put("INTERVAL", INTERVAL);
		
		//扩充sql92 数据类型
		nameToType.put("tinyint", TINYINT);
		nameToType.put("bigint", BIGINT);
		nameToType.put("boolean", BOOLEAN);
		nameToType.put("bool", BOOL);
		nameToType.put("nvarchar", NVARCHAR);
		nameToType.put("text", TEXT);
		
	}
	
	@Override
	Map<String, DataType> getNameToType() {
		return nameToType;
	}

	@Override
	Map<Integer, DataType> getTypeToName() {
		return typeToName;
	}
	

}
