package com.pdd.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.pdd.bean.news;
import com.pdd.utils.RSSUtil;

public class WebTest {

	@Test
	public void test() {
		List<news> list=new ArrayList<>();
		news news=new news();
		news.setTitle("123");
		news.setUrl("http:www.baidu.com");
		news.setContent("ÄÚÈÝ");
		news.setPublishtime(new Date());
		list.add(news);
		RSSUtil.createRSS(list);
	}
}
