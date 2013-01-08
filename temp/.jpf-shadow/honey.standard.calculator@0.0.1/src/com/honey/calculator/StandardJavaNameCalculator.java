package com.honey.calculator;

import org.java.plugin.Plugin;

import com.honey.core.Extension;
import com.honey.core.calculator.JavaNameCalculator;

public class StandardJavaNameCalculator  extends Extension<Plugin> implements JavaNameCalculator {
	
	public StandardJavaNameCalculator(Plugin belongPlugin) {
		super(belongPlugin);
	}
	
	/**
	 * java类名称规则是 首字母必须是字母(一般是大写字符)或者下划线,首字后的字符只能是数字、字母和下划线不能包含特殊字符和.
	 */
	@Override
	public String classNameCalculator(String... inputString) {
		boolean bool =  ((CalculatorPlugin)super.getBelongPlugin()).isObfuscate();
		StandardCalculator calculator = bool ?  new ObfuscateCalculator() : new NameCalculator();
		String answer = calculator.calculator(false, inputString);
		
		
		if(answer == null || answer.length()==0  ) return answer;
		char []chars = answer.toCharArray() ;
		if(chars[0]>=97 &&  chars[0]<=122){ // a=97 z=122
			chars[0] = (char) (chars[0]- 32);//ASCII中大写字母和小写字母相差32
		}
		return new String (chars);
	}

	/**
	 * java 属性名称规则是: 首字母必须是字母(一般是小写写字符)或者下划线,首字后的字符只能是数字、字母和下划线不能包含特殊字符和.
	 * 
	 */
	@Override
	public String fieldNameCalculator(String... inputString) {
		boolean bool =  ((CalculatorPlugin)super.getBelongPlugin()).isObfuscate();
		StandardCalculator calculator = bool ?  new ObfuscateCalculator() : new NameCalculator();
		return calculator.calculator(false, inputString);
	}
	
	@Override
	public String staticFieldCalculator(String... inputString) {
		boolean bool =  ((CalculatorPlugin)super.getBelongPlugin()).isObfuscate();
		StandardCalculator calculator = bool ?  new ObfuscateCalculator() : new NameCalculator();
		return calculator.calculator(true, inputString).toUpperCase();
	}

	@Override
	public String methodNameCalculator(String... inputString) {
		boolean bool =  ((CalculatorPlugin)super.getBelongPlugin()).isObfuscate();
		StandardCalculator calculator = bool ?  new ObfuscateCalculator() : new NameCalculator();
		return calculator.calculator(false, inputString);
	}

	@Override
	public String variableNameCalculator(String... inputString) {
		boolean bool =  ((CalculatorPlugin)super.getBelongPlugin()).isObfuscate();
		StandardCalculator calculator = bool ?  new ObfuscateCalculator() : new NameCalculator();
		return calculator.calculator(false, inputString);
	}
	
	@Override
	public void clean() {
		
	}
}
