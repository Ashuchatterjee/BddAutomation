package com.ashish.stepdefination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ashish.commonutils.ConfigManager;
import com.ashish.commonutils.TestNgUtil;
import com.ashish.constants.Constant;
import com.ashish.helper.SeleniumHelper;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ServiceHooks extends TestNgUtil {

	private static Logger logger = LogManager.getLogger(ServiceHooks.class);

	@Before
	public void initializeTest(Scenario scenario) {
		logger.info("Before hook started for execution");
		ConfigManager config = new ConfigManager();
		Constant.prop = config.setPropertiesFile();	
		String browser = (String) Constant.prop.get("BROWSER");
		SeleniumHelper.openBrowser(browser);
	}

	@After
	public void endTest(Scenario scenario) {
		logger.info("After hook started for execution");
		Constant.driver.quit();
		Constant.driver = null;
		Constant.prop = null;
	}

}
