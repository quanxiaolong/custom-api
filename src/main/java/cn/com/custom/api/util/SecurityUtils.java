/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * <p>标题：SecurityUtils </p>
 * <p>
 *    功能描述：加密工具
 * </p>
 * <p>创建日期：2016年2月18日下午3:55:57</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class SecurityUtils {
	/**
	 * BASE64加密字符串
	 * @date 2016年1月4日下午2:32:04
	 * @param value
	 * @return
	 */
	
	public static String encodeBASE64(String value){
		if(StringUtils.isEmpty(value))
			return null;
		return new BASE64Encoder().encode(value.getBytes());
	}
	
	/**
	 * BASE64解密字符串
	 * @date 2016年1月4日下午2:34:03
	 * @param value
	 * @return
	 * @throws IOException 
	 */
	public static String decodeBASE64(String value) throws IOException{
		if(StringUtils.isEmpty(value))
			return null;
		byte[] bb = new BASE64Decoder().decodeBuffer(value);
		return new String(bb);
	}
	
	/**
	 * md5加密
	 * @date 2016年2月18日下午4:18:08
	 * @author QuanXiaolong
	 * @param value
	 * @return
	 */
	public static String encodeMD5(String value){
		if(StringUtils.isEmpty(value))
			return null;

		MessageDigest md;
		String encodeValue=null;
		try {
			md= MessageDigest.getInstance("MD5");
			md.update(value.getBytes());
			encodeValue= new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encodeValue;
	}
	
	public static void main(String[] args){
		StringBuilder address=new StringBuilder();
		System.out.println(address.toString());
		System.out.println(encodeMD5("123456"));
	}
}