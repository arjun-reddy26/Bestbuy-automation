package bba.stepdefinitions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.xml.XmlTest;

import bba.pageObjects.HomePage;
import bba.pageObjects.SearchResultsPage;
import bba.utils.GetExcelData;
import bba.pageObjects.YourCartPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderProductStepsDefinitions {

	WebDriver driver = null;
	HomePage homePageObject = null;
	SearchResultsPage searchResultsPageObject = null;
	YourCartPage yourCartPageObject = null;
	GetExcelData getExcelData = null;
	XmlTest xmlTestObject = null;
	String testDataFileName = null;
	List <Map<String, String>> testData = null;
	
	@Before
	public void setup() throws Exception {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Setting the implicit wait with 60 seconds.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		// Creating objects for page classes
		homePageObject = new HomePage(driver);
		searchResultsPageObject = new SearchResultsPage(driver);
		yourCartPageObject = new YourCartPage(driver);
		
		// Fetching the test data parameter from testng.xml		
		getExcelData = new GetExcelData();
		xmlTestObject = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
		testDataFileName= xmlTestObject.getParameter("testDataFileName");
		testData = getExcelData.getExcelData(testDataFileName);
	}
	
	@After
	public void quiteSession() {
		driver.quit();
	}

	@Given("User is on the Best buy home page")
	public void launchApplication() {
		driver.get("https://www.bestbuy.com");
		driver.findElement(By.cssSelector("div[class='country-selection'] a[class='us-link']")).click();
	}

	@When("User adds the product")
	public void addproduct() {
		for(Map<String,String> dataEntry : testData) {
			homePageObject.serachForProduct(dataEntry.get("Name"));
			searchResultsPageObject.addProductToCart(dataEntry.get("Description"), dataEntry.get("Model"), dataEntry.get("Price"));
			if(testData.size() > 1)
				searchResultsPageObject.clickOnClearButton();
		}
	}
	
	@When("User click on Cart button")
	public void navigateToCart() {
		homePageObject.clickOnCartButton();
	}
	
	@Then("User should be on the Order Summary page")
	public void validateSummaryPage() {
		yourCartPageObject.validateYourCartPage();
	}
}

