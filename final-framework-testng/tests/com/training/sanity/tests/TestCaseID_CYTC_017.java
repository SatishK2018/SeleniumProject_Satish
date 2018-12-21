/*
 * @ Test Case Description : To Verify whether application allows admin to make the payment for member
 * @ Test Case ID: CYTC_017
 * @ Author : Satish Kale
 * */

package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.training.pom.AdminHomePagePOM;
import com.training.pom.PaymentSysToMemPagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TestCaseID_CYTC_017 {

	private WebDriver driver;
	private String baseUrl;
	private AdminHomePagePOM adminHomePgPOM;
	private PaymentSysToMemPagePOM paysysToMempgPOM;
	private static Properties properties;
	private String actualResult, expectedResult;
	String adminUser,adminPassword;
	
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
		paysysToMempgPOM = new PaymentSysToMemPagePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		adminUser = properties.getProperty("admin_User");
		adminPassword = properties.getProperty("admin_Password");
		// open the browser 
		driver.get(baseUrl);
		Thread.sleep(2000);
		//Pre-Condition
		adminHomePgPOM.adminLogin(adminUser,adminPassword);
	}
	
	@Test
	public void testCase_ID_CYTC_017() throws InterruptedException {
		
		//Step 1: Enter valid credentials in Member login textbox
		adminHomePgPOM.memberLogin("manzoor");
		actualResult = adminHomePgPOM.getPageHeader().substring(0, 11);
		expectedResult = properties.getProperty("MemberLoginPgHeader");
		assertEquals(actualResult.concat("manzoor"),expectedResult);
		
		//Step 2: Click on Submit button of Payment system to member
		adminHomePgPOM.clickSubmitPaySysToMemberBtn();
		actualResult = adminHomePgPOM.getPageHeader();
		expectedResult = properties.getProperty("PaySysMemPgHeader");
		assertEquals(actualResult,expectedResult);
		
		//Step 3: Enter valid credentials in Amount text box
		paysysToMempgPOM.enterAmount("500");
		actualResult = paysysToMempgPOM.getAmount();
		expectedResult = properties.getProperty("PaySysMemPg_Amount");
		assertEquals(actualResult,expectedResult);
		
		//Step 4: Select valid credentials from Transaction type list box
		paysysToMempgPOM.clickTransTypeLstBox();
		paysysToMempgPOM.selectTransType("Debit to member");
		actualResult = paysysToMempgPOM.getSelecedTransType();
		expectedResult = properties.getProperty("PaySysMemPg_TransType");
		assertEquals(actualResult,expectedResult);
		
		//Step 5: Enter valid credentials in Description text box
		paysysToMempgPOM.enterDescription("bonus");
		String actualResult = paysysToMempgPOM.getEnteredDescription();
		String expectedResult = properties.getProperty("PaySysMemPg_Description");
		assertEquals(actualResult,expectedResult);
		
		//Step 6: Click on Submit button
		paysysToMempgPOM.clickSubmitBtn();
		actualResult = paysysToMempgPOM.getTransConfrmMessg();
		expectedResult = properties.getProperty("PaySysMemPg_TransConfMesg");
		assertEquals(actualResult,expectedResult);
		
		//Step 7: Click on Submit button
		paysysToMempgPOM.clickSubmitTransBtn();
		actualResult = paysysToMempgPOM.getSuccessMessg();
		expectedResult = properties.getProperty("PaySysMemPg_SuccessMesg");
		assertEquals(actualResult,expectedResult);
		
		adminHomePgPOM.adminLogout();
		
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
}

