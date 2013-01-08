
package com.honey.compilation.java;



/**
 * else 语句块
 * @author Administrator
 *
 */
public class ElseBlock extends VisibleBlock{
	
	/**
	 * 默认构造函数 
	 */
	public ElseBlock(){
		
	}
	
	@Override
	public String startBlock() {
		
		return "else" + super.startBlock();
	}

}
