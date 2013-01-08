package com.honey.calculator;

import junit.framework.TestCase;

public class TestStandardJavaNameCalculator extends TestCase {
	
	public void test0(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator(new CalculatorPlugin());
		
		String s = cal.classNameCalculator("Test" ,"Java");
	
		assertEquals("TestJava", s);
	}
	
	public void test1(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("#Test" ,"Java");
	
		assertEquals("TestJava", s);
	}
	public void test2(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("#%$Test" ,"Java");
	
		assertEquals("TestJava", s);
	}
	
	public void test3(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("#%$Te%$st" ,"Java");
		assertEquals("TestJava", s);
	}
	
	public void test4(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("#%$Te%$st" ,"Ja*(va");
		assertEquals("TestJava", s);
	}
	
	public void test5(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("#%$Te%$st",null ,"Ja*(va");
		assertEquals("TestJava", s);
	}
	
	public void test6(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("#%$8Te%$st",null ,"Ja*(va");
		//System.out.println(s);
		assertEquals("TestJava", s);
	}
	public void test7(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("#%$8Te6%$st",null ,"Ja*(va");
		//System.out.println(s);
		assertEquals("Te6stJava", s);
	}
	
	public void test8(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("#%$8Te6%$st",null ,"Ja*(7va");
		//System.out.println(s);
		assertEquals("Te6stJa7va", s);
	}
	public void test9(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("#%$8Te6%$st",null ,"ja*(7va");
		//System.out.println(s);
		assertEquals("Te6stJa7va", s);
	}
	public void test10(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("#%$8Te6%$st","" ,"ja*(7va");
		//System.out.println(s);
		assertEquals("Te6stJa7va", s);
	}
	
	public void test11(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("#%$_8T e6%$st"," " ,"ja*(7va");
		//System.out.println(s);
		assertEquals("_8Te6stJa7va", s);
	}
	public void test12(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.classNameCalculator("_test"," " ,"Java");
		//System.out.println(s);
		assertEquals("_testJava", s);
	}
	
	public void test13(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("Test" ,"Java");
	
		assertEquals("testJava", s);
	}
	
	public void test14(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("#Test" ,"Java");
	
		assertEquals("testJava", s);
	}
	public void test15(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("#%$Test" ,"Java");
	
		assertEquals("testJava", s);
	}
	
	public void test16(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("#%$Te%$st" ,"Java");
		assertEquals("testJava", s);
	}
	
	public void test17(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("#%$Te%$st" ,"Ja*(va");
		assertEquals("testJava", s);
	}
	
	public void test18(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("#%$Te%$st",null ,"Ja*(va");
		assertEquals("testJava", s);
	}
	
	public void test19(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("#%$8Te%$st",null ,"Ja*(va");
		//System.out.println(s);
		assertEquals("testJava", s);
	}
	public void test20(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("#%$8Te6%$st",null ,"Ja*(va");
		//System.out.println(s);
		assertEquals("te6stJava", s);
	}
	
	public void test21(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("#%$8Te6%$st",null ,"Ja*(7va");
		//System.out.println(s);
		assertEquals("te6stJa7va", s);
	}
	public void test22(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("#%$8Te6%$st",null ,"ja*(7va");
		//System.out.println(s);
		assertEquals("te6stJa7va", s);
	}
	public void test23(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("#%$8Te6%$st","" ,"ja*(7va");
		//System.out.println(s);
		assertEquals("te6stJa7va", s);
	}
	
	public void test24(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("#%$_8T e6%$st"," " ,"ja*(7va");
		//System.out.println(s);
		assertEquals("_8Te6stJa7va", s);
	}
	public void test25(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("_test"," " ,"Java");
		//System.out.println(s);
		assertEquals("_testJava", s);
	}
	public void test26(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.fieldNameCalculator("9test"," " ,"Java");
		//System.out.println(s);
		assertEquals("testJava", s);
	}
	public void test27(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("Test" ,"Java");
	
		assertEquals("testJava", s);
	}
	
	public void test28(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("#Test" ,"Java");
	
		assertEquals("testJava", s);
	}
	public void test29(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("#%$Test" ,"Java");
	
		assertEquals("testJava", s);
	}
	
