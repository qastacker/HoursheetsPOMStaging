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
import resources.BasePage;

public class SignupPageTest {

	String plan_a = "free";
	String plan_b = "standard";
	String plan_c = "premium";
	String plan_d = "trial";
	String FName = "Namer";
	String LName = "Ender";
	String Email = "adolDeveloper5@gmail.com";
	String Workspace = "adev5";
	String Country = "India";
	String Password = "Devtest123@";
	String phNo = "5550123432";

	WebDriver driver;
	Properties prop;
	BasePage basePage;
	ExtentReports extent;
	SignupPage sp;

	public static Logger log = LogManager.getLogger(BasePage.class.getName());

	@BeforeTest
	public void beforeTest() {
		basePage = new BasePage();
		prop = basePage.initialize_Properties();
		driver = basePage.initializeDriver(prop);
		sp = new SignupPage(driver);
	}

	@Test(priority=0)
	public void selectTrialPlan() {
		sp.clickTrialPlan();
	}

	@Test(priority=1)
	public void verifyFirstNameLastNameTest() {
		sp.getFirstName(FName);
		sp.getLastName(LName);
	}

	@Test(priority=2)
	public void verifyEmail() {
		sp.getEmail(Email);
	}

	@Test(priority=3)
	public void verifyWorkspace() {
		sp.getWorkspace(Workspace);
	}

	@Test(priority=4)
	public void verifyCountrySelected() {
		sp.getCountry(Country);
	}

	@Test(priority = 5)
	public void verifyPhoneDetails() {
		sp.navigateToPhoneDetails();
		sp.selectCountryCode();
		sp.enterPhoneNo(phNo);
	}
	
	@Test(priority=6)
	public void verifyPassword() {
		sp.getPassword(Password);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		// driver.quit();
	}

}
