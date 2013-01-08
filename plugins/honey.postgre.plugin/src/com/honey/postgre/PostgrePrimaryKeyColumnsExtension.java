package com.honey.postgre;

import java.util.List;

import org.java.plugin.Plugin;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.general.databases.extension.point.PrimaryKeyColumns;
import com.honey.postgre.introspect.PostgrePrimaryKeyColumns;

public class PostgrePrimaryKeyColumnsExtension  extends AbstractIntrospect  implements PrimaryKeyColumns{

	public PostgrePrimaryKeyColumnsExtension(Plugin belongPlugin) {
		super(belongPlugin);
	}

	@Override
	public List<Column> introspectedPrimaryKeyColumns(
			ActualSchema actualTableName) {
		return new PostgrePrimaryKeyColumns(
				getDatabaseConnection(),
				actualTableName
		).introspectedPrimaryKeyColumns();
	}
	
	@Override
	public void clean() {
		
	}
}
