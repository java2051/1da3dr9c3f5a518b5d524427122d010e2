package com.honey.postgre;

import org.java.plugin.Plugin;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Table;
import com.honey.general.databases.extension.point.TableInformation;
import com.honey.postgre.introspect.PostgreSchemaInformation;

public class PostgreTableInformationExtension  extends AbstractIntrospect implements TableInformation{

	public PostgreTableInformationExtension(Plugin belongPlugin) {
		super(belongPlugin);
	}

	@Override
	public Table introspectedTableInformation(ActualSchema actualTableName, Table table) {
		
		return new PostgreSchemaInformation(
				getDatabaseConnection(),
				actualTableName
		).introspectedTableInformation(table);
	}
	
	@Override
	public void clean() {
		
	}
}
