package com.honey.general.databases.extension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.java.plugin.Plugin;
import org.java.plugin.PluginLifecycleException;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.introspect.IntrospectTableColumn;
import com.honey.core.dbmapping.structure.Column;
import com.honey.core.dbmapping.structure.ExportedKey;
import com.honey.core.dbmapping.structure.ForeignKey;
import com.honey.core.dbmapping.structure.Schema;
import com.honey.core.dbmapping.structure.TableColumn;
import com.honey.core.mapping.TableMapping;
import com.honey.core.utils.EmptyUtility;
import com.honey.general.databases.GeneralIntrospectPlugin;
import com.honey.general.databases.extension.point.ExportedKeyColumns;
import com.honey.general.databases.extension.point.ForeignKeyColumns;
import com.honey.general.databases.extension.point.PrimaryKeyColumns;
import com.honey.general.databases.extension.point.TableAllColumns;
import com.honey.general.databases.introspected.DatabaseIntrospector;
import com.honey.general.databases.introspected.GeneralTable;
import com.honey.general.databases.introspected.GeneralTableColumn;

public class GeneralIntrospectTableColumn extends AbstractIntrospect implements IntrospectTableColumn {
	
	/** 所有列 */
	private List<Column> allColumns = null;
	
	/** 所有主键列 */
	private List<TableColumn> primaryKeyColumns = null;
	
	/** 所有基本键列 */
	private List<TableColumn> baseColumns = null;
	
	/** 所有blob键列 */
	private List<TableColumn> blobColumns = null;
	
	/** 默认使用jdbc获取信息 */
	private DatabaseIntrospector defaultIntrospector = null ;
	
	private ActualSchema actualTableName;
	/**
	 * 构造函数
	 * @param belongPlugin
	 */
	public GeneralIntrospectTableColumn(Plugin belongPlugin) {
		//调用父类的构造函数
		super(belongPlugin);
	}
	
	@Override
	public void introspect(ActualSchema actualTableName) {
		TableMapping tableMapping = actualTableName.getTableMapping();
		if( tableMapping.getMappingKind() ==TableMapping.MappingKind.DATABASE_TABLE && ( this.actualTableName == null || !this.actualTableName.getTableMapping().getName().equals(tableMapping.getName()) ) ){
			clean();this.actualTableName = actualTableName;
			super.databaseConnection = getDatabaseConnection();
			defaultIntrospector = new DatabaseIntrospector(super.databaseConnection);
			
			this.allColumns  = introspectedAllColumns(actualTableName);
			List<Column> pk  = introspectedPrimaryKeyColumns(actualTableName);
			List<ExportedKey> ek  = introspectedExportedKeyColumns(actualTableName);
			List<ForeignKey>  fk  = introspectedForeignKeyColumns(actualTableName);
			
			if( ! EmptyUtility.isListEmpty( this.allColumns  ) ){
				this.primaryKeyColumns = new ArrayList<TableColumn>();
				this.blobColumns = new ArrayList<TableColumn>();
				this.baseColumns = new ArrayList<TableColumn>();
				
				Map<String, TableColumn> allColumnMap = new HashMap<String, TableColumn>();
				TableColumn  tableColumn = null;
				GeneralIntrospectPlugin belongPlugin = (GeneralIntrospectPlugin)super.getBelongPlugin() ;
				List<Column> newAllColumns = new ArrayList<Column>();
				boolean isIncludeEnum = false;
				//处理链
				for(Column column : this.allColumns){
					GeneralTableColumn tc =(GeneralTableColumn)column;
					tc.setJavaProperty(tc.getName());
					tableColumn = belongPlugin.runColumnProcessorChain(tc);
					if( tableColumn != null){
						newAllColumns.add(tableColumn);
						if (tableColumn.isBLOBColumn()){
							blobColumns.add(tableColumn);
						}
						allColumnMap.put(tableColumn.getName(), tableColumn);
					}
					if( !isIncludeEnum && tc.getEnumSchema()!= null){
						isIncludeEnum = true;
					}
				}
				this.allColumns = newAllColumns;
				
				
				//处理主键
				if( ! EmptyUtility.isListEmpty( pk ) ){
					for(Column column : pk){
						tableColumn = allColumnMap.get(column.getName());
						if( tableColumn != null ){
							((GeneralTableColumn)tableColumn).setPrimaryKey(true);
							this.primaryKeyColumns.add(tableColumn);
						}
					}
				}
				
				//处理baseColumns
				for(Column column : this.allColumns){
					tableColumn =(TableColumn)column;
					if ( ! tableColumn.isBLOBColumn() && ! tableColumn.isPrimaryKey()){
						this.baseColumns .add(tableColumn);
					}
				}
				//ExportedKey键
				GeneralTableColumn tc = null;
				if( ! EmptyUtility.isListEmpty( ek ) ){
					for(ExportedKey exportedKey : ek){
						tc = (GeneralTableColumn)allColumnMap.get(exportedKey.getName());
						tc.addExportedKey(exportedKey);
					}
				}
				//Foreign Key
				if( ! EmptyUtility.isListEmpty( fk ) ){
					for(ForeignKey foreignKey : fk){
						tc = (GeneralTableColumn)allColumnMap.get(foreignKey.getName());
						tc.addForeignKey(foreignKey);
					}
				}
				
				GeneralTable generalTable = null;
				Schema schema = ((GeneralIntrospectPlugin)getBelongPlugin()).getCorePlugin().getIntrospectSchema().getSchema();
				if( schema instanceof GeneralTable)
					generalTable = (GeneralTable)schema;
				if( generalTable != null){
					generalTable.setIncludeBlobColumn(blobColumns.size()>0);
					generalTable.setIncludePrimaryKeyColumn(primaryKeyColumns.size()>0);
					generalTable.setIncludeEnumColumn(isIncludeEnum);
				}
				
			}
		}
	}
	
