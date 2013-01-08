package com.honey.core.utils;

import java.util.Locale;

import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;

/**
 * java beans 工具类
 * 
 * @author Administrator
 * 
 */
public class JavaBeansUtil {
	
	/**
	 * 生成getter 方法
	 * @param javaProperty 字符串
	 * @param fullyQualifiedJavaType 类型
	 * @return
	 */
	public static String getGetterMethodName(String javaProperty, FullyQualifiedJavaType fullyQualifiedJavaType) {
		StringBuilder answer = new StringBuilder();
		int position = 0;
		if (fullyQualifiedJavaType.equals(JDKFullyQualifiedJavaType.getBooleanInstance())) {
			answer.append("is");
			position = 2;
		} else {
			answer.append("get");
			position = 3;
		}
		answer.append(javaProperty);
		if (Character.isLowerCase(answer.charAt(position))) {
			answer.setCharAt(position, Character.toUpperCase(answer.charAt(position)));
		}
		return answer.toString();
	}
	
	public static String getGetterMethodName(String javaProperty) {
		StringBuilder answer = new StringBuilder();
		int position = 3;
		answer.append("get");
		answer.append(javaProperty);
		if (Character.isLowerCase(answer.charAt(position))) {
			answer.setCharAt(position, Character.toUpperCase(answer.charAt(position)));
		}
		return answer.toString();
	}
	
	/**
	 * 生成setter方法
	 * @param javaProperty 字符串
	 * @return
	 */
	public static String getSetterMethodName(String javaProperty) {
		StringBuilder answer = new StringBuilder("set");
		answer.append(javaProperty);
		if (Character.isLowerCase(answer.charAt(3))) {
			answer.setCharAt(3, Character.toUpperCase(answer.charAt(3)));
		}
		return answer.toString();
	}
	
	/**
	 * 把字符串转换成驼峰写法字符串
	 * @param inputString
	 * @param firstCharacterUppercase
	 * @return
	 */
	public static String getCamelCaseString(String inputString, boolean firstCharacterUppercase) {
		StringBuilder sb = new StringBuilder();
		boolean nextUpperCase = false;
		char c = 0;
		for (int i = 0; i < inputString.length(); i++) {
			c = inputString.charAt(i);
			switch (c) {
				case '_':
				case '-':
				case '@':
				case '$':
				case '#':
				case ' ':
				case '/':
				case '&':
					if (sb.length() > 0) {
						nextUpperCase = true;
					}
					break;
				default:
					if (nextUpperCase) {
						sb.append(Character.toUpperCase(c));
						nextUpperCase = false;
					} else {
						sb.append(Character.toLowerCase(c));
					}
					break;
			}
		}

		if (firstCharacterUppercase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}

		return sb.toString();
	}

	public static String getValidPropertyName(String inputString) {
		String answer;

		if (inputString == null) {
			answer = null;
		} else if (inputString.length() < 2) {
			answer = inputString.toLowerCase(Locale.US);
		} else {
			if (Character.isUpperCase(inputString.charAt(0))
					&& !Character.isUpperCase(inputString.charAt(1))) {
				answer = inputString.substring(0, 1).toLowerCase(Locale.US)
						+ inputString.substring(1);
			} else {
				answer = inputString;
			}
		}

		return answer;
	}
}
