package com.honey.core.compiler;


/**
 * 文件类型
 * @author Administrator
 *
 */
public enum CompilationType {
	
	/** java文件 */
	JAVA((short)0, "java", false  ),
	
	/** xml文件 */
	XML((short)1 ,"xml", false ),
	
	/** properties文件 */
	PROPERTIES((short)2 ,"properties", false ),
	
	/** sql文件 */
	SQL ((short)3 ,"sql", false ),
	
	/** txt文件 */
	TXT ((short)4 ,"txt", false ),
	
	/**JSP文件 */
	JSP ((short)5 ,"jsp", false ),	

	/**css文件 */
	CSS ((short)6 ,"css", false ),
	
	/**js文件 */
	JS ((short)7 ,"js", false ),
	
	/**html文件 */
	HTML ((short)8 ,"html", false ),
	;
	/**
	 * 定义文件类型 
	 */
	private final short type;
	
	/**
	 * 文件的后缀名称
	 */
	private final String suffix;

	/**
	 * 是否是二进制文件 true:是  false:否
	 */
	private final boolean binary;
	
	private CompilationType(short type,String suffix, boolean binary){
		this.type = type;
		this.suffix = suffix;
		this.binary = binary;
	}
	
	/**
	 * 获取后缀名
	 * @return
	 */
	public String getSuffix(){
		return suffix;
	}
	
	public short getType(){
		return type;
	}
	
	/**
	 * 是否是二进制文件
	 * @return
	 */
	public boolean isBinary() {
		return binary;
	}

	@Override
	public String toString() {
		return suffix;
	}
	
}
