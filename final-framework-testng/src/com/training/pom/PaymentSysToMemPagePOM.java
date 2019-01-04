package com.training.pom;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.training.generics.ScreenShot;

public class PaymentSysToMemPagePOM {
	
	private WebDriver driver; 
	private ScreenShot screenShot;
	private Select transType; 
	
	public PaymentSysToMemPagePOM(WebDriver driver) throws IOException {
		this.driver = BaseClass.driver;
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);
	}
	
	@FindBy(name="amount")
	private WebElement amount; 
	
	@FindBy(id="type")
	private WebElement transTypeLstBox;
	
	@FindBy(id="description")
	private WebElement descriptionTxtBox;
	
	@FindBy(id="submitButton")
	private WebElement submitBtn;
	
	@FindBy(xpath="//*[@id=\"tdContents\"]/form/table[1]/tbody/tr[2]/td/table/tbody/tr[1]/td")
	private WebElement tranConfrmMesg;
	
	@FindBy (xpath="//INPUT[@type='submit']")
	private WebElement submitTransBtn;
	
	@FindBy (xpath="//TD[@align='center']")
	private WebElement successMsg;
	
	
	//Enter amount in Text box
	public void enterAmount(String amountValue) {
		this.amount.clear();
		this.amount.sendKeys(amountValue);
		screenShot.captureScreenShot();
	}
	
	//Click Transaction Type list box
	public void clickTransTypeLstBox() {
		this.transTypeLstBox.click();
		screenShot.captureScreenShot();
		this.transTypeLstBox.click();
	}
	
	//Select Transaction Type
	public void selectTransType(String transTypeValue) {
		transType = new Select(this.transTypeLstBox);
		transType.selectByVisibleText(transTypeValue);
		screenShot.captureScreenShot();
	}
	
	//Enter description in Text box
	public void enterDescription(String description) {
		this.descriptionTxtBox.sendKeys(description);
		screenShot.captureScreenShot();
	}
	
	//Click Submit button for Transaction confirmation
	public void clickSubmitBtn() {
		this.submitBtn.click();
		screenShot.captureScreenShot();
	}
	
	//Click Submit button for transaction submission
	public void clickSubmitTransBtn() {
		this.submitTransBtn.click();
		screenShot.captureScreenShot();
	}
	
	//Get entered amount in the text box
	public String getAmount() {
		return amount.getAttribute("value");
	}
	
	//Get selected value in Transaction Type list box
	public String getSelecedTransType() {
		return this.transType.getFirstSelectedOption().getText();
	}
	
	//Get entered description in the text box
	public String getEnteredDescription() {
		return this.descriptionTxtBox.getAttribute("value");
	}
	
	//Get transaction confirmation message
	public String getTransConfrmMessg() {
		return tranConfrmMesg.getText().trim();
	}
	
	//Get Success message
	public String getSuccessMessg() {
		return this.successMsg.getText().trim();
	}
	
	
}
