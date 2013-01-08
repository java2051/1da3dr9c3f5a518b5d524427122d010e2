package com.honey.compilation.java;

import com.honey.compilation.java.ClassDecoration.JavaType;
import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.compiler.CompilationException;
import com.honey.core.types.FullyQualifiedJavaType;

/**
 * 顶级实现类
 * @author Administrator
 *
 */
public class Clazz extends InnerClass {
	
	/**
	 * 构造函数
	 * @param type 函数名称类型 
	 * @throws CompilationException
	 */
	public Clazz(FullyQualifiedJavaType type) throws CompilationException {
		super(type);
	}

	/**
	 * 构造函数
	 * @param type 函数名称类型
	 * @throws CompilationException
	 */
	public Clazz(String type) throws CompilationException {
		super(type);
	}

	/**
	 * 构造函数
	 * @param type 函数名称类型
	 * @param classDecoration 类的修饰
	 * @throws CompilationException
	 */
	public Clazz(FullyQualifiedJavaType type,ClassDecoration classDecoration) throws CompilationException {
		super(type, classDecoration);
	}
	
	/**
	 * 构造函数
	 * @param type 函数名称类型
	 * @param classDecoration 类的修饰
	 * @throws CompilationException
	 */
	public Clazz(String type,ClassDecoration classDecoration) throws CompilationException {
		super(type, classDecoration);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.AbstractJavaCompilation#setClassDecoration(com.honey.compilation.java.ClassDecoration)
	 */
	@Override
	public void setClassDecoration(ClassDecoration classDecoration) throws CompilationException {
		if(classDecoration != null){
			JavaType javaType = classDecoration.getJavaType();
			if( javaType != JavaType.CLASS && javaType != JavaType.ABSTRACTCLASS){
				classDecoration.setJavaType(JavaType.CLASS); //顶层类必须是 class类型
			}
			super.setClassDecoration(classDecoration);
			classDecorationRule();
		}
	}

    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#addMethod(com.honey.compilation.java.Method[])
     */
    @Override
	public void addMethod(Method... methods) {
    	if(methods == null ) return ;
		for(Method method : methods){
			if(method != null){
				if(this.getClassDecoration().getJavaType() != JavaType.ABSTRACTCLASS){
					method.getDecoration().setAbstract(false);
				}
				super.addMethod(method);
			}
		}
	}
	
	/**
	 * 类的修饰规则是: <br />
	 * <ul>
	 *   <li>只能使用 class关键字</li>
  	 *   <li>不能使static 关键字</li>
	 *   <li>可视范围只能用public和defaut关键字</li>
	 * </ul>
	 * @throws CompilationException
	 */
    @Override
	protected void classDecorationRule() throws CompilationException{
		ClassDecoration classDecoration = getClassDecoration();
		JavaType javaType = classDecoration.getJavaType();
		if( javaType != JavaType.CLASS && javaType != JavaType.ABSTRACTCLASS){
			classDecoration.setJavaType(JavaType.CLASS); //顶层类必须是 class类型
		}
		classDecoration.setStatic(false); //顶层类不能用static修饰
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.InnerClass#compiledContent()
	 */
	@Override
	public CharacterBuilder compiledContent() throws CompilationException {
		CharacterBuilder answer = CharacterBuilderFactory.createC16StringBuilder();
		classDecorationRule();

		JavaCompilationUtils.importedTypes(this);

		// 生成文件注释
		super.appendFormattedFileComments(answer);

		// 生成包
		super.appendPackageName(answer);

		// 生成静态包导入
		super.appendStaticImport(answer);

		// 生成包导入
		super.appendImport(answer);
		
		// 按照内部类的规则生成
		answer.append(super.compiledContent(0));
		
		// 生成尾部内容
		super.appendAfterBodyComments(answer);
		
		return answer;
	}
	
}
