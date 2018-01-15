package com.pdd.utils;

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
	public static void SendMail(String title,String toAddress,StringBuffer buffer) throws Exception{
		 // 配置发送邮件的环境属性
        final Properties props = new Properties();
			props.load(SendMail.class.getResourceAsStream("/email.properties"));
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

			// 设置邮件标题
			message.setSubject(title);
			
			// 设置邮件的内容体
			message.setContent(buffer.toString(),"text/html;charset=UTF-8");
			// 发送邮件
			Transport.send(message);
	}
}
