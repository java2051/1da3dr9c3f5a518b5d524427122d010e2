package com.honey.core.types;


/**
 * 常用的类
 * @author Administrator
 *
 */
public class ServletFullyQualifiedJavaType extends FullyQualifiedJavaType{
	
	/**
	 * 通过class转换成java对象封装
	 * @param clazz
	 */
	public ServletFullyQualifiedJavaType(Class<?> clazz) {
		super(clazz.getName());
	}

	/**
	 * 通过完整的字符串包转换成java对象封装
	 * @param fullTypeSpecification
	 */
	public ServletFullyQualifiedJavaType(String fullTypeSpecification) {
		super(fullTypeSpecification);
	}	

	public static final FullyQualifiedJavaType getHttpServletInstance() {
		return new FullyQualifiedJavaType("javax.servlet.http.HttpServlet"); 
	}

	public static final FullyQualifiedJavaType getHttpServletRequestInstance() {
		return new FullyQualifiedJavaType("javax.servlet.http.HttpServletRequest"); 
	}

	public static final FullyQualifiedJavaType getHttpServletResponseInstance() {
		return new FullyQualifiedJavaType("javax.servlet.http.HttpServletResponse"); 
	}
	
	public static final FullyQualifiedJavaType getServletExceptionInstance() {
		
		return new FullyQualifiedJavaType("javax.servlet.ServletException"); 
	}
	
}

