package com.home.testsuite;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.home.lib.LIB_Orange;
import com.home.utility.TestBase;

public class TC_003_verifySearch extends TestBase {

	@DataProvider(name="dt_dataProvider1")
	public Object[][] dataProvider()
	{
		return new Object [][] {{"Admin","admin123","John"}}; 
		//return new Object [][] {{"Admin"},{"admin123"},{"John"}}; 	this is iterate 	
		}
	
	@Test(dataProvider="dt_dataProvider1")
	public void tc_verifySearchCandidate(String prm_username, String prm_Password,String value) throws InterruptedException {
		test=extent.createTest("TC_003_verifySearch");
		Reporter.log("==Start TC_003_verifySearch == ",true);
		LIB_Orange.bc_verifyLoginUser(prm_username,prm_Password,value);
		LIB_Orange.bc_navigationTo();
		
		LIB_Orange.bc_verifyJobcount(28);
		LIB_Orange.bc_selecAnotherJob("QA Lead");
		LIB_Orange.bc_searchFromJobAndManager("Software Engineer","Odis Adalwin");
		LIB_Orange.bc_scrolldown();
		LIB_Orange.bc_verifyResultcount(3);
		Reporter.log("==END  TC_003_verifySearch == ",true);
	}
}
