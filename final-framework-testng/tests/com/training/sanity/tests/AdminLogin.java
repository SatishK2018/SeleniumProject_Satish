package com.training.sanity.tests;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.BaseClass;
import com.training.pom.LoginPOM;

public class AdminLogin extends BaseClass {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private ScreenShot screenShot;
	private Properties properties;
	private WebDriverWait wait;
	String browser,adminUser, adminPassword;
	
	/*@org.testng.annotations.Parameters("browser")
	public AdminLogin(String browser) throws IOException, InterruptedException {
		super(browser);
	}*/
	
	/*********
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
	}
	************/
	@Parameters("browser")
	@BeforeTest
	public static void setDriver(String browser) {
		BaseClass.setDriver(browser);
	}
	
	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		BaseClass.loadProperties();
		properties = BaseClass.properties;
		driver = BaseClass.driver;
		loginPOM = new LoginPOM(driver);
		screenShot = new ScreenShot(driver);
		wait = new WebDriverWait(driver,20);
		baseUrl = properties.getProperty("baseURL");
		adminUser = properties.getProperty("admin_User");
		adminPassword = properties.getProperty("admin_Password");
		// Open browser
		driver.get(baseUrl);
		wait.until(ExpectedConditions.titleIs("Bank Application"));
	}

	@Test 
	// Admin Login
	public void adminLogin() throws InterruptedException {
		Thread.sleep(5000);
		loginPOM.sendUserName(adminUser);
		loginPOM.sendPassword(adminPassword);
		loginPOM.clickLoginBtn();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("memberUsername")));
		screenShot.captureScreenShot();
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cyclosUsername")));
		driver.quit();
	}

}
