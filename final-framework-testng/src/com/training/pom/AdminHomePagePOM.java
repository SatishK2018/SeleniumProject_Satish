package com.training.pom;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.training.generics.ScreenShot;

public class AdminHomePagePOM {
	private WebDriver driver; 
	private ScreenShot screenShot;
	private LoginPOM loginPOM;
	private Alert alert;
	
	public AdminHomePagePOM(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		loginPOM = new LoginPOM(driver);
		screenShot = new ScreenShot(driver);
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
	
	@FindBy (xpath="//SPAN[@class='menuText'][text()='Home']")
	private WebElement homeLink;
	
	@FindBy(css="#tdContents > form > table > tbody > tr:nth-child(1) > td.tdHeaderTable")
	private WebElement getPageHeader;
	
	
	//Login using Admin credentials
	public void adminLogin(String userName, String password) {
		loginPOM.sendUserName(userName);
		loginPOM.sendPassword(password);
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot();
		/*String actualResult = loginPOM.getLoggedUser();
		String expectedResult = properties.getProperty("AdminLoginPgHeader");
		assertEquals(actualResult.substring(0, 18),expectedResult);*/	
	}
	
	//Member login
	public void memberLogin(String member){
		this.member.clear();
		this.member.sendKeys(member);
		screenShot.captureScreenShot();
		//fullName = driver.findElement(By.name("member(name)")).getText();
	}
	
	//Click Submit button of Account Information
	public void clickSubmitInformationBtn() {
		this.submitBtn_Account_Information.click();
		screenShot.captureScreenShot();	
	}
	
	//Click Submit button of Payment System to Member
	public void clickSubmitPaySysToMemberBtn() {
		this.submitBtn_Payment_Sys_To_Member.click();
		screenShot.captureScreenShot();
	}
	
	//Click Submit button of Grant Loan
	public void clickSubmitGrantLoanBtn() {
		//jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		this.submitBtn_Grant_Loan.click();
		screenShot.captureScreenShot();
	}
	
	//Click Submit button of View Loan
	public void clickSubmitViewLoanBtn() {
		//jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		this.submitBtn_View_Loan.click();
		screenShot.captureScreenShot();
	}
	
	//Click Home link
	public void clickHomeLink() {
		this.homeLink.click();
		screenShot.captureScreenShot();
	}
	
	//Admin Logout
	public void adminLogout() {
		loginPOM.clickLogoutLnk();
		alert = driver.switchTo().alert();
		alert.accept();
	}
	
	//Get Page header
	public String getPageHeader() {
		return this.getPageHeader.getText();
	}
	
	// Press enter key after entering member login
	public void pressEnterKey() throws InterruptedException {
		this.member.sendKeys(Keys.ENTER);
		screenShot.captureScreenShot();
	}
	
}
