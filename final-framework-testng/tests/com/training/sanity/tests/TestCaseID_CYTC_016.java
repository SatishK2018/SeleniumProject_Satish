package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.training.pom.AdminHomePagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TestCaseID_CYTC_016 {

	private WebDriver driver;
	private String baseUrl;
	private AdminHomePagePOM adminHomePgPOM;
	private static Properties properties;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws InterruptedException, IOException {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		adminHomePgPOM = new AdminHomePagePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		// open the browser 
		driver.get(baseUrl);
		Thread.sleep(2000);
		//Pre-Condition
		adminHomePgPOM.adminLogin("admin","1234");
		Thread.sleep(1000);
	}
	
	@Test
	public void testCase_ID_CYTC_016() throws InterruptedException {
		
		//Step 1: Enter valid credentials in Member login textbox
		adminHomePgPOM.memberLogin("manzoor");
		Thread.sleep(1000);
		//Step 2: Click on Submit button of Account information
		adminHomePgPOM.clickSubmitInformationBtn();
		Thread.sleep(1000);
		adminHomePgPOM.selectPaymentType("Commission payments");
		Thread.sleep(1000);
		adminHomePgPOM.clickSearchBtn();
		Thread.sleep(1000);
		adminHomePgPOM.adminLogout();
	}
	
/*	@Test (priority = 2)
	public void openBrowser() throws InterruptedException {
		baseUrl = properties.getProperty("baseURL");
		// open the browser 
		driver.get(baseUrl);
		Thread.sleep(2000);
	}
	
	@Test (priority = 3)
	public void adminLogin() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("1234");
		loginPOM.clickLoginBtn(); 
		String actualResult = loginPOM.getLoggedUser();
		String expectedResult = properties.getProperty("expectedResult_AdminLogin");
		assertEquals(actualResult.substring(0, 34),expectedResult);
		screenShot.captureScreenShot();
	}
	
	@Test (priority = 4)
	public void memberLogin() {
		adminHomePgPOM.memberLogin("manzoor");
		String actualResult = adminHomePgPOM.getLoggedMember().substring(0, 11);
		String expectedResult = properties.getProperty("expectedResult_MemberLogin");
		assertEquals(actualResult.concat("manzoor"),expectedResult);
		screenShot.captureScreenShot();
	}
	
	@Test (priority = 5)
	public void clickAcctInfoSubmitBtn() {
		adminHomePgPOM.clickSubmitInformationBtn();
		screenShot.captureScreenShot();		
	}
	
	@Test (priority = 6)
	public void selectPaymentType() {
		Select payment_Type_ListBox = new Select(driver.findElement(By.name("query(paymentFilter)")));
		payment_Type_ListBox.selectByVisibleText("Commission payments");
		String actualResult = payment_Type_ListBox.getFirstSelectedOption().getText();
		String expectedResult = properties.getProperty("expectedResult_PaymentType");
		assertEquals(actualResult,expectedResult);
		screenShot.captureScreenShot();
	}
	
	@Test (priority = 7)
	public void clickSearchBtn() {
		adminHomePgPOM.searchBtn();
		screenShot.captureScreenShot();
	}*/
	
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
}

