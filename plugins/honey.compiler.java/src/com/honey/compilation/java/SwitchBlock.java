
package com.honey.compilation.java;


/**
 * switch语句块
 * @author Administrator
 *
 */
@Deprecated
public class SwitchBlock extends VisibleBlock{
	
	private String caseValue ="0";
	
	public SwitchBlock(){
		
	}
	
	public SwitchBlock(String caseValue){	
		this.caseValue = caseValue;
	}
	
	@Override
	public String startBlock() {
		
		return "switch( "+caseValue+" ) " + super.startBlock();
	}

	public String getCaseValue() {
		return caseValue;
	}

	public void setCaseValue(String caseValue) {
		this.caseValue = caseValue;
	}
	
}
