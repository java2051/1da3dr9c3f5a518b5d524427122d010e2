package com.honey.general.databases.extension.point;

import java.util.List;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;

/**
 * 获取数据库主键的扩展点
 * @author Administrator
 *
 */
public interface PrimaryKeyColumns  {
	
	/**
	 * 获取数据库主键
	 * @param actualTableName
	 * @return
	 */
	public List<Column> introspectedPrimaryKeyColumns(ActualSchema actualTableName);
	
	
}
