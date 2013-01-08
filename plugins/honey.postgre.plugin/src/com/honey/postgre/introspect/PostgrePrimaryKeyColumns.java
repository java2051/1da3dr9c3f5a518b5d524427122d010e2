package com.honey.postgre.introspect;

import java.util.List;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.core.utils.EmptyUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.introspected.DatabaseIntrospector;
import com.honey.general.databases.introspected.GeneralTableColumn;

public class PostgrePrimaryKeyColumns {
	
	private DatabaseConnection databaseConnection;
	
	private ActualSchema actualTableName;
	
	public PostgrePrimaryKeyColumns(DatabaseConnection databaseConnection, ActualSchema actualTableName){
		this.databaseConnection = databaseConnection;

		this.actualTableName = actualTableName;
	}
	
	public List<Column> introspectedPrimaryKeyColumns(){
		List<Column> answer ;
		DatabaseIntrospector  databaseIntrospector = new DatabaseIntrospector(this.databaseConnection);
		answer = databaseIntrospector.introspectedPrimaryKeyColumns(actualTableName);
//		if(! EmptyUtility.isListEmpty(answer) ){
//			for(Column column: answer ){
//				if(column instanceof GeneralTableColumn ){
//					GeneralTableColumn gTableColumn =(GeneralTableColumn)column;
//					if(gTableColumn.getDbTypeName().endsWith("serial")){
//						gTableColumn.setDefaultValue(null);
//						gTableColumn.setAutoIncrement(true);
//					}
					
//					String defaultValue = gTableColumn.getDefaultValue();
//					if(defaultValue != null && defaultValue.startsWith("nextval(") ){
//						gTableColumn.setDefaultValue(null);
//						gTableColumn.setAutoIncrement(true);
//					}
//				}
//			}
//		}
		return answer;
	}
}
