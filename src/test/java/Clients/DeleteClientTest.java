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
import ClientsPages.DeleteClient;
import Pages.LoginPage;
import resources.basePage;
import util.AppTest;

public class DeleteClientTest {
	WebDriver driver;
	Properties prop;
	basePage bp;
	LoginPage lp;
	AddClientPage acp;
	DeleteClient dc;
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
		dc = new DeleteClient(driver);
	}
	
	@Test(priority=0)
	public void verifyInvoiceSideButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		if (dc.verifyClientBtn()) {
			dc.clientBtnClick();
		} else {
			dc.getSideBtnVisible().click();
			dc.clientBtnClick();
		}
	}
	
	@Test(priority=1)
	public void validateClient() {
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dc.clickSelectedClient();
		dc.checkSelectedClient();
	}
	
	@Test(priority=2)
	public void selectYesOrNo() {
		dc.selectOption();
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		// driver.quit();
	}
	
	
}
