package com.honey.core.dbmapping.introspect;

import java.util.List;

import com.honey.core.dbmapping.structure.FunctionParameter;

/**
 * 在部分数据库中jdbc是不允许直接调用,例如mysql
 * 从数据结构中获取列信息
 * @author Administrator
 *
 */
public interface IntrospectFunctionParameter extends IntrospectSchemaColumn {
	
	/**
	 * 获取存储过程输入参数
	 * @return
	 */
	public List<FunctionParameter> parameterInColumns();
	
	/**
	 * 获取存储过程输出参数
	 * @return
	 */
	public List<FunctionParameter> parameterOutColumns();
	
}
