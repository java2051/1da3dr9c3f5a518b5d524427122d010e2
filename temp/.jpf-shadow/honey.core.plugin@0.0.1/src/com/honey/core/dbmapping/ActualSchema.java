package com.honey.core.dbmapping;

import com.honey.core.dbmapping.introspect.IntrospectSchema;
import com.honey.core.dbmapping.introspect.IntrospectSchemaColumn;
import com.honey.core.dbmapping.structure.Schema;
import com.honey.core.mapping.TableMapping;

/**
 * 数据库参数信息
 * 
 * 常用数据库Catalog和Schema对照表
 * <table  border="1" cellspacing="0" cellpadding="0">
 * 	<tr>
 * 		<td>供应商</td><td >Catalog支持</td><td >Schema支持</td>
 * 	</tr>
 * 	<tr>
 * 		<td >Oracle</td><td >不支持</td><td >Oracle User ID</td>
 * 	</tr>
 * 	
 * 	<tr>
 * 		<td >MySQL</td><td >不支持</td><td >数据库名</td>
 * 	</tr>
 * 	<tr>
 * 		<td >MS SQL Server</td><td >数据库名</td><td >对象属主名，2005版开始有变</td>
 * 	</tr>
 * 	<tr>
 * 		<td>DB2</td><td >指定数据库对象时，Catalog部分省略</td><td >Catalog属主名</td>
 * 	</tr>
 * 	<tr>
 * 		<td >Sybase</td><td >数据库名</td><td >数据库属主名</td>
 * 	</tr>
 * 	<tr>
 * 		<td>Informix</td><td >不支持</td><td >不需要</td>
 * 	</tr>
 * 	<tr>
 * 		<td >PointBase</td><td >不支持</td><td >数据库名</td>
 * 	</tr>
 * 	</table>
 * 
 * @author Administrator
 *
 */
public class ActualSchema {
	
	private String fullName;
	
	private TableMapping tableMapping ;
	
	private IntrospectSchemaColumn introspectSchemaColumn ;
	
	private IntrospectSchema introspectSchema ;
	
	public ActualSchema( ) {
		
	}
	
	public ActualSchema(TableMapping tableMapping ) {
		this.tableMapping = tableMapping;
	}
	
//	public ActualSchemaName(String schemaName, String entity) {
//		this.schemaName = schemaName;
//		this.entity = entity;
//		fullName = StringUtility.composeFullyQualifiedTableName(null,null, schemaName, '.');
//		
//	}
	
//	public ActualSchemaName(String schemaName, String entity,StructureType structureType) {
//		this.schemaName = schemaName;
//		this.entity = entity;
//		this.structureType =  structureType;
//		fullName = StringUtility.composeFullyQualifiedTableName(null,null, schemaName, '.');
//	}
	
//	public String getSchemaName() {
//		return schemaName;
//	}
//
//	public StructureType getStructureType() {
//		return structureType;
//	}
	
//	public String getEntity() {
//		return entity;
//	}

//	public void setEntity(String entity) {
//		this.entity = entity;
//	}

//	@Override
//	public boolean equals(Object obj) {
//		if (obj == null || !(obj instanceof ActualSchemaName)) {
//			return false;
//		}
//		return obj.toString().equals(this.toString());
//	}
	
//	public void setStructureType(StructureType structureType) {
//		this.structureType = structureType;
//	}

	@Override
	public int hashCode() {
		return fullName.hashCode();
	}

	@Override
	public String toString() {
		return fullName;
	}

	/**
	 * @return the tableMapping
	 */
	public final TableMapping getTableMapping() {
		return tableMapping;
	}

	/**
	 * @param tableMapping the tableMapping to set
	 */
	public final void setTableMapping(TableMapping tableMapping) {
		this.tableMapping = tableMapping;
	}

	/**
	 * @return the schema
	 */
	public final Schema getSchema() {
		Schema answer = null;
		if(introspectSchema != null){
			answer = introspectSchema.getSchema();
		}
		
		return answer;
	}

	/**
	 * @return the introspectSchemaColumn
	 */
	public final IntrospectSchemaColumn getIntrospectSchemaColumn() {
		return introspectSchemaColumn;
	}

	/**
	 * @param introspectSchemaColumn the introspectSchemaColumn to set
	 */
	public final void setIntrospectSchemaColumn(
			IntrospectSchemaColumn introspectSchemaColumn) {
		this.introspectSchemaColumn = introspectSchemaColumn;
	}

	/**
	 * @return the introspectSchema
	 */
	public final IntrospectSchema getIntrospectSchema() {
		return introspectSchema;
	}

	/**
	 * @param introspectSchema the introspectSchema to set
	 */
	public final void setIntrospectSchema(IntrospectSchema introspectSchema) {
		this.introspectSchema = introspectSchema;
	}

}
