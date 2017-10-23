package com.pdd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pdd.bean.news_type;
import com.pdd.service.news_typeService;

@Controller
@Scope("prototype")
public class news_typeController {
	@Autowired
	private news_typeService ns;
	@RequestMapping("/getNews_type")
	@ResponseBody
	public Map<String, String> getnews_type(){
		Map<String, String> map=new HashMap<String, String>();
		try {
			List<news_type> lsn=ns.getNewsType();
			map.put("code", "200");
			map.put("massage", "Success");
			map.put("data", JSON.toJSONString(lsn));
		} catch (Exception e) {
			map.put("code", "100");
			map.put("massage", "Error");
			e.printStackTrace();
		}
		return map;
	}
}
