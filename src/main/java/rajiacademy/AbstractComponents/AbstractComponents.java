package rajiacademy.AbstractComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		//PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartLink;

	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;

	public void browserZoomin(String n) {
		//	javaScriptExecution("document.body.style.zoom='67%'");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.body.style.zoom='"+n+"'");
	}

	public void waitForElementToAppear(By findby) {


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findby));

	}
	public void waitForWebElementToAppear(WebElement findby) {


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));

	}
	public void waitForElementToDisappear(WebElement findby) {


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(findby));

	}

	public CartPage goToCartPage() {

		cartLink.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrderPage goToOrdersPage() {

		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;

	}




}
