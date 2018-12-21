/*
 * @ Test Case Description : To verify whether application allows admin to display the loans granted details for member based on the filtered criteria should get displayed
 * @ Test Case ID: CYTC_019
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
import com.training.pom.ViewLoanPagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TestCaseID_CYTC_019 {

	private WebDriver driver;
	private String baseUrl;
	private AdminHomePagePOM adminHomePgPOM;
	private ViewLoanPagePOM viewLoanPgPOM;
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
		viewLoanPgPOM = new ViewLoanPagePOM(driver);
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
	public void testCase_ID_CYTC_019() throws InterruptedException {
		
		//Step 1: Enter valid credentials in Member login textbox
		adminHomePgPOM.memberLogin("manzoor");
		actualResult = adminHomePgPOM.getPageHeader().substring(0, 11);
		expectedResult = properties.getProperty("MemberLoginPgHeader");
		assertEquals(actualResult.concat("manzoor"),expectedResult);
		
		//Step 2: Click on Submit button of View loans 
		adminHomePgPOM.clickSubmitViewLoanBtn();
		actualResult = adminHomePgPOM.getPageHeader();
		expectedResult = properties.getProperty("ViewLoanPgHeader");
		assertEquals(actualResult,expectedResult);
		
		//Step 3: Click on Closed radio button
		viewLoanPgPOM.isOpenRadioBtnSelected();
		viewLoanPgPOM.clickClosedRadioBtn();
		
		//Step 4: Click on Open radio button
		viewLoanPgPOM.clickOpenRadioBtn();
		
		adminHomePgPOM.adminLogout();
		
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
}

