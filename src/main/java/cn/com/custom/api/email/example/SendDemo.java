/*
 * Copyright (c) 2017年1月20日 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.email.example;

import java.util.Map;

import cn.com.custom.api.email.EmailSenderFactory;
import cn.com.custom.api.email.base.model.Mail;

/**
 * <p>标题：SendDemo </p>
 * <p>
 *    功能描述：
 * </p>
 * <p>创建日期：2017年1月20日下午4:33:16</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class SendDemo {
	
	/**
	 * 邮件发送工厂 需要外部配置 参考 Spring-beans.xml
	 */
	private EmailSenderFactory emailSenderFactory;
	/**
	 * 发送邮件
	 * @date 2017年1月20日下午4:36:06
	 * @author 权小龙
	 * @param key	邮件模版对应的Key
	 * @param mail	邮件对象
	 * @param modelMap	邮件内容参数
	 * @param sync
	 */
	public void  sendEmail(String key, Mail mail, Map<String, Object> modelMap,
			boolean sync) {

		try {
			emailSenderFactory.sendEmail(key, mail, modelMap,sync);
		} catch (Exception e) {
		}
	
	}
}
