
package com.honey.compilation.java;

/**
 * else if语句块
 * @author Administrator
 *
 */
public class ElseIfBlock extends VisibleBlock{
	
	/** 条件语句 */
	private String condition ="true";
	
	/**
	 * 默认构造函数 
	 */
	public ElseIfBlock(){
		
	}
	
	/**
	 * 构造函数
	 * @param condition 条件语句
	 */
	public ElseIfBlock(String condition ){
		this.condition = condition == null?"true":condition;
	}
	
	@Override
	public String startBlock() {
		
		return "else if( "+condition+" )" + super.startBlock();
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
