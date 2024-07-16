package Test.BiotechProject01.MetaData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.openqa.selenium.By;
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
import MiscFunctions.DateUtil;
import Models.DataUploadModel;
import Models.ReportFilters;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static PageObjects.BasePage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static Models.DataUploadModel.*;

public class DataUpload extends BaseTest{


	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		//	spark = new ExtentSparkReporter("target/Reports/MetaData_Upload"+DateUtil.date+".html");
		//	spark.config().setReportName("Data Upload Test Report");
		initReport("MetaData_Upload");
	}


	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}


	@Test (enabled= false, priority = 2)
	public void AssetMetadata() throws InterruptedException, IOException {

		lstDataUploadAsset = DataUploadModel.FillData();
		for (DataUploadModel objModel : lstDataUploadAsset) {
			try {
				Thread.sleep(2000);
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " +url_loginPage);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				steps.createNode("1. Navigate to Data Upload screen");
				steps.createNode("2. Select BiotechProject01 from 'Upload For' dropdown and Asset Metadata from 'Data Template'");

				getDriver().get(url_dataAdmin);
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				getDriver().findElement(By.id("OrgnTypeID")).click();
				getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("BiotechProject01");
				getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				getDriver().findElement(By.id("DataFormatId")).click();
				getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys("Asset Metadata");
				getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);

				for (ReportFilters objFilter : objModel.lstFilters) {
					try {

						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstColumnID.size() && i < 100; i++) {

							FileInputStream fsIP= new FileInputStream(new File("./Excel/Asset Metadata.xlsx"));
							@SuppressWarnings("resource")
							XSSFWorkbook wb = new XSSFWorkbook(fsIP);
							XSSFSheet worksheet = wb.getSheetAt(0);
							Cell cell = null;

							cell=worksheet.getRow(1).createCell(objFilter.LstColumnID.get(i));
							cell.setCellValue(objFilter.LstColumnValues.get(i));   //1033011
							fsIP.close();

							FileOutputStream output_file =new FileOutputStream(new File("./Excel/Asset Metadata.xlsx"));
							wb.write(output_file);
							output_file.close();

							chkCounter++;
						}

						steps.createNode("3. "+objModel.steps);
						getDriver().findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"Excel\\"+AssetFileName);
						waitElementInvisible(loading_cursor);
						waitElementVisible(alertMessage);
						Thread.sleep(2000);
						getScreenshot();
						SoftAssert softAssert = new SoftAssert();
						softAssert.assertEquals(getDriver().findElement(By.id("message")).getText(), objModel.AlertMessage);

						if (objModel.ErrorCase) {
							int i =1;
							if (i==1) {
								getDriver().findElement(By.id("ErrorBtn")).click();
								Thread.sleep(2000);
							}
							i=i+1;
							WebElement ele = getDriver().findElement(By.cssSelector("tr:nth-child(1) .tooltipBlock"));
							Actions action = new Actions(getDriver());
							action.moveToElement(ele).perform();
							Thread.sleep(1000);
							String tooltipText = getDriver().findElement(By.cssSelector(".tooltip-inner")).getText();
							//		System.out.println(tooltipText);
							softAssert.assertEquals(tooltipText, objModel.ErrorMessage);
						}

						softAssert.assertAll();
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						saveResult(ITestResult.SUCCESS, null);

					}
					catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, ex);
					}
					//	getDriver().findElement(By.cssSelector("#alrt button span")).click();
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority = 3)
	public void SitePerformance() throws InterruptedException, IOException {

		getDriver().get(url_dataAdmin);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);

		lstDataUploadSitePerformance = DataUploadModel.FillDataSitePerformance();
		waitElementVisible(By.id("OrgnTypeID"));
		Thread.sleep(1000);
		getDriver().findElement(By.id("OrgnTypeID")).click();
		getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("BiotechProject01");
		getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		getDriver().findElement(By.id("DataFormatId")).click();
		getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys("Site Performance");
		getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);

		for (DataUploadModel objModel : lstDataUploadSitePerformance) {
			try {
				Thread.sleep(2000);
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " +url_loginPage);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				steps.createNode("1. Navigate to Data Upload screen");
				steps.createNode("2. Select BiotechProject01 from 'Upload For' dropdown and Sample Metadata from 'Data Template'");

				for (ReportFilters objFilter : objModel.lstFilters) {
					try {
						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstColumnID.size() && i < 100; i++) {

							FileInputStream fsIP= new FileInputStream(new File("./Excel/"+sitePerformanceFileName));
							@SuppressWarnings("resource")
							XSSFWorkbook wb = new XSSFWorkbook(fsIP);
							XSSFSheet worksheet = wb.getSheetAt(0);
							Cell cell = null;

							cell=worksheet.getRow(1).createCell(objFilter.LstColumnID.get(i));
							cell.setCellValue(objFilter.LstColumnValues.get(i));   //1033011
							fsIP.close();

							FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+sitePerformanceFileName));
							wb.write(output_file);
							output_file.close();
							chkCounter++;
						}

						steps.createNode("3. "+objModel.steps);
						getDriver().findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"Excel\\"+sitePerformanceFileName);
						waitElementInvisible(loading_cursor);
						waitElementVisible(alertMessage);
						Thread.sleep(750);
						Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), objModel.AlertMessage);
						getDriver().findElement(By.cssSelector("#alrt button span")).click();

						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
					}
					catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, new Exception(er));
					}
					catch(Exception ex) {
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


	@Test (enabled= true, priority = 4)
	public void SampleMetaData() throws InterruptedException, IOException {

		lstDataUploadSampleMetadata = DataUploadModel.FillDataSampleMetaData();
		for (DataUploadModel objModel : lstDataUploadSampleMetadata) {
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " +url_loginPage);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				steps.createNode("1. Navigate to Data Upload screen");
				steps.createNode("2. Select BiotechProject01 from 'Upload For' dropdown and Sample Metadata from 'Data Template'");

				getDriver().get(url_dataAdmin);
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				getDriver().findElement(By.id("OrgnTypeID")).click();
				getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("BiotechProject01");
				getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				getDriver().findElement(By.id("DataFormatId")).click();
				getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
				getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);

				for (ReportFilters objFilter : objModel.lstFilters) {
					try {

						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstColumnID.size() && i < 100; i++) {

							FileInputStream fsIP= new FileInputStream(new File("./Excel/"+sampleMetadataFileName));
							@SuppressWarnings("resource")
							XSSFWorkbook wb = new XSSFWorkbook(fsIP);
							XSSFSheet worksheet = wb.getSheetAt(0);
							Cell cell = null;

							cell=worksheet.getRow(i+1).createCell(objFilter.LstColumnID.get(i));
							cell.setCellValue(objFilter.LstColumnValues.get(i));
							fsIP.close();

							FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+sampleMetadataFileName));
							wb.write(output_file);
							output_file.close();
							chkCounter++;
						}

						steps.createNode("3. "+objModel.steps);
						getDriver().findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"Excel\\"+sampleMetadataFileName);
						waitElementInvisible(loading_cursor);
						Thread.sleep(3500);
						SoftAssert softAssert = new SoftAssert();
						softAssert.assertEquals(getDriver().findElement(By.id("message")).getText(), objModel.AlertMessage);

						if (objModel.ErrorCase) {
							int i =1;
							if (i==1) {
								getDriver().findElement(By.id("ErrorBtn")).click();
								Thread.sleep(2000);
							}
							i=i+1;
							WebElement ele = getDriver().findElement(By.cssSelector("tr:nth-child(1) .tooltipBlock"));
							Actions action = new Actions(getDriver());
							action.moveToElement(ele).perform();
							Thread.sleep(1000);
							String tooltipText = getDriver().findElement(By.cssSelector(".tooltip-inner")).getText();
							System.out.println(tooltipText);
							softAssert.assertEquals(tooltipText, objModel.ErrorMessage);
						}

						softAssert.assertAll();
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
					}
					catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, ex);
					}
					getDriver().findElement(By.cssSelector("#alrt button span")).click();
				}
			}
			catch(Exception ex) {
			}
		}
	}




	@Test (enabled= true, priority = 4)
	public void ClostridiumTemplate() throws InterruptedException, IOException {

		lstDataUploadClostridiumTemplate = DataUploadModel.FillDataClostridiumTemplate();
		for (DataUploadModel objModel : lstDataUploadClostridiumTemplate) {
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " +url_loginPage);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				steps.createNode("1. Navigate to Data Upload screen");
				steps.createNode("2. Select BiotechProject01 from 'Upload For' dropdown and Clostridium Template from 'Data Template'");

				getDriver().get(url_dataAdmin);
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				getDriver().findElement(By.id("OrgnTypeID")).click();
				getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("BiotechProject01");
				getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				getDriver().findElement(By.id("DataFormatId")).click();
				getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys("Clostridium Template");
				getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);

				for (ReportFilters objFilter : objModel.lstFilters) {
					try {

						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstColumnID.size() && i < 100; i++) {

							FileInputStream fsIP= new FileInputStream(new File("src/test/resources/Excel/Clostridium Template.xlsx"));
							@SuppressWarnings("resource")
							XSSFWorkbook wb = new XSSFWorkbook(fsIP);
							XSSFSheet worksheet = wb.getSheetAt(0);
							Cell cell = null;

							cell=worksheet.getRow(i+1).createCell(objFilter.LstColumnID.get(i));
							cell.setCellValue(objFilter.LstColumnValues.get(i));
							fsIP.close();

							FileOutputStream output_file =new FileOutputStream(new File("src/test/resources/Excel/Clostridium Template.xlsx"));
							wb.write(output_file);
							output_file.close();
							chkCounter++;
						}

						steps.createNode("3. "+objModel.steps);
						getDriver().findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"src/test/resources/Excel/Clostridium Template.xlsx");
						waitElementInvisible(loading_cursor);
						Thread.sleep(3500);
						SoftAssert softAssert = new SoftAssert();
						softAssert.assertEquals(getDriver().findElement(By.id("message")).getText(), objModel.AlertMessage);

						if (objModel.ErrorCase) {
							int i =1;
							if (i==1) {
								getDriver().findElement(By.id("ErrorBtn")).click();
								Thread.sleep(2000);
							}
							i=i+1;
							WebElement ele = getDriver().findElement(By.cssSelector("tr:nth-child(1) .tooltipBlock"));
							Actions action = new Actions(getDriver());
							action.moveToElement(ele).perform();
							Thread.sleep(1000);
							String tooltipText = getDriver().findElement(By.cssSelector(".tooltip-inner")).getText();
							System.out.println(tooltipText);
							softAssert.assertEquals(tooltipText, objModel.ErrorMessage);
						}

						softAssert.assertAll();
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
					}
					catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, ex);
					}
					getDriver().findElement(By.cssSelector("#alrt button span")).click();
				}
			}
			catch(Exception ex) {
			}
		}
	}



	@Test (enabled= true, priority = 4)
	public void BulkAssetLineageTemplate() throws InterruptedException, IOException {
		lstDataUploadBulkAssetLineageTemplate = DataUploadModel.FillDataBulkAssetLineageTemplate();
		for (DataUploadModel objModel : lstDataUploadBulkAssetLineageTemplate) {
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " + url_loginPage);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				steps.createNode("1. Navigate to Data Upload screen");
				steps.createNode("2. Select BiotechProject01 from 'Upload For' dropdown and Asset Lineage from 'Data Template'");

				getDriver().get(url_dataAdmin);
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				getDriver().findElement(By.id("OrgnTypeID")).click();
				getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("BiotechProject01");
				getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				getDriver().findElement(By.id("DataFormatId")).click();
				getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys("Bulk Create Asset Lineage");
				getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);

				for (ReportFilters objFilter : objModel.lstFilters) {
					try {
						FileInputStream fsIP = new FileInputStream(new File("src/test/resources/Excel/Bulk Create Asset Lineage Template.xlsx"));
						XSSFWorkbook wb = new XSSFWorkbook(fsIP);
						XSSFSheet worksheet = wb.getSheetAt(0);

						// Get the row at index 1 or create it if it doesn't exist
						Row row = worksheet.getRow(1);
						if (row == null) {
							row = worksheet.createRow(1);
						} else {
							// Clear the contents of the row
							for (int cn = row.getFirstCellNum(); cn < row.getLastCellNum(); cn++) {
								Cell cell = row.getCell(cn);
								if (cell != null) {
									row.removeCell(cell);
								}
							}
						}

						// Fill the row with new values from objFilter
						int cellIndex = 0;
						for (int columnID : objFilter.LstColumnID) {
							Cell cell = row.createCell(columnID);
							cell.setCellValue(objFilter.LstColumnValues.get(cellIndex));
							cellIndex++;
						}

						fsIP.close();

						FileOutputStream output_file = new FileOutputStream(new File("src/test/resources/Excel/Bulk Create Asset Lineage Template.xlsx"));
						wb.write(output_file);
						output_file.close();

						steps.createNode("3. " + objModel.steps);
						getDriver().findElement(By.id("file-input")).sendKeys(fileAbsolutePath + "src/test/resources/Excel/Bulk Create Asset Lineage Template.xlsx");
						waitElementInvisible(loading_cursor);
						Thread.sleep(3500);

						SoftAssert softAssert = new SoftAssert();
						softAssert.assertEquals(getDriver().findElement(By.id("message")).getText(), objModel.AlertMessage);

						if (objModel.ErrorCase) {
							int i = 1;
							if (i == 1) {
								getDriver().findElement(By.id("ErrorBtn")).click();
								Thread.sleep(2000);
							}
							i = i + 1;
							WebElement ele = getDriver().findElement(By.cssSelector("tr:nth-child(1) .tooltipBlock"));
							Actions action = new Actions(getDriver());
							action.moveToElement(ele).perform();
							Thread.sleep(1000);
							String tooltipText = getDriver().findElement(By.cssSelector(".tooltip-inner")).getText();
							System.out.println(tooltipText);
							softAssert.assertEquals(tooltipText, objModel.ErrorMessage);
						}

						softAssert.assertAll();
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
					} catch (AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, new Exception(er));
					} catch (Exception ex) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, ex);
					}
