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
			buffer.append("<strong style=\"font-family: '微软雅黑';\">欢迎注册pdd养成计划</strong></div>");
			buffer.append("<div style=\"background-color: #f8f8ee; line-height: 30px;\">");
			buffer.append("<div style=\"text-indent: 2em;\">尊敬的用户请点击以下链接完成注册:</div>");
			buffer.append("<div style=\"text-indent: 4em;\"><a href=\""+registUrl+"\">"+registUrl+"</a></div>");
			buffer.append("<div style=\"text-align: center;\">如有任何问题请联系博主邮箱:871080854@qq.com</div>");
			buffer.append("<div style=\"text-align: center;\">如非本人操作请忽略此邮件</div>");
			String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			buffer.append("<div><div style=\"text-align: right;\">发送人:pdd养成计划 &nbsp; &nbsp;</div><div style=\"text-align: right;\">发送时间:"+date+" &nbsp;&nbsp;</div>");
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
		buffer.append("<strong style=\"font-family: '微软雅黑';\">pdd养成计划-消息提醒</strong></div>");
		buffer.append("<div style=\"background-color: #f8f8ee; line-height: 30px;\">");
		buffer.append("<div style=\"text-indent: 2em;\">"+fsr+" 回复了您的评论:</div>");
		buffer.append("<div style=\"text-indent: 4em;\"><a href=\""+location+"\">点此查看</a></div>");
		buffer.append("<div style=\"text-align: center;\"><p>您也可以复制链接打开:"+location+"</p></div>");
		buffer.append("<div style=\"text-align: center;\">如有任何问题请联系博主邮箱:871080854@qq.com</div>");
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		buffer.append("<div><div style=\"text-align: right;\">发送人:pdd养成计划 &nbsp; &nbsp;</div><div style=\"text-align: right;\">发送时间:"+date+" &nbsp;&nbsp;</div>");
		buffer.append("</div></div></div></div>");
		return buffer;
	}
	
}
