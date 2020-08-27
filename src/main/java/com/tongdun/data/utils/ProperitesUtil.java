package com.tongdun.data.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取配置文件属性值
 * 
 * @author yxw
 *
 */
public class ProperitesUtil {

	private static final Logger logger = LoggerFactory.getLogger(ProperitesUtil.class);

	/**
	 * 
	 * @param propertyName
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getValue(String propertyName, String key) throws IOException {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = ProperitesUtil.class.getClassLoader().getResourceAsStream(propertyName);
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (IOException e) {
			logger.error("get property value error." + e.getMessage());
			return null;
		} finally {
			if (null != in)
				in.close();
		}
	}

	/**
	 * 获取配置文件值
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropertyValue(String key) {
		String value = null;
		try {
			value = ProperitesUtil.getValue("application.properties", key);
		} catch (IOException e) {
			logger.error("get property key error." + e.getMessage());
		}
		return value;
	}

}
