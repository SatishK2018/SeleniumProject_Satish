package com.training.pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.training.generics.ScreenShot;

public class GrantLoanPagePOM {
	
	private WebDriver driver; 
	private static Properties properties;
	private ScreenShot screenShot;
	private AdminHomePagePOM adminHomePgPOM;
	private Alert alertDialog;
	private String alertMessg;
	
	public GrantLoanPagePOM(WebDriver driver) throws IOException {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);
		properties = new Properties();
		adminHomePgPOM = new AdminHomePagePOM(driver);
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	
	@FindBy(id="amount")
	private WebElement amount; 
	
	@FindBy(id="description")
	private WebElement descriptionTxtBox;
	
	@FindBy(xpath="//INPUT[@type='submit']")
	private WebElement submitBtn;
	
	@FindBy(xpath="//*[@id=\"tdContents\"]/form/table[1]/tbody/tr[2]/td/table/tbody/tr[1]/td")
	private WebElement loanConfrmMesg;
	
	@FindBy (xpath="//INPUT[@type='submit']")
	private WebElement submitLoanConfrnBtn;
	
	@FindBy (xpath="//TD[@align='center']")
	private WebElement successMsg;
	
	//Enter amount in Text box
	public void enterAmount(String amountValue) throws InterruptedException {
		this.amount.clear();
		this.amount.sendKeys(amountValue);
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Enter description in Text box
	public void enterDescription(String description) throws InterruptedException {
		this.descriptionTxtBox.sendKeys(description);
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Click Submit button
	public void clickSubmitBtn() throws InterruptedException {
		this.submitBtn.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Click Submit Loan button
	public void clickSubmitLoanBtn() throws InterruptedException {
		this.submitLoanConfrnBtn.click();
		screenShot.captureScreenShot();
	}
	
	//Click OK in popup
	public void clickOkInAlert() throws InterruptedException {
		alertDialog = driver.switchTo().alert();
		alertMessg = alertDialog.getText();
		alertDialog.accept();
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Get entered amount in the text box
	public String getEnteredAmount() {
		return amount.getAttribute("value");
	}
	
	//Get entered description in the text box
	public String getEnteredDescription() {
		return descriptionTxtBox.getAttribute("value");		
	}
	
	//Get grant loan confirmation message
	public String getLoanConfrmMessge() {
		return loanConfrmMesg.getText().trim();
	}
	
	//Get alert text in the pop up
	public String getAlertMessg() {
		return alertMessg;		
	}
		
}
