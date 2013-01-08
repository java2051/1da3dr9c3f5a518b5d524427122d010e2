package com.honey.compilation.java;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.honey.core.builder.CharacterBuilder;
import com.honey.core.builder.CharacterBuilderFactory;
import com.honey.core.compiler.IndentSpace;
import com.honey.core.utils.StringUtility;

/**
 * 把java代码内容抽象为块的形式,例如: 静态块 同步块 分支块(switch) 条件块(if) 等等
 * 
 * @author Administrator
 *
 */
public abstract class Block extends JavaElement{
	
	/** 块中的代码 */
	private List<String> bodyLines;
	
	/** 块中包含块 */
	private List<Block> blocks;
	
	/** 添加顺序 */
	private List<Character> queue;
	
	/**
	 * 默认构造函数
	 */
	public Block(){
		bodyLines = new ArrayList<String>();
		blocks = new ArrayList<Block>();
		queue =  new ArrayList<Character>();
	}
	
	/**
	 * 添加一行代码
	 * @param line
	 */
	public void addBodyLine(String line){
		if(StringUtility.stringHasValue(line)){
    		bodyLines.add(line);
    		queue.add('l');
    	}
	}
	
	/**
	 * 添加代码块
	 * @param blocks
	 */
	public void addBlocks(Block ...blocks){
		for(Block block : blocks){
			if(block != null){
				this.blocks.add(block);
				queue.add('b');
			}
		}
	}
	
	/**
	 * 默认的块开始
	 * @return
	 */
	public  String startBlock(){
		return "{" ;
	}

	/**
	 * 默认的块结束
	 * @return
	 */
	public String endBlock(int indentLevel ){
		return "}";
	}

	/**
	 * 
	 * @param childBodyLines
	 * @param indentLevel
	 */
	private void getChildBodyLines(List<String> childBodyLines,int indentLevel ){
		int line_index=0;
		int block_index=0;
		for (Character c: queue){
			if(c.charValue() =='l' ){
				if(bodyLines.size()>0 ){
					childBodyLines.add( bodyLines.get(line_index ++ ) );
				}
			}else{
				if(getBlocks().size()>0){
					Block block =  getBlocks().get(block_index ++);
					for (String javaDocLine : block.getJavaDocLines()) {
						childBodyLines.add("// "+ javaDocLine);
					}
					childBodyLines.add(block.startBlock());
					block.getChildBodyLines(childBodyLines,++indentLevel);
					childBodyLines.add(block.endBlock(indentLevel));
				}
			}
		}
	}
	
	/**
	 * 自动在代码尾部添加";"号
	 * @param line
	 * @return
	 */
	private String autoAppendOperationSplit(String line){
		String s = line;
        if(s.length()>0){
        	int i = s.indexOf("//");
        	if(i>0)
        		s = s.substring(0, i);
            char last = s.charAt(s.length() - 1 );
            if( 
            	last != '{' && 
            	last != '}' && 
            	last != '(' && 
            	//last != ')' && 
            	last != ':' &&
            	last != '.' &&
            	last != ',' &&
            	last != '=' &&
            	last != '+' &&
            	last != ';' 
            		
            ){
            	s =s+ JavaKeyWord.OPERATION_SPLIT ;
            	if(i>0)
            		s =s+ line.substring(i, line.length());
            }
        }
		return s;
	}
	boolean isaddtobodyLines = false ;
	/**
	 * 格式化加入的代码块
	 * @param indentLevel 级别
	 * @return
	 */
    public CharacterBuilder compiledContent(int indentLevel) {
    	CharacterBuilder answer =  CharacterBuilderFactory.createC16StringBuilder();
 
//        
//        if( ! isaddtobodyLines){
//        	List<String> childBodyLines = new ArrayList<String>();
//        	getChildBodyLines(childBodyLines,indentLevel);
//        	bodyLines.addAll(childBodyLines);
//        	isaddtobodyLines = true;
//        }
    	List<String> childBodyLines = new ArrayList<String>();
    	getChildBodyLines(childBodyLines,indentLevel);
    	
    	
        if(getJavaDocLines().size()>0){
        	IndentSpace.newLine(answer);
        	super.addFormattedJavaSingleDoc(answer, indentLevel);
        }
        
        String start =startBlock() ;
        if( StringUtility.stringHasValue(start)){
        	IndentSpace.newSpace(answer,  indentLevel);
        	answer.append(start);
        	start =  start.trim();
        	if(start.endsWith("{"));
        		indentLevel++;
        }
        
        //ListIterator<String> listIter = bodyLines.listIterator();
        ListIterator<String> listIter = childBodyLines.listIterator();
		String line = null;
		while (listIter.hasNext()) {
			line = listIter.next();
			line = line.trim();
			if (line.endsWith("}")) { //$NON-NLS-1$
				indentLevel--;
			}
			
			IndentSpace.newSpace(answer,  indentLevel);
			
			answer.append(autoAppendOperationSplit(line));
			
			if ((line.endsWith("{") && !line.startsWith("switch"))
                    || line.endsWith(":")) { 
                indentLevel++;
            }
			
			if (line.startsWith("break")) {
				// if the next line is '}', then don't outdent
				if (listIter.hasNext()) {
					String nextLine = listIter.next();
					if (nextLine.startsWith("}")) {
						indentLevel++;
					}

					// set back to the previous element
					listIter.previous();
				}
				indentLevel--;
			}
		}
        String end=endBlock(indentLevel-2);
        if( StringUtility.stringHasValue(end)){
        	end =  end.trim();
        	if(end.endsWith("}"));{
        		indentLevel--;
        	}
        	IndentSpace.newSpace(answer,  indentLevel);
            answer.append(end);
        }
        return answer;
    }

    /**
     * 获取所有块中的代码
     * @return
     */
	public List<String> getBodyLines() {
		return bodyLines;
	}

	/**
	 * 添加代码
	 * @param bodyLines
	 */
	public void setBodyLines(List<String> bodyLines) {
		this.bodyLines = bodyLines;
	}

	/**
	 * 获取块
	 * @return
	 */
	public List<Block> getBlocks() {
		return blocks;
	}

	/**
	 * 添加块
	 * @param blocks
	 */
	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}
	
	/**
	 * 块不支持注解
	 */
	@Override
	public void addAnnotation(String ...annotations) {
	}

	/**
	 * 块不支持注解
	 */
	@Override
	public List<String> getAnnotations() {
		return null;
	}

	/**
	 * 块不支持修饰
	 */
	@Override
	public Decoration getDecoration() {
		return null;
	}
	
	/**
	 * 块不支持修饰
	 */
	@Override
	public void setDecoration(Decoration decoration) {
		
	}
}
