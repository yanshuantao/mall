package com.yst.mall.web.util;

import java.io.InputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yst.mall.controller.ControllerAOP;

/**
 * 属性文件操作类
 * @author gameloft9
 */
@Slf4j
public class PropertyUtil {
	private final static Logger log = LoggerFactory.getLogger(ControllerAOP.class);

	private static Properties property = null;

	private PropertyUtil() {}

	static {
		try {
			if (null == property) {
				property = new Properties();
			}

			InputStream is = PropertyUtil.class.getClassLoader()
					.getResourceAsStream(
							"config/config_" + System.getProperty("run_env", "")
									+ ".properties");
			property.load(is);
		} catch (Exception e) {
			log.error("加载属性异常", e);
		}
	}

	/**
	 * 获取属性值
	 * */
	public static String getProperty(String key) {
		return property.getProperty(key);
	}
}
