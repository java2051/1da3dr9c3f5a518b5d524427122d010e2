package com.honey.compilation.java;

/**
 * java的可视范围
 * 
 * @author Administrator
 * 
 */
public enum JavaVisibility {

	/** public 可视范围  */
	PUBLIC(JavaKeyWord.PUBLIC.getValue(),true),

	/** private 可视范围 */
	PRIVATE(JavaKeyWord.PRIVATE.getValue(),true),

	/** protected 可视范围*/
	PROTECTED(JavaKeyWord.PROTECTED.getValue(),true),

	/** 默认的可视范围  */
	DEFAULT("",false);

	/** 值 */
	private final String value;
	
	/** 是否添加空格 */
	private final boolean space;

	/**
	 * 构造函数
	 * @param value 值
	 */
	private JavaVisibility(String value, boolean space ) {
		this.value = value;
		this.space = space;
	}

	/**
	 * 获取值
	 * @return
	 */
	public final String getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public final String toString() {
		return value +(space?JavaKeyWord.OPERATION_SPACE:"");
	}
}
