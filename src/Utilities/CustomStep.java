package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CustomStep {
	public String comments;
	public Boolean status;
	public Boolean isHeader = false;
	public String path;	
	
	public CustomStep() {}
	
	public CustomStep(ActionResult actionResult) {		
		this.comments = "";
		this.status = false;
		this.isHeader = false;
		this.path = null;		
		actionResult.steps.add(this);
	}
	public String Screenshot(WebDriver driver, String name) 
	{
		String fileName = ".\\ScreenShots\\"+name+".png";
		TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
		File screenShot = screenshotDriver.getScreenshotAs(OutputType.FILE);
		 try {
			FileUtils.copyFile((File) screenShot, new File(fileName));
			this.path = fileName;			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		 
		 return fileName;
	}
}
