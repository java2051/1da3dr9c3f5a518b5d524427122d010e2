
package com.honey.compilation.java;


/**
 * 同步块
 * @author Administrator
 *
 */
public class SynchronizedBlock extends VisibleBlock{

	/** 互斥对象,默认使用对象自己 */
	private String mutex ="this";
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.VisibleBlock#startBlock()
	 */
	@Override
	public String startBlock() {
		return "synchronized ("+mutex+") " + super.startBlock();
	}

	/**
	 * 设定互斥对象
	 * @return
	 */
	public String getMutex() {
		return mutex;
	}

	/**
	 * 设定互斥对象
	 * @param mutex
	 */
	public void setMutex(String mutex) {
		this.mutex = mutex;
	}
}
