package com.honey.compilation.java;

import java.util.Collection;

import com.honey.core.compiler.CompilationException;
import com.honey.compilation.java.ClassDecoration.JavaType;
import com.honey.core.types.ExceptionFullyQualifiedJavaType;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

public  class TestClass  extends BaseTestCase {
	
	public void test0() throws CompilationException{
		Clazz clazz = createClass();
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.DEFAULT ));
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;class"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz = createClass();
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC ));
		s = clazz.compiledContent().toString() ;
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz = createClass();
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,false ));
		s = clazz.compiledContent().toString() ;
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz = createClass();
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,false,true ));
		s = clazz.compiledContent().toString() ;
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;publicfinalclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz = createClass();
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,true ));
		s = clazz.compiledContent().toString() ;
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;publicfinalclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		
		clazz = createClass();
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.ABSTRACTCLASS ));
		s = clazz.compiledContent().toString() ;
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;publicabstractclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz = createClass();
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.INTERFACE ));
		s = clazz.compiledContent().toString() ;
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz = createClass();
		try{
			clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PRIVATE ));
		}catch (CompilationException e ){
			assertTrue(true);
		}
		clazz = createClass();
		try{
			clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PROTECTED ));
		}catch (CompilationException e ){
			assertTrue(true);
		}
	}
	
	public void test1() throws CompilationException{
		 Clazz clazz = createClass();
		 assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		 assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);
		 assertEquals(clazz.getType(), new FullyQualifiedJavaType( TEST_PACKAGE + TEST_TOP_CLASS_NAME ));
		 assertEquals(clazz.getAnnotations().size(), 0);
		 assertEquals(clazz.getClassComments().size(), 0);
		 assertEquals(clazz.getFields().size(), 0);
		 assertEquals(clazz.getImportedTypes().size(), 0);
		 assertEquals(clazz.getStaticImports().size(), 0);
		 assertEquals(clazz.getInnerClasses().size(), 0);
		 assertEquals(clazz.getInnerEnums().size(), 0);
		 assertEquals(clazz.getMethods().size(), 1);
		 assertEquals(clazz.getStaticBlocks().size(), 0);
		 assertEquals(clazz.getSuperInterfaceTypes().size(), 0);

		 assertNull(clazz.getSuperClass());
		 assertNull(clazz.getGeneric());
		 String s = clazz.compiledContent().toString() ;
		 s = StringUtility.getStringHash(s);
		 String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		 nS = StringUtility.getStringHash(nS);
		 assertEquals(s, nS);
		
	}


	public void test2() throws CompilationException{
		Clazz clazz = createClass();
		clazz.addField(new Field("field1",JDKFullyQualifiedJavaType.getStringInstance()));
		clazz.addField(new Field("field2",JDKFullyQualifiedJavaType.getIntegerInstance()));
		clazz.addField(new Field("field3",JDKFullyQualifiedJavaType.getDoubleInstance()));
		clazz.addField(new Field("field4",JDKFullyQualifiedJavaType.getFloatInstance()));
		clazz.addField(new Field("field5",JDKFullyQualifiedJavaType.getLongInstance()));
		clazz.addField(new Field("field6",JDKFullyQualifiedJavaType.getCharacterInstance()));
		clazz.addField(new Field("field7",JDKFullyQualifiedJavaType.getBooleanInstance()));
		clazz.addField(new Field("field8",JDKFullyQualifiedJavaType.getByteInstance()));
		clazz.addField(new Field("field9",JDKFullyQualifiedJavaType.getListInstance()));
		clazz.addField(new Field("field10",JDKFullyQualifiedJavaType.getMapInstance()));
		clazz.addField(new Field("field11",JDKFullyQualifiedJavaType.getHashMapInstance()));
		clazz.addField(new Field("field12",JDKFullyQualifiedJavaType.getArrayListInstance()));
		clazz.addField(new Field("field13",JDKFullyQualifiedJavaType.getDateInstance()));
		clazz.addField(new Field("field14",JDKFullyQualifiedJavaType.getObjectInstance()));
		clazz.addField(new Field("field15",new FullyQualifiedJavaType("java.util.List<String>")));
		clazz.addField(new Field("field16",new FullyQualifiedJavaType("java.util.Map<Object>")));
		clazz.addField(new Field("field17",new FullyQualifiedJavaType("java.util.HashMap<?>")));
		Field field = new Field("field18",new FullyQualifiedJavaType("java.util.HashMap<?>"));
		field.addJavaDocLine("test Field");
		clazz.addField(field);
		
		
		
		field = new Field("field19",new FullyQualifiedJavaType("java.util.HashMap<?>"));
		field.addJavaDocLine("test Field");
		field.addAnnotation("@NotNull");
		field.addAnnotation("SuppressWarnings");
		clazz.addField(field);
		
		field = new Field("field20",new FullyQualifiedJavaType("java.util.HashMap<?>"));
		field.addJavaDocLine("test Field");
		field.addAnnotation("@NotNull");
		field.addAnnotation("SuppressWarnings");
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC));
		clazz.addField(field);
		
		field = new Field("field21",new FullyQualifiedJavaType("java.util.HashMap<?>"));
		field.setDecoration(new Decoration(JavaVisibility.DEFAULT));
		clazz.addField(field);
		
		field = new Field("field22",new FullyQualifiedJavaType("java.util.HashMap<?>"));
		field.setDecoration(new Decoration(JavaVisibility.PROTECTED));
		clazz.addField(field);
		
		field = new Field("field23",new FullyQualifiedJavaType("java.util.HashMap<?>"));
		field.setDecoration(new Decoration(JavaVisibility.PROTECTED,true,false));
		clazz.addField(field);
		
		field = new Field("field24",new FullyQualifiedJavaType("java.util.HashMap<?>"));
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC,false,true));
		clazz.addField(field);
		
		field = new Field("field25",new FullyQualifiedJavaType("java.util.HashMap<?>"));
		field.setDecoration(new Decoration(JavaVisibility.DEFAULT,true,true));
		clazz.addField(field);
		
		field = new Field("field26",new FullyQualifiedJavaType("java.util.HashMap<?>"));
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC,true,true));
		field.setInitializationString("new HashMap();");
		clazz.addField(field);

		FieldDecoration fieldDecoration = new FieldDecoration ();
		fieldDecoration.setVisibility(JavaVisibility.PRIVATE);
		fieldDecoration.setVolatile(true);
		field = new Field("field27",JDKFullyQualifiedJavaType.getIntInstance());
		field.setDecoration(fieldDecoration);
		clazz.addField(field);
		
		assertEquals(clazz.getFields().size(), 27);
		
		assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);
		assertEquals(clazz.getType(), new FullyQualifiedJavaType( TEST_PACKAGE + TEST_TOP_CLASS_NAME ));
		assertEquals(clazz.getAnnotations().size(), 0);
		assertEquals(clazz.getClassComments().size(), 0);
		assertEquals(clazz.getImportedTypes().size(), 0);
		assertEquals(clazz.getStaticImports().size(), 0);
		assertEquals(clazz.getInnerClasses().size(), 0);
		assertEquals(clazz.getInnerEnums().size(), 0);
		assertEquals(clazz.getMethods().size(), 1);
		assertEquals(clazz.getStaticBlocks().size(), 0);
		assertEquals(clazz.getSuperInterfaceTypes().size(), 0);
		assertNull(clazz.getSuperClass());
		assertNull(clazz.getGeneric());
		printResult(clazz);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;importjava.util.Date;importjava.util.HashMap;importjava.util.List;importjava.util.Map;publicclass"+this.TEST_TOP_CLASS_NAME+"{privateStringfield1;privateIntegerfield2;privateDoublefield3;privateFloatfield4;privateLongfield5;privateCharacterfield6;privateBooleanfield7;privateBytefield8;privateListfield9;privateMapfield10;privateHashMapfield11;privateArrayListfield12;privateDatefield13;privateObjectfield14;privateList<String>field15;privateMap<Object>field16;privateHashMap<?>field17;/**testField*/privateHashMap<?>field18;/**testField*/@NotNull@SuppressWarningsprivateHashMap<?>field19;/**testField*/@NotNull@SuppressWarningspublicHashMap<?>field20;HashMap<?>field21;protectedHashMap<?>field22;protectedstaticHashMap<?>field23;publicfinalHashMap<?>field24;staticfinalHashMap<?>field25;publicstaticfinalHashMap<?>field26=newHashMap();;privatevolatileintfield27;/***默认的构造函数***/public"+this.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		           //
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		
	}

	public void test3() throws CompilationException{
		Clazz clazz = createClass();
		clazz.addSerializable();
		clazz.addField(new Field("testField1",JDKFullyQualifiedJavaType.getStringInstance()));
		printResult(clazz);
	}

	public void test4() throws CompilationException{
		Clazz clazz = createClass();
		clazz.addField(new Field("testField1",JDKFullyQualifiedJavaType.getStringInstance()));
		Method method = new Method("method1",new  MethodDecoration(JavaVisibility.PUBLIC));
		method.addBodyLine("//");
		clazz.addMethod(method);
		method = new Method("method2",new  MethodDecoration(JavaVisibility.PRIVATE));
		method.addBodyLine("//");
		clazz.addMethod(method);
		
		method = new Method("method3",new  MethodDecoration(JavaVisibility.PROTECTED));
		method.addBodyLine("//");
		clazz.addMethod(method);
		
		method = new Method("method4",new  MethodDecoration(JavaVisibility.DEFAULT));
		method.addBodyLine("//");
		clazz.addMethod(method);
		
		method = new Method("method5",new  MethodDecoration(JavaVisibility.PUBLIC,true,false));
		method.addBodyLine("//");
		clazz.addMethod(method);
		
		method = new Method("method6",new  MethodDecoration(JavaVisibility.PUBLIC,false,true));
		method.addBodyLine("//");
		clazz.addMethod(method);
		
		method = new Method("method7",new  MethodDecoration(JavaVisibility.PUBLIC,true,true));
		method.addBodyLine("//");
		clazz.addMethod(method);
		
		
		method = new Method("method8",new  MethodDecoration(JavaVisibility.PRIVATE,true,true));
		method.addBodyLine("//");
		clazz.addMethod(method);
		
		method = new Method("method9",new  MethodDecoration(JavaVisibility.PRIVATE,true,true));
		method.addBodyLine("int i =234");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		clazz.addMethod(method);
		
		method = new Method("method10",new  MethodDecoration(JavaVisibility.PRIVATE,true,true));
		method.addBodyLine("return \"NULL\"");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		clazz.addMethod(method);
		
		
		method = new Method("method11",new  MethodDecoration(JavaVisibility.PRIVATE,true,true));
		method.addBodyLine("return \"NULL\"");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		method = new Method("method12",new  MethodDecoration(JavaVisibility.PRIVATE,true,true));
		method.addBodyLine("int i =234");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance(),ExceptionFullyQualifiedJavaType.getNullPointerExceptionInstance());
		clazz.addMethod(method);
		
		assertEquals(clazz.getMethods().size(), 13);
		
		assertEquals(clazz.getFields().size(), 1);
		assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);
		assertEquals(clazz.getType(), new FullyQualifiedJavaType( TEST_PACKAGE + TEST_TOP_CLASS_NAME ));
		assertEquals(clazz.getAnnotations().size(), 0);
		assertEquals(clazz.getClassComments().size(), 0);
		assertEquals(clazz.getImportedTypes().size(), 0);
		assertEquals(clazz.getStaticImports().size(), 0);
		assertEquals(clazz.getInnerClasses().size(), 0);
		assertEquals(clazz.getInnerEnums().size(), 0);		
		assertEquals(clazz.getStaticBlocks().size(), 0);
		assertEquals(clazz.getSuperInterfaceTypes().size(), 0);
		assertNull(clazz.getSuperClass());
		assertNull(clazz.getGeneric());

		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{privateStringtestField1;/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*/publicvoidmethod1(){//;}/***@return*/privatevoidmethod2(){//;}/***@return*/protectedvoidmethod3(){//;}/***@return*/voidmethod4(){//;}/***@return*/publicstaticvoidmethod5(){//;}/***@return*/publicfinalvoidmethod6(){//;}/***@return*/publicstaticfinalvoidmethod7(){//;}/***@return*/privatestaticfinalvoidmethod8(){//;}/***@returnString*/privatestaticfinalStringmethod9(){inti=234;}/***@returnString*/privatestaticfinalStringmethod10(){return\"NULL\";}/***@returnString*/@Deprecated@OverrideprivatestaticfinalStringmethod11(){return\"NULL\";}/***@returnString*@exception{@codejava.lang.Exception}*@exception{@codeNullPointerException}*/privatestaticfinalStringmethod12()throwsException,NullPointerException{inti=234;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}

	public void test5() throws CompilationException{
		Clazz clazz = createClass();
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,true ));
		clazz.setGeneric(JDKFullyQualifiedJavaType.getStringInstance()) ;
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicfinalclass"+super.TEST_TOP_CLASS_NAME+"<String>{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz = createClass();
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,true ));
		clazz.setGeneric(JDKFullyQualifiedJavaType.getObjectInstance()) ;
		s = clazz.compiledContent().toString() ;
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;publicfinalclass"+super.TEST_TOP_CLASS_NAME+"<Object>{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test6() throws CompilationException{
		Clazz clazz = createClass();
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,true ));
		clazz.setGeneric(JDKFullyQualifiedJavaType.getObjectInstance()) ;
		clazz.setSuperClass(JDKFullyQualifiedJavaType.getObjectInstance());
		clazz.setSuperClass(JDKFullyQualifiedJavaType.getArrayListInstance());
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;publicfinalclass"+super.TEST_TOP_CLASS_NAME+"<Object>extendsArrayList{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz = createClass();
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,true ));
		clazz.setGeneric(JDKFullyQualifiedJavaType.getObjectInstance()) ;
		clazz.setSuperClass(JDKFullyQualifiedJavaType.getObjectInstance());
		clazz.setSuperClass(JDKFullyQualifiedJavaType.getArrayListInstance());
		
		clazz.addSuperInterface(JDKFullyQualifiedJavaType.getListInstance());
		clazz.addSuperInterface(JDKFullyQualifiedJavaType.getMapInstance());
		s = clazz.compiledContent().toString() ;
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;importjava.util.List;importjava.util.Map;publicfinalclass"+super.TEST_TOP_CLASS_NAME+"<Object>extendsArrayListimplementsList,Map{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
	}

	public void test7() throws CompilationException{
		Clazz clazz = createClass();
		clazz.addAnnotation("@Deprecated","SuppressWarnings");
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;@Deprecated@SuppressWarningspublicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz = createClass();
		clazz.addAnnotation("@Deprecated","SuppressWarnings");
		clazz.addImportedType(JDKFullyQualifiedJavaType.getDateInstance());
		clazz.addImportedType(JDKFullyQualifiedJavaType.getFileInstance());
		s = clazz.compiledContent().toString() ;
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;importjava.io.File;importjava.util.Date;@Deprecated@SuppressWarningspublicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz = createClass();
		clazz.addFileComment("Test");
		clazz.addFileComment("");
		clazz.addFileComment("测试用例生成的类");
		
		s = clazz.compiledContent().toString() ;
		hash = StringUtility.getStringHash(s);
		nS = "/***Test**测试用例生成的类*/packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz = createClass();
		clazz.addClassComment("test testcase");
		clazz.addClassComment("");
		clazz.addClassComment("test testcase");
		clazz.addClassComment("test testcase");
		
		s = clazz.compiledContent().toString() ;
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;/***testtestcase**testtestcase*testtestcase*/publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	} 
	public void test8() throws CompilationException{
		Clazz clazz = createClass();
		clazz.addField(new Field("testField1",JDKFullyQualifiedJavaType.getStringInstance()));
		Method method = new Method("method1",new  MethodDecoration(JavaVisibility.PUBLIC));
		method.addBodyLine("//");
		
		method.addParameter(
				new Parameter(JDKFullyQualifiedJavaType.getIntegerInstance() ,"i"), 
				new Parameter(JDKFullyQualifiedJavaType.getStringInstance() ,"str")
		);
		Parameter par = new Parameter(JDKFullyQualifiedJavaType.getIntegerInstance() ,"ii",true);
		par.addJavaDocLine("测试参数注释");
		method.addParameter(par);
		par = new Parameter(JDKFullyQualifiedJavaType.getIntegerInstance() ,"iii",true);
		par.addJavaDocLine("测试参数注释");
		par.setComment("comment");
		method.addParameter(par);
		par = new Parameter(JDKFullyQualifiedJavaType.getIntegerInstance() ,"iiii",true);
		par.addJavaDocLine("测试参数注释");
		par.setComment("comment");
		method.addParameter(par);
		
		par = new Parameter(JDKFullyQualifiedJavaType.getIntegerInstance() ,"iiiii",true);
		par.addJavaDocLine("测试参数注释");
		par.setComment("comment");
		par.setDecoration(new Decoration(JavaVisibility.PUBLIC,true,true));
		method.addParameter(par);
		
		clazz.addMethod(method);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{privateStringtestField1;/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@parami{@codeInteger}*@paramstr{@codeString}*@paramii{@codeint}测试参数注释*@paramiii{@codeint}测试参数注释*@paramiiii{@codeint}测试参数注释*@paramiiiii{@codeint}测试参数注释*@return*@seeString*/publicvoidmethod1(Integeri,Stringstr,intii,intiii/*comment*/,intiiii/*comment*/,finalintiiiii/*comment*/){//;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test9() throws CompilationException{
		Clazz clazz = createClass();
		clazz.setAutoAddGetterMethod(true);
		clazz.setAutoAddSetterMethod(true);
		clazz.addField(new Field("testField1",JDKFullyQualifiedJavaType.getStringInstance()));
		clazz.addField(new Field("testField2",JDKFullyQualifiedJavaType.getIntegerInstance()));
		clazz.addField(new Field("testField3",JDKFullyQualifiedJavaType.getIntInstance()));
		Field field = new Field("testField4",JDKFullyQualifiedJavaType.getIntegerInstance());
		field.setBaseType(true);
		clazz.addField(field);
		
		field = new Field("testField5",JDKFullyQualifiedJavaType.getIntegerInstance());
		field.setBaseType(true);
		field.setDecoration(new Decoration(JavaVisibility.PRIVATE,false,true));
		clazz.addField(field);

		field = new Field("testField6",JDKFullyQualifiedJavaType.getIntegerInstance());
		field.setBaseType(true);
		field.setDecoration(new Decoration(JavaVisibility.PRIVATE,true,true));
		clazz.addField(field);

		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{privateStringtestField1;privateIntegertestField2;privateinttestField3;privateinttestField4;privatefinalinttestField5;privatestaticfinalinttestField6;/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***获取{@codetestField1}属性的值*@returnString*/publicStringgetTestField1(){returnthis.testField1;}/***获取{@codetestField2}属性的值*@returnInteger*/publicIntegergetTestField2(){returnthis.testField2;}/***获取{@codetestField3}属性的值*@returnint*/publicintgetTestField3(){returnthis.testField3;}/***获取{@codetestField4}属性的值*@returnInteger*/publicintgetTestField4(){returnthis.testField4;}/***获取{@codetestField5}属性的值*@returnInteger*/publicintgetTestField5(){returnthis.testField5;}/***设定{@codetestField1}属性的值*@paramtestField1{@codeString}*@return*@seeString*/publicvoidsetTestField1(StringtestField1){this.testField1=testField1;}/***设定{@codetestField2}属性的值*@paramtestField2{@codeInteger}*@return*/publicvoidsetTestField2(IntegertestField2){this.testField2=testField2;}/***设定{@codetestField3}属性的值*@paramtestField3{@codeint}*@return*/publicvoidsetTestField3(inttestField3){this.testField3=testField3;}/***设定{@codetestField4}属性的值*@paramtestField4{@codeint}*@return*/publicvoidsetTestField4(inttestField4){this.testField4=testField4;}}";
		           //packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{privateStringtestField1;privateIntegertestField2;privateinttestField3;privateinttestField4;privatefinalinttestField5;privatestaticfinalinttestField6;/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***获取{@codetestField1}属性的值*@returnString*/publicStringgetTestField1(){returnthis.testField1;}/***获取{@codetestField2}属性的值*@returnInteger*/publicIntegergetTestField2(){returnthis.testField2;}/***获取{@codetestField3}属性的值*@returnint*/publicintgetTestField3(){returnthis.testField3;}/***获取{@codetestField4}属性的值*@returnInteger*/publicintgetTestField4(){returnthis.testField4;}/***获取{@codetestField5}属性的值*@returnInteger*/publicintgetTestField5(){returnthis.testField5;}/***设定{@codetestField1}属性的值*@paramtestField1{@codeString}*@return*@seeString*/publicvoidsetTestField1(StringtestField1){this.testField1=testField1;}/***设定{@codetestField2}属性的值*@paramtestField2{@codeInteger}*@return*/publicvoidsetTestField2(IntegertestField2){this.testField2=testField2;}/***设定{@codetestField3}属性的值*@paramtestField3{@codeint}*@return*/publicvoidsetTestField3(inttestField3){this.testField3=testField3;}/***设定{@codetestField4}属性的值*@paramtestField4{@codeint}*@return*/publicvoidsetTestField4(inttestField4){this.testField4=testField4;}}
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}
	
	
	public void test10() throws CompilationException{
		Clazz clazz = createClass();
		assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);
		
		MethodDecoration dec = new MethodDecoration();
		dec.setSynchronized(true);
		Method method = new Method("testSynchronizedMethod1",dec);
		method.addBodyLine("int i =6 + 6;");
		clazz.addMethod(method);
		
		dec = new MethodDecoration(JavaVisibility.PUBLIC);
		dec.setSynchronized(true);
		method = new Method("testSynchronizedMethod2",dec);
		method.addBodyLine("int i =6 + 6;");
		clazz.addMethod(method);
		
		dec = new MethodDecoration(JavaVisibility.PUBLIC,true,false);
		dec.setSynchronized(true);
		method = new Method("testSynchronizedMethod3",dec);
		method.addBodyLine("int i =6 + 6;");
		clazz.addMethod(method);
		
		dec = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		dec.setSynchronized(true);
		method = new Method("testSynchronizedMethod4",dec);
		method.addBodyLine("int i =6 + 6;");
		clazz.addMethod(method);
		
		dec = new MethodDecoration(JavaVisibility.PRIVATE,true,true);
		dec.setSynchronized(true);
		method = new Method("testSynchronizedMethod4",dec);
		method.addBodyLine("int i =6 + 6;");
		method.addBodyLine("return i");
		method.setReturnType(JDKFullyQualifiedJavaType.getIntInstance());
		clazz.addMethod(method);

		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+this.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+this.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*/synchronizedvoidtestSynchronizedMethod1(){inti=6+6;}/***@return*/publicsynchronizedvoidtestSynchronizedMethod2(){inti=6+6;}/***@return*/publicsynchronizedstaticvoidtestSynchronizedMethod3(){inti=6+6;}/***@return*/publicsynchronizedstaticfinalvoidtestSynchronizedMethod4(){inti=6+6;}}";

		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}
	
	public void test11() throws CompilationException{
		Clazz clazz = createClass();
		
		
		Field field = new Field("field0",JDKFullyQualifiedJavaType.getLongInstance() );
		field.setInitializationString("3L");
		clazz.addField(field);
		
		field = new Field("field1",JDKFullyQualifiedJavaType.getLongInstance() );
		field.setInitializationString("3l");
		clazz.addField(field);
		
		field = new Field("field2",JDKFullyQualifiedJavaType.getLongInstance() );
		field.setInitializationString("3");
		clazz.addField(field);
		
		
		
		field = new Field("field3",JDKFullyQualifiedJavaType.getFloatInstance() );
		field.setInitializationString("3F");
		clazz.addField(field);
		
		field = new Field("field4",JDKFullyQualifiedJavaType.getFloatInstance() );
		field.setInitializationString("3f");
		clazz.addField(field);
		
		field = new Field("field5",JDKFullyQualifiedJavaType.getFloatInstance() );
		field.setInitializationString("3");
		clazz.addField(field);
		
		
		field = new Field("field6",JDKFullyQualifiedJavaType.getDoubleInstance() );
		field.setInitializationString("3D");
		clazz.addField(field);
		
		field = new Field("field7",JDKFullyQualifiedJavaType.getDoubleInstance() );
		field.setInitializationString("3d");
		clazz.addField(field);
		
		field = new Field("field8",JDKFullyQualifiedJavaType.getDoubleInstance() );
		field.setInitializationString("3");
		clazz.addField(field);
		
		field = new Field("field9",JDKFullyQualifiedJavaType.getShortInstance() );
		field.setInitializationString("(short)3");
		clazz.addField(field);
		
		field = new Field("field10",JDKFullyQualifiedJavaType.getShortInstance() );
		field.setInitializationString("3");
		clazz.addField(field);
		
		field = new Field("field11",JDKFullyQualifiedJavaType.getShortArrayInstance() );
		field.setInitializationString("new Short[]{2,1,(short)4,6}");
		clazz.addField(field);
		
		field = new Field("field12",JDKFullyQualifiedJavaType.getLongArrayInstance() );
		field.setInitializationString("new Long[]{2,1l,4L,6}");
		clazz.addField(field);
		
		field = new Field("field13",JDKFullyQualifiedJavaType.getIntArrayInstance() );
		field.setInitializationString("new int[]{2,1,4,6}");
		clazz.addField(field);
		
		field = new Field("field14",JDKFullyQualifiedJavaType.getIntegerArrayInstance() );
		field.setInitializationString("new Integer[]{2,1,4,6}");
		clazz.addField(field);
		
		field = new Field("field15",JDKFullyQualifiedJavaType.getDoubleArrayInstance() );
		field.setInitializationString("new Double[]{2,1d,4D,8}");
		clazz.addField(field);
		
		field = new Field("field16",JDKFullyQualifiedJavaType.getFloatArrayInstance() );
		field.setInitializationString("new Float[]{2,1f,4F,8}");
		clazz.addField(field);
		
		field = new Field("field17",JDKFullyQualifiedJavaType.getStringArrayInstance() );
		field.setInitializationString("new String[]{\"2\",\"1\",\"4\",\"8\"}");
		clazz.addField(field);
		
		field = new Field("field18",JDKFullyQualifiedJavaType.getLongArrayInstance() );
		field.setInitializationString("new Long[]{}");
		clazz.addField(field);
		
		field = new Field("field19",JDKFullyQualifiedJavaType.getBooleanArrayInstance() );
		field.setInitializationString("new Boolean[]{2,1,-4,false,true}");
		clazz.addField(field);
		
		field = new Field("field20",JDKFullyQualifiedJavaType.getCharArrayInstance() );
		field.setInitializationString("new char[]{2,1,4}");
		clazz.addField(field);
		
		field = new Field("field21",JDKFullyQualifiedJavaType.getByteArrayInstance() );
		field.setInitializationString("new Byte[]{2,-1,4,0,1,0}");
		clazz.addField(field);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{privateLongfield0=3L;privateLongfield1=3l;privateLongfield2=3L;privateFloatfield3=3F;privateFloatfield4=3f;privateFloatfield5=3F;privateDoublefield6=3D;privateDoublefield7=3d;privateDoublefield8=3D;privateShortfield9=(short)3;privateShortfield10=(short)3;privateShort[]field11=newShort[]{(short)2,(short)1,(short)4,(short)6,};privateLong[]field12=newLong[]{2L,1l,4L,6L,};privateint[]field13=newint[]{2,1,4,6,};privateInteger[]field14=newInteger[]{2,1,4,6,};privateDouble[]field15=newDouble[]{2D,1d,4D,8D,};privateFloat[]field16=newFloat[]{2F,1f,4F,8F,};privateString[]field17=newString[]{\"2\",\"1\",\"4\",\"8\"};privateLong[]field18=newLong[]{};privateBoolean[]field19=newBoolean[]{true,true,false,false,true,};privatechar[]field20=newchar[]{'2','1','4',};privateByte[]field21=newByte[]{1,0,1,0,1,0,};/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		System.out.println(nS);
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(clazz);
	}
	
	public void test12() throws CompilationException{
		Clazz clazz = createClass();

		Field field =  new Field("field22",JDKFullyQualifiedJavaType.getStringInstance());
		field.setInitializationString("\"ABCD\"");
		clazz.addField(field);
		
		field = new Field("field23",JDKFullyQualifiedJavaType.getCharacterInstance());
		field.setInitializationString("c");
		clazz.addField(field);
		
		field = new Field("field24",JDKFullyQualifiedJavaType.getCharacterInstance());
		field.setInitializationString("'c");
		clazz.addField(field);
		
		field = new Field("field25",JDKFullyQualifiedJavaType.getCharacterInstance());
		field.setInitializationString("c'");
		clazz.addField(field);
		

		field = new Field("field26",JDKFullyQualifiedJavaType.getCharacterInstance());
		field.setInitializationString("'c'");
		clazz.addField(field);
		

		field = new Field("field27",JDKFullyQualifiedJavaType.getCharacterInstance());
		field.setInitializationString("''");
		clazz.addField(field);
		
		field = new Field("field28",JDKFullyQualifiedJavaType.getStringInstance());
		field.setInitializationString("ABCDEF");
		clazz.addField(field);
		
		field = new Field("field29",JDKFullyQualifiedJavaType.getStringInstance());
		field.setInitializationString("\"ABCDEF");
		clazz.addField(field);
		
		field = new Field("field30",JDKFullyQualifiedJavaType.getStringInstance());
		field.setInitializationString("ABCDEF\"");
		clazz.addField(field);
		

		field = new Field("field31",JDKFullyQualifiedJavaType.getStringInstance());
		field.setInitializationString("\"ABCDEF\"");
		clazz.addField(field);
		

		field = new Field("field32",JDKFullyQualifiedJavaType.getStringInstance());
		field.setInitializationString("\"\"");
		clazz.addField(field);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{privateStringfield22=\"ABCD\";privateCharacterfield23='c';privateCharacterfield24='c';privateCharacterfield25='c';privateCharacterfield26='c';privateCharacterfield27=''';privateStringfield28=\"ABCDEF\";privateStringfield29=\"ABCDEF\";privateStringfield30=\"ABCDEF\";privateStringfield31=\"ABCDEF\";privateStringfield32=\"\";/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		System.out.println(nS);
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(clazz);
	}
	
	public void test13() throws CompilationException{
		Clazz clazz = createClass();
		clazz.getClassDecoration().setVisibility(JavaVisibility.PUBLIC);
		MethodDecoration decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,true);
		decoration.setAbstract(true);
		Method method = new Method("method0",decoration);
		clazz.addMethod(method);
		
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*/publicvoidmethod0(){//TODOAuto-generatedmethodstubreturn;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test14() throws CompilationException{
		Clazz clazz =new Clazz(super.TEST_PACKAGE+super.TEST_TOP_CLASS_NAME+"<E>");
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,true ));
		clazz.setGeneric(JDKFullyQualifiedJavaType.getStringInstance()) ;
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicfinalclass"+super.TEST_TOP_CLASS_NAME+"<E>{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		
		clazz =new Clazz(super.TEST_PACKAGE+super.TEST_TOP_CLASS_NAME);
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,true ));
		clazz.setGeneric(new FullyQualifiedJavaType("E")) ;
		s = clazz.compiledContent().toString();
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;publicfinalclass"+super.TEST_TOP_CLASS_NAME+"<E>{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	
		
		clazz =new Clazz(super.TEST_PACKAGE+super.TEST_TOP_CLASS_NAME+"<? extends E>");
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC,JavaType.CLASS,true,true ));
		clazz.setGeneric(new FullyQualifiedJavaType("E")) ;
		s = clazz.compiledContent().toString();
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;publicfinalclass"+super.TEST_TOP_CLASS_NAME+"<? extends E>{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test15() throws CompilationException{
		Clazz clazz =new Clazz(super.TEST_PACKAGE+super.TEST_TOP_CLASS_NAME);
		clazz.setSuperClass("sdf<sd,sdf$ew>");
		System.out.println(clazz.compiledContent());
	}
	
	public void test16() throws CompilationException{
		Clazz clazz =new Clazz(super.TEST_PACKAGE+super.TEST_TOP_CLASS_NAME);
		clazz.addAfterBodyComment("Test after body");
		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{}/*Testafterbody*/";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
	}
	
}

