
package com.sel.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sel.pageObjects.AddStaffPage;
import com.sel.pageObjects.LoginPage;
import com.sel.utilities.CommonUtilities;

public class LoginTest extends CommonUtilities {

	LoginPage loginPage;
	AddStaffPage addStaffPage;
	WebDriver driver;
	
	@BeforeClass
	public void launchApp()
	{	
		driver = startBrowser("Chrome", "https://timesheet.sackalerts.world/Login.aspx");
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		addStaffPage = PageFactory.initElements(driver, AddStaffPage.class);
	}
	
	@Test
	@Parameters({"userName", "password"})
	public void loginWithValidCredentials(String uName, String pwd)
			
	{
		try
		{
			test=report.startTest("Login Test 1");
			test.log(LogStatus.INFO, "Test Started"+test.getStartedTime());
		
			elementOperations(loginPage.userName_TextBox, "sendKeys", uName);
			elementOperations(loginPage.password_TextBox, "sendKeys", pwd);
			elementOperations(loginPage.login_Button, "click", "");
			
			Assert.assertEquals(addStaffPage.addStaffForm_HeaderText.getText(), "ADD STAFF");
			
		}
		catch(Exception ex)
		{
			System.out.println("Msg"+ex.getMessage());
		}
		
	}
	
	@AfterClass
	public void closeBrowser()
	{	
		System.out.println("v");
		driver.quit();
		
	}

	
}
