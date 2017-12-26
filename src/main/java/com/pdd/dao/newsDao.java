package com.pdd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pdd.bean.news;

public interface newsDao {
	List<news> getbooks(@Param("type")String type,@Param("canSee")String canSee,@Param("keyword")String keyword);
	int addNews(news news);
	List<news> getHot();
	Integer updateNewsVisible(@Param("nid")Integer nid,@Param("status")Integer status);
	Integer updateNewsIstop(@Param("nid")Integer nid,@Param("status")Integer status);
	Integer deleteNews(List<String> nid);
}
