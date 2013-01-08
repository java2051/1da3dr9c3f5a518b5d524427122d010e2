package com.honey.general.databases.introspected;

import com.honey.core.dbmapping.structure.ForeignKey;
import com.honey.core.dbmapping.structure.Table;
import com.honey.core.dbmapping.structure.TableColumn;

public class GeneralForeignKey implements ForeignKey {

	private String comment;

	private String name;

	private Table foreignKeyTable;
	
	private String javaProperty;

	private TableColumn foreignKeyTableColumn;

	@Override
	public Table getTable() {
		return this.foreignKeyTable;
	}

	@Override
	public TableColumn getTableColumn() {

		return this.foreignKeyTableColumn;
	}

	@Override
	public String getComment() {

		return this.comment;
	}

	@Override
	public String getName() {

		return name;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTableStructure(Table tableStructure) {
		this.foreignKeyTable = tableStructure;
	}

	public void setTableStructureColumn(
			TableColumn tableStructureColumn) {
		this.foreignKeyTableColumn = tableStructureColumn;
	}
	
	@Override
	public String toString() {
        //return ObjectDump.dump(this);
		return foreignKeyTable.getName()+"."+foreignKeyTableColumn.getName();
    }

	@Override
	public String getJavaProperty() {
		return javaProperty;
	}

	public void setJavaProperty(String javaProperty) {
		this.javaProperty = javaProperty;
	}
	
}
