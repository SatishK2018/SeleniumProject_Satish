/*
 * @ Test Case Description : To verify whether application allows admin to grant loan to selected member
 * @ Test Case ID: CYTC_018
 * @ Author : Satish Kale
 * */

package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.training.pom.AdminHomePagePOM;
import com.training.pom.BaseClass;
import com.training.pom.GrantLoanPagePOM;

public class TestCaseID_CYTC_018 {

	private WebDriver driver;
	private Properties properties;
	private AdminHomePagePOM adminHomePgPOM;
	private GrantLoanPagePOM grantLoanPgPOM;
	private WebDriverWait wait;
	private String actualResult, expectedResult;
		
	/*********
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	*********/

	@BeforeMethod
	public void setUp() throws InterruptedException, IOException {
		this.driver = BaseClass.driver;
		this.properties = BaseClass.properties;
		adminHomePgPOM = new AdminHomePagePOM(driver);
		grantLoanPgPOM = new GrantLoanPagePOM(driver);
		wait = new WebDriverWait(driver,10);
				
		/**********
		baseUrl = properties.getProperty("baseURL");
		adminUser = properties.getProperty("admin_User");
		adminPassword = properties.getProperty("admin_Password");
		// open the browser 
		driver.get(baseUrl);
		Thread.sleep(2000);
		//Pre-Condition
		adminHomePgPOM.adminLogin("admin","1234");
		***********/
	}
	
	@Test
	public void testCase_ID_CYTC_018() throws InterruptedException {
		
		//Step 1: Enter valid credentials in Member login textbox
		adminHomePgPOM.clickHomeLink();
		adminHomePgPOM.memberLogin("manzoor");
		Thread.sleep(1000);
		adminHomePgPOM.pressEnterKey();
		actualResult = adminHomePgPOM.getPageHeader().substring(0, 11);
		expectedResult = properties.getProperty("MemberLoginPgHeader");
		assertEquals(actualResult.concat("manzoor"),expectedResult);
		
		//Step 2: Click on Submit button of Payment system to member
		adminHomePgPOM.clickSubmitGrantLoanBtn();
		actualResult = adminHomePgPOM.getPageHeader();
		expectedResult = properties.getProperty("GrantLoanPgHeader");
		assertEquals(actualResult,expectedResult);
		
		//Step 3: Enter valid credentials in amount text box
		grantLoanPgPOM.enterAmount("100000");
		actualResult = grantLoanPgPOM.getEnteredAmount();
		expectedResult = properties.getProperty("GrantLoanPg_Amount");
		assertEquals(actualResult,expectedResult);
		
		//Step 4: Enter valid credentials in Description textbox
		grantLoanPgPOM.enterDescription("home loan");	
		actualResult = grantLoanPgPOM.getEnteredDescription();
		expectedResult = properties.getProperty("GrantLoanPg_Description");
		assertEquals(actualResult,expectedResult);
		
		//Step 5: Click on Submit button
		grantLoanPgPOM.clickSubmitBtn();	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@type='submit']")));
		actualResult = grantLoanPgPOM.getLoanConfrmMessge();
		expectedResult = properties.getProperty("GrantLoanPg_LoanConfMesg");
		assertEquals(actualResult,expectedResult);
		
		//Step 6: Click on Submit button
		grantLoanPgPOM.clickSubmitLoanBtn();
				
		//Step 7: Click on OK button
		wait.until(ExpectedConditions.alertIsPresent());
		actualResult = grantLoanPgPOM.getAlertMessg();
		grantLoanPgPOM.clickOkInAlert();
		expectedResult = properties.getProperty("GrantLoanPg_SuccessMesg");
		assertEquals(actualResult,expectedResult);
		
		//Verify Member details page is displayed after accepting alert
		actualResult = adminHomePgPOM.getPageHeader().substring(0, 11);
		String expectedResult = properties.getProperty("MemberLoginPgHeader");
		assertEquals(actualResult.concat("manzoor"),expectedResult);
		
	}
	
}

