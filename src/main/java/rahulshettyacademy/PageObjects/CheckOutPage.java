package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rajiacademy.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	WebDriver driver;
	
	CheckOutPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	By countryResults = By.cssSelector(".ta-results");
	
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement countryselection;
	
	@FindBy(css=".action__submit")
	WebElement placeOderButton;
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);

        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(countryResults);
        countryselection.click();
        
	}

	public ConfirmationPage submitOrder() {
		placeOderButton.click();
		ConfirmationPage confirmation = new ConfirmationPage(driver);
		return confirmation;
	}

}
