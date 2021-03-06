package com.training.pom;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.training.generics.ScreenShot;

public class ContactsPagePOM {

	private WebDriver driver;
	private ScreenShot screenShot;
	private Alert alert;
	private String xpath;
	private WebElement dynamicXpath;
	
	public ContactsPagePOM(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);
	}

	@FindBy(xpath = "//INPUT[@type='submit']")
	private WebElement submitBtn;

	@FindBy(xpath = "(//INPUT[@type='button'])[1]")
	private WebElement makePaymentsubmitBtn;

	@FindBy(id = "memberUsername")
	private WebElement memberLoginTxtBx;
	
	@FindBy(xpath = "//*[@id=\"tdContents\"]/table/tbody/tr[1]/td[1]")
	private WebElement pageHeader1;
	
	@FindBy(xpath = "//*[@id=\"tdContents\"]/table[1]/tbody/tr[1]/td[1]")
	private WebElement pageHeader2;
	
	@FindBy(xpath = "//*[@id=\"tdContents\"]/form/table[1]/tbody/tr[1]/td[1]")
	private WebElement pageHeader3;
	
	
	// Get Page header
	public String getPageHeader1() {
		return this.pageHeader1.getText().trim();
	}
	
	public String getPageHeader2() {
		return this.pageHeader2.getText().trim();
	}
	
	public String getPageHeader3() {
		return this.pageHeader3.getText().trim();
	}
	
	// Click 'Make Payment' submit button
	public void clickMakePaymentSubmitBtn() {
		this.makePaymentsubmitBtn.click();
		screenShot.captureScreenShot();
		
	}

	// Click Submit button
	public void clickSubmitBtn() throws InterruptedException {
		this.submitBtn.click();
		screenShot.captureScreenShot();
	}

	// Get dynamic xpath
	public void getXpath(String memberLogin) {
		xpath = "//A[@class='profileLink'][text()='"+memberLogin+"']";
		dynamicXpath = driver.findElement(By.xpath(xpath));
	}
	
	// Get added contact 
	public String getAddedContact(String memberLogin) {
		this.getXpath(memberLogin);
		return dynamicXpath.getText();
	}
	
	// Enter member login credentials
	public void enterMemberLogin(String memberLogin) {
		this.memberLoginTxtBx.clear();
		this.memberLoginTxtBx.sendKeys(memberLogin);
		screenShot.captureScreenShot();
	}
	
	// Press enter key after entering member login
	public void pressEnterKey() {
		this.memberLoginTxtBx.sendKeys(Keys.ENTER);
	}

	// Get member login credentials which are entered in the text box
	public String getMemberLogin() {
		return this.memberLoginTxtBx.getAttribute("value");
	}
	
	// Accept alert
	public void acceptAlert() {
		alert = driver.switchTo().alert();
		alert.accept();	
	}
	
	// Click on the added contact
	public void clickOnAddedContact() {
		this.dynamicXpath.click();
		screenShot.captureScreenShot();
	}
	
	
}
