package com.honey.core.generator;

import com.honey.core.compiler.CompilationDescriptor;
import com.honey.core.dbmapping.introspect.IntrospectSchema;
import com.honey.core.dbmapping.introspect.IntrospectSchemaColumn;

/**
 * 生成文件
 * @author Administrator
 *
 */
public interface CodeCompilation {

	/**
	 * 根据数据表信息编译java文件
	 * @param schema 数据库表信息
	 * @param columns 数据库列信息
	 * @return
	 */
	public CompilationDescriptor[] compilation(IntrospectSchema schema,IntrospectSchemaColumn columns) ;
	
}
