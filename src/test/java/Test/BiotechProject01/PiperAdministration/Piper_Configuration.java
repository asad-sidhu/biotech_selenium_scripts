package Test.BiotechProject01.PiperAdministration;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
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
import MiscFunctions.NavigateToScreen;
import Models.PiperConfigurationModel;
import Models.ReportFilters;
import PageObjects.PiperConfigurationsPage;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.*;

public class Piper_Configuration extends BaseTest{


	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		//spark = new ExtentSparkReporter("target/Reports/Piper_Configuration"+date+".html");
		//spark.config().setReportName("Piper Configuration Test Report");
		initReport("Piper_Configuration");
	}

	
	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}
	
	
	@Test (priority = 1) 
	public void Navigate() throws InterruptedException, IOException {
		NavigateToScreen.navigate(url_configurationAdmin, "PIPER Configuration Management", PiperConfigurationsPage.piperConfigurationTitle);
	}


	@Test (enabled= true, priority = 2) 
	public void CreatePiperConfigSalm() throws InterruptedException, IOException {
				
		String minMeanError = null;
		String maxMeanError = null;
		String minStdError = null;
		String maxStdError = null;
		
		PiperConfigurationModel.lstPiperConfigurationCreate = PiperConfigurationModel.FillData();
		waitElementInvisible(loading_cursor);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		getDriver().findElement(By.cssSelector("#PathogenName  .ng-arrow-wrapper")).click();
		Thread.sleep(1000);
		getDriver().findElement(By.cssSelector("#PathogenName input")).sendKeys("Patho_2");
		getDriver().findElement(By.cssSelector("#PathogenName input")).sendKeys(Keys.ENTER);
		getDriver().findElement(By.id("create-installation-run")).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		getDriver().findElement(By.cssSelector("#ImprocName img")).click();
		Thread.sleep(1000);
		getDriver().findElement(By.cssSelector("#ImprocName ul")).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		getDriver().findElement(By.cssSelector("#ImprocVersion input")).sendKeys(date1001+"."+date1001+"."+date1001);
		getDriver().findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
		Thread.sleep(1000);
		Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Invalid Version. Please enter a valid 4-digit version i.e. 11.22.33.44");
		getDriver().findElement(alertMessageClose).click();
		getDriver().findElement(By.cssSelector("#ImprocVersion input")).clear();
		getDriver().findElement(By.cssSelector("#ImprocVersion input")).sendKeys(date1001+"."+date1001+"."+date1001+"."+date1001);
		
		if (getDriver().findElements(By.xpath("//b[contains(text(),'"+date1001+"."+date1001+"."+date1001+"."+date1001+"')]")).size() == 1) {
			getDriver().findElement(By.xpath("//b[contains(text(),'"+date1001+"."+date1001+"."+date1001+"."+date1001+"')]")).click();
		}
		if (getDriver().findElements(By.xpath("//*[contains(text(),'Add New +')]")).size() == 1) {
			getDriver().findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
		}	

	//	getDriver().findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
		Thread.sleep(1000);

		for (PiperConfigurationModel objModel : PiperConfigurationModel.lstPiperConfigurationCreate) { 
			try{
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_loginPage);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Navigate to Piper Configuration Management screen");
				steps.createNode("1. Click on create new button next to Installation Run Config");
				steps.createNode("2. Select improc name and improc version from dropdown");
				SoftAssert softAssert = new SoftAssert();
				
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Thread.sleep(2000);
						getDriver().findElement(By.id("MinMeanVal")).clear();
						getDriver().findElement(By.id("MinMeanVal")).sendKeys(objFilter.MinMean);
						getDriver().findElement(By.id("MaxMeanVal")).clear();
						getDriver().findElement(By.id("MaxMeanVal")).sendKeys(objFilter.MaxMean);
						getDriver().findElement(By.id("MinStdVal")).clear();
						getDriver().findElement(By.id("MinStdVal")).sendKeys(objFilter.MinStd);
						getDriver().findElement(By.id("MaxStdVal")).clear();
						getDriver().findElement(By.id("MaxStdVal")).sendKeys(objFilter.MaxStd);
						getDriver().findElement(By.id("MaxMeanVal")).click();
						getScreenshot();
	
						steps.createNode("3. "+objModel.steps);
						Thread.sleep(1000);
						if (getDriver().findElement(By.id("MinMeanVal")).getAttribute("value").isEmpty())
						{
							minMeanError = getDriver().findElement(By.xpath("//div[contains(text(), ' Min. mean is required ')]")).getText();
							softAssert.assertEquals(minMeanError, " Min. mean is required "); 
						}

						if (getDriver().findElement(By.id("MaxMeanVal")).getAttribute("value").isEmpty())
						{
							maxMeanError = getDriver().findElement(By.xpath("//div[contains(text(), ' Max. mean is required ')]")).getText();
							softAssert.assertEquals(maxMeanError, " Max. mean is required "); 
						}

						if (getDriver().findElement(By.id("MinStdVal")).getAttribute("value").isEmpty())
						{
							minStdError = getDriver().findElement(By.xpath("//div[contains(text(), ' Min. Std. deviation is required ')]")).getText();
							softAssert.assertEquals(minStdError, " Min. Std. deviation is required "); 
						}

						if (getDriver().findElement(By.id("MaxStdVal")).getAttribute("value").isEmpty())
						{
							maxStdError = getDriver().findElement(By.xpath("//div[contains(text(), ' Max. Std. deviation is required ')]")).getText();
							softAssert.assertEquals(maxStdError, " Max. Std. deviation is required ");
						}

						if(objModel.GreaterLesserCheck) {
							maxMeanError = getDriver().findElement(By.xpath("//div[contains(text(), 'Max. mean must be greater than Min. mean')]")).getText();
							softAssert.assertEquals(maxMeanError, " Max. mean must be greater than Min. mean "); 

							maxStdError = getDriver().findElement(By.xpath("//div[contains(text(), 'Max. Std. deviation must be greater than Min. Std. deviation')]")).getText();
							softAssert.assertEquals(maxStdError, " Max. Std. deviation must be greater than Min. Std. deviation ");
						}

						else if (!getDriver().findElement(By.id("MinMeanVal")).getAttribute("value").isEmpty() && 
								!getDriver().findElement(By.id("MaxMeanVal")).getAttribute("value").isEmpty() && 
								!getDriver().findElement(By.id("MinStdVal")).getAttribute("value").isEmpty() && 
								!getDriver().findElement(By.id("MaxMeanVal")).getAttribute("value").isEmpty())
						{
							getDriver().findElement(By.id("btn-save")).click();
							getScreenshot();
							waitElementVisible(alertMessage);
							Thread.sleep(1500);
							String message = getDriver().findElement(alertMessage).getText();
							getScreenshot();
							getDriver().findElement(alertMessageClose).click();				
							softAssert.assertEquals(message, "Installation Run Configuration saved successfully");							
						}
						softAssert.assertAll();
						Thread.sleep(1000);
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);					
						saveResult(ITestResult.SUCCESS, null);				
					}catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, new Exception(er));
					}catch(Exception ex){
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, ex);
					}
				}
			}	
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority = 3) 
	public void CreatePiperConfigCocci() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-PCM-06: Verify user can create Patho_1 configuration");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Select improc name and improc version from dropdown and click on save button");
			getDriver().get(url_configurationAdmin);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			getDriver().findElement(By.cssSelector("#PathogenName  .ng-arrow-wrapper")).click();
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#PathogenName input")).sendKeys("Patho_1");
			getDriver().findElement(By.cssSelector("#PathogenName input")).sendKeys(Keys.ENTER);
			getDriver().findElement(By.id("create-installation-run")).click();
			Thread.sleep(2000);
			getDriver().findElement(By.cssSelector("#ImprocName img")).click();
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#ImprocName ul")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			getDriver().findElement(By.cssSelector("#ImprocVersion input")).clear();
			getDriver().findElement(By.cssSelector("#ImprocVersion input")).sendKeys(date1001+"."+date1001+"."+date1001);
			getDriver().findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Invalid Version. Please enter a valid 4-digit version i.e. 11.22.33.44");
			getDriver().findElement(By.cssSelector("#ImprocVersion input")).clear();
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#ImprocVersion input")).sendKeys(date1001+"."+date1001+"."+date1001+"."+date1001);
			
			if (getDriver().findElements(By.xpath("//b[contains(text(),'"+date1001+"."+date1001+"."+date1001+"."+date1001+"')]")).size() == 1) {
				getDriver().findElement(By.xpath("//b[contains(text(),'"+date1001+"."+date1001+"."+date1001+"."+date1001+"')]")).click();
			}
			if (getDriver().findElements(By.xpath("//*[contains(text(),'Add New +')]")).size() == 1) {
				getDriver().findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
			}	
			
			Thread.sleep(1000);
			getDriver().findElement(By.id("MinMeanVal")).clear();
			getDriver().findElement(By.id("MinMeanVal")).sendKeys("1");
			getDriver().findElement(By.id("MaxMeanVal")).clear();
			getDriver().findElement(By.id("MaxMeanVal")).sendKeys("1600");
			getDriver().findElement(By.id("MinStdVal")).clear();
			getDriver().findElement(By.id("MinStdVal")).sendKeys("1");
			getDriver().findElement(By.id("MaxStdVal")).clear();
			getDriver().findElement(By.id("MaxStdVal")).sendKeys("1"+date0);
			Thread.sleep(1000);
			getDriver().findElement(By.id("MaxMeanVal")).click();
			getScreenshot();
			getDriver().findElement(By.id("btn-save")).click();

			waitElementVisible(alertMessage);
			Thread.sleep(1500);
			String message = getDriver().findElement(alertMessage).getText();
			getScreenshot();
			getDriver().findElement(alertMessageClose).click();
			Assert.assertEquals(message, "Installation Run Configuration saved successfully");
			test.pass("Patho_1 configuration created successfully");
			results.createNode("Patho_1 configuration created successfully");
		
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Patho_1 configuration failed");
			results.createNode("Patho_1 configuration failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			test.fail("Patho_1 configuration failed");
			results.createNode("Patho_1 configuration failed");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority = 4) 
	public void VerifyDropdownsDisabled() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-PCM-07: Verify dropdowns are disabled in update mode");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Select improc name and improc version from dropdown");

			getDriver().get(url_configurationAdmin);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			for (int i = 1; i<=1000; i++) {
				Thread.sleep(1000);
				if (getDriver().findElement(By.cssSelector("#installation-"+i+" td:nth-child(3)")).getText().equals("1"+date0)) {
					int j = i-2;
					if (getDriver().findElements(By.id("edit-installation-"+j)).size() != 0) {
					WebElement scroll = getDriver().findElement(By.id("edit-installation-"+j));
					((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Thread.sleep(1000);
					}
					getDriver().findElement(By.id("edit-installation-"+i)).click();
					break;
				}
			}

			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			Assert.assertTrue(getDriver().findElements(By.cssSelector("#ImprocName.cursor-not-allowed")).size() == 1);
			Assert.assertTrue(getDriver().findElements(By.cssSelector("#ImprocVersion.cursor-not-allowed")).size() == 1);
			test.pass("Dropdowns were disabled in update mode");
			results.createNode("Dropdowns were disabled in update mode");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Dropdowns were not disabled in update mode");
			results.createNode("Dropdowns were not disabled in update mode");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			test.fail("Dropdowns were not disabled in update mode");
			results.createNode("Dropdowns were not disabled in update mode");
			saveResult(ITestResult.FAILURE, ex);
		}
	}
		
	
	@Test (enabled= true, priority = 5) 
	public void UpdatePiperConfig() throws InterruptedException, IOException {		
		try{
			test = extent.createTest("AN-PCM-08: Verify piper configuration can be updated");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Select improc name and improc version from dropdown");
			Thread.sleep(1500);
			getDriver().findElement(By.id("MinStdVal")).clear();
			getDriver().findElement(By.id("MinStdVal")).sendKeys("500");
			getDriver().findElement(By.id("btn-save")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);	
			String message = getDriver().findElement(By.id("message")).getText();
			getScreenshot();
			getDriver().findElement(alertMessageClose).click();
			Assert.assertEquals(message, "Installation Run Configuration saved successfully");
			test.pass("Installation Run Configuration updated successfully");
			results.createNode("Installation Run Configuration updated successfully");
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Installation Run Configuration failed to update");
			results.createNode("Installation Run Configuration failed to update");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			test.fail("Installation Run Configuration failed to update");
			results.createNode("Installation Run Configuration failed to update");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority = 6) 
	public void DeletePiperConfigSalm() throws InterruptedException, IOException {

		try{
			test = extent.createTest("AN-PCM-09: Verify user can delete Patho_2 piper configuration");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Select improc name and improc version from dropdown");

			
			for (int i = 1; i<=1000; i++) {
				if (getDriver().findElement(By.cssSelector("#installation-"+i+" td:nth-child(3)")).getText().equals("1"+date0)) {
					Thread.sleep(1000);
					int j= i-2;
					if (getDriver().findElements(By.id("edit-installation-"+j)).size() != 0) {
					WebElement scroll = getDriver().findElement(By.id("edit-installation-"+j));
					((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					}
					getDriver().findElement(By.id("delete-installation-"+i)).click();
					break;
				}
			}

			waitElementVisible(popupYesButton);
			getDriver().findElement(By.id("btn-yes")).click();
			waitElementVisible(alertMessage);
			Thread.sleep(1500);
			String message = getDriver().findElement(By.id("message")).getText();
			Assert.assertEquals(message, "Installation Run Configuration details deleted.");
			getDriver().findElement(alertMessageClose).click();
			test.pass("Piper configuration deleted successfully");
			results.createNode("Piper configuration deleted successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Piper configuration failed to delete");
			results.createNode("Piper configuration failed to delete");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			test.fail("Piper configuration failed to delete");
			results.createNode("Piper configuration failed to delete");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority = 7) 
	public void DeletePiperConfigCocci() throws InterruptedException, IOException {
		try{
//			getDriver().get(url_piperConfiguration);
//			waitElementInvisible(loading_cursor);
//			Thread.sleep(1000);
			test = extent.createTest("AN-PCM-10: Verify user can delete Patho_1 piper configuration");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Select improc name and improc version from dropdown");
			Thread.sleep(2000);
			for (int i = 1; i<=1000; i++) {
				if (getDriver().findElement(By.cssSelector("#installation-"+i+" td:nth-child(6)")).getText().equals("1"+date0)) {
					int j= i-2;
					Thread.sleep(1000);
					WebElement scroll = getDriver().findElement(By.id("edit-installation-"+j));
					((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					getDriver().findElement(By.id("delete-installation-"+i)).click();
					break;
				}
			}

			waitElementVisible(popupYesButton);
			getDriver().findElement(By.id("btn-yes")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);	
			String message = getDriver().findElement(By.id("message")).getText();
			getScreenshot();
			getDriver().findElement(alertMessageClose).click();
			Assert.assertEquals(message, "Installation Run Configuration details deleted.");	
			test.pass("Piper configuration deleted successfully");
			results.createNode("Piper configuration deleted successfully");
			
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Piper configuration failed to delete");
			results.createNode("Piper configuration failed to delete");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			test.fail("Piper configuration failed to delete");
			results.createNode("Piper configuration failed to delete");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority = 8) 
	public void PAConnfigImprocCheck() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-PCM-11: Verify created imrpoc is displayed in P/A Configurations");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Create new Improc");
			steps.createNode("3. Verify created imrpoc in P/A configurations");

			getDriver().findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Patho_2");
			getDriver().findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
			getDriver().findElement(By.id("create-mpn")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#sampleMatrix3LId input")).click();
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#ImprocVersion3LId input")).click();
			getDriver().findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys(date1001+"."+date1001+"."+date1001+"."+date1001);
			//getDriver().findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys("59.59.59.59");
			Thread.sleep(1500);
			getScreenshot();
			Assert.assertEquals(getDriver().findElements(By.cssSelector(".ng-option-disabled")).size(), 0);
			getDriver().findElement(By.cssSelector("#close-popup-modal p")).click();

			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#PathogenNameConfig input")).clear();
			getDriver().findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Listeria");
			getDriver().findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			getDriver().findElement(By.id("create-mpn")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			getDriver().findElement(By.cssSelector("#sampleMatrixId input")).click();
			Thread.sleep(1500);
			getDriver().findElement(By.cssSelector("#sampleMatrixId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			getDriver().findElement(By.cssSelector("#ImprocVersionId input")).sendKeys(date1001+"."+date1001+"."+date1001+"."+date1001);
			Thread.sleep(3000);
			getScreenshot();
			Assert.assertEquals(getDriver().findElements(By.cssSelector(".ng-option-disabled")).size(), 0);

			test.pass("Created improc displayed in P/A Configurations successfully");
			results.createNode("Created improc displayed in P/A Configurations successfully");
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Created improc failed to display in P/A Configurations");
			results.createNode("Created improc failed to display in P/A Configurations");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			test.fail("Created improc failed to display in P/A Configurations");
			results.createNode("Created improc failed to display in P/A Configurations");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority = 9) 
	public void CreatePAConfigSalm() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-PCM-12: Verify user can create P/A MPN Configuration for Patho_2");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Click on create new button next to MPN P/A Config");
			steps.createNode("2. Select improc name and improc version from dropdown and click on save button");

			getDriver().get(url_configurationAdmin);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			getDriver().findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Patho_2");
			getDriver().findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			getDriver().findElement(By.id("create-mpn")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			getDriver().findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys("AT_SMatrix_Salm_"+date0);
			Thread.sleep(1000);
			
			if (getDriver().findElements(By.cssSelector(".fa-trash")).size() == 1) {
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector(".fa-trash")).click();
			Thread.sleep(1000);
			getDriver().findElement(popupYesButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Sample Matrix details deleted.");
			}
			
			getDriver().findElement(By.id("dilution-factor-var")).click();
			Thread.sleep(1000);
			getDriver().findElement(By.id("newSampleMatrix3LId")).sendKeys("AT_SMatrix_Salm_"+date0);
			
			
			getDriver().findElement(By.cssSelector(".m-l-5px#btn-save")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys("AT_SMatrix_Salm_"+date0);
			getDriver().findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			getScreenshot();
		//	getDriver().findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys(PA_ImprocVersionNew);
			getDriver().findElement(By.cssSelector("#ImprocVersion3LId input")).click();
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			getDriver().findElement(By.id("ThresholdPAId")).sendKeys("1000");
			getDriver().findElement(By.id("EAIUnit3LId")).sendKeys("100");

			getDriver().findElement(By.cssSelector(".ml-1")).click();
			Thread.sleep(750);
			getDriver().findElement(By.id("constIncolEq1Id")).sendKeys("10");
			getDriver().findElement(By.cssSelector("#MicrobialItemsId1LCCV input")).sendKeys("Piper Count");
			Thread.sleep(750);
			getDriver().findElement(By.cssSelector("#MicrobialItemsId1LCCV input")).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			getDriver().findElement(By.id("constMicrobialEq1Id")).sendKeys("10");
			getDriver().findElement(By.cssSelector("#MicrobialItemsId1LMLCV input")).sendKeys("Piper Count");
			Thread.sleep(750);
			getDriver().findElement(By.cssSelector("#MicrobialItemsId1LMLCV input")).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			getDriver().findElement(By.id("enrichVol1LId")).sendKeys("10");
			getDriver().findElement(By.id("enrichDiluFactor1LId")).sendKeys("10");
			getDriver().findElement(By.id("rinsateVol1LId")).sendKeys("10");
			Thread.sleep(750);

			getDriver().findElement(By.cssSelector(".m-l-10px#btn-save")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(getDriver().findElement(By.id("message")).getText(), "MPN & P/A Configuration saved successfully.");
			getScreenshot();
			getDriver().findElement(alertMessageClose).click();
			softAssert.assertAll();
			
			test.pass("P/A MPN configuration created successfully");
			results.createNode("P/A MPN configuration created successfully");
		
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("P/A MPN configuration failed to create");
			results.createNode("P/A MPN configuration failed to create");
			saveResult(ITestResult.FAILURE,
					new Exception(er)); }
		catch(Exception ex) {
			test.fail("P/A MPN configuration failed to create");
			results.createNode("P/A MPN configuration failed to create");
			saveResult(ITestResult.FAILURE, ex);
		}
	}	 


	@Test (enabled= true, priority = 10, dependsOnMethods = {"CreatePAConfigSalm"}) 
	public void DeletePAConfigSalm() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-PCM-13: Verify user can delete P/A MPN Configuration for Patho_2");
			steps = test.createNode(Scenario.class, Steps);
			steps.createNode("1. Create P/A config");
			steps.createNode("2. Delete that config");
			
			for (int i = 1; i<=1000; i++) {
				if (getDriver().findElement(By.cssSelector("#mpn-"+i+" td:nth-child(4) label")).getText().equals("AT_SMatrix_Salm_"+date0)) {
					Thread.sleep(1000);
					int j = i-2;
					WebElement filter_scroll = getDriver().findElement(By.id("delete-mpn-"+j));
					((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
			//		getDriver().findElement(By.id("delete-mpn-"+i)).click();
					
					try {
						getDriver().findElement(By.id("delete-mpn-"+i)).click();
						waitElementVisible(popupYesButton);
						click(popupYesButton);
						waitElementInvisible(loading_cursor);
						Thread.sleep(1500);
						String message = getDriver().findElement(By.id("message")).getText();
						Assert.assertEquals(message, "MPN & P/A Configuration details deleted.");
						getDriver().findElement(alertMessageClose).click();
						test.pass("P/A MPN configuration deleted successfully");
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
						}
						catch(ElementNotInteractableException ex) {
							test.skip("Cannot be deleted because delete icon is behind Help icon");
							saveResult(ITestResult.SKIP, null);
						}
					break;
				}
			}

		}
		catch(AssertionError er) {
			test.fail("P/A MPN configuration failed to delete");
			saveResult(ITestResult.FAILURE,
					new Exception(er)); }
		catch(Exception ex) {
			test.fail("P/A MPN configuration failed to delete");
			saveResult(ITestResult.FAILURE, ex);
		}
	}	 

	
	@Test (enabled= true, priority = 11) 
	public void CreatePAConfigList() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-PCM-14: Verify user can create P/A MPN Configuration for Listeria");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Click on create new button next to MPN P/A Config");
			steps.createNode("2. Select improc name and improc version from dropdown and click on save button");

			getDriver().get(url_configurationAdmin);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Listeria");
			getDriver().findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			getDriver().findElement(By.id("create-mpn")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			getDriver().findElement(By.cssSelector("#sampleMatrixId input")).sendKeys("AT_SMatrix_List_"+date0);
			Thread.sleep(1500);
			SoftAssert softAssert = new SoftAssert();
			if (getDriver().findElements(By.cssSelector(".fa-trash")).size() == 1) {
			getDriver().findElement(By.cssSelector(".fa-trash")).click();
			Thread.sleep(1000);
			getDriver().findElement(popupYesButton).click();	
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getScreenshot();
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Sample Matrix details deleted.");
			}
			
			getDriver().findElement(By.id("dilution-factor-var")).click();
			Thread.sleep(1000);
			getDriver().findElement(By.id("newSampleMatrixId")).sendKeys("AT_SMatrix_List_"+date0);
			getDriver().findElement(By.cssSelector(".m-l-5px#btn-save")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			getScreenshot();
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New Sample Matrix created.");
			getDriver().findElement(By.cssSelector("#sampleMatrixId input")).sendKeys("AT_SMatrix_List_"+date0);
			Thread.sleep(750);
			getDriver().findElement(By.cssSelector("#sampleMatrixId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			//getDriver().findElement(By.cssSelector("#ImprocVersionId input")).sendKeys(PA_ImprocVersionNew);
			getDriver().findElement(By.cssSelector("#ImprocVersionId input")).click();
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#ImprocVersionId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			getDriver().findElement(By.id("ThresholdId")).sendKeys("1000");
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector(".m-l-10px#btn-save")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getScreenshot();
			softAssert.assertEquals(getDriver().findElement(By.id("message")).getText(), "Listeria Configuration saved successfully");
			getDriver().findElement(alertMessageClose).click();
			softAssert.assertAll();
			test.pass("P/A MPN configuration created successfully");
			results.createNode("P/A MPN configuration created successfully");
		
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("P/A MPN configuration failed to create");
			results.createNode("P/A MPN configuration failed to create");
			saveResult(ITestResult.FAILURE,
					new Exception(er)); }
		catch(Exception ex) {
			test.fail("P/A MPN configuration failed to create");
			results.createNode("P/A MPN configuration failed to create");
			saveResult(ITestResult.FAILURE, ex);
		}
	}	 


	@Test (enabled= true, priority = 12) 
	public void DeletePAConfigList() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-PCM-15: Verify user can delete P/A MPN Configuration for Listeria");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Create P/A config");
			steps.createNode("2. Delete that config");
			
			 for(int i=0; i<2; i++){
				   getDriver().findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
				  }
			 
			 Robot robot = new Robot();
			 for (int i = 0; i < 4; i++) {
				   robot.keyPress(KeyEvent.VK_CONTROL);
				   robot.keyPress(KeyEvent.VK_SUBTRACT);
				   robot.keyRelease(KeyEvent.VK_SUBTRACT);
				   robot.keyRelease(KeyEvent.VK_CONTROL);
				  }
			 Thread.sleep(3000);			 
			
			for (int i = 1; i<=1000; i++) {
				if (getDriver().findElement(By.cssSelector("#mpn-"+i+" td:nth-child(4) label")).getText().equals("AT_SMatrix_List_"+date0)) {
					Thread.sleep(1000);
					int j = i-2;
					WebElement scroll = getDriver().findElement(By.id("delete-mpn-"+j));
					((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Thread.sleep(2000);
					
					try {
					getDriver().findElement(By.id("delete-mpn-"+i)).click();
					waitElementVisible(popupYesButton);
					click(popupYesButton);
					waitElementInvisible(loading_cursor);
					Thread.sleep(1500);
					String message = getDriver().findElement(By.id("message")).getText();
					Assert.assertEquals(message, "Listeria Configuration details deleted");
					getDriver().findElement(alertMessageClose).click();
					test.pass("P/A MPN configuration deleted successfully");
					results.createNode("P/A MPN configuration deleted successfully");
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
					}
					catch(ElementNotInteractableException ex) {
						test.skip("Cannot be deleted because delete icon is behind Help icon");
						saveResult(ITestResult.SKIP, null);
					}
					break;
				}
			}


		}
		catch(AssertionError er) {
			test.fail("P/A MPN configuration failed to delete");
			results.createNode("P/A MPN configuration failed to delete");
			saveResult(ITestResult.FAILURE,
					new Exception(er)); }
		catch(Exception ex) {
			test.fail("P/A MPN configuration failed to delete");
			results.createNode("P/A MPN configuration failed to delete");
			saveResult(ITestResult.FAILURE, ex);
		}
	}	 
	
	@AfterTest
	public static void endreport() {
		extent.flush();
	}
}
