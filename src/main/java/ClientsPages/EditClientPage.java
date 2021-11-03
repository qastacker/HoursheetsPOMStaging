package ClientsPages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
public class EditClientPage extends basePage {
	public WebDriver driver;
	AppTest at;
	ExcelUtil ex;
	JavascriptExecutor je;
	String clientListNo = "4";
	String expectedTitle = "Name 3 Last 3";

	String firstname = ex.getCellData("editClients", 1, 0);
	String lastname = ex.getCellData("editClients", 1, 1);
	String email = ex.getCellData("editClients", 1, 2);
	String address = ex.getCellData("editClients", 1, 3);
	String phoneNo = ex.getCellData("editClients", 1, 4);
	String fax = ex.getCellData("editClients", 1, 5);
	String website = ex.getCellData("editClients", 1, 6);

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root menu-btn']")
	By SideBtnVisible;

	@FindBy(xpath = "//a[@href='/clients']//div//span[contains(text(),'Clients')]")
	WebElement clientBtn;

	@FindBy(xpath = "//div[@class='user-header-name']")
	WebElement clientHeading;

	@FindBy(className = "user-edit-icon")
	WebElement editBtn;
	
	@FindBy(id="client_firstname")
	WebElement clientFirstName;
	
	@FindBy(id="client_lastname")
	WebElement clientLastName;
	
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

	public EditClientPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyClientBtn() {
		return clientBtn.isDisplayed();
	}

	public WebElement getSideBtnVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.visibilityOfElementLocated((By) driver.findElement(SideBtnVisible)));
	}

	public void clientBtnClick() {
		clientBtn.click();
	}

	public WebElement selectClient() {
		return driver.findElement(
				By.xpath("//div[@class='MuiCardContent-root']//div[" + clientListNo + "]//div[1]//div[2]//span[1]"));
	}

	public void clickSelectedClient() {
		je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", this.selectClient());
		this.selectClient().click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void checkSelectedClient() {
		String clientHeadingText = clientHeading.getText();
		if (clientHeadingText.equalsIgnoreCase(expectedTitle)) {
			editBtn.click();
		} else {
			System.out.println("Title didn't match");
		}
	}
	
	public void getClientFirstName() {
		clientFirstName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		clientFirstName.sendKeys(firstname);
	}
	
	public void getClientLastName() {
		clientLastName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		clientLastName.sendKeys(lastname);
	}
	
	public void getAddress() {
		clientAddress.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		clientAddress.sendKeys(address);
	}
	
	public void getPhoneNo() {
		clientPhoneNo.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		clientPhoneNo.sendKeys(phoneNo);
	}
	
	public void getFax() {
		clientFax.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		clientFax.sendKeys(fax);
	}
	
	public void getWebsite() {
		clientWebsite.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
