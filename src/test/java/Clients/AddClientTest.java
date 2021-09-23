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
	
	@Test(priority=0)
	public void verifyClientSideButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		if (acp.verifyClientBtn()) {
			acp.clientBtnClick();
		} else {
			acp.getSideBtnVisible().click();
			acp.clientBtnClick();
		}
	}

	@Test(priority=1)
	public void verifyClientAddButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		acp.clickAddClientBtn();
	} 
	
	@Test(priority=2)
	public void clientFirstAndLastName() {
		acp.getClientFirstName();
		acp.getClientLastName();
	}
	
	@Test(priority=3)
	public void clientEmail() {
		acp.getEmail();
	}
	
	@Test(priority=4)
	public void clientAddress() {
		acp.getAddress();
	}
	
	@Test(priority=5)
	public void clientPhNoAndFax() {
		acp.getPhoneNo();
		acp.getFax();
	}
	
	@Test(priority=6)
	public void clientWebsite() {
		acp.getWebsite();
	}
	
	@Test(priority=7)
	public void clientPhoto() throws Exception {
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
