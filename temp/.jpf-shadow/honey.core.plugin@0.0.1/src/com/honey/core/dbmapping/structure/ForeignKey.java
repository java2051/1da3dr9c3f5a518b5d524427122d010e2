package com.honey.core.dbmapping.structure;



/**
 * 外键关联关系. 举例说明这个关系:<br />
 *  A表的id字段记为A.id (A是主键表)<br />
 *  B表的f_1字段记为B.f_1 (B是外键表)<br />
 *  C表的f_2字段记为C.f_2 (C是外键表)<br />
 * 假设:<br />
 * 	A.id是的B.f_1外键<br />
 *  A.id是的C.f_2外键<br />
 * 那么<br />
 * 	真对于A表<br /> 
 * ExportedKeyStructure获取B和B.f_1 以及 C和C.f_2的信息,也就是外键表和外键表字段的信息.<br />
 * ForeignKeyStructure获取不到任何信息
 * 真对于B表<br />
 * 	ExportedKeyStructure获取不到任何信息
 *  ForeignKeyStructure获取到的是A和A.id的信息
 * 真对于C表<br />
 * 	ExportedKeyStructure获取不到任何信息
 *  ForeignKeyStructure获取到的是A和A.id的信息
 * 
 * 这两个对象获取的关系刚好相反,而却很容混淆.<br /><br>
 * 
 * 父类方法说明:<br>
 * 	getName() 是外键表字段名称<br>
 *  getComment() 注释<br>
 * 
 * @author Administrator
 *
 */

public interface ForeignKey extends Structure {
	
	/**
	 * 获取外键关联的表
	 * @return
	 */
	public Table getTable();
	
	/**
	 * 外键关联字段的名称
	 * @return
	 */
	public TableColumn getTableColumn();
}
