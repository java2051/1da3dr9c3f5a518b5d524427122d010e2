package com.honey.core.dbmapping.introspect;

import com.honey.core.dbmapping.structure.Schema;

/**
 * 用于获取数据库结构的扩展点
 * @author Administrator
 *
 */
public interface IntrospectSchema extends Introspect{
	
	/**
	 * 返回数据可结构的类型,SchemaType中定义了如下类型:<br />
	 * 		TABLE <br />
	 *      VIEW <br />
	 *      TEMPORARY <br />
	 *      PROCEDURE <br />
	 *      FUNCTION <br />
	 *  在这些数据结构中定义是否可以使用insert和update语句
	 * @return
	 */
	public Schema getSchema() ;
	
	
	/**
	 * 是否支持当前指定结构名称.
	 * @return
	 */
	public boolean isSupport() ;
}
