/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.email.base.exception;

/**
 * <p>标题：MailException </p>
 * <p>
 *    功能描述：
 * </p>
 * <p>创建日期：2017年1月18日下午1:56:51</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class MailException extends Exception {
	private static final long serialVersionUID = 3756960470603986227L;

	public MailException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailException(String message) {
		super(message);
	}

	public MailException(Throwable cause) {
		super(cause);
	}
	
}
