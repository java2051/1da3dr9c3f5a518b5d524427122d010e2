package com.honey.compilation.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import javax.tools.JavaFileManager.Location;

import junit.framework.TestCase;

import com.honey.compilation.java.AbstractClass;
import com.honey.compilation.java.AbstractJavaCompilation;
import com.honey.compilation.java.Clazz;
import com.honey.compilation.java.ClassDecoration;
import com.honey.compilation.java.InnerClass;
import com.honey.compilation.java.InnerEnum;
import com.honey.compilation.java.Interface;
import com.honey.compilation.java.JavaCompilation;
import com.honey.compilation.java.JavaVisibility;
import com.honey.core.compiler.CompilationException;
import com.honey.core.utils.RandomStringUtils;
import com.honey.core.utils.StringUtility;


public class BaseTestCase extends TestCase {
	/**测试用例使用的包*/
	public static final String TEST_PACKAGE ="com.honey.compilation.java.";
	
	/**测试用例使用的类的名称*/
	public static final String TEST_TOP_CLASS_NAME ;
	
	/**测试用例内部类使用的类的名称*/
	public static final String TEST_INNER_CLASS_NAME ;
	
	/**测试用例内部枚举使用的类的名称*/
	public static final String TEST_INNER_ENUM_NAME ;
	
	public boolean printResult = true;
	
	static{
		String name = RandomStringUtils.randomAlphabetic(8).toLowerCase();
		TEST_TOP_CLASS_NAME = transformToUpperCase(name);
		name = RandomStringUtils.randomAlphabetic(8).toLowerCase();
		TEST_INNER_CLASS_NAME =  transformToUpperCase(name);
		
		name = RandomStringUtils.randomAlphabetic(8).toLowerCase();
		TEST_INNER_ENUM_NAME =  transformToUpperCase(name);
	}

	public  String getRandomString(){
		return RandomStringUtils.randomAlphabetic(8);
	}
	
	public  Clazz createClass() throws CompilationException{
		return  com.honey.compilation.java.CompilationJavaFactory.getGeneralTopLevelClass(TEST_PACKAGE + TEST_TOP_CLASS_NAME);
	}
	
	public  InnerClass createInnerClass() throws CompilationException{
		InnerClass inner = new InnerClass( TEST_PACKAGE + TEST_INNER_CLASS_NAME);
		return  inner;
	}
	
	public  InnerEnum createInnerEnum() throws CompilationException{
		InnerEnum inner = new InnerEnum( TEST_PACKAGE + TEST_INNER_ENUM_NAME);
		return  inner;
	}
	
	public Interface createInterface() throws CompilationException{
		Interface java = new Interface( 
				TEST_PACKAGE + TEST_TOP_CLASS_NAME,
				new ClassDecoration(JavaVisibility.PUBLIC)
		);
		
		return java ;
	}
	
	public AbstractClass createAbstractClass() throws CompilationException{
		AbstractClass java = new AbstractClass( 
				TEST_PACKAGE + TEST_TOP_CLASS_NAME,
				new ClassDecoration(JavaVisibility.PUBLIC)
		);
		return java ;
	}
	
	public void addApacheLicenseComment(AbstractJavaCompilation clazz){
		clazz.addFileComment(
				"",
				"Copyright (C) 2002-2012 青青河边草 ",
				"",
				"Licensed under the Apache License, Version 2.0 (the \"License\");",
				"you may not use this file except in compliance with the License.",
				"You may obtain a copy of the License at",
				"",
				"    http://www.apache.org/licenses/LICENSE-2.0",
				"",
				"Unless required by applicable law or agreed to in writing, software",
				"distributed under the License is distributed on an \"AS IS\" BASIS,",
				"WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.",
				"See the License for the specific language governing permissions and",
				"limitations under the License. ",
				""
		
		);
		
				
		
	}
	
	public void printResult( Object obj  ) throws CompilationException{
		if(printResult){
			if( obj instanceof JavaCompilation ){
				JavaCompilation jc = (JavaCompilation) obj;
				System.out.println(jc.compiledContent());
			}else{
				System.out.println(obj);
			}
		}
	} 
	
	
  
