package bestbuyautomation.pagefiles;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private static WebDriver driver;
	private static WebDriverWait driverWait;

	public HomePage() {
		if (driver == null) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	private By searchBar = By.cssSelector("input[class='search-input']");
	private By submitSearch = By.cssSelector("button[aria-label='submit search']");
	private By searchResultsTitle = By.cssSelector("div[class='title-wrapper title']");
	private By resutProducts = By.cssSelector("div#main-results ol[class='sku-item-list']");
	private By cartIcon = By.cssSelector("div[class='shop-cart-icon']");
	private By addedToCart = By.cssSelector("span[class='added-to-cart']");
	private By continueShopping = By.cssSelector("button.continue-shopping");
	private By orderSummaryHeading = By.cssSelector("h2#cart-order-summary");
	private By checkOutButton = By.cssSelector("button[data-track='Checkout - Top']");

	public WebDriver getDriverInstance() {
		return driver;
	}

	public WebElement getSearchBar() {
		return driverWait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
	}

	public WebElement getSubmiSearch() {
		return driver.findElement(submitSearch);
	}

	public WebElement getSearchResultTitle() {
		return driverWait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsTitle));
	}

	public List<WebElement> getSearchResultProducts() {
		return driver.findElements(resutProducts);
	}

	public WebElement findProduct(String description, String model, String price) {
		String productAddToCartButton = String.format(
				"//a[text()='%s']/parent::h4[@class='sku-title']/following-sibling::div[@class='variation-info']//div[@class='sku-model']//span[@class='sku-value'][text()='%s']/ancestor::div[@class='column-middle']/following-sibling::div//div[@data-testid='customer-price']/span[text()='%s']/ancestor::div[@class='sku-list-item-price']/following-sibling::div/div[@class='sli-add-to-cart']//button[@data-button-state='ADD_TO_CART']",
				description, model, price);
		return driver.findElement(By.xpath(productAddToCartButton));
	}

	public WebElement getAddedToCart() {
		return driverWait.until(ExpectedConditions.visibilityOfElementLocated(addedToCart));
	}

	public WebElement productImageOnCartPopup(String description) {
		return driverWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(String.format("img[alt='%s']", description))));
	}

	public WebElement getContinueShopping() {
		return driver.findElement(continueShopping);
	}

	public WebElement getCartIcon() {
		return driverWait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon));
	}

	public WebElement getSummaryHeading() {
		return driverWait.until(ExpectedConditions.visibilityOfElementLocated(orderSummaryHeading));
	}

	public WebElement getCheckOutButton() {
		return driver.findElement(checkOutButton);
	}

	public WebElement getProductEntryOnSummary(String description) {
		return driver.findElement(By.xpath(String.format("//img[@alt='%s']/ancestor::section[@class='card']/parent::li", description)));
	}
}
