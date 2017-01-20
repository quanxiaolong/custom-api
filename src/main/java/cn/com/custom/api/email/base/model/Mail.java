/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.email.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
/**
 * <p>标题：Mail </p>
 * <p>
 *    功能描述：邮件对象
 * </p>
 * <p>创建日期：2016年5月19日下午4:36:14</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class Mail implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private InternetAddress fromAddress;		//发信人地址
	
	private List<InternetAddress> toAddress;	//收信人地址集合
	
	private String subject;						//主题
	
	private String htmlText;					//邮件Html内容		
	
	private String plainText;					//纯文本内容
	
	/**
	 * 邮件中的图片，Key：图片ID，Value：图片地址
	 */
	private Map<String,String> pictures;
	
	/**
	 * 邮件中附件，key为附件ID，Value：附件地址
	 */
	private Map<String,String> attachments;
	
	/**
	 * @return the fromAddress
	 */
	public InternetAddress getFromAddress() {
		return fromAddress;
	}
	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(InternetAddress fromAddress) {
		this.fromAddress = fromAddress;
	}
	/**
	 * @return the toAddress
	 */
	public List<InternetAddress> getToAddress() {
		return toAddress;
	}
	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(List<InternetAddress> toAddress) {
		this.toAddress = toAddress;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the htmlContent
	 */
	public String getHtmlText() {
		if(StringUtils.isEmpty(htmlText)){
			htmlText="";
		}
		return htmlText;
	}
	/**
	 * @param htmlText the htmlText to set
	 */
	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}
	/**
	 * @return the plainContent
	 */
	public String getPlainText() {
		if(StringUtils.isEmpty(plainText)){
			plainText="";
		}
		return plainText;
	}
	/**
	 * @param plainText the plainText to set
	 */
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}
	/**
	 * @return the pictures
	 */
	public Map<String, String> getPictures() {
		return pictures;
	}
	/**
	 * @param pictures the pictures to set
	 */
	public void setPictures(Map<String, String> pictures) {
		this.pictures = pictures;
	}
	/**
	 * @return the attachments
	 */
	public Map<String, String> getAttachments() {
		return attachments;
	}
	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}
	
	/**
	 * 字符转地址转邮箱对象地址
	 * @date 2016年5月19日下午5:02:03
	 * @author QuanXiaolong
	 * @param listStrAddr
	 * @return
	 * @throws AddressException 
	 */
	public static List<InternetAddress> StringToAddr(List<String> listStrAddr) throws AddressException{
		if(CollectionUtils.isEmpty(listStrAddr))
			return null;
		List<InternetAddress> listAddr=new ArrayList<>();
		for(int index=0,size=listStrAddr.size();index<size;index++){
			String item=listStrAddr.get(index);
			if(!StringUtils.isEmpty(item)){
				listAddr.add(new InternetAddress(item));
			}
		}
		return listAddr;
	}
	/**
	 * 邮件对象是否有效
	 * @date 2016年5月19日上午10:55:30
	 * @author QuanXiaolong
	 * @return
	 */
	public boolean isValid(){
		boolean valid=true;
		if(fromAddress==null||CollectionUtils.isEmpty(toAddress))
		{
			valid=false;
		}
		return valid;
	}
}

