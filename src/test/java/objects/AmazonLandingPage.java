package objects;

import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;



public class AmazonLandingPage {
	WebDriver driver;
	Logger logger  = LogManager.getLogger(AmazonLandingPage.class);


// capture all the elements
	By hamburger = By.id("nav-hamburger-menu");
	By menu = By.id("hmenu-content");
	By kindle1 = By.xpath("//a[@data-menu-id='4']");
	By kindle2 = By.xpath("//div[@id='hmenu-content']/ul[4]/li[3]/a[1]");
	By byNow = By.id("buy-now-button");




	public AmazonLandingPage(WebDriver driver) {
		this.driver=driver;
		// TODO Auto-generated constructor stub
	}
//Navigate to amazon.com
	public void GoToUrl(String url){
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(url);
			logger.info("Amazon WebSite Open Successfully");


		} catch (Exception e){
			System.out.println("Exception Caught" + e.getMessage());
		}
	}
//click on HamBurger menu button
	public void ClickOnHamburgerMenu(){
		try {
			driver.findElement(hamburger).click();
			logger.info("Click On HamBurger Button Successfully");
			Thread.sleep(2000);
			Assert.assertTrue(driver.findElement(menu).isDisplayed());

		} catch (Exception e){
			System.out.println("Exception Caught" + e.getMessage());
		}
	}
//select Kindle from the menu
	public void selectKindle(){
		try {
			driver.findElement(kindle1).click();
			logger.info("Click On Kindle1 Button Successfully");
			Thread.sleep(2000);
			driver.findElement(kindle2).click();
			logger.info("Click On Kindle2 Button Successfully");
		} catch (Exception e){
			System.out.println("Exception Caught" + e.getMessage());
		}
	}
//click on buynow button
	public void selectBuyNow(){
		try {
			driver.findElement(byNow).click();
			logger.info("Click On BuyNow Button Successfully");
			Thread.sleep(2000);
		} catch (Exception e){
			System.out.println("Exception Caught" + e.getMessage());
		}
	}
//Verify that Amazon SignIn  page displayed
	public void verifyLoginPage() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Amazon Sign In");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='ap_email']")).getText(), "E-mail address or mobile phone number");
		Assert.assertTrue(driver.findElement(By.id("ap_email")).isDisplayed(), "Email Text Box is not present");
		logger.info("asked for email or mobile number");

	}
//method for the Screenshot
	public void takeScreenshot() {
		try {
			Date currentdate = new Date();
			String scFileName = currentdate.toString().replace(" ", "-").replace(":", "-");
			File scFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);		
			FileUtils.copyFile(scFile, new File(".//screenshot//" +scFileName+ ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
