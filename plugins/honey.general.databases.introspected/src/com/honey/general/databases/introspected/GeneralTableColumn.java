package com.honey.general.databases.introspected;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.honey.core.dbmapping.structure.EnumType;
import com.honey.core.dbmapping.structure.ExportedKey;
import com.honey.core.dbmapping.structure.ForeignKey;
import com.honey.core.dbmapping.structure.TableColumn;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.types.JDKFullyQualifiedJavaType;
import com.honey.core.utils.KeyValuePair;
import com.honey.core.utils.ObjectDump;

public class GeneralTableColumn implements TableColumn{

	/**
	 * 数据库字段名称
	 */
	private String fieldName;

	/**
	 * jdbc 的数据库类型(来自 java.sql.Types 的 SQL 类型)
	 */
	private int jdbcType;

	/**
	 * 数据库中的类型名称(例如: varcar )
	 */
	private String dbTypeName;

	/**
	 * 是否为空(true : 表示可以空 false:不能为空)
	 */
	private boolean nullable;

	/**
	 * 字段长度(CLOB类型长度会超过int类型长度)
	 */
	private long length;

	/**
	 * 小数的位数 
	 */
	private int scale;
	
	/**
	 * 是否是主键
	 */
	private boolean primaryKey;
	
	/**
	 * 是否是自动增长列
	 */
    private boolean isAutoIncrement; 
    
	
	/**
	 * 映射到javabean的字段名称
	 */
	private String javaProperty;
	
	
	/**
	 * 数据库字段的注释
	 */
	private String comment;
	
    /**
     * 默认值
     */
    private String defaultValue;
	
    /**
     * 虚拟删除的状态值
     */
    private String virtualDeleteValue;
	
    
    /**
     * java下类型映射
     */
    private FullyQualifiedJavaType fullyQualifiedJavaType;
    
    /**
     * 枚举
     */
    private EnumType  enumSchema;
    
    /**
     * 是主键表外键(详细内容参阅类注释)
     */
    private List<ExportedKey> exportedKey ;

    /**
     * 外键(详细内容参阅类注释)(也是import key )
     */
	private List<ForeignKey> foreignKey;

	/**
	 * 验证信息
	 */
	private GeneralColumnValidator[] columnValidator ;
	
	/**
	 * 扩展属性
	 */
	private KeyValuePair[] extendAttribute ;
	
	public GeneralTableColumn(){
		exportedKey = new ArrayList<ExportedKey>();
		foreignKey =  new ArrayList<ForeignKey>();
	}
	
    @Override
    public String getName() {
		return fieldName;
	}

	public void setName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public int getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(int jdbcType) {
		this.jdbcType = jdbcType;
	}

	@Override
	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	@Override
	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	@Override
	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	@Override
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Override
	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}

	public void setAutoIncrement(boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}
	
	@Override
	public String getJavaProperty() {
		return javaProperty;
	}

	public void setJavaProperty(String javaProperty) {
		this.javaProperty = javaProperty;
	}

	@Override
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	
	
	public FullyQualifiedJavaType getFullyQualifiedJavaType() {
		return fullyQualifiedJavaType;
	}

	public void setFullyQualifiedJavaType(
			FullyQualifiedJavaType fullyQualifiedJavaType) {
		this.fullyQualifiedJavaType = fullyQualifiedJavaType;
	}
	
	@Override
	public String getDbTypeName() {
        if (dbTypeName == null) {
            return "OTHER"; //$NON-NLS-1$
        }

        return dbTypeName;
    }
	
	public void setDbTypeName(String dbTypeName) {
		this.dbTypeName = dbTypeName;
	}

	public String getJavaProperty(String prefix) {
        if (prefix == null) {
            return javaProperty;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(javaProperty);

        return sb.toString();
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
/*	
	public boolean isJDBCTimeColumn() {
        return fullyQualifiedJavaType.equals(FullyQualifiedJavaType
                .getDateInstance())
                && "TIME".equalsIgnoreCase(jdbcTypeName); //$NON-NLS-1$
    }*/
	
	@Override
	public boolean isBLOBColumn() {
        int jdbcType = getJdbcType();

        return Types.BINARY==jdbcType ||  Types.BLOB == jdbcType //$NON-NLS-1$ //$NON-NLS-2$
                || Types.CLOB == jdbcType || Types.LONGVARBINARY == jdbcType //$NON-NLS-1$ //$NON-NLS-2$
                || Types.LONGVARCHAR == jdbcType || Types.VARBINARY == jdbcType ; //$NON-NLS-1$ //$NON-NLS-2$
    }
	
	
	@Override
	public EnumType getEnumSchema() {
		
		return this.enumSchema;
	}
	
	public void setEnumSchema(EnumType enumType ) {
		
		this.enumSchema = enumType;
	}

	@Override
	public ExportedKey[] getExportedKey() {
		return  this.exportedKey.toArray(new ExportedKey[this.exportedKey.size()]);
	}
	
	public void addExportedKey(ExportedKey exportedKey) {
		if(exportedKey != null)
			this.exportedKey.add(exportedKey);	
	}

	@Override
	public ForeignKey[] getForeignKey() {
		return  this.foreignKey.toArray(new ForeignKey[this.foreignKey.size()]);
		
	}
	
	public void addForeignKey(ForeignKey foreignKey) {
		if(foreignKey != null)
			this.foreignKey.add(foreignKey);	
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
	public final void setColumnValidator(GeneralColumnValidator[] columnValidator) {
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

	public String getVirtualDeleteValue() {
		return virtualDeleteValue;
	}

	public void setVirtualDeleteValue(String virtualDeleteValue) {
		this.virtualDeleteValue = virtualDeleteValue;
	}

	@Override
	public String toString() {
        return ObjectDump.dump(this);
    }
}
