package com.honey.compilation.java;

import com.honey.core.builder.CharacterBuilder;

/**
 * java 属性修饰
 * 
 * @author Administrator
 * 
 */
public class FieldDecoration extends Decoration {

	/**
	 * 默认的构造函数
	 */
	public FieldDecoration(){
		super(JavaVisibility.PRIVATE );
	}
	
	/**
     * 构造函数 
	 * @param visibility 可视范围
	 */
	public FieldDecoration(JavaVisibility visibility){
		super(visibility);
	}
	
	/**
	 * 构造函数
	 * @param isStatic 是否是静态修饰 true:是 false:否
	 * @param isFinal
	 */
	public FieldDecoration(boolean isStatic , boolean isFinal){
		super(JavaVisibility.PRIVATE,isStatic,isFinal);
		
	}
    
	/**
	 * 构造函数
	 * @param visibility 可视范围
	 * @param isStatic  是否是静态修饰 true:是 false:否
	 * @param isFinal 是否是终态修饰 true:是 false:否
	 */
	public FieldDecoration(JavaVisibility visibility,boolean isStatic , boolean isFinal){
		super(visibility,isStatic,isFinal);
	}
	
	/**
	 * 是否是终态修饰  true:是 false:否
	 * @return 
	 */
	public boolean isVolatile() {
		return DecorationType.VOLATILE.mask == ( this.decoration&DecorationType.VOLATILE.mask );
	}

	/**
	 * volatile 轻量级的线程安全,如果使用volatile和final不能同时使用. 本方法会强制把fianl设置为false
	 * 
	 * @param isVolatile
	 *            是否使用 轻量级的线程安全 true:是 false:否
	 */
	public void setVolatile(boolean isVolatile) {
		if( isVolatile ){
			this.decoration = this.decoration|DecorationType.VOLATILE.mask;
			this.setFinal(false);
		}else{
			this.decoration = this.decoration&DecorationType.VOLATILE.clean;
		}
	}
	
	@Override
	public CharacterBuilder compiledContent(int indentLevel) {
		CharacterBuilder answer = super.compiledContent(indentLevel);
		if(isVolatile())
			answer.append(JavaKeyWord.VOLATILE);
		
		return answer;
	}

	private enum DecorationType {
		/** 终态声明 */
		VOLATILE(04,0177773), // 掩码0000 0100
	
		;
		private final int mask;
	
		private final int clean;
	
		private DecorationType(int mask, int clean) {
			this.mask = mask;
			this.clean = clean;
		}
	}
}
