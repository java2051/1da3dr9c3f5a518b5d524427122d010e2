package com.honey.core.compiler;

import com.honey.core.builder.CharacterBuilder;

/**
 * 
 * 文件缩进格式
 * 
 * @author Administrator
 * 
 */
public final class IndentSpace {

	/** 系统支持的回车符号 */
	public static final String lineSeparator;

	static {
		String ls = System.getProperty("line.separator");
		if (ls == null) {
			ls = "\n";
		}
		lineSeparator = ls;
	}

	/**
	 * 文件缩进,使用4个空格代表一级缩进.文件缩进也可以使用Tab作为缩进的符号,
	 * 但是有些文本编辑器对tab键的定义为4个空格或者8个空格,
	 * 为了统一风格定义4个空格作为缩进这样保证在所有的文本编辑器中风格是一致的.
	 * 
	 * @param cb CharacterBuilder
	 * @param indentLevel 缩进级别,其值大于0
	 */
	public static void spaceIndent(CharacterBuilder cb, int indentLevel) {
		for (int i = 0; i < indentLevel; i++) {
			cb.append("    ");
		}
	}
	
	/**
	 * 新的追加空间, 包含一个换行和一组缩进
	 * @param cb
	 * @param indentLevel
	 */
	public static void newSpace(CharacterBuilder cb, int indentLevel){
		IndentSpace.newLine(cb);
		IndentSpace.spaceIndent(cb, indentLevel);
	}
	
	/**
	 * 文件换行
	 * @param cb CharacterBuilder
	 */
	public static void newLine(CharacterBuilder cb) {
		cb.append(lineSeparator);
	}

	/**
	 * 文件换两行
	 * @param cb CharacterBuilder
	 */
	public static void newDoubleLine(CharacterBuilder cb) {
		cb.append(lineSeparator)
		.append(lineSeparator);
	}
	
	/**
	 * 追加空格
	 * @param cb  CharacterBuilder
	 * @param indentLevel 空格数量 
	 */
	public static void space(CharacterBuilder cb, int indentLevel) {
		for (int i = 0; i < indentLevel; i++) {
			cb.append(" ");
		}
	}

}
