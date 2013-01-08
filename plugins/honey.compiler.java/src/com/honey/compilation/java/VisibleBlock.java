package com.honey.compilation.java;

/**
 * 可视区域块
 * @author Administrator
 *
 */
public class VisibleBlock  extends Block{

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.Block#endBlock(int)
	 */
	@Override
	public String endBlock(int indentLevel) {
		
		return "}";
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.Block#startBlock()
	 */
	@Override
	public String startBlock() {
		
		return "{";
	}

	
}
