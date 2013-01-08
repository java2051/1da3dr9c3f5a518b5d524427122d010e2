package com.honey.compilation.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.compiler.CompilationElement;
import com.honey.core.compiler.CompilationException;
import com.honey.core.compiler.IndentSpace;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

/**
 * java 文件编译的虚类
 * 
 * @author Administrator
 *
 */
public abstract class AbstractJavaCompilation implements JavaCompilation , CompilationElement{

	/** java文件注释内容 */
	private List<String> fileComments;

	/** java文件尾部注释 */
	private List<String> afterBodyComments;
	
	/** java类注释内容 */
    private List<String> classComments;
	
    /** java类导入类库 */
    private Set<FullyQualifiedJavaType> importedTypes;
    
    /** java类静态导入类库 */
    private Set<String> staticImports;
    
    /** interface 接口类(java是多实现) */
    private Set<FullyQualifiedJavaType> superInterfaceTypes;
    
    /** 自身名称 */
    private FullyQualifiedJavaType type;
    
    /** extends接口类(java是单继承) */
    private FullyQualifiedJavaType superClass;
    
    /** 范型 */
    private FullyQualifiedJavaType generic;
    
    /** 方法 */
    private Set<Method> methods;

    /** 属性 */
    private List<Field> fields;
    
    /** 静态块 */
    private List<Block> blocks;
    
    /** 类注解 */
    private List<String> annotations;

	/** 内部枚举 */
    private List<InnerEnum> innerEnums;
    
    /** 内部类 */
    private List<InnerClass> innerClasses;
    
    /** 类的修饰 */
    private ClassDecoration classDecoration;
    
    /** 修饰规则 */
    private DecorationRule decorationRule ;
    
    /**
     * 构造函数
     * @param type 类的名称
     * @throws CompilationException
     */
    public AbstractJavaCompilation(String type) throws CompilationException{
    	this();
    	this.type = new FullyQualifiedJavaType(type);
    }
    
    /**
     * 构造函数
     * @param type 类的名称
     * @throws CompilationException
     */
    public AbstractJavaCompilation(FullyQualifiedJavaType type) throws CompilationException{
    	this();
    	this.type = type;
    }
    
