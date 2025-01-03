package rahulshettyacademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rajiacademy.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}
    @FindBy(css=".hero-primary")
    WebElement confoMessage;
    
    By confom = By.cssSelector(".hero-primary");
	
   // String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
    
    public String confirmation() {
    
    	String confirmMessage = confoMessage.getText();
    	return confirmMessage;
    }

}
