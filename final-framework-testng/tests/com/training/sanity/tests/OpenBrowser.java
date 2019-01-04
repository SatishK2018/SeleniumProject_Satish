package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class OpenBrowser {
	
	private WebDriver driver;
	private Properties properties;
	private String baseUrl;
	private LoginPOM loginPOM;
	private ScreenShot screenShot;
	String browser,adminUser, adminPassword;
	
		
	@BeforeMethod
	@Parameters ("browser")
	public void setBrowser(String borwser) throws IOException, InterruptedException {
		if(browser.equalsIgnoreCase("Firefox")) 
			driver = DriverFactory.getDriver(DriverNames.FIREFOX);
		else if(browser.equalsIgnoreCase("Chrome")) 
			driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		loginPOM = new LoginPOM(driver);
		screenShot = new ScreenShot(driver);
		baseUrl = properties.getProperty("baseURL");
		adminUser = properties.getProperty("admin_User");
		adminPassword = properties.getProperty("admin_Password");
		// Open browser
		driver.get(baseUrl);
		Thread.sleep(2000);
	}

	@Test 
	// Admin Login
	public void adminLogin() throws InterruptedException {
		loginPOM.sendUserName(adminUser);
		loginPOM.sendPassword(adminPassword);
		loginPOM.clickLoginBtn();
		//Thread.sleep(1000);
		screenShot.captureScreenShot();
	}
	
	/*@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}*/

}
