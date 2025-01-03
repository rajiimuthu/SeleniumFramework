package rahulshettyacademy.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SingletonWebDriver {
	
	private static ThreadLocal<WebDriver> driver= new ThreadLocal<>();
	
	private SingletonWebDriver(){
		
	}
	
	public static WebDriver getDriver(String driverType){
		

		if(driver.equals(null)) {
		if(driverType.equalsIgnoreCase("chrome")) {
		driver.set(new ChromeDriver());
		}else if(driverType.equalsIgnoreCase("firefox")){
		driver.set(new FirefoxDriver());
		}else if(driverType.equalsIgnoreCase("edge")){
		driver.set(new EdgeDriver());
		}else {
			System.out.println("Drivertype is incorrect! Please check!!");
		}
		}
		return driver.get();
		
		
	}	
}