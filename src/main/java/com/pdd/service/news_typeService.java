package com.pdd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pdd.bean.news_type;

@Service
public interface news_typeService {
		public List<news_type> getNewsType();
}
