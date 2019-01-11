/*
 * @ Test Case Description : To verify whether loan granted by admin details get displayed in database
 * @ Test Case ID: CYTC_077
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.dataproviders.GrantLoanDataProvider;
import com.training.pom.AccountInfoPagePOM;
import com.training.pom.AdminHomePagePOM;
import com.training.pom.BaseClass;
import com.training.pom.GrantLoanPagePOM;
import com.training.pom.ViewLoanPagePOM;

public class TestCaseID_CYTC_077 {

	private WebDriver driver;
	private Properties properties;
	private AdminHomePagePOM adminHomePgPOM;
	private GrantLoanPagePOM grantLoanPgPOM;
	private AccountInfoPagePOM acctInfoPgPOM;
	private ViewLoanPagePOM viewLoanPgPOM;
	private WebDriverWait wait;
	private String actualResult, expectedResult,dbValue,submittedValue;
	private String adminUser,adminPassword;
		
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
		grantLoanPgPOM = new GrantLoanPagePOM(driver);
		acctInfoPgPOM = new AccountInfoPagePOM(driver); 
		viewLoanPgPOM = new ViewLoanPagePOM(driver);
		wait = new WebDriverWait(driver,20);
		
		adminUser = properties.getProperty("admin_User");
		adminPassword = properties.getProperty("admin_Password");
		adminHomePgPOM.adminLogin(adminUser,adminPassword);
		
		/******
		baseUrl = properties.getProperty("baseURL");
		adminUser = properties.getProperty("admin_User");
		adminPassword = properties.getProperty("admin_Password");
		// open the browser 
		driver.get(baseUrl);
		//Pre-Condition
		adminHomePgPOM.adminLogin(adminUser,adminPassword);
		******/
	}
	
	@Test (dataProvider = "GrantLoan_db-inputs", dataProviderClass = GrantLoanDataProvider.class)
	public void testCaseID_CYTC_077 (String memberLogin, String amount, String descp) throws InterruptedException, ParseException {
		
		//Step 1: Enter valid credentials in Member login text box
		adminHomePgPOM.memberLogin("manzoor");
		Thread.sleep(1000);
		adminHomePgPOM.pressEnterKey();
		actualResult = adminHomePgPOM.getPageHeader();
		String fullName=grantLoanPgPOM.getMemberFullName();
		expectedResult = "Profile of "+fullName;
		assertEquals(actualResult,expectedResult);
		actualResult = acctInfoPgPOM.getDisplayedMemberLogin();
		assertEquals(actualResult,"manzoor");
				
		//Step 2: Click on Submit button of Payment system to member
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("backButton")));
		adminHomePgPOM.clickSubmitGrantLoanBtn();
		actualResult = adminHomePgPOM.getPageHeader();
		expectedResult = "Grant loan to "+fullName;
		assertEquals(actualResult,expectedResult);
		
		//Step 3: Enter valid credentials in amount text box
		grantLoanPgPOM.enterAmount("100000");
		actualResult = grantLoanPgPOM.getEnteredAmount();
		assertEquals(actualResult,"1.000,00");
		
		//Step 4: Enter valid credentials in Description text box
		grantLoanPgPOM.enterDescription("Home Loan");	
		actualResult = grantLoanPgPOM.getEnteredDescription();
		assertEquals(actualResult,"Home Loan");
		
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
		
		// View above granted loan in the view section and match values with the database value
		adminHomePgPOM.clickSubmitViewLoanBtn();
		viewLoanPgPOM.clickLastPage();
		viewLoanPgPOM.clickLastRecord();
		
		//Verify MemberLogin value with the database value
		submittedValue = viewLoanPgPOM.getLoanDetails_MemberLogin();
		dbValue = memberLogin;
		assertEquals(submittedValue,dbValue);
		
		//Verify Amount value with the database value
		submittedValue = viewLoanPgPOM.getLoanDetails_Amount();
		dbValue = amount;
		assertEquals(submittedValue,dbValue);
		
		//Verify Description value with the database value
		submittedValue = viewLoanPgPOM.getLoanDetails_Description();
		dbValue = descp;
		assertEquals(submittedValue,dbValue);
		
		adminHomePgPOM.adminLogout();
	
	}		
	
	
}

