package com.honey.core.notify;

/**
 * 接收通知
 * @author Administrator
 *
 */
public interface Notify {
	
	/**
	 * 支持信号量类型
	 * @return
	 */
	public Class<Signal>[] supportSignal();
	
	
	/**
	 *  插件执行通知过来的信号
	 * @param signal
	 */
	public void execute( Signal signal );
	
	
	
}
