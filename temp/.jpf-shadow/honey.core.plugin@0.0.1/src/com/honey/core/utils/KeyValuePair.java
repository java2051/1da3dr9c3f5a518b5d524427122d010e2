package com.honey.core.utils;

/**
 * 键值对
 * @author Administrator
 *
 */
public class KeyValuePair {
	
	/** 键  */
	private String key;
	
	/** 值  */
	private String value;

	/**
	 * 构造函数
	 * @param key 键
	 * @param value 值
	 */
	public KeyValuePair(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 获取键
	 * @return the key
	 */
	public final String getKey() {
		return key;
	}

	/**
	 * 设定键
	 * @param key 键名称
	 */
	public final void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取值
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * 设定值
	 * @param value 值
	 */
	public final void setValue(String value) {
		this.value = value;
	}
}