    /**
     * 默认的构造函数
     * @throws CompilationException
     */
	private AbstractJavaCompilation() throws CompilationException{
		fileComments = new ArrayList<String>();
		afterBodyComments = new ArrayList<String>();
        classComments = new ArrayList<String>();
        innerEnums= new ArrayList<InnerEnum>();
        innerClasses= new ArrayList<InnerClass>();
        
        importedTypes = new TreeSet<FullyQualifiedJavaType>();
        staticImports = new TreeSet<String>();
        superInterfaceTypes = new LinkedHashSet<FullyQualifiedJavaType>();
        methods = new LinkedHashSet<Method>();
        fields = new ArrayList<Field>();
        blocks = new ArrayList<Block>();
        annotations = new ArrayList<String>();
        setClassDecoration(new ClassDecoration(JavaVisibility.PUBLIC));
        decorationRule = new NullDecorationRule();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.core.dom.Compilation#addFileComment(java.lang.String[])
	 */
	@Override
    public void addFileComment(String ...comments) {
    	for(String comment :comments )
    		if( comment != null )this.fileComments.add( comment );
    }
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.core.compiler.Compilation#addAfterBodyComment(java.lang.String[])
	 */
	@Override
	public void addAfterBodyComment(String ...comments) {
    	for(String comment :comments )
    		if( comment != null )this.afterBodyComments.add( comment );
    }
	
	/**
	 * 
	 * @param comments
	 */
	public void addFileComment(List<String> comments) {
		this.fileComments.addAll(comments);
    }
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.JavaCompilation#addClassComment(java.lang.String[])
	 */
    @Override
    public void addClassComment(String ...comments) {
    	for(String comment :comments )
    		if( comment != null )this.classComments.add( comment );
    }
	
    /**
     * 
     * @param comments
     */
    public void addClassComment(List<String> comments) {
    	this.classComments.addAll(comments);
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.core.dom.Compilation#getFileComments()
     */
    @Override
    public List<String> getFileComments() {
        return fileComments;
    }
    
    
    public List<String> getAfterBodyComments() {
        return this.afterBodyComments;
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#getClassComments()
     */
    @Override
    public List<String> getClassComments() {
        return classComments;
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#getImportedTypes()
     */
    @Override
    public Set<FullyQualifiedJavaType> getImportedTypes() {
    	return importedTypes ;
    }
    
    
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#addImportedType(com.honey.core.types.FullyQualifiedJavaType[])
     */
    @Override
    public void addImportedType(FullyQualifiedJavaType ...importedTypes) {
    	for(FullyQualifiedJavaType importedType : importedTypes )
    		if( importedType != null) addImportedType(importedType);
    }
    
    /**
     * 
     * @param importedTypes
     */
    public void addImportedType(Set<FullyQualifiedJavaType> importedTypes) {
    	this.importedTypes.addAll( importedTypes ) ;
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#addImportedType(java.lang.String[])
     */
    @Override
	public void addImportedType(String... importedTypes) throws CompilationException {
		for(String importedType : importedTypes){
			if (StringUtility.stringHasValue(importedType))
				addImportedType(new FullyQualifiedJavaType(importedType));
		}
	}
    
    /**
     * 添加导入包
     * @param importedType
     */
    public void addImportedType(FullyQualifiedJavaType importedType) {
        if (importedType != null &&
        		importedType.isExplicitlyImported()
                && !importedType.getPackageName().equals(type.getPackageName())) {
            importedTypes.add(importedType);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#getStaticImports()
     */
    @Override
    public Set<String> getStaticImports() {
        return staticImports;
    }

    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#addStaticImport(java.lang.String[])
     */
    @Override
    public void addStaticImport(String ...staticImports) {
        for(String staticImport : staticImports){
        	if (StringUtility.stringHasValue( staticImport )){
        		this.staticImports.add(staticImport);
        	}
        }
    }

    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#addStaticImports(java.util.Set)
     */
    @Override
    public void addStaticImports(Set<String> staticImports) {
        this.staticImports.addAll(staticImports);
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#getSuperInterfaceTypes()
     */
    @Override
    public Set<FullyQualifiedJavaType> getSuperInterfaceTypes() {
        return superInterfaceTypes;
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#addSuperInterface(com.honey.core.types.FullyQualifiedJavaType[])
     */
    @Override
    public void addSuperInterface(FullyQualifiedJavaType ...superInterfaces) {
    	for(  FullyQualifiedJavaType  superInterface : superInterfaces) 
    		if(superInterface != null )this.superInterfaceTypes.add(superInterface);
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#addSuperInterface(java.lang.String[])
     */
    @Override
    public void addSuperInterface(String ...superInterfaces) {
    	for(  String  superInterface : superInterfaces) 
    		if(StringUtility.stringHasValue(superInterface) )this.superInterfaceTypes.add(new FullyQualifiedJavaType( superInterface));
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#setSuperClass(com.honey.core.types.FullyQualifiedJavaType)
     */
    @Override
    public void setSuperClass(FullyQualifiedJavaType superClass) {
        /*if(superClass !=null )*/
        this.superClass = superClass;
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#setSuperClass(java.lang.String)
     */
    @Override
    public void setSuperClass(String superClassType) {
        if(StringUtility.stringHasValue(superClassType) ) this.superClass = new FullyQualifiedJavaType(superClassType);
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#getSuperClass()
     */
	@Override
	public FullyQualifiedJavaType getSuperClass() throws CompilationException {
		
		return superClass;
	}
    
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.JavaCompilation#getFields()
	 */
    @Override
    public List<Field> getFields() {
        return fields;
    }

    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#addField(com.honey.compilation.java.Field[])
     */
    @Override
    public void addField(Field ...fields) {
    	if( fields == null )return ;
    	for( Field field : fields)
    		if(field != null)this.fields.add(field);
    }
    
    /**
     * 
     * @param fields
     */
    public void addField(List<Field> fields) {
    	this.fields.addAll(fields);
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#getMethods()
     */
    @Override
    public Set<Method> getMethods() {
        return methods;
    }
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#addMethod(com.honey.compilation.java.Method[])
     */
    @Override
	public void addMethod(Method... methods) {
    	if(methods == null ) return ;
		for(Method method : methods)
			if(method != null) this.methods.add(method);
	}
   
    /**
     * 
     * @param methods
     */
    public void addMethod(Set<Method> methods) {
		 this.methods.addAll(methods);
	}
    
    
    public List<Block> getStaticBlocks() {
        return blocks;
    }
   
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#addStaticBlocks(com.honey.compilation.java.Block[])
     */
    @Override
    public void addStaticBlocks(Block ...blocks) {
        for(Block block : blocks)
        	if( block!= null )this.blocks.add(block);
    }
    
    /**
     * 
     * @param blocks
     */
    public void addStaticBlocks(List<Block> blocks) {
    	this.blocks.addAll(blocks);
    }
    
    /**
     * 获取泛型的类型
     * @return
     */
    public FullyQualifiedJavaType getGeneric() {
		return generic;
	}
    
    /*
     * (non-Javadoc)
     * @see com.honey.compilation.java.JavaCompilation#setGeneric(com.honey.core.types.FullyQualifiedJavaType)
     */
    @Override
	public void setGeneric(FullyQualifiedJavaType generic) {
		if( generic != null ) this.generic = generic;
	}
	
    /**
     * 获取注解
     * @return
     */
	public List<String> getAnnotations() {
        return annotations;
    }

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.JavaCompilation#addAnnotation(java.lang.String[])
	 */
	@Override
    public void addAnnotation(String ...annotations) {
		for( String  annotation : annotations)
    		if (StringUtility.stringHasValue(annotation) ) this.annotations.add(annotation);
    }
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.JavaCompilation#getType()
	 */
	@Override
	public FullyQualifiedJavaType getType() {
		
		return type;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.JavaCompilation#setType(com.honey.core.types.FullyQualifiedJavaType)
	 */
	@Override
	public void setType( FullyQualifiedJavaType type) throws CompilationException {
		this.type =type ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.JavaCompilation#getClassDecoration()
	 */
	@Override
	public ClassDecoration getClassDecoration() {
		if(classDecoration == null){
			classDecoration = new ClassDecoration();
		}
		return classDecoration;
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.JavaCompilation#setClassDecoration(com.honey.compilation.java.ClassDecoration)
	 */
	@Override
	public void setClassDecoration(ClassDecoration classDecoration) throws CompilationException {
		
		this.classDecoration = classDecoration ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.JavaCompilation#getInnerEnums()
	 */
	@Override
	public List<InnerEnum> getInnerEnums(){
		return this.innerEnums;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.JavaCompilation#addInnerEnum(com.honey.compilation.java.InnerEnum[])
	 */
	@Override
	public void addInnerEnum(InnerEnum ...innerEnums){
		for(InnerEnum innerEnum :  innerEnums){
			if(innerEnum != null ){
				this.innerEnums.add(innerEnum);
			}
		}
	}
	
	/**
	 * 
	 * @param innerEnums
	 * @throws CompilationException
	 */
	public void addInnerEnum(List<InnerEnum> innerEnums){
		this.innerEnums.addAll(innerEnums);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.JavaCompilation#getInnerClasses()
	 */
	@Override
    public List<InnerClass> getInnerClasses() {
		return innerClasses;
	}
    
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.JavaCompilation#addInnerClass(com.honey.compilation.java.InnerClass[])
	 */
	@Override
	public void addInnerClass(InnerClass ...innerClasses) {
		for(InnerClass innerClass  : innerClasses){
			if(innerClass != null){
				this.innerClasses.add( innerClass );
			}
		}
	}

	/**
	 * 
	 * @param innerClasses
	 */
	public void addInnerClass(List<InnerClass> innerClasses) {
		this.innerClasses.addAll(innerClasses);
	}
	
	/**
     * 生成java文件注释(javadoc注释)
     * @param cb CharacterBuilder
     */
	protected void appendFormattedFileComments(CharacterBuilder cb  ){
		if( this.fileComments.size()>0){
        	cb.append("/**");
        	for (String fileCommentLine : this.fileComments ) {
        		IndentSpace.newLine(cb);
        		cb.append(" * ").append(fileCommentLine);
	        }
        	IndentSpace.newLine(cb);
	        cb.append(" */");
	        IndentSpace.newLine(cb);
	        IndentSpace.newLine(cb);
        }
	}
	
	/**
	 * 追尾尾部内容
	 * @param cb
	 */
	protected void appendAfterBodyComments( CharacterBuilder cb ){
		if( this.afterBodyComments.size() > 0 ){
        	for ( String fileCommentLine : this.afterBodyComments ){
        		IndentSpace.newLine(cb);
        		cb.append("/* ")
        		.append(fileCommentLine)
        		.append(" */");
	        }
        }
	}
	
	/**
	 * 生成java类注释
	 * @param cb CharacterBuilder
	 * @param indentLevel 缩进级别
	 */
	protected void appendFormattedClassComments(CharacterBuilder cb ,int indentLevel ){
		if( getClassComments().size()>0){
			IndentSpace.spaceIndent(cb, indentLevel);
			cb.append("/**");
        	for (String classCommentLine : getClassComments()) {
        		IndentSpace.newLine(cb);
        		IndentSpace.spaceIndent(cb, indentLevel);
        		cb.append(" * ").append(classCommentLine);
	        }
        	IndentSpace.newLine(cb);
        	IndentSpace.spaceIndent(cb, indentLevel);
	        cb.append(" */");
	        IndentSpace.newLine(cb);
        }
	}
	
	/**
	 * 生成包
	 * @param cb 
	 * @throws CompilationException
	 */
	protected void appendPackageName(CharacterBuilder cb  ) throws CompilationException{
		if (StringUtility.stringHasValue(getType().getPackageName())) {
            cb.append(JavaKeyWord.PACKAGE);
            cb.append(getType().getPackageName());
            cb.append(JavaKeyWord.OPERATION_SPLIT);
            IndentSpace.newLine(cb);
            IndentSpace.newLine(cb);
        }
	}
	
	/**
	 * 生成静态导入
	 * @param cb 
	 * @throws CompilationException
	 */
	protected void appendStaticImport(CharacterBuilder cb  ) throws CompilationException{
		for (String staticImport : getStaticImports()) {
            cb.append(JavaKeyWord.IMPORT).append(JavaKeyWord.STATIC) ;
            cb.append(staticImport);
            cb.append(JavaKeyWord.OPERATION_SPLIT);
            IndentSpace.newLine(cb);
        }
        if ( getStaticImports().size() > 0) {
            IndentSpace.newLine(cb);
        }
	}
	
	/**
	 * 生成包导入
	 * @param cb
	 * @throws CompilationException
	 */
	protected void appendImport(CharacterBuilder cb  ) throws CompilationException{
		 Set<String> importStrings = JavaCompilationUtils.calculateImports(getImportedTypes());
		 for (String importString : importStrings) {
			 cb.append(importString);
			 IndentSpace.newLine(cb);
		 }
		 if (importStrings.size() > 0) {
			 IndentSpace.newLine(cb);
		 }
	}
	
	/**
	 * 生成类注解
	 * @param cb CharacterBuilder
	 * @param indentLevel 缩进级别
	 */
	protected void appendFormattedAnnotations(CharacterBuilder cb, int indentLevel) {
        for (String annotation : annotations) {
        	annotation = annotation.trim();
        	if( !annotation.startsWith("@")){
        		annotation ="@"+annotation;
        	}
        	IndentSpace.spaceIndent(cb, indentLevel);
            cb.append(annotation);
            IndentSpace.newLine(cb);
        }
    }
	
	/**
	 * 生成类的修饰
	 * @param cb
	 * @param indentLevel 缩进级别
	 * @throws CompilationException
	 */
	protected void appendJavaClassDecoration(CharacterBuilder cb, int indentLevel) throws CompilationException {
		cb.append(getClassDecoration().compiledContent(indentLevel));
	}
	
	/**
	 * 生成类的名称
	 * @param cb
	 * @param indentLevel 缩进级别
	 * @throws CompilationException
	 */
	protected void appendType(CharacterBuilder cb, int indentLevel) throws CompilationException {
        cb.append(getType().getFullName()).append(JavaKeyWord.OPERATION_SPACE);
	}

	/**
	 * 生成类范型
	 * @param cb
	 * @param indentLevel 缩进级别
	 * @throws CompilationException
	 */
	protected void appendGeneric(CharacterBuilder cb, int indentLevel) throws CompilationException {
		FullyQualifiedJavaType type =  getGeneric();
		if( type != null && ! getType().isGeneric() ){
			if( !type.isGeneric() ){
				cb.append(JavaKeyWord.OPERATION_ANGLE_BRACKETS_LEFT).append(type.getFullName()).append(JavaKeyWord.OPERATION_ANGLE_BRACKETS_RIGHT);
			}else{
				cb.append(type);
			}
			cb.append(JavaKeyWord.OPERATION_SPACE);
        }
	}

	/**
	 * 生成继承父类(extends)
	 * @param cb
	 * @param indentLevel 缩进级别
	 * @throws CompilationException
	 */
	protected void appendSuperClass(CharacterBuilder cb, int indentLevel) throws CompilationException {
		if (getSuperClass() != null) {
            cb.append(JavaKeyWord.EXTENDS);
            cb.append(getSuperClass().getFullName());
			IndentSpace.space(cb, 1);
        }
	}

	/**
	 * 生成实现类(interface)
	 * @param cb
	 * @param indentLevel 缩进级别
	 * @throws CompilationException
	 */
	protected void appendSuperInterfaceTypes(CharacterBuilder cb, int indentLevel) throws CompilationException {
		if (getSuperInterfaceTypes().size() > 0) {
            cb.append(JavaKeyWord.IMPLEMENTS);
            boolean comma = false;
            for (FullyQualifiedJavaType fqjt : getSuperInterfaceTypes()) {
                if (comma) cb.append(JavaKeyWord.OPERATION_COMMA); else comma = true;
                cb.append(fqjt.getFullName());
            }
			IndentSpace.space(cb, 1);
        }
	}
	
	/**
	 * 生成字段
	 * @param cb
	 * @param indentLevel 缩进级别
	 * @throws CompilationException
	 */
	protected void appendFields(CharacterBuilder cb, int indentLevel) throws CompilationException {
		Iterator<Field> fldIter = getFields().iterator();
        while (fldIter.hasNext()) {
            IndentSpace.newLine(cb);
            Field field = fldIter.next();
            cb.append(field.compiledContent(indentLevel));
            if (fldIter.hasNext()) {
                IndentSpace.newLine(cb);
            }
        }
	}
	
	/**
	 * 生成初始化块
	 * @param cb
	 * @param indentLevel 缩进级别
	 * @throws CompilationException
	 */
	protected void appendStaticBlocks(CharacterBuilder cb, int indentLevel) throws CompilationException {
		if (getStaticBlocks().size() > 0) {
            IndentSpace.newLine(cb);
        }
        Iterator<Block> blkIter = getStaticBlocks().iterator();
        while (blkIter.hasNext()) {
            IndentSpace.newLine(cb);
            Block initializationBlock = blkIter.next();
            cb.append(initializationBlock.compiledContent(indentLevel));
        }
	}
	
	/**
	 * 
	 * @param cb
	 * @param indentLevel
	 * @param interfaceMethod 缩进级别
	 * @throws CompilationException
	 */
	protected void appendMethods(CharacterBuilder cb, int indentLevel,boolean interfaceMethod) throws CompilationException {
		if (getMethods().size() > 0) {
            IndentSpace.newLine(cb);
        }
        Iterator<Method> mtdIter = getMethods().iterator();
        while (mtdIter.hasNext()) {
            IndentSpace.newLine(cb);
            Method method = mtdIter.next();
            cb.append(method.compiledContent(indentLevel, interfaceMethod));
            if (mtdIter.hasNext()) {
                IndentSpace.newLine(cb);
            }
        }
	}
	
	/**
	 * 内部枚举
	 * @param cb
	 * @param indentLevel 缩进级别
	 * @throws CompilationException
	 */
	protected void appendInnerEnums(CharacterBuilder cb, int indentLevel) throws CompilationException {
		if (innerEnums.size() > 0) {
	        IndentSpace.newLine(cb);
	    }
	    Iterator<InnerEnum> ieIter = innerEnums.iterator();
	    while (ieIter.hasNext()) {
	        IndentSpace.newLine(cb);
	        InnerEnum innerEnum = ieIter.next();
	        cb.append(innerEnum.compiledContent(indentLevel));
	        if (ieIter.hasNext()) {
	            IndentSpace.newLine(cb);
	        }
	    }
	}
	
	/**
	 * 内部类
	 * @param cb
	 * @param indentLevel 缩进级别
	 * @throws CompilationException
	 */
	protected void appendInnerClasses(CharacterBuilder cb, int indentLevel) throws CompilationException {
		if (innerClasses.size() > 0) {
	        IndentSpace.newLine(cb);
	    }
	    Iterator<InnerClass> icIter = innerClasses.iterator();
	    while (icIter.hasNext()) {
	        IndentSpace.newLine(cb);
	        BaseClass innerClass = icIter.next();
	        cb.append(innerClass.compiledContent(indentLevel));
	        if (icIter.hasNext()) {
	            IndentSpace.newLine(cb);
	        }
	    }
	}
	
	/**
	 * 检查约束规则
	 * @param decoration
	 * @param obj
	 */
	protected void checkDecorationRule(Decoration decoration , Object obj) {
		if( obj instanceof BaseClass ){
			this.decorationRule.classDecorationRule(decoration);
		}else if( obj instanceof Field ){
			this.decorationRule.fieldDecorationRule(decoration);
		}else if( obj instanceof Method ){
			this.decorationRule.methodDecorationRule(decoration);
		}else if( obj instanceof Parameter ){
			this.decorationRule.parameterDecorationRule(decoration);
		}
	}
}
