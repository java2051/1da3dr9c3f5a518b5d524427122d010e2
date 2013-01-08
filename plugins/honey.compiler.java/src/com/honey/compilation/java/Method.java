
package com.honey.compilation.java;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.compiler.IndentSpace;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

/**
 * java方法
 * @author Administrator
 *
 */
public class Method extends JavaElement {
	
	/** 方法名称 */
	private String name;
	
	/** 是否强制使用基础类型 */
    boolean isBaseType =false ;
	
    /** 方法代码 */
    private List<Block> bodyLines;
    
    /** 传入参数 */
    private Set<Parameter> parameters;

    /** 声明异常 */
    private Set<FullyQualifiedJavaType> exceptions;

    /** 返回值类型  */
    private FullyQualifiedJavaType returnType;

    /** 是否编译过 */
    private boolean isNotCompilation = true ;
    
    /**
     * 构造函数
     * @param name 方法名称
     */
    public Method(String name) {
        super();
        this.bodyLines = new ArrayList<Block>();
        this.parameters = new LinkedHashSet<Parameter>();
        this.exceptions = new LinkedHashSet<FullyQualifiedJavaType>();
        this.name = name;
        setDecoration(new MethodDecoration(JavaVisibility.PUBLIC));
    }

    /**
     * 构造函数
     * @param name 方法名称
     * @param constructor 是否是构造函数
     */
    public Method(String name,boolean constructor) {
        this(name);
        this.getDecoration().setConstructor(constructor);
    }
    
    /**
     * 构造函数
     * @param name 方法名称 
     * @param decoration 方法修饰
     */
    public Method(String name , MethodDecoration decoration) {
        this(name);
        if( decoration == null ){
        	setDecoration(new MethodDecoration(JavaVisibility.PUBLIC));
        }else{
        	setDecoration(decoration);
        }
    }

    /**
     * 构造函数
     * @param name 方法名称
     * @param returnType 返回类型
     */
    public Method(String name ,FullyQualifiedJavaType returnType) {
       this(name);
       this.returnType = returnType;
    }
    
    /**
     * 构造函数
     * @param name 方法名称
     * @param returnType 返回类型
     */
    public Method(String name ,FullyQualifiedJavaType returnType,MethodDecoration decoration) {
        this(name, returnType);
        if( decoration == null ){
        	setDecoration(new MethodDecoration(JavaVisibility.PUBLIC));
        }else{
        	setDecoration(decoration);
        }
    }
    
    /**
     * 构造函数
     * @param name 方法名称
     * @param returnType 返回类型
     * @param decoration 修饰
     * @param javadoc  注释
     */
    public Method(String name ,FullyQualifiedJavaType returnType,MethodDecoration decoration,String javadoc ) {
    	this(name, returnType, decoration);
    	addJavaDocLine(javadoc);
    }
    
    /**
     * 是否强制使用基础类型
     * @return
     */
    public boolean isBaseType() {
		return isBaseType;
	}

    /**
     * 是否强制使用基础类型
     * @param isBaseType
     * @return
     */
	public Method setBaseType(boolean isBaseType) {
		this.isBaseType = isBaseType;
		 return this;
	}

	/**
	 * 代码块
	 * @return
	 */
    public List<Block> getBodyLines() {
        return bodyLines;
    }

    /**
     * 添加方法的实现代码
     * @param lines
     * @return
     */
    public Method addBodyLine(String ...lines) {
    	for(String line : lines ){
	    	if(StringUtility.stringHasValue(line)){
	    		if( bodyLines.size()==0 ){
	    			bodyLines.add(new DefaultBlock(line));
	    		}else{
	    			Block lastBlock = bodyLines.get(bodyLines.size() - 1 ) ;  
	    			if( lastBlock instanceof DefaultBlock){
	    				lastBlock.addBodyLine(line);
	    			}else{
	    				bodyLines.add(new DefaultBlock(line));
	    			}
	    		}
	    	}
    	}
        return this;
    }
    
    /**
     * 添加代码快
     * @param blocks 代码块
     * @return
     */
    public Method addBlocks(Block ...blocks){
    	for(Block block : blocks ){
    		if(block != null)
    			bodyLines.add(block);
    	}
    	return this;
    }
    
