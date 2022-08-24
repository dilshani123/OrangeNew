package com.home.testsuite;

import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.home.lib.LIB_Orange;
import com.home.utility.TestBase;

public class TC_004_verifyLoginFromDB extends TestBase {

	@Test
	public void tc_loginApplication() throws SQLException {
		test=extent.createTest("TC_004_verifyLoginFromDB");
		Reporter.log("==Start TC_004_verifyLoginFromDB == ",true);
	
	//	LIB_Orange.bc_ReadFromDB();
		HashMap<String, String> data = LIB_Orange.bc_ReadFromDB1();
		String user = data.get("username");
		System.out.println(user);
		String pw = data.get("password");
		System.out.println(pw);
		LIB_Orange.bc_verifyLoginUser(user, pw,"Welcome");
		Reporter.log("==END  TC_004_verifyLoginFromDB == ",true);
	}
}
