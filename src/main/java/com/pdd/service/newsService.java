package com.pdd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pdd.bean.news;

@Service
public interface newsService {
	List<news> getbooks();
	int addNews(news news);
	List<news> getHot();
}
