package Test.BiotechProject01.Administration;

import java.io.IOException;
import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import Config.ReadPropertyFile;
import MiscFunctions.ClickElement;
import MiscFunctions.DateUtil;
import MiscFunctions.NavigateToScreen;
import PageObjects.UserManagementPage;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static PageObjects.AgreementManagementPage.*;
import static PageObjects.BasePage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static Models.AgreementManagementModel.*;

public class AgreementManagement extends BaseTest{

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
//		spark = new ExtentSparkReporter("target/Reports/Administration_Agreement_Management"+DateUtil.date+".html");
//		spark.config().setReportName("Agreement Management Test Report");
		initReport("Administration_Agreement_Management");
	}

	
	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
		NavigateToScreen.navigate(url_entityManagement, "Agreement Management", amTitle);
	}


	@Test (description="Test Case: Upload file",enabled= true, priority = 1)
	public void LicenseUpload() throws InterruptedException, IOException {

		for (int i=0; i<=lstAgreementManagement.size(); i++) {
			try {
				test = extent.createTest(lstAgreementManagement.get(i).testCaseTitle, lstAgreementManagement.get(i).testCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_entityManagement);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Administration and select Agreement Management");

				Thread.sleep(2000);
				steps.createNode("1. Click on dotted box; file explorer opens");
				steps.createNode("2. Upload "+lstAgreementManagement.get(i).fileType+"and verify the file is uploaded and visible in box");
				getScreenshot();
				getDriver().findElement(By.cssSelector("#file-license")).sendKeys(System.getProperty("user.dir")+lstAgreementManagement.get(i).fileName);

				waitElementVisible(By.id("alrt"));
				Thread.sleep(1000);
				Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), lstAgreementManagement.get(i).alertMessage);

				test.pass(lstAgreementManagement.get(i).passMessage);
				results.createNode(lstAgreementManagement.get(i).passMessage);
				getScreenshot();
				saveResult(ITestResult.SUCCESS,null);	
				getDriver().findElement(By.cssSelector("#alrt > button")).click();
			}
			catch(AssertionError er) {
				test.fail(lstAgreementManagement.get(i).failMessage);
				results.createNode(lstAgreementManagement.get(i).failMessage);
				saveResult(ITestResult.FAILURE,new Exception(er));
			}
			catch(Exception ex) {
				test.fail(lstAgreementManagement.get(i).failMessage);
				results.createNode(lstAgreementManagement.get(i).failMessage);
				saveResult(ITestResult.FAILURE,ex);
			}
		}
	}



	@Test (description="Test Case: Verify uploaded file",enabled= true, priority = 2)
	public void VerifyUploadFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-07: Verify uploaded file is displayed in User Agreement dropdown", "This test case will verify uploaded file on Apply User Agreement page");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");

			steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			getDriver().findElement(By.id("progressbar-2")).click();
			Thread.sleep(500);
			steps.createNode("2. Search for uploaded file in user agreement dropdown");
			getDriver().findElement(By.cssSelector("#ApplyEulaId input")).sendKeys(lstAgreemmentManagementFileName.get(0));
			Thread.sleep(1000);

			Assert.assertEquals(getDriver().findElement(By.xpath(amDropdownSelect)).getText(), lstAgreemmentManagementFileName.get(0));;
			test.pass("The user was able to see the uploaded file in user agreement dropdown successfully");
			results.createNode("The user was able to see the uploaded file in user agreement dropdown successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,null);	
			getDriver().findElement(By.id("progressbar-1")).click();
		}
		catch(AssertionError er) {
			test.fail("The user was not able to see the uploaded file in user agreement dropdown");
			results.createNode("The user was not able to see the uploaded file in user agreement dropdown");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to see the uploaded file in user agreement dropdown");
			results.createNode("The user was not able to see the uploaded file in user agreement dropdown");
			saveResult(ITestResult.FAILURE,ex);
		}
	}


	@Test (description="Test Case: View file",enabled= true, priority = 3)
	public void ViewFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-08: Verify user can view the uploaded file", "This test case will verify that user can view the uploaded file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			steps.createNode("1. Click on eye icon next to uploaded file; uploaded file opens in popup");
			getScreenshot();

			for (int i=1; i<=10; i++) {
				String actualXpath = amBeforelist+i+amAfterList;
				WebElement element = getDriver().findElement(By.xpath(actualXpath));

				int j= i-1;
				if (element.getText().equals(lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(500);
					getDriver().findElement(By.id("view-license-"+j)).click();
					break;
				}
			}

			waitElementVisible(By.id("close-popup-modal"));
			Thread.sleep(1000);
			Assert.assertTrue(getDriver().findElement(By.id("close-popup-modal")).isDisplayed());
			test.pass("The user was able to view the uploaded file successfully");
			results.createNode("The user was able to view the uploaded file successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,null);	
			getDriver().findElement(By.id("close-popup-modal")).click();
		}
		catch(AssertionError er) {
			test.fail("The user was not able to view the uploaded file successfully");
			results.createNode("The user was not able to view the uploaded file successfully");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to view the uploaded file successfully");
			results.createNode("The user was not able to view the uploaded file successfully");
			saveResult(ITestResult.FAILURE,ex);
		}
	}


	@Test (description="Test Case: Delete from File",enabled= true, priority = 4)
	public void DeleteFromFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-09: Verify user can delete the uploaded file from table list", "This test case will verify that user can delete the uploaded file from table list");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			getScreenshot();

			steps.createNode("1. Click on delete icon next to uploaded file in table");
			waitElementVisible(By.id("progressbar-1"));
			Thread.sleep(1000);

			for (int i=1; i<=10; i++) {
				String actualXpath = amBeforelist+i+amAfterList2;
				WebElement element = getDriver().findElement(By.xpath(actualXpath));

				if (element.getText().equals(lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(1500);
					int j= i-1;
					getDriver().findElement(By.id("delete-license-"+j)).click();
					break;
				}
			}

			Thread.sleep(2500);
			steps.createNode("2. Click on yes button from delete confirmation box");
			getDriver().findElement(By.id("btn-yes")).click();
			Thread.sleep(1000); 

			waitElementVisible(alertMessage);
			Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "User agreement details deleted.");;
			test.pass("The user was able to delete the uploaded file from the table below successfully");
			results.createNode("The user was able to delete the uploaded file from the table below successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,null);	
		}
		catch(AssertionError er) {
			test.fail("The user was not able to delete the uploaded file from the table below successfully");
			results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to delete the uploaded file from the table below successfully");
			results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			saveResult(ITestResult.FAILURE,ex);
		}
	}


	@Test (description="Test Case: Delete from Grid",enabled= true, priority = 5)
	public void DeleteFromGrid() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-10: Verify user can delete the uploaded file from grid", "This test case will verify that user can delete the uploaded file from grid");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			getScreenshot();

			steps.createNode("1. Click on delete icon next to uploaded file in table");
			Thread.sleep(1000);

			getDriver().findElement(By.cssSelector("#file-license")).sendKeys(System.getProperty("user.dir")+lstAgreementManagement.get(0).fileName);
			Thread.sleep(2000);


			for (int i=1; i<=15; i++) {
				String actualXpath = amBeforeGrid+i+"]/p[1]";
				WebElement element = getDriver().findElement(By.xpath(actualXpath));

				if (element.getText().equals(lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(500);
					int j = i-1;

					((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", element); 
					Thread.sleep(500);
					getDriver().findElement(By.id("del-license-ic-"+j)).click();
					break;
				}
			}

			Thread.sleep(1000);
			steps.createNode("2. Click on yes button from delete confirmation box");
			getDriver().findElement(By.id("btn-yes")).click();
			Thread.sleep(1000); 

			waitElementVisible(alertMessage);
			Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "User agreement details deleted.");;
			test.pass("The user was able to delete the uploaded file from the table below successfully");
			results.createNode("The user was able to delete the uploaded file from the table below successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,null);	
		}
		catch(AssertionError er) {
			test.fail("The user was not able to delete the uploaded file from the table below successfully");
			results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to delete the uploaded file from the table below successfully");
			results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			saveResult(ITestResult.FAILURE,ex);
		}
	}


	@Test (description="Test Case: Rename file",enabled= true, priority = 6)
	public void RenameFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-11: Verify user can rename the file", "This test case will verify that user can rename the uploaded file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			steps.createNode("1. Click on filename in user agreement name column and rename the file");
			getScreenshot();

			getDriver().findElement(By.cssSelector("#file-license")).sendKeys(System.getProperty("user.dir")+lstAgreementManagement.get(0).fileName);
			Thread.sleep(3000);

			List<WebElement> rows = getDriver().findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
			int count = rows.size();

			for(int j = 1; j<count; j++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)")).getText().equals(lstAgreemmentManagementFileName.get(0))) {

					ClickElement.clickByCss(getDriver(), "tr:nth-child("+j+") td:nth-child(2)");
					Thread.sleep(3000);

					
					for (int i=1; i <=13; i++) {
						getDriver().findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(Keys.BACK_SPACE);
					}
					getDriver().findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(lstAgreemmentManagementFileName.get(1));
					break;
				}
			}

			Thread.sleep(500);
			ClickElement.clickById(getDriver(), "progressbar-1");
			Thread.sleep(1000);

			for(int j = 1; j<count; j++) {

				if (getDriver().findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)")).getText().equals(lstAgreemmentManagementFileName.get(1))) {
					WebElement element = getDriver().findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)"));

					Assert.assertEquals(element.getText(), lstAgreemmentManagementFileName.get(1));
					test.pass("The user was able to rename the uploaded file successfully");
					results.createNode("The user was able to rename the uploaded file successfully");
					getScreenshot();
					saveResult(ITestResult.SUCCESS,null);
				}
			}
		}
		catch(AssertionError er) {
			test.fail("The user was not able to rename the uploaded file successfully");
			results.createNode("The user was not able to rename the uploaded file successfully");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to rename the uploaded file successfully");
			results.createNode("The user was not able to rename the uploaded file successfully");
			saveResult(ITestResult.FAILURE,ex);
		}
	}


	@Test (description="Test Case: Deactivate file",enabled= true, priority = 7)
	public void DeactivateFile() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-License-12: Verify user can deactivate the uploaded file", "This test case will verify that user can deactivate the uploaded file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			steps.createNode("1. Click on deactivate toggle button in Actions column next to uploaded file");
			getScreenshot();

			List<WebElement> rows = getDriver().findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
			int count = rows.size();

			for(int j = 1; j<count; j++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)")).getText().equals(lstAgreemmentManagementFileName.get(1))) {
					Thread.sleep(500);
					getDriver().findElement(By.id("status-license-"+j)).click();
					break;
				}			
			}

			Thread.sleep(1000);
			getDriver().findElement(By.id("btn-yes")).click();

			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "User agreement details updated.");;
			test.pass("The user was able to deactivate the uploaded file successfully");
			results.createNode("The user was able to deactivate the uploaded file successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,null);	
		}
		catch(AssertionError er) {
			test.fail("The user was not able to deactivate the uploaded file successfully");
			results.createNode("The user was not able to deactivate the uploaded file successfully");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to deactivate the uploaded file successfully");
			results.createNode("The user was not able to dactivate the uploaded file successfully");
			saveResult(ITestResult.FAILURE,ex);
		}
	}


	@Test (description="Test Case: Verify Deactivate file",enabled= true, priority = 8)
	public void VerifyDeactivateFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-13: Verify deactivated file is not shown in User Agreement dropdown", "This test case will verify deactivated file is not shown in User Agreement dropdown");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			preconditions.createNode("6. Click on deactivate toggle button in Actions column next to uploaded file");
			getScreenshot();

			steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			WebElement element = getDriver().findElement(By.id("progressbar-2"));
			waitElementClickable(By.id("progressbar-2"));

			((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy(0,-100)");
			Thread.sleep(500);

			ClickElement.clickById(getDriver(), "progressbar-2");
			Thread.sleep(500);
			steps.createNode("2. Search for deactivated file");
			getDriver().findElement(By.cssSelector(".ng-input input")).sendKeys(lstAgreemmentManagementFileName.get(1));
			Thread.sleep(500);

			Assert.assertEquals(getDriver().findElement(By.cssSelector(".ng-option")).getText(), "No items found");
			test.pass("The user was not able to see the deactivated file in User Agreement dropdown successfully");
			results.createNode("The user was not able to see the deactivated file in User Agreement dropdown successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,null);	
		}
		catch(AssertionError er) {
			test.fail("Deactivated file showed in User Agreement dropdown");
			results.createNode("Deactivated file showed in User Agreement dropdown");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Deactivated file showed in User Agreement dropdown");
			results.createNode("Deactivated file showed in User Agreement dropdown");
			saveResult(ITestResult.FAILURE,ex);
		}
		getDriver().findElement(By.id("progressbar-1")).click();
	}


	@Test (description="Test Case: Reactivate file",enabled= true, priority = 9)
	public void ReactivateFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-14: Verify user can reactivate deactivated file", "This test case will verify that user can reactivate deactivated file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			preconditions.createNode("6. Click on deactivate toggle button in Actions column next to uploaded file");
			getScreenshot();

			steps.createNode("1. Click on Activate toggle button again");

			List<WebElement> rows = getDriver().findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
			int count = rows.size();

			for(int j = 1; j<count; j++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)")).getText().equals(lstAgreemmentManagementFileName.get(1))) {
					Thread.sleep(500);
					getDriver().findElement(By.id("status-license-"+j)).click();
					break;
				}			
			}

			Thread.sleep(1000);
			getDriver().findElement(By.id("btn-yes")).click();

			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "User agreement details updated.");;
			test.pass("The user was able to reactivate the deactivated file successfully");
			results.createNode("The user was able to deactivate the uploaded file successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,null);	
		}
		catch(AssertionError er) {
			test.fail("The user was not able to reactivate the deactivated file");
			results.createNode("The user was not able to reactivate the deactivated file");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to reactivate the deactivated file");
			results.createNode("The user was not able to reactivate the deactivated file");
			saveResult(ITestResult.FAILURE,ex);
		}
	}


	@Test (description="Test Case: Verify Reactivate file",enabled= true, priority = 10)
	public void VerifyReactivateFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-15: Verify file is displayed in User Agreement dropdown on reactivating", "This test case will verify file is displayed in User Agreement dropdown on reactivating");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			preconditions.createNode("6. Click on deactivate toggle button in Actions column next to uploaded file");
			preconditions.createNode("7. Again activate the deactivated file");
			getScreenshot();

			steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			WebElement element = getDriver().findElement(By.id("progressbar-2"));
			waitElementClickable(By.id("progressbar-2"));

			((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy(0,-100)");
			Thread.sleep(500);

			ClickElement.clickById(getDriver(), "progressbar-2");
			Thread.sleep(500);
			steps.createNode("2. Search for uploaded file in user agreement dropdown");
			getDriver().findElement(By.cssSelector(".ng-input input")).sendKeys(lstAgreemmentManagementFileName.get(1));
			Thread.sleep(1000);

			Assert.assertEquals(getDriver().findElement(By.cssSelector(".ng-option")).getText(), lstAgreemmentManagementFileName.get(1));
			test.pass("The user was able to see the uploaded file in user agreement dropdown successfully on reactivating the file");
			results.createNode("The user was able to see the uploaded file in user agreement dropdown successfully on reactivating the file");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,null);	

		}
		catch(AssertionError er) {
			test.fail("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			results.createNode("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			results.createNode("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			saveResult(ITestResult.FAILURE,ex);
		}
		getDriver().findElement(By.id("progressbar-1")).click();
	}



	@Test (description="Test Case: Search User",enabled= true, priority = 11)
	public void SearchUser() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-16: Verify search bar is functional", "This test case will verify that user is able to search for a user from search bar");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			getScreenshot();

			steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			getDriver().findElement(By.id("progressbar-2")).click();
			Thread.sleep(500);
			steps.createNode("2. Search for user is search bar");
			getDriver().findElement(By.id("userSearchId")).sendKeys("QA");
			Thread.sleep(500);
			getDriver().findElement(By.id("userSearchId")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			if (Integer.parseInt(getDriver().findElement(By.xpath(amSearchNo)).getText()) >=1) {
				test.pass("The user was able to search for the user in User Agreement page");
				results.createNode("The user was able to search for the user in User Agreement page");
				getScreenshot();
				saveResult(ITestResult.SUCCESS,null);	
			}

		}
		catch(AssertionError er) {
			test.fail("The user was not able to search for the user in User Agreement page");
			results.createNode("The user was not able to search for the user in User Agreement page");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to search for the user in User Agreement page");
			results.createNode("The user was not able to search for the user in User Agreement page");
			saveResult(ITestResult.FAILURE,ex);
		}
		getDriver().findElement(By.id("progressbar-1")).click();
	}


	@Test (description="Test Case: Invalid Selection",enabled= true, priority = 12)
	public void InvalidSelection() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-License-17: Verify user cannot select organization without selecting User Agreement", "This test case will verify that user cannot select organization without selecting User Agreement");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			getScreenshot();
			Thread.sleep(1000);
			steps.createNode("1. Go to apply agreement page");
			ClickElement.clickById(getDriver(), "progressbar-2");
			Thread.sleep(3000);
			steps.createNode("2. Click on organization radio button");

			getDriver().findElement(By.id("ic-orgnType-1")).click();

			waitElementVisible(alertMessage);
			Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "Please select user agreement.");;
			test.pass("The user was not able to select radio button without selecting agreement from dropdown");
			results.createNode("The user was not able to select radio button without selecting agreement from dropdown");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,null);
		}
		catch(AssertionError er) {
			test.fail("The user was able to select radio button without selecting agreement from dropdown");
			results.createNode("The user was able to select radio button without selecting agreement from dropdown");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was able to select radio button without selecting agreement from dropdown");
			results.createNode("The user was able to select radio button without selecting agreement from dropdown");
			saveResult(ITestResult.FAILURE,ex);
		}
		getDriver().findElement(By.id("progressbar-1")).click();
	}	


	@Test (description="Test Case: Deactivate Assign Agreement",enabled= true, priority = 13)
	public void DeactivateAssignAgreement() throws InterruptedException, IOException {

		test = extent.createTest("AN-License-31: Verify user can deactivate assigned Agreement", "This test case will verify that user can deactivate assigned agreement");
		preconditions = test.createNode(Scenario.class, PreConditions);
		steps = test.createNode(Scenario.class, Steps);
		results = test.createNode(Scenario.class, Results);

		preconditions.createNode("1. Go to url " +url_entityManagement);
		preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		preconditions.createNode("3. Hover to sidebar to expand the menu");
		preconditions.createNode("4. Click on Administration and select Agreement Management");
		preconditions.createNode("5. Upload a file");
		preconditions.createNode("6. Go to Apply Agreement page and assign user with an agreement");

		String filename = getDriver().findElement(By.id("eula-name-1")).getText();

		for (int i=0; i<2;i++) {

			waitElementVisible(By.id("progressbar-1"));
			getDriver().findElement(By.id("progressbar-1")).click();
			Thread.sleep(1000);
			steps.createNode("1. Deactivate the assigned agreement");
			if (getDriver().findElements(By.cssSelector("#status-license-1 .wrapper-true")).size() == 1) {
				getDriver().findElement(By.id("status-license-1")).click();
			}
			Thread.sleep(2000);
			getDriver().findElement(By.id("btn-yes")).click();
			try {
				waitElementVisible(alertMessage);
				Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "User agreement details updated.");;
				test.pass("The user was able to deactivate assigned agreement successfully");
				results.createNode("The user was able to deactivate assigned agreement successfully");
				getScreenshot();
				saveResult(ITestResult.SUCCESS,null);
			}
			catch(AssertionError er) {
				test.fail("The user was not able to deactivate assigned agreement");
				results.createNode("The user was not able to deactivate assigned agreement");
				saveResult(ITestResult.FAILURE,new Exception(er));
			}
			catch(Exception ex) {
				test.fail("The user was not able to deactivate assigned agreement");
				results.createNode("The user was not able to deactivate assigned agreement");
				saveResult(ITestResult.FAILURE,ex);
			}


			try {
				test = extent.createTest(lstAgreementManagementDeactivate.get(i).testCaseTitle, lstAgreementManagementDeactivate.get(i).testCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_entityManagement);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Administration and select Agreement Management");
				preconditions.createNode("5. Upload a file");
				preconditions.createNode("6. Go to Apply Agreement page and assign user with an agreement");
				preconditions.createNode("7. Deactivated the uploaded agreement");
				ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
				steps.createNode("1. Go to user management page");
				getDriver().get(url_userManagement);
				waitElementInvisible(loading_cursor);
				Thread.sleep(2500); 

				UserManagementPage.openEditUserPopup(config.ie_username());
				click(popupNextButton);
				Thread.sleep(500);	
				click(popupNextButton);
				Thread.sleep(500);	
				
				steps.createNode("3. Search for the agreement in User Agreement dropdown");
				ClickElement.clickById(getDriver(), "euladdl");
				Thread.sleep(1000);
				SoftAssert softAssert = new SoftAssert();
				getDriver().findElement(By.cssSelector("#euladdl > div > div > div.ng-input > input[type=text]")).sendKeys(filename);
				Thread.sleep(1000); 

				if (i==0) {
					softAssert.assertEquals(getDriver().findElements(By.cssSelector(".ng-option-disabled")).size(), "1", "'No items found' did not displayed on deactivating the agreement");
				}
				if (i==1) {
					softAssert.assertEquals(getDriver().findElements(By.xpath("//*[contains(text(),'"+filename+"')]")).size(), 1, "Agreement did not displayed on deactivating the agreement");
				}
				test.pass(lstAgreementManagementDeactivate.get(i).passMessage);
				results.createNode(lstAgreementManagementDeactivate.get(i).passMessage);
				getScreenshot();
				saveResult(ITestResult.SUCCESS,null);
			}
			catch(AssertionError er) {
				test.fail(lstAgreementManagementDeactivate.get(i).failMessage);
				results.createNode(lstAgreementManagementDeactivate.get(i).failMessage);
				saveResult(ITestResult.FAILURE,new Exception(er));
			}
			catch(Exception ex) {
				test.fail(lstAgreementManagementDeactivate.get(i).failMessage);
				results.createNode(lstAgreementManagementDeactivate.get(i).failMessage);
				saveResult(ITestResult.FAILURE,ex);
			}

			getDriver().get(url_assetAdmin);
			waitElementVisible(By.id("progressbar-1"));
			Thread.sleep(1500);
		}
	}


	@Test (description="Test Case: Delete Assigned File",enabled= true, priority = 14)
	public void DeleteAssignFile() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-License-40: Verify user cannot delete the uploaded file", "This test case will verify that user can delete the uploaded file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_entityManagement);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			getScreenshot();

			steps.createNode("1. Assign the agreement to some user or organization");
			waitElementVisible(By.id("progressbar-1"));
			Thread.sleep(1000);
			getDriver().findElement(By.id("progressbar-1")).click();
			steps.createNode("2. Click on delete icon next to uploaded file in table");
			Thread.sleep(1000);
			getDriver().findElement(By.id("delete-license-0")).click();

			steps.createNode("2. Click on yes button from delete confirmation box");
			getDriver().findElement(By.id("btn-yes")).click();		
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000); 
			Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "This user agreement is already assigned. It cannot be deleted.");;
			test.pass("The user was not able to delete the assigned agreement successfully");
			results.createNode("The user was not able to delete the assigned agreement successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,null);	
		}
		catch(AssertionError er) {
			test.fail("The user was able to delete the assigned agreement");
			results.createNode("The user was able to delete the assigned agreement");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was able to delete the assigned agreement");
			results.createNode("The user was able to delete the assigned agreement");
			saveResult(ITestResult.FAILURE,ex);
		}
	}


	@Test (enabled= true, priority = 15)
	public void DeleteFile() throws InterruptedException, IOException {

		List<WebElement> rows = getDriver().findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
		int count = rows.size();
		for(int j = 1; j<count; j++) {
			if (getDriver().findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)")).getText().equals(lstAgreemmentManagementFileName.get(1))) {
				int x = j-1;
				getDriver().findElement(By.id("delete-license-"+x)).click();
				break;
			}
		}
		Thread.sleep(2000);
		getDriver().findElement(By.id("btn-yes")).click();	
	}
	
	@AfterTest
	public static void endreport() {
		extent.flush();
	}
	
}
