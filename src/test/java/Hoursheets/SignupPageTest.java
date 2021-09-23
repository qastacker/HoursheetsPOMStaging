package Hoursheets;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import Pages.SignupPage;
import resources.basePage;

public class SignupPageTest {
	
	WebDriver driver;
	Properties prop;
	basePage bp;
	ExtentReports extent;
	SignupPage sp;

	public static Logger log = LogManager.getLogger(basePage.class.getName());

	@BeforeTest
	public void beforeTest() {
		bp = new basePage();
		prop = bp.initialize_Properties();
		driver = bp.initializeDriver(prop);
		sp = new SignupPage(driver);
	}

	@Test(priority=0)
	public void selectTrialPlan() {
		sp.clickTrialPlan();
	}

	@Test(priority=1)
	public void verifyFirstNameLastNameTest() {
		sp.getFirstName();
		sp.getLastName();
	}

	@Test(priority=2)
	public void verifyEmail() {
		sp.getEmail();
	}

	@Test(priority=3)
	public void verifyWorkspace() {
		sp.getWorkspace();
	}

	@Test(priority=4)
	public void verifyCountrySelected() {
		sp.getCountry();
	}

	@Test(priority = 5)
	public void verifyPhoneDetails() {
		sp.navigateToPhoneDetails();
		sp.selectCountryCode();
		sp.enterPhoneNo();
	}
	
	@Test(priority=6)
	public void verifyPassword() {
		sp.getPassword();
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		// driver.quit();
	}

}
