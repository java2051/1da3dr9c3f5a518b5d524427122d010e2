package com.honey.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.honey.compilation.java.JavaKeyWord;

/**
 * 字符串处理通用类
 * @author Administrator
 *
 */
public final class StringUtility {
	
	/** 空字符串 */
	public final static String EMPTY="";
	
	/**
	 * 构造函数
	 */
	private StringUtility() {
        super();
    }

    /**
     * true :  s != null && s.length() > 0
     * @param s
     * @return
     */
    public static final boolean stringHasValue(String s) {
        return s != null && s.length() > 0;
    }

    public static String composeFullyQualifiedTableName(String catalog,
            String schema, String tableName, char separator) {
        StringBuilder sb = new StringBuilder();

        if (stringHasValue(catalog)) {
            sb.append(catalog);
            sb.append(separator);
        }

        if (stringHasValue(schema)) {
            sb.append(schema);
            sb.append(separator);
        } else {
            if (sb.length() > 0) {
                sb.append(separator);
            }
        }

        sb.append(tableName);

        return sb.toString();
    }
    
    /**
     * 字符串中是否包含空格
     * @param s
     * @return
     */
    public static boolean stringContainsSpace(String s) {
        return s != null && s.indexOf(' ') != -1;
    }
    
    /**
     * java 字符串转义
     * 
     * @param s
     * @return
     */
    public static String escapeStringForJava(String s) {
        StringTokenizer st = new StringTokenizer(s, "\"", true); 
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if ("\"".equals(token)) { 
                sb.append("\\\""); 
            } else {
                sb.append(token);
            }
        }

