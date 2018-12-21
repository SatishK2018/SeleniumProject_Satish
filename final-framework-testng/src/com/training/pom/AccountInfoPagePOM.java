package com.training.pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
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
	
	@FindBy(xpath="//INPUT[@type='submit']")
	private WebElement search;	
	
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
		
}



