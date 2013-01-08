package com.honey.core.calculator;

/**
 * 数据库字段映射到java的bean字段转换器
 * @author Administrator
 *
 */
public interface MappingCalculator extends Calculator {
	
	/**
	 * 数据库结构(表、视图、临时表、函数、存储过程)名称到java名称转换
	 * @param schemaName 数据库结构名称
	 * @return java名称
	 */
	public String schemaCalculator(String ...inputString);
	
	/**
	 * 数据库字段映射到javabean属性转换器
	 * @param columnName 数据库字段名称
	 * @return 映射到java的bean属性的名称
	 */
	public String mappingCalculator(String ...inputString);
	
	
}
