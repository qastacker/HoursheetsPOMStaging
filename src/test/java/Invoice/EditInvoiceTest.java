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
import InvoicePages.EditInvoicePage;
import Pages.LoginPage;
import resources.basePage;
import util.AppTest;

public class EditInvoiceTest extends basePage{

	WebDriver driver;
	Properties prop;
	basePage bp;
	LoginPage lp;
	AddClientPage acp;
	EditInvoicePage eip;
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
		eip = new EditInvoicePage(driver);
	}
	
	@Test(priority=0)
	public void verifyInvoiceSideButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		if (eip.verifyInvoiceBtn()) {
			eip.invoiceBtnClick();
		} else {
			eip.getSideBtnVisible().click();
			eip.invoiceBtnClick();
		}
	}

	@Test(priority=1)
	public void selectAndEditInvoice() {
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		eip.selectInvoice();
		eip.checkSelectedInvoice();
	}
	
	@Test(priority=2)
	public void verifyCompanyNameAndLogo() throws Exception {
		eip.getCompanyName();
		eip.getCompanyLogo();
	}
	
	@Test(priority=3)
	public void verifyClient() {
		eip.getClientName();
	}
	
	@Test(priority=4)
	public void verifyInvoiceNo() {
		eip.getInvoiceNo();
	}
	
	@Test(priority=5)
	public void verifyDateAndDueDate() {
		eip.selectDate();
		eip.selectDueDate();
	}
	
	@Test(priority=6)
	public void verifyAddItem() throws InvalidFormatException, IOException {
		eip.editItemManually();
	}
	
	@Test(priority=7)
	public void verifyTaxAndDiscount() {
		eip.getTax1AndTax2();
		eip.getDiscountValue();
	}
	
	@Test(priority=8)
	public void verifyInvoiceAndPrivateNote() {
		eip.InvoiceNote();
		eip.PrivateNote();
	}

	@AfterTest
	public void tearDown() {
		eip.clickSaveInvoice();
		driver.close();
		// driver.quit();
	}
}
