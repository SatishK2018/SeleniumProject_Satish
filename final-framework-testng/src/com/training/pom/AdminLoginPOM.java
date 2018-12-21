package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPOM {
	private WebDriver driver; 
	
	public AdminLoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="cyclosUsername")
	private WebElement userName; 
	
	@FindBy(id="cyclosPassword")
	private WebElement password;
	
	@FindBy(xpath="//INPUT[@type='submit']")
	private WebElement loginBtn; 
	
	@FindBy(id="loginDataBar")
	private WebElement getLoggedUser; 
	
	@FindBy(xpath="//SPAN[@class='menuText'][text()='Logout']")
	private WebElement logoutLink;
	
	//Set User name
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	//Set Password
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	//Click Login button
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	//Verify logged user
	public String getLoggedUser() {
		return this.getLoggedUser.getText();
	}
	
	//Click Logout button
	public void clickLogoutLnk() {
		this.logoutLink.click();
	}
	
	
}