	/**
	 * 清除对象中的数据
	 */
	@Override
	public void clean(){
		if(allColumns != null )allColumns.clear();
		if(primaryKeyColumns != null )primaryKeyColumns.clear();
		if(baseColumns != null )baseColumns.clear();
		if(blobColumns != null )blobColumns.clear();
		actualTableName = null;
	}

	/**
	 * 获取所有列
	 * @param actualTableName
	 * @return
	 */
	private List<Column> introspectedAllColumns(ActualSchema actualTableName){
		List<Column>  answer = null;
		TableAllColumns tableAllColumns = ((GeneralIntrospectPlugin)super.getBelongPlugin()).
												introspectedAllTableColumnsExtension(actualTableName);
		if( tableAllColumns !=null ){
			answer = tableAllColumns.introspectedAllTableColumns(actualTableName);
		}else{
			answer = this.defaultIntrospector.introspectedAllColumns(actualTableName);
		}
		return answer;
	}
	
	/**
	 * 获取主键列
	 * @param actualTableName
	 * @return
	 */
	private List<Column>  introspectedPrimaryKeyColumns(ActualSchema actualTableName){
		List<Column>  answer = null;
		PrimaryKeyColumns primaryKeyColumns = ((GeneralIntrospectPlugin)super.getBelongPlugin()).
											introspectedPrimaryKeyColumns(actualTableName);
		if( primaryKeyColumns !=null ){
			answer = primaryKeyColumns.introspectedPrimaryKeyColumns(actualTableName);
		}else{
			answer = this.defaultIntrospector.introspectedPrimaryKeyColumns(actualTableName);
		}
		return answer;
	}
	
	/**
	 * 获取导出外键列
	 * @param actualTableName
	 * @return
	 */
	private List<ExportedKey>  introspectedExportedKeyColumns(ActualSchema actualTableName){
		List<ExportedKey>  answer = null;
		ExportedKeyColumns exportedKeyColumns = ((GeneralIntrospectPlugin)super.getBelongPlugin()).
											introspectedExportedKeyColumns(actualTableName);
		if( exportedKeyColumns !=null ){
			answer = exportedKeyColumns.introspectedExportedKeyColumns(actualTableName);
		}else{
			answer = this.defaultIntrospector.introspectedExportedKeyColumns(actualTableName);
		}
		return answer;
	}
	
	/**
	 * 获取外键列
	 * @param actualTableName
	 * @return
	 */
	private List<ForeignKey>  introspectedForeignKeyColumns(ActualSchema actualTableName){
		List<ForeignKey>  answer = null;
		ForeignKeyColumns foreignKeyColumns = ((GeneralIntrospectPlugin)super.getBelongPlugin()).
											introspectedForeignKeyColumns(actualTableName);
		if( foreignKeyColumns !=null ){
			answer = foreignKeyColumns.introspectedForeignKeyColumns(actualTableName);
		}else{
			answer = this.defaultIntrospector.introspectedForeignKeyColumns(actualTableName);
		}
		return answer;
	}
	
	@Override
	public List<Column> allColumns() {
		return allColumns;
	}

	@Override
	public List<TableColumn> primaryKeyColumns() {
		return primaryKeyColumns;
	}

	@Override
	public List<TableColumn> baseColumns() {
		return baseColumns;
	}

	@Override
	public List<TableColumn> blobColumns() {
		return blobColumns;
	}
}
