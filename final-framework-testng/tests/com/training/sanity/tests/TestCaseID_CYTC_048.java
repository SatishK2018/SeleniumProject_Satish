/*
 * @ Test Case Description : To verify whether application allows member to filter message details
 * @ Test Case ID: CYTC_048
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
import com.training.pom.MessagesPagePOM;

public class TestCaseID_CYTC_048 {

	private WebDriver driver;
	private Properties properties;
	private MemberHomePagePOM memberHomePgPOM;
	private MessagesPagePOM messgPgPOM;
	private String actualResult, expectedResult;
	private boolean actual_Result, expected_Result;
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
		messgPgPOM = new MessagesPagePOM(driver);
		memberUser = properties.getProperty("member_User1");
		memberPassword = properties.getProperty("member_Password1");
		
		/*****
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		// open the browser
		driver.get(baseUrl);
		Thread.sleep(1000);
		// Pre-Condition
		memberHomePgPOM.memberLogin(memberUser, memberPassword);
		*****/
	}

	@Test
	public void testCase_ID_CYTC_048() throws InterruptedException {
		
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

		// Step 2: Click on Messages link
		memberHomePgPOM.clickMessagesLink();
		actualResult = messgPgPOM.getPageHeader1();
		expectedResult = properties.getProperty("Messages_PageHeader");
		assertEquals(actualResult, expectedResult);

		// Step 3: Click on Advanced button
		messgPgPOM.clickAdvancedBtn();
		// Verify that the Message filtering options - 'Message box' list box is
		// displayed
		actual_Result = messgPgPOM.isMessageBoxListBxDisplayed();
		expected_Result = true;
		assertEquals(actual_Result, expected_Result);
		// Verify that the Message filtering options - 'Type' list box is displayed
		actual_Result = messgPgPOM.isTypeListBxDisplayed();
		expected_Result = true;
		assertEquals(actual_Result, expected_Result);

		// Step 4: Select Valid credentials (Sent items) in Message box list box
		messgPgPOM.selectMessageListBx("Sent items");
		actualResult = messgPgPOM.getSelectedValueMessgBox();
		expectedResult = properties.getProperty("Messages_MessageBoxValue1");
		assertEquals(actualResult, expectedResult);

		// Step 5: Select Valid credentials in Type list box
		messgPgPOM.selectTypeListBx("Administration");
		actualResult = messgPgPOM.getSelectedValueType();
		expectedResult = properties.getProperty("Messages_TypeValue1");
		assertEquals(actualResult, expectedResult);

		// Step 6: Click on Submit button
		messgPgPOM.clickSubmitBtn();
		// Verify that the messages are displayed in the search results which are sent
		// to 'Administration'
		actualResult = messgPgPOM.verifySearchResult();
		expectedResult = properties.getProperty("Messages_SearchResult");
		assertEquals(actualResult, expectedResult);

		// Step 7: Select Valid credentials (Inbox) in Message box list box
		messgPgPOM.selectMessageListBx("Inbox");
		actualResult = messgPgPOM.getSelectedValueMessgBox();
		expectedResult = properties.getProperty("Messages_MessageBoxValue2");
		assertEquals(actualResult, expectedResult);

		// Step 8: Select Valid credentials in Type list box
		messgPgPOM.selectTypeListBx("Administration");
		actualResult = messgPgPOM.getSelectedValueType();
		expectedResult = properties.getProperty("Messages_TypeValue1");
		assertEquals(actualResult, expectedResult);

		// Step 9: Click on Submit button
		messgPgPOM.clickSubmitBtn();
		// Verify that the messages are displayed in the search results which are
		// received from 'Administration'
		actualResult = messgPgPOM.verifySearchResult();
		expectedResult = properties.getProperty("Messages_SearchResult");
		assertEquals(actualResult, expectedResult);

		// Step 10: Select Valid credentials (Inbox) in Message box list box
		messgPgPOM.selectMessageListBx("Inbox");
		actualResult = messgPgPOM.getSelectedValueMessgBox();
		expectedResult = properties.getProperty("Messages_MessageBoxValue2");
		assertEquals(actualResult, expectedResult);

		// Step 11: Select Valid credentials in Type list box
		messgPgPOM.selectTypeListBx("All");
		actualResult = messgPgPOM.getSelectedValueType();
		expectedResult = properties.getProperty("Messages_TypeValue2");
		assertEquals(actualResult, expectedResult);

		// Step 12: Click on Submit button
		messgPgPOM.clickSubmitBtn();
		// Verify that the messages are displayed which received from and sent to
		// 'Administration'
		actualResult = messgPgPOM.verifySearchResult();
		expectedResult = properties.getProperty("Messages_SearchResult");
		assertEquals(actualResult, expectedResult);

		//Logout
		memberHomePgPOM.clickLogout();
		memberHomePgPOM.clickOKInPopup();

	}
	
}
