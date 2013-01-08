package com.honey.core.dbmapping.structure;

import java.util.List;


/**
 * 数据库表列的映射继承SchemaColumn
 * @author Administrator
 *
 */
public interface TableColumn extends Column{
	
	/**
	 * 是否是主键
	 * @return
	 */
	public boolean isPrimaryKey() ;

	/**
	 * 是否是自动增长列
	 * @return
	 */
	public boolean isAutoIncrement() ;

	/**
	 * 数据库中设定的默认值
	 * @return
	 */
	public String getDefaultValue() ;
	
	/**
	 * 获取外键表信息(详细参阅对象注释)
	 * @return
	 */
	public ForeignKey[] getForeignKey() ;

	/**
	 * 获取虚拟删除的状态值, 如果返回空表示不使用虚拟删除
	 * @return
	 */
	public String getVirtualDeleteValue() ;

	
	/**
	 * 获取表的所有导出的键(外键关联)(详细参阅对象注释)
	 * @return
	 */
	public ExportedKey[] getExportedKey ();
	
/*	public boolean isStringColumn() {
        return fullyQualifiedJavaType.equals(FullyQualifiedJavaType
                .getStringInstance());
    }

    public boolean isJdbcCharacterColumn() {
        return jdbcType == Types.CHAR || jdbcType == Types.CLOB
                || jdbcType == Types.LONGVARCHAR || jdbcType == Types.VARCHAR;
    }
    
	public boolean isJDBCDateColumn() {
        return fullyQualifiedJavaType.equals(FullyQualifiedJavaType
                .getDateInstance())
                && "DATE".equalsIgnoreCase(jdbcTypeName); //$NON-NLS-1$
    }
	
	public boolean isJDBCTimeColumn() {
        return fullyQualifiedJavaType.equals(FullyQualifiedJavaType
                .getDateInstance())
                && "TIME".equalsIgnoreCase(jdbcTypeName); //$NON-NLS-1$
    }*/
	
	/**
	 * 是否是大字段类型(java.sql.Types中定义的BLOB类型或者CLOB类型,数据库中类型映射到blob或者clob类型由jdbc驱动器决定. 
	 * 例如:mysql中text类型被映射成CLOB类型  )
	 */
	public boolean isBLOBColumn() ;	
	
}
