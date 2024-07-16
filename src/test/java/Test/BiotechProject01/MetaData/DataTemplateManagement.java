package Test.BiotechProject01.MetaData;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import MiscFunctions.FrameworkConstants;
import MiscFunctions.Methods;
import MiscFunctions.DateUtil;
import MiscFunctions.NavigateToScreen;
import Models.DataTemplateModel;
import PageObjects.UserManagementPage;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static PageObjects.DataTemplateManagementPage.*;
import static PageObjects.BasePage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static Models.DataTemplateModel.*;
import static MiscFunctions.Methods.*;

public class DataTemplateManagement extends BaseTest{


	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		//spark = new ExtentSparkReporter("target/Reports/MetaData_Template_Management"+DateUtil.date+".html");
		//spark.config().setReportName("Data Template Management Test Report");
		initReport("MetaData_Template_Management");

	}

	
	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		System.out.println("Test 1: "+Thread.currentThread().getId());
		LoginTest.login();
	}
	
	
	@Test(priority= 1, enabled = true)
	public void Navigate() throws InterruptedException, IOException {
		NavigateToScreen.navigate(url_templateAdmin, "Data Template Management", dataTemplateTitle);
	}
	

	@Test (description="Exceptional Flow: Reset field check", enabled= true, priority= 2) 
	public void ResetFieldCheck() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-DTM-02: Verify user can reset Create Template fields");

			click(dtmCreateButton);
			waitElementInvisible(loading_cursor);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			type(dtmName, "Test");
			Thread.sleep(1000);
			type(dtmDesc, "Description");
			Thread.sleep(1000);
			getScreenshot();
			click(popupResetButton);
			Thread.sleep(1500);

			String nameActual = getDriver().findElement(dtmName).getText();
			String descActual = getDriver().findElement(dtmDesc).getText();	

			Assert.assertTrue(nameActual.isEmpty(), "Name field was not reset");
			Assert.assertTrue(descActual.isEmpty(), "Description field was not reset");
			test.pass("Template fields reset successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Template fields failed to reset");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Template fields failed to reset");
			saveResult(ITestResult.FAILURE, ex);
		}
	}	


	@Test (description="Exceptional Flow: Column Reset field check", enabled= true, priority= 3) 
	public void ClmResetFieldCheck() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-DTM-03: Verify user can reset Template Column fields");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Click on Create New button");
			steps.createNode("2. Enter data in template column fields");
			steps.createNode("3. Click on reset button");

			getDriver().findElement(dtmClmName).sendKeys("Test");
			Thread.sleep(750);
			getDriver().findElement(dtmClmDefaultValue).sendKeys("100");
			Thread.sleep(750);
			getDriver().findElement(dtmClmLength).sendKeys("100");
			Thread.sleep(750);
			getScreenshot();
			getDriver().findElement(By.id("btn-reset-field")).click();
			Thread.sleep(1000);

			String nameActual = getDriver().findElement(By.id("ColNameID")).getText();
			String defaultValueActual = getDriver().findElement(By.id("defaultValueId")).getText();	
			String lengthActual = getDriver().findElement(By.id("colLengthId")).getText();

			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(nameActual.isEmpty(), "Name field was not reset");
			softAssert.assertTrue(defaultValueActual.isEmpty(), "Description field was not reset");
			softAssert.assertTrue(lengthActual.isEmpty(), "Description field was not reset");
			softAssert.assertAll();
			test.pass("Template fields reset successfully");
			results.createNode("Template fields reset successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Template column fields failed to reset");
			results.createNode("Template fields failed to reset");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Template column fields failed to reset");
			results.createNode("Template fields failed to reset");
			saveResult(ITestResult.FAILURE, ex);
		}
	}	



	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 4) 
	public void MandatoryFieldCheck() throws InterruptedException, IOException {

//		getDriver().get(url_templateAdmin);
//		waitElementInvisible(loading_cursor);
//		Thread.sleep(2500);
//		click(dtmCreateButton);
//		waitElementInvisible(loading_cursor);
//		Thread.sleep(1000);

		for (DataTemplateModel objModel : lstDTMMandatoryCheck) {

			test = extent.createTest(objModel.testcaseTitle, objModel.testcaseDesc);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode(objModel.step1);
			steps.createNode("2. Click on save button");

			getDriver().findElement(popupResetButton).click();
			getDriver().findElement(dtmName).sendKeys(objModel.dtmName);
			Thread.sleep(1000);
			getDriver().findElement(dtmDesc).sendKeys(objModel.dtmDesc);	
			Thread.sleep(1000);

			getScreenshot();
			getDriver().findElement(popupSaveButton).click(); 
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			if (getDriver().findElements(alertMessage).size() != 0) {
				Actions actions = new Actions(getDriver());
				actions.moveToElement(getDriver().findElement(alertMessageClose)).click().perform();
			}


			try {

				if (objModel.chkMandatoryFieldsS1)
				{
					if ( objModel.dtmName.isEmpty())
					{
						Assert.assertEquals(getDriver().findElements(dtmNameValidation).size(), 1); 
					}

					if ( objModel.dtmDesc.isEmpty())
					{
						Assert.assertEquals(getDriver().findElements(dtmDescValidation).size(), 1); 
					}

					test.pass(objModel.passScenario);
					results.createNode(objModel.passScenario);
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
					continue;
				}

			}catch(AssertionError er) {
				test.fail("Mandatory check failed");
				results.createNode("Mandatory check failed");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Mandatory check failed");
				results.createNode("Mandatory check failed");
				saveResult(ITestResult.FAILURE, ex);
			}


			if (objModel.chkAlert)
			{
				Methods.waitElementVisible(alertMessage);
				Thread.sleep(1000);
				
				String actual = getDriver().findElement(By.id("message")).getText();
				String expected = "Please select one column as key field";

				try{
					Assert.assertEquals(actual, expected); 
					test.pass("Not able to add template without atleast one column successfully");
					results.createNode(objModel.passScenario);
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
				}catch(AssertionError e){
					test.fail("Not Able to add template without atleast one column failed");
					saveResult(ITestResult.FAILURE, new Exception(e));
				}
				catch(Exception ex) {
					test.fail("Not Able to add template without atleast one column failed");
					saveResult(ITestResult.FAILURE, ex);
				}
			}

			try {
				if (objModel.chkClm)
				{

					Thread.sleep(1000);
					getDriver().findElement(By.id("btn-reset-field")).click();
					getDriver().findElement(dtmClmName).sendKeys(objModel.clmName);
					Thread.sleep(1000);

					if(objModel.clmType) {
						getDriver().findElement(By.id("ColTypeId")).click();
						Thread.sleep(1000);
						getDriver().findElement(By.cssSelector(".ng-option:nth-child(1)")).click();			
					}

					getDriver().findElement(By.id("num-colLengthId")).sendKeys(objModel.clmLength);
					Thread.sleep(1000);

					waitElementVisible(By.id("btn-add-field"));

					getDriver().findElement(By.id("btn-add-field")).click(); 
					Thread.sleep(1000);

				}
				if (objModel.chkS2)
				{

					if ( objModel.clmName.isEmpty())
					{
						Assert.assertEquals(getDriver().findElements(dtmNameValidation).size(), 1); 
					}

					if ( objModel.clmLength.isEmpty())
					{
						Assert.assertEquals(getDriver().findElements(dtmNameValidation).size(), 1); 
					}
					test.pass(objModel.passScenario);
					results.createNode(objModel.passScenario);
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
					continue;
				}
			}
			catch(AssertionError e){
				test.fail(objModel.failScenario);
				results.createNode(objModel.failScenario);
				saveResult(ITestResult.FAILURE, new Exception(e));
			}
			catch(Exception ex){
				test.fail(objModel.failScenario);
				results.createNode(objModel.failScenario);
				saveResult(ITestResult.FAILURE, ex);
			}


			if (objModel.verifyClm) {

				WebElement filter_scroll = getDriver().findElement(By.id("edit-field-1"));
				((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 

				int actual =	getDriver().findElements(By.cssSelector(".dataformat-table-container tr")).size();
				try{
					Assert.assertEquals(actual, 2); 
					test.pass("Column added successfully");
					results.createNode(objModel.passScenario);
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
				}catch(AssertionError e){
					test.fail("Column adding failed");
					saveResult(ITestResult.FAILURE, new Exception(e));
				}
				catch(Exception ex){
					test.fail("Column adding failed");
					results.createNode("Column adding failed");
					saveResult(ITestResult.FAILURE, ex);
				}

			}		
		}
	}


	@Test (description="Test Case: Create Template Column",enabled= true, priority= 5) 
	public void CreateTemplate() throws InterruptedException, IOException {
		try {		
			test = extent.createTest("AN-DTM-10: Verify that user is able to create new templete", "This testcase will verify that user is able to create template");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			steps.createNode("1. Verify that the Result Found label shows the right count of the existing templates on the screen");
			steps.createNode("2. Click on Create New button");
			steps.createNode("3. Add valid data in all fields");
			steps.createNode("4. Add a new column");
			steps.createNode("5. Click on save button");

			SoftAssert softAssert = new SoftAssert();
			getDriver().get(url_templateAdmin);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			int rowsActual = getDriver().findElements(By.cssSelector("tr")).size();
			String rowsExpected = getDriver().findElement(By.id("results-found-count")).getText();
			softAssert.assertEquals(rowsActual-1, Integer.parseInt(rowsExpected), "Does not match the expected rows");

			getDriver().findElement(dtmCreateButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			getDriver().findElement(popupResetButton).click();
			getDriver().findElement(dtmName).sendKeys(TemplateName);
			Thread.sleep(1000);
			getDriver().findElement(dtmDesc).sendKeys("Test Template created by Automation script");

			getDriver().findElement(dtmClmReset).click();
			getDriver().findElement(dtmClmName).sendKeys("ID");
			getDriver().findElement(dtmClmType).click();
			Thread.sleep(1000);

			String[] stringArray = {"Date", "String", "Decimal", "Time", "Integer"};
			List<WebElement> op = getDriver().findElements(By.cssSelector(".ng-option-label"));
			int size = op.size();
			for(int i =0; i<size ; i++){
				String options = op.get(i).getText();
			//	System.out.println("Options: "+options);
				softAssert.assertEquals(options, stringArray[i]);
			}

			getDriver().findElement(By.cssSelector(".ng-option:nth-child(1)")).click();
			getDriver().findElement(dtmClmLength).clear();
			getDriver().findElement(dtmClmLength).sendKeys("25");	
			getDriver().findElement(dtmClmAdd).click();
			getDriver().findElement(popupSaveButton).click();
			Thread.sleep(1000);

			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Please select one column as Key Field");
			getDriver().findElement(dtmClmEdit).click();
			Thread.sleep(1000);
			getDriver().findElement(dtmKeyField).click();
			getDriver().findElement(dtmClmSave).click();
			Thread.sleep(1000);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New data template created."); 
			softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) label")).getText(), DateUtil.dateMMDDYYYY1);
			softAssert.assertAll();
			test.pass("New Template created successfully");
			results.createNode("New Template created successfully; displays  an alert message 'New data template created.'");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("New Template failed to create");
			results.createNode("New Template failed to create");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("New Template failed to create");
			results.createNode("New Template failed to create");
			saveResult(ITestResult.FAILURE, ex);
		}
	//	getDriver().findElement(alertMessageClose).click();
	}




	@Test (description="Test Case: Update Template Column",enabled= true, priority= 6) 
	public void UpdateTemplateColumn() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-DTM-11: Verify user can update Template Column", "This test case will verify that user can update templates column");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			preconditions.createNode("5. Create a new template");
			steps.createNode("1. Click on update icon next to created template to open template in update mode");
			steps.createNode("2. Make changes in template column");
			steps.createNode("3. Click on save button");

			for (int i=1;i<getDriver().findElements(By.cssSelector("tr")).size(); i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(TemplateName)) {
					WebElement filter_scroll = getDriver().findElement(By.id("edit-data-format-"+i));
					((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					getDriver().findElement(By.id("edit-data-format-"+i)).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					break;
				}
			}	

			WebElement filter_scroll = getDriver().findElement(dtmClmEdit);
			((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
			Thread.sleep(1000);
			getDriver().findElement(dtmClmEdit).click();
			Thread.sleep(1000);
			getDriver().findElement(dtmClmDefaultValue).clear();  
			getDriver().findElement(dtmClmDefaultValue).sendKeys("10");  
			Thread.sleep(1000);
			getDriver().findElement(dtmClmSave).click();
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(By.cssSelector(".dataformat-table-container tr:nth-child(1) td:nth-child(5)")).getText(), "10"); 
			test.pass("Template column updated successfully");
			results.createNode("Template column updated successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Template column failed to update");
			results.createNode("Template column failed to update");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Template column failed to update");
			results.createNode("Template column failed to update");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Delete Column Template", enabled= true, priority= 7) 
	public void DeleteColumnTemplate() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-DTM-12: Verify user can delete Template Column", "This test case will verify that user can update template column");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			preconditions.createNode("5. Create a new template");
			steps.createNode("1. Click on update icon next to created template to open template in update mode");
			steps.createNode("2. Click on delete icon next to created template column");
			steps.createNode("3. Confirmation popup appears");
			steps.createNode("4. Click on yes button");

			WebElement filter_scroll = getDriver().findElement(dtmClmDelete);
			((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
			getDriver().findElement(dtmClmDelete).click();  //click on edit template column
			Thread.sleep(2000);
			getScreenshot();
			getDriver().findElement(popupYesButton).click();

			Assert.assertEquals(getDriver().findElements(By.cssSelector(".dataformat-table-container tr")).size(), 1); 
			test.pass("Template column updated successfully");
			results.createNode("Template column updated successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Template column failed to delete");
			results.createNode("Template column failed to delete");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Template column failed to delete");
			results.createNode("Template column failed to delete");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Update Template", enabled= true, priority= 8) 
	public void UpdateTemplate() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-DTM-13: Verify user can update Template", "This test case will verify that user can update template");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			preconditions.createNode("5. Create a new template");
			steps.createNode("1. Click on update icon next to created template to open template in update mode");
			steps.createNode("2. Make changes in template name or description");
			steps.createNode("3. Click on save button");

			waitElementInvisible(loading_cursor);	
			getDriver().findElement(dtmClmReset).click();
			getDriver().findElement(dtmClmName).sendKeys("ID");
			getDriver().findElement(dtmClmType).click();
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector(".ng-option:nth-child(2)")).click();	
			getDriver().findElement(dtmClmLength).sendKeys("25");
			Thread.sleep(1000);
			getDriver().findElement(dtmKeyField).click();
			Thread.sleep(1000);
			getDriver().findElement(dtmClmAdd).click();
			Thread.sleep(1000);

			//add identification column
			getDriver().findElement(dtmClmName).sendKeys("Name");
			getDriver().findElement(dtmClmType).click();
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector(".ng-option:nth-child(2)")).click();	
			Thread.sleep(1000);
			getDriver().findElement(dtmIdentificationField).click();
			Thread.sleep(1000);
			getDriver().findElement(dtmClmAdd).click();
			Thread.sleep(1000);

			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data template details updated."); 
			test.pass("Template updated successfully");
			results.createNode("Template updated successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Template failed to update");
			results.createNode("Template failed to update");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Template failed to update");
			results.createNode("Template failed to update");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@SuppressWarnings("resource")
	@Test (description="Test Case: ExportTemplate",enabled= true, priority= 9) 
	public void ExportTemplate() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-DTM-14: Verify user can Export Template", "This test case will verify that user can export a Template");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			preconditions.createNode("5. Create a new template");
			steps.createNode("1. Click on update icon next to created template to open template in update mode");
			steps.createNode("2. Click on export button");

			int totalRows = getDriver().findElements(By.cssSelector("tr")).size();
			for (int i=1; i<totalRows; i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(TemplateName)) {
					WebElement filter_scroll = getDriver().findElement(By.id("delete-data-format-"+i));
					((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					getDriver().findElement(By.id("edit-data-format-"+i)).click();
					break;
				}
			}

			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector(".export-csv-Asset")).click();
			Thread.sleep(1000);
			getDriver().findElement(By.id("export-records")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(4000);

			File downloadFolder = new File(fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
			if(namesOfFiles.contains(TemplateName+".xlsx")) {	
			//	System.out.println("Success");
				Assert.assertTrue(true);
				test.pass("Template exported successfully");
				results.createNode("Template exporteed successfully");
				getScreenshot();
				saveResult(ITestResult.SUCCESS, null);
			}


			/////////////////////////////////////////////////////////////

			File file = new File(fileDownloadPath+"\\"+TemplateName+".xlsx");
			if(file.exists()){
				System.out.println("File Exists");
			}	

			SoftAssert softAssert = new SoftAssert();
			String path = fileDownloadPath+"\\"+TemplateName+".xlsx";
			FileInputStream fs = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(0);
			softAssert.assertEquals(cell.toString(), "ID");
			cell = row.getCell(1);
			softAssert.assertEquals(cell, null);		
			softAssert.assertAll();
			///////////////////////////////////////////////////////////////////////////


		}catch(AssertionError er){
			test.fail("Template failed to export");
			results.createNode("Template failed to export");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Template failed to export");
			results.createNode("Template failed to export");
			saveResult(ITestResult.FAILURE, ex);
		}
		finally {
			File file = new File(fileDownloadPath+"\\"+TemplateName+".xlsx"); 
			if(file.delete())
				System.out.println("CSV file deleted");
		}
	}


	@Test (description="Test Case: IdentificationColumn",enabled= true, priority= 10) 
	public void IdentificationColumnTest() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-DTM-15: Verify identification column is not mandatory", "This test case will verify that identification column is not mandatory");

			getDriver().get(url_dataAdmin);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			getDriver().findElement(By.id("OrgnTypeID")).click();
			getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("BiotechProject01");
			getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			getDriver().findElement(By.id("DataFormatId")).click();
			getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(TemplateName);
			getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
			SoftAssert softAssert = new SoftAssert();
			getDriver().findElement(By.id("file-input")).sendKeys(FrameworkConstants.DataTemplateIdentificationColumnCheckFile);
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			getScreenshot();
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "IndentificationFieldCheck.xlsx loaded successfully.", "File failed to load");
			getDriver().findElement(By.cssSelector(".fa-save")).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			getScreenshot();
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "IndentificationFieldCheck.xlsx saved successfully.", "File failed to save; message not appeared");
			softAssert.assertAll();
			test.pass("Identification field check verified successfully");
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Identification field check failed to verify");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Identification field check failed to verify");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Inactivate Template",enabled= true, priority= 11) 
	public void InActivateTemplate() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-DTM-16: Verify inactivated template is not displayed in data upload screen", "This test case will verify that inactivated template is not displayed in data upload screen");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			preconditions.createNode("5. Create a new template");
			steps.createNode("1. Inactivate the template");
			steps.createNode("2. Go to data upload screen to verify that template is not visible there");

			getDriver().get(url_templateAdmin);
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);

			int totalRows = getDriver().findElements(By.cssSelector("tr")).size();
			for (int i=1; i<totalRows; i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(TemplateName)) {
					WebElement filter_scroll = getDriver().findElement(By.id("delete-data-format-"+i));
					((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					getDriver().findElement(By.id("edit-data-format-"+i)).click();
					break;
				}
			}

			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(dtmInactivateTemplate).click();
			getDriver().findElement(popupSaveButton).click();

			waitElementInvisible(loading_cursor);	
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data template details updated."); 

			getDriver().get(url_dataAdmin);
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);

			getDriver().findElement(By.id("OrgnTypeID")).click();
			getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("BiotechProject01");
			getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			getDriver().findElement(By.id("DataFormatId")).click();
			getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(TemplateName);
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElements(By.cssSelector(".ng-option-disabled")).size(), 1);
			test.pass("Template was inactivated successfully");
			results.createNode("Template was inactivated successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Template was not inactivated");
			results.createNode("Template was not inactivated");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Template was not inactivated");
			results.createNode("Template was not inactivated");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Delete Template",enabled= true, priority= 12) 
	public void DeleteTemplate() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-DTM-17: Verify user can delete Template", "This test case will verify that user can delete a Template");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			preconditions.createNode("5. Create a new template");
			steps.createNode("1. Click on delete icon next to created template; confirmation box popups");
			steps.createNode("2. Click on yes button");

			getDriver().get(url_templateAdmin);
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);

			int totalRows = getDriver().findElements(By.cssSelector("tr")).size();
			for (int i=1; i<totalRows; i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(TemplateName)) {
					WebElement filter_scroll = getDriver().findElement(By.id("delete-data-format-"+i));
					((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					getDriver().findElement(By.id("delete-data-format-"+i)).click();
					break;
				}
			}

			Thread.sleep(1000);
			getDriver().findElement(popupYesButton).click();
			waitElementInvisible(loading_cursor);	
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data template details deleted."); 
			test.pass("Template deleted successfully");
			results.createNode("Template deleted successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Failed to delete the Template");
			results.createNode("Failed to delete the Template");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Failed to delete the Template");
			results.createNode("Failed to delete the Template");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority= 13) 
	public void EndOfAssetColumnsVerification() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-DTM-18: Verify end of Asset column verfication");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			steps.createNode("1. Open EOF Template");
			steps.createNode("2. Verify columns and template names");

			SoftAssert softAssert = new SoftAssert();
			
			getDriver().get(url_templateAdmin);
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);
			
			for(int i=1; i<=Integer.parseInt(getDriver().findElement(By.id(ResultsCount)).getText()); i++) {

				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals("End of Asset Template")) {
					getDriver().findElement(By.id("edit-data-format-"+i)).click();
					waitElementInvisible(loading_cursor);	
					Thread.sleep(2000);
				}
			}

			List<String> columnName = Arrays.asList("", "UNIQUE_Asset_ID", "INTEGRATOR_Asset_ID", "FARM_NAME", "FARM_SITE_ID", "WEEKLY_FARM_RANK", 
					"HISTORICAL_FARM_COST_VARIANCE", "WEEKLY_FARM_COST_VARIANCE", "HATCH_DATE", "deployment_DATE", "DAYS_OUT", "AGE_OF_LITTER", 
					"AVG_SOLD_AGE", "NUM_animalS_SOLD", "deployment_DENSITY", "PROCESSING_DATE", "PROCESSING_SITE_ID", "USDA_PLANT_ID", "PLANT_LOCATION", 
					"NUM_animalS_PROCESSED", "AVG_animal_WEIGHT_LBS", "AVG_animal_WEIGHT_KGS", "AVG_DAILY_WEIGHT_GAIN_LB", "AVG_DAILY_WEIGHT_GAIN_KG", 
					"TOTAL_WEIGHT_PROCESSED_LB", "TOTAL_WEIGHT_PROCESSED_KG", "TOTAL_FEED_WEIGHT_LB", "TOTAL_FEED_WEIGHT_KG", "FCR", "FCR_ADJ",
					"FEED_COST_PER_LIVE_POUND", "MEDICATION_COST_PER_LIVE_POUND", "GROWER_COST_PER_LIVE_POUND", "LIVABILITY_PERCENTAGE", 
					"OVERALL_MORTALITY_PERCENTAGE", "WEEK_1_MORTALITY", "WEEK_2_MORTALITY", "WEEK_3_MORTALITY", "WEEK_4_MORTALITY", "WEEK_5_MORTALITY",
					"WEEK_6_MORTALITY", "WEEK_7_MORTALITY", "WEEK_8_MORTALITY", "WEEK_9_MORTALITY", "NUM_animalS_DOA_PLANT", "TOTAL_WEIGHT_CONDEMNED_LB",
					"TOTAL_WEIGHT_CONDEMNED_KG", "NUM_animalS_CONDEMNED_WHOLE", "PARTS_WEIGHT_CONDEMNED_LB", "PARTS_WEIGHT_CONDEMNED_KG", "kCAL_PER_POUND",
					"A_GRADE_PAWS_PERC", "AIRSAC_PERCENTAGE", "IP_PERCENTAGE", "LEUKOSIS_PERCENTAGE", "SEPTOX_PERCENTAGE", "TUMOR_PERCENTAGE");
			
			List<String> columnType = Arrays.asList("", "String", "String", "String","Integer", "Integer", "Decimal", "Decimal", "Date", "Date", "Integer",
					  "Integer", "Integer", "Integer", "Decimal", "Date", "Integer", "String", "String", "Integer", "Decimal", "Decimal", "Decimal",
					  "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal",
					  "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Integer", "Decimal", "Decimal",
					  "Integer", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal", "Decimal");
		    
			for(int i=1;i<columnName.size(); i++) {
				softAssert.assertEquals(getDriver().findElement(By.cssSelector(".table-resp tr:nth-child("+i+") td:nth-child(3)  label")).getText(), columnName.get(i), getDriver().findElement(By.cssSelector(".table-resp tr:nth-child("+i+") td:nth-child(2)  label")).getText());
				softAssert.assertEquals(getDriver().findElement(By.cssSelector(".table-resp tr:nth-child("+i+") td:nth-child(4)  label")).getText(), columnType.get(i), getDriver().findElement(By.cssSelector(".table-resp tr:nth-child("+i+") td:nth-child(2)  label")).getText());
			}

			getDriver().findElement(popupCloseButton).click();
			softAssert.assertAll();
			test.pass("EOF template columns verified successfully");
			results.createNode("EOF template columns verified successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("EOF template columns failed to verify successfully");
			results.createNode("EOF template columns failed to verify successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("EOF template columns failed to verify successfully");
			results.createNode("EOF template columns failed to verify successfully");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority= 14) 
	public void VerifyClientMappingPopup() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-DTM-19: Verify client mapping popup opens and user is able to see all template list", "This test case will verify that client mapping popup opens and user is able to see all template list");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Open Client mapping popup");
			steps.createNode("2. Verify created template along with all other template are displayed in the list");

			SoftAssert softAssert = new SoftAssert();

			List<WebElement> templateNamesTable = getDriver().findElements(By.cssSelector("tr td:nth-child(1) label"));
			int excludeBulkSiteTemplates = templateNamesTable.size() - 5;

			getDriver().findElement(dtmClientMappingOpenButton).click();
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);
			getScreenshot();	
			List<WebElement> templateNamesClientMapping = getDriver().findElements(By.cssSelector(".popup-content tr td:nth-child(2) label"));
			
			softAssert.assertEquals(templateNamesClientMapping.size(), excludeBulkSiteTemplates);
			softAssert.assertAll();
			test.pass("Client mapping popup opened successfully and displayed all templates");
			results.createNode("Client mapping popup opened successfully and displayed all templates");	
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Client mapping popup didnot opened successfully or did not displayed all templates");
			results.createNode("Client mapping popup didnot opened successfully or did not displayed all templates");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Client mapping popup didnot opened successfully or did not displayed all templates");
			results.createNode("Client mapping popup didnot opened successfully or did not displayed all templates");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority= 15) 
	public void VerifyClientMappingList() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-DTM-20: Verify client dropdown in client mapping displays all clients assigned to user", "This test case will verify that client dropdown in client mapping displays all clients assigned to user");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Create a new template");
			steps.createNode("1. Open Client mapping popup");
			steps.createNode("2. Verify client dropdown in client mapping displays all clients assigned to user");		
			
			ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
			
			SoftAssert softAssert = new SoftAssert();

			UserManagementPage.openEditUserPopup(config.ie_username());
			
			getDriver().findElement(By.id("btn-next")).click();
			Thread.sleep(1000);
			getDriver().findElement(By.id("btn-next")).click();
			getScreenshot();	
			int userClientSites = getDriver().findElements(By.cssSelector(".site-tree-card")).size() - 1;
			System.out.println("User client sites: "+userClientSites);
			Thread.sleep(1000);
			
			getDriver().get(url_templateAdmin);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
			List<WebElement> templateNamesTable = getDriver().findElements(By.cssSelector("tr td:nth-child(1) label"));
			click(dtmClientMappingOpenButton);
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);
			click(dtmClientMappingClientDropdown);
			Thread.sleep(1000);
			getScreenshot();	
			int MappingClientSites = size(By.cssSelector(".ng-option"));
			//check if all clients displays in dropdown which are assign to user
			
			System.out.println("Mapping: "+ MappingClientSites);
		//	softAssert.assertEquals(userClientSites, MappingClientSites);
			
			click(dtmClientMappingClientDropdown);
			Thread.sleep(1000);			
			
			if (size(By.cssSelector(".ng-option:nth-child(1) .ng-option-label")) != 0) {
			
			
			for (int j =1; j<=MappingClientSites; j++) {
				click(dtmClientMappingClientDropdown);
				Thread.sleep(1000);
				
				click(By.cssSelector(".ng-option:nth-child("+j+") .ng-option-label"));
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				List<WebElement> templateNamesClientMapping = getDriver().findElements(By.cssSelector(".popup-content tr td:nth-child(2) label"));
				int i = templateNamesClientMapping.size()+1;
		
				System.out.println(templateNamesTable.size()+""+" :: "+i);
				
				softAssert.assertEquals(templateNamesTable.size(), i);
				
			}

			if (getDriver().findElement(By.id("isCreate0")).isSelected() == false) {
				click(By.cssSelector(".popup-content tr:nth-child(1) td:nth-child(3) .custom-checkbox"));
			}
			if (getDriver().findElement(By.id("isCreate1")).isSelected() == false) {
				click(By.cssSelector(".popup-content tr:nth-child(2) td:nth-child(3) .custom-checkbox"));
			}
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);
			softAssert.assertEquals(getText(alertMessage), "Client mapping details has been updated successfully.");

			softAssert.assertAll();
			test.pass("Client dropdown in client mapping displayed all clients assigned to user successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
			}
			else {
				test.skip("Not able to test because sites are not assigned to user");
				getScreenshot();	
				saveResult(ITestResult.SKIP, null);
			}
		}catch(AssertionError er){
			test.fail("Client dropdown in client mapping did not displayed all clients assigned to user");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Client dropdown in client mapping did not displayed all clients assigned to user");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority= 16) 
	public void VerifyClientUploadScreen() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-DTM-21: Verify templates assigned to client are displayed in data upload screen", "This test case will verify that templates assigned to client are displayed in data upload screen");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Open Client mapping popup");
			steps.createNode("2. Assign templates to client");
			steps.createNode("3. Go to data upload screen and verify that assign templates are displayed against that client");

			getDriver().get(url_templateAdmin);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			String rows = getText(By.id(ResultsCount));

			click(dtmClientMappingOpenButton);
			waitElementInvisible(loading_cursor);	

			click(dtmClientMappingClientDropdown);
			Thread.sleep(1500);
			if (size(By.cssSelector(".ng-option:nth-child(1) .ng-option-label")) != 0) {
			String clientName = getDriver().findElement(By.cssSelector(".ng-option:nth-child(1) .ng-option-label")).getText();
			click(By.cssSelector(".ng-option:nth-child(1) .ng-option-label"));
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);

			int templateRows = Integer.parseInt(rows) - 5;
			int count = 0;
			for (int i=0; i< templateRows; i++) {
				
				if (getDriver().findElement(By.id("isCreate"+i)).isSelected() == true) {		
					count = count + 1;
					if (i == templateRows-1) {
						getDriver().get(url_dataAdmin);
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);
						click(By.id("OrgnTypeID"));
						getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Client");
						getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						getDriver().findElement(By.id("ClientId")).click();
						getDriver().findElement(By.cssSelector("#ClientId input")).sendKeys(clientName);
						getDriver().findElement(By.cssSelector("#ClientId input")).sendKeys(Keys.ENTER);
						getDriver().findElement(By.id("DataFormatId")).click();
						
						Assert.assertEquals(getDriver().findElements(By.cssSelector(".ng-option")).size(), count);
						test.pass("Template assigned to user displayed in data upload screen successfully");
						results.createNode("Template assigned to user displayed in data upload screen successfully");
						getScreenshot();	
						saveResult(ITestResult.SUCCESS, null);
					}	
				}
			}
			}
			else {
				test.skip("Templates not displayed because sites are note assigned to users");
				saveResult(ITestResult.SKIP, null);
			}
		}catch(AssertionError er){
			test.fail("Template assigned to user failed to display in data upload screen");
			results.createNode("Template assigned to user failed to display in data upload screen");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Template assigned to user failed to display in data upload screen");
			results.createNode("Template assigned to user failed to display in data upload screen");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority= 13)
	public void AssetLineageColumnsVerification() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-DTM-18: Verify Asset Lineage column verfication");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			steps.createNode("1. Open Asset Lineage Template");
			steps.createNode("2. Verify columns and template names");

			SoftAssert softAssert = new SoftAssert();

			getDriver().get(url_templateAdmin);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			for(int i=1; i<=Integer.parseInt(getDriver().findElement(By.id(ResultsCount)).getText()); i++) {

				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals("Bulk Create Asset Lineage")) {
					getDriver().findElement(By.id("edit-data-format-"+i)).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
				}
			}

			List<String> columnName = Arrays.asList("", "INTEGRATOR_PARENT_Asset_ID", "INTEGRATOR_CHILD_Asset_ID", "PARENT_Asset_LOCATION_NAME",
					"PARENT_Asset_LOCATION_EXTERNAL_ID", "PARENT_Asset_deployment_DATE", "CHILD_Asset_LOCATION_NAME", "CHILD_Asset_LOCATION_EXTERNAL_ID",
					"CHILD_Asset_deployment_DATE");

			List<String> columnType = Arrays.asList("String", "String", "String", "String", "String", "String", "String", "String");

			for(int i=1;i<columnName.size(); i++) {
				softAssert.assertEquals(getDriver().findElement(By.cssSelector(".table-resp tr:nth-child("+i+") td:nth-child(3)  label")).getText(), columnName.get(i), getDriver().findElement(By.cssSelector(".table-resp tr:nth-child("+i+") td:nth-child(2)  label")).getText());
				softAssert.assertEquals(getDriver().findElement(By.cssSelector(".table-resp tr:nth-child("+i+") td:nth-child(4)  label")).getText(), columnType.get(i), getDriver().findElement(By.cssSelector(".table-resp tr:nth-child("+i+") td:nth-child(2)  label")).getText());
			}

			getDriver().findElement(popupCloseButton).click();
			softAssert.assertAll();
			test.pass("Asset Lineage template columns verified successfully");
			results.createNode("Asset Lineage template columns verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Asset Lineage template columns failed to verify successfully");
			results.createNode("Asset Lineage template columns failed to verify successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Asset Lineage template columns failed to verify successfully");
			results.createNode("Asset Lineage template columns failed to verify successfully");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		extent.flush();
	}

}

