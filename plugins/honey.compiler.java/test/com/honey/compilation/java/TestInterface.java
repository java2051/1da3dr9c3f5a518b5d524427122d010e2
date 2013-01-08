package com.honey.compilation.java;

import java.util.Properties;

import com.honey.core.compiler.CompilationException;
import com.honey.core.types.ExceptionFullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

public class TestInterface extends BaseTestCase {

	public void test0() throws CompilationException {
		JavaCompilation inteface = new Interface(super.TEST_PACKAGE
				+ super.TEST_TOP_CLASS_NAME, new ClassDecoration(
				JavaVisibility.PUBLIC));
		String s = inteface.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"
				+ super.TEST_TOP_CLASS_NAME + "{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);

		inteface = new Interface(super.TEST_PACKAGE + super.TEST_TOP_CLASS_NAME,
				new ClassDecoration(JavaVisibility.DEFAULT));
		s = inteface.compiledContent().toString();
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;interface"
				+ super.TEST_TOP_CLASS_NAME + "{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);

		try {
			inteface = new Interface(super.TEST_PACKAGE
					+ super.TEST_TOP_CLASS_NAME, new ClassDecoration(
					JavaVisibility.PRIVATE));
		} catch (CompilationException e) {
			assertTrue(true);
		}

		try {
			inteface = new Interface(super.TEST_PACKAGE
					+ super.TEST_TOP_CLASS_NAME, new ClassDecoration(
					JavaVisibility.PROTECTED));
		} catch (CompilationException e) {
			assertTrue(true);
		}
	}

