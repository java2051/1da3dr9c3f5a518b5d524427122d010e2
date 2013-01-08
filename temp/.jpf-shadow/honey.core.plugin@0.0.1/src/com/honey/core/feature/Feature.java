package com.honey.core.feature;

/**
 * 
 * 对编译的内容划分成块,实现子类分别实现块结构的特征.
 * @author Administrator
 *
 */
public interface Feature {
	
	/**
	 * 内容块的名称
	 * @return
	 */
	public String blockName();
	
	/**
	 * 当前名称所指定的块的特征值
	 * @return
	 */
	public String feater();
	
	/**
	 * 用于识别块的唯一值.
	 * @return
	 */
	public int blockIdentity();
	
}
