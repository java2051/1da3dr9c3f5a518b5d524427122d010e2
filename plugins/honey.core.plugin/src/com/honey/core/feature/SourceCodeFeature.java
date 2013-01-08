package com.honey.core.feature;

import java.io.File;

import com.honey.core.compiler.Compilation;
import com.honey.core.compiler.CompilationType;
import com.honey.core.types.FullyQualifiedJavaType;

/**
 * 
 * @author Administrator
 *
 */
public interface SourceCodeFeature{
	
	/**
	 * 支持抽取文件特征的文件类型
	 * @return 
	 */
	public CompilationType[] supportCompilationTypes( );
	
	/**
	 * 特征值计算器的名称
	 * @return
	 */
	public String getCalculaterName();
	
	/**
	 * 计算内容的特征值,如果计算成功返回true, 否则返回false;
	 * @param compilation 被编译的内容
	 * @return
	 */
	public boolean calculationFeature(Compilation compilation,CompilationType compilationType ,String charset);
	
	/**
	 * 获取编译内容的特征值.
	 * @param compilation
	 * @return
	 */
	public Feature[] getFeatures();
	
	/**
	 * 验证被覆盖的文件的前一次编译后,文件内容是否发生过有效的修改.
	 * @param workspace 工作空间
	 * @param type 文件包
	 * @param compilationType 编译类型 
	 * @param features 特征值
	 * @return
	 */
	public boolean originalChecksum(String workspace ,FullyQualifiedJavaType type ,CompilationType compilationType , Feature[] features,String charset);
	
}
