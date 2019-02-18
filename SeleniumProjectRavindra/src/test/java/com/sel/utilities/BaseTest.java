package com.sel.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {

	WebDriver driver;

	public static String dest;
	public static String time;

	public static ExtentReports report;
	public static ExtentTest test;

	public static String takeScreenshot(WebDriver driver) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
			Date date = new Date();
			// System.out.println(dateFormat.format(date)); // 2016/11/16
			// 12:08:43
			time = dateFormat.format(date);
			// System.out.println("Time is" + time);
			TakesScreenshot tc = (TakesScreenshot) driver;
			File src = tc.getScreenshotAs(OutputType.FILE);

			dest = System.getProperty("user.dir") + "\\Screenshot\\" + time + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(src, destination);
			// System.out.println("image destination" + dest);
			System.out.println("Screen shot taken");

			// return dest;
		} catch (Exception ex) {
			System.out.println("Screenshot error is" + ex.getMessage());
		}
		return dest;
	}

	@BeforeTest
	public void Reportsetup() {
		try {
			report = new ExtentReports(System.getProperty("user.dir") + "//ExtentReport//Report.html", true);
			report.addSystemInfo("HostName", "Ravindra").addSystemInfo("Environment", "QA")
					.addSystemInfo("User", "Ambadas").addSystemInfo("Project Name", "Automation Demo");
			report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));

		} catch (Exception ex) {
			System.out.println("Issue is" + ex.getMessage());
		}
	}

	@AfterMethod
	public void getReport(ITestResult result) {
		try {
			String screnshotpath = takeScreenshot(driver);
			if (result.getStatus() == ITestResult.FAILURE) {

				test.log(LogStatus.FAIL, result.getThrowable());
				test.log(LogStatus.FAIL, "Below is the screen shot:-" + test.addScreenCapture(screnshotpath));
				test.log(LogStatus.FAIL, "Test Case Fail is:- " + result.getName());

			} else if (result.getStatus() == ITestResult.SUCCESS) {
				test.log(LogStatus.PASS, "Test Case pass is:- " + result.getName());
				test.log(LogStatus.PASS, "Below is the screen shot:-" + test.addScreenCapture(screnshotpath));
			} else if (result.getStatus() == ITestResult.SKIP) {
				test.log(LogStatus.SKIP, "test Case skip is:- " + result.getName());
			} else if (result.getStatus() == ITestResult.STARTED) {
				test.log(LogStatus.INFO, "Test Case started");

			}
			report.endTest(test);

		} catch (Exception es) {
			System.out.println(" Report genration Excepion is:- " + es.getMessage());
		}
	}

	@AfterTest
	public void endTest() {
		report.flush();
		report.close();
	}

	/**
	 * This method opens the browser and enters the url
	 *
	 * @author RavindraGarapati
	 * @param browser
	 *            (accepts either chrome/firefox)
	 * @param url
	 *            (accepts url link)
	 * @return returns the webdriver instance
	 */
	public WebDriver startBrowser(String browser, String url) {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/chromeDriver/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/main/resources/firefoxDriver/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);

		return driver;
	}

	public void closeBrowser() {
		driver.quit();
	}

}
