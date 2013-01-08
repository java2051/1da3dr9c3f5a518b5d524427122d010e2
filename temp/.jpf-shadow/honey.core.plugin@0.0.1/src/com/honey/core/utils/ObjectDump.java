package com.honey.core.utils;

import java.lang.reflect.Field;

/**
 * 打印出对象中字段属性值
 * 
 * @author Administrator
 * 
 */
public class ObjectDump {

	public static String dump(Object obj) {
		String answer = null;
		if (obj != null) {
			try {
				Class<?> cls = obj.getClass();
				answer = "[[" + obj.getClass().getName() + "[\n";
				Field fieldlist[] = cls.getDeclaredFields();
				for (int i = 0; i < fieldlist.length; i++) {
					Field fld = fieldlist[i];
					boolean bool = fld.isAccessible();
					fld.setAccessible(true);
					answer += "field [";
					answer += "name = " + fld.getName() + " ";
					answer += "value = " + fld.get(obj) + "";
					//answer += "type = " + fld.getType() + "\n";
					//answer += "modifiers = " + Modifier.toString(fld.getModifiers()) + "\n";
					answer += "]\n";
					fld.setAccessible(bool);
				}
				answer += "]" + "\n";		
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return answer;
	}
}
