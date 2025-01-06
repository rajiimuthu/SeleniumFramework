package rahulshettyacademy.Tests;
import rahulshettyacademy.PageObjects.*;
import rahulshettyacademy.PageObjects.CheckOutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.OrderPage;
import rahulshettyacademy.PageObjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrder extends BaseTest{

@Test(groups= {"primary"}, dataProvider="getData")

	public void submitOrderTest(HashMap<String,String> input) throws IOException{
		
		//ExtentTest test = extent.createTest("submitOrder");
		String productName = input.get("product");
		//LandingPage landingpage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));
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
//		test.fail("result mismatch");
//		extent.flush();
		
	}


//@Test(dependsOnMethods= {"submitOrder"})
public void verifyOrders() throws IOException{
	
	
	String productName = "QWERTY";
	//LandingPage landingpage = launchApplication();
	ProductCatalogue productCatalogue = landingPage.loginApp("rajimuthu@gmail.com", "Pass@123");
	OrderPage orderPage = productCatalogue.goToOrdersPage();
	Assert.assertTrue(orderPage.verifyOrdersPageDetails(productName));
		

}

@DataProvider
public Object[][] getData() throws IOException {
	//String jsonContent = FileUtils.readFileToString(new File("), StandardCharsets.UTF_8);
	List<HashMap<String,String>> mapList = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//TestData//Data.json");
	return new Object[][] {{mapList.get(0)},{mapList.get(1)}};
}



}
