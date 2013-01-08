package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;
import com.honey.core.types.ExceptionFullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

public class TestMethod extends BaseTestCase {

	public void test0() throws CompilationException{
		Clazz clazz = createClass();
		
		Method method = new Method("method",new  MethodDecoration(JavaVisibility.PRIVATE));
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/privateStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test1() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@Deprecated@OverridepublicStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);	
	}
	
	public void test2() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PROTECTED);
		
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@Deprecated@OverrideprotectedStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test3() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.DEFAULT);
		
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@Deprecated@OverrideStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test4() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setSynchronized(true);
		
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@Deprecated@OverridepublicsynchronizedStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test5() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setStatic(true);
		
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@Deprecated@OverridepublicstaticStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test6() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@Deprecated@OverridepublicfinalStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test7() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		d.setStatic(true);
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@Deprecated@OverridepublicstaticfinalStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test8() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		d.setStatic(true);
		d.setSynchronized(true);
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@Deprecated@OverridepublicsynchronizedstaticfinalStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test9() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		d.setStatic(true);
		Method method = new Method("method",d);
		
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*/@Deprecated@Overridepublicstaticfinalvoidmethod(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);	
	}
	
	public void test10() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		Method method = new Method("method",d);
		
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*/@Deprecated@Overridepublicfinalvoidmethod(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);	
	}
	
	
	public void test11() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setAbstract(true);
		d.setSynchronized(true);
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@Deprecated@OverridepublicsynchronizedStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	
	public void test12() throws CompilationException{
		Clazz clazz = createClass();
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
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@Deprecated@OverridepublicsynchronizedstaticStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test14() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setAbstract(true);
		d.setSynchronized(true);
		d.setStatic(true);
		d.setFinal(true);
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@Deprecated@OverridepublicsynchronizedstaticfinalStringmethod(){returnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}

	
	
	public void test15() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		Method method = new Method("method",d);
		method.setReturnType(JDKFullyQualifiedJavaType.getIntegerInstance());
		method.addAnnotation("Deprecated");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnInteger*/@DeprecatedpublicfinalIntegermethod(){//TODOAuto-generatedmethodstubreturn0;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);	
	}
	
	public void test16() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		Method method = new Method("method",d);
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnString*/@DeprecatedpublicfinalStringmethod(){//TODOAuto-generatedmethodstubreturnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);	
	}
	
	public void test17() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		Method method = new Method("method",d);
		method.setReturnType(JDKFullyQualifiedJavaType.getLongInstance());
		method.addAnnotation("Deprecated");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnLong*/@DeprecatedpublicfinalLongmethod(){//TODOAuto-generatedmethodstubreturn0L;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);	
	}
	
	public void test18() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		Method method = new Method("method",d);
		method.setReturnType(JDKFullyQualifiedJavaType.getDoubleInstance());
		method.addAnnotation("Deprecated");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnDouble*/@DeprecatedpublicfinalDoublemethod(){//TODOAuto-generatedmethodstubreturn0.0D;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);	
	}
	
	public void test19() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		Method method = new Method("method",d);
		method.setReturnType(JDKFullyQualifiedJavaType.getFloatInstance());
		method.addAnnotation("Deprecated");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnFloat*/@DeprecatedpublicfinalFloatmethod(){//TODOAuto-generatedmethodstubreturn0.0F;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);	
	}
	
	public void test20() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		Method method = new Method("method",d);
		method.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method.addAnnotation("Deprecated");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@returnjava.lang.Object*/@DeprecatedpublicfinalObjectmethod(){//TODOAuto-generatedmethodstubreturnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);	
	}
	
	
	public void test21() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		Method method = new Method("method",d);
		method.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method.addAnnotation("Deprecated");
		method.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method.addException(ExceptionFullyQualifiedJavaType.getIOExceptionInstance());
		method.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		
		clazz.addMethod(method);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.io.IOException;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/publicOmycwtoh(){//TODOAuto-generatedmethodstub;}/***@returnjava.lang.Object*@exception{@codejava.lang.Exception}*@exception{@codejava.io.IOException}*/@DeprecatedpublicfinalObjectmethod()throwsException,IOException{//TODOAuto-generatedmethodstubreturnnull;}}";
		nS = StringUtility.getStringHash(nS);
		
		printResult(s);	
	}
	
	public void test22() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		Method method = new Method("method",d);
		method.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method.addAnnotation("Deprecated");
		method.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		Parameter  par1= new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1");
		method.addParameter(par1);
		method.addParameter(par1);
		Parameter  par2= new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1");
		method.addParameter(par2);
		clazz.addMethod(method);

		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+clazz.getType().getShortName()+"{/***默认的构造函数**/public"+clazz.getType().getShortName()+"(){//TODOAuto-generatedmethodstub;}/***@parampar1{@codeString}*@returnjava.lang.Object*@seeString*@exception{@codejava.lang.Exception}*/@DeprecatedpublicfinalObjectmethod(Stringpar1)throwsException{//TODOAuto-generatedmethodstubreturnnull;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);	
	}
	
	public void test23() throws CompilationException{
		Clazz clazz = createClass();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		Method method = new Method("method",d);
		method.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method.addAnnotation("Deprecated");
		method.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		Parameter  par1= new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1");
		par1.setComment("参数注释");
		method.addParameter(par1);
		clazz.addMethod(method);
				
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@parampar1{@codeString}*@returnjava.lang.Object*@seeString*@exception{@codejava.lang.Exception}*/@DeprecatedpublicfinalObjectmethod(Stringpar1/*参数注释*/)throwsException{//TODOAuto-generatedmethodstubreturnnull;}}";
				    
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);	
	}
	
	
	public void test24() throws CompilationException{
		//函数重载
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		Method method1 = new Method("method",d);
		method1.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method1.addAnnotation("Deprecated");
		method1.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		Method method2 = new Method("method",d);
		method2.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method2.addAnnotation("Deprecated");
		method2.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		assertEquals(method1.hashCode(), method2.hashCode()) ;
		assertTrue(method1.equals(method2) );

		//函数重载跟可视范围没有关系
		method1 = new Method("method",new  MethodDecoration(JavaVisibility.PUBLIC));
		method1.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method1.addAnnotation("Deprecated");
		method1.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		method2 = new Method("method",new MethodDecoration(JavaVisibility.PRIVATE));
		method2.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method2.addAnnotation("Deprecated");
		method2.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		assertEquals(method1.hashCode(), method2.hashCode()) ;
		assertTrue(method1.equals(method2) );

		//函数重载跟返回值没有关系
		method1 = new Method("method",new  MethodDecoration(JavaVisibility.PUBLIC));
		method1.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method1.addAnnotation("Deprecated");
		method1.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		method2 = new Method("method",new MethodDecoration(JavaVisibility.PRIVATE));
		method2.setReturnType(JDKFullyQualifiedJavaType.getBooleanInstance());
		method2.addAnnotation("Deprecated");
		method2.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		assertEquals(method1.hashCode(), method2.hashCode()) ;
		assertTrue(method1.equals(method2) );
		
		//函数重载跟是否声明抛出异常没有关系
		method1 = new Method("method",new  MethodDecoration(JavaVisibility.PUBLIC));
		method1.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method1.addAnnotation("Deprecated");
		method1.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		method2 = new Method("method",new MethodDecoration(JavaVisibility.PRIVATE));
		method2.setReturnType(JDKFullyQualifiedJavaType.getBooleanInstance());
		method2.addAnnotation("Deprecated");
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		assertEquals(method1.hashCode(), method2.hashCode()) ;
		assertTrue(method1.equals(method2) );
		
		//函数重载跟可视范围没有关系
		method1 = new Method("method",new  MethodDecoration(JavaVisibility.PUBLIC));
		method1.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method1.addAnnotation("Deprecated");
		method1.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		method2 = new Method("method",new MethodDecoration(JavaVisibility.PRIVATE));
		method2.setReturnType(JDKFullyQualifiedJavaType.getBooleanInstance());
		method2.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		assertEquals(method1.hashCode(), method2.hashCode()) ;
		assertTrue(method1.equals(method2) );
		
		//函数重载跟方法体内容没有关系
		method1 = new Method("method",new  MethodDecoration(JavaVisibility.PUBLIC));
		method1.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method1.addAnnotation("Deprecated");
		method1.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		method2 = new Method("method",new MethodDecoration(JavaVisibility.PRIVATE));
		method2.setReturnType(JDKFullyQualifiedJavaType.getBooleanInstance());
		method2.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		method2.addBodyLine("int i=9;");
		assertEquals(method1.hashCode(), method2.hashCode()) ;
		assertTrue(method1.equals(method2) );
		
		//函数重载跟参数名称没有关系
		method1 = new Method("method",new  MethodDecoration(JavaVisibility.PUBLIC));
		method1.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method1.addAnnotation("Deprecated");
		method1.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		method2 = new Method("method",new MethodDecoration(JavaVisibility.PRIVATE));
		method2.setReturnType(JDKFullyQualifiedJavaType.getBooleanInstance());
		method2.addAnnotation("Deprecated");
		method2.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par2"));
		
		assertEquals(method1.hashCode(), method2.hashCode()) ;
		assertTrue(method1.equals(method2) );
		
		
		//函数重载跟参数类型有关系
		method1 = new Method("method",new  MethodDecoration(JavaVisibility.PUBLIC));
		method1.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method1.addAnnotation("Deprecated");
		method1.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		method2 = new Method("method",new MethodDecoration(JavaVisibility.PRIVATE));
		method2.setReturnType(JDKFullyQualifiedJavaType.getBooleanInstance());
		method2.addAnnotation("Deprecated");
		method2.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"par1"));
		
		assertFalse(method1.hashCode()==method2.hashCode());
		assertFalse(method1.equals(method2) );
		
		//函数重载跟参数个数有关系
		method1 = new Method("method",new  MethodDecoration(JavaVisibility.PUBLIC));
		method1.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method1.addAnnotation("Deprecated");
		method1.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		method2 = new Method("method",new MethodDecoration(JavaVisibility.PRIVATE));
		method2.setReturnType(JDKFullyQualifiedJavaType.getBooleanInstance());
		method2.addAnnotation("Deprecated");
		method2.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par2"));
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getObjectInstance(),"par2"));
		
		assertFalse(method1.hashCode()==method2.hashCode());
		assertFalse(method1.equals(method2) );
		
		
		//函数重载跟参数顺序有关系(参数类型相同 参数名称向同 参数顺序不同)
		method1 = new Method("method",new  MethodDecoration(JavaVisibility.PUBLIC));
		method1.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method1.addAnnotation("Deprecated");
		method1.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getObjectInstance(),"par2"));
		
		method2 = new Method("method",new MethodDecoration(JavaVisibility.PRIVATE));
		method2.setReturnType(JDKFullyQualifiedJavaType.getBooleanInstance());
		method2.addAnnotation("Deprecated");
		method2.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getObjectInstance(),"par2"));
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		assertFalse(method1.hashCode()==method2.hashCode());
		assertFalse(method1.equals(method2) );
		
		
		//函数重载跟函数名称有关系(如果函数名称不同是两个不一样的方法 )
		method1 = new Method("method1",new  MethodDecoration(JavaVisibility.PUBLIC));
		method1.setReturnType(JDKFullyQualifiedJavaType.getObjectInstance());
		method1.addAnnotation("Deprecated");
		method1.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method1.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));

		method2 = new Method("method2",new MethodDecoration(JavaVisibility.PRIVATE));
		method2.setReturnType(JDKFullyQualifiedJavaType.getBooleanInstance());
		method2.addAnnotation("Deprecated");
		method2.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method2.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par1"));
		
		assertFalse(method1.hashCode()==method2.hashCode());
		assertFalse(method1.equals(method2) );

	}
	
	public void test25() throws CompilationException{
		Clazz clazz = createClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		MethodDecoration decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setConstructor(true);
		Method method = new Method("method0",decoration);
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/****/publicmethod0(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test26() throws CompilationException{
		Clazz clazz = createClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		MethodDecoration decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setConstructor(true);
		decoration.setFinal(true);
		Method method = new Method("method0",decoration);
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/****/publicmethod0(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test27() throws CompilationException{
		Clazz clazz = createClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		MethodDecoration decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setConstructor(true);
		decoration.setStatic(true);
		Method method = new Method("method0",decoration);
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/****/publicmethod0(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test28() throws CompilationException{
		Clazz clazz = createClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		MethodDecoration decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setConstructor(true);
		decoration.setAbstract(true);
		Method method = new Method("method0",decoration);
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/****/publicmethod0(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test29() throws CompilationException{
		Clazz clazz = createClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		MethodDecoration decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		//decoration.setConstructor(true);
		//decoration.setAbstract(true);
		Method method = new Method("method0",decoration);
		method.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		method.addException(ExceptionFullyQualifiedJavaType.getIOExceptionInstance());
		method.addException(ExceptionFullyQualifiedJavaType.getRuntimeExceptionInstance());
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		System.out.println(s);
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.io.IOException;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*@exception{@codejava.lang.Exception}*@exception{@codejava.io.IOException}*@exception{@codejava.lang.RuntimeException}*/publicstaticfinalvoidmethod0()throwsException,IOException,RuntimeException{//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
}