//					getDriver().findElement(By.cssSelector("#alrt button span")).click();
				}
			} catch (Exception ex) {
				// Handle outer exception
				test.fail("An unexpected error occurred: " + ex.getMessage());
				results.createNode("An unexpected error occurred: " + ex.getMessage());
				saveResult(ITestResult.FAILURE, ex);
			}
		}
	}


	@Test (enabled= true, priority = 4)
	public void BulkUserTemplate() throws InterruptedException, IOException {
		lstDataUploadBulkAssetLineageTemplate = DataUploadModel.FillDataBulkAssetLineageTemplate();
		for (DataUploadModel objModel : lstDataUploadBulkAssetLineageTemplate) {
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " + url_loginPage);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				steps.createNode("1. Navigate to Data Upload screen");
				steps.createNode("2. Select BiotechProject01 from 'Upload For' dropdown and Asset Lineage from 'Data Template'");

				getDriver().get(url_dataAdmin);
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				getDriver().findElement(By.id("OrgnTypeID")).click();
				getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("BiotechProject01");
				getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				getDriver().findElement(By.id("DataFormatId")).click();
				getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys("Bulk Create Asset Lineage");
				getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);

				for (ReportFilters objFilter : objModel.lstFilters) {
					try {
						FileInputStream fsIP = new FileInputStream(new File("src/test/resources/Excel/Bulk Create Asset Lineage Template.xlsx"));
						XSSFWorkbook wb = new XSSFWorkbook(fsIP);
						XSSFSheet worksheet = wb.getSheetAt(0);

						// Get the row at index 1 or create it if it doesn't exist
						Row row = worksheet.getRow(1);
						if (row == null) {
							row = worksheet.createRow(1);
						} else {
							// Clear the contents of the row
							for (int cn = row.getFirstCellNum(); cn < row.getLastCellNum(); cn++) {
								Cell cell = row.getCell(cn);
								if (cell != null) {
									row.removeCell(cell);
								}
							}
						}

						// Fill the row with new values from objFilter
						int cellIndex = 0;
						for (int columnID : objFilter.LstColumnID) {
							Cell cell = row.createCell(columnID);
							cell.setCellValue(objFilter.LstColumnValues.get(cellIndex));
							cellIndex++;
						}

						fsIP.close();

						FileOutputStream output_file = new FileOutputStream(new File("src/test/resources/Excel/Bulk Create Asset Lineage Template.xlsx"));
						wb.write(output_file);
						output_file.close();

						steps.createNode("3. " + objModel.steps);
						getDriver().findElement(By.id("file-input")).sendKeys(fileAbsolutePath + "src/test/resources/Excel/Bulk Create Asset Lineage Template.xlsx");
						waitElementInvisible(loading_cursor);
						Thread.sleep(3500);

						SoftAssert softAssert = new SoftAssert();
						softAssert.assertEquals(getDriver().findElement(By.id("message")).getText(), objModel.AlertMessage);

						if (objModel.ErrorCase) {
							int i = 1;
							if (i == 1) {
								getDriver().findElement(By.id("ErrorBtn")).click();
								Thread.sleep(2000);
							}
							i = i + 1;
							WebElement ele = getDriver().findElement(By.cssSelector("tr:nth-child(1) .tooltipBlock"));
							Actions action = new Actions(getDriver());
							action.moveToElement(ele).perform();
							Thread.sleep(1000);
							String tooltipText = getDriver().findElement(By.cssSelector(".tooltip-inner")).getText();
							System.out.println(tooltipText);
							softAssert.assertEquals(tooltipText, objModel.ErrorMessage);
						}

						softAssert.assertAll();
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
					} catch (AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, new Exception(er));
					} catch (Exception ex) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResult(ITestResult.FAILURE, ex);
					}
