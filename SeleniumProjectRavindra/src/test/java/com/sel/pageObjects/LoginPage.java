package com.sel.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.relevantcodes.extentreports.LogStatus;
import com.sel.utilities.BaseTest;

public class LoginPage extends BaseTest {

	WebDriver driver;
	
	public LoginPage(WebDriver ldriver) {
		this.driver = ldriver;
	}
	
	@FindBy(how=How.ID,using="TextBox2")
	public WebElement userName_TextBox;
	
	@FindBy(how=How.ID,using="TextBox3")
	public WebElement password_TextBox;
	
	@FindBy(how=How.ID,using="Button1")
	public WebElement login_Button;
	
	/*public void loginToApplication(String uName, String pwd)
	{
		userName_TextBox.sendKeys(uName);
		test.log(LogStatus.INFO, "User entered USERNAME successfully");
		password_TextBox.sendKeys(pwd);
		test.log(LogStatus.INFO, "User entered Password successfully");
		login_Button.click();
		test.log(LogStatus.INFO, "User clicked Button successfully");
	}*/
	
	
}
