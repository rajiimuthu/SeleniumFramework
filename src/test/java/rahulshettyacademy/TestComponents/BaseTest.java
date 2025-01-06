package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	
	public Properties propfileAccess() throws IOException {
	Properties prop = new Properties();

	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
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
	


	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToUrl();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		
		  if (driver != null) {
		        driver.quit();
		    }
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	
	//Jackson Databind - converting string to Hashmap
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,String>> mapData = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return mapData;

		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//TestReports//"+testCaseName+".png");
		FileUtils.copyFile(source, dest );
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
}
