package com.honey.configureation.holder;

import java.util.Properties;

import org.w3c.dom.Element;

import com.honey.configureation.xmlparser.ParseElement;


/**
 * JDBC 节点解析
 * @author Administrator
 *
 */
public class JDBCConnectionHolder implements ConfigurationHolder{
	
	/** jdb jar文件位置*/
	private String library ;
	
	/** 数据库驱动器 */
	private String driver ;
	
	/** 数据库连接地址 */
	private String url ;
	
	/** 连接数据的用户名称 */
	private String username ;
	
	/** 连接数据的密码 */
	private String password ;
	
	/** catalog */
	private String catalog ;
	
	/** 数据库结构 */
	private String schema ;
	
	/** 数据库连接的其它配置 */
	private Properties properties ;
	
	@Override
	public void parserElement(Element element, Properties systemProperties) {
		library = ParseElement.getConfiguration(element, "library", systemProperties);
		
		driver =  ParseElement.getConfiguration(element, "driver", systemProperties) ;
		
	 	url = ParseElement.getConfiguration(element, "url", systemProperties) ;
		
	 	username = ParseElement.getConfiguration(element, "username", systemProperties) ;
		
	 	password = ParseElement.getConfiguration(element, "password", systemProperties) ;
		
		catalog = ParseElement.getConfiguration(element, "catalog", systemProperties) ;
		
		schema = ParseElement.getConfiguration(element, "schema", systemProperties);
		
		properties = ParseElement.parseKeyValueNode(element, systemProperties) ;
		Object tmp = properties.remove("library");
		if(tmp!= null)
			library =tmp.toString();
		
		tmp = properties.remove("driver");
		if(tmp!= null)
			driver =tmp.toString();
		
		tmp = properties.remove("url");
		if(tmp!= null)
			url =tmp.toString();
		
		tmp = properties.remove("username");
		if(tmp!= null)
			username =tmp.toString();
		
		tmp = properties.remove("password");
		if(tmp!= null)
			password =tmp.toString();
		
		tmp = properties.remove("password");
		if(tmp!= null)
			password =tmp.toString();
		
		tmp = properties.remove("catalog");
		if(tmp!= null)
			catalog =tmp.toString();
		
		tmp = properties.remove("schema");
		if(tmp!= null)
			schema =tmp.toString();
	}
	
	@Override
	public String toXmlElement() {
		
		return null;
	}

	
	@Override
	public void validate() {
		
		
	}

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public Properties getProperties() {
		return properties;
	}
}
