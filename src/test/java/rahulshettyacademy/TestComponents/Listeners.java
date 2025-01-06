package rahulshettyacademy.TestComponents;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extentReport = ExtentReportsNG.configs();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {

		test = extentReport.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	@Override 
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");

	}
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		String path=null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			
			path = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test skipped");

	}


	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Optional: can be implemented if needed
	}

	@Override
	public void onStart(ITestContext context) {
		// Optional: can be implemented if needed
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
	}

}