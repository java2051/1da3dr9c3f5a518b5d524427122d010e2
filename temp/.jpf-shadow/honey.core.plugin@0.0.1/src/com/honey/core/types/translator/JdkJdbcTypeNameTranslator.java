package com.honey.core.types.translator;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.honey.core.types.DataType;
import com.honey.core.types.JavaTypeResolver;

/**
 * JDK定义数据库类型转到java.sql.Types类型
 * @author Administrator
 * 
 */
public class JdkJdbcTypeNameTranslator extends AbstractJdbcTypeNameTranslator{
			
	/**java.sql.Types类型到数据库类型映射  */
	private static Map<Integer, DataType> typeToName;
	
	/**数据库类型到java.sql.Types类型映射  */
	private static Map<String, DataType> nameToType;

	private final static DataType ARRAY = new DataType("ARRAY", Types.ARRAY, false, false, false);
	private final static DataType BIGINT = new DataType("BIGINT", Types.BIGINT, false, false, false);
	private final static DataType BINARY = new DataType("BINARY", Types.BINARY, false, false, false);
	private final static DataType BIT = new DataType("BIT", Types.BIT, false, false, false);
	private final static DataType BLOB = new DataType("BLOB", Types.BLOB, false, false, false);
	private final static DataType BOOLEAN = new DataType("BOOLEAN", Types.BOOLEAN, false, false, false);
	private final static DataType CHAR = new DataType("CHAR", Types.CHAR, true, false, false);
	private final static DataType CLOB = new DataType("CLOB", Types.CLOB, true, false, false);
	private final static DataType DATALINK = new DataType("DATALINK", Types.DATALINK, false, false, false);
	private final static DataType DATE = new DataType("DATE", Types.DATE, false, false, false);
	private final static DataType DECIMAL = new DataType("DECIMAL", Types.DECIMAL, true, true, false);
	private final static DataType DISTINCT = new DataType("DISTINCT", Types.DISTINCT, false, false, false);
	private final static DataType DOUBLE = new DataType("DOUBLE", Types.DOUBLE, false, false, false);
	private final static DataType FLOAT = new DataType("FLOAT", Types.FLOAT, false, false, false);
	private final static DataType INTEGER = new DataType("INTEGER", Types.INTEGER, false, false, false);
	private final static DataType JAVA_OBJECT = new DataType("JAVA_OBJECT", Types.JAVA_OBJECT, false, false, false);
	private final static DataType LONGVARBINARY = new DataType("LONGVARBINARY", Types.LONGVARBINARY, false, false, false);
	private final static DataType LONGVARCHAR = new DataType("LONGVARCHAR", Types.LONGVARCHAR, false, false, false);
	private final static DataType NCHAR = new DataType("NCHAR", JavaTypeResolver.NCHAR, true, false, false);
	private final static DataType NCLOB = new DataType("NCLOB", JavaTypeResolver.NCLOB, true, false, false);
	private final static DataType NVARCHAR = new DataType("NVARCHAR", JavaTypeResolver.NVARCHAR, true, false, false);
	private final static DataType NULL = new DataType("NULL", Types.NULL, false, false, false);
	private final static DataType NUMERIC = new DataType("NUMERIC", Types.NUMERIC, true, true, false);
	private final static DataType OTHER = new DataType("OTHER", Types.OTHER, false, false, false);
	private final static DataType REAL = new DataType("REAL", Types.REAL, false, false, false);
	private final static DataType REF = new DataType("REF", Types.REF, false, false, false);
	private final static DataType SMALLINT = new DataType("SMALLINT", Types.SMALLINT, false, false, false);
	private final static DataType STRUCT = new DataType("STRUCT", Types.STRUCT, false, false, false);
	private final static DataType TIME = new DataType("TIME", Types.TIME, false, false, false);
	private final static DataType TIMESTAMP = new DataType("TIMESTAMP", Types.TIMESTAMP, false, false, false);
	private final static DataType TINYINT = new DataType("TINYINT", Types.TINYINT, false, false, false);
	private final static DataType VARBINARY = new DataType("VARBINARY", Types.VARBINARY, false, false, false);
	private final static DataType VARCHAR = new DataType("VARCHAR", Types.VARCHAR, true, false, false);
	
