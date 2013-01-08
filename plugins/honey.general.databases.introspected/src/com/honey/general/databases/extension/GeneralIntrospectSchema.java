package com.honey.general.databases.extension;

import org.java.plugin.Plugin;

import com.honey.core.calculator.MappingCalculator;
import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.introspect.IntrospectSchema;
import com.honey.core.dbmapping.structure.Procedure;
import com.honey.core.dbmapping.structure.Schema;
import com.honey.core.mapping.TableMapping;
import com.honey.general.databases.GeneralIntrospectPlugin;
import com.honey.general.databases.extension.point.ProcedureInformation;
import com.honey.general.databases.extension.point.TableInformation;
import com.honey.general.databases.introspected.DatabaseIntrospector;
import com.honey.general.databases.introspected.GeneralTable;

/**
 * 获取数据库结构的扩展点
 * @author Administrator
 *
 */
public class GeneralIntrospectSchema extends AbstractIntrospect implements IntrospectSchema {
	/** 数据库结构 */
	private Schema schema = null;
	
	/** 使用标准的jdbc API 获取数据信息 */
	private DatabaseIntrospector defaultDatabaseIntrospector;
	
	/** 判定是否执行过 */
	private ActualSchema actualTableName ;
	public GeneralIntrospectSchema(Plugin belongPlugin) {
		super(belongPlugin);
	}

	@Override
	public void introspect(ActualSchema actualTableName) {
		TableMapping tableMapping = actualTableName.getTableMapping();
		if( tableMapping.getMappingKind() ==TableMapping.MappingKind.DATABASE_TABLE &&( this.actualTableName == null || ! this.actualTableName.getTableMapping().getName().equals(tableMapping.getName()) ) ){
			clean();  this.actualTableName = actualTableName;
			
			super.databaseConnection = getDatabaseConnection();
			DatabaseIntrospector di = new DatabaseIntrospector(super.databaseConnection);
			schema = di.introspectedSchemaInformation(actualTableName);
			switch(schema .getSchemaType() ){
				case TABLE:
				case VIEW:
				case TEMPORARY:
					GeneralTable gs =(GeneralTable)schema;
					MappingCalculator mappingCalculator = ((GeneralIntrospectPlugin)super.getBelongPlugin()).getMappingCalculator() ;
					gs.setJavaProperty(mappingCalculator.schemaCalculator(gs.getName()));
					TableInformation  tableInformation = ((GeneralIntrospectPlugin)super.getBelongPlugin()).introspectedTableInformation(actualTableName);
					if( tableInformation != null ){
						schema = tableInformation.introspectedTableInformation(actualTableName,gs);
					}
					
					break ;
				case PROCEDURE:
					ProcedureInformation  procedureInformation = ((GeneralIntrospectPlugin)super.getBelongPlugin()).introspectedProcedureInformation(actualTableName);
					if( procedureInformation !=null ){
						schema = procedureInformation.introspectedProcedureInformation(actualTableName,(Procedure)schema);
					}
					break ;
			}
		}
	}
	

	
	@Override
	public void clean(){
		schema = null;
	}
	
	@Override
	public Schema getSchema() {
		return this.schema;
	}
}
