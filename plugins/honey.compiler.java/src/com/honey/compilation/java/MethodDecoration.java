package com.honey.compilation.java;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.compiler.IndentSpace;

/**
 * 方法修饰
 * @author Administrator
 *
 */
public class MethodDecoration extends Decoration{
	
	/**
	 * 默认的构造函数
	 */
    public MethodDecoration(){
    	super();	
    }
    
    /**
     * 构造函数
     * @param visibility 方法可视范围 
     */
	public MethodDecoration(JavaVisibility visibility){
		super(visibility);
	}
	
	/**
	 * 构造函数
	 * @param visibility 方法可视范围
	 * @param constructor 是否是构造函数
	 */
	public MethodDecoration(JavaVisibility visibility,boolean constructor){
		super(visibility);
		setConstructor(constructor);
	}
	
	/**
	 * 构造函数
	 * @param visibility 方法可视范围
	 * @param isStatic 是否是静态修饰 true:是 false:否
	 * @param isFinal 是否是终态修饰 true:是 false:否
	 */
	public MethodDecoration(JavaVisibility visibility,boolean isStatic , boolean isFinal){
		super(visibility, isStatic, isFinal);
	}
	
	/**
	 * 构造函数
	 * @param visibility 方法可视范围
	 * @param isStatic 是否是静态修饰 true:是 false:否
	 * @param isFinal 是否是终态修饰 true:是 false:否
	 * @param isConstructor 是否是构造函数 true:是 false:否
	 * @param isSynchronized 是否是同步方法 true:是 false:否
	 */
	public MethodDecoration(JavaVisibility visibility,boolean isStatic , boolean isFinal,boolean isConstructor,boolean isSynchronized){
		super(visibility, isStatic, isFinal);
		setSynchronized(isSynchronized);
		setConstructor(isConstructor);
	}
    
	/**
	 * 是否是构造方法
	 * @return
	 */
    public boolean isConstructor() {
    	return DecorationType.CONSTRUCTOR.mask == ( this.decoration&DecorationType.CONSTRUCTOR.mask );
	}
	
	/**
	 * 构造函数不能用final satic 和synchronized 修饰
	 * @param constructor
	 */
	public void setConstructor(boolean constructor) {
		if( constructor ){
			this.decoration = this.decoration|DecorationType.CONSTRUCTOR.mask;
			super.setFinal(false);
			super.setStatic(false);
			setSynchronized(false);
			setAbstract(false);
		}else{
			this.decoration = this.decoration&DecorationType.CONSTRUCTOR.clean;
		}
	}
	
	/**
	 * 是否是同步方法 true:是 false:否 
	 * @return
	 */
	public boolean isSynchronized() {
		return DecorationType.SYNCHRONIZED.mask == ( this.decoration&DecorationType.SYNCHRONIZED.mask );
	}
	
	/**
	 * 设定同步方法 
	 * @param isSynchronized true:是 false:否
	 */
	public void setSynchronized(boolean isSynchronized) {
		if( isConstructor() ){
			isSynchronized = false ;
		}
		if( isSynchronized ){
			this.decoration = this.decoration|DecorationType.SYNCHRONIZED.mask;
		}else{
			this.decoration = this.decoration&DecorationType.SYNCHRONIZED.clean;
		}
	}
	
	/**
	 * 是否是虚方法 true:是 false:否 
	 * @return
	 */
	public boolean isAbstract() {
		return DecorationType.ABSTRACT.mask == ( this.decoration&DecorationType.ABSTRACT.mask );
	}
	
	/**
	 * 设定虚方法 
	 * @param isSynchronized true:是 false:否
	 */
	public void setAbstract(boolean isAbstract) {
		if( isConstructor() ){
			isAbstract = false ;
		}
		if( isAbstract ){
			this.decoration = this.decoration|DecorationType.ABSTRACT.mask;
			super.setFinal(false);
			super.setStatic(false);
			setSynchronized(false);
		}else{
			this.decoration = this.decoration&DecorationType.ABSTRACT.clean;
		}
	}
	
	
	/**
	 * 掩码范围第二个四位
	 * @author Administrator
	 *
	 */
    private enum DecorationType {
    	
		/** 构造函数 */
    	CONSTRUCTOR(020,0177757), // 掩码0001 0000

		/** 同步 */
    	SYNCHRONIZED(040,0177737), // 掩码0010 0000
    	
    	/** 虚方法*/
    	ABSTRACT(0100,0177677), // 掩码0100 0000
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
		if(! isConstructor() ){	
			if( isAbstract() ){
				answer.append(JavaKeyWord.ABSTRACT);
			}else{
				if( isSynchronized() )
					answer.append(JavaKeyWord.SYNCHRONIZED);
				
				if(isStatic() )
					answer.append(JavaKeyWord.STATIC);
				if(isFinal() )
					answer.append(JavaKeyWord.FINAL);
			}
		}
		
		return answer;
	}
}
