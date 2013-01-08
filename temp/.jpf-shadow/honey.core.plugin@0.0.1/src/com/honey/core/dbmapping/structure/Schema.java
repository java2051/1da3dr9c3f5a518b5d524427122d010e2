package com.honey.core.dbmapping.structure;

import java.util.Map;

import com.honey.core.types.Vendor;

/**
 * 数据库结构描述. 一般情况下数据库分为如下几种结构.<br />
 * 		TABLE <br />
 *      VIEW <br />
 *      TEMPORARY <br />
 *      PROCEDURE <br />
 *      FUNCTION <br />
 * 这些结构定义请参阅StructureType这个类    
 * 
 * @author Administrator
 *
 */
public interface Schema extends Structure {
	
	/**
	 * 返回数据可结构的类型,SchemaType中定义了如下类型:<br />
	 * 		TABLE <br />
	 *      VIEW <br />
	 *      TEMPORARY <br />
	 *      PROCEDURE <br />
	 *      FUNCTION <br />
	 *  在这些数据结构中定义是否可以使用insert和update语句
	 * @return
	 */
	public StructureType getSchemaType();
	
	/**
	 * 获取结构的创建语句(如果可以获取)
	 * @return
	 */
	public String getSqlScipt() ;

	/**
	 * 获取数据的vendor(数据库产品名称,例如 mysql oracle) 
	 * @return
	 */
	public Vendor getVendor() ;
	
	/**
	 * 获取数据更多其它信息,以Key value的形式存在map中
	 * @return
	 */
	public Map<String, String> getMoreInformation();
	
	/**
	 * 是否包含大字段列
	 * @return
	 */
	public boolean isIncludeBlobColumn();
	
	/**
	 * 是否包含主键列
	 * @return
	 */
	public boolean isIncludePrimaryKeyColumn();
	
	/**
	 * 是否包含枚举列
	 * @return
	 */
	public boolean isIncludeEnumColumn();
	
	
}
