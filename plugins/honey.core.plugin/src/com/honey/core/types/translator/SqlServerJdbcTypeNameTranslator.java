package com.honey.core.types.translator;

import java.util.HashMap;
import java.util.Map;

import com.honey.core.types.DataType;

/**
 * SqlServer 数据库类型映射,同样适用sybase数据库<br />
 * 关系映射来源jtds-1.2.6-src.zip(SqlServer/sybase jdbc驱动),源码中的TdsData类
 * @author Administrator
 *
 */
public class SqlServerJdbcTypeNameTranslator extends AbstractJdbcTypeNameTranslator {
	
	/**java.sql.Types类型到数据库类型映射  */
	private static Map<Integer, DataType> typeToName;

	/**数据库类型到java.sql.Types类型映射  */
	private static Map<String, DataType> nameToType;

	private final static DataType  CHAR = new DataType("char", java.sql.Types.CHAR, true, false, false );
	private final static DataType  VARCHAR = new DataType("varchar", java.sql.Types.VARCHAR, true, false, false );
//	private final static DataType  INT = new DataType("int", java.sql.Types.INTEGER, false, false, false );
	private final static DataType  TINYINT = new DataType("tinyint", java.sql.Types.TINYINT, false, false, false );
//	private final static DataType  SMALLINT = new DataType("smallint", java.sql.Types.SMALLINT, false, false, false );
//	private final static DataType  INT = new DataType("int", java.sql.Types.INTEGER, false, false, false );
//	private final static DataType  BIGINT = new DataType("bigint", java.sql.Types.BIGINT, false, false, false );
//	private final static DataType  FLOAT = new DataType("float", java.sql.Types.DOUBLE, false, false, false );
//	private final static DataType  DATETIME = new DataType("datetime", java.sql.Types.TIMESTAMP, false, false, false );
//	private final static DataType  BIT = new DataType("bit", java.sql.Types.BIT, false, false, false );
	private final static DataType  TEXT = new DataType("text",  java.sql.Types.CLOB, false, false, false );
	private final static DataType  NTEXT = new DataType("ntext",  java.sql.Types.CLOB, false, false, false );
	private final static DataType  UNITEXT = new DataType("unitext",  java.sql.Types.CLOB, false, false, false );
	private final static DataType  IMAGE = new DataType("image", java.sql.Types.BLOB, false, false, false );
	private final static DataType  SMALLMONEY = new DataType("smallmoney", java.sql.Types.DECIMAL, true, true, false );
//	private final static DataType  MONEY = new DataType("money", java.sql.Types.DECIMAL, false, false, false );
	private final static DataType  SMALLDATETIME = new DataType("smalldatetime", java.sql.Types.TIMESTAMP, false, false, false );
	private final static DataType  REAL = new DataType("real", java.sql.Types.REAL, false, false, false );
	private final static DataType  BINARY = new DataType("binary", java.sql.Types.BINARY, false, false, false );
	//private final static DataType  VOID = new DataType("void,0, false, false, false );
	private final static DataType  VARBINARY = new DataType("varbinary", java.sql.Types.VARBINARY, false, false, false );
	private final static DataType  NVARCHAR = new DataType("nvarchar", java.sql.Types.VARCHAR, true, false, false );
	private final static DataType  BIT = new DataType("bit", java.sql.Types.BIT, false, false, false );
	private final static DataType  NUMERIC = new DataType("numeric", java.sql.Types.NUMERIC, true, true, false );
	private final static DataType  DECIMAL = new DataType("decimal", java.sql.Types.DECIMAL, true, true, false );
	private final static DataType  FLOAT = new DataType("float", java.sql.Types.DOUBLE, false, false, false );
	private final static DataType  MONEY = new DataType("money", java.sql.Types.DECIMAL, false, false, false );
	private final static DataType  DATETIME = new DataType("datetime", java.sql.Types.TIMESTAMP, false, false, false );
	private final static DataType  DATE = new DataType("date", java.sql.Types.DATE, false, false, false );
	private final static DataType  TIME = new DataType("time", java.sql.Types.TIME, false, false, false );
//	private final static DataType  DATE = new DataType("date", java.sql.Types.DATE, false, false, false );
//	private final static DataType  TIME = new DataType("time", java.sql.Types.TIME, false, false, false );
//	private final static DataType  CHAR = new DataType("char",  java.sql.Types.CHAR, false, false, false );
//	private final static DataType  VARCHAR = new DataType("varchar",  java.sql.Types.VARCHAR, false, false, false );
//	private final static DataType  NVARCHAR = new DataType("nvarchar",  java.sql.Types.VARCHAR, false, false, false );
	private final static DataType  NCHAR = new DataType("nchar",  java.sql.Types.CHAR, false, false, false );
//	private final static DataType  VARBINARY = new DataType("varbinary", java.sql.Types.VARBINARY, false, false, false );
//	private final static DataType  BINARY = new DataType("binary", java.sql.Types.BINARY, false, false, false );
//	private final static DataType  VARBINARY = new DataType("varbinary", java.sql.Types.BINARY, false, false, false );
//	private final static DataType  TINYINT = new DataType("tinyint", java.sql.Types.TINYINT, false, false, false );
	private final static DataType  SMALLINT = new DataType("smallint", java.sql.Types.INTEGER, false, false, true );
	private final static DataType  INT = new DataType("int", java.sql.Types.BIGINT, false, false, true );
	private final static DataType  BIGINT = new DataType("bigint", java.sql.Types.BIGINT, false, false, true );
//	private final static DataType  INT = new DataType("unsigned int", java.sql.Types.BIGINT, false, false, true );
	private final static DataType  UNIQUEIDENTIFIER = new DataType("uniqueidentifier", java.sql.Types.CHAR, true, false, false );
	private final static DataType  SQL_VARIANT = new DataType("sql_variant", java.sql.Types.VARCHAR, true, false, false );
	//private final static DataType  BIGINT = new DataType("bigint", java.sql.Types.BIGINT, false, false, false );
	
	
/**
 * types[SYBCHAR]      = new TypeInfo("char",          -1, -1,  1, false, false, java.sql.Types.CHAR);
        types[SYBVARCHAR]   = new TypeInfo("varchar",       -1, -1,  1, false, false, java.sql.Types.VARCHAR);
        types[SYBINTN]      = new TypeInfo("int",           -1, 10, 11, true,  false, java.sql.Types.INTEGER);
        types[SYBINT1]      = new TypeInfo("tinyint",        1,  3,  4, false, false, java.sql.Types.TINYINT);
        types[SYBINT2]      = new TypeInfo("smallint",       2,  5,  6, true,  false, java.sql.Types.SMALLINT);
        types[SYBINT4]      = new TypeInfo("int",            4, 10, 11, true,  false, java.sql.Types.INTEGER);
        types[SYBINT8]      = new TypeInfo("bigint",         8, 19, 20, true,  false, java.sql.Types.BIGINT);
        types[SYBFLT8]      = new TypeInfo("float",          8, 15, 24, true,  false, java.sql.Types.DOUBLE);
        types[SYBDATETIME]  = new TypeInfo("datetime",       8, 23, 23, false, false, java.sql.Types.TIMESTAMP);
        types[SYBBIT]       = new TypeInfo("bit",            1,  1,  1, false, false, java.sql.Types.BIT);
        types[SYBTEXT]      = new TypeInfo("text",          -4, -1, -1, false, true,  java.sql.Types.CLOB);
        types[SYBNTEXT]     = new TypeInfo("ntext",         -4, -1, -1, false, true,  java.sql.Types.CLOB);
        types[SYBUNITEXT]   = new TypeInfo("unitext",       -4, -1, -1, false, true,  java.sql.Types.CLOB);
        types[SYBIMAGE]     = new TypeInfo("image",         -4, -1, -1, false, false, java.sql.Types.BLOB);
        types[SYBMONEY4]    = new TypeInfo("smallmoney",     4, 10, 12, true,  false, java.sql.Types.DECIMAL);
        types[SYBMONEY]     = new TypeInfo("money",          8, 19, 21, true,  false, java.sql.Types.DECIMAL);
        types[SYBDATETIME4] = new TypeInfo("smalldatetime",  4, 16, 19, false, false, java.sql.Types.TIMESTAMP);
        types[SYBREAL]      = new TypeInfo("real",           4,  7, 14, true,  false, java.sql.Types.REAL);
        types[SYBBINARY]    = new TypeInfo("binary",        -1, -1,  2, false, false, java.sql.Types.BINARY);
        types[SYBVOID]      = new TypeInfo("void",          -1,  1,  1, false, false, 0);
        types[SYBVARBINARY] = new TypeInfo("varbinary",     -1, -1, -1, false, false, java.sql.Types.VARBINARY);
        types[SYBNVARCHAR]  = new TypeInfo("nvarchar",      -1, -1, -1, false, false, java.sql.Types.VARCHAR);
        types[SYBBITN]      = new TypeInfo("bit",           -1,  1,  1, false, false, java.sql.Types.BIT);
        types[SYBNUMERIC]   = new TypeInfo("numeric",       -1, -1, -1, true,  false, java.sql.Types.NUMERIC);
        types[SYBDECIMAL]   = new TypeInfo("decimal",       -1, -1, -1, true,  false, java.sql.Types.DECIMAL);
        types[SYBFLTN]      = new TypeInfo("float",         -1, 15, 24, true,  false, java.sql.Types.DOUBLE);
        types[SYBMONEYN]    = new TypeInfo("money",         -1, 19, 21, true,  false, java.sql.Types.DECIMAL);
        types[SYBDATETIMN]  = new TypeInfo("datetime",      -1, 23, 23, false, false, java.sql.Types.TIMESTAMP);
        types[SYBDATE]      = new TypeInfo("date",           4, 10, 10, false, false, java.sql.Types.DATE);
        types[SYBTIME]      = new TypeInfo("time",           4,  8,  8, false, false, java.sql.Types.TIME);
        types[SYBDATEN]     = new TypeInfo("date",          -1, 10, 10, false, false, java.sql.Types.DATE);
        types[SYBTIMEN]     = new TypeInfo("time",          -1,  8,  8, false, false, java.sql.Types.TIME);
        types[XSYBCHAR]     = new TypeInfo("char",          -2, -1, -1, false, true,  java.sql.Types.CHAR);
        types[XSYBVARCHAR]  = new TypeInfo("varchar",       -2, -1, -1, false, true,  java.sql.Types.VARCHAR);
        types[XSYBNVARCHAR] = new TypeInfo("nvarchar",      -2, -1, -1, false, true,  java.sql.Types.VARCHAR);
        types[XSYBNCHAR]    = new TypeInfo("nchar",         -2, -1, -1, false, true,  java.sql.Types.CHAR);
        types[XSYBVARBINARY]= new TypeInfo("varbinary",     -2, -1, -1, false, false, java.sql.Types.VARBINARY);
        types[XSYBBINARY]   = new TypeInfo("binary",        -2, -1, -1, false, false, java.sql.Types.BINARY);
        types[SYBLONGBINARY]= new TypeInfo("varbinary",     -5, -1,  2, false, false, java.sql.Types.BINARY);
        types[SYBSINT1]     = new TypeInfo("tinyint",        1,  2,  3, false, false, java.sql.Types.TINYINT);
        types[SYBUINT2]     = new TypeInfo("unsigned smallint", 2,  5,  6, false, false, java.sql.Types.INTEGER);
        types[SYBUINT4]     = new TypeInfo("unsigned int",   4, 10, 11, false, false, java.sql.Types.BIGINT);
        types[SYBUINT8]     = new TypeInfo("unsigned bigint",8, 20, 20, false, false, java.sql.Types.DECIMAL);
        types[SYBUINTN]     = new TypeInfo("unsigned int",  -1, 10, 11, true,  false, java.sql.Types.BIGINT);
        types[SYBUNIQUE]    = new TypeInfo("uniqueidentifier",-1,36,36, false, false, java.sql.Types.CHAR);
        types[SYBVARIANT]   = new TypeInfo("sql_variant",   -5,  0, 8000, false, false, java.sql.Types.VARCHAR);
        types[SYBSINT8]     = new TypeInfo("bigint",         8, 19, 20, true,  false, java.sql.Types.BIGINT);
 */
	
