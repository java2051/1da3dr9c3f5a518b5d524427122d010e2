
package com.honey.compilation.java;


/**
 * 代码区域(普通代码)
 * @author Administrator
 *
 */
public class DefaultBlock extends Block {

	/**
	 * 构造函数
	 */
	public DefaultBlock() {
		super();
	}
	
	/**
	 * 构造函数
	 * @param line 代码
	 */
	public DefaultBlock(String line) {
		super();
		addBodyLine(line);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.Block#endBlock(int)
	 */
	@Override
	public String endBlock(int indentLevel) {
		return null;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.Block#startBlock()
	 */
	@Override
	public String startBlock() {
		
		return null;
	}
	
}
