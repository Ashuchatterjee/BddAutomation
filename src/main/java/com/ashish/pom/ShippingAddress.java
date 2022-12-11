package com.ashish.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.ashish.constants.Constant;
import com.ashish.helper.SeleniumHelper;

public class ShippingAddress {

	private static Logger logger = LogManager.getLogger(ShippingAddress.class);
	private static WebElement element = null;

	public static WebElement streetAddressTextBox() {
		element = null;
		By by = By.xpath("//input[@name='street[0]']");
		return SeleniumHelper.waitForVisibility(Constant.driver, by, "Street Address Textbox");
	}

	public static WebElement cityTextBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//input[@name='city']"));
		} catch (Exception e) {
			logger.error("City Textbox not found" + e);
			Assert.fail("City Textbox not found");
		}
		return element;
	}

	public static WebElement postcodeTextBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//input[@name='postcode']"));
		} catch (Exception e) {
			logger.error("Postcode Textbox not found" + e);
			Assert.fail("Postcode Textbox not found");
		}
		return element;
	}

	public static WebElement phoneNumberTextBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//input[@name='telephone']"));
		} catch (Exception e) {
			logger.error("Phone Number Textbox not found" + e);
			Assert.fail("Phone Number Textbox not found");
		}
		return element;
	}

	public static WebElement stateDropDown() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//select[@name='region_id']"));
		} catch (Exception e) {
			logger.error("State Drop Down not found" + e);
			Assert.fail("State Drop Down not found");
		}
		return element;
	}

	public static WebElement countryDropDown() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//select[@name='country_id']"));
		} catch (Exception e) {
			logger.error("Country Drop Down not found" + e);
			Assert.fail("Country Drop Down not found");
		}
		return element;
	}

	public static WebElement tableRateShippingMethodCheckBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//td/following-sibling::td[text()='Table Rate']"));
		} catch (Exception e) {
			logger.error("Table Rate Shipping Method CheckBox not found" + e);
			Assert.fail("Table Rate Shipping Method CheckBox not found");
		}
		return element;
	}

	public static WebElement fixedShippingMethodCheckBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//td/following-sibling::td[text()='Fixed']"));
		} catch (Exception e) {
			logger.error("Fixed Shipping Method CheckBox not found" + e);
			Assert.fail("Fixed Shipping Method CheckBox not found");
		}
		return element;
	}

	public static WebElement nextButton() {
		element = null;
		By by = By.xpath("//span[text()='Next']");
		return SeleniumHelper.waitForElementToBeClickAble(Constant.driver, by, "Next Button");
	}

	public static WebElement placeOrderButton() {
		element = null;
		By by = By.xpath("//span[text()='Place Order']");
		return SeleniumHelper.waitForElementToBeClickAble(Constant.driver, by, "Place Order Button");
	}

	public static WebElement thankYouPurchaseNote() {
		element = null;
		By by = By.xpath("//span[text()='Thank you for your purchase!']");
		return SeleniumHelper.waitForVisibility(Constant.driver, by, "Next Button");

	}

	public static WebElement getOrderNumber() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//a[@class='order-number']/strong"));
		} catch (Exception e) {
			logger.error("Order Number not found" + e);
			Assert.fail("Order Number not found");
		}
		return element;
	}

}
