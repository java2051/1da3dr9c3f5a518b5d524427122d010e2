package com.honey.mysql5;

import java.util.List;

import org.java.plugin.Plugin;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.general.databases.extension.point.TableAllColumns;
import com.honey.mysql5.introspect.Mysql5AllColumns;

public class Mysql5AllTableColumnsExtension extends AbstractIntrospect implements TableAllColumns {

	public Mysql5AllTableColumnsExtension(Plugin belongPlugin) {
		super(belongPlugin);
	}

	@Override
	public List<Column> introspectedAllTableColumns(ActualSchema actualTableName) {
		return new Mysql5AllColumns(
				 super.getDatabaseConnection(),
				actualTableName
		).introspectAllTableColumns();
	}
	
}
