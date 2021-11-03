package Clients;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import ClientsPages.AddClientPage;
import ClientsPages.EditClientPage;
import Pages.LoginPage;
import resources.basePage;
import util.AppTest;

public class EditClientTest extends basePage {
	WebDriver driver;
	Properties prop;
	basePage bp;
	LoginPage lp;
	AddClientPage acp;
	EditClientPage ecp;
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
		ecp = new EditClientPage(driver);
	}
	
	@Test(priority=0)
	public void verifyClientSideButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		if (ecp.verifyClientBtn()) {
			ecp.clientBtnClick();
		} else {
			ecp.getSideBtnVisible().click();
			ecp.clientBtnClick();
		}
	}
	
	@Test(priority=1)
	public void validateClient() {
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		ecp.clickSelectedClient();
		ecp.checkSelectedClient();
	}
	
	@Test(priority=2)
	public void clientFirstAndLastName() {
		ecp.getClientFirstName();
		ecp.getClientLastName();
	}
	
	@Test(priority=3)
	public void clientAddress() {
		ecp.getAddress();
	}
	
	@Test(priority=4)
	public void clientPhNoAndFax() {
		ecp.getPhoneNo();
		ecp.getFax();
	}
	
	@Test(priority=5)
	public void clientWebsite() {
		ecp.getWebsite();
	}
	
	@Test(priority=6)
	public void clientPhoto() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ecp.addClientPhoto();
		ecp.clickClientSubmitBtn();
	}
}
