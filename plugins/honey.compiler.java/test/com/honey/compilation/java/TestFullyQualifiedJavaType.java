package com.honey.compilation.java;

import java.io.File;

import com.honey.core.compiler.CompilationException;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;

public class TestFullyQualifiedJavaType extends BaseTestCase{

	
	public void test0() throws CompilationException{
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(String.class);
		assertEquals("String", type.toString());
		printResult(type);
	}
	
	public void test1() throws CompilationException{
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(Integer.class);
		assertEquals("Integer", type.toString());
		printResult(type);
	}
	public void test2() throws CompilationException{
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(Object.class);
		assertEquals("Object", type.toString());
		printResult(type);
	}
	
	public void test3() throws CompilationException{
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(File.class);
		assertEquals("java.io.File", type.toString());
		printResult(type);
	}

	public void test4() throws CompilationException{
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(Long.class);
		assertEquals("Long", type.toString());
		printResult(type);
	}

	public void test5() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getLongArrayInstance();
		assertEquals("Long[]", type.toString());
		printResult(type);
	}
	
	public void test6() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getLongArrayInstance();
		assertEquals("long[]", type.getBaseShortName());
		printResult(type);
	}
	
	public void test7() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getStringArrayInstance();
		assertEquals("String[]", type.getBaseShortName());
		printResult(type);
	}
	
	public void test8() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getFileArrayInstance();
		assertEquals("File[]", type.getBaseShortName());
		printResult(type);
	}
	
	public void test9() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getLongInstance();
		assertEquals("0L", type.getInitializationValue());
		printResult(type);
	}
	
	public void test10() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getDoubleInstance();
		assertEquals("0.0D", type.getInitializationValue());
		printResult(type);
	}
	public void test11() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getFloatInstance();
		assertEquals("0.0F", type.getInitializationValue());
		printResult(type);
	}
	public void test12() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getCharacterInstance();
		assertEquals("0", type.getInitializationValue());
		printResult(type);
	}
	public void test13() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getCharInstance();
		assertEquals("0", type.getInitializationValue());
		printResult(type);
	}
	public void test14() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getShortInstance();
		assertEquals("0", type.getInitializationValue());
		printResult(type);
	}
	public void test15() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getStringInstance();
		assertEquals("null", type.getInitializationValue());
		printResult(type);
		
	}
	
	public void test16() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getBooleanInstance();
		assertEquals("false", type.getInitializationValue());
		printResult(type);
		
	}
	
	public void test17() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getByteInstance();
		assertEquals("0", type.getInitializationValue());
		printResult(type);
		
	}
	
	public void test18() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getIntInstance();
		assertEquals("12", type.formatValue("12"));

		
	}
	public void test19() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getDoubleInstance();
		assertEquals("12D", type.formatValue("12"));

		type = JDKFullyQualifiedJavaType.getDoubleInstance();
		assertEquals("10.2D", type.formatValue("10.2"));

		type = JDKFullyQualifiedJavaType.getDoubleInstance();
		assertEquals("0.002D", type.formatValue("0.002"));

	}
	
	public void test20() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getFloatInstance();
		assertEquals("12F", type.formatValue("12"));

		type = JDKFullyQualifiedJavaType.getFloatInstance();
		assertEquals("10.2F", type.formatValue("10.2"));

		type = JDKFullyQualifiedJavaType.getFloatInstance();
		assertEquals("0.002F", type.formatValue("0.002"));

	}
	
	public void test21() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getLongInstance();
		assertEquals("12L", type.formatValue("12"));
	}
	
	public void test22() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getBooleanInstance();
		assertEquals("true", type.formatValue("12"));
		type = JDKFullyQualifiedJavaType.getBooleanInstance();
		assertEquals("true", type.formatValue("true"));
	}
	
	public void test23() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getShortInstance();
		assertEquals("(short)12", type.formatValue("12"));
	}
	
	public void test24() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getCharacterInstance();
		assertEquals("'1'", type.formatValue("12"));
		
		type = JDKFullyQualifiedJavaType.getCharacterInstance();
		assertEquals("'A'", type.formatValue("ABC"));
	}
	
	public void test25() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getStringInstance();
		assertEquals("\"12\"", type.formatValue("12"));
		
		type = JDKFullyQualifiedJavaType.getStringInstance();
		assertEquals("\"ABCDEF\"", type.formatValue("ABCDEF"));
		
		type = JDKFullyQualifiedJavaType.getStringInstance();
		assertEquals("null", type.formatValue("null"));
	}

	public void test26() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getShortArrayInstance();
		assertEquals("null", type.getInitializationValue());
		
		type = JDKFullyQualifiedJavaType.getIntArrayInstance();
		assertEquals("null", type.getInitializationValue());
		
		type = JDKFullyQualifiedJavaType.getDoubleArrayInstance();
		assertEquals("null", type.getInitializationValue());
		
		type = JDKFullyQualifiedJavaType.getFloatArrayInstance() ;
		assertEquals("null", type.getInitializationValue());
		
		type = JDKFullyQualifiedJavaType.getCharacterArrayInstance();
		assertEquals("null", type.getInitializationValue());
		
		type = JDKFullyQualifiedJavaType.getCharArrayInstance();
		assertEquals("null", type.getInitializationValue());
		
		type = JDKFullyQualifiedJavaType.getByteArrayInstance();
		assertEquals("null", type.getInitializationValue());
		
		type = JDKFullyQualifiedJavaType.getBooleanArrayInstance();
		assertEquals("null", type.getInitializationValue());
		
		type = JDKFullyQualifiedJavaType.getObjectArrayInstance();
		assertEquals("null", type.getInitializationValue());
	}
	
	public void test27() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getStringInstance();
		assertEquals("\"12\"", type.formatValue("12"));
		assertEquals("\"12\"", type.formatValue("\"12"));
		assertEquals("\"12\"", type.formatValue("12\""));
		assertEquals("\"12\"", type.formatValue("\"12\""));
	}
	
	public void test28() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getCharInstance();
		assertEquals("'1'", type.formatValue("12"));
		assertEquals("'R'", type.formatValue("\'R"));
		assertEquals("'R'", type.formatValue("R\'"));
		assertEquals("'R'", type.formatValue("\'R\'"));
	}
	
	public void test29() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getLongArrayInstance();
		assertEquals("newLong{4L,6L,6l,98L,}", type.formatValue("new Long{4,6L,6l,98}").replaceAll(" ", ""));
	}
	
	public void test30() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getDoubleArrayInstance();
		assertEquals("newDouble{4D,6D,6d,98D,}", type.formatValue("new Double{4,6D,6d,98}").replaceAll(" ", ""));
	}

	public void test31() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getFloatArrayInstance();
		assertEquals("newFloat{4F,6F,6f,98F,}", type.formatValue("new Float{4,6F,6f,98}").replaceAll(" ", ""));
	}

	public void test32() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getIntArrayInstance();
		assertEquals("newint{4,6,6,98,}", type.formatValue("new int{4,6,6,98}").replaceAll(" ", ""));
	}
	
	public void test33() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getBooleanArrayInstance();
		assertEquals("newBoolean{true,true,true,true,false,}", type.formatValue("new Boolean{4,true,6,98,-1}").replaceAll(" ", ""));
	}
	
	public void test34() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getLongArrayInstance();
		assertEquals("new Long{4L,6L,6l,98L,}", type.formatValue("new Long{4,6L,6l,98}"));
	}
	
	public void test35() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getShortArrayInstance();
		assertEquals("new Short{(short)4,(short)6,(short)6,(short)98,}", type.formatValue("new Short{4,(short)6,6,98}"));
	}
		
	public void test36() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getByteArrayInstance();
		assertEquals("new Byte{1,0,1,1,0,}", type.formatValue("new Byte{4,(short)6,6,98,false}"));
	}
	
	public void test37() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getObjectArrayInstance();
		assertEquals("new Object[2]", type.formatValue("new Object[2]"));
	}
	
	public void test38() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getStringInstance();
		assertEquals("", type.formatValue(""));
	}
	
	public void test39() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getCharInstance();
		assertEquals("\'\'\'", type.formatValue("\'\'"));
	}

	public void test40() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getCharInstance();
		assertEquals("", type.formatValue(""));
	}
	
	
	public void test41() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getLongArrayInstance();
		assertEquals("new Long{}", type.formatValue("new Long{}"));
	}
	
	public void test42() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getDoubleArrayInstance();
		assertEquals("new Double{}", type.formatValue("new Double{}"));
	}

	public void test43() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getFloatArrayInstance();
		assertEquals("new Float{}", type.formatValue("new Float{}"));
	}

	public void test44() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getIntArrayInstance();
		assertEquals("new int{}", type.formatValue("new int{}"));
	}
	
	public void test45() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getBooleanArrayInstance();
		assertEquals("new Boolean{}", type.formatValue("new Boolean{}"));
	}
	
	public void test46() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getLongArrayInstance();
		assertEquals("new Long{}", type.formatValue("new Long{}"));
	}
	
	public void test47() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getShortArrayInstance();
		assertEquals("new Short{}", type.formatValue("new Short{}"));
	}
		
	public void test48() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getByteArrayInstance();
		assertEquals("new Byte{}", type.formatValue("new Byte{}"));
	}

	
	
	
	
	

	
	public void test49() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getLongArrayInstance();
		assertEquals("new Long{}", type.formatValue("new Long{      }"));
	}
	
	public void test50() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getDoubleArrayInstance();
		assertEquals("new Double{}", type.formatValue("new Double{  }"));
	}

	public void test51() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getFloatArrayInstance();
		assertEquals("new Float{}", type.formatValue("new Float{    }"));
	}

	public void test52() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getIntArrayInstance();
		assertEquals("new int{}", type.formatValue("new int{    }"));
	}
	
	public void test53() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getBooleanArrayInstance();
		assertEquals("new Boolean{}", type.formatValue("new Boolean{   }"));
	}
	
	public void test54() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getLongArrayInstance();
		assertEquals("new Long   {}", type.formatValue("new Long   {  }"));
	}
	
	public void test55() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getShortArrayInstance();
		assertEquals("new Short{}", type.formatValue("new Short{  }"));
	}
		
	public void test56() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getByteArrayInstance();
		assertEquals("new Byte{}", type.formatValue("new Byte{    }"));
	}
	
	public void test57() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getCharArrayInstance();
		assertEquals("new char{}", type.formatValue("new char{    }"));
	}
	
	public void test58() throws CompilationException{
		FullyQualifiedJavaType type = JDKFullyQualifiedJavaType.getObjectArrayInstance();
		assertEquals("new Object{    }", type.formatValue("new Object{    }"));
	}
	
	public void test59() throws CompilationException{
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.util.List<E>");
		printResult(type);
		assertEquals("java.util.List<E>", type.toString());
	}
	
	public void test60() throws CompilationException{
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.util.List<?>");
		assertEquals("java.util.List<?>", type.toString());
	}
	
	public void test61() throws CompilationException{
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("java.util.List<? extends E>");
		//System.out.println(type.);
		assertEquals("java.util.List<? extends E>", type.toString());
	}
}

