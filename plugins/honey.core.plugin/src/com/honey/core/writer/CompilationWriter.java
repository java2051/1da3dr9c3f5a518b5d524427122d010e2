package com.honey.core.writer;

import java.io.File;

import com.honey.core.compiler.CompilationDescriptor;

/**
 * 编译后的内容写入文件中
 * @author Administrator
 *
 */
public interface CompilationWriter {
	
	/**
	 * 按照编译描述符写入文件
	 * @param compilationDescriptor
	 */
	public void writeToFile(CompilationDescriptor  compilationDescriptor);
	
	/**
	 * 获取写入成功后的目标文件
	 * @return
	 */
	public File getTargetSourceFile();
	
	
	
}
