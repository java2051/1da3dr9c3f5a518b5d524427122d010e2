package com.honey.core.types;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.honey.core.dbmapping.structure.Column;
import com.honey.core.dbmapping.structure.TableColumn;

/**
 * java.sql.Types类型转到java对象类型
 * 
 * @author Administrator
 *
 */
public class JavaTypeResolver {

	/** 是否强制转换成BigDecimal类型 */
	private boolean forceBigDecimals;

	/** 映射表 */
	private Map<Integer, JdbcTypeInformation> typeMap;
	
	public static final int NVARCHAR = -9;
	
	public static final int NCHAR = -15;
	
	public static final int NCLOB = 2011;
	
	/**
	 * 构造函数
	 */
	public JavaTypeResolver( ) {
		this(false);
	}
	
	/**
	 * 构造函数
	 * @param forceBigDecimals 是否强制转换BigDecimal
	 */
	public JavaTypeResolver( boolean forceBigDecimals ) {
		this.forceBigDecimals = forceBigDecimals;
		
		typeMap = new HashMap<Integer, JdbcTypeInformation>();

		typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY", 
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT", 
				JDKFullyQualifiedJavaType.getLongInstance()));
		typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY", 
				new FullyQualifiedJavaType("byte[]"))); 
		typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", 
				JDKFullyQualifiedJavaType.getBooleanInstance()));
		typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB", 
				new FullyQualifiedJavaType("byte[]"))); 
		typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN", 
				JDKFullyQualifiedJavaType.getBooleanInstance()));
		typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR", 
				JDKFullyQualifiedJavaType.getStringInstance()));
		typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB", 
				JDKFullyQualifiedJavaType.getStringInstance()));
		typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK", 
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", 
				new FullyQualifiedJavaType(Date.class.getName())));
		typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT", 
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE", 
				new FullyQualifiedJavaType(Double.class.getName())));
		typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT", 
				JDKFullyQualifiedJavaType.getDoubleInstance()));
		typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER", 
				JDKFullyQualifiedJavaType.getIntegerInstance()));
		typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT", 
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation(
				"LONGVARBINARY", 
				new FullyQualifiedJavaType("byte[]"))); 
		typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR", 
				JDKFullyQualifiedJavaType.getStringInstance()));
		typeMap.put(NCHAR, new JdbcTypeInformation("NCHAR", 
				JDKFullyQualifiedJavaType.getStringInstance()));
		typeMap.put(NCLOB, new JdbcTypeInformation("NCLOB", 
				JDKFullyQualifiedJavaType.getStringInstance()));
		typeMap.put(NVARCHAR, new JdbcTypeInformation("NVARCHAR", 
				JDKFullyQualifiedJavaType.getStringInstance()));
		typeMap.put(Types.NULL, new JdbcTypeInformation("NULL", 
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER", 
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.REAL, new JdbcTypeInformation("REAL", 
				JDKFullyQualifiedJavaType.getFloatInstance()));
		typeMap.put(Types.REF, new JdbcTypeInformation("REF", 
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", 
				JDKFullyQualifiedJavaType.getShortInstance()));
		typeMap.put(Types.STRUCT, new JdbcTypeInformation("STRUCT", 
				new FullyQualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", 
				new FullyQualifiedJavaType(Date.class.getName())));
		typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", 
				new FullyQualifiedJavaType(Date.class.getName())));
		typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", 
				JDKFullyQualifiedJavaType.getShortInstance()));
		typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY", 
				new FullyQualifiedJavaType("byte[]"))); 
		typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR", 
				JDKFullyQualifiedJavaType.getStringInstance()));
	}
	
	
	/**
	 * 转换java对象类型
	 * @param column
	 * @return
	 */
	public FullyQualifiedJavaType calculateJavaType( Column column) {
		FullyQualifiedJavaType answer;
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(column.getJdbcType());

		if (jdbcTypeInformation == null) {
			switch (column.getJdbcType()) {
			case Types.DECIMAL:
			case Types.NUMERIC:
				if (column.getScale() > 0
						|| column.getLength() > 18
						|| forceBigDecimals) {
					answer = new FullyQualifiedJavaType(BigDecimal.class.getName());
				} else if (column.getLength() > 9) {
					answer = JDKFullyQualifiedJavaType.getLongInstance();
				} else if (column.getLength() > 4) {
					answer = JDKFullyQualifiedJavaType.getIntegerInstance();
				} else {
					answer = JDKFullyQualifiedJavaType.getShortInstance();
				}
				break;

			default:
				answer = null;
				break;
			}
		} else {
			answer = jdbcTypeInformation.getFullyQualifiedJavaType();
		}

		return answer;
	}
	
	/**
	 * 转换成数据类型
	 * @param introspectedColumn
	 * @return
	 */
	public String calculateJdbcTypeName(TableColumn introspectedColumn) {
        String answer;
        JdbcTypeInformation jdbcTypeInformation = typeMap.get(introspectedColumn.getJdbcType());

        if (jdbcTypeInformation == null) {
            switch (introspectedColumn.getJdbcType()) {
            case Types.DECIMAL:
                answer = "DECIMAL"; 
                break;
            case Types.NUMERIC:
                answer = "NUMERIC"; 
                break;
            default:
                answer = null;
                break;
            }
        } else {
            answer = jdbcTypeInformation.getJdbcTypeName();
        }

        return answer;
    }
	
	private static class JdbcTypeInformation {
		private String jdbcTypeName;

		private FullyQualifiedJavaType fullyQualifiedJavaType;

		public JdbcTypeInformation(String jdbcTypeName,
				FullyQualifiedJavaType fullyQualifiedJavaType) {
			this.jdbcTypeName = jdbcTypeName;
			this.fullyQualifiedJavaType = fullyQualifiedJavaType;
		}

		public String getJdbcTypeName() {
			return jdbcTypeName;
		}

		public FullyQualifiedJavaType getFullyQualifiedJavaType() {
			return fullyQualifiedJavaType;
		}
	}
}
