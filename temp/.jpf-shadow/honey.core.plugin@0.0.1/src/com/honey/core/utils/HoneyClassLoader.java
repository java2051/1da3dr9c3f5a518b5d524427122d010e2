package com.honey.core.utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 导入jar到系统的ClassLoader中.
 * 
 * @author Administrator
 * 
 */
public class HoneyClassLoader {

	public final static void loadLibraryToDefaultClassLoader(String library){
		URL []urls = splitLibrary(library);
		for(URL url : urls ){
			addUrlToClassPath(url, getDefaultClassLoader());
		}
	}
	
	/**
	 * 获取系统的类加载器
	 * @return
	 */
	public final static ClassLoader getSystemClassLoader() {
		return ClassLoader.getSystemClassLoader();
	}
	
	/**
	 * 获取当前程序使用的类加载器
	 * @return
	 */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if (cl == null) {
			cl = HoneyClassLoader.class.getClassLoader();
		}
		return cl;
	}
	
	/**
	 * 把指定的路径添加到指定的类加载器中
	 * @param path
	 * @param classLoader
	 */
	public static void addFileToClassPath(String path, ClassLoader classLoader) {
		addFileToClassPath(new File(path), classLoader);
	}
	
	/**
	 * 把指定的路径添加到指定的类加载器中
	 * @param path
	 * @param classLoader
	 */
	public static void addFileToClassPath(File path, ClassLoader classLoader) {
		try {
			addUrlToClassPath(path.toURL(), classLoader);
		} catch (MalformedURLException muex) {
			throw new IllegalArgumentException("Invalid path: " + path, muex);
		}
	}
	
	/**
	 * 把指定的路径添加到指定的类加载器中
	 * @param url
	 * @param classLoader
	 */
	public static void addUrlToClassPath(URL url, ClassLoader classLoader) {
		try {
			invokeDeclared(URLClassLoader.class, classLoader, "addURL",
					new Class[]{URL.class}, new Object[]{url});
		} catch (Exception ex) {
			throw new IllegalArgumentException("Unable to add URL: " + url, ex);
		}
	}
	
	/**
	 * 加载库文件
	 * @param clazz
	 * @param obj
	 * @param method
	 * @param paramClasses
	 * @param params
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	private static Object invokeDeclared(Class<?> clazz, Object obj, String method, Class<?>[] paramClasses, Object[] params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Method m = clazz.getDeclaredMethod(method, paramClasses);
		boolean bool = m.isAccessible();
		m.setAccessible(true);
		URLClassLoader classloader = (URLClassLoader)m.invoke(obj, params);
		m.setAccessible(bool);
		return classloader;
	}
	
	private final static URL[] splitLibrary( String library){
		String entries[] = StringUtility.parseStringsplit(library);
		URL[] urls = new URL[ entries.length ];
		File file;
		for (int i=0;i<entries.length;i++) {
			file = new File(entries[i]);
			if (!file.exists())
				continue;
			try {
				urls[i]=file.toURI().toURL();
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException("文件路径错误: " + entries[i]);
			}
		}
		return urls;
	}
	
//	public final void get(String sClass){
//		
//		Class clazz =  Class.forName(sClass) ;
//		
//		clazz.getConstructor(String.class, int.class);
//	}
//	
	
}
