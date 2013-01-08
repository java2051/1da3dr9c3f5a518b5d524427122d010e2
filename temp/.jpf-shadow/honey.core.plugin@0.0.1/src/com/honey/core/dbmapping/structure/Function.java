package com.honey.core.dbmapping.structure;

/**
 * 在部分数据库中jdbc是不允许直接调用
 * 函数结构
 * @author Administrator
 *
 */
public interface Function extends Schema{

	/**
	 * 函数是否返回值, 详情参阅DatabaseMetaData类
	 * @return
	 */
	public FunctionResultType getFunctionResultType();

	/**
	 * 函数是否返回值.请参阅DatabaseMetaData类
	 * @author Administrator
	 *
	 */
	public enum FunctionResultType{
		
		FUNCTION_RESULTUN_KNOWN ((short) 0 ) ,

	    /**
	     * Indicates that the function  does not return a table.
	     * <P>
	     * A possible value for column <code>FUNCTION_TYPE</code> in the
	     * <code>ResultSet</code> object returned by the method
	     * <code>getFunctions</code>.
	     * @since 1.6
	     */
	    FUNCTION_NO_TABLE	( (short) 1 ),

	    /**
	     * Indicates that the function  returns a table.
	     * <P>
	     * A possible value for column <code>FUNCTION_TYPE</code> in the
	     * <code>ResultSet</code> object returned by the method
	     * <code>getFunctions</code>.
	     * @since 1.6
	     */
	    FUNCTION_RETURNS_TABLE	( (short) 2 )
		;
				
		private short type; 
		
		private FunctionResultType(short type){
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
		public static FunctionResultType getFunctionResultType(short type){
			switch (type){
				case 0: //FUNCTION_RESULTUN_KNOWN
					return FUNCTION_RESULTUN_KNOWN;
					
				case 1: //FUNCTION_NO_TABLE
					return FUNCTION_NO_TABLE;
					
				case 2: //FUNCTION_RETURNS_TABLE
					return FUNCTION_RETURNS_TABLE;
						
				default : //FUNCTION_RESULTUN_KNOWN
					return FUNCTION_RESULTUN_KNOWN;
			}			
		}
	}
}
