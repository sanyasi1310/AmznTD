package Test_Scenarios;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import objects.AmazonLandingPage;


public class TestMethods {

	WebDriver driver;
	Logger logger  = LogManager.getLogger(AmazonLandingPage.class);

	//TestNG format
	//Before and After test method will run before/after test execution
	//we are giving browser name in the xml test suite for the multibrowser testing
	//parallel testing is also possible using this assignmentTest

	@Parameters("browser")
	@BeforeTest
	public void setup(String browser) {
		try {
			if(browser.equalsIgnoreCase("Firefox")){
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				logger.info("FireFox Driver Setup Completed");
			}
			else if(browser.equalsIgnoreCase("chrome")){
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();	
				logger.info("Chrome Driver Setup Completed");
			}
			else if(browser.equalsIgnoreCase("ie")){
				WebDriverManager.iedriver().setup();;
				driver = new InternetExplorerDriver();	
				logger.info("IE Driver Setup Completed");
			}else {
				throw new Exception("Incorrect Or Empty Browser Name");
			}
		} catch (Exception e){
			System.out.println("Exception Caught" + e.getMessage());
		}			
	}

	@Test	
	public void AmazonOperations()  {
		AmazonLandingPage page = new AmazonLandingPage(driver);
		//Navigate to amazon.com
		page.GoToUrl("http://www.amazon.ca");
		page.takeScreenshot();
		//click on HamBurger menu button
		page.ClickOnHamburgerMenu();
		page.takeScreenshot();
		//select Kindle from the menu
		page.selectKindle();
		page.takeScreenshot();
		//click on buynow button
		page.selectBuyNow();
		page.takeScreenshot();
		//Verify that Amazon SignIn  page displayed
		page.verifyLoginPage();
		page.takeScreenshot();

	}

	@AfterTest
	public void endtest() {
		try {
			driver.close();
			driver.quit();
			logger.info("Test Completed");

		} catch (Exception e){
			System.out.println("Exception Caught" + e.getMessage());
		}
	}
}
