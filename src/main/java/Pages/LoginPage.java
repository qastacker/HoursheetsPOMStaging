package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ClientsPages.AddClientPage;

public class LoginPage {

	public WebDriver driver;
	
	@FindBy(name="email")
	WebElement email;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//span[@class='MuiButton-label']")
	WebElement continueBtn;
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void getEmail(String Email) {
		email.sendKeys(Email);
	}
	
	public void getPassword(String Password) {
		password.sendKeys(Password);
	}
	
	public void clickContinueBtn() {
		continueBtn.click();
	}
	
	public AddClientPage loginToHoursheets(String email, String password) {
		this.getEmail(email);
		this.getPassword(password);
		this.clickContinueBtn();
		return new AddClientPage(driver);
	}

}
