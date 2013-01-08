package com.honey.configureation.xmlparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 解析xml节点
 * @author Administrator
 *
 */
public class ParseElement {
	
	private static final String OPEN = "${";
	
	private static final String CLOSE = "}";
	/**
	 * 替换系统中的环境变量 ${}
	 * @param string 字符串
	 * @param systemProperties 配置
	 * @return
	 */
	public static String parsePropertyTokens(String string, Properties systemProperties) {
		String newString = string;
		if (newString != null) {
			int start = newString.indexOf(OPEN);
			int end = newString.indexOf(CLOSE);
			while (start > -1 && end > start) {
				String prepend = newString.substring(0, start);
				String append = newString.substring(end + CLOSE.length());
				String propName = newString.substring(start + OPEN.length(), end);
				String propValue = systemProperties.getProperty(propName);
				if (propValue != null) {
					newString = prepend + propValue + append;
				}
				start = newString.indexOf(OPEN, end);
				end = newString.indexOf(CLOSE, end);
			}
		}
		return newString;
	}
	
	/**
	 * 读取节点中的属性并且加入到指定的Properties
	 * @param element 指定节点
	 * @param systemProperties 指定Properties
	 * @return
	 */
	public static void parseAttributesForProperties(Element element, Properties systemProperties) {
		parseAttributes(element, systemProperties, true);
	}
	
	
	public static Properties parseAttributes(Element element, Properties systemProperties) {
		return parseAttributes(element, systemProperties, false);
	}
	
	/**
	 * 读取element的Attributes
	 * @param element
	 * @param systemProperties
	 * @param addToSystemProperties
	 * @return
	 */
	public static Properties parseAttributes(Element element, Properties systemProperties,boolean addToSystemProperties) {
		Properties attributes =addToSystemProperties?systemProperties:new Properties();
		NamedNodeMap nnm = element.getAttributes();
		for (int i = 0; i < nnm.getLength(); i++) {
			Node attribute = nnm.item(i);
			String value = parsePropertyTokens(attribute.getNodeValue(), systemProperties);
			if (value != null) {
				value = value.trim();
				if (value.length() > 0)
					attributes.put(attribute.getNodeName(), value);
			}
		}
		//attributes.putAll(parseKeyValueNode(element, systemProperties));

		return attributes;
	}
	
	/**
	 * 解析指定节点下的Key  value节点 , 解析的结果存放到systemProperties对象里
	 * @param element
	 * @param systemProperties 系统properties,存放系统key value变量,用户${}格式替换.解析结果也存放到这个里面 
	 */
	public static void parseElementForProperties(Element element, Properties systemProperties) {
		
		parseKeyValueNode(element, systemProperties, true);
	}
	
	/**
	 * 解析指定节点下的Key  value节点 , 返回新Properties对象
	 * @param element 
	 * @param systemProperties 系统properties,存放系统key value变量,用户${}格式替换 
	 * @return
	 */
	public static Properties parseKeyValueNode(Element element, Properties systemProperties) {
		
		return parseKeyValueNode(element, systemProperties, false);
	}
	
	/**
	 * 解析指定节点下的Key  value节点
	 * @param element 节点
	 * @param systemProperties 系统properties,存放系统key value变量,用户${}格式替换 
	 * @param addToSystemProperties 是否把解析出来的key value 内容加入到systemProperties中 true:是   false:否
	 * @return
	 */
	private static Properties parseKeyValueNode(Element element, Properties systemProperties,boolean addToSystemProperties) {
		Properties attributes =addToSystemProperties?systemProperties:new Properties();
		NodeList nodeList = element.getChildNodes();
		Node n = null;
		for (int i = 0, size = nodeList.getLength(); i < size; i++) {
			n = nodeList.item(i);
			if(n.getNodeType() != Node.ELEMENT_NODE) continue;
			if ("entity".equalsIgnoreCase(n.getNodeName()) || "parameter".equalsIgnoreCase(n.getNodeName()) || "property".equalsIgnoreCase(n.getNodeName()) ||  "attribute".equalsIgnoreCase(n.getNodeName())) {
				String name = ParseElement.getConfiguration((Element) n, "key",systemProperties);
				if (name == null || name.length()==0) {
					name = ParseElement.getConfiguration((Element) n, "name",systemProperties);
					if (name == null || name.length()==0) {
						name = ParseElement.getConfiguration((Element) n, "id",systemProperties);
					}
				}
				String value = ParseElement.getConfiguration((Element) n, "value",systemProperties);
				if(value == null || value.length()==0) {
					value = getTextContent((Element) n, systemProperties);
				}
				if (value != null && name != null) {
					value = value.trim();
					name = name.trim();
					if (value.length() > 0 && name.length() > 0){
						attributes.put(name, value);
					}
				}

			} else if (n.getNodeType() == Node.ELEMENT_NODE) { // <rootNode><keyName>value</keyName></rootNOde>
				if (n.getAttributes().getLength() == 0) {
					if (isOnlyTextNode(n)) {
						// System.out.println(node.getNodeName() + " "
						// +n.getNodeName() +" " + n.getTextContent());
						String text = n.getTextContent();
						if (text != null) {
							text = text.trim();
							if (text.length() > 0) {
								attributes.put(n.getNodeName(), ParseElement.parsePropertyTokens(text, systemProperties));
							}
						}
					}
				} else if (n.getAttributes().getLength() == 1) { //
					Node att = n.getAttributes().getNamedItem("value");
					if (att != null && isOnlyTextNode(n)) {
						String text = att.getTextContent();
						if (text != null) {
							text = text.trim();
							if (text.length() > 0) {
								attributes.put(n.getNodeName(), ParseElement.parsePropertyTokens(text, systemProperties));
							}
						}
					}
				}
			}
		}
		return attributes;
	}

