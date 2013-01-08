package com.honey.compilation.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.compiler.IndentSpace;
import com.honey.core.types.FullyQualifiedJavaType;

/**
 * 可视区域块
 * @author Administrator
 *
 */
public class TryCatchBlock  extends Block{
	
	private List<FullyQualifiedJavaType> catchCases= null; 
	
	private Map<FullyQualifiedJavaType, String[]> processException = null;
	
	public TryCatchBlock (){
		catchCases = new ArrayList<FullyQualifiedJavaType>();
		processException = new HashMap<FullyQualifiedJavaType, String[]>();
	} 
	
	public TryCatchBlock (FullyQualifiedJavaType  ...catchCases){
		this();
		addCatchCases(catchCases);
	} 
	
	public void addCatchCases (FullyQualifiedJavaType  ...catchCases){
		for(FullyQualifiedJavaType catchCase : catchCases){
			if(catchCase != null){
				addCatchCase(catchCase, null);
			}
		}
	}
	
	public void addCatchCase (FullyQualifiedJavaType  catchCases , String ...processException ){
		if(catchCases != null) this.catchCases.add(catchCases);
		if(processException != null){
			this.processException.put(catchCases, processException);
		}
//		else{
//			this.processException.put(catchCases, new String[]{"DEF"});
//		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.Block#endBlock(int)
	 */
	@Override
	public String endBlock(int indentLevel) {
		CharacterBuilder answer = CharacterBuilderFactory.createC16StringBuilder();
		answer.append(JavaKeyWord.OPERATION_BRACE_RIGHT);
		
		for(int i =0,size = catchCases.size() ;i<size;i++){
			FullyQualifiedJavaType catchCase = catchCases.get(i);
			
			answer.append(JavaKeyWord.CATCH)
			.append(JavaKeyWord.OPERATION_BRACKET_LEFT)
			.append( catchCase.getShortName()  )
			.append(JavaKeyWord.OPERATION_SPACE).append("e")
			.append(JavaKeyWord.OPERATION_BRACKET_RIGHT).append(JavaKeyWord.OPERATION_BRACE_LEFT)
			.append(JavaKeyWord.OPERATION_SPACE)
			;
			indentLevel ++ ;
			IndentSpace.newLine(answer);
			String ex[] = processException.get(catchCase);
			if(ex != null){
				for(String s: ex){
					IndentSpace.spaceIndent(answer, indentLevel);
					answer.append(s);
				}
			}else{
				IndentSpace.spaceIndent(answer, indentLevel);
				answer.append("e.printStackTrace();");
			}
            indentLevel -- ;
            IndentSpace.newLine(answer);
            IndentSpace.spaceIndent(answer, indentLevel);

            answer.append(JavaKeyWord.OPERATION_BRACE_RIGHT);
		}
		
		return answer.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.honey.compilation.java.Block#startBlock()
	 */
	@Override
	public String startBlock() {
		
		return "try{";
	}
	
	
}
