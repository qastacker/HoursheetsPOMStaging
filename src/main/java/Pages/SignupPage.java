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
import util.TimeUtil;

public class SignupPage extends basePage{

	public WebDriver driver;
	JavascriptExecutor js;
	
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

	@FindBy(xpath="//input[@name='country']")
	WebElement countrySelected;

	@FindBy(xpath = "//div[@class='special-label']")
	WebElement phone_label;

	@FindBy(xpath = "//div[@class=' flag-dropdown']")
	WebElement flag_dropdown;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	List<WebElement> flagOptions;
	
	//Select country based on count
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
	
	public void getFirstName(String Firstname) {
		firstname.sendKeys(Firstname);
	}

	public void getLastName(String Lastname) {
		lastname.sendKeys(Lastname);
	}

	public void getEmail(String Email) {
		email.sendKeys(Email);
	}

	public void getWorkspace(String Workspace) {
		workspace.sendKeys(Workspace);
	}

	public void getCountry(String Country) {
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
	
	public void enterPhoneNo(String phNo) {
		// TODO Auto-generated method stub
		phone.sendKeys(phNo);
	}
	
	public void getPassword(String Password) {
		password.sendKeys(Password);
		submit.click();
	}

	

	

	

	
}
