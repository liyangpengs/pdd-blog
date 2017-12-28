package com.pdd.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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
import com.pdd.utils.MD5;
import com.pdd.utils.SendMail;
import com.pdd.utils.SerializableUtil;
import com.pdd.utils.UserHeadRandomUtil;
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
	public JsonData register(User user,HttpServletRequest request){
		JsonData json=new JsonData();
		Map<String, String> map=new HashMap<String, String>();
		json.setMassage("您好!认证邮件我们已经发送至您的注册邮箱啦,快去邮箱完成认证吧~~");
		//判断用户名是否已经存在
		if(redis.get(user.getSname())!=null){
			json.setCode(101);
			json.setMassage("对不起,此用户名已经存在");
			return json;
		}else{
			//注册信息只缓存5分钟 超时则视为不注册
			redis.StrSet(user.getSname(), "用户名注册地址为:"+request.getHeader("x-forwarded-for"),60*5);
			user.setRegisterIp(request.getHeader("x-forwarded-for"));
			String registInfo=SerializableUtil.Serialize(user);
			redis.StrSet(MD5.GetMD5Code(user.getSname()), registInfo,60*5);
		}
		try {
			//发送邮件
			SendMail.SendMail(MD5.GetMD5Code(user.getSname()), user.getSemail());
		} catch (Exception e) {
			e.printStackTrace();
			redis.del(user.getSname());
			redis.del(MD5.GetMD5Code(user.getSname()));
			json.setCode(100);
			json.setMassage("邮件发送失败,请注意您输入的邮箱是否正确!");
		}
		return json;
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
			String keys= urlCode;
			if(redis.get(keys)==null){
				reponseStr="<script type='text/javascript'>alert('注册信息已失效!');location='/';</script>";
			}else{
				String str=redis.get(keys);
				User u=(User)SerializableUtil.deSerialize(str);
				u.setRegisdate(new Date());
				u.setUserHead(UserHeadRandomUtil.getHeadImg());//随机获取用户头像
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
		} catch (UnknownAccountException e) {
			data.setCode(100);
			data.setMassage("账号不存在!");
		}catch(IncorrectCredentialsException e){
			data.setCode(100);
			data.setMassage("密码错误,连续输入错误超过5次账号将被锁定!");
		}
		catch(ExcessiveAttemptsException e){
			data.setCode(100);
			data.setMassage("账号已被锁定,暂时无法登陆!");
		}
		return data;
	}
}
