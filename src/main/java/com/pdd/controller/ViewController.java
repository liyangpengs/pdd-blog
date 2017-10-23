package com.pdd.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class ViewController {
	
	@RequestMapping("/view/{path1}/{path2}")
	public String getVIew(@PathVariable("path1")String path1,@PathVariable("path2")String path2){
		return path1+"/"+path2;
	}
	
	@RequestMapping("/admin")
	public String adminView(){
		return "admin/login";
	}
}
