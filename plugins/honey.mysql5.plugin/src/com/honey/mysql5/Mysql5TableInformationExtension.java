package com.honey.mysql5;

import org.java.plugin.Plugin;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Table;
import com.honey.general.databases.extension.point.TableInformation;
import com.honey.mysql5.introspect.MySql5SchemaInformation;

public class Mysql5TableInformationExtension  extends AbstractIntrospect implements TableInformation{

	public Mysql5TableInformationExtension(Plugin belongPlugin) {
		super(belongPlugin);
	}
	
	@Override
	public Table introspectedTableInformation(ActualSchema actualTableName,Table table) {
		return (Table)new MySql5SchemaInformation(
				getDatabaseConnection(),
				actualTableName
		).introspectedTableInformation(table);
	}
}
