package com.honey.core;

import org.java.plugin.Plugin;

/**
 * 扩展点,
 * @author Administrator
 *
 */
public abstract class Extension<T extends Plugin> {
	
	/** 扩展点所属的plugin */
	private T belongPlugin;
	
	/**
	 * 构造函数
	 * @param belongPlugin
	 */
	public Extension(T  belongPlugin){
		this.belongPlugin  = belongPlugin;
	}
	
	/**
	 * @return the plugin
	 */
	public final T getBelongPlugin() {
		return belongPlugin;
	}
	
	/**
	 * 清除插入点对象中的数据,避免脏数据影响下次操作.
	 */
	public abstract void clean();
}
