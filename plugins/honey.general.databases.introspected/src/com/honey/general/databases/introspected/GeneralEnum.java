package com.honey.general.databases.introspected;

import java.util.ArrayList;
import java.util.List;

import com.honey.core.dbmapping.structure.EnumType;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.utils.ObjectDump;

/**
 * 详细描述参阅接口的描述
 * 
 * @author Administrator
 * 
 */
public class GeneralEnum implements EnumType {

	/**
	 * 枚举值列表
	 */
	private List<EnumValue> enums;

	/**
	 * 枚举注释(可以是字段注释)
	 */
	private String comment;

	/**
	 * 枚举对象名称(字段名称)
	 */
	private String name;
	
	/**
	 * java名称
	 */
	private String javaProperty;
	
    /**
     * 枚举字段数据类型(也是当前字段的数据类型)
     */
    private FullyQualifiedJavaType fullyQualifiedJavaType;
    

	public GeneralEnum() {
		this.enums = new ArrayList<EnumValue>();
	}

	/**
	 * 
	 * @param name
	 *            枚举名称(可以是字段名称)
	 */
	public GeneralEnum(String name) {

		this.enums = new ArrayList<EnumValue>();
		this.name = name;

	}

	@Override
	public List<EnumValue> getEnums() {

		return enums;
	}

	@Override
	public String getComment() {

		return this.comment;
	}

	@Override
	public String getName() {

		return this.name;
	}

	/**
	 * 添加枚举值
	 * 
	 * @param enums
	 */
	public void setEnums(List<EnumValue> enums) {
		this.enums = enums;
	}

	/**
	 * 添加枚举值
	 * 
	 * @param enumValue
	 */
	public void addEnumValue(EnumValue enumValue) {
		if (enumValue != null)
			this.enums.add(enumValue);
	}

	/**
	 * 设定枚举对象注释(可以是字段的注释)
	 * 
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 设定枚举对象名称(可以是字段名称)
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getJavaProperty() {
		return javaProperty;
	}

	public void setJavaProperty(String javaProperty) {
		this.javaProperty = javaProperty;
	}
	
	@Override
	public FullyQualifiedJavaType getFullyQualifiedJavaType() {
		return fullyQualifiedJavaType;
	}

	public void setFullyQualifiedJavaType(
			FullyQualifiedJavaType fullyQualifiedJavaType) {
		this.fullyQualifiedJavaType = fullyQualifiedJavaType;
	}

	/**
	 * 添加枚举字段
	 * @param value
	 * @param name
	 * @param comment
	 */
	public void addEnumValue(String value,String name,String javaProperty ,String comment){
		GeneralEnumValue enumValue = new GeneralEnumValue();
		enumValue.setValue(value);
		enumValue.setName(name);
		enumValue.setComment(comment);
		enumValue.setJavaProperty(javaProperty);
		this.enums.add(enumValue) ;
	}
	
	@Override
	public String toString() {
        return ObjectDump.dump(this);
    }

	/**
	 * 枚举对象( 映射到数据库的枚举或者 解析数据库commen得到枚举对象) 详细的注释参阅接口注释
	 * 
	 * @author Administrator
	 * 
	 */
	public class GeneralEnumValue implements EnumValue {

		private String javaProperty;
		
		/**
		 * 枚举值的名称
		 */
		private String name;

		/**
		 * 枚举值的注释
		 */
		private String comment;

		/**
		 * 枚举值
		 */
		private String value;

		@Override
		public String getValue() {
			return this.value;
		}

		@Override
		public String getComment() {
			return this.comment;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
		
		
		@Override
		public String getJavaProperty() {
			return javaProperty;
		}

		public void setJavaProperty(String javaProperty) {
			this.javaProperty = javaProperty;
		}
		
	}

}
