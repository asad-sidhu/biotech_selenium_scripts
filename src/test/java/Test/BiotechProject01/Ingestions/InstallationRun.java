package Test.BiotechProject01.Ingestions;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import MiscFunctions.ClickElement;
import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.ExtentVariables;
import Models.InstallationRunModel;
import Models.ReportFilters;
import PageObjects.BasePage;
import PageObjects.Patho_1LogPage;
import PageObjects.Patho_2LogPage;
import Test.BiotechProject01.Login.LoginTest;

import static MiscFunctions.DateUtil.date;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;

import static Models.IngestionsModel.*;
import static PageObjects.Patho_2LogPage.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class InstallationRun extends BaseTest {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Normal Ingestion"+date+".html");
		spark.config().setReportName("Normal Ingestion Test Report");
	}

	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}
	
	@SuppressWarnings("unchecked") 
	@Test (enabled= true, priority = 1) 
	public void installationRunConfigSalmList() throws InterruptedException, IOException {

		int z = 0;
		InstallationRunModel.lstInstallationRunCreate = InstallationRunModel.FillData();
		for (InstallationRunModel objModel : InstallationRunModel.lstInstallationRunCreate) { 
			try{
				test = extent.createTest(objModel.TestCaseName);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				ExtentVariables.steps.createNode("1. Click on create new button next to Installation Run Config");
				SoftAssert softAssert = new SoftAssert();
				
				for (ReportFilters objFilter : objModel.lstFilters) {

					getDriver().get(Constants.url_configurationAdmin);
					waitElementInvisible(BasePage.loading_cursor);
					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(1000);

					for (int i = 1; i<=100; i++) {
						if (getDriver().findElements(By.cssSelector("#installation-"+i+" td:nth-child(2)")).size() != 0) {
							if (getDriver().findElement(By.cssSelector("#installation-"+i+" td:nth-child(2) label")).getText().equals(InstallationRunModel.installationImprocVersionSalm)) {
								Thread.sleep(1000);
								getDriver().findElement(By.id("edit-installation-"+i)).click();
								waitElementInvisible(BasePage.loading_cursor);
								Thread.sleep(1000);
								break;
							}
//							else {
//								getDriver().findElement(By.id("PathogenName")).sendKeys("Patho_2");
//								getDriver().findElement(By.id("PathogenName")).sendKeys(Keys.ENTER);
//								getDriver().findElement(By.id("create-installation-run")).click();
//								waitElementInvisible(BasePage.loading_cursor);
//								Thread.sleep(1000);
//								getDriver().findElement(By.cssSelector("#ImprocName img")).click();
//								Thread.sleep(800);
//								getDriver().findElement(By.xpath("//*[contains(text(),'ImprocSalm01')]")).click();
//								getDriver().findElement(By.cssSelector("#ImprocVersion input")).sendKeys(Test_Variables.installationImprocVersionSalm);
//								getDriver().findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
//								break;
//							}
						}
					}

					ExtentVariables.steps.createNode("2. Select improc name and improc version from dropdown");
					ExtentVariables.steps.createNode("3. "+objModel.steps);
					getDriver().findElement(By.id("MinMeanVal")).clear();
					getDriver().findElement(By.id("MinMeanVal")).sendKeys(objFilter.MinMean);
					getDriver().findElement(By.id("MaxMeanVal")).clear();
					getDriver().findElement(By.id("MaxMeanVal")).sendKeys(objFilter.MaxMean);
					getDriver().findElement(By.id("MinStdVal")).clear();
					getDriver().findElement(By.id("MinStdVal")).sendKeys(objFilter.MinStd);
					getDriver().findElement(By.id("MaxStdVal")).clear();
					getDriver().findElement(By.id("MaxStdVal")).sendKeys(objFilter.MaxStd);
					getDriver().findElement(By.id("MinStdVal")).click();
					getScreenshot();
					getDriver().findElement(By.id("btn-save")).click();
					Thread.sleep(2000);
					getScreenshot();
					
					RequestSpecification request = RestAssured.given();
					request.header("Content-Type", "application/json");
					JSONObject json = new JSONObject();   
					json.put("piperid", piperId);
					json.put("password", piperPassword);
					json.put("DISAPIVersion", "14.13");
					request.body(json.toString());
					Response response = request.post(Constants.api_authenticate);
					int code = response.getStatusCode();
					Assert.assertEquals(code, 200);

					String data = response.asString();
					System.out.println(data);
					JsonPath jsonPathEvaluator = response.jsonPath();
					String token = jsonPathEvaluator.get("token");		
					//System.out.println(token);	

					Thread.sleep(2000);
					RequestSpecification request_announcement = RestAssured.given();
					request_announcement.header("Content-Type", "application/json");
					request_announcement.header("Authorization", "bearer " +token);
					HttpGet postRequest = new HttpGet(Constants.api_announcementList);
					postRequest.addHeader("Content-Type", "application/json");
					postRequest.addHeader("Authorization", "Bearer "+token);

					JSONObject json1 = new JSONObject();
					JSONObject json2 = new JSONObject();
					JSONObject json3 = new JSONObject();
					JSONObject json4 = new JSONObject();
					JSONArray list = new JSONArray();

					json1.put("runId", AnnouncementRunID);
					json1.put("dateTime", lstApiAnnouncement.get(1));
					json1.put("Piperid",  lstApiAnnouncement.get(2));
					json1.put("MPNCalculationType", lstApiAnnouncement.get(3));
					json2.put("fileName", lstApiAnnouncement.get(4));
					json2.put("checksum", lstApiAnnouncement.get(5));

					list.add(json2);
					json1.put("files", list);
					request_announcement.body(json1.toString());
					Response response1 = request_announcement.post(Constants.api_announcementList);
					String data1 = response1.asString();
					System.out.println(data1);

					if (objModel.runStartAssay) {
						RequestSpecification request_startAssay = RestAssured.given();

						request_startAssay.header("Content-Type", "application/json");
						request_startAssay.header("Authorization", "bearer " +token);

						HttpGet postRequest3 = new HttpGet(Constants.api_announcementList);
						postRequest3.addHeader("Content-Type", "application/json");
						postRequest3.addHeader("Authorization", "Bearer "+token);

						json4.put("DateTime", lstStartAssayPatho_2.get(0).DateTime);
						json4.put("InstrumentId", lstStartAssayPatho_2.get(0).InstrumentID);
						json4.put("UserId", lstStartAssayPatho_2.get(0).UserID);
						json4.put("CartridgeId", lstStartAssayPatho_2.get(0).CartridgeID);
						json4.put("RunId", objModel.sampleID);
						json4.put("PathogenName", objModel.pathogen);				

						request_startAssay.body(json4.toString());
						Response response3 = request_startAssay.post(Constants.api_announcementList);

						String data4 = response3.asString();
						System.out.println(data4);	
						Thread.sleep(5000);
					}

					RequestSpecification request_fileupload = RestAssured.given();
					request_fileupload.header("Content-Type", "application/json");
					request_fileupload.header("Authorization", "bearer " +token);

					HttpGet postRequest1 = new HttpGet(Constants.api_uploadFile);
					postRequest1.addHeader("Content-Type", "application/json");
					postRequest1.addHeader("Authorization", "Bearer "+token);

					json3.put("runId", lstPatho_2Ingest.get(0).runId);
					json3.put("checksum", lstPatho_2Ingest.get(0).checksum);
					json3.put("fileName", lstPatho_2Ingest.get(0).fileName);
					json3.put("fileType", lstPatho_2Ingest.get(0).fileType);
					json3.put("file", lstPatho_2Ingest.get(0).file);
					json3.put("fileJson", objModel.fileJson);				
					json3.put("Improc", lstPatho_2Ingest.get(0).improc);
					json3.put("RunMode", "2");
					json3.put("Pathogen", lstPatho_2Ingest.get(0).pathogen);

					request_fileupload.body(json3.toString());
					Response response2 = request_fileupload.post(Constants.api_uploadFile);

					String data3 = response2.asString();
					System.out.println(data3);

					JsonPath jsonPathEvaluator1 = response.jsonPath();
					jsonPathEvaluator1.get("statusCode");
					Thread.sleep(75000);

					Patho_2LogPage.openPatho_2LogPage();
					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(3000);

					ExtentVariables.steps.createNode("4. Navigate to report and search for Ingested sample id");
					getDriver().findElement(By.id("sampleId_show-filter")).click();
					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(2000);

					getDriver().findElement(By.id("sampleId_search-input")).clear();
					getDriver().findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(3000);	

					try {
						getDriver().findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.sampleID+" b")).click();
					}
					catch (Exception ex) {
						ClickElement.clickByCss(getDriver(), "#sampleId_cust-cb-lst-txt_"+objModel.sampleID);
					}

					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(1500);
					System.out.println(z);
					z++;

					ExtentVariables.steps.createNode("5. Verify the status in QC Code column");
					getDriver().findElement(By.id("sampleId_apply")).click();
					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(1500);					

					for (int x=0; x<12; x++) {
						String getData = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slQCCodeCol)).getText();
						System.out.println(getData);
						Assert.assertEquals(getData, objModel.dataLogOutcome);

						String getRunType = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slRunTypeCol+" label")).getText();
						Assert.assertEquals(getRunType, RunType_Installation, "Run Type is not displayed in table");

						WebElement hover = getDriver().findElement(By.id("audit-trial-"+x));
						Actions builder = new Actions(getDriver());
						builder.moveToElement(hover).build().perform();
						waitElementClickable(By.id("audit-trial-"+x));
						getDriver().findElement(By.id("audit-trial-"+x)).click();
						waitElementInvisible(BasePage.loading_cursor);
						waitElementClickable(By.cssSelector(closeAudit));

						Thread.sleep(1500);

						String getAuditQCCode = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditQCCodeCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditQCCode, objModel.dataLogOutcome);

						String getAuditRunType = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditRunTypeCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditRunType, RunType_Installation);
						
						if (objModel.runStartAssay) {
							ExtentVariables.steps.createNode("Verify Action as 'Modified' in Audit log");
							String getAuditAction = getDriver().findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Modified");
							softAssert.assertEquals(getDriver().findElements(By.cssSelector(".popup-body tr")).size(), 3, "Rows in audit should be 2");
						}

						if (!objModel.runStartAssay) {
							ExtentVariables.steps.createNode("Verify Action as 'Created' in Audit log");
							String getAuditAction = getDriver().findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Created");	
							softAssert.assertEquals(getDriver().findElements(By.cssSelector(".popup-body tr")).size(), 2, "Rows in audit should be 1");
						}

						getScreenshot();
						getDriver().findElement(By.cssSelector(closeAudit)).click();
						Thread.sleep(800);
					}
			
					getScreenshot();
					softAssert.assertAll();	
					ExtentVariables.test.pass(objModel.passStep);
					ExtentVariables.results.createNode(objModel.passStep);
					saveResult(ITestResult.SUCCESS,  null);
					Thread.sleep(1000);		
				}
			}catch(AssertionError er) {
				ExtentVariables.test.fail(objModel.failStep);
				ExtentVariables.results.createNode(objModel.failStep);
				saveResult(ITestResult.FAILURE,  new Exception(er));
			}catch(Exception ex){
				ExtentVariables.test.fail(objModel.failStep);
				ExtentVariables.results.createNode(objModel.failStep);
				saveResult(ITestResult.FAILURE,  ex);
			}
		}
	}


	@SuppressWarnings({ "unchecked", "unused" })
	@Test (enabled= true, priority = 3) 
	public void installationRunConfigPatho_1() throws InterruptedException, IOException {

		int z = 0;
		InstallationRunModel.lstInstallationRunCreatePatho_1 = InstallationRunModel.FillDataPatho_1();
		for (InstallationRunModel objModel : InstallationRunModel.lstInstallationRunCreatePatho_1) { 
			try{
				ExtentVariables.test = ExtentVariables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
				ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
				ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

				ExtentVariables.preconditions.createNode("1. Go to url " +Constants.url_loginPage);
				ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				ExtentVariables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				ExtentVariables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
				ExtentVariables.steps.createNode("1. Click on create new button next to Installation Run Config");
				SoftAssert softAssert = new SoftAssert();
				
				for (ReportFilters objFilter : objModel.lstFilters) {

					getDriver().get(Constants.url_configurationAdmin);
					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(1000);

					for (int i = 1; i<=100; i++) {
						if (getDriver().findElements(By.cssSelector("#installation-"+i+" td:nth-child(2)")).size() != 0) {
							if (getDriver().findElement(By.cssSelector("#installation-"+i+" td:nth-child(2) label")).getText().equals(InstallationRunModel.installationImprocVersionCocci)) {
								Thread.sleep(1000);
								getDriver().findElement(By.id("edit-installation-"+i)).click();
								waitElementInvisible(BasePage.loading_cursor);
								Thread.sleep(1500);
								break;
							}
						}
					}

					ExtentVariables.steps.createNode("2. Select improc name and improc version from dropdown");
					ExtentVariables.steps.createNode("3. "+objModel.steps);
					getDriver().findElement(By.id("MinMeanVal")).clear();
					getDriver().findElement(By.id("MinMeanVal")).sendKeys(objFilter.MinMean);
					getDriver().findElement(By.id("MaxMeanVal")).clear();
					getDriver().findElement(By.id("MaxMeanVal")).sendKeys(objFilter.MaxMean);
					getDriver().findElement(By.id("MinStdVal")).clear();
					getDriver().findElement(By.id("MinStdVal")).sendKeys(objFilter.MinStd);
					getDriver().findElement(By.id("MaxStdVal")).clear();
					getDriver().findElement(By.id("MaxStdVal")).sendKeys(objFilter.MaxStd);
					getDriver().findElement(By.id("MinStdVal")).click();
					getDriver().findElement(By.id("btn-save")).click();
					Thread.sleep(2000);

					////////////////////////////////////////////////////////////////////////////////////////

					RequestSpecification request = RestAssured.given();
					request.header("Content-Type", "application/json");
					JSONObject json = new JSONObject();   
					json.put("piperid", piperId);
					json.put("password", piperPassword);
					json.put("DISAPIVersion", "14.13");
					request.body(json.toString());
					Response response = request.post(Constants.api_authenticate);
					int code = response.getStatusCode();
					Assert.assertEquals(code, 200);

					String data = response.asString();
					System.out.println(data);
					JsonPath jsonPathEvaluator = response.jsonPath();
					String token = jsonPathEvaluator.get("token");		
					System.out.println(token);	

					Thread.sleep(2000);
					RequestSpecification request_announcement = RestAssured.given();
					request_announcement.header("Content-Type", "application/json");
					request_announcement.header("Authorization", "bearer " +token);
					HttpGet postRequest = new HttpGet(Constants.api_announcementList);
					postRequest.addHeader("Content-Type", "application/json");
					postRequest.addHeader("Authorization", "Bearer "+token);

					JSONObject json1 = new JSONObject();
					JSONObject json2 = new JSONObject();
					JSONObject json3 = new JSONObject();
					JSONObject json4 = new JSONObject();
					JSONArray list = new JSONArray();

					json1.put("runId", lstApiAnnouncement.get(0));
					json1.put("dateTime", lstApiAnnouncement.get(1));
					json1.put("Piperid",  lstApiAnnouncement.get(2));
					json1.put("MPNCalculationType", lstApiAnnouncement.get(3));
					json2.put("fileName", lstApiAnnouncement.get(4));
					json2.put("checksum", lstApiAnnouncement.get(5));

					list.add(json2);
					json1.put("files", list);
					request_announcement.body(json1.toString());
					Response response1 = request_announcement.post(Constants.api_announcementList);
					String data1 = response1.asString();
					System.out.println(data1);

					if (objModel.runStartAssay) {
						RequestSpecification request_startAssay = RestAssured.given();
						request_startAssay.header("Content-Type", "application/json");
						request_startAssay.header("Authorization", "bearer " +token);

						HttpGet postRequest3 = new HttpGet(Constants.api_announcementList);
						postRequest3.addHeader("Content-Type", "application/json");
						postRequest3.addHeader("Authorization", "Bearer "+token);

						json4.put("DateTime", lstStartAssayPatho_1.get(0).DateTime);
						json4.put("InstrumentId", lstStartAssayPatho_1.get(0).InstrumentID);
						json4.put("UserId", lstStartAssayPatho_1.get(0).UserID);
						json4.put("CartridgeId", lstStartAssayPatho_1.get(0).CartridgeID);
						json4.put("RunId", objModel.sampleID);
						json4.put("PathogenName", lstStartAssayPatho_1.get(0).PathogenName);				

						request_startAssay.body(json4.toString());
						Response response3 = request_startAssay.post(Constants.api_announcementList);

						String data4 = response3.asString();
						System.out.println(data4);
						Thread.sleep(10000);
					}

					RequestSpecification request_fileupload = RestAssured.given();
					request_fileupload.header("Content-Type", "application/json");
					request_fileupload.header("Authorization", "bearer " +token);

					HttpGet postRequest1 = new HttpGet(Constants.api_uploadFile);
					postRequest1.addHeader("Content-Type", "application/json");
					postRequest1.addHeader("Authorization", "Bearer "+token);

					json3.put("runId", lstPatho_1Ingest.get(0).runId);
					json3.put("checksum", lstPatho_1Ingest.get(0).checksum);
					json3.put("fileName", lstPatho_1Ingest.get(0).fileName);
					json3.put("fileType", lstPatho_1Ingest.get(0).fileType);
					json3.put("file", lstPatho_1Ingest.get(0).file);
					json3.put("fileJson", objModel.fileJson);				
					json3.put("Improc", lstPatho_1Ingest.get(0).improc);
					json3.put("RunMode", "2");
					json3.put("Pathogen", lstPatho_1Ingest.get(0).pathogen);

					request_fileupload.body(json3.toString());
					Response response2 = request_fileupload.post(Constants.api_uploadFile);

					String data3 = response2.asString();
					System.out.println(data3);

					JsonPath jsonPathEvaluator1 = response.jsonPath();
					jsonPathEvaluator1.get("statusCode");
					Thread.sleep(75000);

					Patho_1LogPage.openPatho_1LogPage();
					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(2000);

					ExtentVariables.steps.createNode("4. Navigate to report and search for Ingested sample id");
					getDriver().findElement(By.id("sampleId_show-filter")).click();
					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(2000);

					getDriver().findElement(By.id("sampleId_search-input")).clear();
					getDriver().findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(3000);	

					try {
						getDriver().findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.sampleID+" b")).click();
					}
					catch (Exception ex) {
						ClickElement.clickByCss(getDriver(), "#sampleId_cust-cb-lst-txt_"+objModel.sampleID);
					}

					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(1500);
					System.out.println(z);
					z++;

					ExtentVariables.steps.createNode("5. Verify the status in QC Code column");
					getDriver().findElement(By.id("sampleId_apply")).click();
					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(1500);				

					for (int x=0; x<12; x++) {
						String getData = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+Patho_1LogPage.clQCCodeCol)).getText();
						System.out.println(getData);
						Assert.assertEquals(getData, objModel.dataLogOutcome);

						String getRunType = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+Patho_1LogPage.clRunTypeCol+" label")).getText();
						Assert.assertEquals(getRunType, RunType_Installation, "Run Type is not displayed in table");

						WebElement hover = getDriver().findElement(By.id("audit-trial-"+x));
						Actions builder = new Actions(getDriver());
						builder.moveToElement(hover).build().perform();
						waitElementClickable(By.id("audit-trial-"+x));
						getDriver().findElement(By.id("audit-trial-"+x)).click();
						waitElementInvisible(BasePage.loading_cursor);
						waitElementClickable(By.cssSelector(closeAudit));
						Thread.sleep(1500);

						String getAuditQCCode = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Patho_1LogPage.clAuditQCCodeCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditQCCode, objModel.dataLogOutcome);

						String getAuditRunType = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Patho_1LogPage.clAuditRunTypeCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditRunType, RunType_Installation);

						if (objModel.runStartAssay) {
							softAssert.assertEquals(getDriver().findElements(By.id("audit-action-1")).size(), 1);
							softAssert.assertEquals(getDriver().findElements(By.id("audit-action-2")).size(), 0);
						}
						else {
							softAssert.assertEquals(getDriver().findElements(By.id("audit-action-0")).size(), 1);
							softAssert.assertEquals(getDriver().findElements(By.id("audit-action-1")).size(), 0);
						}
						
						if (objModel.runStartAssay) {
							ExtentVariables.steps.createNode("Verify Action as 'Modified' in Audit log");
							String getAuditAction = getDriver().findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Modified");
							softAssert.assertEquals(getDriver().findElements(By.cssSelector(".popup-body tr")).size(), 3, "Rows in audit should be 2");
						}
						else {
							ExtentVariables.steps.createNode("Verify Action as 'Created' in Audit log");
							String getAuditAction = getDriver().findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Created");	
							softAssert.assertEquals(getDriver().findElements(By.cssSelector(".popup-body tr")).size(), 2, "Rows in audit should be 1");
						}

						getScreenshot();
						getDriver().findElement(By.cssSelector(closeAudit)).click();
						Thread.sleep(800);
					}
					
					getScreenshot();
					softAssert.assertAll();
					ExtentVariables.test.pass(objModel.passStep);
					ExtentVariables.results.createNode(objModel.passStep);
					saveResult(ITestResult.SUCCESS,  null);
					Thread.sleep(1000);		
				}
			}catch(AssertionError er) {
				ExtentVariables.test.fail(objModel.failStep);
				ExtentVariables.results.createNode(objModel.failStep);
				saveResult(ITestResult.FAILURE,  new Exception(er));
			}catch(Exception ex){
				ExtentVariables.test.fail(objModel.failStep);
				ExtentVariables.results.createNode(objModel.failStep);
				saveResult(ITestResult.FAILURE,  ex);
			}
		}
	}

}
