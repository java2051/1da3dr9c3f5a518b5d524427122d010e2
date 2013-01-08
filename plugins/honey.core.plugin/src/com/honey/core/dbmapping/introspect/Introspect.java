package com.honey.core.dbmapping.introspect;

import com.honey.core.dbmapping.ActualSchema;


/**
 * 从数据库中获取信息
 * @author Administrator
 *
 */
public interface Introspect {
	
	/**
	 * 执行
	 */
	public void introspect(ActualSchema actualTableName);
	
}
