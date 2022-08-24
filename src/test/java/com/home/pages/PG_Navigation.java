package com.home.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PG_Navigation {
	WebDriver driver;
	
	public PG_Navigation(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
 @FindBy(xpath="//div[@id='mainMenu']/ul/child::li/a/b[contains(text(),'Recruitment')]") WebElement ele_Recuitment;
 @FindBy(xpath="//div[@id='mainMenu']/ul/child::li/ul/li/a[contains(text(),'Candidates')]") WebElement ele_Candidates;


public void click_Recuitment() {
	ele_Recuitment.click();
}
public void click_Candidates() {
	ele_Candidates.click();
}
}
