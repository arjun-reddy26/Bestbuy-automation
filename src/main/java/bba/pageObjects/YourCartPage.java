package bba.pageObjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourCartPage {

	WebDriver driver;
	
	@FindBy(css ="h2#cart-order-summary")
	WebElement orderSummaryHeading;
	
	@FindBy(css ="button[data-track='Checkout - Top']")
	WebElement checkOutButton;
	
	public YourCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Function to validate the your cart page details 
	 */
	public void validateYourCartPage() {
		assertTrue(orderSummaryHeading.isDisplayed(), "User is on the Your cart page");
		assertEquals(orderSummaryHeading.getText(), "Order Summary");
	}
}
