package Utilities;


import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;



public class SelectElement implements WrapsElement
{

    public SelectElement(WebElement element) {
	}
    public ArrayList<WebElement> AllSelectedOptions;
	public boolean IsMultiple;
    public ArrayList<WebElement> Options;
    public WebElement SelectedOption;
    public WebElement WrappedElement;
    
    public ArrayList<WebElement> getAllSelectedOptions() {
		return AllSelectedOptions;
	}

	public void setAllSelectedOptions(ArrayList<WebElement> allSelectedOptions) {
		AllSelectedOptions = allSelectedOptions;
	}

	public boolean isIsMultiple() {
		return IsMultiple;
	}

	public void setIsMultiple(boolean isMultiple) {
		IsMultiple = isMultiple;
	}

	public ArrayList<WebElement> getOptions() {
		return Options;
	}

	public void setOptions(ArrayList<WebElement> options) {
		Options = options;
	}

	public WebElement getSelectedOption() {
		return SelectedOption;
	}

	public void setSelectedOption(WebElement selectedOption) {
		SelectedOption = selectedOption;
	}

	public void setWrappedElement(WebElement wrappedElement) {
		WrappedElement = wrappedElement;
	}

    
    

    public void DeselectAll() {
	}

    public void DeselectByIndex(int index) {
	}

    public void DeselectByText(String text) {
	}

    public void DeselectByValue(String value) {
	}

    public void SelectByIndex(int index) {
	}

    public void SelectByText(String text) {
	}

    public void SelectByValue(String value) {
	}

	public WebElement getWrappedElement() {
		// TODO Auto-generated method stub
		return null;
	}
}