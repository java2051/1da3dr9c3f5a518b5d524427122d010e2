package com.honey.core.types;

/**
 * 常用的类
 * @author Administrator
 *
 */
public class JDKFullyQualifiedJavaType extends FullyQualifiedJavaType{

	/**
	 * 通过class转换成java对象封装
	 * @param clazz
	 */
	public JDKFullyQualifiedJavaType(Class<?> clazz) {
		super(clazz.getName());
	}

	/**
	 * 通过完整的字符串包转换成java对象封装
	 * @param fullTypeSpecification
	 */
	public JDKFullyQualifiedJavaType(String fullTypeSpecification) {
		super(fullTypeSpecification);
	}	

	public static final FullyQualifiedJavaType getIteratorInstance() {
		return new FullyQualifiedJavaType( "java.util.Iterator"); 
	}
	
	public static final FullyQualifiedJavaType getIteratorArrayInstance() {
		return new FullyQualifiedJavaType( "java.util.Iterator[]"); 
	}
	
	
	public static final FullyQualifiedJavaType getArrayListInstance() {

		return new FullyQualifiedJavaType("java.util.ArrayList");
	}
	
	public static final FullyQualifiedJavaType getArrayListArrayInstance() {

		return new FullyQualifiedJavaType("java.util.ArrayList[]");
	}
	
	public static final FullyQualifiedJavaType getMapInstance() {
		return new FullyQualifiedJavaType("java.util.Map"); 
	}
	
	public static final FullyQualifiedJavaType getMapArrayInstance() {
		return new FullyQualifiedJavaType("java.util.Map[]"); 
	}
	
	public static final FullyQualifiedJavaType getListInstance() {
		return new FullyQualifiedJavaType("java.util.List"); 
	}
	
	public static final FullyQualifiedJavaType getListArrayInstance() {
		return new FullyQualifiedJavaType("java.util.List[]"); 
	}
	
	public static final FullyQualifiedJavaType getHashMapInstance() {
		return new FullyQualifiedJavaType("java.util.HashMap"); 
	}
	
	public static final FullyQualifiedJavaType getHashMapArrayInstance() {
		return new FullyQualifiedJavaType("java.util.HashMap[]"); 
	}
	
	public static final FullyQualifiedJavaType getPropertiesInstance() {
		return new FullyQualifiedJavaType("java.util.Properties"); 
	}
	
	public static final FullyQualifiedJavaType getPropertiesArrayInstance() {
		return new FullyQualifiedJavaType("java.util.Properties[]"); 
	}
	
	public static final FullyQualifiedJavaType getFileInstance() {
		return new FullyQualifiedJavaType("java.io.File"); 
	}
	
	public static final FullyQualifiedJavaType getFileArrayInstance() {
		return new FullyQualifiedJavaType("java.io.File[]"); 
	}
	
	public static final FullyQualifiedJavaType getInputStreamInstance() {
		return new FullyQualifiedJavaType("java.io.InputStream"); 
	}
	
	public static final FullyQualifiedJavaType getInputStreamArrayInstance() {
		return new FullyQualifiedJavaType("java.io.InputStream[]"); 
	}
	
	public static final FullyQualifiedJavaType getOutputStreamInstance() {
		return new FullyQualifiedJavaType("java.io.OutputStream"); 
	}
	
	public static final FullyQualifiedJavaType getOutputStreamArrayInstance() {
		return new FullyQualifiedJavaType("java.io.OutputStream[]"); 
	}
	
	
	public static final FullyQualifiedJavaType getSerializableInstance() {
		return new FullyQualifiedJavaType("java.io.Serializable"); 
	}
	
	public static final FullyQualifiedJavaType getCloneableInstance() {
		return new FullyQualifiedJavaType("java.lang.Cloneable"); 
	}
	
	public static final FullyQualifiedJavaType getJSONObjectInstance() {
		return new FullyQualifiedJavaType("org.json.JSONObject"); 
	}
	
