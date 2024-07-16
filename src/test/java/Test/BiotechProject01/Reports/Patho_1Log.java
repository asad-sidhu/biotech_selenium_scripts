package Test.BiotechProject01.Reports;


import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import PageObjects.Patho_1LogPage;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static LogViewFunctions.FieldAccess.*;
import static LogViewFunctions.LogCSVExport.CSVAuditExport;
import static LogViewFunctions.LogCSVExport.CSVExport1;
import static LogViewFunctions.LogPNGExport.PNGExport;
import static LogViewFunctions.LogTemplateExport.TemplateExport;
import static LogViewFunctions.ResultDateFilter.DateFilter;
import static LogViewFunctions.ResultDateFilter.DateFilterLock;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;
import static LogViewFunctions.RowsPerPage.RowsPerPage_;
import static LogViewFunctions.SiteNameFilter.SiteName;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.DateUtil.date;
import static PageObjects.Patho_1LogPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static PageObjects.SSMPage.ssmDeepSerotypingLogTable;

public class Patho_1Log extends BaseTest{

	@BeforeTest (groups = {"smoke","regression"})
	public void extent() throws InterruptedException, IOException {
//		spark = new ExtentSparkReporter("target/Reports/Patho_1_Log"+ date+".html");
//		spark.config().setReportName("Patho_1 Log Test Report");
		initReport("Patho_1_Log");

	}

