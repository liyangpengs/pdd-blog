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
	public Map<String, String> register(String name,String pwd,String nickname,String phone,String email,HttpServletRequest request){
		Map<String, String> map=new HashMap<String, String>();
		map.put("code", "200");
		map.put("msg", "����!��֤�ʼ������Ѿ�����������ע��������,��ȥ���������֤��~~");
		String uuid=UUID.randomUUID().toString();
		//�ж��û����Ƿ��Ѿ�����
		if(redis.get(name)!=null){
			map.put("code", "101");
			map.put("msg", "�Բ���,���û����Ѿ�����");
			return map;
		}else{
			//ע����Ϣֻ����5���� ��ʱ����Ϊ��ע��
			redis.StrSet(name, "�û���ע���ַΪ:"+request.getHeader("x-forwarded-for"),60*5);
			String registInfo="name:"+name+",pwd:"+pwd+",nickname:"+nickname+",phone:"+phone+",email:"+email+",registerIp:"+request.getHeader("x-forwarded-for");
			redis.StrSet(uuid, registInfo,60*5);
		}
		try {
			//�����ʼ�
			SendMail.SendMail(Base64.getEncoder().encodeToString(uuid.getBytes("utf-8")), email);
		} catch (Exception e) {
			e.printStackTrace();
			redis.del(name);
			redis.del(uuid);
			map.put("code", "101");
			map.put("msg", "�ʼ�����ʧ��,��ע��������������Ƿ���ȷ!");
		}
		return map;
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
			String keys= new String(Base64.getDecoder().decode(urlCode),"utf-8");
			if(redis.get(keys)==null){
				reponseStr="<script type='text/javascript'>alert('ע����Ϣ��ʧЧ!');location='/';</script>";
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
				redis.del(keys);//ɾ��
				redis.StrSet(u.getSname(), u.toString());
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
	 * ע��
	 * @param name �˺�
	 * @param pwd  ����
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
			msg="�û���������";
		}else{
			User user=us.login(name, MD5.GetMD5Code(pwd));
			if(user==null){
				code="102";
				msg="�û������������";
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
