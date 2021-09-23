package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class basePage {

	public static WebDriver driver;
	public static Properties prop;  

	public WebDriver initializeDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println(browserName);

		if (browserName.equals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("IE")) {
			// IE code
			// System.setProperty("webdriver.chrome.driver","C://Users//Adol-sys-410//Downloads//ujars//chromedriver.exe");
			// System.setProperty("webdriver.gecko.driver","C://Users//Adol-sys-410//Downloads//geckodriver-v0.24.0-win64//geckodriver.exe");
		} else {
			System.out.println("Browser" + browserName
					+ "is not defined in properties file, please give the correct browser name");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("trialUrl"));
		/*
		 * Staging Signup
		 * driver.get(prop.getProperty("trialUrl"));
		 * **/
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	/**
	 * This method is used to initialize the properties and it will return
	 * properties reference
	 * 
	 * @return prop
	 */
	public Properties initialize_Properties() {

		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/resources/data.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyy_hh_mm_ss");
		// String png= System.currentTimeMillis()+ ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName
				+ formater.format(calendar.getTime()) + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}
	
//	public String getScreenshot(String result,WebDriver driver) throws IOException {
//		
//		TakesScreenshot ts=(TakesScreenshot) driver;
//		File src = ts.getScreenshotAs(OutputType.FILE);
//		String destination = System.getProperty("user.dir")+"\\TestScreenshot\\"+result+".png";
//		FileUtils.copyFile(src, new File(destination));
//		return destination;
//	}
}
