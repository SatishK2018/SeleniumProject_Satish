/*
 * @ Test Case Description : To verify whether application allows member to filter transaction details
 * @ Test Case ID: CYTC_046
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
import com.training.pom.BaseClass;
import com.training.pom.MemberHomePagePOM;

public class TestCaseID_CYTC_046 {

	private WebDriver driver;
	private Properties properties;
	private MemberHomePagePOM memberHomePgPOM;
	private AccountInfoPagePOM acctInfoPgPOM;
	private String actualResult, expectedResult;
	private String memberUser, memberPassword;

	/**********
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	***********/

	@BeforeMethod
	public void setUp() throws InterruptedException, IOException {
		this.driver=BaseClass.driver;
		this.properties=BaseClass.properties;
		memberHomePgPOM = new MemberHomePagePOM(driver);
		acctInfoPgPOM = new AccountInfoPagePOM(driver);
		memberUser = properties.getProperty("member_User2");
		memberPassword = properties.getProperty("member_Password2");
		
		/******
		baseUrl = properties.getProperty("baseURL");
		// open the browser
		driver.get(baseUrl);
		Thread.sleep(2000);
		// Pre-Condition
		******/
	}

	@Test 
	public void testCase_ID_CYTC_046() throws InterruptedException {
		
		// Login as a Member to the application
		memberHomePgPOM.memberLogin(memberUser, memberPassword);
		
		// Step 1: Click on Account tab
		memberHomePgPOM.clickAccountTab();
		// Verify that the 'Account Information' sub menu is displayed
		actualResult = memberHomePgPOM.getSubMenu("Account Information");
		expectedResult = properties.getProperty("AccountMenu_SubMenu1");
		assertEquals(actualResult, expectedResult);
		// Verify that the 'Scheduled payments' sub menu is displayed
		actualResult = memberHomePgPOM.getSubMenu("Scheduled payments");
		expectedResult = properties.getProperty("AccountMenu_SubMenu2");
		assertEquals(actualResult, expectedResult);
		// Verify that the 'Invoices' sub menu is displayed
		actualResult = memberHomePgPOM.getSubMenu("Invoices");
		expectedResult = properties.getProperty("AccountMenu_SubMenu3");
		assertEquals(actualResult, expectedResult);
		
		// Step 2: Click on Account Information link
		memberHomePgPOM.clickAccountInformationMenu();
		actualResult = memberHomePgPOM.getPageHeader();
		expectedResult = properties.getProperty("AcctInfo_PageHeader");
		assertEquals(actualResult, expectedResult);

		// Step 3: Click on Advanced button
		memberHomePgPOM.clickAdvancedBtn();
		actualResult = memberHomePgPOM.getPageHeader();
		expectedResult = properties.getProperty("AcctInfo_PageHeader");
		assertEquals(actualResult, expectedResult);
		
		// Step 4: Enter valid credentials in Member login text box
		acctInfoPgPOM.enterMemberLogin("Satish1");
		acctInfoPgPOM.pressEnterKey();
		actualResult = acctInfoPgPOM.getEnteredMemberLogin();
		expectedResult = properties.getProperty("MemberLoginName");
		assertEquals(actualResult, expectedResult);

		// Step 5: Click on Search button
		acctInfoPgPOM.clickSearchBtn();
		actualResult = acctInfoPgPOM.getSearchResult("Satish1");
		expectedResult = properties.getProperty("MemberLoginName");
		assertEquals(actualResult, expectedResult);

		// Step 6: Select from date
		acctInfoPgPOM.clearMemberLoginAndNameTxtBx();
		acctInfoPgPOM.selectFromDate();
		  
		// Step 7: Select to date 
		acctInfoPgPOM.selectToDate();
				  
		// Step 8: Click on Search button 
		acctInfoPgPOM.clickSearchBtn();
		
		// Step 9: Select Payment type as Loan Payment
		acctInfoPgPOM.clearFromDateAndToDateTxtBx();
		acctInfoPgPOM.clearMemberLoginAndNameTxtBx();
		acctInfoPgPOM.selectPaymentType("Loan payments");
		actualResult = acctInfoPgPOM.getSelectedPaymentType();
		expectedResult = properties.getProperty("PaymentType");
		assertEquals(actualResult, expectedResult);

		// Step 10: Click on Search button
		acctInfoPgPOM.clickSearchBtn();
		actualResult = acctInfoPgPOM.getSearchResultLoanRepayments();
		expectedResult = properties.getProperty("SearchResultLoanRepayments");
		assertEquals(actualResult, expectedResult);
		
		//Logout
		memberHomePgPOM.clickLogout();
		memberHomePgPOM.clickOKInPopup();
			
	}

}
