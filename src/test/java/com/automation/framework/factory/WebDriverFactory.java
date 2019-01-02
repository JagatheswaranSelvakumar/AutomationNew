package com.automation.framework.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.automation.framework.browserOption.ChromeBrowserOption;
import com.automation.framework.browserOption.FirefoxBrowserOption;
import com.automation.framework.common.CommonVariable;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class WebDriverFactory implements DriverFactory {
	private static Logger logger = LogManager.getLogger(WebDriverFactory.class);
	protected static WebDriver driver;

	public WebDriver createDriver(DesiredCapabilities caps) {
		String browser = caps.getBrowserName();
		String breakPoint = CommonVariable.getBreakPoint();
		if (browser.equalsIgnoreCase("firefox")) {
			FirefoxBrowserOption firefoxBrowserOption = new FirefoxBrowserOption();
			firefoxBrowserOption.setHeadless(Boolean.parseBoolean(CommonVariable.getHeadlessBrowser()));
			driver = new FirefoxDriver(firefoxBrowserOption.build());
		} else if (browser.equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			if (breakPoint.equalsIgnoreCase("Desktop")) {
				driver = new SafariDriver();
			} else if (breakPoint.equalsIgnoreCase("Mobile") || breakPoint.equalsIgnoreCase("Tablet")) {
				try {
					initializeiOSMobileBrowserCapabilities(caps);
					driver = new IOSDriver<IOSElement>(new URL(CommonVariable.getAppiumServerUrl()), caps);
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.MILLISECONDS);
				} catch (MalformedURLException e) {
					logger.error("Error in initialize iOS Mobile Safari driver");
					logger.error(e.getMessage());
				}
			}
		} else if (browser.equalsIgnoreCase("chrome")) {
			if (breakPoint.equalsIgnoreCase("Desktop") && CommonVariable.getUserAgent().equalsIgnoreCase("no")) {
				ChromeBrowserOption chromeBrowserOption = new ChromeBrowserOption();
				chromeBrowserOption.setHeadless(Boolean.parseBoolean(CommonVariable.getHeadlessBrowser()));
				driver = new ChromeDriver(chromeBrowserOption.build());
			} else if (breakPoint.equalsIgnoreCase("Desktop") && CommonVariable.getUserAgent().equalsIgnoreCase("Yes")) {
				CommonVariable.setBreakPoint("Mobile");
				driver = new ChromeDriver();
				driver.manage().window().setSize(new Dimension(360, 640));
			} else if (breakPoint.equalsIgnoreCase("Mobile") || breakPoint.equalsIgnoreCase("Tablet")) {
				try {
					initializeAndroidMobileBrowserCapabilities(caps);
					driver = new AndroidDriver<AndroidElement>(new URL(CommonVariable.getAppiumServerUrl()), caps);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
				} catch (MalformedURLException e) {
					logger.error("Error in initialize Android Mobile Chrome driver");
					logger.error(e.getMessage());
				}
			} else {
				logger.error(
						"Breakpoint which you metioned in application.properties something wrong...Please check it!!!!!!");
				try {
					throw new Exception(
							"Breakpoint which you metioned in application.properties something wrong...Please check it!!!!!!");
				} catch (Exception e) {
				}
			}
		} else {
			try {
				logger.error(
						"Browser which you metioned in application.properties something wrong...Please check it!!!!!!");
				throw new Exception(
						"Browser which you metioned in application.properties something wrong...Please check it!!!!!!");
			} catch (Exception e) {
			}
		}
		return driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public DesiredCapabilities initializeAndroidMobileBrowserCapabilities(DesiredCapabilities caps) {
		caps.setCapability("user", CommonVariable.getPerfectoUser());
		caps.setCapability("password", CommonVariable.getPerfectoPassword());
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, CommonVariable.getPlatform());
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, CommonVariable.getVersion());
		caps.setCapability(MobileCapabilityType.UDID, CommonVariable.getUdid());
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, CommonVariable.getDeviceName());
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, CommonVariable.getBrowserName());
		caps.setCapability(MobileCapabilityType.NO_RESET, false);
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		caps.setCapability(MobileCapabilityType.NO_RESET, false);
		caps.setCapability("--session-override", true);
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		return caps;
	}

	public DesiredCapabilities initializeiOSMobileBrowserCapabilities(DesiredCapabilities caps) {
		caps.setCapability("user", CommonVariable.getPerfectoUser());
		caps.setCapability("password", CommonVariable.getPerfectoPassword());
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, CommonVariable.getPlatform());
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, CommonVariable.getVersion());
		caps.setCapability(MobileCapabilityType.UDID, CommonVariable.getUdid());
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, CommonVariable.getDeviceName());
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, CommonVariable.getBrowserName());
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		caps.setCapability(MobileCapabilityType.NO_RESET, false);
		caps.setCapability("--session-override", true);
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		return caps;
	}
}