	public void test1() throws CompilationException {
		Interface clazz = createInterface();
		clazz.setSuperClass("Cloneable");

		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"+ super.TEST_TOP_CLASS_NAME + "extendsCloneable{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);

		clazz = createInterface();
		clazz.setSuperClass("Cloneable");
		clazz.addSuperInterface(JDKFullyQualifiedJavaType.getListInstance());
		s = clazz.compiledContent().toString();
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;publicinterface" + super.TEST_TOP_CLASS_NAME + "extendsCloneable{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test2() throws CompilationException {
		Interface clazz = createInterface();
		clazz.setSuperClass("Cloneable");
		clazz.addImportedType(JDKFullyQualifiedJavaType.getArrayListInstance());
		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;import java.util.ArrayList;publicinterface"+ super.TEST_TOP_CLASS_NAME + "extendsCloneable{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
		

		clazz = createInterface();
		clazz.setSuperClass("Cloneable");
		clazz.addSuperInterface(JDKFullyQualifiedJavaType.getListInstance());
		clazz.addClassComment("类注释");
		s = clazz.compiledContent().toString();
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;/***类注释*/publicinterface" + super.TEST_TOP_CLASS_NAME + "extendsCloneable{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);

		clazz = createInterface();
		clazz.setSuperClass("Cloneable");
		clazz.addSuperInterface(JDKFullyQualifiedJavaType.getListInstance());
		clazz.addClassComment("类注释");
		clazz.addStaticImport("java.lang.System.out",null,"java.lang.System.in");
		s = clazz.compiledContent().toString();
		hash = StringUtility.getStringHash(s);
		nS = "packagecom.honey.compilation.java;import static java.lang.System.in;import static java.lang.System.out;/***类注释*/publicinterface" + super.TEST_TOP_CLASS_NAME + "extendsCloneable{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);

	}
	
	public void test3() throws CompilationException {
		Interface clazz = createInterface();
		clazz.setSuperClass("Cloneable");

		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"+ super.TEST_TOP_CLASS_NAME + "extendsCloneable{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	public void test4() throws CompilationException {
		Interface clazz = createInterface();
		clazz.setSuperClass("Cloneable");
		clazz.addField(new Field("field0",JDKFullyQualifiedJavaType.getStringInstance()));
		clazz.addField(new Field("field1",JDKFullyQualifiedJavaType.getIntegerInstance()));
		clazz.addField(new Field("field2",JDKFullyQualifiedJavaType.getDoubleInstance()));
		clazz.addField(new Field("field3",JDKFullyQualifiedJavaType.getFloatInstance()));
		clazz.addField(new Field("field4",JDKFullyQualifiedJavaType.getShortInstance()));
		clazz.addField(new Field("field5",JDKFullyQualifiedJavaType.getLongInstance()));
		clazz.addField(new Field("field6",JDKFullyQualifiedJavaType.getCharacterInstance()));
		clazz.addField(new Field("field7",JDKFullyQualifiedJavaType.getCharInstance()));
		clazz.addField(new Field("field8",JDKFullyQualifiedJavaType.getObjectInstance()));
		clazz.addField(new Field("field9",JDKFullyQualifiedJavaType.getIntInstance()));
		clazz.addField(new Field("field10",JDKFullyQualifiedJavaType.getBooleanInstance()));
		
		
		
		Field field = new Field("field11",JDKFullyQualifiedJavaType.getIntInstance());
		field.setInitializationString("1");
		clazz.addField(field);
		
		field = new Field("field12",JDKFullyQualifiedJavaType.getLongInstance());
		field.setInitializationString("1");
		clazz.addField(field);
		
		field = new Field("field13",JDKFullyQualifiedJavaType.getDoubleInstance());
		field.setInitializationString("2");
		clazz.addField(field);
		
		field = new Field("field14",JDKFullyQualifiedJavaType.getFloatInstance());
		field.setInitializationString("2");
		clazz.addField(field);
		
		field = new Field("field16",JDKFullyQualifiedJavaType.getCharInstance());
		field.setInitializationString("A");
		clazz.addField(field);
		
		field = new Field("field16",JDKFullyQualifiedJavaType.getCharacterInstance());
		field.setInitializationString("A");
		clazz.addField(field);
		
		field = new Field("field17",JDKFullyQualifiedJavaType.getStringInstance());
		field.setInitializationString("A");
		clazz.addField(field);

		field = new Field("field18",JDKFullyQualifiedJavaType.getBooleanInstance());
		field.setInitializationString("1");
		clazz.addField(field);
		
		field = new Field("field19",JDKFullyQualifiedJavaType.getBooleanInstance());
		field.setInitializationString("true");
		clazz.addField(field);
		
		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"+super.TEST_TOP_CLASS_NAME+"extendsCloneable{Stringfield0=null;Integerfield1=0;Doublefield2=0.0D;Floatfield3=0.0F;Shortfield4=(short)0;Longfield5=0L;Characterfield6='0';charfield7='0';Objectfield8=null;intfield9=0;Booleanfield10=false;intfield11=1;Longfield12=1L;Doublefield13=2D;Floatfield14=2F;charfield16='A';Characterfield16='A';Stringfield17=\"A\";Booleanfield18=true;Booleanfield19=true;}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	public void test5() throws CompilationException {
		Interface clazz = createInterface();
		Field field = new Field("field0",JDKFullyQualifiedJavaType.getObjectInstance());
		field.setDecoration(new Decoration(JavaVisibility.PRIVATE));
		clazz.addField(field);
		
		field = new Field("field1",JDKFullyQualifiedJavaType.getListInstance());
		field.setDecoration(new Decoration(JavaVisibility.PROTECTED));
		clazz.addField(field);
		
		field = new Field("field2",JDKFullyQualifiedJavaType.getBooleanInstance());
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC));
		clazz.addField(field);

		field = new Field("field3",JDKFullyQualifiedJavaType.getDateInstance());
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC,true,false));
		clazz.addField(field);

		field = new Field("field4",JDKFullyQualifiedJavaType.getFileInstance());
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC,false,true));
		clazz.addField(field);
		
		field = new Field("field5",JDKFullyQualifiedJavaType.getPropertiesInstance());
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC,true,true));
		clazz.addField(field);
		
		field = new Field("field6",JDKFullyQualifiedJavaType.getPropertiesInstance());
		field.setInitializationNewObject(Properties.class);
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC,true,true));
		clazz.addField(field);
		
		field = new Field("field7",JDKFullyQualifiedJavaType.getArrayListInstance());
		field.addJavaDocLine("测试注释");
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC,true,true));
		clazz.addField(field);
		
		field = new Field("field8",JDKFullyQualifiedJavaType.getStringInstance());
		field.setDecoration(new Decoration(JavaVisibility.PUBLIC,true,true));
		field.addAnnotation("@Deprecated");
		field.addAnnotation("@NotNull");
		clazz.addField(field);
		
		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.io.File;importjava.util.ArrayList;importjava.util.Date;importjava.util.List;importjava.util.Properties;publicinterface"+super.TEST_TOP_CLASS_NAME+"{Objectfield0=null;Listfield1=null;publicBooleanfield2=false;publicstaticDatefield3=null;publicfinalFilefield4=null;publicstaticfinalPropertiesfield5=null;publicstaticfinalPropertiesfield6=newProperties;/**测试注释*/publicstaticfinalArrayListfield7=null;@Deprecated@NotNullpublicstaticfinalStringfield8=null;}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	public void test6() throws CompilationException {
		Interface clazz = createInterface();
		clazz.addAnnotation("Deprecated");
		
		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;@Deprecatedpublicinterface"+super.TEST_TOP_CLASS_NAME+"{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test7() throws CompilationException {
		Interface clazz = createInterface();
		Method method = new Method("method0") ;
		clazz.addMethod(method);
		
		method = new Method("method1" , new MethodDecoration(JavaVisibility.PRIVATE));
		clazz.addMethod(method);
		
		method = new Method("method2" , new MethodDecoration(JavaVisibility.PUBLIC,true,false));
		clazz.addMethod(method);
		
		method = new Method("method3" , new MethodDecoration(JavaVisibility.PUBLIC,false,true));
		clazz.addMethod(method);
		
		method = new Method("method4" , new MethodDecoration(JavaVisibility.PUBLIC,true,false));
		method.addJavaDocLine("测试注释");
		clazz.addMethod(method);
		
		method = new Method("method5" , new MethodDecoration(JavaVisibility.PUBLIC,true,false));
		method.addAnnotation("@Deprecated");
		clazz.addMethod(method);
		
		method = new Method("method6" , new MethodDecoration(JavaVisibility.PUBLIC,true,false));
		method.addBodyLine("int i=9+0");
		clazz.addMethod(method);
		
		MethodDecoration decoration = new MethodDecoration(JavaVisibility.PUBLIC,true,false);
		decoration.setConstructor(true);
		method = new Method("method7" ,decoration);
		
		method.addBodyLine("int i=9+0");
		clazz.addMethod(method);
		
		
		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"+super.TEST_TOP_CLASS_NAME+"{/***@return*/publicvoidmethod0();/***@return*/voidmethod1();/***@return*/publicvoidmethod2();/***@return*/publicvoidmethod3();/***测试注释*@return*/publicvoidmethod4();/***@return*/@Deprecatedpublicvoidmethod5();/***@return*/publicvoidmethod6();/***@return*/publicvoidmethod7();}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test8() throws CompilationException {
		Interface clazz = createInterface();
		Method method = new Method("method0") ;
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" ));
		clazz.addMethod(method);
		
		
		method = new Method("method1") ;
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" ));
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getHashMapInstance(),"par2" ));
		clazz.addMethod(method);
		
		method = new Method("method1") ;
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" ));
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getHashMapInstance(),"par2" ));
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getHashMapInstance(),"par3" ));
		clazz.addMethod(method);
		
		method = new Method("method1") ;
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getHashMapInstance(),"par2" ));
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" ));
		clazz.addMethod(method);
		
		method = new Method("method2") ;
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getHashMapInstance(),"par2" ));
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" ));
		clazz.addMethod(method);
		
		method = new Method("method3") ;
		Parameter parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" );
		parameter.setComment("comment");
		method.addParameter(parameter);
		clazz.addMethod(method);
		
		method = new Method("method4") ;
		parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" );
		parameter.setComment("comment");
		method.addParameter(parameter);
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getHashMapInstance(),"par2" ));
		clazz.addMethod(method);
		
		method = new Method("method5") ;
		parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" );
		parameter.setDecoration(new Decoration(JavaVisibility.PUBLIC,true,true));
		method.addParameter(parameter);
		clazz.addMethod(method);
		
		
		method = new Method("method6") ;
		parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" );
		parameter.setDecoration(new Decoration(JavaVisibility.PUBLIC,true,true));
		parameter.addJavaDocLine("注释1");
		method.addParameter(parameter);
		parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par2" );
		parameter.setDecoration(new Decoration(JavaVisibility.PUBLIC,true,true));
		parameter.addJavaDocLine("注释2");
		method.addParameter(parameter);
		parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par3" );
		parameter.setDecoration(new Decoration(JavaVisibility.PUBLIC,true,true));
		parameter.addJavaDocLine("注释3");
		parameter.setComment("comment");
		method.addParameter(parameter);
		clazz.addMethod(method);
		
		method = new Method("method6") ;
		parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" );
		ParameterDecoration pd = new ParameterDecoration(true,true);
		parameter.setDecoration(pd);
		method.addParameter(parameter);
		clazz.addMethod(method);
		
		method = new Method("method7") ;
		parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" );
		pd = new ParameterDecoration(true,true);
		pd.setVararg(false);
		parameter.setDecoration(pd);
		method.addParameter(parameter);
		clazz.addMethod(method);
		
		method = new Method("method8") ;
		parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" );
		pd = new ParameterDecoration(true,true);
		pd.setVararg(false);
		pd.setVararg(true);
		parameter.setDecoration(pd);
		method.addParameter(parameter);
		clazz.addMethod(method);
		

		method = new Method("method9") ;
		parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" );
		pd = new ParameterDecoration(true,true);
		parameter.setDecoration(pd);
		method.addParameter(parameter);
		parameter = new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"par2" );
		method.addParameter(parameter);
		clazz.addMethod(method);
		
		method = new Method("method10") ;
		parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" );
		pd = new ParameterDecoration(true,true);
		parameter.setDecoration(pd);
		method.addParameter(parameter);
		parameter = new Parameter(JDKFullyQualifiedJavaType.getIntInstance(),"par2" );
		method.addParameter(parameter);
		parameter = new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"par3" );
		pd = new ParameterDecoration(true,true);
		parameter.setDecoration(pd);
		method.addParameter(parameter);
		clazz.addMethod(method);
		
		method = new Method("method11") ;
		parameter = new Parameter(JDKFullyQualifiedJavaType.getArrayListInstance(),"par1" );
		parameter.addAnnotation("@Deprecated","NotNull");
		method.addParameter(parameter);
		clazz.addMethod(method);
		
		method = new Method("method12") ;
		parameter = new Parameter(JDKFullyQualifiedJavaType.getCharArrayInstance(),"par1" );
		parameter.addAnnotation("@Deprecated","NotNull");
		method.addParameter(parameter);
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;importjava.util.HashMap;publicinterface"+super.TEST_TOP_CLASS_NAME+"{/***@parampar1{@codejava.util.ArrayList}*@return*@seejava.util.ArrayList*/publicvoidmethod0(ArrayListpar1);/***@parampar1{@codejava.util.ArrayList}*@parampar2{@codejava.util.HashMap}*@return*@seejava.util.ArrayList*@seejava.util.HashMap*/publicvoidmethod1(ArrayListpar1,HashMappar2);/***@parampar1{@codejava.util.ArrayList}*@parampar2{@codejava.util.HashMap}*@parampar3{@codejava.util.HashMap}*@return*@seejava.util.ArrayList*@seejava.util.HashMap*@seejava.util.HashMap*/publicvoidmethod1(ArrayListpar1,HashMappar2,HashMappar3);/***@parampar2{@codejava.util.HashMap}*@parampar1{@codejava.util.ArrayList}*@return*@seejava.util.HashMap*@seejava.util.ArrayList*/publicvoidmethod1(HashMappar2,ArrayListpar1);/***@parampar2{@codejava.util.HashMap}*@parampar1{@codejava.util.ArrayList}*@return*@seejava.util.HashMap*@seejava.util.ArrayList*/publicvoidmethod2(HashMappar2,ArrayListpar1);/***@parampar1{@codejava.util.ArrayList}*@return*@seejava.util.ArrayList*/publicvoidmethod3(ArrayListpar1/*comment*/);/***@parampar1{@codejava.util.ArrayList}*@parampar2{@codejava.util.HashMap}*@return*@seejava.util.ArrayList*@seejava.util.HashMap*/publicvoidmethod4(ArrayListpar1/*comment*/,HashMappar2);/***@parampar1{@codejava.util.ArrayList}*@return*@seejava.util.ArrayList*/publicvoidmethod5(finalArrayListpar1);/***@parampar1{@codejava.util.ArrayList}注释1*@parampar2{@codejava.util.ArrayList}注释2*@parampar3{@codejava.util.ArrayList}注释3*@return*@seejava.util.ArrayList*@seejava.util.ArrayList*@seejava.util.ArrayList*/publicvoidmethod6(finalArrayListpar1,finalArrayListpar2,finalArrayListpar3/*comment*/);/***@parampar1{@codejava.util.ArrayList}*@return*@seejava.util.ArrayList*/publicvoidmethod6(finalArrayList...par1);/***@parampar1{@codejava.util.ArrayList}*@return*@seejava.util.ArrayList*/publicvoidmethod7(finalArrayListpar1);/***@parampar1{@codejava.util.ArrayList}*@return*@seejava.util.ArrayList*/publicvoidmethod8(finalArrayList...par1);/***@parampar1{@codejava.util.ArrayList}*@parampar2{@codeint}*@return*@seejava.util.ArrayList*/publicvoidmethod9(finalArrayList...par1,intpar2);/***@parampar1{@codejava.util.ArrayList}*@parampar2{@codeint}*@parampar3{@codeString}*@return*@seejava.util.ArrayList*@seeString*/publicvoidmethod10(finalArrayList...par1,intpar2,finalString...par3);/***@parampar1{@codejava.util.ArrayList}*@return*@seejava.util.ArrayList*/publicvoidmethod11(@Deprecated@NotNullArrayListpar1);/***@parampar1{@codechar[]}*@return*/publicvoidmethod12(@Deprecated@NotNullchar[]par1);}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	
	public void test9() throws CompilationException {
		Interface clazz = createInterface();
		
		Method method = new Method("method0") ;
		method.setReturnType(JDKFullyQualifiedJavaType.getArrayListInstance());
		clazz.addMethod(method);
		
		method = new Method("method1") ;
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		clazz.addMethod(method);
		
		method = new Method("method2") ;
		method.setReturnType(JDKFullyQualifiedJavaType.getLongArrayInstance());
		clazz.addMethod(method);
		
		method = new Method("method3") ;
		method.setReturnType(JDKFullyQualifiedJavaType.getLongArrayInstance());
		method.setBaseType(true);
		clazz.addMethod(method);
		
		method = new Method("method4") ;
		method.setReturnType(JDKFullyQualifiedJavaType.getLongArrayInstance());
		method.setBaseType(true);
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringArrayInstance(),"par1"));
		clazz.addMethod(method);
		

		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.util.ArrayList;publicinterface"+super.TEST_TOP_CLASS_NAME+"{/***@returnjava.util.ArrayList*/publicArrayListmethod0();/***@returnString*/publicStringmethod1();/***@returnLong[]*/publicLong[]method2();/***@returnLong[]*/publiclong[]method3();/***@parampar1{@codeString[]}*@returnLong[]*@seeString[]*/publiclong[]method4(String[]par1);}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test10() throws CompilationException {
		Interface clazz = createInterface();
		
		Method method = new Method("method0") ;
		method.setReturnType(JDKFullyQualifiedJavaType.getArrayListInstance());
		method.addException(ExceptionFullyQualifiedJavaType.getExceptionInstance());
		clazz.addMethod(method);
		

		method = new Method("method1") ;
		method.setReturnType(JDKFullyQualifiedJavaType.getArrayListInstance());
		method.addException(ExceptionFullyQualifiedJavaType.getIOExceptionInstance());
		method.addException(ExceptionFullyQualifiedJavaType.getSQLExceptionInstance());
		method.addException(ExceptionFullyQualifiedJavaType.getNullPointerExceptionInstance());
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;importjava.io.IOException;importjava.sql.SQLException;importjava.util.ArrayList;publicinterface"+super.TEST_TOP_CLASS_NAME+"{/***@returnjava.util.ArrayList*@exception{@codejava.lang.Exception}*/publicArrayListmethod0()throwsException;/***@returnjava.util.ArrayList*@exception{@codejava.io.IOException}*@exception{@codejava.sql.SQLException}*@exception{@codeNullPointerException}*/publicArrayListmethod1()throwsIOException,SQLException,NullPointerException;}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test11() throws CompilationException {
		JavaCompilation inteface = new Interface(super.TEST_PACKAGE+ super.TEST_TOP_CLASS_NAME, new ClassDecoration(JavaVisibility.PUBLIC,true,true));
		String s = inteface.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"
				+ super.TEST_TOP_CLASS_NAME + "{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
	}
	
	
	public void test12() throws CompilationException {
		JavaCompilation inteface = new Interface(super.TEST_PACKAGE+ super.TEST_TOP_CLASS_NAME);
		ClassDecoration classDecoration = new ClassDecoration(JavaVisibility.PUBLIC,true,true);
		inteface.setClassDecoration(classDecoration);
		classDecoration.setStatic(true);
		classDecoration.setFinal(true);
		String s = inteface.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"
				+ super.TEST_TOP_CLASS_NAME + "{}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
	}
	
	
	public void test13() throws CompilationException{
		Interface clazz = createInterface();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setAbstract(true);
		d.setSynchronized(true);
		d.setStatic(true);
		d.setConstructor(true);
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"+super.TEST_TOP_CLASS_NAME+"{/***@returnString*/@Deprecated@OverridepublicStringmethod();}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	
	public void test14() throws CompilationException{
		Interface clazz = createInterface();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setAbstract(true);
		
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"+super.TEST_TOP_CLASS_NAME+"{/***@returnString*/@Deprecated@OverridepublicStringmethod();}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	
	public void test15() throws CompilationException{
		Interface clazz = createInterface();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setSynchronized(true);
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"+super.TEST_TOP_CLASS_NAME+"{/***@returnString*/@Deprecated@OverridepublicStringmethod();}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test16() throws CompilationException{
		Interface clazz = createInterface();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setStatic(true);
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"+super.TEST_TOP_CLASS_NAME+"{/***@returnString*/@Deprecated@OverridepublicStringmethod();}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test17() throws CompilationException{
		Interface clazz = createInterface();
		MethodDecoration d = new  MethodDecoration(JavaVisibility.PUBLIC);
		d.setConstructor(true);
		Method method = new Method("method",d);
		method.addBodyLine("return null");
		method.setReturnType(JDKFullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("Deprecated","@Override");
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"+super.TEST_TOP_CLASS_NAME+"{/***@returnString*/@Deprecated@OverridepublicStringmethod();}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);		
	}
	
	public void test18() throws CompilationException{
		Interface clazz = createInterface();
		clazz.addAfterBodyComment("Test after body");
		String s = clazz.compiledContent().toString();
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicinterface"+super.TEST_TOP_CLASS_NAME+"{}/*Testafterbody*/";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
	}
}
