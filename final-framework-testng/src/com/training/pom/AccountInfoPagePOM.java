package com.training.pom;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.training.generics.ScreenShot;

public class AccountInfoPagePOM {
	
	private WebDriver driver; 
	private ScreenShot screenShot;
	private Select payment_Type_ListBox;
	private WebElement dynamicXpath;
	private String xpath;
		
	public AccountInfoPagePOM(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);	
	}
	
	@FindBy(name= "query(paymentFilter)")
	private WebElement paymentTypeListBx;
	
	@FindBy (id = "memberUsername")
	private WebElement memberLogin;
	
	@FindBy (id = "memberName")
	private WebElement memberName;
	
	@FindBy(xpath="//INPUT[@type='submit']")
	private WebElement search;	
	
	@FindBy(name = "query(period).begin")
	private WebElement fromDateTxtBx;
	
	@FindBy(name = "query(period).end")
	private WebElement toDateTxtBx;
	
	@FindBy (xpath = "//div[@class='calendar']/table/tbody/tr[2]/td[6]")
	private WebElement selectFromDate;
	
	@FindBy (xpath = "//div[@class='calendar']/table/tbody/tr[5]/td[7]")
	private WebElement selectToDate;
	
	@FindBy(id = "calendarTrigger0")
	private WebElement fromDatePicker;
	
	@FindBy(id = "calendarTrigger1")
	private WebElement toDatePicker;
	
	@FindBy(xpath = "//DIV[@unselectable='on'][text()='‹']")
	private WebElement movePreviousMonth;
	
	@FindBy(xpath = "//DIV[@unselectable='on'][text()='›']")
	private WebElement moveNextMonth;
	
	@FindBy(xpath = "//td[text()='Loan repayment']")
	private WebElement searchReltLoanRepay;
	
	@FindBy(xpath="//img[contains(@src,'/web/pages/images/view.gif')]")
	private WebElement viewImgBtn;
	
	@FindBy(xpath="//*[@id=\"tdContents\"]/form/table[1]/tbody/tr[2]/td/table/tbody/tr[1]/td")
	private WebElement tranConfrmMesg;
	
	@FindBy(id= "backButton")
	private WebElement backBtn;
	
	@FindBy(css= "#tdContents > table.defaultTableContent > tbody > tr:nth-child(1) > td.tdHeaderTable")
	private WebElement getPageHeader;
	
	
	//Select Payment Type from the List box
	public void selectPaymentType(String paymentType) {
		payment_Type_ListBox = new Select(paymentTypeListBx);
		payment_Type_ListBox.selectByVisibleText(paymentType);
		screenShot.captureScreenShot();
	}
	
	//Verify elements in the Payment Type list box
	/*public void verifyPaymentTypeListbx(Select payment_Type_ListBox) {
		List<WebElement> list = payment_Type_ListBox.getOptions();
		 	for(int j=0; j<list.size();j++) {
		 		System.out.println(list.get(j).getText());
		 	}
	
	}*/
	
	//Click in the Payment Type list box to display all values in the list box
	public void clickPaymentTypeListBox() {
		this.paymentTypeListBx.click();
		screenShot.captureScreenShot();
		this.paymentTypeListBx.click();
	}
	
	//Click Search button on Transaction Details page
	public void clickSearchBtn() {
		this.search.click();
		screenShot.captureScreenShot();
	}
	
	//Click View icon button to display particular transaction
	public void clickViewImgBtn() {
		this.viewImgBtn.click();
		screenShot.captureScreenShot();
	}
	
	// Enter Member Login name
	public void enterMemberLogin(String memberLogin) {
		this.memberLogin.sendKeys(memberLogin);
		screenShot.captureScreenShot();
	}
	
	// Press enter key after entering member login
	public void pressEnterKey() {
		this.memberLogin.sendKeys(Keys.ENTER);
		screenShot.captureScreenShot();
	}
	
	// Get entered Member Login name
	public String getEnteredMemberLogin() {
		return memberLogin.getAttribute("value");
	}
	
	//Click Back button on Transaction Details page
	public void clickBackBtn_TransDetailsPg() throws InterruptedException {
		this.backBtn.click();
		screenShot.captureScreenShot();	
	}
	
	//Get Page Header
	public String getPageHeader() {
		return this.getPageHeader.getText();
	}
		
	//Get selected option in Payment Type list box 
	public String getSelectedPaymentType() {		
		return this.payment_Type_ListBox.getFirstSelectedOption().getText();
	}
	
	// Get dynamic xpath
	public void getXpath(String memberLogin) {
		xpath = "//td[2]/a[@class='profileLink'and text()='"+memberLogin+"']";
		dynamicXpath = driver.findElement(By.xpath(xpath));
	}
		
	// Get Search Result 
	public String getSearchResult(String memberLogin) {
		this.getXpath(memberLogin);
		return dynamicXpath.getText();
	}
	
	// Get Loan Repayments search result
	public String getSearchResultLoanRepayments() {
		return this.searchReltLoanRepay.getText();
	}
	
	// Select From Date from Date Picker
	public void selectFromDate() {
		this.fromDatePicker.click();
		this.movePreviousMonth.click();
		this.selectFromDate.click();	
		screenShot.captureScreenShot();
	}
	
	// Select To Date from Date Picker
	public void selectToDate() {
		//this.toDatePicker.click();
		//this.selectToDate.click();
		this.toDateTxtBx.sendKeys("30/12/2018");
		screenShot.captureScreenShot();
	}
	
	// Clear Member Login and Member Name text box
	public void clearMemberLoginAndNameTxtBx() throws InterruptedException {
		Thread.sleep(1000);
		this.memberLogin.sendKeys(Keys.CONTROL, "a");
		this.memberLogin.sendKeys(Keys.DELETE);
		this.memberName.sendKeys(Keys.CONTROL, "a");
		this.memberName.sendKeys(Keys.DELETE);
	}
	
	// Clear From date and To date text box
	public void clearFromDateAndToDateTxtBx() throws InterruptedException {
		Thread.sleep(1000);
		this.toDateTxtBx.sendKeys(Keys.CONTROL, "a");
		this.toDateTxtBx.sendKeys(Keys.DELETE);
		this.fromDateTxtBx.sendKeys(Keys.CONTROL, "a");
		this.fromDateTxtBx.sendKeys(Keys.DELETE);
	}
	
		
		
}



