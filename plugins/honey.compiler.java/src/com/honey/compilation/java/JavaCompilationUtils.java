package com.honey.compilation.java;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.compiler.CompilationException;
import com.honey.core.compiler.IndentSpace;
import com.honey.core.types.FullyQualifiedJavaType;


/**
 * java文件编译工具类
 * @author Administrator
 *
 */
public class JavaCompilationUtils {

	/**
	 * 计算需要导入的包
	 * @param importedTypes
	 * @return
	 */
	protected static Set<String> calculateImports(
			Set<FullyQualifiedJavaType> importedTypes) {
		StringBuilder sb = new StringBuilder();
		Set<String> importStrings = new TreeSet<String>();
		for (FullyQualifiedJavaType fqjt : importedTypes) {
			for (String importString : fqjt.getImportList()) {
				sb.setLength(0);
				sb.append(JavaKeyWord.IMPORT); 
				sb.append(importString);
				sb.append(JavaKeyWord.OPERATION_SPLIT);
				importStrings.add(sb.toString());
			}
		}
		return importStrings;
	}

	/**
	 * 添加单行注释
	 * @param cb CharacterBuilder 
	 * @param indentLevel 缩进级别
	 * @param javaDocLines 注释
	 */
	protected static void addFormattedJavaSingleDoc(CharacterBuilder cb, int indentLevel,List<String> javaDocLines) {
    	if(javaDocLines.size() <=0) return ;
    	for (String javaDocLine : javaDocLines) {
    		IndentSpace.newLine(cb);
    		IndentSpace.spaceIndent(cb, indentLevel);
            cb.append("// ").append(javaDocLine);
        }
    }
	
	/**
	 * 添加多行注释
	 * @param cb CharacterBuilder
	 * @param indentLevel 缩进级别
	 * @param javaDocLines 注释
	 */
	protected static void addFormattedJavaMultiDoc(CharacterBuilder cb, int indentLevel,List<String> javaDocLines) {
    	addFormattedJavaMultiDoc(cb, indentLevel, true,javaDocLines);
    }
    
    /**
     * 添加java doc注释
     * @param cb
     * @param indentLevel
     */
	protected static void addFormattedJavaMultiDoc(CharacterBuilder cb, int indentLevel, boolean newLine,List<String> javaDocLines) {
    	if(javaDocLines.size() <=0) return ;
    	IndentSpace.spaceIndent(cb, indentLevel);
    	cb.append("/**");
    	if( newLine )IndentSpace.newLine(cb);
    	for (String javaDocLine : javaDocLines) {
    		if( newLine )IndentSpace.spaceIndent(cb, indentLevel);
    		if( newLine )cb.append(" * ");
            cb.append(javaDocLine);
            if( newLine )IndentSpace.newLine(cb);
        }
    	if( newLine )IndentSpace.spaceIndent(cb, indentLevel);
    	cb.append(" */");
    	IndentSpace.newLine(cb);
    }

	/**
	 * 添加注释
	 * @param cb
	 * @param indentLevel
	 * @param javaDocLines
	 */
	protected static void addFormattedJavaDoc(CharacterBuilder cb, int indentLevel,List<String> javaDocLines) {
    	if(javaDocLines.size() <=0) return ;
    	for (String javaDocLine : javaDocLines) {
            IndentSpace.spaceIndent(cb, indentLevel);
            cb.append(javaDocLine);
            IndentSpace.newLine(cb);
        }
    }
	
    
	private static void importedTypesForFields(Set<FullyQualifiedJavaType> typeList, List<Field> fields){
		for (Field field : fields){
			if(field== null) continue;
			typeList.add(field.getType());
		}
	}
	
	private static void importedTypesForMotheds(Set<FullyQualifiedJavaType> typeList, Set<Method> methods){
		for (Method method : methods) {
			if (method != null) {
				// exception type
				typeList.addAll(method.getExceptions());

				// parameter type
				for (Parameter p : method.getParameters()) {
					typeList.add(p.getType());
				}
				// return type
				if( method.getReturnType() != null )
					typeList.add( method.getReturnType());
			}
		}
	}
	
	
	private static void importedTypesForSuperInterface(Set<FullyQualifiedJavaType> typeList, Set<FullyQualifiedJavaType> set){
		for (FullyQualifiedJavaType t : set) {
			typeList.add(t);
		}
	}
	
