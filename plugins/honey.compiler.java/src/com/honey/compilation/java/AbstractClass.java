package com.honey.compilation.java;

import com.honey.compilation.java.ClassDecoration.JavaType;
import com.honey.core.compiler.CompilationException;
import com.honey.core.types.FullyQualifiedJavaType;

/**
 * 虚类
 * @author Administrator
 */
public class AbstractClass extends Clazz {

	/**
	 * 构造函数
	 * @param type 函数名称类型
	 * @throws CompilationException
	 */
	public AbstractClass(FullyQualifiedJavaType type) throws CompilationException {
		super(type);
		
	}
	
	/**
	 * 构造函数
	 * @param type 函数名称类型
	 * @throws CompilationException
	 */
	public AbstractClass(String type) throws CompilationException {
		super(type);
	}
	
	/**
	 * 构造函数
	 * @param type 函数名称类型
	 * @param classDecoration 类的修饰
	 * @throws CompilationException
	 */
	public AbstractClass(FullyQualifiedJavaType type,ClassDecoration classDecoration) throws CompilationException {
		super(type,classDecoration);
	}
	
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#addMethod(com.honey.compilation.java.Method[])
     */
    @Override
	public void addMethod(Method... methods) {
		for(Method method : methods){
			if(method != null){
				boolean b =  method.getDecoration().isAbstract(); 
				super.addMethod(method);
				method.getDecoration().setAbstract(b);
			}
		}
	}
    
	/**
	 * 构造函数
	 * @param type  函数名称类型
	 * @param classDecoration 类的修饰
	 * @throws CompilationException
	 */
	public AbstractClass(String type,ClassDecoration classDecoration) throws CompilationException {
		super(type,classDecoration);
	}
	
	/**
	 * 虚类修饰的规则是: <br />
	 * <ul>
	 *   <li>只能使用abstract class关键字</li>
	 *   <li>不能使static和final关键字</li>
	 *   <li>可视范围只能用public和defaut关键字</li>
	 * </ul>
	 * @throws CompilationException
	 */
	@Override
	protected void classDecorationRule() throws CompilationException{
		ClassDecoration classDecoration = getClassDecoration();
		classDecoration.setJavaType(JavaType.ABSTRACTCLASS);
		classDecoration.setStatic(false);
		classDecoration.setFinal(false);
	}
	
}
