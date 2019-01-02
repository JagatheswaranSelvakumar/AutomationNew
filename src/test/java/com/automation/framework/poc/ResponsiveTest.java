package com.automation.framework.poc;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ResponsiveTest {
	public static WebDriver driver= null;
	public static void main(String[] args) throws InterruptedException {
		
		Map<String, Object> deviceMetrics = new HashMap<>();

		deviceMetrics.put("width", 320);

		deviceMetrics.put("height", 700);

		deviceMetrics.put("pixelRatio", 3.0);



		Map<String, Object> mobileEmulation = new HashMap<>();

		mobileEmulation.put("deviceMetrics", deviceMetrics);

		mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.2; nl-nl; SAMSUNG GT-I9505 Build/JDQ39) AppleWebKit/535.19 (KHTML, like Gecko) Version/1.0 Chrome/18.0.1025.308 Mobile Safari/535.19");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		
		driver = new ChromeDriver(chromeOptions);
		driver.get("https://www.manulife.ca/for-you.html");
		
		
		Thread.sleep(10000);
		driver.quit();
		
	}

}
