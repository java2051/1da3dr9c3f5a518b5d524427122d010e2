package com.honey.compilation.java.pattern.singleton;

import com.honey.compilation.java.BaseTestCase;
import com.honey.compilation.java.Clazz;
import com.honey.compilation.java.Field;
import com.honey.compilation.java.IfBlock;
import com.honey.compilation.java.JavaVisibility;
import com.honey.compilation.java.Method;

public class TestSingleton extends BaseTestCase {

	public void testCreateSingleton1() throws Exception{
		Clazz clazz = new Clazz(TEST_PACKAGE+"Singleton");
		addApacheLicenseComment(clazz);
		clazz.addClassComment("单例模式");
		
		Field field = new Field( "singleton", clazz.getType()); 
		field.getDecoration().setFinal(true);
		field.getDecoration().setStatic(true);
		field.setInitializationString("new Singleton()");
		clazz.addField(field);
		
		//Constructor method
		Method method = new Method(clazz.getType().getShortName());
		method.getDecoration().setConstructor(true);
		method.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		clazz.addMethod(method);
		
		//Instance method
		method = new Method("getInstance");
		method.setReturnType(clazz.getType());
		method.addBodyLine("return "+field.getName());
		method.getDecoration().setStatic(true);
		clazz.addMethod(method);
		
		//doSomething
		method = new Method("doSomething");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString();
		printResult(s);
		
		
		dynamicJavaCompiler(TEST_PACKAGE, "Singleton", s);
	}
	
	
	public void testCreateSingleton2() throws Exception{
		Clazz clazz = new Clazz(TEST_PACKAGE+"Singleton");
		addApacheLicenseComment(clazz);
		clazz.addClassComment("单例模式");
		
		Field field = new Field( "singleton", clazz.getType()); 
		field.getDecoration().setFinal(false);
		field.getDecoration().setStatic(true);
		clazz.addField(field);
		
		//Constructor method
		Method method = new Method(clazz.getType().getShortName());
		method.getDecoration().setConstructor(true);
		method.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		clazz.addMethod(method);
		
		//Instance method
		method = new Method("getInstance");
		method.getDecoration().setSynchronized(true);
		method.setReturnType(clazz.getType());
		IfBlock block = new IfBlock();
		block.addBodyLine(field.getName()+" = new Singleton()");
		block.setCondition("singleton == null");
		method.addBlocks(block);
		
		method.addBodyLine("return "+field.getName());
		method.getDecoration().setStatic(true);
		clazz.addMethod(method);
		
		//doSomething
		method = new Method("doSomething");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString();
		printResult(s);
		
		dynamicJavaCompiler(TEST_PACKAGE, "Singleton", s);
	}
}
