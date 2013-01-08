package com.honey.compilation.java;

import com.honey.core.compiler.CompilationException;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.ReflectUtility;


/**
 * 动态编译生成的java源码并执行mian函数
 * @author Administrator
 *
 */
public class TestHelloWord extends BaseTestCase {
	
	public void testHelloword () throws CompilationException{
		System.out.println("动态生成Hello word 程序代码如下 : ");
		Clazz clazz = CompilationJavaFactory.getGeneralTopLevelClass("test.HelloWord");
		System.out.println("---------------------------------------------------------------");
		clazz.addFileComment("这是一个Hello word java程序.");
		Method method = new Method("main",new MethodDecoration(JavaVisibility.PUBLIC,true,false));
		method.addParameter(new Parameter(JDKFullyQualifiedJavaType.getStringInstance(),"[]args"));
		method.addBodyLine("System.out.println(\"Hello word !\")");
		
		clazz.addMethod(method);
		String s = clazz.compiledContent().toString() ;
		printResult(s);
		System.out.println("---------------------------------------------------------------");
		System.out.println("执行main方法结果如下 : ");
		try {//动态编译java类
			java.lang.Class cls = dynamicJavaCompiler("test","HelloWord",s);
			String []args= new String[]{};
			java.lang.reflect.Method m = ReflectUtility.getMethod0(cls, "main", args.getClass());
			ReflectUtility.invoke(cls, m, new Object[]{args} );
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("动态编译类失败",false);
		}
		
	}
}
