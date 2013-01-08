package com.honey.compilation.java;

import java.util.List;
import java.util.Set;

import com.honey.core.compiler.Compilation;
import com.honey.core.compiler.CompilationException;
import com.honey.core.types.FullyQualifiedJavaType;

/**
 * java 类文件编译接口
 * @author Administrator
 *
 */
public interface JavaCompilation extends Compilation{
	
	/**
	 * 获取导入的类库
	 * @return
	 */
	public Set<FullyQualifiedJavaType> getImportedTypes()throws CompilationException;
    
    /**
     * 获取静态导入的类库
     * @return
     */
	public Set<String> getStaticImports()throws CompilationException;

    /**
     * 获取父类(extends)
     * @return
     */
	public FullyQualifiedJavaType getSuperClass()throws CompilationException;

    /**
     * 获取父类(interface)
     * @return
     */
	public Set<FullyQualifiedJavaType> getSuperInterfaceTypes()throws CompilationException;
    
    /**
     * 获取java类的修饰( interface, enum, class , abstract class,  static 和 final)
     * 设定javal类可视范围(针对于类只能有两种修饰: default 和 public )
     * @return
     */
	public ClassDecoration getClassDecoration()throws CompilationException;
    
    /**
     * 设定java类的修饰( interface, enum, class , abstract class,  static 和 final)
     * @return
     */
	public void setClassDecoration(ClassDecoration classDecoration)throws CompilationException;
    
    /**
     * 获取类的类型
     * @return
     */
	//public FullyQualifiedJavaType getType()throws CompilationException;

    /**
     * 设定类的类型
     * @param type 类的类型
     * @throws CompilationException
     */
	public void setType(FullyQualifiedJavaType type)throws CompilationException;
    
    /**
     * 添加导入类库 
     * @param importedType  FullyQualifiedJavaType 类型
     */
	public void addImportedType(FullyQualifiedJavaType ...importedType)throws CompilationException;
    
    /**
     * 添加导入类库
     * @param importedType String类型
     */
	public void addImportedType(String ...importedType)throws CompilationException;

    /**
     * 添加静态导入类库( 静态导入是没有FullyQualifiedJavaType类型)
     * @param staticImport
     */
	public void addStaticImport(String ...staticImport)throws CompilationException;
    
    /**
     * 添加静态导入类库( 静态导入是没有FullyQualifiedJavaType类型, 静态导入规则是 : 包名.方法名称) 
     * @param staticImports
     */
	public void addStaticImports(Set<String> staticImports)throws CompilationException;
    
    /**
     * 添加实现的接口类(java中接口可以继承多个)
     * @param superInterface 添加接口
     */
	public void addSuperInterface(FullyQualifiedJavaType ...superInterface)throws CompilationException;
    
    /**
     * 添加实现的接口类(java中接口可以继承多个)
     * @param superInterface  添加接口
     */
	public void addSuperInterface(String ...superInterfaces)throws CompilationException;
    
    /**
     * 设定父类(java中只能单继承 )
     * @param superClass 父类 
     */
	public void setSuperClass(FullyQualifiedJavaType superClass)throws CompilationException;

    /**
     * 设定父类(java中只能单继承 )
     * @param superClass 父类
     */
	public void setSuperClass(String superClassType)throws CompilationException;
    
    /**
     * 添加类注释
     * @param comments 注释
     */
	public void addClassComment(String ...comments)throws CompilationException;
    
    /**
     * 获取类注释
     * @return
     */
	public List<String> getClassComments()throws CompilationException;
    
    /**
	 * 获取枚举类
	 * @return
	 */
	public List<InnerEnum> getInnerEnums()throws CompilationException;
	
	/**
	 * java下所有的类都是可以加入内部枚举类( 接口,类,内部类,枚举,内部枚举类和虚类)
	 * @param innerEnums 内部类
	 * @throws CompilationException
	 */
	public void addInnerEnum(InnerEnum ...innerEnums) throws CompilationException;
	
    /**
	 * 获取java方法
	 * @return
	 */
	public Set<Method> getMethods()throws CompilationException;
	
	/**
	 * 添加java方法
	 * @param methods 方法
	 */
	public void addMethod(Method ...methods) throws CompilationException;
	
	/**
	 * 获取java属性
	 * @return
	 */
	public List<Field> getFields()throws CompilationException;
	
	/**
	 * 添加java属性
	 * @param field java属性
	 */
	public void addField(Field ...field) throws CompilationException;

	/**
	 * 添加静态块
	 * @param blocks 块
	 */
	public void addStaticBlocks(Block ...blocks) throws CompilationException;
	
	/**
	 * 添加类的注解
	 * @param annotation 注解
	 */
	public void addAnnotation(String ...annotation)throws CompilationException;
	
	/**
	 * 添加范型
	 * @param generic 泛型类型
	 */
	public void setGeneric(FullyQualifiedJavaType generic) throws CompilationException;

	/**
	 * 获取内部类
	 * @return 
	 * @throws CompilationException
	 */
	public List<InnerClass> getInnerClasses()throws CompilationException;
	
	/**
	 * 添加内部类
	 * @param innerClasses  内部类
	 * @throws CompilationException
	 */
	public void addInnerClass(InnerClass ...innerClasses)throws CompilationException; 
}
