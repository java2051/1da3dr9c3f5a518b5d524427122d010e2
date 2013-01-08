package com.honey.core.types;

/**
 * 数据库类型转到java.sql.Types类型
 * @author Administrator
 * 
 */
public interface JdbcTypeNameTranslator {
	
	/**
	 * 根据java.sql.Types获取数据库类型
	 * @param jdbcType java.sql.Types类型
	 * @return DataType
	 */
	@Deprecated
	public DataType getJdbcTypeName(int jdbcType) ;
	
	/**
	 * 根据数据库类型获取java.sql.Types类型
	 * @param jdbcTypeName 数据库类型名称
	 * @return 返回DataType
	 */
	public DataType getJdbcType(String jdbcTypeName);
	
	/**
	 * 根据jdbcType创建数据库类型格式字符串,例如 :<br />
	 *  jdbcType = Types.NUMERIC <br >
	 *  length = 19 <br >
	 *  scale = 4 <br > 
	 *  unsigned = false <br >
	 *  返回结果是: numeric(19,4)
	 *  又例如:
	 *  	jdbcType = Types.INTEGER <br >
	 *  	length = 19 <br >
	 *  	scale = 0 <br > 
	 *  	unsigned = true <br >
	 *  mysql数据库返回结果是: bigint(19) UNSIGNED <br />
	 *  Sql Server数据库返回结果是: unsigned bigint(19)<br />
	 *  如果数据库不支持unsigned修饰,返回结果 bigint(19)<br />
	 *  
	 * @param jdbcType java.sql.Types 类型
	 * @param length 长度
	 * @param scale 小数位长度
	 * @param unsigned 是否是无符号数(数据库支持无符号数类型有效)
	 * @return 返回数据库类型格式
	 */
	public String createSqlType(int jdbcType,int length,int scale ,boolean unsigned );
}
