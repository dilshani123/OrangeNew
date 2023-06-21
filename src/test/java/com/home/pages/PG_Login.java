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
	public PG_Login(WebDriver driver) {
		this.driver=driver;
			PageFactory.initElements(driver, this);
		//	PageFactory.initElements(driver, page);
	}
	@FindBy(xpath="//input[@name='username']")public static WebElement ele_userName;
	@FindBy(xpath="//input[@name='password']") static WebElement ele_password;
	@FindBy(xpath="//input[@name='password']") private static WebElement ele_password1; // private elements 
	@FindBy(xpath="//form[@class='oxd-form']/child::div/button")public static WebElement btn_login;
	@FindBy(xpath="//p[@class='oxd-userdropdown-name']")WebElement ele_Welcome;
	
	public void typeUsername(String prm_username) {
		TestBase.type(ele_userName, prm_username);
		WebElement element = driver.findElement(By.name("username"));
		System.out.println("Value is: " + element.getAttribute("name"));
	}
	public void clickSignIn() {
		TestBase.click(btn_login);
	}
/*	public void typePassword2(String prm_passsword) {
		WebElement element = driver.findElement(By.name("password"));
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("arguments[0].click();", element);
		TestBase.type(ele_password, prm_passsword);
	
	}
		*/
	public void checkWelcomeandGetText(String prm_input_Value) {
		TestBase.checkAndGetText(ele_Welcome);
		String outputValInSystem=ele_Welcome.getText();
		String[] output =outputValInSystem.split(" ");
		if (prm_input_Value.contains(output[0])) {
			System.out.println(output[0]);
		}
		Assert.assertEquals(output[0], prm_input_Value);
		TestBase.test.log(Status.INFO,"checkWelcome and GetText1122 : "  +" Actual param "+ output[0]+" Expected Result "+ prm_input_Value);	
	}
	public static  WebElement getEle_password() {
		return ele_password1;
	}
	public void setEle_password(String prm_passsword11) { // methods from getters and setters 

		//this.ele_password1 = prm_passsword11;
		TestBase.type(ele_password1, prm_passsword11);
	}
}