	static{
		typeToName = new HashMap<Integer, DataType>();
		typeToName.put(java.sql.Types.CHAR, CHAR);
		typeToName.put(java.sql.Types.VARCHAR, VARCHAR);
		typeToName.put(java.sql.Types.INTEGER, INT);
		typeToName.put(java.sql.Types.TINYINT, TINYINT);
		typeToName.put(java.sql.Types.SMALLINT, SMALLINT);
		typeToName.put(java.sql.Types.INTEGER, INT);
		typeToName.put(java.sql.Types.BIGINT, BIGINT);
		typeToName.put(java.sql.Types.DOUBLE, FLOAT);
		typeToName.put(java.sql.Types.TIMESTAMP, DATETIME);
		typeToName.put(java.sql.Types.BIT, BIT);
		typeToName.put(java.sql.Types.CLOB, TEXT);
//		typeToName.put(java.sql.Types.CLOB, "ntext");
//		typeToName.put(java.sql.Types.CLOB, "unitext");
		typeToName.put(java.sql.Types.BLOB, IMAGE);
//		typeToName.put(java.sql.Types.DECIMAL, "smallmoney");
//		typeToName.put(java.sql.Types.DECIMAL, "money");
		typeToName.put(java.sql.Types.TIMESTAMP, SMALLDATETIME);
		typeToName.put(java.sql.Types.REAL, REAL);
		typeToName.put(java.sql.Types.BINARY, BINARY);
//		typeToName.put(0, "void");
		typeToName.put(java.sql.Types.VARBINARY, VARBINARY);
		typeToName.put(java.sql.Types.VARCHAR, NVARCHAR);
//		typeToName.put(java.sql.Types.BIT, BIT);
		typeToName.put(java.sql.Types.NUMERIC, NUMERIC);
		typeToName.put(java.sql.Types.DECIMAL, DECIMAL);
//		typeToName.put(java.sql.Types.DOUBLE, "float");
//		typeToName.put(java.sql.Types.DECIMAL, "money");
		typeToName.put(java.sql.Types.TIMESTAMP, DATETIME);
//		typeToName.put(java.sql.Types.DATE, "date");
//		typeToName.put(java.sql.Types.TIME, "time");
		typeToName.put(java.sql.Types.DATE, DATE);
		typeToName.put(java.sql.Types.TIME, TIME);
//		typeToName.put(java.sql.Types.CHAR, "char");
//		typeToName.put(java.sql.Types.VARCHAR, "varchar");
//		typeToName.put(java.sql.Types.VARCHAR, "nvarchar");
//		typeToName.put(java.sql.Types.CHAR, "nchar");
//		typeToName.put(java.sql.Types.VARBINARY, "varbinary");
		typeToName.put(java.sql.Types.BINARY, BINARY);
//		typeToName.put(java.sql.Types.BINARY, "varbinary");
//		typeToName.put(java.sql.Types.TINYINT, "tinyint");
//		typeToName.put(java.sql.Types.INTEGER, "unsignedsmallint");
//		typeToName.put(java.sql.Types.BIGINT, "unsignedint");
//		typeToName.put(java.sql.Types.DECIMAL, "unsignedbigint");
//		typeToName.put(java.sql.Types.BIGINT, "unsignedint");
//		typeToName.put(java.sql.Types.CHAR, "uniqueidentifier");
//		typeToName.put(java.sql.Types.VARCHAR, "sql_variant");
//		typeToName.put(java.sql.Types.BIGINT, "bigint");

		
		
		
		nameToType = new HashMap<String, DataType>();
		nameToType.put("char", CHAR);
		nameToType.put("varchar", VARCHAR);
		nameToType.put("int", INT);
		nameToType.put("tinyint", TINYINT);
		nameToType.put("smallint", SMALLINT);
		nameToType.put("int", INT);
		nameToType.put("bigint", BIGINT);
		nameToType.put("float", FLOAT);
		nameToType.put("datetime", DATETIME);
		nameToType.put("bit", BIT);
		nameToType.put("text",  TEXT);
		nameToType.put("ntext",  NTEXT);
		nameToType.put("unitext",  UNITEXT);
		nameToType.put("image", IMAGE);
		nameToType.put("smallmoney", SMALLMONEY);
		nameToType.put("money", MONEY);
		nameToType.put("smalldatetime", SMALLDATETIME);
		nameToType.put("real", REAL);
		nameToType.put("binary", BINARY);
		//nameToType.put("void,0);
		nameToType.put("varbinary", VARBINARY);
		nameToType.put("nvarchar", NVARCHAR);
		nameToType.put("bit", BIT);
		nameToType.put("numeric", NUMERIC);
		nameToType.put("decimal", DECIMAL);
		nameToType.put("float", FLOAT);
		nameToType.put("money", MONEY);
		nameToType.put("datetime", DATETIME);
		nameToType.put("date", DATE);
		nameToType.put("time", TIME);
//		nameToType.put("date", DATE);
//		nameToType.put("time", TIME);
//		nameToType.put("char",  CHAR);
//		nameToType.put("varchar",  VARCHAR);
//		nameToType.put("nvarchar",  VARCHAR);
		nameToType.put("nchar",  NCHAR);
//		nameToType.put("varbinary", VARBINARY);
//		nameToType.put("binary", BINARY);
//		nameToType.put("varbinary", BINARY);
//		nameToType.put("tinyint", TINYINT);
//		nameToType.put("unsigned smallint", INTEGER);
//		nameToType.put("unsigned int", BIGINT);
//		nameToType.put("unsigned bigint", DECIMAL);
//		nameToType.put("unsigned int", BIGINT);
		nameToType.put("uniqueidentifier", UNIQUEIDENTIFIER);
		nameToType.put("sql_variant", SQL_VARIANT);
		nameToType.put("bigint", BIGINT);
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
