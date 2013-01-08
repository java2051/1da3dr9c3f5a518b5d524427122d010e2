package com.honey.general.databases.datasource;

import com.honey.general.databases.DatabaseConnection;

public class ConnectionPoolFactory {
	
	private ConnectionPoolFactory(){}
	
	enum PoolType{
		
		/**
		 * 使用c3p0的PoolBackedDataSource创建对连接池
		 */
		C3P0_POOL_BACKED_CONNECTION_POOL((short)1),
		
		/**
		 * 使用c3p0的ComboPooledDataSource创建对连接池
		 */
		C3P0_COMBO_POOLED_CONNECTION_POOL((short)2),
		
		/**
		 * 使用系统内置的数据连接池
		 */
		DEFAULT_CONNECTION_POOL((short)3),
		
		
		/**
		 * 自动判定使用何种数据库连接池
		 */
		AUTO_CHOOSE_CONNECTION_POOL((short)0),
		
		;
		private final short type;
		
		private PoolType (short type){
			this.type = type;
		}
		public short getType() {
			return type;
		}
		
		
		
	}
	public static final ConnectionPool getConnectionPool(DatabaseConnection databaseConnection , PoolType poolType){
		ConnectionPool answer = null; 
		switch( poolType.getType() ){
			case 0:
				answer = getAutoChooseConnectionPool(databaseConnection);
				break ;
			case 1:
				answer = getPoolBackedDataSource(databaseConnection);
				break ;
			case 2:
				answer = getComboPooledDataSource(databaseConnection);
				break ;
			case 3 :
				answer = getDefaultConnectionPool(databaseConnection);
				break ;
			default :
				getAutoChooseConnectionPool(databaseConnection);
				break ;
		}
		
		return answer;
	} 
	
	private static final ConnectionPool getAutoChooseConnectionPool(DatabaseConnection databaseConnection ){
		ConnectionPool pool = new C3p0ConnectionPool(databaseConnection);
		if( ! pool.isEnable()){
			pool = new ConnectionPoolImpl(databaseConnection);
		}
		return pool;
	}
	
	
	/**
	 * 获取c3p0中ComboPooledDataSource连接池
	 * @param databaseConnection
	 * @return
	 */
	private static final ConnectionPool getPoolBackedDataSource(DatabaseConnection databaseConnection ){
		databaseConnection.getJdbcProperties().setProperty("usePoolBackedDataSource","true");
		return  new C3p0ConnectionPool(databaseConnection);
	}
	
	
	/**
	 * 获取c3p0中ComboPooledDataSource连接池
	 * @param databaseConnection
	 * @return
	 */
	private static final ConnectionPool getComboPooledDataSource(DatabaseConnection databaseConnection ){
		databaseConnection.getJdbcProperties().remove("usePoolBackedDataSource");
		return  new C3p0ConnectionPool(databaseConnection);
	}
	
	/**
	 * 获取默认的连接池
	 * @param databaseConnection
	 * @return
	 */
	private static final ConnectionPool getDefaultConnectionPool(DatabaseConnection databaseConnection ){
		return new ConnectionPoolImpl(databaseConnection);
	}
	
}
