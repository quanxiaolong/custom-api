/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.util;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>标题：CommonArrayUtils </p>
 * <p>
 *    功能描述： 自定义数组操作工具类
 * </p>
 * <p>创建日期：2015年11月9日下午6:42:49</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class CommonArrayUtils {
	
	/**
	 * 数字判断正则
	 */
	private static final String DIGITAL_REGX="[0-9]+";
	/**
	 * 字符串集合转成数字集合
	 * @date 2015年11月9日下午6:48:18
	 * @param listString
	 * @return
	 */
	public final static Integer[] ListToArray(List<String> listString){
		List<Integer> listInteger=new ArrayList<Integer>();
		if(listString!=null&&listString.size()>0){
			for(int i=0;i<listString.size();i++){
				String curValue=listString.get(i);
				if(curValue!=null&&curValue!=""){
					if(curValue.matches(DIGITAL_REGX)){
						listInteger.add(Integer.valueOf(curValue));
					}
				}
				
			}
		}
		
		return listInteger.toArray(new Integer[0]);
	}
}

