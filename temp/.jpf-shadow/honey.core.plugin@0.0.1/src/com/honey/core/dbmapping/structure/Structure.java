package com.honey.core.dbmapping.structure;

/**
 * 抽象出数据库结构
 * @author Administrator
 *
 */
public interface Structure {
	
	/**
	 * 获取结构的名称:返回以下几种情况<br />
	 * 	SchemaType.TABLE 返回表的名称<br />
	 *  SchemaType.VIEW 返回视图名称<br />
	 *  SchemaType.TEMPORARY 返回表名称<br />
	 *  SchemaType.PROCEDURE 返回存储过程名称<br />
	 *  SchemaType.FUNCTION 返回函数名称<br />
	 *  表字段名称 <br />
	 *  存储过程参数名称<br />
	 *  函数参数名称<br />
	 * @return
	 */
	public String getName() ;
	
	/**
	 * 获取结构的comment(注释)(如果可以获取)
	 * @return
	 */
	public String getComment() ;

	/**
	 * 映射到javabean的字段名称
	 * @return
	 */
	public String getJavaProperty() ;
}
