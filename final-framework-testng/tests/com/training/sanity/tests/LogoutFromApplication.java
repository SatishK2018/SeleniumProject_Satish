package com.training.sanity.tests;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.training.pom.BaseClass;
import com.training.pom.LoginPOM;

public class LogoutFromApplication {

	private WebDriver driver;
	private LoginPOM loginPOM;
	//private Properties properties;
	
	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		//driver = DriverFactory.getDriver(DriverNames.CHROME);
		this.driver=BaseClass.driver;
		//this.properties=BaseClass.properties;
		loginPOM = new LoginPOM(driver);
	}

	@Test 
	// Logout from the application
	public void adminLogout() throws InterruptedException {
		loginPOM.clickLogoutLnk();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
