package com.honey.core.utils;

import java.io.File;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * 对象为空通用判定类
 * @author Administrator
 *
 */
public class EmptyUtility {
	
	/**
	 * str==null 或者 str.length ==0 返回true
	 * @param str
	 * @return
	 */
	public static final boolean  isStringEmpty(String str){
		return str== null || str.length() == 0 ;
	}
	
	/**
	 * Object[]==null 或者 Object[].length ==0 返回true
	 * @param str
	 * @return
	 */
	public static final boolean  isObjectArrayEmpty(Object[] objects){
		return objects== null || objects.length == 0 ;
	}
	
	/**
	 * String[]==null 或者 String[].length ==0 返回true
	 * @param str
	 * @return
	 */
	public static final boolean  isStringArrayEmpty(String[] strs){
		return strs== null || strs.length == 0 ;
	}
	
	/**
	 * obj==null  返回true
	 * @param obj
	 * @return
	 */
	public static final boolean  isObjectEmpty(Object object){
		return object== null ;
		
	}
	
	/**
	 * collection== null 或者 collection.size()==0 返回true <br />
	 * 用于判断 List 类和Set 类
	 * @param list
	 * @return
	 */
	public static final boolean  isListEmpty(Collection list){
		return list== null || list.size()==0 ;
		
	}
	

	/**
	 * map== null 或者 map.size()==0 返回true
	 * @param map
	 * @return
	 */
	public static final boolean  isMapEmpty(Map map){
		
		return map== null || map.size()==0 ;
	}
	
	/**
	 * iterator== null 或者 iterator.size()==0 返回true
	 * @param map
	 * @return
	 */
	public static final boolean  isIteratorEmpty(Iterator it){
		return it== null || it.hasNext() ;
	}
	
	
	/**
	 * enumeration== null 或者 enumeration.size()==0 返回true
	 * @param map
	 * @return
	 */
	public static final boolean  isEnumerationEmpty(Enumeration e){
		return e== null || e.hasMoreElements() ;
	}
	
	/**
	 * 判定文件夹是否为空<br />
	 * @param directory
	 * @return
	 */
	public static final boolean  isFileDirectoryEmpty(String directory){
		return isFileDirectoryEmpty( new File(directory) );
	}
	
	/**
	 * 判定文件夹是否为空 <br />
	 * 
	 * @param directory
	 * @return
	 */
	public static final boolean  isFileDirectoryEmpty(File directory){
		boolean bool = false ;
		if( directory.exists() ){
			if(directory.isDirectory()){
				bool = directory.listFiles().length > 0;
			}
		}
		return bool;
	}
}
