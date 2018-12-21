package com.training.pom;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.training.generics.ScreenShot;

public class ViewLoanPagePOM {
	
	private WebDriver driver; 
	private static Properties properties;
	private ScreenShot screenShot;
	String radioBtn;
	
	public ViewLoanPagePOM(WebDriver driver) throws IOException {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		screenShot = new ScreenShot(driver);
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	
	@FindBy(xpath="(//INPUT[@type='radio'])[1]")
	private WebElement radioBtn_OPEN; 
	
	@FindBy(xpath="(//INPUT[@type='radio'])[2]")
	private WebElement radioBtn_CLOSED; 
	
	//Verify Open radio button is selected
	public void isOpenRadioBtnSelected() {
		radioBtn = this.radioBtn_OPEN.getAttribute("value");
		if(radioBtn_OPEN.equals("CLOSED"))
			this.radioBtn_OPEN.click();
	}
	
	//Click Closed radio button
	public void clickClosedRadioBtn() throws InterruptedException {
		this.radioBtn_CLOSED.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();		
	}
	
	//Click Open radio button
	public void clickOpenRadioBtn() throws InterruptedException {
		this.radioBtn_OPEN.click();
		Thread.sleep(1000);
		screenShot.captureScreenShot();		
	}
	
		
}
