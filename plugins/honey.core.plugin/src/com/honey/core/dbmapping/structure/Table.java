package com.honey.core.dbmapping.structure;


/**
 * 数据表的结构
 * @author Administrator
 */
public interface Table extends Schema{
	
	/**
	 * 数据库的catalog
	 * @return
	 */
	public String getCatalog() ;
	
	/**
	 * 获取数据库的schema
	 * @return
	 */
	public String getSchema() ;

	/**
	 * 获取数据库的别名
	 * @return
	 */
	public String getAlias() ;

}

