package com.ashish.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.ashish.constants.Constant;
import com.ashish.helper.SeleniumHelper;

public class MensPage {

	private static Logger logger = LogManager.getLogger(MensPage.class);
	private static WebElement element = null;

	public static WebElement hoodiesAndSweatshirtsButton() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//a[text()='Hoodies & Sweatshirts']"));
		} catch (Exception e) {
			logger.error("Hoodies & Sweatshirts button not found" + e);
			Assert.fail("Hoodies & Sweatshirts button not found");
		}
		return element;
	}

	public static WebElement firstProductImage() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("(//span[@class='product-image-wrapper']/img)[1]"));
		} catch (Exception e) {
			logger.error("Hoodies & Sweatshirts Home page is blank" + e);
			Assert.fail("Hoodies & Sweatshirts  Home page is blank");
		}
		return element;
	}

	public static WebElement addToCartButton() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//span[text()='Add to Cart']"));
		} catch (Exception e) {
			logger.error("Add to Cart button not found" + e);
			Assert.fail("Add to Cart button not found");
		}
		return element;
	}

	public static WebElement selectSizeButton(String size) {
		element = null;
		try {
			element = Constant.driver
					.findElement(By.xpath("//span[text()='Size']/following-sibling::div/div[text()='" + size + "']"));
		} catch (Exception e) {
			logger.error("Size " + size + " not found" + e);
			Assert.fail("Size " + size + " not found");
		}
		return element;
	}

	public static WebElement selectColour() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//span[text()='Color']/following-sibling::div/div"));
		} catch (Exception e) {
			logger.error("No colour not found" + e);
		}
		return element;
	}

	public static WebElement quantityTextBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//input[@id='qty']"));
		} catch (Exception e) {
			logger.error("Quantity text box not found" + e);
			Assert.fail("Quantity text box not found");
		}
		return element;
	}

	public static WebElement myCartTextBox() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("//input[@id='qty']"));
		} catch (Exception e) {
			logger.error("Quantity text box not found" + e);
			Assert.fail("Quantity text box not found");
		}
		return element;
	}

	public static WebElement validateMessageAddToCart() {
		element = null;
		By by = By.xpath("(//a[contains(@href,'checkout/cart')]/..)[4]");
		return SeleniumHelper.waitForVisibility(Constant.driver, by, "Product Added to Cart Message");
	}

	public static WebElement shoppingCart() {
		element = null;
		try {
			element = Constant.driver.findElement(By.xpath("(//a[contains(@href,'checkout/cart')])[4]"));
		} catch (Exception e) {
			logger.error("Shopping cart link not found" + e);
			Assert.fail("Shopping cart link not found");
		}
		return element;
	}

	public static WebElement proceedToCheckoutButton() {
		element = null;
		By by = By.xpath("//span[contains(text(),'Proceed to Checkout')]");
		return SeleniumHelper.waitForVisibility(Constant.driver, by, "Proceed to Checkout Button");
	}

}
