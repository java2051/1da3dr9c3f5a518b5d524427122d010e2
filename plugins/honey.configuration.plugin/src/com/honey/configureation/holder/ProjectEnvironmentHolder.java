package com.honey.configureation.holder;

import java.util.Properties;

import org.w3c.dom.Element;

import com.honey.configureation.xmlparser.ParseElement;
import com.honey.core.compiler.StorageType;
import com.honey.core.utils.StringUtility;

public class ProjectEnvironmentHolder implements ConfigurationHolder{
	
	private String targetProjectWorkspace = "";
	
	private String targetSrcDirectory = "";
	
	private String rootPackage = "";

	/** 存储类型 */
	private  StorageType storageType = StorageType.BACKUP_STORAGE; 
	
	private String charset="UTF-8" ;
	
	private boolean isForcePrimitiveType = true;
	
	@Override
	public void parserElement(Element element, Properties properties) {

		targetProjectWorkspace = ParseElement.getConfiguration(element, "targetProjectWorkeySpace", properties);
		if( targetProjectWorkspace == null ){
			targetProjectWorkspace = StringUtility.mergeDirectoryPath( properties.getProperty("localpath"), "ProjectWorkeySpace" );
		}else{
			targetProjectWorkspace = StringUtility.mergeDirectoryPath( targetProjectWorkspace  );
		}
		targetSrcDirectory = ParseElement.getConfiguration(element, "targetSrcDirectory", properties);
		if( targetSrcDirectory == null){
			targetSrcDirectory = StringUtility.mergeDirectoryPath(targetProjectWorkspace, "src");
		}else{
			targetSrcDirectory = StringUtility.mergeDirectoryPath( targetSrcDirectory );
		}

		rootPackage = ParseElement.getConfiguration(element, "rootPackage", properties);
		if( rootPackage == null) rootPackage = "" ;
		if(StringUtility.stringHasValue( rootPackage ) && rootPackage.charAt(rootPackage.length() - 1) != '.'){
			rootPackage +=".";
		}
		properties.setProperty("rootPackage", rootPackage);
		
		String tmp =ParseElement.getConfiguration(element, "forcePrimitiveType", properties);
		if(StringUtility.isTrue(tmp)){
			this.isForcePrimitiveType = true;
		}else{
			this.isForcePrimitiveType = false;
		}
		
		tmp = ParseElement.getConfiguration(element, "storage", properties);
		if( "backup".equalsIgnoreCase(tmp) ){
			this.storageType =StorageType.BACKUP_STORAGE ;
		}else if( "multi".equalsIgnoreCase(tmp) ){
			this.storageType =StorageType.MULTI_STORAGE ;
		}else if( "override".equalsIgnoreCase(tmp) ){
			this.storageType =StorageType.OVERRIDE_STORAGE ;
		}else if( "checking".equalsIgnoreCase(tmp) ){
			this.storageType =StorageType.CHECKING_STORAGE ;
		}else if( "checkingandbackup".equalsIgnoreCase(tmp) ){
			this.storageType =StorageType.CHECKING_BACKUP_STORAGE ;
		}else if( "checkingbackup".equalsIgnoreCase(tmp) ){
			this.storageType =StorageType.CHECKING_BACKUP_STORAGE ;
		}else if( "cab".equalsIgnoreCase(tmp) ){
			this.storageType =StorageType.CHECKING_BACKUP_STORAGE ;
		}else if( "checkingandmulti".equalsIgnoreCase(tmp) ){
			this.storageType =StorageType.CHECKING_MULTI_STORAGE ;
		}else if( "checkingmulti".equalsIgnoreCase(tmp) ){
			this.storageType =StorageType.CHECKING_MULTI_STORAGE ;
		}else if( "cam".equalsIgnoreCase(tmp) ){
			this.storageType =StorageType.CHECKING_MULTI_STORAGE ;
		}
		
		tmp = ParseElement.getConfiguration(element, "charset", properties);
		if(tmp != null){
			charset = tmp ;
		}
	}

	@Override
	public String toXmlElement() {
		
		return null;
	}

	@Override
	public void validate() {

	}

	public String getTargetProjectWorkspace() {
		return targetProjectWorkspace;
	}

	public String getTargetSrcDirectory() {
		return targetSrcDirectory;
	}

	public String getRootPackage() {
		return rootPackage;
	}

	public StorageType getStorageType() {
		return storageType;
	}

	public String getCharset() {
		return charset;
	}

	public boolean isForcePrimitiveType() {
		return isForcePrimitiveType;
	}
	
	
}
