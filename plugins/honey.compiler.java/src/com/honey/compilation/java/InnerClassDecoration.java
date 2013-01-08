package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;

/**
 * 
 * inner java 类的修饰
 * 
 * @author Administrator
 *
 */
public class InnerClassDecoration extends ClassDecoration {

	/**
	 * 构造函数
	 */
	public InnerClassDecoration(){
		super();
	}
	
	/**
	 * 
	 * @param visibility
	 * @throws CompilationException
	 */
	public InnerClassDecoration(JavaVisibility visibility) throws CompilationException{
		super(visibility);
	}
	
	/**
	 * 
	 * @param visibility
	 * @param javatype
	 * @throws CompilationException
	 */
	public InnerClassDecoration(JavaVisibility visibility, JavaType javatype) throws CompilationException{
		super(visibility, javatype);
	}
	
	/**
	 * 
	 * @param isStatic
	 * @param isFinal
	 * @throws CompilationException
	 */
	public InnerClassDecoration(boolean isStatic , boolean isFinal) throws CompilationException{
		super(isStatic, isFinal);
	}
	
	/**
	 * 
	 * @param visibility
	 * @param isStatic
	 * @param isFinal
	 * @throws CompilationException
	 */
	public InnerClassDecoration(JavaVisibility visibility,boolean isStatic , boolean isFinal) throws CompilationException{
		super(visibility, null, isStatic, isFinal);
	}
	
	/**
	 * 
	 * @param visibility
	 * @param javatype
	 * @param isStatic
	 * @param isFinal
	 * @throws CompilationException
	 */
	public InnerClassDecoration(JavaVisibility visibility,JavaType javatype,boolean isStatic , boolean isFinal) throws CompilationException{
		super(visibility, javatype, isStatic, isFinal);
	}
	
	/**
	 * 设定java类的类型,如果设定为虚类那么不能使用final修饰,将final强制修成false
	 * @param javaType
	 */
	public void setJavaType(JavaType javaType)throws CompilationException {
		if(javaType != null ){
			super.javaType = javaType;
			if( javaType.equals( JavaType.ABSTRACTCLASS) )
				setFinal(false);
		}else{
			super.javaType = JavaType.CLASS;
		}
	}

}
