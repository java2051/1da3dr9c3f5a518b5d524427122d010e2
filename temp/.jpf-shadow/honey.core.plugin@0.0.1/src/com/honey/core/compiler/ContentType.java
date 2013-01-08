package com.honey.core.compiler;

/**
 * 生成类的主要用途和内容
 * @author Administrator
 *
 */
public enum ContentType {
	
	/** 实体bean类 */ 
	JAVA_ENTITY_BEEN ,

	
	/** DAO 接口类  */
	JAVA_DAO_INTERFACE ,
	
	/** DAO 接口实现类 */
	JAVA_DAO_IMPLEMENTS ,

	/** base DAO 接口类  */
	JAVA_BASE_DAO,
	
	/** Service接口类 */
	JAVA_SERVICE_INTERFACE ,
	
	/** Service接口实现类 */
	JAVA_SERVICE_IMPLEMENTS ,
	
	/** acion 接口类 */
	JAVA_ACTION_INTERFACE ,

	/** acion 接口实现类 */
	JAVA_ACTION_IMPLEMENTS ,
	
	/** from表单类 */
	JAVA_FORM_ENTITY ,
	
	/** model */
	JAVA_MODEL ,
	
	/** xml 配置文件  */
	XML_CONFIGURETION ,
	
	/** spring 配置文件 */
	XML_SPRING_CONFIGURETION ,
	
	/** mybatis 配置文件 */
	XML_MYBATIS_CONFIGURETION ,
	
	/** mybatis 配置文件 */
	XML_MYBATIS_MAPPER_CONFIGURETION ,
	
	
	/** ibatis 配置文件 */
	XML_IBATIS_CONFIGURETION ,
	
	/** ibatis 配置文件 */
	XML_IBATIS_MAPPER_CONFIGURETION ,
	
	/** ibatis 配置文件 */
	XML_HIBERNATE_CONFIGURETION ,
	
	/** ibatis 配置文件 */
	XML_LOG4G_CONFIGURETION ,
	
	/** struts2 配置文件 */
	XML_STRUTS2_CONFIGURETION ,
	
	/** struts2 action 配置文件 */
	XML_STRUTS2_ACTION_CONFIGURETION ,
	
	/** properties 配置文件 */
	PROPERTIES_CONFIGURETION ,
	
	/** JSP文件 */
	JSP ,
	
	/** JS文件 */
	JS ,

	/** CSS文件 */
	CSS ,
	
	/** 为定义的类 */
	OTHER ,
	;
}
