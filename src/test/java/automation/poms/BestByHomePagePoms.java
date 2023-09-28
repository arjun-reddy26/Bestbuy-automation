package automation.poms;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BestByHomePagePoms {
	protected static WebDriver driver;
	protected Wait<WebDriver> driverWait;

	public BestByHomePagePoms() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
		if(driver == null) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
			
		
		this.driverWait = new WebDriverWait(driver, 5);
		
		PageFactory.initElements(driver, this);
	}


	public WebDriver getDriver() {
		return driver;
	}
	
	public WebElement waitUntilForElement(Function<WebDriver, WebElement> ec) {
		try {
			WebElement we = driverWait.until(ec);
			return we;
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;	
	}
	
	public List<WebElement> waitUntilForElements(Function<WebDriver, List<WebElement>> ec) {
		List<WebElement> we = driverWait.until(ec);
		return we;
	}
	
	private By searchTextBoxElement = By.id("gh-search-input");
	
	private By searchSubmitButtonElement = By.xpath("//button[@title=\"submit search\"]");
	
	private By preOwnedFilterElement = By.xpath("//fieldset[@name='Condition']//a[text()='Pre-Owned']");
	
	private By mainResultElement = By.id("main-results");
	
	private By searchResultElements = By.xpath("//ol//li[@class='sku-item']");
	
	private By popupDialogElement = By.xpath("//div[contains(@class,'c-modal-grid')]");
	
	private By popupDialogMessageElement = By.className("added-to-cart");
	
	private By closePopupDialogButtonElement = By.xpath("//button[contains(@class,'close-modal-x')]");
	
	private By cartLinkElement = By.xpath("//div[@class='cart-icon']//a[@title='Cart']");
	
	private By cartSummaryElement = By.id("cart-order-summary");
	
	private By cartCheckoutButtonElement = By.xpath("//button[text()='Checkout']");
	
	private By cartItemElements = By.xpath("//ul[@class='item-list']//li//section[@class='card']");
	
	private By totalItemInCartList = By.xpath("//div[@class='cart-icon']//a[@title='Cart']//div");
	
	private By surveyWindowElement = By.id("survey_window");
	
	private By surveyWindowCloseButtonElement = By.id("survey_invite_no");
	
	public WebElement getSearchTextBoxElement() {
		return driver.findElement(searchTextBoxElement);
	}
	
	public WebElement getSearchSubmitButtonElement() {
		return driver.findElement(searchSubmitButtonElement);
	}
	
	public WebElement getPreOwnedFilterElement() {
		return driver.findElement(preOwnedFilterElement);
	}
	
	public WebElement getMainResultDivElement() {
	 WebElement mainResultDiv =	waitUntilForElement(ExpectedConditions.visibilityOfElementLocated(mainResultElement));
	 return mainResultDiv;
	}
	
	public List<WebElement> getSearchResults() {
		List<WebElement> elements = waitUntilForElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResultElements));
		return elements;
	}
	
	public WebElement getPopupDialogBoxElement() {
		 WebElement popupDiv =	waitUntilForElement(ExpectedConditions.presenceOfElementLocated(popupDialogElement));
		 return popupDiv;
	}
	
	public WebElement getAddToCardMessageElement() {
		return waitUntilForElement(ExpectedConditions.visibilityOfElementLocated(popupDialogMessageElement));
	}
	
	public WebElement getClosePopupDialogButtonElement() {
		return waitUntilForElement(ExpectedConditions.visibilityOfElementLocated(closePopupDialogButtonElement));
	}
	
	public WebElement getCartLink() {
		return driver.findElement(cartLinkElement);
	}
	
	public WebElement getCartSummaryElement() {
		return driver.findElement(cartSummaryElement);
	}
	
	public WebElement getCartCheckoutButtonElement() {
		return driver.findElement(cartCheckoutButtonElement);
	}
	
	public List<WebElement> getCartItems() {
		List<WebElement> elements = waitUntilForElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItemElements));
		return elements;
	}
	
	public WebElement totalItemInCartElement() {
		return waitUntilForElement(ExpectedConditions.visibilityOfElementLocated(totalItemInCartList));
	}
	
	public WebElement getSurveyWindowElement() {
		return waitUntilForElement(ExpectedConditions.visibilityOfElementLocated(surveyWindowElement));
	}
	public WebElement getSurveyWindowCloseButtonElement() {
		return waitUntilForElement(ExpectedConditions.visibilityOfElementLocated(surveyWindowCloseButtonElement));
	}
	
}
