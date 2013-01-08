package com.honey.general.databases.extension.point;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Procedure;

/**
 * 获取存储过程信息扩展点
 * @author Administrator
 *
 */
public interface ProcedureInformation {
	
	/**
	 * 获取存储过程
	 * @param actualTableName
	 * @param procedure
	 * @return
	 */
	public Procedure introspectedProcedureInformation(ActualSchema actualTableName ,Procedure procedure);
	
}
