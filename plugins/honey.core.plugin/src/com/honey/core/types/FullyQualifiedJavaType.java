package com.honey.core.types;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import com.honey.compilation.java.JavaKeyWord;
import com.honey.core.utils.StringUtility;

/**
 * java 类型封装. 子类的写法: com.packagename.ParentClassName$ChildClassName
 * @author Administrator
 *
 */
public class FullyQualifiedJavaType implements Comparable<FullyQualifiedJavaType> {

	/** java类型类的名称,不包含名称. */
	private String simpleName;

	/** 完整的java类型名称,包含包的名称和类的名称 */
	private String baseQualifiedName;
	
	/**当前java类是否需要导入包.(java.lang包中的所有内容不需要导入) true:是 false:否*/
	private boolean explicitlyImported;
	
	/**包名称*/
	private String packageName;
	
	/** 数组类型 */
	private String array;
	
	/**是否有原始类型: true:是 true:否*/
	private PrimitiveType primitive;
	
	/** 泛型类型 */
	private List<FullyQualifiedJavaType> typeArguments;
	
	/**是否是泛型类型*/
	private boolean generic ;
	
	/**在泛型中是否有通配符*/
	private boolean wildcardType;
	
	private boolean boundedWildcard;
	private boolean extendsBoundedWildcard;
	
	/** 类型初始值 */
	private String initializationValue;
	
	/**
	 * 字符串转换成java对象封装
	 * @param fullTypeSpecification
	 */
	public FullyQualifiedJavaType(String fullTypeSpecification) {
		typeArguments = new ArrayList<FullyQualifiedJavaType>();
		parse(fullTypeSpecification);
		initializationValue = initializationValue();
	}
	
	/**
	 * 字符串转换成java对象封装
	 * @param packageName 包名称
	 * @param className 类名称
	 */
	public FullyQualifiedJavaType(String packageName , String className) {
		String fullTypeSpecification = packageName==null?"":packageName ;
		if(StringUtility.stringHasValue( fullTypeSpecification ) && packageName.charAt(fullTypeSpecification.length() - 1) != '.'){
			fullTypeSpecification +=".";
		}
		if(className.charAt(0)=='.' ){
			fullTypeSpecification = fullTypeSpecification + className.substring(1);
		}else{
			fullTypeSpecification = fullTypeSpecification + className;
		}
		
		typeArguments = new ArrayList<FullyQualifiedJavaType>();
		parse(fullTypeSpecification);
		initializationValue = initializationValue();
	}
	
	/**
	 * java class对象转换成java对象封装
	 * @param fullTypeSpecification
	 */
	public FullyQualifiedJavaType(Class<?> fullTypeSpecification) {
		super();
		typeArguments = new ArrayList<FullyQualifiedJavaType>();
		String clazzName = fullTypeSpecification.getName();
		if(clazzName.startsWith("java.lang")){
			clazzName = clazzName.substring(10, clazzName.length());
		}
		parse(clazzName);
		initializationValue = initializationValue();
	}

	/**
	 * 解析类型
	 * @param fullTypeSpecification
	 */
	protected void parse(String fullTypeSpecification) {
		String spec = fullTypeSpecification.trim();

		if (spec.startsWith("?")) { 
			wildcardType = true;
			spec = spec.substring(1).trim();
			if (spec.startsWith("extends ")) { 
				boundedWildcard = true;
				extendsBoundedWildcard = true;
				spec = spec.substring(8);
			} else if (spec.startsWith("super ")) { 
				boundedWildcard = true;
				extendsBoundedWildcard = false;
				spec = spec.substring(6);
			} else {
				boundedWildcard = false;
			}
			parse(spec);
		} else {
			int index = fullTypeSpecification.indexOf('<');
			if (index == -1) {
				simpleParse(fullTypeSpecification);
			} else {
				simpleParse(fullTypeSpecification.substring(0, index));
				genericParse(fullTypeSpecification.substring(index));
				generic= true;
			}
		}
	}

	private void simpleParse(String typeSpecification) {
		baseQualifiedName = typeSpecification.trim();
		if (baseQualifiedName.contains(".")) { 
			packageName = getPackage(baseQualifiedName);
			simpleName = baseQualifiedName
					.substring(packageName.length() + 1);
			int index = simpleName.lastIndexOf('.');
			if (index != -1) {
				simpleName = simpleName.substring(index + 1);
			}

			if ("java.lang".equals(packageName)) { 
				explicitlyImported = false;
			} else {
				explicitlyImported = true;
			}
		} else {
			simpleName = baseQualifiedName;
			explicitlyImported = false;
			packageName = ""; 
		}
		
		int start = simpleName.indexOf('[');
		if(start>-1 ){
			int end = simpleName.indexOf(']');
			if(end>-1 ){
				array = simpleName.substring(start,end+1);
			}
		}
		this.primitive = PrimitiveType.getPrimitiveType(simpleName);
	}

