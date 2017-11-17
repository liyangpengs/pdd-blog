package com.pdd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeUtility;

public class SendMail {
	/**
	 * 发送邮件
	 * @param content 邮件内容
	 * @param toAddress 邮件地址
	 */
	public static void SendMail(String registUrl,String toAddress) throws Exception{
		 // 配置发送邮件的环境属性
        final Properties props = new Properties();
			props.load(SendMail.class.getResourceAsStream("/email.properties"));
			//拼接验证链接
			registUrl=props.getProperty("mail.host")+registUrl;
	        // 构建授权信息，用于进行SMTP进行身份验证
	        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
			message.setFrom(new InternetAddress(props.getProperty("mail.user"), MimeUtility.encodeText("pdd养成计划")));

			// 设置收件人
			InternetAddress to = new InternetAddress(toAddress);
			message.setRecipient(RecipientType.TO, to);

			// 设置密送至管理员
			InternetAddress cc = new InternetAddress(props.getProperty("mail.cs"));
			message.setRecipient(RecipientType.BCC, cc);

			// 设置邮件标题
			message.setSubject("pdd养成计划注册邮件");
			//设置邮件主题内容
			StringBuffer buffer=new StringBuffer();
			buffer.append("<div style=\"width: 480px;border: 1px solid rgb(234,234,234);font-size: 12px;margin: 0 auto;\">");
			buffer.append("<div><div style=\"height: 40px; border-bottom: 1px solid rgb(234,234,234);background-color: #f8f8ee;text-align: center; padding-top: 15px;\">");
			buffer.append("<strong style=\"font-family: '微软雅黑';\">欢迎注册pdd养成计划</strong></div>");
			buffer.append("<div style=\"background-color: #f8f8ee; line-height: 30px;\">");
			buffer.append("<div style=\"text-indent: 2em;\">尊敬的用户请点击以下链接完成注册:</div>");
			buffer.append("<div style=\"text-indent: 5em;\"><a href=\""+registUrl+"\">"+registUrl+"</a></div>");
			buffer.append("<div style=\"text-align: center;\">如有任何问题请联系博主邮箱:871080854@qq.com</div>");
			buffer.append("<div style=\"text-align: center;\">如非本人操作请忽略此邮件</div>");
			String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			buffer.append("<div><div style=\"text-align: right;\">发送人:pdd养成计划 &nbsp; &nbsp;</div><div style=\"text-align: right;\">发送时间:"+date+" &nbsp;&nbsp;</div>");
			buffer.append("</div></div></div></div>");
			// 设置邮件的内容体
			message.setContent(buffer.toString(),"text/html;charset=UTF-8");
			// 发送邮件
			Transport.send(message);
	}
}
