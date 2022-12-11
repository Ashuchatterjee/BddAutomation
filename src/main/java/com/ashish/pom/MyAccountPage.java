package com.ashish.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.ashish.constants.Constant;
import com.ashish.helper.SeleniumHelper;
import com.ashish.stepdefination.TestStoreStepDefination;

public class MyAccountPage {
	private static Logger logger = LogManager.getLogger(TestStoreStepDefination.class);
	private static WebElement element = null;	

	public static WebElement thankyouNote() {
		element = null;
		try {
			By by = By.xpath("//div[text()='Thank you for registering with Fake Online Clothing Store.']");
			element = SeleniumHelper.waitForVisibility(Constant.driver, by, "Thank you note");
		} catch (Exception e) {
			logger.error("Thank you note not found not found" + e);
		}
		return element;
	}
}
