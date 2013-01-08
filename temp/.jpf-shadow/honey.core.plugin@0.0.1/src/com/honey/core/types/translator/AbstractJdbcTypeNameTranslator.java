package com.honey.core.types.translator;

import java.sql.Types;
import java.util.Map;

import com.honey.core.types.DataType;
import com.honey.core.types.JdbcTypeNameTranslator;

/**
 * 抽象虚类转换器
 * @author Administrator
 *
 */
public abstract class AbstractJdbcTypeNameTranslator implements JdbcTypeNameTranslator {
	
	@Deprecated
	@Override
	public DataType getJdbcTypeName(int jdbcType) {
		
		
		throw new RuntimeException("暂不支持");
	}
	
	@Override
	public DataType getJdbcType(String jdbcTypeName) {
		DataType answer = new DataType("OTHER", Types.OTHER, false, false, false);
		if(jdbcTypeName != null && jdbcTypeName.length()>0 ){
			DataType tmp = getNameToType().get(jdbcTypeName.toLowerCase());
			if (tmp != null) {
				answer = tmp;
			}
		}
		return answer;
	}
	
	@Override
	public String createSqlType(int jdbcType,int length,int scale ,boolean unsigned ){
		throw new RuntimeException("暂不支持");
	}

	/**
	 * 获取 java.sql.Types -> SQL Type Name 映射器
	 * @return
	 */
	abstract Map<Integer, DataType> getTypeToName();
	
	/**
	 * 获取 SQL Type Name 映射器 -> java.sql.Types  
	 * @return
	 */
	abstract Map<String, DataType> getNameToType();
	
}
