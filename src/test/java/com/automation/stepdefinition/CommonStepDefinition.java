package com.automation.stepdefinition;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.automation.framework.builder.WebCapabilitiesBuilder;
import com.automation.framework.common.CommonLibrary;
import com.automation.framework.common.CommonVariable;
import com.automation.framework.factory.WebDriverFactory;
import com.vimalselvam.cucumber.listener.Reporter;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CommonStepDefinition {
	protected static WebDriver driver;
	protected DesiredCapabilities caps;
	private static Logger logger = LogManager.getLogger(CommonStepDefinition.class);
	protected static Properties prop;
	public CommonVariable commonVariables;
	ATUTestRecorder recorder;

	@Before
	public void beforeScenario() throws Exception {
		logger.info("Automation Execution start method");
		caps = new WebCapabilitiesBuilder().addBrowser(CommonVariable.getBrowserName())
				.addBrowserDriverExecutablePath(
						System.getProperty("user.dir") + File.separator + CommonVariable.getDriverPath())
				.addVersion(CommonVariable.getVersion()).addPlatform(CommonVariable.getPlatform()).build();
		driver = new WebDriverFactory().createDriver(caps);
		CommonLibrary.openUrl(CommonVariable.getUrl());
		recorder = new ATUTestRecorder(System.getProperty("user.dir"), "SeleniumVideo", false);
		recorder.start();
		if (CommonVariable.getBreakPoint().equalsIgnoreCase("DESKTOP")
				&& CommonVariable.getMaximizeBrowser().equalsIgnoreCase("Yes")) {
			logger.info("Running Automation in Desktop " + CommonVariable.getBrowserName()
					+ " Browser with maximize browser size");
			driver.manage().window().maximize();
		} else if (CommonVariable.getBreakPoint().equalsIgnoreCase("MOBILE")) {
			logger.info("Running Automation in Mobile " + CommonVariable.getBrowserName() + " Browser");
		}
		driver.manage().deleteAllCookies();

	}

	@After
	public void afterScenario(Scenario scenario) throws InterruptedException, IOException, ATUTestRecorderException {
		if (scenario.isFailed()) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destinationPath = new File(CommonVariable.getCurrentRootDir() + File.separator + "target" + File.separator
					+ "screenshots" + File.separator + scenario.getName() + ".png");
			FileUtils.copyFile(scrFile, destinationPath);
			FileUtils.copyFile(
					OutputType.FILE.convertFromBase64Png(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)),
					new File(CommonVariable.getCurrentRootDir() + File.separator + "target" + File.separator + "screenshots"
							+ File.separator + scenario.getName() + ".png"));
			scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
			Reporter.addScreenCaptureFromPath(destinationPath.toString());
		}
		if (!CommonVariable.getBrowserName().equalsIgnoreCase("firefox")) {
			driver.close();
		}
		driver.quit();
		recorder.stop();
	}
}
