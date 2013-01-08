package com.honey.core.compiler;

import com.honey.core.builder.CharacterBuilder;


/**
 * 编译文件内容节点
 * 
 * @author Administrator
 *
 */
public interface CompilationElement {
	
	/**
	 * 按照缩进级别编译文件节点
	 * @param indentLevel 缩进级别
	 * @return 
	 * @throws CompilationException
	 */
	public  CharacterBuilder compiledContent(int indentLevel)  throws CompilationException ;
}
