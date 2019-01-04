/*
 * @ Test Case Description : To verify whether application allows member to view scheduled payments
 * @ Test Case ID: CYTC_047
 * @ Author : Satish Kale
 * */

package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.training.pom.BaseClass;
import com.training.pom.MemberHomePagePOM;
import com.training.pom.MemberPaymentPagePOM;

public class TestCaseID_CYTC_047 {

	private WebDriver driver;
	private Properties properties;
	private MemberHomePagePOM memberHomePgPOM;
	private MemberPaymentPagePOM memberPayPgPOM;
	private String actualResult, expectedResult;
	private String memberUser, memberPassword;

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
		memberHomePgPOM = new MemberHomePagePOM(driver);
		memberPayPgPOM = new MemberPaymentPagePOM(driver);
		memberUser = properties.getProperty("member_User1");
		memberPassword = properties.getProperty("member_Password1");
		
		/******
		baseUrl = properties.getProperty("baseURL");
		memberUser = properties.getProperty("member_User1");
		memberPassword = properties.getProperty("member_Password1");
		// open the browser
		driver.get(baseUrl);
		Thread.sleep(1000);
		// Pre-Condition
		memberHomePgPOM.memberLogin(memberUser, memberPassword);
		******/
	}

	@Test
	public void testCase_ID_CYTC_047() throws InterruptedException {

		//Login to Member
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
		
		//Step 2: Click on Member Payment
		memberHomePgPOM.clickMemberPaymentMenu();
		actualResult = memberHomePgPOM.getPageHeader();
		expectedResult = properties.getProperty("MemberPayment_PageHeader");
		assertEquals(actualResult, expectedResult);
		
		//Step 3: Enter valid credentials in Login text box
		memberPayPgPOM.enterNameInLoginTxtBx("Selenium");
		Thread.sleep(1000);
		memberPayPgPOM.pressEnterKey();
		actualResult = memberPayPgPOM.getEnteredName();
		expectedResult = properties.getProperty("MemberPayment_Login");
		assertEquals(actualResult, expectedResult);
		
		//Step 4: Enter valid credentials in Amount text box
		memberPayPgPOM.enterAmount("5000");
		actualResult = memberPayPgPOM.getEnteredAmount();
		expectedResult = properties.getProperty("MemberPayment_Amount");
		assertEquals(actualResult, expectedResult);
		
		//Step 5: Select valid credentials in Scheduling list box
		memberPayPgPOM.selectSchedulingType("Scheduled for future date");
		actualResult = memberPayPgPOM.getSelectedScheduleType();
		expectedResult = properties.getProperty("MemberPayment_SchedulingType");
		assertEquals(actualResult, expectedResult);
		
		//Step 6: select date in Scheduled date for
		//memberPayPgPOM.selectScheduledDate("27/01/2019");
		memberPayPgPOM.selectScheduledDate();
				
		//Step 7: Enter valid credentials in Description text box
		memberPayPgPOM.enterDescription("welcome");
		actualResult = memberPayPgPOM.getDescription();
		expectedResult = properties.getProperty("MemberPayment_Description");
		assertEquals(actualResult, expectedResult);
		
		//Step 8: Click on Submit button
		memberPayPgPOM.clickSubmitBtn1();
		actualResult = memberPayPgPOM.getConfMessg();
		expectedResult = properties.getProperty("MemberPayment_Message");
		assertEquals(actualResult, expectedResult);
		
		//Step 9: Click on Submit button
		memberPayPgPOM.clickSubmitBtn2();
		actualResult = memberPayPgPOM.getPageHeader();
		expectedResult = properties.getProperty("MemberPayment_PageHeader1");
		assertEquals(actualResult, expectedResult);
		
		//Step 10: Click on Scheduled Payments link
		memberHomePgPOM.clickScheduledPaymentsMenu();
		actualResult = memberHomePgPOM.getPageHeader();
		expectedResult = properties.getProperty("MemberPayment_PageHeader2");
		assertEquals(actualResult, expectedResult);
		
		//Logout
		memberHomePgPOM.clickLogout();
		memberHomePgPOM.clickOKInPopup();
	}
	
}
