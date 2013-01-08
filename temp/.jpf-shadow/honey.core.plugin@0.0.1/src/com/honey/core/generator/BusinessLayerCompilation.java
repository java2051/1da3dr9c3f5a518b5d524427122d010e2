package com.honey.core.generator;


/**
 * 业务层
 * @author Administrator
 *
 */
public interface BusinessLayerCompilation extends MvcCompilation{
	
	/**
	 * 设定持久层处理的类
	 * @param sublayer
	 */
	public void setPresentationLayerCompilation(PresentationLayerCompilation presentationLayer) ;
	
}