	static {
		typeToName = new HashMap<Integer, DataType>();
		typeToName.put(Types.ARRAY, ARRAY); 
		typeToName.put(Types.BIGINT, BIGINT); 
		typeToName.put(Types.BINARY, BINARY); 
		typeToName.put(Types.BIT, BIT); 
		typeToName.put(Types.BLOB, BLOB); 
		typeToName.put(Types.BOOLEAN, BOOLEAN); 
		typeToName.put(Types.CHAR, CHAR); 
		typeToName.put(Types.CLOB, CLOB); 
		typeToName.put(Types.DATALINK, DATALINK); 
		typeToName.put(Types.DATE, DATE); 
		typeToName.put(Types.DECIMAL, DECIMAL); 
		typeToName.put(Types.DISTINCT, DISTINCT); 
		typeToName.put(Types.DOUBLE, DOUBLE); 
		typeToName.put(Types.FLOAT, FLOAT); 
		typeToName.put(Types.INTEGER, INTEGER); 
		typeToName.put(Types.JAVA_OBJECT, JAVA_OBJECT); 
		typeToName.put(Types.LONGVARBINARY, LONGVARBINARY); 
		typeToName.put(Types.LONGVARCHAR, LONGVARCHAR); 
//		typeToName.put(JavaTypeResolver.NCHAR, NCHAR); 
//		typeToName.put(JavaTypeResolver.NCLOB, NCLOB); 
		typeToName.put(JavaTypeResolver.NVARCHAR, NVARCHAR); 
		typeToName.put(Types.NULL, NULL); 
		typeToName.put(Types.NUMERIC, NUMERIC); 
		typeToName.put(Types.OTHER, OTHER); 
		typeToName.put(Types.REAL, REAL); 
		typeToName.put(Types.REF, REF); 
		typeToName.put(Types.SMALLINT, SMALLINT); 
		typeToName.put(Types.STRUCT, STRUCT); 
		typeToName.put(Types.TIME, TIME); 
		typeToName.put(Types.TIMESTAMP, TIMESTAMP); 
		typeToName.put(Types.TINYINT, TINYINT); 
		typeToName.put(Types.VARBINARY, VARBINARY); 
		typeToName.put(Types.VARCHAR, VARCHAR); 
//		
		nameToType = new HashMap<String, DataType>();
		nameToType.put("ARRAY", ARRAY); 
		nameToType.put("BIGINT", BIGINT); 
		nameToType.put("BINARY", BINARY); 
		nameToType.put("BIT", BIT); 
		nameToType.put("BLOB", BLOB); 
		nameToType.put("BOOLEAN", BOOLEAN); 
		nameToType.put("CHAR", CHAR); 
		nameToType.put("CLOB", CLOB); 
		nameToType.put("DATALINK", DATALINK); 
		nameToType.put("DATE", DATE); 
		nameToType.put("DECIMAL", DECIMAL); 
		nameToType.put("DISTINCT", DISTINCT); 
		nameToType.put("DOUBLE", DOUBLE); 
		nameToType.put("FLOAT", FLOAT); 
		nameToType.put("INTEGER", INTEGER); 
		nameToType.put("JAVA_OBJECT", JAVA_OBJECT); 
		nameToType.put("LONGVARBINARY", LONGVARBINARY); 
		nameToType.put("LONGVARCHAR", LONGVARCHAR); 
//		nameToType.put("NCHAR", JavaTypeResolver.NCHAR); 
//		nameToType.put("NCLOB", JavaTypeResolver.NCLOB); 
//		nameToType.put("NVARCHAR", JavaTypeResolver.NVARCHAR); 
		nameToType.put("NULL", NULL); 
		nameToType.put("NUMERIC", NUMERIC); 
		nameToType.put("OTHER", OTHER); 
		nameToType.put("REAL", REAL); 
		nameToType.put("REF", REF); 
		nameToType.put("SMALLINT", SMALLINT); 
		nameToType.put("STRUCT", STRUCT); 
		nameToType.put("TIME", TIME); 
		nameToType.put("TIMESTAMP", TIMESTAMP); 
		nameToType.put("TINYINT", TINYINT); 
		nameToType.put("VARBINARY", VARBINARY); 
		nameToType.put("VARCHAR", VARCHAR); 
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
