package PageObjects;

import Config.BaseTest;
import MiscFunctions.DB_Config_DW;
import MiscFunctions.DateUtil;
import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.HttpGet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Config.BaseTest.saveResult;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.FrameworkConstants.IMAGESPATH;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.FrameworkConstants.ReportingPlatformIngestionTemplateUpload;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Queries.*;
import static PageObjects.BasePage.loading_cursor;
import static PageObjects.DataUploadPage.*;
import static PageObjects.DataUploadPage.uploadedTemplateSaveButton;


public class ReportingPlatformPage {

    static String randomString = date0;

    public static String runIDValue1 = dateYYYYMMDD + "_Cocci_1" + randomString;
    public static String runIDValue2 = dateYYYYMMDD + "_Cocci_2" + randomString;
    public static String runIDValue3 = dateYYYYMMDD + "_Cocci_3" + randomString;

    public static String cartridgeID = dateYYYYMMDD + "-" + randomString;
    public static String dateTime = dateMMDDYYYY1 + " " + dateRIT;
    public static String reportingAssetIntegratorID = "RP-"+ DateUtil.dateYYYYMMDD+"-"+randomString;
    public static String ReportingOrganizationName = "Test Organization"+randomString;
    public static String ReportingHouse1Name = "Reporting House 1 ("+randomString+")";
    public static String ReportingHouse2Name = "Reporting House 2 ("+randomString+")";
    public static String ReportingHouse3Name = "Reporting House 3 ("+randomString+")";
    public static String ReportingHouse4Name = "Reporting House 4 ("+randomString+")";
    public static String reportingFeedProgramName = "Reporting Feed Program_"+randomString;
    public static String reportingTemplateName = "ReportingComplexSite_"+randomString;
    public static String reportingPlanName = "ReportingPlan_"+randomString;

