package com.home.pages;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.home.utility.TestBase;


public class PG_Candidate{

	WebDriver driver;
	public PG_Candidate(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//select[@id='candidateSearch_jobTitle']")WebElement dd_jobTitle;
	@FindBy(xpath="//select[@id='candidateSearch_jobTitle']/option")List<WebElement> dd_jobTitleCount;
	@FindBy(xpath="//select[@id='candidateSearch_hiringManager']")WebElement dd_hiringManager;
	@FindBy(xpath="//input[@id='btnSrch']")WebElement btn_search;
	@FindBy(xpath="//table[@id='resultTable']/tbody/tr")List<WebElement> dd_ResultAfterSearch;
	
	
	public void selectJobTitle(String byText) {
		TestBase.selectByTextValue(dd_jobTitle, byText);
	}
	public void getObjectCount(Integer inputValue) {
		Integer x=dd_jobTitleCount.size();
		if (x.equals(inputValue)) {
			System.out.println("count is equal actual with "+ x + " expect "+inputValue);
		}
		else {
			System.out.println("count is not equal actual with "+ x + " expect "+inputValue);
			
		}
	}
	public void getObjectCountValue(String prm_Value) {
		Select ddJob =new Select(dd_jobTitle);
		List<WebElement>alloption=ddJob.getOptions();
		for (WebElement option :alloption)
		{
			System.out.println("dropdown value is" +option.getText());
		
		if(option.getText().equals(prm_Value)) {
			option.click();
			break;
		}
		}
	}
	public void selectHiringManager(String byText) {
		TestBase.selectByTextValue(dd_hiringManager, byText);
	}
	public void clickSearch() {
		TestBase.click(btn_search);
	}
	public void scrollToBottomPG() throws InterruptedException {
		TestBase.scrollDown();
	}	
	public void getObjectCountInResult(Integer inputValue) {
		Integer x=dd_ResultAfterSearch.size();
		if (x.equals(inputValue)) {
			System.out.println("count is equal actual with "+ x + " expect "+inputValue);
		}
		else {
			System.out.println("count is not equal actual with "+ x + " expect "+inputValue);
			
		}
	}
		
}
