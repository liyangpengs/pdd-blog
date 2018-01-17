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
		channel.setTitle("pdd���ɼƻ�");
		channel.setDescription("�����¼��Pdd�ڹ������Լ���Ͼʱ��Ӵ�����java����֪ʶ!�ú�ѧϰ,��������!");
		channel.setLanguage("zh-cn");
		channel.setEncoding("utf-8");
		channel.setPubDate(new Date());
		channel.setLink("https://pdd-java.top");
		List<Item> itmeList=new ArrayList<Item>();
		for (news n : newsList) {
			Item it=new Item();
			it.setAuthor("pdd���ɼƻ�");
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
