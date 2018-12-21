package com.training.pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.training.generics.ScreenShot;

public class AdminHomePagePOM {
	private WebDriver driver; 
	private static Properties properties;
	private ScreenShot screenShot;
	private AdminLoginPOM loginPOM;
	private JavascriptExecutor jse;
	//private String fullName;
	
	public AdminHomePagePOM(WebDriver driver) throws IOException {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		loginPOM = new AdminLoginPOM(driver);
		screenShot = new ScreenShot(driver);
		jse = (JavascriptExecutor)driver;
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	
	@FindBy(id="memberUsername")
	private WebElement member; 
	
	@FindBy(xpath="(//INPUT[@type='button'])[7]")
	private WebElement submitBtn_Account_Information;
	
	@FindBy(xpath="(//INPUT[@type='button'])[9]")
	private WebElement submitBtn_Payment_Sys_To_Member;
	
	@FindBy(xpath="(//INPUT[@type='button'])[22]")
	private WebElement submitBtn_Grant_Loan;
	
	@FindBy(xpath="(//INPUT[@type='button'])[21]")
	private WebElement submitBtn_View_Loan;
	
	@FindBy(css="#tdContents > form > table > tbody > tr:nth-child(1) > td.tdHeaderTable")
	private WebElement getPageHeader;
	
	
	//Login using Admin credentials
	public void adminLogin(String userName, String password) throws InterruptedException {
		loginPOM.sendUserName(userName);
		loginPOM.sendPassword(password);
		loginPOM.clickLoginBtn(); 
		Thread.sleep(1000);
		screenShot.captureScreenShot();
		/*String actualResult = loginPOM.getLoggedUser();
		String expectedResult = properties.getProperty("AdminLoginPgHeader");
		assertEquals(actualResult.substring(0, 18),expectedResult);*/	
	}
	
	//Member login
	public void memberLogin(String member) throws InterruptedException {
		this.member.clear();
		this.member.sendKeys(member);
		Thread.sleep(1000);
		screenShot.captureScreenShot();
		//fullName = driver.findElement(By.name("member(name)")).getText();
	}
	
	//Click Submit button of Account Information
	public void clickSubmitInformationBtn() throws InterruptedException {
		this.submitBtn_Account_Information.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();	
	}
	
	//Click Submit button of Payment System to Member
	public void clickSubmitPaySysToMemberBtn() throws InterruptedException {
		this.submitBtn_Payment_Sys_To_Member.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Click Submit button of Grant Loan
	public void clickSubmitGrantLoanBtn() throws InterruptedException {
		//jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		this.submitBtn_Grant_Loan.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Click Submit button of View Loan
	public void clickSubmitViewLoanBtn() throws InterruptedException {
		//jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		this.submitBtn_View_Loan.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Admin Logout
	public void adminLogout() throws InterruptedException {
		loginPOM.clickLogoutLnk();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	//Get Page header
	public String getPageHeader() {
		return this.getPageHeader.getText();
	}
	
}
