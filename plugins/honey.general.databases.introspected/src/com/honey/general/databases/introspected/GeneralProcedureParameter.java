package com.honey.general.databases.introspected;

import java.sql.Types;

import com.honey.core.dbmapping.structure.EnumType;
import com.honey.core.dbmapping.structure.ProcedureParameter;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.KeyValuePair;
import com.honey.core.utils.ObjectDump;

/**
 * 存储过程参数
 * @author Administrator
 *
 */
public class GeneralProcedureParameter implements ProcedureParameter {

	/** jdbc的类型 */
	private int jdbcType;

	/** 数据库中的类型名称(例如: varcar , bigint ) */
	private String dbTypeName;

	/** 存储过程列类型(输入 输出 ) */
	private ProcedureColumnType procedureColumnType;

	/** 数据库类型映射到java类型 */
	private FullyQualifiedJavaType fullyQualifiedJavaType;

	/** 映射到java的名称 */
	private String javaProperty;

	/** 数据类型长度 */
	private long length;

	/** 数据库类型小数位 */
	private int scale;

	/** 是否为空(true:表示可以空 false:不能为空) */
	private boolean nullable;

	/** 注释 */
	private String comment;

	/** 名称 */
	private String name;

	/** 枚举 */
    private EnumType  enumSchema;
	
	/** 验证配置 */
	private GeneralColumnValidator[] columnValidator ;

	/** 扩展属性  */
	private KeyValuePair[] extendAttribute ;
	
	@Override
	public ProcedureColumnType getProcedureColumnType() {
		
		return procedureColumnType;
	}

	@Override
	public String getDbTypeName() {

		return this.dbTypeName;
	}

	@Override
	public FullyQualifiedJavaType getFullyQualifiedJavaType() {

		return this.fullyQualifiedJavaType;
	}

	@Override
	public String getJavaProperty() {

		return this.javaProperty;
	}

	@Override
	public int getJdbcType() {
		return this.jdbcType;
	}

	@Override
	public long getLength() {
		return this.length;
	}

	@Override
	public int getScale() {
		return this.scale;
	}

	@Override
	public boolean isNullable() {
		return this.nullable;
	}

	@Override
	public String getComment() {
		return this.comment;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setDbTypeName(String dbTypeName) {
		this.dbTypeName = dbTypeName;
	}

	public void setProcedureColumnType(ProcedureColumnType procedureColumnType) {
		this.procedureColumnType = procedureColumnType;
	}

	public void setFullyQualifiedJavaType(
			FullyQualifiedJavaType fullyQualifiedJavaType) {
		this.fullyQualifiedJavaType = fullyQualifiedJavaType;
	}

	public void setJavaProperty(String javaProperty) {
		this.javaProperty = javaProperty;
	}

	public void setJdbcType(int jdbcType) {
		this.jdbcType = jdbcType;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isStringColumn() {
        return fullyQualifiedJavaType.equals(JDKFullyQualifiedJavaType
                .getStringInstance());
    }
	
	@Override
    public boolean isJdbcCharacterColumn() {
        return jdbcType == Types.CHAR || jdbcType == Types.CLOB
                || jdbcType == Types.LONGVARCHAR || jdbcType == Types.VARCHAR;
    }

    @Override
	public boolean isJDBCDateColumn() {
        return fullyQualifiedJavaType.equals(JDKFullyQualifiedJavaType
                .getDateInstance())
                && "DATE".equalsIgnoreCase(dbTypeName); //$NON-NLS-1$
    }

	/**
	 * @return the columnValidator
	 */
    @Override
	public final GeneralColumnValidator[] getColumnValidator() {
		return columnValidator;
	}

	/**
	 * @param columnValidator the columnValidator to set
	 */
	public final void setColumnValidator(GeneralColumnValidator []columnValidator) {
		this.columnValidator = columnValidator;
	}
	
	/**
	 * @return the extendAttribute
	 */
	@Override
	public final KeyValuePair[] getExtendAttribute() {
		return extendAttribute;
	}

	/**
	 * @param extendAttribute the extendAttribute to set
	 */
	public final void setExtendAttribute(KeyValuePair[] extendAttribute) {
		this.extendAttribute = extendAttribute;
	}

	@Override
	public EnumType getEnumSchema() {
		
		return this.enumSchema;
	}
	
	public void setEnumSchema(EnumType enumType ) {
		
		this.enumSchema = enumType;
	}
	
	@Override
	public String toString() {
        return ObjectDump.dump(this);
    }
	
}
