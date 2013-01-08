package com.honey.core;

public final class PluginConstent {
	
	/** 获取数据库结构参数 */
	public static final String EXTENSION_INTROSPECT_SCHEMA = "IntrospectSchema" ;
	
	/** 获取存储过程参数插入点 */
	public static final String EXTENSION_INTROSPECT_PROCEDURE_PARAMETER="IntrospectProcedureParameter" ; 
	
	/** 获取数据库列的插入点 */
	public static final String EXTENSION_INTROSPECT_TABLE_COLUMN="IntrospectTableColumn" ; 
	
	/** 代码生成插入点 */
	public static final String EXTENSION_CODE_GENERATOR="CodeGenerator" ; 

	/** 持久层插入点 */
	public static final String EXTENSION_PRESENTATION_LAYER="PresentationLayer" ; 
	
	/** 业务插入点 */
	public static final String EXTENSION_BUSINESS_LAYER="BusinessLayer" ; 
	
	
	/** java 名称转换插入点 */
	public static final String EXTENSION_JAVA_NAME_CALCULATOR = "JavaNameCalculator" ;
	
	/** 数据库名称映射到java名称转换插入点 */
	public static final String EXTENSION_MAPPING_CALCULATOR = "MappingCalculator" ;
	
	/** 数据库列处理器插入点 */
	public static final String EXTENSION_COLUMN_PROCESSOR="ColumnProcessor";
	
	/** 存储插件入点 */
	public static final String EXTENSION_COMPILATION_WRITER="CompilationWriter";
	
	/** 回调插入点 */
	public static final String EXTENSION_CALLBACK_COMPILATION="CallbackCompilation";
	
	/** 文件格式化插入点  */
	public static final String EXTENSION_CODE_FORMATER="CodeFormater";
	
	/** 通知插入点  */
	public static final String EXTENSION_Notify = "Notify";

	/** 计算代码特征值插入点  */
	public static final String SOURCE_CODE_FEATURE="SourceCodeFeature";
	
}
