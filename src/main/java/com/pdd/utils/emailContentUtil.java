package com.pdd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class emailContentUtil {
	
	public static StringBuffer RegistContentEmail(String registUrl){
		Properties props = new Properties();
		StringBuffer buffer=new StringBuffer();
		try {
			props.load(SendMail.class.getResourceAsStream("/email.properties"));
			registUrl=props.getProperty("mail.host")+registUrl;
			buffer.append("<div style=\"width: 480px;border: 1px solid rgb(234,234,234);font-size: 12px;margin: 0 auto;\">");
			buffer.append("<div><div style=\"height: 40px; border-bottom: 1px solid rgb(234,234,234);background-color: #f8f8ee;text-align: center; padding-top: 15px;\">");
			buffer.append("<strong style=\"font-family: '΢���ź�';\">��ӭע��pdd���ɼƻ�</strong></div>");
			buffer.append("<div style=\"background-color: #f8f8ee; line-height: 30px;\">");
			buffer.append("<div style=\"text-indent: 2em;\">�𾴵��û����������������ע��:</div>");
			buffer.append("<div style=\"text-indent: 4em;\"><a href=\""+registUrl+"\">"+registUrl+"</a></div>");
			buffer.append("<div style=\"text-align: center;\">�����κ���������ϵ��������:871080854@qq.com</div>");
			buffer.append("<div style=\"text-align: center;\">��Ǳ��˲�������Դ��ʼ�</div>");
			String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			buffer.append("<div><div style=\"text-align: right;\">������:pdd���ɼƻ� &nbsp; &nbsp;</div><div style=\"text-align: right;\">����ʱ��:"+date+" &nbsp;&nbsp;</div>");
			buffer.append("</div></div></div></div>");
			return buffer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}
	
	public static StringBuffer CommentContentEmail(String fsr,String location){
		StringBuffer buffer=new StringBuffer();
		buffer.append("<div style=\"width: 480px;border: 1px solid rgb(234,234,234);font-size: 12px;margin: 0 auto;\">");
		buffer.append("<div><div style=\"height: 40px; border-bottom: 1px solid rgb(234,234,234);background-color: #f8f8ee;text-align: center; padding-top: 15px;\">");
		buffer.append("<strong style=\"font-family: '΢���ź�';\">pdd���ɼƻ�-��Ϣ����</strong></div>");
		buffer.append("<div style=\"background-color: #f8f8ee; line-height: 30px;\">");
		buffer.append("<div style=\"text-indent: 2em;\">"+fsr+" �ظ�����������:</div>");
		buffer.append("<div style=\"text-indent: 4em;\"><a href=\""+location+"\">��˲鿴</a></div>");
		buffer.append("<div style=\"text-align: center;\"><p>��Ҳ���Ը������Ӵ�:"+location+"</p></div>");
		buffer.append("<div style=\"text-align: center;\">�����κ���������ϵ��������:871080854@qq.com</div>");
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		buffer.append("<div><div style=\"text-align: right;\">������:pdd���ɼƻ� &nbsp; &nbsp;</div><div style=\"text-align: right;\">����ʱ��:"+date+" &nbsp;&nbsp;</div>");
		buffer.append("</div></div></div></div>");
		return buffer;
	}
	
}
