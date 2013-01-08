
package com.honey.compilation.java;

import java.util.ArrayList;
import java.util.List;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.compiler.CompilationElement;
import com.honey.core.compiler.IndentSpace;
import com.honey.core.utils.StringUtility;

/**
 * java类中抽象出来的数据结构
 * @author Administrator
 *
 */
public abstract class JavaElement implements CompilationElement{

	/** 注释 */
	private List<String> javaDocLines;

	/** 这个注释是跟在语句尾部的注释. */
	private String comment;
    
	/** 修饰 */
    private Decoration decoration ;
    
    /** 注解 */
    private List<String> annotations;

    /** 默认的构造函数 */
    public JavaElement() {
        super();
        decoration =  new Decoration();
        javaDocLines = new ArrayList<String>();
        annotations = new ArrayList<String>();
    }
    
    /**
     * 构造函数
     * @param decoration 修饰
     */
    public JavaElement(Decoration decoration ) {
    	this() ;
    	setDecoration(decoration);
    }
    
    /**
     * 获取注释
     * @return
     */
    public List<String> getJavaDocLines() {
        return javaDocLines;
    }
    
    /**
     * 添加注释
     * @param javaDocLines
     */
    public void addJavaDocLine(String ...javaDocLines) {
        for(String s : javaDocLines){
        	if(s!= null )
        		this.javaDocLines.add(s);
        }
    }
    
    /**
     * 获取注解
     * @return
     */
    public List<String> getAnnotations() {
        return annotations;
    }
    
    /**
     * 获取修饰
     * @return
     */
    public Decoration getDecoration() {
		return decoration;
	}
    
    /**
     * 设定修饰
     * @param decoration 修饰 
     */
	public void setDecoration(Decoration decoration) {
		if(decoration == null) return ;
		this.decoration = decoration;
	}
    
    /**
     * 添加注解
     * @param annotations 注解
     */ 
    public void addAnnotation(String ...annotations) {
    	for( String  annotation : annotations)
    		if (StringUtility.stringHasValue(annotation) ) this.annotations.add(annotation);
    }
    
    /**
     * 添加解除警告的注解
     */
    public void addSuppressTypeWarningsAnnotation() {
        addAnnotation("@SuppressWarnings(\"unchecked\")");
    }

    /**
     * 语句尾部的注释
     * @return
     */
	public String getComment() {
		return comment;
	}

	/**
	 * 语句尾部注释
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
    
    /**
     * 添加单行注释
     * @param cb CharacterBuilder
     * @param indentLevel 缩进
     */
    protected final void addFormattedJavaSingleDoc(CharacterBuilder cb, int indentLevel) {
    	if(javaDocLines.size() <=0) return ;
    	for (String javaDocLine : javaDocLines) {
    		IndentSpace.newLine(cb);
    		IndentSpace.spaceIndent(cb, indentLevel);
            cb.append("// ").append(javaDocLine);
        }
    }

    /**
     * 格式化多行注释
     * @param cb CharacterBuilder
     * @param indentLevel 缩进级别
     */
    protected final void addFormattedJavaMultiDoc(CharacterBuilder cb, int indentLevel) {
    	addFormattedJavaMultiDoc(cb, indentLevel, true);
    }
    
    /**
     * 添加多行注释
     * @param cb
     * @param indentLevel
     */
    protected final void addFormattedJavaMultiDoc(CharacterBuilder cb, int indentLevel, boolean newLine) {
    	if(javaDocLines.size() <=0) return ;
    	IndentSpace.spaceIndent(cb, indentLevel);
    	cb.append("/** ");
    	if( newLine )IndentSpace.newLine(cb);
    	for (String javaDocLine : javaDocLines) {
    		if( newLine )IndentSpace.spaceIndent(cb, indentLevel);
    		if( newLine )cb.append(" * ");
            cb.append(javaDocLine);
            if( newLine )IndentSpace.newLine(cb);
        }
    	if( newLine )IndentSpace.spaceIndent(cb, indentLevel);
    	cb.append(" */");
    	IndentSpace.newLine(cb);
    }

    /**
     * 添加注释
     * @param cb
     * @param indentLevel 
     */
    protected final void addFormattedJavaDoc(CharacterBuilder cb, int indentLevel) {
    	if(javaDocLines.size() <=0) return ;
    	for (String javaDocLine : javaDocLines) {
            IndentSpace.spaceIndent(cb, indentLevel);
            cb.append(javaDocLine);
            IndentSpace.newLine(cb);
        }
    }
    
    /**
     * 格式化注解
     * @param cb
     * @param indentLevel
     */
    protected final void addFormattedAnnotations(CharacterBuilder cb, int indentLevel) {
    	for (String annotation : annotations) {
        	annotation = annotation.trim();
        	if( !annotation.startsWith("@")){
        		annotation ="@"+annotation;
        	}
        	IndentSpace.spaceIndent(cb, indentLevel);
            cb.append(annotation);
            IndentSpace.newLine(cb);
        }
    }
    
    /**
     * 在代码的尾部添加注释. 注释的格式是 \/*comment*\/ 
     * @param cb
     * @param indentLevel
     */
    protected final void addFormattedComment(CharacterBuilder cb) {
    	if(comment != null )
    		cb.append("/*").append(this.comment).append("*/");
    }

    /**
     * 在代码的尾部添加注释. 注释的格式是 \/*comment*\/ 
     * @param cb
     * @param indentLevel
     */
    protected final void addFormattedComment(CharacterBuilder cb,String  comment) {
    	if(comment != null )
    		cb.append("/*").append(comment).append("*/");
    }
}
