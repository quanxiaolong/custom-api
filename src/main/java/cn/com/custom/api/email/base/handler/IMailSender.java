/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.email.base.handler;

import cn.com.custom.api.email.base.exception.MailException;
import cn.com.custom.api.email.base.model.Mail;

/**
 * <p>标题：IMailSender </p>
 * <p>
 *    功能描述：邮件发送器接口
 * </p>
 * <p>创建日期：2017年1月18日下午1:48:46</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public interface IMailSender {
	void sendMail(Mail mail) throws MailException;
	
	/**
	 * 异步发送邮件
	 * @param mail
	 */
	void sendMailAsync(final Mail mail);
}
