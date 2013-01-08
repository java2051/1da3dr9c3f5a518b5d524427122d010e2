package com.honey.core;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.java.plugin.Plugin;
import org.java.plugin.PluginLifecycleException;
import org.java.plugin.boot.Application;
import org.java.plugin.boot.ApplicationPlugin;
import org.java.plugin.util.ExtendedProperties;

import com.honey.configureation.ConfigurationPlugin;
import com.honey.configureation.Context;
import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.introspect.IntrospectSchema;
import com.honey.core.dbmapping.introspect.IntrospectSchemaColumn;
import com.honey.core.mapping.TableMapping;
import com.honey.core.utils.ObjectFactory;

/**
 * 
 * 代码生成器核心插件
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class CorePlugin extends ApplicationPlugin implements Application {
	
	protected void doStart() throws Exception {/*Nop*/}
	protected void doStop() throws Exception {/*Nop*/}
	
	/** 系统环境 */ 
	private Context context ; 

	private IntrospectSchema introspectSchema ;
	
	private IntrospectSchemaColumn introspectSchemaColumn ;
	
	private Map<TableMapping, ActualSchema> actualSchemas = null;
	
	@Override
	protected Application initApplication(ExtendedProperties config, String[] args) throws Exception {
		Plugin plugin = ObjectFactory.getRequestPlugin( this , "honey.configuration.plugin");
		if(plugin instanceof ConfigurationPlugin ){
			ConfigurationPlugin configurationPlugin = (ConfigurationPlugin)plugin;
			configurationPlugin.applicationInitConfiguration(config, args);
			context = configurationPlugin.getContextInstant();
		}
		actualSchemas = new HashMap<TableMapping, ActualSchema>();
		
		return this;
	}
	
	@Override
	public void startApplication() throws Exception {
		List<TableMapping> mappings = this.context.getMappingEnvironmentHolder().getMappings();
		
		ActualSchema actualSchema =  null;
		for(TableMapping mapping : mappings ){
			actualSchema = new ActualSchema(mapping);
			
			introspectSchema =  introspectedSchema(actualSchema);
			actualSchema.setIntrospectSchema( introspectSchema );
//			StructureType schemaType = actualSchemaName.getSchema().getSchemaType();
//			switch ( schemaType){
//				case TABLE:
//				case VIEW:
//				case TEMPORARY:
//					introspectSchemaColumn = introspectedTableColumn(actualSchemaName);
//					break ;
//				case PROCEDURE:
//					introspectSchemaColumn = introspectProcedureParameter(actualSchemaName) ;
//					break ;
//			}
			System.out.println(introspectSchema.getSchema());
			
			
			actualSchemas.put(mapping, actualSchema);
		}
	}
	
	/**
	 * 存储过程参数
	 * @param actualSchemaName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws PluginLifecycleException
	 */
	private IntrospectSchemaColumn introspectProcedureParameter(ActualSchema actualSchemaName ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, PluginLifecycleException{		
		IntrospectSchemaColumn answer = null;
		com.honey.core.Extension<CorePlugin>  extension = ObjectFactory.getExtensionClass(this, PluginConstent.EXTENSION_INTROSPECT_PROCEDURE_PARAMETER);
		if(extension instanceof IntrospectSchemaColumn){
			answer = (IntrospectSchemaColumn)extension;
			answer.introspect(actualSchemaName);
		}
		return answer;
	}
	
	/**
	 * 
	 * 获取数据库列信息
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws PluginLifecycleException
	 */
	private IntrospectSchemaColumn introspectedTableColumn(ActualSchema actualSchemaName ) throws PluginLifecycleException{
		IntrospectSchemaColumn answer = null;
		com.honey.core.Extension<CorePlugin>  extension = ObjectFactory.getExtensionClass( this , PluginConstent.EXTENSION_INTROSPECT_TABLE_COLUMN);
		if(extension instanceof IntrospectSchemaColumn){
			answer = (IntrospectSchemaColumn)extension;
			answer.introspect(actualSchemaName);
		}
		return answer;
	}
	
	/**
	 * 获取数据库结构的扩展点.扩展点类是com.honey.core.dbmapping.introspectIntrospectSchema
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws PluginLifecycleException
	 */
	private IntrospectSchema introspectedSchema(ActualSchema actualSchemaName ) throws PluginLifecycleException{
		IntrospectSchema answer = null;
		com.honey.core.Extension<CorePlugin> extension = ObjectFactory.getExtensionClass( this , PluginConstent.EXTENSION_INTROSPECT_SCHEMA);
		if(extension instanceof IntrospectSchema){
			answer = (IntrospectSchema)extension;
			answer.introspect(actualSchemaName);
		}
		return answer;
	}
}
