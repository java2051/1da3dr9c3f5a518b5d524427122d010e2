package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;
import com.honey.compilation.java.ClassDecoration.JavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

public class TestInnerClass extends BaseTestCase{
	
	public void test0() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}publicclass"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test1() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PRIVATE ));
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}privateclass"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	public void test2() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PROTECTED ));
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}protectedclass"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	public void test3() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.DEFAULT ));
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}class"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test4() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PROTECTED,false,true ));
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}protectedfinalclass"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test5() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PROTECTED,true,false ));
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}protectedstaticclass"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test6() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PROTECTED,true,true ));
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}protectedstaticfinalclass"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test7() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PUBLIC,true,true ));
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}publicstaticfinalclass"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test8() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PRIVATE,true,true ));
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}privatestaticfinalclass"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test9() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PRIVATE,true,true ));
		inner.addAnnotation("@Deprecated");
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}@Deprecatedprivatestaticfinalclass"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test10() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PRIVATE,true,true ));
		inner.addClassComment("inner class");
		
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***innerclass*/privatestaticfinalclass"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test11() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PRIVATE,true,true ));
		inner.addClassComment("inner class");
		inner.addField(new Field("field0",JDKFullyQualifiedJavaType.getIntInstance()));
		inner.addField(new Field("field1",JDKFullyQualifiedJavaType.getLongInstance()));
		inner.addField(new Field("field2",JDKFullyQualifiedJavaType.getDoubleInstance()));
		inner.addField(new Field("field3",JDKFullyQualifiedJavaType.getFloatInstance()));
		inner.addField(new Field("field4",JDKFullyQualifiedJavaType.getCharacterInstance()));
		inner.addField(new Field("field5",JDKFullyQualifiedJavaType.getBooleanInstance()));
		inner.addField(new Field("field6",JDKFullyQualifiedJavaType.getByteInstance()));
		inner.addField(new Field("field7",JDKFullyQualifiedJavaType.getShortInstance()));
		inner.addField(new Field("field8",JDKFullyQualifiedJavaType.getStringInstance()));
		inner.addField(new Field("field9",JDKFullyQualifiedJavaType.getObjectInstance()));

		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***innerclass*/privatestaticfinalclass"+super.TEST_INNER_CLASS_NAME+"{privateintfield0;privateLongfield1;privateDoublefield2;privateFloatfield3;privateCharacterfield4;privateBooleanfield5;privateBytefield6;privateShortfield7;privateStringfield8;privateObjectfield9;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test12() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PRIVATE,true,true ));
		inner.addClassComment("inner class");
		inner.addField(new Field("field0",JDKFullyQualifiedJavaType.getIntArrayInstance()));
		inner.addField(new Field("field1",JDKFullyQualifiedJavaType.getLongArrayInstance()));
		inner.addField(new Field("field2",JDKFullyQualifiedJavaType.getDoubleArrayInstance()));
		inner.addField(new Field("field3",JDKFullyQualifiedJavaType.getFloatArrayInstance()));
		inner.addField(new Field("field4",JDKFullyQualifiedJavaType.getCharacterArrayInstance()));
		inner.addField(new Field("field5",JDKFullyQualifiedJavaType.getBooleanArrayInstance()));
		inner.addField(new Field("field6",JDKFullyQualifiedJavaType.getByteArrayInstance()));
		inner.addField(new Field("field7",JDKFullyQualifiedJavaType.getShortArrayInstance()));
		inner.addField(new Field("field8",JDKFullyQualifiedJavaType.getStringArrayInstance()));
		inner.addField(new Field("field9",JDKFullyQualifiedJavaType.getObjectArrayInstance()));

		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***innerclass*/privatestaticfinalclass"+super.TEST_INNER_CLASS_NAME+"{privateint[]field0;privateLong[]field1;privateDouble[]field2;privateFloat[]field3;privateCharacter[]field4;privateBoolean[]field5;privateByte[]field6;privateShort[]field7;privateString[]field8;privateObject[]field9;}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test13() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PRIVATE,true,true ));
		inner.addClassComment("inner class");
		inner.addField(new Field("field0",JDKFullyQualifiedJavaType.getIntArrayInstance()));
		inner.addField(new Field("field1",JDKFullyQualifiedJavaType.getLongArrayInstance()));
		inner.addField(new Field("field2",JDKFullyQualifiedJavaType.getDoubleArrayInstance()));
		inner.addField(new Field("field3",JDKFullyQualifiedJavaType.getFloatArrayInstance()));
		inner.addField(new Field("field4",JDKFullyQualifiedJavaType.getCharacterArrayInstance()));
		inner.addField(new Field("field5",JDKFullyQualifiedJavaType.getBooleanArrayInstance()));
		inner.addField(new Field("field6",JDKFullyQualifiedJavaType.getByteArrayInstance()));
		inner.addField(new Field("field7",JDKFullyQualifiedJavaType.getShortArrayInstance()));
		inner.addField(new Field("field8",JDKFullyQualifiedJavaType.getStringArrayInstance()));
		inner.addField(new Field("field9",JDKFullyQualifiedJavaType.getObjectArrayInstance()));
		//inner.setAutoAddSetterAndGetterMethod(true);
		inner.setAutoAddGetterMethod(true);
		inner.setAutoAddSetterMethod(true);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***innerclass*/privatestaticfinalclass"+super.TEST_INNER_CLASS_NAME+"{privateint[]field0;privateLong[]field1;privateDouble[]field2;privateFloat[]field3;privateCharacter[]field4;privateBoolean[]field5;privateByte[]field6;privateShort[]field7;privateString[]field8;privateObject[]field9;/***获取{@codefield0}属性的值*@returnint[]*/publicint[]getField0(){returnthis.field0;}/***获取{@codefield1}属性的值*@returnLong[]*/publicLong[]getField1(){returnthis.field1;}/***获取{@codefield2}属性的值*@returnDouble[]*/publicDouble[]getField2(){returnthis.field2;}/***获取{@codefield3}属性的值*@returnFloat[]*/publicFloat[]getField3(){returnthis.field3;}/***获取{@codefield4}属性的值*@returnCharacter[]*/publicCharacter[]getField4(){returnthis.field4;}/***获取{@codefield5}属性的值*@returnBoolean[]*/publicBoolean[]getField5(){returnthis.field5;}/***获取{@codefield6}属性的值*@returnByte[]*/publicByte[]getField6(){returnthis.field6;}/***获取{@codefield7}属性的值*@returnShort[]*/publicShort[]getField7(){returnthis.field7;}/***获取{@codefield8}属性的值*@returnString[]*/publicString[]getField8(){returnthis.field8;}/***获取{@codefield9}属性的值*@returnjava.lang.Object[]*/publicObject[]getField9(){returnthis.field9;}/***设定{@codefield0}属性的值*@paramfield0{@codeint[]}*@return*/publicvoidsetField0(int[]field0){this.field0=field0;}/***设定{@codefield1}属性的值*@paramfield1{@codeLong[]}*@return*/publicvoidsetField1(Long[]field1){this.field1=field1;}/***设定{@codefield2}属性的值*@paramfield2{@codeDouble[]}*@return*/publicvoidsetField2(Double[]field2){this.field2=field2;}/***设定{@codefield3}属性的值*@paramfield3{@codeFloat[]}*@return*/publicvoidsetField3(Float[]field3){this.field3=field3;}/***设定{@codefield4}属性的值*@paramfield4{@codeCharacter[]}*@return*/publicvoidsetField4(Character[]field4){this.field4=field4;}/***设定{@codefield5}属性的值*@paramfield5{@codeBoolean[]}*@return*/publicvoidsetField5(Boolean[]field5){this.field5=field5;}/***设定{@codefield6}属性的值*@paramfield6{@codeByte[]}*@return*/publicvoidsetField6(Byte[]field6){this.field6=field6;}/***设定{@codefield7}属性的值*@paramfield7{@codeShort[]}*@return*/publicvoidsetField7(Short[]field7){this.field7=field7;}/***设定{@codefield8}属性的值*@paramfield8{@codeString[]}*@return*@seeString[]*/publicvoidsetField8(String[]field8){this.field8=field8;}/***设定{@codefield9}属性的值*@paramfield9{@codejava.lang.Object[]}*@return*@seejava.lang.Object[]*/publicvoidsetField9(Object[]field9){this.field9=field9;}}}";
		           //
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test14() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PRIVATE ));
		inner.addSuperInterface(JDKFullyQualifiedJavaType.getListInstance());
		inner.addSuperInterface(JDKFullyQualifiedJavaType.getMapInstance());
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.List;importjava.util.Map;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}privateclass"+super.TEST_INNER_CLASS_NAME+"implementsList,Map{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	public void test15() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PRIVATE ));
		inner.setSuperClass(JDKFullyQualifiedJavaType.getArrayListInstance());
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;import java.util.ArrayList;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}privateclass"+super.TEST_INNER_CLASS_NAME+"extendsArrayList{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	public void test16() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		clazz.addInnerClass(inner);
		inner.setClassDecoration(new InnerClassDecoration(JavaVisibility.PRIVATE ));
		inner.addInnerClass(createInnerClass());
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}privateclass"+super.TEST_INNER_CLASS_NAME+"{publicclass"+super.TEST_INNER_CLASS_NAME+"{}}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test17() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		inner.getClassDecoration().setJavaType(JavaType.INTERFACE);
		clazz.addInnerClass(inner);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}publicinterface"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test18() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		inner.getClassDecoration().setJavaType(JavaType.INTERFACE);
		clazz.addInnerClass(inner);
		inner.addSuperInterface(JDKFullyQualifiedJavaType.getListInstance());
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;import java.util.List;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}publicinterface"+super.TEST_INNER_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test19() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		inner.getClassDecoration().setJavaType(JavaType.INTERFACE);
		clazz.addInnerClass(inner);
		inner.setSuperClass(JDKFullyQualifiedJavaType.getListInstance());
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;import java.util.List;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}publicinterface"+super.TEST_INNER_CLASS_NAME+"extendsList{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test20() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		inner.getClassDecoration().setJavaType(JavaType.INTERFACE);
		clazz.addInnerClass(inner);
		inner.setSuperClass(JDKFullyQualifiedJavaType.getListInstance());
		Method method = new Method("method0");
		inner.addMethod(method);
		
		
		method = new Method("method1", new MethodDecoration(JavaVisibility.PROTECTED));
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getLongArrayInstance(),"par1" ));
		inner.addMethod(method);
		
		method = new Method("method2", new MethodDecoration(JavaVisibility.PROTECTED));
		Parameter par = new Parameter(JDKFullyQualifiedJavaType.getLongArrayInstance(),"par1" );
		par.setComment("comment");
		method.addParameter(par);
		
		par = new Parameter(JDKFullyQualifiedJavaType.getShortInstance(),"par2" );
		par.setComment("comment");
		par.setDecoration(new ParameterDecoration(false,true));
		method.addParameter(par);
		inner.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.List;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}publicinterface"+super.TEST_INNER_CLASS_NAME+"extendsList{/***@return*/publicvoidmethod0();/***@parampar1{@codeLong[]}*@return*/protectedvoidmethod1(Long[]par1);/***@parampar1{@codeLong[]}*@parampar2{@codeShort}*@return*/protectedvoidmethod2(Long[]par1/*comment*/,Short...par2/*comment*/);}}";
		            
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test21() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInnerClass();
		inner.getClassDecoration().setJavaType(JavaType.ABSTRACTCLASS);
		clazz.addInnerClass(inner);
		Method method = new Method("method0");
		inner.addMethod(method);
		
		MethodDecoration methodDecoration = new MethodDecoration(JavaVisibility.PROTECTED);
		methodDecoration.setAbstract(true);
		method = new Method("method2",methodDecoration );
		Parameter par = new Parameter(JDKFullyQualifiedJavaType.getLongArrayInstance(),"par1" );
		par.setComment("comment");
		method.addParameter(par);
		inner.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}publicabstractclass"+super.TEST_INNER_CLASS_NAME+"{/***@return*/publicvoidmethod0(){//TODOAuto-generatedmethodstubreturn;}/***@parampar1{@codeLong[]}*@return*/protectedabstractvoidmethod2(Long[]par1/*comment*/);}}" ;
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test22() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInterface();		
		clazz.addInnerClass(inner);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}publicinterface"+super.TEST_TOP_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test23() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInterface();	
		inner.addClassComment("这是一个内部接口类");
		clazz.addInnerClass(inner);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***这是一个内部接口类*/publicinterface"+super.TEST_TOP_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test24() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInterface();	
		inner.addClassComment("这是一个内部接口类");
		inner.addAnnotation("@Deprecated");
		clazz.addInnerClass(inner);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***这是一个内部接口类*/@Deprecatedpublicinterface"+super.TEST_TOP_CLASS_NAME+"{}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test25() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createInterface();	
		inner.addClassComment("这是一个内部接口类");
		inner.addAnnotation("@Deprecated");
		
		inner.addInnerClass(createInnerClass());
		
		clazz.addInnerClass(inner);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***这是一个内部接口类*/@Deprecatedpublicinterface"+super.TEST_TOP_CLASS_NAME+"{publicclass"+super.TEST_INNER_CLASS_NAME+"{}}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test26() throws CompilationException{
		Clazz clazz = createClass();
		InnerClass inner = createAbstractClass();	
		inner.addClassComment("这是一个内部虚类");
		inner.addAnnotation("@Deprecated");
		
		inner.addInnerClass(createInnerClass());
		
		clazz.addInnerClass(inner);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***这是一个内部虚类*/@Deprecatedpublicabstractclass"+super.TEST_TOP_CLASS_NAME+"{publicclass"+super.TEST_INNER_CLASS_NAME+"{}}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
}
