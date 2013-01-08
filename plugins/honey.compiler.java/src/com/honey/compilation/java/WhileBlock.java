
package com.honey.compilation.java;


/**
 * while语句块
 * @author Administrator
 *
 */
public class WhileBlock extends VisibleBlock{
	
	/** while条件语句 */
	private String condition ="true";
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.VisibleBlock#startBlock()
	 */
	@Override
	public String startBlock() {
		return "while( "+condition+" ) " + super.startBlock();
	}

	/**
	 * 获取条件
	 * @return
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * 设定条件
	 * @param condition
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
}
