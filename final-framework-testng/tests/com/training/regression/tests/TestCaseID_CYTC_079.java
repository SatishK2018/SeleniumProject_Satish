/*
 * @ Test Case Description : To Verify whether application allows member to perform payment to multiple registered member
 * @ Test Case ID: CYTC_079
 * @ Author : Satish Kale
 * */

package com.training.regression.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.dataproviders.MemberPaymentDataProvider;
import com.training.pom.BaseClass;
import com.training.pom.MemberHomePagePOM;
import com.training.pom.MemberPaymentPagePOM;

public class TestCaseID_CYTC_079 {

	private WebDriver driver;
	private Properties properties;
	private MemberHomePagePOM memberHomePgPOM;
	private MemberPaymentPagePOM memberPayPgPOM;
	private String actualResult, expectedResult;
	private String memberUser, memberPassword;

	/******
	@BeforeTest
	public static void setUpBeforeClass() throws IOException {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	******/	

	@BeforeClass
	public void setUp() throws InterruptedException, IOException {
		this.driver = BaseClass.driver;
		this.properties = BaseClass.properties;
		memberHomePgPOM = new MemberHomePagePOM(driver);
		memberPayPgPOM = new MemberPaymentPagePOM(driver);
		
		memberUser = properties.getProperty("member_User");
		memberPassword = properties.getProperty("member_Password");
		memberHomePgPOM.memberLogin(memberUser, memberPassword);
				
		/******
		baseUrl = properties.getProperty("baseURL");
		memberUser = properties.getProperty("member_User");
		memberPassword = properties.getProperty("member_Password");
		// open the browser
		driver.get(baseUrl);
		// Pre-Condition
		memberHomePgPOM.memberLogin(memberUser, memberPassword);
		******/
	}

	@Test (dataProvider = "MemberPayment_excel-inputs_TC_079", dataProviderClass = MemberPaymentDataProvider.class)
	public void testCase_ID_CYTC_079(String memberLogin, String amount, String descrp ) throws InterruptedException {

		// Step 1: Click on Account tab
		if(!memberHomePgPOM.isMemberPaymentClickable())
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
		// Verify that the 'Loans' sub menu is displayed
		actualResult = memberHomePgPOM.getSubMenu("Loans");
		expectedResult = properties.getProperty("AccountMenu_SubMenu4");
		assertEquals(actualResult, expectedResult);
		
		//Step 2: Click on Member Payment
		//if(memberHomePgPOM.isMemberPaymentClickable())
		memberHomePgPOM.clickMemberPaymentMenu();
					
		//memberHomePgPOM.clickMemberPaymentMenu();
		actualResult = memberHomePgPOM.getPageHeader();
		expectedResult = properties.getProperty("MemberPayment_PageHeader");
		assertEquals(actualResult, expectedResult);
		
		//Step 3: Enter valid credentials in Login text box
		memberPayPgPOM.enterNameInLoginTxtBx(memberLogin);
		Thread.sleep(1000);
		memberPayPgPOM.pressEnterKey();
		actualResult = memberPayPgPOM.getEnteredName();
		assertEquals(actualResult, memberLogin);
		
		//Step 4: Enter valid credentials in Amount text box
		memberPayPgPOM.enterAmount(amount);
		actualResult = memberPayPgPOM.getEnteredAmount();
		assertEquals(actualResult, amount);
		
		//Step 5: Select valid credentials in Scheduling list box
		memberPayPgPOM.selectSchedulingType("Scheduled for future date");
		actualResult = memberPayPgPOM.getSelectedScheduleType();
		expectedResult = properties.getProperty("MemberPayment_SchedulingType");
		assertEquals(actualResult, expectedResult);
		
		//Step 6: select date in Scheduled date for
		memberPayPgPOM.selectScheduledDate();
				
		//Step 7: Enter valid credentials in Description text box
		memberPayPgPOM.enterDescription(descrp);
		actualResult = memberPayPgPOM.getDescription();
		assertEquals(actualResult, descrp);
		
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
		
	}
	
	@AfterClass
	public void memberLogout() {
		memberHomePgPOM.clickLogout();
		memberHomePgPOM.clickOKInPopup();
	}
	
}
