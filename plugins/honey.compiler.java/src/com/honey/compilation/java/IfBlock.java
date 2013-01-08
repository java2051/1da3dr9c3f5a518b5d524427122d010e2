
package com.honey.compilation.java;

/**
 * if语句块
 * @author Administrator
 *
 */
public class IfBlock extends VisibleBlock{
	
	/** 条件语句 */
	private String condition ="true";
	
	/**
	 * 默认构造函数 
	 */
	public IfBlock(){
		
	}
	
	/**
	 * 构造函数
	 * @param condition 条件语句
	 */
	public IfBlock(String condition ){
		this.condition = condition == null?"true":condition;
	}
	
	@Override
	public String startBlock() {
		
		return "if( "+condition+" )" + super.startBlock();
	}
	
	/**
	 * 获取条件语句
	 * @return
	 */
	public String getCondition() {
		return condition;
	}

	
	/**
	 * 设定条件语句
	 * @param condition
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
}
