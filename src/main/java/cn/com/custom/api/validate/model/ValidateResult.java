/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.validate.model;

import java.io.Serializable;

/**
 * <p>标题：ValidateResult </p>
 * <p>
 *    功能描述：校验结果模型
 * </p>
 * <p>创建日期：2016年2月19日下午2:41:41</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class ValidateResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isSuccess=false;		//校验是否成功
	
	private String errMessage="";			//校验失败信息

	/**
	 * 创建校验结果模型
	 */
	public ValidateResult(){
		
	};
	
	/**
	 * 创建检验结果模型
	 * @param isSuccess 校验结果初始化
	 */
	public ValidateResult(boolean isSuccess){
		this.isSuccess=isSuccess;
	}
	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
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

	@Override
	public String toString() {
		return "ValidateResult [isSuccess=" + isSuccess + ", errMessage=" + errMessage + "]";
	}
	
	
}