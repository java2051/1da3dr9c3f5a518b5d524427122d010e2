package com.honey.mysql5;

import java.util.List;

import org.java.plugin.Plugin;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.general.databases.extension.point.ProcedureParameters;
import com.honey.mysql5.introspect.Mysql5AllColumns;

public class Mysql5ProcedureParametersExtension extends AbstractIntrospect implements ProcedureParameters{

	public Mysql5ProcedureParametersExtension(Plugin belongPlugin) {
		super(belongPlugin);
	}

	@Override
	public List<Column> introspectedProcedureParameters(
			ActualSchema actualTableName) {
		
		return new Mysql5AllColumns(
				 super.getDatabaseConnection(),
				actualTableName
		).introspectProcedureParameters();
	}
}
