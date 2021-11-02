package InvoicePages;

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

public class DeleteInvoice extends basePage{
	public WebDriver driver;
	AppTest at1;
	ExcelUtil ex;
	JavascriptExecutor je;

	String invoiceListNo = "2";
	String expectedTitle = "INV:GD#2676";
	String d = "No";
	
	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root menu-btn']")
	By SideBtnVisible;

	@FindBy(xpath = "//a[@href='/invoices']//div//span[contains(text(),'Invoices')]")
	WebElement invoiceBtn;

	@FindBy(xpath = "//button[@title='Delete']")
	WebElement clickDeleteBtn;
	
	@FindBy(xpath = "//div[@class='button-and-content-height']//button[1]")
	WebElement YesBtn;
	
	@FindBy(xpath = "//div[@class='button-and-content-height']//button[2]")
	WebElement NoBtn;
	
	public DeleteInvoice(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyInvoiceBtn() {
		return invoiceBtn.isDisplayed();
	}

	public WebElement getSideBtnVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.visibilityOfElementLocated((By) driver.findElement(SideBtnVisible)));
	}

	public void invoiceBtnClick() {
		invoiceBtn.click();
	}
	
	public WebElement selectedInvoice() {
		return driver.findElement(By.xpath("(//div[@class='InvoiceId-Date'])[" + invoiceListNo + "]"));
	}
	
	public WebElement selectedInvoiceTitle() {
		return driver
				.findElement(By.xpath("//div[@class='advance-repo-scroll-container scroll-container-searchFalse']//div["
						+ invoiceListNo + "]//div[1]//div[1]//div[1]"));
	}

	public void selectInvoice() {
		je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", this.selectedInvoice());
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void clickSelectedInvoice() {
		this.selectedInvoice().click();
		clickDeleteBtn.click();
	}

	public void checkSelectedInvoice() {
		String InvoiceTitleText = this.selectedInvoiceTitle().getText();
		if (InvoiceTitleText.equalsIgnoreCase(expectedTitle)) {
			this.clickSelectedInvoice();
		} else {
			System.out.println("Title didn't match");
		}
	}
	
	public void selectOption() {
		if (d == "Yes") {
			YesBtn.click(); // for clicking yes button
			System.out.println("Invoice " + expectedTitle + " is Deleted");
		} else if (d == "No") {
			NoBtn.click(); // for clicking no
			System.out.println("Invoice " + expectedTitle + " not deleted");
		} else {
			System.out.println("Invoice " + expectedTitle + " not found");
		}
	}
	
	
}
