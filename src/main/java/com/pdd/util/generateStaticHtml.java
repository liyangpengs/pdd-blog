package com.pdd.util;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
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
	public static String generate(Map<String, String> map,String path){
		String FileName=String.valueOf(new Date().getTime())+".html";
		Configuration config=new Configuration();
		config.setClassForTemplateLoading(generateStaticHtml.class, "/");
		config.setDefaultEncoding("UTF-8");
		try {
			Template template=config.getTemplate("booksTemplate.ftl");
			Writer write=new OutputStreamWriter(new FileOutputStream(path+"/"+FileName),"UTF-8");
			template.process(map, write);
			write.flush();
			write.close();
			return "/data/"+FileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
