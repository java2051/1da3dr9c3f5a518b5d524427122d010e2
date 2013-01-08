package com.honey.core.dbmapping.structure;


/**
 * 存储过程参数结构
 * 
 * @author Administrator
 * 
 */
public interface ProcedureParameter extends Column{

	/**
	 * 存储过程参数类型(指 输入参数 还是输出参数)
	 * 
	 * @return
	 */
	public ProcedureColumnType getProcedureColumnType();

	/**
	 * 
	 * 参数输入输出类型
	 * 
	 * DatabaseMetaData中对getProcedureColumns方法对COLUMN_TYPE的描述
	 * procedureColumnUnknown - nobody knows procedureColumnIn - IN parameter
	 * procedureColumnInOut - INOUT parameter procedureColumnOut - OUT parameter
	 * procedureColumnReturn - procedure return value procedureColumnResult -
	 * result column in ResultSet
	 */
	public enum ProcedureColumnType
	{
		/**
		 * 
		 * Indicates that type of the column is unknown.
		 * <P>
		 * A possible value for the column <code>COLUMN_TYPE</code> in the
		 * <code>ResultSet</code> returned by the method
		 * <code>getProcedureColumns</code>.
		 */
		PROCEDURE_COLUMN_UNKNOWN( (short) 0 ),
		/**
		 * Indicates that the column stores IN parameters.
		 * <P>
		 * A possible value for the column <code>COLUMN_TYPE</code> in the
		 * <code>ResultSet</code> returned by the method
		 * <code>getProcedureColumns</code>.
		 */
		PROCEDURE_COLUMN_IN( (short) 1 ),
		/**
		 * Indicates that the column stores INOUT parameters.
		 * <P>
		 * A possible value for the column <code>COLUMN_TYPE</code> in the
		 * <code>ResultSet</code> returned by the method
		 * <code>getProcedureColumns</code>.
		 */
		PROCEDURE_COLUMN_INOUT( (short) 2 ),
		/**
		 * Indicates that the column stores OUT parameters.
		 * <P>
		 * A possible value for the column <code>COLUMN_TYPE</code> in the
		 * <code>ResultSet</code> returned by the method
		 * <code>getProcedureColumns</code>.
		 */
		PROCEDURE_COLUMN_OUT( (short) 4 ),
		/**
		 * Indicates that the column stores return values.
		 * <P>
		 * A possible value for the column <code>COLUMN_TYPE</code> in the
		 * <code>ResultSet</code> returned by the method
		 * <code>getProcedureColumns</code>.
		 */
		PROCEDURE_COLUMN_RETURN( (short) 5 ),
		/**
		 * Indicates that the column stores results.
		 * <P>
		 * A possible value for the column <code>COLUMN_TYPE</code> in the
		 * <code>ResultSet</code> returned by the method
		 * <code>getProcedureColumns</code>.
		 */
		PROCEDURE_COLUMN_RESULT( (short) 3 ), ;

		private short	type;

		private ProcedureColumnType(short type)
		{
			this.type = type;
		}

		public short getType()
		{
			return type;
		}

		/**
		 * 根据type返回ProcedureColumnType枚举
		 * 
		 * @param type
		 * @return
		 */
		public static ProcedureColumnType getProcedureColumnType(short type)
		{
			switch ( type )
			{
				case 0 : // PROCEDURE_COLUMN_UNKNOWN
					return ProcedureColumnType.PROCEDURE_COLUMN_UNKNOWN;
				case 1 : // PROCEDURE_COLUMN_IN
					return ProcedureColumnType.PROCEDURE_COLUMN_IN;
				case 2 : // PROCEDURE_COLUMN_INOUT
					return ProcedureColumnType.PROCEDURE_COLUMN_INOUT;
				case 4 : // PROCEDURE_COLUMN_OUT
					return ProcedureColumnType.PROCEDURE_COLUMN_OUT;
				case 5 : // PROCEDURE_COLUMN_RETURN
					return ProcedureColumnType.PROCEDURE_COLUMN_RETURN;
				case 3 : // PROCEDURE_COLUMN_RESULT
					return ProcedureColumnType.PROCEDURE_COLUMN_RESULT;
				default : // PROCEDURE_COLUMN_UNKNOWN
					return ProcedureColumnType.PROCEDURE_COLUMN_UNKNOWN;
			}
		}
	}
}
