package com.home.testsuite;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.home.lib.LIB_Orange;
import com.home.utility.TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_002_verifyNavigationToCandidate extends TestBase {

	
	@Test
	public void tc_verifyNavigationToCandidate() {
		test=extent.createTest("TC_002_verifyNavigationToCandidate");
		Reporter.log("==Start TC_002_verifyNavigationToCandidate1 == ",true);
		LIB_Orange.bc_verifyLoginUser("Admin", "admin123","John");
//		LIB_Orange.bc_navigationTo();
		Reporter.log("==END  TC_002_verifyNavigationToCandidate == ",true);
	}
}
