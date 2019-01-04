package com.training.pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class BaseClass {

	public static WebDriver driver,driver1,driver2;
	public static Properties properties;
	public static String baseUrl;
	
	/*public BaseClass(String browser) throws InterruptedException {
		if(browser.equalsIgnoreCase("Firefox")) {
			driver = DriverFactory.getDriver(DriverNames.FIREFOX);
		}else if(browser.equalsIgnoreCase("Chrome")) {
			driver = DriverFactory.getDriver(DriverNames.CHROME);
		}
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}*/
		
	public static void loadProperties() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		baseUrl=properties.getProperty("baseURL");
	}
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static Properties getProperties() {
		return properties;
	}

	/*public void setProperties() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		//baseUrl=properties.getProperty("baseURL");
	}*/

	public static void setDriver(String browser) {
		if(browser.equalsIgnoreCase("Edge")) {
			driver = DriverFactory.getDriver(DriverNames.IE);
		}else if(browser.equalsIgnoreCase("Chrome")) {
			driver = DriverFactory.getDriver(DriverNames.CHROME);
		}else if(browser.equalsIgnoreCase("Firefox")) {
			driver = DriverFactory.getDriver(DriverNames.FIREFOX);
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
}
