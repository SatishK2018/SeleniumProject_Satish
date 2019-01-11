package com.training.pom;

import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.training.generics.ScreenShot;

public class MemberPaymentPagePOM {

	private WebDriver driver;
	private ScreenShot screenShot;
	private Select schedule_Type_ListBox;

	public MemberPaymentPagePOM(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);
	}

	@FindBy(id = "memberUsername")
	private WebElement loginTxtBx;

	@FindBy(id = "amount")
	private WebElement amountTxtBx;

	@FindBy(id = "schedulingTypeSelect")
	private WebElement schedulingListBx;

	@FindBy(id = "calendarTrigger0")
	private WebElement scheduledDate;
	
	@FindBy (xpath="(//DIV[@unselectable='on'][text()='›'][text()='›'])")
	private WebElement nextCalendarMonth;
	
	@FindBy (xpath="//table/tbody/tr[5]/td[2]")
	private WebElement selectDate;

	@FindBy(name = "description")
	private WebElement descriptTxtBx;
	
	@FindBy(id="submitButton")
	private WebElement submitBtn1;
	
	@FindBy(xpath="//INPUT[@type='submit']")
	private WebElement submitBtn2;

	@FindBy(xpath="//*[@id=\"tdContents\"]/form/table[1]/tbody/tr[2]/td/table/tbody/tr[1]/td")
	private WebElement transConfMesg;
	
	@FindBy(xpath= "//*[@id=\"tdContents\"]/table[1]/tbody/tr[1]/td[1]")
	private WebElement pageHeader;
	
	
	//Enter name in the Login(recipient) text box
	public void enterNameInLoginTxtBx(String name) {
		this.loginTxtBx.clear();
		this.loginTxtBx.sendKeys(name);
		screenShot.captureScreenShot();
	}

	//Get entered value in the Login(recipient) text box
	public String getEnteredName() {
		return this.loginTxtBx.getAttribute("value");
	}

	//Enter amount in the Amount text box
	public void enterAmount(String amount) {
		this.amountTxtBx.clear();
		this.amountTxtBx.sendKeys(amount);
		screenShot.captureScreenShot();
	}

	//Get entered amount in the Amount text box
	public String getEnteredAmount() {
		return this.amountTxtBx.getAttribute("value");
	}

	//Select scheduling type from Scheduling List box
	public void selectSchedulingType(String scheduleType) {
		schedule_Type_ListBox = new Select(schedulingListBx);
		schedule_Type_ListBox.selectByVisibleText(scheduleType);
		screenShot.captureScreenShot();
	}

	//Get selected type in Scheduling Type list box
	public String getSelectedScheduleType() {
		return this.schedule_Type_ListBox.getFirstSelectedOption().getText();
	}

	//Select date from the calendar
	public void selectScheduledDate() {
		//this.scheduledDate.sendKeys(scheduledDate);
		this.scheduledDate.click();
		this.nextCalendarMonth.click();
		this.selectDate.click();
		screenShot.captureScreenShot();
	}
	
	//Get selected scheduled date
	public String getSelectedDate() {
		return this.scheduledDate.getAttribute("value");
	}
	
	//Enter description in the Description text box
	public void enterDescription(String description) {
		this.descriptTxtBx.clear();
		this.descriptTxtBx.sendKeys(description);
		screenShot.captureScreenShot();
	}
	
	//Get entered description in the Description text box
	public String getDescription() {
		return this.descriptTxtBx.getAttribute("value");
	}
	
	//Click Submit button
	public void clickSubmitBtn1() {
		this.submitBtn1.click();
		screenShot.captureScreenShot();
	}
	
	//Verify confirmation message
	public String getConfMessg() {
		return this.transConfMesg.getText().trim();
	}
	
	//Click Submit button
	public void clickSubmitBtn2() {
		this.submitBtn2.click();
		screenShot.captureScreenShot();
	}

	//Get Page header 
	public String getPageHeader() {
		return this.pageHeader.getText().trim();
	}
	
	// Press enter key after entering member login
	public void pressEnterKey() {
		this.loginTxtBx.sendKeys(Keys.ENTER);
		screenShot.captureScreenShot();
	}
	

}
