/*
 * @ Test Case Description : To verify whether application allows admin to add contact & make payment
 * @ Test Case ID: CYTC_050
 * @ Author : Satish Kale
 * */

package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.training.pom.BaseClass;
import com.training.pom.ContactsPagePOM;
import com.training.pom.MemberHomePagePOM;
import com.training.pom.PaymentSysToMemPagePOM;

public class TestCaseID_CYTC_050 {

	private WebDriver driver;
	private MemberHomePagePOM memberHomePgPOM;
	private ContactsPagePOM contactPgPOM;
	private PaymentSysToMemPagePOM paysysToMempgPOM;
	private static Properties properties;
	private String actualResult, expectedResult;
	private String memberUser, memberPassword;
	
	/*****
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	*****/

	@BeforeMethod
	public void setUp() throws InterruptedException, IOException {
		this.driver = BaseClass.driver;
		this.properties = BaseClass.properties;
		memberHomePgPOM = new MemberHomePagePOM(driver);
		paysysToMempgPOM = new PaymentSysToMemPagePOM(driver);
		contactPgPOM = new ContactsPagePOM(driver);
		memberUser = properties.getProperty("member_User1");
		memberPassword = properties.getProperty("member_Password1");
		
		/*****
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		baseUrl = properties.getProperty("baseURL");
		// open the browser
		driver.get(baseUrl);
		Thread.sleep(1000);
		// Pre-Condition
		memberHomePgPOM.memberLogin(memberUser, memberPassword);
		*****/
	}

	@Test
	public void testCase_ID_CYTC_050() throws InterruptedException {
		
		// Login to the application
		memberHomePgPOM.memberLogin(memberUser, memberPassword);
		
		// Step 1: Click on Personal Tab
		memberHomePgPOM.clickPersonalTab();
		// Verify that the 'Profile' sub menu is displayed
		actualResult = memberHomePgPOM.getSubMenu("Profile");
		expectedResult = properties.getProperty("PersonalMenu_SubMenu1");
		assertEquals(actualResult, expectedResult);
		// Verify that the 'Messages' sub menu is displayed
		actualResult = memberHomePgPOM.getSubMenu("Messages");
		expectedResult = properties.getProperty("PersonalMenu_SubMenu2");
		assertEquals(actualResult, expectedResult);
		// Verify that the 'Contacts' sub menu is displayed
		actualResult = memberHomePgPOM.getSubMenu("Contacts");
		expectedResult = properties.getProperty("PersonalMenu_SubMenu3");
		assertEquals(actualResult, expectedResult);

		// Step 2: Click on Contacts link
		memberHomePgPOM.clickContactsLink();
		actualResult = contactPgPOM.getPageHeader1();
		expectedResult = properties.getProperty("Contacts_PageHeader");
		assertEquals(actualResult, expectedResult);

		// Step 3: Enter valid credential in Member Login text box
		contactPgPOM.enterMemberLogin("selenium");
		contactPgPOM.pressEnterKey();
		actualResult = contactPgPOM.getMemberLogin();
		expectedResult = properties.getProperty("Contacts_MemberLogin");
		assertEquals(actualResult, expectedResult);
		
		// Step 4: Click on Submit button
		contactPgPOM.clickSubmitBtn();
		contactPgPOM.acceptAlert();
		actualResult = contactPgPOM.getAddedContact("selenium");
		expectedResult = properties.getProperty("Contacts_AddedMember");
		assertEquals(actualResult, expectedResult);
		
		// Step 5: Click on added contact
		contactPgPOM.clickOnAddedContact();
		actualResult = contactPgPOM.getPageHeader2();
		expectedResult = properties.getProperty("Contacts_ProfileHeader");
		assertEquals(actualResult, expectedResult);
		
		// Step 6: Click on Submit button of Make Payment section
		contactPgPOM.clickMakePaymentSubmitBtn();
		actualResult = contactPgPOM.getPageHeader3();
		expectedResult = properties.getProperty("PaymentToMember_Header");
		assertEquals(actualResult, expectedResult);
		
		// Step 7: Enter valid credentials in Amount text box
		paysysToMempgPOM.enterAmount("500");
		actualResult = paysysToMempgPOM.getAmount();
		expectedResult = properties.getProperty("PaySysMemPg_Amount");
		assertEquals(actualResult,expectedResult);
		
		//Step 8: Enter valid credentials in Description text box
		paysysToMempgPOM.enterDescription("welcome");
		String actualResult = paysysToMempgPOM.getEnteredDescription();
		String expectedResult = properties.getProperty("PaySysMemPg_Description1");
		assertEquals(actualResult,expectedResult);
		
		// Step 9: Click on Submit button
		paysysToMempgPOM.clickSubmitBtn();
		actualResult = paysysToMempgPOM.getTransConfrmMessg();
		expectedResult = properties.getProperty("PaySysMemPg_TransConfMesg");
		assertEquals(actualResult,expectedResult);
		
		// Step 10: Click on Submit button
		paysysToMempgPOM.clickSubmitTransBtn();
		actualResult = paysysToMempgPOM.getSuccessMessg();
		expectedResult = properties.getProperty("PaySysMemPg_SuccessMesg");
		assertEquals(actualResult,expectedResult);
		
		//Logout
		memberHomePgPOM.clickLogout();
		memberHomePgPOM.clickOKInPopup();
	}
	
}
