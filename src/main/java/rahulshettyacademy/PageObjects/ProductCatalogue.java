package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rajiacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css="b")
	WebElement product;
	
	By productsBy = By.cssSelector(".mb-3");
	By productBy = By.cssSelector("b");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.cssSelector("#toast-container");
	By animator = By.cssSelector(".ng-animating");
	
		public List<WebElement> getProdList() {
			waitForElementToAppear(productsBy);
			return products;
		}
		
		public WebElement getProductbyName(String productName) {
			WebElement prod = getProdList().stream().filter(p-> p.findElement(productBy).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
			return prod;
		}
		
		public void addProdTotheCart(String productName) {
			WebElement prod = getProductbyName(productName);
			prod.findElement(addToCart).click();
			waitForElementToAppear(toastContainer);
			waitForElementToDisappear(driver.findElement(animator));
		}


}
