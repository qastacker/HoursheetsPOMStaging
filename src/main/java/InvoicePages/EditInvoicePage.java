package InvoicePages;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.basePage;
import util.AppTest;
import util.ExcelUtil;
import util.TimeUtil;

public class EditInvoicePage extends basePage {
	public WebDriver driver;
	AppTest at1;
	ExcelUtil ex;
	JavascriptExecutor je;

	String sheetName = "invoice";
	String invoiceListNo = "2";
	String expectedTitle = "INV:GD#2676";
	String NewInvoiceNo = "INV:GD#2777";
	String getCompanyName = "Invoice Edit 1";
	String invoiceNoteText = "Re-edited My super long text string to be typed into invoice textarea element";
	String privateNoteText = "Re-edited My Private super long text string to be typed into private textarea element";
	DataFormatter formatter = new DataFormatter();
	public static String TEST_DATA_SHEET_PATH = "./src/main/java/testData/HoursheetsTestData.xlsx";
	private static Workbook wb; // Excel WorkBook
	private static Sheet s; // Excel Sheet

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root menu-btn']")
	By SideBtnVisible;

	@FindBy(xpath = "//a[@href='/invoices']//div//span[contains(text(),'Invoices')]")
	WebElement invoiceBtn;

	@FindBy(xpath = "//button[@title='Edit']")
	WebElement clickEditBtn;

	@FindBy(xpath = "//textarea[@placeholder='Your Company']")
	WebElement companyName;

	@FindBy(xpath = "//label[@role='button']")
	WebElement selectLogo;

	@FindBy(id = "filter-demo")
	WebElement selectClient;

	@FindBy(name = "invoiceNo")
	WebElement enterInvoiceNumber;

	@FindBy(xpath = "(//input[@id='outlined-adornment-amount'])[1]")
	WebElement clickOnDate;

	@FindBy(xpath = "//span[@class='rdrMonthAndYearPickers']")
	WebElement month_year;

	@FindBy(xpath = "//button[@class='rdrNextPrevButton rdrPprevButton']")
	WebElement prevButton;

	@FindBy(xpath = "//span[@class='rdrDayNumber']//span[contains(text(),'20')]")
	WebElement enterDate;

	@FindBy(xpath = "(//input[@id='outlined-adornment-amount'])[2]")
	WebElement clickOnDueDate;

	@FindBy(xpath = "//span[@class='rdrDayNumber']//span[contains(text(),'20')]")
	WebElement enterDueDate;

	@FindBy(name = "Tax1Label")
	WebElement tax1Label;

	@FindBy(name = "tax1")
	WebElement tax1;

	@FindBy(name = "Tax2Label")
	WebElement tax2Label;

	@FindBy(name = "tax2")
	WebElement tax2;

	@FindBy(name = "discount")
	WebElement discount;

	@FindBy(name = "invoiceNotes")
	WebElement getInvoiceNotes;

	@FindBy(name = "privateNotes")
	WebElement getPrivateNotes;

	@FindBy(xpath = "//button[@title='Save Invoice']")
	WebElement saveInvoice;

	public EditInvoicePage(WebDriver driver) {
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
		clickEditBtn.click();
	}

	public void checkSelectedInvoice() {
		String InvoiceTitleText = this.selectedInvoiceTitle().getText();
		if (InvoiceTitleText.equalsIgnoreCase(expectedTitle)) {
			this.clickSelectedInvoice();
		} else {
			System.out.println("Title didn't match");
		}
	}

	public void getCompanyName() {
		companyName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		companyName.sendKeys(getCompanyName);
	}

	public void getCompanyLogo() throws Exception {
		at1 = new AppTest(driver);
		selectLogo.click();
		at1.uploadFile(AppTest.ADD_INVOICE_PHOTO_UPLOAD_PATH);
		TimeUtil.SleepWaitOne();
	}

	public void getClientName() {
		selectClient.sendKeys("name");
		selectClient.sendKeys(Keys.DOWN, Keys.ENTER);
	}

	public void getInvoiceNo() {
		enterInvoiceNumber.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		enterInvoiceNumber.sendKeys(NewInvoiceNo);
	}

	public void minimiseCalendar() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.tagName("body")), 0, 0);
		action.moveByOffset(0, 0).click().build().perform();
	}

	public void selectDate() {
		clickOnDate.click();
		while (!month_year.getText().equalsIgnoreCase("September 2021")) {
			prevButton.click();
		}
		enterDate.click();
		this.minimiseCalendar();
	}

	public void selectDueDate() {
		clickOnDueDate.click();
		enterDueDate.click();
		this.minimiseCalendar();
	}

	public void editItemManually() throws InvalidFormatException, IOException {

		wb = WorkbookFactory.create(new File(TEST_DATA_SHEET_PATH));
		s = wb.getSheet("invoice");
		DataFormatter formatter = new DataFormatter();
		// get all rows in the sheet
		int rowCount = s.getLastRowNum() - s.getFirstRowNum();

		for (int i = 1; i <= rowCount; i++) {
			
			WebElement item = driver.findElement(By.xpath("(//input[contains(@name,'item')])[" + i + "]"));
			item.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			item.sendKeys(s.getRow(i).getCell(0).getStringCellValue());

			WebElement description = driver
					.findElement(By.xpath("(//input[contains(@name,'description')])[" + i + "]"));
			description.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			description.sendKeys(s.getRow(i).getCell(1).getStringCellValue());

			WebElement pricePerUnit = driver
					.findElement(By.xpath("(//input[contains(@name,'pricePerUnit')])[" + i + "]"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			pricePerUnit.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			String price = formatter.formatCellValue(s.getRow(i).getCell(2));
			pricePerUnit.sendKeys(price);

			WebElement qty = driver.findElement(By.xpath("(//input[contains(@name,'qty')])[" + i + "]"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			qty.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			String qtys = formatter.formatCellValue(s.getRow(i).getCell(3));
			qty.sendKeys(qtys);
		}
	}

	public void getTax1AndTax2() {

		tax1Label.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		tax1Label.sendKeys("CGST");
		tax1.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		tax1.sendKeys("7.5");
		tax2Label.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		tax2Label.sendKeys("SGST");
		tax2.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		tax2.sendKeys("7.5");
	}

	public void getDiscountValue() {

		discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		discount.sendKeys("1.99");
	}

	public void InvoiceNote() {

		getInvoiceNotes.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		getInvoiceNotes.sendKeys(invoiceNoteText);
	}

	public void PrivateNote() {
		getPrivateNotes.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		getPrivateNotes.sendKeys(privateNoteText);
	}

	public void clickSaveInvoice() {
		// TODO Auto-generated method stub
		saveInvoice.click();
	}

}
