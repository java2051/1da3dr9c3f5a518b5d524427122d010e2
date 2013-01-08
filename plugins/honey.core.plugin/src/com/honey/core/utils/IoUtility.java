package com.honey.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Io 类库
 * @author Administrator
 *
 */
public class IoUtility {
	
	/**
	 * 读取文件的行号
	 * @param str
	 * @return
	 */
	public static Map<Integer, String> readTextFileOfLineNumber(String str){
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(str),"UTF-8" ));
			String line;
			int linleNumber =0;
			while((line=br.readLine())!=null){
				line = line.replaceAll("\\s", "");
				if(line.length() == 0 ){
					linleNumber ++ ;
					continue ;
				}
				
				map.put(linleNumber, line);
				linleNumber ++ ;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map ;
	}
	
	/**
	 * 读取指定行的行号
	 * @param line
	 * @param jump
	 * @param file
	 * @return
	 */
	public static int readTextFileLineNumber(String line,String jump, String file){
		line = line.replaceAll("\\s", "");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8" ));
			String str;
			int linleNumber =0;
			boolean b = jump != null; 
			while((str=br.readLine())!=null){
				str = str.replaceAll("\\s", "");
				if( b && str.indexOf(jump)>=0){
					linleNumber ++ ;
					continue ;
				}
				if (str.indexOf(line)>=0){
					return linleNumber ;
				};
				linleNumber ++ ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return -1 ;
	}
	
	
	/**
	 * 把字符串保存为文件
	 * @param file 文件路径
	 * @param str 字符串
	 */
	public static void saveToFile(File file ,String str ){
		if(file == null || str== null || str.length() == 0){
			return ;
		}
		OutputStream out = null ;
		try {
			out = new FileOutputStream( file );   
			out.write(str.getBytes("utf-8"));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void save(File file ,int startLineNumber ,int endLineNumber  ,String str ){
		if(file == null || file.length() == 0){
			return ;
		}
		if(startLineNumber<0){
			startLineNumber = 0;
		}
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8" ));
			String line;
			int linleNumber =0;
			StringBuilder start = new StringBuilder();
			StringBuilder end = new StringBuilder();
			while((line=br.readLine())!=null){
				if(linleNumber < startLineNumber){
					start.append(line).append("\n");
				}
				if(linleNumber > endLineNumber){
					end.append(line).append("\n");
				}
				
				linleNumber ++ ;
			}
			br.close();
			start.append(str).append(end);
			saveToFile(file, start.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 追加的方式写入文件
	 * @param file
	 * @param str
	 */
	@Deprecated
	public static void saveAppend(File file,String str ){
		if(file == null || file.length() == 0){
			return ;
		}
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8" ));
			String line;
			StringBuilder sb = new StringBuilder();
			while((line=br.readLine())!=null){
				sb.append(line).append("\n");
			}
			br.close();
			sb.append(str);
			saveToFile(file, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

