package com.automation.framework.common;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonVariable {
	private static Logger logger = LogManager.getLogger(CommonVariable.class);
	private static String url;
	private static String browserName;
	private static String version;
	private static String os;
	private static String platform;
	private static String driverPath;
	private static String waitTimeInSeconds;
	private static String headlessBrowser;
	private static String breakPoint;
	private static String cucumberJsonReportPath;
	private static String cucumberUsageJsonReportPath;
	private static String udid;
	private static String deviceName;
	private static String appiumServerUrl;
	private static String perfectoUser;
	private static String perfectoPassword;
	private static String currentRootDir = System.getProperty("user.dir");
	private static String maximizeBrowser;
	private static String userAgent;

	public CommonVariable() {
	}

	public void init(Properties prop) {
		logger.info("Started to initialize the properties value");
		url = readProperty(prop, "url");
		browserName = readProperty(prop, "browserName");
		maximizeBrowser = readProperty(prop, "maximizeBrowser");
		version = readProperty(prop, "version");
		platform = readProperty(prop, "platform");
		driverPath = readProperty(prop, "driverPath");
		waitTimeInSeconds = readProperty(prop, "waitTimeInSeconds");
		headlessBrowser = readProperty(prop, "headlessBrowser");
		breakPoint = readProperty(prop, "breakPoint");
		cucumberJsonReportPath = readProperty(prop, "cucumberJsonReportPath");
		cucumberUsageJsonReportPath = readProperty(prop, "cucumberUsageJsonReportPath");
		udid = readProperty(prop, "udid");
		os = System.getProperty("os.name").toLowerCase().substring(0, 3);
		deviceName = readProperty(prop, "deviceName");
		appiumServerUrl = readProperty(prop, "appiumServerUrl");
		perfectoUser = readProperty(prop, "perfectoUser");
		perfectoPassword = readProperty(prop, "perfectoPassword");
		userAgent = readProperty(prop, "userAgent");

		logger.info("All the properties values are initialized");
	}

	public String readProperty(Properties prop, String key) {
		return prop.getProperty(key);
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		CommonVariable.url = url;
	}

	public static String getBrowserName() {
		return browserName;
	}

	public static void setBrowserName(String browserName) {
		CommonVariable.browserName = browserName;
	}

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String version) {
		CommonVariable.version = version;
	}

	public static String getOs() {
		return os;
	}

	public static void setOs(String os) {
		CommonVariable.os = os;
	}

	public static String getPlatform() {
		return platform;
	}

	public static void setPlatform(String platform) {
		CommonVariable.platform = platform;
	}

	public static String getDriverPath() {
		return driverPath;
	}

	public static void setDriverPath(String driverPath) {
		CommonVariable.driverPath = driverPath;
	}

	public static String getWaitTimeInSeconds() {
		return waitTimeInSeconds;
	}

	public static void setWaitTimeInSeconds(String waitTimeInSeconds) {
		CommonVariable.waitTimeInSeconds = waitTimeInSeconds;
	}

	public static String getHeadlessBrowser() {
		return headlessBrowser;
	}

	public static void setHeadlessBrowser(String headlessBrowser) {
		CommonVariable.headlessBrowser = headlessBrowser;
	}

	public static String getBreakPoint() {
		return breakPoint;
	}

	public static void setBreakPoint(String breakPoint) {
		CommonVariable.breakPoint = breakPoint;
	}

	public static String getCucumberJsonReportPath() {
		return cucumberJsonReportPath;
	}

	public static void setCucumberJsonReportPath(String cucumberJsonReportPath) {
		CommonVariable.cucumberJsonReportPath = cucumberJsonReportPath;
	}

	public static String getCucumberUsageJsonReportPath() {
		return cucumberUsageJsonReportPath;
	}

	public static void setCucumberUsageJsonReportPath(String cucumberUsageJsonReportPath) {
		CommonVariable.cucumberUsageJsonReportPath = cucumberUsageJsonReportPath;
	}

	public static String getUdid() {
		return udid;
	}

	public static void setUdid(String udid) {
		CommonVariable.udid = udid;
	}

	public static String getDeviceName() {
		return deviceName;
	}

	public static void setDeviceName(String deviceName) {
		CommonVariable.deviceName = deviceName;
	}

	public static String getAppiumServerUrl() {
		return appiumServerUrl;
	}

	public static void setAppiumServerUrl(String appiumServerUrl) {
		CommonVariable.appiumServerUrl = appiumServerUrl;
	}

	public static String getPerfectoUser() {
		return perfectoUser;
	}

	public static void setPerfectoUser(String perfectoUser) {
		CommonVariable.perfectoUser = perfectoUser;
	}

	public static String getPerfectoPassword() {
		return perfectoPassword;
	}

	public static void setPerfectoPassword(String perfectoPassword) {
		CommonVariable.perfectoPassword = perfectoPassword;
	}

	public static String getCurrentRootDir() {
		return currentRootDir;
	}

	public static void setCurrentRootDir(String currentRootDir) {
		CommonVariable.currentRootDir = currentRootDir;
	}

	public static String getMaximizeBrowser() {
		return maximizeBrowser;
	}

	public static void setMaximizeBrowser(String maximizeBrowser) {
		CommonVariable.maximizeBrowser = maximizeBrowser;
	}

	public static String getUserAgent() {
		return userAgent;
	}

	public static void setUserAgent(String userAgent) {
		CommonVariable.userAgent = userAgent;
	}


}
