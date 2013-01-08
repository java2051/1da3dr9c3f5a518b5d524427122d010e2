package com.honey.postgre;

import org.java.plugin.Plugin;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Procedure;
import com.honey.general.databases.extension.point.ProcedureInformation;
import com.honey.postgre.introspect.PostgreSchemaInformation;

public class PostgreProcedureInformationExtension extends AbstractIntrospect implements ProcedureInformation{

	public PostgreProcedureInformationExtension(Plugin belongPlugin) {
		super(belongPlugin);
	}

	@Override
	public Procedure introspectedProcedureInformation(
			ActualSchema actualTableName, Procedure procedure) {

		return new PostgreSchemaInformation(
				getDatabaseConnection(),
				actualTableName
		).introspectedProcedureInformation(procedure);

	}
	
	@Override
	public void clean() {
		
	}

}
