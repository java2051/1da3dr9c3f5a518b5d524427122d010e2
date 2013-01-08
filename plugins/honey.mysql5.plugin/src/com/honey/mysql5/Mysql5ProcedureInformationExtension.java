package com.honey.mysql5;

import org.java.plugin.Plugin;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Procedure;
import com.honey.general.databases.extension.point.ProcedureInformation;
import com.honey.mysql5.introspect.MySql5SchemaInformation;

public class Mysql5ProcedureInformationExtension  extends AbstractIntrospect implements ProcedureInformation{

	public Mysql5ProcedureInformationExtension(Plugin belongPlugin) {
		super(belongPlugin);
	}
	
	@Override
	public Procedure introspectedProcedureInformation(ActualSchema actualTableName,Procedure procedure) {
		return new MySql5SchemaInformation(
				getDatabaseConnection(),
				actualTableName
		).introspectedProcedureInformation(procedure);
	}
}
