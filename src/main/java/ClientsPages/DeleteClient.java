package ClientsPages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.basePage;
import util.AppTest;
import util.ExcelUtil;

public class DeleteClient extends basePage{

	public WebDriver driver;
	AppTest at1;
	ExcelUtil ex;
	JavascriptExecutor je;

	String clientListNo = "5";
	String d = "No";
	String expectedTitle = "Name 3 Last 3";

	
	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root menu-btn']")
	By SideBtnVisible;

	@FindBy(xpath = "//a[@href='/clients']//div//span[contains(text(),'Clients')]")
	WebElement clientBtn;

	@FindBy(xpath = "//div[@class='user-header-name']")
	WebElement clientHeading;
	
	@FindBy(className = "user-delete-icon")
	WebElement deleteBtn;
	
	@FindBy(xpath = "//div[@class='button-and-content-height']//button[1]")
	WebElement YesBtn;
	
	@FindBy(xpath = "//div[@class='button-and-content-height']//button[2]")
	WebElement NoBtn;
	
	public DeleteClient(WebDriver driver) {
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
		return driver.findElement(By.xpath("//div[@class='MuiCardContent-root']//div["+ clientListNo +"]//div[1]//div[2]//span[1]"));
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
			deleteBtn.click();
		} else {
			System.out.println("Title didn't match");
		}
	}
	
	public void selectOption() {
		if (d == "Yes") {
			YesBtn.click(); // for clicking yes button
			System.out.println("Client " + expectedTitle + " is Deleted");
		} else if (d == "No") {
			NoBtn.click(); // for clicking no
			System.out.println("Client " + expectedTitle + " not deleted");
		} else {
			System.out.println("Client " + expectedTitle + " not found");
		}
	}
}
