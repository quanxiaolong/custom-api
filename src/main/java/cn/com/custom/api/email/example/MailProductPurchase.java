/*
 * Copyright (c) 2017年1月20日 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.email.example;

import cn.com.custom.api.email.AbstractMailProduct;

/**
 * <p>标题：MailProductPurchase </p>
 * <p>
 *    功能描述：进货单邮件模型创建器
 * </p>
 * <p>创建日期：2016年5月20日上午11:58:51</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class MailProductPurchase extends AbstractMailProduct {

	@Override
	public String createPlainText() {
		return "";
	}

	@Override
	public String getSubject() {
		return "您有状态更新的进货单未处理";
	}

}
