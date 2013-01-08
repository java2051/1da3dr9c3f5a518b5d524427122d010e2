package com.honey.compilation.java.pattern.factory;

import com.honey.compilation.java.BaseTestCase;
import com.honey.compilation.java.Clazz;
import com.honey.compilation.java.Field;
import com.honey.compilation.java.JavaVisibility;
import com.honey.compilation.java.Method;
import com.honey.compilation.java.StaticBlock;
import com.honey.compilation.java.TryCatchBlock;
import com.honey.core.types.ExceptionFullyQualifiedJavaType;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;

public class TestFactory extends BaseTestCase{

	public void test0() {

		//printResult(s);
		
		//String s;
		try {
			String  s = createSingletonClass();
			dynamicJavaCompiler(TEST_PACKAGE, "Singleton", s);
			
			s = createSingletonFactory();
			dynamicJavaCompiler(TEST_PACKAGE, "SingletonFactory", s);
			printResult(s);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}
	
	private String createSingletonFactory() throws Exception {
		Clazz clazz = new Clazz( TEST_PACKAGE + "SingletonFactory");
		addApacheLicenseComment(clazz);
		Field field = new Field("singleton" ,new FullyQualifiedJavaType("Singleton"));
		field.getDecoration().setStatic(true);
		clazz.addField(field);
		StaticBlock staticBlock = new StaticBlock();
		clazz.addStaticBlocks(staticBlock);
		TryCatchBlock block = new TryCatchBlock();
		block.addBodyLine("Class cl= Class.forName(Singleton.class.getName())");
		block.addBodyLine("Constructor constructor=cl.getDeclaredConstructor()");
		block.addBodyLine("constructor.setAccessible(true)");
		block.addBodyLine("singleton = (Singleton)constructor.newInstance()" );
		block.addCatchCase(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		staticBlock.addBlocks(block);
		
		clazz.addImportedType(new FullyQualifiedJavaType("ava.lang.reflect.Constructor"));
		
		Method method = new Method("getSingleton");
		method.setReturnType("Singleton");
		method.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		method.getDecoration().setStatic(true);
		method.addBodyLine("return singleton ");
		clazz.addMethod(method);
		
		return clazz.compiledContent().toString();
	}
	
	
	private String createSingletonClass() throws Exception{
		Clazz clazz = new Clazz( TEST_PACKAGE + "Singleton");
		addApacheLicenseComment(clazz);
		Method method = new Method(clazz.getType().getShortName(),true);
		method.getDecoration().setVisibility(JavaVisibility.PRIVATE);
		clazz.addMethod(method);
		
		method = new Method("doSomething");
		method.addBodyLine("//业务处理");
		clazz.addMethod(method);

		String  s = clazz.compiledContent().toString();
		return s;
	}
	
}