	public void test30(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("#%$Te%$st" ,"Java");
		assertEquals("testJava", s);
	}
	
	public void test31(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("#%$Te%$st" ,"Ja*(va");
		assertEquals("testJava", s);
	}
	
	public void test32(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("#%$Te%$st",null ,"Ja*(va");
		assertEquals("testJava", s);
	}
	
	public void test33(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("#%$8Te%$st",null ,"Ja*(va");
		//System.out.println(s);
		assertEquals("testJava", s);
	}
	public void test34(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("#%$8Te6%$st",null ,"Ja*(va");
		//System.out.println(s);
		assertEquals("te6stJava", s);
	}
	
	public void test35(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("#%$8Te6%$st",null ,"Ja*(7va");
		//System.out.println(s);
		assertEquals("te6stJa7va", s);
	}
	public void test36(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("#%$8Te6%$st",null ,"ja*(7va");
		//System.out.println(s);
		assertEquals("te6stJa7va", s);
	}
	public void test37(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("#%$8Te6%$st","" ,"ja*(7va");
		//System.out.println(s);
		assertEquals("te6stJa7va", s);
	}
	
	public void test38(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("#%$_8T e6%$st"," " ,"ja*(7va");
		//System.out.println(s);
		assertEquals("_8Te6stJa7va", s);
	}
	public void test39(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("_test"," " ,"Java");
		//System.out.println(s);
		assertEquals("_testJava", s);
	}
	public void test40(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("9test"," " ,"Java");
		//System.out.println(s);
		assertEquals("testJava", s);
	}
	
	public void test41(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("get","test"," " ,"Java");
		//System.out.println(s);
		assertEquals("getTestJava", s);
	}
	public void test42(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("is","test"," " ,"Java");
		//System.out.println(s);
		assertEquals("isTestJava", s);
	}
	public void test43(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.methodNameCalculator("set","test"," " ,"Java");
		//System.out.println(s);
		assertEquals("setTestJava", s);
	}
	
	
	public void test44(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("Test" ,"Java");
		//System.out.println(s);
		assertEquals("TEST_JAVA", s);
	}
	
	public void test45(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("#Test" ,"Java");
	
		assertEquals("TEST_JAVA", s);
	}
	public void test46(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("#%$Test" ,"Java");
	
		assertEquals("TEST_JAVA", s);
	}
	
	public void test47(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("#%$Te%$st" ,"Java");
		assertEquals("TEST_JAVA", s);
	}
	
	public void test48(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("#%$Te%$st" ,"Ja*(va");
		assertEquals("TEST_JAVA", s);
	}
	
	public void test49(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("#%$Te%$st",null ,"Ja*(va");
		assertEquals("TEST_JAVA", s);
	}
	
	public void test50(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("#%$8Te%$st",null ,"Ja*(va");
		//System.out.println(s);
		assertEquals("TEST_JAVA", s);
	}
	public void test51(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("#%$8Te6%$st",null ,"Ja*(va");
		//System.out.println(s);
		assertEquals("TE6ST_JAVA", s);
	}
	
	public void test52(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("#%$8Te6%$st",null ,"Ja*(7va");
		//System.out.println(s);
		assertEquals("TE6ST_JA7VA", s);
	}
	public void test53(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("#%$8Te6%$st",null ,"ja*(7va");
		//System.out.println(s);
		assertEquals("TE6ST_JA7VA", s);
	}
	public void test54(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("#%$8Te6%$st","" ,"ja*(7va");
		//System.out.println(s);
		assertEquals("TE6ST_JA7VA", s);
	}
	
	public void test55(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("#%$_8T e6%$st"," " ,"ja*(7va");

		assertEquals("_8TE6ST_JA7VA", s);
	}
	public void test56(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("_test"," " ,"Java");

		assertEquals("_TEST_JAVA", s);
	}
	public void test57(){
		StandardJavaNameCalculator cal = new StandardJavaNameCalculator( new CalculatorPlugin() );
		
		String s = cal.staticFieldCalculator("9test"," " ,"Java");

		assertEquals("TEST_JAVA", s);
	}
}
