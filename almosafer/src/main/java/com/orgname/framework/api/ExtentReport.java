package com.orgname.framework.api;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    public static ExtentReports extentReportCreation() {
        String path = System.getProperty("user.dir") + "\\reports\\Almosafer_Extent_Report.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("Almosafer Report");
        extentSparkReporter.config().setDocumentTitle("Almosafer Extent Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(extentSparkReporter);
        return extent;
    }
}
