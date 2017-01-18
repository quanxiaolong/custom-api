/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
/**
 * <p>标题：CommonListUtil </p>
 * <p>
 *    功能描述：集合操作工具
 * </p>
 * <p>创建日期：2015年11月18日下午2:55:20</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class CommonListUtil {
	
	/**
	 * 从初始位置截取指定长度的集合
	 * @date 2015年11月18日下午3:21:47
	 * @param list
	 * @param length 截取长度
	 * @return
	 */
	public final static <T> List<T> getSubListFromStart(List<T> list,int length){
		
		List<T> listSub=null;
		if(list!=null){
			length=list.size()>length?length:list.size();
			listSub=list.subList(0, length);
		}
		return listSub;
	}
	
	/**
	 * 集合整型转字符串
	 * @date 2016年6月7日下午5:47:32
	 * @author QuanXiaolong
	 * @param list
	 * @return
	 */
	public final static List<String> IntegerToString(List<Integer> list){
		List<String> result = null;
		if(!CollectionUtils.isEmpty(list)){
			result = new ArrayList<>();
			Transformer<? super Integer, ? extends String> transformer = new Transformer<Integer, String>() {

				@Override
				public String transform(Integer input) {
					if(input!=null)
						return input.toString();
					return "";
				}
			};
			CollectionUtils.collect(list, transformer,result);
		}
		return result;
	}
	
	public static void main(String[] arg){
//		List<String> listString=new ArrayList<String>();
//		listString.add("1");
//		listString.add("2");
//		listString.add("3");
//		listString.add("4");
//		listString.add("5");
//		listString.add("6");
//		listString.add("7");
//		listString.add("8");
//		listString.add("9");
//		
//		List<String> subList=new ArrayList<String>();
//		
//		subList=getSubListFromStart(listString,5);
//		for(String item :subList){
//			System.out.println(item);
//		}
		List<Integer> listString=new ArrayList<Integer>();
		listString.add(1);
		listString.add(2);
		listString.add(3);
		listString.add(4);
		listString.add(5);
		listString.add(6);
		listString.add(7);
		listString.add(8);
		listString.add(9);
		List<String> result = IntegerToString(listString);
		System.out.println(result);

	}

}
