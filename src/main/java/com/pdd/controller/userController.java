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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pdd.bean.User;
import com.pdd.service.userService;
import com.pdd.utils.JedisUtil;
import com.pdd.utils.SendMail;
import com.pdd.utils.SerializableUtil;
import com.pdd.vo.JsonData;

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
	public Map<String, String> register(User user,HttpServletRequest request){
		Map<String, String> map=new HashMap<String, String>();
		map.put("code", "200");
		map.put("msg", "您好!认证邮件我们已经发送至您的注册邮箱啦,快去邮箱完成认证吧~~");
		String uuid=UUID.randomUUID().toString();
		//判断用户名是否已经存在
		if(redis.get(user.getSname())!=null){
			map.put("code", "101");
			map.put("msg", "对不起,此用户名已经存在");
			return map;
		}else{
			//注册信息只缓存5分钟 超时则视为不注册
			redis.StrSet(user.getSname(), "用户名注册地址为:"+request.getHeader("x-forwarded-for"),60*5);
			user.setRegisterIp(request.getHeader("x-forwarded-for"));
			String registInfo=SerializableUtil.Serialize(user);
			redis.StrSet(uuid, registInfo,60*5);
		}
		try {
			//发送邮件
			SendMail.SendMail(Base64.getEncoder().encodeToString(uuid.getBytes("utf-8")), user.getSemail());
		} catch (Exception e) {
			e.printStackTrace();
			redis.del(user.getSname());
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
				User u=(User)SerializableUtil.deSerialize(str);
				u.setRegisdate(new Date());
				SecureRandomNumberGenerator srng=new SecureRandomNumberGenerator();
				String salt=srng.nextBytes().toHex();
				String password=new Md5Hash(u.getSpwd(), u.getSname()+"%%shiro-pdd-java.top%%"+salt, 1).toHex();
				u.setSpwd(password);
				u.setSalt(salt);//盐值加密
				us.regis(u);//注册
				us.addRole(u.getSid());//默认权限
				session.setAttribute("userKey", u);
				redis.del(keys);//删除
				redis.StrSet(u.getSname(), SerializableUtil.Serialize(u));
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
	 * 登录
	 * @param name 账号
	 * @param pwd  密码
	 * @return
	 */
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	@ResponseBody
	public JsonData login(String name,String pwd){
		//登录验证
		UsernamePasswordToken token=new UsernamePasswordToken(name, pwd);
		JsonData data=new JsonData();
//		//登录
		try {
			SecurityUtils.getSubject().login(token);
			User user=(User)SecurityUtils.getSubject().getPrincipal();//拿到登录信息
			Map<String,String> map=new HashMap<String,String>();
			map.put("userHead", user.getUserHead());
			map.put("snickName", user.getSnickName());
			data.setData(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
