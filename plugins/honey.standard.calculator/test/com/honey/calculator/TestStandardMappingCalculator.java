package com.honey.calculator;

import junit.framework.TestCase;

public class TestStandardMappingCalculator extends TestCase {
	
	public void test13(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("Test" ,"Java");
	
		assertEquals("testJava", s);
	}
	
	public void test14(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("#Test" ,"Java");
	
		assertEquals("testJava", s);
	}
	public void test15(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("#%$Test" ,"Java");
	
		assertEquals("testJava", s);
	}
	
	public void test16(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("#%$Te%$st" ,"Java");
		assertEquals("testJava", s);
	}
	
	public void test17(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("#%$Te%$st" ,"Ja*(va");
		assertEquals("testJava", s);
	}
	
	public void test18(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("#%$Te%$st",null ,"Ja*(va");
		assertEquals("testJava", s);
	}
	
	public void test19(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("#%$8Te%$st",null ,"Ja*(va");
		//System.out.println(s);
		assertEquals("testJava", s);
	}
	public void test20(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("#%$8Te6%$st",null ,"Ja*(va");
		//System.out.println(s);
		assertEquals("te6stJava", s);
	}
	
	public void test21(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("#%$8Te6%$st",null ,"Ja*(7va");
		//System.out.println(s);
		assertEquals("te6stJa7va", s);
	}
	public void test22(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("#%$8Te6%$st",null ,"ja*(7va");
		//System.out.println(s);
		assertEquals("te6stJa7va", s);
	}
	public void test23(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("#%$8Te6%$st","" ,"ja*(7va");
		//System.out.println(s);
		assertEquals("te6stJa7va", s);
	}
	
	public void test24(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("#%$_8T e6%$st"," " ,"ja*(7va");
		//System.out.println(s);
		assertEquals("_8Te6stJa7va", s);
	}
	public void test25(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("_test"," " ,"Java");
		//System.out.println(s);
		assertEquals("_testJava", s);
	}
	public void test26(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.mappingCalculator("9test"," " ,"Java");
		//System.out.println(s);
		assertEquals("testJava", s);
	}
	
	
	public void test27(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.schemaCalculator("tb_Test" ,"Table");
	
		assertEquals("testTable", s);
	}
	
	public void test28(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.schemaCalculator("Test" ,"Table");
	
		assertEquals("testTable", s);
	}
	
	public void test29(){
		StandardMappingCalculator cal = new StandardMappingCalculator( new CalculatorPlugin() );
		
		String s = cal.schemaCalculator("$#ddtb_Test" ,"Table");
	
		assertEquals("testTable", s);
	}
}
