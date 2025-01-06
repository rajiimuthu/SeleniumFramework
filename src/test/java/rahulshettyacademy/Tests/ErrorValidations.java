package rahulshettyacademy.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.RetryTest;

public class ErrorValidations extends BaseTest{

	@Test(groups= {"Regression"},retryAnalyzer=RetryTest.class)
	public void loginErrorValidation() throws IOException {
		
		//String productName = "QWERTY";
		//LandingPage landingpage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApp("rajimuthu123@gmail.com", "Pass@123");
		Assert.assertEquals("Incorrect email or password..", landingPage.getErrorMessage());

	}

}
