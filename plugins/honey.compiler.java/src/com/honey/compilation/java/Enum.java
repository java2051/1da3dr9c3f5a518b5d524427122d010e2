package com.honey.compilation.java;

import com.honey.compilation.java.ClassDecoration.JavaType;
import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.compiler.CompilationException;
import com.honey.core.types.FullyQualifiedJavaType;

/**
 * 枚举类
 * @author Administrator
 *
 */
public class Enum extends InnerEnum {
	
	/**
	 * 构造函数
	 * @param type 类名称的类型
	 * @throws CompilationException
	 */
	public Enum(FullyQualifiedJavaType type)
			throws CompilationException {
		super(type);
	}

	/**
	 * 构造函数
	 * @param type 类名称的类型
	 * @throws CompilationException
	 */
	public Enum(String type) throws CompilationException {
		super(type);
	}

	/**
	 * 构造函数
	 * @param type 类名称的类型
	 * @param classDecoration 类的修饰
	 * @throws CompilationException
	 */
	public Enum(FullyQualifiedJavaType type, ClassDecoration classDecoration) throws CompilationException {
		super(type, classDecoration);
	}

	/**
	 * 构造函数
	 * @param type 类名称的类型
	 * @param classDecoration 类的修饰
	 * @throws CompilationException
	 */
	public Enum(String type, ClassDecoration classDecoration) throws CompilationException {
		super(type, classDecoration);
	}

	protected void classDecorationRule() throws CompilationException{
		ClassDecoration classDecoration = getClassDecoration();
		classDecoration.setJavaType(JavaType.ENUM); 
		classDecoration.setFinal(false);
		classDecoration.setStatic(false);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.InnerEnum#compiledContent()
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
