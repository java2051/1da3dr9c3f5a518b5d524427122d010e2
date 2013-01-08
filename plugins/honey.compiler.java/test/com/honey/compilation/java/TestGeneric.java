package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

import junit.framework.TestCase;

public class TestGeneric extends TestCase {

	public void test0() throws CompilationException{
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("com.test.Generic<t extends types >"); 
		Clazz clazz = new Clazz(type);
		Method method = new Method("testGenericMethod1",   new FullyQualifiedJavaType("List<Object>"));
		clazz.addMethod(method);
		
		method = new Method("testGenericMethod2", JDKFullyQualifiedJavaType.getIntegerInstance());
		method.setBaseType(true);
		
		Parameter par = new Parameter( new FullyQualifiedJavaType("Map< String, Object >"), "generic");
		method.addParameter(par);
		clazz.addMethod(method);
		
		Field field = new Field("field1", new FullyQualifiedJavaType("Map< String, Object >"));
		clazz.addField(field);
		
		clazz.setSuperClass( new FullyQualifiedJavaType("Map< String, Object >") );
		clazz.addSuperInterface( new FullyQualifiedJavaType("Map< String, Object >") );
		
		String s =  clazz.compiledContent().toString();
		System.out.println( s);
		String hash = StringUtility.getStringHash(s);
		assertEquals("3a263588783c0fc478000bc4e90461df40d39cee" , hash);
		
		
		
		
	}
	
	
	
}