	private static void importedTypesForEnumField(Set<FullyQualifiedJavaType> typeList, List<EnumField>  enumFields){
		for(EnumField enumField : enumFields ){
			for(Parameter parameter : enumField.getParameters() ){
				typeList.add(parameter.getType());
			}
		}
	}

	private static void importedTypesForOther(Set<FullyQualifiedJavaType> typeList, AbstractJavaCompilation  abstractJavaClass) throws CompilationException{
		if( abstractJavaClass.getSuperClass() != null)
			typeList.add(abstractJavaClass.getSuperClass());
		
		if( abstractJavaClass.getGeneric() != null)
			typeList.add(abstractJavaClass.getGeneric());
		
	}
	
	private static void  importedTypesForInnerEnumClass( Set<FullyQualifiedJavaType> typeList, InnerEnum innerEnum) throws CompilationException{
		//导入属性
		importedTypesForFields(typeList, innerEnum.getFields());
		
		//导入方法
		importedTypesForMotheds(typeList, innerEnum.getMethods());
		
		//导入父类(interface)
		importedTypesForSuperInterface(typeList, innerEnum.getSuperInterfaceTypes());
		
		//导入import
		typeList.addAll(innerEnum.getImportedTypes());
		
		importedTypesForEnumField(typeList, innerEnum.getEnumFields());
		
		importedTypesForOther(typeList, innerEnum) ;
		
		for(BaseClass inner : innerEnum.getInnerClasses()){
			importedTypesForInnerClass(typeList, inner);
		}
		
		for(InnerEnum inner :  innerEnum.getInnerEnums()){
			importedTypesForInnerEnumClass(typeList, inner);
		}
		
	}
	
	
	private static void  importedTypesForInnerClass( Set<FullyQualifiedJavaType> typeList, BaseClass innerClass) throws CompilationException{
		importedTypesForFields(typeList, innerClass.getFields());
		
		importedTypesForMotheds(typeList, innerClass.getMethods());

		importedTypesForSuperInterface(typeList, innerClass.getSuperInterfaceTypes());
		
		//导入import
		typeList.addAll(innerClass.getImportedTypes());
		
		importedTypesForOther(typeList, innerClass) ;
		
		for(BaseClass inner :  innerClass.getInnerClasses()){
			importedTypesForInnerClass(typeList, inner);
		}
		
		for(InnerEnum inner :  innerClass.getInnerEnums()){
			importedTypesForInnerEnumClass(typeList, inner);
		}
		
	}
	
	/**
	 * 从 方法 属性 内部类 父类中导入相关的包(除了block的内容需要手工导入,其它全部自动导入)
	 * 导入相关的类库
	 * @param javaClass
	 * @throws CompilationException 
	 */
	protected static void importedTypes(JavaCompilation javaClass) throws CompilationException {
		Set<FullyQualifiedJavaType> typeList =javaClass.getImportedTypes();//  new ArrayList<FullyQualifiedJavaType>();
		
		//导入属性
		importedTypesForFields(typeList, javaClass.getFields());
		
		//导入方法
		importedTypesForMotheds(typeList, javaClass.getMethods());
		//导入父类
		importedTypesForSuperInterface(typeList, javaClass.getSuperInterfaceTypes());
		
		for(BaseClass inner :  javaClass.getInnerClasses()){
			importedTypesForInnerClass(typeList, inner);
		}
		
		for(InnerEnum inner :  javaClass.getInnerEnums()){
			importedTypesForInnerEnumClass(typeList, inner);
		}
		
		if( javaClass instanceof Enum ){
			Enum et =(Enum)javaClass;
			importedTypesForEnumField(typeList, et.getEnumFields());
		}
		
		if( javaClass instanceof AbstractJavaCompilation ){
			
			AbstractJavaCompilation ab = (AbstractJavaCompilation)javaClass ;
			
			importedTypesForOther(typeList, ab);
		}
	}
}
