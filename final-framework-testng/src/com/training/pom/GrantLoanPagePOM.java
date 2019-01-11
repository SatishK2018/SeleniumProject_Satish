package com.training.pom;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.training.generics.ScreenShot;

public class GrantLoanPagePOM {
	
	private WebDriver driver; 
	private ScreenShot screenShot;
	//private Alert alertDialog;
	private String alertMessg;
	
	public GrantLoanPagePOM(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);
	}
	
	@FindBy(id="amount")
	private WebElement amount; 
	
	@FindBy (name="member(name)")
	private WebElement memberFullName;
	
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
	public void enterAmount(String amountValue) {
		this.amount.clear();
		this.amount.sendKeys(amountValue);
		screenShot.captureScreenShot();
	}
	
	//Enter description in Text box
	public void enterDescription(String description) {
		this.descriptionTxtBox.sendKeys(description);
		screenShot.captureScreenShot();
	}
	
	//Click Submit button
	public void clickSubmitBtn() {
		this.submitBtn.click();
		screenShot.captureScreenShot();
	}
	
	//Click Submit Loan button
	public void clickSubmitLoanBtn() {
		this.submitLoanConfrnBtn.click();
		screenShot.captureScreenShot();
	}
	
	//Click OK in popup
	public void clickOkInAlert() {
		/*alertDialog = driver.switchTo().alert();
		alertDialog.accept();*/
		driver.switchTo().alert().accept();
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
		alertMessg = driver.switchTo().alert().getText();
		return alertMessg;		
	}
	
	//Get Member full Name
	public String getMemberFullName() {
		return memberFullName.getAttribute("value");
	}
		
}
