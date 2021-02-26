package pageObjects;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Pattern;

import Utilities.DriverHandler;
import main.Main;

public class Find {

	public static WebElement Element(String locatorType, String locatorValue) 
			throws JSONException, IOException 
	{
		WebElement element = null;
		switch(Main.type) 
		{
		case Web: 
			element = DriverHandler.driver.findElement(by(locatorType,locatorValue));
			break;
		case Desktop:
			element = DriverHandler.app.findElement(by(locatorType,locatorValue));
			break;
		default:
			System.out.println("Test Case type is not defined");
		}
		return element;	
	}

	public static By by(String locatorType, String locatorValue) 
	{
		switch(locatorType) 
		{
		case "id": 
			return By.id(locatorValue);

		case "xpath":
			return By.xpath(locatorValue);
			
		case "name":
			return By.name(locatorValue);
			
		case "cssSelector":
			return By.cssSelector(locatorValue);
			
		case "partialLinkText":
			return By.partialLinkText(locatorValue);
			default: 
				return null;
			
		}
		
	}

	public static Pattern Pattern(String name) throws JSONException, IOException, ParseException {

		byte[] imageInBytes = null;
		JSONParser parser = new JSONParser();
	    Object obje = parser.parse(new FileReader("./DataSheets/imageRepository.json"));
        JSONArray jsonObject = (JSONArray) obje;

		for(int i=0; i<jsonObject.size(); i++) 
		{
			JSONObject x = (JSONObject) jsonObject.get(i);
			if(x.containsValue(name)) 
			{
				imageInBytes = Base64.decodeBase64(x.get("Image").toString());
				x.get("Image").toString();
				System.out.println(x.get("Image").toString());
			}
		}
		try {
			if (imageInBytes == null)
				return null;

			Pattern pattern = null;
			InputStream in = new ByteArrayInputStream(imageInBytes);
			BufferedImage image = ImageIO.read(in);
			pattern = new Pattern(image);
			return pattern;
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
			return null;
		}
	}

}