	public static final FullyQualifiedJavaType getJSONObjectArrayInstance() {
		return new FullyQualifiedJavaType("org.json.JSONObject[]"); 
	}
	
	public static final FullyQualifiedJavaType getDateInstance() {
		return  new FullyQualifiedJavaType("java.util.Date"); 
	}
	
	public static final FullyQualifiedJavaType getDateArrayInstance() {
		return  new FullyQualifiedJavaType("java.util.Date[]"); 
	}
	
	public static final FullyQualifiedJavaType getObjectInstance() {
		return new FullyQualifiedJavaType("java.lang.Object"); 
	}

	public static final FullyQualifiedJavaType getObjectArrayInstance() {
		return new FullyQualifiedJavaType("java.lang.Object[]"); 
	}
	
	public static final FullyQualifiedJavaType getStringInstance() {
		return new FullyQualifiedJavaType("String"); 
	}
	
	public static final FullyQualifiedJavaType getStringArrayInstance() {
		return new FullyQualifiedJavaType("String[]"); 
	}	
	
	public static final FullyQualifiedJavaType getIntInstance() {
		
		return new FullyQualifiedJavaType("int"); 
	}
	
	public static final FullyQualifiedJavaType getIntArrayInstance() {
		
		return new FullyQualifiedJavaType("int[]"); 
	}
	
	public static final FullyQualifiedJavaType getIntegerInstance() {
		
		return new FullyQualifiedJavaType("Integer"); 
	}
	
	public static final FullyQualifiedJavaType getIntegerArrayInstance() {
		
		return new FullyQualifiedJavaType("Integer[]"); 
	}
	
	public static final FullyQualifiedJavaType getDoubleInstance() {
		return new FullyQualifiedJavaType("Double"); 
	}
	
	public static final FullyQualifiedJavaType getDoubleArrayInstance() {
		return new FullyQualifiedJavaType("Double[]"); 
	}
	
	public static final FullyQualifiedJavaType getFloatInstance() {
		
		return new FullyQualifiedJavaType("Float"); 
	}
	
	public static final FullyQualifiedJavaType getFloatArrayInstance() {
		
		return new FullyQualifiedJavaType("Float[]"); 
	}
	
	
	public static final FullyQualifiedJavaType getShortInstance() {
		
		return new FullyQualifiedJavaType("Short"); 
	}
	
	public static final FullyQualifiedJavaType getShortArrayInstance() {
		
		return new FullyQualifiedJavaType("Short[]"); 
	}
	
	public static final FullyQualifiedJavaType getCharInstance() {
		
		return new FullyQualifiedJavaType("char"); 
	}
	
	public static final FullyQualifiedJavaType getCharArrayInstance() {
		
		return new FullyQualifiedJavaType("char[]"); 
	}
	
	public static final FullyQualifiedJavaType getCharacterInstance() {
		
		return new FullyQualifiedJavaType("Character"); 
	}
	public static final FullyQualifiedJavaType getCharacterArrayInstance() {
		
		return new FullyQualifiedJavaType("Character[]"); 
	}
	
	public static final FullyQualifiedJavaType getByteInstance() {
		
		return new FullyQualifiedJavaType("Byte"); 
	}
	
	public static final FullyQualifiedJavaType getByteArrayInstance() {
		
		return new FullyQualifiedJavaType("Byte[]"); 
	}
	
	public static final FullyQualifiedJavaType getLongInstance() {
		
		return new FullyQualifiedJavaType("Long"); 
	}
	
	public static final FullyQualifiedJavaType getLongArrayInstance() {
		
		return new FullyQualifiedJavaType("Long[]"); 
	}
	
	public static final FullyQualifiedJavaType getBooleanInstance() {
		
		return new FullyQualifiedJavaType("Boolean"); 
	}

	public static final FullyQualifiedJavaType getBoolInstance() {
		
		return new FullyQualifiedJavaType("boolean"); 
	}
	
	
	public static final FullyQualifiedJavaType getBooleanArrayInstance() {	
		return new FullyQualifiedJavaType("Boolean[]"); 
	}
}

