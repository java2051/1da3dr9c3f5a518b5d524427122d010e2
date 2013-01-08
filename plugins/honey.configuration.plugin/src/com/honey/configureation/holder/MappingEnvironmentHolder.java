package com.honey.configureation.holder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.honey.configureation.xmlparser.ParseElement;
import com.honey.core.mapping.TableMapping;
import com.honey.core.utils.Constant;
import com.honey.core.utils.ObjectDump;
import com.honey.core.utils.StringUtility;

public class MappingEnvironmentHolder implements ConfigurationHolder{

	private List<TableMapping> mappings = new ArrayList<TableMapping>();
	
	@Override
	public void parserElement(Element element, Properties properties) {
		NodeList imports = element.getElementsByTagName("import");
		if(imports!= null && imports.getLength() > 0  ){
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				for(int i =0,size = imports.getLength(); i < size ; i++  ){
					Element importElement = (Element)imports.item(i);
					String sImportFiles = importElement.getTextContent();
					String []importFiles = StringUtility.parseStringsplit(sImportFiles);
					for(String importFile: importFiles ){
						File file = new File(importFile);
						Document  document  =  builder.parse( file );
						Element rootNode = document.getDocumentElement();
						String version =  ParseElement.getConfiguration(rootNode, "version", properties);
						if(! Constant.VERSION.equals(version) ){
							continue ;
						}
						if(rootNode != null ) {
							NodeList tables = rootNode.getElementsByTagName("table");
							if(tables!= null && tables.getLength() > 0  ){
								for(int t =0,tSize = tables.getLength(); t < tSize ; t++  ){
									parserTableElement((Element)tables.item(t), properties);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		NodeList tables = element.getElementsByTagName("table");
		if(tables!= null && tables.getLength() > 0  ){
			for(int i =0,size = tables.getLength(); i < size ; i++  ){
				parserTableElement((Element)tables.item(i), properties);
			}
		}
	}

	@Override
	public String toXmlElement() {
		return null;
	}

	@Override
	public void validate() {
		
	}
	
	private void parserTableElement(Element element, Properties properties){
		Properties propertie = ParseElement.getConfigurations(element, properties) ;
		String tableName = propertie.getProperty("tableName");
		//String tableName = ParseElement.getConfiguration(element, "tableName", properties);
		if(StringUtility.stringHasValue(tableName) ){
			MappingEnvironment mapping = new MappingEnvironment();
			mapping.setName(tableName);
			mapping.setPropertie(propertie);
			mappings.add(mapping);
		}
	}
	
	public List<TableMapping> getMappings() {
		return mappings;
	}
	
	public class MappingEnvironment  implements TableMapping{
		
		private String name ;

		private Properties propertie;
		
		@Override
		public String getName() {
			
			return name;
		}

		@Override
		public MappingKind getMappingKind() {
			return MappingKind.DATABASE_TABLE;
		}
		
		/**
		 * @return the propertie
		 */
		public final Properties getPropertie() {
			return propertie;
		}

		/**
		 * @param propertie the propertie to set
		 */
		public final void setPropertie(Properties propertie) {
			this.propertie = propertie;
		}

		/**
		 * @param name the name to set
		 */
		public final void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return ObjectDump.dump(this);
		}
	}
}
