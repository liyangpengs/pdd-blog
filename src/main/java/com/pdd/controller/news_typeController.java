package com.pdd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.pdd.bean.news_type;
import com.pdd.service.news_typeService;
import com.pdd.vo.JsonData;

@Controller
@Scope("prototype")
public class news_typeController {
	@Autowired
	private news_typeService ns;
	@RequestMapping("/getNews_type")
	@ResponseBody
	public JsonData getnews_type(){
		JsonData json=new JsonData();
		try {
			List<news_type> lsn=ns.getNewsType();
			json.setData(JSON.toJSONString(lsn));
		} catch (Exception e) {
			json.setCode(100);
			e.printStackTrace();
		}
		return json;
	}
}
