package Clients;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import ClientsPages.AddClientPage;
import Pages.LoginPage;
import resources.basePage;
import util.AppTest;

public class AddClientTest extends basePage {

	WebDriver driver;
	Properties prop;
	basePage bp;
	LoginPage lp;
	AddClientPage acp;
	AppTest at;
	ExtentReports extent;

	String Firstname = "Name 3";
	String Lastname = "Last 3";
	String email = "devtest.3@mexcool2.co";
	String Address = "";
	String PhoneNo = "";
	String Fax = "";
	String Website = "";

	public static Logger log = LogManager.getLogger(basePage.class.getName());

	@BeforeTest
	public void bTest() throws IOException {

		log.info("Driver is initialised");
		bp = new basePage();
		prop = bp.initialize_Properties();
		driver = bp.initializeDriver(prop);
		lp = new LoginPage(driver);
		acp = lp.loginToHoursheets(prop.getProperty("tenantEmail"), prop.getProperty("tenantPassword"));
		log.info("Navigated to Login page");
	}

	@Test(priority = 0)
	public void AddingClientPageTest() throws Exception {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		if (acp.verifyClientBtn()) {
			acp.clientBtnClick();
		} else {
			acp.getSideBtnVisible().click();
			acp.clientBtnClick();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		acp.clickAddClientBtn();
		acp.getClientFirstName(Firstname);
		acp.getClientLastName(Lastname);
		acp.getEmail(email);
		acp.getAddress(Address);
		acp.getPhoneNo(PhoneNo);
		acp.getFax(Fax);
		acp.getWebsite(Website);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		acp.addClientPhoto();
		acp.clickClientSubmitBtn();
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		// driver.quit();
	}
}
