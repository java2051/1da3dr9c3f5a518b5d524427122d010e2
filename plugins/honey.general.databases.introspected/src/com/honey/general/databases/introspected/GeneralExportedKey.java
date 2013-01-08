package com.honey.general.databases.introspected;

import com.honey.core.dbmapping.structure.ExportedKey;
import com.honey.core.dbmapping.structure.Table;
import com.honey.core.dbmapping.structure.TableColumn;

/**
 * 导出键
 * @author Administrator
 *
 */
public class GeneralExportedKey implements ExportedKey {
	
	/** 注释 */
	private String comment;

	/** 名称 */
	private String name;

	/** 导出的表 */
	private Table exportedTable;

	/** 导出表的列 */
	private TableColumn exportedTableColumn;

	/** 当前列映射到java的名称 */
	private String javaProperty;
	
	@Override
	public Table getTable() {
		return this.exportedTable;
	}

	@Override
	public TableColumn getTableColumn() {
		return this.exportedTableColumn;
	}

	@Override
	public String getComment() {
		return this.comment;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTable(Table tableStructure) {
		this.exportedTable = tableStructure;
	}

	public void setTableColumn(
			TableColumn tableStructureColumn) {
		this.exportedTableColumn = tableStructureColumn;
	}

	@Override
	public String getJavaProperty() {
		return javaProperty;
	}

	public void setJavaProperty(String javaProperty) {
		this.javaProperty = javaProperty;
	}
	@Override
	public String toString() {
		//return ObjectDump.dump(this);
		return exportedTable.getName()+"."+exportedTableColumn.getName();
	}
	
}
