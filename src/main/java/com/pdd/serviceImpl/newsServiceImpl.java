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

	public List<news> getbooks(String istop,String status) {
		return bdao.getbooks(istop,status);
	}
	
	public int addNews(news news) {
		return bdao.addNews(news);
	}
}