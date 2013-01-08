package com.honey.configureation;

import com.honey.core.dbmapping.introspect.IntrospectSchema;
import com.honey.core.dbmapping.introspect.IntrospectSchemaColumn;

public class SchemaConfiguration {
	
	/**
	 * 数据库结构信息
	 */
	private IntrospectSchema introspectSchema ;
	
	private IntrospectSchemaColumn introspectSchemaColumn ;
	
	/**
	 * 构造函数
	 * @param introspectSchema 设定数据库结构信息
	 */
	public SchemaConfiguration(IntrospectSchema introspectSchema,IntrospectSchemaColumn introspectSchemaColumn){
		this.introspectSchema = introspectSchema;
		this.introspectSchemaColumn = introspectSchemaColumn;
	}
	
	/**
	 * 获取数据库结构信息
	 * @return
	 */
	public IntrospectSchema getIntrospectSchema() {
		return introspectSchema;
	}

	/**
	 * 获取数据库结构列信息
	 * @return
	 */
	public IntrospectSchemaColumn getIntrospectSchemaColumn() {
		return introspectSchemaColumn;
	}
	
}
