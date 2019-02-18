package com.sel.utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtilities extends BaseTest {
	
	
	public void elementOperations(WebElement element, String operation, String value)
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
		
		if(operation.equalsIgnoreCase("click"))
		{
			ele.click();
		}
		else if(operation.equalsIgnoreCase("sendKeys"))
		{
			ele.sendKeys(value);
		}
	}
	
}
