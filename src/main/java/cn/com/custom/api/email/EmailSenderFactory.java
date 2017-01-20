/*
 * Copyright (c) 2017年1月20日 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.email;

import java.util.HashMap;
import java.util.Map;

import cn.com.custom.api.email.base.handler.IMailSender;
import cn.com.custom.api.email.base.model.Mail;


/**
 * <p>标题：EmailSenderFactory </p>
 * <p>
 *    功能描述：邮件发送工厂
 * </p>
 * <p>创建日期：2015年9月24日上午10:44:42</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class EmailSenderFactory {
	
	/**
	 * 邮件模版生成器 key模版类型，Value邮件模版生成器
	 */
	private  HashMap<String, IMailProducer> mapMailProduct;
	
	/**
	 * 邮件发送器 
	 */
	private IMailSender emailSender;

	/**
	 * 发送邮件
	 * @date 2016年5月19日下午7:38:13
	 * @author QuanXiaolong
	 * @param key
	 * @param mail
	 * @param modelMap
	 * @param sync 是否同步
	 * @throws Exception
	 */
	public void sendEmail(String key,Mail mail,Map<String,Object> modelMap,boolean sync) throws Exception{
		if(mapMailProduct!=null&&mapMailProduct.containsKey(key)){
			Mail resultMail=mapMailProduct.get(key).createMail(mail, modelMap);
			if(sync){
				emailSender.sendMail(resultMail);
			}else{
				emailSender.sendMailAsync(resultMail);
			}
		}else{
			throw new Exception("未知邮件模版");
		}
	}


	/**
	 * @param mapMailProduct the mapMailProduct to set
	 */
	public void setMapMailProduct(HashMap<String, IMailProducer> mapMailProduct) {
		this.mapMailProduct = mapMailProduct;
	}


	/**
	 * @param emailSender the emailSender to set
	 */
	public void setEmailSender(IMailSender emailSender) {
		this.emailSender = emailSender;
	}


}