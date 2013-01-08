package com.honey.core.formater;

import java.io.File;

import com.honey.core.compiler.CompilationType;

public interface CodeFormater {

	/**
	 * 支持格式化的代码文件的类型
	 * @return 
	 */
	public CompilationType[] supportCompilationTypes( );
	
	/**
	 * 格式或代码
	 * @param code 源代码文件流
	 * @return 
	 */
	public void format(File code, String charset);
	
}
