
package com.honey.compilation.java;

/**
 * 静态块
 * @author Administrator
 *
 */
public class StaticBlock extends VisibleBlock{

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.VisibleBlock#startBlock()
	 */
	@Override
	public String startBlock() {
		return "static" + super.startBlock();
	}
	

}
