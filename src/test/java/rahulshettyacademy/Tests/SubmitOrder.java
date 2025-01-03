package rahulshettyacademy.Tests;
import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckOutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.OrderPage;
import rahulshettyacademy.PageObjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitOrder extends BaseTest{

@Test

	public void submitOrder() throws IOException{
		
		String productName = "QWERTY";
		//LandingPage landingpage = launchApplication();
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
		checkout.selectCountry("India");
		ConfirmationPage ca = checkout.submitOrder();
		Assert.assertTrue(ca.confirmation().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}


@Test(dependsOnMethods= {"submitOrder"})
public void verifyOrders() throws IOException{
	
	
	String productName = "QWERTY";
	//LandingPage landingpage = launchApplication();
	ProductCatalogue productCatalogue = landingPage.loginApp("rajimuthu@gmail.com", "Pass@123");
	OrderPage orderPage = productCatalogue.goToOrdersPage();
	Assert.assertTrue(orderPage.verifyOrdersPageDetails(productName));
		

}



}
