package com.ashish.commonutils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigManager {

	private static Logger logger = LogManager.getLogger(ConfigManager.class);

	public Properties setPropertiesFile() {
		Properties property = new Properties();
		Object file = this.getClass().getResourceAsStream("/Config/config.properties");
		try {
			if (file instanceof String) {
				FileInputStream fis = new FileInputStream(file.toString());
				property.load(fis);
			} else if (file instanceof InputStream) {
				InputStream in = (InputStream) file;
				property.load(in);

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), true);
		}
		return property;
	}

}
