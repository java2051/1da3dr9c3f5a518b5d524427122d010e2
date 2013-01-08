package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

public class TestEnumField extends BaseTestCase{
	
	public void test0() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1",  s );
		assertEquals(ef.getAnnotations().size(), 0);
		assertEquals(ef.getJavaDocLines().size(), 0);

		assertEquals(ef.getName(), "ENUF1");
		assertNull(ef.getDecoration());
		printResult(s);
	}
	
	
	public void test1() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		
		int indentLevel =1; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("    ENUF1",  s );
		assertEquals(ef.getAnnotations().size(), 0);
		assertEquals(ef.getJavaDocLines().size(), 0);
		assertEquals(ef.getName(), "ENUF1");
		assertNull(ef.getDecoration());
		printResult(s);
	}
	
	public void test2() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		
		int indentLevel =2; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("        ENUF1",  s );
		assertEquals(ef.getAnnotations().size(), 0);
		assertEquals(ef.getJavaDocLines().size(), 0);
		assertEquals(ef.getName(), "ENUF1");
		assertNull(ef.getDecoration());
		printResult(s);
	}
	
	public void test3() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addAnnotation("@Deprecated");
		
		int indentLevel =1; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("    @Deprecated\r\n    ENUF1",  s );
		
		assertEquals(ef.getAnnotations().size(), 1);
		assertEquals(ef.getJavaDocLines().size(), 0);

		assertEquals(ef.getName(), "ENUF1");
		assertNull(ef.getDecoration());
		printResult(s);
	}

	public void test4() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addAnnotation("@Deprecated");
		ef.addAnnotation("NoNull");
		int indentLevel =1; 
		String s = ef.compiledContent(indentLevel).toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "@Deprecated@NoNullENUF1";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		assertEquals(ef.getAnnotations().size(), 2);
		assertEquals(ef.getJavaDocLines().size(), 0);

		assertEquals(ef.getName(), "ENUF1");
		assertNull(ef.getDecoration());
		printResult(s);
	}
	
	public void test5() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getLongInstance(),"E"),"9");

		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1( 9L )", s);
		printResult(s);
	}
	
	public void test6() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getLongInstance(),"E"),"9");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getLongInstance(),"F"),"90");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getLongInstance(),"G"),"8l");
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1( 9L, 90L, 8l )", s);
		printResult(s);
	}
	
	public void test7() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"E"),"9");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"F"),"90");

		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1( 9, 90 )", s);
		printResult(s);
	}
	
	public void test8() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getDoubleInstance(),"E"),"9");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getDoubleInstance(),"F"),"90");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getDoubleInstance(),"G"),"8d");
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1( 9D, 90D, 8d )", s);
		printResult(s);
	}
	
	public void test9() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getFloatInstance(),"E"),"9");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getFloatInstance(),"F"),"90");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getFloatInstance(),"G"),"8f");
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1( 9F, 90F, 8f )", s);
		printResult(s);
	}
	
	
	public void test10() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getShortInstance(),"E"),"9");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getShortInstance(),"F"),"90");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getShortInstance(),"G"),"(short)8");
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1( (short)9, (short)90, (short)8 )", s);
		printResult(s);
	}
	
	public void test11() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"E"),"9");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"F"),"-4");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"G"),"true");
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1( true, false, true )", s);
		printResult(s);
	}
	
	public void test12() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"E"),"9");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"F"),"-4");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"G"),"true");
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1( '9', '-', 't' )", s);
		printResult(s);
	}
	
	public void test13() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addJavaDocLine("测试注释1");
		ef.addJavaDocLine("测试注释2");
		
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("\r\n/** \r\n * 测试注释1\r\n * 测试注释2\r\n */\r\nENUF1", s);
		printResult(s);
	}
	
	public void test14() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.setDecoration(new Decoration());
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1",  s );
		assertEquals(ef.getAnnotations().size(), 0);
		assertEquals(ef.getJavaDocLines().size(), 0);
		assertEquals(ef.getName(), "ENUF1");
		assertNull(ef.getDecoration());
		printResult(s);
	}
	
	public void test15() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.setDecoration(null);
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1",  s );
		assertEquals(ef.getAnnotations().size(), 0);
		assertEquals(ef.getJavaDocLines().size(), 0);
		assertEquals(ef.getName(), "ENUF1");
		assertNull(ef.getDecoration());
		printResult(s);
	}

	public void test16() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addMethod(new Method("method"));
		ef.setDecoration(null);
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "ENUF1{/***@return*/publicvoidmethod(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test17() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addMethod(new Method("method"));
		ef.setDecoration(null);
		int indentLevel =1; 
		String s = ef.compiledContent(indentLevel).toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "ENUF1{/***@return*/publicvoidmethod(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test18() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addMethod(new Method("method1"));
		ef.addMethod(new Method("method2"));
		ef.addMethod(new Method("method3"));
		ef.setDecoration(null);
		int indentLevel =1; 
		String s = ef.compiledContent(indentLevel).toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "ENUF1{/***@return*/publicvoidmethod1(){//TODOAuto-generatedmethodstubreturn;}/***@return*/publicvoidmethod2(){//TODOAuto-generatedmethodstubreturn;}/***@return*/publicvoidmethod3(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test19() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		ef.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"E"),"9");
		ef.addMethod(new Method("method1"));

		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "ENUF1('9'){/***@return*/publicvoidmethod1(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	public void test20() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		Parameter parameter = new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"E") ;
		parameter.setComment("comment");
		ef.addParameter(parameter,"9");

		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1( '9'/*comment*/ )", s);
		printResult(s);
	}

	public void test21() throws CompilationException{
		EnumField ef = new EnumField("ENUF1");
		Parameter parameter = new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"E") ;
		parameter.setComment("comment");
		ef.addParameter(parameter,"9");
		ef.setComment("comment");
		
		int indentLevel =0; 
		String s = ef.compiledContent(indentLevel).toString() ;
		assertEquals("ENUF1( '9'/*comment*/ )/*comment*/", s);
		printResult(s);
	}
	
}
