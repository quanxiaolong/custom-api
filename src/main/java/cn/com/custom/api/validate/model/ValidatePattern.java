/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.validate.model;

/**
 * <p>标题：ValidatePattern </p>
 * <p>
 *    功能描述：正则表达式验证
 * </p>
 * <p>创建日期：2016年2月19日下午3:31:25</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class ValidatePattern{
	
	private String fieldName;				//字段名称
	
	private String pattern;					//正则表达式
	
	private String errMessage;				//错误信息

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	/**
	 * @return the errMessage
	 */
	public String getErrMessage() {
		return errMessage;
	}

	/**
	 * @param errMessage the errMessage to set
	 */
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	
}

