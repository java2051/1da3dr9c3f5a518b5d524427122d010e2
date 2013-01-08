package com.honey.postgre.introspect;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;

import com.honey.core.dbmapping.ActualSchema;
import com.honey.core.dbmapping.structure.Column;
import com.honey.core.dbmapping.structure.Schema;
import com.honey.core.dbmapping.structure.StructureType;
import com.honey.core.utils.EmptyUtility;
import com.honey.core.utils.StringUtility;
import com.honey.general.databases.DatabaseConnection;
import com.honey.general.databases.introspected.DatabaseIntrospector;
import com.honey.general.databases.introspected.GeneralTableColumn;

/**
 * 查询 postgre 数据库列信息 
 * @author Administrator
 *
 */
public class PostgreAllColumns {

	/** 数据库连接 */
	private DatabaseConnection databaseConnection;

	/** 被查询的数据库结构 */
	private ActualSchema actualTableName;

	/**
	 * 构造函数
	 * @param databaseConnection 数据库连接
	 * @param actualTableName 被查询的数据库结构
	 */
	public PostgreAllColumns(DatabaseConnection databaseConnection, ActualSchema actualTableName) {
		this.databaseConnection = databaseConnection;
		this.actualTableName = actualTableName;
	}
	
	/**
	 * 查询所有的列
	 * @return
	 */
	public List<Column> introspectAllColumns() {
		List<Column> answer = null;
		DatabaseIntrospector  databaseIntrospector = new DatabaseIntrospector(this.databaseConnection);
		answer = databaseIntrospector.introspectedAllColumns(actualTableName);
		
		if(! EmptyUtility.isListEmpty(answer) ){
			Connection conn = databaseConnection.getConnection();
			Statement statement = null ;
			try {
				statement = conn.createStatement() ;
				for(Column column: answer ){
					if(column instanceof GeneralTableColumn ){
						GeneralTableColumn gTableColumn =(GeneralTableColumn)column;
						String s = PostgreLocalSql.getTableCloumnComment(databaseConnection, actualTableName.getTableMapping().getName(), gTableColumn.getName()) ;
						ResultSet result = statement.executeQuery(s);
						if(result.next()){
							gTableColumn.setComment(result.getString(1));
						}
						result.close();
						if(gTableColumn.getDbTypeName().endsWith("serial")){
							gTableColumn.setDefaultValue(null);
							gTableColumn.setAutoIncrement(true);
						}
//					String defaultValue = gTableColumn.getDefaultValue();
//					if(defaultValue != null && defaultValue.startsWith("nextval(") ){
//						gTableColumn.setDefaultValue(null);
//						gTableColumn.setAutoIncrement(true);
//					}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if( statement != null){
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if( conn != null){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
		Schema schema = databaseIntrospector.introspectedSchemaInformation(actualTableName);
		if( StructureType.VIEW  == schema.getSchemaType() ){
			parseViewScript(answer, schema);
		}
		return answer;
	}

	/**
	 * 通过解析创建视图脚本,获取视图关联关系从而得到视图列的comment.
	 * @param column 所有的列
	 * @param schema 数据库结构
	 */
	private void parseViewScript(List<Column> columns,Schema schema ){
		Connection connection = databaseConnection.getConnection();
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().executeQuery(PostgreLocalSql.getViewSelect(databaseConnection, actualTableName));
			if(resultSet.next()){
				String selectScript = resultSet.getString(1) ;
				Select select = (Select)(new CCJSqlParserManager()).parse(new StringReader(selectScript));
				PlainSelect body=  (PlainSelect)select.getSelectBody();
				Map<String, Table> tableMap = new HashMap<String, Table>();
				FromItem fromItem = body.getFromItem();
				parseFromItem(fromItem,tableMap);
				List joins = body.getJoins() ;
				if( joins != null ){
					for(int i=0,size=joins.size();i<size;i++ ){
						Join join =(Join) joins.get(i);
						parseFromItem(join.getRightItem(),tableMap);
					}
				}
				Map<String, Ietm> columnMap = new HashMap<String, Ietm>();
				List list = body.getSelectItems();
				for(int i =0,size =  list.size();i<size;i++ ){
					Ietm  ietm = new Ietm();
					SelectExpressionItem selectItem = (SelectExpressionItem)list.get(i);
					Expression e = selectItem.getExpression() ;
					if(e instanceof net.sf.jsqlparser.schema.Column){
						net.sf.jsqlparser.schema.Column col = (net.sf.jsqlparser.schema.Column)e;
						ietm.associateColumnName = col ;
						ietm.table = tableMap.get(col.getTable().getName()) ;
					}
					String alias = selectItem.getAlias();
					if( StringUtility.stringHasValue(alias) ){
						ietm.columnAlias = alias;
						columnMap.put(alias, ietm);
					}
					columnMap.put(ietm.associateColumnName.getColumnName(), ietm);
				}
				for(Column column :  columns){
					Ietm item = columnMap.get(column.getName());
					if(item != null && column instanceof GeneralTableColumn ){
						GeneralTableColumn tableColumn = (GeneralTableColumn) column;
						resultSet = connection.createStatement().executeQuery(PostgreLocalSql.getTableCloumnComment(databaseConnection, item.table.getName(), item.associateColumnName.getColumnName()));
						if(resultSet.next()){
							tableColumn.setComment(resultSet.getString(1));
						}
						resultSet.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if( resultSet != null ){
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if( connection != null ){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 解析查询语句中from结构
	 * @param fromItem
	 * @param map
	 */
	private void parseFromItem(FromItem fromItem,Map<String, Table> map){
		if(fromItem instanceof Table ){
			parseFromTable((Table)fromItem,map);
		}else if(fromItem instanceof SubJoin ){
			parseFromSubJoin((SubJoin)fromItem,map );
		}else if(fromItem instanceof SubSelect ){
			parseFromSubSelect((SubSelect)fromItem );		
		}
	}

	/**
	 * 解析子查询
	 * @param subSelect
	 */
	private void parseFromSubSelect(SubSelect subSelect ){
		//subSelect.getSelectBody()
	}
	
	/**
	 * 解析查询表 
	 * @param table
	 * @param map
	 */
	private void parseFromTable(Table table ,Map<String, Table> map){
		String key = table.getAlias();
		if( StringUtility.stringHasValue(key ) ){
			map.put(key, table);
		}
		map.put(table.getName(), table);
	}

	/**
	 * 解析join查询
	 * @param subJoin
	 * @param map
	 */
	private void parseFromSubJoin(SubJoin subJoin,Map<String, Table> map ){
		parseFromItem(subJoin.getLeft(),map); //左边表达式
		Join join =  subJoin.getJoin();
		if(join != null ){
			parseFromItem(join.getRightItem(),map);
		}
	}

	private class Ietm{
		Table table ;
		String columnAlias;
		net.sf.jsqlparser.schema.Column associateColumnName;
	}	
}
