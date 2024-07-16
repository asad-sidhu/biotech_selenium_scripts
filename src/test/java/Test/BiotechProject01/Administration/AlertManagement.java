package Test.BiotechProject01.Administration;

import java.io.IOException;

import java.util.ArrayList;


import java.util.List;

import MiscFunctions.NavigateToScreen;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.json.simple.JSONArray;
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

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.ExtentVariables;
import Models.AlertManagementModel;
import Models.ForgotPasswordModel;
import Models.ReportFilters;
import PageObjects.BasePage;
import Test.BiotechProject01.Login.LoginTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.Methods.*;
import static Models.IngestionsModel.*;
import static PageObjects.AlertManagementPage.alertManagementTitle;
import static PageObjects.UserManagementPage.userManagementTitle;

public class AlertManagement extends BaseTest{

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
//		ExtentVariables.spark = new ExtentSparkReporter("target/Reports/Administration_Alert_Management"+DateUtil.date+".html");
//		ExtentVariables.spark.config().setReportName("Agreement Management Test Report");
		initReport("Administration_Alert_Management");
	}

	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
		NavigateToScreen.navigate(url_notificationSettings, "Alert Management", alertManagementTitle);
	}

	@Test (description="Test Case: Navigate to Alert Management Screen",enabled= true, priority = 1) 
	public void NavigateAlertManagement() throws InterruptedException, IOException {
		try {
			ExtentVariables.test = ExtentVariables.extent.createTest("AN-License-01: Verify user can navigate to Alert Management screen", "This test case will verify that user can navigate to Alert Management screen");
			ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
			ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
			ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

			getScreenshot();
			getDriver().get(url_notificationSettings);
			waitElementVisible(By.id("Alert Management"));
			Thread.sleep(1000);

			ExtentVariables.preconditions.createNode("1. Go to url " + url_loginPage);
			ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			ExtentVariables.steps.createNode("1. Hover to sidebar to expand the menu");
			ExtentVariables.steps.createNode("2. Click on Administration and select Alert Management");

			Assert.assertEquals(getDriver().findElement(By.id("Alert Management")).getText(), "Alert Management"); 
			ExtentVariables.test.pass("User navigated successfully");
			ExtentVariables.results.createNode("User navigates to Alert Management Screen");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			ExtentVariables.test.fail("User navigation failed");
			ExtentVariables.results.createNode("User did not navigate to Alert Management Screen");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			ExtentVariables.test.fail("User navigation failed");
			ExtentVariables.results.createNode("User did not navigate to Alert Management Screen");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Login To Email",enabled= true, priority = 2) 
	public void EmailLogin() throws InterruptedException, IOException {
		try {

			((JavascriptExecutor)getDriver()).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
			getDriver().switchTo().window(tabs.get(1));

			getDriver().get(url_gmailLogin);
			Thread.sleep(1500);

			if (getDriver().findElements(By.id("identifierId")).size() != 0) {
				getDriver().findElement(By.id("identifierId")).sendKeys(ForgotPasswordModel.forgotPassword_email);    
				Thread.sleep(1000);
				getDriver().findElement(By.id("identifierId")).sendKeys(Keys.ENTER);	
			}

			waitElementVisible(By.name("password"));
			Thread.sleep(1000);
			getDriver().findElement(By.name("password")).sendKeys(ForgotPasswordModel.forgotPassword_password);
			getDriver().findElement(By.name("password")).sendKeys(Keys.ENTER);	
			Thread.sleep(6000);
			System.out.println(getDriver().findElements(By.id(("headingText"))).size());
			
			if (getDriver().findElements(By.id(("headingText"))).size() !=  0) {
				getDriver().findElement(By.cssSelector((".vxx8jf"))).click();
				Thread.sleep(2000);
				getDriver().findElement(By.id("knowledge-preregistered-email-response")).sendKeys(ForgotPasswordModel.forgotPasswordSecurityEmail);
				getDriver().findElement(By.id("knowledge-preregistered-email-response")).sendKeys(Keys.ENTER);
			}

			Thread.sleep(2000);
			ArrayList<String> tabs2 = new ArrayList<String> (getDriver().getWindowHandles());
			getDriver().switchTo().window(tabs2.get(0));
			Thread.sleep(2000);	
		}
		catch(Exception ex) {

		}

	}


	@SuppressWarnings({ "unused", "unchecked" })
	@Test (enabled= false, priority = 2) 
	public void CreateAlert() throws InterruptedException, IOException {

		AlertManagementModel.lstAlertCreate = AlertManagementModel.FillData();
		for (AlertManagementModel objModel : AlertManagementModel.lstAlertCreate) { 
			try{
				ExtentVariables.test = ExtentVariables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
				ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
				ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

				ExtentVariables.preconditions.createNode("1. Go to url " + url_loginPage);
				ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				ExtentVariables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				ExtentVariables.preconditions.createNode("4. Navigate to Alert Management screen");
				ExtentVariables.steps.createNode("1. Click on create new button");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {

						for (int i=2; i <=getDriver().findElements(By.cssSelector("tr")).size(); i++) {
							if (getDriver().findElements(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).size() != 0) {
								if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).getText().equals(objModel.AlertDesc)) {
									getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).click();
									Thread.sleep(1500);
									WebElement filter_scroll = getDriver().findElement(By.id("duplicate-alert"));
									((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
									Thread.sleep(1000);
									if (getDriver().findElements(By.id("activate-alert")).size() == 1) {
										getDriver().findElement(By.id("activate-alert")).click();
									}
									else {
										getDriver().findElement(By.id("close-duplicate-modal")).click();
									}
								}
							}
						}

						List<WebElement> rows = getDriver().findElements(By.cssSelector("td:nth-child(6)"));
						List<String> value = new ArrayList<String>();

						for (int j=0; j<rows.size(); j++){
							//System.out.println(rows.get(j).getText());
							value.add(rows.get(j).getText());
						}
						if (value.contains(objModel.AlertDesc)){
							System.out.println("Value found");
						}
						else{
							//System.out.println("Not Found");
							getDriver().findElement(By.id("create-alert")).click();
							Thread.sleep(1000);
							getDriver().findElement(By.cssSelector("#organizationId input")).sendKeys("BiotechProject01");
							Thread.sleep(1000);
							getDriver().findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
							getDriver().findElement(By.cssSelector("div[class='form-control form-control-inv']")).click();
							Thread.sleep(1000);
							getDriver().findElement(By.cssSelector(".d-inline-block ")).click();
							Thread.sleep(1000);
							getDriver().findElement(By.id("list-title_apply")).click();
							Thread.sleep(1000);
							getDriver().findElement(By.id("alert-info-input")).sendKeys(objModel.AlertInfo);
							getDriver().findElement(By.id("alert-desc-input")).sendKeys(objModel.AlertDesc);
							Thread.sleep(1000);
							getDriver().findElement(By.id("btn-next")).click();
							Thread.sleep(1000);
							getDriver().findElement(By.cssSelector("#dataSourcesid input")).sendKeys(objModel.DataSource);
							getDriver().findElement(By.cssSelector("#dataSourcesid input")).sendKeys(Keys.ENTER);

							if (getDriver().findElements(By.cssSelector("#reportId input")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector("#reportId input")).sendKeys(objModel.Report);
								getDriver().findElement(By.cssSelector("#reportId input")).sendKeys(Keys.ENTER);
							}

							if (getDriver().findElements(By.cssSelector("#fieldId input")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector("#fieldId input")).sendKeys(objModel.AlertVariable);
								getDriver().findElement(By.cssSelector("#fieldId input")).sendKeys(Keys.ENTER);
							}

							if (getDriver().findElements(By.cssSelector("#alertModesId input")).size() != 0 ) {
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector("#alertModesId input")).sendKeys(objModel.AlertMode);
								getDriver().findElement(By.cssSelector("#alertModesId input")).sendKeys(Keys.ENTER);	  //threshold
							}

							Thread.sleep(1000);
							if (getDriver().findElements(By.cssSelector("#thresholdConditionId input")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(objModel.Condition);
								getDriver().findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(Keys.ENTER);
							}

							Thread.sleep(1000);
							if (getDriver().findElements(By.id("minId")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.id("minId")).sendKeys(objModel.minValue);
							}

							Thread.sleep(1000);
							if (getDriver().findElements(By.id("maxId")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.id("maxId")).sendKeys(objModel.maxValue);
							}

							Thread.sleep(1000);
							System.out.println("adasd");
							if (getDriver().findElements(By.id("aggregateModeId")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector("#aggregateModeId input")).sendKeys(objModel.AggregateMode);
								getDriver().findElement(By.cssSelector("#aggregateModeId input")).sendKeys(Keys.ENTER);
							}

							Thread.sleep(1000);
							if (getDriver().findElements(By.id("aggregeateConditionId")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector("#aggregeateConditionId input")).sendKeys(objModel.Condition);
								getDriver().findElement(By.cssSelector("#aggregeateConditionId input")).sendKeys(Keys.ENTER);
							}

							Thread.sleep(1000);
							if (getDriver().findElements(By.id("alert-aggregate-days-input")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.id("alert-aggregate-days-input")).sendKeys(objModel.Text);
							}

							if (getDriver().findElements(By.id("absenceDelayFactorId-input")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.id("absenceDelayFactorId-input")).sendKeys(objModel.Deviation);
							}

							if (getDriver().findElements(By.id("alert-deviation-input")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.id("alert-deviation-input")).sendKeys(objModel.Deviation);	
							}

							if (getDriver().findElements(By.id("notifyEveryId")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector("#notifyEveryId input")).sendKeys(objModel.Notify);
								getDriver().findElement(By.cssSelector("#notifyEveryId input")).sendKeys(Keys.ENTER);
							}

							if (getDriver().findElements(By.id("alert-custom-days-input")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.id("alert-custom-days-input")).sendKeys(objModel.customDays);
							}

							if (getDriver().findElements(By.id("alert-duration-input")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.id("alert-duration-input")).sendKeys(objModel.Text);
							}					

							if (getDriver().findElements(By.id("absenceDelayFactorId")).size() != 0) {
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector("#absenceDelayFactorId input")).sendKeys(objModel.Days);
								getDriver().findElement(By.cssSelector("#absenceDelayFactorId input")).sendKeys(Keys.ENTER);
							}

							Thread.sleep(1000);
							getDriver().findElement(By.id("btn-next")).click();
							Thread.sleep(1000);
							getDriver().findElement(By.id("btn-finish")).click();
							Thread.sleep(1000);
							waitElementVisible(BasePage.alertMessage);
							Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "New alert configuration created.");
							Thread.sleep(1000);
						}

						ExtentVariables.test.pass("User was able to create alert successfully");
						ExtentVariables.results.createNode("User was able to create alert successfully");
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
					}catch(AssertionError er) {
						ExtentVariables.test.fail("User failed to create alert");
						ExtentVariables.results.createNode("User failed to create alert");
						saveResult(ITestResult.FAILURE, new Exception(er));
					}catch(Exception ex){
						ExtentVariables.test.fail("User failed to create alert");
						ExtentVariables.results.createNode("User failed to create alert");
						saveResult(ITestResult.FAILURE, ex);
					}	
				}

				if (objModel.isGenerate) {
				RequestSpecification request = RestAssured.given();
				request.header("Content-Type", "application/json");
				JSONObject json = new JSONObject();   
				json.put("piperid", piperId);
				json.put("password", piperPassword);
				json.put("DISAPIVersion", "14.13");
				request.body(json.toString());
				Response response = request.post(api_authenticate);
				int code = response.getStatusCode();

				String data = response.asString();
				System.out.println(data);
				JsonPath jsonPathEvaluator = response.jsonPath();
				String token = jsonPathEvaluator.get("token");		
				//System.out.println(token);	

				Thread.sleep(2000);
				RequestSpecification request_announcement = RestAssured.given();

				request_announcement.header("Content-Type", "application/json");
				request_announcement.header("Authorization", "bearer " +token);

				HttpGet postRequest = new HttpGet(api_announcementList);
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
				Response response1 = request_announcement.post(api_announcementList);

				String data1 = response1.asString();
				System.out.println(data1);

				RequestSpecification request_fileupload = RestAssured.given();

				request_fileupload.header("Content-Type", "application/json");
				request_fileupload.header("Authorization", "bearer " +token);

				HttpGet postRequest1 = new HttpGet(api_uploadFile);
				postRequest1.addHeader("Content-Type", "application/json");
				postRequest1.addHeader("Authorization", "Bearer "+token);

				json3.put("runId", lstPatho_2Ingest.get(0).runId);
				json3.put("checksum", lstPatho_2Ingest.get(0).checksum);
				json3.put("fileName", lstPatho_2Ingest.get(0).fileName);
				json3.put("fileType", lstPatho_2Ingest.get(0).fileType);
				json3.put("file", lstPatho_2Ingest.get(0).file);
				json3.put("fileJson", objModel.fileJson);				
				json3.put("Improc", lstPatho_2Ingest.get(0).improc);
				json3.put("RunMode", "1");
				json3.put("Pathogen", objModel.pathogen);

				request_fileupload.body(json3.toString());
				Response response2 = request_fileupload.post(Constants.api_uploadFile);

				String data3 = response2.asString();
				System.out.println(data3);

				JsonPath jsonPathEvaluator1 = response.jsonPath();
				jsonPathEvaluator1.get("statusCode");
				Thread.sleep(1000);
				}

				ArrayList<String> tabs2 = new ArrayList<String> (getDriver().getWindowHandles());
				getDriver().switchTo().window(tabs2.get(1));
				Thread.sleep(2000);	

				getDriver().navigate().refresh();
				waitElementVisible(By.xpath("//*[@class='yW']/span"));
				Thread.sleep(2000);
				List<WebElement> a = getDriver().findElements(By.xpath("//*[@class='yW']/span"));
				for(int i=0;i<a.size();i++){
					if(a.get(i).getText().equals("BiotechProject01 Alert Center")){  
						Assert.assertTrue(a.get(i).getText().equals("BiotechProject01 Alert Center"));
						a.get(i).click();
					}
				}

				Thread.sleep(1000);
				getDriver().findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")).click();

				getDriver().switchTo().window(tabs2.get(0));
				Thread.sleep(2000);	
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
