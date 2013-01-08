package com.honey.core.dbmapping.introspect;

import java.util.List;

import com.honey.core.dbmapping.structure.TableColumn;

/**
 * 从数据结构中获取列信息
 * @author Administrator
 *
 */
public interface IntrospectTableColumn extends IntrospectSchemaColumn {
	
	/**
	 * 除数据库主键,大字段,外的所有字段
	 * @return
	 */
	public List<TableColumn> baseColumns();
	
	/**
	 * 获取数据库大字段列
	 * @return
	 */
	public List<TableColumn> blobColumns();
	
	/**
	 * 获取数据库的所有主键字段
	 * @return
	 */
	public List<TableColumn> primaryKeyColumns();
	
	
//	/**
//	 * 获取数据库被关联的字段
//	 * @return
//	 */
//	public List<ExportedKey> exportedKeyColumns();
//	
//	/**
//	 * 获取数据库的关联外键
//	 * @return
//	 */
//	public List<ForeignKey> foreignKeyColumns();
	
}
