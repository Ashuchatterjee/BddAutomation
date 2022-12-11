package com.ashish.stepdefination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ashish.commonutils.TestNgUtil;
import com.ashish.constants.Constant;
import com.ashish.helper.ApiHelper;
import com.ashish.pojo.User;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import io.restassured.response.Response;

@CucumberOptions(features = "src/test/resources/FeatureFiles/", glue = {
		"com.ashish.stepdefination" }, monochrome = true, plugin = { "pretty",
				"html:target/cucumber-reports/cucumber-pretty", "json:target/cucumber-reports/CucumberTestReport.json",
				"rerun:target/cucumber-reports/rerun.txt" }, tags = { "@tag" })

public class ApiTestRunner extends TestNgUtil {

	private static Logger logger = LogManager.getLogger(ApiTestRunner.class);

	@Given("^Hit the genrate user API$")
	public void hitGenrateUserApi() {
		String baseUrl = (String) Constant.prop.get("API_BASE_URL");
		Response rs = ApiHelper.executeAndGetResponse(baseUrl, "get", null, null, "", null);
		try {
			Constant.user = rs.getBody().as(User.class);
		} catch (Exception e) {
			logger.error("Json to class binding error: " + e.getStackTrace());
		}

	}

}
