package com.pdd.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pdd.bean.User;
import com.pdd.bean.comment;
import com.pdd.bean.comments;
import com.pdd.service.commentService;
import com.pdd.utils.getAddressUtil;
import com.pdd.utils.xssUtils;
import com.pdd.vo.JsonData;

@Controller
@Scope("prototype")
public class commentController {
	@Autowired
	private commentService cs;
	
	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	@ResponseBody
	public JsonData addComment(@RequestParam(required=true)Integer nid,@RequestParam(required=true)String content,HttpServletRequest request){
		JsonData json=new JsonData();
		try {
			User user=(User)SecurityUtils.getSubject().getPrincipal();
			content=xssUtils.xssFilter(content);
			if(user==null){
				json.setCode(100);
				json.setMassage("您还未登录,暂时无法评论哟");
			}else{
				String ip=request.getHeader("x-forwarded-for");
				if(StringUtils.isEmpty(ip)){
					ip=request.getRemoteAddr();
				}
				comment comment=new comment();
				comment.setPublishIp(ip);
				comment.setPublishCity(getAddressUtil.getAddress(ip));
				comment.setNid(nid);
				comment.setAuthor(user);
				comment.setPublishTime(new Date());
				comment.setContent(content);
				int state=cs.addcomment(comment);
				if(state==0){
					json.setCode(100);
					json.setMassage("评论发布失败.");
				}
			}
		} catch (Exception e) {
			json.setCode(100);
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/getComment")
	@ResponseBody
	public JsonData getComment(Integer nid){
		JsonData json=new JsonData();
		try {
			json.setData(cs.getcomment(nid));
		} catch (Exception e) {
			json.setCode(200);
		}
		return json;
	}
	
	@RequestMapping("/reply")
	@ResponseBody
	public JsonData reply(String ids,String content,HttpServletRequest request){
		JsonData json=new JsonData();
		try {
			content=xssUtils.xssFilter(content);
			User user=(User)SecurityUtils.getSubject().getPrincipal();
			if(user==null){
				json.setCode(100);
				json.setMassage("您还未登录,暂时无法回复评论哟");
			}else{
				String ip=request.getHeader("x-forwarded-for");
				if(StringUtils.isEmpty(ip)){
					ip=request.getRemoteAddr();
				}
				String [] split=ids.split("-");
				comments css=new comments();
				css.setPublishIp(ip);
				css.setPublishCity(getAddressUtil.getAddress(ip));
				css.setContent(content);
				css.setPublishTime(new Date());
				css.setReplyAuthor(user);
				User beuser=new User();
				beuser.setSid(Integer.parseInt(split[0]));
				css.setBereplyAuthor(beuser);
				css.setCid(Integer.parseInt(split[1]));
				int state=cs.addcomments(css);
				if(state==0){
					json.setCode(100);
					json.setMassage("评论回复失败");
				}
			}
		} catch (Exception e) {
			json.setCode(100);
			e.printStackTrace();
		}
		return json;
	}
}
