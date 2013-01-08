package com.honey.compilation.java;

import java.util.List;

import com.honey.compilation.java.ClassDecoration.JavaType;
import com.honey.core.compiler.CompilationException;
import com.honey.core.types.FullyQualifiedJavaType;


public class CompilationJavaFactory {
	
	
	/**
	 * 创建接口
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static Interface getInterfaceInstance(FullyQualifiedJavaType type) throws CompilationException {
		Interface answer = new Interface(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.INTERFACE,false,false ));
		return answer;
	}

	/**
	 * 创建接口
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static Interface getInterfaceInstance(String type) throws CompilationException {
		Interface answer = new Interface(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.INTERFACE,false,false ));
		
		return answer;
	}

	/**
	 * 创建一般类(使用默认的构造函数)EX:<br/> public Example class { <br />
	 * <br />
	 * &nbsp;&nbsp;public Example (){<br />
	 * <br />
	 * &nbsp;&nbsp;}<br /> }<br />
	 * 
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static Clazz getGeneralTopLevelClass(
			FullyQualifiedJavaType type) throws CompilationException {
		Clazz answer = getTopLevelClassInstance(type);
		Method  constructor = getConstructorOfPublicInstance(answer);
		constructor.addBodyLine("// TODO Auto-generated method stub;");
		constructor.addJavaDocLine("默认的构造函数");
		return answer;
	}

	/**
	 * 创建一般类(使用默认的构造函数)EX:<br/> public Example class { <br />
	 * <br />
	 * &nbsp;&nbsp;public Example (){<br />
	 * <br />
	 * &nbsp;&nbsp;}<br /> }<br />
	 * 
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static Clazz getGeneralTopLevelClass(String type) throws CompilationException {
		Clazz answer = getTopLevelClassInstance(type);
		Method  constructor = getConstructorOfPublicInstance(answer);
		constructor.addBodyLine("// TODO Auto-generated method stub;");
		constructor.addJavaDocLine("默认的构造函数");
		return answer;
	}

	/**
	 * 创建一般类(使用带参数的构造函数)EX:<br/> public Example class { <br />
	 * <br />
	 * &nbsp;&nbsp;public Example (String parameter1,String parameter2){<br />
	 * <br />
	 * &nbsp;&nbsp;}<br /> }<br />
	 * 
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static Clazz getGeneralTopLevelClass(String type,
			Parameter... parameters) throws CompilationException {
		Clazz answer = getTopLevelClassInstance(type);
		getConstructorOfPublicInstance(answer, parameters);
		return answer;
	}

	/**
	 * 创建类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static Clazz getTopLevelClassInstance(
			FullyQualifiedJavaType type) throws CompilationException {
		Clazz answer = new Clazz(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,false,false ));
		return answer;
	}

	/**
	 * 创建类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static Clazz getTopLevelClassInstance(String type) throws CompilationException {
		Clazz answer = new Clazz(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,false,false ));
		return answer;
	}

	/**
	 * 创建虚类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static Clazz getTopLevelClassOfAbstractInstance(
			FullyQualifiedJavaType type) throws CompilationException {
		Clazz answer = new Clazz(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.ABSTRACTCLASS,false,false ));
		return answer;
	}

	/**
	 * 创建虚类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static Clazz getTopLevelClassOfAbstractInstance(String type) throws CompilationException {
		Clazz answer = new Clazz(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.ABSTRACTCLASS,false,false ));
		
		return answer;
	}

	/**
	 * 创建java下面没有static修饰的类(内部类才可以)
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public static Clazz getTopLevelClassOfStaticInstance(
			FullyQualifiedJavaType type) {
		return null;
	}

	/**
	 * 创建java下面没有static修饰的类(内部类才可以)
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public static Clazz getTopLevelClassOfStaticInstance(String type) {
		return null;
	}

	/**
	 * 创建static 修饰的类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static Clazz getTopLevelClassOfFinalInstance(
			FullyQualifiedJavaType type) throws CompilationException {
		Clazz answer = new Clazz(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,false,true ));
		
		return answer;
	}

	/**
	 * 创建static 修饰的类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static Clazz getTopLevelClassOfFinalInstance(String type) throws CompilationException {
		Clazz answer = new Clazz(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,false,false ));
		
		return answer;
	}

	/**
	 * 创建类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static InnerClass getInnerClassInstance(FullyQualifiedJavaType type) throws CompilationException {
		InnerClass answer = new InnerClass(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,false,false ));
		return answer;
	}

	/**
	 * 创建类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static InnerClass getInnerClassInstance(String type) throws CompilationException {
		InnerClass answer = new InnerClass(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,false,false ));
		return answer;
	}

	/**
	 * 创建虚类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static InnerClass getInnerClassOfAbstractInstance(
			FullyQualifiedJavaType type) throws CompilationException {
		InnerClass answer = new InnerClass(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.ABSTRACTCLASS,false,false ));
		return answer;
	}

	/**
	 * 创建虚类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static InnerClass getInnerClassOfAbstractInstance(String type) throws CompilationException {
		InnerClass answer = new InnerClass(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.ABSTRACTCLASS,false,false ));
		return answer;
	}

	/**
	 * 创建java下面没有static修饰的类(内部类才可以)
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static InnerClass getInnerClassOfStaticInstance(
			FullyQualifiedJavaType type) throws CompilationException {
		InnerClass answer = new InnerClass(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,false ));
		
		return answer;
	}

	/**
	 * 创建java下面没有static修饰的类(内部类才可以)
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static InnerClass getInnerClassOfStaticInstance(String type) throws CompilationException {
		InnerClass answer = new InnerClass(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,false ));
		
		return answer;
	}

	/**
	 * 创建final 修饰的类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static InnerClass getInnerClassOfFinalInstance(
			FullyQualifiedJavaType type) throws CompilationException {
		InnerClass answer = new InnerClass(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,false,true ));
		
		return answer;
	}

	/**
	 * 创建final 修饰的类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static InnerClass getInnerClassOfFinalInstance(String type) throws CompilationException {
		InnerClass answer = new InnerClass(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,false,true ));
		
		return answer;
	}

	/**
	 * 创建final 和 static修饰的类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static InnerClass getInnerClassOfFinalAndStaticInstance(
			FullyQualifiedJavaType type) throws CompilationException {
		InnerClass answer = new InnerClass(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,true ));
		
		return answer;
	}

	/**
	 * 创建final 和 static修饰的类
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws CompilationException 
	 */
	public static InnerClass getInnerClassOfFinalAndStaticInstance(String type) throws CompilationException {
		InnerClass answer = new InnerClass(type,new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,true ));
		
