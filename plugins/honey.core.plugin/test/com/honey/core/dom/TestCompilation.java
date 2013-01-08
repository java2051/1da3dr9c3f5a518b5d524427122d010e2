package com.honey.core.dom;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.honey.compilation.sql.NewLineScript;
import com.honey.compilation.sql.SqlColumn;
import com.honey.compilation.sql.SqlCompilation;
import com.honey.compilation.sql.ddl.AlterTableAddPrimaryKey;
import com.honey.compilation.sql.dml.Select;
import com.honey.compilation.xml.Document;
import com.honey.compilation.xml.XmlElement;
import com.honey.compilation.java.Clazz;
import com.honey.compilation.java.ClassDecoration;
import com.honey.compilation.java.CompilationJavaFactory;
import com.honey.compilation.java.JavaVisibility;
import com.honey.core.compiler.Compilation;
import com.honey.core.compiler.CompilationException;
import com.honey.core.utils.ObjectFactory;
import com.honey.core.utils.RandomStringUtils;

public class TestCompilation extends TestCase {
	public void test0() throws CompilationException{
		List<Compilation> cc = new ArrayList<Compilation>();
		cc.add(getJavaClass());
		cc.add(getSQL());
		cc.add(getXML());
		for(Compilation c : cc ){
			String s=  c.compiledContent().toString();
			System.out.println(s);
		}
	}
	
	
	private Compilation getJavaClass() throws CompilationException{
		String testPackage="com.honey.core.dom.java.";
		String testName = RandomStringUtils.randomAlphabetic(8).toLowerCase();
		
		Clazz clazz =  CompilationJavaFactory.getGeneralTopLevelClass(testPackage + testName);
		clazz.setClassDecoration(new ClassDecoration(JavaVisibility.DEFAULT ));
		return clazz;
	}
	
	private Compilation getSQL() throws CompilationException{
		SqlCompilation sql = new SqlCompilation(null);
		sql.addFileComment("comments");
		
		Select select = new Select("table");
		select.addColumn(new SqlColumn("column"));
		sql.addSqlScript(select);
		
		sql.addSqlScript(new NewLineScript(2));
		
		String tableName = RandomStringUtils.randomAlphabetic(4);
		AlterTableAddPrimaryKey alterTableAddPrimaryKey = new AlterTableAddPrimaryKey(tableName);
		String cloumnName = RandomStringUtils.randomAlphabetic(4);
		SqlColumn column = new SqlColumn(cloumnName,Types.VARCHAR);
		alterTableAddPrimaryKey.addColumn(column);
		sql.addSqlScript(alterTableAddPrimaryKey);
		
		return sql ;
	}
	
	private Compilation getXML() throws CompilationException{
		String publicId = "-//mybatis.org//DTD Config 3.0//EN";
		String systemId="http://mybatis.org/dtd/mybatis-3-config.dtd";
		Document doc = new Document(null,publicId,systemId);
		XmlElement rootElement = new XmlElement("configuration");
		doc.setRootElement(rootElement);
		return doc;
	}
}
