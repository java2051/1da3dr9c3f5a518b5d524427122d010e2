package com.honey.core.types;

/**
 * 数据类型,包括一下几种类型:<br />
 * 	<ul>
 * 		<li>
 * 			jdbc 类型
 * 		</li>
 * 		<li>
 * 			sql类型
 * 		</li>
 * 		<li>
 * 			java对象类型
 * 		</li>
 *  </ul>
 * @author Administrator
 *
 */
public class DataType {
	/** 数据库类型名称  */
	private String databaseName;
	
	/** jdbc中  java.sql.Types 类型 */
	private int jdbcType;
	
	/** java数据类型 */
	//private FullyQualifiedJavaType fullyQualifiedJavaType ;
	
	//下面属性 用于数据库
	/** 是否支持长度 , 支持:true 不支持:false */
	private boolean isSupportLength;
	
	/** 是否支持小数 , 支持:true 不支持:false */
	private boolean isSupportScale;
	
	/** 是否支持无符号数 , 支持:true 不支持:false */
	private boolean isSupportUnsigned;

	/**
	 * 构造函数
	 * @param databaseName 数据库类型名称  
	 * @param jdbcType jdbc中  java.sql.Types 类型
	 * @param isSupportLength 是否支持长度 , 支持:true 不支持:false
	 * @param isSupportScale 是否支持小数 , 支持:true 不支持:false 
	 * @param isSupportUnsigned 是否支持无符号数 , 支持:true 不支持:false
	 */
	public DataType(String databaseName, int jdbcType,
			boolean isSupportLength, boolean isSupportScale,
			boolean isSupportUnsigned) {
		super();
		this.databaseName = databaseName;
		this.jdbcType = jdbcType;
		//this.fullyQualifiedJavaType = fullyQualifiedJavaType;
		this.isSupportLength = isSupportLength;
		this.isSupportScale = isSupportScale;
		this.isSupportUnsigned = isSupportUnsigned;
	}

	/** 数据库类型名称
	 * @return 数据库类型名称
	 */
	public final String getDatabaseName() {
		return databaseName;
	}

	/** 
	 *  jdbc中  java.sql.Types 类型
	 * @return  jdbc中  java.sql.Types 类型
	 */
	public final int getJdbcType() {
		return jdbcType;
	}

	/**
	 * 是否支持长度 , 支持:true 不支持:false
	 * @return 是否支持长度 , 支持:true 不支持:false
	 */
	public final boolean isSupportLength() {
		return isSupportLength;
	}

	/** 
	 * 是否支持小数 , 支持:true 不支持:false
	 * @return 是否支持小数 , 支持:true 不支持:false
	 */
	public final boolean isSupportScale() {
		return isSupportScale;
	}

	/**
	 * 是否支持无符号数 , 支持:true 不支持:false
	 * @return 是否支持无符号数 , 支持:true 不支持:false
	 */
	public final boolean isSupportUnsigned() {
		return isSupportUnsigned;
	}
	
	/**
	 * java对象数据类型
	 * @return java对象数据类型
	 */
//	public final FullyQualifiedJavaType getFullyQualifiedJavaType() {
//		return fullyQualifiedJavaType;
//	}
	
}
