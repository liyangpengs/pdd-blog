package com.pdd.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class WebTest {

	@Test
	public void test() {
//		try {
//			Configuration config=new Configuration();
//			config.setClassForTemplateLoading(this.getClass(), "/");
//			config.setDefaultEncoding("UTF-8");
//			Template template=config.getTemplate("booksTemplate.ftl");
//			Map<String, Object> map=new HashMap<String, Object>();
//			map.put("title", "这是标题");
//			map.put("author", "李阳鹏");
//			map.put("publishTime", "2017-09-20 12:33:11");
//			map.put("news_type", "java");
//			map.put("keyword", "关键字,java");
//			map.put("content", "<p>这是内容</p>");
//			Writer write=new OutputStreamWriter(new FileOutputStream("/data/success.html"),"UTF-8");
//			template.process(map, write);
//			write.flush();
//			write.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String [] str={"1","2","3","1"};
		for (int i = 0; i < str.length; i++) {
			if(str[i].equals("1")){
				str[i]="";
			}
		}
		for (String string : str) {
			System.err.println(string);
		}
	}
}
