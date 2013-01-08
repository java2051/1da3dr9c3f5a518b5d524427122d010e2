package com.honey.core.dbmapping.structure;

import java.util.Map;

/**
 * 数据列验证器,数据提交时按照指定的格式进行验证;
 * @author Administrator
 *
 */
public interface ColumnValidator {
	
	/**
	 * 获取验证器名称
	 * @return
	 */
	public String getValidatorName();
	
	/**
	 * 验证错误提示信息
	 * @return
	 */
	public String getErrorMessage();
	
	/**
	 * 其他扩充验证参数 , 例如: 验证正则表达式 , 数据最小长度等.
	 * @return
	 */
	public Map<String, String> getExtendParameter();
	
	
}
