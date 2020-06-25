package ts_03;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pageobjects.GiftCard;
import setup.Environment_Setup;
import utils.Excelutils;
import utils.Report;
import utils.Screenshots;

public class Giftcard_with_invalid_minimum_amount extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;

	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}

	//Tries to fill a value less than 1000 in the gift amount field
	@Test
	public void invalid_minimum_amount() throws Exception {
		
		Report report = new Report();

		log = reports.createTest("Test Invalid minimum Gift Card amount");

		report.startBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));

		GiftCard gc  = PageFactory.initElements(driver, GiftCard.class);

		report.select(log, "Invalid minimum Gift Card amount");

		log.pass(MarkupHelper.createLabel("Invalid minimum gift card amount did not enable 'Next' button", ExtentColor.GREEN));

		gc.enter_to_giftcard("Order type");

		gc.select_gifttype("Occasion");

		gc.enter_amount("Invalid minimum Gift Card amount");
		
		Screenshots.takesnap(driver, "Invalid minimum Gift Card amount",browsertype);
		
		report.display(log, "Invalid minimum Gift Card amount didn't perform further operations");

		log.pass(MarkupHelper.createLabel("Invalid minimum Gift Card amount wasn't accepted", ExtentColor.GREEN));

		report.closeBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));


	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Invalid minimum Gift Card amount Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Invalid minimum Gift Card amount Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Invalid minimum Gift Card amount Test: ENDED",browsertype);
		// close the driver

		driver.quit();
	}		

}
