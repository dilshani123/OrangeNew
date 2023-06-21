package com.home.testsuite;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.home.lib.LIB_Orange;
import com.home.utility.TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_005ReadFromExcel extends TestBase {

	public String filenme="E://OrangeWeb//OrangeWeb//src//main//java//dataFile//credentials.xls";
	public String sheetName="Sheet1";
	@DataProvider(name = "empLogin")
	   public Object[][] loginData() {
Object[][] arrayObject = getExcelData(filenme, sheetName);
return arrayObject;
}
	@Test(dataProvider ="empLogin" )
	public void tc_verifyNavigationToCandidate(String username,String passwoard) {
		test=extent.createTest("TC_005_verifyNavigationToCandidate");
		Reporter.log("==Start TC_005_verifyNavigationToCandidate == ",true);
		LIB_Orange.bc_verifyLoginUser(username,passwoard,"ajay");
//		LIB_Orange.bc_navigationTo();
		Reporter.log("==END  TC_005_verifyNavigationToCandidate == ",true);
	}
}
