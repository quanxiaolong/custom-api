/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.email.impl;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.CollectionUtils;

import cn.com.custom.api.email.IMailSender;
import cn.com.custom.api.email.exception.MailException;
import cn.com.custom.api.email.model.Mail;

/**
 * <p>标题：MailSenderImpl </p>
 * <p>
 *    功能描述：
 * </p>
 * <p>创建日期：2017年1月18日下午1:55:04</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class MailSenderImpl implements IMailSender, InitializingBean {
	private static final Logger log = org.slf4j.LoggerFactory
			.getLogger(MailSenderImpl.class);
	
	private int poolSize = 5;
	private static Executor executor;
	private JavaMailSenderImpl javaMailSender;
	private Boolean auth=true;
	private Integer timeOut=25000;
	@Override
	public void sendMail(Mail mail) throws MailException {
		send(mail);
	}

	@Override
	public void sendMailAsync(final Mail mail) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					send(mail);
				} catch(Exception ex) {
					log.error("邮件发送失败", ex);
				}
			}
		};
		executor.execute(task);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		executor  = Executors.newFixedThreadPool(poolSize);
	}
	
	/**
	 * 发送邮件
	 * @date 2016年5月19日上午11:01:25
	 * @author QuanXiaolong
	 * @param mail
	 * @throws MailException
	 */
	private void send(Mail mail) throws MailException{
		if(mail==null||!mail.isValid())
			return;
		try {
			MimeMessage message = javaMailSender.createMimeMessage();		
			MimeMessageHelper msgHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			msgHelper.setFrom(mail.getFromAddress());
			msgHelper.setTo(mail.getToAddress().toArray(new InternetAddress[0]));
			msgHelper.setSubject(mail.getSubject());
			msgHelper.setText(mail.getPlainText(), mail.getHtmlText());
			
			Map<String,String> mapPictures=mail.getPictures();
			Map<String,String> mapAttachments=mail.getAttachments();
			if(!CollectionUtils.isEmpty(mapPictures)){
				Set<Entry<String, String>> picEntry=mapPictures.entrySet();
				for(Entry<String, String>mapItem:picEntry){
					String picCode=mapItem.getKey();
					String picAddr=mapItem.getValue();
					if(StringUtils.isEmpty(picCode)||StringUtils.isEmpty(picAddr)){
						continue;
					}
					File picFile=new File(picAddr);
					if(picFile.isFile()&&picFile.exists()){
						FileSystemResource img = new FileSystemResource(picFile);  
						msgHelper.addInline(picCode, img); 
					}
				}
			}
			if(!CollectionUtils.isEmpty(mapAttachments)){
				Set<Entry<String,String>> attEntry=mapAttachments.entrySet();
				for(Entry<String,String> mapItem:attEntry){
					String attCode=mapItem.getKey();
					String attAddr=mapItem.getValue();
					if(StringUtils.isEmpty(attCode)||StringUtils.isEmpty(attAddr)){
						continue;
					}
					File attFile=new File(attAddr);
					if(attFile.isFile()&&attFile.exists()){
						FileSystemResource fileResource = new FileSystemResource(attFile);  
						msgHelper.addAttachment(attCode, fileResource);
					}
				}
			}
//			Properties prop = new Properties();  
//			prop.put("mail.smtp.auth", auth); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
//			prop.put("mail.smtp.timeout", timeOut);  
//			// 添加验证  
//			MyAuthenticator auth = new MyAuthenticator(mail.getUserName(), mail.getPassWord());  
//			Session session = Session.getDefaultInstance(prop, auth);  
//			javaMailSender.setSession(session);  
			javaMailSender.send(message);
		} catch(Exception ex) {
			throw new MailException("邮件发送失败", ex);
		}
	}
	
	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(Boolean auth) {
		this.auth = auth;
	}

	/**
	 * @param timeOut the timeOut to set
	 */
	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}
	
}

/**
 * <p>标题：MyAuthenticator </p>
 * <p>
 *    功能描述：发件人信息验证类
 * </p>
 * <p>创建日期：2016年5月19日上午11:41:24</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
class MyAuthenticator extends Authenticator {  
	private String username;  
	private String password;  

	
	public MyAuthenticator(String username, String password) {  
		super();  
		this.username = username;  
		this.password = password;  
	}

	protected PasswordAuthentication getPasswordAuthentication() {  
		return new PasswordAuthentication(username, password);  
	}
}
