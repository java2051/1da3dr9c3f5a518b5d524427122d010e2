package com.honey.calculator;

import org.java.plugin.Plugin;

import com.honey.core.dbmapping.structure.Column;
import com.honey.core.processor.ColumnProcessor;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.introspected.GeneralProcedureParameter;
import com.honey.general.databases.introspected.GeneralTableColumn;

public class ColumnNameMappingCalculator extends StandardMappingCalculator implements ColumnProcessor {
	
	public ColumnNameMappingCalculator(Plugin belongPlugin) {
		super(belongPlugin);
	}
	
	public Column processor(Column column) {
		String tmp  =  column.getJavaProperty();
		if( !StringUtility.stringHasValue(tmp) ){
			tmp = column.getName();
		}
		if(column instanceof GeneralTableColumn ){
			((GeneralTableColumn)column).setJavaProperty(schemaCalculator(tmp));
		}else if(column instanceof GeneralProcedureParameter ){
			((GeneralProcedureParameter)column).setJavaProperty(schemaCalculator(tmp));
		}
		return column;
	}
}
