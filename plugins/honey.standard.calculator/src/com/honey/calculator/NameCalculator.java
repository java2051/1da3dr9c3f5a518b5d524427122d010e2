package com.honey.calculator;

import com.honey.core.utils.StringUtility;

/**
 * 名称转换
 * @author Administrator
 *
 */
class NameCalculator implements StandardCalculator {

	/**
	 * 名称转换规则是:首字母小写,略过以特殊字符和数字开始的字符,首字母以后只略过特殊字符(字母和数字和下划线)
	 * @param inputString
	 * @return
	 */
	@Override
	public  String calculator(boolean underline,String ...inputString){
		StringBuilder answer = new StringBuilder();
		if( inputString.length > 0){
			boolean flag=false;
			for(String s : inputString ){
				if(StringUtility.stringHasValue(s)  ){
					char chars[] = s.toCharArray() ;
					if(chars[0]>=97 &&  chars[0]<=122){ // a=97 z=122
						chars[0] = (char) (chars[0]- 32);//ASCII中大写字母和小写字母相差32
					}
					for(int i =0;i<chars.length;i++){
						if(!flag &&(
						  chars[i]==95 ||
						  chars[i]>=65 && chars[i] <= 90 ||
						  chars[i]>=97 && chars[i] <= 122)){
							if(chars[i]>=65 &&  chars[i]<=90){ // A=65 Z=90
								chars[i] = (char) (chars[i]+ 32);//ASCII中大写字母和小写字母相差32
							}
							answer.append( chars[i] );
							flag =true;
							continue;
						}
						if(	flag && (
								chars[i]==95 ||
								chars[i]>=48 && chars[i] <= 57 ||
								chars[i]>=65 && chars[i] <= 90 ||
								chars[i]>=97 && chars[i] <= 122) 
						){
							if(i==0&&underline)answer.append("_");
							answer.append( chars[i] );
						}
					}
				}
			}
		}
		return answer.toString();
	}
}
