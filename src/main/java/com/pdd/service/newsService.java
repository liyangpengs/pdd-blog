package com.pdd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pdd.bean.news;

@Service
public interface newsService {
	List<news> getbooks(String type,String canSee,String keyWord);
	int addNews(news news);
	List<news> getHot();
	Integer updateNewsVisible(Integer nid,Integer status);
	Integer updateNewsIstop(Integer nid,Integer status);
	Integer deleteNews(List<String> nid);
}
