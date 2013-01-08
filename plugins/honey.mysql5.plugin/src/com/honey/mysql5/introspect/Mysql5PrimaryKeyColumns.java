package com.honey.mysql5.introspect;

import java.util.List;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.introspected.DatabaseIntrospector;

public class Mysql5PrimaryKeyColumns {
	private static final String PRIMARY_KEY_COLUMN_KEY="PRI" ;
	
	private DatabaseConnection databaseConnection;
	
	private ActualSchema actualTableName;
	
	public Mysql5PrimaryKeyColumns(DatabaseConnection databaseConnection, ActualSchema actualTableName){
		this.databaseConnection = databaseConnection;

		this.actualTableName = actualTableName;
	}
	
	public List<Column> introspectedPrimaryKeyColumns(){
		List<Column> answer ;
		DatabaseIntrospector  databaseIntrospector = new DatabaseIntrospector(this.databaseConnection);
		answer = databaseIntrospector.introspectedPrimaryKeyColumns(actualTableName);
		return answer;
	}
}
