package com.pdd.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pdd.bean.news;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedOutput;

public class RSSUtil {
	
	public static String createRSS(List<news> newsList){
		Channel channel=new Channel("rss_2.0");
		channel.setTitle("pdd养成计划");
		channel.setDescription("这里记录了Pdd在工作中以及闲暇时间接触到的java技术知识!好好学习,天天向上!");
		channel.setLanguage("zh-cn");
		channel.setEncoding("utf-8");
		channel.setPubDate(new Date());
		channel.setLink("https://pdd-java.top");
		List<Item> itmeList=new ArrayList<Item>();
		for (news n : newsList) {
			Item it=new Item();
			it.setAuthor("pdd养成计划");
			it.setTitle(n.getTitle());
			it.setPubDate(n.getPublishtime());
			it.setLink(n.getUrl());
			Description desc=new Description();
			desc.setType("CDATA");
			desc.setValue(n.getContent());
			it.setDescription(desc);
			itmeList.add(it);
		}
		channel.setItems(itmeList);
		WireFeedOutput out=new WireFeedOutput();
		try {
			String xml=out.outputString(channel);
			return xml;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
