package com.honey.compilation.java;

/**
 * 修饰规则
 * @author Administrator
 *
 */
public interface DecorationRule {
	
	/**
	 * 类的修饰规则
	 * @param decoration
	 */
	void  classDecorationRule( Decoration decoration);
	
	/**
	 * field 修饰规则
	 * @param decoration
	 */
	void  fieldDecorationRule( Decoration decoration);
	
	/**
	 * 方法修饰规则
	 * @param decoration
	 */
	void  methodDecorationRule( Decoration decoration);
	
	/**
	 * 参数修饰规则
	 * @param decoration
	 */
	void parameterDecorationRule( Decoration decoration);
	
	
}
