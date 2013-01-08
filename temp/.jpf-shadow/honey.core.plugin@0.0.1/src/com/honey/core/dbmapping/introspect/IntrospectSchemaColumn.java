package com.honey.core.dbmapping.introspect;

import java.util.List;

import com.honey.core.dbmapping.structure.Column;

/**
 * 从数据结构中获取列信息
 * @author Administrator
 *
 */
public interface IntrospectSchemaColumn extends Introspect {
	
	/**
	 * 获取数据库所有的字段
	 * @return
	 */
	public List<Column> allColumns();
	
}
