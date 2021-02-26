package Utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import main.Main;


public class DriverHandler {

	public static WebDriver driver;
	public static WebDriverWait varWait;
	public static WiniumDriver app;
	
	public static void Start() throws MalformedURLException 
	{
		switch(Main.type) {
		case Web:
			System.setProperty("webdriver.chrome.driver","C:\\Temp\\JavaCABuild\\Drivers\\chromedriver.exe");		
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-extensions");
			driver = new ChromeDriver();
			varWait = new WebDriverWait(driver, (long) 30);
			break;
		case Desktop:
			DesktopOptions opt = new DesktopOptions();
			opt.setApplicationPath("C:\\Program Files\\Internet Explorer\\iexplore.exe");
			opt.setDebugConnectToRunningApp(true);
			app = new WiniumDriver(new URL("http://localhost:9999"), opt);
			break;
		default:
			System.out.println("Test Case type is not defined");
		}
	}
	
	public static void Finish() 
	{
		switch(Main.type) {
			case Web:
				driver.quit();
				break;
			case Desktop:
				app.close();
			default:
				System.out.println("Test Case type is not defined");
		}
	}
	

}
