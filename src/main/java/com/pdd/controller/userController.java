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
	 * ע�᷽��
	 * @param name �û���
	 * @param pwd  ����
	 * @param nickname �ǳ�
	 * @param phone �ֻ���
	 * @param email ����
	 * @param request ��������Ӧ
	 * @return
	 */
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	@ResponseBody
	public JsonData register(User user,HttpServletRequest request){
		JsonData json=new JsonData();
		Map<String, String> map=new HashMap<String, String>();
		json.setMassage("����!��֤�ʼ������Ѿ�����������ע��������,��ȥ���������֤��~~");
		//�ж��û����Ƿ��Ѿ�����
		if(redis.get(user.getSname())!=null){
			json.setCode(101);
			json.setMassage("�Բ���,���û����Ѿ�����");
			return json;
		}else{
			//ע����Ϣֻ����5���� ��ʱ����Ϊ��ע��
			redis.StrSet(user.getSname(), "�û���ע���ַΪ:"+request.getHeader("x-forwarded-for"),60*5);
			user.setRegisterIp(request.getHeader("x-forwarded-for"));
			String registInfo=SerializableUtil.Serialize(user);
			redis.StrSet(MD5.GetMD5Code(user.getSname()), registInfo,60*5);
		}
		try {
			//�����ʼ�
			SendMail.SendMail(MD5.GetMD5Code(user.getSname()), user.getSemail());
		} catch (Exception e) {
			e.printStackTrace();
			redis.del(user.getSname());
			redis.del(MD5.GetMD5Code(user.getSname()));
			json.setCode(100);
			json.setMassage("�ʼ�����ʧ��,��ע��������������Ƿ���ȷ!");
		}
		return json;
	}
	/**
	 * ��֤ע��
	 * @param urlCode MD5�����ַ���
	 * @param session Session
	 * @param reponse ��������Ӧ
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
				reponseStr="<script type='text/javascript'>alert('ע����Ϣ��ʧЧ!');location='/';</script>";
			}else{
				String str=redis.get(keys);
				User u=(User)SerializableUtil.deSerialize(str);
				u.setRegisdate(new Date());
				u.setUserHead(UserHeadRandomUtil.getHeadImg());//�����ȡ�û�ͷ��
				SecureRandomNumberGenerator srng=new SecureRandomNumberGenerator();
				String salt=srng.nextBytes().toHex();
				String password=new Md5Hash(u.getSpwd(), u.getSname()+"%%shiro-pdd-java.top%%"+salt, 1).toHex();
				u.setSpwd(password);
				u.setSalt(salt);//��ֵ����
				us.regis(u);//ע��
				us.addRole(u.getSid());//Ĭ��Ȩ��
				session.setAttribute("userKey", u);
				redis.del(keys);//ɾ��
				redis.StrSet(u.getSname(), SerializableUtil.Serialize(u));
				reponseStr="<script type='text/javascript'>alert('�û�:"+u.getSname()+" ����,����ע��ɹ�!');location='/';</script>";
			}
		} catch (Exception e) {
			e.printStackTrace();
			reponseStr="<script type='text/javascript'>alert('ע��ʧ��!����ϵ����Ա!');location='/';</script>";
		}
		out.println(reponseStr);
		out.flush();
	}
	/**
	 * ��¼
	 * @param name �˺�
	 * @param pwd  ����
	 * @return
	 */
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	@ResponseBody
	public JsonData login(String name,String pwd){
		//��¼��֤
		UsernamePasswordToken token=new UsernamePasswordToken(name, pwd);
		JsonData data=new JsonData();
//		//��¼
		try {
			SecurityUtils.getSubject().login(token);
			User user=(User)SecurityUtils.getSubject().getPrincipal();//�õ���¼��Ϣ
			Map<String,String> map=new HashMap<String,String>();
			map.put("userHead", user.getUserHead());
			map.put("snickName", user.getSnickName());
			data.setData(map);
		} catch (UnknownAccountException e) {
			data.setCode(100);
			data.setMassage("�˺Ų�����!");
		}catch(IncorrectCredentialsException e){
			data.setCode(100);
			data.setMassage("�������,����������󳬹�5���˺Ž�������!");
		}
		catch(ExcessiveAttemptsException e){
			data.setCode(100);
			data.setMassage("�˺��ѱ�����,��ʱ�޷���½!");
		}
		return data;
	}
}
