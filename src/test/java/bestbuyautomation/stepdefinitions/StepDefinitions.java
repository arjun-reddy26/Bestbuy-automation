package bestbuyautomation.stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import bestbuyautomation.pagefiles.HomePage;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends HomePage {
	static WebDriver driver;
	WebElement product;

	public StepDefinitions() {
		super();
	}

	@AfterAll
	public static void closebrowser() {
		driver.quit();
	}

	@Given("Launch the Best Buy application")
	public void launch_the_best_buy_application() {
		driver = getDriverInstance();
		driver.get("https://www.bestbuy.com/");
	}

	@Given("Search for product {string} on home page and click on Search icon")
	public void search_for_product_on_home_page_and_click_on_search_icon(String prodcutName) {
		getSearchBar().sendKeys(prodcutName);
		getSubmiSearch().click();
	}

	@Then("Validate the serach results for the {string}")
	public void validate_the_serach_results_for_the(String productName) {
		Assert.assertTrue(getSearchResultTitle().getText().toLowerCase().contains(productName.toLowerCase()),
				String.format("The search results are displaying for %s product", productName));
		Assert.assertTrue(getSearchResultProducts().size() > 0);
	}

	@When("Find the product with {string} {string} and {string}")
	public void find_the_product_with_and(String description, String model, String price) {
		product = findProduct(description, model, price);
	}

	@When("Click on the Add to cart")
	public void click_on_the_add_to_cart() {
		product.click();
	}

	@Then("Validate the Added to cart popup for {string}")
	public void validate_the_added_to_cart_popup(String description) {
		Assert.assertTrue(getAddedToCart().isDisplayed(), "Added to card message is displaying");
		Assert.assertTrue(productImageOnCartPopup(description).isDisplayed(),
				String.format("The product with description %s is displaying on cart", description));
	}

	@When("Close the added to cart popup")
	public void close_the_added_to_cart_popup() {
		getContinueShopping().click();
	}

	@When("Click on Cart icon")
	public void click_on_the_cart_icon() {
		getCartIcon().click();
	}

	@Then("Validate the Order Summary Page")
	public void validate_the_order_summary_page() {
		Assert.assertTrue(getSummaryHeading().isDisplayed(), "Order summary page is displayed");
		Assert.assertTrue(getCheckOutButton().isDisplayed(), "Checkout button displayed on order summary page");
	}

	@Then("Validate product {string} and {string} on Summary Page")
	public void validate_product_on_summary_page(String description, String price) {
		WebElement productLaout = getProductEntryOnSummary(description);
		Assert.assertTrue(productLaout.isDisplayed(),
				String.format("The product %s is displaying on the summary", description));
		Assert.assertEquals(productLaout.findElement(By.cssSelector("span[class=price-block__inline]")).getText(),
				price);
	}
}
