/*
 * @ Test Case Description : To Verify whether application allows admin to display Accounts details of a particular member based on the search criteria
 * @ Test Case ID: CYTC_016
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
import com.training.pom.AccountInfoPagePOM;
import com.training.pom.AdminHomePagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TestCaseID_CYTC_016 {

	private WebDriver driver;
	private String baseUrl;
	private AdminHomePagePOM adminHomePgPOM;
	private AccountInfoPagePOM acctInfoPgPOM;
	private static Properties properties;
	String adminUser, adminPassword;
	private String actualResult, expectedResult;
	
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
		acctInfoPgPOM = new AccountInfoPagePOM(driver);
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
	public void testCase_ID_CYTC_016() throws InterruptedException {
		
		//Step 1: Enter valid credentials in Member login textbox
		adminHomePgPOM.memberLogin("manzoor");
		actualResult = adminHomePgPOM.getPageHeader().substring(0, 11);
		expectedResult = properties.getProperty("MemberLoginPgHeader");
		assertEquals(actualResult.concat("manzoor"),expectedResult);
		
		//Step 2: Click on Submit button of Account information
		adminHomePgPOM.clickSubmitInformationBtn();
		actualResult = adminHomePgPOM.getPageHeader();
		expectedResult = properties.getProperty("AcctInfoPgHeader");
		assertEquals(actualResult,expectedResult);
		
		//Step 3: Click on Payment type list box
		acctInfoPgPOM.clickPaymentTypeListBox();
		
		//Step 4: Select Valid credentials from Payment type list box
		acctInfoPgPOM.selectPaymentType("Commission payments");
		actualResult = acctInfoPgPOM.getSelectedPaymentType();
		expectedResult = properties.getProperty("AcctInfopg_PaymentType");
		assertEquals(actualResult,expectedResult);
		
		//Step 5: Click on Search button
		acctInfoPgPOM.clickSearchBtn();
		
		adminHomePgPOM.adminLogout();
		
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
}

