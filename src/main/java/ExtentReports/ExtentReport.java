package ExtentReports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import static MiscFunctions.ExtentVariables.extent;
import MiscFunctions.FrameworkConstants;
import Enums.CategoryType;

import static MiscFunctions.DateUtil.date;


public final class ExtentReport {

	//private ExtentReport() {}

//	private static ExtentReports extent;


	public static void initReports(String classname) {
		if(Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath()); 
			extent.attachReporter(spark);
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("Project123 IE Report");
			spark.config().setReportName(classname+" Test Report");
		}
	}

	public static void initReport(String reportName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-test-output/"+reportName+"_"+date+".html");
		//	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/Reports/"+reportName+"_"+date+".html");
	//	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Project123 Test Report");
		htmlReporter.config().setReportName(reportName+" Test Report");
	}


	public static void flushReports(){
		if(Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentManager.unload();
		try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void createTest(String testcasename) {
		ExtentManager.setExtentTest(extent.createTest(testcasename));
	}

	public static void addAuthors(String[] authors) {
		for(String temp:authors) {
			ExtentManager.getExtentTest().assignAuthor(temp);
		}
	}

	public static void addCategories(CategoryType[] categories) {
		for(CategoryType temp:categories) {
			ExtentManager.getExtentTest().assignCategory(temp.toString());
		}
	}




}
