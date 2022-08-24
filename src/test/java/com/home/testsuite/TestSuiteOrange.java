package com.home.testsuite;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.home.lib.LIB_Orange;
import com.home.utility.TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestSuiteOrange extends TestBase {


	@Test (groups = {"myTest"})
	public void tc_001_tc_loginApplication() throws Exception {
		test=extent.createTest("TC_001_verifyLogin");
		
		
		driver.get(getDataFromPropertyFile("URL"));
		SessionId s = ((RemoteWebDriver) driver).getSessionId();
		System.out.println("Session Id is 1: " + s);
		String x1=driver.getTitle();
		System.out.println(x1);
	//	Reporter.log("==Start TC_001_verifyLogin == ",true);
	//	LIB_Orange.bc_verifyLoginUser("Admin", "admin123","Welcome");
		LIB_Orange.bc_verifyLoginUserUsername("Admin");
	//	driver.quit();
	//	System.out.println("Session 1 closed");
	//	Reporter.log("==END  TC_001_verifyLogin == ",true);
	}
	
	@Test
	public void tc_002_tc_verifyNavigationToCandidate() throws Exception {
		test=extent.createTest("TC_002_verifyNavigationToCandidate");
	//	driver.quit();
	
	//	String x=driver.getTitle();
	//	System.out.println(x);
	//	Reporter.log("==Start TC_002_verifyNavigationToCandidate == ",true);
	//	TestBase.driver.manage().window().maximize();
	//	TestBase.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		TestBase.driver.get(getDataFromPropertyFile("URL"));
		SessionId s = ((RemoteWebDriver) driver).getSessionId();
		System.out.println("Session Id is 2: " + s);
		String x1=driver.getTitle();
		System.out.println(x1);
		//LIB_Orange.bc_verifyLoginUser("Admin", "admin123","Welcome");
		LIB_Orange.bc_verifyLoginUserUsername("Admin");
		driver.close();
		System.out.println("Session 2 closed");
		//LIB_Orange.bc_navigationTo();
	//	Reporter.log("==END  TC_002_verifyNavigationToCandidate == ",true);
	}
	@Test (groups = {"myTest"})
	public void tc_003_tc_loginApplication() throws Exception {
		test=extent.createTest("TC_003_verifyLogin");
		
		
		driver.get(getDataFromPropertyFile("URL"));
		SessionId s = ((RemoteWebDriver) driver).getSessionId();
		System.out.println("Session Id is 3: " + s);
		String x1=driver.getTitle();
		System.out.println(x1);

		LIB_Orange.bc_verifyLoginUserUsername("Admin3");
	
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
