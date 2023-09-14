package bba.pageObjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	// Declaring the locators needed for the home page
	@FindBy(css = "nav[class='hamburger-menu']")
	WebElement hamburgerMenu;

	@FindBy(css = "input[class='search-input']")
	WebElement searchBar;

	@FindBy(css = "button[aria-label='submit search']")
	WebElement submitSearch;	
	

	@FindBy(css = "div[class='title-wrapper title']")
	WebElement searchResultsText;
	
	@FindBy(css = "div[class='shop-cart-icon']")
	WebElement cartIcon;

	/**
	 * Initiate the webdriver object for the page file
	 * 
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Function to search for a product on home page
	 * 
	 * @param productName The name of the product that we want to search for
	 */
	public void serachForProduct(String productName) {
		searchBar.sendKeys(productName);
		submitSearch.click();
		// Validation of search results
		// In some instance the case of the product is different in the search result so converting them to lower case to validate 
		assertTrue((searchResultsText.getText().toLowerCase()).contains(productName.toLowerCase()), String.format("The results for the product %s are displaying", productName));
	}
	
	public void clickOnCartButton() {
		cartIcon.click();
	}

}
