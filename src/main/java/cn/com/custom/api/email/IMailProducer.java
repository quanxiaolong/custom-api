/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.email;

import java.util.Map;

import cn.com.custom.api.email.model.Mail;

/**
 * <p>标题：IHandler </p>
 * <p>
 *    功能描述：邮件模版创造器
 * </p>
 * <p>创建日期：2016年5月19日下午4:04:59</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public interface IMailProducer {
	
	/**
	 * 创建邮件对象
	 * @date 2016年5月19日下午4:19:43
	 * @author QuanXiaolong
	 * @return
	 */
	public Mail createMail(Mail mail,Map<String,Object> modelMap)throws Exception;

	/**
	 * @date 2016年5月19日下午4:24:19
	 * @author QuanXiaolong
	 * @return
	 */
	public String createHtmlText(Map<String,Object> modelMap) throws Exception;
	
	/**
	 * @date 2016年5月19日下午4:24:21
	 * @author QuanXiaolong
	 * @return
	 */
	public String createPlainText();
	
	/**
	 * 获取邮件主题
	 * @date 2016年5月19日下午5:58:59
	 * @author QuanXiaolong
	 * @return
	 */
	public String getSubject();
}
