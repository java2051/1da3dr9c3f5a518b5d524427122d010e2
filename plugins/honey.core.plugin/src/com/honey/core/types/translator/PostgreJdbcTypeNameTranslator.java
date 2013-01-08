package com.honey.core.types.translator;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.honey.core.types.DataType;

/**
 * postgre数据库类型到java.sql.Types类型数据转换<br />
 * 没有完全支持postgre数据库类型<br />
 * @author Administrator
 *
 */
public class PostgreJdbcTypeNameTranslator extends AbstractJdbcTypeNameTranslator {
	
	/**java.sql.Types类型到数据库类型映射  */
	private static Map<Integer, DataType> typeToName;
	
	/**数据库类型到java.sql.Types类型映射  */
	private static Map<String, DataType> nameToType;

	
	private final static DataType  INT2 = new DataType("int2", Types.SMALLINT, false, false,false );
	private final static DataType  SMALLINT = new DataType("smallint", Types.SMALLINT, false, false,false );

	private final static DataType  INT4 = new DataType("int4", Types.INTEGER, false, false,false );
	private final static DataType  INTEGER = new DataType("integer", Types.INTEGER, false, false,false );
	private final static DataType  OID = new DataType("oid", Types.INTEGER, false, false,false ); 
	private final static DataType  SERIAL = new DataType("serial", Types.INTEGER, false, false,false );//自增整数

	private final static DataType  INT8 = new DataType("int8", Types.BIGINT, false, false,false ); 
	private final static DataType  BIGINT = new DataType("bigint", Types.BIGINT, false, false,false );
	private final static DataType  BIGSERIAL = new DataType("bigserial", Types.BIGINT, false, false,false );//自增long类型

	private final static DataType  MONEY = new DataType("money", Types.DOUBLE, false, false,false ); 
	private final static DataType  NUMERIC = new DataType("numeric", Types.NUMERIC, true, true,false ); 
	private final static DataType  DECIMAL = new DataType("decimal", Types.DECIMAL, true, true,false );
	private final static DataType  FLOAT4 = new DataType("float4", Types.REAL, false, false,false ); 
	private final static DataType  FLOAT8 = new DataType("float8", Types.DOUBLE, false, false,false ); 
	private final static DataType  REAL = new DataType("real", Types.REAL, false, false,false );
	
	//post没有doble类型
	private final static DataType  DOUBLE = new DataType("float8", Types.DOUBLE, false, false,false );
	private final static DataType  ARRAY = new DataType("array", Types.ARRAY, false, false,false );

	private final static DataType  BPCHAR = new DataType("bpchar", Types.CHAR, false, false,false ); 
	private final static DataType  VARCHAR = new DataType("varchar", Types.VARCHAR, false, false,false ); 
	private final static DataType  CHAR = new DataType("char", Types.CHAR, true, false,false ); 

	private final static DataType  TEXT = new DataType("text", Types.VARCHAR, true, false,false ); 
	private final static DataType  NAME = new DataType("name", Types.VARCHAR, true, false,false ); 
	private final static DataType  BYTEA = new DataType("bytea", Types.BINARY, false, false,false ); 
	private final static DataType  BOOL = new DataType("bool", Types.BOOLEAN, false, false,false );
	private final static DataType  BOOLEAN = new DataType("boolean", Types.BOOLEAN, false, false,false );

	private final static DataType  BIT = new DataType("bit", Types.BIT, false, false,false ); 
	private final static DataType  DATE = new DataType("date", Types.DATE, false, false,false ); 
	private final static DataType  TIME = new DataType("time", Types.TIME, false, false,false ); 
	private final static DataType  TIMETZ = new DataType("timetz", Types.TIME, false, false,false ); 
	private final static DataType  TIMESTAMP = new DataType("timestamp", Types.TIMESTAMP, false, false,false ); 
	private final static DataType  TIMESTAMPTZ = new DataType("timestamptz", Types.TIMESTAMP, false, false,false );
	
	static {
		typeToName = new HashMap<Integer, DataType>();
		typeToName.put(Types.SMALLINT, INT2);
		typeToName.put(Types.INTEGER, INT4);
		//typeToName.put(Types.INTEGER, "oid");
		typeToName.put(Types.BIGINT, INT8);
		//typeToName.put(Types.DOUBLE, DOUBLE);
		typeToName.put(Types.NUMERIC, NUMERIC);
		typeToName.put(Types.REAL, FLOAT4);
		typeToName.put(Types.DOUBLE, FLOAT8);
		typeToName.put(Types.CHAR, CHAR);
		typeToName.put(Types.VARCHAR, VARCHAR);
		typeToName.put(Types.BINARY, BYTEA);
		typeToName.put(Types.BIT, BIT);
		typeToName.put(Types.DATE, DATE);
		typeToName.put(Types.TIME, TIME);
		typeToName.put(Types.TIMESTAMP, TIMESTAMP);
		typeToName.put(Types.BOOLEAN, BOOLEAN);
		typeToName.put(Types.ARRAY, ARRAY);
		
		nameToType = new HashMap<String, DataType>();
		nameToType.put("int2", INT2);
		nameToType.put("smallint", SMALLINT);
		
		nameToType.put("int4", INT4);
		nameToType.put("integer", INTEGER);
		nameToType.put("oid", OID); 
		nameToType.put("serial", SERIAL);//自增整数
		
		nameToType.put("int8", INT8); 
		nameToType.put("bigint", BIGINT);
		nameToType.put("bigserial", BIGSERIAL);//自增long类型
		
		nameToType.put("money", MONEY); 
		nameToType.put("numeric", NUMERIC); 
		//nameToType.put("numeric", NUMERIC);
		nameToType.put("float4", FLOAT4); 
		nameToType.put("float8", FLOAT8); 
		nameToType.put("real", REAL);
		nameToType.put("double", DOUBLE);
		nameToType.put("array", ARRAY);
		
		nameToType.put("bpchar", BPCHAR); 
		nameToType.put("varchar", VARCHAR); 
		nameToType.put("char", CHAR); 
		
		nameToType.put("text", TEXT); 
		nameToType.put("name", NAME); 
		nameToType.put("bytea", BYTEA); 
		nameToType.put("bool", BOOL);
		nameToType.put("boolean", BOOLEAN);
		
		nameToType.put("bit", BIT); 
		nameToType.put("date", DATE); 
		nameToType.put("time", TIME); 
		nameToType.put("timetz", TIMETZ); 
		nameToType.put("timestamp", TIMESTAMP); 
		nameToType.put("timestamptz", TIMESTAMPTZ); 
		
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
