package ClientsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.basePage;
import util.AppTest;
import util.ExcelUtil;
import util.TimeUtil;

@SuppressWarnings("static-access")
public class AddClientPage extends basePage{

	public WebDriver driver;
	AppTest at;
	ExcelUtil ex;
	
	String firstname = ex.getCellData("clients",1, 0);
	String lastname = ex.getCellData("clients",1, 1);
	String email = ex.getCellData("clients",1, 2);
	String address = ex.getCellData("clients",1, 3);
	String phoneNo = ex.getCellData("clients",1, 4);
	String fax = ex.getCellData("clients",1, 5);
	String website = ex.getCellData("clients",1, 6);
	
	@FindBy(xpath="//button[@class='MuiButtonBase-root MuiIconButton-root menu-btn']")
	By SideBtnVisible;
	
	@FindBy(xpath="//a[@href='/clients']//div//span[contains(text(),'Clients')]")
	WebElement clientBtn;
	
	@FindBy(xpath="//button[@title='Add Client']")
	WebElement clickAddClientBtn;
	
	@FindBy(id="client_firstname")
	WebElement clientFirstName;
	
	@FindBy(id="client_lastname")
	WebElement clientLastName;
	
	@FindBy(id="email")
	WebElement clientEmail;
	
	@FindBy(id="address")
	WebElement clientAddress;
	
	@FindBy(id="phoneNo")
	WebElement clientPhoneNo;
	
	@FindBy(id="fax")
	WebElement clientFax;
	
	@FindBy(id="website")
	WebElement clientWebsite;
	
	@FindBy(xpath="//span[@aria-label='upload picture']")
	WebElement uploadPhoto;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement ClickClientSubmitBtn;
	
	public AddClientPage(WebDriver driver) { 
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyClientBtn(){
		return clientBtn.isDisplayed();
	}
	
	public WebElement getSideBtnVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.visibilityOfElementLocated((By) driver.findElement(SideBtnVisible)));
	}
	
	public void clientBtnClick() {
		 clientBtn.click();
	}
	
	public void clickAddClientBtn() {
		clickAddClientBtn.click();
	}
	
	public void getClientFirstName() {
		clientFirstName.sendKeys(firstname);
	}
	
	public void getClientLastName() {
		clientLastName.sendKeys(lastname);
	}
	
	public void getEmail() {
		clientEmail.sendKeys(email);
	}
	
	public void getAddress() {
		clientAddress.sendKeys(address);
	}
	
	public void getPhoneNo() {
		clientPhoneNo.sendKeys(phoneNo);
	}
	
	public void getFax() {
		clientFax.sendKeys(fax);
	}
	
	public void getWebsite() {
		clientWebsite.sendKeys(website);
	}
	
	public void addClientPhoto() throws Exception {
		at = new AppTest(driver);
		uploadPhoto.click();
		at.uploadFile(AppTest.CLIENT_PHOTO_UPLOAD_PATH);
		TimeUtil.SleepWaitOne();
	}
	
	public void clickClientSubmitBtn() {
		ClickClientSubmitBtn.click();
		TimeUtil.SleepWaitOne();
	}

}
