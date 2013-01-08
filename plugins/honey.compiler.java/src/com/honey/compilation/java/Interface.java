package com.honey.compilation.java;

import com.honey.compilation.java.ClassDecoration.JavaType;
import com.honey.core.compiler.CompilationException;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

/**
 * 接口类
 * @author Administrator
 *
 */
public class Interface extends Clazz{

	/**
	 * 构造函数
	 * @param type  接口类的名称类型
	 * @throws CompilationException
	 */
	public Interface(FullyQualifiedJavaType type) throws CompilationException {
		super(type);
	}
	
	/**
	 * 
	 * 构造函数
	 * @param type  接口类的名称类型
	 * @throws CompilationException
	 */
	public Interface(String type) throws CompilationException {
		super(type);
	}

	/**
	 * 构造函数
	 * @param type  接口类的名称类型
	 * @param classDecoration 接口类的修饰
	 * @throws CompilationException
	 */
	public Interface(FullyQualifiedJavaType type,ClassDecoration classDecoration) throws CompilationException {
		super(type, classDecoration);
	}
	
	/**
	 * 构造函数
	 * @param type 接口类的名称类型
	 * @param classDecoration 接口类的修饰
	 * @throws CompilationException
	 */
	public Interface(String type,ClassDecoration classDecoration) throws CompilationException {
		super(type, classDecoration);
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.AbstractJavaCompilation#addMethod(com.honey.compilation.java.Method[])
	 */
	@Override
	public void addMethod(Method... methods) {
		for(Method method  : methods){
			MethodDecoration decoration= method.getDecoration(); 
			if( decoration!= null ){
				decoration.setConstructor(false);
				decoration.setSynchronized(false);
				decoration.setStatic(false);
				decoration.setFinal(false);
				if( decoration.getVisibility().equals(JavaVisibility.PRIVATE) || 
						decoration.getVisibility().equals(JavaVisibility.PROTECTED ) ){
					decoration.setVisibility(JavaVisibility.DEFAULT);
		         }
			}
		}
		
		super.addMethod(methods);
	}


	/**
	 * 接口中的属性必须赋值,而且只能用 public 和default 修饰
	 */
	@Override
	public void addField(Field... fields) {
		for(Field field :fields ){
			String initialization = null;
			if( !StringUtility.stringHasValue(field.getInitializationString()) ){
				initialization = field.getType().getInitializationValue();
            }else{
            	initialization = field.getInitializationString();
            	//initialization = JavaCompilationUtils.getFormatType(field.getType(),initialization);
            	initialization =  field.getType().formatValue(initialization);
            }
        	field.setInitializationString(initialization);
        	
			if( field.getDecoration().getVisibility().equals(JavaVisibility.PRIVATE) || 
	            	field.getDecoration().getVisibility().equals(JavaVisibility.PROTECTED ) ){
	            	field.getDecoration().setVisibility(JavaVisibility.DEFAULT);
	         }
			super.addField(field);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.AbstractJavaCompilation#addStaticBlocks(com.honey.compilation.java.Block[])
	 */
	@Override
	public void addStaticBlocks(Block... blocks) {
		//接口中不能添加静态块
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.AbstractJavaCompilation#addSuperInterface(com.honey.core.types.FullyQualifiedJavaType[])
	 */
	@Override
	public void addSuperInterface(FullyQualifiedJavaType ...superInterface) {
		//接口只能继承.
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.AbstractJavaCompilation#addSuperInterface(java.lang.String[])
	 */
	@Override
	public void addSuperInterface(String ...superInterfaces) {
		//接口只能继承.
	}
	
	/**
	 * 接口的修饰的规则是:<br />
	 * <ul>
	 *   <li>只能使用interface关键字</li>
	 *   <li>不能使static和final关键字</li>
	 *   <li>可视范围只能用public和defaut关键字</li>
	 * </ul> 
	 * @throws CompilationException
	 */
	@Override
	protected void classDecorationRule() throws CompilationException{
		ClassDecoration classDecoration = getClassDecoration();
		classDecoration.setJavaType(JavaType.INTERFACE);
		classDecoration.setStatic(false);
		classDecoration.setFinal(false);
	}
	
}
