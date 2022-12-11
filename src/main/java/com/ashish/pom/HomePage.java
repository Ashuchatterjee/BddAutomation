package com.ashish.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.ashish.constants.Constant;

public class HomePage {

	private static Logger logger = LogManager.getLogger(HomePage.class);
	private static WebElement element = null;

	public static WebElement createAccountButton() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("(//a[text()='Create an Account'])[1]"));
		} catch (Exception e) {
			logger.error("Create Account button not found" + e);
			Assert.fail("Create Account button not found");
		}
		return element;
	}

	public static WebElement signInAccountButton() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("(//a[normalize-space(text())='Sign In'])[1]"));
		} catch (Exception e) {
			logger.error("Sign In Account button not found" + e);
			Assert.fail("Sign In Account button not found");
		}
		return element;
	}

	public static WebElement menLink() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//a/span[text()='Men']"));
		} catch (Exception e) {
			logger.error("Men Hyperlink not found" + e);
			Assert.fail("Men Hyperlink not found");
		}
		return element;
	}

	public static WebElement productTypeLink(String gender) {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//a/span[text()='" + gender + "']"));
		} catch (Exception e) {
			logger.error("Men Hyperlink not found" + e);
			Assert.fail("Men Hyperlink not found");
		}
		return element;
	}

}
