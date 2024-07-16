package Test.BiotechProject01.CSM;

import Config.BaseTest;
import MiscFunctions.*;
import Models.ReportFilters;
import PageObjects.Patho_1LogPage;
import Test.BiotechProject01.Login.LoginTest;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.HttpGet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static Models.IngestionsModel.*;
import static PageObjects.BasePage.*;
import static PageObjects.Patho_1LogPage.*;
import static PageObjects.DataTemplateManagementPage.*;
import static PageObjects.AssetManagementPage.*;

public class PreAppDataCreation_Execute2 extends BaseTest {

    String name = "none";

    @BeforeTest
    public void extent() throws InterruptedException, IOException {
        spark = new ExtentSparkReporter("target/Reports/Pre_Flutter_Mobile" + DateUtil.date + ".html");
        spark.config().setReportName("Pre Flutter Mobile Test Report");
        DB_Config_DW.test();
    }

    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
    }


    public String readSiteIDFromExcel(int houseNumber) throws IOException {
        FileInputStream fis = new FileInputStream(FrameworkConstants.CSMSiteIDsFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(houseNumber);
        Cell cell = row.getCell(0);
        String cellval = cell.getStringCellValue();
        System.out.println(cellval);
        return cellval;
    }


    @SuppressWarnings({"unchecked", "unused"})
    @Test(enabled = true, priority = 8)
    public void Ingestion_Asset() throws InterruptedException, IOException, SQLException {
        CSMModel.lstComplexConfig = CSMModel.FillDataCocci();

        for (CSMModel objModel : CSMModel.lstComplexConfig) {
            if (objModel.createAsset) {
                try {
                    test = extent.createTest("AN-Asset: Verify user can create Asset");
                    getDriver().get(url_animalManagement);
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(2000);

                    getDriver().findElement(AssetCreateButton).click();
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(2000);

                    click(AssetFarmDropdownExpand);
                    type(AssetFarmDropdownSearch, CSMModel.organizationFarm1Name);
                    waitElementInvisible(loading_cursor);
                    waitElementClickable(By.cssSelector("label b"));
                    Thread.sleep(2000);
                    scroll(By.cssSelector("label b"));
                    click(By.cssSelector("label b"));

                    type(AssetIntegratorAssetID, CSMModel.AssetIntegratorID);

                    if (size(AssetIntegratorAssetAddNew) != 0) {
                        click(AssetIntegratorAssetAddNew);
                    } else {
                        getDriver().findElement(AssetIntegratorAssetID).sendKeys(Keys.ENTER);
                    }

                    click(AssetanimalSizeInput);
                    List<WebElement> animalSizeList = getDriver().findElements(AssetanimalSizeDropDownOptions);
                    animalSizeList.get(objModel.animalSize).click();

                    scroll(AssetdeploymentDateCalendar);
                    click(AssetdeploymentDateCalendar);


                    //		List<WebElement> list = getDriver().findElements(By.cssSelector(".dp-current-day"));
                    //		List<WebElement> list = getDriver().findElements(By.xpath("//*[text()='01']"));

                    scroll(By.xpath("//label[text() = 'Asset Information']"));
                    Thread.sleep(1000);
                    //		click(By.cssSelector(".deploymentDate button[data-date= '02/01/2023']"));
                    click(By.cssSelector(".deploymentDate button[data-date= '" + DateUtil.dateMMDDYYYY1 + "']"));
                    Thread.sleep(1000);

                    try {
                        scroll(AssetHousePlacedDropdownExpand);
                        click(AssetHousePlacedDropdownExpand);
                    } catch (ElementNotInteractableException ex) {
                        click(AssetHousePlacedDropdownExpand);
                    }

                    Thread.sleep(2000);

                    for (int i = 0; i < objModel.LstHouses.size(); i++) {
                        scroll(By.xpath("//*[text() = '" + objModel.LstHouses.get(i) + "']"));
                        getDriver().findElement(By.xpath("//*[text() = '" + objModel.LstHouses.get(i) + "']")).click();
                    }

                    scroll(AssetAddNewProgram);
                    ClickElement.clickByXpath(getDriver(), "//*[text() = 'Add New Program']");
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(1000);
                    type(AssetAddNewProgramTypeInput, objModel.programType);
                    enterKey(AssetAddNewProgramTypeInput);
                    Thread.sleep(1000);

                    type(AssetAddNewProgramNameInput, objModel.program);
                    enterKey(AssetAddNewProgramNameInput);
                    Thread.sleep(1000);

                    if (objModel.programType.equals("Vaccine")) {
                        type(AssetCMSAdminInput, "CSMAdminTest");
                        if (size(clickAddNewDropdown) != 0) {
                            click(clickAddNewDropdown);
                        } else {
                            click(By.xpath("//*[text() = 'CSMAdminTest']"));
                        }
                    }

                    waitElementClickable(popupSubmitButton);
                    Thread.sleep(500);
                    click(popupSubmitButton);
                    waitElementInvisible(loading_cursor);
                    waitElementVisible(alertMessage);
                    Thread.sleep(5000);
                    Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");
                    System.out.println("Asset created successfully");

                    test.pass("Asset was created successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                } catch (AssertionError er) {
                    test.fail("Asset failed to create");
                    saveResult(ITestResult.FAILURE, new Exception(er));
                } catch (Exception ex) {
                    test.fail("Asset failed to create");
                    saveResult(ITestResult.FAILURE, ex);
                }

            }


            test = extent.createTest("AN-API_Login-01: Verify Login API", "This test case will run login api and verify that token is generated or not");

            SoftAssert softAssert = new SoftAssert();

            for (ReportFilters objFilter : objModel.lstFilters) {

                DateFormat dateFormat = new SimpleDateFormat("mm.ss");
                Date date1 = new Date();
                dateFormat.format(date1);

                RequestSpecification request = RestAssured.given();
                request.header("Content-Type", "application/json");
                JSONObject json = new JSONObject();
                json.put("piperid", piperId);
                json.put("password", piperPassword);
                json.put("DISAPIVersion", "14.13");
                request.body(json.toString());
                Response response = request.post(api_authenticate);
                int code = response.getStatusCode();
                Assert.assertEquals(code, 200);

                String data = response.asString();
                System.out.println(data);

                JsonPath jsonPathEvaluator = response.jsonPath();
                String token = jsonPathEvaluator.get("token");
                Thread.sleep(2000);


                RequestSpecification request_announcement = RestAssured.given();
                request_announcement.header("Content-Type", "application/json");
                request_announcement.header("Authorization", "bearer " + token);

                HttpGet postRequest = new HttpGet(api_announcementList);
                postRequest.addHeader("Content-Type", "application/json");
                postRequest.addHeader("Authorization", "Bearer " + token);

                JSONObject json1 = new JSONObject();
                JSONObject json2 = new JSONObject();
                JSONObject json3 = new JSONObject();
                JSONObject json4 = new JSONObject();
                JSONArray list = new JSONArray();

                json1.put("runId", lstApiAnnouncement.get(0));
                json1.put("dateTime", lstApiAnnouncement.get(1));
                json1.put("Piperid", lstApiAnnouncement.get(2));
                json1.put("MPNCalculationType", lstApiAnnouncement.get(3));
                json2.put("fileName", lstApiAnnouncement.get(4));
                json2.put("checksum", lstApiAnnouncement.get(5));

                list.add(json2);
                json1.put("files", list);

                request_announcement.body(json1.toString());
                Response response1 = request_announcement.post(api_announcementList);

                String data1 = response1.asString();
                System.out.println(data1);
                Thread.sleep(2000);

                ///////////////////////////////////////////////////////////////////File Upload API////////////////////////////////////////////////////////////////////////////////

                test = extent.createTest("AN-Patho_1-01: Ingest Patho_1 run", "This test case will run and verify  ingestion");


                RequestSpecification request_fileupload = RestAssured.given();
                request_fileupload.header("Content-Type", "application/json");
                request_fileupload.header("Authorization", "bearer " + token);

                HttpGet postRequest1 = new HttpGet(api_uploadFile);
                postRequest1.addHeader("Content-Type", "application/json");
                postRequest1.addHeader("Authorization", "Bearer " + token);

                json3.put("runId", lstPatho_1Ingest.get(0).runId);
                json3.put("checksum", lstPatho_1Ingest.get(0).checksum);
                json3.put("fileName", lstPatho_1Ingest.get(0).fileName);
                json3.put("fileType", lstPatho_1Ingest.get(0).fileType);
                json3.put("file", lstPatho_1Ingest.get(0).file);
                json3.put("fileJson", objModel.fileJson);
                json3.put("Improc", lstPatho_1Ingest.get(0).improc);
                json3.put("RunMode", "1");
                json3.put("Pathogen", objModel.pathogen);

                request_fileupload.body(json3.toString());
                Response response2 = request_fileupload.post(api_uploadFile);
                String data3 = response2.asString();
                System.out.println(data3);
                Thread.sleep(10000);

                System.out.println("Run ID: " + objModel.SampleID);

                try {
                    first:
                    for (int x = 0; x <= 120; x++) {

                        String query2 = "Select count(status) as count from COCCIDA_OUTPUT where Sample_ID = '" + objModel.SampleID + "'";

                        ResultSet rs2 = DB_Config_DW.getStmt().executeQuery(query2);

                        while (rs2.next()) {
                            System.out.println("Count: " + rs2.getString("count"));

                            if (rs2.getString("count").equals("12")) {

                                Patho_1LogPage.openPatho_1LogPage();
                                waitElementInvisible(loading_cursor);
                                waitElementInvisible(loading_cursor);
                                waitElementVisible(By.id("sort-sampleId"));
                                Thread.sleep(3000);


                                ClickElement.clickById(getDriver(), "sampleId_show-filter");
                                waitElementInvisible(loading_cursor);
                                Thread.sleep(1000);
                                getDriver().findElement(By.id("sampleId_view-all")).click();
                                waitElementInvisible(loading_cursor);
                                Thread.sleep(1000);

                                getDriver().findElement(By.id("sampleId_search-input")).clear();
                                getDriver().findElement(By.id("sampleId_search-input")).sendKeys(objModel.SampleID);
                                waitElementInvisible(loading_cursor);
                                Thread.sleep(2000);
                                try {
                                    getDriver().findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_" + objModel.SampleID)).click();
                                } catch (Exception ex) {
                                    Thread.sleep(1000);
                                    getDriver().findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_" + objModel.SampleID)).click();
                                }
                                waitElementInvisible(loading_cursor);
                                Thread.sleep(800);

                                getDriver().findElement(By.id("sampleId_apply")).click();
                                waitElementInvisible(loading_cursor);
                                Thread.sleep(4000);
                                getScreenshot();
                                String records = getDriver().findElement(By.id("results-found-count")).getText();

                                softAssert.assertEquals(records, "12");
                                getScreenshot();
                                test.pass("Run ingested successfully");

                                saveResult(ITestResult.SUCCESS, null);
                                break first;
                            } else {
                                Thread.sleep(15000);
                            }
                        }
                    }
                    softAssert.assertAll();
                } catch (Exception ex) {
                    test.fail("Data failed to verify on uploading Sample Metadata Template");
                    saveResult(ITestResult.FAILURE, ex);
                } catch (AssertionError er) {
                    test.fail("Ingestion failed");
                    saveResult(ITestResult.FAILURE, new Exception(er));
                }

                ////////////////////////////////////////////////////////////End File Upload//////////////////////////////////////////////////////////////////////

                try {
                    test = extent.createTest("AN-Patho_1-03: Upload Sample MetaData File and verify the data in Report", "This test case will verify the data in report on uploading sample metedata");
                    preconditions = test.createNode(Scenario.class, PreConditions);
                    steps = test.createNode(Scenario.class, Steps);
                    results = test.createNode(Scenario.class, Results);

                    FileInputStream fsIP = new FileInputStream(new File(FrameworkConstants.CSMDataTemplateUpload));
                    @SuppressWarnings("resource")
                    XSSFWorkbook wb = new XSSFWorkbook(fsIP);
                    XSSFSheet worksheet = wb.getSheetAt(0);
                    Cell cell = null;

                    if (getDriver().findElement(By.id(ResultsCount)).getText().equals("12")) {
                        for (int z = 0; z < 12; z++) {

                            String getResultID = getDriver().findElement(By.cssSelector("#row-" + z + " #col-" + clResultIDCol + " label")).getText();
                            cell = worksheet.getRow(z + 1).createCell(metadata_ResultID);
                            cell.setCellValue(getResultID);

                            String getCollectionSiteID = getDriver().findElement(By.cssSelector("#row-" + z + " #col-" + clCollectionSiteIDCol + " label")).getText();
                            cell = worksheet.getRow(z + 1).createCell(metadata_CollectionSiteID);
                            cell.setCellValue(getCollectionSiteID);

                            String getSampleID = getDriver().findElement(By.cssSelector("#row-" + z + " #col-" + clSampleIDCol + " label")).getText();
                            cell = worksheet.getRow(z + 1).createCell(metadata_LabSampleID);
                            cell.setCellValue(getSampleID);

                            String selectQuery = "Select unique_Asset_id from dbo.Asset_mgmt where integrator_Asset_id = '" + CSMModel.AssetIntegratorID + "' and animal_Size = '" + objModel.animalSizeName + "'";
                            ResultSet rs = DB_Config_DW.getStmt().executeQuery(selectQuery);
                            while (rs.next()) {
                                String AssetID = rs.getString("unique_Asset_id");
                                //System.out.println("Unique Asset ID: "+AssetID);
                                cell = worksheet.getRow(z + 1).createCell(metadata_AssetID);
                                cell.setCellValue(AssetID);
                            }

                            String getComplex = getDriver().findElement(By.cssSelector("#row-" + z + " #col-" + clComplexCol + " label")).getText();
                            cell = worksheet.getRow(z + 1).createCell(metadata_Complex);
                            cell.setCellValue(getComplex);

                            String getFarm = getDriver().findElement(By.cssSelector("#row-" + z + " #col-" + clFarmCol + " label")).getText();
                            cell = worksheet.getRow(z + 1).createCell(metadata_Farm);
                            cell.setCellValue(getFarm);

                            String getLane = getDriver().findElement(By.cssSelector("#row-" + z + " #col-" + clLaneCol + " label")).getText();
                            cell = worksheet.getRow(z + 1).createCell(metadata_Lane);
                            cell.setCellValue(getLane);

                            String getResultDate = getDriver().findElement(By.cssSelector("#row-" + z + " #col-" + clDateCol + " label")).getText();
                            cell = worksheet.getRow(z + 1).createCell(metadata_ResultDate);
                            cell.setCellValue(getResultDate);

                            String getresultTime = getDriver().findElement(By.cssSelector("#row-" + z + " #col-" + clTimeCol + " label")).getText();
                            cell = worksheet.getRow(z + 1).createCell(metadata_ResultTime);
                            cell.setCellValue(getresultTime);

                            cell = worksheet.getRow(z + 1).createCell(metadata_CartridgeID);
                            cell.setCellValue(CSMModel.cartridgeID);

                            cell = worksheet.getRow(z + 1).createCell(metadata_InstrumentID);
                            cell.setCellValue(InstrumentID);

                            String getPiperUser = getDriver().findElement(By.cssSelector("#row-" + z + " #col-" + clPiperUserCol)).getText();
                            cell = worksheet.getRow(z + 1).createCell(metadata_PiperUser);
                            cell.setCellValue(getPiperUser);

                            cell = worksheet.getRow(z + 1).createCell(metadata_CollectionDate);
                            cell.setCellValue(objModel.CollectionDatee);

                            fsIP.close();
                        }

                        FileOutputStream output_file = new FileOutputStream(new File(FrameworkConstants.CSMDataTemplateUpload));
                        wb.write(output_file);
                        output_file.close();

                        getDriver().get(url_dataAdmin);
                        waitElementVisible(By.id("OrgnTypeID"));
                        Thread.sleep(000);
                        getDriver().findElement(By.id("OrgnTypeID")).click();
                        getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("BiotechProject01");
                        getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
                        Thread.sleep(1000);
                        getDriver().findElement(By.id("DataFormatId")).click();
                        getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
                        getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
                        Thread.sleep(1000);
                        getDriver().findElement(By.id("file-input")).sendKeys(FrameworkConstants.CSMDataTemplateUpload);
                        waitElementInvisible(loading_cursor);
                        waitElementVisible(alertMessage);
                        Thread.sleep(4000);
                        getDriver().findElement(By.cssSelector(".fa-save")).click();
                        waitElementInvisible(loading_cursor);
                        waitElementVisible(alertMessage);
                        waitElementVisible(alertMessage);
                        Thread.sleep(6000);
                        getScreenshot();
                        Assert.assertTrue(getDriver().findElement(alertMessage).getText().contains("SampleMetadata_Mobile.xlsx saved successfully."));
                        System.out.println("Template created successfully");
                        test.pass("Template saved successfully");
                        saveResult(ITestResult.SUCCESS, null);
                    } else {
                        test.skip("12 records not displaying in table hence file upload method not executed");
                        saveResult(ITestResult.SKIP, null);
                    }
                } catch (AssertionError er) {
                    test.fail("Data failed to verify on uploading Sample Metadata Template");
                    saveResult(ITestResult.FAILURE, new Exception(er));
                } catch (Exception ex) {
                    test.fail("Data failed to verify on uploading Sample Metadata Template");
                    saveResult(ITestResult.FAILURE, ex);
                }
                Thread.sleep(2000);
            }
        }


    }


    @AfterTest
    public static void endreport() {
        extent.flush();
    }
}