    /**
     * 添加参数注释
     */
    private void addParameterDoc(){
    	StringBuilder sb = new StringBuilder();
    	for(Parameter parameter : parameters){
    		sb.setLength(0);
    		sb.append("@param  ")
    		.append(parameter.getName() )
    		.append(" {@code   ");
    		if(parameter.isBaseType()){
    			sb.append(parameter.getType().getBaseShortName());
    		}else{
    			sb.append(parameter.getType());
    		}
    		sb.append("} ")
    		.append(parameter.getJavadoc()== null ? "" : parameter.getJavadoc());
    		;
    		this.addJavaDocLine(sb.toString()) ;
    	}
    	sb.setLength(0);
    	if(!((MethodDecoration)getDecoration()).isConstructor()){
	    	sb.append("@return " );
	    	if(getReturnType() != null){
	    		sb.append(JavaKeyWord.OPERATION_SPACE).append(getReturnType()) ;
	    	}
    	}
    	this.addJavaDocLine(sb.toString()) ;
    	//see
    	for(Parameter parameter : parameters){
    		if(! parameter.getType().isPrimitive() ){
    			sb.setLength(0);
    			sb.append("@see    ");
    			sb.append(parameter.getType());
    			this.addJavaDocLine(sb.toString()) ;
    		}
    	}
    }

    /**
     * 添加抛出的异常注释
     */
    private void addThrowsExceptionDoc(){
    	StringBuilder sb = new StringBuilder();
    	for(FullyQualifiedJavaType exception : this.exceptions ){
    		sb.setLength(0);
    		sb.append("@exception  {@code ").append(exception.getFullyQualifiedName()).append("}");
    		this.addJavaDocLine(sb.toString()) ;
    	}
    }
    
    
    /**
     * 编译
     * @param indentLevel 缩进级别
     * @return
     */
    @Override
    public CharacterBuilder compiledContent(int indentLevel) {
    	return compiledContent(indentLevel, false);
    }
    
    /**
     * 编译
     * @param indentLevel 缩进级别
     * @param interfaceMethod 是否是接口方法声明
     * @return
     */
    public CharacterBuilder compiledContent(int indentLevel, boolean interfaceMethod) {
    	CharacterBuilder answer = CharacterBuilderFactory.createC16StringBuilder();
        if( isNotCompilation ){
        	addParameterDoc();
        	addThrowsExceptionDoc();
        	isNotCompilation = false ;
        }
        
        addFormattedJavaMultiDoc(answer, indentLevel);
    	addFormattedAnnotations(answer, indentLevel);
        //方法修饰
        if (interfaceMethod) {
        	IndentSpace.spaceIndent(answer, indentLevel);
        	answer.append(getDecoration().getVisibility());
        }else{
        	answer.append(getDecoration().compiledContent(indentLevel));
        }
        if(getDecoration().isAbstract() )
        	interfaceMethod =true;

        //返回值
        FullyQualifiedJavaType returnType = getReturnType() ;
        if ( !getDecoration().isConstructor() ) {
            if (returnType == null) {
                answer.append( JavaKeyWord.VOID ); 
            } else {
            	if(isBaseType && returnType.isPrimitive()){
            		answer.append(returnType.getBaseShortName());
            	}else{
            		answer.append(returnType.getFullName());
            	}
            	answer.append(JavaKeyWord.OPERATION_SPACE);
            }
        }
        
        //参数
        answer.append(getName());
        answer.append(JavaKeyWord.OPERATION_BRACKET_LEFT);
        boolean comma = false;
        for (Parameter parameter : getParameters()) {
            if (comma)answer.append(JavaKeyWord.OPERATION_COMMA); else comma = true;
            answer.append(parameter.compiledContent(0));
        }
        answer.append(JavaKeyWord.OPERATION_BRACKET_RIGHT);

        //声明抛出异常
        if (getExceptions().size() > 0) {
            answer.append(JavaKeyWord.THROWS); 
            comma = false;
            for (FullyQualifiedJavaType exception : getExceptions()) {
                if (comma)answer.append( JavaKeyWord.OPERATION_COMMA ); else comma = true;
                answer.append(exception.getShortName());
            }
        }
        //方法主体
        if( !interfaceMethod ){
	            answer.append(JavaKeyWord.OPERATION_BRACE_LEFT); 
	            indentLevel++;
	            
	            ListIterator<Block> listIter = bodyLines.listIterator();
	            while (listIter.hasNext()) {
	            	answer.append( listIter.next().compiledContent(indentLevel) );
	            }
	            //如果方法体没有任何内容,自动加入默认的代码.
	            if (bodyLines.size() == 0) {
	            	IndentSpace.newSpace(answer, indentLevel);
	            	answer.append("// TODO Auto-generated method stub");
	            	if( getReturnType() == null ){
	            		IndentSpace.newDoubleLine(answer);
	            		IndentSpace.spaceIndent(answer, indentLevel);
		            	answer.append(JavaKeyWord.RETURN).append(JavaKeyWord.OPERATION_SPLIT);
	            	}else{
	            		IndentSpace.newDoubleLine(answer);
		            	IndentSpace.spaceIndent(answer, indentLevel);
		            	answer.append(JavaKeyWord.RETURN).append(getReturnType().getInitializationValue()).append(JavaKeyWord.OPERATION_SPLIT);
	            	}
	            }
	            indentLevel-- ;
	            IndentSpace.newSpace(answer, indentLevel);
	            answer.append(JavaKeyWord.OPERATION_BRACE_RIGHT);
        }else{
        	 answer.append(JavaKeyWord.OPERATION_SPLIT );
        }
        return answer;
    }

