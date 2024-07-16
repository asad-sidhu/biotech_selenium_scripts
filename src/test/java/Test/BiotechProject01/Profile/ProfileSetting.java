package Test.BiotechProject01.Profile;

import java.io.IOException;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.Methods;
import PageObjects.BasePage;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.ExtentVariables.*;
import static Models.ProfileModel.*;


public class ProfileSetting extends BaseTest{

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {
		//spark = new ExtentSparkReporter("target/Reports/Profile_Setting"+DateUtil.date+".html");
		//spark.config().setReportName("Profile Setting Test Report");
		initReport("Profile_Setting");

	}

	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}
	
	@Test (enabled= true, priority = 2) 
	public void NavigateProfile() throws InterruptedException, IOException {
		try{
			for(int i=0; i<lstProfileNavigate.size();i++) {
				try {
					test = extent.createTest(lstProfileNavigate.get(i).testCaseNavigate);

					preconditions = test.createNode(Scenario.class, PreConditions);
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					preconditions.createNode("1. Go to url " +Constants.url_loginPage);
					preconditions.createNode("2. Login with valid credentials; user navigates to home page");
					steps.createNode(lstProfileNavigate.get(i).stepPage);
					steps.createNode("2. Click on Profile Settings icon on top right of screen");

					getDriver().get(lstProfileNavigate.get(i).url);
					Methods.waitElementInvisible(BasePage.loading_cursor);
					Methods.waitElementInvisible(BasePage.loading_cursor);
					Methods.waitElementClickable(By.id("open-profile"));
					Methods.getScreenshot();
					getDriver().findElement(By.id("open-profile")).click();
					Methods.waitElementInvisible(BasePage.loading_cursor);
					Methods.waitElementVisible(By.id("btn-save-2"));
					Thread.sleep(500);

					Assert.assertTrue(getDriver().findElement(By.id("firstNameId")).isDisplayed());
					getDriver().findElement(By.id("close-profile")).click();
					Methods.waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(500);
					Assert.assertEquals(getDriver().findElement(By.cssSelector("#screen-header p")).getText(), lstProfileNavigate.get(i).pageTitle);

					test.pass("User navigated successfully to Profile Setting page");
					results.createNode("User navigates to Profile Setting page");
					Methods.getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
				}
				catch(AssertionError er) {
					test.fail("User did not navigated to Profile Setting page");
					results.createNode("User did not navigated to Profile Setting page");
					saveResult(ITestResult.FAILURE, new Exception(er));
				}			
			}
		}
		catch(Exception ex){
			test.fail("User did not navigated to Profile Setting page");
			results.createNode("User did not navigated to Profile Setting page");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}


	

	@Test (enabled= true, priority = 4) 
	public void ExitProfile() throws InterruptedException, IOException {

		test = extent.createTest("AN-PS-24: Exit Profile Screen", "This test case will verify user can exit profile screen" );
		preconditions = test.createNode(Scenario.class, PreConditions);
		steps = test.createNode(Scenario.class, Steps);
		results = test.createNode(Scenario.class, Results);

		preconditions.createNode("1. Go to url " +Constants.url_loginPage);
		preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		steps.createNode("1. Click on Profile Setting icon on top right of screen; Profile setting page opens");
		steps.createNode("2. Again click on it");
		getDriver().get(Constants.url_userManagement);
		Methods.waitElementInvisible(BasePage.loading_cursor);
		Thread.sleep(1000);
		getDriver().findElement(By.id("open-profile")).click();
		test.createNode("Click on Profile Setting button");
		Methods.waitElementInvisible(BasePage.loading_cursor);
		Thread.sleep(1000);
		Methods.getScreenshot();
		getDriver().findElement(By.id("close-profile")).click();
		Methods.waitElementInvisible(BasePage.loading_cursor);
		Thread.sleep(1000);

		try{
			Assert.assertEquals(getDriver().findElement(By.id("User Management")).getText(), "User Management"); 
			test.pass("User successfully closed profile page");
			results.createNode("User successfully closed profile page");
			Methods.getScreenshot();
		}catch(AssertionError e){
			test.fail("User failed to close profile page");
			results.createNode("User failed to close profile page");
		}	

	}


	@AfterTest
	public static void endreport() {
		extent.flush();
	}
}
