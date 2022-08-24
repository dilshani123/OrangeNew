package com.home.lib;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import java.sql.Connection;
import com.aventstack.extentreports.Status;
import com.home.pages.PG_Candidate;
import com.home.pages.PG_Login;
import com.home.pages.PG_Navigation;
import com.home.utility.TestBase;


public class LIB_Orange extends TestBase{

	static PG_Login login= new PG_Login(TestBase.driver);
	static PG_Navigation navigation = new PG_Navigation(TestBase.driver);
	static PG_Candidate candidate=new PG_Candidate(TestBase.driver);
	
	public static void bc_verifyLoginUser(String prm_username,String prm_password,String prm_system) {
		//.log("==Start  bc_verifyLoginUser == ",true);
		
		test.log(Status.INFO,"Start  bc_verifyLoginUser");
		login.typeUsername(prm_username);
		test.log(Status.INFO,"Type username value is : "+prm_username +" : in element : "+ PG_Login.ele_userName);
		
		login.typePassword2(prm_password);
		test.log(Status.INFO,"Type password value is : "+prm_password +":  in element : "+PG_Login.getEle_password());
		login.clickSignIn();
		test.log(Status.INFO,"click  signIn button : " +" in element : "+ PG_Login.btn_login);
		login.checkWelcomeandGetText(prm_system);
	//	Reporter.log("==End  bc_verifyLoginUser == ",true);
	}
	public static void bc_verifyLoginUserUsername(String prm_username) {
		//.log("==Start  bc_verifyLoginUser == ",true);
		
		test.log(Status.INFO,"Start  bc_verifyLoginUseiiiiir");
		login.typeUsername(prm_username);
		System.out.println(prm_username);
		test.log(Status.INFO,"Type username value is : "+prm_username +" : in element : "+ PG_Login.ele_userName);
		System.out.println(prm_username);
		
	//	Reporter.log("==End  bc_verifyLoginUser == ",true);
	}
	public static HashMap<String, String> bc_ReadFromDB1() throws SQLException {
		Connection con =DriverManager.getConnection ("jdbc:mysql://localhost:3306/student1","root","");
		Statement stm=con.createStatement();
		String s3="SELECT `username1`, `password` FROM `student1` WHERE `id`=1";
		ResultSet rs=stm.executeQuery(s3);
		HashMap<String, String> data = new HashMap<String, String>();
		while(rs.next()) {
			data.put("username", rs.getString("username1"));
			data.put("password",rs.getString("password"));
//			String prm_username=rs.getString("username1");
//			String prm_password=rs.getString("password");
//			System.out.println(prm_username);
//			System.out.println(prm_password);
//			String prm_output=prm_username+prm_password;
//			System.out.println(prm_output);
//			return prm_output;
		}
		return data;
		
	}
	
	public static void bc_navigationTo() {
		
		navigation.click_Recuitment();
		navigation.click_Candidates();	
	}

public static void bc_verifyJobcount(int inputValue) {
	candidate.getObjectCount(inputValue);
	}
public static void bc_selecAnotherJob(String prm_job1) {
	
	candidate.getObjectCountValue(prm_job1);
	}
public static void bc_searchFromJobAndManager(String prm_job,String prm_manager) {
	
	candidate.selectJobTitle(prm_job);
	candidate.selectHiringManager(prm_manager);
	candidate.clickSearch();
	}
public static void bc_scrolldown() throws InterruptedException {
	candidate.scrollToBottomPG();
	}
public static void bc_verifyResultcount(int inputValue) {
	candidate.getObjectCountInResult(inputValue);
	}
}
