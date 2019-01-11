/*
 * @ Test Case Description : To verify whether application allows admin to grant loan to multiple member
 * @ Test Case ID: CYTC_076
 * @ Author : Satish Kale
 * */

package com.training.regression.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.dataproviders.GrantLoanDataProvider;
import com.training.pom.AdminHomePagePOM;
import com.training.pom.BaseClass;
import com.training.pom.GrantLoanPagePOM;

public class TestCaseID_CYTC_076 {

	private WebDriver driver;
	private Properties properties;
	private AdminHomePagePOM adminHomePgPOM;
	private GrantLoanPagePOM grantLoanPgPOM;
	private WebDriverWait wait;
	private String actualResult, expectedResult;
	//private String baseUrl,adminUser,adminPassword;
		
	/******
	@BeforeTest
	public static void setUpBeforeClass() throws IOException {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	******/
	

	@BeforeClass
	public void setUp() throws InterruptedException, IOException {
		this.driver = BaseClass.driver;
		this.properties = BaseClass.properties;
		adminHomePgPOM = new AdminHomePagePOM(driver);
		grantLoanPgPOM = new GrantLoanPagePOM(driver);
		wait = new WebDriverWait(driver,20);
		
		/******
		baseUrl = properties.getProperty("baseURL");
		adminUser = properties.getProperty("admin_User");
		adminPassword = properties.getProperty("admin_Password");
		// open the browser 
		driver.get(baseUrl);
		//Pre-Condition
		adminHomePgPOM.adminLogin(adminUser,adminPassword);
		******/
	}
	
	@Test (dataProvider = "GrantLoan_excel-inputs_TC_076", dataProviderClass = GrantLoanDataProvider.class)
	public void testCaseID_CYTC_076 (String memberLogin, String amount, String descp) throws InterruptedException {
		
		//Step 1: Enter valid credentials in Member login text box
		adminHomePgPOM.memberLogin(memberLogin);
		if(memberLogin.equals("manzoor")) {
		Thread.sleep(1000);
		adminHomePgPOM.pressEnterKey();
		}
		actualResult = adminHomePgPOM.getPageHeader();
		String fullName=grantLoanPgPOM.getMemberFullName();
		expectedResult = "Profile of "+fullName;
		//System.out.println(actualResult+"\n"+expectedResult);
		assertEquals(actualResult,expectedResult);
				
		//Step 2: Click on Submit button of Payment system to member
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("backButton")));
		adminHomePgPOM.clickSubmitGrantLoanBtn();
		actualResult = adminHomePgPOM.getPageHeader();
		expectedResult = "Grant loan to "+fullName;
		/*System.out.println(actualResult);
		System.out.println(expectedResult);*/
		assertEquals(actualResult,expectedResult);
		
		//Step 3: Enter valid credentials in amount text box
		grantLoanPgPOM.enterAmount(amount);
		actualResult = grantLoanPgPOM.getEnteredAmount();
		assertEquals(actualResult,amount);
		
		//Step 4: Enter valid credentials in Description textbox
		grantLoanPgPOM.enterDescription(descp);	
		actualResult = grantLoanPgPOM.getEnteredDescription();
		assertEquals(actualResult,descp);
		
		//Step 5: Click on Submit button
		grantLoanPgPOM.clickSubmitBtn();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@type='submit']")));
		actualResult = grantLoanPgPOM.getLoanConfrmMessge();
		expectedResult = properties.getProperty("GrantLoanPg_LoanConfMesg");
		assertEquals(actualResult,expectedResult);
		
		//Step 6: Click on Submit button
		grantLoanPgPOM.clickSubmitLoanBtn();
		wait.until(ExpectedConditions.alertIsPresent());
		actualResult = driver.switchTo().alert().getText();
		expectedResult = properties.getProperty("GrantLoanPg_SuccessMesg");
		assertEquals(actualResult,expectedResult);
			
		//Step 7: Click on OK button
		driver.switchTo().alert().accept();
		actualResult = adminHomePgPOM.getPageHeader();
		expectedResult = "Profile of "+fullName;
		//System.out.println(actualResult+"\n"+expectedResult);
		assertEquals(actualResult,expectedResult);
		
		adminHomePgPOM.clickHomeLink();
	}		
	
	@AfterClass
		public void adminLogout() {
			adminHomePgPOM.adminLogout();
	}	
	
}

