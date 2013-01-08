package com.honey.core.dbmapping.structure;

/**
 * 数据库结构类型定义<br />
 * 
 * @author Administrator
 *
 */
public enum StructureType {
	
	/** 物理表 */
	TABLE((short)0,true,"TABLE"),
	
	/** 视图 */
	VIEW((short)1,false,"VIEW"),
	
	/** 临时表 */
	TEMPORARY((short)2,false,"TEMPORARY"),
	
	/** 存储过程 */
	PROCEDURE((short)3,false,"PROCEDURE"),
	
	/** 函数 */
	FUNCTION((short)4,false,"FUNCTION"),

	/** 其它 */
	OTHER((short)5,false,"OTHER"),

	;
	
	private short type;
	
	private boolean alter;
	
	private String name;
	
	private StructureType(short type,boolean alter ,String name){
		this.type = type;
		this.alter = alter;
		this.name = name;
	}

	/**
	 * 获取数据库结构的类型
	 *  @return
	 */
	public short getType() {
		return type;
	}
	
	/**
	 * 当前数据库结构是否可以使用 insert和update语句, true:是   false:否<br />
	 * 本系统中只用table是允许使用 insert和update(view在部分条件下是可以使用insert和update,本系统中不考虑这种情况).
	 * @return
	 */
	public boolean isAlter() {
		return alter;
	}
	
	/**
	 * 获取数据库结构的名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		
		return name;
	}

}
