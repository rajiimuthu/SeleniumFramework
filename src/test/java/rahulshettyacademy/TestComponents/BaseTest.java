package rahulshettyacademy.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	public Properties propfileAccess() throws IOException {
	Properties prop = new Properties();

	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/rahulshettyacademy/resources/GlobalData.properties");
	prop.load(fis);
	return prop;

	}
	public WebDriver initializeDriver() throws IOException {

		
		String browser = propfileAccess().getProperty("browser");

		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = SingletonWebDriver.getDriver("chrome");
			driver = new ChromeDriver();
		}
		else
		{
			WebDriverManager.edgedriver().setup();
			//driver = SingletonWebDriver.getDriver("edge");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToUrl();
		return landingPage;
	}
	
	@AfterMethod
	public void tearDown() {
		
		  if (driver != null) {
		        driver.quit();
		    }
	}
	
}
