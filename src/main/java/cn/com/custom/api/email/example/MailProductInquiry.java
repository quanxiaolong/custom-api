/*
 * Copyright (c) 2017年1月20日 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.email.example;

import cn.com.custom.api.email.AbstractMailProduct;

/**
 * <p>标题：MailProductInquiry </p>
 * <p>
 *    功能描述：询价单邮件模版生成器
 * </p>
 * <p>创建日期：2016年5月19日下午4:42:59</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class MailProductInquiry extends AbstractMailProduct {


	@Override
	public String createPlainText() {
		return "";
	}

	@Override
	public String getSubject() {
		return "您有已报价的询价单未处理";
	}

}
