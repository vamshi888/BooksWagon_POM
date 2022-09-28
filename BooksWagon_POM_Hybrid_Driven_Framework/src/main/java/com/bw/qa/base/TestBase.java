package com.bw.qa.base;

import java.io.FileInputStream;   
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.log4testng.Logger;

import com.bw.qa.util.TestUtil;
import com.bw.qa.util.WebEventListener;


public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static Logger log =Logger.getLogger(TestBase.class) ;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase(){
		prop = new Properties();
		FileInputStream ip = null;
		try {
			ip = new FileInputStream(
					"C:\\Users\\hp\\eclipse-workspace\\BooksWagon_POM_Hybrid_Driven_Framework\\src\\main\\java\\com\\bw\\qa\\config\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		public static void initialization() {
			String browserName = prop.getProperty("browser");
			if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\Downloads\\chromedriver_win32\\chromedriver.exe");
			 driver = new ChromeDriver();
			}
			e_driver = new EventFiringWebDriver(driver);
			eventListener = new WebEventListener();
			e_driver.register(eventListener);
			driver = e_driver;
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));
		}}
	