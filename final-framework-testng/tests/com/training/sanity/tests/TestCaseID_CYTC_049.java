/*
 * @ Test Case Description : To verify whether application allows member to view message sent by the another member
 * @ Test Case ID: CYTC_049
 * @ Author : Satish Kale
 * */

package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.training.pom.BaseClass;
import com.training.pom.LoginPOM;
import com.training.pom.MemberHomePagePOM;
import com.training.pom.MessagesPagePOM;

public class TestCaseID_CYTC_049 {

	private WebDriver driver;
	private Properties properties;
	private LoginPOM loginPOM;
	private MemberHomePagePOM memberHomePgPOM;
	private MessagesPagePOM messgPgPOM;
	private String actualResult, expectedResult;
	private String memberUser1, memberPassword1, memberUser2, memberPassword2;
	
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
		messgPgPOM = new MessagesPagePOM(driver);
		loginPOM = new LoginPOM(driver);
		memberUser1 = properties.getProperty("member_User1");
		memberPassword1 = properties.getProperty("member_Password1");
		memberUser2 = properties.getProperty("member_User2");
		memberPassword2 = properties.getProperty("member_Password2");
						
		/*****
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		baseUrl = properties.getProperty("baseURL");
		loginPOM = new LoginPOM(driver);
		// open the browser
		driver.get(baseUrl);
		Thread.sleep(1000);
		// Pre-Condition
		memberHomePgPOM.memberLogin(memberUser1, memberPassword1);
		*****/
	}

	@Test
	public void testCase_ID_CYTC_049() throws InterruptedException {

		// Login to the application
		memberHomePgPOM.memberLogin(memberUser1, memberPassword1);
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

		// Step 3: Click on Submit button
		messgPgPOM.clickSubmitBtnForNewMessg();
		actualResult = messgPgPOM.getPageHeader2();
		expectedResult = properties.getProperty("SendMessage_Header");
		assertEquals(actualResult, expectedResult);

		// Step 4: Select Valid credentials in Send To list box
		messgPgPOM.selectSendToListBx("Member");
		actualResult = messgPgPOM.getSelectedValueSendToListBx();
		expectedResult = properties.getProperty("SendMessage_SendTo");
		assertEquals(actualResult, expectedResult);

		// Step 5: Enter valid credential in Member login text box
		messgPgPOM.enterMemberLogin("selenium");
		Thread.sleep(1000);
		messgPgPOM.pressEnterKey();
		actualResult = messgPgPOM.getMemberLogin();
		expectedResult = properties.getProperty("SendMessage_MemberLogin");
		assertEquals(actualResult, expectedResult);

		// Step 6: Enter valid credentials in Subject text box
		messgPgPOM.enterSubject("hello");
		actualResult = messgPgPOM.getEnteredSubject();
		expectedResult = properties.getProperty("SendMessage_Subject");
		assertEquals(actualResult, expectedResult);

		// Step 7: Enter valid credentials in Body Text box
		messgPgPOM.switchToFrame();
		messgPgPOM.enterMesgInBody("welcome");
		actualResult = messgPgPOM.getEnteredMessg();
		messgPgPOM.switchToDefaultContent();
		expectedResult = properties.getProperty("SendMessage_MessgBody");
		assertEquals(actualResult, expectedResult);

		// Step 8: Click on Submit button
		messgPgPOM.clickSubmitBtnToSendMesg();
		actualResult = messgPgPOM.getAlertMessg();
		expectedResult = properties.getProperty("Messages_AlertMessgInPopup");
		assertEquals(actualResult, expectedResult);

		// Step 9: Click OK button of pop up window
		messgPgPOM.clickOKInPopup();
		actualResult = messgPgPOM.getPageHeader1();
		expectedResult = properties.getProperty("Messages_PageHeader");
		assertEquals(actualResult, expectedResult);

		// Step 10: Click on Logout button
		memberHomePgPOM.clickLogout();
		actualResult = memberHomePgPOM.getAlertMessg();
		expectedResult = properties.getProperty("MemberLogout_AlertMessg");
		assertEquals(actualResult, expectedResult);

		// Step 11: Click OK Button
		memberHomePgPOM.clickOKInPopup();
		actualResult = memberHomePgPOM.getLoginTitle();
		expectedResult = properties.getProperty("LoginTitle");
		assertEquals(actualResult, expectedResult);

		// Step 12: Enter valid member name in Login Name
		loginPOM.sendUserName(memberUser2);
		actualResult = loginPOM.getLoginName();
		expectedResult = properties.getProperty("member_User2");
		assertEquals(actualResult, expectedResult);

		// Step 13: Enter valid credentials in password text box
		loginPOM.sendPassword(memberPassword2);
		actualResult = loginPOM.getPassword();
		expectedResult = properties.getProperty("member_Password2");
		assertEquals(actualResult, expectedResult);
		
		// Step 14: Click on Submit button
		loginPOM.clickLoginBtn();
		
		// Step 15: Click on Personal Tab
		memberHomePgPOM.clickPersonalTab();
		// Verify that the 'Profile' sub menu is displayed
		actualResult = memberHomePgPOM.getSubMenu("Profile");
		expectedResult = properties.getProperty("PersonalMenu_SubMenu1");
		assertEquals(actualResult, expectedResult);
		// Verify that the 'Messages' sub menu is displayed
		actualResult = memberHomePgPOM.getSubMenu("Messages");
		expectedResult = properties.getProperty("PersonalMenu_SubMenu2");
		assertEquals(actualResult, expectedResult);

		// Step 16: Click on Messages link
		memberHomePgPOM.clickMessagesLink();
		actualResult = messgPgPOM.getPageHeader1();
		expectedResult = properties.getProperty("Messages_PageHeader");
		assertEquals(actualResult, expectedResult);
		
		// Step 17: Click on message to view
		messgPgPOM.clickOnEmail();
		// Verify that the Message details page contains detailed information
		// Verify the email subject
		actualResult = messgPgPOM.getEmailSubjectFromMessgDetails();
		expectedResult = properties.getProperty("SendMessage_Subject");
		assertEquals(actualResult, expectedResult);
		// Verify the email body
		actualResult = messgPgPOM.getEmailBodyFromMessgDetails();
		expectedResult = properties.getProperty("SendMessage_MessgBody");
		assertEquals(actualResult, expectedResult);
		
		//Logout
		memberHomePgPOM.clickLogout();
		memberHomePgPOM.clickOKInPopup();
	
	}

}
