package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;

public class testFieldDecoration extends BaseTestCase{
	
	public void test0() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString(), s);
		
		indentLevel = 1 ;
		s = d.compiledContent(indentLevel).toString();
		assertEquals("    " +JavaVisibility.PRIVATE.toString(), s);
		printResult(s);
	} 
	
	
	public void test1() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setFinal(true);
		
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString()+ JavaKeyWord.FINAL, s);
		printResult(s);
	}
	public void test2() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setFinal(true);
		d.setFinal(false);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString(), s);
		printResult(s);
	}
	
	
	public void test3() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setFinal(true);
		d.setFinal(false);
		d.setFinal(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString()+ JavaKeyWord.FINAL, s);
		printResult(s);
	}
	
	public void test4() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setStatic(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString()+ JavaKeyWord.STATIC, s);
		printResult(s);
	}
	
	public void test5() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setStatic(true);
		d.setStatic(false);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString(), s);
		printResult(s);
	}
	public void test6() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setStatic(true);
		d.setStatic(false);
		d.setStatic(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString()+ JavaKeyWord.STATIC, s);
		printResult(s);
	}

	public void test7() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setVolatile(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString()+ JavaKeyWord.VOLATILE, s);
		printResult(s);
	}
	
	public void test8() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setVolatile(true);
		d.setVolatile(false);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString(), s);
		printResult(s);
	}

	public void test9() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setVolatile(true);
		d.setVolatile(false);
		d.setVolatile(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString()+ JavaKeyWord.VOLATILE, s);
		printResult(s);
	}
	
	public void test10() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PUBLIC);
		d.setVolatile(true);
		d.setVolatile(false);
		d.setVolatile(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PUBLIC.toString()+ JavaKeyWord.VOLATILE, s);
		printResult(s);
	}
	
	public void test11() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PUBLIC);
		d.setStatic(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PUBLIC.toString()+ JavaKeyWord.STATIC, s);
		printResult(s);
	}
	
	public void test12() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PROTECTED);
		d.setStatic(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PROTECTED.toString()+ JavaKeyWord.STATIC, s);
		printResult(s);
	}
	
	public void test13() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PROTECTED);
		d.setFinal( true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PROTECTED.toString()+ JavaKeyWord.FINAL, s);
		printResult(s);
	}
	
	
	
	public void test14() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.DEFAULT);
		d.setStatic(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.DEFAULT.toString()+ JavaKeyWord.STATIC, s);
		printResult(s);
	}
	
	public void test15() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.DEFAULT);
		d.setVolatile(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.DEFAULT.toString()+ JavaKeyWord.VOLATILE, s);
		printResult(s);
	}
	
	public void test16() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.DEFAULT);
		d.setFinal( true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.DEFAULT.toString()+ JavaKeyWord.FINAL, s);
		printResult(s);
	}
	
	public void test17() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setFinal( true);
		d.setVolatile(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString()+ JavaKeyWord.VOLATILE, s);
		printResult(s);
	}
	
	public void test18() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setFinal( false);
		d.setVolatile(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString()+ JavaKeyWord.VOLATILE, s);
		printResult(s);
	}
	
	
	public void test19() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PRIVATE);
		d.setFinal( true);
		d.setVolatile(true);
		d.setStatic(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString()+ JavaKeyWord.STATIC+ JavaKeyWord.VOLATILE, s);
		printResult(s);
	}
	
	public void test20() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.PROTECTED);
		d.setFinal( true);
		d.setVolatile(true);
		d.setStatic(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PROTECTED.toString()+ JavaKeyWord.STATIC+ JavaKeyWord.VOLATILE, s);
		printResult(s);
	}
	
	public void test21() throws CompilationException{
		FieldDecoration d = new FieldDecoration(JavaVisibility.DEFAULT);
		d.setFinal( true);
		d.setVolatile(true);
		d.setStatic(true);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.DEFAULT.toString()+ JavaKeyWord.STATIC+ JavaKeyWord.VOLATILE, s);
		printResult(s);
	}
	
	public void test22() throws CompilationException{
		FieldDecoration d = new FieldDecoration(null);
		
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.DEFAULT.toString(), s);
		
		printResult(s);
	}
	
	public void test23() throws CompilationException{
		FieldDecoration d = new FieldDecoration();
		
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.PRIVATE.toString(), s);
		
		printResult(s);
	}
	
	public void test24() throws CompilationException{
		FieldDecoration d = new FieldDecoration();
		d.setVisibility(null);
		int  indentLevel = 0 ;
		String s = d.compiledContent(indentLevel).toString();
		assertEquals(JavaVisibility.DEFAULT.toString(), s);
		
		printResult(s);
	}
}

