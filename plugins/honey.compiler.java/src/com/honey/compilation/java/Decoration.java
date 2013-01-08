package com.honey.compilation.java;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.compiler.CompilationElement;
import com.honey.core.compiler.IndentSpace;

/**
 * java 修饰 .例如: 类的修饰,方法修饰,属性修饰,参数修饰
 * @author Administrator
 *
 */
public class Decoration implements CompilationElement {
	/** 修饰位 */
    protected int decoration = 0x0;
    
	
	/** 可视范围修饰(public private protected 和 default ) */
	private JavaVisibility visibility;

    /**
     * 修饰事例:
     * 	class ex{<br />
     * 	<br />
     * 	}<br />
     * 
     */
	public Decoration(){
		visibility = JavaVisibility.DEFAULT ;
	}
	
	/**
     * 构造函数 
	 * @param visibility 可视范围
	 */
	public Decoration(JavaVisibility visibility){
		this.visibility =  visibility == null ? visibility = JavaVisibility.DEFAULT : visibility ;
	}
	
	/**
	 * 构造函数
	 * @param isStatic 是否是静态修饰 true:是 false:否
	 * @param isFinal
	 */
	public Decoration(boolean isStatic , boolean isFinal){
		this.visibility = JavaVisibility.DEFAULT;
		setStatic(isStatic);
		setFinal(isFinal);
		
	}
    
	/**
	 * 构造函数
	 * @param visibility 可视范围
	 * @param isStatic  是否是静态修饰 true:是 false:否
	 * @param isFinal 是否是终态修饰 true:是 false:否
	 */
	public Decoration(JavaVisibility visibility,boolean isStatic , boolean isFinal){
		this.visibility =  visibility == null ? visibility = JavaVisibility.DEFAULT : visibility ;
		setStatic(isStatic);
		setFinal(isFinal);
	}
    
	/**
	 * 获取可视范围
	 * @return
	 */
	public JavaVisibility getVisibility() {
        return visibility;
    }
	
	/**
	 * 设定可视范围
	 * @param visibility 如果visibility为null设定default修饰
	 */
    public void setVisibility(JavaVisibility visibility){
    	this.visibility =  visibility == null ? visibility = JavaVisibility.DEFAULT : visibility ;
    }

    /**
	 * 判断是否存在掩码位
	 * @param constraint
	 * @return
	 */
	protected final boolean isMask( DecorationType decoration ){
		return decoration.mask == ( this.decoration&decoration.mask );
	}
    
	/**
	 * 是否是终态修饰  true:是 false:否
	 * @return 
	 */
	public boolean isFinal() {
		return isMask(DecorationType.FINAL);
	}
	
	/**
	 * 设定终态修饰
	 * @param isFinal  true:是 false:否
	 */
	public void setFinal(boolean isFinal) {
		if( isFinal )
			this.decoration = this.decoration|DecorationType.FINAL.mask;
		else
			this.decoration = this.decoration&DecorationType.FINAL.clean;
	}
	
	/**
	 * 是否是静态修饰  true:是 false:否
	 * @return
	 */
	public boolean isStatic() {
		return isMask(DecorationType.STATIC);
	}
	
	/**
	 * 设定静态修饰
	 * @param isStatic  true:是 false:否
	 */
	public void setStatic(boolean isStatic) {
		if( isStatic )
			this.decoration = this.decoration|DecorationType.STATIC.mask;
		else
			this.decoration = this.decoration&DecorationType.STATIC.clean;
	}
	

	/**
	 * 掩码范围第一个四位
	 * @author Administrator
	 *
	 */
    private enum DecorationType {
    	
    	
    	/** 未定义声明 */
		UNDEFINE(0177777,0177777), // 掩码1111 1111 1111 1111

		/** 静态声明 */
		STATIC(01,0177776), // 掩码0000 0001

		/** 终态声明 */
		FINAL(02,0177775), // 掩码0000 0010

		/** 终态声明 */
		ABSTRACT(03,0177775),//掩码0000 0100
		
		;
		private final int mask;
		
		private final int clean;
		
		private DecorationType(int mask, int clean){
			this.mask = mask;
			this.clean = clean;
		}
		
    }

    /*
     * (non-Javadoc)
     * @see com.honey.core.dom.CompilationElement#compiledContent(int)
     */
	@Override
	public CharacterBuilder compiledContent(int indentLevel) {
		CharacterBuilder answer = CharacterBuilderFactory.createC16StringBuilder();
		IndentSpace.spaceIndent(answer, indentLevel);
		answer.append(this.getVisibility());
		if(isStatic() )
			answer.append(JavaKeyWord.STATIC);
		if(isFinal() )
			answer.append(JavaKeyWord.FINAL);
		
		return answer;
	}
	
}
