package com.honey.core.generator;

import com.honey.compilation.java.Method;
import com.honey.core.types.FullyQualifiedJavaType;

interface MvcCompilation  extends CodeCompilation{
	
	/**
	 * 
	 * 通常情况下java web网站都是采用MVC三层结构开发, 这三层分别是 持久层 , 业务层 和 控制层.
	 * 这三层的调用关系是: 持久层 -> 业务层 -> 控制层,本代码生成器也是按照这个逻辑生成代码,
	 * 本方法就是返回当前生成代码属于那一层的代码.
	 * 返回当前编译类所在的层
	 * @return
	 */
	public LayerKind mvcLayerKind();
	
	/**
	 * 返回当前层类型名称
	 * @param introspectSchema
	 * @return
	 */
	public FullyQualifiedJavaType layerType();
	
	/**
	 * 返回当前层向上层提供的公开方法列表
	 * @param introspectSchema
	 * @param introspectSchemaColumn
	 * @return
	 */
	public Method[] layerPublicMethod();
	
	/**
	 * 需要导入的类
	 * @return
	 */
	public FullyQualifiedJavaType[] requestClass();
	
	/**
	 * MVC 三层架构名称
	 * @author Administrator
	 *
	 */
	public enum LayerKind {
		/**
		 * 持久层
		 */
		PRESENTATION_LAYER,
		
		/**
		 * 业务层
		 */
		BUSINESS_LAYER,
		
		/**
		 * 控制层
		 */
		ACTION_LAYER,
		
		;
	}
}
