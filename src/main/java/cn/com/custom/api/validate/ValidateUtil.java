/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.validate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import cn.com.custom.api.validate.model.ValidatePattern;
import cn.com.custom.api.validate.model.ValidateResult;


/**
 * <p>标题：ValidateUtil </p>
 * <p>
 *    功能描述：对象校验类
 * </p>
 * <p>创建日期：2016年2月19日上午9:58:50</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class ValidateUtil {
	/**
	 * 参数为空错误消息
	 */
	public static final String OBJ_NULL_MSG="params.object.is.null";
	
	private static ValidatorFactory vf =Validation.buildDefaultValidatorFactory();

	private static Validator validator = vf.getValidator();
	/**
	 * 对象属性非空校验
	 * @date 2016年2月19日上午11:37:11
	 * @author QuanXiaolong
	 * @param validateFields key:要校验的字段，value 错误的提示信息
	 * @param obj
	 * @return
	 */
	public static <T> ValidateResult validateNotNull(Map<String,String> validateFields,T obj){
		ValidateResult validateResult=new ValidateResult();
		if(obj==null){
			validateResult.setErrMessage(OBJ_NULL_MSG);
			return validateResult;
		}
		if(MapUtils.isEmpty(validateFields)){
			validateResult.setSuccess(true);
			return validateResult;
		}
		validateResult.setSuccess(true);
		try {
				Field[] decFields=obj.getClass().getDeclaredFields();
				for(int arrIndex=0,len=decFields.length;arrIndex<len;arrIndex++){
					Field curField=decFields[arrIndex];
					if(validateFields.containsKey(curField.getName())){
						curField.setAccessible(true);
						//如果是引用类型
						if(Object.class.isAssignableFrom(curField.getType())){
							Object value=curField.get(obj);
							if(value==null){
								validateResult.setSuccess(false);
								validateResult.setErrMessage(validateFields.get(curField.getName()));
								break;
							}
							if(String.class.isAssignableFrom(curField.getType())){
								if(StringUtils.isEmpty(value.toString())){
									validateResult.setSuccess(false);
									validateResult.setErrMessage(validateFields.get(curField.getName()));
									break;
								}
							}
						}
					
					}
				}
			} catch (Exception e) {
				
			}
		return validateResult;
	}
	

	/**
	 * 基于注解对象属性非空校验
	 * 对象包含 {@link NotBlank}或{@link NotNull} 注解
	 * @date 2016年2月22日下午4:16:03
	 * @author QuanXiaolong
	 * @param validateFields	待校验的字段集合
	 * @param obj	待校验的对象
	 * @return
	 */
	public static <T> ValidateResult validateNotNull(List<String> validateFields,T obj){
		ValidateResult validateResult=new ValidateResult();
		if(obj==null){
			validateResult.setErrMessage(OBJ_NULL_MSG);
			return validateResult;
		}
		if(CollectionUtils.isEmpty(validateFields)){
			validateResult.setSuccess(true);
			return validateResult;
		}
		validateResult.setSuccess(true);
		try {
				Field[] decFields=obj.getClass().getDeclaredFields();
				for(int arrIndex=0,len=decFields.length;arrIndex<len;arrIndex++){
					Field curField=decFields[arrIndex];

					if(validateFields.contains(curField.getName())){
						NotBlank notblock=curField.getAnnotation(NotBlank.class);
						NotNull notNull=curField.getAnnotation(NotNull.class);
						if(notNull==null&&notblock==null)
							continue;
						curField.setAccessible(true);
						String message=(notNull!=null)?notNull.message():notblock.message();
						//如果是引用类型
						if(Object.class.isAssignableFrom(curField.getType())){
							Object value=curField.get(obj);
							if(value==null){
								validateResult.setSuccess(false);
								validateResult.setErrMessage(message);
								break;
							}
							if(String.class.isAssignableFrom(curField.getType())){
								if(StringUtils.isEmpty(value.toString())){
									validateResult.setSuccess(false);
									validateResult.setErrMessage(message);
									break;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				
			}
		return validateResult;
	}
	/**
	 * 基于注解校验
	 * 校验对象指定属性上的所有注解规则
	 * @date 2016年5月5日下午6:04:59
	 * @author QuanXiaolong
	 * @param validateFields
	 * @param obj
	 * @return
	 */
	public static <T> ValidateResult validateAnno(List<String> validateFields,T obj){
		ValidateResult validateResult=new ValidateResult();
		if(obj==null){
			validateResult.setErrMessage(OBJ_NULL_MSG);
			return validateResult;
		}
		if(CollectionUtils.isEmpty(validateFields)){
			validateResult.setSuccess(true);
			return validateResult;
		}
		validateResult.setSuccess(true);
		try {
			Field[] decFields=obj.getClass().getDeclaredFields();
			for(int arrIndex=0,len=decFields.length;arrIndex<len;arrIndex++){
				Field curField=decFields[arrIndex];
				String fieldName=curField.getName();
				if(validateFields.contains(fieldName)){
					curField.setAccessible(true);
					//如果是引用类型
					if(Object.class.isAssignableFrom(curField.getType())){
						Set<ConstraintViolation<T>> set=validator.validateProperty(obj, fieldName);
						for (ConstraintViolation<T>constraintViolation : set) {
							validateResult.setSuccess(false);
							validateResult.setErrMessage(constraintViolation.getMessage());
							return validateResult;
						}
					}
				}
			}
		} catch (Exception e) {
			
		}
		return validateResult;
	}
	/**
	 * 正则校验
	 * @date 2016年5月5日下午3:30:27
	 * @author QuanXiaolong
	 * @param validatePattern
	 * 正则校验集合 {@link ValidatePattern}
	 * @param obj
	 * @return
	 */
	public static <T> ValidateResult validatePattern(List<ValidatePattern> listPattern,T obj){
		ValidateResult validateResult=new ValidateResult();
		if(obj==null){
			validateResult.setErrMessage(OBJ_NULL_MSG);
			return validateResult;
		}
		if(CollectionUtils.isEmpty(listPattern)){
			validateResult.setSuccess(true);
			return validateResult;
		}
		validateResult.setSuccess(true);
		Map<String,ValidatePattern> mapPattern = new HashMap<String, ValidatePattern>();
		
		for(int index = 0 ,size=listPattern.size(); index<size;index++){
			ValidatePattern item = listPattern.get(index);
			if(item!=null){
				mapPattern.put(item.getFieldName(), item);
			}
		}
		try {
			Field[] decFields=obj.getClass().getDeclaredFields();
			for(int arrIndex=0,len=decFields.length;arrIndex<len;arrIndex++){
				Field curField=decFields[arrIndex];
				String fieldName=curField.getName();
				if(mapPattern.containsKey(fieldName)){
					ValidatePattern pattern=mapPattern.get(fieldName);
					curField.setAccessible(true);
					String message=pattern.getErrMessage();
					//如果是引用类型
					if(String.class.isAssignableFrom(curField.getType())){
						Object value=curField.get(obj);
						if(value==null){
							validateResult.setSuccess(false);
							validateResult.setErrMessage(message);
							break;
						}
						java.util.regex.Pattern patternRegx =java.util.regex.Pattern.compile(pattern.getPattern());
						if(patternRegx!=null){
							Matcher m = patternRegx.matcher(value.toString());
							if(!m.matches()){
								validateResult.setSuccess(false);
								validateResult.setErrMessage(message);
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			
		}
		
		return validateResult;
	}
	/**
	 * 基于注解的正则校验
	 * 对象校验字段注解为{@link Pattern}
	 * @date 2016年5月5日下午3:24:46
	 * @author QuanXiaolong
	 * @param validateFields 待校验的字段名称
	 * @param obj
	 * @return
	 */
	public static <T> ValidateResult validateAnnoPattern(List<String> validateFields,T obj){
		
		ValidateResult validateResult=new ValidateResult();
		if(obj==null){
			validateResult.setErrMessage(OBJ_NULL_MSG);
			return validateResult;
		}
		if(CollectionUtils.isEmpty(validateFields)){
			validateResult.setSuccess(true);
			return validateResult;
		}
		validateResult.setSuccess(true);
		try {
				Field[] decFields=obj.getClass().getDeclaredFields();
				for(int arrIndex=0,len=decFields.length;arrIndex<len;arrIndex++){
					Field curField=decFields[arrIndex];

					if(validateFields.contains(curField.getName())){
						Pattern patternAnno=curField.getAnnotation(Pattern.class);
						if(patternAnno==null)
							continue;
						curField.setAccessible(true);
						String message=patternAnno.message();
						//如果是引用类型
						if(String.class.isAssignableFrom(curField.getType())){
							Object value=curField.get(obj);
							if(value==null){
								validateResult.setSuccess(false);
								validateResult.setErrMessage(message);
								break;
							}
							java.util.regex.Pattern patternRegx = createPattern(patternAnno);
							if(patternRegx!=null){
								Matcher m = patternRegx.matcher(value.toString());
								if(!m.matches()){
									validateResult.setSuccess(false);
									validateResult.setErrMessage(message);
									break;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				
			}
		
		return validateResult;
	}
	/**
	 * 根据正则注解创建 正则校验器
	 * @date 2016年5月5日下午3:12:49
	 * @author QuanXiaolong
	 * @param patternAnno
	 * @return
	 */
	private static java.util.regex.Pattern createPattern(Pattern patternAnno){
		java.util.regex.Pattern pattern=null;
		Pattern.Flag flags[] = patternAnno.flags();
		int intFlag = 0;
		for ( Pattern.Flag flag : flags ) {
			intFlag = intFlag | flag.getValue();
		}

		try {
			pattern = java.util.regex.Pattern.compile( patternAnno.regexp(), intFlag );
		}
		catch ( PatternSyntaxException e ) {
			pattern = null;
		}
		return pattern;
	}

}