	public static boolean isOnlyTextNode(Node node) {
		NodeList nodeList = node.getChildNodes();
		Node n = null;
		for (int i = 0, size = nodeList.getLength(); i < size; i++) {
			n = nodeList.item(i);
			if (n.getNodeType() != Node.TEXT_NODE) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 从节点中获取指定配置, 例如: 从generator节点中获取database的配置写法如下:<br />
	 * &lt;generator database="mysql"&gt;&lt;/generator&gt; <br />
	 * &lt;generator
	 * &gt;&lt;database&gt;mysql&lt;/database&gt;&lt;/generator&gt; <br />
	 * &lt;generator &gt;&lt;database value="mysql" /&gt;&lt;/generator&gt;
	 * <br />
	 * &lt;generator &gt;&lt;database att="mysql" /&gt;&lt;/generator&gt; <br />
	 * &lt;generator &gt;&lt;database attribute="mysql" /&gt;&lt;/generator&gt;<br />
	 * 
	 * @param element
	 * @param tagName
	 *            先从Attribute中找属性,如果Attribute为null然后再子节点中找.
	 * @return
	 * @throws ParserConfigurationException
	 */
	public static String getConfiguration(Element element, String tagName,Properties systemProperties) {
		if (element == null)
			return null;
		String text = getAttribute(element, tagName,systemProperties);
		if (text == null) {
			Element ele = getElementsByTagName(element, tagName);
			if (ele != null) {
				text = getTextContent(ele ,systemProperties);
				if (text == null) {
					String a[] = "value;att;attribute".split(";");
					for (String t : a) {
						text = getAttribute(ele, t,systemProperties);
						if (text != null) {
							break;
						}
					}
				}
			}
		}
		return text;
	}
	
	public static Properties getConfigurations(Element element,Properties systemProperties) {
		Properties answer =parseAttributes(element, systemProperties);
		NodeList nodeList = element.getChildNodes();
		Node node = null;
		for( int i=0, size = nodeList.getLength(); i < size ; i++ ){
			node = nodeList.item(i);
			if ( node.getNodeType() == Node.ELEMENT_NODE ){
				String nodeName = node.getNodeName() ;
				String value = getConfiguration(element, nodeName, systemProperties);
				if( value != null ){
					answer.put(nodeName, value);
				}
			}
		}
		return answer ;
	}
	
	
	public static List<String> getConfigurationList(Element element, String tagName, Properties systemProperties) {
		if (element == null)
			return null;
		List<String> list = new ArrayList<String>();
		NodeList runtimes = element.getChildNodes();
		for (int r = 0,rSize=runtimes.getLength(); r <rSize ; r++  ) {
			Node rNode = runtimes.item(r);
			if ( rNode.getNodeType() == Node.ELEMENT_NODE ){
				Element ele = (Element)rNode;
				if( tagName .equalsIgnoreCase( rNode.getNodeName() )){
					String text = getTextContent(ele,systemProperties);
					if (text == null) {
						String a[] = "value;att;attribute".split(";");
						for (String t : a) {
							text = getAttribute(ele, t,systemProperties);
							if (text != null) {
								text = parsePropertyTokens(text, systemProperties);
								list.add(text);
								break;
							}
						}
					}else{
						text = parsePropertyTokens(text, systemProperties);
						list.add(text);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取属性
	 * 
	 * @param element
	 * @param att
	 * @return
	 */
	public static String getAttribute(Element element, String att, Properties systemProperties) {
		String text = element.getAttribute(att);
		if (text == null)
			return null;
		else {
			text = text.trim();
			if (text.length() == 0)
				return null;
			
			text = parsePropertyTokens(text, systemProperties);
			return text;
		}
	}

	/**
	 * 从节点中获取内容
	 * 
	 * @param element
	 * @return
	 */
	public static String getTextContent(Element element,Properties systemProperties) {
		if (element == null)
			return null;

		String text = element.getTextContent();
		if (text != null) {
			text = text.trim();
			if (text.length() == 0){
				return null;
			}else{
				text = parsePropertyTokens(text, systemProperties);
				return text;
			}
		} else
			return null;
	}

	/**
	 * 从节点中按照名称获取一个新的节点(如果有多个节点取第一个节点)
	 * 
	 * @param element
	 * @param tagname
	 * @return
	 */
	public static Element getElementsByTagName(Element element, String tagname) {
		if (element == null || tagname == null)
			return null;

		NodeList nodeList = element.getElementsByTagName(tagname);
		if (nodeList != null && nodeList.getLength() <= 0)
			return null;
		else
			return (Element) nodeList.item(0);
	}
}
