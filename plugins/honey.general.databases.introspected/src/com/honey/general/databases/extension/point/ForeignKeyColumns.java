package com.honey.general.databases.extension.point;

import java.util.List;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.ForeignKey;

/**
 * 获取数据库主键的扩展点
 * @author Administrator
 *
 */
public interface ForeignKeyColumns  {
	
	/**
	 * 获取数据库外键
	 * @param actualTableName
	 * @return
	 */
	public List<ForeignKey> introspectedForeignKeyColumns(ActualSchema actualTableName);
	
	
}
