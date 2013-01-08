package com.honey.core.compiler;

import java.io.File;

/**
 * 编译文件描述符
 * @author Administrator
 *
 */
public class CompilationDescriptor {
	
	/** 文件编译器 */
	private Compilation compilation;
	
	/** 编译后的文件所在的工作空间目录 */
	private String workSpace;
	
	/** 文件类型 */
	private CompilationType compilationType ;
	
	/** 文件特征值. 抽取出文件内容(部分或者全部内容)按照某种算法(通常hash算法)计算出能够代表这个文件的唯一值. */
	private Feature feature = null;
	
	/** 存储类型  */
	private StorageType storageType = StorageType.BACKUP_STORAGE; 
	
	/** 文件类型   */
	private ContentType contentType = ContentType.OTHER; 
	
	/** 编译完成后 写入的目标文件 */
	private File sourceCodeFile = null;
	
	private String charset = null;
	/**
	 * 构造函数
	 * @param compilation 文件编译器  (必须值)
	 * @param workSpace	编译后的文件所在的工作空间目录 (必须值)
	 * @param fileType 编译后文件的类型(必须值)
	 * @param storageType 文件存储类型 (非必须值)
	 * @param contentType 文件存储类型 (非必须值)
	 * @param feature 文件特征值	 (非必须值)
	 * 
	 * @throws CompilationException 
	 */
	public CompilationDescriptor(Compilation compilation,String workSpace,CompilationType compilationType,StorageType storageType, ContentType contentType, String charset){
		this.compilation = compilation;
		this.workSpace = workSpace;
		this.compilationType = compilationType;
		this.storageType = storageType;
		if(contentType != null )
			this.contentType = contentType;
		this.charset = charset;
	}
	
	/**
	 * 文件编译器 
	 * @return
	 */
	public Compilation getCompilation() {
		
		return compilation;
	}
	
	/**
	 * 文件路径
	 * @return
	 */
	public String getWorkSpace() {
		return workSpace;
	}
	
	/**
	 * 文件特征值. 抽取出文件内容(部分或者全部内容)按照某种算法(通常hash算法)计算出能够代表这个文件的唯一值. 
	 * @return
	 */
	public com.honey.core.feature.Feature[] getFeature() {
		if( feature == null ) throw new RuntimeException("还未运行抽取特征值的插件,请先执行该插件后调用本方法.");
		return feature._feature;
	}
	
	/**
	 * 特征值验证是否成功
	 * @return
	 */
	public boolean isCheckedFeature(){
		if( feature == null ) throw new RuntimeException("还未运行抽取特征值的插件,请先执行该插件后调用本方法.");
		return feature._checked;
	}

	/**
	 * 文件类型
	 * @return
	 */
	public CompilationType getCompilationType() {
		return compilationType;
	}
	
	/**
	 * 文件存储类型
	 * @return
	 */
	public StorageType getStorageType() {
		return storageType;
	}

	/**
	 * 文件内容类型
	 * @return the contentType
	 */
	public final ContentType getContentType() {
		return contentType;
	}

	/**
	 * @return the sourceCodeFile
	 */
	public final File getSourceCodeFile() {
		return sourceCodeFile;
	}

	/**
	 * @param sourceCodeFile the sourceCodeFile to set
	 */
	public final void setSourceCodeFile(File sourceCodeFile) {
		this.sourceCodeFile = sourceCodeFile;
	}
	
	@Override
	public String toString() {
		return compilationType.toString();
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	public void setFeature(boolean isChecked , com.honey.core.feature.Feature[] feature) {
		this.feature = new Feature();
		this.feature._checked = isChecked;
		this.feature._feature = feature;
	}
	
	/**
	 * @param storageType the storageType to set
	 */
	public final void setStorageType(StorageType storageType) {
		this.storageType = storageType;
	}

	/**
	 * 文件特征值
	 * @author Administrator
	 *
	 */
	private class Feature{
		
		private boolean _checked =false ;
		
		private com.honey.core.feature.Feature[] _feature = null;
	}

	
	
}
