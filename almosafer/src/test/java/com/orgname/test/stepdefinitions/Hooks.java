package com.orgname.test.stepdefinitions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.orgname.test.testRunner.TestRunner;
import cucumber.api.Result;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    ExtentTest extentTest;
    @Before ("@API")
        public void setupApi(Scenario scenario){
        extentTest= TestRunner.extentReport.createTest(scenario.getName());
        scenario.write("I am here in hooks");
        }
        @After ("@API")
        public void tearDownApi(Scenario scenario) {
            if (scenario.getStatus()== Result.Type.PASSED)
                extentTest.log(Status.PASS, "Test is "+scenario.getStatus());
            else if (scenario.isFailed())
                    extentTest.log(Status.FAIL, "Test is "+ scenario.getLines());
            }
        }