//					getDriver().findElement(By.cssSelector("#alrt button span")).click();
				}
			} catch (Exception ex) {
				// Handle outer exception
				test.fail("An unexpected error occurred: " + ex.getMessage());
				results.createNode("An unexpected error occurred: " + ex.getMessage());
				saveResult(ITestResult.FAILURE, ex);
			}
		}
	}

	@Test
	public void as() {
		String myHomePath= System.getProperty("user.home");
		System.out.println(myHomePath);
	}



	@Test (enabled= true, priority = 5)
	public void saveTemplates() throws InterruptedException, IOException {

		lstDataUploadSaveTemplate = DataUploadModel.FillDataSaveTemplate();
		for (DataUploadModel objModel : lstDataUploadSaveTemplate) {
			try {
				Thread.sleep(2000);
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " +url_loginPage);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				steps.createNode("1. Navigate to Data Upload screen");
				steps.createNode("2. Select BiotechProject01 from 'Upload For' dropdown and "+objModel.templateName+" from 'Data Template'");

				for (@SuppressWarnings("unused") ReportFilters objFilter : objModel.lstFilters) {
					try {
						waitElementVisible(By.id("OrgnTypeID"));
						Thread.sleep(1000);
						getDriver().findElement(By.id("OrgnTypeID")).click();
						getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("BiotechProject01");
						getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						getDriver().findElement(By.id("DataFormatId")).click();
						getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(objModel.templateName);
						getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);

						steps.createNode("3. "+objModel.steps);
						getDriver().findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"Excel\\"+objModel.fileName);
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);
						//			if (getDriver().findElement(By.id("message")).getText().equals(objModel.fileName+" loaded successfully.")) {
						getDriver().findElement(By.cssSelector(".fa-save")).click();
						waitElementVisible(alertMessage);
						Thread.sleep(1000);
						Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), objModel.fileName+" saved successfully.");
						test.pass(objModel.templateName+" saved successfully");
						results.createNode(objModel.templateName+" saved successfully");
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
						//		}
					}
					catch(AssertionError er) {
						test.fail(objModel.templateName+" failed to save");
						results.createNode(objModel.templateName+" failed to save");
						saveResult(ITestResult.FAILURE, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objModel.templateName+" failed to save");
						results.createNode(objModel.templateName+" failed to save");
						saveResult(ITestResult.FAILURE, ex);
					}
				}
			}
			catch(Exception ex) {
			}
		}
	}

