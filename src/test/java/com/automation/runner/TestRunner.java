package com.automation.runner;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.automation.framework.common.CommonVariable;
import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(glue = "com/automation/stepdefinition", features = "src/test/java/com/automation/feature", plugin = {
		"html:target/cucumber-htmlreport", "json:target/cucumber-report.json", "pretty:target/cucumber-pretty.txt",
		"usage:target/cucumber-usage.json",
		"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:" }, tags = { "@VerifySubmitClaimButton" }, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

	@BeforeSuite(alwaysRun = true)
	public static void setup() throws IOException {
		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
		extentProperties.setReportPath(CommonVariable.getCurrentRootDir() + "/ExtentReport/Automation_Execution_Report.html");
	}

	@AfterSuite(alwaysRun = true)
	public void getResult() throws IOException {
		Reporter.loadXMLConfig(new File(CommonVariable.getCurrentRootDir() + "/src/test/resources/extent-config.xml"));
	}
}
