package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;

public class testMethodDecoration extends BaseTestCase{

	public void test0() throws CompilationException{
		MethodDecoration d = new MethodDecoration();
		
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals("", s);
		printResult(s);
		
		d = new MethodDecoration();
		
		indentLevel= 1;
		s =d.compiledContent(indentLevel).toString();
		assertEquals("    ", s);
		printResult(s);
	}
	public void test1() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString(), s);
		printResult(s);
		
		
		d = new MethodDecoration(JavaVisibility.PROTECTED);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PROTECTED.toString(), s);
		printResult(s);
		
		d = new MethodDecoration(JavaVisibility.PRIVATE);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PRIVATE.toString(), s);
		printResult(s);
	}
	
	public void test2() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.FINAL.toString(), s);
		printResult(s);
	}
	
	public void test3() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setStatic(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.STATIC.toString(), s);
		printResult(s);
	}
	
	public void test4() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setConstructor(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString(), s);
		printResult(s);
		
		d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setConstructor(true);
		d.setFinal(true);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString(), s);
		printResult(s);
		
		d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setConstructor(true);
		d.setFinal(true);
		d.setStatic(true);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString(), s);
		printResult(s);


		d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setConstructor(true);
		d.setFinal(true);
		d.setStatic(true);
		d.setAbstract(true);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString(), s);
		printResult(s);
		
		d = new MethodDecoration(JavaVisibility.PRIVATE);
		d.setConstructor(true);
		d.setFinal(true);
		d.setStatic(true);
		d.setAbstract(true);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PRIVATE.toString(), s);
		printResult(s);
		
		d = new MethodDecoration(JavaVisibility.PROTECTED);
		d.setConstructor(true);
		d.setFinal(true);
		d.setStatic(true);
		d.setAbstract(true);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PROTECTED.toString(), s);
		printResult(s);
		
		d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setConstructor(true);
		d.setFinal(true);
		d.setSynchronized(true);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString(), s);
		printResult(s);
	}
	
	public void test5() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setAbstract(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.ABSTRACT.toString(), s);
		printResult(s);
		
		d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setAbstract(true);
		d.setFinal(true);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.ABSTRACT.toString(), s);
		printResult(s);
		
		d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setAbstract(true);
		d.setStatic(true);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.ABSTRACT.toString(), s);
		printResult(s);
		
		d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setAbstract(true);
		d.setFinal(true);
		d.setStatic(true);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.ABSTRACT.toString(), s);
		printResult(s);
		
		d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setAbstract(true);
		d.setConstructor(true);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString(), s);
		printResult(s);
		
		d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setAbstract(true);
		d.setSynchronized(true);
		s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.ABSTRACT.toString(), s);
		printResult(s);
	}
	
	public void test6() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(false);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString(), s);
		printResult(s);
	}
	
	public void test7() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		d.setFinal(false);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString(), s);
		printResult(s);
	}
	
	public void test8() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setFinal(true);
		d.setFinal(false);
		d.setFinal(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.FINAL.toString(), s);
		printResult(s);
	}
	
	public void test9() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setStatic(false);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString(), s);
		printResult(s);
	}
	
	public void test10() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setStatic(true);
		d.setStatic(false);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString(), s);
		printResult(s);
	}
	
	public void test11() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setStatic(true);
		d.setStatic(false);
		d.setStatic(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.STATIC.toString(), s);
		printResult(s);
	}
	
	public void test12() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setConstructor(false);
		d.setStatic(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.STATIC.toString(), s);
		printResult(s);
	}
	
	public void test13() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setConstructor(true);
		d.setConstructor(false);
		d.setStatic(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.STATIC.toString(), s);
		printResult(s);
	}
	
	public void test14() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setConstructor(true);
		d.setConstructor(false);
		d.setStatic(true);
		d.setConstructor(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString() , s);
		printResult(s);
	}
	
	public void test15() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setConstructor(true);
		d.setConstructor(false);
		d.setStatic(true);
		d.setConstructor(true);
		d.setStatic(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString() , s);
		printResult(s);
	}
	
	public void test16() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setConstructor(true);
		d.setConstructor(false);
		d.setStatic(true);
		d.setConstructor(true);
		d.setStatic(true);
		d.setFinal( true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString() , s);
		printResult(s);
	}
	
	public void test17() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PUBLIC);
		d.setSynchronized(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PUBLIC.toString()+JavaKeyWord.SYNCHRONIZED.toString(), s);
		printResult(s);
	}
	
	public void test18() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PRIVATE);
		d.setSynchronized(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PRIVATE.toString()+JavaKeyWord.SYNCHRONIZED.toString(), s);
		printResult(s);
	}
	
	public void test19() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PROTECTED);
		d.setSynchronized(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PROTECTED.toString()+JavaKeyWord.SYNCHRONIZED.toString(), s);
		printResult(s);
	}
	public void test20() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.DEFAULT);
		d.setSynchronized(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.SYNCHRONIZED.toString(), s);
		printResult(s);
	}
	
	public void test21() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PROTECTED);
		d.setSynchronized(true);
		d.setStatic(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PROTECTED.toString()+JavaKeyWord.SYNCHRONIZED.toString()+JavaKeyWord.STATIC.toString(), s);
		printResult(s);
	}
	
	public void test22() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PROTECTED);
		d.setSynchronized(true);
		d.setFinal(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PROTECTED.toString()+JavaKeyWord.SYNCHRONIZED.toString()+JavaKeyWord.FINAL.toString(), s);
		printResult(s);
	}
	
	public void test23() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PROTECTED);
		d.setSynchronized(true);
		d.setFinal(true);
		d.setStatic(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PROTECTED.toString()+JavaKeyWord.SYNCHRONIZED.toString()+JavaKeyWord.STATIC.toString()+JavaKeyWord.FINAL.toString(), s);
		printResult(s);
	}
	
	public void test24() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PROTECTED);
		d.setSynchronized(false);
		d.setFinal(true);
		d.setStatic(true);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PROTECTED.toString()+JavaKeyWord.STATIC.toString()+JavaKeyWord.FINAL.toString(), s);
		printResult(s);
	}
	
	public void test25() throws CompilationException{
		MethodDecoration d = new MethodDecoration(JavaVisibility.PROTECTED);
		d.setSynchronized(true);
		d.setFinal(true);
		d.setStatic(false);
		int indentLevel=0 ;
		String s =d.compiledContent(indentLevel).toString();
		assertEquals(JavaKeyWord.PROTECTED.toString()+JavaKeyWord.SYNCHRONIZED.toString()+JavaKeyWord.FINAL.toString(), s);
		printResult(s);
	}
}
