package tests;
/**
 * @author Nikhil Padinchare Chalukulangara
 * Program written for Upgrade recruitment assignment purpose
 */
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Executeetest {

	public static WebDriver driver;
	//Create a chrome session.
	@BeforeTest
	public void setup() {
		
		WebDriverManager.chromedriver().version("100.0.4896.20").setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); 
		options.addArguments("enable-automation"); 
		options.addArguments("--no-sandbox"); 
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-browser-side-navigation"); 
		options.addArguments("--disable-gpu"); 
		driver = new ChromeDriver(options); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	//Close the browser after the test.
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=5, alwaysRun = true, description = "Verify via the UI that as a loan borrower - you are seeing loan offers, upon filling the required form fields with valid inputs.")
	public void happyPathUI() throws InterruptedException {
		driver.get("https://www.credify.tech/phone/nonDMFunnel");
		String elementPrefix = "/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]";

		//Find Element and Enter Loan Amount
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("2000");
		//Find Element and select Loan Purpose
		Select purpose = new Select(driver.findElement(By.name("loan-purpose")));
		purpose.selectByIndex(1);

		//Go to Next Page
		next();

		//Enter FirstName, Lastname, HomeAddress, City, State, Zipcode, DOB
		driver.findElement(By.xpath(elementPrefix+"/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(generateRandomWord(4));
		driver.findElement(By.xpath(elementPrefix+"/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(generateRandomWord(4));
		driver.findElement(By.xpath(elementPrefix+"/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("275 Battery St 23rd floor, San Francisco");		
		driver.findElement(By.id("ChIJH46kxmGAhYAREaxuwYpbinM")).click();
		driver.findElement(By.xpath(elementPrefix+"/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("San Francisco");
		driver.findElement(By.xpath(elementPrefix+"/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("CA");	
		driver.findElement(By.xpath(elementPrefix+"/div[1]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("94111");
		driver.findElement(By.xpath(elementPrefix+"/div[1]/div[5]/div[1]/div[1]/div[1]/input[1]")).sendKeys("01101991");

		//Go to Next Page
		next();

		//Find Element and enter Annual Income and Additional income
		driver.findElement(By.xpath(elementPrefix+"/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("130000");
		driver.findElement(By.xpath(elementPrefix+"/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]")).sendKeys("6000");
		driver.findElement(By.cssSelector("#main-content > div.sc-iGkqmO.dAOHDc.row.center-xs > div > h1")).click();

		//Go to Next Page
		next();

		//Find Element and enter email address and password
		String randomEmail =  "candidate+"+randomNumber()+"@upgrade-challenge.com";
		System.out.println(randomEmail);
		String password = "Cartoon-Duck-14-Coffee-Glvs";
		driver.findElement(By.xpath(elementPrefix+"/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(randomEmail);
		driver.findElement(By.xpath(elementPrefix+"/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]")).sendKeys(password);
		//Click on the Checkbox for Terms of Use
		driver.findElement(By.xpath(elementPrefix+"/div[2]/div[1]/label[1]/div[1]")).click();

		//Go to Next Page
		next();

		//TODO: Need to find workaround for Google Captcha (Need to investigate either (disabling captcha in test env, adding hook to click captcha checkbox, delay webdriver)

		/*
		 *Once react to Offer Page extract the values same as the below to fetch data 
		 */

		//Login with same credentials to reach Offer Page
		driver.get("https://www.credify.tech/portal/login");

		//Doing a work around to complete remaining steps
		//driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(randomEmail);
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("candidate+23@upgrade-challenge.com");

		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]")).sendKeys(password);

		next();

		//TODO: Need to find workaround for Google Captcha (Need to investigate either (disabling captcha in test env, adding hook to click captcha checkbox, delay webdriver)
		/*
		String ExpectedLoanAmount = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[2]")).getText();
		String ExpectedAPR = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]")).getText();
		String ExpectedTerm = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]")).getText();
		String ExpectedMontlyPayment = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[2]")).getText();
		System.out.println(ExpectedLoanAmount + "ExpectedLoanAmount" + ExpectedAPR +"ExpectedAPR" +ExpectedTerm+ "ExpectedTerm"+ ExpectedMontlyPayment + "ExpectedMontlyPayment"   );
		 */
	}

	//Method to use for generating random Number between 0 t0 24
	public static int randomNumber() {
		//instance of random class
		Random rand = new Random(); 
		int upperbound = 25;
		//generate random values from 0-24
		int int_random = rand.nextInt(upperbound);
		return int_random;
	}

	//Method to use for generating random words
	public static String generateRandomWord(int len) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

	//Method to use for clicking on Submit button in pages.
	public void next() {
		driver.findElement(By.cssSelector("button[type='submit']")).click();
	}

}