package com.ashish.commonutils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

public class TestNgUtil extends AbstractTestNGCucumberTests {
	protected TestNGCucumberRunner runner;

	@BeforeSuite(alwaysRun = true)

	public void setUpClass() {
		runner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper features) {
		runner.runCucumber(features.getCucumberFeature());
	}


	@DataProvider
	public Object[][] features() {
		return runner.provideFeatures();

	}
	
	@AfterSuite(alwaysRun = true)
	public void tearDownClass() {
		runner.finish();
	}

}
