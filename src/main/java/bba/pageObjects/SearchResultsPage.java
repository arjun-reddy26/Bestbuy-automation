package bba.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {

	@FindBy(css = "span[class='added-to-cart']")
	WebElement addedToCart;

	@FindBy(css = "button.continue-shopping")
	WebElement continueShopping;

	@FindBy(css = "button[class='clear-search-icon ']")
	WebElement clearSearchIcon;
	
	WebDriver driver;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Function to add a product into cart based on description, model and price.
	 * Note: This function will only work for Add to cart button and for see details
	 * button it will fail.
	 * 
	 * @param description The description of the product
	 * @param model       The model of the product
	 * @param price       The price of the Product.
	 */
	public void addProductToCart(String description, String model, String price) {
		String productAddToCartButton = String.format(
				"//a[text()='%s']/parent::h4[@class='sku-title']/following-sibling::div[@class='variation-info']//div[@class='sku-model']//span[@class='sku-value'][text()='%s']/ancestor::div[@class='column-middle']/following-sibling::div//div[@data-testid='customer-price']/span[text()='%s']/ancestor::div[@class='sku-list-item-price']/following-sibling::div/div[@class='sli-add-to-cart']//button[@data-button-state='ADD_TO_CART']",
				description, model, price);
		driver.findElement(By.xpath(productAddToCartButton)).click();
		// Validation for added to cart text
		addedToCart.isDisplayed();
		continueShopping.click();
	}
	
	public void clickOnClearButton() {
		clearSearchIcon.click();
	}
	
	
}