    @Test(enabled = true, priority = 7, groups = {"smoke", "regression"})
    public static void Patho_1RIIngestion(Boolean runStartAssay, String runID, String laneNo, String fileName, int collectionSiteID, String imageName, String checksum) throws IOException {
        try {
            SoftAssert softAssert = new SoftAssert();
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            JSONObject json = new JSONObject();
            json.put("piperid", "PSN0006");
            json.put("password", "piperdemo");
            json.put("DISAPIVersion", "15.2.0");
            request.body(json.toString());

            Response response = request.post(api_authenticate);
            int code = response.getStatusCode();
            Assert.assertEquals(code, 200);
            String data = response.asString();
            System.out.println(data);

            JsonPath jsonPathEvaluator = response.jsonPath();
            String token = jsonPathEvaluator.get("token");
            String statusCode = jsonPathEvaluator.get("statusCode");
            softAssert.assertEquals(statusCode, "114");

            JSONObject jsonRI = new JSONObject();
            JSONObject jsonSA = new JSONObject();

            ///////////////////////////////////////////////////////////Start Assay/////////////////////////////////////////////////////////////////////////////////

            if (runStartAssay == true) {

                RequestSpecification request_startAssay = RestAssured.given();
                request_startAssay.header("Content-Type", "application/json");
                request_startAssay.header("Authorization", "bearer " + token);

                HttpGet postRequest3 = new HttpGet(api_announcementList);
                postRequest3.addHeader("Content-Type", "application/json");
                postRequest3.addHeader("Authorization", "Bearer " + token);

                System.out.println("Run-ID: " + runID);
                System.out.println("Date-ID: " + dateTime);
                System.out.println("User-ID: " + getUsersId());

                jsonSA.put("DateTime", dateTime);
                jsonSA.put("InstrumentId", "PSN0001");
                jsonSA.put("UserId", getUsersId());
                jsonSA.put("CartridgeId", cartridgeID);
                jsonSA.put("RunId", runID);
                jsonSA.put("PathogenName", "Patho_1");

                request_startAssay.body(jsonSA.toString());
                Response responseSA = request_startAssay.post(api_announcementList);

                String dataSA = responseSA.asString();
                System.out.println(dataSA);
                Thread.sleep(1000);
            }
            /////////////////////////////////////////////////////////End Start Assay////////////////////////////////////////////////////////////////////////////////


            /////////////////////////////////////////////////////////Raw Image API////////////////////////////////////////////////////////////////////////////////

            RequestSpecification rawimageRequest = RestAssured.given();
            rawimageRequest.header("Content-Type", "application/json");
            rawimageRequest.header("Authorization", "bearer " + token);

            System.out.println("Run-ID Raw Image: " + runID);
            jsonRI.put("cartridgeId", cartridgeID);
            jsonRI.put("lane", laneNo);
            jsonRI.put("dateTime", dateTime);
            jsonRI.put("piperId", "PSN0001");
            jsonRI.put("runType", "Normal");
            jsonRI.put("runId", runID + "_" + laneNo);
            jsonRI.put("Pathogen", "Patho_1");
            jsonRI.put("sampleid", runID);
            jsonRI.put("instrumentid", "PSN0001");
            jsonRI.put("userid", getUsersId());
            jsonRI.put("checksum", checksum);
            jsonRI.put("fileName", runID + "_" + fileName + ".bmp");
            jsonRI.put("fileType", "bmp");

            String TestFile = IMAGESPATH + "\\base64\\" + imageName;
            FileReader FR = new FileReader(TestFile);
            BufferedReader BR = new BufferedReader(FR);
            String Content = "";
            while ((Content = BR.readLine()) != null) {
                jsonRI.put("file", Content);
            }

            jsonRI.put("Improc", "ImprocCocc01");
            jsonRI.put("countOutCome", "");
            jsonRI.put("statusCode", "");
            jsonRI.put("IE_COLLECTION_SITE_ID", collectionSiteID);
            jsonRI.put("runMode", "1");
            jsonRI.put("dilutionFactor", "");

            rawimageRequest.body(jsonRI.toString());
            Response responseRI = rawimageRequest.post(api_announcementList);
            String dataRI = responseRI.asString();
            System.out.println(dataRI);
            jsonPathEvaluator = responseRI.jsonPath();
            String message = jsonPathEvaluator.get("message");
            softAssert.assertEquals(message, "Successful Response.");
            softAssert.assertAll();
        } catch (AssertionError er) {
            test.fail("Failed to run ingestion");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Failed to run ingestion");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public static void UploadMetaDataFunction(String runId, String houseName, int AssetDay) throws IOException, SQLException {
        try {
            test = extent.createTest("Upload Sample MetaData File and verify the data");
            SoftAssert softAssert = new SoftAssert();

            for (int x = 0; x <= 100; x++) {

                int lanesCompleted = getIngestedLanesCount(runId);

                if (lanesCompleted == 12) {

                    System.out.println(ReportingPlatformIngestionTemplateUpload);
                    FileInputStream fsIP = new FileInputStream(new File(ReportingPlatformIngestionTemplateUpload));
                    @SuppressWarnings("resource")
                    XSSFWorkbook wb = new XSSFWorkbook(fsIP);
                    XSSFSheet worksheet = wb.getSheet("Data");
                 //   Cell cell = null;

                    for (int lane = 1; lane <= 12; lane++) {

                        XSSFRow row = worksheet.getRow(lane);
                        if (row == null) {
                            row = worksheet.createRow(lane);
                        }

                        XSSFCell cell = row.createCell(0); //result id
                        cell.setCellValue(getTRunId(runId, lane));

                        cell = row.createCell(1);  //collection site id
                        cell.setCellValue(getCreatedHouseUniqueSiteID(houseName));

                        cell = row.createCell(3); //sample matrix
                        cell.setCellValue(dateYYYYMMDD + "_Cocc");



                        cell = row.createCell(4); //sample id
                        cell.setCellValue(runId);

                        cell = row.createCell(9); //Asset id
                        cell.setCellValue(getAssetId());

                        cell = row.createCell(16);  //lane
                        cell.setCellValue(lane);

                        cell = row.createCell(19); //cartridge id
                        cell.setCellValue(cartridgeID);

                        cell = row.createCell(23);    //collection date
                        cell.setCellValue(getDatefromCurrentDate(AssetDay));

                        fsIP.close();
                    }

                    FileOutputStream output_file = new FileOutputStream(new File(ReportingPlatformIngestionTemplateUpload));
                    wb.write(output_file);
                    output_file.close();
                    break;
                }

            }

            BaseTest driver = new BaseTest();
            driver.getDriver().get(api_announcementList);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            click(uploadForDropdown);
            type(uploadForDropdownInput, "Project123");
            enterKey(uploadForDropdownInput);
            Thread.sleep(1000);

            click(dataTemplateDropDown);
            type(dataTemplateDropDownInput, "Sample Metadata");
            enterKey(dataTemplateDropDownInput);
            Thread.sleep(2000);

            driver.getDriver().findElement(browseButton).sendKeys(ReportingPlatformIngestionTemplateUpload);
            waitElementVisible(BasePage.alertMessage);
            Thread.sleep(3000);
            click(uploadedTemplateSaveButton);
            waitElementVisible(BasePage.alertMessage);
            Thread.sleep(2000);
            softAssert.assertAll();
            getScreenshot();
            test.pass("Asset was created successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("File Upload Failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("File Upload Failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }



    public static String getTRunId(String runID, int laneNo) throws SQLException {
        ResultSet getTRunIdData = DB_Config_DW.getStmt().executeQuery("Select * from Patho_1_data where run_id = '"+runID+"' and lane_no = '"+laneNo+"'");
        System.out.println("Select * from Patho_1_data where run_id = '"+runID+"'");
        String trundID = null;
        while (getTRunIdData.next()) {
            trundID = getTRunIdData.getString("T_RUN_ID");
            System.out.println("TRunID: " + trundID);
        }
        return trundID;
    }

    public static String getAssetId() throws SQLException {
        ResultSet getTRunIdData = DB_Config_DW.getStmt().executeQuery("Select * from Asset_mgmt where integrator_Asset_id = '"+reportingAssetIntegratorID+"'");
        String AssetID = null;
        while (getTRunIdData.next()) {
            AssetID = getTRunIdData.getString("UNIQUE_Asset_ID");
            System.out.println("AssetID: " + AssetID);
        }
        return AssetID;
    }

    public static int getIngestedLanesCount(String runID) throws SQLException {
        ResultSet getTRunIdData = DB_Config_DW.getStmt().executeQuery("Select count(status) as count from Patho_1_data where run_id = '"+runID+"'");
        int lanesIngested = 0;
        while (getTRunIdData.next()) {
            lanesIngested = getTRunIdData.getInt("Count");
            System.out.println("Count: " + lanesIngested);
        }
        return lanesIngested;
    }

}