		return answer;
	}

	// ---

	/**
	 * 创建使用public修饰属性的字段
	 * 
	 * @param javaClass
	 *            类
	 * @param name
	 *            属性名称
	 * @param type
	 *            属性类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Field getFieldOfPublicInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType type) throws CompilationException {
		Field answer = new Field(name, type);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		compilation.addField(answer);
		return answer;
	}

	/**
	 * 创建使用public和static修饰属性的字段
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            属性名称
	 * @param type
	 *            属性类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Field getFieldOfStaticAndPublicInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType type) throws CompilationException {
		Field answer = new Field(name, type);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.getDecoration().setStatic(true);
		compilation.addField(answer);
		return answer;
	}

	/**
	 * 创建使用public和static和final修饰属性的字段
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            属性名称
	 * @param type
	 *            属性类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Field getFieldOfStaticAndPublicAndFinalInstance(
			JavaCompilation compilation, String name, FullyQualifiedJavaType type) throws CompilationException {
		Field answer = new Field(name, type);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.getDecoration().setStatic(true);
		answer.getDecoration().setFinal(true);
		compilation.addField(answer);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的字段
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            属性名称
	 * @param type
	 *            属性类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Field getFieldOfPrivateInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType type) throws CompilationException {
		Field answer = new Field(name, type);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		compilation.addField(answer);
		return answer;
	}

	/**
	 * 创建使用private和static修饰属性的字段
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            属性名称
	 * @param type
	 *            属性类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Field getFieldOfStaticAndPrivateInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType type) throws CompilationException {
		Field answer = new Field(name, type);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.getDecoration().setStatic(true);
		compilation.addField(answer);
		return answer;
	}

	/**
	 * 创建使用private和static和Final修饰属性的字段
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            属性名称
	 * @param type
	 *            属性类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Field getFinalOfStaticAndPrivateAndFieldInstance(
			JavaCompilation compilation, String name, FullyQualifiedJavaType type) throws CompilationException {
		Field answer = new Field(name, type);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.getDecoration().setStatic(true);
		answer.getDecoration().setFinal(true);
		compilation.addField(answer);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的字段
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            属性名称
	 * @param type
	 *            属性类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Field getFieldOfDefaultInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType type) throws CompilationException {
		Field answer = new Field(name, type);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		compilation.addField(answer);
		return answer;
	}

	// ---1

	/**
	 * 创建使用public修饰属性的方法(无传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @return
	 */
	public static Method getMethodOfPublicInstance(String name) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(无传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 */
	public static Method getMethodOfPublicInstance(String name,
			FullyQualifiedJavaType returnType) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(一个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 */
	public static Method getMethodOfPublicInstance(String name,
			FullyQualifiedJavaType returnType, String parameterName,
			String parameterType, String javadoc) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(多个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 */
	public static Method getMethodOfPublicInstance(String name,
			FullyQualifiedJavaType returnType, Parameter... parameters) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(无传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 */
	public static Method getMethodOfPublicInstance(String name,
			String returnType) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(一个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 */
	public static Method getMethodOfPublicInstance(String name,
			String returnType, String parameterName, String parameterType,
			String javadoc) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(多个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 */
	public static Method getMethodOfPublicInstance(String name,
			String returnType, Parameter... parameters) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(无传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPublicInstance(JavaCompilation compilation,
			String name) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(无传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPublicInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType returnType) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(一个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPublicInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType returnType,
			String parameterName, String parameterType, String javadoc) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(多个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPublicInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType returnType,
			Parameter... parameters) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(无传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPublicInstance(JavaCompilation compilation,
			String name, String returnType) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(一个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPublicInstance(JavaCompilation compilation,
			String name, String returnType, String parameterName,
			String parameterType, String javadoc) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用public修饰属性的方法(多个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPublicInstance(JavaCompilation compilation,
			String name, String returnType, Parameter... parameters) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		compilation.addMethod(answer);
		return answer;
	}

	// ---2
	/**
	 * 创建使用private修饰属性的方法(无传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @return
	 */
	public static Method getMethodOfPrivateInstance(String name) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(无传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 */
	public static Method getMethodOfPrivateInstance(String name,
			FullyQualifiedJavaType returnType) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(一个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 */
	public static Method getMethodOfPrivateInstance(String name,
			FullyQualifiedJavaType returnType, String parameterName,
			String parameterType, String javadoc) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(多个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 */
	public static Method getMethodOfPrivateInstance(String name,
			FullyQualifiedJavaType returnType, Parameter... parameters) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(无传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 */
	public static Method getMethodOfPrivateInstance(String name,
			String returnType) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(一个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 */
	public static Method getMethodOfPrivateInstance(String name,
			String returnType, String parameterName, String parameterType,
			String javadoc) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(多个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 */
	public static Method getMethodOfPrivateInstance(String name,
			String returnType, Parameter... parameters) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(无传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPrivateInstance(JavaCompilation compilation,
			String name) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(无传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPrivateInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType returnType) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(一个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPrivateInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType returnType,
			String parameterName, String parameterType, String javadoc) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(多个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPrivateInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType returnType,
			Parameter... parameters) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(无传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPrivateInstance(JavaCompilation compilation,
			String name, String returnType) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(一个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPrivateInstance(JavaCompilation compilation,
			String name, String returnType, String parameterName,
			String parameterType, String javadoc) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用private修饰属性的方法(多个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfPrivateInstance(JavaCompilation compilation,
			String name, String returnType, Parameter... parameters) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		compilation.addMethod(answer);
		return answer;
	}

	// ---3

	/**
	 * 创建使用default修饰属性的方法(无传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @return
	 */
	public static Method getMethodOfDefaultInstance(String name) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(无传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 */
	public static Method getMethodOfDefaultInstance(String name,
			FullyQualifiedJavaType returnType) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(一个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 */
	public static Method getMethodOfDefaultInstance(String name,
			FullyQualifiedJavaType returnType, String parameterName,
			String parameterType, String javadoc) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(多个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 */
	public static Method getMethodOfDefaultInstance(String name,
			FullyQualifiedJavaType returnType, Parameter... parameters) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(无传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 */
	public static Method getMethodOfDefaultInstance(String name,
			String returnType) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(一个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 */
	public static Method getMethodOfDefaultInstance(String name,
			String returnType, String parameterName, String parameterType,
			String javadoc) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(多个传入参数)
	 * 
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 */
	public static Method getMethodOfDefaultInstance(String name,
			String returnType, Parameter... parameters) {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(无传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfDefaultInstance(JavaCompilation compilation,
			String name) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(无传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfDefaultInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType returnType) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(一个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfDefaultInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType returnType,
			String parameterName, String parameterType, String javadoc) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(多个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfDefaultInstance(JavaCompilation compilation,
			String name, FullyQualifiedJavaType returnType,
			Parameter... parameters) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(无传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfDefaultInstance(JavaCompilation compilation,
			String name, String returnType) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(一个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfDefaultInstance(JavaCompilation compilation,
			String name, String returnType, String parameterName,
			String parameterType, String javadoc) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		answer.addParameter(parameterName, parameterType, javadoc);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 创建使用default修饰属性的方法(多个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param name
	 *            方法名称
	 * @param returnType
	 *            返回类型
	 * @param parameters
	 *            传入参数名称
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getMethodOfDefaultInstance(JavaCompilation compilation,
			String name, String returnType, Parameter... parameters) throws CompilationException {
		Method answer = new Method(name);
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.setReturnType(returnType);
		for (Parameter parameter : parameters)
			answer.addParameter(parameter);
		compilation.addMethod(answer);
		return answer;
	}

	// ---

	/**
	 * 根据类创建该类的构造函数, 使用public修饰(没有传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getConstructorOfPublicInstance(JavaCompilation compilation) throws CompilationException {
		Method answer = new Method(compilation.getType().getShortName());
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.getDecoration().setConstructor(true);

		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 根据类创建该类的构造函数, 使用public修饰(一个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getConstructorOfPublicInstance(JavaCompilation compilation,
			String parameterName, String parameterType, String javadoc) throws CompilationException {
		Method answer = new Method(compilation.getType().getShortName());
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.getDecoration().setConstructor(true);
		answer.addParameter(parameterName, parameterType, javadoc);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 根据类创建该类的构造函数, 使用public修饰(多个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param parameters
	 *            传入参数
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getConstructorOfPublicInstance(JavaCompilation compilation,
			Parameter... parameters) throws CompilationException {
		Method answer = new Method(compilation.getType().getShortName());
		answer.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		answer.getDecoration().setConstructor(true);
		if (parameters != null)
			for (Parameter parameter : parameters)
				answer.addParameter(parameter);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 根据类创建该类的构造函数, 使用private修饰(没有传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getConstructorOfPrivateInstance(JavaCompilation compilation) throws CompilationException {
		Method answer = new Method(compilation.getType().getShortName());
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.getDecoration().setConstructor(true);

		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 根据类创建该类的构造函数, 使用private修饰(一个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getConstructorOfPrivateInstance(JavaCompilation compilation,
			String parameterName, String parameterType, String javadoc) throws CompilationException {
		Method answer = new Method(compilation.getType().getShortName());
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.getDecoration().setConstructor(true);
		answer.addParameter(parameterName, parameterType, javadoc);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 根据类创建该类的构造函数, 使用private修饰(多个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param parameters
	 *            传入参数
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getConstructorOfPrivateInstance(JavaCompilation compilation,
			Parameter... parameters) throws CompilationException {
		Method answer = new Method(compilation.getType().getShortName());
		answer.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		answer.getDecoration().setConstructor(true);
		if (parameters != null)
			for (Parameter parameter : parameters)
				answer.addParameter(parameter);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 根据类创建该类的构造函数, 使用default修饰(没有传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getConstructorOfDefaultInstance(JavaCompilation compilation) throws CompilationException {
		Method answer = new Method(compilation.getType().getShortName());
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.getDecoration().setConstructor(true);

		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 根据类创建该类的构造函数, 使用default修饰(一个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param parameterName
	 *            传入参数名称
	 * @param parameterType
	 *            传入参数类型
	 * @param javadoc
	 *            传入参数注释
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getConstructorOfDefaultInstance(JavaCompilation compilation,
			String parameterName, String parameterType, String javadoc) throws CompilationException {
		Method answer = new Method(compilation.getType().getShortName());
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.getDecoration().setConstructor(true);
		answer.addParameter(parameterName, parameterType, javadoc);
		compilation.addMethod(answer);
		return answer;
	}

	/**
	 * 根据类创建该类的构造函数, 使用default修饰(多个传入参数)
	 * 
	 * @param compilation
	 *            类
	 * @param parameters
	 *            传入参数
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getConstructorOfDefaultInstance(JavaCompilation compilation,
			Parameter... parameters) throws CompilationException {
		Method answer = new Method(compilation.getType().getShortName());
		answer.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		answer.getDecoration().setConstructor(true);
		if (parameters != null)
			for (Parameter parameter : parameters)
				answer.addParameter(parameter);
		compilation.addMethod(answer);
		return answer;
	}

	// ---
	/**
	 * 通过类的属性创建构造函数(非常使用于java bean的构造函数,如果使用final修饰的字段不会生成)
	 * 
	 * @param compilation
	 * @return
	 * @throws CompilationException 
	 */
	public static Method getConstructorFromFieldOfPublicInstance(
			JavaCompilation compilation) throws CompilationException {
		Method answer =null; 
		List<Field> fields = compilation.getFields();
		if (fields.size() > 0) {
			Parameter parameter = null;
			Parameter[] parameters = new Parameter[fields.size()];
			for (int i = 0, size = fields.size(); i < size; i++) {
				Field field = fields.get(i);
				if( field.getDecoration().isFinal()  ) continue;
				parameter = new Parameter(field.getType(), field.getName());
				parameter.addJavaDocLine(field.getJavaDocLines().toString());
				parameter.getDecoration().setFinal(true);
				parameters[i] = parameter;
			}
			answer = getConstructorOfPublicInstance(compilation, parameters);
			for (Field field : fields) {
				if( field.getDecoration().isFinal()  ) continue;
				answer.addBodyLine("this . " + field.getName() + " = "
						+ field.getName());
			}
			answer.addJavaDocLine("带有参数的构造函数");
		}
		return answer;
	}

}
