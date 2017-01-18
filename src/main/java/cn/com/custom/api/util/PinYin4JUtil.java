/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * <p>标题：PinYin4JUtil </p>
 * <p>
 *	功能描述：汉字转换拼音工具
 * </p>
 * <p>创建日期：2016年3月25日上午11:44:21</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class PinYin4JUtil {
	
	
	/**
	 * 品转转换 首字母
	 * 如果存在多音字 则只取返回第一个
	 * @date 2016年3月25日下午12:07:01
	 * @author QuanXiaolong
	 * @param chines
	 * @return
	 */
	public static String convertToFirstShortSpell(String chines){
		List<String> result= convertToShortSpell(chines);
		if(CollectionUtils.isEmpty(result))
			return null;
		return result.get(0);
	}
	  
	/** 
	 * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（长沙市长:cssc,zssz,zssc,cssz） 
	 *  
	 * @param chines 
	 *			汉字 
	 * @return 拼音 
	 */  
	public static List<String> convertToShortSpell(String chines) {  
		StringBuffer pinyinName = new StringBuffer();  
		char[] nameChar = chines.toCharArray();  
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
		for (int i = 0; i < nameChar.length; i++) {  
			if (nameChar[i] > 128) {  
				try {  
					// 取得当前汉字的所有全拼  
					String[] strs = PinyinHelper.toHanyuPinyinStringArray(  
							nameChar[i], defaultFormat);  
					if (strs != null) {  
						for (int j = 0; j < strs.length; j++) {  
							// 取首字母  
							pinyinName.append(strs[j].charAt(0));  
							if (j != strs.length - 1) {  
								pinyinName.append(",");  
							}  
						}  
					}  
					// else {  
					// pinyinName.append(nameChar[i]);  
					// }  
				} catch (BadHanyuPinyinOutputFormatCombination e) {  
					e.printStackTrace();  
				}  
			} else {  
				pinyinName.append(nameChar[i]);  
			}  
			pinyinName.append(" ");  
		}  
		// return pinyinName.toString();  
		String result = parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
		return parseString2List(result);
	}  
  
	/**
	 * 品转转换
	 * 如果存在多音字 则只取返回第一个
	 * @date 2016年3月25日下午12:07:01
	 * @author QuanXiaolong
	 * @param chines
	 * @return
	 */
	public static String convertToFirstSpell(String chines){
		List<String> result= convertToSpell(chines);
		if(CollectionUtils.isEmpty(result))
			return null;
		return result.get(0);
	}
	/** 
	 * 汉字转换位汉语全拼，英文字符不变，特殊字符丢失 
	 * 支持多音字，生成方式如（重当参:zhongdangcen,zhongdangcan,chongdangcen 
	 * ,chongdangshen,zhongdangshen,chongdangcan） 
	 *  
	 * @param chines 
	 *			汉字 
	 * @return 拼音 
	 */  
	public static List<String> convertToSpell(String chines) {  
		StringBuffer pinyinName = new StringBuffer();  
		char[] nameChar = chines.toCharArray();  
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
		for (int i = 0; i < nameChar.length; i++) {  
			if (nameChar[i] > 128) {  
				try {  
					// 取得当前汉字的所有全拼  
					String[] strs = PinyinHelper.toHanyuPinyinStringArray(  
							nameChar[i], defaultFormat);  
					if (strs != null) {  
						for (int j = 0; j < strs.length; j++) {  
							pinyinName.append(strs[j]);  
							if (j != strs.length - 1) {  
								pinyinName.append(",");  
							}  
						}  
					}  
				} catch (BadHanyuPinyinOutputFormatCombination e) {  
					e.printStackTrace();  
				}  
			} else {  
				pinyinName.append(nameChar[i]);  
			}  
			pinyinName.append(" ");  
		}  
		// return pinyinName.toString(); 
		String result=parseTheChineseByObject(discountTheChinese(pinyinName.toString()));  
		return parseString2List(result);
	}  
  
	/** 
	 * 去除多音字重复数据 
	 *  
	 * @param theStr 
	 * @return 
	 */  
	private static List<Map<String, Integer>> discountTheChinese(String theStr) {  
		// 去除重复拼音后的拼音列表  
		List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>();  
		// 用于处理每个字的多音字，去掉重复  
		Map<String, Integer> onlyOne = null;  
		String[] firsts = theStr.split(" ");  
		// 读出每个汉字的拼音  
		for (String str : firsts) {  
			onlyOne = new Hashtable<String, Integer>();  
			String[] china = str.split(",");  
			// 多音字处理  
			for (String s : china) {  
				Integer count = onlyOne.get(s);  
				if (count == null) {  
					onlyOne.put(s, new Integer(1));  
				} else {  
					onlyOne.remove(s);  
					count++;  
					onlyOne.put(s, count);  
				}  
			}  
			mapList.add(onlyOne);  
		}  
		return mapList;  
	}  
  
	/** 
	 * 解析并组合拼音，对象合并方案(推荐使用) 
	 *  
	 * @return 
	 */  
	private static String parseTheChineseByObject(  
			List<Map<String, Integer>> list) {  
		Map<String, Integer> first = null; // 用于统计每一次,集合组合数据  
		// 遍历每一组集合  
		for (int i = 0; i < list.size(); i++) {  
			// 每一组集合与上一次组合的Map  
			Map<String, Integer> temp = new Hashtable<String, Integer>();  
			// 第一次循环，first为空  
			if (first != null) {  
				// 取出上次组合与此次集合的字符，并保存  
				for (String s : first.keySet()) {  
					for (String s1 : list.get(i).keySet()) {  
						String str = s + s1;  
						temp.put(str, 1);  
					}  
				}  
				// 清理上一次组合数据  
				if (temp != null && temp.size() > 0) {  
					first.clear();  
				}  
			} else {  
				for (String s : list.get(i).keySet()) {  
					String str = s;  
					temp.put(str, 1);  
				}  
			}  
			// 保存组合数据以便下次循环使用  
			if (temp != null && temp.size() > 0) {  
				first = temp;  
			}  
		}  
		String returnStr = "";  
		if (first != null) {  
			// 遍历取出组合字符串  
			for (String str : first.keySet()) {  
				returnStr += (str + ",");  
			}  
		}  
		if (returnStr.length() > 0) {  
			returnStr = returnStr.substring(0, returnStr.length() - 1);  
		}  
		return returnStr;  
	}
	
	
	/**
	 * 转化为集合
	 * @date 2016年3月25日下午12:02:10
	 * @author QuanXiaolong
	 * @param str
	 * @return
	 */
	private static List<String> parseString2List(String str){
		if(StringUtils.isEmpty(str))
			return null;
		String[] splitResult=str.split(",");
		List<String> listResult=new ArrayList<String>();
		if(splitResult!=null&&splitResult.length>0){
			for(int index=0,len=splitResult.length;index<len;index++){
				String item=splitResult[index];
				if(StringUtils.isEmpty(item))
					continue;
				if(listResult.contains(item))
					continue;
				listResult.add(item);
			}
		}
		return listResult;
	}
	
	
	public static void main(String[] args){

	}
	
	public static String shuffString(String useName,String passWord){
		String result ="";
		int userNameLen=useName.length();
		int passWordLen=passWord.length();
		int minLen=(userNameLen<=passWordLen)?userNameLen:passWordLen;
		for(int index=0;index<minLen;index++){
			result+=(useName.substring(index, index+1)+passWord.substring(index, index+1));
		}
		String more="";
		if(userNameLen>passWordLen)
			more=useName.substring(minLen, userNameLen);
		else if(passWordLen>userNameLen)
			more=passWord.substring(minLen, passWordLen);
			result+=more;
		return result;
	}
	
	public static String[] realString(int userLen,int psLen,String usepass){
		String [] result = new String[2];
		int min =(userLen<=psLen)?userLen:psLen;
		String useName="";
		String passWord="";
		for(int index=0;index<2*min;index+=2){
			useName+=usepass.substring(index,index+1);
			passWord+=usepass.substring(index+1,index+2);
		}
		if(userLen>psLen){
			useName+=usepass.substring(2*min, usepass.length());
		}
		if(userLen<psLen){
			passWord+=usepass.substring(2*min, usepass.length());
		}
		result[0]=useName;
		result[1]=passWord;
		return result;
	}
}
