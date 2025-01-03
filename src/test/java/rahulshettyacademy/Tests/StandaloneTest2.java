package rahulshettyacademy.Tests;
import io.github.bonigarcia.wdm.WebDriverManager;

import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckOutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.ProductCatalogue;
import rahulshettyacademy.Utils.ExcelReaderUtility;
import rahulshettyacademy.Utils.SingletonWebDriver;
import rajiacademy.AbstractComponents.ProductDetails;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class StandaloneTest2{

	public static void main(String[] args){
		
		
//		ExcelReaderUtility testData = new ExcelReaderUtility();
//		List<ProductDetails> testDataList = testData.getDataFromExcel("C:\\Users\\rajes\\OneDrive\\Desktop\\TestDataSelenium.xlsx");
//		System.out.println(testDataList);
		
		//String browser = "chrome";
		String productName = "QWERTY";
		String country = "India";
		WebDriverManager.chromedriver().setup();
	//	WebDriver driver = SingletonWebDriver.getDriver(testDataList.get(0).getBrowser());
		
		WebDriver driver = SingletonWebDriver.getDriver("chrome");
		LandingPage landingPage = new LandingPage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		ProductCatalogue productCatalogue = landingPage.loginApp("rajimuthu@gmail.com", "Pass@123");
		List<WebElement> products = productCatalogue.getProdList();		
		//WebElement prod = products.getProductbyName(productName);
	//	productCatalogue.addProdTotheCart(testDataList.get(0).getProductName());
		productCatalogue.addProdTotheCart(productName);
		System.out.println("Product added to cart successfully");
		CartPage cartPage = productCatalogue.goToCartPage();
	//	Boolean match = cartPage.verifyCartDetails(testDataList.get(0).getProductName());
		Boolean match = cartPage.verifyCartDetails(productName);
		Assert.assertTrue(match);
		CheckOutPage checkout = cartPage.checkOut();
	//	checkout.selectCountry(testDataList.get(0).getCountry());
		checkout.selectCountry(country);
		ConfirmationPage ca = checkout.submitOrder();
		Assert.assertTrue(ca.confirmation().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}
