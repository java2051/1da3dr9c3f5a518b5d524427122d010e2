package com.honey.core.types;

import com.honey.core.types.translator.MysqlJdbcTypeNameTranslator;
import com.honey.core.types.translator.PostgreJdbcTypeNameTranslator;
import com.honey.core.types.translator.JdkJdbcTypeNameTranslator;
import com.honey.core.types.translator.SQL92JdbcTypeNameTranslator;
import com.honey.core.types.translator.SqlServerJdbcTypeNameTranslator;
import com.honey.core.utils.StringUtility;

/**
 * 数据库厂商
 * @author Administrator
 *
 */
public class Vendor {
	
	/** 数据库名称 */
	private VendorFactory vendor;
	
	/** 主版本号 */
	private int majorVersion;
	
	/** 次版本号 */
	private int minorVersion;
	
	/**
	 * 构造函数
	 * @param vendor 数据库厂商的名称
	 * @param majorVersion 主版本号
	 */
	public Vendor(String vendor ,int majorVersion  ){
		this(vendor, majorVersion, 0);
	}
	
	/**
	 * 构造函数
	 * @param vendor 数据库厂商的名称
	 * @param majorVersion 主版本号
	 * @param minorVersion 次版本号
	 */
	public Vendor(String vendor ,int majorVersion , int minorVersion ){
		this.vendor = VendorFactory.getVendorFactory(vendor);
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
	}
	
	/**
	 * 获取数据库厂商
	 * @return
	 */
	public VendorFactory getVendor() {
		return this.vendor;
	}
	
	/**
	 * 获取数据库主版本号
	 * @return
	 */
	public int getMajorVersion() {
		return this.majorVersion;
	}

	/**
	 * 获取数据次版本号
	 * @return
	 */
	public int getMinorVersion() {
		return this.minorVersion;
	}
	
	/**
	 * 获取数据库类型转换器
	 * @return
	 */
	public JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
		return this.vendor.getJdbcTypeNameTranslator();
	}
	
	@Override
	public String toString() {
		return this.vendor.getName() + "-"+this.majorVersion+"."+this.minorVersion;
	}
	
	enum VendorFactory{
		
		/**Oracle mysql */
		MYSQL("Mysql"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return new MysqlJdbcTypeNameTranslator();
			}
		},
		
		/** oracle */
		ORACLE("Oracle"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return null;
			}
		},
		
		/** sql server(2000, 2005, 2008) */
		MS_SQL_SERVER("SQL Server"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return new SqlServerJdbcTypeNameTranslator();
			}
		},
		
		/** postgre  */
		POSTGRE("Postgre SQL"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return new PostgreJdbcTypeNameTranslator();
			}
		},
		
		/** db2 */
		DB2("DB2"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return null;
			}
		},
		
		/** hsql */
		HSQL("HSQL DB"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return null;
			}
		},
		
		/** Sybase */
		SYBASE("Sybase"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return new SqlServerJdbcTypeNameTranslator();
			}
		},
		
		/** SQLite */
		SQLITE("SQLite"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return null;
			}
		},
		
		/** Apache Derby */
		DERBY("Apache Derby"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				//derby的的数据类型用的就是jdk的定义的数据类型
				return new JdkJdbcTypeNameTranslator();
			}
		},
		
		/** Informix */
		INFORMIX("Informix"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return null;
			}
		},
		
		/** Firebird */
		FIREBIRD("Firebird"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return null;
			}
		},
		
		/** H2 Database Engine */
		H2("H2 Database Engine"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return null;
			}
		},
		
		/** MS Access */
		ACCESS("MS Access"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return null;
			}
		},
		
		/** SQL 92 */
		SQL92("SQL 92"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return new SQL92JdbcTypeNameTranslator();
			}
		},
		
		/** Other */
		OTHER("Other"){
			JdbcTypeNameTranslator getJdbcTypeNameTranslator(){
				return new JdkJdbcTypeNameTranslator();
			}
		},
		;

		/** 数据库厂商名称 */
		private final String name;
		
		/**
		 * 构造函数
		 * @param name
		 */
		private VendorFactory(String name ){
			this.name = name;
		}
		
		static VendorFactory getVendorFactory(String name){
			VendorFactory answer  = OTHER;
			if(StringUtility.stringHasValue(name)){
				name =  name.toLowerCase();
				if(name.indexOf("mysql")>=0 )answer =MYSQL;
				else if(name.indexOf("oracle")>=0 )answer =ORACLE;
				else if(name.indexOf("sql server")>=0 )answer =MS_SQL_SERVER;
				else if(name.indexOf("postgre")>=0 )answer =POSTGRE;
				else if(name.indexOf("db2")>=0 )answer =DB2;
				else if(name.indexOf("hql")>=0 )answer =HSQL;
				else if(name.indexOf("sybase")>=0 )answer =SYBASE;
				else if(name.indexOf("sqlite")>=0 )answer =SQLITE;
				else if(name.indexOf("derby")>=0 )answer =DERBY;
				else if(name.indexOf("informix")>=0 )answer =INFORMIX;
				else if(name.indexOf("firebird")>=0 )answer =FIREBIRD;
				else if(name.indexOf("h2")>=0 )answer =H2;
				else if(name.indexOf("access")>=0 )answer =ACCESS;
				else if(name.indexOf("sql92")>=0 )answer =SQL92;
				else if(name.indexOf("sql-92")>=0 )answer =SQL92;
				else if(name.indexOf("sql-3")>=0 )answer =SQL92;
				else if(name.indexOf("sql-2")>=0 )answer =SQL92;
				else if(name.indexOf("sql2")>=0 )answer =SQL92;
			//	else if(name.indexOf("sql2")>=0 )answer =SQL92;
				else answer =OTHER;
			}
			return answer;
		}
		
		/**
		 * 获取数据库厂商名称
		 * @return
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * 数据库类型转换器
		 * @return
		 */
		abstract JdbcTypeNameTranslator getJdbcTypeNameTranslator();
		
		@Override
		public String toString() {
			return this.name;
		}
	}
}
