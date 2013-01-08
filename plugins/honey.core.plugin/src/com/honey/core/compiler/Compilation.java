package com.honey.core.compiler;

import java.util.List;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.types.FullyQualifiedJavaType;

/**
 * 文件编译器,用于各种文件编译接口
 * @author Administrator
 *
 */
public interface Compilation {
	
	/**
	 * 编译文件内容
	 * 
	 * @return
	 */
	public CharacterBuilder compiledContent() throws CompilationException;

    /**
     * 添加文件注释
     * @param comments
     */
	public void addFileComment(String ...comments) throws CompilationException;

	/**
	 * 在文件尾部添加注释
	 * @param comments
	 * @throws CompilationException
	 */
	public void addAfterBodyComment(String ...comments) throws CompilationException;
	
    /**
     * 获取文件注释
     * @return
     */
	public List<String> getFileComments() throws CompilationException;
	
	/**
	 * 获取文件的包
	 * @return
	 */
	public FullyQualifiedJavaType getType();
	
}
