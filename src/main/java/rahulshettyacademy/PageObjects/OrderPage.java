package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rajiacademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(xpath="//app-myorders//table//tr//td[position() = 2]")
	List<WebElement> ordersList;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;

		public Boolean verifyOrdersPageDetails(String productName) {
			Boolean match = ordersList.stream().anyMatch(order-> order.getText().equalsIgnoreCase(productName));
			return match;
		}
		
}
