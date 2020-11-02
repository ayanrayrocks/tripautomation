package utilities;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class writeExel {
	 
     
	WebDriver driver;
	 public void getflightdetailstoexel(WebDriver driver) throws IOException
	 	{
			
			List<WebElement> airlinename= driver.findElements(By.xpath("//div[@class='ow-tuple-container__details ms-grid-row-2']//img"));
			List<WebElement> departuretime=driver.findElements(By.xpath("//div[@class='ms-grid-column-1 ms-grid-row-1']/p"));
			List<WebElement> arrivaltime=driver.findElements(By.xpath("//div[@class='ms-grid-column-2 ms-grid-row-1']/p"));
			List<WebElement> price=driver.findElements(By.xpath("//div[@class='flex flex-column pl-2 flex-bottom']/p"));			
		    List<WebElement> duration=driver.findElements(By.xpath("//div[@class='ms-grid-column-3 ms-grid-row-1']/p"));

							
			   XSSFWorkbook workbook=new XSSFWorkbook();
				  XSSFSheet sheet=workbook.createSheet("TripAutomation");
								
					String FlightData[]=new String[5];
					//Entering the title for worksheet
				       Row first= sheet.createRow(0);
				       Cell Title=first.createCell(0);
				       Title.setCellValue("Flight Name");
				       Cell dt=first.createCell(1);
				       dt.setCellValue("Departure Time");
				       Cell at=first.createCell(2);
				       at.setCellValue("Arrival Time");
				       Cell dura=first.createCell(3);
				       dura.setCellValue("Duration");
				       Cell Price=first.createCell(4);
				       Price.setCellValue("Price");
				       
					
				     /*Getting the details of flight in Worksheet*/
				      for (int i = 0;i <5;i++) 
							{
						
							Row row=sheet.createRow(i+1);

							FlightData[0]= airlinename.get(i).getAttribute("alt");
							FlightData[1]= departuretime.get(i).getText();
							FlightData[2]= arrivaltime.get(i).getText();
							FlightData[3]= duration.get(i).getText();
							FlightData[4]= price.get(i).getText();
							for (int j = 0;j <5;j++) 
							    {
							      Cell cell=row.createCell(j);
							      cell.setCellValue(FlightData[j]);
							
							    }
							}

							FileOutputStream fileOutputStream=new FileOutputStream(new File("TripAutomation.xlsx"));
							   workbook.write(fileOutputStream);
							   
							   workbook.close();
							   System.out.println("Excel file is created");
							   
	 	}
}
