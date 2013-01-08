package com.honey.general.databases.introspected;

import java.util.HashMap;
import java.util.Map;

import com.honey.core.dbmapping.structure.StructureType;
import com.honey.core.dbmapping.structure.Table;
import com.honey.core.types.Vendor;
import com.honey.core.utils.ObjectDump;

public class GeneralTable implements Table{
	
	private String catalog;
	
	private String schema;
	
	private String tableName;
	
	private String alias;

	private String javaProperty;
	
	private String comment;
	
	private Vendor vendor;
	
	private String sqlScipt ;
	
	private StructureType schemaType =  StructureType.TABLE;
	
	private Map<String , String> moreInformation = new HashMap<String, String>();
	
	private boolean includeBlobColumn ;
	
	private boolean includePrimaryKeyColumn;

	private boolean includeEnumColumn;
	
	
	@Override
	public String getAlias() {
		
		return this.alias;
	}

	@Override
	public String getCatalog() {

		return this.catalog;
	}

	@Override
	public String getComment() {
		
		return this.comment;
	}

	@Override
	public Map<String, String> getMoreInformation() {
		
		return this.moreInformation;
	}

	@Override
	public String getSchema() {
		
		return this.schema;
	}

	@Override
	public String getSqlScipt() {
		
		return this.sqlScipt;
	}

	@Override
	public String getName(){
		
		return this.tableName;
	}

	@Override
	public Vendor getVendor() {
		
		return this.vendor;
	}
	
	public void addInformation(String key ,String value){
		this.moreInformation.put(key, value);
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public void setName(String tableName) {
		this.tableName = tableName;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public void setSqlScipt(String sqlScipt) {
		this.sqlScipt = sqlScipt;
	}
	
	public void setMoreInformation(Map<String, String> moreInformation) {
		this.moreInformation = moreInformation;
	}
	
	public void setSchemaType(StructureType schemaType) {
		this.schemaType = schemaType;
	}

	@Override
	public StructureType getSchemaType() {
		return schemaType;
	}

	public String getJavaProperty() {
		return javaProperty;
	}

	public void setJavaProperty(String javaProperty) {
		this.javaProperty = javaProperty;
	}
	
	@Override
	public String toString() {
        return ObjectDump.dump(this);
    }

	public boolean isIncludeBlobColumn() {
		return includeBlobColumn;
	}

	public void setIncludeBlobColumn(boolean includeBlobColumn) {
		this.includeBlobColumn = includeBlobColumn;
	}

	public boolean isIncludePrimaryKeyColumn() {
		return includePrimaryKeyColumn;
	}

	public void setIncludePrimaryKeyColumn(boolean includePrimaryKeyColumn) {
		this.includePrimaryKeyColumn = includePrimaryKeyColumn;
	}

	public boolean isIncludeEnumColumn() {
		return includeEnumColumn;
	}

	public void setIncludeEnumColumn(boolean includeEnumColumn) {
		this.includeEnumColumn = includeEnumColumn;
	}
	
}
