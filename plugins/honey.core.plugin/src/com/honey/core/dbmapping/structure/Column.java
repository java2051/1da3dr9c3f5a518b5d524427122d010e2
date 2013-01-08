package com.honey.core.dbmapping.structure;

import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.utils.KeyValuePair;

/**
 * 
 * 在DatabaseSchema类中描述了数据库的结构, 在这些结构中对外操作都需要用的字段.例如:<br />
 * 	table使用列, procedure使用传入参数等,把这些字段抽象出SchemaColumn.
 * 
 * @author Administrator
 *
 */
public interface Column extends Structure {	

    /**
     * jdbc的数据库类型(来自 java.sql.Types 中定义的SQL 类型)
     * @return
     */
	public int getJdbcType() ;

	/**
	 * 数据库中的类型名称(例如: varcar , bigint )
	 * @return
	 */
	public String getDbTypeName() ;
	
	/**
	 * 是否为空(true:表示可以空 false:不能为空)
	 * @return
	 */
	public boolean isNullable() ;
	
	/**
	 * 字段长度(CLOB类型长度会超过int类型长度,所以用long类型定义)
	 * @return
	 */
	public long getLength() ;

	/**
	 * 小数的位数 
	 * @return
	 */
	public int getScale() ;
	
	/**
	 * 获取枚举对象
	 * @return
	 */
	public EnumType getEnumSchema();

	/**
	 * 数据库类映射到java下的类型
	 * @return
	 */
	public FullyQualifiedJavaType getFullyQualifiedJavaType() ;

	/**
	 * 获取数据列验证器.
	 * @return
	 */
	public ColumnValidator[] getColumnValidator();
	
	/**
	 * 获取扩展属性,用于插件扩展使用
	 * @return
	 */
	public KeyValuePair[] getExtendAttribute();
	
	/**
	 * 是否字符串列
	 * @return
	 */
	public boolean isStringColumn() ;
	
	/**
	 * 是否是字符列
	 * @return
	 */
	public boolean isJdbcCharacterColumn();
	
	/**
	 * 是否是日期类型
	 * @return
	 */
	public boolean isJDBCDateColumn();
	
}
