/*
 * @ Test Case Description : To Verify whether application allows admin to display Accounts details of a particular member based on the search criteria
 * @ Test Case ID: CYTC_016
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
import com.training.pom.AccountInfoPagePOM;
import com.training.pom.AdminHomePagePOM;
import com.training.pom.BaseClass;

public class TestCaseID_CYTC_016 {

	private WebDriver driver;
	private Properties properties;
	private AdminHomePagePOM adminHomePgPOM;
	private AccountInfoPagePOM acctInfoPgPOM;
	private WebDriverWait wait;
	private String actualResult, expectedResult;
		
	/**********
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	**********/

	@BeforeMethod
	public void setUp() throws InterruptedException, IOException {
		this.driver = BaseClass.driver;
		this.properties = BaseClass.properties;
		adminHomePgPOM = new AdminHomePagePOM(driver);
		acctInfoPgPOM = new AccountInfoPagePOM(driver);
		wait = new WebDriverWait(driver,10);
				
		/**********
		baseUrl = properties.getProperty("baseURL");
		adminUser = properties.getProperty("admin_User");
		adminPassword = properties.getProperty("admin_Password");
		// open the browser 
		driver.get(baseUrl);
		Thread.sleep(2000);
		//Pre-Condition
		adminHomePgPOM.adminLogin(adminUser,adminPassword);
		************/
	}
	
	@Test
	public void testCase_ID_CYTC_016() throws InterruptedException {
		
		//Step 1: Enter valid credentials in Member login textbox
		adminHomePgPOM.clickHomeLink();
		adminHomePgPOM.memberLogin("manzoor");
		wait.wait();
		adminHomePgPOM.pressEnterKey();
		actualResult = adminHomePgPOM.getPageHeader().substring(0, 11);
		expectedResult = properties.getProperty("MemberLoginPgHeader");
		assertEquals(actualResult.concat("manzoor"),expectedResult);
		
		//Step 2: Click on Submit button of Account information
		adminHomePgPOM.clickSubmitInformationBtn();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("memberUsername")));
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
		
	}
	
}

