package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	public static ExtentReports extent;

	public static ExtentReports configs() {

		ExtentSparkReporter extentSpark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\TestReports\\index.html");
		extentSpark.config().setReportName("Raji's Automation Framework Results");
		extentSpark.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(extentSpark);
		extent.setSystemInfo("Tester", "Rajii");
		return extent;
		

	}
}
