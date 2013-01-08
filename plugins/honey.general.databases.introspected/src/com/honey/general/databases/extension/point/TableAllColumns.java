package com.honey.general.databases.extension.point;

import java.util.List;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;

/**
 * 获取所有数据库列的扩展点
 * @author Administrator
 *
 */
public interface TableAllColumns  {
	
	
	/**
	 * 获取所有数据库列
	 * @param actualTableName
	 * @return
	 */
	public List<Column> introspectedAllTableColumns(ActualSchema actualTableName);
	
}
