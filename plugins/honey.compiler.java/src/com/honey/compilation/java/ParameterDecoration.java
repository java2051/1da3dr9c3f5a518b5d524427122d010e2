package com.honey.compilation.java;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;

/**
 * 参数修饰. 参数修饰只有final和动态参数两种, 其它修饰无效. 
 * @author Administrator
 *
 */
public class ParameterDecoration extends Decoration{
	
	/**
	 * 默认的构造函数
	 */
    public ParameterDecoration(){
    	super(JavaVisibility.DEFAULT, false, false);
    }
    
	
	/**
	 * 构造函数
	 * @param isFinal 是否是终态修饰 true:是 false:否
	 */
	public ParameterDecoration(boolean isFinal){
		super(JavaVisibility.DEFAULT, false, isFinal);
	}
	
	/**
	 * 构造函数
	 * @param isFinal 是否是终态修饰 true:是 false:否
	 * @param isDynamicParameter 是否是动态参数
	 */
	public ParameterDecoration(boolean isFinal ,boolean isDynamicParameter){
		super(JavaVisibility.DEFAULT, false, isFinal);
		setVararg(isDynamicParameter);
	}
	
	/**
	 * 是否是动态参数
	 * @return
	 */
    public boolean isVararg() {
    	return DecorationType.VARARG.mask == ( this.decoration&DecorationType.VARARG.mask );
	}
    
	/**
	 * 设定是否是动态参数 
	 * @param isVarargus 
	 */
	public void setVararg(boolean isVarargus) {
		if( isVarargus ){
			this.decoration = this.decoration|DecorationType.VARARG.mask;
		}else{
			this.decoration = this.decoration&DecorationType.VARARG.clean;
		}
	}
	
	/**
	 * 掩码范围第三个四位
	 * @author Administrator
	 *
	 */
    private enum DecorationType {
    	
		/** 构造函数 */
    	VARARG(0400,0177377), // 掩码0001 0000 0000
       //VARARG
    	//varargus
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
		//IndentSpace.spaceIndent(answer, indentLevel);
		if(isFinal()){
			answer.append(JavaKeyWord.FINAL);
		}
		return answer;
	}
}
