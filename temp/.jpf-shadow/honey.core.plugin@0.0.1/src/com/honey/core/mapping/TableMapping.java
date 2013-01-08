package com.honey.core.mapping;

import java.util.Properties;

/**
 * 数据库映射
 * 
 * @author Administrator
 *
 */
public interface TableMapping {
	
	/**
	 * 获取数据库名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 其他的配置参数
	 */
	public Properties getPropertie();
	
	/**
	 * 返回映射种类
	 * @return
	 */
	public MappingKind  getMappingKind();
	
	/**
	 * 映射类型
	 * @author Administrator
	 *
	 */
	public enum MappingKind{
		
		/**
		 * 数据库表
		 */
		DATABASE_TABLE ,

		/**
		 * 其他数据结构
		 */
		OTHER_SCHEMA,
		
	}
}
