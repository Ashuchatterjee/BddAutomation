package com.ashish.helper;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.ashish.constants.Constant;

public class SeleniumHelper {

	private static Logger logger = LogManager.getLogger(SeleniumHelper.class);

	/**
	 * @author ashishchatterjee
	 * @param browser
	 * @return WebDriver
	 */
	public static WebDriver openBrowser(String browser) {
		switch (browser.toLowerCase()) {
		case "chrome":
			Constant.driver = new ChromeDriver();
			Constant.driver.manage().window().maximize();
			logger.info("Chrome Browser Launched");
			break;
		case "firefox":
			Constant.driver = new FirefoxDriver();
			Constant.driver.manage().window().maximize();
			logger.info("Fire fox Browser Launched");
			break;
		default:
			logger.error("No browser found with the name: " + browser);
			break;
		}
		return Constant.driver;
	}

	public static void pageScroll(WebDriver driver, String from, String to) {
		SeleniumHelper.browserWait(2);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + from + "," + to + ")");
	}

	/**
	 * @author ashishchatterjee
	 * @param seconds
	 */
	public static void browserWait(int seconds) {
		int milliseconds = seconds * 1000;
		try {
			Thread.sleep(milliseconds);
			logger.info("Wait for '" + seconds + "' seconds");
		} catch (InterruptedException e) {

		}
	}

	/**
	 * @author ashishchatterjee
	 * @param WebDriver
	 * @return boolean
	 */
	public static boolean waitForJStoLoad(WebDriver driver) {
		JavascriptExecutor javaScript = (JavascriptExecutor) driver;
		Long time = (long) Integer.parseInt(Constant.prop.getProperty("ObjectWaitTime"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) javaScript.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return javaScript.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	/**
	 * @author ashishchatterjee
	 * @param driver
	 * @param by
	 * @param description
	 * @return WebElement
	 */
	public static WebElement waitForVisibility(WebDriver driver, By by, String description) {
		WebElement returnElement = null;
		try {
			SeleniumHelper.waitForJStoLoad(driver);
			Long time = (long) Integer.parseInt(Constant.prop.getProperty("ObjectWaitTime"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
			returnElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			// returnElement=testConfig.driver.findElement(by);
			logger.info(description + " is visible now.");
		} catch (Exception we) {
			returnElement = null;
			logger.error(description + " not found on page", we, true);
		}
		return returnElement;
	}

	/**
	 * @author ashishchatterjee
	 * @param driver
	 * @param by
	 * @param description
	 * @return
	 */
	public static WebElement waitForElementToBeClickAble(WebDriver driver, By by, String description) {
		WebElement returnElement = null;
		try {
			SeleniumHelper.waitForJStoLoad(driver);
			Long time = (long) Integer.parseInt(Constant.prop.getProperty("ObjectWaitTime"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
			returnElement = wait.until(ExpectedConditions.elementToBeClickable(by));
			// returnElement=testConfig.driver.findElement(by);
			logger.info(description + " is visible now.");
		} catch (Exception we) {
			returnElement = null;
			logger.error(description + " not found on page", we, true);
		}
		return returnElement;
	}

	/**
	 * @author ashishchatterjee
	 * @param element
	 * @param index
	 * @param description
	 */
	public static void selectElementFromDropDownUsingIndex(WebElement element, int index, String description) {
		Select sc = new Select(element);
		sc.selectByIndex(index);
		logger.info(description + " index " + index + " is selected ");
	}

	public static void selectElementFromDropDownUsingText(WebElement element, String value, String description) {
		Select sc = new Select(element);
		try {
			sc.selectByVisibleText(value);
			logger.info(description + value + " is selected ");
		} catch (NoSuchElementException e) {
			logger.info("Cannot locate option with value: " + value);
			logger.info("Exception: " + e.getStackTrace());
			Assert.fail("Cannot locate option with value: " + value);
		}

	}

	/**
	 * @author ashishchatterjee
	 * @param element
	 * @return
	 */
	public static List<WebElement> getListOfWebElementsFromDropDown(WebElement element) {
		Select sc = new Select(element);
		return sc.getOptions();
	}

}
