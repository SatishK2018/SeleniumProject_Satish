/*
 * @ Test Case Description : To verify whether application displays Account information of member
 * @ Test Case ID: CYTC_020
 * @ Author : Satish Kale
 * */

package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.training.pom.AccountInfoPagePOM;
import com.training.pom.AdminHomePagePOM;
import com.training.pom.BaseClass;

public class TestCaseID_CYTC_020 {

	private WebDriver driver;
	private Properties properties;
	private AdminHomePagePOM adminHomePgPOM;
	private AccountInfoPagePOM acctInfoPgPOM;
	private String actualResult, expectedResult;
		
	/******
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	******/

	@BeforeMethod
	public void setUp() throws InterruptedException, IOException {
		this.driver = BaseClass.driver;
		this.properties = BaseClass.properties;
		adminHomePgPOM = new AdminHomePagePOM(driver);
		acctInfoPgPOM = new AccountInfoPagePOM(driver);
				
		/*******
		baseUrl = properties.getProperty("baseURL");
		adminUser = properties.getProperty("admin_User");
		adminPassword = properties.getProperty("admin_Password");
		// open the browser 
		driver.get(baseUrl);
		Thread.sleep(2000);
		//Pre-Condition
		adminHomePgPOM.adminLogin(adminUser,adminPassword);
		*******/
	}
	
	@Test
	public void testCase_ID_CYTC_020() throws InterruptedException {
		
		adminHomePgPOM.clickHomeLink();		
		adminHomePgPOM.memberLogin("manzoor");
		Thread.sleep(1000);
		adminHomePgPOM.pressEnterKey();
		actualResult = adminHomePgPOM.getPageHeader().substring(0, 11);
		expectedResult = properties.getProperty("MemberLoginPgHeader");
		assertEquals(actualResult.concat("manzoor"),expectedResult);
		//Step 1: Click on Account link 
		//NOTE: Account > Account Information link is not available
		
		//Step 2: Click on Account Information link
		adminHomePgPOM.clickSubmitInformationBtn();
		actualResult = adminHomePgPOM.getPageHeader();
		expectedResult = properties.getProperty("AcctInfoPgHeader");
		assertEquals(actualResult,expectedResult);
		
		//Step 3: Click on View icon of Particular transaction
		acctInfoPgPOM.clickViewImgBtn();
		actualResult = acctInfoPgPOM.getPageHeader().trim();
		expectedResult = properties.getProperty("TransDetailsPgHeader");
		assertEquals(actualResult,expectedResult);
		
		//Step 4: Click on Back button
		acctInfoPgPOM.clickBackBtn_TransDetailsPg();
		actualResult = adminHomePgPOM.getPageHeader().trim();
		expectedResult = properties.getProperty("AcctInfoPgHeader");
		assertEquals(actualResult,expectedResult);
		
	}
	
}

