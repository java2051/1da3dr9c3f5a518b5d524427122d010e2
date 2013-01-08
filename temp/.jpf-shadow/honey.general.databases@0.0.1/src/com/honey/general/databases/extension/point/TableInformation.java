package com.honey.general.databases.extension.point;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Table;

/**
 * 获取表信息的扩展点
 * @author Administrator
 *
 */
public interface TableInformation {
	
	/**
	 * 获取表信息
	 * @param actualTableName
	 * @param table
	 * @return
	 */
	public Table introspectedTableInformation(ActualSchema actualTableName,Table table);
	
}
