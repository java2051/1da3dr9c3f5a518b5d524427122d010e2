package com.honey.core.dbmapping.structure;

/**
 * 存储过程结构
 * @author Administrator
 *
 */
public interface Procedure extends Schema{
	
	/**
	 * 存储过程是否返回值, 详情参阅DatabaseMetaData类
	 * @return
	 */
	public ProcedureResultType getProcedureResultType();

	/**
	 * 存储过程是否返回值.请参阅DatabaseMetaData类
	 * @author Administrator
	 *
	 */
	public enum ProcedureResultType{
		
		/**
		 * 请参阅DatabaseMetaData类的procedureResultUnknown属性<br />
		 * 不知道存储过程是否返回值<br />
	     * Indicates that it is not known whether the procedure returns
	     * a result.
	     * <P>
	     * A possible value for column <code>PROCEDURE_TYPE</code> in the
	     * <code>ResultSet</code> object returned by the method
	     * <code>getProcedures</code>.
	     */
		PROCEDURE_RESULT_UNKNOWN((short)0),
		
		/**
		 * 请参阅DatabaseMetaData类的procedureNoResult属性<br />
		 * 存储过程不返回值<br />
	     * Indicates that the procedure does not return a result.
	     * <P>
	     * A possible value for column <code>PROCEDURE_TYPE</code> in the
	     * <code>ResultSet</code> object returned by the method
	     * <code>getProcedures</code>.
	     */
		PROCEDURE_NO_RESULT((short)1),

	    /**
	     * 请参阅DatabaseMetaData类的procedureReturnsResult属性<br />
	     * 存储过程有返回值<br />
	     * Indicates that the procedure returns a result.
	     * <P>
	     * A possible value for column <code>PROCEDURE_TYPE</code> in the
	     * <code>ResultSet</code> object returned by the method
	     * <code>getProcedures</code>.
	     */
	    PROCEDURE_RETURNS_RESULT((short)2);
		;
				
		private short type; 
		
		private ProcedureResultType(short type){
			this.type = type;
		}
		
		public short getType() {
			return type;
		}
	
		/**
		 * 根据type的值返回ProcedureResultType类型
		 * @param type
		 * @return
		 */
		public static ProcedureResultType getProcedureResultType(short type){
			switch (type){
				case 0: //PROCEDURE_RESULT_UNKNOWN
					return PROCEDURE_RESULT_UNKNOWN;
					
				case 1: //PROCEDURE_NO_RESULT
					return PROCEDURE_NO_RESULT;
					
				case 2: //PROCEDURE_RETURNS_RESULT
					return PROCEDURE_RETURNS_RESULT;
						
				default : //PROCEDURE_RESULT_UNKNOWN
					return PROCEDURE_RESULT_UNKNOWN;
			}
		}
	}
}
