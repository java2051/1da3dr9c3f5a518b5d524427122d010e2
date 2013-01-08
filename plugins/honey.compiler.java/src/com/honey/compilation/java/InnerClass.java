
package com.honey.compilation.java;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.compiler.CompilationException;
import com.honey.core.types.FullyQualifiedJavaType;

/**
 * 内部类 http://www.blogjava.net/raylong1982/archive/2007/10/24/155439.html
 * @author Administrator
 *
 */
public class InnerClass extends BaseClass {
	/**
	 * 构造函数
	 * @param type 类的名称类型
	 * @throws CompilationException
	 */
    public InnerClass(FullyQualifiedJavaType type) throws CompilationException {
		super(type);
	}
	
    /**
     * 构造函数
     * @param type 类的名称类型
     * @throws CompilationException
     */
	public InnerClass(String type) throws CompilationException {
		super(type);
	}
	
	/**
	 * 构造函数 
	 * @param type 类的名称类型
	 * @param classDecoration 类的修饰
	 * @throws CompilationException 
	 */
	public InnerClass(FullyQualifiedJavaType type,ClassDecoration classDecoration ) throws CompilationException {
		super(type, classDecoration);
	}
	
	/**
	 * 构造函数
	 * @param type 类的名称类型
	 * @param classDecoration 类的修饰
	 * @throws CompilationException
	 */
	public InnerClass(String type,ClassDecoration classDecoration) throws CompilationException {
		super(type, classDecoration);
	}
	
	 /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.AbstractJavaCompilation#begianCompilationClass(com.honey.core.builder.CharacterBuilder, int)
     */
	@Override
	protected void begianCompilationClass(CharacterBuilder cb, int indentLevel) {
		//不做任何事情
	}
    
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.AbstractJavaCompilation#endCompilationClass(com.honey.core.builder.CharacterBuilder, int)
	 */
	@Override
	protected void endCompilationClass(CharacterBuilder cb, int indentLevel) {
		//不做任何事情
	}

}
