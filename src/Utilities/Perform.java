package Utilities;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.hotkey.Keys;
import org.sikuli.script.Button;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.App;

import jxl.read.biff.BiffException;
import main.Main;
import pageObjects.Find;


public class Perform {
	
	public static HashMap<String, String> iteration = null;

	public static void Action(Action action, String value, String customActionName) 
	{
		value = IsGlobal(value);
		
		System.out.println("		ACTION: " + action + "<---->VALUE: " + value);
		
		switch (action) {
	
		case CUSTOM:

			ExecuteCustomAction(customActionName, value);
			
		default:
			break;
		}
	}
	
	public static void Action(Action action, WebElement element, String value) throws Exception {
		
		value = IsGlobal(value);
		
		boolean desk = false;
		Screen s= null;
		if(Main.type.equals(TestCaseType.Desktop)) 
		{
			s = new Screen();
			desk = true;
		}
		
		Actions actions = new Actions(DriverHandler.app);
		
		System.out.println("		ACTION: " + action + "<---->VALUE: " + value);
		
		DriverHandler.varWait.until(ExpectedConditions.elementToBeClickable(element));

		switch (action) {
		case Type:
			element.sendKeys(value);
			break;	
		case Click:
			if (element != null && value.toString().equalsIgnoreCase("ifexist")) {
				element.click();
			} else {
				element.click();
			}
			break;
		case DeselectByIndex:
			SelectElement elementSelectIn = new SelectElement(element);
			elementSelectIn.DeselectByIndex(Integer.parseInt(value));
			break;
		case DeselectByText:
			SelectElement elementSelect = new SelectElement(element);
			elementSelect.DeselectByText(value);
		case DeselectByValue:
			SelectElement elementSelectVal = new SelectElement(element);
			elementSelectVal.DeselectByValue(value);
			break;
		case DragAndDrop:
			if(desk) 
			{
				String[] coord = dropInAt(value);
				actions.moveToElement(element).perform();
				s.mouseDown(Button.LEFT);
				actions.moveByOffset(Integer.parseInt(coord[0]), Integer.parseInt(coord[1])).perform();
				s.mouseUp(Button.LEFT);
			}else 
			{
				String Offset = value; 
				String[] xyOffset = Offset.split(",");
				String sxOffset = xyOffset[0];
				int xOffset = Integer.parseInt(sxOffset);
				String syOffset = xyOffset[1];
				int yOffset = Integer.parseInt(syOffset);
				Actions _dragDrop = new Actions(DriverHandler.driver);
				org.openqa.selenium.interactions.Action DragDrop = _dragDrop.dragAndDropBy(element, xOffset, yOffset).build();
				DragDrop.perform();
			}
			break;
		case DragAndDropToObject:
			if(desk) 
			{
				String[] targetElem = dropInAt(value);
				String slocatorType = targetElem[0]; // LocatorType
				String slocator = targetElem[1]; // LocatorValue
				WebElement elementTarget = Find.Element(slocator, slocatorType);
				actions.moveToElement(element).perform();
				s.mouseDown(Button.LEFT);
				actions.moveToElement(elementTarget).perform();
				s.mouseUp(Button.LEFT);
			}else 
			{
				//ToDo:
			}
			break;
		case MouseOverAndClick:
			//ToDo:
			break;
		case TypeRandomValue:
			String randomValue = generateRandomValue(value);
			element.sendKeys(randomValue);
			break;
		case SelectByIndex:
			if(desk) 
			{
				Select dropDown2 = new Select(element);
				dropDown2.selectByIndex(Integer.parseInt(value));
			}else 
			{
				Select dropDown2 = new Select(element);
				dropDown2.selectByIndex(Integer.parseInt(value));
			}
			break;
		case SelectByText:
			Select dropDown = new Select(element);
			String selected2 = dropDown.getFirstSelectedOption().getText();
			if (!selected2.equals(value)) {
				List<WebElement> Options2 = dropDown.getOptions();
				for (WebElement option : Options2) {
					if (option.getText().equals(value)) {
						option.click(); 
						break;
					}
				}
			}

			break;
		case SelectByValue:
			Select dropDown3 = new Select(element);
			dropDown3.selectByValue(value);
			break;
		case TypeAction:
			Actions _type = new Actions(DriverHandler.driver);
			org.openqa.selenium.interactions.Action Type = _type.sendKeys(element, value).build();
			Type.perform();
			break;
		case TypeJS:
			//ToDo:
		case TypeSecure:	
			element.sendKeys(value == null ? "" : value);
			break;
		case GetProperty:
			//ToDo:
			break;
		case VerifyText:
			//ToDo:
			break;
		case VerifyProperty:
			//ToDo:
			break;
		case VerifyIfExists:
			//ToDo:
			break;
		case WaitForCondition:
			WebDriverWait waitVar = new WebDriverWait(DriverHandler.driver, (long) 30);
			if (value.contains(":")) {
				String Condition = value;
				String[] waitCond = Condition.split(":");
				String sCondition = waitCond[0];
				String sParam = waitCond[1];
				switch (sCondition.toLowerCase()) {
				case "frameavailableandswitch":
					waitVar.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(sParam));

					break;
				case "titlecontains":
					waitVar.until(ExpectedConditions.titleContains(sParam));
					break;
				case "titleis":
					waitVar.until(ExpectedConditions.titleIs(sParam));
					break;
				case "urlis":
					waitVar.until(ExpectedConditions.urlToBe(sParam));
					break;
				case "urlcontains":
					waitVar.until(ExpectedConditions.urlContains(sParam));
					break;
				}
			} else {
				switch (value.toLowerCase()) {
				case "elementtobeclickable":
					waitVar.until(ExpectedConditions.elementToBeClickable(element));
					break;
				case "elementtobeselected":
					waitVar.until(ExpectedConditions.elementToBeSelected(element));
					break;
				case "invisibilityofelement":
					waitVar.until(ExpectedConditions
							.invisibilityOfElementLocated((By) element));
					break;
				case "presenceofallelements":
					waitVar.until(ExpectedConditions
							.presenceOfAllElementsLocatedBy((By) element));
					break;
				case "texttobepresentinelement":
					waitVar.until(ExpectedConditions
							.textToBePresentInElement(element, value));
					break;
				case "visibilityofallelements":
					waitVar.until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy((By) element));
					break;
				case "elementisvisible":
					waitVar.until(ExpectedConditions
							.visibilityOfElementLocated((By) element));
					break;

				default:
					System.out.println("STEP NOT EXECUTED: value is needed");
					break;
				}
			}
			break;
		case SwitchToFrame:
			//ToDo:
			break;
			//WINODWS ACTIONS
		case WIN_SwitchToActiveElement:
			DriverHandler.app.switchTo().activeElement();
			break;
		default:
			break;
		}

	}

	public static void Action(Action action, String value) throws Exception {

		value = IsGlobal(value);
		boolean desk = false;
		Screen s= null;
		if(Main.type.equals(TestCaseType.Desktop)) 
		{
			s = new Screen();
			desk = true;
		}
		
		System.out.println("		ACTION: " + action + "<---->VALUE: " + value);
		
		switch (action) {
		
		case AlertAccept:
				DriverHandler.driver.switchTo().alert().accept();
			break;
		case AlertAcceptIfExist:
				DriverHandler.driver.switchTo().alert().accept();
			break;
		case AlertDissmis:
				DriverHandler.driver.switchTo().alert().dismiss();
			break;
		case AlertGetText:
			String GetText = DriverHandler.driver.switchTo().alert().getText();
			if (!GetText.equals(value)) {
				System.out.println("Expected:"  + value +  "Found:"  + GetText);
			}
			break;
		case AlertSetText:
				DriverHandler.driver.switchTo().alert().sendKeys(value);
			break;
		case ClickAndHold:
				Actions _clkAndHld = new Actions(DriverHandler.driver);
				org.openqa.selenium.interactions.Action ClickAndHold = _clkAndHld.clickAndHold().build();
				ClickAndHold.perform();
			break;
		case Close:
				DriverHandler.driver.close();
			break;
		case Quit:
			if(desk) 
			{
				App.close(value);
				System.out.println("Closed Succesfully");
			}else 
			{
				DriverHandler.driver.quit();
			}
			
		break;
		case DoubleClick:
			if(desk) 
			{
				s.mouseDown(Button.LEFT);
				s.mouseUp(Button.LEFT);
				s.mouseDown(Button.LEFT);
				s.mouseUp(Button.LEFT);
			}
				Actions _dblClick = new Actions(DriverHandler.driver);
				org.openqa.selenium.interactions.Action DoubleClick = _dblClick.doubleClick().build();
				DoubleClick.perform();
			break;
		case GoBack:
				DriverHandler.driver.navigate().back();
			break;
		case GoForward:
				DriverHandler.driver.navigate().forward();
			break;
		case KeyDown:
			if(desk) 
			{
				s.keyDown(Key(value));
			}else 
			{
				Actions _keyDwn = new Actions(DriverHandler.driver);
				org.openqa.selenium.interactions.Action KeyDown = _keyDwn.keyDown(Press.Key(value)).build();
				KeyDown.perform();
			}
			break;

		case KeyUp:
			if(desk) 
			{
				s.keyUp(Key(value));
			}else
			{
				Actions _keyUp = new Actions(DriverHandler.driver);
				org.openqa.selenium.interactions.Action KeyUp = _keyUp.keyUp(Press.Key(value)).build();
				KeyUp.perform();
			}
			break;
		case Navigate:
			if(desk) 
			{
				value = (value.startsWith("\"") && value.endsWith("\"")) ? value.trim() : "\"" + value + "\"";
				App app = new App(value);	
				app.open(2);

				if (app.isValid()) {
					app.focus();
				} else
					throw new Exception("Invalid app");
			}else
			{
				if (!value.contains("http://") && !value.contains("https://")
						&& !value.contains("file:///")) {
					value = "http://" + value;
					DriverHandler.driver.navigate().to(value);
				} else {
					DriverHandler.driver.navigate().to(value);
				}
			}
			break;

		case Refresh:
				DriverHandler.driver.navigate().refresh();
			break;
		case Release:
				Actions _release = new Actions(DriverHandler.driver);
				org.openqa.selenium.interactions.Action Release = _release.release().build();
				Release.perform();
			break;
		case RightClick:
			if(desk) 
			{
				s.mouseDown(Button.RIGHT);
				s.mouseUp(Button.RIGHT);
			}else 
			{
				Actions _rghtClick = new Actions(DriverHandler.driver);
				org.openqa.selenium.interactions.Action RightClick = _rghtClick.contextClick().build();
				RightClick.perform();
			}
			
			break;
		case ResizeWindow:
			String[] arry;
			Dimension d = null;
			if (value.contains(",")) {
				arry = value.split(",");
				if (arry.length > 0) {
					int w = Integer.parseInt(arry[0]);
					int h = Integer.parseInt(arry[1]);
					d = new Dimension(w, h);
				}
			} else {
				if (value.contentEquals("Galaxy S5"))
					d = new Dimension(360, 640);

				else if (value.contentEquals("Pixel 2"))
					d = new Dimension(411, 731);

				else if (value.contentEquals("Pixel 2 XL"))
					d = new Dimension(411, 823);

				else if (value.contentEquals("iPhone 5/SE"))
					d = new Dimension(320, 7568);

				else if (value.contentEquals("iPhone 6/7/8"))
					d = new Dimension(375, 667);

				else if (value.contentEquals("iPhone 6/7/8 Plus"))
					d = new Dimension(414, 736);

				else if (value.contentEquals("iPhone X"))
					d = new Dimension(375, 812);

				else if (value.contentEquals("iPad"))
					d = new Dimension(768, 1024);

				else if (value.contentEquals("iPad Pro"))
					d = new Dimension(768, 1024);

				else
					d = new Dimension(360, 640);
			}

			DriverHandler.driver.manage().window().setSize(d);
			break;
		case Scroll:
			JavascriptExecutor jse = (JavascriptExecutor) DriverHandler.driver;
			jse.executeScript("window.scrollBy(" + value + ")", "");
			break;
		case SwitchToWindow:
			int i = 0;
			String currWin = "";
			currWin = DriverHandler.driver.getWindowHandle();
			boolean titleFound = false;
			Set<String> handles = DriverHandler.driver.getWindowHandles(); // get all window handles
			String windowChild;
			handles.remove(currWin);

			for (String winHandle : handles) {
				if (winHandle != currWin) {
					windowChild = winHandle; // Storing handle of second window handle
					DriverHandler.driver.switchTo().window(windowChild);
					if (DriverHandler.driver.getTitle().startsWith(value)) {
						titleFound = true;
						break;
					}
					if (i == Integer.parseInt(value)) {
						titleFound = true;
						break;
					}
				}

				i++;
			}
			if (!titleFound) {
				DriverHandler.driver.switchTo().window(currWin);
			}
			break;
		case SwitchToDefaultContent:
			DriverHandler.driver.switchTo().defaultContent();
			break;
			
		case Wait:
			if(desk) 
			{
				Thread.sleep(Long.parseLong(value) * 1000);
			}else 
			{
				DriverHandler.driver.manage().timeouts().implicitlyWait(Integer.parseInt(value), TimeUnit.SECONDS);	
			}
			break;
		case WaitForPageToLoad:
			DriverHandler.driver.manage().timeouts().pageLoadTimeout(Long.parseLong(value), TimeUnit.SECONDS);
			break;
		case WaitForPopUp:
			WebDriverWait waitVar = new WebDriverWait(DriverHandler.driver, Long.parseLong(value));
			waitVar.until(ExpectedConditions.alertIsPresent());
			break;
		case WindowFocus:
			if (value.toLowerCase().equals("child")) {
				DriverHandler.driver.switchTo().window(DriverHandler.driver.getWindowHandle());
			} else if (value.toLowerCase().equals("main")) {
				DriverHandler.driver.switchTo().window(DriverHandler.driver.getWindowHandle());
			} else if (!value.equals(null)) {
				DriverHandler.driver.switchTo().window(value);
			} 
			break;
		case RobotKeyboard:
			//
			break;
		case SecureRobotKeyboard:
			//
			break;
			
		//WINDOWS ACTIONS
		case WIN_Wait:
			if (tryParseIntBol(value)) {
				Thread.sleep(Integer.parseInt(value));
			} else {
				System.out.println("Value must be an Integer");
			}	
			break;
		case WIN_Click:
			String locator2;
			String locatorType2;
			if (value.startsWith("<") && value.endsWith(">")) {
					value = value.substring(1, value.length() - 1);
					String[] identifier = value.split(":");
					locatorType2 = identifier[0].toLowerCase();
					locator2 = identifier[1];
					List<WebElement> elements = getElementList(locator2, locatorType2);
					if (!elements.isEmpty()) {
						for (WebElement element : elements) {
							//if (element.getAttribute(locatorType.toString()).equalsIgnoreCase(locator)) {
								element.click();
							//}
						}
					}
			}
			break;
		default:
			break;
		}

	}

	public static void Action(Action action, Pattern pattern) throws FindFailed 
	{
		System.out.println("		ACTION: " + action);
		Screen s = new Screen();
		
		switch (action) {
			case Clear:
				s.type(pattern, "a", Keys.CTRL);
				s.type(Keys.BACKSPACE);
				break;
			case Click:
				s.click(pattern);
				break;
			case Drag:
				s.drag(pattern);
				break;
			case Drop:
				s.dropAt(pattern);
				break;
			case Highlight:
				if (s.exists(pattern) != null) {
					Match m = s.find(pattern);
					m.highlight(1, "red");
				} else {
					System.out.println("Unable to find pattern in screen");
				}
			case MouseOver:
					s.hover(pattern);
				break;
			case RightClickOnObject:
					s.rightClick(pattern);
				break;
		}
	}
	
	public static void Action(Action action, Pattern pattern, String value) throws FindFailed 
	{
		System.out.println("		ACTION: " + action);
		Screen s = new Screen();
		
		switch (action) 
		{
		case SelectByIndex:
			s.click(pattern);
			int i = 1;
			while (i <= Integer.parseInt(value)) {
				s.type(Keys.DOWN);
				i++;
			}
			s.type(Keys.ENTER);
			break;
		case Type:
			if (pattern == null) {
				s.type(value);
			} else {
				s.type(pattern, value);
			}
			break;
		case TypeRandomValue:
			String randomValue = generateRandomValue(value);
			s.type(pattern, randomValue);
			break;
		case TypeSecure:
				s.type(pattern, (value == null) ? "" : value);
			break;
		case VerifyIfExists:
			if (s.exists(pattern) != null) {
				Match m = s.find(pattern);
				m.highlight(1, "red");
			} else {
				System.out.println("Unable to find pattern in screen");
			}
			break;
		case VerifyText:
			//ToDo:
			break;
		default:
			System.out.println("Action not yet implemented");
		}
	}
	
	public static void Action(Action action, WebElement element) {
		
		System.out.println("		ACTION: " + action);
		
		boolean desk = false;
		Screen s= null;
		if(Main.type.equals(TestCaseType.Desktop)) 
		{
			s = new Screen();
			desk = true;
		}
		
		switch (action) {
		case Click:
			element.click();
			break;
		case Clear:
				element.clear();	
			break;
		case Highlight:
			//cleanHighlight();
			//HighlightFunction();
			break;
		case ClickAndHoldOnObject:
			if(desk) 
			{
				Actions _clkAndHldObj = new Actions(DriverHandler.app);
				_clkAndHldObj.moveToElement(element).perform();
				s.mouseDown(Button.LEFT);
			}else 
			{
				Actions _clkAndHldObj = new Actions(DriverHandler.driver);
				org.openqa.selenium.interactions.Action ClickAndHoldObj = _clkAndHldObj.clickAndHold(element).build();
				ClickAndHoldObj.perform();
			}
			break;
		case DeselectAll:
			SelectElement elementSelect = new SelectElement(element);
			elementSelect.DeselectAll();
			break;
		case DoubleClickOnObject:
			if(desk) 
			{
				element.click();
				element.click();
			}else 
			{
				Actions _dblClickObj = new Actions(DriverHandler.driver);
				org.openqa.selenium.interactions.Action DoubleClickObj = _dblClickObj.doubleClick(element).build();
				DoubleClickObj.perform();
			}
			break;
		case MouseOver:
			if(desk) 
			{
				Actions _mouseOvr = new Actions(DriverHandler.app);
				_mouseOvr.moveToElement(element).perform();
			}else 
			{
				Actions _mouseOvr = new Actions(DriverHandler.driver);
				org.openqa.selenium.interactions.Action MouseOver = _mouseOvr.moveToElement(element).build();
				MouseOver.perform();
			}
			break;
		case ObjectDisplayed:
			//ToDo:
			break;
		case ObjectEnabled:
			//ToDo:
			break;
		case ObjectSelected:
			//ToDo:
			break;
		case ReleaseOnObject:
			Actions _releaseObj = new Actions(DriverHandler.driver);
			org.openqa.selenium.interactions.Action ReleaseObj = _releaseObj.release(element).build();
			ReleaseObj.perform();
			break;
		case RightClickOnObject:
			if(desk) 
			{
				Actions _rghtClickObj = new Actions(DriverHandler.app);
				_rghtClickObj.contextClick(element).perform();
			}else 
			{
				Actions _rghtClickObj = new Actions(DriverHandler.driver);
				org.openqa.selenium.interactions.Action RightClickObj = _rghtClickObj.contextClick(element).build();
				RightClickObj.perform();
			}
			break;
		case Submit:
			element.submit();
			break;
		case Uncheck:
			if (element.isSelected()) {
				element.click();
			}
			break;
		case CheckOption:
			//
			break;
		case Check:
			element.click();
			break;
		default:
			break;

		}

	}

	public static void Action(Action action) 
	{
		switch (action) {
			case WIN_SwitchToActiveElement:
				DriverHandler.app.switchTo().activeElement();
			break;
		default:
			break;
		}
	}
	
	public static String IsGlobal(String value) {
		if(value.startsWith("GDT_")) {		
			try {
				value = ReadFile.GetparameterValue(ReadFile.Excel("test.xls"), value);
			} catch (BiffException e) {
				System.out.println(e.getLocalizedMessage());
			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
			}
		} else if (value.startsWith("DT_") && iteration != null) {
			try {			
				value = ReadFile.getLocal(iteration, value);
			}
			catch (Exception ex) {			
			}
		}

		return value;
	}
	
	public static void ExecuteCustomAction(String customActionName, String value) 
	{
		Class[] params;
		try {
			Class<?> custom = Class.forName("customAction."+customActionName);
			Object instance = custom.newInstance();
            
            Method ExecuteMethod = custom.getMethods()[0];
            java.lang.reflect.Parameter[] parameters = ExecuteMethod.getParameters();  
            
            if(parameters.length==2){				
				params = new Class[2];									
				params[0] = parameters[0].getType();
				
				Class<?> type  = parameters[1].getType();

				if(value.contains(",") && type.isArray()) {
					params[1] = String[].class;
				}else 
				{
					params[1] = String.class;
				}
				
			}else{
				
				params = new Class[1];
				params[0] = WebDriver.class;
			}
            
            
            Method thisMethod = custom.getDeclaredMethod("Execute", params);
            
            thisMethod.invoke(instance, DriverHandler.driver, value);
            
            
            
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean tryParseIntBol(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static List<WebElement> getElementList(String locator1, String LocatorType1) {
		List<WebElement> elements = null;
		switch (LocatorType1.toLowerCase()) {
		case "xpath":
			try {
				elements = DriverHandler.app.findElementsByXPath(locator1);
			} catch (Exception e) {
				elements = null;
			}
			break;
		case "name":
			try {
				elements = DriverHandler.app.findElementsByName(locator1);
			} catch (Exception e) {
				elements = null;
			}
			break;
		case "classname":
			try {
				elements = DriverHandler.app.findElementsByClassName(locator1);
			} catch (Exception e) {
				elements = null;
			}
			break;
		}

		return elements;
	}
	
	public static String Key(String key) {
		key = key.toUpperCase();

		switch (key) {
		case "ALT":

			return Keys.ALT;

		case "ADD":
			return Keys.ADD;

		case "ARROW_DOWN":
			return Keys.DOWN;

		case "ARROW_LEFT":
			return Keys.LEFT;

		case "ARROW_RIGHT":
			return Keys.RIGHT;

		case "ARROW_UP":
			return Keys.UP;

		case "BACK_SPACE":
			return Keys.BACKSPACE;

		case "COMMAND":
			return Keys.CMD;

		case "CONTROL":
			return Keys.CTRL;

		case "DECIMAL":
			return Keys.DECIMAL;

		case "DELETE":
			return Keys.DELETE;

		case "DIVIDE":
			return Keys.DIVIDE;

		case "DOWN":
			return Keys.DOWN;

		case "END":
			return Keys.END;

		case "ENTER":
			return Keys.ENTER;

		case "ESCAPE":
			return Keys.ESC;

		case "F1":
			return Keys.F1;

		case "F10":
			return Keys.F10;

		case "F11":
			return Keys.F11;

		case "F12":
			return Keys.F12;

		case "F2":
			return Keys.F2;

		case "F3":
			return Keys.F3;

		case "F4":
			return Keys.F4;

		case "F5":
			return Keys.F5;

		case "F6":
			return Keys.F6;

		case "F7":
			return Keys.F7;

		case "F8":
			return Keys.F8;

		case "F9":
			return Keys.F9;

		case "HOME":
			return Keys.HOME;

		case "INSERT":
			return Keys.INSERT;

		case "LEFT":
			return Keys.LEFT;

		case "META":
			return Keys.META;

		case "MULTIPLY":
			return Keys.MULTIPLY;

		case "NUMPAD0":
			return Keys.NUM0;

		case "NUMPAD1":
			return Keys.NUM1;

		case "NUMPAD2":
			return Keys.NUM2;

		case "NUMPAD3":
			return Keys.NUM3;

		case "NUMPAD4":
			return Keys.NUM4;

		case "NUMPAD5":
			return Keys.NUM5;

		case "NUMPAD6":
			return Keys.NUM6;

		case "NUMPAD7":
			return Keys.NUM7;

		case "NUMPAD8":
			return Keys.NUM8;

		case "NUMPAD9":
			return Keys.NUM9;

		case "PAGE_DOWN":
			return Keys.PAGE_DOWN;

		case "PAGE_UP":
			return Keys.PAGE_UP;

		case "PAUSE":
			return Keys.PAUSE;

		case "RIGHT":
			return Keys.RIGHT;

		case "SEPARATOR":
			return Keys.SEPARATOR;

		case "SHIFT":
			return Keys.SHIFT;

		case "SPACE":
			return Keys.SPACE;

		case "SUBTRACT":
			return Keys.MINUS;

		case "TAB":
			return Keys.TAB;

		case "UP":
			return Keys.UP;

		default:
			return null;
		}
	}

	public static String[] dropInAt(String value) throws Exception {
			String[] xyOffset = value.split(",");
			if (xyOffset.length < 2)
				throw new Exception("Please verify your input value. (x,y)");
			String xOffset = xyOffset[0];
			String yOffset = xyOffset[1];
			return new String[] { xOffset, yOffset };
	}
	
	public static String generateRandomValue(String value) {
		String randomValue = "";
		try {
			String[] tokens = value.split(":", 2);
			String typeOfString = tokens[0].trim();
			String longOfString = tokens[1].trim();
			switch (typeOfString.toLowerCase()) {
			case "alphabetic":
				randomValue = RandomStringUtils.randomAlphabetic(Integer.parseInt(longOfString));
				break;
			case "alphanumeric":
				randomValue = RandomStringUtils.randomAlphanumeric(Integer.parseInt(longOfString));
				break;
			case "numeric":
				String chars = "1234567890";
				randomValue = RandomStringUtils.random(Integer.parseInt(longOfString), chars);
				break;
			default:
				if (longOfString.contains("-")) {
					String[] tokensRange = longOfString.split("-", 2);
					if (tokensRange.length == 2) {
						int minVal = Integer.parseInt(tokensRange[0].trim());
						int maxVal = Integer.parseInt(tokensRange[1].trim());
						Random ran = new Random();
						int integerRandom = ThreadLocalRandom.current().nextInt(minVal, maxVal);
						randomValue = Integer.toString(integerRandom);
					} else {
						randomValue = longOfString;
					}
				} else {
					String alphabet = typeOfString;
					randomValue = RandomStringUtils.random(Integer.parseInt(longOfString), alphabet);
				}
				break;
			}
		} catch (Exception ex) {
			System.out.println("Failed generating random value, return empty string instead.");
			System.out.println(ex.getMessage());
		}
		return randomValue;
	}

}
