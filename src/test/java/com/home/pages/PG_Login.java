package com.home.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.home.utility.TestBase;

public class PG_Login {

	WebDriver driver;
	//ExtentTest test;
	public PG_Login(WebDriver driver) {
		this.driver=driver;
			PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@name='username']")
	public static
	 WebElement ele_userName;
	@FindBy(xpath="//input[@name='password']")
	 static WebElement ele_password;
	@FindBy(xpath="//form[@class='oxd-form']/child::div/button")
	public static WebElement btn_login;
	
	@FindBy(xpath="//a[@id='welcome']")WebElement ele_Welcome;
	
	
	public void typeUsername(String prm_username) {
		TestBase.type(ele_userName, prm_username);
	//	test.log(Status.INFO,"Type username value in PG_Login: "+prm_username +" : in element : "+PG_Login.ele_userName);
	}
	public void typePassword2(String prm_passsword) {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("document.getElementById('txtPassword').click();");
		TestBase.type(ele_password, prm_passsword);
	//	test.log(Status.INFO,"Type username value in PG_Login: "+prm_username +" : in element : "+PG_Login.ele_userName);
	}
	public void typePassword(String prm_passsword) {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("document.getElementById('txtPassword').click();");
		TestBase.type(getEle_password(), prm_passsword);
	}
	public void clickSignIn() {
		TestBase.click(btn_login);
	}
	public void checkWelcomeandGetText(String prm_input_Value) {
		TestBase.checkAndGetText(ele_Welcome);
		String outputValInSystem=ele_Welcome.getText();
		String[] output =outputValInSystem.split(" ");
		if (prm_input_Value.contains(output[0])) {
			System.out.println(output[0]);
		}
		Assert.assertEquals(output[0], prm_input_Value);
		
	}
	public static WebElement getEle_password() {
		return ele_password;
	}
	public void setEle_password(WebElement ele_password) {
		this.ele_password = ele_password;
	}
}
