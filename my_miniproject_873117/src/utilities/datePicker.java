package utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class datePicker {

	WebDriver driver;
	
	public void friday(WebDriver driver) {
		driver.findElement(By.id("DepartDate")).clear();
		driver.findElement(By.xpath("//div[contains(@class,'searchForm clearFix')]//div[1]//dl[1]//dd[1]//div[1]//a[1]//i[1]")).click();
        
		
		
		
		LocalDate datea=LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        String selectDate=datea.toString();        
        String date1[]=selectDate.split("-");
        
        int size=date1.length;
        String finalDate=date1[size-1];
        
        String mounth = null;
      
        switch(date1[1]) {
        case "01":
                  mounth = "January 2020";
          break;
        case "02":
        	      mounth = "February 2020";
          break;
        case "03":
    	      mounth = "March 2020";
          break;
        case "04":
    	      mounth = "April 2020";
          break;
        case "05":
    	      mounth = "May 2020";
          break;
        case "06":
    	      mounth = "June 2020";
          break;
        case "07":
    	      mounth = "July 2020";
          break;
        case "08":
    	      mounth = "August 2020";
          break;
        case "09":
    	      mounth = "September 2020";
          break;
        case "10":
    	      mounth = "October 2020";
          break;
        case "11":
    	      mounth = "November 2020";
          break;
        case "12":
    	      mounth = "December 2020";
          break;
        
      }
		
		
	int a=Integer.parseInt(finalDate);
	//a = a+7;
	String newdate = Integer.toString(a); 
		
		while(true) {
			String gg = driver.findElement(By.xpath("//div[contains(@class,'monthBlock first')]//div[contains(@class,'title')]")).getText();
			 if(gg.equals(mounth)) {
				  break;
			 } else
			 {
				 driver.findElement(By.xpath("//a[contains(@class,'nextMonth')]")).click();
			 }
		}
		
		List<WebElement> allDates= driver.findElements(By.xpath("//div[contains(@class,'monthBlock first')]//a[contains(@class,'ui-state-default')]"));
		for(WebElement ele :allDates) {
			String date_text = ele.getText();
						
			if(date_text.equals(newdate)) {
				ele.click();
				break;
			}
		}
	}
	
}
