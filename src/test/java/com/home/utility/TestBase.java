
package com.home.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.read.biff.BiffException;

public class TestBase {

	
	public static  WebDriver driver;
	public static String userDir = System.getProperty("user.dir");
	public static int retry;
	public  ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Connection con1;
	
	
	@BeforeClass
	public void startTestReport() {
		htmlReporter=new ExtentHtmlReporter(userDir +"/test-output/myReportResult.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("TestName", "Dilshani");
		extent.setSystemInfo("Browser", "Chrome");
	}
	
	public static String getDataFromPropertyFile(String key) throws Exception {
		FileReader reader = new FileReader(userDir + "//runtime.properties");
		Properties p = new Properties();
		p.load(reader);
		return p.getProperty(key).toString();
	}

	@BeforeTest (alwaysRun = true)
	public void settupApplication() throws Exception {
		String browser = getDataFromPropertyFile("browser");
		if (browser.contains("chrome")) {
			// System.setProperty("webdriver.chrome.driver","D:\\EclipseWorkspace\\Ebay\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			 ChromeOptions option = new ChromeOptions();
	            option.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(option);
			 
		} else if (browser.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(getDataFromPropertyFile("URL"));
	//	JavascriptExecutor js = (JavascriptExecutor) driver;
	//	String domainName=  js.executeScript("return document.domain;").toString();
     //   System.out.println("Domain is: "+domainName);
		//waitTillPageLoad();
	}
	@AfterMethod
public static void tearDown(ITestResult result)throws IOException{
	if(result.getStatus()==ITestResult.FAILURE) {
		test.log(Status.FAIL,"TEST CASE FAILED IS " + result.getName());
		test.log(Status.FAIL,"TEST CASE FAILED IS " + result.getThrowable());
		String screenshotPath = TestBase.getScreenshot(driver, result.getName());
		
		test.addScreenCaptureFromPath(screenshotPath);
	}
	else if (result.getStatus()==ITestResult.SKIP) {
		test.log(Status.SKIP,"TEST CASE SKIP IS " + result.getName());
}else if (result.getStatus()==ITestResult.SUCCESS) {
	test.log(Status.PASS,"TEST CASE SUCCESS IS " + result.getName());
	String screenshotPath = TestBase.getScreenshot(driver, result.getName());
	
	test.addScreenCaptureFromPath(screenshotPath);
}
}
public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot ts=(TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	
String destination= System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";	
	File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
	return destination;
}
@AfterSuite
	public static void quiteBrowser() {
	SessionId s = ((RemoteWebDriver) driver).getSessionId();
	System.out.println("Session Id is : " + s);
	   if(driver != null) {
		driver.close();
		driver.quit();
		System.out.println("Session id  closed 123");
		  extent.flush();
		  System.out.println("Session id closed 1234");
	   }else {
		   driver.quit();
	   }
	}/*
public static void closeBrowser() {
	  
		driver.close();
		
		 
	   
	}*/
public String[][] getExcelData(String fileName, String sheetName) {
	String[][] arrayExcelData = null;
	try {
		FileInputStream fs = new FileInputStream(fileName);
		jxl.Workbook wb = jxl.Workbook.getWorkbook(fs);
		jxl.Sheet sh = wb.getSheet(sheetName);

		int totalNoOfCols = sh.getColumns();
		int totalNoOfRows = sh.getRows();
		
		arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
		
		for (int i= 1 ; i < totalNoOfRows; i++) {

			for (int j=0; j < totalNoOfCols; j++) {
				arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
			}

		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
		e.printStackTrace();
	} catch (BiffException e) {
		e.printStackTrace();
	}
	return arrayExcelData;
}
	public static void waitTillPageLoad() {
		try {
			retry = Integer.parseInt(getDataFromPropertyFile("retry"));
			JavascriptExecutor j = (JavascriptExecutor) driver;
			int i = 0;
			Thread.sleep(1000);
			while (i < retry) {
				//if (j.executeScript("return.document.readyState;").toString().equals("complete")) {
				if (j.executeScript("return.document.readyState").toString().equals("complete")){
					System.out.println("page has loaded");
					break;
				} else {
					Thread.sleep(1000);
					i++;
				}
			}
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public void getConnectionDB() throws SQLException {
		con1 =DriverManager.getConnection ("jdbc:mysql://localhost:3306/student1","root","");
	}
public static void click(WebElement element) {
	  //  waitTillPageLoad() ;
	//WebDriverWait wait = new WebDriverWait(TestBase.driver, Duration.ofSeconds(3));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	public static void type(WebElement element, String valueToType1) {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(element));
		//String valueToType =valueToType1;
      element.clear();
	element.sendKeys(valueToType1);
	}
	public void checkElementPresent(WebElement element) {
		element.isDisplayed();
		//WebDriverWait wait = new WebDriverWait(TestBase.driver, Duration.ofSeconds(3));
		//wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}
	public static void checkAndGetText(WebElement element) {
		element.isDisplayed();
		
	}
	public static void selectByTextValue(WebElement element,String text) {
		Select select =new Select(element);
		select.selectByVisibleText(text);
		
	}
	public static  void scrollDown() throws InterruptedException {
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("scrollBy(0,2000)");
		Thread.sleep(1000);
	}

}
