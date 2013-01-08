package com.honey.core.calculator;


/**
 * java名称转换器
 * @author Administrator
 *
 */
public interface JavaNameCalculator extends Calculator{

	/**
	 * java类名称转换器
	 * @param str 指定字符串
	 * @return 转换成java类名称
	 */
	public String classNameCalculator(String ...inputString);
	
	/**
	 * java方法名称转换器
	 * @param str 指定字符串
	 * @return 转换成java方法名称
	 */
	public String methodNameCalculator(String ...inputString);
	
	/**
	 * java属性名称转换器 
	 * @param str 指定字符串
	 * @return 转换成java属性名称
	 */
	public String fieldNameCalculator(String ...inputString);
	
	/**
	 * java静态属性名称转换器
	 * @param str 指定字符串
	 * @return 转换成java静态属性名称
	 */
	public String staticFieldCalculator(String ...inputString);
	
	/**
	 * java变量名称转换器 
	 * @param str 指定字符串
	 * @return 转换成java属性名称
	 */
	public String variableNameCalculator(String ...inputString);
	
}
