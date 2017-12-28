package com.pdd.utils;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class generateStaticHtml {
	/**
	 * Éú³É¾²Ì¬Ò³Ãæ
	 * @param map
	 * @param path
	 * @return
	 */
	public static void generate(Map<String, String> map,String path,String fileName) throws Exception{
		Configuration config=new Configuration();
		config.setClassForTemplateLoading(generateStaticHtml.class, "/");
		config.setDefaultEncoding("UTF-8");
		Template template=config.getTemplate("booksTemplate.ftl");
		Writer write=new OutputStreamWriter(new FileOutputStream(path+"/"+fileName),"UTF-8");
		template.process(map, write);
		write.flush();
		write.close();
	}
}
