package com.honey.compilation.java;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.compiler.CompilationException;
import com.honey.core.compiler.IndentSpace;

/**
 * 
 * inner java 类的修饰
 * 
 * @author Administrator
 *
 */
public class ClassDecoration extends Decoration {

	/** java类的类型(interface,class,enum,abstract class) */
	protected JavaType javaType ;

	/**
	 * 构造函数
	 */
	public ClassDecoration(){
		super();
		javaType = JavaType.CLASS;
	}
	
	/**
	 * 构造函数,注意java类可视范围只能用 default 和 public 两种关键字修饰
	 * @param visibility 类的可视范围,只能用 default 和 public两种关键字修饰
	 * @throws CompilationException
	 */
	public ClassDecoration(JavaVisibility visibility) throws CompilationException{
		super(visibility);
		setJavaType(null);
	}
	/**
	 * 修饰事例:
     * ${visibility} ${javatype} ex{<br />
     * 	<br />
     * 	}<br />
     * 
	 * @param visibility 可视范围(default和public)
	 * @param javatype 类型(interface, class, enum,abstract class)
	 * @throws CompilationException 
	 */
	public ClassDecoration(JavaVisibility visibility, JavaType javatype) throws CompilationException{
		this(visibility);
		setJavaType(javatype);
	}
	
	/**
	 * public ${isStatic}  ${isFinal} class ex{<br />
     * 	<br />
     * 	}<br />
	 * @param isStatic
	 * @param isFinal
	 * @throws CompilationException
	 */
	public ClassDecoration(boolean isStatic , boolean isFinal) throws CompilationException{
		this(JavaVisibility.PUBLIC, JavaType.CLASS, isStatic, isFinal);
	}
	
	
	
	/**
	 * 修饰事例:
     * ${visibility} ${isStatic}  ${isFinal} class ex{<br />
     * 	<br />
     * 	}<br />
	 * @param visibility
	 * @param isStatic
	 * @param isFinal
	 * @throws CompilationException
	 */
	public ClassDecoration(JavaVisibility visibility,boolean isStatic , boolean isFinal) throws CompilationException{
		this(visibility, JavaType.CLASS, isStatic, isFinal);
	}
	
	/**
     * 修饰事例:
     * ${visibility} ${isStatic}  ${isFinal} ${javatype} ex{<br />
     * 	<br />
     * 	}<br />
	 * @param visibility 可视范围
	 * @param javatype java类的类型
	 * @param isStatic 是否是静态修饰
	 * @param isFinal 是否是终态修饰
	 */
	public ClassDecoration(JavaVisibility visibility,JavaType javatype,boolean isStatic , boolean isFinal) throws CompilationException{
		super(visibility, isStatic, isFinal);
		setJavaType(javatype);
	}
	
	/**
	 * 获取java类的类型
	 * @return
	 */
	public JavaType getJavaType() {
		return this.javaType;
	}

	/**
	 * 设定java类的类型,如果设定为虚类那么不能使用final修饰,将final强制修成false
	 * @param javaType
	 */
	public void setJavaType(JavaType javaType)throws CompilationException {
		JavaVisibility visibility= getVisibility();
		if(visibility.equals(JavaVisibility.PRIVATE ) || visibility.equals(JavaVisibility.PROTECTED )){
    		throw new CompilationException(" java类的可视范围只能用default 和 public 两种关键字修饰");
    	}
		if(javaType != null ){
			this.javaType = javaType;
			if( javaType.equals( JavaType.ABSTRACTCLASS) )
				setFinal(false);
		}else{
			this.javaType = JavaType.CLASS;
		}
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.Decoration#setVisibility(com.honey.compilation.java.JavaVisibility)
	 */
	@Override
	public void setVisibility(JavaVisibility visibility){
    	if(visibility.equals(JavaVisibility.PRIVATE ) || visibility.equals(JavaVisibility.PROTECTED )){
    		super.setVisibility(JavaVisibility.DEFAULT);
    	}else{
    		super.setVisibility(visibility);
    	}
    }
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.Decoration#compiledContent(int)
	 */
	@Override
	public CharacterBuilder compiledContent(int indentLevel) {
		CharacterBuilder answer = CharacterBuilderFactory.createC32StringBuilder();
		IndentSpace.spaceIndent(answer, indentLevel);
		answer.append(this.getVisibility());
		
		if(isStatic() )
			answer.append(JavaKeyWord.STATIC);
		if(isFinal() )
			answer.append(JavaKeyWord.FINAL);
		
		answer.append(javaType);
		
		return answer;
	}



	/**
	 * java下class的分类
	 * @author Administrator
	 *
	 */
	public enum JavaType {
		/**
		 * 接口类( interface  )
		 */
		INTERFACE (JavaKeyWord.INTERFACE),
		
		/**
		 * 类( class )
		 */
	    CLASS (JavaKeyWord.CLASS),
	    
	    /**
	     * 枚举类 ( enum )
	     */
	    ENUM (JavaKeyWord.ENUM),
	    
	    /**
	     * 虚类 ( abstract class )
	     */
	    ABSTRACTCLASS (JavaKeyWord.ABSTRACTCLASS);
		
		private final JavaKeyWord javaKeyWord ;
		
		private JavaType(JavaKeyWord javaKeyWord ){
			this.javaKeyWord = javaKeyWord;
		}
		
		@Override
		public String toString() {
			
			return javaKeyWord.toString();
		}
	}
}
