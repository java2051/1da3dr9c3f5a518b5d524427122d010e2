
package com.honey.compilation.java;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;

/**
 * java类的 属性
 * @author Administrator
 *
 */
public class Field extends JavaElement {
	
	/** 类型 */
	private FullyQualifiedJavaType type;
	
	/** 名称 */
    private String name;
    
    /** 初始化值 */
    private String initializationString;
    
    /** 是否使用默认值作为初始化值 */
    private boolean isUseDefaultValue = false;
    
    /** 是否转换成基本类型 */
    private boolean isBaseType =false;
    
    /**
     * 构造函数
     * @param fieldName 属性名称,默认使用int类型
     */
    public Field(String fieldName) {
        this(fieldName, JDKFullyQualifiedJavaType.getIntInstance());
    }
    
    /**
     * 构造函数
     * @param fieldName  属性名称
     * @param type 属性类型
     */
    public Field(String fieldName, FullyQualifiedJavaType type) {
        super();
        this.name = fieldName;
        this.type = type;
        setDecoration(new Decoration(JavaVisibility.PRIVATE));
    }
    
    /**
     * 构造函数
     * @param fieldName  属性名称
     * @param type 属性类型
     * @param javadoc javadoc
     */
    public Field(String fieldName, FullyQualifiedJavaType type,String javadoc) {
        super();
        this.name = fieldName;
        this.type = type;
        setDecoration(new Decoration(JavaVisibility.PRIVATE));
        addJavaDocLine(javadoc);
    }
    
    /**
     * 构造函数
     * @param fieldName 属性名称
     * @param type  属性类型
     * @param decoration 属性修饰
     */
    public Field(String fieldName, FullyQualifiedJavaType type, Decoration  decoration) {
        this(fieldName, type);
        setDecoration(decoration);
    }
    
    /**
     * 构造函数
     * @param fieldName 属性名称
     * @param type 属性类型
     * @param decoration 属性修饰
     * @param javadoc 注释
     */
    public Field(String fieldName, FullyQualifiedJavaType type, Decoration  decoration,String javadoc) {
        this(fieldName, type);
        setDecoration(decoration);
        addJavaDocLine(javadoc);
    }
    
    /**
     * 构造函数
     * @param fieldName 属性名称
     * @param type  属性类型
     * @param isBaseType 是否是基础类型
     */
    public Field(String fieldName, FullyQualifiedJavaType type,boolean isBaseType ) {
        this(fieldName, type);
        setBaseType(isBaseType);
    }
    /**
     * 是否强制使用基础类型 
     * @return
     */
    public boolean isBaseType() {
		return isBaseType;
	}
    
    /**
     * 是否强制使用基础类型
     * @param isBaseType
     */
	public void setBaseType(boolean isBaseType) {
		this.isBaseType = isBaseType;
	}

	/**
	 * 获取属性名称
	 * @return
	 */
    public String getName() {
        return name;
    }

   /**
    * 获取属性类型
    * @return
    */
    public FullyQualifiedJavaType getType() {
        return type;
    }

    
    /**
     * 设定属性类型
     * @param type
     */
    public void setType(FullyQualifiedJavaType type) {
        this.type = type;
    }

    /**
     * 获取属性初始化值
     * @return
     */
    public String getInitializationString() {
        return initializationString;
    }

   /**
    * 设定属性初始化值
    * @param initializationString 字符串
    */
    public void setInitializationString(String initializationString) {
    	setInitializationString(initializationString, true);
    }

    /**
     * 设定属性初始化值
     * @param initializationString 字符串
     */
     public void setInitializationString(String initializationString,boolean isFormat) {
         this.initializationString = initializationString;
         if( isFormat )this.initializationString = this.type.formatValue(this.initializationString);
     }

    
    /**
     * 设定属性初始化值
     * @param initializationNewObject 指定对象
     */
    public void setInitializationNewObject(java.lang.Class<?> initializationNewObject) {
        this.initializationString ="new "+ initializationNewObject.getSimpleName();
    }
    
    public boolean isUseDefaultValue() {
		return isUseDefaultValue;
	}

    /**
     * 是否使用类型的默认值作为属性的初始值
     * @param isUseDefaultValue
     */
	public void setUseDefaultValue(boolean isUseDefaultValue) {
		this.isUseDefaultValue = isUseDefaultValue;
	}

	/***
     * 编译内容
     * @param indentLevel 缩进级别
     * @return
     */
    @Override
    public CharacterBuilder compiledContent(int indentLevel) {
    	CharacterBuilder answer = CharacterBuilderFactory.createC16StringBuilder();

        addFormattedJavaMultiDoc(answer, indentLevel,false);
        addFormattedAnnotations(answer, indentLevel);

        answer.append(getDecoration().compiledContent(indentLevel));
        if(isBaseType && type.isPrimitive()){
        	answer.append(type.getBaseShortName());
        }else{
        	answer.append(type.getFullName());
        }
        answer.append(JavaKeyWord.OPERATION_SPACE)
        .append(name);

        if (initializationString != null && initializationString.length() > 0 ) {
            answer.append(JavaKeyWord.OPERATION_BRACKET_EQUALS) 
            .append(initializationString);
        }else if(getDecoration().isFinal() && isUseDefaultValue ){
        	answer.append(JavaKeyWord.OPERATION_BRACKET_EQUALS)
        	.append(getType().getInitializationValue());
        }

        answer
        .append(JavaKeyWord.OPERATION_SPACE)
        .append(JavaKeyWord.OPERATION_SPLIT);
        
        addFormattedComment(answer);
        return answer;
    }
}
