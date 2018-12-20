package com.training.pom;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;

public class AdminHomePagePOM {
	private WebDriver driver; 
	private static Properties properties;
	private ScreenShot screenShot;
	private AdminLoginPOM loginPOM;
	
	public AdminHomePagePOM(WebDriver driver) throws IOException {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		loginPOM = new AdminLoginPOM(driver);
		screenShot = new ScreenShot(driver);
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	
	@FindBy(id="memberUsername")
	private WebElement member; 
	
	@FindBy(xpath="(//INPUT[@type='button'])[7]")
	private WebElement submit_Account_Information;
	
	/*@FindBy(xpath="//SELECT[@name='query(paymentFilter)']")
	List<WebElement> payment_Type_ListBox;*/
	
	@FindBy(xpath="//INPUT[@type='submit']")
	private WebElement search;
	
	@FindBy(css="#tdContents > form > table > tbody > tr:nth-child(1) > td.tdHeaderTable")
	private WebElement getLoggedMember;
	
	public void adminLogin(String userName, String password) throws InterruptedException {
		loginPOM.sendUserName(userName);
		loginPOM.sendPassword(password);
		loginPOM.clickLoginBtn(); 
		Thread.sleep(1000);
		screenShot.captureScreenShot();
		String actualResult = loginPOM.getLoggedUser();
		String expectedResult = properties.getProperty("expectedResult_AdminLogin");
		assertEquals(actualResult.substring(0, 34),expectedResult);		
	}
	
	
	public void memberLogin(String member) throws InterruptedException {
		this.member.clear();
		this.member.sendKeys(member);
		Thread.sleep(1000);
		screenShot.captureScreenShot();
		String actualResult = getLoggedMember().substring(0, 11);
		String expectedResult = properties.getProperty("expectedResult_MemberLogin");
		assertEquals(actualResult.concat("manzoor"),expectedResult);		
	}
	
	
	public void clickSubmitInformationBtn() throws InterruptedException {
		this.submit_Account_Information.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();	
	}
	
	
	public void selectPaymentType(String paymentType) throws InterruptedException {
		Select payment_Type_ListBox = new Select(driver.findElement(By.name("query(paymentFilter)")));
		payment_Type_ListBox.selectByVisibleText(paymentType);
		Thread.sleep(1000);
		screenShot.captureScreenShot();
		String actualResult = payment_Type_ListBox.getFirstSelectedOption().getText();
		String expectedResult = properties.getProperty("expectedResult_PaymentType");
		assertEquals(actualResult,expectedResult);
	}
	
	
	//Step 5: Click on Search button
		public void clickSearchBtn() throws InterruptedException {
			this.search.click();
			Thread.sleep(1000);
			screenShot.captureScreenShot();
	}
	
		
	public void adminLogout() {
		loginPOM.clickLogoutLnk();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	
	public String getLoggedMember() {
		return this.getLoggedMember.getText();
	}
	
}
