package com.training.pom;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.generics.ScreenShot;

public class MessagesPagePOM {

	private WebDriver driver;
	private ScreenShot screenShot;
	private Select messageBox_ListBox, type_ListBox, sendTo_ListBox;
	private Alert alertDialog;
	private List<WebElement> emails;
		
	public MessagesPagePOM(WebDriver driver) throws IOException {
		this.driver = BaseClass.driver;
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);
	}

	@FindBy(id = "modeButton")
	private WebElement advancedBtn;

	@FindBy(id = "messageBoxSelect")
	private WebElement messgBoxListBx;

	@FindBy(name = "query(rootType)")
	private WebElement typeListBx;

	@FindBy(xpath = "//INPUT[@type='submit']")
	private WebElement submitBtn;

	@FindBy(id = "newButton")
	private WebElement submitBtnForNewMesg;

	/*@FindBy(xpath = "//INPUT[@type='submit']")
	private WebElement submitBtnToSendMesg;*/

	@FindBy(id = "memberUsername")
	private WebElement memberLoginTxtBx;
	
	@FindBy (id = "memberName")
	private WebElement memberNameTxtBx;

	@FindBy(name = "message(subject)")
	private WebElement subjectTxtBx;

	@FindBy(className = "CSS1Compat")
	private WebElement messgBody;
	
	@FindBy(xpath = "//*[contains(text(),'Administration')]")
	private WebElement searchResult;

	@FindBy(id = "sendToSelect")
	private WebElement sendToListBx;
	
	@FindBy(xpath = "//tr[4]/td[@class='headerField']")
	private WebElement mailSubject;
	
	@FindBy(xpath = "//tr[5]/td[@class='headerField']")
	private WebElement mailBody;
	
	@FindBy(xpath = "//*[@id=\"tdContents\"]/form[1]/table/tbody/tr[1]/td[1]")
	private WebElement pageHeader1;

	@FindBy(xpath = "//*[@id=\"tdContents\"]/form/table/tbody/tr[1]/td[1]")
	private WebElement pageHeader2;

	// Click Advanced button on Messages page
	public void clickAdvancedBtn() throws InterruptedException {
		this.advancedBtn.click();
		//Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	// Get Page header
	public String getPageHeader1() {
		return this.pageHeader1.getText().trim();
	}

	public String getPageHeader2() {
		return this.pageHeader2.getText().trim();
	}

	// Verify that Message Box list box is displayed
	public boolean isMessageBoxListBxDisplayed() {
		return this.messgBoxListBx.isDisplayed();
	}

	// Verify that Type list box is displayed
	public boolean isTypeListBxDisplayed() {
		return this.messgBoxListBx.isDisplayed();
	}

	// Select value in Message box list box
	public void selectMessageListBx(String messgType) {
		messageBox_ListBox = new Select(messgBoxListBx);
		messageBox_ListBox.selectByVisibleText(messgType);
		screenShot.captureScreenShot();
	}

	// Get selected value from Message box list box
	public String getSelectedValueMessgBox() {
		return this.messageBox_ListBox.getFirstSelectedOption().getText();
	}

	// Select value in Message box list box
	public void selectTypeListBx(String type) {
		type_ListBox = new Select(typeListBx);
		type_ListBox.selectByVisibleText(type);
		screenShot.captureScreenShot();
	}

	// Get selected value from Type list box
	public String getSelectedValueType() {
		return this.type_ListBox.getFirstSelectedOption().getText();
	}

	// Click Submit button
	public void clickSubmitBtn() {
		this.submitBtn.click();
		screenShot.captureScreenShot();
	}

	// Verify that the messages are displayed in the search result which received OR
	// sent to'Administration'
	public String verifySearchResult() {
		return this.searchResult.getText();
	}

	// Click Submit button for new message
	public void clickSubmitBtnForNewMessg() {
		this.submitBtnForNewMesg.click();
		screenShot.captureScreenShot();
	}

	// Select value in Send to list box
	public void selectSendToListBx(String value) {
		sendTo_ListBox = new Select(sendToListBx);
		sendTo_ListBox.selectByVisibleText(value);
		screenShot.captureScreenShot();
	}

	// Get selected value from 'Send to' list box
	public String getSelectedValueSendToListBx() {
		return this.sendTo_ListBox.getFirstSelectedOption().getText();
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
	
	// Enter subject in the 'Subject' text box
	public void enterSubject(String subject) {
		this.subjectTxtBx.sendKeys(subject);
		screenShot.captureScreenShot();
	}

	// Get entered subject in the Subject text box
	public String getEnteredSubject() {
		return this.subjectTxtBx.getAttribute("value");
	}

	// Enter message in the body
	public void enterMesgInBody(String messg) {
		//this.messgBody.clear();
		this.messgBody.sendKeys(messg);
		// WebElement test = driver.findElement(By.className("CSS1Compat"));
		//Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	// Switch to frame
	public void switchToFrame( ) {
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));		
	}
	
	// Switch back to default content
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
	

	// Get entered message in the body
	public String getEnteredMessg() {
		return this.messgBody.getText();
	}

	// Click Submit button to send new message
	public void clickSubmitBtnToSendMesg() {
		this.submitBtn.click();
		screenShot.captureScreenShot();
	}
	
	// Get Alert message - The message was successfully sent
	public String getAlertMessg() {
		alertDialog = driver.switchTo().alert();
		return alertDialog.getText();		
	}
	
	// Click OK in the pop up window
	public void clickOKInPopup() {
		alertDialog.accept();
		screenShot.captureScreenShot();
	}
	
	// Click on unread email with subject 'hello' to view message which user1 has sent
	public void clickOnEmail() {
		emails = driver.findElements(By.xpath("//tr/td[@class='unreadMessage']/a[@class='linkList messageDetails']"));
				
		for(WebElement emailSubject : emails){
		    if(emailSubject.getText().equals("hello")){
		        //System.out.println(emailSubject.getText());
		    	emailSubject.click();
		    	break;
		    }
	    }
		screenShot.captureScreenShot();
	}
	
	// Get email subject from message details
	public String getEmailSubjectFromMessgDetails() {
		return this.mailSubject.getText();
	}
	
	// Get email body from message details
	public String getEmailBodyFromMessgDetails() {
		return this.mailBody.getText();
	}
	

}
