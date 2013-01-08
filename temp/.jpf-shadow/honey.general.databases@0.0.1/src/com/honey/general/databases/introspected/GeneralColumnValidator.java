package com.honey.general.databases.introspected;

import java.util.HashMap;
import java.util.Map;

import com.honey.core.dbmapping.structure.ColumnValidator;
import com.honey.core.utils.ObjectDump;

public class GeneralColumnValidator implements ColumnValidator {
	
	/** 效验器名称  */
	private String validatorName ;
	
	/** 验证失败提示信息  */
	private String errorMessage ;
	
	/** 其他扩充验证 参数  */
	private Map<String, String> extendParameter ;

	public GeneralColumnValidator() {
		extendParameter = new HashMap<String, String>();
	}

	/**
	 * @return the validatorName
	 */
	@Override
	public final String getValidatorName() {
		return validatorName;
	}

	/**
	 * @param validatorName the validatorName to set
	 */
	public final void setValidatorName(String validatorName) {
		this.validatorName = validatorName;
	}

	/**
	 * @return the errorMessage
	 */
	@Override
	public final String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public final void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the extendParameter
	 */
	@Override
	public final Map<String, String> getExtendParameter() {
		return extendParameter;
	}

	/**
	 * @param extendParameter the extendParameter to set
	 */
	public final void setExtendParameter(Map<String, String> extendParameter) {
		this.extendParameter = extendParameter;
	}
	
	@Override
	public String toString() {
        return ObjectDump.dump(this);
    }
}