    /**
     * 获取方法名称
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设定方法名称
     * @param name 方法名称
     * @return
     */
    public Method setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取传入参数
     * @return
     */
    public Set<Parameter> getParameters() {
        return parameters;
    }

    /**
     * 添加传入参数
     * @param parameters
     * @return
     */
    public Method addParameter(Parameter ...parameters) {
    	for(Parameter parameter: parameters ){
    		if(parameter != null )
    			this.parameters.add(parameter);
    	}
    	 return this;
    }
    
    /**
     * 添加传入参数
     * @param parameterName 参数名称
     * @param parameterType 参数类型
     * @return
     */
    public Method addParameter(String parameterName ,String parameterType ) {
    	if( StringUtility.stringHasValue(parameterName) && StringUtility.stringHasValue(parameterType)){
    		addParameter(new Parameter(new FullyQualifiedJavaType(parameterType),parameterName));
    	}
    	return this;
    }
    
    /**
     * 添加传入参数
     * @param parameterName 参数名称
     * @param parameterType 参数类型
     * @param javadoc 注释
     * @return
     */
    public Method addParameter(String parameterName ,String parameterType,String javadoc ) {
    	if( StringUtility.stringHasValue(parameterName) && StringUtility.stringHasValue(parameterType)){
    		Parameter parameter = new Parameter(new FullyQualifiedJavaType(parameterType),parameterName);
    		parameter.addJavaDocLine(javadoc);
    		addParameter(parameter);
    	}
    	return this;
    }
    
    /**
     * 添加传入参数
     * @param parameterType 参数类型
     * @param parameterName 参数名称
     * @return
     */
    public Method addParameter(FullyQualifiedJavaType parameterType,String parameterName) {
    	this.parameters.add(new Parameter(parameterType,parameterName));
    	 return this;
    }
    
    /**
     * 获取返回类型
     * @return
     */
    public FullyQualifiedJavaType getReturnType() {
        return returnType;
    }

   /**
    * 设定返回类型
    * @param returnType
    * @return
    */
    public Method setReturnType(FullyQualifiedJavaType returnType) {
    	if(returnType != null) 
    		this.returnType = returnType;
        return this;
    }

    /**
     * 设定返回类型
     * @param returnType
     * @return
     */
    public Method setReturnType(String returnType) {
    	if(StringUtility.stringHasValue(returnType))
    		this.returnType =new FullyQualifiedJavaType( returnType );
    	return this;
    }
    
    /**
     * 获取异常声明
     * @return
     */
    public Set<FullyQualifiedJavaType> getExceptions() {
        return exceptions;
    }

    /**
     * 添加异常声明
     * @param exceptions
     * @return
     */
    public Method addException(FullyQualifiedJavaType ...exceptions) {
    	for( FullyQualifiedJavaType exception : exceptions  ){
    		if(exception != null)
    			this.exceptions.add(exception);
    	}
        return this;
    }
    
    /**
     * 添加异常声明
     * @param exceptions
     * @return
     */
    public Method addException(String ...exceptions) {
    	for(String exception:  exceptions){
    		if (StringUtility.stringHasValue(exception) )
    			this.exceptions.add(new FullyQualifiedJavaType(exception));
    		}
        return this;
    }
    
    /**
     * 获取方法修饰
     */
    public MethodDecoration getDecoration(){
    	return (MethodDecoration)super.getDecoration();
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaElement#setDecoration(com.honey.compilation.java.Decoration)
     */
	@Override
	public void setDecoration(Decoration decoration) {
		if(decoration instanceof MethodDecoration ){
			super.setDecoration(decoration);
		}else{
			throw new RuntimeException("方法必须使用MethodDecoration");
		}
	}
	
	/**
	 * 添加方法修饰
	 * @param decoration
	 */
	public void setDecoration(MethodDecoration decoration) {
		super.setDecoration(decoration);
	}
	
	/**
	 * java 重载函数要求是: 函数名称相同参数类型不同或者参数个数不同.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Method ){
			Method overloaded = (Method)obj;
			StringBuilder self = new StringBuilder();
			self.append(this.name);
			for(Parameter parameter : this.parameters ){
				self.append(parameter.getType());
			}
			StringBuilder same= new StringBuilder();
			same.append(overloaded.name);
			for( Parameter parameter : overloaded.getParameters() ){
				same.append(parameter.getType());
			}
			return self.toString().equals(same.toString() );
		}else{
			return super.equals(obj);
		}
	}

	@Override
	public int hashCode() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		for(Parameter parameter : parameters ){
			sb.append(parameter.getType());
		}
		return sb.toString().hashCode();
	}
}
