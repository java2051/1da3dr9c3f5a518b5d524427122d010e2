package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;
import com.honey.compilation.java.ClassDecoration.JavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

public class TestAbstractClass extends BaseTestCase{


	public void test0() throws CompilationException{
		AbstractClass clazz = new AbstractClass(super.TEST_PACKAGE+super.TEST_TOP_CLASS_NAME);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	public void test1() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setFinal(true);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test2() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setStatic(true);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test3() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setJavaType(JavaType.CLASS);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test4() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.DEFAULT);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;abstractclass"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test5() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PRIVATE);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;abstractclass"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test6() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PROTECTED);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;abstractclass"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test7() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		Method method = new Method("method0",new MethodDecoration(JavaVisibility.PUBLIC,false,false));
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{/***@return*/publicvoidmethod0(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test8() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		Method method = new Method("method0",new MethodDecoration(JavaVisibility.PUBLIC,true,false));
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{/***@return*/publicstaticvoidmethod0(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test9() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		Method method = new Method("method0",new MethodDecoration(JavaVisibility.PUBLIC,false,true));
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{/***@return*/publicfinalvoidmethod0(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test10() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		Method method = new Method("method0",new MethodDecoration(JavaVisibility.PUBLIC,true,true));
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{/***@return*/publicstaticfinalvoidmethod0(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test11() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		MethodDecoration decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setAbstract(true);
		Method method = new Method("method0",decoration);
		
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{/***@return*/publicabstractvoidmethod0();}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test12() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setAbstract(true);
		d.setSynchronized(true);
		d.setStatic(true);
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{/***@returnString*/@Deprecated@OverridepublicabstractStringmethod();}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test13() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		MethodDecoration decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setConstructor(true);
		//decoration.setAbstract(true);
		Method method = new Method(super.TEST_TOP_CLASS_NAME,decoration);
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{/****/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test14() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		MethodDecoration decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setConstructor(true);
		Method method = new Method(super.TEST_TOP_CLASS_NAME,decoration);
		clazz.addMethod(method);
		
		
		decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setConstructor(true);
		method = new Method(super.TEST_TOP_CLASS_NAME,decoration);
		clazz.addMethod(method);
		
		decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		method = new Method("method0",decoration);
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{/****/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstubreturn;}/***@return*/publicstaticfinalvoidmethod0(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test15() throws CompilationException{
		AbstractClass clazz = createAbstractClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		MethodDecoration decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setConstructor(true);
		Method method = new Method(super.TEST_TOP_CLASS_NAME,decoration);
		clazz.addMethod(method);
		
		decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setConstructor(true);
		method = new Method(super.TEST_TOP_CLASS_NAME,decoration);
		clazz.addMethod(method);
		
		decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		method = new Method("method0",decoration);
		clazz.addMethod(method);
		
		decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setAbstract(true);
		method = new Method("method1",decoration);
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{/****/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstubreturn;}/***@return*/publicstaticfinalvoidmethod0(){//TODOAuto-generatedmethodstubreturn;}/***@return*/publicabstractvoidmethod1();}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
}
