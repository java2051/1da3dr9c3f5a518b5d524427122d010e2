package com.honey.general.databases.extension;

import java.util.ArrayList;
import java.util.List;

import org.java.plugin.Plugin;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.introspect.IntrospectProcedureParameter;
import com.honey.core.dbmapping.structure.Column;
import com.honey.core.dbmapping.structure.ProcedureParameter;
import com.honey.core.dbmapping.structure.ProcedureParameter.ProcedureColumnType;
import com.honey.core.mapping.TableMapping;
import com.honey.core.utils.EmptyUtility;
import com.honey.general.databases.GeneralIntrospectPlugin;
import com.honey.general.databases.extension.point.ProcedureParameters;
import com.honey.general.databases.introspected.DatabaseIntrospector;
import com.honey.general.databases.introspected.GeneralProcedureParameter;

/**
 * 
 * @author Administrator
 *
 */
public class GeneralIntrospectProcedureParameter extends AbstractIntrospect implements IntrospectProcedureParameter {

	/** 所有列 */
	private List<Column> allColumns = null;
	
	/** 输入参数 */
	private List<ProcedureParameter> inColumns = null;
	
	/** 输出参数 */
	private List<ProcedureParameter> outColumns = null;
	
	/** 使用标准的jdbc API 获取数据信息 */
	private DatabaseIntrospector defaultDatabaseIntrospector;
	
	/** 判定是否执行过 */
	private ActualSchema actualTableName ;
	//private boolean isIntrospected=false;
	
	/**
	 * 构造函数
	 * @param belongPlugin
	 */
	public GeneralIntrospectProcedureParameter(Plugin belongPlugin) {
		//调用父类的构造
		super(belongPlugin);
	}
	
	@Override
	public void introspect(ActualSchema actualTableName) {
		TableMapping tableMapping = actualTableName.getTableMapping();
		if( tableMapping.getMappingKind() ==TableMapping.MappingKind.DATABASE_TABLE && ( this.actualTableName == null || ! this.actualTableName.getTableMapping().getName().equals(tableMapping.getName()) ) ){
			this.clean(); this.actualTableName = actualTableName;
			
			databaseConnection = getDatabaseConnection();
			defaultDatabaseIntrospector = new DatabaseIntrospector(this.databaseConnection);
			ProcedureParameters procedureParameters = ((GeneralIntrospectPlugin)super.getBelongPlugin()).introspectedProcedureParameters(actualTableName);
			if(procedureParameters != null){
				this.allColumns = procedureParameters.introspectedProcedureParameters(actualTableName);
			}else{
				this.allColumns = defaultDatabaseIntrospector.introspectedAllColumns(actualTableName);
			}
			if( ! EmptyUtility.isListEmpty(this.allColumns ) ){
				inColumns = new ArrayList<ProcedureParameter>();
				outColumns = new ArrayList<ProcedureParameter>();
				ProcedureParameter procedureParameter = null;
				ProcedureColumnType procedureColumnType = null;
				
				List<Column> newAllColumns = new ArrayList<Column>();
				GeneralIntrospectPlugin belongPlugin = (GeneralIntrospectPlugin)super.getBelongPlugin() ;
				for(Column column :  this.allColumns){
					GeneralProcedureParameter pc = (GeneralProcedureParameter)column;
					pc.setJavaProperty(pc.getName());
					procedureParameter = belongPlugin.runColumnProcessorChain(pc);
					if( procedureParameter != null ){
						newAllColumns.add(procedureParameter);
						procedureColumnType = procedureParameter.getProcedureColumnType();
						switch(procedureColumnType){
							case PROCEDURE_COLUMN_IN:
								inColumns.add(procedureParameter);
								break ;
							case PROCEDURE_COLUMN_OUT:
								outColumns.add(procedureParameter);
								break ;
							case PROCEDURE_COLUMN_INOUT:
								inColumns.add(procedureParameter);
								outColumns.add(procedureParameter);
								break ;
						}
					}
				}
				this.allColumns = newAllColumns;
			}
			//isIntrospected = true ;
		}
	}

	@Override
	public void clean() {
		if (allColumns != null) allColumns.clear();
		
		if (inColumns != null) inColumns.clear();
		
		if (outColumns != null) outColumns.clear();
		
		defaultDatabaseIntrospector = null;
		
		actualTableName = null;
		
	}

	@Override
	public List<Column> allColumns() {
		return allColumns;
	}
	
	@Override
	public List<ProcedureParameter> parameterInColumns() {
		return inColumns;
	}

	@Override
	public List<ProcedureParameter> parameterOutColumns() {
		return outColumns;
	}
}
