package com.honey.general.databases.extension.point;

import java.util.List;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.ExportedKey;

/**
 * 获取数据库主键的扩展点
 * @author Administrator
 *
 */
public interface ExportedKeyColumns  {
	
	/**
	 * 获取数据库导出键
	 * @param actualTableName
	 * @return
	 */
	public List<ExportedKey> introspectedExportedKeyColumns(ActualSchema actualTableName);
	
	
}
