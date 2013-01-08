package com.honey.core.compiler;


/**
 * 文件存储类型
 * @author Administrator
 *
 */
public enum StorageType {
	
	/** 备份存储 */
	BACKUP_STORAGE ("Backup"),
	
	/** 多文件存储 */
	MULTI_STORAGE ("Multi"),
	
	/** 强制覆盖存储,不验证文件正确性,强制覆盖源文件,不建议使用 */
	OVERRIDE_STORAGE ("Override"),
	
	/** 验证文件存储,文件验证通过覆盖文件,如果验证失败跳过生成,建议使用该方式 */
	CHECKING_STORAGE ("Checking"),
	
	/** 验证文件存储,文件验证通过覆盖文件,如果验证失败使用备份的方式生成文件*/
	CHECKING_BACKUP_STORAGE ("Checking And Backup"),
	
	/** 验证文件存储,文件验证通过覆盖文件,如果验证失败,不改变当前文件内容,新文件内容生成新文件*/
	CHECKING_MULTI_STORAGE ("Checking And Multi"),
	
	;
	
	private final String name ;
	
	private StorageType(String name){
		this.name =  name;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return this.name;
	}
	
	
}
