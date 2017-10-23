package com.pdd.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdd.bean.news_type;
import com.pdd.dao.news_typeDao;
import com.pdd.service.news_typeService;

@Service
public class news_typeServiceImpl implements news_typeService{
	@Autowired
	private news_typeDao tdao;
	
	public List<news_type> getNewsType() {
		return tdao.getnewsType();
	}
}