	@BeforeClass (groups = {"smoke","regression"})
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
		openPatho_1LogPage();
	}

	@Test (priority = 2, enabled = true, groups = {"smoke","regression"})
	public void LockFilter() throws InterruptedException, IOException {
		Lock(Patho_1LogTable, "Patho_1 Log", 2);
	}
	

	@Test (priority = 3, enabled = false, groups = "regression")
	public void WildcardCocci() throws InterruptedException, IOException {
		Patho_1LogPage.openPatho_1LogPage();
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Wildcard(Patho_1LogTable, "Patho_1 Log", 2);
	}


	@Test(priority= 4, groups = {"smoke","regression"})
	public void FilterSorting() throws InterruptedException, IOException {
		getDriver().navigate().refresh();
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Sorting(Patho_1LogTable, "Patho_1 Log", 2);
	}
	
	@Test(priority= 5, enabled = true, groups = "regression")
	public void RowsPerPage() throws InterruptedException, IOException {
		RowsPerPage1(Patho_1LogTable);
	}

	@Test(priority= 6, enabled = true, groups = "regression")
	public void PaginationCocci() throws InterruptedException, IOException {
		getDriver().navigate().refresh();
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Pagination(Patho_1LogTable, "Patho_1 Log");
	}

	@Test(priority= 7, enabled = true, groups = "regression")
	public void FieldAccessCocci() throws InterruptedException, IOException {
		FieldAccessFunctionality(Patho_1LogTable);
	}

	@Test(priority= 8, enabled = true, groups = "regression")
	public void FieldAccessKeyColumns() throws InterruptedException, IOException {
		KeyColumnsCheck(Patho_1LogTable, new String[]{"Result ID", "Result Date"});
	}

	@Test(priority = 9, enabled = true, groups = "regression")
	public void VerifyColumns() throws IOException, InterruptedException {
		VerifyAllColumns(Patho_1LogTable, new String[]{"Lane", "Sample ID", "QC Code", "Result Status", "Total OPG", "Small OPG", "Medium OPG", "Large OPG", "Result", "Assay", "Result ID", "Result Date", "Result Time", "Collection Site Name", "Sample Matrix", "Customer Sample Id", "Date Received", "Cartridge ID", "Instrument ID", "Total Count", "Small Count", "Medium Count", "Large Count", "Mean Intensity", "PIPER User", "Kit Lot", "Improc Version", "Test Site ID", "Test Site Name", "Collection Site Type", "Requested Assay", "Asset ID", "Run Type", "Collection Date", "Collection Time", "Collection Site ID", "Farm", "Complex", "Vaccine Program", "Feed Program", "Asset Day", "Asset Age Unit", "Bioshuttle Program", "Work Order", "Result Unit"});
	}

	@Test(enabled = true, priority = 10, groups = "regression")
	public void VerifyResultDateFilter() throws IOException, InterruptedException {
		DateFilter();
	}

	@Test(priority = 11, enabled = true, groups = "regression")
	public void ResultDateFilterLock() throws IOException, InterruptedException {
		DateFilterLock();
	}

	@Test(priority = 12, enabled = true, groups = "regression")
	public void CocciSiteNameFilter() throws IOException {
		SiteName();
	}

	@Test(priority = 15, enabled = true, groups = "regression")
	public void CocciPNGExport() throws IOException, InterruptedException {
		PNGExport("#dc-bar-chart-coci-png img", clPNGFileName);
	}

	@Test(priority = 16, enabled = true, groups = "regression")
	public void CocciCSVExport() throws IOException, InterruptedException {
		CSVExport1("Patho_1 Log", clCSVFileName, Patho_1LogTable, 4, 3);
	}

	@Test(priority = 17, enabled = true, groups = "regression")
	public void CocciCSVAuditExport() throws IOException, InterruptedException {
		CSVAuditExport("Patho_1 Log", clCSVAuditFileName, Patho_1LogTable, true);
	}

	@Test(priority = 18, enabled = true, groups = "regression")
	public void CocciTemplateExport() throws InterruptedException, IOException {
		TemplateExport(Patho_1LogPage.clSampleMetaData);
	}

	@Test (enabled= true, priority = 13, groups = "regression")
	public void AllignmentTest() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-CL-180: Verify that int data type columns are right alligned", "This testcase will verify that int data type columns are right alligned");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Patho_1 Log; Patho_1 Log reports open");
			steps.createNode("1. Verify int data type columns are right alligned");
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clLaneCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clTotalOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clSmallOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clMediumOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clLargeOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clTotalCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clSmallCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clMediumCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clLargeCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clMeanIntensityCol+" .text-right")).isDisplayed() == true);
			softAssert.assertAll();
			test.pass("Int data type columns are right alligned");
			results.createNode("Int data type columns are right alligned");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Int data type columns are not right alligned");
			results.createNode("Int data type columns are not right alligned");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			test.fail("Int data type columns are not right alligned");
			results.createNode("Int data type columns are not right alligned");
			saveResult(ITestResult.FAILURE, ex);
		}
	}

	@Test (description="Test Case: IE-9532",enabled= true, priority = 18, groups = "regression")
	public void LockFiltersRefreshPageTestCase() throws  IOException {
		try {
			test = extent.createTest("Verify that the log view will not be blank if the data does not exist in the 3 months data security feature.");
			steps = test.createNode(Scenario.class, Steps);

			steps.createNode("1. Navigate to Patho_1 logs");
			steps.createNode("2. View the latest 3 months data");
			steps.createNode("3. Click on the reset filters button");

			SoftAssert softAssert = new SoftAssert();

			Patho_1LogPage.openPatho_1LogPage();
			waitElementInvisible(loading_cursor);

			click(By.id(ResetFilters));
			waitElementInvisible(loading_cursor);

			steps.createNode("4. Click on Lock button");
			if (getDriver().findElements(By.cssSelector(removeFilterIcon)).size() == 1) {
				click(By.id(LockFilter));
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
			}

			//Apply filter on Customer Sample ID Column

			WebElement filter_scroll = getDriver().findElement(By.id(clCustomerSampleID + "" + clShowFilter));
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
			getDriver().findElement(By.id(clCustomerSampleID + "" + clShowFilter)).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			getDriver().findElement(By.id(clCustomerSampleID + "" + viewAllFilter)).click();
			waitElementInvisible(loading_cursor);
			getDriver().findElement(By.xpath(customerSampleIdFilterLastItem)).click();
			Thread.sleep(1000);
			getDriver().findElement(By.id(clCustomerSampleID + "" + clApplyFilter)).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			String recordsBeforeFilter = getDriver().findElement(By.id(ResultsCount)).getText();
			Thread.sleep(500);
			getScreenshot();
			steps.createNode("6. Refresh current page");
			getDriver().navigate().refresh();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			String recordsAfterFilter = getDriver().findElement(By.id(ResultsCount)).getText();

			softAssert.assertEquals(recordsAfterFilter, recordsBeforeFilter);

			click(By.id(ResetFilters));
			waitElementInvisible(loading_cursor);
			getScreenshot();
			softAssert.assertAll();

			test.pass("Lock filters refresh page test case passed successfully");
			saveResult(ITestResult.SUCCESS, null);
		} catch (AssertionError er) {
			test.fail("Lock filters refresh page test case failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		} catch (Exception ex) {
			test.fail("Lock filters refresh page test case failed");
			saveResult(ITestResult.FAILURE, ex);
		}
	}



	@AfterTest (groups = {"smoke","regression"})
	public static void endreport() {
		extent.flush();
	}
}
