/*
 * Copyright (c) 2017年1月20日 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.api.email;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.com.custom.api.email.base.model.Mail;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * <p>标题：AbstractMailProduct </p>
 * <p>
 *    功能描述：抽象邮箱模版生成器
 *    Implementation of {@link IMailProducer}
 *    需要外部实现
 * </p>
 * <p>创建日期：2016年5月19日下午4:30:13</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public abstract class AbstractMailProduct implements IMailProducer {
	
	/*需要外部配置的参数*/
	private String tplDirectory = "/tpl";		//HTML模版文件夹
	
	private String encoding = "UTF-8";
	
	
	private Configuration config;
	private String templateName;
	
	@Override
	public Mail createMail(Mail mail,Map<String,Object> modelMap) throws Exception {
		if(mail==null){
			return null;
		}
		String htmlText=createHtmlText(modelMap);
		mail.setHtmlText(htmlText);
		String plainText=createPlainText();
		mail.setPlainText(plainText);
		return mail;
	}
	
	/**
	 * 创建HTML文档
	 * @date 2017年1月20日上午11:26:05
	 * @author 权小龙
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	private String createHtmlText(Map<String,Object> modelMap) throws Exception {
		if(StringUtils.isEmpty(templateName))
			return null;
		if (config == null) initConfig();
		if(modelMap==null)
			modelMap = new HashMap<>();
		Template t = config.getTemplate(templateName);
		StringWriter out = new StringWriter();
		t.process(modelMap, out);
		out.flush();
		out.close();
		return out.toString();
	}
	/**
	 * 初始化模板状态
	 */
	private void initConfig() {
		config = new Configuration();
		config.setTemplateLoader(new ClassTemplateLoader(getClass(), tplDirectory));
		config.setOutputEncoding(encoding);
	}
	
	
	/**
	 * @return the tplDirectory
	 */
	public String getTplDirectory() {
		return tplDirectory;
	}
	
	/**
	 * @param tplDirectory the tplDirectory to set
	 */
	public void setTplDirectory(String tplDirectory) {
		this.tplDirectory = tplDirectory;
	}
	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}


	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	
	
}
