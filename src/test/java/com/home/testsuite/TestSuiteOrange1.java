package com.home.testsuite;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.home.lib.LIB_Orange;
import com.home.utility.TestBase;

public class TestSuiteOrange1 extends TestBase {


	@Test (description ="TC 001", priority=1)
	public void tc_001_web_loginApplication() throws Exception {
		test=extent.createTest("TC_001_verifyLogin");
		
		
		driver.get(getDataFromPropertyFile("URL"));
	SessionId s = ((RemoteWebDriver) driver).getSessionId();
		System.out.println("Session Id is 1: " + s);
	//	String x1=driver.getTitle();
	//	System.out.println(x1);
		LIB_Orange.bc_verifyLoginUserUsername("Admin1");
		Thread.sleep(2000);
	
	}
	
	@Test (description ="TC 002" , dependsOnMethods="tc_001_web_loginApplication",priority=3)
	public void tc_002_tc_verifyNavigationToCandidate() throws Exception {
		test=extent.createTest("TC_002_verifyNavigationToCandidate");
	
		TestBase.driver.get(getDataFromPropertyFile("URL"));
		SessionId s = ((RemoteWebDriver) driver).getSessionId();
		System.out.println("Session Id is 2: " + s);
		String x1=driver.getTitle();
		System.out.println(x1);
		//LIB_Orange.bc_verifyLoginUser("Admin", "admin123","Welcome");
	
		LIB_Orange.bc_verifyLoginUserUsername("Admin2");
		
		Thread.sleep(2000);
	//	driver.close();
	
		//LIB_Orange.bc_navigationTo();
	//	Reporter.log("==END  TC_002_verifyNavigationToCandidate == ",true);
	}
	@Test (description ="TC 003", dependsOnMethods="tc_001_web_loginApplication",priority=2)
	public void tc_003_tc_loginApplication() throws Exception {
		test=extent.createTest("TC_003_verifyLogin");
		
		
		driver.get(getDataFromPropertyFile("URL"));
		SessionId s = ((RemoteWebDriver) driver).getSessionId();
		System.out.println("Session Id is 3: " + s);
	
	LIB_Orange.bc_verifyLoginUserUsername("Admin3");
		//LIB_Orange.bc_verifyLoginUser("Admin", "admin123","Paul");
		Thread.sleep(2000);
	}
	
	/*@AfterSuite
		public static void quiteBrowser() {
		   if(driver != null) {
			
			driver.close();
			System.out.println("Session closed");
			  extent.flush();
			 
		   }
		}*/
}