  /**
   * 把字符串首字母转成大写
   * @param str
   * @return
   */
	public static String transformToUpperCase(String str) {
		if(str == null || str.length()==0  ) return str;
		char []chars = str.toCharArray() ;
		if(chars[0]>=97 &&  chars[0]<=122){ // a=97 z=122
			chars[0] = (char) (chars[0]- 32);//ASCII中大写字母和小写字母相差32
		}
		return new String (chars);
	}

	/**
	 * 把字符串首字母转成小写
	 * @param str
	 * @return
	 */
	public static String transformToLowerCase(String str) {
		if(str == null || str.length()==0  ) return str;
		char []chars = str.toCharArray() ;
		if(chars[0]>=65 &&  chars[0]<=90){ // A=65 Z=90
			chars[0] = (char) (chars[0]+ 32);//ASCII中大写字母和小写字母相差32
		}
		return new String (chars);
	}
	
	
	public void runDynamicJavaMethod(String packageName ,String className ,String str,String runMethodName, Object []parmes ) throws Exception{
//		Class clazz = dynamicJavaCompiler(packageName, className, str);
//		
//		String []args= new String[]{};
//		java.lang.reflect.Method m = ReflectUtility.getMethod0(clazz, runMethodName, args.getClass());
//		Object obj = cls.newInstance();
//		
//		ReflectUtility.invoke(cls, m, new Object[]{args} );
//		
		
	}
	
	
	private void createReadmeFile(String path  ){
		String readMe=path+"/ReadMe.txt";
		File f =  new File(readMe);
		if( !f.exists()){
			String str="文件夹下所有的内容都是测试用例动态编译类生成的临时class文件, 测试完成后可以删除.";
			try {
				FileOutputStream out = new FileOutputStream(f);   
				out.write(str.getBytes("utf-8"));
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
	}
	
	/**
	 * 对java源码字符串进行动态编译
	 * @param packageName 包 
	 * @param className 类的名称
	 * @param str 字符串(java源码字符串)
	 * @return Class
	 * @throws Exception
	 */
	public java.lang.Class dynamicJavaCompiler(String packageName ,String className ,String str) throws Exception{
		String classFilePath = "";
		if(packageName == null) packageName = "";
		packageName = packageName.trim();
		if(packageName.length()>0 ){
			classFilePath = packageName.replace('.', '/');
			if( !packageName.endsWith(".") ){
				classFilePath = classFilePath + "/";
				packageName =packageName+"."; 
			}
		}
		class StringObject extends SimpleJavaFileObject {
			private String contents = null;
			public StringObject(String className, String contents) throws Exception {
				super(URI.create("string:///" + className.replace('.', '/')
						+ Kind.SOURCE.extension), Kind.SOURCE);
				this.contents = contents;
			}
			public CharSequence getCharContent(boolean ignoreEncodingErrors)throws IOException {
				return contents;	
			} 
		}
		String path =StringUtility.class.getResource("/").getPath() + "/dynacode/";
		File classFileDir = new File( path);
		classFileDir.mkdir();
		createReadmeFile(path);
		
		//注意如果compiler返回为NULL通常是因为环境变量中找不到tools.jar这个java包,请正确配置环境变量
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		StandardJavaFileManager  fileManager  = compiler.getStandardFileManager(null, null, null);
		Location location = StandardLocation.CLASS_OUTPUT;
		
		File[] outputs = new File[]{classFileDir};
		fileManager.setLocation(location, Arrays.asList(outputs));
		
		String d =path+classFilePath+className+".class";
		(new File(d)).delete();
		
		StringObject so = null;
		so = new StringObject( className, str);
		JavaFileObject file = so;
		Iterable files = Arrays.asList(file);
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,null, null, null, files);
		Boolean result = task.call(); 
		if (result){ 
			java.lang.Class clazz = null;
			File classesDir = new File(path);
			ClassLoader parentLoader = StringUtility.class.getClassLoader();
			URLClassLoader loader1 = new URLClassLoader(
					new URL[] { classesDir.toURL() }, parentLoader);
			clazz = loader1.loadClass(packageName+className);
			return clazz;
		}
		return null;
	}
	
	public void test(){
		
	}
}
