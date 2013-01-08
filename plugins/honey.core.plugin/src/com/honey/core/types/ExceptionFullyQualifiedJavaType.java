package com.honey.core.types;


/**
 * 常用的类
 * @author Administrator
 *
 */
public class ExceptionFullyQualifiedJavaType extends FullyQualifiedJavaType{
	
	/**
	 * 通过class转换成java对象封装
	 * @param clazz
	 */
	public ExceptionFullyQualifiedJavaType(Class<?> clazz) {
		super(clazz.getName());
	}

	/**
	 * 通过完整的字符串包转换成java对象封装
	 * @param fullTypeSpecification
	 */
	public ExceptionFullyQualifiedJavaType(String fullTypeSpecification) {
		super(fullTypeSpecification);
	}	

	public static final FullyQualifiedJavaType getIOExceptionInstance() {
		
		return new FullyQualifiedJavaType("java.io.IOException"); 
	}

	public static final FullyQualifiedJavaType getExceptionInstance() {
		
		return new FullyQualifiedJavaType("java.lang.Exception"); 
	}
	
	public static final FullyQualifiedJavaType getRuntimeExceptionInstance() {
		
		return new FullyQualifiedJavaType("java.lang.RuntimeException"); 
	}
	
	public static final FullyQualifiedJavaType getSQLExceptionInstance() {
		
		return new FullyQualifiedJavaType("java.sql.SQLException"); 
	}
	
	public static final FullyQualifiedJavaType getNullPointerExceptionInstance() {
	
		return new FullyQualifiedJavaType("NullPointerException"); 
	}
	
	public static final FullyQualifiedJavaType getServletExceptionInstance() {
		
		return new FullyQualifiedJavaType("javax.servlet.ServletException"); 
	}
	
	
}

