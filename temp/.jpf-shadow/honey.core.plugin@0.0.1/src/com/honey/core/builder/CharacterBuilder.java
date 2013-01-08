package com.honey.core.builder;

/**
 * 字符缓冲
 * @author Administrator
 *
 */
public interface CharacterBuilder {

	
	public CharacterBuilder append(CharacterBuilder sb);
	
	/**
	 * 追加对象
	 * @param obj
	 * @return
	 */
	public CharacterBuilder append(Object obj);
	
	/**
	 * 追加字符串
	 * @param str
	 * @return
	 */
    public CharacterBuilder append(String str);
    
    /**
     * 追加 int 类型
     * @param i
     * @return
     */
    public CharacterBuilder append(int i);
    
    /**
     * 追加 float 类型
     * @param f
     * @return
     */
    public CharacterBuilder append(float f);
    
    /**
     * 追加 float 类型
     * @param d
     * @return
     */
    public CharacterBuilder append(double d);
    
    /**
     * 追加 char 类型
     * @param c
     * @return
     */
    public CharacterBuilder append(char c);
    
    /**
     * 追加 char 类型数组
     * @param c
     * @return
     */
    public CharacterBuilder append(char []c);
    
    /**
     * 追加 long 类型
     * @param l
     * @return
     */
    public CharacterBuilder append(long l);
    
    /**
     * 追加 boolean 类型
     * @param b
     * @return
     */
    public CharacterBuilder append(boolean b);
    
    
    /**
     * 追加java.lang.StringBuilder
     * @param sb
     * @return
     */
    public CharacterBuilder append(java.lang.StringBuilder sb);
    
    /**
     * 追加java.lang.StringBuffer
     * @param sb
     * @return
     */
    public CharacterBuilder append(java.lang.StringBuffer sb);
    
    /**
     * 在指定位置插入对象
     * @param index
     * @param obj
     * @return
     */
	public CharacterBuilder insert( int index ,Object obj);

	/**
	 * 在指定位置插入字符串
	 * @param index
	 * @param str
	 * @return
	 */
	public CharacterBuilder insert( int index ,String str);

	/**
	 * 在指定位置插入 int 类型
	 * @param index
	 * @param i
	 * @return
	 */
	public CharacterBuilder insert( int index ,int i);

	/**
	 * 在指定位置插入 float 类型
	 * @param index
	 * @param f
	 * @return
	 */
	public CharacterBuilder insert( int index ,float f);

	/**
	 * 在指定位置插入 double 类型
	 * @param index
	 * @param d
	 * @return
	 */
	public CharacterBuilder insert( int index ,double d);

	/**
	 * 在指定位置插入 char 类型
	 * @param index
	 * @param c
	 * @return
	 */
	public CharacterBuilder insert( int index ,char c);
	
	/**
	 * 在指定位置插入 char 数组
	 * @param index
	 * @param c
	 * @return
	 */
	public CharacterBuilder insert( int index ,char []c);
	
	/**
	 * 
	 * 在指定位置插入 long 类型
	 * @param index
	 * @param l
	 * @return
	 */
	public CharacterBuilder insert( int index ,long l);

	/**
	 * 
	 * 在指定位置插入 boolean 类型
	 * @param index
	 * @param b
	 * @return
	 */
	public CharacterBuilder insert( int index ,boolean b);

	/**
	 * 在指定位置插入 java.lang.StringBuilder 
	 * @param index
	 * @param sb
	 * @return
	 */
	public CharacterBuilder insert( int index ,java.lang.StringBuilder sb);

	/**
	 * 在指定位置插入 java.lang.StringBuffer 
	 * @param index
	 * @param sb
	 * @return
	 */
	public CharacterBuilder insert( int index ,java.lang.StringBuffer sb);
	
	
    /**
     * 清除缓冲区
     * @return
     */
    public CharacterBuilder clear();
    
    /**
     * 删除指定位置上的字符
     * @param index
     * @return
     */
    public CharacterBuilder delete( int index);
    
    /**
     * 删除字符串
     * @param start 起始位置
     * @param end 终止位置
     * @return
     */
    public CharacterBuilder delete( int start, int end);
    
    
    public int indexOf(String str) ;
    
    
    public char[] toArray()  ;
    /**
     * 字符串长度
     * @return
     */
    public int length();
    
    
    /**
     * 缓冲区容量
     * @return
     */
    public int capacity() ;
}
