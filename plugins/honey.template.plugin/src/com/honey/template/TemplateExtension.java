package com.honey.template;

import org.java.plugin.Plugin;

import com.honey.core.Extension;
import com.honey.core.compiler.CompilationDescriptor;
import com.honey.core.dbmapping.introspect.IntrospectSchema;
import com.honey.core.dbmapping.introspect.IntrospectSchemaColumn;
import com.honey.core.generator.CodeCompilation;

public  class TemplateExtension extends Extension<Plugin> implements CodeCompilation{
	
	public TemplateExtension(Plugin belongPlugin) {
		super(belongPlugin);
	}

	@Override
	public CompilationDescriptor[] compilation(IntrospectSchema schema, IntrospectSchemaColumn columns) {
		System.out.println("00000000000000000000000000");
		
		
		
		return null;
	}
	
	@Override
	public void clean() {
		
	}
}
