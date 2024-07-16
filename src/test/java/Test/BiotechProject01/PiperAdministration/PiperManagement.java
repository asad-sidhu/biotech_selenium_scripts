package Test.BiotechProject01.PiperAdministration;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import MiscFunctions.DateUtil;
import MiscFunctions.NavigateToScreen;
import Models.PiperManagementModel;
import Models.ReportFilters;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static PageObjects.PiperManagementPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static LogViewFunctions.FilterSort.*;

public class PiperManagement extends BaseTest{

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {
//		spark = new 	ExtentSparkReporter("target/Reports/Piper_Management"+DateUtil.date+".html");
//		spark.config().setReportName("Piper Management Test Report");
		initReport("Piper_Management");
	}

	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}

	
	@Test(priority= 1)
	public void Navigate() throws InterruptedException, IOException {
		NavigateToScreen.navigate(url_entityManagement, "PIPER Management", piperManagementTitle);
	}
	
	
	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		Lock(piperManagementTable, "PIPER Management", 0);
	}
	
	
	@Test (priority = 3, enabled = true) 
	public void WildcardPiper() throws InterruptedException, IOException {
		Wildcard(piperManagementTable, "PIPER Management", 0);
	}

	
	@Test(priority= 4)
	public void FilterSorting() throws InterruptedException, IOException {
		Sorting(piperManagementTable, "PIPER Management", 0);
	}
	
	
	@Test (enabled= true, priority = 5) 
	public void TestSiteFilter() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-PM-011: Verify Test Site Filter Functionality");
			steps = test.createNode(Scenario.class, "Steps");
			results = test.createNode(Scenario.class, "Result");
			steps.createNode("1. Verify Test Site Filter Functionality");
			
			SoftAssert softAssert = new SoftAssert();
			String recordBefore = getDriver().findElement(By.id(ResultsCount)).getText();
			click(By.id("testSite_show-filter"));
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			click(By.cssSelector("th:nth-child(2) tr:nth-child(2) td:nth-child(2) label:nth-child(1)"));
			click(By.cssSelector("th:nth-child(2) .filter-popup__footer--apply"));
			waitElementInvisible(loading_cursor);	
			steps.createNode("2. Click on lock button");	
			getDriver().findElement(By.id(LockFilter)).click();
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);
			String recordsafterfilter = getDriver().findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsafterfilter, recordBefore, "Filter failed to apply");
			getDriver().navigate().refresh();
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			softAssert.assertEquals(getDriver().findElement(By.id(ResultsCount)).getText(), recordsafterfilter, "Lock functionality failed");
			Thread.sleep(1000);
			getDriver().findElement(By.id(UnlockFilter)).click();
			waitElementInvisible(loading_cursor);
			getDriver().findElement(By.cssSelector("th:nth-child(2) .log-header__clear-filter span")).click();
			waitElementInvisible(loading_cursor);
			softAssert.assertAll();
			test.pass("Test Site Filter verified successfully");
			results.createNode("Test Site Filter verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,  null);
		}catch(AssertionError er){
			test.fail("Test Site filter failed to verify");
			results.createNode("Test Site filter failed to verify");
			saveResult(ITestResult.FAILURE,  new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Test Site filter failed to verify");
			results.createNode("Test Site filter failed to verify");
			saveResult(ITestResult.FAILURE,  ex);
		}	
	}
	
	

	@Test (enabled= true, priority = 6) 
	public void ViewSites() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-PM-12: Verify user can view sites", "This test case will verify that user can view sites");
			steps = test.createNode(Scenario.class, "Steps");
			results = test.createNode(Scenario.class, "Result");
			getScreenshot();

			steps.createNode("1. Open configure piper popop");
			steps.createNode("2. Verify sites hierarcy");
			steps.createNode("3. Verify oranization appears in dropdown");
			steps.createNode("4. Verify user can save the settings");
			
			getDriver().get(url_entityManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
			SoftAssert softAssert = new SoftAssert();

		//	String piperName = getDriver().findElement(By.cssSelector("#row-1 #col-0 label")).getText();
			
			scroll(By.cssSelector("td:nth-child(12)"));
			Thread.sleep(1500);
			getDriver().findElement(By.id("edit-piper-2")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(4000);
		//	softAssert.assertEquals(getDriver().findElement(By.id("descId")).getText(), piperName, "Piper Name does not appear in text field");
			getDriver().findElement(By.cssSelector(".b-md:nth-child(1)")).click();
			waitElementInvisible(loading_cursor);
			getDriver().findElement(By.id("btn-show-tree")).click();
			waitElementInvisible(loading_cursor);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector(".popup-heading")).isDisplayed());
			
			getDriver().findElement(By.cssSelector("#orgTypeId .ng-arrow-wrapper")).click();
			waitElementInvisible(loading_cursor);
			if (getDriver().findElement(By.cssSelector(".ng-option:nth-child(1)")).getText() == "BiotechProject01" && getDriver().findElement(By.cssSelector(".ng-option:nth-child(2)")).getText() == "Client" && getDriver().findElement(By.cssSelector(".ng-option:nth-child(3)")).getText() == "Partner" && getDriver().findElement(By.cssSelector(".ng-option:nth-child(4)")).getText() == "Consumer" && getDriver().findElement(By.cssSelector(".ng-option:nth-child(4)")).getText() == "Allied Partner") {
				softAssert.assertTrue(true, "Organization types not displaying in dropdown");
			}
			waitElementInvisible(loading_cursor);
			getDriver().findElement(By.id("btn-cancel-sites")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
		//	getDriver().findElement(By.xpath("//*[text() = ' Submit ']")).click();
		//	wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
		//	softAssert.assertEquals(getDriver().findElement(By.id("message")).getText(), "Testing sites details updated successfully.");
			softAssert.assertAll();
			test.pass("Sites and dropdowns verified successfully");
			results.createNode("Sites and dropdowns verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,  null);
		}catch(AssertionError er){
			test.fail("Sites and dropdowns failed to verify");
			results.createNode("Sites and dropdowns failed to verify");
			saveResult(ITestResult.FAILURE,  new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Sites and dropdowns failed to verify");
			results.createNode("Sites and dropdowns failed to verify");
			saveResult(ITestResult.FAILURE,  ex);
		}	
	}
	
	
	@Test (enabled= true, priority = 7) 
	public void AlertSetting() throws InterruptedException, IOException {

		getDriver().navigate().refresh();
		waitElementInvisible(loading_cursor);
		PiperManagementModel.lstPiperManagementCreate = PiperManagementModel.FillData();
		for (PiperManagementModel objModel : PiperManagementModel.lstPiperManagementCreate) { 
			try{
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, "Pre_Conditions");
				steps = test.createNode(Scenario.class, "Steps");
				results = test.createNode(Scenario.class, "Result");
				getScreenshot();

				preconditions.createNode("1. Go to url " +url_entityManagement+ "and login with valid credentials");
				preconditions.createNode("2. Hover to sidebar to expand menu");
				preconditions.createNode("3. Expand Administration and click on Piper Managment");
				steps.createNode("1. Open Alert Setting popup");
				steps.createNode(objModel.passStep);
				SoftAssert softAssert = new SoftAssert();
				waitElementInvisible(loading_cursor);
				Thread.sleep(2000);
				getDriver().findElement(By.xpath("//button[contains(text(),'Alert Setting')]")).click();
		
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Thread.sleep(2000);
						getDriver().findElement(By.id("programNameId")).clear();
						getDriver().findElement(By.id("programNameId")).sendKeys(objModel.emailList);
						getDriver().findElement(By.id("num-descId")).clear();
						getDriver().findElement(By.id("num-descId")).sendKeys(objModel.hoursList);
						Thread.sleep(2000);
						getDriver().findElement(By.id("btn-save")).click();
						if (objModel.negativeScenario) {
						for (int i =0; i<objFilter.LstFilterValues.size(); i++) {
							
							softAssert.assertEquals(getDriver().findElements(By.xpath(objFilter.LstFilterValues.get(i))).size(), 1);	
						}

						getDriver().findElement(By.id("close-popup-modal")).click();
						Thread.sleep(2000);
						}
						
						if (objModel.positiveScenario) {
							waitElementVisible(alertMessage);
							Thread.sleep(1000);
							softAssert.assertEquals(getDriver().findElement(By.id("message")).getText(), "Alert Settings details updated.");
						}

						softAssert.assertAll();
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						getScreenshot();
						saveResult(ITestResult.SUCCESS,  null);
					}catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE,  new Exception(er));
					}catch(Exception ex){
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE,  ex);
					}
				}
			}	
			catch(Exception ex) {
			}
		}
	}

	@AfterTest
	public static void endreport() {
		extent.flush();
	}
	
}
