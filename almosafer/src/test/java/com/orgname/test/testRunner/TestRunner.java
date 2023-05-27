package com.orgname.test.testRunner;

import com.aventstack.extentreports.ExtentReports;
import com.orgname.framework.api.ExtentReport;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber-reports/Cucumber2.json"}, 
		features = "src/test/java/com/orgname/test/features",
		glue = "com.orgname.test.stepdefinitions",
		 tags = {"@API"},
		monochrome=true)

public class TestRunner {
	public static ExtentReports  extentReport;
	@BeforeClass
	public static void setUp() {
		extentReport = ExtentReport.extentReportCreation();
	}

	@AfterClass
	public static void tearDown() {
		extentReport.flush();
	}
}
