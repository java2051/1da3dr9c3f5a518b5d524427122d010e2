
package com.honey.compilation.java;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.compiler.IndentSpace;


/**
 * 枚举类中的枚举字段
 * @author Administrator
 *
 */
public class EnumField extends JavaElement {

	/** 枚举字段名称 */
	private String name;
	
	/** 枚举字段参数 */
	private Set<Parameter> parameters;
	
	/** 枚举字段参数的值 */
	private Map<Parameter, String> values;
	
	/** 枚举字段的方法 */
	private Set<Method> methods;
	
	
	/**
	 * 构造函数
	 * @param name 枚举字段名称
	 */
	public EnumField( String name){
		this.name = name ;
		parameters = new LinkedHashSet<Parameter>();
		values = new HashMap<Parameter, String>();
		methods = new LinkedHashSet<Method>();
	}
	
	/**
	 * 构造函数
	 * @param name 枚举字段名称 
	 * @param javadoc 枚举字注释
	 */
	public EnumField( String name,String javadoc){
		this.name = name ;
		parameters = new LinkedHashSet<Parameter>();
		values = new HashMap<Parameter, String>();
		methods = new LinkedHashSet<Method>();
		
		super.addJavaDocLine(javadoc);
	}
	
	
	/**
	 * 枚举字段的获取名称
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 枚举字段参数,如果枚举字段带有参数,那么枚举类必须有构造函数,构造函数的参数必须和枚举字段参数类型一致.
	 * @param parameter 参数类型
	 * @param value 参数值
	 */
	public void addParameter(Parameter parameter , String value ) {
		if ( parameter != null && value != null ) {
			this.parameters.add(parameter);
			this.values.put(parameter, value);
		}
	}
	
	/**
	 * 添加方法
	 * @param methods
	 */
	public void addMethod(Method ...methods ){
		for(Method method : methods ){
			if(method !=  null){	
				this.methods.add(method);
			}
		}	
	}
	
	
	/**
	 * 编译
	 * @param indentLevel 缩进级别
	 * @return
	 */
	public CharacterBuilder compiledContent(int indentLevel) {
		CharacterBuilder answer = CharacterBuilderFactory.createC16StringBuilder();
		if (getJavaDocLines().size() > 0) {
			IndentSpace.newLine(answer);
			addFormattedJavaMultiDoc(answer, indentLevel);
		}
		addFormattedAnnotations(answer, indentLevel);
		IndentSpace.spaceIndent(answer, indentLevel);
		answer.append(name);
		//枚举字段参数
		if (this.parameters.size() > 0) {
			answer.append(JavaKeyWord.OPERATION_BRACKET_LEFT );
			 boolean comma = false;
			 String tmp = null;
			for(Parameter parameter : parameters ){
				if (comma) answer.append(JavaKeyWord.OPERATION_COMMA); else comma = true;
				tmp = parameter.getType().formatValue( values.get(parameter) );
				answer.append( tmp ) ;

				addFormattedComment(answer,parameter.getComment());
				tmp = null;
			}
			answer.append( JavaKeyWord.OPERATION_BRACKET_RIGHT );
			addFormattedComment(answer);
		}
		
		//枚举字段的方法
		if( this.methods.size() >0 ){
			answer.append( JavaKeyWord.OPERATION_BRACE_LEFT);
			indentLevel++;
			for(Method method : this.methods ){
				IndentSpace.newDoubleLine(answer);
				method.getDecoration().setStatic(false);
				answer.append(method.compiledContent(indentLevel));
			}
			indentLevel-- ;
			IndentSpace.newSpace(answer,  indentLevel);
			answer.append( JavaKeyWord.OPERATION_BRACE_RIGHT);
		}
		return answer;
	}

	/**
	 * 或枚举字段参数
	 * @return
	 */
	public Set<Parameter> getParameters() {
		return parameters;
	}
	
	/**
	 * 获取枚举字段的方法
	 * @return
	 */
	public Set<Method> getMethods() {
		return methods;
	}

	/**
	 * 枚举字段不支持任何修饰
	 */
	@Override
	public Decoration getDecoration() {
		return null;
	}

	/**
	 * 枚举字段不支持任何修饰
	 */
	@Override
	public void setDecoration(Decoration decoration) {
		
	}

}
