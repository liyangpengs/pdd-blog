package com.pdd.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdd.bean.news;
import com.pdd.dao.newsDao;
import com.pdd.service.newsService;

@Service
public class newsServiceImpl implements newsService{
	
	@Autowired
	private newsDao bdao;
	
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
}
