package com.honey.general.databases;

import org.java.plugin.Plugin;
import org.java.plugin.PluginLifecycleException;

import com.honey.core.CorePlugin;
import com.honey.core.calculator.MappingCalculator;
import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.ProcedureParameter;
import com.honey.core.dbmapping.structure.TableColumn;
import com.honey.core.processor.ColumnProcessor;
import com.honey.core.utils.ObjectFactory;
import com.honey.general.databases.extension.point.ExportedKeyColumns;
import com.honey.general.databases.extension.point.ForeignKeyColumns;
import com.honey.general.databases.extension.point.PrimaryKeyColumns;
import com.honey.general.databases.extension.point.ProcedureInformation;
import com.honey.general.databases.extension.point.ProcedureParameters;
import com.honey.general.databases.extension.point.TableAllColumns;
import com.honey.general.databases.extension.point.TableInformation;

/**
 * 使用jdbc 获取数据库全部信息
 * @author Administrator
 * 
 */
public class GeneralIntrospectPlugin extends Plugin {
	
	private CorePlugin corePlugin ;
	
	private MappingCalculator mappingCalculator ;
	
	private ColumnProcessor[] columnProcessorChain;
	
	@Override
	protected void doStart() throws Exception {
		corePlugin= (CorePlugin)ObjectFactory.getRequestPlugin(this, "honey.core.plugin");
		mappingCalculator = corePlugin.getMappingCalculator();
		columnProcessorChain =  corePlugin.getColumnProcessor();
	}

	public MappingCalculator getMappingCalculator(){
		return this.mappingCalculator;
	}
	
	public ColumnProcessor[] getColumnProcessorChain() {
		return columnProcessorChain;
	}
	
	/**
	 * 运行数据列处理链
	 * @param column
	 * @return
	 */
	public TableColumn runColumnProcessorChain( TableColumn column  ) {
		TableColumn answer = column;
		if(columnProcessorChain != null && columnProcessorChain.length > 0  ){
			for(ColumnProcessor columnProcessor : columnProcessorChain){
				if(answer != null ){
					answer = (TableColumn) columnProcessor.processor(answer);
				}else{
					break ;
				}
			}
		}
		return answer;
	}
	
	/**
	 * 运行数据列处理链
	 * @param column
	 * @return
	 */
	public ProcedureParameter runColumnProcessorChain( ProcedureParameter column  ) {
		ProcedureParameter answer = column;
		if(columnProcessorChain != null && columnProcessorChain.length > 0  ){
			for(ColumnProcessor columnProcessor : columnProcessorChain){
				if(answer != null ){
					answer = (ProcedureParameter) columnProcessor.processor(answer);
				}else{
					break ;
				}
			}
		}
		return answer;
	}
	
	/**
	 * 获取表信息
	 * @param actualSchemaName
	 */
	public TableInformation introspectedTableInformation(ActualSchema actualTableName){
		TableInformation answer = null;
		try {
			com.honey.core.Extension<GeneralIntrospectPlugin>  extension = ObjectFactory.getExtensionClass( this , "IntrospectedTableInformation");
			if(extension instanceof TableInformation){
				answer = (TableInformation)extension;
			}
		} catch (PluginLifecycleException e) {
			e.printStackTrace();
		}
		return answer;
	}

	/**
	 * 
	 * @param actualTableName
	 * @return
	 */
	public ProcedureInformation introspectedProcedureInformation(ActualSchema actualTableName){
		ProcedureInformation answer = null;
		try {
			com.honey.core.Extension<GeneralIntrospectPlugin>  extension = ObjectFactory.getExtensionClass( this , "IntrospectedProcedureInformation");
			if(extension instanceof ProcedureInformation){
				answer = (ProcedureInformation)extension;
			}
		} catch (PluginLifecycleException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * 所有列
	 * @param actualTableName
	 * @return
	 */
	public TableAllColumns introspectedAllTableColumnsExtension(ActualSchema actualTableName){
		TableAllColumns answer = null;
		try {
			com.honey.core.Extension<GeneralIntrospectPlugin>  extension = ObjectFactory.getExtensionClass( this , "IntrospectedAllTableColumns");
			if(extension instanceof TableAllColumns){
				answer = (TableAllColumns)extension;
			}
		} catch (PluginLifecycleException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * 主键
	 * @param actualTableName
	 * @return
	 */
	public PrimaryKeyColumns introspectedPrimaryKeyColumns(ActualSchema actualTableName){
		PrimaryKeyColumns answer = null;
		try {
			com.honey.core.Extension<GeneralIntrospectPlugin>  extension = ObjectFactory.getExtensionClass( this , "IntrospectedPrimaryKeyColumns");
			if(extension instanceof PrimaryKeyColumns){
				answer = (PrimaryKeyColumns)extension;
			}
		} catch (PluginLifecycleException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * 导出键
	 * @param actualTableName
	 * @return
	 */
	public ExportedKeyColumns introspectedExportedKeyColumns(ActualSchema actualTableName){
		ExportedKeyColumns answer = null;
		try {
			com.honey.core.Extension<GeneralIntrospectPlugin> extension = ObjectFactory.getExtensionClass( this , "IntrospectedExportedKeyColumns");
			if(extension instanceof ExportedKeyColumns){
				answer = (ExportedKeyColumns)extension;
			}
		} catch (PluginLifecycleException e) {
			e.printStackTrace();
		}
		return answer;
	}
	/**
	 * 导出键
	 * @param actualTableName
	 * @return
	 */
	public ForeignKeyColumns introspectedForeignKeyColumns(ActualSchema actualTableName){
		ForeignKeyColumns answer = null;
		try {
			com.honey.core.Extension<GeneralIntrospectPlugin> extension = ObjectFactory.getExtensionClass( this , "IntrospectedForeignKeyColumns");
			if(extension instanceof ForeignKeyColumns){
				answer = (ForeignKeyColumns)extension;
			}
		} catch (PluginLifecycleException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	
	/**
	 * 存储过程参数
	 * @param actualTableName
	 * @return
	 */
	public ProcedureParameters introspectedProcedureParameters(ActualSchema actualTableName){
		ProcedureParameters answer = null;
		try {
			com.honey.core.Extension<GeneralIntrospectPlugin> extension = ObjectFactory.getExtensionClass( this , "IntrospectProcedureParameters");
			if(extension instanceof ProcedureParameters){
				answer = (ProcedureParameters)extension;
			}
		} catch (PluginLifecycleException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public CorePlugin getCorePlugin() {
		return corePlugin;
	}

	@Override
	protected void doStop() throws Exception {
		
	}
}
