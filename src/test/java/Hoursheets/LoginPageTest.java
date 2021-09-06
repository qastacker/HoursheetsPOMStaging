package Hoursheets;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import Pages.LoginPage;
import resources.BasePage;

public class LoginPageTest extends BasePage {

	WebDriver driver;
	Properties prop;
	BasePage basePage;
	ExtentReports extent;
	LoginPage lp;
	public static Logger log = LogManager.getLogger(BasePage.class.getName());

	@BeforeTest
	public void bTest() {
		basePage = new BasePage();
		prop = basePage.initialize_Properties();
		driver = basePage.initializeDriver(prop);
		lp = new LoginPage(driver);
	}

	@Test(dataProvider = "getData")
	public void UserLoggedinPageTest(String email, String password, String text) throws InterruptedException {

		log.info("Navigated to New First page");

		lp = new LoginPage(driver);
		lp.getEmail(email);
		lp.getPassword(password);
		lp.clickContinueBtn();
		
	}

	@DataProvider
	public Object[][] getData() {

//		Object[][] data=new Object[3][3];
//		Object[][] data = new Object[2][3];
		Object[][] data = new Object[1][3];

		data[0][0] = prop.getProperty("tenantEmail");
		data[0][1] = prop.getProperty("tenantPassword");
		data[0][2] = "Authorised User";

//		data[1][0] = "zchah@asdbwegweq.xyz";
//		data[1][1] = "Devtest123@";
//		data[1][2] = "Second User";
		
//		data[2][0]="test2@gmail.com";
//		data[2][1]="test123";
//		data[2][2]="Restricted USer";

		return data;
	}

}