	/**
	 * 
	 * @param genericSpecification
	 */
	private void genericParse(String genericSpecification) {
		int lastIndex = genericSpecification.lastIndexOf('>');
		if (lastIndex == -1) {
			throw new RuntimeException(); 
		}
		String argumentString = genericSpecification.substring(1, lastIndex);
		// need to find "," outside of a <> bounds
		StringTokenizer st = new StringTokenizer(argumentString, ",<>", true); 
		int openCount = 0;
		StringBuilder sb = new StringBuilder();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if ("<".equals(token)) {
				sb.append(token);
				openCount++;
			} else if (">".equals(token)) { 
				sb.append(token);
				openCount--;
			} else if (",".equals(token)) { 
				if (openCount == 0) {
					typeArguments
							.add(new FullyQualifiedJavaType(sb.toString()));
					sb.setLength(0);
				} else {
					sb.append(token);
				}
			} else {
				sb.append(token);
			}
		}

		if (openCount != 0) {
			throw new RuntimeException(); 
		}

		String finalType = sb.toString();
		if (StringUtility.stringHasValue(finalType)) {
			typeArguments.add(new FullyQualifiedJavaType(finalType));
		}
	}

	/**
	 * 获取包名称
	 * @param baseQualifiedName
	 * @return
	 */
	private String getPackage(String baseQualifiedName) {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(baseQualifiedName, "."); 
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (Character.isUpperCase(s.charAt(0))) {
				break;
			} else {
				if (sb.length() > 0) {
					sb.append('.');
				}
				sb.append(s);
			}
		}

		if (baseQualifiedName.equals(sb.toString())) {
			sb.setLength(0);
			int i = baseQualifiedName.lastIndexOf('.');
			if (i != -1) {
				sb.append(baseQualifiedName.substring(0, i));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 返回类型全部名称(包括继承父类,被实现的接口类,泛型) 例如构造List类型:<br/>
	 * FullyQualifiedJavaType type =new FullyQualifiedJavaType("java.util.List&lt;Object&gt;"); <br />
	 * <ul>
	 * 	<li>type.getShortName()返回List</li>
	 *  <li>type.getLongName()返回java.util.List</li>
	 *  <li>type.getFullName()返回List&lt;Object&gt;</li>
	 *  <li>type.getPackageName()返回java.util</li>
	 *  <li><b>type.getFullyQualifiedName()返回java.util.List&lt;Object&gt;</b></li>
	 *  <li>type.getBaseShortName()针对于8种基础类型有效,其它类型返回getShortName()的值</li>
	 * </ul>
	 * @return
	 */
	public String getFullyQualifiedName() {
		StringBuilder sb = new StringBuilder();
		if (wildcardType) {
			sb.append('?');
			if (boundedWildcard) {
				if (extendsBoundedWildcard) {
					sb.append(" extends "); 
				} else {
					sb.append(" super "); 
				}

				sb.append(baseQualifiedName);
			}
		} else {
			sb.append(baseQualifiedName);
		}

		if (typeArguments.size() > 0) {
			boolean coment = true;
			sb.append('<');
			for (FullyQualifiedJavaType fqjt : typeArguments) {
				if (coment) {
					coment = false;
				} else {
					sb.append(","); 
				}
				sb.append(fqjt.getFullyQualifiedName());

			}
			sb.append('>');
		}

		return sb.toString();
	}

	/**
	 * 获取需要导入的包
	 * @return
	 */
	public List<String> getImportList() {
		List<String> answer = new ArrayList<String>();
		if (isExplicitlyImported()) {
			int index = simpleName.indexOf('.');
			if (index == -1) {
				answer.add(baseQualifiedName);
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append(packageName)
				.append('.')
				.append(simpleName.substring(0, index));
				answer.add(sb.toString());
			}
		}
		
		for (FullyQualifiedJavaType fqjt : typeArguments) {
			answer.addAll(fqjt.getImportList());
		}
		return answer;
	}
	
	/**
	 * 获取类的短名称,不包含泛型也不包含包名称.例如构造List类型:<br/>
	 * FullyQualifiedJavaType type =new FullyQualifiedJavaType("java.util.List&lt;Object&gt;"); <br />
	 * <ul>
	 * 	<li><b>type.getShortName()返回List</b></li>
	 *  <li>type.getLongName()返回java.util.List</li>
	 *  <li>type.getFullName()返回List&lt;Object&gt;</li>
	 *  <li>type.getPackageName()返回java.util</li>
	 *  <li>type.getFullyQualifiedName()返回java.util.List&lt;Object&gt;</li>
	 *  <li>type.getBaseShortName()针对于8种基础类型有效,其它类型返回getShortName()的值</li>
	 * </ul>
	 * @return
	 */
	public String getShortName(){
		return simpleName;
	}
	
	/**
	 * 获取类的长名称,不包含泛型但包含包名称.例如构造List类型:<br/>
	 * FullyQualifiedJavaType type =new FullyQualifiedJavaType("java.util.List&lt;Object&gt;"); <br />
	 * <ul>
	 * 	<li>type.getShortName()返回List</li>
	 *  <li><b>type.getLongName()返回java.util.List</b></li>
	 *  <li>type.getFullName()返回List&lt;Object&gt;</li>
	 *  <li>type.getPackageName()返回java.util</li>
	 *  <li>type.getFullyQualifiedName()返回java.util.List&lt;Object&gt;</li>
	 *  <li>type.getBaseShortName()针对于8种基础类型有效,其它类型返回getShortName()的值</li>
	 * </ul>
	 * @return
	 */
	public String getLongName(){
		return new StringBuilder()
		.append(packageName)
		.append(packageName.length() == 0 ? "" : JavaKeyWord.OPERATION_DOT)
		.append(simpleName).toString();
	}
	
	/**
	 * 获取全名称,包含泛型但不包含包名称.例如构造List类型:<br/>
	 * FullyQualifiedJavaType type =new FullyQualifiedJavaType("java.util.List&lt;Object&gt;"); <br />
	 * <ul>
	 * 	<li>type.getShortName()返回List</li>
	 *  <li>type.getLongName()返回java.util.List</li>
	 *  <li><b>type.getFullName()返回List&lt;Object&gt;</b></li>
	 *  <li>type.getPackageName()返回java.util</li>
	 *  <li>type.getFullyQualifiedName()返回java.util.List&lt;Object&gt;</li>
	 *  <li>type.getBaseShortName()针对于8种基础类型有效,其它类型返回getShortName()的值</li>
	 * </ul>
	 * @return
	 */
	public String getFullName() {
		StringBuilder answer = new StringBuilder();
		if (wildcardType) {
			answer.append('?');
			if (boundedWildcard) {
				if (extendsBoundedWildcard) {
					answer.append(" extends "); 
				} else {
					answer.append(" super "); 
				}
				answer.append(simpleName);
			}
		} else {
			answer.append(simpleName);
		}
		if (typeArguments.size() > 0) {
			boolean comment = true;
			answer.append("<");
			for (FullyQualifiedJavaType fqjt : typeArguments) {
				if (comment) comment = false; else answer.append(", "); 
				answer.append(fqjt.getFullName());
			}
			answer.append(">");
		}
		String s = answer.toString();
		s = s.replace('$', '.');
		return s;
	}
	
	/**
	 * 获取类型包名称,例如构造List类型:<br/>
	 * FullyQualifiedJavaType type =new FullyQualifiedJavaType("java.util.List&lt;Object&gt;"); <br />
	 * <ul>
	 * 	<li>type.getShortName()返回List</li>
	 *  <li>type.getLongName()返回java.util.List</li>
	 *  <li>type.getFullName()返回List&lt;Object&gt;</li>
	 *  <li><b>type.getPackageName()返回java.util</b></li>
	 *  <li>type.getFullyQualifiedName()返回java.util.List&lt;Object&gt;</li>
	 *  <li>type.getBaseShortName()针对于8种基础类型有效,其它类型返回getShortName()的值</li>
	 * </ul>
	 * @return
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * 设定包名称
	 * @param packageName
	 */
	public void setPackageName(String packageName) {
		if(packageName== null )this.packageName = "";
		else this.packageName = packageName;
	}
	
	
	/**
	 * 是否是原始类型 true:是 false:否
	 * @return
	 */
	public boolean isPrimitive() {
		return primitive!=null;
	}

	/**
	 * 当前java类是否需要导入包.在java中只有java.lang中的类是不需要导入包的.
	 * 	所以只有java.lang中的类返回false.
	 * @return  true:需要导入包 false:不需要
	 */
	public boolean isExplicitlyImported() {
		return explicitlyImported;
	}

	/**
	 * 为类型添加泛型
	 * @param type
	 */
	public void addTypeArgument(FullyQualifiedJavaType type) {
		typeArguments.add(type);
	}

	
	/**
	 * 获取初始值
	 * @return
	 */
	public String getInitializationValue() {
		return initializationValue;
	}

	/**
	 * 分析初始值
	 * @return
	 */
	private String initializationValue(){
		String answer ="null" ;
		if(primitive!= null && array == null ){
			answer = primitive.defaultValue;
		}
		return answer;
	}

	/**
	 * 泛型中是否通配符
	 * @return
	 */
	public boolean isWildcardType() {
		return wildcardType;
	}
	
	/**
	 * 是否是泛型类型
	 * @return
	 */
	public boolean isGeneric() {
		return generic;
	}

	/**
	 * java有8中基础类型 char boolean int long double float byte short,这8中基础类型都有对应的类类型
	 * Character Boolean Integer Long Double Float Byte Short.类型之间的转换通过编译器自动装箱和自动拆箱完成.例如:<br />
	 * Integer i =0 ;<br />double d = new Double(0);<br />
	 * 上面的两种写法违反java的语法规则, 但是编译器自动装箱和自动拆箱过程转成:<br /> 
	 * Integer i =new Integer(0); <br />double d = (new Double(0)).doubleValue();<br />
	 * 转后的语法并不违反java的语法规则.自动装箱和自动拆箱是由编译器在编译过程中完成的对于开发者来说是不可见的.
	 * 本方法是由类类型直接转换成基础类型,例如Integer对象:<br />
	 * FullyQualifiedJavaType type =new FullyQualifiedJavaType("java.lang.Integer"); <br />
	 * <ul>
	 * 	<li>type.getShortName()返回Integer</li>
	 *  <li>type.getLongName()返回java.lang.Integer</li>
	 *  <li>type.getFullName()返回java.lang.Integer</li>
	 *  <li>type.getPackageName()返回java.lang</li>
	 *  <li>type.getFullyQualifiedName()返回java.lang.Integer</li>
	 *  <li><b>type.getBaseShortName()返回int</b></li>
	 * </ul> 
	 * <font color="red">注意:</font><br />
	 * <ul>
	 * 	<li>使用对象:
	 * 		<ul>
	 * 		<li>Character</li>
	 *  	<li>Boolean</li>
	 *  	<li>Integer</li>
	 *  	<li>Long</li>
	 *  	<li>Double</li>
	 *  	<li>Float</li>
	 *  	<li>Byte</li>
	 *  	<li>Short</li>
	 * 		</ul>
	 *  </li>
	 *  <li>String不属于基础类型,所以本法方法返回String.</li>
	 *  <li>非java的基础类型本方法返回getShortName的值</li>
	 * </ul> 
	 * 自动装箱和自动拆箱只能用于java的基础类型,其它的类型都不能使用.所以本方只是把基础类型进行转换,其它的类型返回类的名称.<br />
	 * String 不属于基础类型
	 * @return
	 */
	public String getBaseShortName() {
		String answer =null;
		if(this.primitive != null){
			answer = this.primitive.primitiveName + ( this.array == null ? "" : this.array );
		}else{
			answer = getShortName();
		}
		return answer;
	}

	/**
	 * 格式化类型的赋值, java中需要对基础类型赋值进行格式化.
	 * @param value
	 * @return
	 */
	public String formatValue(String value){
		String answer = value;
		if(value == null || value.length() ==0 ) return answer;
		if(this.primitive != null && array == null){
			answer =  this.primitive.formatValue(value);
		}else if(this.primitive != null && array != null){
			int start = value.indexOf('{');
			if( start > 0 ){
				int end = value.indexOf('}');
				if(start > 0 && end>0 && end > start + 1 ){
					String str = value.substring(start+1 , end).replaceAll(" ", "");
					StringBuilder sb = new StringBuilder().append(value.substring(0 , start) ).append("{");
					if(str.length()> 0 ){
						String []array = str.split(",");
						for(String s :  array ){
							sb .append( this.primitive.formatValue(s) ).append(",");
						}
					}
					sb.append("}");
					answer = sb.toString();
				}
			}
		}
		else{
			if(simpleName.equals("String") ){
				if( !"null".equals(value) ){
					StringBuilder sb = new StringBuilder() ;
					if(value.charAt(0) !='\"' ){
						sb. append("\"");
					}
					sb.append(value);
					if(value.charAt(value.length() -1 ) !='\"' ){
						sb. append("\"");
					}
					answer = sb.toString();
				}
			}
		}
		return answer;
	}

	/**
	 * java 的基础类型
	 * @author Administrator
	 *
	 */
	private enum PrimitiveType{

		/**  char */
		CHAR( "char","charValue()", "0"){
			public  String formatValue( String value){
				String answer = null;
				if(value.charAt(0) !='\'' ){
					answer = new String(new char[]{'\'',value.charAt(0),'\''});
				}else{
					answer = new String(new char[]{'\'',value.charAt(1),'\''});
				}
				return answer.toString();
			}
		},

		/**  boolean */
		BOOLEAN("boolean","booleanValue()","false"){
			public  String formatValue( String value){
				String answer= "false";
				try {
					int i = Integer.parseInt(value);
					if (i > 0)answer="true";
				} catch (Exception e) {
					if (StringUtility.isTrue(value)) answer="true";
				}
				return answer;
			}
		},

		/**  int */
		INT("int","intValue()","0"){
			public  String formatValue( String value){
				return value;
			}
		},
		
		/**  long */
		LONG("long","longValue()","0L"){
			public  String formatValue( String value){
				return super.formatValue(value, 'L', 'l');
			}
		},

		/**  double */
		DOUBLE("double","doubleValue()","0.0D"){
			public  String formatValue( String value){
				return super.formatValue(value, 'D', 'd');
			}
		},
		
		/** float */
		FLOAT("float","floatValue()","0.0F"){
			public  String formatValue( String value){
				return super.formatValue(value, 'F', 'f');
			}
		},
		
		/**  byte */
		BYTE("byte","byteValue()","0"){
			public  String formatValue( String value){
				String answer= "0";
				try {
					int i = Integer.parseInt(value);
					if (i > 0)answer="1";
				} catch (Exception e) {
					if (StringUtility.isTrue(value)) answer="1";
				}
				return answer;
			}
		},
		
		/**  short */
		SHORT("short","shortValue()","0"){
			public  String formatValue( String value){
				String answer = value;
				if( !answer.startsWith("(short)") ){
					answer ="(short)"+value;
				}
				return answer ;
			}
		},
		;

		/** 基础类型名称 */
		private final String primitiveName;
		
		/** 转成原始类型的方法 */
		private final String primitiveMethod ;
		
		/** 初始值 */
		private final String defaultValue ;
		
		private PrimitiveType(String primitiveName, String primitiveMethod,String defaultValue){
			this.primitiveName = primitiveName;
			this.primitiveMethod = primitiveMethod;
			this.defaultValue = defaultValue;
		}
		
		private static final PrimitiveType getPrimitiveType(String simpleName ){
			String name =simpleName.toLowerCase();
			if (name.startsWith("long"))
				return PrimitiveType.LONG;
			else if (name.startsWith("integer") || name.startsWith("int"))
				return PrimitiveType.INT;
			else if (name.startsWith("character") || name.startsWith("char"))
				return PrimitiveType.CHAR;
			else if (name.startsWith("short"))
				return PrimitiveType.SHORT;
			else if ( name.startsWith("boolean")  )
				return PrimitiveType.BOOLEAN;
			else if (name.startsWith("float"))
				return PrimitiveType.FLOAT;
			else if (name.startsWith("double"))
				return PrimitiveType.DOUBLE;
			else if (name.startsWith("byte"))
				return PrimitiveType.BYTE;
			else{
				return null;
			}
		}

		/**
		 * 格式化赋值
		 * @param value
		 * @return
		 */
		protected abstract String formatValue(String value );
		
		/**
		 * 格式化
		 * @param value 值
		 * @param upper 大写后缀
		 * @param lower 小写后缀
		 * @return
		 */
		protected String formatValue(String value ,char upper, char lower){
			String answer = value ;
			char c = value.charAt( value.length() -1 );
			if(c!=upper && c!=lower ){
				answer =value+upper;
			}
			return answer;
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(FullyQualifiedJavaType other) {
		return getFullyQualifiedName().compareTo(other.getFullyQualifiedName());
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FullyQualifiedJavaType)) {
			return false;
		}

		FullyQualifiedJavaType other = (FullyQualifiedJavaType) obj;

		return getFullyQualifiedName().equals(other.getFullyQualifiedName());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return getFullyQualifiedName().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getFullyQualifiedName();
	}
}
