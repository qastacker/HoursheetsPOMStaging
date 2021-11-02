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

public class AddInvoicePage extends basePage {
	public WebDriver driver;
	AppTest at1;
	ExcelUtil ex;
	String sheetName = "invoice";
	DataFormatter formatter = new DataFormatter();
	String invoiceNoteText = "My super long text string to be typed into invoice textarea element";
	String privateNoteText = "My Private super long text string to be typed into private textarea element";
	public static String TEST_DATA_SHEET_PATH = "./src/main/java/testData/HoursheetsTestData.xlsx"; // Location of Test data excel file
	private static Workbook wb; // Excel WorkBook
	private static Sheet s; // Excel Sheet

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root menu-btn']")
	By SideBtnVisible;

	@FindBy(xpath = "//a[@href='/invoices']//div//span[contains(text(),'Invoices')]")
	WebElement invoiceBtn;

	@FindBy(xpath = "//button[@title='Add Invoice']")
	WebElement clickAddInvoiceBtn;

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

	@FindBy(xpath = "//span[@class='rdrDayNumber']//span[contains(text(),'18')]")
	WebElement enterDate;

	@FindBy(xpath = "(//input[@id='outlined-adornment-amount'])[2]")
	WebElement clickOnDueDate;

	@FindBy(xpath = "//span[@class='rdrDayNumber']//span[contains(text(),'18')]")
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

	public AddInvoicePage(WebDriver driver) {
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

	public void clickAddInvoiceBtn() {
		clickAddInvoiceBtn.click();
	}

	public void getCompanyName() {
		companyName.sendKeys("Invoice 1");
	}

	public void getCompanyLogo() throws Exception {
		at1 = new AppTest(driver);
		selectLogo.click();
		at1.uploadFile(AppTest.ADD_INVOICE_PHOTO_UPLOAD_PATH);
		TimeUtil.SleepWaitOne();
	}

	public void getClientName() {
		selectClient.sendKeys("fname");
		selectClient.sendKeys(Keys.DOWN, Keys.ENTER);
	}

	public void getInvoiceNo() {
		enterInvoiceNumber.sendKeys("INV:GD#2676");
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

	public void addItemManually() throws InvalidFormatException, IOException {

		wb = WorkbookFactory.create(new File(TEST_DATA_SHEET_PATH));
		s = wb.getSheet("invoice");
		DataFormatter formatter = new DataFormatter();
		// get all rows in the sheet
		int rowCount = s.getLastRowNum() - s.getFirstRowNum();

		for (int i = 1; i <= rowCount; i++) {
			driver.findElement(By.xpath("(//input[contains(@name,'item')])[" + i + "]"))
					.sendKeys(s.getRow(i).getCell(0).getStringCellValue());
			driver.findElement(By.xpath("(//input[contains(@name,'description')])[" + i + "]"))
					.sendKeys(s.getRow(i).getCell(1).getStringCellValue());

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

			if (i < rowCount) {
				driver.findElement(By.xpath("//button[@title='Add item']")).sendKeys(Keys.ENTER);
			}
		}
	}

	public void getTax1AndTax2() {

		tax1Label.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		tax1Label.sendKeys("CGST");
		tax1.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		tax1.sendKeys("2.5");
		tax2Label.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		tax2Label.sendKeys("SGST");
		tax2.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		tax2.sendKeys("2.5");
	}
	
	public void getDiscountValue() {
		
		discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		discount.sendKeys("0");
	}
	
	public void InvoiceNote() {
		
		getInvoiceNotes.clear();
		getInvoiceNotes.sendKeys(invoiceNoteText);
	}
	
	public void PrivateNote() {
		getPrivateNotes.clear();
		getPrivateNotes.sendKeys(privateNoteText);
	}

	public void clickSaveInvoice() {
		// TODO Auto-generated method stub
		saveInvoice.click();
	}

}
