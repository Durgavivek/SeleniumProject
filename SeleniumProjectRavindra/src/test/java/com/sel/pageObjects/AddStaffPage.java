package com.sel.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.sel.utilities.BaseTest;

public class AddStaffPage extends BaseTest {
	
	WebDriver driver;
	
	public AddStaffPage(WebDriver ldriver) {
		this.driver = ldriver;
	}
	
	@FindBy(how=How.CSS,using=".container > h3")
	public WebElement addStaffForm_HeaderText;
	/*
	public void assertAddStaffFormHeaderText(String expectedHeader)
	{
		Assert.assertEquals(addStaffForm_HeaderText.getText(), expectedHeader);
		test.log(LogStatus.INFO, "Assetion done");
	}*/
}
