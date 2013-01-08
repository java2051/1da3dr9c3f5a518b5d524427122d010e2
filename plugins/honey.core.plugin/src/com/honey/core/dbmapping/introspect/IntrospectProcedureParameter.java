package com.honey.core.dbmapping.introspect;

import java.util.List;

import com.honey.core.dbmapping.structure.ProcedureParameter;

/**
 * 从数据结构中获取列信息
 * @author Administrator
 *
 */
public interface IntrospectProcedureParameter extends IntrospectSchemaColumn {
	
	/**
	 * 获取存储过程输入参数
	 * @return
	 */
	public List<ProcedureParameter> parameterInColumns();
	
	/**
	 * 获取存储过程输出参数
	 * @return
	 */
	public List<ProcedureParameter> parameterOutColumns();
}
