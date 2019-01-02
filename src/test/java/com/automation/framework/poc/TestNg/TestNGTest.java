package com.automation.framework.poc.TestNg;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNGTest {
	@Test(dataProvider = "dp1")
	public void f(Integer n, String s) {
		System.out.println(n + "=>" + s);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}

	@DataProvider(name = "dp")
	public Object[][] dp() {
		return new Object[][] { 
			new Object[] { 1, "a" }, new Object[] { 2, "b" }, new Object[] { 3, "c" }, };
	}

	@DataProvider(name = "dp1")
	public Object[][] dp1() {
		return new Object[][] { { 1, "a" }, { 2, "b" }, { 3, "c" } };
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before class");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After Class");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("After Test");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");
	}

}
