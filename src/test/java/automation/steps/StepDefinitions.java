package automation.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import automation.poms.BestByHomePagePoms;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;


public class StepDefinitions extends BestByHomePagePoms {
	
	public StepDefinitions(){
		super();
	}
	
	@Given("user Open the BestBuy website")
	public void user_open_the_best_buy_website() {  	
		driver.get("https://www.bestbuy.com/");
		
		WebElement surveyWindow = getSurveyWindowElement();
		if(surveyWindow != null && surveyWindow.isDisplayed() && surveyWindow.isEnabled())
			getSurveyWindowCloseButtonElement().click();
	}

	@When("user search for {string} in the top search bar and click the search icon")
	public void user_search_for_in_the_top_search_bar_and_click_the_search_icon(String searchText) {
		getSearchTextBoxElement().sendKeys(searchText);
		getSearchSubmitButtonElement().click();
	}
	
	@Then("user choose Pre-Owned from conditions filter")
	public void user_choose_phone_conditions() {
	  getPreOwnedFilterElement().click();
	}

	@Then("user verify result is present in the page")
	public void user_verify_result_is_present_in_the_page() {
	 Assert.assertEquals(getMainResultDivElement().isDisplayed(),true);
	 Assert.assertEquals(getSearchResults().size()>0,true);
	 
	}
	
	@Then("user find items with {string} {string} and {string} then click Add to Cart")
	public void user_find_items_with_and_click_add_to_cart(String description, String model, String price) {
		boolean itemFound = false;
		for(WebElement element: getSearchResults()) {
			String descriptionText = element.findElement(By.className("sku-title")).getText().toLowerCase();
			String modelText = element.findElement(By.className("sku-value")).getText().toLowerCase();
			String priceText = element.findElement(By.xpath("//div[contains(@class,'priceView-customer-price')]//span[1]")).getText().toLowerCase();
			
			if(descriptionText.equals(description.toLowerCase())&& modelText.equals(model.toLowerCase()) && priceText.equals(price.toLowerCase())) {
				element.findElement(By.xpath("//button[text()=\"Add to Cart\"]")).click();
				itemFound = true;
			}
			
			 Assert.assertEquals(itemFound, true);
				
		}
	}

	@Then("user verify that a popup appears with the text {string}")
	public void user_verify_that_a_popup_appears_with_the_text(String message) {
		Assert.assertEquals(getPopupDialogBoxElement().isDisplayed(),true);
		Assert.assertEquals(getAddToCardMessageElement().getText().equals(message),true);
	}
	
	
	@Then("user close popup dialog")
	public void user_close_popup_dialog() {
		getClosePopupDialogButtonElement().click();
	}
	
	
	@When("user open cart")
	public void user_open_cart() {
	    getCartLink().click();
	}

	@Then("user verify order summary in the cart page")
	public void user_verify_order_summary_in_the_cart_page() {
		Assert.assertEquals(getCartSummaryElement().getText().trim().equals("Order Summary"), true);
		Assert.assertEquals(getCartCheckoutButtonElement().isDisplayed(), true);
	}

	@Then("user verify {string} not present in the cart items")
	public void user_verify_not_present_in_the_cart_items(String string) {
		boolean itemFound = false;
		for(WebElement element: getCartItems()) {
			itemFound = element.findElement(By.className("item-title")).getText().contains(string);
			if(itemFound)
				break;
		}
		Assert.assertEquals(itemFound, false);
	}

	@Then("user change quantity of item {string} to {string}")
	public void user_change_quantity_of_item_to(String string, String string2) {
		
		for(WebElement element: getCartItems()) {
			String itemText = element.findElement(By.className("item-title")).getText();
			if(itemText.contains(string)) {
				 WebElement ddl =  element.findElement(By.xpath("//select[@class='tb-select']"));
				 ddl.click();
				 
				 String optXpaht = String.format("//option[@value=\"%s\"]", string2);
				 WebElement ddlOpt =   ddl.findElement(By.xpath(optXpaht));
				 ddlOpt.click();
				break;
			}
		}
	}
	
	@Then("user verify total items in cart should be {string}")
	public void user_verify_total_items_in_cart_should_be(String string) {
		String totalItemInCart = totalItemInCartElement().getText();
		System.out.println(totalItemInCart);
		Assert.assertEquals(totalItemInCart.equals(string), true);
	}
	
	@Then("user close automation browser window")
	public void user_close_automation_browser_window() {
		driver.quit();
	}
}
