package Test.BiotechProject01.Administration;

import java.io.IOException;
import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import Config.ReadPropertyFile;
import MiscFunctions.DateUtil;
import MiscFunctions.NavigateToScreen;
import Models.ReportsManagementModel;
import PageObjects.Patho_2LogPage;
import PageObjects.UserManagementPage;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static Models.UserManagementModel.createUserEmail;
import static PageObjects.ReportsManagementPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static Models.ReportsManagementModel.*;
import static PageObjects.UserManagementPage.userEmail;


public class ReportsManagement extends BaseTest{

	@BeforeTest (groups = {"smoke", "regression"})
	public void extent() throws InterruptedException, IOException {
//		spark = new ExtentSparkReporter("target/Reports/Administration_Reports_Management"+DateUtil.date+".html");
//		spark.config().setReportName("Reports Management Test Report");
		initReport("Administration_Reports_Management");
	}

	@BeforeClass(groups = {"smoke", "regression"})
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
		NavigateToScreen.navigate(url_reportManagement, "Reports Management", reportsManagementTitle);
	}

	
	@Test (priority = 1, enabled = true, groups = {"smoke", "regression"})
	public void LockFilter() throws InterruptedException, IOException {
		Lock(reportsManagementTable, "Reports Management", 0);
	}
	

	@Test (description="Open Create Role Popup", enabled= true, priority= 3, groups = {"regression"})
	public void OpenPopup() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-RM-02: Verify user can open Create Role Popup");
			getScreenshot();
			Thread.sleep(1000);
			getDriver().findElement(rmCreateButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			Assert.assertTrue(getDriver().findElement(rmName).isDisplayed(), "Popup failed to open"); 
			test.pass("Create Role popup window opened successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Create Role popup window did not open successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Create Role popup window did not open successfully");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Exceptional Flow: Reset field check", enabled= true, priority= 3, groups = {"regression"})
	public void ResetFieldCheck() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-RM-03: Verify Reset fields check", "This test case will verify that user is able to reset create role fields");
			getDriver().findElement(rmName).sendKeys("Test");
			Thread.sleep(1000);
			getDriver().findElement(rmDesc).sendKeys("Description");
			getScreenshot();
			getDriver().findElement(popupResetButton ).click();
			Thread.sleep(1000);		
			Assert.assertEquals(getDriver().findElements(alertMessage).size(), 0);
			String nameActual = getDriver().findElement(By.id("nameId")).getAttribute("value");
			String descActual = getDriver().findElement(By.id("DescId")).getAttribute("value");
			Assert.assertTrue(nameActual.isEmpty(), "Name field is not empty");
			Assert.assertTrue(descActual.isEmpty(), "Desc field not empty");
			test.pass("Create Role popup fields reset successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Create Role popup window did not reset successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Create Role popup window did not reset successfully");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 4, groups = {"regression"})
	public void MandatoryFieldCheck() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-RM-04: Verify mandatory field check", "This testcase will verify mandatory field check");
			getDriver().findElement(rmName).click();
			getDriver().findElement(rmDesc).click();
			getDriver().findElement(rmName).click();
			getDriver().findElement(popupSaveButton).click();
			Assert.assertEquals(getDriver().findElements(alertMessage).size(), 0);
			Assert.assertEquals(getDriver().findElements(By.cssSelector(".has-error")).size(), 4);
			test.pass("Mandatory field check verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Mandatory field verification failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Mandatory field verification failed");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Exceptional Flow: Create Role", enabled= true, priority= 5, groups = {"smoke", "regression"})
	public void CreateRole() throws IOException {
		try {
			test = extent.createTest("AN-RM-05: Verify user can create role", "This testcase will verify that user can create role");
			getDriver().get(url_reportManagement);
			waitElementInvisible(loading_cursor);
			click(rmCreateButton);
			Thread.sleep(1000);
			type(rmName, RoleName);
			type(rmDesc, "Role created by automation script");
			click(popupSaveButton);
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Role has been created successfully.");
			test.pass("Role created successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Role failed to create successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Role failed to create successfully");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Update Role",enabled= true, priority= 6, groups = {"smoke", "regression"})
	public void UpdateRole() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AM-RM-06: Verify user can update Role", "This test case will verify that user can update a Role");

			int rows = getDriver().findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(RoleName) ) {
					getDriver().findElement(By.id("edit-report-role-"+i)).click();
						waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					break;
				}
			}

			getDriver().findElement(rmDesc).clear();
			getDriver().findElement(rmDesc).sendKeys("Role created and updated by automation script");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "Role has been updated successfully."); 
			test.pass("Role updated successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("User did not received an alert message that 'Role details updated'");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User did not received an alert message that 'Role details updated'");
			saveResult(ITestResult.FAILURE, ex);
		}
		getDriver().findElement(alertMessageClose).click();
	}


	@Test (description="Test Case: Verify Update Role",enabled= true, priority= 7, groups = {"regression"})
	public void VerifyUpdateRole() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-RM-07: Verify changes remains save on updating the Role", "This test case will verify that changes remains save on updating the Role");

			int rows = getDriver().findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(RoleName) ) {
					getDriver().findElement(By.id("edit-report-role-"+i)).click();
						waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					break;
				}
			}

			Assert.assertEquals(getDriver().findElement(rmDesc).getAttribute("value"), "Role created and updated by automation script"); 
			test.pass("Role updation verified successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Role updation verification failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Role updation verification failed");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Audit Trail",enabled= true, priority= 8, groups = {"smoke", "regression"})
	public void AuditTrail() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-RM-08: Verify Audit trail functionality", "This test case will verify audit trail functionality");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			preconditions.createNode("4. Click on Create New button; popup appears");
			preconditions.createNode("5. Create a Role");
			steps.createNode("1. Click on update icon next to created role; popup appears");
			steps.createNode("2. Click on Audit Trail toggle button and click on save button");
			steps.createNode("3. Verify Audit trail button is shown in reports");
			
			getDriver().get(url_userManagement);
			waitElementInvisible(loading_cursor);
			waitElementVisible(UserManagementPage.usercreateButton);
			Thread.sleep(3000);
			ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);

			getDriver().findElement(By.id(userEmail + "" + ShowFilter)).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(By.id(userEmail + "" + SearchInput)).sendKeys(config.ie_username());
			Thread.sleep(1200);
			getDriver().findElement(By.id(userEmail + "" + SelectAll)).click();
			getDriver().findElement(By.id(userEmail + "" + ApplyFilter)).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			String ReportRole = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+UserManagementPage.userReportingCol+" label")).getText();

			getDriver().get(url_reportManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			for (int i=1;i<getDriver().findElements(By.cssSelector("tr")).size(); i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ReportRole)) {
					getDriver().findElement(By.id("edit-report-role-"+i)).click();
					break;
				}	
			}
			
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			if (!getDriver().findElement(By.cssSelector("#status-audit-trail .toggle")).isEnabled()) {
				getDriver().findElement(By.cssSelector("#status-audit-trail .toggle")).click();
				getDriver().findElement(popupSaveButton).click();
			}

			Patho_2LogPage.openPatho_2LogPage();
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			Assert.assertEquals(getDriver().findElements(By.id("audit-trial-0")).size(), 1);
			test.pass("Audit trail icon displayed in reports successfully");
			results.createNode("Audit trail icon displayed in reports successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Audit trail icon did not displayed in reports");
			results.createNode("Audit trail icon did not displayed in reports");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Audit trail icon did not displayed in reports");
			results.createNode("Audit trail icon did not displayed in reports");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: InActivate Role",enabled= true, priority= 9, groups = {"regression"})
	public void InActiveRole() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-RM-09: Verify InActivate Role functionality", "This test case will verify user can inactivate the role");

			getDriver().get(url_reportManagement);
				waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			int rows = getDriver().findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(RoleName) ) {
					getDriver().findElement(By.id("edit-report-role-"+i)).click();
						waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					break;
				}
			}
			getDriver().findElement(By.cssSelector("#status-report-role .toggle")).click();
			getDriver().findElement(popupSaveButton).click();
				waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "Role has been updated successfully."); 
			test.pass("Role inactivated successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Role inactivation failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Role inactivation failed");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Verify InActive Role on User Screen",enabled= true, priority= 10, groups = {"regression"})
	public void VerifyInActiveRoleUsers() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-RM-10: Verify Role is inactivated in User Management screen", "This test case will verify user cannot view inactive role on user create or update screen");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			preconditions.createNode("4. Click on Create New button; popup appears");
			steps.createNode("1. Create a Role; Click on InActivate toggle button and click on save button");
			steps.createNode("2. Go to User Management");
			steps.createNode("3. Click on Create New button and go to step 3 of create user popup");
			steps.createNode("4. Open Report Roles dropdown");
			steps.createNode("5. Find the inactivated role in dropdown list");

			getDriver().get(url_userManagement);
				waitElementInvisible(loading_cursor);
				waitElementVisible(UserManagementPage.usercreateButton);
			Thread.sleep(3000);
			getDriver().findElement(By.id("edit-user-1")).click();
				waitElementInvisible(loading_cursor);
			Thread.sleep(4000);
			getDriver().findElement(popupNextButton).click();
			Thread.sleep(1500);
			getDriver().findElement(popupNextButton).click();
			Thread.sleep(1500);
			getDriver().findElement(By.cssSelector("#reportRoleId input")).clear();
			getDriver().findElement(By.cssSelector("#reportRoleId input")).sendKeys(RoleName);
			Thread.sleep(1000);
			Assert.assertTrue(getDriver().findElement(By.cssSelector(".ng-option-disabled")).isDisplayed());
			test.pass("InActivated Role was not found in User Management screen");
			results.createNode("User was not able to see InActivated Role in dropdown list");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("User was able to see InActivated Role in dropdown list");
			results.createNode("User was able to see InActivated Role in dropdown list");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User was able to see InActivated Role in dropdown list");
			results.createNode("User was able to see InActivated Role in dropdown list");
			saveResult(ITestResult.FAILURE, ex);
		}
	}



	@Test (description="Open Create Report Groups Popup", enabled= true, priority= 12, groups = {"smoke", "regression"})
	public void OpenReportGroupsPopup() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-RM-12: Verify user can open Create Report Group Popup", "This test case will verify that user is able to open create report group popup");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			steps.createNode("1. Click on Report Group button");

			getDriver().get(url_reportManagement);
				waitElementInvisible(loading_cursor);
				waitElementVisible(rmCreateButton);
			Thread.sleep(3000);
			getDriver().findElement(rmReportGroupPopupOpen).click();
				waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			String actual = getDriver().findElement(By.cssSelector(".popup-header")).getText();
			String expected = "Report Groups";
			Assert.assertEquals(actual, expected); 
			test.pass("Report Groups popup window opened successfully");
			results.createNode("Report Groups popup window opened successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Report Groups popup window did not open successfully");
			results.createNode("Failed to open Report Groups popup window");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Report Groups popup window did not open successfully");
			results.createNode("Failed to open Report Groups popup window");
			saveResult(ITestResult.FAILURE, ex);
		}
	}



	@Test (description="Exceptional Flow: Reset field check", enabled= true, priority= 13, groups = {"smoke", "regression"})
	public void ResetGroupReportsFieldCheck() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-RM-13: Verify user can reset Group Details fields", "This test case will verify that user is able to reset Report Details fields");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			preconditions.createNode("4. Click on Report Groups button; Report Group popup opens");
			preconditions.createNode("5. Click on '+' icon; Report Group Details screen shows up");
			steps.createNode("1. Enter valid data in all fields");
			steps.createNode("2. Click on reset fields button");

			waitElementVisible(rmCreateButton);
			Thread.sleep(3000);
			getDriver().findElement(rmReportGroupsCreateButton).click();
			Thread.sleep(1000);
			getDriver().findElement(rmReportGroupsName).sendKeys("Test");
			Thread.sleep(500);
			getDriver().findElement(rmReportGroupsDesc).sendKeys("Description");
			getScreenshot();	
			getDriver().findElement(rmReportGroupsresetButton).click();
			Thread.sleep(1000);

			String nameActual = getDriver().findElement(rmReportGroupsName).getAttribute("value");
			String descActual = getDriver().findElement(rmReportGroupsDesc).getAttribute("value");

			Assert.assertEquals(nameActual, ""); 
			Assert.assertEquals(descActual, "");
			test.pass("Report Group Details fields reset successfully");
			results.createNode("Report Group Details fields reset successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Report Group Details fields failed to reset");
			results.createNode("Report Group Details fields failed to reset");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Report Group Details fields failed to reset");
			results.createNode("Report Group Details fields failed to reset");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 14, groups = {"smoke", "regression"})
	public void MandatoryReportGroupFieldCheck() throws InterruptedException, IOException {

		for (ReportsManagementModel objModel : lstRGMandatoryCheck) {
			test = extent.createTest(objModel.rgtestcaseTitle, objModel.rgtestcaseDesc);
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar; Click on Administration and select Report Management");	
			preconditions.createNode("4. Click on Create New button; Popup appears");
			steps.createNode("1. "+objModel.rgstep1);
			steps.createNode("2. Click on save button");

			getDriver().findElement(rmReportGroupsName).clear();
			getDriver().findElement(rmReportGroupsName).sendKeys(objModel.rgName);
			Thread.sleep(1000);
			getDriver().findElement(rmReportGroupsDesc).clear();
			getDriver().findElement(rmReportGroupsDesc).sendKeys(objModel.rgDesc);
			Thread.sleep(1000);

			if(objModel.rgReport) {
				getDriver().findElement(By.id("groupRprtId")).click();
				Thread.sleep(1000);
				getDriver().findElement(By.xpath("//label[contains(text(),'Select All')]")).click();
				getDriver().findElement(rmReportGroupsDesc).click();
			}

			getScreenshot();
			getDriver().findElement(By.id("btn-save-2")).click();
			try{
				if(objModel.rgstep)	
				{
					if ( objModel.rgName.isEmpty())
					{
						Assert.assertEquals(getDriver().findElements(By.cssSelector("#groupNameId-error-container .hide")).size(), 0); 
					}

					if ( objModel.rgDesc.isEmpty())
					{
						Assert.assertEquals(getDriver().findElements(By.cssSelector("groupDescId-error-container .hide")).size(), 0); 
					}

					if ( objModel.rgDesc.isEmpty())
					{
						Assert.assertEquals(getDriver().findElements(By.cssSelector(".floating-error.hide")).size(), 0); 
					}

					test.pass(objModel.rgpassScenario);
					results.createNode(objModel.rgpassScenario);
					getScreenshot();	
					continue;
				}

				if (objModel.rgsave)
				{
					waitElementVisible(alertMessage);
					Thread.sleep(1000);
					String actual = getDriver().findElement(alertMessage).getText();
					String expected = "New report group created.";
					Assert.assertEquals(actual, expected); 
				}

				test.pass(objModel.rgpassScenario);
				results.createNode(objModel.rgpassScenario);
				getScreenshot();	
				saveResult(ITestResult.SUCCESS, null);
			}		
			catch(AssertionError er) {
				test.fail(objModel.rgfailScenario);
				results.createNode(objModel.rgfailScenario);
				saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail(objModel.rgfailScenario);
				results.createNode(objModel.rgfailScenario);
				saveResult(ITestResult.FAILURE, ex);
			}
		}
	}


	@Test (description="Test Case: Update Report Details", enabled= true, priority= 15, groups = {"smoke", "regression"})
	public void UpdateReportGroupDetails() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AM-RM-17: Verify user can update Report Group", "This test case will verify that user can update Report Details");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			preconditions.createNode("4. Click on Report Groups button; Report Group popup opens");
			preconditions.createNode("5. Click on '+' icon and create a Report Group");
			steps.createNode("1. Click on created popup; report details screen shows up");
			steps.createNode("2. Update data and click on save button");

			waitElementVisible(rmCreateButton);
			Thread.sleep(3000);
			
			List<WebElement> op = getDriver().findElements(By.cssSelector(".popup-content ul"));
			for (int i=0; i<op.size(); i++) {
				if (op.get(i).getText().equals(ReportGroupName)) {
					getDriver().findElement(By.xpath("//label[contains(text(),'"+ReportGroupName+"')]")).click();
					break;
				}
			}

				waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(rmReportGroupsDesc).clear();
			getDriver().findElement(rmReportGroupsDesc).sendKeys("Group created and updated by automation script");
			getDriver().findElement(By.id("btn-save-2")).click();
				waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			String actual = getDriver().findElement(By.id("message")).getText();
			String expected = "Report Group details updated successfully.";

			Assert.assertEquals(actual, expected); 
			test.pass("Report details updated successfully");
			results.createNode("Report Group details updated successfully; an alert message was generated 'Report Group details updated.'");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Report Group details failed to update");
			results.createNode("Report Group details failed to update");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Report Group details failed to update");
			results.createNode("Report Group details failed to update");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority= 16, groups = {"smoke", "regression"})
	public void VerifyReportGroupOnEditRights() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AM-RM-18: Verify Report Groups are visible on Edit Rights popup", "This test case will verify that added report group is being displayed on Edit Rights popup");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			preconditions.createNode("4. Click on Create New button; popup appears");
			preconditions.createNode("5. Create a Role");
			steps.createNode("1. Click on edit rights icon next to created role; popup appears");

			getDriver().get(url_reportManagement);
				waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			int rows = getDriver().findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(RoleName) ) {
					getDriver().findElement(By.id("edit-role-rights-"+i)).click();
						waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					break;
				}
			}

			List<WebElement> op = getDriver().findElements(By.cssSelector(".popup-content ul"));
			for (int i=0; i<op.size(); i++) {
				if (op.get(i).getText().equals(ReportGroupName)) {
					Assert.assertTrue(true, "Report Group not visible"); 
					test.pass("Created Report group was visible on edit rights successfully");
					results.createNode("Created Report group was visible on edit rights successfully");
					getScreenshot();	
					saveResult(ITestResult.SUCCESS, null);
					break;
				}
			}
		}
		catch(AssertionError er) {
			test.fail("Created Report group was not visible on edit rights");
			results.createNode("Created Report group was not visible on edit rights");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Created Report group was not visible on edit rights");
			results.createNode("Created Report group was not visible on edit rights");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Verify Report Details",enabled= true, priority= 17, groups = {"smoke", "regression"})
	public void VerifyReportDetails() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AM-RM-19: Verify Reports Details are displayed in Edit Rights screen", "This test case will verify that report details are displayed on Edit Rights popup on clicking Report Group");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			preconditions.createNode("4. Click on Create New button; popup appears");
			preconditions.createNode("5. Create a Role");
			steps.createNode("1. Click on edit rights icon next to created role; popup appears");
			steps.createNode("2. Click on report group");
			steps.createNode("3. Verify all reports are visible in report group that are assigned to it");

			getDriver().get(url_reportManagement);
				waitElementInvisible(loading_cursor);
				waitElementVisible(rmCreateButton);
			Thread.sleep(3000);
			getDriver().findElement(rmReportGroupPopupOpen).click();
				waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			List<WebElement> op = getDriver().findElements(By.cssSelector(".popup-content ul label"));
			for (int i=0; i<op.size(); i++) {
				if (op.get(i).getText().equals(ReportGroupName)) {
					getDriver().findElement(By.xpath("//label[contains(text(),'"+ReportGroupName+"')]")).click();  //open report group
					Thread.sleep(1500);
					break;
				}
			}

			getDriver().findElement(By.id("groupRprtId")).click();
			Thread.sleep(2000);
			List<WebElement> groupReports = getDriver().findElements(By.cssSelector("#groupRprtId .custom-control-label")); //get list of all reports assigned to report group

			getDriver().findElement(popupCloseButton).click();

			int rows = getDriver().findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(RoleName) ) {

					getDriver().findElement(By.id("edit-role-rights-"+i)).click();  //open edit rights popup
						waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					break;
				}
			}


			List<WebElement> reportGroups = getDriver().findElements(By.cssSelector("label.group-card-text"));
			for (int i=0; i<reportGroups.size(); i++) {
				if (reportGroups.get(i).getText().equals(ReportGroupName)) {
					int j=i+1;
					getDriver().findElement(By.cssSelector("#status-report-right-"+j+" .row")).click();
					break;
				}
			}
			Thread.sleep(2000);
			Assert.assertEquals(getDriver().findElements(By.cssSelector(".popup-content tr")).size(), groupReports.size()); //verify all reports assigned to report group are vivsible in edit rights popup

			getDriver().findElement(By.cssSelector(".fa-caret-down")).click();
			getDriver().findElement(By.xpath("//label[contains(text(),'Select all')]")).click();

			List<WebElement> viewCheckbox = getDriver().findElements(By.cssSelector(".custom-checkbox label"));
			for (int j =0;j<viewCheckbox.size();j++) {
				Assert.assertTrue(viewCheckbox.get(j).isEnabled());
			}
			test.pass("Reports in report groups displayed successfully");
			results.createNode("Displays list of reports that are present in that Report Group");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Did not displayed list of reports that are present in that Report Group");
			results.createNode("Did not displayed list of reports that are present in that Report Group");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Did not displayed list of reports that are present in that Report Group");
			results.createNode("Did not displayed list of reports that are present in that Report Group");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Save Report Access rights",enabled= true, priority= 18, groups = {"smoke", "regression"})
	public void SaveAccessRights() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AM-RM-20: Verify user can save Report Access Rights", "This test case will verify that user can save Report Access rights");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			preconditions.createNode("4. Click on Create New button; popup appears");
			preconditions.createNode("5. Create a Report Group and add a new report into it");
			steps.createNode("1. Click on edit rights icon next to created role; popup appears");
			steps.createNode("2. Click on Save button");
			Thread.sleep(2000);
			getDriver().findElement(popupSaveButton).click();
			Thread.sleep(1000);
			waitElementVisible(alertMessage);
			String actual =	getDriver().findElement(By.id("message")).getText();
			String expected = "Report access rights saved successfully.";

			Assert.assertEquals(actual, expected); 
			test.pass("Edit Rights saved successfully");
			results.createNode("Edit Rights saved successfully; displays an alert message 'Report access rights saved successfully'");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Edit Rights failed to saved;did not displayed an alert message 'Report access rights saved successfully'");
			results.createNode("Edit Rights failed to saved;did not displayed an alert message 'Report access rights saved successfully'");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Edit Rights failed to saved;did not displayed an alert message 'Report access rights saved successfully'");
			results.createNode("Edit Rights failed to saved;did not displayed an alert message 'Report access rights saved successfully'");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Inactivate Report Group",enabled= true, priority= 19, groups = {"smoke", "regression"})
	public void InActivateReportGroup() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AM-RM-21: Verify user can inactivate Report Group", "This test case will verify that user can inactivate Report Group");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			preconditions.createNode("4. Click on Create New button; popup appears");
			preconditions.createNode("5. Create a Report Group");
			steps.createNode("1. Click on inactivate toggle button");

			getDriver().findElement(rmReportGroupPopupOpen).click();
				waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			List<WebElement> op = getDriver().findElements(By.cssSelector(".popup-content ul"));
			for (int i=0; i<op.size(); i++) {
				if (op.get(i).getText().equals(ReportGroupName)) {
					getDriver().findElement(By.xpath("//label[contains(text(),'"+ReportGroupName+"')]")).click();
					break;
				}
			}
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#status-report-role .row")).click();	
			Thread.sleep(1000);
			getDriver().findElement(By.id("btn-save-2")).click();
				waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			String actual = getDriver().findElement(By.id("message")).getText();
			String expected = "Report Group details updated successfully.";

			Assert.assertEquals(actual, expected); 
			test.pass("Report Group inactivated successfully");
			results.createNode("Report Group inactivated successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Report Group failed to inactivate");
			results.createNode("Report Group failed to inactivate");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Report Group failed to inactivate");
			results.createNode("Report Group failed to inactivate");
			saveResult(ITestResult.FAILURE, ex);
		}
		getDriver().findElement(popupCloseButton);
		Thread.sleep(1000);
	}


	@Test (description="Test Case: Verify inactivated report group in edit rights screen",enabled= true, priority= 20, groups = {"smoke", "regression"})
	public void VerifyInactivatedReportGroup() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AM-RM-22: Verify inactivated Report Group is not visible on Edit Rights popup", "This test case will verify that inactivated report group is not being displayed on Edit Rights popup");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			preconditions.createNode("4. Click on Create New button; popup appears");
			preconditions.createNode("5. Create a Report Group and make it inactivated");
			steps.createNode("1. Click on edit rights icon to check if report group appears in list or not");

			getDriver().get(url_reportManagement);
				waitElementInvisible(loading_cursor);
			Thread.sleep(4000);

			getDriver().findElement(By.id("edit-role-rights-1")).click();
				waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			List<WebElement> op = getDriver().findElements(By.cssSelector(".popup-content ul"));
			for (int i=0; i<op.size(); i++) {
				if (!op.get(i).getText().equals(ReportGroupName)) {
					Assert.assertTrue(true, "Report Group not visible"); 
					test.pass("Inactivated report group is not displayed in Edit Rights page successfully");
					results.createNode("Inactivated report group is not displayed in Edit Rights page successfully");
					getScreenshot();	
					saveResult(ITestResult.SUCCESS, null);
					break;
				}
			}
		}
		catch(AssertionError er) {
			test.fail("Inactivated report group is displayed in Edit Rights page");
			results.createNode("Inactivated report group is displayed in Edit Rights page");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Inactivated report group is displayed in Edit Rights page");
			results.createNode("Inactivated report group is displayed in Edit Rights page");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Delete Report Group",enabled= true, priority= 21, groups = {"smoke", "regression"})
	public void DeleteReportGroup() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AM-RM-23: Verify user can delete Report Group", "This test case will verify that user can delete Report Group");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			preconditions.createNode("4. Click on Create New button; popup appears");
			preconditions.createNode("5. Create a Report Group");
			steps.createNode("1. Click on delete button next to created report group; confirmaton message appears");
			steps.createNode("2. Click on Yes");

			getDriver().get(url_reportManagement);
				waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			getDriver().findElement(rmReportGroupPopupOpen).click();
				waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			List<WebElement> op = getDriver().findElements(By.cssSelector(".popup-content ul label"));
			for (int i=0; i<op.size(); i++) {
				if (op.get(i).getText().equals(ReportGroupName)) {
					int j=i+1;
					WebElement scroll = getDriver().findElement(By.cssSelector("ul:nth-child("+j+") > li > div > div:nth-child(2) > span.group-card-option.m-l-0px > img"));
					((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Thread.sleep(1000);
					getDriver().findElement(By.cssSelector("ul:nth-child("+j+") > li > div > div:nth-child(2) > span.group-card-option.m-l-0px > img")).click();
					Thread.sleep(1000);
					getDriver().findElement(By.id("delete-group-"+j)).click();
					break;
				}
			}

			Thread.sleep(1000);
			getDriver().findElement(popupYesButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			String actual = getDriver().findElement(alertMessage).getText();
			String expected = "Report Group details has been deleted.";

			Assert.assertEquals(actual, expected); 
			test.pass("Report Group deleted successfully");
			results.createNode("Report Group deleted successfully");
			getScreenshot();	
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("User did not receive an alert message that 'Report Group details deleted.'");
			results.createNode("User did not receive an alert message that 'Report Group details deleted.'");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User did not receive an alert message that 'Report Group details deleted.'");
			results.createNode("User did not receive an alert message that 'Report Group details deleted.'");
			saveResult(ITestResult.FAILURE, ex);
		}
	}
	
	@AfterTest(groups = {"smoke", "regression"})
	public static void endreport() {
		extent.flush();
	}
}