package Test.BiotechProject01.Ingestions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.regex.Pattern;

import org.apache.http.client.methods.HttpGet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
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
import Models.PAModel;
import PageObjects.BasePage;
import PageObjects.Patho_2LogPage;
import Test.BiotechProject01.Login.LoginTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static PageObjects.Patho_2LogPage.*;
import static PageObjects.BasePage.*;
import static Models.PAModel.*;
import static MiscFunctions.Methods.*;
import static Models.IngestionsModel.*;

public class PAConfig extends BaseTest {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		ExtentVariables.spark = new ExtentSparkReporter("target/Reports/PA_Configuration"+DateUtil.date+".html");
		ExtentVariables.spark.config().setReportName("P/A Configuration Test Report"); 
	}

	@Test
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Test (enabled= true, priority = 1) 
	public void PAConfiguration() throws InterruptedException, IOException {
		
		int z = 0;
		PAModel.lstPAPatho_2 = PAModel.FillData();
		for (PAModel objModel : PAModel.lstPAPatho_2) { 
			SoftAssert softAssert = new SoftAssert();
	//		try {
			try{
				ExtentVariables.test = ExtentVariables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
				ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
				ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

				ExtentVariables.preconditions.createNode("1. Go to url " +Constants.url_loginPage);
				ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				ExtentVariables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				ExtentVariables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
				ExtentVariables.steps.createNode("1. Create new configuration and get its sample matrix id from database");

				if (objModel.runIngestion) {					
					getDriver().get(Constants.url_configurationAdmin);			
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);

					for (int i = 1; i<=100; i++) {
						if (getDriver().findElements(By.cssSelector("#mpn-"+i+" td:nth-child(4) label")).size() != 0) {
							if (getDriver().findElement(By.cssSelector("#mpn-"+i+" td:nth-child(4) label")).getText().equals(objModel.SampleMatrixConfig)) {
								Thread.sleep(1000);
								break;
							}
						}
					}

					ExtentVariables.steps.createNode("2. Ingest with runmode 3 and sample matrix id");
					RequestSpecification request = RestAssured.given();
					request.header("Content-Type", "application/json");
					JSONObject json = new JSONObject();   
					json.put("piperid", piperId);
					json.put("password", piperPassword);
					json.put("DISAPIVersion", "14.13");
					request.body(json.toString());
					Response response = request.post(Constants.api_authenticate);
					int code = response.getStatusCode();
					softAssert.assertEquals(code, 200);

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
					System.out.println(objModel.sampleID);	
					Thread.sleep(10000);
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
					json3.put("RunMode", "3");
					json3.put("Pathogen", objModel.pathogen);
					z++;

					request_fileupload.body(json3.toString());
					Response response2 = request_fileupload.post(Constants.api_uploadFile);

					String data3 = response2.asString();
					System.out.println(data3);

					JsonPath jsonPathEvaluator1 = response.jsonPath();
					jsonPathEvaluator1.get("statusCode");
					Thread.sleep(45000);

					Patho_2LogPage.openPatho_2LogPage();
					waitElementInvisible(loading_cursor);
					waitElementClickable(By.id("sampleId_show-filter"));
					Thread.sleep(2000);
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);

					ExtentVariables.steps.createNode("3. Navigate to report and search for Ingested sample id");
					getDriver().findElement(By.id("sampleId_show-filter")).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					getDriver().findElement(By.id("sampleId_search-input")).clear();
					getDriver().findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);		
					try {
						getDriver().findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.sampleID+" b")).click();
					}
					catch (Exception ex) {
						Patho_2LogPage.openPatho_2LogPage();
						waitElementInvisible(loading_cursor);
						waitElementClickable(By.id("sampleId_show-filter"));
						Thread.sleep(2000);
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);

						ExtentVariables.steps.createNode("3. Navigate to report and search for Ingested sample id");
						getDriver().findElement(By.id("sampleId_show-filter")).click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);
						getDriver().findElement(By.id("sampleId_search-input")).clear();
						getDriver().findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
						waitElementInvisible(loading_cursor);
						Thread.sleep(2000);	
						ClickElement.clickByCss(getDriver(), "#sampleId_cust-cb-lst-txt_"+objModel.sampleID);
					}
					waitElementInvisible(loading_cursor);
					Thread.sleep(1500);

					ExtentVariables.steps.createNode("4. Compare the result column with w2 cell count; Threshold value is "+PA_Threshold);
					getDriver().findElement(By.id("sampleId_apply")).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(1500);	
					getScreenshot();

					for (int x=0; x<12; x++) {
						String getResult = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slResultCol)).getText();
						String getCount = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slW2CellCountCol)).getText();
						String getQCCode = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slQCCodeCol)).getText();
						String getLane = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slLaneCol)).getText();

						String regex = "(?<=[\\d])(,)(?=[\\d])";
						Pattern p = Pattern.compile(regex);
						String str = getCount;
						//Matcher m = p.matcher(str);
						//str = m.replaceAll("");
						str = str.replaceAll(",", "");


						String getSiteID = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slSiteIDCol)).getText();
						softAssert.assertEquals(getSiteID, CSiteID_Salm);

						String getAssay = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slAssayCol)).getText();
						softAssert.assertTrue(getAssay.equalsIgnoreCase(objModel.pathogen));

						String getResultStatus = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slResultStatusCol)).getText();
						softAssert.assertEquals(getResultStatus, "Completed");

						String getTime = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slTimeCol)).getText();

						if (objModel.errorCode == false) {

							if (Integer.parseInt(str) <= Integer.parseInt(PA_Threshold)) {
								if (getResult.equals("Missing Sample Metadata") || getResult.equals("Invalid Result, Please Retest all Dilutions")) {
									System.out.print("");
								}
								else {
									softAssert.assertEquals(getResult, "Negative", "W2CellCount: "+str+" | Threshold: "+PA_Threshold);
								}
							}

							if (Integer.parseInt(str) > Integer.parseInt(PA_Threshold)) {
								if (getResult.equals("Missing Sample Metadata") || getResult.equals("Invalid Result, Please Retest all Dilutions")) {
									System.out.println("");
								}
								else {
									softAssert.assertEquals(getResult, "Positive", "W2CellCount: "+str+" | Threshold: "+PA_Threshold);
								}
							}
						}

						if (objModel.errorCode) {

							if (Integer.parseInt(str) <= Integer.parseInt(PA_Threshold) && !getLane.equals("1")) {
								if (getResult.equals("Missing Sample Metadata") || getResult.equals("Invalid Result, Please Retest all Dilutions")) {
									System.out.print("");
								}
								else {
									softAssert.assertEquals(getResult, "Negative", "W2CellCount: "+str+" | Threshold: "+PA_Threshold);
								}
							}

							if (Integer.parseInt(str) > Integer.parseInt(PA_Threshold) && !getLane.equals("1")) {
								if (getResult.equals("Missing Sample Metadata") || getResult.equals("Invalid Result, Please Retest all Dilutions")) {
									System.out.print("");
								}
								else {
									softAssert.assertEquals(getResult, "Positive", "W2CellCount: "+str+" | Threshold: "+PA_Threshold);
								}
							}		

							if (getLane.equals("1")) { 
								if (getResult.equals("Missing Sample Metadata") || getResult.equals("Invalid Result, Please Retest all Dilutions")) {
									System.out.print("");
								}
								else {
									softAssert.assertEquals(getResult, "QCFail");
									softAssert.assertEquals(getQCCode, "E066");
								}
							}
						}

						WebElement hover = getDriver().findElement(By.id("audit-trial-"+x));
						Actions builder = new Actions(getDriver());
						builder.moveToElement(hover).build().perform();
						waitElementClickable(By.id("audit-trial-"+x));
						getDriver().findElement(By.id("audit-trial-"+x)).click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);
						getScreenshot();
						String getAuditRunType = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditRunTypeCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditRunType, RunType_PA);

						String getAuditTestSiteId = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteIDCol+".text-dark")).getText();
						softAssert.assertTrue(getAuditTestSiteId.isEmpty() == false);

						String getAuditTestSiteName = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteNameCol+".text-dark")).getText();
						softAssert.assertTrue(getAuditTestSiteName.isEmpty() == false);

						String getAuditAction = getDriver().findElement(By.id("audit-action-0")).getText();
						softAssert.assertEquals(getAuditAction, "Modified");

						String getAuditTime = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTimeCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditTime, getTime);

						String getAuditResultStatus = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditResultStatusCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditResultStatus, "Completed");

						String getAuditLane = getDriver().findElement(By.cssSelector(".apl-resp-table td:nth-child("+slAuditLaneColCss+")")).getText();
						String getAuditQCCode = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditQCCodeCol+".text-dark")).getText();

						String getAuditCount = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditW2CellCountCol+".text-dark")).getText();
						String getAuditResult = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditResultCol+".text-dark")).getText();
						String str1 = getAuditCount;
						str1 = str1.replaceAll(",", "");

						if (objModel.errorCode == false) {
							if (Integer.parseInt(str1) <= Integer.parseInt(PA_Threshold)) {
								if (getAuditResult.equals("Missing Sample Metadata") || getAuditResult.equals("Invalid Result, Please Retest all Dilutions")) {
									System.out.print("");
								}
								else {
									softAssert.assertEquals(getAuditResult, "Negative", "W2CellCount: "+str1+" | Threshold: "+PA_Threshold);
								}
							}

							if (Integer.parseInt(str1) > Integer.parseInt(PA_Threshold)) {
								if (getAuditResult.equals("Missing Sample Metadata") || getAuditResult.equals("Invalid Result, Please Retest all Dilutions")) {
									System.out.print("");
								}
								else {
									softAssert.assertEquals(getAuditResult, "Positive", "W2CellCount: "+str1+" | Threshold: "+PA_Threshold);
								}
							}
						}

						if (objModel.errorCode) {

							if (Integer.parseInt(str1) <= Integer.parseInt(PA_Threshold) && !getAuditLane.equals("1")) {
								if (getAuditResult.equals("Missing Sample Metadata") || getAuditResult.equals("Invalid Result, Please Retest all Dilutions")) {
									System.out.print("");
								}
								else {
									softAssert.assertEquals(getAuditResult, "Negative", "W2CellCount: "+str1+" | Threshold: "+PA_Threshold);
								}
							}

							if (Integer.parseInt(str1) > Integer.parseInt(PA_Threshold) && !getAuditLane.equals("1")) {
								if (getAuditResult.equals("Missing Sample Metadata") || getAuditResult.equals("Invalid Result, Please Retest all Dilutions")) {
									System.out.print("");
								}
								else {
									softAssert.assertEquals(getAuditResult, "Positive", "W2CellCount: "+str1+" | Threshold: "+PA_Threshold);
								}
							}

							if (getAuditLane.equals("1")) {
								if (getAuditResult.equals("Missing Sample Metadata") || getAuditResult.equals("Invalid Result, Please Retest all Dilutions")) {
									System.out.print("");
								}
								else {
									softAssert.assertEquals(getAuditResult, "QCFail");
									softAssert.assertEquals(getAuditQCCode, "E066");
								}
							}
						}
						getDriver().findElement(By.cssSelector(closeAudit)).click();
						Thread.sleep(800);
					}
			//		softAssert.assertAll();
				}

				if (objModel.runTemplateUpload) {
					if (Integer.parseInt(getDriver().findElement(By.id("results-found-count")).getText()) == 12) {
						FileInputStream fsIP= new FileInputStream(new File("./Excel/"+PA_fileName));
						@SuppressWarnings("resource")
						XSSFWorkbook wb = new XSSFWorkbook(fsIP);
						XSSFSheet worksheet = wb.getSheetAt(0);
						Cell cell = null;

						for (int i=0; i<12; i++) {

							String getResultDate = getDriver().findElement(By.cssSelector("#row-"+i+" #col-"+slDateCol)).getText();
							cell=worksheet.getRow(i+1).createCell(17); 
							cell.setCellValue(getResultDate);  

							String getLane = getDriver().findElement(By.cssSelector("#row-"+i+" #col-"+slLaneCol)).getText();
							cell=worksheet.getRow(i+1).createCell(16); 
							cell.setCellValue(getLane);  

							cell=worksheet.getRow(i+1).createCell(19); 
							cell.setCellValue(CartridgeID);

							String getResultID = getDriver().findElement(By.cssSelector("#row-"+i+" #col-"+slResultIDCol)).getText();
							cell=worksheet.getRow(i+1).createCell(0); 
							cell.setCellValue(getResultID);

							cell=worksheet.getRow(i+1).createCell(3); 
							cell.setCellValue(objModel.SampleMatrix); 

							cell=worksheet.getRow(i+1).createCell(4); 
							cell.setCellValue(objModel.sampleID);  
							fsIP.close();
						}

						FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+PA_fileName));
						wb.write(output_file);
						output_file.close();  

						ExtentVariables.steps.createNode("5. Upload sample matrix");
						getDriver().get(Constants.url_dataAdmin);
						waitElementInvisible(loading_cursor);
						waitElementVisible(By.id("OrgnTypeID"));
						Thread.sleep(1000);
						getDriver().findElement(By.id("OrgnTypeID")).click();
						getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("BiotechProject01");
						getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						getDriver().findElement(By.id("DataFormatId")).click();
						getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
						getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);

						getDriver().findElement(By.id("file-input")).sendKeys(ExtentVariables.fileAbsolutePath+"Excel\\"+PA_fileName);
						waitElementInvisible(loading_cursor);
						waitElementInvisible(BasePage.loading_cursor); 
						Thread.sleep(2000);
						getDriver().findElement(By.cssSelector(".fa-save")).click();
						waitElementInvisible(loading_cursor);
						waitElementInvisible(BasePage.loading_cursor); 
						Thread.sleep(1000);
						getScreenshot();

						Patho_2LogPage.openPatho_2LogPage();
						waitElementInvisible(loading_cursor);
						Thread.sleep(3000);

						ExtentVariables.steps.createNode("6. Navigate to report and search for Ingested sample id");
						getDriver().findElement(By.id("sampleId_show-filter")).click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(2000);
						getDriver().findElement(By.id("sampleId_search-input")).clear();
						getDriver().findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
						waitElementInvisible(loading_cursor);
						Thread.sleep(2000);		
						try {
							getDriver().findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.sampleID)).click();
						}
						catch (Exception ex) {
							Patho_2LogPage.openPatho_2LogPage();
							waitElementInvisible(loading_cursor);
							Thread.sleep(3000);

							ExtentVariables.steps.createNode("6. Navigate to report and search for Ingested sample id");
							getDriver().findElement(By.id("sampleId_show-filter")).click();
							waitElementInvisible(loading_cursor);
							Thread.sleep(2000);
							getDriver().findElement(By.id("sampleId_search-input")).clear();
							getDriver().findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
							waitElementInvisible(loading_cursor);
							Thread.sleep(2000);	
							getDriver().findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.sampleID)).click();
						}
						waitElementInvisible(loading_cursor);
						Thread.sleep(1500);

						ExtentVariables.steps.createNode("7. Compare the result column with w2 cell count; Threshold value is "+objModel.ThresholdValue);
						getDriver().findElement(By.id("sampleId_apply")).click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(1500);			
						getScreenshot();

						for (int x=0; x<12; x++) {

							String getRunType = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slRunTypeCol+" label")).getText();
							softAssert.assertEquals(getRunType, RunType_PA, "Run Type is not displayed in table");

							String getSampleMatrix = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slSampleMatrixCol+" label")).getText();
							softAssert.assertEquals(getSampleMatrix, objModel.SampleMatrix);

							String getTestSiteID = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slTestSiteIDCol+" label")).getText();
							softAssert.assertTrue(getTestSiteID.isEmpty() == false, "Test Site ID is not dislayed in table");

							String getTestSiteName = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slTestSiteNameCol+" label")).getText();
							softAssert.assertTrue(getTestSiteName.isEmpty() == false, "Test Site Name is not dislayed in table");

							String getResult = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slResultCol)).getText();
							String getCount = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slW2CellCountCol)).getText();

							String regex = "(?<=[\\d])(,)(`?=[\\d])";
							Pattern p = Pattern.compile(regex);
							String str = getCount;
						//	Matcher m = p.matcher(str);
					//		str = m.replaceAll("");
							str = str.replaceAll(",", "");
							
						//	System.out.println("03"+str);
							
							if (objModel.validSampleMatrix) {
								if (Integer.parseInt(str) <= Integer.parseInt(objModel.ThresholdValue)) {
									softAssert.assertEquals(getResult, "Negative", "W2CellCount: "+str+" | Threshold: "+objModel.ThresholdValue);
								}

								if (Integer.parseInt(str) > Integer.parseInt(objModel.ThresholdValue)) {
									softAssert.assertEquals(getResult, "Positive", "W2CellCount: "+str+" | Threshold: "+objModel.ThresholdValue);
								}
							}

							if (objModel.invalidSampleMatrix) {
								softAssert.assertEquals(getResult, "Missing Sample Metadata", "W2CellCount: "+str+" | Threshold: "+objModel.ThresholdValue);
							}

					//		System.out.println("02"+str);
							
							WebElement hover = getDriver().findElement(By.id("audit-trial-"+x));
							Actions builder = new Actions(getDriver());
							builder.moveToElement(hover).build().perform();
							waitElementClickable(By.id("audit-trial-"+x));
							getDriver().findElement(By.id("audit-trial-"+x)).click();
							waitElementInvisible(loading_cursor);
							Thread.sleep(1000);

							String getAuditRunType = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditRunTypeCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditRunType, RunType_PA);

							String getAuditSampleMatrix = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditSampleMatrixCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditSampleMatrix, objModel.SampleMatrix);

							String getAuditTestSiteId = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteIDCol+".text-dark")).getText();
							softAssert.assertTrue(getAuditTestSiteId.isEmpty() == false);

							String getAuditTestSiteName = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteNameCol+".text-dark")).getText();
							softAssert.assertTrue(getAuditTestSiteName.isEmpty() == false);

							String getAuditAction = getDriver().findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Modified");

							String getAuditCount = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditW2CellCountCol+".text-dark")).getText();
							String getAuditResult = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditResultCol+".text-dark")).getText();
							String str1 = getAuditCount;
							str1 = str1.replaceAll(",", "");
							getScreenshot();

							if(objModel.validSampleMatrix) {
								if (Integer.parseInt(str1) <= Integer.parseInt(objModel.ThresholdValue)) {
									softAssert.assertEquals(getAuditResult, "Negative", "W2CellCount: "+str1+" | Threshold: "+objModel.ThresholdValue);
								}

								if (Integer.parseInt(str1) > Integer.parseInt(objModel.ThresholdValue)) {
									softAssert.assertEquals(getAuditResult, "Positive", "W2CellCount: "+str1+" | Threshold: "+objModel.ThresholdValue);
								}
							}

							if (objModel.invalidSampleMatrix) {
								softAssert.assertEquals(getResult, "Missing Sample Metadata", "W2CellCount: "+str+" | Threshold: "+objModel.ThresholdValue);
							}

							getDriver().findElement(By.cssSelector(closeAudit)).click();
							Thread.sleep(800);
				//			softAssert.assertAll();
						}

						if (objModel.invalidSampleMatrix) {
							getDriver().get(Constants.url_configurationAdmin);
							waitElementInvisible(loading_cursor);
							Thread.sleep(2000);
							getDriver().findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(objModel.pathogen);
							getDriver().findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
							getDriver().findElement(By.id("create-mpn")).click();
							waitElementInvisible(loading_cursor);
							Thread.sleep(2000);
							
							if (objModel.pathogen.equals("Listeria")) {
								getDriver().findElement(By.id("dilution-factor-var")).click();
								getDriver().findElement(By.id("newSampleMatrixId")).sendKeys(objModel.SampleMatrix);

								getDriver().findElement(By.cssSelector(".m-l-5px#btn-save")).click();
								waitElementInvisible(loading_cursor);
								Thread.sleep(1500);
								getDriver().findElement(By.cssSelector("#sampleMatrixId input")).sendKeys(objModel.SampleMatrix);
								Thread.sleep(750);
								getDriver().findElement(By.cssSelector("#sampleMatrixId input")).sendKeys(Keys.ENTER);
								Thread.sleep(750);
								getDriver().findElement(By.cssSelector("#ImprocVersionId input")).sendKeys(PA_ImprocVersionNew);
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector("#ImprocVersionId input")).sendKeys(Keys.ENTER);
								Thread.sleep(1000);
								getDriver().findElement(By.id("ThresholdId")).sendKeys(objModel.ThresholdValue);
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector(".m-l-10px#btn-save")).click();
								waitElementInvisible(loading_cursor);
								waitElementInvisible(BasePage.loading_cursor);
								Thread.sleep(1000);
								softAssert.assertEquals(getDriver().findElement(By.id("message")).getText(), "Listeria Configuration saved successfully");
								getScreenshot();
					//			softAssert.assertAll();
							}

							if (objModel.pathogen.equals("Patho_2")) {
								getDriver().findElement(By.id("dilution-factor-var")).click();
								getDriver().findElement(By.id("newSampleMatrix3LId")).sendKeys(objModel.SampleMatrix);

								getDriver().findElement(By.cssSelector(".m-l-5px#btn-save")).click();
								waitElementInvisible(loading_cursor);
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys(objModel.SampleMatrix);
								getDriver().findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys(Keys.ENTER);
								Thread.sleep(750);
								getDriver().findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys(PA_ImprocVersionNew);
								Thread.sleep(1000);
								getDriver().findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys(Keys.ENTER);
								Thread.sleep(1000);
								getDriver().findElement(By.id("ThresholdPAId")).sendKeys(objModel.ThresholdValue);
								Thread.sleep(1000);
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
								Thread.sleep(1000);

								getDriver().findElement(By.cssSelector(".m-l-10px#btn-save")).click();
								waitElementInvisible(loading_cursor);
								waitElementInvisible(BasePage.loading_cursor);
								Thread.sleep(1000);
								softAssert.assertEquals(getDriver().findElement(By.id("message")).getText(), "MPN & P/A Configuration saved successfully.");
								getScreenshot();
						//		softAssert.assertAll();
							}		

							Patho_2LogPage.openPatho_2LogPage();
							waitElementInvisible(loading_cursor);
							Thread.sleep(3000);

							getDriver().findElement(By.id("sampleId_show-filter")).click();
							waitElementInvisible(loading_cursor);
							Thread.sleep(2000);
							getDriver().findElement(By.id("sampleId_search-input")).clear();
							getDriver().findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
							waitElementInvisible(loading_cursor);
							Thread.sleep(2000);		
							try {
								getDriver().findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.sampleID)).click();
							}
							catch (Exception ex) {
								Patho_2LogPage.openPatho_2LogPage();
								waitElementInvisible(loading_cursor);
								Thread.sleep(3000);

								getDriver().findElement(By.id("sampleId_show-filter")).click();
								waitElementInvisible(loading_cursor);
								Thread.sleep(2000);
								getDriver().findElement(By.id("sampleId_search-input")).clear();
								getDriver().findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
								waitElementInvisible(loading_cursor);
								Thread.sleep(2000);	
								ClickElement.clickByCss(getDriver(), "#sampleId_cust-cb-lst-txt_"+objModel.sampleID);
							}
							waitElementInvisible(loading_cursor);
							Thread.sleep(1500);

							getDriver().findElement(By.id("sampleId_apply")).click();
							waitElementInvisible(loading_cursor);
							Thread.sleep(2000);			
							getScreenshot();

							for (int x=0; x<12; x++) {

								String getRunType = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slRunTypeCol+" label")).getText();
								softAssert.assertEquals(getRunType, RunType_PA, "Run Type is not displayed in table");

								String getSampleMatrix = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slSampleMatrixCol+" label")).getText();
								softAssert.assertEquals(getSampleMatrix, objModel.SampleMatrix);

								String getTestSiteID = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slTestSiteIDCol+" label")).getText();
								softAssert.assertTrue(getTestSiteID.isEmpty() == false, "Test Site ID is not dislayed in table");

								String getTestSiteName = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slTestSiteNameCol+" label")).getText();
								softAssert.assertTrue(getTestSiteName.isEmpty() == false, "Test Site Name is not dislayed in table");

								String getResult = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slResultCol)).getText();
								String getCount = getDriver().findElement(By.cssSelector("#row-"+x+" #col-"+slW2CellCountCol)).getText();

								String regex = "(?<=[\\d])(,)(?=[\\d])";
								Pattern p = Pattern.compile(regex);
								String str = getCount;
								str = str.replaceAll(",", "");
		
								if (Integer.parseInt(str) <= Integer.parseInt(objModel.ThresholdValue)) {
									softAssert.assertEquals(getResult, "Negative", "W2CellCount: "+str+" | Threshold: "+objModel.ThresholdValue);
								}

								if (Integer.parseInt(str) > Integer.parseInt(objModel.ThresholdValue)) {
									softAssert.assertEquals(getResult, "Positive", "W2CellCount: "+str+" | Threshold: "+objModel.ThresholdValue);
								}
	
								WebElement hover = getDriver().findElement(By.id("audit-trial-"+x));
								Actions builder = new Actions(getDriver());
								builder.moveToElement(hover).build().perform();
								waitElementClickable(By.id("audit-trial-"+x));
								getDriver().findElement(By.id("audit-trial-"+x)).click();
								waitElementInvisible(loading_cursor);
								Thread.sleep(1000);

								String getAuditRunType = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditRunTypeCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditRunType, RunType_PA);

								String getAuditSampleMatrix = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditSampleMatrixCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditSampleMatrix, objModel.SampleMatrix);

								String getAuditTestSiteId = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteIDCol+".text-dark")).getText();
								softAssert.assertTrue(getAuditTestSiteId.isEmpty() == false);

								String getAuditTestSiteName = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteNameCol+".text-dark")).getText();
								softAssert.assertTrue(getAuditTestSiteName.isEmpty() == false);

								String getAuditAction = getDriver().findElement(By.id("audit-action-0")).getText();
								softAssert.assertEquals(getAuditAction, "Modified");

								String getAuditCount = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditW2CellCountCol+".text-dark")).getText();
								String getAuditResult = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditResultCol+".text-dark")).getText();
								String str1 = getAuditCount;
								str1 = str1.replaceAll(",", "");
								getScreenshot();

								if (Integer.parseInt(str1) <= Integer.parseInt(objModel.ThresholdValue)) {
									softAssert.assertEquals(getAuditResult, "Negative", "W2CellCount: "+str1+" | Threshold: "+objModel.ThresholdValue);
								}

								if (Integer.parseInt(str1) > Integer.parseInt(objModel.ThresholdValue)) {
									softAssert.assertEquals(getAuditResult, "Positive", "W2CellCount: "+str1+" | Threshold: "+objModel.ThresholdValue);
								}
								getDriver().findElement(By.cssSelector(closeAudit)).click();
								Thread.sleep(800);
						//		softAssert.assertAll();
							}
						}
					}
					else {
						System.out.print("PA not ingested");
					}
				}
				softAssert.assertAll();
				ExtentVariables.test.pass("Result column dislayed Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold");
				ExtentVariables.results.createNode("Result column dislayed Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold successfully");
				saveResult(ITestResult.SUCCESS, null);
				Thread.sleep(1000);

			}catch(AssertionError er) {
				ExtentVariables.test.fail("Result column failed to dislay Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold");
				ExtentVariables.results.createNode("Result column failed to dislay Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}catch(Exception ex){
				ExtentVariables.test.fail("Result column failed to dislay Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold");
				ExtentVariables.results.createNode("Result column failed to dislay Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold");
				saveResult(ITestResult.FAILURE, ex);
			}
		}
	}
}