/*
	@Test (description="Test Case: Client Dropdown List",enabled= false, priority = 6) 
	public void ClientList() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-DU-02: Verify user's own client appear in the client dropdown list", "This test case will verify that user's own client appear in the client dropdown list");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");

			steps.createNode("1. Go to User Management Screen");
			getDriver().get(url_user);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userSearch)));
			Thread.sleep(1000);
			steps.createNode("2. Search for own user and open it");
			getDriver().findElement(By.id("orgnType-1")).click();
			Thread.sleep(800);
			getDriver().findElement(By.id("outer-0")).click();
			Thread.sleep(800);

			for (int i=1; i<=30; i++) {
				String actualXpath = "//*[@id=\""+i+"\"]/td[4]";
				WebElement element = getDriver().findElement(By.xpath(actualXpath));

				int j= i+1;
				if (element.getText().equals(login_email)) {
					Thread.sleep(500);
					getDriver().findElement(By.xpath("//*[@id=\"edit-user-"+j+"\"]")).click();
					break;
				}
			}
			steps.createNode("3. Go to third step of popup");
			Thread.sleep(1500);
			getDriver().findElement(By.id(userNextBtn)).click();
			Thread.sleep(1000);
			getDriver().findElement(By.id(userNextBtn)).click();
			Thread.sleep(1000);
			steps.createNode("4. Add a new client and assign a site");
			getDriver().findElement(By.cssSelector(userClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			getDriver().findElement(By.xpath(userClientInputSelect)).click();
			Thread.sleep(1000);
			getDriver().findElement(By.id(userClientSiteBtn)).click();
			Thread.sleep(1500);
			getDriver().findElement(By.cssSelector(userClientInput2)).sendKeys("Test");
			Thread.sleep(1000);        
			getDriver().findElement(By.xpath(userClientInput2Select)).click();
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector(userClientCheckbox)).click();
			Thread.sleep(1000);
			getDriver().findElement(By.id(userClientOKBtn)).click();
			Thread.sleep(1000);
			steps.createNode("5. Click on save button");
			getDriver().findElement(By.id(userSaveBtn)).click();
			Thread.sleep(1000);

			steps.createNode("6. Go to Data Upload Screen");
			getDriver().get(url_dataAdmin);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(duUploadDropdown)));
			Thread.sleep(1000);
			steps.createNode("7. Select client from Upload For dropdown");
			getDriver().findElement(By.cssSelector(duUploadDropdown)).click();
			Thread.sleep(1000);
			getDriver().findElement(By.xpath(duUploadDropdownSelectClient)).click();
			Thread.sleep(1000);
			steps.createNode("8. Expand client dropdown and verify that added client is displayed in Client dropdown");
			getDriver().findElement(By.cssSelector(duClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			String value = getDriver().findElement(By.xpath(duClientInputSelect)).getText();

			test.addScreenCaptureFromPath(getScreenshot("Login", LoginReportPath));
			Assert.assertEquals(value, "Test Client");
			test.pass("User's own client appeared successfully");
			results.createNode("User's own client appeared successfully");
			saveResult(ITestResult.SUCCESS, ReportFilePath, null);
		}
		catch(AssertionError er) {
			test.fail("User's own client failed to appear in dropdown list");
			results.createNode("User's own client failed to appear in dropdown list");
			saveResult(ITestResult.FAILURE, ReportFilePath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User's own client failed to appear in dropdown list");
			results.createNode("User's own client failed to appear in dropdown list");
			saveResult(ITestResult.FAILURE, ReportFilePath, ex);
		}
	}


	@Test (description="Test Case: Remove Client Dropdown List",enabled= false, priority = 7) 
	public void ClientListRemove() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-DU-03: Verify client does not appear in the client dropdown list after removing it", "This test case will verify that client does not appear in the client dropdown list after removing it");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_loginPage);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");

			steps.createNode("1. Go to User Management Screen");
			getDriver().get(url_user);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userSearch)));
			Thread.sleep(1000);
			steps.createNode("2. Search for own user and open it");
			getDriver().findElement(By.id("orgnType-1")).click();
			Thread.sleep(800);
			getDriver().findElement(By.id("outer-0")).click();
			Thread.sleep(800);

			for (int i=1; i<=30; i++) {
				String actualXpath = "//*[@id=\""+i+"\"]/td[4]";
				WebElement element = getDriver().findElement(By.xpath(actualXpath));

				int j= i+1;
				if (element.getText().equals(login_email)) {
					Thread.sleep(500);
					getDriver().findElement(By.xpath("//*[@id=\"edit-user-"+j+"\"]")).click();
					break;
				}
			}
			steps.createNode("3. Go to third step of popup");
			Thread.sleep(1500);
			getDriver().findElement(By.id(userNextBtn)).click();
			Thread.sleep(1000);
			getDriver().findElement(By.id(userNextBtn)).click();
			Thread.sleep(1000);
			steps.createNode("4. Remove the added client");
			getDriver().findElement(By.cssSelector(userClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			getDriver().findElement(By.xpath(userClientInputSelect)).click();
			Thread.sleep(1000);
			steps.createNode("5. Click on save button");
			getDriver().findElement(By.id(userSaveBtn)).click();
			Thread.sleep(1000);

			steps.createNode("6. Go to Data Upload Screen");
			getDriver().get(url_dataAdmin);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(duUploadDropdown)));
			Thread.sleep(1000);
			steps.createNode("7. Select client from Upload For dropdown");
			getDriver().findElement(By.cssSelector(duUploadDropdown)).click();
			Thread.sleep(1000);
			getDriver().findElement(By.xpath(duUploadDropdownSelectClient)).click();
			Thread.sleep(1000);
			steps.createNode("8. Expand client dropdown and verify that deleted client is not displayed in Client dropdown");
			getDriver().findElement(By.cssSelector(duClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			String value = getDriver().findElement(By.xpath(duClientInputSelect)).getText();

			test.addScreenCaptureFromPath(getScreenshot("Login", LoginReportPath));
			Assert.assertEquals(value, "No items found");
			test.pass("User's own client was removed from list successfully");
			results.createNode("User's own client was removed from list successfully");
			saveResult(ITestResult.SUCCESS, ReportFilePath, null);
		}
		catch(AssertionError er) {
			test.fail("User's own client failed to remove from dropdown list");
			results.createNode("User's own client failed to remove from dropdown list");
			saveResult(ITestResult.FAILURE, ReportFilePath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User's own client failed to remove from dropdown list");
			results.createNode("User's own client failed to remove from dropdown list");
			saveResult(ITestResult.FAILURE, ReportFilePath, ex);
		}
	}
*/

	@AfterTest
	public static void endreport() {
		extent.flush();
	}



}
