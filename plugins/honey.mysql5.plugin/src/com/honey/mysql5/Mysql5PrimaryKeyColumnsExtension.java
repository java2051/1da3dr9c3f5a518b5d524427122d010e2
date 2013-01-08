package com.honey.mysql5;

import java.util.List;

import org.java.plugin.Plugin;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.general.databases.extension.point.PrimaryKeyColumns;
import com.honey.mysql5.introspect.Mysql5PrimaryKeyColumns;

public class Mysql5PrimaryKeyColumnsExtension extends AbstractIntrospect implements PrimaryKeyColumns{

	public Mysql5PrimaryKeyColumnsExtension(Plugin belongPlugin) {
		super(belongPlugin);
	}

	@Override
	public List<Column> introspectedPrimaryKeyColumns( ActualSchema actualTableName) {
		return new Mysql5PrimaryKeyColumns(
				getDatabaseConnection(),
				actualTableName
		).introspectedPrimaryKeyColumns();
	}
}
