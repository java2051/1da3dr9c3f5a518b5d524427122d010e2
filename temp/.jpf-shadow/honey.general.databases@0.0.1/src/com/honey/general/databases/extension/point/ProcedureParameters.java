package com.honey.general.databases.extension.point;

import java.util.List;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;

/**
 * 获取存储过程参数扩展点
 * @author Administrator
 *
 */
public interface ProcedureParameters  {
	
	/**
	 * 获取存储过程参数
	 * @param actualTableName
	 * @return
	 */
	public List<Column> introspectedProcedureParameters(ActualSchema actualTableName);
	
}
