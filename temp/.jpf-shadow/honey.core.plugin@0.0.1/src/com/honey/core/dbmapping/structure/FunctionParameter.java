package com.honey.core.dbmapping.structure;

/**
 * 函数参数结构
 * @author Administrator
 *
 */
public interface FunctionParameter  extends Column{

	/**
	 * 存储过程参数类型(指 输入参数 还是输出参数)
	 * @return
	 */
	public FunctionColumnType getFunctionColumnType();
	
	/**
	 * 
	 * 参数输入输出类型
	 * 
	 * DatabaseMetaData中对getProcedureColumns方法对COLUMN_TYPE的描述
	 * functionColumnUnknown - nobody knows 
	 * functionColumnIn - IN parameter 
	 * functionColumnInOut - INOUT parameter 
	 * functionColumnOut - OUT parameter 
	 * functionColumnReturn - function return value 
	 * functionColumnResult - Indicates that the parameter or column is a column in
	 */
	public enum FunctionColumnType{
		
		/**
	     * Indicates that type of the parameter or column is unknown.
	     * <P>
	     * A possible value for the column
	     * <code>COLUMN_TYPE</code>
	     * in the <code>ResultSet</code> 
	     * returned by the method <code>getFunctionColumns</code>.
	     */
	     FUNCTION_COLUMN_UNKNOWN ((short)0),

	     /**
	      * Indicates that the parameter or column is an IN parameter.
	      * <P>
	      *  A possible value for the column
	      * <code>COLUMN_TYPE</code>
	      * in the <code>ResultSet</code> 
	      * returned by the method <code>getFunctionColumns</code>.
	      * @since 1.6
	      */
	    FUNCTION_COLUMN_IN ( (short)1 ),

	    /**
	     * Indicates that the parameter or column is an INOUT parameter.
	     * <P>
	     * A possible value for the column
	     * <code>COLUMN_TYPE</code>
	     * in the <code>ResultSet</code> 
	     * returned by the method <code>getFunctionColumns</code>.     
	     * @since 1.6
	     */
	    FUNCTION_COLUMN_INOUT ((short) 2 ) ,

	    /**
	     * Indicates that the parameter or column is an OUT parameter.
	     * <P>
	     * A possible value for the column
	     * <code>COLUMN_TYPE</code>
	     * in the <code>ResultSet</code> 
	     * returned by the method <code>getFunctionColumns</code>.
	     * @since 1.6
	     */
	    FUNCTION_COLUMN_OUT  ((short) 3) ,
	    
	    /**
	     * Indicates that the parameter or column is a return value.
	     * <P>
	     *  A possible value for the column
	     * <code>COLUMN_TYPE</code>
	     * in the <code>ResultSet</code> 
	     * returned by the method <code>getFunctionColumns</code>.     
	     * @since 1.6
	     */
	    FUNCTION_COLUMN_RETURN ( (short) 4 ) ,
	    

	    /**
	     * Indicates that the parameter or column is a column in a result set.
	     * <P>
	     *  A possible value for the column
	     * <code>COLUMN_TYPE</code>
	     * in the <code>ResultSet</code> 
	     * returned by the method <code>getFunctionColumns</code>.     
	     * @since 1.6
	     */
	    FUNCTION_COLUMN_RESULT ( (short) 5 ) ,
	    
	    ;
		
	    private  short type ;
	    
		private FunctionColumnType( short type ){
			this.type = type ;
		}

		public short getType() {
			return type;
		}
		
		/**
		 * 根据type返回FUNCTIONColumnType枚举
		 * @param type
		 * @return
		 */
		public  FunctionColumnType getFunctionColumnType(short type){
			switch(type){
				case 0 : //FUNCTION_COLUMN_UNKNOWN
					return FUNCTION_COLUMN_UNKNOWN;
					
				case 1 : //FUNCTION_COLUMN_IN
					return FUNCTION_COLUMN_IN;
				
				case 2 : //FUNCTION_COLUMN_INOUT
					return FUNCTION_COLUMN_INOUT;
				
				case 4 : //FUNCTION_COLUMN_OUT
					return FUNCTION_COLUMN_OUT;
			
				case 5 : //FUNCTION_COLUMN_RETURN
					return FUNCTION_COLUMN_RETURN;
			
				case 3 : //FUNCTION_COLUMN_RESULT
					return FUNCTION_COLUMN_RESULT;
				default : //FUNCTION_COLUMN_UNKNOWN
					return FUNCTION_COLUMN_UNKNOWN;
			}
		}		
	}
}
