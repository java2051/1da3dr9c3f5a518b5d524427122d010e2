package com.honey.core.types;

import junit.framework.TestCase;

public class TestFullyQualifiedJavaType extends TestCase {
	
	public void test0(){
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("junit.framework.TestCase<t extends types >");
		assertEquals("junit.framework.TestCase", type.getLongName());
		assertEquals("TestCase", type.getShortName());
		assertEquals("junit.framework", type.getPackageName());
		assertEquals("TestCase<t extends types>", type.getFullName());
		assertEquals("junit.framework.TestCase<t extends types>", type.getFullyQualifiedName());
		assertEquals("TestCase", type.getBaseShortName());
	}
	
	public void test1(){
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.lang.String");
		assertEquals("java.lang.String", type.getLongName());
		assertEquals("String", type.getShortName());
		assertEquals("java.lang", type.getPackageName());
		assertEquals("String", type.getFullName());
		assertEquals("java.lang.String", type.getFullyQualifiedName());
		assertEquals("String", type.getBaseShortName());
	}
	
	public void test2(){
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.lang.Integer");
		assertEquals("java.lang.Integer", type.getLongName());
		assertEquals("Integer", type.getShortName());
		assertEquals("java.lang", type.getPackageName());
		assertEquals("Integer", type.getFullName());
		assertEquals("java.lang.Integer", type.getFullyQualifiedName());
		assertEquals("int", type.getBaseShortName());
	}
	
	public void test3(){
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.lang.Long");
		assertEquals("java.lang.Long", type.getLongName());
		assertEquals("Long", type.getShortName());
		assertEquals("java.lang", type.getPackageName());
		assertEquals("Long", type.getFullName());
		assertEquals("java.lang.Long", type.getFullyQualifiedName());
		assertEquals("long", type.getBaseShortName());
	}
	
	public void test4(){
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.lang.Double");
		assertEquals("java.lang.Double", type.getLongName());
		assertEquals("Double", type.getShortName());
		assertEquals("java.lang", type.getPackageName());
		assertEquals("Double", type.getFullName());
		assertEquals("java.lang.Double", type.getFullyQualifiedName());
		assertEquals("double", type.getBaseShortName());
	}
	
	public void test5(){
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.lang.Float");
		assertEquals("java.lang.Float", type.getLongName());
		assertEquals("Float", type.getShortName());
		assertEquals("java.lang", type.getPackageName());
		assertEquals("Float", type.getFullName());
		assertEquals("java.lang.Float", type.getFullyQualifiedName());
		assertEquals("float", type.getBaseShortName());
	}
	
	public void test6(){
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.lang.Short");
		assertEquals("java.lang.Short", type.getLongName());
		assertEquals("Short", type.getShortName());
		assertEquals("java.lang", type.getPackageName());
		assertEquals("Short", type.getFullName());
		assertEquals("java.lang.Short", type.getFullyQualifiedName());
		assertEquals("short", type.getBaseShortName());
	}
	
	public void test7(){
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.lang.Byte");
		assertEquals("java.lang.Byte", type.getLongName());
		assertEquals("Byte", type.getShortName());
		assertEquals("java.lang", type.getPackageName());
		assertEquals("Byte", type.getFullName());
		assertEquals("java.lang.Byte", type.getFullyQualifiedName());
		assertEquals("byte", type.getBaseShortName());
	}
	public void test8(){
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.lang.Character");
		assertEquals("java.lang.Character", type.getLongName());
		assertEquals("Character", type.getShortName());
		assertEquals("java.lang", type.getPackageName());
		assertEquals("Character", type.getFullName());
		assertEquals("java.lang.Character", type.getFullyQualifiedName());
		assertEquals("char", type.getBaseShortName());
	}
	
	public void test9(){
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.lang.Boolean");
		assertEquals("java.lang.Boolean", type.getLongName());
		assertEquals("Boolean", type.getShortName());
		assertEquals("java.lang", type.getPackageName());
		assertEquals("Boolean", type.getFullName());
		assertEquals("java.lang.Boolean", type.getFullyQualifiedName());
		assertEquals("boolean", type.getBaseShortName());
	}
	
	
	public void test10(){
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("junit.framework.TestCase$child<t extends types > ");
		
		assertEquals("junit.framework.TestCase$child", type.getLongName());
		assertEquals("TestCase$child", type.getShortName());
		assertEquals("junit.framework", type.getPackageName());
		assertEquals("TestCase.child<t extends types>", type.getFullName());
		assertEquals("junit.framework.TestCase$child<t extends types>", type.getFullyQualifiedName());
		assertEquals("TestCase$child", type.getBaseShortName());
	}
}
