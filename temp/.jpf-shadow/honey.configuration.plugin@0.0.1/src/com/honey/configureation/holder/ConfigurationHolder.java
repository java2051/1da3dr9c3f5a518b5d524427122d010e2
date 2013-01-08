package com.honey.configureation.holder;

import java.util.Properties;

import org.w3c.dom.Element;

/**
 * 配置文件插入点
 * @author Administrator
 *
 */
public interface ConfigurationHolder {
	
	/**
	 * 解析xml节点
	 * @param element 节点
	 * @param properties 插件环境变量
	 */
	public void parserElement(Element element,Properties properties ) ;
	
	/**
	 * 生成xml节点
	 */
	public  String toXmlElement() ;
	
	/**
	 * 验证xml节点是否正确
	 */
	public  void validate() ;
	
}
