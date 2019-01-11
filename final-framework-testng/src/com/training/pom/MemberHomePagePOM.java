package com.training.pom;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.training.generics.ScreenShot;

public class MemberHomePagePOM {
	private WebDriver driver;
	private ScreenShot screenShot;
	private LoginPOM loginPOM;
	private Alert alert;
	// private String fullName;

	public MemberHomePagePOM(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);
		loginPOM = new LoginPOM(driver);
	}

	@FindBy(xpath = "//SPAN[@class='menuText'][text()='Account']")
	private WebElement accountMenu;

	@FindBy(xpath = "//SPAN[@class='subMenuText'][text()='Account Information']")
	private WebElement accountSubMenu_AccountInformation;

	@FindBy(xpath = "//SPAN[@class='subMenuText'][text()='Scheduled payments']")
	private WebElement accountSubMenu_ScheduledPayments;

	@FindBy(xpath = "//SPAN[@class='subMenuText'][text()='Invoices']")
	private WebElement accountSubMenu_Invoices;

	@FindBy(xpath = "//SPAN[@class='subMenuText'][text()='Member Payment']")
	private WebElement accountSubMenu_MemberPayment;
	
	@FindBy(xpath = "//SPAN[@class='subMenuText'][text()='Loans']")
	private WebElement accountSubMenu_Loans;

	@FindBy(xpath = "//SPAN[@class='menuText'][text()='Personal']")
	private WebElement personalMenu;

	@FindBy(xpath = "//SPAN[@class='subMenuText'][text()='Profile']")
	private WebElement personalSubMenu_Profile;

	@FindBy(xpath = "//SPAN[@class='subMenuText'][text()='Messages']")
	private WebElement personalSubMenu_Messages;
	
	@FindBy(xpath = "//SPAN[@class='subMenuText'][text()='Contacts']")
	private WebElement personalSubMenu_Contacts;

	@FindBy(xpath = "(//INPUT[@type='button'])[22]")
	private WebElement submitBtn_Grant_Loan;

	@FindBy(xpath = "(//INPUT[@type='button'])[21]")
	private WebElement submitBtn_View_Loan;
	
	@FindBy (xpath = "//SPAN[@class='subMenuText'][text()='Member Payment']")
	private WebElement memberPaymentLink;
	
	@FindBy(id = "modeButton")
	private WebElement advancedBtn;

	@FindBy(xpath = "//*[@id=\"tdContents\"]/form/table/tbody/tr[1]/td[1]")
	private WebElement pageHeader;

	@FindBy(className = "loginTitle")
	private WebElement loginTitle;

	// Get Login title
	public String getLoginTitle() {
		return this.loginTitle.getText();
	}

	// Login using Member credentials
	public void memberLogin(String userName, String password) {
		loginPOM.sendUserName(userName);
		loginPOM.sendPassword(password);
		loginPOM.clickLoginBtn();
		screenShot.captureScreenShot();
	}
	
	// Click Logout link
	public void clickLogout() {
		loginPOM.clickLogoutLnk();
	}

	// Get logout confirmation message
	public String getAlertMessg() {
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	// Click OK in the pop up
	public void clickOKInPopup() {
		alert = driver.switchTo().alert();
		alert.accept();
		screenShot.captureScreenShot();
	}

	// Click on Account tab
	public void clickAccountTab() {
		this.accountMenu.click();
		screenShot.captureScreenShot();
	}

	// Click on Personal tab
	public void clickPersonalTab() {
		this.personalMenu.click();
		screenShot.captureScreenShot();
	}

	// Get available sub menu in Account menu
	public String getSubMenu(String subMenu) {

		switch (subMenu) {

		case "Account Information":
			subMenu = accountSubMenu_AccountInformation.getText();
			break;
		case "Scheduled payments":
			subMenu = accountSubMenu_ScheduledPayments.getText();
			break;
		case "Invoices":
			subMenu = accountSubMenu_Invoices.getText();
			break;
		case "Profile":
			subMenu = personalSubMenu_Profile.getText();
			break;
		case "Messages":
			subMenu = personalSubMenu_Messages.getText();
			break;
		case "Contacts":
			subMenu = personalSubMenu_Contacts.getText();
			break;

		}
		return subMenu;
	}

	// Click on Member Payment sub menu
	public void clickMemberPaymentMenu() {
		this.accountSubMenu_MemberPayment.click();
		screenShot.captureScreenShot();
	}
	
	// Click on Account Information sub menu
	public void clickAccountInformationMenu() {
		this.accountSubMenu_AccountInformation.click();
		screenShot.captureScreenShot();
	}
	
	// Click on Loans sub menu
	public void clickLoansMenu() {
		this.accountSubMenu_Loans.click();
		screenShot.captureScreenShot();
	}

	// Click on Messages sub menu
	public void clickMessagesLink() {
		this.personalSubMenu_Messages.click();
		screenShot.captureScreenShot();
	}
	
	// Click on Contacts sub menu
	public void clickContactsLink() {
		this.personalSubMenu_Contacts.click();
		screenShot.captureScreenShot();
	}

	// Click on Scheduled payments sub menu
	public void clickScheduledPaymentsMenu() {
		this.accountSubMenu_ScheduledPayments.click();
		screenShot.captureScreenShot();
	}

	// Get Page header
	public String getPageHeader() {
		return this.pageHeader.getText();
	}
	
	// Click Advanced button
	public void clickAdvancedBtn() {
		this.advancedBtn.click();
		screenShot.captureScreenShot();
	}
	
	//Verify Member Payment option is present
	public boolean isMemberPaymentClickable() {
		if(memberPaymentLink.isDisplayed())
			return true;
		else
			return false;
		
	}

}
