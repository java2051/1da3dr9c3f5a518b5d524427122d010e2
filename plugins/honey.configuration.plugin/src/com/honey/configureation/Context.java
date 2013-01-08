package com.honey.configureation;

import java.util.Properties;

import com.honey.configureation.holder.JDBCConnectionHolder;
import com.honey.configureation.holder.MappingEnvironmentHolder;
import com.honey.configureation.holder.ProjectEnvironmentHolder;

public class Context {
	private Properties systemProperties = null;
	
	private String[]  systemArg = null;
	
	private ProjectEnvironmentHolder projectEnvironmentHolder = null;
	
	private JDBCConnectionHolder jdbcConnectionHolder = null; 
	
	private MappingEnvironmentHolder mappingEnvironmentHolder = null; 
	
	/**
	 * 数据库信息配置
	 */
	private SchemaConfiguration schemaConfiguration ;
	
	Context(){
		
	}
	
	public SchemaConfiguration getSchemaConfiguration() {
		return schemaConfiguration;
	}

	public Properties getSystemProperties() {
		return systemProperties;
	}

	public void setSystemProperties(Properties systemProperties) {
		this.systemProperties = systemProperties;
	}

	public String[] getSystemArg() {
		return systemArg;
	}

	void setSystemArg(String[] systemArg) {
		this.systemArg = systemArg;
	}

	void setSchemaConfiguration(SchemaConfiguration schemaConfiguration) {
		this.schemaConfiguration = schemaConfiguration;
	}

	public ProjectEnvironmentHolder getProjectEnvironmentHolder() {
		return projectEnvironmentHolder;
	}

	void setProjectEnvironmentHolder(
			ProjectEnvironmentHolder projectEnvironmentHolder) {
		this.projectEnvironmentHolder = projectEnvironmentHolder;
	}

	public JDBCConnectionHolder getJdbcConnectionHolder() {
		return jdbcConnectionHolder;
	}

	void setJdbcConnectionHolder(JDBCConnectionHolder jdbcConnectionHolder) {
		this.jdbcConnectionHolder = jdbcConnectionHolder;
	}

	public MappingEnvironmentHolder getMappingEnvironmentHolder() {
		return mappingEnvironmentHolder;
	}
	
	void setMappingEnvironmentHolder(
			MappingEnvironmentHolder mappingEnvironmentHolder) {
		this.mappingEnvironmentHolder = mappingEnvironmentHolder;
	}
	
}
