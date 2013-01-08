package com.honey.core.notify;

/**
 * 通知的信号
 * @author Administrator
 *
 */
public class Signal {
	
	/** 通知信号的来源插件  */
	private String fromPlugin = null;
	
	/** 通知信号的目的插件 */
	private String toPlugin = null;
	
	/** 通知信号的内容 */
	private Object attachment = null;

	/**
	 * @return the fromPlugin
	 */
	public final String getFromPlugin() {
		return fromPlugin;
	}

	/**
	 * @param fromPlugin the fromPlugin to set
	 */
	public final void setFromPlugin(String fromPlugin) {
		this.fromPlugin = fromPlugin;
	}

	/**
	 * @return the toPlugin
	 */
	public final String getToPlugin() {
		return toPlugin;
	}

	/**
	 * @param toPlugin the toPlugin to set
	 */
	public final void setToPlugin(String toPlugin) {
		this.toPlugin = toPlugin;
	}

	/**
	 * @return the attachment
	 */
	public Object getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(Object attachment) {
		this.attachment = attachment;
	}

}
