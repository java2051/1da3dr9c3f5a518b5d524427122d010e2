package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;
import com.honey.core.types.JDKFullyQualifiedJavaType;

public class TestField extends BaseTestCase{

	public void test0() throws CompilationException{
		Field field = new Field("field");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private int field ;", s);
		printResult(s);
	}
	
	public void test1() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private Integer field ;", s);
		printResult(s);
	}
	
	public void test2() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getLongInstance());
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private Long field ;", s);
		printResult(s);
	}
	
	public void test3() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getLongInstance());
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private Long field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test4() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getLongInstance());
		field.setComment("");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private Long field ;/**/", s);
		printResult(s);
	}
	
	public void test5() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		field.getDecoration().setVisibility(JavaVisibility.PUBLIC);
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("public Integer field ;", s);
		printResult(s);
	}
	
	public void test6() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		field.getDecoration().setVisibility(JavaVisibility.PROTECTED);
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("protected Integer field ;", s);
		printResult(s);
	}
	
	public void test7() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		field.getDecoration().setVisibility(JavaVisibility.DEFAULT);
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("Integer field ;", s);
		printResult(s);
	}
	
	public void test8() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		field.getDecoration().setVisibility(JavaVisibility.PROTECTED);
		field.getDecoration().setStatic(true);
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("protected static Integer field ;", s);
		printResult(s);
	}
	
	public void test9() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		field.getDecoration().setVisibility(JavaVisibility.PROTECTED);
		field.getDecoration().setFinal(true);
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("protected final Integer field ;", s);
		printResult(s);
	}
	public void test10() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		field.getDecoration().setVisibility(JavaVisibility.PROTECTED);
		field.getDecoration().setFinal(true);
		field.getDecoration().setStatic(true);
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("protected static final Integer field ;", s);
		printResult(s);
	}
	public void test11() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		FieldDecoration fieldDecoration =  new FieldDecoration() ;
		fieldDecoration.setVolatile(true);
		field.setDecoration(fieldDecoration);
		
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private volatile Integer field ;", s);
		printResult(s);
	}
	
	public void test12() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		FieldDecoration fieldDecoration =  new FieldDecoration() ;
		fieldDecoration.setVolatile(true);
		fieldDecoration.setStatic(true);
		field.setDecoration(fieldDecoration);
		
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private static volatile Integer field ;", s);
		printResult(s);
	}
	
	public void test13() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		FieldDecoration fieldDecoration =  new FieldDecoration() ;
		fieldDecoration.setVolatile(true);
		fieldDecoration.setFinal(true);
		field.setDecoration(fieldDecoration);
		
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private final volatile Integer field ;", s);
		printResult(s);
	}
	
	public void test14() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		FieldDecoration fieldDecoration =  new FieldDecoration() ;
		fieldDecoration.setVolatile(true);
		fieldDecoration.setFinal(true);
		fieldDecoration.setStatic(true);
		field.setDecoration(fieldDecoration);
		
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private static final volatile Integer field ;", s);
		printResult(s);
	}
	
	public void test15() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		FieldDecoration fieldDecoration =  new FieldDecoration() ;
		fieldDecoration.setFinal(true);
		fieldDecoration.setStatic(true);
		field.setDecoration(fieldDecoration);
		field.addAnnotation("NotNull");
		
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("@NotNull\r\nprivate static final Integer field ;", s);
		printResult(s);
	}
	
	public void test16() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		FieldDecoration fieldDecoration =  new FieldDecoration() ;
		fieldDecoration.setFinal(true);
		fieldDecoration.setStatic(true);
		field.setDecoration(fieldDecoration);
		field.addJavaDocLine("doc");
		
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("/** doc */\r\nprivate static final Integer field ;", s);
		printResult(s);
	}
	
	public void test17() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getLongInstance());
		field.setInitializationString("4");
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private Long field = 4L ;/*comment*/", s);
		printResult(s);
	}
	
	public void test18() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getLongArrayInstance());
		
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private Long[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test19() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getDoubleArrayInstance());
		
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private Double[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test20() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getFloatArrayInstance());
		
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private Float[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test21() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getCharArrayInstance());
		
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private char[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test22() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getBooleanArrayInstance());
		
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private Boolean[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test23() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getByteArrayInstance());
		
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private Byte[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test24() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntArrayInstance());
		
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private int[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test25() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerArrayInstance());
		
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private Integer[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test26() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerArrayInstance());
		field.setBaseType(true);
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private int[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test27() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getLongArrayInstance());
		field.setBaseType(true);
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private long[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test28() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getDoubleArrayInstance());
		field.setBaseType(true);
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private double[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test29() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getFloatArrayInstance());
		field.setBaseType(true);
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private float[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test30() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getCharacterArrayInstance());
		field.setBaseType(true);
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private char[] field ;/*comment*/", s);
		printResult(s);
	}
	public void test31() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getByteArrayInstance());
		field.setBaseType(true);
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private byte[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test32() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getBooleanArrayInstance());
		field.setBaseType(true);
		field.setComment("comment");
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private boolean[] field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test33() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getBooleanArrayInstance());
		field.setBaseType(true);
		field.getDecoration().setFinal(true);
		field.setComment("comment");
		
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private final boolean[] field ;/*comment*/", s);
		printResult(s);
	}

	public void test34() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getBooleanArrayInstance());
		field.setBaseType(true);
		field.getDecoration().setFinal(true);
		field.setComment("comment");
		field.setType(JDKFullyQualifiedJavaType.getObjectInstance());
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private final Object field ;/*comment*/", s);
		printResult(s);
	}
	
	public void test35() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getIntegerInstance());
		field.getDecoration().setVisibility(JavaVisibility.PROTECTED);
		field.getDecoration().setFinal(true);
		field.setUseDefaultValue(true);
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		
		assertEquals("protected final Integer field = 0 ;", s);
		printResult(s);
	}
	
	
	public void test36() throws CompilationException{
		Field field = new Field("field",JDKFullyQualifiedJavaType.getBooleanArrayInstance());
		field.setBaseType(true);
		field.getDecoration().setFinal(true);
		field.setComment("comment");
		field.setUseDefaultValue(true);
		field.setType(JDKFullyQualifiedJavaType.getObjectInstance());
		int indentLevel= 0; 
		String s = field.compiledContent(indentLevel).toString();
		assertEquals("private final Object field = null ;/*comment*/", s);
		printResult(s);
	}
}
