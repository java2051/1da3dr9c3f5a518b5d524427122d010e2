package com.honey.mysql5.config;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.java.plugin.Plugin;
import org.java.plugin.registry.PluginAttribute;
import org.java.plugin.registry.PluginDescriptor;
import org.w3c.dom.Element;

import com.honey.configureation.holder.JDBCConnectionHolder;
import com.honey.configureation.xmlparser.ParseElement;
import com.honey.core.utils.StringUtility;

public class MysqlConfigurationHolder {
	
	private String driver = null;
	
	private String url = null;
	
	private String userName = null;
	
	private String password = null;
	
	private String library = null;
	
	public MysqlConfigurationHolder(){
		
	}

	public void holderPluginDescriptorAttribute(PluginDescriptor pluginDescriptor){
		PluginAttribute pa =  pluginDescriptor.getAttribute("driver");
		String tmp ;
		if(pa != null){
			tmp= pa.getValue();
			this.setDriver(tmp);
		}
		pa =  pluginDescriptor.getAttribute("url");
		if(pa != null){
			tmp= pa.getValue();
			this.setUrl(tmp);
		}
		pa =  pluginDescriptor.getAttribute("username");
		if(pa != null){
			tmp= pa.getValue();
			this.setUserName(tmp);
		}
		pa =  pluginDescriptor.getAttribute("password");
		if(pa != null){
			tmp = pa.getValue();
			this.setPassword(tmp);
		}
		pa = pluginDescriptor.getAttribute("library");
		if(pa != null){
			tmp = pa.getValue();
			this.setLibrary(tmp);
		}
	}
	
	public void holderDatabasesConfiguration(JDBCConnectionHolder connectionHolder){
		String tmp = connectionHolder.getDriver();
		if(StringUtility.stringHasValue(tmp)){
			this.setDriver(tmp);
		}
		tmp = connectionHolder.getLibrary();
		if(StringUtility.stringHasValue(tmp)){
			this.setLibrary(tmp);
		}
		tmp = connectionHolder.getPassword();
		if(StringUtility.stringHasValue(tmp)){
			this.setPassword(tmp);
		}
		tmp = connectionHolder.getUrl();
		if(StringUtility.stringHasValue(tmp)){
			this.setUrl(tmp);
		}
		tmp = connectionHolder.getUsername();
		if(StringUtility.stringHasValue(tmp)){
			this.setUserName(tmp);
		}
	}
	
	public void parserElement(Element element, Properties systemProperties) {
		Properties properties  = ParseElement.parseAttributes(element, systemProperties);
		properties.putAll(ParseElement.parseKeyValueNode(element, systemProperties) );
		String tmp = properties.getProperty("driver");
		if(StringUtility.stringHasValue(tmp)){
			this.driver=tmp;
		}
		tmp = properties.getProperty("url");
		if(StringUtility.stringHasValue(tmp)){
			this.url=tmp;
		}
		tmp = properties.getProperty("username");
		if(StringUtility.stringHasValue(tmp)){
			this.userName=tmp;
		}
		
		tmp = properties.getProperty("password");
		if(StringUtility.stringHasValue(tmp)){
			this.password=tmp;
		}
		
		tmp = properties.getProperty("library");
		if(StringUtility.stringHasValue(tmp)){
			this.library=tmp;
		}
	}
	
	public String toXmlElement() {
		
		return null;
	}

	
	public void validate() {
		
	}
	
	public Connection initConnection(Plugin plugin ){
		Connection answer = null;
		try {
			/*  //如果没有插件还没有被激活,可以使用下列的方法激活自身
			    PluginManager mngr = PluginManager.lookup(this);
            	mngr.activatePlugin(ext.getDeclaringPluginDescriptor().getId());
			*/
			
			//把外部导入的jar全部加载到系统中
			if(library!= null){
				String entries[] = library.split(";");
				List<URL> urls = new ArrayList<URL>();
				File file;
				if (entries != null) {
					for (String classPathEntry : entries) {
						file = new File(classPathEntry);
						if (!file.exists())continue;
						try {urls.add(file.toURI().toURL());
						} catch (MalformedURLException e) {}
					}
					URLClassLoader classloader = null;
					try {
						//非法注入系统addURL方法
						Method systemClassloaderAddURLMethod = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
						boolean bool = systemClassloaderAddURLMethod.isAccessible();
						systemClassloaderAddURLMethod.setAccessible(true);
						classloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
						for(URL url : urls){
							systemClassloaderAddURLMethod.invoke(classloader, new Object[] {url });
						}
						systemClassloaderAddURLMethod.setAccessible(bool);
					} catch (Exception e) {
						
					}
				}
			}
			
			Driver jdbcDriver = null;
			
			//系统驱动器jar包
			try {
				jdbcDriver =  (Driver) Class.forName(driver).newInstance();
			} catch (Exception e) {
				jdbcDriver = null;
			}
			
			//插件周中加载的驱动器jar包
			if(jdbcDriver == null){
				try {
					ClassLoader classLoader = plugin.getManager().getPluginClassLoader(plugin.getDescriptor());
					Class clazz = classLoader.loadClass(driver);
					jdbcDriver = (Driver)clazz.newInstance();
				} catch (Exception e) {
					jdbcDriver = null;
				}
			}
			Properties props = new Properties();
			props.put("user", userName);
			props.put("password", password);
			answer = jdbcDriver.connect(url, props);
			
			//
			//注意这里不能用DriverManager获取connection(插件使用了插件的类加载器,DriverManager使用jdk的类加载器)
			//connection = DriverManager.getConnection(URL, userName, password) ;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return answer ;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

}
