package com.honey.calculator;

import java.util.regex.Pattern;

import org.java.plugin.Plugin;

import com.honey.core.Extension;
import com.honey.core.calculator.MappingCalculator;
import com.honey.core.utils.JavaBeansUtil;

public class StandardMappingCalculator extends Extension<Plugin> implements  MappingCalculator {
	

	private String replacement = "";
	
	private Pattern pattern = null; 
	
	public StandardMappingCalculator(Plugin belongPlugin) {
		super(belongPlugin);
		CalculatorPlugin calculatorPlugin = (CalculatorPlugin)belongPlugin;
		String regex = calculatorPlugin.getReplaceRegex();
		replacement = calculatorPlugin.getReplacement();
		if(regex != null ){
			pattern = Pattern.compile(regex);
		}
	}
	
	@Override
	public String schemaCalculator(String... inputString) {
		if(inputString.length > 0){
//			String str = schemaName[0];
//			char chars[] = str.toCharArray();
//			
//			for(int i=0;i<chars.length;i++ ){
//				if(chars[i]==95 ){
//					schemaName[0] = str.substring(i+1, chars.length);
//					break;
//				}
//			}
			String in = inputString[0];
			if(pattern != null ){
				in =pattern.matcher(in).replaceAll(replacement);
			}
			inputString[0] =JavaBeansUtil.getCamelCaseString(in, true);
		}		
		return mappingCalculator(inputString);
	}

	@Override
	public String mappingCalculator(String... columnName) {
		boolean bool =  ((CalculatorPlugin)super.getBelongPlugin()).isObfuscate();
		StandardCalculator calculator = bool ?  new ObfuscateCalculator() : new NameCalculator();
		return calculator.calculator(false, columnName);
	}
	@Override
	public void clean() {
		
	}
}
