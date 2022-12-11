package com.ashish.pom;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.ashish.constants.Constant;

public class CreateAccount {
	private static Logger logger = LogManager.getLogger(CreateAccount.class);
	private static WebElement element = null;

	public static WebElement firstNameTextBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.id("firstname"));
		} catch (Exception e) {
			logger.error("First Name Text box not found" + e);
			Assert.fail("First Name Text box not found");
		}
		return element;
	}

	public static WebElement lastNameTextBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.id("lastname"));
		} catch (Exception e) {
			logger.error("Last Name Text box not found" + e);
			Assert.fail("Last Name Text box not found");
		}
		return element;
	}

	public static WebElement emailTextBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.id("email_address"));
		} catch (Exception e) {
			logger.error("Email Address Text box not found" + e);
			Assert.fail("Email Address Text box not found");
		}
		return element;
	}

	public static WebElement passwordTextBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.id("password"));
		} catch (Exception e) {
			logger.error("Password Text box not found" + e);
			Assert.fail("Password Text box not found");
		}
		return element;
	}
	
	public static WebElement confirmPasswordTextBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.id("password-confirmation"));
		} catch (Exception e) {
			logger.error("Confirm Password Text box not found" + e);
			Assert.fail("Confirm Password Text box not found");
		}
		return element;
	}
	
	public static WebElement submitButton() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//button/span[text()='Create an Account']"));
		} catch (Exception e) {
			logger.error("Create an Account Submit Button not found" + e);
			Assert.fail("Create an Account Submit Button not found");
		}
		return element;
	}
}
