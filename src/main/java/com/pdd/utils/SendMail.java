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
	 * �����ʼ�
	 * @param content �ʼ�����
	 * @param toAddress �ʼ���ַ
	 */
	public static void SendMail(String title,String toAddress,StringBuffer buffer) throws Exception{
		 // ���÷����ʼ��Ļ�������
        final Properties props = new Properties();
			props.load(SendMail.class.getResourceAsStream("/email.properties"));
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

			// �����ʼ�����
			message.setSubject(title);
			
			// �����ʼ���������
			message.setContent(buffer.toString(),"text/html;charset=UTF-8");
			// �����ʼ�
			Transport.send(message);
	}
}
