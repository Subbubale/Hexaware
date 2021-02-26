package pageObjects;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import Utilities.DriverHandler;

public class Expedia  {

private static WebElement element = null;
public static WebDriver driver = DriverHandler.driver;

public static WebElement And_Click_On_Continue_Button() {

element = driver.findElement(By.xpath("//span[contains(text(),'Continue Booking')]"));

return element;

}
public static WebElement And_I_Click_Complete_Booking() {

element = driver.findElement(By.cssSelector("button[id='complete-booking']"));

return element;

}
public static WebElement And_I_Click_no_thanks() {

element = driver.findElement(By.cssSelector("span[class='no-thanks-content']"));

return element;

}
public static WebElement And_I_Click_on_Submit_Button() {

/*element = driver.findElement(By.description("<button class='uitk-button uitk-button-small uitk-menu-icon-trigger uitk-menu-trigger all-x-padding-three' aria-expanded='false' type='button'><svg xmlns='http://www.w3.org/2000/svg' class='uitk-icon uitk-button-icon uitk-icon-medium' role='img' aria-labelledby='account-menu-icon-title' viewbox='0 0 24 24' width='100%' height='100%' xmlns:ns8='' ns8:xmlns:xlink='http://www.w3.org/1999/xlink' xmlns='http://www.w3.org/2000/svg'><title id='account-menu-icon-title'>Account</title><svg><path clip-rule='evenodd' fill-rule='evenodd' d='M 12 2 a 10 10 0 1 0 0 20 a 10 10 0 0 0 0 -20 Z m 0 3 a 3 3 0 1 1 0 6 a 3 3 0 0 1 0 -6 Z M 6 15.98 a 7.2 7.2 0 0 0 12 0 c -0.03 -1.99 -4.01 -3.08 -6 -3.08 c -2 0 -5.97 1.09 -6 3.08 Z'></path></svg></svg></button>"));*/

return element;

}
public static WebElement And_i_click_select_button() {

/*element = driver.findElement(By.description("<button class='uitk-button uitk-button-small uitk-menu-icon-trigger uitk-menu-trigger all-x-padding-three' aria-expanded='false' type='button'><svg xmlns='http://www.w3.org/2000/svg' class='uitk-icon uitk-button-icon uitk-icon-medium' role='img' aria-labelledby='account-menu-icon-title' viewbox='0 0 24 24' width='100%' height='100%' xmlns:ns8='' ns8:xmlns:xlink='http://www.w3.org/1999/xlink' xmlns='http://www.w3.org/2000/svg'><title id='account-menu-icon-title'>Account</title><svg><path clip-rule='evenodd' fill-rule='evenodd' d='M 12 2 a 10 10 0 1 0 0 20 a 10 10 0 0 0 0 -20 Z m 0 3 a 3 3 0 1 1 0 6 a 3 3 0 0 1 0 -6 Z M 6 15.98 a 7.2 7.2 0 0 0 12 0 c -0.03 -1.99 -4.01 -3.08 -6 -3.08 c -2 0 -5.97 1.09 -6 3.08 Z'></path></svg></svg></button>"));*/

return element;

}
public static WebElement And_I_Enter_Security_Code() {

/*element = driver.findElement(By.description("<span class='uitk-tab-text'>Packages</span>"));*/

return element;

}
public static WebElement And_I_Enter_Surname() {

/*element = driver.findElement(By.description("<input name='d1' class='uitk-field-input is-hidden' id='d1' aria-invalid='false' aria-required='false' type='text' placeholder='Check-in' value='2021-03-27' data-stid='input-date'>"));*/

return element;

}
public static WebElement And_I_Select_Expiry_Month() {

element = driver.findElement(By.cssSelector("select[name='expiration_month']"));

return element;

}
public static WebElement And_I_Select_Expiry_Year() {

element = driver.findElement(By.cssSelector("select[name='expiration_year']"));

return element;

}
public static WebElement And_I_Type_Billing_Address() {

element = driver.findElement(By.cssSelector("input[name='street']"));

return element;

}
public static WebElement And_I_Type_City() {

element = driver.findElement(By.cssSelector("input[name='city']"));

return element;

}
public static WebElement And_I_Type_Departing_Date() {

element = driver.findElement(By.cssSelector("input[id='flight-departing-single-hp-flight']"));

return element;

}
public static WebElement And_I_Type_Email_Address() {

element = driver.findElement(By.cssSelector("input[name='email']"));

return element;

}
public static WebElement And_I_Type_FirstName() {

element = driver.findElement(By.cssSelector("input[name='tripPreferencesRequest.airTripPreferencesRequest.travelerPreferences[0].firstName']"));

return element;

}
public static WebElement And_I_Type_in_Flying_to() {

/*element = driver.findElement(By.description("<button class='uitk-button uitk-button-large uitk-button-fullWidth uitk-button-has-text uitk-button-primary' type='submit' data-testid='submit-button'>Search</button>"));*/

return element;

}
public static WebElement And_I_Type_Password() {

element = driver.findElement(By.cssSelector("input[name='password']"));

return element;

}
public static WebElement And_I_Type_Phone_Number() {

element = driver.findElement(By.cssSelector("input[id='phone-number[0]']"));

return element;

}
public static WebElement And_I_Type_Postcode() {

element = driver.findElement(By.cssSelector("input[name='zipcode']"));

return element;

}
public static WebElement I_Enter_Card_Number() {

element = driver.findElement(By.cssSelector("input[name='card_number']"));

return element;

}
public static WebElement I_Enter_Debit_Card_Holder_Name() {

element = driver.findElement(By.cssSelector("input[name='cardholder_name']"));

return element;

}
public static WebElement type_flying_from_destination() {

element = driver.findElement(By.cssSelector("input[class='clear-btn-input gcw-storeable text gcw-origin gcw-required gcw-distinct-locations ']"));

return element;

}
public static WebElement When_I_Click_flight_tab() {

/*element = driver.findElement(By.description("<button class='on' data-lob='flight' data-section-id='#section-flight-tab-hp' id='tab-flight-tab-hp' type='button'>"));*/

return element;

}
public static WebElement When_I_Click_oneway() {

/*element = driver.findElement(By.description("<span class='uitk-field-label' aria-hidden='true'>Going to</span>"));*/

return element;

}
public static WebElement When_I_Click_Side() {

/*element = driver.findElement(By.description("<li class='uitk-tab uitk-tab-icon-text ' role='presentation'><a class='uitk-tab-anchor' role='tab' aria-selected='false' aria-controls='wizard-car-pwa' draggable='false' href='?pwaLob=wizard-car-pwa' data-toggle='tab'><svg xmlns='http://www.w3.org/2000/svg' class='uitk-icon uitk-icon-medium' aria-hidden='true' viewbox='0 0 24 24' width='100%' height='100%' xmlns:ns11='' ns11:xmlns:xlink='http://www.w3.org/1999/xlink' xmlns='http://www.w3.org/2000/svg'><svg><path d='M 21.86 11.16 L 20.2 9.62 l -1.66 -5.53 a 0.39 0.39 0 0 0 -0.14 -0.21 a 0.4 0.4 0 0 0 -0.25 -0.08 H 5.85 a 0.4 0.4 0 0 0 -0.25 0.08 a 0.4 0.4 0 0 0 -0.14 0.2 l -1.8 5.54 l -1.54 1.54 a 0.38 0.38 0 0 0 -0.1 0.14 a 0.44 0.44 0 0 0 -0.02 0.16 v 4.94 l 0.03 0.15 c 0.02 0.06 0.05 0.1 0.09 0.14 l 0.72 0.7 v 2.37 c 0 0.11 0.04 0.22 0.12 0.3 c 0.08 0.08 0.17 0.13 0.29 0.13 h 3.34 c 0.12 0 0.21 -0.05 0.3 -0.13 a 0.43 0.43 0 0 0 0.11 -0.3 v -2.37 h 10 v 2.37 c 0 0.11 0.04 0.22 0.12 0.3 c 0.08 0.08 0.17 0.13 0.29 0.13 h 3.34 c 0.12 0 0.21 -0.05 0.3 -0.13 a 0.43 0.43 0 0 0 0.11 -0.3 v -2.37 l 0.72 -0.7 a 0.38 0.38 0 0 0 0.1 -0.14 l 0.02 -0.15 v -4.92 a 0.39 0.39 0 0 0 -0.04 -0.18 a 0.52 0.52 0 0 0 -0.1 -0.14 Z M 6.8 5.46 h 10.4 l 1.25 4.16 H 5.54 l 1.25 -4.16 Z m -0.9 9.16 a 1.6 1.6 0 0 1 -1.18 -0.49 a 1.6 1.6 0 0 1 -0.48 -1.17 c 0 -0.46 0.16 -0.85 0.48 -1.17 a 1.6 1.6 0 0 1 1.18 -0.49 c 0.45 0 0.84 0.16 1.17 0.49 c 0.32 0.32 0.49 0.71 0.49 1.17 c 0 0.46 -0.17 0.85 -0.5 1.17 a 1.6 1.6 0 0 1 -1.16 0.49 Z m 12.22 0 a 1.6 1.6 0 0 1 -1.17 -0.49 a 1.6 1.6 0 0 1 -0.49 -1.17 c 0 -0.46 0.17 -0.85 0.5 -1.17 a 1.6 1.6 0 0 1 1.16 -0.49 c 0.46 0 0.85 0.16 1.18 0.49 c 0.32 0.32 0.48 0.71 0.48 1.17 c 0 0.46 -0.16 0.85 -0.48 1.17 a 1.6 1.6 0 0 1 -1.18 0.49 Z'></path></svg></svg><span class='uitk-tab-text'>Cars</span></a></li>"));*/

return element;

}
public static WebElement When_I_Select_Title() {

element = driver.findElement(By.cssSelector("Select[class='cko-field title-id-name gb-whitelist']"));

return element;

}
}
