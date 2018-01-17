package com.pdd.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdd.bean.news;
import com.pdd.dao.newsDao;
import com.pdd.service.newsService;
import com.pdd.utils.JedisUtil;
import com.pdd.utils.RSSUtil;
import com.pdd.utils.SerializableUtil;

@Service
public class newsServiceImpl implements newsService{
	
	@Autowired
	private newsDao bdao;
	@Autowired
	private JedisUtil jedis;
	
	@Override
	public List<news> getbooks(String type,String canSee,String keyWord) {
		return bdao.getbooks(type,canSee,keyWord);
	}
	@Override
	public int addNews(news news) {
		return bdao.addNews(news);
	}
	@Override
	public List<news> getHot() {
		return bdao.getHot();
	}

	@Override
	public Integer updateNewsVisible(Integer nid,Integer status) {
		return bdao.updateNewsVisible(nid,status);
	}
	
	@Override
	public Integer updateNewsIstop(Integer nid,Integer status) {
		return bdao.updateNewsIstop(nid,status);
	}
	
	@Override
	public Integer deleteNews(List<String> nid) {
		return bdao.deleteNews(nid);
	}
	
	@Override
	public String getNewsRSS() {
		if(jedis.get("Rss_feed")==null){
			jedis.StrSet("Rss_feed", SerializableUtil.Serialize(bdao.getNewsRSS()), (60*60*24));//设置RSS缓存 每天更新一次
		}
		List<news> newsList=(List<news>)SerializableUtil.deSerialize(jedis.get("Rss_feed"));
		return RSSUtil.createRSS(newsList);
	}
}
