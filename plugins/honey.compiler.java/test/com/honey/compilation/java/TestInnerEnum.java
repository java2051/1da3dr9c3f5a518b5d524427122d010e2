package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;


public class TestInnerEnum extends BaseTestCase{
	
	
	public void test0() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "publicenum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}
	
	public void test1() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		
		int indentLevel = 1 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "publicenum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}

	public void test2() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		
		int indentLevel = 2 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "publicenum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}
	
	public void test3() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC ));
		
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "publicenum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}
	
	public void test4() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PRIVATE ));
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "privateenum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}
	
	
	public void test5() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PROTECTED ));
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "protectedenum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}
	
	public void test6() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.DEFAULT ));
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "enum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}
	
	public void test7() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.DEFAULT,true,false ));
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "staticenum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}
	
	
	public void test8() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.DEFAULT,false,true ));
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "enum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	
	public void test9() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.DEFAULT,true,true ));
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "staticenum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test10() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test11() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		inner.setSuperClass(JDKFullyQualifiedJavaType.getArrayListInstance());
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test12() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		inner.addSuperInterface(JDKFullyQualifiedJavaType.getCloneableInstance());
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"implements Cloneable{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test13() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		inner.addSuperInterface(JDKFullyQualifiedJavaType.getCloneableInstance());
		inner.addSuperInterface(JDKFullyQualifiedJavaType.getListInstance());
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"implements Cloneable,List{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test14() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		inner.addAnnotation("NotNull");

		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/@NotNullpublicenum"+super.TEST_INNER_ENUM_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test15() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		EnumField enumField = new EnumField("E");
		inner.addEnumFields(enumField);

		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{E;}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	public void test16() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		EnumField enumField = new EnumField("E");
		inner.addEnumFields(enumField);

		enumField = new EnumField("F");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("A");
		inner.addEnumFields(enumField);

		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{E, F, A;}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test17() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		EnumField enumField = new EnumField("E");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getObjectInstance(),"E"), "new Object()");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("F");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getObjectInstance(),"f"), "new Object()");
		inner.addEnumFields(enumField);		
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{    E( new Object() ),F( new Object() );}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	public void test18() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		EnumField enumField = new EnumField("RED");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "1");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "红色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "true");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "R");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("GREEN");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "2");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "绿色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "G");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("YELLOW");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "3");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "黄色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "B");
		inner.addEnumFields(enumField);
	
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{RED( 1, \"红色\", true, 'R' ),     GREEN( 2, \"绿色\", false, 'G' ),     YELLOW( 3, \"黄色\", false, 'B' );}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test19() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		EnumField enumField = new EnumField("RED");
		enumField.setComment("RGB色中的红色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "1");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "红色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "true");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "R");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("GREEN");
		enumField.setComment("RGB色中的绿色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "2");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "绿色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "G");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("YELLOW");
		enumField.setComment("RGB色中的黄色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "3");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "黄色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "B");
		inner.addEnumFields(enumField);
	
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{RED( 1, \"红色\", true, 'R' )/*RGB色中的红色*/, GREEN( 2, \"绿色\", false, 'G' )/*RGB色中的绿色*/, YELLOW( 3, \"黄色\", false, 'B' )/*RGB色中的黄色*/;}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test20() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		EnumField enumField = new EnumField("RED");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "1");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "红色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "true");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "R");
		enumField.addMethod(new Method("method"));
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("GREEN");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "2");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "绿色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "G");
		enumField.addMethod(new Method("method"));
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("YELLOW");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "3");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "黄色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "B");
		enumField.addMethod(new Method("method"));
		inner.addEnumFields(enumField);
	
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{RED(1,\"红色\",true,'R'){/***@return*/publicvoidmethod(){//TODOAuto-generatedmethodstubreturn;}},GREEN(2,\"绿色\",false,'G'){/***@return*/publicvoidmethod(){//TODOAuto-generatedmethodstubreturn;}},YELLOW(3,\"黄色\",false,'B'){/***@return*/publicvoidmethod(){//TODOAuto-generatedmethodstubreturn;}};}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test21() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		EnumField enumField = new EnumField("RED");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "1");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "红色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "true");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "R");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("GREEN");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "2");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "绿色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "G");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("YELLOW");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "3");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "黄色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "B");
		inner.addEnumFields(enumField);
		inner.addField(new Field("number",JDKFullyQualifiedJavaType.getIntInstance()));
		inner.addField(new Field("name",JDKFullyQualifiedJavaType.getStringInstance()));
		inner.addField(new Field("error",JDKFullyQualifiedJavaType.getBooleanInstance()));
		inner.addField(new Field("rgb",JDKFullyQualifiedJavaType.getCharInstance()));
		
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{RED( 1, \"红色\", true, 'R' ),     GREEN( 2, \"绿色\", false, 'G' ),     YELLOW( 3, \"黄色\", false, 'B' );privateintnumber;privateStringname;privateBooleanerror;privatecharrgb;}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test22() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		EnumField enumField = new EnumField("RED");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "1");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "红色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "true");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "R");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("GREEN");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "2");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "绿色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "G");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("YELLOW");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "3");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "黄色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "B");
		inner.addEnumFields(enumField);
		Field  field =  new Field("number",JDKFullyQualifiedJavaType.getIntInstance()) ;
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC));
		inner.addField(field);
		
		field = new Field("name",JDKFullyQualifiedJavaType.getStringInstance());
		field.setDecoration(new Decoration(JavaVisibility.PROTECTED));
		inner.addField( field );
		
		field =  new Field("error",JDKFullyQualifiedJavaType.getBooleanInstance());
		field.setDecoration(new Decoration(JavaVisibility.DEFAULT));
		inner.addField(field);
		
		field =  new Field("rgb",JDKFullyQualifiedJavaType.getCharInstance());
		field.setDecoration(new Decoration(JavaVisibility.PRIVATE));
		inner.addField(field);
		
		field =  new Field("field1",JDKFullyQualifiedJavaType.getCharInstance());
		field.setDecoration(new Decoration(JavaVisibility.PRIVATE,true,false));
		inner.addField(field);
		
		field =  new Field("field2",JDKFullyQualifiedJavaType.getCharInstance());
		field.setUseDefaultValue(true);
		field.setDecoration(new Decoration(JavaVisibility.PRIVATE,false,true));
		inner.addField(field);
		
		field =  new Field("field3",JDKFullyQualifiedJavaType.getCharInstance());
		field.setDecoration(new Decoration(JavaVisibility.PRIVATE,true,true));
		field.setUseDefaultValue(true);
		inner.addField(field);
				
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{RED( 1, \"红色\", true, 'R' ),     GREEN( 2, \"绿色\", false, 'G' ),     YELLOW( 3, \"黄色\", false, 'B' );publicintnumber;protectedStringname;Booleanerror;privatecharrgb;privatestaticcharfield1;privatefinalcharfield2=0;privatestaticfinalcharfield3=0;}";
		
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	
	public void test23() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		EnumField enumField = new EnumField("RED");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "1");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "红色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "true");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "R");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("GREEN");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "2");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "绿色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "G");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("YELLOW");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "3");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "黄色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "B");
		inner.addEnumFields(enumField);
		Field  field =  new Field("number",JDKFullyQualifiedJavaType.getIntInstance()) ;
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC));
		inner.addField(field);
		
		field = new Field("name",JDKFullyQualifiedJavaType.getStringInstance());
		field.setDecoration(new Decoration(JavaVisibility.PROTECTED));
		inner.addField( field );
		
		field =  new Field("error",JDKFullyQualifiedJavaType.getBooleanInstance());
		field.setDecoration(new Decoration(JavaVisibility.DEFAULT));
		inner.addField(field);
		
		field =  new Field("rgb",JDKFullyQualifiedJavaType.getCharInstance());
		field.setDecoration(new Decoration(JavaVisibility.PRIVATE));
		inner.addField(field);
		
		field =  new Field("field1",JDKFullyQualifiedJavaType.getCharInstance());
		field.setDecoration(new Decoration(JavaVisibility.PRIVATE,true,false));
		inner.addField(field);
		
		field =  new Field("field2",JDKFullyQualifiedJavaType.getCharInstance());
		field.setDecoration(new Decoration(JavaVisibility.PRIVATE,false,true));
		field.setUseDefaultValue(true);
		inner.addField(field);
		
		field =  new Field("field3",JDKFullyQualifiedJavaType.getCharInstance());
		field.setDecoration(new Decoration(JavaVisibility.PRIVATE,true,true));
		field.setUseDefaultValue(true);
		inner.addField(field);
		inner.setAutoAddGetterMethod(true);
		inner.setAutoAddSetterMethod(true);
		
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{RED(1,\"红色\",true,'R'),GREEN(2,\"绿色\",false,'G'),YELLOW(3,\"黄色\",false,'B');publicintnumber;protectedStringname;Booleanerror;privatecharrgb;privatestaticcharfield1;privatefinalcharfield2=0;privatestaticfinalcharfield3=0;/***获取{@codergb}属性的值*@returnchar*/publicchargetRgb(){returnthis.rgb;}/***获取{@codefield2}属性的值*@returnchar*/publicchargetField2(){returnthis.field2;}/***设定{@codergb}属性的值*@paramrgb{@codechar}*@return*/publicvoidsetRgb(charrgb){this.rgb=rgb;}}";
		           //
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test24() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		EnumField enumField = new EnumField("RED");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "1");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "红色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "true");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "R");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("GREEN");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "2");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "绿色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "G");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("YELLOW");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "3");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "黄色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "B");
		inner.addEnumFields(enumField);
		
		MethodDecoration methodDecoration = new MethodDecoration ();
		methodDecoration.setConstructor(true);
		Method method = new Method(super.TEST_INNER_ENUM_NAME,methodDecoration);
		method.addParameter(new Parameter( JDKFullyQualifiedJavaType.getIntInstance() , "number" ));
		method.addParameter(new Parameter( JDKFullyQualifiedJavaType.getStringInstance() , "name" ));
		method.addParameter(new Parameter( JDKFullyQualifiedJavaType.getBooleanInstance() , "error" ));
		method.addParameter(new Parameter( JDKFullyQualifiedJavaType.getCharInstance() , "RGB" ));
		
		
		inner.addMethod(method);
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{RED(1,\"红色\",true,'R'),GREEN(2,\"绿色\",false,'G'),YELLOW(3,\"黄色\",false,'B');/***@paramnumber{@code   int}*@paramname{@code   String}*@paramerror{@code   Boolean}*@paramRGB{@code   char}** @see    String*/private"+super.TEST_INNER_ENUM_NAME+"(intnumber,Stringname,Booleanerror,charRGB){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test25() throws CompilationException{
		InnerEnum inner = createInnerEnum();
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,false,true ));
		inner.addClassComment("comment");
		EnumField enumField = new EnumField("RED");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "1");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "红色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "true");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "R");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("GREEN");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "2");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "绿色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "G");
		inner.addEnumFields(enumField);
		
		enumField = new EnumField("YELLOW");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"number"), "3");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"name"), "黄色");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getBooleanInstance(),"error"), "false");
		enumField.addParameter(new Parameter(JDKFullyQualifiedJavaType.getCharInstance(),"RGB"), "B");
		inner.addEnumFields(enumField);
		
		MethodDecoration methodDecoration = new MethodDecoration ();
		methodDecoration.setConstructor(true);
		Method method = new Method(super.TEST_INNER_ENUM_NAME,methodDecoration);
		method.addParameter(new Parameter( JDKFullyQualifiedJavaType.getIntInstance() , "number" ));
		method.addParameter(new Parameter( JDKFullyQualifiedJavaType.getStringInstance() , "name" ));
		method.addParameter(new Parameter( JDKFullyQualifiedJavaType.getBooleanInstance() , "error" ));
		method.addParameter(new Parameter( JDKFullyQualifiedJavaType.getCharInstance() , "RGB" ));
		inner.addMethod(method);
		
		
		methodDecoration = new MethodDecoration ();
		methodDecoration.setVisibility(JavaVisibility.PUBLIC);
		method = new Method("method0",methodDecoration);
		inner.addMethod(method);
		
		methodDecoration = new MethodDecoration ();
		methodDecoration.setVisibility(JavaVisibility.PRIVATE);
		method = new Method("method1",methodDecoration);
		inner.addMethod(method);
		
		methodDecoration = new MethodDecoration ();
		methodDecoration.setVisibility(JavaVisibility.PROTECTED);
		method = new Method("method2",methodDecoration);
		inner.addMethod(method);
		
		methodDecoration = new MethodDecoration ();
		methodDecoration.setVisibility(JavaVisibility.DEFAULT);
		method = new Method("method3",methodDecoration);
		inner.addMethod(method);
		
		methodDecoration = new MethodDecoration ();
		methodDecoration.setVisibility(JavaVisibility.PUBLIC);
		methodDecoration.setStatic(true);
		method = new Method("method4",methodDecoration);
		inner.addMethod(method);
		
		methodDecoration = new MethodDecoration ();
		methodDecoration.setVisibility(JavaVisibility.PRIVATE);
		methodDecoration.setStatic(true);
		method = new Method("method4",methodDecoration);
		inner.addMethod(method);
		
		methodDecoration = new MethodDecoration ();
		methodDecoration.setVisibility(JavaVisibility.PRIVATE);
		methodDecoration.setFinal(true);
		method = new Method("method5",methodDecoration);
		inner.addMethod(method);
		
		methodDecoration = new MethodDecoration ();
		methodDecoration.setVisibility(JavaVisibility.PRIVATE);
		methodDecoration.setFinal(true);
		methodDecoration.setAbstract(true);
		method = new Method("method6",methodDecoration);
		inner.addMethod(method);
		
		
		int indentLevel = 0 ;
		String s = inner.compiledContent( indentLevel ).toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "/***comment*/publicenum"+super.TEST_INNER_ENUM_NAME+"{RED(1,\"红色\",true,'R'),GREEN(2,\"绿色\",false,'G'),YELLOW(3,\"黄色\",false,'B');/***@paramnumber{@code   int}*@paramname{@code   String}*@paramerror{@code   Boolean}*@paramRGB{@code   char}** @see    String*/private"+super.TEST_INNER_ENUM_NAME+"(intnumber,Stringname,Booleanerror,charRGB){//TODOAuto-generatedmethodstubreturn;}/***@return*/publicvoidmethod0(){//TODOAuto-generatedmethodstubreturn;}/***@return*/privatevoidmethod1(){//TODOAuto-generatedmethodstubreturn;}/***@return*/protectedvoidmethod2(){//TODOAuto-generatedmethodstubreturn;}/***@return*/voidmethod3(){//TODOAuto-generatedmethodstubreturn;}/***@return*/publicstaticvoidmethod4(){//TODOAuto-generatedmethodstubreturn;}/***@return*/privatefinalvoidmethod5(){//TODOAuto-generatedmethodstubreturn;}/***@return*/privateabstractvoidmethod6();}";
		nS = StringUtility.getStringHash(nS);
		
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public enum Vrmgkkuk {
	    RED( 1, "红色", true, 'R' ), 
	    GREEN( 2, "绿色", false, 'G' ), 
	    YELLOW( 3, "黄色", false, 'B' );


	    /**
	     * @param number {int} 
	     * @param name {String} 
	     * @param error {Boolean} 
	     * @param RGB {char} 
	     * 
	     */
	    private Vrmgkkuk( int number, String name, Boolean error, char RGB ){
	        // TODO Auto-generated method stub

	        return ;
	    }
	}
}
