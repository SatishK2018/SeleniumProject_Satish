package com.training.pom;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.training.generics.ScreenShot;

public class ViewLoanPagePOM {
	
	private WebDriver driver; 
	private ScreenShot screenShot;
	String radioBtn;
	
	public ViewLoanPagePOM(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);
	}
	
	@FindBy(xpath = "(//INPUT[@type='radio'])[1]")
	private WebElement radioBtn_OPEN; 
	
	@FindBy(xpath = "(//INPUT[@type='radio'])[2]")
	private WebElement radioBtn_CLOSED; 
	
	@FindBy(id = "amountText")
	private WebElement amountTxtBx;
	
	@FindBy(xpath = "//tr[1]/td[@class='headerField'][1]")
	private WebElement loanDetails_MemberLogin;
	
	@FindBy(xpath = "//tr[4]/td[@class='headerField'][1]")
	private WebElement loanDetails_Amount;
	
	@FindBy (xpath = "//tr[5]/td[@class='headerField'][1]")
	private WebElement loanDetails_Description;
	
	@FindBy(xpath = "//IMG[contains(@src,'/web/pages/images/next.gif')]")
	private WebElement nextImgBtn;
	
	@FindBy(xpath = "//*[@value=\"Repay\"]")
	private WebElement repayBtn;
	
	@FindBy(xpath = "//*[@id=\"tdContents\"]/table[2]/tbody/tr/td[2]/span")
	private WebElement tableContents;
	
	@FindBy(xpath = "//*[@id=\"tdContents\"]/form/table/tbody/tr[1]/td[1]")
	private WebElement pgHeader;
	
	@FindBy(xpath = "//*[@id=\"tdContents\"]/table[1]/tbody/tr[1]/td[1]")
	private WebElement pgHeader_LoanDetails;
	
	@FindBy(xpath = "//tbody/tr[4]/td[@class='headerField'][2]")
	private WebElement verifyRemainingAmount;
	
	//Verify Open radio button is selected
	public void isOpenRadioBtnSelected() {
		radioBtn = this.radioBtn_OPEN.getAttribute("value");
		if(radioBtn_OPEN.equals("CLOSED"))
			this.radioBtn_OPEN.click();
	}
	
	//Click Closed radio button
	public void clickClosedRadioBtn() {
		this.radioBtn_CLOSED.click();
		screenShot.captureScreenShot();		
	}
	
	//Click Open radio button
	public void clickOpenRadioBtn() {
		this.radioBtn_OPEN.click();
		screenShot.captureScreenShot();		
	}
	
	//Get Total records in the Search Result
	public int getTotalRecords() throws ParseException {
		int totalRecords;
		String getText;
		
		getText = this.tableContents.getText();
		totalRecords = ((Number)NumberFormat.getInstance().parse(getText)).intValue();
		
		return totalRecords;
	}
		
		
	//Click on the last page
		public void clickLastPage() throws ParseException {
			int getLastPgNum,totalRecords;
			
			totalRecords = this.getTotalRecords();
			
			if(totalRecords >= 16) {
			
			if(totalRecords%15 == 0)
				getLastPgNum = totalRecords/15;
			else
				getLastPgNum = (totalRecords/15)+1;
			
			//System.out.println(getLastPgNum);
			
			driver.findElement(By.xpath("//A[@onclickhandler=''][text()='"+getLastPgNum+"']")).click();	
			screenShot.captureScreenShot();
			}
		}
	
		
	//Click on the last record
	public void clickLastRecord() throws ParseException {
		int totalRecords,lastRecord;
		
		totalRecords = this.getTotalRecords();
		
		if(totalRecords >= 16) {
		if(totalRecords%15 == 0)
			lastRecord = 16;
		else
			lastRecord = (totalRecords%15)+1;
		
		driver.findElement(By.xpath("//tr["+lastRecord+"]/td[4]/img")).click();		
		screenShot.captureScreenShot();
		} else {
			lastRecord = totalRecords+1;
			driver.findElement(By.xpath("//tr["+lastRecord+"]/td[4]/img")).click();		
			screenShot.captureScreenShot();
		}
			
	}
	
	// Get View Loans page header
	public String getPageHeader() {
		return this.pgHeader.getText().trim();
	}
	
	//Get Loan Details page header
	public String getPageHeader_LoanDetails() {
		return this.pgHeader_LoanDetails.getText().trim();
	}
	
	//Enter repay amount
	public void enterRepayAmount(String amount) {
		this.amountTxtBx.sendKeys(Keys.CONTROL,"a");
		this.amountTxtBx.sendKeys(Keys.CONTROL,"x");
		this.amountTxtBx.sendKeys(amount);
		screenShot.captureScreenShot();
	}
	
	//Get Repay amount in the text box
	public String getRepayAmount() {
		return this.amountTxtBx.getAttribute("value");
	}
	
	
	//Click Repay button
	public void clickRepayBtn() {
		this.repayBtn.click();
		screenShot.captureScreenShot();
	}
	
	//Verify remaining amount
	public String verifyRemainingAmount() {
		return this.verifyRemainingAmount.getText().trim();
	}
	
	//Capture screen
	public void getScreenShot() {
		screenShot.captureScreenShot();
	}
	
	//Get Member Login from Loan Details page
	public String getLoanDetails_MemberLogin() {
		return this.loanDetails_MemberLogin.getText().trim();
	}
	
	//Get Amount from Loan Details page
	public String getLoanDetails_Amount() {
		return this.loanDetails_Amount.getText().substring(0,8).trim();
	}
	
	//Get Description from Loan Details page
	public String getLoanDetails_Description() {
		return this.loanDetails_Description.getText().trim();
	}

		
}
