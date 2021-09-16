package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.basePage;

public class DashboardPage extends basePage{
	
	public WebDriver driver;
	
	@FindBy(xpath="//button[@class='MuiButtonBase-root MuiIconButton-root menu-btn']")
	By SideBtnVisible;
	
	By clientBtn = By.xpath("//a[@href='/clients']//div//span[contains(text(),'Clients')]");
	WebElement ClientBtnElement = driver.findElement(clientBtn);
	
	public DashboardPage(WebDriver driver) { 
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyClientBtn(){
		return ClientBtnElement.isDisplayed();
	}
	
	public WebElement getSideBtnVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.visibilityOfElementLocated((By) driver.findElement(SideBtnVisible)));
	}
	
	public WebElement getClientBtn() {
		return driver.findElement(clientBtn);
	}
	
}
