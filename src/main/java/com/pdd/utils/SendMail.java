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
	 * �����ʼ�
	 * @param content �ʼ�����
	 * @param toAddress �ʼ���ַ
	 */
	public static void SendMail(String registUrl,String toAddress) throws Exception{
		 // ���÷����ʼ��Ļ�������
        final Properties props = new Properties();
			props.load(SendMail.class.getResourceAsStream("/email.properties"));
			//ƴ����֤����
			registUrl=props.getProperty("mail.host")+registUrl;
	        // ������Ȩ��Ϣ�����ڽ���SMTP���������֤
	        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // �û���������
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
        Session mailSession = Session.getInstance(props, authenticator);
        // �����ʼ���Ϣ
        MimeMessage message = new MimeMessage(mailSession);
        // ���÷�����
			message.setFrom(new InternetAddress(props.getProperty("mail.user"), MimeUtility.encodeText("pdd���ɼƻ�")));

			// �����ռ���
			InternetAddress to = new InternetAddress(toAddress);
			message.setRecipient(RecipientType.TO, to);

			// ��������������Ա
			InternetAddress cc = new InternetAddress(props.getProperty("mail.cs"));
			message.setRecipient(RecipientType.BCC, cc);

			// �����ʼ�����
			message.setSubject("pdd���ɼƻ�ע���ʼ�");
			//�����ʼ���������
			StringBuffer buffer=new StringBuffer();
			buffer.append("<div style=\"width: 480px;border: 1px solid rgb(234,234,234);font-size: 12px;margin: 0 auto;\">");
			buffer.append("<div><div style=\"height: 40px; border-bottom: 1px solid rgb(234,234,234);background-color: #f8f8ee;text-align: center; padding-top: 15px;\">");
			buffer.append("<strong style=\"font-family: '΢���ź�';\">��ӭע��pdd���ɼƻ�</strong></div>");
			buffer.append("<div style=\"background-color: #f8f8ee; line-height: 30px;\">");
			buffer.append("<div style=\"text-indent: 2em;\">�𾴵��û����������������ע��:</div>");
			buffer.append("<div style=\"text-indent: 5em;\"><a href=\""+registUrl+"\">"+registUrl+"</a></div>");
			buffer.append("<div style=\"text-align: center;\">�����κ���������ϵ��������:871080854@qq.com</div>");
			buffer.append("<div style=\"text-align: center;\">��Ǳ��˲�������Դ��ʼ�</div>");
			String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			buffer.append("<div><div style=\"text-align: right;\">������:pdd���ɼƻ� &nbsp; &nbsp;</div><div style=\"text-align: right;\">����ʱ��:"+date+" &nbsp;&nbsp;</div>");
			buffer.append("</div></div></div></div>");
			// �����ʼ���������
			message.setContent(buffer.toString(),"text/html;charset=UTF-8");
			// �����ʼ�
			Transport.send(message);
	}
}
