package com.honey.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 反射通用类
 * @author Administrator
 *
 */
public class ReflectUtility {

	/** 空的Class数组 */
	public static final Class<?>[] NO_PARAMETERS = new Class[0];

	/** 空的对象数组 */
	public static final Object[] NO_ARGUMENTS = new Object[0];


	private static Method _getMethod0;

//	private static Method _getField0;

	
	/** 在jdk的系统中Class对象有一个getMethod0方法用于在继承链中查找方法,关于更多信息请查看 */
	static {
		try {
			_getMethod0 = Class.class.getDeclaredMethod("getMethod0", String.class, Class[].class);
			_getMethod0.setAccessible(true);
		} catch (Exception ignore) {
			try {
				_getMethod0 = Class.class.getMethod("getMethod", String.class, Class[].class);
			} catch (Exception ignored) {
				_getMethod0 =  null;
			}
		}
		
//		try {
//			_getField0 = Class.class.getDeclaredMethod("getField0", String.class);
//			_getField0.setAccessible(true);
//		} catch (Exception ignore) {
//			ignore.printStackTrace();
//			try {
//				_getField0 = Class.class.getMethod("getField", String.class);
//			} catch (Exception ignored) {
//				_getField0 =  null;
//			}
//		}
	}
	
	
	/**
	 * 在继承链中查找方法
	 * @param clazz
	 * @param name
	 * @param parameterTypes
	 * @return
	 */
	public static Method getMethod0(Class<?> clazz, String name, Class<?> ...parameterTypes) {
		try {
			if(parameterTypes == null) parameterTypes=NO_PARAMETERS;
			return (Method) _getMethod0.invoke(clazz, name, parameterTypes);
		} catch (Exception ignore) {
			return null;
		}
	}

	/**
	 * 模拟jdk中getField0方法(从继承链中获取属性)
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static Field getField0(Class<?> clazz, String name) {
		//Field  answer = (Field) _getField0.invoke(clazz, name);
		Field  answer = null;
		try {
			answer = clazz.getDeclaredField(name);
		} catch (Exception e) {}
		
		if(answer == null ){
			Class<?> c =  clazz.getSuperclass();
			if(c != null ) answer = getField0(c, name);
		}	
		return answer;
	}
	
	/**
	 * 获取对象的Class
	 * @param objects 动态参数对象
	 * @return
	 */
	public static final Class<?>[] getClasses(Object ...objects) {
		if (objects == null) {
			return null;
		}
		Class<?>[] result = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] != null) {
				result[i] = objects[i].getClass();
			}
		}
		return result;
	}

	/**
	 * 执行方法
	 * @param obj 对象
	 * @param method 方法
	 * @param params 方法参数
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public static final Object invoke(Object obj, Method method, Object[] params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Object answer = null;
		boolean b = method.isAccessible();
		method.setAccessible(true);
		answer = method.invoke(obj, params);
		if( !b )method.setAccessible(false);
		return answer;
	}
	
	/**
	 *  执行方法
	 * @param clazz class
	 * @param obj 对象
	 * @param method 方法
	 * @param paramClasses 参数class 
	 * @param params 参数
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	private static final Object invoke(Class<?> clazz, Object obj, String method, Class<?>[] paramClasses, Object[] params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		
		if(obj == null ) obj = new Object();
		if(params !=null && paramClasses == null ){ 
			paramClasses = getClasses(params);
		}else if (params ==null && paramClasses == null ){
			paramClasses = NO_PARAMETERS;
		}
		if( params ==  null ) params =NO_ARGUMENTS;
		
		//Method m = null ;
		//m = clazz.getDeclaredMethod ( method, paramClasses);
		//m = getMethod0(clazz, method, paramClasses);
		return invoke(obj, getMethod0(clazz, method, paramClasses), params); 
	}
	
	/**
	 * 
	 * @param clazz
	 * @param method
	 * @param paramClasses
	 * @param params
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public static final Object invoke(Class<?> clazz, String method, Class<?>[] paramClasses, Object[] params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		return invoke(clazz, null, method, paramClasses, params);
	}
	
	/**
	 * 
	 * @param clazz
	 * @param method
	 * @param params
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public static final Object invoke(Class<?> clazz, String method,  Object ...params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		return invoke(clazz, null, method, null, params);
	}
	
	/**
	 * 
	 * @param obj
	 * @param method
	 * @param paramClasses
	 * @param params
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public static final Object invoke(Object obj, String method, Class<?>[] paramClasses, Object[] params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		return invoke(obj.getClass(), obj, method, paramClasses, params);
	}
	
	/**
	 * 
	 * @param obj
	 * @param method
	 * @param params
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public static final Object invoke(Object obj, String method,  Object ...params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		return invoke(obj.getClass(), obj, method, null, params);
	}
	
	/**
	 * 
	 * @param obj
	 * @param method
	 * @param params
	 */
	public static final void findMethod( Object obj, String method,  Object ...params ){
		
		
	}

	/**
	 * 动态代理类
	 * @param clazz 代理对象的Class
	 * @param handler 代理句柄,需要调用着实现InvocationHandler接口
	 * @return
	 */
	public static final Object dynamicProxy(Class<?> clazz, InvocationHandler handler){
		if( handler == null || clazz == null) return null;
		return Proxy.newProxyInstance(
				clazz.getClassLoader(), 
				clazz.getInterfaces(),
				handler
		);
	}
	
	/**
	 * 获取属性的值
	 * @param obj 对象
	 * @param filedName 属性名称
	 * @return
	 */
	public static final Object getFieldValue(Object obj, String filedName ){
		Object answer = null;
		java.lang.reflect.Field field = getField0(obj.getClass(), filedName);
		if( field != null ){
			try {
				boolean  bool = field.isAccessible();
				field.setAccessible(true);
				answer = field.get(obj);
				field.setAccessible(bool);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return answer ;
	}
	
	/**
	 * 设定属性的值
	 * @param obj 对象
	 * @param filedName 属性名称
	 * @param value 值
	 */
	public static final void setValueToField(Object obj, String filedName, Object value ){
		java.lang.reflect.Field field = getField0(obj.getClass(), filedName);
		if( field != null ){
			boolean  bool = field.isAccessible();
			try {
				field.setAccessible(true);
				field.set(obj, value);
				field.setAccessible(bool);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
}