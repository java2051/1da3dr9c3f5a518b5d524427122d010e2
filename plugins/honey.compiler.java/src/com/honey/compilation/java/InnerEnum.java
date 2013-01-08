package com.honey.compilation.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.honey.compilation.java.ClassDecoration.JavaType;
import com.honey.core.builder.CharacterBuilder;
import com.honey.core.compiler.CompilationException;
import com.honey.core.compiler.IndentSpace;
import com.honey.core.types.FullyQualifiedJavaType;
import com.honey.core.utils.StringUtility;

/**
 * 内部枚举类
 * 
 * @author Administrator
 * 
 */
public class InnerEnum extends BaseClass {

	/** 枚举字段 */
	private List<EnumField> enumFields;

	/**
	 * 构造函数
	 * @param type 枚举类的名称类型
	 * @throws CompilationException
	 */
	public InnerEnum(FullyQualifiedJavaType type) throws CompilationException {
		super(type);
		enumFields = new ArrayList<EnumField>();
	}

	/**
	 * 构造函数
	 * @param type 枚举类的名称类型
	 * @throws CompilationException
	 */
	public InnerEnum(String type) throws CompilationException {
		super(type);
		enumFields = new ArrayList<EnumField>();
	}

	/**
	 * 构造函数
	 * @param type 枚举类的名称类型
	 * @param classDecoration 枚举类的修饰
	 * @throws CompilationException
	 */
	public InnerEnum(FullyQualifiedJavaType type,ClassDecoration classDecoration) throws CompilationException {
		super(type, classDecoration);
		enumFields = new ArrayList<EnumField>();
	}

	/**
	 * 构造函数
	 * @param type 枚举类的名称类型
	 * @param classDecoration 枚举类的修饰
	 * @throws CompilationException
	 */
	public InnerEnum(String type, ClassDecoration classDecoration)
			throws CompilationException {
		super(type, classDecoration);
		enumFields = new ArrayList<EnumField>();
	}

	/**
	 * 枚举类的构造函数不能是 public 和 protected 修饰
	 */
	@Override
	public void addMethod(Method... methods) {
		for (Method method : methods) {
			if (method.getDecoration().isConstructor()) {
				method.getDecoration().setVisibility(JavaVisibility.PRIVATE);
			}
		}
		super.addMethod(methods);
	}

	/**
	 * 枚举类不能用final修饰
	 */
	@Override
	public void setClassDecoration(ClassDecoration classDecoration)
			throws CompilationException {
		if (classDecoration != null) {
			classDecoration.setJavaType(ClassDecoration.JavaType.ENUM);
			classDecoration.setFinal(false);

			super.setClassDecoration(classDecoration);
		}
	}
	
	/**
	 * 枚举的修饰规则是: <br />
	 * <ul>
	 *   <li>只能使用 enum关键字</li>
  	 *   <li>不能使final 关键字</li>
	 * </ul>
	 * @throws CompilationException
	 */
	@Override
	protected void classDecorationRule() throws CompilationException{
		ClassDecoration classDecoration = getClassDecoration();
		classDecoration.setJavaType(JavaType.ENUM); 
		classDecoration.setFinal(false);
	}
	
	
	/**
	 * 添加枚举字段
	 * @param enumFields 
	 */
	public void addEnumFields(EnumField... enumFields) {
		for (EnumField enumField : enumFields) {
			if (enumField != null)
				this.enumFields.add(enumField);
		}
	}

	/**
	 * 添加枚举字段
	 * @param enumFields 枚举字段
	 */
	public void addEnumFields(String... enumFields) {
		for (String enumField : enumFields) {
			if (StringUtility.stringHasValue(enumField))
				this.enumFields.add(new EnumField(enumField));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.AbstractJavaCompilation#getSuperClass()
	 */
	@Override
	public FullyQualifiedJavaType getSuperClass() throws CompilationException {
		//枚举不支持继承
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.AbstractJavaCompilation#setSuperClass(com.honey.core.types.FullyQualifiedJavaType)
	 */
	@Override
	public void setSuperClass(FullyQualifiedJavaType superClass) {
		//枚举不支持继承
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.AbstractJavaCompilation#setSuperClass(java.lang.String)
	 */
	@Override
	public void setSuperClass(String superClassType) {
		//枚举不支持继承
	}

	/**
	 * 创建默认的构造函数
	 * @return
	 * @throws CompilationException
	 */
	private Method createDefaltConstructor() throws CompilationException {
		Method answer = null;
//		Iterator<Method> mtdIter = getMethods().iterator();
//
//		boolean hasConstructor = false;
//		while (mtdIter.hasNext()) {
//			Method method = mtdIter.next();
//			if (method.getDecoration().isConstructor()) {
//				hasConstructor = true;
//				continue;
//			}
//		}
//		if (!hasConstructor) {
//			if (enumFields.size() > 0) {
//				EnumField ef = enumFields.get(0);
//				List<FullyQualifiedJavaType> lft = ef .getInitializationStringTypes();
//				if (lft.size() > 0) {
//					answer = new Method(super.getType().getShortName(),
//							new MethodDecoration(JavaVisibility.PRIVATE, true));
//					for (int i = 0, size = lft.size(); i < size; i++) {
//						FullyQualifiedJavaType type = lft.get(i);
//						Parameter parameter = new Parameter(type, "emf" + i);
//						answer.addParameter(parameter);
//						answer.addBodyLine("this.emf" + i + " = emf" + i);
//						Field field = new Field("emf" + i, type,
//								new Decoration(JavaVisibility.PRIVATE));
//						// field.setVisibility(JavaVisibility.PRIVATE);
//						this.addField(field);
//					}
//				}
//			}
//		}
		return answer;
	}
	
	/**
	 * 内部枚举类不能直接编译,只能在顶层类中调用编译
	 */
	@Override
	public CharacterBuilder compiledContent() throws CompilationException {
		throw new CompilationException(getType().getFullyQualifiedName() + " : 内部枚举类使不能被直接调用");
	}

	/**
	 * 获取所有的枚举字段
	 * @return
	 */
	public List<EnumField> getEnumFields() {
		return enumFields;
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.InnerClass#begianCompilationClass(com.honey.core.builder.CharacterBuilder, int)
	 */
	@Override
	protected void begianCompilationClass(CharacterBuilder cb, int indentLevel) {
		// 枚举字段
		Iterator<EnumField> it = enumFields.iterator();
		while (it.hasNext()) {
			IndentSpace.newLine(cb);
			EnumField ef = it.next();
			cb.append(ef.compiledContent(indentLevel));
			if (it.hasNext()) {
				cb.append(JavaKeyWord.OPERATION_COMMA);
			} else {
				cb.append(JavaKeyWord.OPERATION_SPLIT);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.BaseClass#endCompilationClass(com.honey.core.builder.CharacterBuilder, int)
	 */
	@Override
	protected void endCompilationClass(CharacterBuilder cb, int indentLevel) {
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.InnerClass#compiledContent(int)
	 */
	@Override
	public CharacterBuilder compiledContent(int indentLevel) throws CompilationException { 
		Method constructor = createDefaltConstructor();
		if (constructor != null)
			this.addMethod(constructor);
		CharacterBuilder answer = super.compiledContent(indentLevel);

		return answer;
	}

}
