package com.honey.compilation.java;

/**
 * java 关键字
 * @author Administrator
 *
 */
public  enum JavaKeyWord {
	
	ABSTRACT ( "abstract" , true ),
	ABSTRACTCLASS( "abstract class" , true ),
	BOOLEAN ( "boolean" , true ),
	BREAK ( "break" , true ),
	BYTE ( "byte" , true ),
	CASE ( "case" , true ),
	CATCH ( "catch" , true ),
	CHAR ( "char" , true ),
	CLASS ( "class" , true ),
	CONTINUE ( "continue" , true ),
	DEFAULT ( "default" , true ),
	DO ( "do" , true ),
	DOUBLE ( "double" , true ),
	ELSE ( "else" , true ),
	ENUM ( "enum" , true ),
	EXTENDS ( "extends" , true ),
	FINAL ( "final" , true ),
	FINALLY ( "finally" , true ),
	FLOAT ( "float" , true ),
	FOR ( "for" , true ),
	IF ( "if" , true ),
	IMPLEMENTS ( "implements" , true ),
	IMPORT ( "import" , true ),
	INSTANCEOF ( "instanceof" , true ),
	INT ( "int" , true ),
	INTERFACE ( "interface" , true ),
	LONG ( "long" , true ),
	NATIVE ( "native" , true ),
	NEW ( "new" , true ),
	PACKAGE ( "package" , true ),
	PRIVATE ( "private" , true ),
	PROTECTED ( "protected" , true ),
	PUBLIC ( "public" , true ),
	RETURN ( "return" , true ),
	STRICTFP ( "strictfp" , true ),
	SHORT ( "short" , true ),
	STATIC ( "static" , true ),
	SUPER ( "super" , true ),
	SWITCH ( "switch" , true ),
	SYNCHRONIZED ( "synchronized" , true ),
	THIS ( "this" , true ),
	THROW ( "throw" , true ),
	THROWS ( "throws" , true ),
	TRANSIENT ( "transient" , true ),
	TRY ( "try" , true ),
	VOID ( "void" , true ),
	VOLATILE ( "volatile" , true ),
	WHILE ( "while" , true ),
	NULL ( "null" , true ),
	
	
	/** java动态参数(可变参数)*/
	DYNAMIC_PARAMETER ( "..." , false ),
	

	/** this 关键字*/
	OPERATION_THIS ( "this." , false ),
	
	/** ; 结束符*/
	OPERATION_SPLIT ( ";" , false ),
	
	/** 空格 */
	OPERATION_SPACE ( " " , false ),

	/** ,  */
	OPERATION_COMMA ( "," , true ),
	
	/** { */
	OPERATION_BRACE_LEFT ( "{" , false ),

	/** } */
	OPERATION_BRACE_RIGHT ( "}" , false ),
	
	/** ( */
	OPERATION_BRACKET_LEFT ( "(" , true ),

	/** ) */
	OPERATION_BRACKET_RIGHT ( " )" , false ),

	/** < */
	OPERATION_ANGLE_BRACKETS_LEFT ( "<" , true ),

	/** > */
	OPERATION_ANGLE_BRACKETS_RIGHT ( " >" , false ),
	
	
	OPERATION_BRACKET_EQUALS ( " =" , true ),
	
	/** == */
	LOGIC_EQUALS ( " ==" , true ),
	
	/** || */
	LOGIC_OR ( " ||" , true ),
	
	/** && */
	LOGIC_AND ( " &&" , true ),
	
	/** != */
	LOGIC_NOT_EQUALS ( " !=" , true ),
	
	OPERATION_$ ( "$" , false ),
	
	/** . */
	OPERATION_DOT ( "." , false ),
	;

	/** 关键字的值 */
	private final String value;
    
	/** 是否带有空格 */
    private final boolean space;
    
    /**
     * 构造函数
     * @param value 关键字 
     * @param space 是否带有空格
     */
    private JavaKeyWord(String value,boolean space) {
    	this.value = value;
    	this.space = space;
    }
    
    /**
     * 获取值
     * @return
     */
    public final String getValue() {
        return this.value ;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public final String toString() {
    
    	return value +(space?OPERATION_SPACE:"");
    }
}










