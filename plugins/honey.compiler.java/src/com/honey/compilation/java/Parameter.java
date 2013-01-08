
package com.honey.compilation.java;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.types.FullyQualifiedJavaType;

/**
 * 方法传入参数
 * @author Administrator
 *
 */
public class Parameter extends JavaElement {
    
	/** 参数名称 */
	private String name;
    
	/** 参数类型 */
	private FullyQualifiedJavaType type;
    
	/** 是否强制使用基础类型 */
	boolean isBase = false ;
	
	/**
	 * 构造函数
	 * @param type 参数类型
	 * @param name 参数名称
	 * @param isBase 是否强制使用基础类型
	 */
    public Parameter(FullyQualifiedJavaType type, String name, boolean isBase) {
    	this(type,name );
        this.isBase = isBase;
    }
    
    /**
     * 构造函数
     * @param type 参数类型
     * @param name 参数名称
     */
    public Parameter(String type, String name) {
        this(new  FullyQualifiedJavaType( type ),name );
    }

    /**
     * 构造函数
     * @param type 参数类型
     * @param name 参数名称
     * @param javadoc 参数注释
     */
    public Parameter(String type, String name,String javadoc) {
        this(new  FullyQualifiedJavaType( type ),name );
        addJavaDocLine(javadoc);
    }

    /**
     * 构造函数
     * @param type 参数类型
     * @param name 参数名称
     */
    public Parameter(FullyQualifiedJavaType type, String name) {
        super();
        this.name = name;
        this.type = type;
    }

    /**
     * 构造函数
     * @param type 参数类型
     * @param name 参数名称
     */
    public Parameter(FullyQualifiedJavaType type, String name,String javadoc) {
        super();
        this.name = name;
        this.type = type;
        addJavaDocLine(javadoc);
    }
    
    /**
     * 构造函数
     * @param type 参数类型
     * @param name 参数名称
     * @param annotation 注解
     * @param isBase 是否强制使用基础类型
     */
    public Parameter(FullyQualifiedJavaType type, String name, String annotation,boolean isBase) {
        this(type, name ,isBase);
        addAnnotation(annotation);
    }
    
//    /**
//     * 构造函数
//     * @param type 参数类型
//     * @param name 参数名称
//     * @param annotation 注解
//     */
//    public Parameter(FullyQualifiedJavaType type, String name, String annotation) {
//        this(type, name);
//        addAnnotation(annotation);
//    }

    /**
     * 获取参数名称
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 获取参数类型
     * @return
     */
    public FullyQualifiedJavaType getType() {
        return type;
    }
    
    @Override
    public CharacterBuilder compiledContent( int indentLevel ) {
    	CharacterBuilder answer = CharacterBuilderFactory.createC16StringBuilder();
    	
    	//参数注解
        for (String annotation : getAnnotations()) {
        	annotation = annotation.trim();
        	if( !annotation.startsWith("@")){
        		annotation ="@"+annotation;
        	}
            answer.append(annotation)
            .append(JavaKeyWord.OPERATION_SPACE);
        }

        //修饰
        Decoration decoration = getDecoration();
        if(decoration.isFinal()) answer.append(JavaKeyWord.FINAL);
        
        //参数类型
        if(isBase && type.isPrimitive() )answer.append(type.getBaseShortName());
        else answer.append(type.getFullName());
        answer.append(JavaKeyWord.OPERATION_SPACE);
        
        //参数名称
        if( decoration instanceof ParameterDecoration && ((ParameterDecoration)decoration).isVararg()){
        	answer.append(JavaKeyWord.DYNAMIC_PARAMETER);
        }
        answer.append(name);

        //参数注释
        addFormattedComment(answer);

        return answer;
    }

	public boolean isBaseType() {
		return isBase;
	}

	public void setBaseType(boolean isBaseType) {
		this.isBase = isBaseType;
	}

	/**
	 * 获取注释
	 * @return
	 */
	public String getJavadoc(){
		StringBuilder sb = new StringBuilder();
		for(String javadoc : getJavaDocLines() ){
			sb.append(javadoc);
		}
		return sb.toString();
	}
	
	/**
	 * 注意函数参数修饰只有final有效
	 */
	@Override
	public void setDecoration(Decoration decoration) {
		super.setDecoration(decoration);
	}
	
	/**
	 * 设定参数修饰
	 * @param decoration
	 */
	public void setDecoration(ParameterDecoration decoration) {
		super.setDecoration(decoration);
	}
	
	@Override
    public String toString() {
        return compiledContent(0).toString();
    }
	
	@Override
	public int hashCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("name").append(type.toString());
		return sb.toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Parameter ){
			Parameter par =(Parameter)obj;
			StringBuilder salf = new StringBuilder();
			salf.append(this.name).append(this.type.toString());

			StringBuilder same = new StringBuilder();
			same.append(par.name).append(type.toString());
			
			return salf.toString().equals(same.toString());
		}else{
			return super.equals(obj);
		}
	}
}
