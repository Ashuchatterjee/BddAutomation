package com.ashish.stepdefination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.ashish.commonutils.TestNgUtil;
import com.ashish.constants.Constant;
import com.ashish.helper.SeleniumHelper;
import com.ashish.pom.CreateAccount;
import com.ashish.pom.HomePage;
import com.ashish.pom.MensPage;
import com.ashish.pom.MyAccountPage;
import com.ashish.pom.ShippingAddress;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@CucumberOptions(features = "src/test/resources/FeatureFiles/", glue = {
		"com.ashish.stepdefination" }, monochrome = true, plugin = { "pretty",
				"html:target/cucumber-reports/cucumber-pretty", "json:target/cucumber-reports/CucumberTestReport.json",
				"rerun:target/cucumber-reports/rerun.txt" }, tags = { "@tag" })

public class TestStoreStepDefination extends TestNgUtil {
	private static Logger logger = LogManager.getLogger(TestStoreStepDefination.class);

	@When("^Navigate to site of the test store$")
	public void navigateToSiteOfTheTestStore() throws Throwable {
		Constant.driver.get((String) Constant.prop.get("WEBSITE_URL"));
		logger.info("Navigated to: " + Constant.prop.get("WEBSITE_URL"));
	}

	/**
	 * @author ashishchatterjee
	 * @throws InterruptedException
	 */
	@Then("^Register new user on the test store site using the data of user API$")
	public void registerNewUserOnTheTestStoreSiteUsingTheDataOfUserApi() {
		HomePage.createAccountButton().click();
		String name = Constant.user.getResults().get(0).getName().getFirst();
		CreateAccount.firstNameTextBox().sendKeys(name);
		logger.info("Entered First Name: " + name);
		String lastName = Constant.user.getResults().get(0).getName().getLast();
		CreateAccount.lastNameTextBox().sendKeys(lastName);
		logger.info("Entered Last Name: " + lastName);
		String emailId = Constant.user.getResults().get(0).getEmail();
		CreateAccount.emailTextBox().sendKeys(emailId);
		logger.info("Entered Email ID : " + emailId);
		CreateAccount.passwordTextBox().sendKeys((String) Constant.prop.get("PASSWORD"));
		logger.info("Entered Password");
		CreateAccount.confirmPasswordTextBox().sendKeys((String) Constant.prop.get("PASSWORD"));
		logger.info("Entered Password Confirmation");
		SeleniumHelper.pageScroll(Constant.driver, "0", "150");
		CreateAccount.submitButton().click();
		logger.info("Clicked on create account submit button");
		SeleniumHelper.browserWait(3);
		if (!MyAccountPage.thankyouNote().isDisplayed()) {
			Assert.fail("Thank you note not available");
		}
	}

	@Then("^Add any item to cart$")
	public void addAnyItemToCart() {
		HomePage.menLink().click();
		logger.info("Mens link clicked");
		MensPage.hoodiesAndSweatshirtsButton().click();
		logger.info("Hoodies & Sweatshirts link clicked");
		MensPage.firstProductImage().click();
		logger.info("First product in the list is clicked");
		SeleniumHelper.browserWait(2);
		String size = "XS";
		MensPage.selectSizeButton(size).click();
		logger.info("Selected size: " + size);
		MensPage.selectColour().click();
		logger.info("Product Colour Selected");
		MensPage.quantityTextBox().click();
		MensPage.quantityTextBox().clear();
		MensPage.quantityTextBox().sendKeys("1");
		logger.info("Entered the quantity");
		MensPage.addToCartButton().click();
		logger.info("Product added to cart");
		SeleniumHelper.browserWait(3);
		if (!MensPage.validateMessageAddToCart().isDisplayed()) {
			Assert.fail("ProductAdded to cart is failed");
		}
	}

	@Then("^Proceed to checkout$")
	public void proceedToCheckout() {
		MensPage.shoppingCart().click();
		logger.info("Clicked on the shopping cart link");
		MensPage.proceedToCheckoutButton().click();
		logger.info("Clicked on proceed to checkout button link");
		SeleniumHelper.browserWait(5);
		String address = Constant.user.getResults().get(0).getLocation().getStreet().getName();
		ShippingAddress.streetAddressTextBox().sendKeys(address);
		logger.info("Entered street address: " + address);
		String city = Constant.user.getResults().get(0).getLocation().getCity();
		ShippingAddress.cityTextBox().sendKeys(city);
		logger.info("Entered City: " + city);
		SeleniumHelper.pageScroll(Constant.driver, "0", "100");
		SeleniumHelper.browserWait(3);
		SeleniumHelper.selectElementFromDropDownUsingText(ShippingAddress.stateDropDown(), "Alaska", "State");
		Object postCode = Constant.user.getResults().get(0).getLocation().getPostcode();
		if (postCode instanceof String) {
			ShippingAddress.postcodeTextBox().sendKeys(postCode.toString());
		} else if (postCode instanceof Integer) {
			int code = (int) postCode;
			ShippingAddress.postcodeTextBox().sendKeys(String.valueOf(code));
		}
		logger.info("Entered PostCode: " + postCode);
		String country = Constant.user.getResults().get(0).getLocation().getCountry();
		SeleniumHelper.selectElementFromDropDownUsingText(ShippingAddress.countryDropDown(), country, "Country");
		String phone = (String) Constant.user.getResults().get(0).getPhone();
		ShippingAddress.phoneNumberTextBox().sendKeys(phone);
		logger.info("Entered Phone Number: " + phone);
		ShippingAddress.fixedShippingMethodCheckBox().click();
		logger.info("Fixed Shipping Method Selected");
		SeleniumHelper.browserWait(3);
		ShippingAddress.nextButton().click();
		logger.info("Clicked on Next Button");
		SeleniumHelper.browserWait(5);
		ShippingAddress.placeOrderButton().click();
		logger.info("Clicked on place order");
		if (!ShippingAddress.thankYouPurchaseNote().isDisplayed()) {
			Assert.fail("Thank You Note is not displayed");
			logger.info("Thank You Note is not displayed");

		}
	}
	
	 @Then("^Verify the purchase$")
	    public void verifyurchase() {
		 String orderId = ShippingAddress.getOrderNumber().getText();
		 logger.info("Order Id: "+orderId);
		 ShippingAddress.getOrderNumber().click();
	    }
}
