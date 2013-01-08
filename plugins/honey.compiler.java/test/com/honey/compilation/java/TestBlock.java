package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;
import com.honey.compilation.java.ClassDecoration.JavaType;
import com.honey.core.types.ExceptionFullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

public class TestBlock extends BaseTestCase {
	
	public void test0() throws CompilationException{
		 Clazz clazz = createClass();
		 assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		 assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);
		 Block b = new StaticBlock();
		 b.addBodyLine("int i=2+3");
		 b.addAnnotation("注解");
		 clazz.addStaticBlocks(b);

		 b = new StaticBlock();
		 b.addBodyLine("double d = 9/2 ");
		 b.addAnnotation("注解");
		 clazz.addStaticBlocks(b);
		 assertEquals(clazz.getStaticBlocks().size(), 2);
		 
		 String s = clazz.compiledContent().toString() ;
		 String hash = StringUtility.getStringHash(s);
		 String nS = "packagecom.honey.compilation.java;publicclass"+this.TEST_TOP_CLASS_NAME+"{static{inti=2+3;}static{doubled=9/2;}/***默认的构造函数**/public"+this.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}}";
		 nS = StringUtility.getStringHash(nS);
		 assertEquals(hash, nS);
		 printResult(s);
	}

	
	public void test1() throws CompilationException{
		 Clazz clazz = createClass();
		 assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		 assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);
		 Block b = new StaticBlock();
		 b.addBodyLine("System.out.println(\"执行类的静态块\");");
		 b.addBodyLine("int i=2+3");
		 b.addBodyLine("i++ ;");
		 b.addBodyLine("int result = i+100");
		 clazz.addStaticBlocks(b);
		 assertEquals(clazz.getStaticBlocks().size(), 1);
		 
		 clazz.addField(new Field("field1",JDKFullyQualifiedJavaType.getStringInstance()));
		 clazz.addField(new Field("field2",JDKFullyQualifiedJavaType.getStringInstance()));
		 assertEquals(clazz.getFields().size(), 2);
		 clazz.setAutoAddGetterMethod(true);
		 clazz.setAutoAddSetterMethod(true);
		 
		 String s = clazz.compiledContent().toString() ;
		 try {
			 java.lang.Class cls = dynamicJavaCompiler(super.TEST_PACKAGE,super.TEST_TOP_CLASS_NAME,s);
			assertNotNull(cls);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("动态编译类失败",false);
		}
		String hash = StringUtility.getStringHash(s);
		String nS ="packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{privateStringfield1;privateStringfield2;static{System.out.println(\"执行类的静态块\");inti=2+3;i++;intresult=i+100;}/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***获取{@codefield1}属性的值*@returnString*/publicStringgetField1(){returnthis.field1;}/***获取{@codefield2}属性的值*@returnString*/publicStringgetField2(){returnthis.field2;}/***设定{@codefield1}属性的值*@paramfield1{@codeString}*@return*@seeString*/publicvoidsetField1(Stringfield1){this.field1=field1;}/***设定{@codefield2}属性的值*@paramfield2{@codeString}*@return*@seeString*/publicvoidsetField2(Stringfield2){this.field2=field2;}}";
				   // 
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}

	
	public void test2() throws CompilationException{
		 Clazz clazz = createClass();
		 assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		 assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);
		 Block b = new StaticBlock();
		 b.addJavaDocLine("测试static块");
		 b.addBodyLine("System.out.println(\"执行类的静态块\");");
		 b.addBodyLine("int i=2+3");
		 b.addBodyLine("i++ ;");
		 b.addBodyLine("int result = i+100");
		 clazz.addStaticBlocks(b);
		 IfBlock ifb1 = new IfBlock();
		 ifb1.addJavaDocLine("测试if语句块");
		 ifb1.setCondition("i > 100");
		 ifb1.addBodyLine("double d =9/2");
		 b.addBlocks(ifb1);
		 
		 IfBlock ifb2 = new IfBlock();
		 ifb2.addJavaDocLine("测试if语句块");
		 ifb2.addJavaDocLine("测试if语句块");
		 ifb2.setCondition("i > 100");
		 ifb2.addBodyLine("d =9/2");
		 ifb1.addBlocks(ifb2);
		 
		 IfBlock ifb3 = new IfBlock();
		 ifb3.addJavaDocLine("测试if语句块");
		 ifb3.setCondition("i > 100");
		 ifb3.addBodyLine("d =9/2");
		 ifb2.addBlocks(ifb3);
		 
		 assertEquals(clazz.getStaticBlocks().size(), 1);
		 
		 clazz.addField(new Field("field1",JDKFullyQualifiedJavaType.getStringInstance()));
		 clazz.addField(new Field("field2",JDKFullyQualifiedJavaType.getStringInstance()));
		 assertEquals(clazz.getFields().size(), 2);
		 clazz.setAutoAddGetterMethod(true);
		 clazz.setAutoAddSetterMethod(true);
		 
		 String s = clazz.compiledContent().toString() ;
		 try {
			 java.lang.Class cls = dynamicJavaCompiler(super.TEST_PACKAGE,super.TEST_TOP_CLASS_NAME,s);
			assertNotNull(cls);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("动态编译类失败",false);
		}
		
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+this.TEST_TOP_CLASS_NAME+"{privateStringfield1;privateStringfield2;//测试static块static{System.out.println(\"执行类的静态块\");inti=2+3;i++;intresult=i+100;//测试if语句块;if(i>100){doubled=9/2;//测试if语句块;//测试if语句块;if(i>100){d=9/2;//测试if语句块;if(i>100){d=9/2;}}}}/***默认的构造函数**/public"+this.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***获取{@codefield1}属性的值*@returnString*/publicStringgetField1(){returnthis.field1;}/***获取{@codefield2}属性的值*@returnString*/publicStringgetField2(){returnthis.field2;}/***设定{@codefield1}属性的值*@paramfield1{@codeString}*@return*@seeString*/publicvoidsetField1(Stringfield1){this.field1=field1;}/***设定{@codefield2}属性的值*@paramfield2{@codeString}*@return*@seeString*/publicvoidsetField2(Stringfield2){this.field2=field2;}}";
		           //    
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
			
	}
	
	
	public void test3() throws CompilationException{
		Clazz clazz = createClass();
		assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);
		Block b = new StaticBlock();
		b.addBodyLine("System.out.println(\"执行类的静态块\");");
		b.addBodyLine("int i=2+3");
		b.addBodyLine("i++ ;");
		b.addBodyLine("int result = i+100");
		clazz.addStaticBlocks(b);
		assertEquals(clazz.getStaticBlocks().size(), 1);
		 
		Method method = new Method("testMethod");
		method.addBodyLine("int i =6 + 6;");
		
		IfBlock ifb1 = new IfBlock();
		ifb1.addJavaDocLine("测试if语句块");
		ifb1.addJavaDocLine("测试if语句块");
		ifb1.setCondition("i > 100");
		ifb1.addBodyLine("d =9/2");
		
		WhileBlock while1 = new WhileBlock();
		while1.addJavaDocLine("测试while语句块");
		while1.setCondition("i > 100");
		while1.addBodyLine("d =9/2");
		ifb1.addBlocks(while1);
		
		WhileBlock while2 = new WhileBlock();
		while2.addJavaDocLine("测试while语句块");
		while2.addJavaDocLine("测试while语句块");
		while2.setCondition("i > 100");
		while2.addBodyLine("d =9/2");
		while1.addBlocks(while2);
		

		IfBlock ifb2 = new IfBlock();
		ifb2.setCondition("i > 100");
		ifb2.addBodyLine("d =9/2");
		while2.addBlocks(ifb2);
		
		method.addBlocks(ifb1);
		
		clazz.addMethod(method);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{static{System.out.println(\"执行类的静态块\");inti=2+3;i++;intresult=i+100;}/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*/publicvoidtestMethod(){inti=6+6;//测试if语句块//测试if语句块if(i>100){d=9/2;//测试while语句块;while(i>100){d=9/2;//测试while语句块;//测试while语句块;while(i>100){d=9/2;if(i>100){d=9/2;}}}}}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	
	public void test4() throws CompilationException{
		Clazz clazz = createClass();
		assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);
		Block b = new StaticBlock();
		b.addBodyLine("System.out.println(\"执行类的静态块\");");
		b.addBodyLine("int i=2+3");
		b.addBodyLine("i++ ;");
		b.addBodyLine("int result = i+100");
		clazz.addStaticBlocks(b);
		assertEquals(clazz.getStaticBlocks().size(), 1);
		 
		Method method = new Method("testMethod");
		method.addBodyLine("int i =6 + 6;");
		
		WhileBlock while1 = new WhileBlock();
		while1.addJavaDocLine("测试while语句块");
		while1.setCondition("i > 100");
		while1.addBodyLine("d =9/2");
		
		IfBlock ifb1 = new IfBlock();
		ifb1.addJavaDocLine("测试if语句块");
		ifb1.addJavaDocLine("测试if语句块");
		ifb1.setCondition("i > 100");
		ifb1.addBodyLine("d =9/2");
		while1.addBlocks(ifb1);
		while1.addBodyLine("System.out.println(i)" );
		
		WhileBlock while2 = new WhileBlock();
		while2.addJavaDocLine("测试while语句块");
		while2.setCondition("i > 100");
		while2.addBodyLine("d =9/2");
		
		while1.addBlocks(while2);
		
		IfBlock ifb2 = new IfBlock();
		ifb2.addJavaDocLine("测试if语句块");
		ifb2.addJavaDocLine("测试if语句块");
		ifb2.setCondition("i > 100");
		ifb2.addBodyLine("d =9/2");
		while2.addBlocks(ifb2);
		while1.addBodyLine("System.out.println(i)" );
		
		method.addBlocks(while1);
		
		clazz.addMethod(method);
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{static{System.out.println(\"执行类的静态块\");inti=2+3;i++;intresult=i+100;}/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*/publicvoidtestMethod(){inti=6+6;//测试while语句块while(i>100){d=9/2;//测试if语句块;//测试if语句块;if(i>100){d=9/2;}System.out.println(i);//测试while语句块;while(i>100){d=9/2;//测试if语句块;//测试if语句块;if(i>100){d=9/2;}}System.out.println(i);}}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		printResult(s);
	}
	
	public void test5() throws CompilationException{
		Clazz clazz = createClass();
		assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);

		Method method = new Method("testMethod");
		method.addBodyLine("int i =6 + 6;");
		SynchronizedBlock sb = new SynchronizedBlock();
		sb.addBodyLine("i++;");

		IfBlock ifb1 = new IfBlock();
		ifb1.addJavaDocLine("测试if语句块");
		ifb1.addJavaDocLine("测试if语句块");
		ifb1.setCondition("i > 100");
		ifb1.addBodyLine("d =9/2");
		sb.addBlocks(ifb1);

		method.addBlocks(sb);
		sb.setMutex("i");
		method.addBlocks(sb);
		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+super.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+super.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*/publicvoidtestMethod(){inti=6+6;synchronized(i){i++;//测试if语句块;//测试if语句块;if(i>100){d=9/2;}}synchronized(i){//测试if语句块;//测试if语句块;if(i>100){d=9/2;}i++;}}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}

	public void test6() throws CompilationException{
		Clazz clazz = createClass();
		assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);

		Method method = new Method("testMethod");

		VisibleBlock block = new VisibleBlock();
		block.addBodyLine("int i =6 + 6;");
		block.addBodyLine("i++ ;") ;
		method.addBlocks(block);
		
		block = new VisibleBlock();
		block.addBodyLine("int i =6 + 6;");
		block.addBodyLine("i++ ;") ;
		IfBlock ifb1 = new IfBlock();
		ifb1.addJavaDocLine("测试if语句块");
		ifb1.addJavaDocLine("测试if语句块");
		ifb1.setCondition("i > 100");
		ifb1.addBodyLine("d =9/2");
		block.addBlocks(ifb1);
		
		method.addBlocks(block);

		clazz.addMethod(method);
		
		String s = clazz.compiledContent().toString() ;
		String hash = StringUtility.getStringHash(s);
		String nS = "packagecom.honey.compilation.java;publicclass"+this.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+this.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*/publicvoidtestMethod(){{inti=6+6;i++;}{inti=6+6;i++;//测试if语句块;//测试if语句块;if(i>100){d=9/2;}}}}";
		nS = StringUtility.getStringHash(nS);
		assertEquals(hash, nS);
		
		printResult(s);
	}
	

	public void test7() throws CompilationException{
		Clazz clazz = createClass();
		assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);
		
		IfBlock ifb1 = new IfBlock();
		ifb1.addJavaDocLine("测试if语句块");
		ifb1.addJavaDocLine("测试if语句块");
		ifb1.setCondition("i > 100");
		ifb1.addBodyLine("d =9/2");
		
		IfBlock ifb2 = new IfBlock();
		ifb2.addJavaDocLine("测试if语句块");
		ifb2.addJavaDocLine("测试if语句块");
		ifb2.setCondition("i > 100");
		ifb2.addBodyLine("d =9/2");
		ifb1.addBlocks(ifb2) ;
		
		IfBlock ifb3 = new IfBlock();
		ifb3.addJavaDocLine("测试if语句块");
		ifb3.addJavaDocLine("测试if语句块");
		ifb3.setCondition("i > 100");
		ifb3.addBodyLine("d =9/2");
		ifb2.addBlocks(ifb3) ;

		Method method = new Method("testMethod");
		TryCatchBlock block = new TryCatchBlock();
		block.addCatchCases(ExceptionFullyQualifiedJavaType.getIOExceptionInstance());
		block.addCatchCase(ExceptionFullyQualifiedJavaType.getExceptionInstance(),"i++;");
		block.addBodyLine(" i++ ;") ;
		ifb3.addBlocks(block);
		

		method.addBlocks(ifb1);
		
		block = new TryCatchBlock();
		block.addCatchCases(ExceptionFullyQualifiedJavaType.getIOExceptionInstance());
		block.addCatchCase(ExceptionFullyQualifiedJavaType.getExceptionInstance(),"i++;");
		block.addBodyLine(" i++ ;") ;
		method.addBlocks(block);
		
		clazz.addMethod(method);

		String s = clazz.compiledContent().toString() ;
//		String hash = StringUtility.getStringHash(s);
//		String nS = "packagecom.honey.compilation.java;publicclass"+this.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+this.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*/publicvoidtestMethod(){{inti=6+6;i++;}{inti=6+6;i++;//测试if语句块;//测试if语句块;if(i>100){d=9/2;}}}}";
//		nS = StringUtility.getStringHash(nS);
//		assertEquals(hash, nS);
		
		printResult(s);
	}
	
	
	public void test8() throws CompilationException{
		Clazz clazz = createClass();
		assertEquals(clazz.getClassDecoration().getVisibility(), JavaVisibility.PUBLIC);
		assertEquals(clazz.getClassDecoration().getJavaType(), JavaType.CLASS);
		
		Method method = new Method("testMethod");
		VisibleBlock block = new VisibleBlock();
		block.addBodyLine("int i =6 + 6;");
		block.addBodyLine("i++ ;") ;
		method.addBlocks(block);
		
		SwitchBlock sb = new SwitchBlock( "i" );
		sb.addBodyLine("case 1 : ");
		sb.addBodyLine("i++") ;
		sb.addBodyLine("break;") ;
		sb.addBodyLine("case 2 : ");
		sb.addBodyLine("i++") ;
		sb.addBodyLine("break;") ;
		block.addBlocks(sb);
		
		clazz.addMethod(method);

		String s = clazz.compiledContent().toString() ;
//		String hash = StringUtility.getStringHash(s);
//		String nS = "packagecom.honey.compilation.java;publicclass"+this.TEST_TOP_CLASS_NAME+"{/***默认的构造函数**/public"+this.TEST_TOP_CLASS_NAME+"(){//TODOAuto-generatedmethodstub;}/***@return*/publicvoidtestMethod(){{inti=6+6;i++;}{inti=6+6;i++;//测试if语句块;//测试if语句块;if(i>100){d=9/2;}}}}";
//		nS = StringUtility.getStringHash(nS);
//		assertEquals(hash, nS);
		
		printResult(s);
		
		s = clazz.compiledContent().toString() ;
		printResult(s);
	}
}

