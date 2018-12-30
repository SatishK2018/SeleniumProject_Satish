package com.training.pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
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
	private static Properties properties;
	private ScreenShot screenShot;
	private Select payment_Type_ListBox;
	private WebElement dynamicXpath;
	private String xpath;
		
	public AccountInfoPagePOM(WebDriver driver) throws IOException {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
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
	public void selectPaymentType(String paymentType) throws InterruptedException{
		payment_Type_ListBox = new Select(paymentTypeListBx);
		payment_Type_ListBox.selectByVisibleText(paymentType);
		Thread.sleep(1000);
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
	public void clickPaymentTypeListBox() throws InterruptedException {
		this.paymentTypeListBx.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();
		this.paymentTypeListBx.click();
	}
	
	//Click Search button on Transaction Details page
	public void clickSearchBtn() throws InterruptedException {
		this.search.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	//Click View icon button to display particular transaction
	public void clickViewImgBtn() throws InterruptedException {
		this.viewImgBtn.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	// Enter Member Login name
	public void enterMemberLogin(String memberLogin) throws InterruptedException {
		this.memberLogin.sendKeys(memberLogin);
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	// Press enter key after entering member login
	public void pressEnterKey() throws InterruptedException {
		this.memberLogin.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	// Get entered Member Login name
	public String getEnteredMemberLogin() {
		return memberLogin.getAttribute("value");
	}
	
	//Click Back button on Transaction Details page
	public void clickBackBtn_TransDetailsPg() throws InterruptedException {
		this.backBtn.click();
		Thread.sleep(1000);
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
	public void selectFromDate() throws InterruptedException {
		this.fromDatePicker.click();
		this.movePreviousMonth.click();
		this.selectFromDate.click();	
		Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	// Select To Date from Date Picker
	public void selectToDate() throws InterruptedException {
		//this.toDatePicker.click();
		//this.selectToDate.click();
		this.toDateTxtBx.sendKeys("30/12/2018");
		Thread.sleep(1000);
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



