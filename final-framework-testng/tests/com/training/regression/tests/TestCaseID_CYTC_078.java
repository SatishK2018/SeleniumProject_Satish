/*
 * @ Test Case Description : To verify whether application allows admin to grant loan to multiple member
 * @ Test Case ID: CYTC_076
 * @ Author : Satish Kale
 * */

package com.training.regression.tests;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.dataproviders.GrantLoanDataProvider;
import com.training.pom.AdminHomePagePOM;
import com.training.pom.BaseClass;
import com.training.pom.GrantLoanPagePOM;
import com.training.pom.LoginPOM;
import com.training.pom.MemberHomePagePOM;
import com.training.pom.ViewLoanPagePOM;

public class TestCaseID_CYTC_078 {

	private WebDriver driver;
	private Properties properties;
	private LoginPOM loginPOM;
	private AdminHomePagePOM adminHomePgPOM;
	private MemberHomePagePOM memberHomePgPOM;
	private ViewLoanPagePOM viewLoanPgPOM;
	private GrantLoanPagePOM grantLoanPgPOM;
	private WebDriverWait wait;
	private String actualResult, expectedResult;
	private String adminUser,adminPassword, memberUser, memberPassword;
		
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
		adminHomePgPOM = new AdminHomePagePOM(driver);
		memberHomePgPOM = new MemberHomePagePOM(driver);
		grantLoanPgPOM = new GrantLoanPagePOM(driver);
		viewLoanPgPOM = new ViewLoanPagePOM(driver);
		loginPOM = new LoginPOM(driver);
		wait = new WebDriverWait(driver,20);
		
		//baseUrl = properties.getProperty("baseURL");
		adminUser = properties.getProperty("admin_User");
		adminPassword = properties.getProperty("admin_Password");
		memberUser = properties.getProperty("member_User3");
		memberPassword = properties.getProperty("member_Password3");
		// open the browser 
		//driver.get(baseUrl);
		//Pre-Condition
		adminHomePgPOM.adminLogin(adminUser,adminPassword);
		
	}
	
	@Test (dataProvider = "GrantLoan_excel-inputs_TC_078", dataProviderClass = GrantLoanDataProvider.class)
	public void testCaseID_CYTC_078 (String memberLogin, String amount, String description, String repayAmount) throws InterruptedException, ParseException {
			
		//Step 1: Enter valid credentials in Member login text box
		adminHomePgPOM.memberLogin(memberLogin);
		if(memberLogin.equals("manzoor")) {
		Thread.sleep(1000);
		adminHomePgPOM.pressEnterKey();
		}
		actualResult = adminHomePgPOM.getPageHeader();
		String fullName=grantLoanPgPOM.getMemberFullName();
		expectedResult = "Profile of "+fullName;
		//System.out.println(actualResult+"\n"+expectedResult);
		assertEquals(actualResult,expectedResult);
				
		//Step 2: Click on Submit button of Grant loan 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("backButton")));
		adminHomePgPOM.clickSubmitGrantLoanBtn();
		actualResult = adminHomePgPOM.getPageHeader();
		expectedResult = "Grant loan to "+fullName;
		assertEquals(actualResult,expectedResult);
		
		//Step 3: Enter valid credentials in amount text box
		grantLoanPgPOM.enterAmount(amount);
		actualResult = grantLoanPgPOM.getEnteredAmount();
		assertEquals(actualResult,amount);
		
		//Step 4: Enter valid credentials in Description text box
		grantLoanPgPOM.enterDescription(description);	
		actualResult = grantLoanPgPOM.getEnteredDescription();
		assertEquals(actualResult,description);
		
		//Step 5: Click on Submit button
		grantLoanPgPOM.clickSubmitBtn();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@type='submit']")));
		actualResult = grantLoanPgPOM.getLoanConfrmMessge();
		expectedResult = properties.getProperty("GrantLoanPg_LoanConfMesg");
		assertEquals(actualResult,expectedResult);
		
		//Step 6: Click on Submit button
		grantLoanPgPOM.clickSubmitLoanBtn();
		wait.until(ExpectedConditions.alertIsPresent());
		actualResult = driver.switchTo().alert().getText();
		expectedResult = properties.getProperty("GrantLoanPg_SuccessMesg");
		assertEquals(actualResult,expectedResult);
			
		//Step 7: Click on OK button
		driver.switchTo().alert().accept();
		actualResult = adminHomePgPOM.getPageHeader();
		expectedResult = "Profile of "+fullName;
		assertEquals(actualResult,expectedResult);
		
		//Step 8: Click on Logout tab
		adminHomePgPOM.adminLogout();
		
		//Step 9: Enter valid credentials in Login name text box
		loginPOM.sendUserName(memberUser);
		actualResult = loginPOM.getLoginName();
		expectedResult = properties.getProperty("member_User3");
		assertEquals(actualResult, expectedResult);
		
		//Step 10: Enter valid credentials in password text box
		loginPOM.sendPassword(memberPassword);
		actualResult = loginPOM.getPassword();
		expectedResult = properties.getProperty("member_Password3");
		assertEquals(actualResult, expectedResult);
		
		//Step 11: Click on Submit button
		loginPOM.clickLoginBtn();
		
		//Step 12: Click on Account link
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
		
		//Step 13: Click on Loans link
		memberHomePgPOM.clickLoansMenu();
		actualResult = viewLoanPgPOM.getPageHeader();
		expectedResult = properties.getProperty("MyLoanPageHeader");
		assertEquals(actualResult, expectedResult);
		
		//Step 14: Click on View icon of Particular loan		
		viewLoanPgPOM.clickLastPage();
		viewLoanPgPOM.clickLastRecord();
		actualResult = viewLoanPgPOM.getPageHeader_LoanDetails();
		expectedResult = properties.getProperty("LoanDetailsPageHeader");
		assertEquals(actualResult, expectedResult);
		
		//Step 15: Enter Valid credentials in Amount text box
		viewLoanPgPOM.enterRepayAmount(repayAmount);
		actualResult = viewLoanPgPOM.getRepayAmount();
		//expectedResult = properties.getProperty("RepayAmount");
		assertEquals(actualResult, repayAmount);
		
		//Step 16: Click on Repay button
		viewLoanPgPOM.clickRepayBtn();
		actualResult = driver.switchTo().alert().getText();
		expectedResult = properties.getProperty("LoanRepayConfMessg");
		assertEquals(actualResult, expectedResult);
		
		//Step 17: Click on OK button
		driver.switchTo().alert().accept();
		actualResult = driver.switchTo().alert().getText();
		expectedResult = properties.getProperty("LoanRepaysuccessMessg");
		assertEquals(actualResult, expectedResult);
		
		//Step 18: Click on OK button
		driver.switchTo().alert().accept();
		actualResult = viewLoanPgPOM.getRepayAmount();
		viewLoanPgPOM.getScreenShot();
		expectedResult = "995,00";
		assertEquals(actualResult, expectedResult);
	}		
	
	@AfterClass
	public void adminLogout() {
		adminHomePgPOM.adminLogout();
	}	
	
}

