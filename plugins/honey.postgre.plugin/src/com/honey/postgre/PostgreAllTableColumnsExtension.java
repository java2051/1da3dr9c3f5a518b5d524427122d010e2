package com.honey.postgre;

import java.util.List;

import org.java.plugin.Plugin;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.general.databases.extension.point.TableAllColumns;
import com.honey.postgre.introspect.PostgreAllColumns;

public class PostgreAllTableColumnsExtension  extends AbstractIntrospect  implements TableAllColumns{

	public PostgreAllTableColumnsExtension(Plugin belongPlugin) {
		super(belongPlugin);
	}

	@Override
	public List<Column> introspectedAllTableColumns( ActualSchema actualTableName) {
		return new PostgreAllColumns(
				getDatabaseConnection(),
				actualTableName
		).introspectAllColumns();
	}

	@Override
	public void clean() {
		
	}
	
}
