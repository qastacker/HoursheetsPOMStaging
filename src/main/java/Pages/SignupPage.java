package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.basePage;
import util.ExcelUtil;
import util.TimeUtil;

@SuppressWarnings("static-access")
public class SignupPage extends basePage {

	public WebDriver driver;
	JavascriptExecutor js;
	ExcelUtil ex;
	
//	String FName = "Namer";
//	String LName = "Ender";
//	String Email = "adolDeveloper6@gmail.com";
//	String Workspace = "adev6";
//	String Country = "Australia";
//	String Password = "Devtest123@";
//	String phNo = "5550123432";

	String FName = ex.getCellData("signup",1, 0);
	String LName = ex.getCellData("signup",1, 1);
	String Email = ex.getCellData("signup",1, 2);
	String Workspace = ex.getCellData("signup",1, 3);
	String Country = ex.getCellData("signup",1, 4);
	String Password = ex.getCellData("signup",1, 7);
	String phNo = ex.getCellData("signup",1, 5);
	
	@FindBy(xpath = "//span[normalize-space()='continue to trial for 14 days']")
	WebElement scrollToTrial;

	@FindBy(xpath = "//a[@href='/app/signup?plan=trial']")
	WebElement trialUrl;

	@FindBy(name = "firstName")
	WebElement firstname;

	@FindBy(name = "lastName")
	WebElement lastname;

	@FindBy(id = "email")
	WebElement email;

	@FindBy(name = "workspace")
	WebElement workspace;

	@FindBy(xpath = "//input[@name='country']")
	WebElement countrySelected;

	@FindBy(xpath = "//div[@class='special-label']")
	WebElement phone_label;

	@FindBy(xpath = "//div[@class=' flag-dropdown']")
	WebElement flag_dropdown;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	List<WebElement> flagOptions;

	// Select country based on count
	@FindBy(xpath = "//ul[@role='listbox']/li[11]")
	WebElement countryCodeByFlag;

	@FindBy(name = "phone")
	WebElement phone;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit;

	public SignupPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickTrialPlan() {
		// TODO Auto-generated method stub

//		TimeUtil.FiveWait();
//		js.executeScript("arguments[0].scrollIntoView(true);", scrollToTrial);
		trialUrl.click();
	}

	public void getFirstName() {
		firstname.sendKeys(FName);
	}

	public void getLastName() {
		lastname.sendKeys(LName);
	}

	public void getEmail() {
		email.sendKeys(Email);
	}

	public void getWorkspace() {
		workspace.sendKeys(Workspace);
	}

	public void getCountry() {
		countrySelected.sendKeys(Country);
		countrySelected.sendKeys(Keys.DOWN, Keys.ENTER);
	}

	public void navigateToPhoneDetails() {
		// TODO Auto-generated method stub
		js = (JavascriptExecutor) driver;
		TimeUtil.FiveWait();
		js.executeScript("arguments[0].scrollIntoView(true);", phone_label);
	}

	public void selectCountryCode() {
		// TODO Auto-generated method stub
		js = (JavascriptExecutor) driver;
		flag_dropdown.click();
		TimeUtil.FiveWait();
		for (WebElement opt : flagOptions) {
			if (opt.equals(countryCodeByFlag)) {
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				js.executeScript("arguments[0].click();", countryCodeByFlag);
				break;
			}
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void enterPhoneNo() {
		// TODO Auto-generated method stub
		phone.sendKeys(phNo);
	}

	public void getPassword() {
		password.sendKeys(Password);
		submit.click();
	}

}
