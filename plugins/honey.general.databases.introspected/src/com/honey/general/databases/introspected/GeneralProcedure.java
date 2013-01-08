package com.honey.general.databases.introspected;

import java.util.HashMap;
import java.util.Map;

import com.honey.core.dbmapping.structure.Procedure;
import com.honey.core.dbmapping.structure.StructureType;
import com.honey.core.types.Vendor;
import com.honey.core.utils.ObjectDump;

public class GeneralProcedure implements Procedure {

	private StructureType schemaType = StructureType.TABLE;

	private Map<String, String> moreInformation = new HashMap<String, String>();

	private String comment;

	private String name;

	private Vendor vendor;

	private String sqlScipts;

	private String javaProperty;
	
	private ProcedureResultType procedureResultType;

	@Override
	public ProcedureResultType getProcedureResultType() {
		return this.procedureResultType;
	}

	@Override
	public Map<String, String> getMoreInformation() {
		return this.moreInformation;
	}

	@Override
	public StructureType getSchemaType() {
		return this.schemaType;
	}

	@Override
	public String getSqlScipt() {
		return this.sqlScipts;
	}

	@Override
	public Vendor getVendor() {
		return this.vendor;
	}

	@Override
	public String getComment() {
		return this.comment;
	}

	@Override
	public String getName() {

		return this.name;
	}

	@Override
	public String toString() {
		return ObjectDump.dump(this);
	}

	public String getSqlScipts() {
		return sqlScipts;
	}

	public void setSqlScipts(String sqlScipts) {
		this.sqlScipts = sqlScipts;
	}

	public void setSchemaType(StructureType schemaType) {
		this.schemaType = schemaType;
	}

	public void setMoreInformation(Map<String, String> moreInformation) {
		this.moreInformation = moreInformation;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	public void setProcedureResultType(ProcedureResultType procedureResultType) {
		this.procedureResultType = procedureResultType;
	}

	public String getJavaProperty() {
		return javaProperty;
	}

	public void setJavaProperty(String javaProperty) {
		this.javaProperty = javaProperty;
	}

	@Override
	public boolean isIncludeBlobColumn() {
		return false;
	}

	@Override
	public boolean isIncludeEnumColumn() {
		return false;
	}

	@Override
	public boolean isIncludePrimaryKeyColumn() {
		return false;
	}
	
}
