package com.honey.core.callback;

import com.honey.core.compiler.CompilationDescriptor;

/**
 * 文件编译完成后执行回调函数处理,用于插件再次处理新生成的编译内容
 * @author Administrator
 *
 */
public interface CallbackCompilation {
	
	/**
	 * 执行回调函数
	 * @param compilation
	 * @return
	 */
	public void execute( CompilationDescriptor compilationDescriptor );
	
}
