package com.pdd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pdd.bean.news;

public interface newsDao {
	List<news> getbooks(@Param("type")String type);
	int addNews(news news);
	List<news> getHot();
}
