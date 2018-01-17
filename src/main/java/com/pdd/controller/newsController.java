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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.baidu.ueditor.ActionEnter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pdd.bean.User;
import com.pdd.bean.news;
import com.pdd.service.newsService;
import com.pdd.utils.EliminateCacheImg;
import com.pdd.utils.MD5;
import com.pdd.utils.generateStaticHtml;
import com.pdd.utils.xssUtils;
import com.pdd.vo.JsonData;

@Controller
@Scope("prototype")
public class newsController {
	@Autowired
	private newsService bs;
	
	@RequestMapping("/getListNews")
	@ResponseBody
	public JsonData getnews(String type,Integer pageNum,Integer pageSize,String keyword){
		JsonData data=new JsonData();
		try {
			PageHelper.startPage(pageNum, pageSize);
			List<news> newsList=bs.getbooks(type, "true",keyword);
			PageInfo<news> pageinfo=new PageInfo<>(newsList);
			String str=JSON.toJSONString(pageinfo.getList());
			data.setData(str);
		} catch (Exception e) {
			data.setCode(100);
			e.printStackTrace();
		}   
		return data;
	}
	
	@RequiresPermissions("admin:addnews")
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
	
	@RequiresPermissions("admin:addnews")
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
	@RequiresPermissions("admin:addnews")
	@RequestMapping("/generate")
	@ResponseBody
	public JsonData generateHtml(news news,HttpServletRequest request,String content){
		JsonData json=new JsonData();
		try {
			String [] cacheimg=String.valueOf(request.getSession().getAttribute("ImgCache")).split(",");
			news.setAuthor((User)SecurityUtils.getSubject().getPrincipal());
			news.setContent(xssUtils.xssFilter(content));
			String startPath=request.getServletContext().getRealPath("/data/");
			String fileName=String.valueOf(new Date().getTime())+".html";
			news.setUrl("/data/"+fileName);
			int index=bs.addNews(news);
			if(index>0){
				Map<String, String> map=new HashMap<String, String>();
				map.put("title", news.getTitle());
				map.put("author", news.getAuthor().getSnickName());
				map.put("publishTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(news.getPublishtime()));
				map.put("news_type", news.getNews_type().getTname());
				map.put("keyword", news.getKeyword());
				map.put("content", content);
				map.put("selfInfo", "{\"nid\":\""+news.getNid()+"\",\"title\":\""+news.getTitle()+"\",\"url\":\""+news.getUrl()+"\",\"desc\":\""+news.getDescs()+"\",\"img\":\""+news.getImgUrl()+"\"}");
				map.put("descs", news.getDescs());
				generateStaticHtml.generate(map, startPath,fileName);
				for (int i = 0; i < cacheimg.length; i++) {
					if(news.getImgUrl().contains(cacheimg[i])||content.contains(cacheimg[i])){
						cacheimg[i]="";
					}
				}
				//清空缓存图片
				EliminateCacheImg.DelImg(cacheimg, request.getSession());
			}
		} catch (Exception e) {
			json.setCode(100);
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/getHotNews")
	public void getHotNews(String callback,HttpServletResponse reponse){
		try {
			List<news> newsList=bs.getHot();
			String str=JSON.toJSONString(newsList);
			PrintWriter out=reponse.getWriter();
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
	
	@RequiresPermissions("admin:newsList:select")
	@RequestMapping(value="/getAllNews")
	@ResponseBody
	public JsonData getAllnews(Integer pageNum,Integer pageSize,String keyword){
		JsonData json=new JsonData();
		PageHelper.startPage(pageNum, pageSize);
		List<news> newsList=bs.getbooks(null,null,keyword);
		PageInfo<news> pageinfo=new PageInfo<>(newsList);
		json.setData(pageinfo.getList());
		json.setPageInfo(new com.pdd.vo.PageInfo(pageinfo.getPages(), pageinfo.getTotal(), pageNum, pageSize));
		try {
		} catch (Exception e) {
			json.setCode(100);
			e.printStackTrace();
		}   
		return json;
	}
	
	@RequiresPermissions("admin:newsList:upvisible")
	@RequestMapping("/NewsVisible")
	@ResponseBody
	public JsonData updateNewsVisible(Integer nid,Boolean open){
		JsonData json=new JsonData();
		try {
			Integer AffectedRow=bs.updateNewsVisible(nid,open==true?1:0);
			if(AffectedRow<1){
				json.setCode(100);
			}
		} catch (Exception e) {
			json.setCode(100);
			e.printStackTrace();
		}
		return json;
	}
	
	@RequiresPermissions("admin:newsList:upistop")
	@RequestMapping("/updateNewsIstop")
	@ResponseBody
	public JsonData updateNewsIstop(Integer nid,Boolean open){
		JsonData json=new JsonData();
		try {
			Integer AffectedRow=bs.updateNewsIstop(nid,open==true?1:0);
			if(AffectedRow<1){
				json.setCode(100);
			}
		} catch (Exception e) {
			json.setCode(100);
			e.printStackTrace();
		}
		return json;
	}
	
	@RequiresPermissions("admin:newsList:delete")
	@RequestMapping("/deleteNews")
	@ResponseBody
	public JsonData deleteNews(@RequestBody List<String> nid){
		JsonData json=new JsonData();
		System.out.println(nid);
		try {
			Integer AffectedRow=bs.deleteNews(nid);
			if(AffectedRow<1){
				json.setCode(100);
			}
		} catch (Exception e) {
			json.setCode(100);
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/dy/feed.rss")
	public void del(HttpServletResponse response){
		response.setContentType("text/xml; charset=UTF-8");
		try {
			PrintWriter out=response.getWriter();
			out.println(bs.getNewsRSS());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
