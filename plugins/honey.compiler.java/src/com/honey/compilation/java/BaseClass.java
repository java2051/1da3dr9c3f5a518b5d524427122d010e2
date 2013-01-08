
package com.honey.compilation.java;

import java.util.Random;

import com.honey.compilation.java.ClassDecoration.JavaType;
import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.compiler.CompilationException;
import com.honey.core.compiler.IndentSpace;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.JavaBeansUtil;

/**
 * 基础类
 * @author Administrator
 *
 */
 abstract class BaseClass extends AbstractJavaCompilation {
	
	/**
	 * 是否自动添加属性的getter 和 setter方法(生成java bean)
	 */
	private boolean autoAddGetterMethod;
	
	private boolean autoAddSetterMethod;
	
	/**
	 * 构造函数
	 * @param type 类的名称类型
	 * @throws CompilationException
	 */
    public BaseClass(FullyQualifiedJavaType type) throws CompilationException {
		super(type);
	}
	
    /**
     * 构造函数
     * @param type 类的名称类型
     * @throws CompilationException
     */
	public BaseClass(String type) throws CompilationException {
		super(type);
	}
	
	/**
	 * 构造函数 
	 * @param type 类的名称类型
	 * @param classDecoration 类的修饰
	 * @throws CompilationException 
	 */
	public BaseClass(FullyQualifiedJavaType type,ClassDecoration classDecoration ) throws CompilationException {
		super(type);
		setClassDecoration(classDecoration);
	}
	
	/**
	 * 构造函数
	 * @param type 类的名称类型
	 * @param classDecoration 类的修饰
	 * @throws CompilationException
	 */
	public BaseClass(String type,ClassDecoration classDecoration) throws CompilationException {
		super(type);
		setClassDecoration(classDecoration);
	}

	/**
	 * 设定内部类修饰
	 * @param innerClassDecoration 内部类修饰
	 * @throws CompilationException
	 */
	public void setClassDecoration(InnerClassDecoration innerClassDecoration) throws CompilationException {
		super.setClassDecoration( innerClassDecoration ) ;
	}
	
	/**
	 * 类的修饰规则,对于内部修饰是最开放的,本方基本上由子类覆盖;
	 * @throws CompilationException
	 */
	protected void classDecorationRule() throws CompilationException{
		
	}
	
    /**
     * 内部类不能直接编译,只能在一个顶层类中被编译.
     */
    public CharacterBuilder compiledContent() throws CompilationException {
    	throw new CompilationException(getType().getFullyQualifiedName()+ " : 内部类使不能被直接调用");
	}
    
    /**
     * 添加Serializable
     * @throws CompilationException 
     */
    public void addSerializable() throws CompilationException{
    	FullyQualifiedJavaType type =JDKFullyQualifiedJavaType.getSerializableInstance();
    	addImportedType(type);addSuperInterface(type);
    	Field  field  = new Field("serialVersionUID",JDKFullyQualifiedJavaType.getLongInstance());
    	field.addJavaDocLine("类序列化版本标识");
    	field.setInitializationString(new Random().nextLong()+"L");
    	field.setDecoration(new Decoration(JavaVisibility.PRIVATE,true,true));
    	field.setBaseType(true);
    	addField( field ) ;
    }

    /**
     * 是否自动添加属性的getter方法
     * @return
     */
    public boolean isAutoAddGetterMethod() {
		return autoAddGetterMethod;
	}
    
    /**
     * 是否自动添加属性的setter方法
     * @return
     */
    public boolean isAutoAddSetterMethod() {
		return autoAddSetterMethod;
	}
    
    /**
     * 是否自动添加属性的getter方法(生成java bean).
     * 只生成用private修饰和 final修饰属性.如果属性使用final修饰不会生成setter方法.
     * @param autoAddGetterMethod true:是 false:否
     */
	public void setAutoAddGetterMethod(boolean autoAddGetterMethod) {
		this.autoAddGetterMethod = autoAddGetterMethod;
	}
	
    /**
     * 是否自动添加属性的setter方法(生成java bean).
     * 只生成用private修饰和 final修饰属性.如果属性使用final修饰不会生成setter方法.
     * @param autoAddSetterMethod true:是 false:否
     */
	public void setAutoAddSetterMethod(boolean autoAddSetterMethod) {
		this.autoAddSetterMethod = autoAddSetterMethod;
	}
	
	/**
	 * 添加setter 和 getter方法
	 */
	private void addSetterAndGetterMethod(){
		if( this.autoAddGetterMethod ){
			for(Field field : getFields() ){
				Decoration decoration= field.getDecoration();
				if( !decoration.isStatic() && decoration.getVisibility() == JavaVisibility.PRIVATE){
					Method method = new Method( JavaBeansUtil.getGetterMethodName(field.getName(),field.getType() ) );
					//method.addJavaDocLine("获取" +field.getName()+"属性的值" );
					method.addJavaDocLine("获取" +JavaKeyWord.OPERATION_BRACE_LEFT+"@code "+field.getName()+JavaKeyWord.OPERATION_BRACE_RIGHT+"属性的值" );
					method.setReturnType(field.getType());
					method.addBodyLine("return this."+field.getName());
					method.setBaseType(field.isBaseType());
					addMethod(method);
				}
			}
		}
		if( this.autoAddSetterMethod ){
			for(Field field : getFields() ){
				Decoration decoration= field.getDecoration();
				//如果使用final修饰不产生sett方法
				if( !decoration.isStatic() && decoration.getVisibility() == JavaVisibility.PRIVATE && !decoration.isFinal()){
					Method method = new Method( JavaBeansUtil.getSetterMethodName(field.getName()) );
					method.addJavaDocLine("设定" +JavaKeyWord.OPERATION_BRACE_LEFT+"@code "+field.getName()+JavaKeyWord.OPERATION_BRACE_RIGHT+"属性的值" );
					method.addParameter(new Parameter( field.getType(),field.getName(),null,field.isBaseType() ));
					method.addBodyLine("this."+field.getName()+JavaKeyWord.OPERATION_BRACKET_EQUALS+field.getName());
					method.setBaseType(field.isBaseType());
					addMethod(method);
				}
			}
		}
	}

	/**
     * 编译内容
     * @param indentLevel 级别 
     * @return
     * @throws CompilationException
     */
    public CharacterBuilder compiledContent(int indentLevel) throws CompilationException {
    	CharacterBuilder answer = CharacterBuilderFactory.createC16StringBuilder();
    	classDecorationRule();
    	ClassDecoration classDecoration = getClassDecoration();
    	boolean bool = classDecoration.getJavaType() == JavaType.INTERFACE;
    	
    	//添加setter 和 getter 方法(java bean)
    	addSetterAndGetterMethod();
        // 生成类注释
		super.appendFormattedClassComments(answer, indentLevel );
		
        //注解
        appendFormattedAnnotations(answer, indentLevel);
        
        //类修饰
        //IndentSpace.spaceIndent(answer, indentLevel);
        appendJavaClassDecoration(answer, indentLevel);
        
        //类名称
        appendType(answer, indentLevel);
        
        //范型
        appendGeneric(answer, indentLevel);
        
        //extends
        appendSuperClass(answer, indentLevel);

        //interface
        if( !bool )
        	appendSuperInterfaceTypes(answer, indentLevel);
        
        //类开始 {
        answer.append(JavaKeyWord.OPERATION_BRACE_LEFT); //$NON-NLS-1$
        indentLevel++;
        
        begianCompilationClass(answer, indentLevel);
        //属性
        IndentSpace.newLine(answer);
        appendFields(answer, indentLevel);
        
        //静态块
        appendStaticBlocks(answer, indentLevel);

        //方法
        if(!bool) 
        	appendMethods(answer, indentLevel,false);
        else 
        	appendMethods(answer, indentLevel,true);

        //内部类
        appendInnerClasses(answer, indentLevel);
        
        //内部枚举
        appendInnerEnums(answer, indentLevel);
        
        endCompilationClass(answer, indentLevel);
        //类结束}
        indentLevel--;
        IndentSpace.newSpace(answer,  indentLevel);
        answer.append(JavaKeyWord.OPERATION_BRACE_RIGHT);

        return answer;
    }

    /**
	 * 开始生成类的内容
	 * @param cb
	 * @param indentLevel
	 */
	protected abstract void begianCompilationClass(CharacterBuilder cb, int indentLevel);
	
	/**
	 * 结束生成类的内容
	 * @param cb
	 * @param indentLevel
	 */
	protected abstract void endCompilationClass(CharacterBuilder cb, int indentLevel);
    

    
}
