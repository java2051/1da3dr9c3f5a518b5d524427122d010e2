package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

public class TestEnum extends BaseTestCase{

	public void test0() throws CompilationException{
		Enum enumClass = new Enum(super.TEST_PACKAGE + super.TEST_TOP_CLASS_NAME );
		enumClass.addImportedType(JDKFullyQualifiedJavaType.getArrayListInstance());
		
		String s =  enumClass.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;publicenum"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test1() throws CompilationException{
		Enum enumClass = new Enum(super.TEST_PACKAGE + super.TEST_TOP_CLASS_NAME );
		enumClass.addImportedType(JDKFullyQualifiedJavaType.getArrayListInstance());
		enumClass.addFileComment("");
		enumClass.addFileComment("文件注释");
		String s =  enumClass.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/** *  * 文件注释 */packagecom.honey.compilation.java;importjava.util.ArrayList;publicenum"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	public void test2() throws CompilationException{
		Enum enumClass = new Enum(super.TEST_PACKAGE + super.TEST_TOP_CLASS_NAME );
		enumClass.addImportedType(JDKFullyQualifiedJavaType.getArrayListInstance());
		enumClass.addClassComment("comment");

		String s =  enumClass.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;/** * comment */publicenum"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test3() throws CompilationException{
		Enum enumClass = new Enum(super.TEST_PACKAGE + super.TEST_TOP_CLASS_NAME );
		enumClass.getClassDecoration().setVisibility(JavaVisibility.PRIVATE);
		enumClass.addImportedType(JDKFullyQualifiedJavaType.getArrayListInstance());
		enumClass.addClassComment("comment");

		String s =  enumClass.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;/** * comment */enum"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test4() throws CompilationException{
		Enum enumClass = new Enum(super.TEST_PACKAGE + super.TEST_TOP_CLASS_NAME );
		enumClass.getClassDecoration().setVisibility(JavaVisibility.PROTECTED);
		enumClass.addImportedType(JDKFullyQualifiedJavaType.getArrayListInstance());
		enumClass.addClassComment("comment");

		String s =  enumClass.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;/** * comment */enum"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test5() throws CompilationException{
		Enum enumClass = new Enum(super.TEST_PACKAGE + super.TEST_TOP_CLASS_NAME );
		enumClass.getClassDecoration().setVisibility(JavaVisibility.DEFAULT);
		enumClass.addImportedType(JDKFullyQualifiedJavaType.getArrayListInstance());
		enumClass.addClassComment("comment");

		String s =  enumClass.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;/** * comment */enum"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test6() throws CompilationException{
		Enum enumClass = new Enum(super.TEST_PACKAGE + super.TEST_TOP_CLASS_NAME );
		enumClass.getClassDecoration().setVisibility(JavaVisibility.DEFAULT);
		enumClass.getClassDecoration().setStatic(true);
		enumClass.addImportedType(JDKFullyQualifiedJavaType.getArrayListInstance());
		enumClass.addClassComment("comment");

		String s =  enumClass.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;/** * comment */enum"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test7() throws CompilationException{
		Enum enumClass = new Enum(super.TEST_PACKAGE + super.TEST_TOP_CLASS_NAME );
		enumClass.getClassDecoration().setVisibility(JavaVisibility.DEFAULT);
		enumClass.getClassDecoration().setFinal(true);
		enumClass.addImportedType(JDKFullyQualifiedJavaType.getArrayListInstance());
		enumClass.addClassComment("comment");

		String s =  enumClass.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;/** * comment */enum"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test8() throws CompilationException{
		Enum enumClass = new Enum(super.TEST_PACKAGE + super.TEST_TOP_CLASS_NAME );
		enumClass.addAfterBodyComment("Test after body");
		String s = enumClass.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicenum"+super.TEST_TOP_CLASS_NAME+"{}/*Testafterbody*/";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
	}
}
