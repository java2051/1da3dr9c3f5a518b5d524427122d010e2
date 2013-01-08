package com.honey.core.dbmapping.structure;

import java.util.List;

import com.honey.core.types.FullyQualifiedJavaType;

/**
 * 数据库枚举类型, 包含 枚举类的名称和枚举对象.
 * 父类方法说明<br />
 *  getName() 返回枚举对象名称,就是数据库字段名称<br />
 *  getComment() 枚举注释,也可以是字段注释<br />
 *  本类方法说明<br />
 *  getEnums <br />
 *  返回枚举列表
 * @author Administrator
 *
 */
public interface EnumType extends Structure{
	
	/**
	 *返回枚举列表
	 * @return
	 */
	public  List<EnumValue> getEnums();
	
	
	/**
	 * 数据库类映射到java下的类型
	 * @return
	 */
	public FullyQualifiedJavaType getFullyQualifiedJavaType() ;
	
	
	/**
	 * 枚举字段. 映射到数据库的枚举或者 解析数据库commen得到枚举对象,例如:<br />
	 * 某个字段的comment 如下:<br /> 
	 * 是否有效 0:ENABLE:启用 1:DISABLE:禁用 <br />
	 * 这种描述会被解析成枚举对象 <br /><br />
	 * 父类方法说明: <br />
	 * getName() 返回枚举名称(事例中的"ENABLE" 和 "DISABLE") <br />
	 * getComment() 返回枚举注释(事例中的"启用" 和 "禁用"  )<br />
	 *  本类方法说明:<br />
	 *  getValue() 返回枚举值( 事例中的"0" 和 "1")
	 * @author Administrator
	 *
	 */
	public interface EnumValue  extends Structure{
		
		/**
		 * 返回枚举值
		 * @return
		 */
		public String getValue();

	}
}


