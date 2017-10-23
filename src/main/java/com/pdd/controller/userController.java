package com.pdd.controller;

import java.io.PrintWriter;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pdd.bean.User;
import com.pdd.service.userService;
import com.pdd.util.JedisUtil;
import com.pdd.util.MD5;
import com.pdd.util.SendMail;

@Controller
@Scope("prototype")
public class userController {
	@Autowired
	private userService us;
	@Autowired
	private JedisUtil redis;
	
	/**
	 * 注册方法
	 * @param name 用户名
	 * @param pwd  密码
	 * @param nickname 昵称
	 * @param phone 手机号
	 * @param email 邮箱
	 * @param request 服务器响应
	 * @return
	 */
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> register(String name,String pwd,String nickname,String phone,String email,HttpServletRequest request){
		Map<String, String> map=new HashMap<String, String>();
		map.put("code", "200");
		map.put("msg", "您好!认证邮件我们已经发送至您的注册邮箱啦,快去邮箱完成认证吧~~");
		String uuid=UUID.randomUUID().toString();
		//判断用户名是否已经存在
		if(redis.get(name)!=null){
			map.put("code", "101");
			map.put("msg", "对不起,此用户名已经存在");
			return map;
		}else{
			//注册信息只缓存5分钟 超时则视为不注册
			redis.StrSet(name, "用户名注册地址为:"+request.getHeader("x-forwarded-for"),60*5);
			String registInfo="name:"+name+",pwd:"+pwd+",nickname:"+nickname+",phone:"+phone+",email:"+email+",registerIp:"+request.getHeader("x-forwarded-for");
			redis.StrSet(uuid, registInfo,60*5);
		}
		try {
			//发送邮件
			SendMail.SendMail(Base64.getEncoder().encodeToString(uuid.getBytes("utf-8")), email);
		} catch (Exception e) {
			e.printStackTrace();
			redis.del(name);
			redis.del(uuid);
			map.put("code", "101");
			map.put("msg", "邮件发送失败,请注意您输入的邮箱是否正确!");
		}
		return map;
	}
	/**
	 * 验证注册
	 * @param urlCode MD5加密字符串
	 * @param session Session
	 * @param reponse 服务器响应
	 */
	@RequestMapping(value="/register/{urlCode}",method=RequestMethod.GET)
	public void register(@PathVariable String urlCode,HttpSession session,HttpServletResponse reponse){
		String reponseStr="";
		reponse.setCharacterEncoding("utf-8");
		reponse.setContentType("text/html;charset=utf-8");
		PrintWriter out=null;
		try {
			out=reponse.getWriter();
			String keys= new String(Base64.getDecoder().decode(urlCode),"utf-8");
			if(redis.get(keys)==null){
				reponseStr="<script type='text/javascript'>alert('注册信息已失效!');location='/';</script>";
			}else{
				String str=redis.get(keys);
				String [] obj=str.split(",");
				User u=new User();
				u.setSname(obj[0].split(":")[1]);
				u.setPwd(MD5.GetMD5Code(obj[1].split(":")[1]));
				u.setSnickName(obj[2].split(":")[1]);
				u.setSphone(obj[3].split(":")[1]);
				u.setSemail(obj[4].split(":")[1]);
				u.setRegisterIp(obj[5].split(":")[1]);
				u.setRegisdate(new Date());
				u.setIsadmin(0);
				us.regis(u);
				System.out.println(u.getSid());
				session.setAttribute("userKey", u);
				redis.del(keys);//删除
				redis.StrSet(u.getSname(), u.toString());
				reponseStr="<script type='text/javascript'>alert('用户:"+u.getSname()+" 您好,您已注册成功!');location='/';</script>";
			}
		} catch (Exception e) {
			e.printStackTrace();
			reponseStr="<script type='text/javascript'>alert('注册失败!请联系管理员!');location='/';</script>";
		}
		out.println(reponseStr);
		out.flush();
	}
	/**
	 * 注册
	 * @param name 账号
	 * @param pwd  密码
	 * @return
	 */
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(String name,String pwd,HttpSession session){
		Map<String, String> map=new HashMap<String, String>();
		String code="";
		String msg="";
		if(redis.get(name)==null){
			code="101";
			msg="用户名不存在";
		}else{
			User user=us.login(name, MD5.GetMD5Code(pwd));
			if(user==null){
				code="102";
				msg="用户名或密码错误";
			}else{
				session.setAttribute("userKey", user);
				code="200";
			}
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
}
