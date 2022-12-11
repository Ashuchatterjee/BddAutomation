package com.ashish.TestRunner;

import com.ashish.commonutils.TestNgUtil;

import cucumber.api.CucumberOptions;

@CucumberOptions(features = "src/test/resources/FeatureFiles/", glue = { "com.ashish.stepdefination" }, plugin = {
		"pretty", "html:target/cucumber-reports/cucumber-pretty",
		"json:target/cucumber-reports/CucumberTestReport.json",
		"rerun:target/cucumber-reports/rerun.txt" }, tags = { "@tag" })

public class CommonRunner extends TestNgUtil {

}
