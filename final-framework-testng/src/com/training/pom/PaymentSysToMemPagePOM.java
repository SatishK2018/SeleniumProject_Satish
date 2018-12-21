package com.training.pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.training.generics.ScreenShot;

public class PaymentSysToMemPagePOM {
	
	private WebDriver driver; 
	private static Properties properties;
	private ScreenShot screenShot;
	private Select transType; 
	
	public PaymentSysToMemPagePOM(WebDriver driver) throws IOException {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
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
	public void enterAmount(String amountValue) throws InterruptedException {
		this.amount.clear();
		this.amount.sendKeys(amountValue);
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Click Transaction Type list box
	public void clickTransTypeLstBox() {
		this.transTypeLstBox.click();
		screenShot.captureScreenShot();
		this.transTypeLstBox.click();
	}
	
	//Select Transaction Type
	public void selectTransType(String transTypeValue) throws InterruptedException {
		transType = new Select(this.transTypeLstBox);
		transType.selectByVisibleText(transTypeValue);
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Enter description in Text box
	public void enterDescription(String description) throws InterruptedException {
		this.descriptionTxtBox.sendKeys(description);
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Click Submit button for Transaction confirmation
	public void clickSubmitBtn() throws InterruptedException {
		this.submitBtn.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Click Submit button for transaction submission
	public void clickSubmitTransBtn() throws InterruptedException {
		this.submitTransBtn.click();
		Thread.sleep(1000);
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
