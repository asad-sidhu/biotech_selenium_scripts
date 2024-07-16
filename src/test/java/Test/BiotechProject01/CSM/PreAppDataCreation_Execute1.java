package Test.BiotechProject01.CSM;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import MiscFunctions.*;
import PageObjects.OrganizationManagementPage;
import org.aeonbits.owner.ConfigFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import Models.ProgramManagementModel;
import PageObjects.UserManagementPage;
//import Test.BiotechProject01.Administration.OrganizationManagement;
import Test.BiotechProject01.Login.LoginTest;

import static PageObjects.OrganizationManagementPage.*;
import static PageObjects.ProgramManagementPage.*;
import static PageObjects.ComplexOPGPage.*;
import static PageObjects.BasePage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;

public class PreAppDataCreation_Execute1 extends BaseTest {

	String name = "none";

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Pre_Flutter_Mobile"+DateUtil.date+".html");
		spark.config().setReportName("Pre Flutter Mobile Test Report"); 
		DB_Config_DB.test();
	}

	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}

	@Test (enabled = true, priority = 1) 
	public void CreateOrganization() throws InterruptedException, IOException {
		OrganizationManagementPage org = new OrganizationManagementPage();
		org.CreateOrganizationFunction(CSMModel.organizationName);
	}


	@Test (enabled= true, priority= 2) 
	public void CreateOrganizationSitesHierarchy() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-31-38: Verify Complete Organization Site Hierarchy", "This test case will verify complete site hierarchy");
			steps = test.createNode(Scenario.class, Steps);
			SoftAssert softAssert = new SoftAssert();

			getDriver().get(url_organizationManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);

			//open org site popup
			for (int i=1;i<getDriver().findElements(By.cssSelector("tr")).size(); i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(CSMModel.organizationName)) {
					getDriver().findElement(By.id("edit-orgn-sites-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}
			Thread.sleep(1000);	

			//click on Add site button
			getDriver().findElement(orgAddSite1).click();
			waitElementInvisible(loading_cursor);
			//Thread.sleep(1000);

			//select org type from dropdown
			getDriver().findElement(orgSiteTypeInput).click();
			String regionType = getDriver().findElement(orgSiteTypeDropDownValue).getText();
			softAssert.assertEquals(regionType, "Region");

			//enter region site name
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Region");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("4. Verify Region Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("5. Click on + icon to create new site and verify Site Type is Sub Region");
			getDriver().findElement(orgAddSite2).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(orgSiteTypeInput).click();
			String subregionType = getDriver().findElement(orgSiteTypeDropDownValue).getText();
			softAssert.assertEquals(subregionType, "Sub Region");
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Sub Region");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("6. Verify Sub Region Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("7. Click on + icon to create new site and verify Site Type as Complex, Processing PLant, Testing Lab");
			getDriver().findElement(orgAddSite3).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			Thread.sleep(1000);

			getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).click();
			getDriver().findElement(orgSiteNameInput).sendKeys(CSMModel.complexName);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("8. Verify Complex Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("9. Click on + icon to create new site and verify Site Type as Farm");
			getDriver().findElement(orgAddSite4).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			String farmType = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(farmType, "Farm");
			getDriver().findElement(orgSiteNameInput).sendKeys(CSMModel.organizationFarm1Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			steps.createNode("10. Verify Farm Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("11. Create House 1");
			getDriver().findElement(orgAddSite5).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			String HouseType = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(HouseType, "House");
			getDriver().findElement(orgSiteNameInput).sendKeys(CSMModel.organizationHouse1Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(2000);
			steps.createNode("12. Verify House Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("13. Create house 2");
			getDriver().findElement(orgAddSite5).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteNameInput).sendKeys(CSMModel.organizationHouse2Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(2000);

			steps.createNode("14. Create house 3");
			getDriver().findElement(orgAddSite5).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteNameInput).sendKeys(CSMModel.organizationHouse3Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
		waitElementVisible(alertMessage);
			Thread.sleep(1000);

			steps.createNode("15. Create house 4");
			getDriver().findElement(orgAddSite5).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteNameInput).sendKeys(CSMModel.organizationHouse4Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
		waitElementVisible(alertMessage);
			Thread.sleep(1000);

			steps.createNode("16. Create house 5");
			getDriver().findElement(orgAddSite5).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteNameInput).sendKeys(CSMModel.organizationHouse5Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
		waitElementVisible(alertMessage);
			Thread.sleep(1000);

			softAssert.assertAll();
			test.pass("Site heirarchy verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}catch(AssertionError er){
			test.fail("Site heirarchy failed to verify successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Site heirarchy failed to verify successfully");
			saveResult(ITestResult.FAILURE, ex);
		}
	}	


	@Test (enabled = true, priority = 3)
	public void addSiteIDsToExcel() throws IOException, InterruptedException, SQLException {

		FileInputStream fsIP= new FileInputStream(new File(FrameworkConstants.CSMSiteIDsFile));
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fsIP);
		XSSFSheet worksheet = wb.getSheetAt(0);
		Cell cell = null;

		for (int z=1; z<=5; z++) {
			String getHouseSiteID = "Select siteUniqueNumber from dbo.Site where siteName = 'TestHouse"+z+"_"+CSMModel.creationDate+"'";

			ResultSet getUniqueSiteID = DB_Config_DB.getStmt().executeQuery(getHouseSiteID);
				while (getUniqueSiteID.next()) {
					String siteID = getUniqueSiteID.getString("siteUniqueNumber");
					System.out.println("Site ID: "+siteID);
					cell=worksheet.getRow(z).createCell(0);
					cell.setCellValue(siteID);
					fsIP.close();
				}
		}

			FileOutputStream output_file =new FileOutputStream(new File(FrameworkConstants.CSMSiteIDsFile));
			wb.write(output_file);
			output_file.close();
	}




	@Test (enabled= true, priority= 4)
	public void AssignTestingSites() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-14: Verify Sites column displays Active after assigning All Testing Sites to the user");
			ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
			UserManagementPage.openEditUserPopup(config.ie_username());
			click(popupNextButton);
			click(popupNextButton);
			Thread.sleep(750);
			click(UserManagementPage.userSitesButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			type(UserManagementPage.userSitesSearch, CSMModel.organizationName);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			click(By.xpath("//*[text()='"+ CSMModel.organizationName+"']"));
			Thread.sleep(1000);
			click(UserManagementPage.userSitesSaveButton);
			Thread.sleep(1000);
			getScreenshot();
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);

			Assert.assertEquals(getText(alertMessage), "User details updated.");	
			test.pass("Site Assigned to user successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Site failed to assigned to user successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Site failed to assigned to user successfully");	
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	
	@SuppressWarnings("unused")
	@Test (enabled= true, priority= 5)
	public void CreateProgramVaccine() throws InterruptedException, IOException {
		try {		
			test = extent.createTest("AN-Program-02: Verify that user is able to create new Vaccine program", "This testcase will verify that user is able to create new program");

			getDriver().get(url_programAdmin);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			SoftAssert softAssert = new SoftAssert();

			click(programVaccineProgramTab);
			waitElementInvisible(loading_cursor);

			for (int j=1;j<getDriver().findElements(By.id("col-0-vaccine")).size();j++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+j+") #col-0-vaccine label")).getText().equals(CSMModel.vaccineName)) {
					test.skip("Program already created");
					getScreenshot();
					saveResult(ITestResult.SKIP, null);
					break;
				}

				else {
					getDriver().findElement(By.xpath("//*[text()=' Register New Program']")).click();
					waitElementInvisible(loading_cursor);
					//Program Name
					getDriver().findElement(programName).sendKeys(CSMModel.vaccineName);

					//Target Pathogen
					getDriver().findElement(programTargetPathogen).click();
					Thread.sleep(500);
					getDriver().findElement(programTargetPathogen).sendKeys(Keys.ENTER);
					Thread.sleep(500);

					//Program Type
					getDriver().findElement(programProgramType).sendKeys("Vaccine");
					Thread.sleep(500);	
					getDriver().findElement(programProgramType).sendKeys(Keys.ENTER);

					//Supplier
					getDriver().findElement(programSupplier).sendKeys(ProgramManagementModel.SupplierName);
					Thread.sleep(500);
					if (getDriver().findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
						getDriver().findElement(By.xpath("//*[text()='Add New + ']")).click();
					}
					else {
						getDriver().findElement(By.cssSelector(".list-item")).click();		
					}
					Thread.sleep(500);

					//Complex
					click(programComplexList);
					type(programComplexSearch, CSMModel.complexName);
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					click(clickSearchItemFromHierarchy);
					Thread.sleep(1000);
					
					//Start Date
				System.out.println(1);
					click(programStartDateIcon);
					System.out.println(2);	
					waitElementInvisible(loading_cursor);
					Thread.sleep(500);
					Methods method = new Methods();
					WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
					List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
					System.out.println(3);
					DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
					Thread.sleep(500);

					//End Date
					getDriver().findElement(By.cssSelector("#endDate img")).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(500);
					WebElement dateWidgetToEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
					List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
					DateUtil.clickGivenDay(columns2, DateUtil.getDay("30"));
					Thread.sleep(700);

					//Program Description
					getDriver().findElement(programDescription).sendKeys(ProgramManagementModel.DescriptionName);

					String NoApplicationAsset = "2";
					getDriver().findElement(programNoApplicationAsset).sendKeys(NoApplicationAsset);
					Thread.sleep(500);

					for(int i=1; i<=Integer.parseInt(NoApplicationAsset); i++) {
						getDriver().findElement(By.id(programDaysApplicationAsset+"-"+i)).sendKeys(""+i);
					}

					getScreenshot();
					click(popupSaveButtonXpath);
					waitElementVisible(alertMessage);
					softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New program has been created successfully"); 
					test.pass("New Program created successfully");
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
					break;
				}
			}

		}catch(AssertionError er) {
			test.fail("New Program failed to create");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("New Program failed to create");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@SuppressWarnings("unused")
	@Test (enabled= true, priority= 6)
	public void CreateProgramFeed() throws InterruptedException, IOException {
		try {		
			test = extent.createTest("AN-Program-05: Verify that user is able to create new Feed program", "This testcase will verify that user is able to create new program");

			getDriver().get(url_programAdmin);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);

			getDriver().findElement(programFeedProgramTab).click();
			Thread.sleep(1500);

			for (int j=1;j<getDriver().findElements(By.id("col-0-feedprogram")).size();j++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+j+") #col-0-feedprogram label")).getText().equals(CSMModel.vaccineName)) {
					test.skip("Program already created");
					getScreenshot();
					saveResult(ITestResult.SKIP, null);
					break;
				}

				else {

					getDriver().findElement(programCreateButton).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(1500);

					getDriver().findElement(programName).sendKeys(CSMModel.feedName);

					getDriver().findElement(programTargetPathogen).click();
					Thread.sleep(1000);
					getDriver().findElement(programTargetPathogen).sendKeys(Keys.ENTER);

					getDriver().findElement(programProgramType).sendKeys("Feed");
					Thread.sleep(700);	
					getDriver().findElement(programProgramType).sendKeys(Keys.ENTER);

					getDriver().findElement(programSupplier).sendKeys("China");
					Thread.sleep(700);
					if (getDriver().findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
						getDriver().findElement(By.xpath("//*[text()='Add New + ']")).click();
					}
					else {
						getDriver().findElement(By.cssSelector(".list-item")).click();		
					}
					Thread.sleep(700);

					getDriver().findElement(programDescription).sendKeys("Feed Testing Program");

					//Complex
					click(programComplexList);
					type(programComplexSearch, CSMModel.complexName);
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					click(clickSearchItemFromHierarchy);


					//Start Date
					click(programStartDateIcon);
					waitElementInvisible(loading_cursor);
					Thread.sleep(500);
					Methods method = new Methods();
					WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
					List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
					DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
					Thread.sleep(500);
					

					getDriver().findElement(programFeedTypeDropdown).click();
					Thread.sleep(1000);	
					getDriver().findElement(programFeedTypeDropdown).sendKeys(Keys.ENTER);

					getDriver().findElement(programAssetDayStart).sendKeys("1");

					WebElement EndDay = getDriver().findElement(programAssetDayStart);
					getDriver().findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");

					WebElement ingredient = getDriver().findElement(programFeedTypeDropdown);
					getDriver().findElement(with(By.tagName("input")).below(ingredient)).sendKeys("Sugar");

				//	WebElement ingredientCategory = getDriver().findElement(programAssetDayStart);
				//	getDriver().findElement(with(By.tagName("input")).below(ingredientCategory)).click();
				//	List<WebElement> ingredientCategories = getDriver().findElements(By.cssSelector(".ng-option-label"));
				//	ingredientCategories.get(0).click();

					getScreenshot();
					getDriver().findElement(By.xpath(("//button[text() = ' Submit ']"))).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "New program has been created successfully"); 
					test.pass("New Program created successfully");
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
					break;
				}
			}


		}catch(AssertionError er) {
			test.fail("New Program failed to create");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("New Program failed to create");
			saveResult(ITestResult.FAILURE, ex);
		}
	}



	@Test (description="Test Case: Create complex Configurations",enabled= true, priority = 7)
	public void CreateComplexConfig() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Complex: Create Complex Configuration");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Complex OPG Range Config; Screen opens");
			steps.createNode("1. Create Configuration");

			getDriver().get(url_cycleConfig);;
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			CSMModel.lstCreateComplexConfig = CSMModel.CreateConfig();

			getDriver().findElement(complexCreateButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(complexSelectComplexDropdown).click();
			Thread.sleep(1000);
			getDriver().findElement(complexSearchComplex).sendKeys(CSMModel.complexName);
			Thread.sleep(1000);
			getDriver().findElement(complexSelectComplexSite).click();

			getDriver().findElement(complexSelectProgramType).sendKeys("Vaccine");
			Thread.sleep(2000);
			getDriver().findElement(complexSelectProgramType).sendKeys(Keys.ENTER);

			getDriver().findElement(complexSelectProgramId).sendKeys(CSMModel.vaccineName);
			Thread.sleep(1000);
			getDriver().findElement(complexSelectProgramId).sendKeys(Keys.ENTER);

			By addProgramButton = RelativeLocator.with(By.tagName("button")).toRightOf(complexSelectProgramId);
			getDriver().findElement(addProgramButton).click();

			getDriver().findElement(complexSelectProgramType).sendKeys("Feed");
			Thread.sleep(1000);
			getDriver().findElement(complexSelectProgramType).sendKeys(Keys.ENTER);

			getDriver().findElement(complexSelectProgramId).sendKeys(CSMModel.feedName);
			Thread.sleep(1000);
			getDriver().findElement(complexSelectProgramId).sendKeys(Keys.ENTER);

			getDriver().findElement(addProgramButton).click();

			for (CSMModel objModel : CSMModel.lstCreateComplexConfig) {

				getDriver().findElement(complexOPGType).click();
				Thread.sleep(1000);
				List<WebElement> OPGTypeSelect = getDriver().findElements(complexSelectValueFromDropdown);
				OPGTypeSelect.get(0).click();

				getDriver().findElement(complexanimalSize).click();
				Thread.sleep(1000);
				List<WebElement> animalSizeSelect = getDriver().findElements(complexSelectValueFromDropdown);
				animalSizeSelect.get(0).click();

				getDriver().findElement(complexSamplingInterval).click();
				Thread.sleep(1000);
				List<WebElement> SamplingIntervalSelect = getDriver().findElements(complexSelectValueFromDropdown);
				SamplingIntervalSelect.get(0).click();

				getDriver().findElement(complexComplexThreshold).sendKeys(objModel.ComplexThreshold);
				getDriver().findElement(complexHouseThreshold).sendKeys(objModel.HouseThreshold);
				getDriver().findElement(complexLowerLimit).sendKeys(objModel.LowerLimit);
				getDriver().findElement(complexUpperLimit).sendKeys(objModel.UpperLimit);
				Thread.sleep(1000);
				By addConfigButton = RelativeLocator.with(By.tagName("button")).toRightOf(complexUpperLimit);
				getDriver().findElement(addConfigButton).click();
			}

			getDriver().findElement(complexSubmitButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			Assert.assertNotEquals(getDriver().findElement(alertMessage).getText(), "Complex cycling configuration details saved");
			test.pass("Complex cycling configuration details saved successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Complex cycling configuration details failed to save");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Complex cycling configuration details failed to save");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		extent.flush();
	}
}
