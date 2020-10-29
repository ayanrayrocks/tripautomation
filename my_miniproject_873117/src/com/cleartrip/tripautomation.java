package com.cleartrip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;


import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

import utilities.datePicker;
import utilities.getURL;
import utilities.quitBrowser;
import utilities.writeExel;


public class tripautomation {
	
	 public static WebDriver driver;
	 
	 
	 
	 // Selecting the browser driver
	 public static WebDriver browserNameSelect(String name) {
		  
	 try {
		 if(name.equalsIgnoreCase("Chrome")) {
			 ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
			 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
                driver = new ChromeDriver(options);
		 } else {
			 FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--disable-notifications");
			 System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver(options);
			 
		 }
		 
	 } catch(Exception e) { 
		 
	 }
		    driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
			
			return driver;
	 }
	 
	 
	 
	 
	 //Use property file to set the type of browser type
	 public static String readPropertiesFile(String value) throws IOException {
		 Properties prop = new Properties();
		 String bname=null;
		 try {
			InputStream input = new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\config.properties");
		     prop.load(input);
		     bname=prop.getProperty(value);
		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return bname;
		 
	 }
	 
	 //set the value in the browser
	 public void setTravelDetails(WebDriver driver) throws InterruptedException, IOException {
 
		 // click on any element to remove message from cookies
		 driver.findElement(By.className("subTitle")).click();
		 Thread.sleep(2000);
		 
		 //Enter Delhi in From place from properties file
		 String toplace = readPropertiesFile("Toplace");
		 driver.findElement(By.id("FromTag")).sendKeys(toplace);
         Thread.sleep(5000);
         driver.findElement(By.id("FromTag")).sendKeys(Keys.ENTER);
		 
		 //Enter Kolkata in To place place from properties file
         String fromplace = readPropertiesFile("Fromplace");
		 driver.findElement(By.id("ToTag")).sendKeys(fromplace);
         Thread.sleep(5000);
         driver.findElement(By.id("ToTag")).sendKeys(Keys.ENTER);
		 
		 
	 }
	 
 
	//selecting the adult
	 public void adultSelect() throws InterruptedException {
		 Select ad1 = new Select(driver.findElement(By.id("Adults")));
	        ad1.selectByValue("2");
         Thread.sleep(4000);
			
	 }
	 
	//click on search
	 public void searchbtn() throws InterruptedException {
		 driver.findElement(By.id("SearchBtn")).click();
         Thread.sleep(8000);
	 }
	 
	 // shorting the price from high to low
	 public void shortElement() throws InterruptedException {
		 driver.findElement(By.xpath("//span[@class='fs-inherit c-inherit mr-1 fw-500']")).click();  
         Thread.sleep(4000);
	 }
	 
 
 
	 
	 public static void main(String[] args) throws InterruptedException, IOException {
		
		 tripautomation trip = new tripautomation();
		
		 String bname = readPropertiesFile("browser");
		 WebDriver driver = browserNameSelect(bname);
		 
		 //Navigate to cleartrip website
		 getURL url = new getURL();
         url.geturl(driver);
		 
         trip.setTravelDetails(driver);
		 
		 //Picking date as next Friday
		 datePicker datel = new datePicker();
		 datel.friday(driver);
		  
		 trip.adultSelect();
		 trip.searchbtn();
		 trip.shortElement();

		 //creating exel file and store it in exel
		 writeExel exel = new writeExel();
		 exel.getflightdetailstoexel(driver);

		 //Quiting the browser
		 quitBrowser quit = new quitBrowser();
         quit.quitthebrowser(driver);
		 
         
		 
	}

}