        return sb.toString();
    }

    
    /**
     * xml字符串转义
     * @param s
     * @return
     */
    public static String escapeStringForXml(String s) {
    	StringBuilder answer = new StringBuilder();
		char charArray[] = s.toCharArray();
		for(char c : charArray ){
			if(c == '&' ){
				answer.append("&amp;");
			}else if(c == '<' ){
				answer.append("&lt;");
			}else  if(c == '>' ){
				answer.append("&gt;");
			}else  if(c == '"' ){
				answer.append("&quot;");
			}else  if(c == '\'' ){
				answer.append("&apos;");
			}else {
				answer.append( c );
			}	
		}
		return answer.toString();
    }
    
    /**
     * 是否可以转换成true
     * @param s 
     * @return
     */
    public static boolean isTrue(String s) {
    	return "true".equalsIgnoreCase( s) || "t".equalsIgnoreCase( s) || "1".equals( s) || "on".equalsIgnoreCase( s )  ;
    }
    
    public static boolean stringContainsSQLWildcard(String s) {
        if (s == null) {
            return false;
        }
        return s.indexOf('%') != -1 || s.indexOf('_') != -1;
    }
    
    /**
     * 分割字符串按照 \n|;|,|\\|
     * @param str
     * @return
     */
    public static String[] parseStringsplit(String str ){
    	String s[]  = str.split("\n|;|,|\\|");
    	List<String> list= new ArrayList<String>();
		if( s.length>0 ){
			for(String t : s ){
				if( t == null || t.length() == 0 )
					continue;
				t = t.trim();
				if(t.length() ==0)
	    			continue;
				list.add(t);
			}
			return list.toArray(new String[list.size()]);
		}else{
			return null;
		}
    }
    
    /**
     * 清除字符串数组中无效的字符串
     * @param s
     * @param jump
     * @return
     */
    public static String[] cleanup(String []s ,String jump){
		if(s == null || s.length ==0)
			return s;
		else{
			//StringBuffer bu = new StringBuffer();
			String tmp[]=new String[s.length];
			int index =0 ;
			for(int i =0 ;i<s.length;i++){
				String str = s[i];
				if(jump != null && jump.equals(str)){
					continue ;
				}
				
				if(str == null ){
					continue ;
				}else{
					str = str.trim();
					if( str.length()==0)
						continue ;
					tmp[index] = str;
					index ++ ;
				}
			}
			String newArray[] =new String[index];
			if(index==0)
				return null;
			else{
				for(int i =0 ;i<index;i++){
					newArray[i] =tmp[i]; 
				}
			}
			return newArray;
		}
	}
    
    /**
     * 通过字符串数组创建新的字符串
     * @param s 字符串数组
     * @param append 每个字符尾部追加的字符串
     * @param offset 在字符串数组中偏移的位置
     * @return
     */
    public static String createString(String s[]  ,String append ,int offset){
    	if(s == null || s.length == 0)
    		return null ;
    	if(offset <=0){
    		offset = 0; 
    	}
    	
    	StringBuffer b = new StringBuffer();
    	String str = null;
    	for(int i=offset;i<s.length ;i++  ){
    		str = s[i];
    		if(str!= null)
    			b.append( str ).append(append) ;
    	}
    	return b.toString();
    }
    
    /**
     * 时间格式化
     * @param date
     * @return
     */
    public static String format(Date date) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sf.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取文件最后一行
	 * @param path
	 * @return
	 */
	public static String getLastLine(String path){
		String line= null;
		try {
			BufferedReader  bReader = new BufferedReader(new FileReader(path));
			String readString = bReader.readLine(); 
			while(readString != null){
				readString = bReader.readLine();
				if(readString != null )
					line =readString;
			}
			bReader.close();  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}

	/**
	 * 获取文件内容,默认使用utf-8的字符集
	 * @param path 文件路径
	 * @return
	 */
	public static String getStringFromFile(String path){
		return getStringFromFile(path,"UTF-8");
	}

	/**
	 * 获取文件内容
	 * @param path 文件路径
	 * @param fileCharacter 文件字符集
	 * @return
	 */
	public static String getStringFromFile(String path, String fileCharacter){
		StringBuilder answer= new StringBuilder();
		InputStreamReader inputStreamReader = null;
		BufferedReader  reader = null;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream(path), fileCharacter);
			reader = new BufferedReader( inputStreamReader );
			String line = null;
			while( (line=reader.readLine() )!=null){
				answer.append( line ).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(inputStreamReader != null){
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return answer.toString();
	}
	
	/**
	 * 使用SHA-1方式对字符串进行hash
	 * @param str
	 * @return
	 */
	public static String getStringHash(String str){
		str = str.replaceAll("\\s", "");
		System.out.println(str);
		String hardsalt ="^1!z_.$?')<'$-2>$2<|" ; //加盐
		str += hardsalt;
		String  result = null ;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] b= md.digest();
			result = new String( HexCode.encodeHex(b) );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	/**
	 * 产生一个随机的字符串(不包含数字)
	 * @param size
	 * @return
	 */
	public static String getRandomString(int size) {
		return RandomStringUtils.randomAlphabetic(size);
		
	}
	
	public static String upperString(String name) {
		if(name == null || name.length()==0  ) return null;
		char []chars = name.toCharArray() ;
		if(chars[0]>=97 &&  chars[0]<=122){ // a=97 z=122
			chars[0] = (char) (chars[0]- 32);
		}
		return new String (chars);
	}

	
	/**
	 * 把InputStream转换成String
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {
		 BufferedReader reader = new BufferedReader(new InputStreamReader(is));   
		 StringBuilder answer = new StringBuilder();   
		 String line = null;   
		 try {
			 while ((line = reader.readLine()) != null) {   
				 answer.append(line + "\n");   
			 }   
		 } catch (IOException e) {   
			 e.printStackTrace();   
		 } finally {   
			 try {   
				 is.close();   
			 } catch (IOException e) {   
				 e.printStackTrace();   
			 }   
		 }
		 return answer.toString() ;
	}
	
	/**
	 * 把字符串数组合并成文件路径
	 * @param str
	 * @return
	 */
	public static String  mergeDirectoryPath(String ...str){
		StringBuilder answer = new StringBuilder();
		if(str != null ){
			char c = 0;
			int length  = 0;
			boolean  comma = false;
			for( String  s : str){
				if(s != null && s.length() > 0){
					length = s.length();
					c = s.charAt( length - 1 );
					if (comma)answer.append( File.separator); else comma = true;
					if( c == '/'  || c == '\\' ){
						answer.append( s.substring(0,  length - 1) );
					}else{
						answer.append(s);
					}
				}
			}
		}
		return answer.length() ==0? null: answer.toString();
	}
}
