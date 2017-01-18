/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.util;

import java.math.BigDecimal;

/**
 * <p>标题：DoubleUtil </p>
 * <p>
 *    功能描述：double数据工具
 * </p>
 * <p>创建日期：2016年7月27日下午2:29:53</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class CommonDoubleUtil {

	/**
	 * 截取小数调后指定位数，返回截取后结果
	 * eg: value :1.236,precision :2, result:1.23
	 * @date 2016年7月27日下午2:34:52
	 * @author QuanXiaolong
	 * @param value
	 * @param precision 精度，表示小数点后位数
	 * @return
	 */
	public static double getPrecisionDouble(double value,int precision){
		BigDecimal bd = new BigDecimal(String.valueOf(value));
		return bd.setScale(precision, BigDecimal.ROUND_FLOOR).doubleValue();
	}
}
