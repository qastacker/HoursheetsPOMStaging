package Invoice;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import ClientsPages.AddClientPage;
import InvoicePages.AddInvoicePage;
import Pages.LoginPage;
import resources.basePage;
import util.AppTest;

public class AddInvoiceTest extends basePage{

	WebDriver driver;
	Properties prop;
	basePage bp;
	LoginPage lp;
	AddClientPage acp;
	AddInvoicePage aip;
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
		aip = new AddInvoicePage(driver);
	}
	
	@Test(priority=0)
	public void verifyInvoiceSideButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		if (aip.verifyInvoiceBtn()) {
			aip.invoiceBtnClick();
		} else {
			aip.getSideBtnVisible().click();
			aip.invoiceBtnClick();
		}
	}

	@Test(priority=1)
	public void verifyAddInvoiceButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		aip.clickAddInvoiceBtn();
	}
	
	@Test(priority=2)
	public void verifyCompanyNameAndLogo() throws Exception {
		aip.getCompanyName();
		aip.getCompanyLogo();
	}
	
	@Test(priority=3)
	public void verifyClient() {
		aip.getClientName();
	}
	
	@Test(priority=4)
	public void verifyInvoiceNo() {
		aip.getInvoiceNo();
	}
	
	@Test(priority=5)
	public void verifyDateAndDueDate() {
		aip.selectDate();
		aip.selectDueDate();
	}
	
	@Test(priority=6)
	public void verifyAddItem() throws InvalidFormatException, IOException {
		aip.addItemManually();
	}
	
	@Test(priority=7)
	public void verifyTaxAndDiscount() {
		aip.getTax1AndTax2();
		aip.getDiscountValue();
	}
	
	@Test(priority=8)
	public void verifyInvoiceAndPrivateNote() {
		aip.InvoiceNote();
		aip.PrivateNote();
	}

	@AfterTest
	public void tearDown() {
		aip.clickSaveInvoice();
		driver.close();
		// driver.quit();
	}
}
