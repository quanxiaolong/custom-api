/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <p>标题：SerialNumber </p>
 * <p>
 *    功能描述：序列号生成器  see {@link SerialNumberGenerator}
 * </p>
 * <p>创建日期：2015年11月10日下午2:32:15</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class UniqueCodeCreator {
	
	private static final Random R = new Random();
	
	/**
	 *  时间戳格式
	 */
	private static final String TIME_FORMAT_REGEX = "yyyyMMddHHmmssSSS";
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT_REGEX); 

	
	/**
	 * 生成制定位数的随机数
	 * @date 2015年11月10日下午3:06:48
	 * @param randomLength
	 * @return
	 */
	public static String generateSixCode(int randomLength){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < randomLength; i ++){
			sb.append(R.nextInt(10));
		}
		return sb.toString();
	}
	
	
	/**
	 * 生成17为毫秒级别的时间戳 [Format:yyyyMMddHHmmssSSS]
	 * @date 2015年11月10日下午3:00:08
	 * @return
	 */
	public static String generateDateNumber(){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String result=dateFormat.format(new Date());
		return result;
	}
	
	public static void main(String[] args){
		for(int i=0;i<10;i++){
			System.out.println(generateDateNumber());
		}
	}
}

