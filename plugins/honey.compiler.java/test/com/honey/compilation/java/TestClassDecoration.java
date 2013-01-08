package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;
import com.honey.compilation.java.ClassDecoration.JavaType;

public class TestClassDecoration extends BaseTestCase{
	
	public void test0() throws CompilationException{
		ClassDecoration d = new ClassDecoration();
		int indentLevel =0;
		String s = d.compiledContent( indentLevel ).toString();
		assertEquals("class ", s);
		printResult(s);
		
		indentLevel =1;
		s = d.compiledContent( indentLevel ).toString();
		assertEquals("    "+ JavaKeyWord.CLASS, s);
		printResult(s);
	}
	
	
	public void test1() throws CompilationException{
		ClassDecoration d = new ClassDecoration(JavaVisibility.DEFAULT);
		int indentLevel =0;
		String s = d.compiledContent( indentLevel ).toString();
		assertEquals( JavaKeyWord.CLASS +"", s);
		printResult(s);
		
		d = new ClassDecoration(JavaVisibility.PUBLIC);
		s = d.compiledContent( indentLevel ).toString();
		assertEquals(JavaKeyWord.PUBLIC +""+ JavaKeyWord.CLASS +"", s);
		printResult(s);
		
		try {
			d = new ClassDecoration(JavaVisibility.PROTECTED);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		try {
			d = new ClassDecoration(JavaVisibility.PRIVATE);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	public void test2() throws CompilationException{
		ClassDecoration d = new ClassDecoration(JavaVisibility.PUBLIC);
		d.setStatic(true);
		d.setFinal(true);
		int indentLevel =0;
		String s = d.compiledContent( indentLevel ).toString();
		assertEquals(JavaKeyWord.PUBLIC +""+JavaKeyWord.STATIC+ JavaKeyWord.FINAL+ JavaKeyWord.CLASS +"", s);
		printResult(s);
	}
	
	public void test3() throws CompilationException{
		ClassDecoration d = new ClassDecoration(JavaVisibility.PUBLIC);
		d.setStatic(true);
		d.setFinal(true);
		d.setJavaType(JavaType.ABSTRACTCLASS);
		int indentLevel =0;
		String s = d.compiledContent( indentLevel ).toString();
		assertEquals(JavaKeyWord.PUBLIC +""+JavaKeyWord.STATIC+ JavaKeyWord.ABSTRACT  + JavaKeyWord.CLASS +"", s);
		printResult(s);
	}
	
	public void test4() throws CompilationException{
		ClassDecoration d = new ClassDecoration(JavaVisibility.PUBLIC);
		d.setStatic(false);
		d.setFinal(true);
		d.setJavaType(JavaType.ABSTRACTCLASS);
		int indentLevel =0;
		String s = d.compiledContent( indentLevel ).toString();
		assertEquals(JavaKeyWord.PUBLIC +""+JavaKeyWord.ABSTRACT  + JavaKeyWord.CLASS +"", s);
		printResult(s);
	}
	
	public void test5() throws CompilationException{
		ClassDecoration d = new ClassDecoration(JavaVisibility.PUBLIC,null);
		d.setStatic(false);
		d.setFinal(true);
		d.setJavaType(JavaType.ABSTRACTCLASS);
		int indentLevel =0;
		String s = d.compiledContent( indentLevel ).toString();
		assertEquals(JavaKeyWord.PUBLIC +""+JavaKeyWord.ABSTRACT  + JavaKeyWord.CLASS +"", s);
		printResult(s);
	}
	
	public void test6() throws CompilationException{
		ClassDecoration d = new ClassDecoration(JavaVisibility.PUBLIC,null,true,true);
		d.setJavaType(JavaType.ABSTRACTCLASS);
		int indentLevel =0;
		String s = d.compiledContent( indentLevel ).toString();
		assertEquals(JavaKeyWord.PUBLIC +""+JavaKeyWord.STATIC  +JavaKeyWord.ABSTRACT  + JavaKeyWord.CLASS +"", s);
		printResult(s);
	}
}
