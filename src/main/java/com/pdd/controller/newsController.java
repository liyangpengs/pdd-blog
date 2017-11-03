package com.pdd.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.baidu.ueditor.ActionEnter;
import com.pdd.bean.User;
import com.pdd.bean.news;
import com.pdd.service.newsService;
import com.pdd.util.EliminateCacheImg;
import com.pdd.util.MD5;
import com.pdd.util.generateStaticHtml;

@Controller
@Scope("prototype")
public class newsController {
	@Autowired
	private newsService bs;
	
	@RequestMapping("/getListNews")
	@ResponseBody
	public Map<String, String> getbook(String type){
		Map<String, String> map=new HashMap<String, String>();
		try {
			List<news> newsList=bs.getbooks(type);
			String str=JSON.toJSONString(newsList);
			map.put("status", "200");
			map.put("massage", "success");
			map.put("data", str);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "400");
			map.put("massage", "Error");
		}   
		return map;
	}
	@RequestMapping("/ueditor")
	public void initUeditor(HttpServletResponse response,HttpServletRequest request){
		try {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding( "utf-8" );
			response.setHeader("Content-Type" , "text/html");
			String rootPath = request.getServletContext().getRealPath("/ueditor");
			out.write( new ActionEnter( request, rootPath ).exec() );
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/uploadImg")
	public void uploadImg(MultipartFile upfile,   
            HttpServletRequest request,HttpServletResponse response){
		Map<String,String> map = new HashMap<String,String>();  
		if(null!=upfile&&!upfile.isEmpty()){
			//判断文件类型
			if(upfile.getContentType().equals("image/jpeg")){
				String prifix="static/uploadImg";
				String fileName=MD5.GetMD5Code(String.valueOf(new Date().getTime()))+".jpg";
				String path = request.getServletContext().getRealPath(prifix);		
				File df = new File(path,fileName);
				try {
					FileUtils.copyInputStreamToFile(upfile.getInputStream(), df);
					String filePath="/"+prifix+"/"+fileName;
					//将图片缓存起来
					if(request.getSession().getAttribute("ImgCache")==null){
						request.getSession().setAttribute("ImgCache", fileName);
					//如果已经有缓存则继续添加
					}else{
						String ImgCache=(String)request.getSession().getAttribute("ImgCache");
						ImgCache+=","+fileName;
						request.getSession().setAttribute("ImgCache", ImgCache);
					}
				 	map.put("state", "SUCCESS");  
		            map.put("url",filePath);  
		            map.put("title", "");  
		            map.put("original","");  
				} catch (IOException e) {
					e.printStackTrace();
					map.put("state", "上传失败");
				}
			}else{
				map.put("state", "只能上传jpg图片");
			}
            try {
				response.getWriter().write(JSON.toJSONString(map));
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 生成静态页面 并且入库
	 * @param request
	 */
	@RequestMapping("/generate")
	@ResponseBody
	public Map<String, String> generateHtml(news news,HttpServletRequest request,String content){
		String [] cacheimg=String.valueOf(request.getSession().getAttribute("ImgCache")).split(",");
		news.setAuthor((User)request.getSession().getAttribute("userKey"));
		Map<String, String> map=new HashMap<String, String>();
		try {
			map.put("title", news.getTitle());
			map.put("author", news.getAuthor().getSnickName());
			map.put("publishTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(news.getPublishtime()));
			map.put("news_type", news.getNews_type().getTname());
			map.put("keyword", news.getKeyword());
			map.put("content", content);
			map.put("descs", news.getDescs());
			String path=request.getServletContext().getRealPath("/data/");
			String html=generateStaticHtml.generate(map, path);
			if(null!=html&&html.startsWith("/data/")){
				news.setUrl(html);
				news.setStatus(1);
				for (int i = 0; i < cacheimg.length; i++) {
					if(news.getImgUrl().contains(cacheimg[i])||content.contains(cacheimg[i])){
						cacheimg[i]="";
					}
				}
				//清空缓存图片
				EliminateCacheImg.DelImg(cacheimg, request.getSession());
			}
			int idnex=bs.addNews(news);
			//返回状态
			if(idnex>0){
				map.clear();
				map.put("code", "200");
				map.put("Massage", "Success");
			}else{
				map.clear();
				map.put("code", "100");
				map.put("Massage", "Error");
			}
		} catch (Exception e) {
			map.clear();
			map.put("code", "100");
			map.put("Massage",e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/getHotNews")
	public void getHotNews(String callback,HttpServletResponse reponse){
		try {
			List<news> newsList=bs.getHot();
			String str=JSON.toJSONString(newsList);
			PrintWriter out=reponse.getWriter();
			//前台后台分开使用接口数据
			if(callback!=null&&!callback.isEmpty()){
				out.print(callback+"("+str+")");
			}else{
				out.print(str);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
