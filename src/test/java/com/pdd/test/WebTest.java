package com.pdd.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pdd.utils.JedisUtil;

public class WebTest {

	@Test
	public void test() {
		ApplicationContext application=new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
		JedisUtil jedis=application.getBean(JedisUtil.class);
		String key="pdd123";
		String value=jedis.get(key);
		jedis.hset(key, value);
		jedis.del(key);
	}
}
