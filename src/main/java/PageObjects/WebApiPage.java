package PageObjects;

import MiscFunctions.*;
import com.jayway.jsonpath.Configuration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static Config.BaseTest.saveResultNoScreenshot;
import static MiscFunctions.DB_Config_DW.*;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.DateUtil.getDayAfterTomorrowDate;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.waitElementVisible;
import static MiscFunctions.Queries.*;

public class WebApiPage {

    public static String getAssetIDQuery = "Select Top 1 id from dbo.asset_mgmt Order By id desc";

    public static String getAssetRowQuery;

    static {
        getAssetRowQuery = "Select TOP 1 * from dbo.asset_mgmt Order By id desc";
    }

    public static String getProgramQuery = "Select Top 1 * from program where isActive = 1 and isDeleted = 0";
    public static String getAssetProgramRowQuery;

    static {
        try {
            getAssetProgramRowQuery = "Select TOP 1 * from Asset_program_details where Asset_id = '" + getAssetId() + "' Order By Asset_id desc";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getAssetSettlementRowQuery;

    static {
        try {
            getAssetSettlementRowQuery = "Select TOP 1 * from Asset_settlement where Asset_id = '" + getAssetId() + "' Order By Asset_id desc";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getAddedSettlementQuery;

    static {
        try {
            getAddedSettlementQuery = "SELECT * FROM dbo.Asset_SETTLEMENT WHERE Asset_ID = " + getAssetId() + " AND IS_ACTIVE=1";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getMortalityAuditRowCountQuery;

    static {
        try {
            getMortalityAuditRowCountQuery = "Select Count(id) as Rows from Asset_Audit where Asset_id = '" + getAssetId() + "'";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getCreatedAssetQuery = "Select * from dbo.asset_mgmt where ID = ";

    public static String animalName = "Broilers";


    public static String getFarmNotAssignedToUserQuery;

    static {
        try {
            getFarmNotAssignedToUserQuery = "exec spGetAllComplexNotAssigned " + getUsersId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getProcessingSiteIDAssignedToUserQuery;

    static {
        try {
            getProcessingSiteIDAssignedToUserQuery = "SELECT TOP 1 siteId FROM dbo.ClientSiteAssn WHERE userId  = " + getUsersId() + "   AND isActive = 1 AND isDeleted = 0 ";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static int getAssetId() throws SQLException {
        ResultSet getAssetID = DB_Config_DW.getStmt().executeQuery(getAssetIDQuery);
        int AssetID = 0;
        while (getAssetID.next()) {
            AssetID = getAssetID.getInt("id");
            System.out.println("AssetID: " + AssetID);
        }
        return AssetID;
    }

    public static int getProcessingSiteIDAssignedToUserOld() throws SQLException {
        ResultSet getProcessingSiteIDResults = DB_Config_DB.getStmt().executeQuery(getProcessingSiteIDAssignedToUserQuery);
        int getProcessingSiteID = 0;
        while (getProcessingSiteIDResults.next()) {
            getProcessingSiteID = getProcessingSiteIDResults.getInt("siteId");
            System.out.println("Processing Site ID: " + getProcessingSiteID);
        }
        return getProcessingSiteID;
    }


    public static int getanimalSizeID(String name) throws SQLException {
        ResultSet getanimalSizeResults = DB_Config_DB.getStmt().executeQuery(Queries.getanimalSizeID + " and name = '" + name + "'");
        int getanimalSizeID = 0;
        while (getanimalSizeResults.next()) {
            getanimalSizeID = getanimalSizeResults.getInt("id");
        }
        return getanimalSizeID;
    }

    public static String getSampleID() throws SQLException {
        ResultSet getSampleIDResults = DB_Config_DW.getStmt().executeQuery("Select Top 1 Sample_ID from Patho_1_DATA where user_id = '" + getUsersId() + "' and Sample_Id IS NOT NULL");
        String getSampleid = null;
        while (getSampleIDResults.next()) {
            getSampleid = getSampleIDResults.getString("Sample_ID");
        }
        return getSampleid;
    }

    public static String getClostridiumSampleID() throws SQLException {
        ResultSet getSampleIDResults = DB_Config_DW.getStmt().executeQuery("Select Top 1 Sample_ID from Clostridium_DATA where user_id = '" + getUsersId() + "' and Sample_Id IS NOT NULL");
        String getSampleid = null;
        while (getSampleIDResults.next()) {
            getSampleid = getSampleIDResults.getString("Sample_ID");
        }
        return getSampleid;
    }

    public static String getTVBSampleID() throws SQLException {
        ResultSet getSampleIDResults = DB_Config_DW.getStmt().executeQuery("Select Top 1 Sample_ID from TVB_DATA where user_id = '" + getUsersId() + "' and Sample_Id IS NOT NULL");
        String getSampleid = null;
        while (getSampleIDResults.next()) {
            getSampleid = getSampleIDResults.getString("Sample_ID");
        }
        return getSampleid;
    }

    public static int getdeploymentID() throws SQLException {
        ResultSet getdeploymentIDResults = DB_Config_DW.getStmt().executeQuery("Select Top 1 id from Asset_house_details order by id desc");
        int deploymentID = 0;
        while (getdeploymentIDResults.next()) {
            deploymentID = getdeploymentIDResults.getInt("id");
            System.out.println("deploymentID1: " + deploymentID);
        }
        return deploymentID;
    }


    public static int getFarmIDNotAssignedtoUser() throws SQLException {
        String DB_URL = "jdbc:sqlserver://Project123-asql-001.database.windows.net;databaseName=" + config.ie_database_db() + ";user=" + DB_UserName + ";Password=" + DB_Password;
        Connection conn = DriverManager.getConnection(DB_URL, DB_UserName, DB_Password);
        setStmt(conn.createStatement());

        CallableStatement cstmt = conn.prepareCall(getFarmNotAssignedToUserQuery);
        ResultSet getFarmIDResults = cstmt.executeQuery();
        int getFarmID = 0;

        while (getFarmIDResults.next()) {
            getFarmID = getFarmIDResults.getInt("siteId");
            System.out.println("Farm id: " + getFarmID);
            break;
        }
        return getFarmID;
    }

    public static int getAuditRowsCount(String query) throws SQLException {
        ResultSet getAssetID = DB_Config_DW.getStmt().executeQuery(query);
        int count = 0;
        while (getAssetID.next()) {
            count = getAssetID.getInt("Rows");
            System.out.println("Rows: " + count);
        }
        return count;
    }

    public static String LoginAPI() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        JSONObject json = new JSONObject();

        json.put("userEmail", config.ie_username());
        json.put("userPassword", config.ie_password());
        request.body(json.toString());

        Response response = request.post(Constants.config.webAPI() + "/AdminManagement/Login");
        int code = response.getStatusCode();
        //   Assert.assertEquals(code, 200, "Did not return 200 status code");
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("responseBody.token");
        //    Assert.assertTrue(token.startsWith("ey"), "Did not return token string");
        return token;
    }


    public static void getAssetByID() throws IOException {
        try {
            test = extent.createTest("IE-10255/IE-10290: Run Get Asset API with valid ID");
            SoftAssert softAssert = new SoftAssert();

            System.out.println("Here>>>>>>>>>>>>>>>.>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("Token: " + LoginAPI());

            RequestSpecification request_getAssetID = RestAssured.given();
            request_getAssetID.header("Content-Type", "application/json");
            request_getAssetID.header("Authorization", "bearer " + LoginAPI());
            Response response = request_getAssetID.get(Constants.config.webAPI() + "/AssetManagement/Assets/" + getAssetId());

            int code = response.getStatusCode();
            softAssert.assertEquals(code, 200, "Did not return 200 status code");

            String response_data = response.asString();
            System.out.println("Response: " + response_data);
            test.info("Response: " + response_data);
            if (!response_data.equals("")) {
                System.out.println(getAssetIDQuery);
                ResultSet getAssetRow = DB_Config_DW.getStmt().executeQuery(getAssetRowQuery);
                while (getAssetRow.next()) {
                    JsonPath jsonPathEvaluator = response.jsonPath();
                    softAssert.assertEquals(jsonPathEvaluator.get("title"), "Success");
                    softAssert.assertEquals(jsonPathEvaluator.getInt("status"), 200);
                    softAssert.assertEquals(jsonPathEvaluator.getString("responseBody.uniqueAssetId"), getAssetRow.getString("UNIQUE_Asset_ID"));
                    softAssert.assertEquals("1", getAssetRow.getString("PRODUCTION_TYPE_ID"));
                    softAssert.assertEquals(jsonPathEvaluator.getString("responseBody.farmId"), String.valueOf(getHouseParentSiteId()));
                    softAssert.assertEquals(jsonPathEvaluator.getString("responseBody.animalSizeId"), String.valueOf(getanimalSizeID(animalName)));
                    softAssert.assertEquals(jsonPathEvaluator.getString("responseBody.animalSizeName"), animalName);
                    softAssert.assertEquals(jsonPathEvaluator.getString("responseBody.animalSexId"), "4");
                    softAssert.assertEquals(jsonPathEvaluator.getString("responseBody.animalSexName"), "Male");
                    softAssert.assertEquals(jsonPathEvaluator.getString("responseBody.integratorAssetId"), dateYYYYMMDD + "_100");
                    softAssert.assertEquals(jsonPathEvaluator.getInt("responseBody.marketingProgramId"), 1);
                    softAssert.assertEquals(jsonPathEvaluator.get("responseBody.marketingProgramName"), "Conventional");
                }


                ResultSet getAssetProgramRow = DB_Config_DW.getStmt().executeQuery(getAssetProgramRowQuery);
                while (getAssetProgramRow.next()) {
                    JsonPath jsonPath = new JsonPath(response_data);
                    //int programId = jsonPath.getInt("responseBody.programs[0].programId");
                    //softAssert.assertEquals(jsonPath.getInt("responseBody.programs[0].programId"), getProgramID(), "Program ID not correct");
                    //  softAssert.assertEquals(jsonPath.getString("responseBody.programs[0].programName"), getProgramName());
                    // softAssert.assertEquals(jsonPath.getString("responseBody.programs[0].programDisplayName"), getProgramDisplayName());
                    //  softAssert.assertEquals(jsonPath.getInt("responseBody.programs[0].programTypeId"), getProgramTypeID());
                }

                ResultSet getAssetSettlementRow = DB_Config_DW.getStmt().executeQuery(getAssetSettlementRowQuery);
                while (getAssetSettlementRow.next()) {
                    JsonPath jsonPath = new JsonPath(response_data);
                    softAssert.assertEquals(jsonPath.getString("responseBody.settlements[0].processingSiteId"), String.valueOf(getProcessingSiteIDAssignedToUser()));
                    softAssert.assertEquals(jsonPath.getString("responseBody.settlements[0].processingDate"), getDayAfterTomorrowDate(), "Processing Date is empty");
                }

//                ResultSet getAssetSamplingPlanRow = DB_Config_DB.getStmt().executeQuery(getSamplingPlanQuery());
//                while (getAssetSamplingPlanRow.next()) {
//                    JsonPath jsonPath = new JsonPath(response_data);
//                    softAssert.assertEquals(jsonPath.getInt("responseBody.deployments[0].samplingPlans[0].samplingPlanId"), getSamplingPlanID());
//                    softAssert.assertEquals(jsonPath.getString("responseBody.deployments[0].samplingPlans[0].samplingPlanName"), getSamplingPlanName());
//                    softAssert.assertEquals(jsonPath.getString("responseBody.deployments[0].samplingPlans[0].startDate"), getSamplingPlanStartDate());
//                }


                softAssert.assertAll();
                test.pass("Test Passed Successfully");
                saveResultNoScreenshot(ITestResult.SUCCESS, null);
            }

            //      }
            else {

                Assert.assertTrue(false, "Did not get response body from the api");
            }

        } catch (AssertionError | SQLException ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public static void AssetInvalidID() throws IOException {
        try {
            test = extent.createTest("IE-10256: Run Get Asset API with invalid ID");
            SoftAssert softAssert = new SoftAssert();
            RequestSpecification request_getAssetID = RestAssured.given();
            request_getAssetID.header("Content-Type", "application/json");
            request_getAssetID.header("Authorization", "bearer " + LoginAPI());

            Response response = request_getAssetID.get(Constants.config.webAPI() + "/AssetManagement/Assets/12121");

            int code = response.getStatusCode();
            softAssert.assertEquals(code, 404, "Did not return 404 status code");

            String response_data = response.asString();
            System.out.println(response_data);
            test.info("Response: " + response_data);
            JsonPath jsonPathEvaluator = response.jsonPath();
            softAssert.assertEquals(jsonPathEvaluator.get("title"), "Not Found");
            softAssert.assertEquals(jsonPathEvaluator.getInt("status"), 404);
            softAssert.assertEquals(jsonPathEvaluator.getString("systemErrors.type"), "[AssetNotFoundException]");
            softAssert.assertEquals(jsonPathEvaluator.getString("systemErrors.detail"), "[The Asset with the identifier 12121 was not found.]");
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public void CreateAssetWithAllAttributes(int animalSizeId, String animalSizeName) throws SQLException, IOException {
        try {
            test = extent.createTest("IE:10257/10258/10260/10261/10262 : Create Asset with Valid Data");
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets";

            String requestBody = "{\n" +
                    "  \"integratorAssetId\": \"" + dateYYYYMMDD + "_100\",\n" +
                    "  \"marketingProgram\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Conventional\"\n" +
                    "  },\n" +
                    "  \"productionType\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Broiler\"\n" +
                    "  },\n" +
                    "  \"farm\": {\n" +
                    "    \"siteId\": " + getHouseParentSiteId() + "\n" +
                    "  },\n" +
                    "  \"animalSize\": {\n" +
                    "    \"id\": " + animalSizeId + ",\n" +
                    "    \"name\": \"" + animalSizeName + "\"\n" +
                    "  },\n" +
                    "  \"animalSex\": {\n" +
                    "    \"id\": 4,\n" +
                    "    \"name\": \"Male\"\n" +
                    "  },\n" +
                    "  \"Assetdeployments\": [\n" +
                    "    {\n" +
                    "      \"house\": {\n" +
                    "        \"siteId\": " + getHouseIDAssignedToUser() + "\n" +
                    "      },\n" +
                    "      \"deploymentDate\": \"" + getLastYearDate() + "\",\n" +
                    "      \"estimatedProcessingDate\": \"" + getDayAfterTomorrowDate() + "\",\n" +
                    "      \"animalsPlaced\": 2,\n" +
//                                 "      \"AssetSamplingPlans\": [\n" +
//                                  "        {\n" +
//                                  "          \"samplingPlanIdComplexLevel\": " + getSamplingPlanID() + "\n" +
//                                  "        }\n" +
//                                 "      ]\n" +
                    "    }\n" +
                    "  ],\n" +
//                    "  \"AssetPrograms\": [\n" +
//                            "    {\n" +
//                            "      \"programId\": " + getProgramID() + "\n" +
//                            "    }\n" +
//                    "  ],\n" +
                    "  \"AssetSettlements\": [\n" +
                    "    {\n" +
                    "      \"processingSiteId\": " + getProcessingSiteIDAssignedToUser() + ",\n" +
                    "      \"processingDate\": \"" + getDayAfterTomorrowDate() + "\",\n" +
                    "      \"avgSoldAge\": 3,\n" +
                    "      \"numanimalsSold\": 2,\n" +
                    "      \"weeklyFarmRank\": 1,\n" +
                    "      \"historicalFarmCostVariance\": 0,\n" +
                    "      \"weeklyFarmCostVariance\": 1,\n" +
                    "      \"daysOut\": 2,\n" +
                    "      \"ageOfLitter\": 3,\n" +
                    "      \"deploymentDensity\": 4,\n" +
                    "      \"usdaPlantId\": \"AO11\",\n" +
                    "      \"plantLocation\": \"US\",\n" +
                    "      \"numanimalsProcessed\": 5,\n" +
                    "      \"avganimalWeightLbs\": 6,\n" +
                    "      \"avganimalWeightKgs\": 7,\n" +
                    "      \"avgDailyWeightGainLb\": 8,\n" +
                    "      \"avgDailyWeightGainKg\": 9,\n" +
                    "      \"totalWeightProcessedLb\": 10,\n" +
                    "      \"totalWeightProcessedKg\": 11,\n" +
                    "      \"totalFeedWeightLb\": 12,\n" +
                    "      \"totalFeedWeightKg\": 13,\n" +
                    "      \"fcr\": 14,\n" +
                    "      \"fcrAdj\": 15,\n" +
                    "      \"feedCostPerLivePound\": 16,\n" +
                    "      \"medicationCostPerLivePound\": 17,\n" +
                    "      \"growerCostPerLivePound\": 18,\n" +
                    "      \"livabilityPercentage\": 19,\n" +
                    "      \"overallMortalityPercentage\": 20,\n" +
                    "      \"hatchDate\": \"" + getSecondDayAfterTomorrowDate() + "\",\n" +
                    "      \"week1Mortality\": 1,\n" +
                    "      \"week2Mortality\": 2,\n" +
                    "      \"week3Mortality\": 3,\n" +
                    "      \"week4Mortality\": 4,\n" +
                    "      \"week5Mortality\": 5,\n" +
                    "      \"week6Mortality\": 6,\n" +
                    "      \"week7Mortality\": 7,\n" +
                    "      \"week8Mortality\": 8,\n" +
                    "      \"week9Mortality\": 0,\n" +
                    "      \"numanimalsDOAPlant\": 1,\n" +
                    "      \"totalWeightCondemnedLb\": 2,\n" +
                    "      \"totalWeightCondemnedKg\": 3,\n" +
                    "      \"numanimalsCondemnedWhole\": 4,\n" +
                    "      \"partsWeightCondemnedLb\": 5,\n" +
                    "      \"partsWeightCondemnedKg\": 6,\n" +
                    "      \"kCalPerPound\": 7,\n" +
                    "      \"gradeAPawsPerc\": 8,\n" +
                    "      \"airsacPercentage\": 9,\n" +
                    "      \"ipPercentage\": 10,\n" +
                    "      \"leukosisPercentage\": 11,\n" +
                    "      \"septoxPercentage\": 12,\n" +
                    "      \"tumorPercentage\": 13\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 200);

            String responseBody = response.asString();
            System.out.println("Response: " + responseBody);
            test.info("Response: " + responseBody);


            JsonPath jsonPath = new JsonPath(responseBody);

            System.out.println(jsonPath.getInt("responseBody"));
            String AssetID = jsonPath.getString("responseBody");

            if (statusCode == 200) {
                ResultSet getCreatedAsset = DB_Config_DW.getStmt().executeQuery(getCreatedAssetQuery + AssetID);
                boolean hasRow = getCreatedAsset.next();
                softAssert.assertEquals(hasRow, true, "Created Asset did not save in database");
            } else {
                softAssert.assertTrue(false, "Query was not executed because the api did not return 200 status code");
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////

            ResultSet getAssetRow = DB_Config_DW.getStmt().executeQuery("Select * from asset_mgmt where id = '" + AssetID + "'");
            while (getAssetRow.next()) {
                softAssert.assertEquals("A0" + String.valueOf(AssetID), getAssetRow.getString("UNIQUE_Asset_ID"));
                softAssert.assertEquals(dateYYYYMMDD + "_100", getAssetRow.getString("INTEGRATOR_Asset_ID"));
                softAssert.assertEquals(getAssetRow.getInt("animal_SIZE_ID"), animalSizeId, "animal size id not correct");
                softAssert.assertEquals(getAssetRow.getString("animal_SEX_ID"), "4", "animal sex id not correct");
                softAssert.assertEquals(getAssetRow.getString("MARKETING_PROGRAM_ID"), "1", "Marketing program id not correct");
                softAssert.assertEquals(getAssetRow.getString("PRODUCTION_TYPE_ID"), "1", "Production type id not correct");
                softAssert.assertEquals(getAssetRow.getString("animal_SIZE"), animalSizeName);
                softAssert.assertEquals(getAssetRow.getString("animal_SEX"), "Male");
                softAssert.assertEquals(getAssetRow.getString("MARKETING_PROGRAM"), "Conventional");
                softAssert.assertEquals(getAssetRow.getString("TOTAL_animalS_PLACED"), "2");
                softAssert.assertEquals(getAssetRow.getString("FARM_INTERNAL_ID"), String.valueOf(getHouseParentSiteId()));
            }


            ///////////////////////////////////////////////////////////////////////////////////////////////////////////

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public void CreateAssetAPIValidationChecks(String testcaseName, int farmSiteId, int animalSizeId, int houseId, int processingSiteId, String deploymentDate, String estimatedDate, String processingDate, String errorMessageExpected) throws SQLException, IOException {
        try {
            test = extent.createTest(testcaseName);
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets";

            String requestBody = "{\n" +
                    "  \"integratorAssetId\": \"" + dateYYYYMMDD + "_100\",\n" +
                    "  \"marketingProgram\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Conventional\"\n" +
                    "  },\n" +
                    "  \"farm\": {\n" +
                    "    \"siteId\": " + farmSiteId + "\n" +
                    "  },\n" +
                    "  \"animalSize\": {\n" +
                    "    \"id\": " + animalSizeId + ",\n" +
                    "    \"name\": \"Small\"\n" +
                    "  },\n" +
                    "  \"animalSex\": {\n" +
                    "    \"id\": 4,\n" +
                    "    \"name\": \"Male\"\n" +
                    "  },\n" +
                    "  \"Assetdeployments\": [\n" +
                    "    {\n" +
                    "      \"house\": {\n" +
                    "        \"siteId\": " + houseId + "\n" +
                    "      },\n" +
                    "      \"deploymentDate\": \"" + deploymentDate + "\",\n" +
                    "      \"estimatedProcessingDate\": \"" + estimatedDate + "\",\n" +
                    "      \"animalsPlaced\": 2,\n" +
                    "      \"AssetSamplingPlans\": [\n" +
                    "        {\n" +
                    "          \"samplingPlanIdComplexLevel\": " + getSamplingPlanIDWithNullEndDate() + "\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"AssetPrograms\": [\n" +
                    "    {\n" +
                    "      \"programId\": " + getProgramID() + "\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"AssetSettlements\": [\n" +
                    "    {\n" +
                    "      \"processingSiteId\": " + processingSiteId + ",\n" +
                    "      \"processingDate\": \"" + processingDate + "\",\n" +
                    "      \"avgSoldAge\": 3,\n" +
                    "      \"numanimalsSold\": 2,\n" +
                    "      \"weeklyFarmRank\": 1,\n" +
                    "      \"historicalFarmCostVariance\": 0,\n" +
                    "      \"weeklyFarmCostVariance\": 1,\n" +
                    "      \"daysOut\": 2,\n" +
                    "      \"ageOfLitter\": 3,\n" +
                    "      \"deploymentDensity\": 4,\n" +
                    "      \"usdaPlantId\": \"AO11\",\n" +
                    "      \"plantLocation\": \"US\",\n" +
                    "      \"numanimalsProcessed\": 5,\n" +
                    "      \"avganimalWeightLbs\": 6,\n" +
                    "      \"avganimalWeightKgs\": 7,\n" +
                    "      \"avgDailyWeightGainLb\": 8,\n" +
                    "      \"avgDailyWeightGainKg\": 9,\n" +
                    "      \"totalWeightProcessedLb\": 10,\n" +
                    "      \"totalWeightProcessedKg\": 11,\n" +
                    "      \"totalFeedWeightLb\": 12,\n" +
                    "      \"totalFeedWeightKg\": 13,\n" +
                    "      \"fcr\": 14,\n" +
                    "      \"fcrAdj\": 15,\n" +
                    "      \"feedCostPerLivePound\": 16,\n" +
                    "      \"medicationCostPerLivePound\": 17,\n" +
                    "      \"growerCostPerLivePound\": 18,\n" +
                    "      \"livabilityPercentage\": 19,\n" +
                    "      \"overallMortalityPercentage\": 20,\n" +
                    "      \"hatchDate\": \"" + getSecondDayAfterTomorrowDate() + "\",\n" +
                    "      \"week1Mortality\": 1,\n" +
                    "      \"week2Mortality\": 2,\n" +
                    "      \"week3Mortality\": 3,\n" +
                    "      \"week4Mortality\": 4,\n" +
                    "      \"week5Mortality\": 5,\n" +
                    "      \"week6Mortality\": 6,\n" +
                    "      \"week7Mortality\": 7,\n" +
                    "      \"week8Mortality\": 8,\n" +
                    "      \"week9Mortality\": 0,\n" +
                    "      \"numanimalsDOAPlant\": 1,\n" +
                    "      \"totalWeightCondemnedLb\": 2,\n" +
                    "      \"totalWeightCondemnedKg\": 3,\n" +
                    "      \"numanimalsCondemnedWhole\": 4,\n" +
                    "      \"partsWeightCondemnedLb\": 5,\n" +
                    "      \"partsWeightCondemnedKg\": 6,\n" +
                    "      \"kCalPerPound\": 7,\n" +
                    "      \"gradeAPawsPerc\": 8,\n" +
                    "      \"airsacPercentage\": 9,\n" +
                    "      \"ipPercentage\": 10,\n" +
                    "      \"leukosisPercentage\": 11,\n" +
                    "      \"septoxPercentage\": 12,\n" +
                    "      \"tumorPercentage\": 13\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            String token = LoginAPI();
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post();

            int statusCode = response.getStatusCode();

            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            softAssert.assertEquals(statusCode, 400);


            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);

            JsonPath jsonPathEvaluator = response.jsonPath();

            softAssert.assertEquals(jsonPathEvaluator.get("title"), "One or more system errors occurred.");


            System.out.println("Actual: " + jsonPathEvaluator.get("validationErrors.detail"));
            softAssert.assertTrue(responseBody.contains(errorMessageExpected), "Expected " + errorMessageExpected + " but found " + jsonPathEvaluator.get("validationErrors.detail"));
            test.info("Response" + responseBody);
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public void CreateAsset(int animalSizeId, String animalSizeName, String deploymentDate) throws SQLException, IOException {
        try {
            test = extent.createTest("IE:10257: Create Asset with Valid Data");
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets";

            String requestBody = "{\n" +
                    "  \"integratorAssetId\": \"" + dateYYYYMMDD + "_101\",\n" +
                    "  \"marketingProgram\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Conventional\"\n" +
                    "  },\n" +
                    "  \"farm\": {\n" +
                    "    \"siteId\": " + getHouseParentSiteId() + "\n" +
                    "  },\n" +
                    "  \"animalSize\": {\n" +
                    "    \"id\": " + animalSizeId + ",\n" +
                    "    \"name\": \"" + animalSizeName + "\"\n" +
                    "  },\n" +
                    "  \"animalSex\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"male\"\n" +
                    "  },\n" +
                    "  \"Assetdeployments\": [\n" +
                    "    {\n" +
                    "      \"house\": {\n" +
                    "        \"siteId\": " + getHouseIDAssignedToUser() + "\n" +
                    "      },\n" +
                    "      \"deploymentDate\": \"" + deploymentDate + "\",\n" +
                    "      \"estimatedProcessingDate\": \"\",\n" +
                    "      \"animalsPlaced\": 0,\n" +
                    "      \"AssetSamplingPlans\": [\n" +
                    "     \n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"AssetPrograms\": [\n" +
//                    "    {\n" +
//                    "      \"programId\": " + getProgramID() + "\n" +
//                    "    }\n" +
                    "  ],\n" +
                    "  \"AssetSettlements\": [\n" +
                    "  ]\n" +
                    "}";


            System.out.println(requestBody);

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 200);

            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);
            test.info("Response: " + responseBody);

            JsonPath JsonPathEvaluator = new JsonPath(responseBody);
            int AssetID = JsonPathEvaluator.getInt("responseBody");
            System.out.println("AssetID: " + AssetID);

            if (statusCode == 200) {
                ResultSet getCreatedAsset = DB_Config_DW.getStmt().executeQuery(getCreatedAssetQuery + AssetID);
                boolean hasRow = getCreatedAsset.next();
                softAssert.assertEquals(hasRow, true, "Created Asset did not save in database");
            } else {
                softAssert.assertTrue(false, "Query was not executed because the api did not return 200 status code");
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////

            ResultSet getAssetRow = DB_Config_DW.getStmt().executeQuery(getCreatedAssetQuery + AssetID);
            while (getAssetRow.next()) {
                softAssert.assertEquals("A0" + String.valueOf(AssetID), getAssetRow.getString("UNIQUE_Asset_ID"));
                softAssert.assertEquals(dateYYYYMMDD + "_101", getAssetRow.getString("INTEGRATOR_Asset_ID"));
                softAssert.assertEquals(animalSizeId, getAssetRow.getInt("animal_SIZE_ID"), "animal size id not correct");
                softAssert.assertEquals("1", getAssetRow.getString("animal_SEX_ID"), "animal sex id not correct");
                softAssert.assertEquals("1", getAssetRow.getString("MARKETING_PROGRAM_ID"), "Marketing program id not correct");
                softAssert.assertEquals(animalSizeName, getAssetRow.getString("animal_SIZE"));
                softAssert.assertEquals("All male", getAssetRow.getString("animal_SEX"));
                softAssert.assertEquals("Conventional", getAssetRow.getString("MARKETING_PROGRAM"));
                softAssert.assertEquals("0", getAssetRow.getString("TOTAL_animalS_PLACED"));
                softAssert.assertEquals(getAssetRow.getString("PRODUCTION_TYPE_ID"), "1", "Production Type was Empty");
                softAssert.assertEquals(String.valueOf(getHouseParentSiteId()), getAssetRow.getString("FARM_INTERNAL_ID"));
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public void AssetdeploymentAPI(String testcaseName, int AssetId, int houseSiteId, String deploymentDate, String estProcessingDate, int samplingPlanID, String messageExpected, int statusCodeExpected) throws SQLException, IOException {
        try {
            test = extent.createTest(testcaseName);
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets/" + AssetId + "/deployments";

            String requestBody = "{\n" +
                    "  \"AssetId\": " + AssetId + ",\n" +
                    "  \"Assetdeployments\": [\n" +
                    "    {\n" +
                    "      \"house\": {\n" +
                    "        \"siteId\": " + houseSiteId + "\n" +
                    "      },\n" +
                    "      \"deploymentDate\": \"" + deploymentDate + "\",\n" +
                    "      \"estimatedProcessingDate\": \"" + estProcessingDate + "\",\n" +
                    "      \"animalsPlaced\": 2,\n" +
                    "      \"AssetSamplingPlans\": [\n" +
                    "        {\n" +
                    "          \"samplingPlanIdComplexLevel\": " + samplingPlanID + "\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI();
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, statusCodeExpected);

            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);
            test.info("Response: " + responseBody);
            softAssert.assertTrue(responseBody.contains(messageExpected), "Did not receive the expected response message");

            if (statusCode != 400) {
                JsonPath JsonPathEvaluator = new JsonPath(responseBody);
                int AssetID = JsonPathEvaluator.getInt("responseBody");

                ResultSet getAddeddeploymentRow = DB_Config_DW.getStmt().executeQuery("SELECT sample_plan.* FROM dbo.asset_mgmt\n" +
                        "Asset_main JOIN dbo.Asset_HOUSE_DETAILS Asset_house\n" +
                        "ON Asset_house.Asset_ID = Asset_main.ID\n" +
                        "JOIN dbo.SAMPLING_PLAN_Asset_HOUSE sample_plan \n" +
                        "ON sample_plan.Asset_HOUSE_DETAILS_ID = Asset_house.ID\n" +
                        "WHERE Asset_main.UNIQUE_Asset_ID = '" + AssetID + "' ");

                while (getAddeddeploymentRow.next()) {
                    softAssert.assertEquals(getAddeddeploymentRow.getInt("is_Active"), 1, "deployment not Active");
                }
            }

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }

    public void AssetdeploymentWithTwoSamplingPlanAPI(String testcaseName, int AssetId, int houseSiteId, String deploymentDate, String estProcessingDate, int samplingPlanID, String messageExpected, int statusCodeExpected) throws SQLException, IOException {
        try {
            test = extent.createTest(testcaseName);
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets/" + AssetId + "/deployments";

            String requestBody = "{\n" +
                    "  \"AssetId\": " + AssetId + ",\n" +
                    "  \"Assetdeployments\": [\n" +
                    "    {\n" +
                    "      \"house\": {\n" +
                    "        \"siteId\": " + houseSiteId + "\n" +
                    "      },\n" +
                    "      \"deploymentDate\": \"" + deploymentDate + "\",\n" +
                    "      \"estimatedProcessingDate\": \"" + estProcessingDate + "\",\n" +
                    "      \"animalsPlaced\": 2,\n" +
                    "      \"AssetSamplingPlans\": [\n" +
                    "        {\n" +
                    "          \"samplingPlanIdComplexLevel\": " + samplingPlanID + "\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"samplingPlanIdComplexLevel\": " + samplingPlanID + "\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI();
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, statusCodeExpected);

            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);
            test.info("Response: " + responseBody);
            softAssert.assertTrue(responseBody.contains(messageExpected), "Did not receive the expected response message");


            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public void DeleteSamplingPlanAPI(String testcaseName, int AssetId, int deploymentID, int samplingPlanID, String messageExpected, int statusCodeExpected) throws SQLException, IOException {
        try {
            test = extent.createTest(testcaseName);
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets/" + AssetId + "/deployments/" + deploymentID + "/samplingPlans/" + samplingPlanID;

            String requestBody = "{\n" +
                    "  \"AssetId\": " + AssetId + ",\n" +
                    "  \"deploymentId\": " + deploymentID + ",\n" +
                    "  \"samplingPlanId\": " + samplingPlanID + "\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI();
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .delete();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, statusCodeExpected);

            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);
            test.info("Response: " + responseBody);
            softAssert.assertTrue(responseBody.contains(messageExpected), "Did not receive the expected response message");

            ResultSet getsamplingPlanRow = DB_Config_DW.getStmt().executeQuery(getSamplingPlanAttachedtodeployment("A0" + AssetId, deploymentID));

            while (getsamplingPlanRow.next()) {
                softAssert.assertEquals(getsamplingPlanRow.getInt("is_Active"), 0, "deployment still showing active in deployment row");
                softAssert.assertEquals(getsamplingPlanRow.getInt("is_Deleted"), 1, "deployment not deleted from db");
            }

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }

    public void AddProgramAPI(String testcaseName, int AssetId, int programID, String messageExpected, int statusCodeExpected) throws SQLException, IOException {
        try {
            test = extent.createTest(testcaseName);
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets/" + AssetId + "/programs";

            String requestBody = "{\n" +
                    "  \"AssetId\": " + AssetId + ",\n" +
                    "  \"AssetPrograms\": [\n" +
                    "    {\n" +
                    "      \"programId\": " + programID + "\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI();
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, statusCodeExpected);

            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);
            test.info("Response: " + responseBody);
            softAssert.assertTrue(responseBody.contains(messageExpected), "Did not receive the expected response message");
            JsonPath jsonPathEvaluator = response.jsonPath();
            int AssetID = jsonPathEvaluator.get("responseBody");
            System.out.println("----> " + AssetID);
            softAssert.assertEquals(AssetID, AssetId);

            ResultSet getProgramRow = DB_Config_DW.getStmt().executeQuery(getProgramAttachedToAsset("A0" + AssetId));

            while (getProgramRow.next()) {
                softAssert.assertEquals(getProgramRow.getInt("isActive"), 1, "Program showing active in deployment row");
                softAssert.assertEquals(getProgramRow.getInt("isDeleted"), 0, "Program showing deleted in DB");
            }

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }

    public void DeleteProgramAPI(String testcaseName, int AssetId, int programID, String messageExpected, int statusCodeExpected) throws SQLException, IOException {
        try {
            test = extent.createTest(testcaseName);
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets/" + AssetId + "/programs/" + programID;

            String requestBody = "{\n" +
                    "  \"AssetId\": " + AssetId + ",\n" +
                    "  \"programId\": " + programID + "\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI();
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .delete();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, statusCodeExpected);

            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);
            test.info("Response: " + responseBody);
            softAssert.assertTrue(responseBody.contains(messageExpected), "Did not receive the expected response message");

            ResultSet getProgramRow = DB_Config_DW.getStmt().executeQuery(getProgramAttachedToAsset("A0" + AssetId));

            while (getProgramRow.next()) {
                softAssert.assertEquals(getProgramRow.getInt("isActive"), 0, "Program still showing active in deployment row");
                softAssert.assertEquals(getProgramRow.getInt("isDeleted"), 1, "Program not deleted from db");
            }

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public void AssetAddSamplingPlanToExistingdeploymentAPI(String testcaseName, int AssetId, int houseSiteId, String deploymentDate, int samplingPlanID, boolean rundeployment, String messageExpected, int statusCodeExpected) throws SQLException, IOException {
        try {
            test = extent.createTest(testcaseName);
            SoftAssert softAssert = new SoftAssert();

            //  boolean rundeployment = false;
            String token = LoginAPI();
            if (rundeployment == true) {
                RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets/" + AssetId + "/deployments";

                String requestBody = "{\n" +
                        "  \"AssetId\": " + AssetId + ",\n" +
                        "  \"Assetdeployments\": [\n" +
                        "    {\n" +
                        "      \"house\": {\n" +
                        "        \"siteId\": " + houseSiteId + "\n" +
                        "      },\n" +
                        "      \"deploymentDate\": \"" + deploymentDate + "\",\n" +
                        "      \"estimatedProcessingDate\": \"\",\n" +
                        "      \"animalsPlaced\": 2\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";

                System.out.println(requestBody);


                Response response = RestAssured.given()
                        .header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .patch();

                String responseBody = response.getBody().asString();
                System.out.println("Response Assetdeployment: " + responseBody);
            }

            int deploymentID = getdeploymentID();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets/" + AssetId + "/deployments/" + deploymentID + "/samplingPlans";
            System.out.println(Constants.config.webAPI() + "/AssetManagement/Assets/" + AssetId + "/deployments/" + deploymentID + "/samplingPlans");
            String requestBody = "{\n" +
                    "\t\"AssetId\": \"" + AssetId + "\",\n" +
                    "\t\"AssetdeploymentId\": \"" + deploymentID + "\",\n" +
                    "\t\"AssetSamplingPlans\": [{\n" +
                    "\t\t\"samplingPlanIdComplexLevel\": \"" + samplingPlanID + "\"\n" +
                    "\t}]\n" +
                    "}";

            System.out.println(requestBody);

            // String token = LoginAPI();
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, statusCodeExpected);

            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);
            test.info("Response: " + responseBody);
            softAssert.assertTrue(responseBody.contains(messageExpected), "Did not receive the expected response message");

            ResultSet getSamplingPlanRow = DB_Config_DW.getStmt().executeQuery("SELECT Top 1 sampling_plan_id FROM dbo.asset_mgmt\n" +
                    "Asset_main JOIN dbo.Asset_HOUSE_DETAILS Asset_house\n" +
                    "ON Asset_house.Asset_ID = Asset_main.ID\n" +
                    "JOIN dbo.SAMPLING_PLAN_Asset_HOUSE sample_plan \n" +
                    "ON sample_plan.Asset_HOUSE_DETAILS_ID = Asset_house.ID\n" +
                    "WHERE Asset_main.UNIQUE_Asset_ID = 'A0" + getAssetId() + "' and is_Active = 1");

            while (getSamplingPlanRow.next()) {
                softAssert.assertEquals(getSamplingPlanRow.getInt("sampling_plan_id"), samplingPlanID, "Row not added in db");
            }

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public void AssetSettlementAPIValidationChecks(String testcaseName, int AssetId, int processingSiteId, String processingDate, String hatchDate, String messageExpected, int StatusCodeExpected) throws SQLException, IOException {
        try {
            test = extent.createTest(testcaseName);
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets/" + AssetId + "/settlements";

            System.out.println();

            int PreSettlementAuditRows = getAuditRowsCount("Select Count(id) as Rows from Asset_Settlement_Audit where Asset_id = '" + AssetId + "'");
            int PreMortalityAuditRows = getAuditRowsCount("Select Count(id) as Rows from Asset_Audit where Asset_id = '" + AssetId + "'");
            int PreCondemnationAuditRows = getAuditRowsCount("Select Count(id) as Rows from Asset_Condemnation_Audit where Asset_id = '" + AssetId + "'");

            String requestBody = "{\n" +
                    "  \"AssetId\": " + AssetId + ",\n" +
                    "  \"AssetSettlements\": [\n" +
                    "    {\n" +
                    "      \"processingSiteId\": " + processingSiteId + ",\n" +
                    "      \"processingDate\": \"" + processingDate + "\",\n" +
                    "      \"avgSoldAge\": 1,\n" +
                    "      \"numanimalsSold\": 1,\n" +
                    "      \"weeklyFarmRank\": 1,\n" +
                    "      \"historicalFarmCostVariance\": 1,\n" +
                    "      \"weeklyFarmCostVariance\": 1,\n" +
                    "      \"daysOut\": 1,\n" +
                    "      \"ageOfLitter\": 1,\n" +
                    "      \"deploymentDensity\": 1,\n" +
                    "      \"usdaPlantId\": \"1\",\n" +
                    "      \"plantLocation\": \"1\",\n" +
                    "      \"numanimalsProcessed\": 1,\n" +
                    "      \"avganimalWeightLbs\": 1,\n" +
                    "      \"avganimalWeightKgs\": 1,\n" +
                    "      \"avgDailyWeightGainLb\": 1,\n" +
                    "      \"avgDailyWeightGainKg\": 1,\n" +
                    "      \"totalWeightProcessedLb\": 1,\n" +
                    "      \"totalWeightProcessedKg\": 1,\n" +
                    "      \"totalFeedWeightLb\": 1,\n" +
                    "      \"totalFeedWeightKg\": 1,\n" +
                    "      \"fcr\": 1,\n" +
                    "      \"fcrAdj\": 1,\n" +
                    "      \"feedCostPerLivePound\": 1,\n" +
                    "      \"medicationCostPerLivePound\": 1,\n" +
                    "      \"growerCostPerLivePound\": 1,\n" +
                    "      \"livabilityPercentage\": 1,\n" +
                    "      \"overallMortalityPercentage\": 1,\n" +
                    "      \"hatchDate\": \"" + hatchDate + "\",\n" +
                    "      \"week1Mortality\": 1,\n" +
                    "      \"week2Mortality\": 2,\n" +
                    "      \"week3Mortality\": 3,\n" +
                    "      \"week4Mortality\": 4,\n" +
                    "      \"week5Mortality\": 5,\n" +
                    "      \"week6Mortality\": 6,\n" +
                    "      \"week7Mortality\": 7,\n" +
                    "      \"week8Mortality\": 8,\n" +
                    "      \"week9Mortality\": 0,\n" +
                    "      \"numanimalsDOAPlant\": 1,\n" +
                    "      \"totalWeightCondemnedLb\": 2,\n" +
                    "      \"totalWeightCondemnedKg\": 3,\n" +
                    "      \"numanimalsCondemnedWhole\": 4,\n" +
                    "      \"partsWeightCondemnedLb\": 5,\n" +
                    "      \"partsWeightCondemnedKg\": 6,\n" +
                    "      \"kCalPerPound\": 7,\n" +
                    "      \"gradeAPawsPerc\": 8,\n" +
                    "      \"airsacPercentage\": 9,\n" +
                    "      \"ipPercentage\": 10,\n" +
                    "      \"leukosisPercentage\": 11,\n" +
                    "      \"septoxPercentage\": 12,\n" +
                    "      \"tumorPercentage\": 13\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            String token = LoginAPI();
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch();

            System.out.println("Payload: " + requestBody);
            int siteID = processingSiteId;

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, StatusCodeExpected);

            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);
            test.info("Response: " + responseBody);
            softAssert.assertTrue(responseBody.contains(messageExpected), "Did not receive the expected response message");

            ResultSet getAddedSettlementRow = DB_Config_DW.getStmt().executeQuery(getAddedSettlementQuery);
            while (getAddedSettlementRow.next()) {
                softAssert.assertEquals(getAddedSettlementRow.getInt("WEEKLY_FARM_RANK"), 1, "Weekly farm rank not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("HISTORICAL_FARM_COST_VARIANCE"), "1.0000", "Histroical Farm cost variance not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("WEEKLY_FARM_COST_VARIANCE"), "1.0000", "Weekly farm variance not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("DAYS_OUT"), 1, "Days out not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("AGE_OF_LITTER"), 1, "Age of litter not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("AVG_SOLD_AGE"), 1, "Avg sold age not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("NUM_animalS_SOLD"), 1, "Num brids sold not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("deployment_DENSITY"), "1.0000", "deployment Density not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("PROCESSING_DATE"), "2023-01-02", "Processing Date not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("PROCESSING_SITE_ID"), siteID, "Processing Site Id not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("USDA_PLANT_ID"), "1", "USDA plant id not showing correct value in db");
                //  softAssert.assertEquals(getAddedSettlementRow.getInt("PLANT_LOCATION"), 1, "Plant location not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("NUM_animalS_PROCESSED"), 1, "Num animals sold not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("AVG_animal_WEIGHT_LBS"), "1.0000", "Avg animal weight lbs not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("AVG_animal_WEIGHT_KGS"), "1.0000", "Avg animal weight kgs not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("AVG_DAILY_WEIGHT_GAIN_LB"), "1.0000", "Avg daily weight gain lbs not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("AVG_DAILY_WEIGHT_GAIN_KG"), "1.0000", "Avg daily weight gains kgs not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("TOTAL_WEIGHT_PROCESSED_LB"), "1.0000", "Total weight processed lbs not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("TOTAL_WEIGHT_PROCESSED_KG"), "1.0000", "Total weight processed kg not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("TOTAL_FEED_WEIGHT_LB"), "1.0000", "Total feed weight lb not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("TOTAL_FEED_WEIGHT_KG"), "1.0000", "Total feed weight kg not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("FCR"), "1.0000", "FCR not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("FCR_ADJ"), "1.0000", "FCR Adj not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("FEED_COST_PER_LIVE_POUND"), "1.0000", "Feed cost per live pound not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("MEDICATION_COST_PER_LIVE_POUND"), "1.0000", "Medication Cost per live pound not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("GROWER_COST_PER_LIVE_POUND"), "1.0000", "Grower cost per live pound not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("LIVABILITY_PERCENTAGE"), "1.0000", "Livability percentage not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("OVERALL_MORTALITY_PERCENTAGE"), "1.0000", "Overall mobility percentage not showing correct value in db");
                //   softAssert.assertEquals(getAddedSettlementRow.getInt("Asset_ID"), getAssetId());
                softAssert.assertEquals(getAddedSettlementRow.getInt("Is_DELETED"), 0, "Is deleted not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("IS_ACTIVE"), 1, "Is active not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("IS_DATA_MIGRATED"), 0, "Is data migrated not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("HATCH_DATE"), DateUtil.getTomorrowDate(), "Hatch date not showing correct value in db");
            }

            ResultSet getAddedSettlementAuditRow = DB_Config_DW.getStmt().executeQuery("SELECT Top 1 * FROM dbo.Asset_SETTLEMENT_AUDIT WHERE Asset_ID = '" + getAssetId() + "'  order by id desc");
            while (getAddedSettlementAuditRow.next()) {
                softAssert.assertEquals(getAddedSettlementAuditRow.getInt("WEEKLY_FARM_RANK"), 1, "Weekly farm rank Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("HISTORICAL_FARM_COST_VARIANCE"), "1.0000", "Histroical Farm cost variance Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("WEEKLY_FARM_COST_VARIANCE"), "1.0000", "Weekly farm variance Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getInt("DAYS_OUT"), 1, "Days out Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getInt("AGE_OF_LITTER"), 1, "Age of litter Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getInt("AVG_SOLD_AGE"), 1, "Avg sold age Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getInt("NUM_animalS_SOLD"), 1, "Num brids sold Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("deployment_DENSITY"), "1.0000", "deployment Density Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("PROCESSING_DATE"), "2023-01-02", "Processing Date Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getInt("PROCESSING_SITE_ID"), siteID, "Processing Site Id Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("USDA_PLANT_ID"), "1", "USDA plant id Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getInt("PLANT_LOCATION"), 1, "Plant location Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getInt("NUM_animalS_PROCESSED"), 1, "Num animals sold Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("AVG_animal_WEIGHT_LBS"), "1.0000", "Avg animal weight lbs Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("AVG_animal_WEIGHT_KGS"), "1.0000", "Avg animal weight kgs Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("AVG_DAILY_WEIGHT_GAIN_LB"), "1.0000", "Avg daily weight gain lbs Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("AVG_DAILY_WEIGHT_GAIN_KG"), "1.0000", "Avg daily weight gains kgs Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("TOTAL_WEIGHT_PROCESSED_LB"), "1.0000", "Total weight processed lbs Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("TOTAL_WEIGHT_PROCESSED_KG"), "1.0000", "Total weight processed kg Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("TOTAL_FEED_WEIGHT_LB"), "1.0000", "Total feed weight lb Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("TOTAL_FEED_WEIGHT_KG"), "1.0000", "Total feed weight kg not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("FCR"), "1.0000", "FCR Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("FCR_ADJ"), "1.0000", "FCR Adj Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("FEED_COST_PER_LIVE_POUND"), "1.0000", "Feed cost per live pound Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("MEDICATION_COST_PER_LIVE_POUND"), "1.0000", "Medication Cost per live pound Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("GROWER_COST_PER_LIVE_POUND"), "1.0000", "Grower cost per live pound Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("LIVABILITY_PERCENTAGE"), "1.0000", "Livability percentage Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("OVERALL_MORTALITY_PERCENTAGE"), "1.0000", "Overall mobility percentage Audit not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementAuditRow.getString("HATCH_DATE"), DateUtil.getTomorrowDate(), "Hatch date Audit not showing correct value in db");
            }

            ResultSet getAddedMortalityRow = DB_Config_DW.getStmt().executeQuery("Select Top 1 * from Asset_Mortality where Asset_id = '" + AssetId + "' order by id desc");
            while (getAddedMortalityRow.next()) {
                softAssert.assertEquals(getAddedMortalityRow.getDouble("WEEK1_MORTALITY"), 1.0000, "WEEK1_MORTALITY not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityRow.getDouble("WEEK2_MORTALITY"), 2.0000, "WEEK2_MORTALITY not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityRow.getDouble("WEEK3_MORTALITY"), 3.0000, "WEEK3_MORTALITY not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityRow.getDouble("WEEK4_MORTALITY"), 4.0000, "WEEK4_MORTALITY not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityRow.getDouble("WEEK5_MORTALITY"), 5.0000, "WEEK5_MORTALITY not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityRow.getDouble("WEEK6_MORTALITY"), 6.0000, "WEEK6_MORTALITY not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityRow.getDouble("WEEK7_MORTALITY"), 7.0000, "WEEK7_MORTALITY not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityRow.getDouble("WEEK8_MORTALITY"), 8.0000, "WEEK8_MORTALITY not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityRow.getDouble("WEEK9_MORTALITY"), 0.0000, "WEEK9_MORTALITY not showing correct value in db");
            }

            ResultSet getAddedMortalityAuditRow = DB_Config_DW.getStmt().executeQuery("Select Top 1 * from Asset_Audit where Asset_id = '" + AssetId + "' order by id desc");
            while (getAddedMortalityAuditRow.next()) {
                softAssert.assertEquals(getAddedMortalityAuditRow.getDouble("WEEK1_MORTALITY"), 1.0000, "WEEK1_MORTALITY Audit not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityAuditRow.getDouble("WEEK2_MORTALITY"), 2.0000, "WEEK2_MORTALITY Audit not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityAuditRow.getDouble("WEEK3_MORTALITY"), 3.0000, "WEEK3_MORTALITY Audit not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityAuditRow.getDouble("WEEK4_MORTALITY"), 4.0000, "WEEK4_MORTALITY Audit not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityAuditRow.getDouble("WEEK5_MORTALITY"), 5.0000, "WEEK5_MORTALITY Audit not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityAuditRow.getDouble("WEEK6_MORTALITY"), 6.0000, "WEEK6_MORTALITY Audit not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityAuditRow.getDouble("WEEK7_MORTALITY"), 7.0000, "WEEK7_MORTALITY Audit not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityAuditRow.getDouble("WEEK8_MORTALITY"), 8.0000, "WEEK8_MORTALITY Audit not showing correct value in db");
                softAssert.assertEquals(getAddedMortalityAuditRow.getDouble("WEEK9_MORTALITY"), 0.0000, "WEEK9_MORTALITY Audit not showing correct value in db");
            }

            ResultSet getAddedCondemnationRow = DB_Config_DW.getStmt().executeQuery("Select Top 1 * from Asset_Condemnation where Asset_id = '" + AssetId + "' order by id desc");
            while (getAddedCondemnationRow.next()) {
                softAssert.assertEquals(getAddedCondemnationRow.getDouble("TOTAL_WEIGHT_CONDEMNED_LB"), 2.0000, "TOTAL_WEIGHT_CONDEMNED_LB not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationRow.getDouble("TOTAL_WEIGHT_CONDEMNED_KG"), 3.0000, "TOTAL_WEIGHT_CONDEMNED_KG not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationRow.getInt("NUM_animalS_CONDEMNED_WHOLE"), 4, "NUM_animalS_CONDEMNED_WHOLE not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationRow.getDouble("PARTS_WEIGHT_CONDEMNED_LB"), 5.0000, "PARTS_WEIGHT_CONDEMNED_LB not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationRow.getDouble("PARTS_WEIGHT_CONDEMNED_KG"), 6.0000, "Weekly farm rank not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationRow.getDouble("kCAL_PER_POUND"), 7.0000, "PARTS_WEIGHT_CONDEMNED_KG not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationRow.getDouble("A_GRADE_PAWS_PERC"), 8.0000, "A_GRADE_PAWS_PERC not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationRow.getDouble("AIRSAC_PERCENTAGE"), 9.0000, "AIRSAC_PERCENTAGE not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationRow.getDouble("IP_PERCENTAGE"), 10.0000, "IP_PERCENTAGE not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationRow.getDouble("LEUKOSIS_PERCENTAGE"), 11.0000, "LEUKOSIS_PERCENTAGE not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationRow.getDouble("SEPTOX_PERCENTAGE"), 12.0000, "SEPTOX_PERCENTAGE not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationRow.getDouble("TUMOR_PERCENTAGE"), 13.0000, "TUMOR_PERCENTAGE not showing correct value in db");
            }

            ResultSet getAddedCondemnationAuditRow = DB_Config_DW.getStmt().executeQuery("Select Top 1 * from Asset_Condemnation_Audit where Asset_id = '" + AssetId + "'  order by id desc");
            while (getAddedCondemnationAuditRow.next()) {
                softAssert.assertEquals(getAddedCondemnationAuditRow.getDouble("TOTAL_WEIGHT_CONDEMNED_LB"), 2.0000, "TOTAL_WEIGHT_CONDEMNED_LB Audit not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationAuditRow.getDouble("TOTAL_WEIGHT_CONDEMNED_KG"), 3.0000, "TOTAL_WEIGHT_CONDEMNED_KG Audit not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationAuditRow.getInt("NUM_animalS_CONDEMNED_WHOLE"), 4, "PARTS_WEIGHT_CONDEMNED_LB Audit not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationAuditRow.getDouble("PARTS_WEIGHT_CONDEMNED_LB"), 5.0000, "Weekly farm rank Audit not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationAuditRow.getDouble("PARTS_WEIGHT_CONDEMNED_KG"), 6.0000, "PARTS_WEIGHT_CONDEMNED_KG Audit not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationAuditRow.getDouble("kCAL_PER_POUND"), 7.0000, "A_GRADE_PAWS_PERC Audit not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationAuditRow.getDouble("A_GRADE_PAWS_PERC"), 8.0000, "Weekly farm rank Audit not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationAuditRow.getDouble("AIRSAC_PERCENTAGE"), 9.0000, "AIRSAC_PERCENTAGE Audit not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationAuditRow.getDouble("IP_PERCENTAGE"), 10.0000, "LEUKOSIS_PERCENTAGE Audit not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationAuditRow.getDouble("LEUKOSIS_PERCENTAGE"), 11.0000, "Weekly farm rank Audit not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationAuditRow.getDouble("SEPTOX_PERCENTAGE"), 12.0000, "SEPTOX_PERCENTAGE Audit not showing correct value in db");
                softAssert.assertEquals(getAddedCondemnationAuditRow.getDouble("TUMOR_PERCENTAGE"), 13.0000, "TUMOR_PERCENTAGE Audit not showing correct value in db");
            }

            int PostSettlementAuditRows = getAuditRowsCount("Select Count(id) as Rows from Asset_Settlement_Audit where Asset_id = '" + AssetId + "'");
            int PostMortalityAuditRows = getAuditRowsCount("Select Count(id) as Rows from Asset_Audit where Asset_id = '" + AssetId + "'");
            int PostCondemnationAuditRows = getAuditRowsCount("Select Count(id) as Rows from Asset_Condemnation_Audit where Asset_id = '" + AssetId + "'");

            softAssert.assertEquals(PostSettlementAuditRows, PreSettlementAuditRows + 1, "New Row not added in Audit for Settlement");
            softAssert.assertEquals(PostMortalityAuditRows, PreMortalityAuditRows + 1, "New Row not added in Audit for Mortality");
            softAssert.assertEquals(PostCondemnationAuditRows, PreCondemnationAuditRows + 1, "New Row not added in Audit for Condemnation");

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }

    public static void getSampleFromPatho_1Data() throws IOException {
        try {
            test = extent.createTest("IE-10656: Verify user can get sample from Patho_1 data by running on \"/Patho_1-data/samples/{sampleID}\" API and verify the response from db");
            SoftAssert softAssert = new SoftAssert();
            RequestSpecification request_getSample = RestAssured.given();
            request_getSample.header("Content-Type", "application/json");
            request_getSample.header("Authorization", "bearer " + LoginAPI());
            Response response = request_getSample.get(Constants.config.webAPI() + "/AssetManagement/Patho_1-data/samples/" + getSampleID());

            int code = response.getStatusCode();
            softAssert.assertEquals(code, 200, "Did not return 200 status code");

            String response_data = response.asString();
            System.out.println(response_data);
            test.info("Response: " + response_data);

            String sampleID = getSampleID();

            ResultSet getSampleIDRow = DB_Config_DW.getStmt().executeQuery("Select TOP 1 * from Patho_1_DATA where sample_ID = '" + getSampleID() + "'");


            DateTimeFormatter expectedFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            DateTimeFormatter actualFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

            while (getSampleIDRow.next()) {
                JsonPath jsonPathEvaluator = response.jsonPath();

                // Parse the JSON string
                JSONObject jsonResponse = new JSONObject(response_data);

                // Extract the value of sampleId
                JSONArray responseBody = jsonResponse.getJSONArray("responseBody");
                JSONObject firstObject = responseBody.getJSONObject(0);
                String sampleId = firstObject.getString("sampleId");
                String collectionSiteId = firstObject.getString("collectionSiteId");
                String collectionDate = firstObject.getString("collectionDate");

                softAssert.assertEquals(sampleId, sampleID);
                softAssert.assertEquals(collectionSiteId, getSampleIDRow.getString("COLLECTION_SITE_ID"));

                LocalDate expectedDate = LocalDate.parse(getSampleIDRow.getString("COLLECTION_DATE"), expectedFormatter);
                LocalDate actualDate = LocalDate.parse(collectionDate, actualFormatter);
                softAssert.assertEquals(actualDate, expectedDate);

                softAssert.assertAll();
                test.pass("Test Passed Successfully");
                saveResultNoScreenshot(ITestResult.SUCCESS, null);
            }
        } catch (AssertionError | SQLException ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public static void getInvalidSampleFromPatho_1Data() throws IOException {
        try {
            test = extent.createTest("IE-10656: Verify user does not get sample from Patho_1 data by running on \"/Patho_1-data/samples/{sampleID}\" API using invalid sample id");
            SoftAssert softAssert = new SoftAssert();
            RequestSpecification request_getSample = RestAssured.given();
            request_getSample.header("Content-Type", "application/json");
            request_getSample.header("Authorization", "bearer " + LoginAPI());
            Response response = request_getSample.get(Constants.config.webAPI() + "/AssetManagement/Patho_1-data/samples/invalidSampleID");

            int code = response.getStatusCode();
            softAssert.assertEquals(code, 200, "Did not return 200 status code");

            String response_data = response.asString();
            System.out.println(response_data);
            test.info("Response: " + response_data);
            softAssert.assertEquals(response_data, "{\"title\":\"Success\",\"status\":200,\"logTraceId\":\"\",\"systemErrors\":[],\"responseBody\":[],\"validationErrors\":[]}");

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }

    public static int getSampleIDAuditCount() throws SQLException {
        ResultSet getSampleIDAuditCount = DB_Config_DW.getStmt().executeQuery("Select Count(ID) as ID from Patho_1_DATA_AUDIT where sample_ID = '" + getSampleID() + "'");

        int count = 0;
        while (getSampleIDAuditCount.next()) {
            count = getSampleIDAuditCount.getInt("ID");
            System.out.println(count);
        }
        return count;
    }


    public void updateSampleInPatho_1Data() throws SQLException, IOException {
        try {
            test = extent.createTest("IE:10661 : Update sample for Patho_1 Data and verify the response from db");
            SoftAssert softAssert = new SoftAssert();

            String sampleID = getSampleID();
            System.out.println(sampleID);
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Patho_1-data/samples/" + sampleID;

            int getPreAuditCount = getSampleIDAuditCount();

            String requestBody = "{\n" +
                    "  \"sampleId\": \"" + sampleID + "\",\n" +
                    "  \"collectionSiteId\": " + getHouseSiteIDAssignedToUser(getAssetId()) + ",\n" +
                    "  \"collectionDate\": \"" + dateYMD + "\",\n" +
                    "  \"receivedDate\": \"" + getTomorrowDate() + "\"\n" +
                    "}";

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .put();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 200);

            String response_data = response.asString();
            System.out.println("Response: " + response_data);
            softAssert.assertEquals(response_data, "{\"title\":\"Success\",\"status\":200,\"logTraceId\":\"\",\"systemErrors\":[],\"responseBody\":1,\"validationErrors\":[]}");
            test.info("Response: " + response_data);

            ResultSet getSampleIDRow = DB_Config_DB.getStmt().executeQuery("Select TOP 1 * from et.Patho_1_DATA where sample_ID = '" + sampleID + "'");

            DateTimeFormatter expectedFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

            while (getSampleIDRow.next()) {

                LocalDate expectedCollectionDate = LocalDate.parse(getSampleIDRow.getString("COLLECTION_DATE"), expectedFormatter);
                LocalDate expectedReceivedDate = LocalDate.parse(getSampleIDRow.getString("METADATA_DATE_RECIEVED"), expectedFormatter);


                System.out.println(dateYMD + "--->" + expectedCollectionDate.toString().substring(0, 10));
                System.out.println(getTomorrowDate() + "-->" + expectedReceivedDate.toString().substring(0, 10));

                softAssert.assertEquals(dateYMD, expectedCollectionDate.toString().substring(0, 10));
                softAssert.assertEquals(getTomorrowDate(), expectedReceivedDate.toString().substring(0, 10));

                int getPostAuditCount = getSampleIDAuditCount();

                softAssert.assertEquals(getPostAuditCount, getPreAuditCount + 1, "Row not added in audit log");

                softAssert.assertAll();
                test.pass("Test Passed Successfully");
                saveResultNoScreenshot(ITestResult.SUCCESS, null);

            }
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public void updateSampleInPatho_1DataWithInvalidSiteID() throws SQLException, IOException {
        try {
            test = extent.createTest("IE:10661 : Update sample for Patho_1 Data and verify the response from db");
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Patho_1-data/samples/" + getSampleID();

            String requestBody = "{\n" +
                    "  \"sampleId\": \"" + getSampleID() + "\",\n" +
                    "  \"collectionSiteId\": " + getFarmIDNotAssignedtoUser() + ",\n" +
                    "  \"collectionDate\": \"" + dateYMD + "\",\n" +
                    "  \"receivedDate\": \"" + getTomorrowDate() + "\"\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .put();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 400);

            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);

            test.info("Response: " + responseBody);


            softAssert.assertTrue(responseBody.contains("\"type\":\"USER_DONT_HAVE_ACCESS_TO_SITE\",\"title\":\"User don't have access to site.\",\"detail\":\"User don't have access to House\""), "Did not receive the expected response message");
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);


        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public void AddProgram(int animalSizeId, String animalSizeName) throws SQLException, IOException {
        try {
            test = extent.createTest("IE:10257/10258/10260/10261/10262 : Create Asset with Valid Data");
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets";

            String requestBody =
                    "\"AssetPrograms\": [\n" +
                            "{             \n" +
                            "\"programId\": \"1\"        \n" +
                            "}    \n" +
                            "]\n" +
                            "}";

            System.out.println(requestBody);

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 200);

            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);
            test.info("Response: " + responseBody);

            if (statusCode == 200) {
                ResultSet getCreatedAsset = DB_Config_DW.getStmt().executeQuery(getCreatedAssetQuery + responseBody);
                boolean hasRow = getCreatedAsset.next();
                softAssert.assertEquals(hasRow, true, "Created Asset did not save in database");
            } else {
                softAssert.assertTrue(false, "Query was not executed because the api did not return 200 status code");
            }
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public static void returnIDNOtAssigned() {

        // List to store orgnIds
        List<String> orgnIds = new ArrayList<>();

        String DB_URL = "jdbc:sqlserver://Project123-asql-001.database.windows.net;databaseName=" + config.ie_database_db() + ";user=" + DB_UserName + ";Password=" + DB_Password;

        // Execute stored procedure to get siteIds
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_UserName, DB_Password);
             CallableStatement cs = connection.prepareCall("{call spGetAllComplexNotAssigned(?)}")) {
            cs.setInt(1, 406);
            ResultSet resultSet = cs.executeQuery();

            while (resultSet.next()) {
                String siteId = resultSet.getString("siteId");

                // Execute query to get orgnId for each siteId
                try (PreparedStatement ps = connection.prepareStatement("SELECT orgnID FROM site WHERE siteId = ?")) {
                    ps.setString(1, siteId);
                    ResultSet orgnResult = ps.executeQuery();

                    if (orgnResult.next()) {
                        String orgnId = orgnResult.getString("orgnID");
                        orgnIds.add(orgnId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Check if there are any rows for each orgnId
        for (String orgnId : orgnIds) {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_UserName, DB_Password);
                 PreparedStatement ps = connection.prepareStatement("SELECT * FROM site WHERE orgnId = ? AND siteTypeId = '7'")) {
                ps.setString(1, orgnId);
                ResultSet result = ps.executeQuery();

                if (result.next()) {
                    // Rows found for the orgnId
                    System.out.println("Rows found for orgnId: " + orgnId);
                } else {
                    // No rows found for the orgnId
                    System.out.println("No rows found for orgnId: " + orgnId);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void createAssetWithInvalidProductionType(int animalSizeId, String animalSizeName, String deploymentDate, int productionTypeID) throws SQLException, IOException {
        try {
            test = extent.createTest("IE:12362: Verify validation check on entering invalid production type id while creating a Asset using createAsset API");
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Assets";

            String requestBody = "{\n" +
                    "  \"integratorAssetId\": \"" + dateYYYYMMDD + "_101\",\n" +
                    "  \"marketingProgram\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Conventional\"\n" +
                    "  },\n" +
                    "  \"farm\": {\n" +
                    "    \"siteId\": " + getHouseParentSiteId() + "\n" +
                    "  },\n" +
                    "  \"productionType\": {\n" +
                    "    \"id\": " + productionTypeID + ",\n" +
                    "    \"name\": \"Broiler\"\n" +
                    "  },\n" +
                    "  \"animalSize\": {\n" +
                    "    \"id\": " + animalSizeId + ",\n" +
                    "    \"name\": \"" + animalSizeName + "\"\n" +
                    "  },\n" +
                    "  \"animalSex\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"male\"\n" +
                    "  },\n" +
                    "  \"Assetdeployments\": [\n" +
                    "    {\n" +
                    "      \"house\": {\n" +
                    "        \"siteId\": " + getHouseIDAssignedToUser() + "\n" +
                    "      },\n" +
                    "      \"deploymentDate\": \"" + deploymentDate + "\",\n" +
                    "      \"estimatedProcessingDate\": \"\",\n" +
                    "      \"animalsPlaced\": 0,\n" +
                    "      \"AssetSamplingPlans\": [\n" +
                    "     \n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"AssetPrograms\": [\n" +
                    "  ],\n" +
                    "  \"AssetSettlements\": [\n" +
                    "  ]\n" +
                    "}";


            System.out.println(requestBody);

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 400);

            String responseBody = response.getBody().asString();
            System.out.println("Response: " + responseBody);
            test.info("Response: " + responseBody);
            softAssert.assertTrue(responseBody.contains("[{\"type\":\"PRODUCTION_TYPE_NOT_EXISTS\",\"title\":\"Production Type is invalid.\""), "Did not receive the expected response message");

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }

    public void updatePatho_1QuantificationDataWithValidSampleID() throws SQLException, IOException {
        try {
            test = extent.createTest("IE:13244 : Update Coccdia Quantification Data with Valid Sample ID and verify the response from db");
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Patho_1-quantification-data/samples/" + getSampleID();

            String requestBody = "{\n" +
                    "  \"collectedBy\": \"xxxxxx\",\n" +
                    "  \"collectionDate\": \"" + dateYMD + "\",\n" +
                    "  \"collectionSiteId\": " + getHouseIDAssignedToUser() + ",\n" +
                    "  \"customerSampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"farmId\": 101,\n" +
                    "  \"AssetAge\": \"100\",\n" +
                    "  \"houseRegion\": \"255152\",\n" +
                    "  \"laboratorySampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"line\": \"2\",\n" +
                    "  \"organization\": \"1\",\n" +
                    "  \"plant\": \"2\",\n" +
                    "  \"receivedDate\": \"" + getTomorrowDate() + "\",\n" +
                    "  \"sampleId\": \"" + getSampleID() + "\",\n" +
                    "  \"sampleMatrix\": \"Vaccine-1\"\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 200);

            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            System.out.println(jsonPath.getString("responseBody"));
            String sampleID = jsonPath.getString("responseBody");

            softAssert.assertEquals(sampleID, getSampleID(), "SampleID not shown in response body");

            ResultSet getSampleIDRow = DB_Config_DW.getStmt().executeQuery("Select Top 1 * from Patho_1_DATA where user_id = '"+getUsersId()+"' and SAMPLE_ID = '" + getSampleID() + "'");
            while (getSampleIDRow.next()) {
                softAssert.assertEquals(getSampleIDRow.getString("COLLECTED_BY"), "xxxxxx", "Collected by column not showing correct data");
                softAssert.assertTrue(getSampleIDRow.getString("COLLECTION_DATE").contains(dateYMD), "Collection date not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getInt("SITE_ID"), getHouseIDAssignedToUser(), "Site Id not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("CUSTOMER_SAMPLE_ID"), dateYYYYMMDD + "-100", "Customer sample id not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("AGE_OF_animal_DAYS"), "100", "Asset Age not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("HOUSE_REGION"), "255152", "House Region not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("LAB_SAMPLE_ID"), dateYYYYMMDD + "-100", "Lab Sample Id not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("LINE"), "2", "Line not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("CUSTOMER"), "1", "Organization not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("PLANT"), "2", "Plant not showing correct data");
                softAssert.assertTrue(getSampleIDRow.getString("METADATA_DATE_RECIEVED").contains(getTomorrowDate()), "Date Received not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("SAMPLE_MATRIX"), "Vaccine-1", "Sample Matrix not showing correct data");
            }

            ResultSet getSampleIDAuditRow = DB_Config_DW.getStmt().executeQuery("Select Top 1 * from Patho_1_DATA_AUDIT where user_id = '"+getUsersId()+"' and SAMPLE_ID = '" + getSampleID() + "' order by id desc");
            while (getSampleIDAuditRow.next()) {
                softAssert.assertEquals(getSampleIDAuditRow.getString("COLLECTED_BY"), "xxxxxx", "Collected by column not showing correct data in audit");
                softAssert.assertTrue(getSampleIDAuditRow.getString("COLLECTION_DATE").contains(dateYMD), "Collection date not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getInt("SITE_ID"), getHouseIDAssignedToUser(), "Site Id not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("CUSTOMER_SAMPLE_ID"), dateYYYYMMDD + "-100", "Customer sample id not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("AGE_OF_animal_DAYS"), "100", "Asset Age not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("HOUSE_REGION"), "255152", "House Region not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("LAB_SAMPLE_ID"), dateYYYYMMDD + "-100", "Lab Sample Id not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("LINE"), "2", "Line not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("CUSTOMER"), "1", "Organization not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("PLANT"), "2", "Plant not showing correct data in audit");
                softAssert.assertTrue(getSampleIDAuditRow.getString("METADATA_DATE_RECIEVED").contains(getTomorrowDate()), "Date Received not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("SAMPLE_MATRIX"), "Vaccine-1", "Sample Matrix not showing correct data in audit");
            }

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);


        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }

    public void updatePatho_1QuantificationDataValidations(String testcaseTitle, String sampleId, int siteId, String AssetAge, String collectionDate, String receivedDate, String errorMessage) throws SQLException, IOException {
        try {
            test = extent.createTest(testcaseTitle);
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/Patho_1-quantification-data/samples/" + getSampleID();

            String requestBody = "{\n" +
                    "  \"collectedBy\": \"xxxxxx\",\n" +
                    "  \"collectionDate\": \"" + collectionDate + "\",\n" +
                    "  \"collectionSiteId\": " + siteId + ",\n" +
                    "  \"customerSampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"farmId\": 1000,\n" +
                    "  \"AssetAge\": \""+AssetAge+"\",\n" +
                    "  \"houseRegion\": \"255152\",\n" +
                    "  \"laboratorySampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"line\": \"2\",\n" +
                    "  \"organization\": \"1\",\n" +
                    "  \"plant\": \"2\",\n" +
                    "  \"receivedDate\": \"" + getTomorrowDate() + "\",\n" +
                    "  \"sampleId\": \"" + sampleId + "\",\n" +
                    "  \"sampleMatrix\": \"Vaccine-1\"\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 400);

            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            System.out.println("ResponseBody: "+jsonPath.getString("validationErrors"));

            softAssert.assertTrue(responseBody.contains(errorMessage), "Did not receive the expected response message");

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public void updateClostridiumQuantificationDataWithValidSampleID() throws SQLException, IOException {
        try {
            test = extent.createTest("IE:13251/13255/13256: Update Clostridium Quantification Data with Valid Sample ID and verify the response from db");
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/clostridium-quantification-data/samples/" + getClostridiumSampleID();

            String requestBody = "{\n" +
                    "  \"collectedBy\": \"xxxxxx\",\n" +
                    "  \"collectionDate\": \"" + dateYMD + "\",\n" +
                    "  \"collectionSiteId\": " + getHouseIDAssignedToUser() + ",\n" +
                    "  \"customerSampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"farmId\": 1000,\n" +
                    "  \"AssetAge\": \"100\",\n" +
                    "  \"houseRegion\": \"255152\",\n" +
                    "  \"laboratorySampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"line\": \"2\",\n" +
                    "  \"organization\": \"1\",\n" +
                    "  \"plant\": \"2\",\n" +
                    "  \"receivedDate\": \"" + getTomorrowDate() + "\",\n" +
                    "  \"sampleId\": \"" + getClostridiumSampleID() + "\",\n" +
                    "  \"sampleMatrix\": \"Vaccine-1\"\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 200);

            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            System.out.println(jsonPath.getString("responseBody"));
            String sampleID = jsonPath.getString("responseBody");

            softAssert.assertEquals(sampleID, getClostridiumSampleID(), "SampleID not shown in response body");

            ResultSet getSampleIDRow = DB_Config_DW.getStmt().executeQuery("Select Top 1 * from Clostridium_DATA where user_id = '"+getUsersId()+"' and SAMPLE_ID = '" + getClostridiumSampleID() + "'");
            while (getSampleIDRow.next()) {
                softAssert.assertEquals(getSampleIDRow.getString("SAMPLED_BY"), "xxxxxx", "Collected by column not showing correct data");
                softAssert.assertTrue(getSampleIDRow.getString("COLLECTION_DATE").contains(dateYMD), "Collection date not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getInt("HOUSE_INTERNAL_ID"), getHouseIDAssignedToUser(), "Site Id not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("CUSTOMER_SAMPLE_ID"), dateYYYYMMDD + "-100", "Customer sample id not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("Asset_AGE"), "100", "Asset Age not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("HOUSE_REGION"), "255152", "House Region not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("LAB_SAMPLE_ID"), dateYYYYMMDD + "-100", "Lab Sample Id not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("LINE"), "2", "Line not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("CUSTOMER"), "1", "Organization not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("PLANT"), "2", "Plant not showing correct data");
                softAssert.assertTrue(getSampleIDRow.getString("DATE_RECEIVED").contains(getTomorrowDate()), "Date Received not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("SAMPLE_MATRIX"), "Vaccine-1", "Sample Matrix not showing correct data");
            }

            ResultSet getSampleIDAuditRow = DB_Config_DW.getStmt().executeQuery("Select Top 1 * from Clostridium_DATA_AUDIT where user_id = '"+getUsersId()+"' and SAMPLE_ID = '" + getClostridiumSampleID() + "' order by id desc");
            while (getSampleIDAuditRow.next()) {
                softAssert.assertEquals(getSampleIDAuditRow.getString("SAMPLED_BY"), "xxxxxx", "Collected by column not showing correct data in audit");
                softAssert.assertTrue(getSampleIDAuditRow.getString("COLLECTION_DATE").contains(dateYMD), "Collection date not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getInt("HOUSE_INTERNAL_ID"), getHouseIDAssignedToUser(), "Site Id not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("CUSTOMER_SAMPLE_ID"), dateYYYYMMDD + "-100", "Customer sample id not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("Asset_AGE"), "100", "Asset Age not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("HOUSE_REGION"), "255152", "House Region not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("LAB_SAMPLE_ID"), dateYYYYMMDD + "-100", "Lab Sample Id not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("LINE"), "2", "Line not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("CUSTOMER"), "1", "Organization not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("PLANT"), "2", "Plant not showing correct data in audit");
                softAssert.assertTrue(getSampleIDAuditRow.getString("DATE_RECEIVED").contains(getTomorrowDate()), "Date Received not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("SAMPLE_MATRIX"), "Vaccine-1", "Sample Matrix not showing correct data in audit");
            }
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }

    public void updateClostridiumQuantificationDataValidations(String testcaseTitle, String sampleId, int siteId, String AssetAge, String collectionDate, String receivedDate, String errorMessage) throws SQLException, IOException {
        try {
            test = extent.createTest(testcaseTitle);
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/clostridium-quantification-data/samples/" + getClostridiumSampleID();

            String requestBody = "{\n" +
                    "  \"collectedBy\": \"xxxxxx\",\n" +
                    "  \"collectionDate\": \"" + collectionDate + "\",\n" +
                    "  \"collectionSiteId\": " + siteId + ",\n" +
                    "  \"customerSampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"farmId\": 1000,\n" +
                    "  \"AssetAge\": \"" + AssetAge + "\",\n" +
                    "  \"houseRegion\": \"255152\",\n" +
                    "  \"laboratorySampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"line\": \"2\",\n" +
                    "  \"organization\": \"1\",\n" +
                    "  \"plant\": \"2\",\n" +
                    "  \"receivedDate\": \"" + receivedDate + "\",\n" +
                    "  \"sampleId\": \"" + sampleId + "\",\n" +
                    "  \"sampleMatrix\": \"Vaccine-1\"\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 400);

            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            System.out.println("ResponseBody: " + jsonPath.getString("validationErrors"));
            softAssert.assertTrue(responseBody.contains(errorMessage), "Did not receive the expected response message");
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public void updateTVBQuantificationDataWithValidSampleID() throws SQLException, IOException {
        try {
            test = extent.createTest("IE:13258/13263/13264: Update TVB Quantification Data with Valid Sample ID and verify the response from db");
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/tvb-quantification-data/samples/" + getTVBSampleID();

            String requestBody = "{\n" +
                    "  \"collectedBy\": \"xxxxxx\",\n" +
                    "  \"collectionDate\": \"" + dateYMD + "\",\n" +
                    "  \"collectionSiteId\": " + getHouseIDAssignedToUser() + ",\n" +
                    "  \"customerSampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"farmId\": 101,\n" +
                    "  \"AssetAge\": \"100\",\n" +
                    "  \"houseRegion\": \"255152\",\n" +
                    "  \"laboratorySampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"line\": \"2\",\n" +
                    "  \"organization\": \"1\",\n" +
                    "  \"plant\": \"2\",\n" +
                    "  \"receivedDate\": \"" + getTomorrowDate() + "\",\n" +
                    "  \"sampleId\": \"" + getTVBSampleID() + "\",\n" +
                    "  \"sampleMatrix\": \"Vaccine-1\"\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 200);

            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            System.out.println(jsonPath.getString("responseBody"));
            String sampleID = jsonPath.getString("responseBody");

            softAssert.assertEquals(sampleID, getTVBSampleID(), "SampleID not shown in response body");

            ResultSet getSampleIDRow = DB_Config_DW.getStmt().executeQuery("Select Top 1 * from TVB_DATA where user_id = '"+getUsersId()+"' and SAMPLE_ID = '" + getTVBSampleID() + "'");
            while (getSampleIDRow.next()) {
                System.out.println(">>>>"+getSampleIDRow.getString("SAMPLED_BY"));
                softAssert.assertEquals(getSampleIDRow.getString("SAMPLED_BY"), "xxxxxx", "Collected by column not showing correct data");
                softAssert.assertTrue(getSampleIDRow.getString("COLLECTION_DATE").contains(dateYMD), "Collection date not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getInt("HOUSE_INTERNAL_ID"), getHouseIDAssignedToUser(), "Site Id not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("CUSTOMER_SAMPLE_ID"), dateYYYYMMDD + "-100", "Customer sample id not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("Asset_AGE"), "100", "Asset Age not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("HOUSE_REGION"), "255152", "House Region not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("LAB_SAMPLE_ID"), dateYYYYMMDD + "-100", "Lab Sample Id not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("LINE"), "2", "Line not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("CUSTOMER"), "1", "Organization not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("PLANT"), "2", "Plant not showing correct data");
                softAssert.assertTrue(getSampleIDRow.getString("DATE_RECEIVED").contains(getTomorrowDate()), "Date Received not showing correct data");
                softAssert.assertEquals(getSampleIDRow.getString("SAMPLE_MATRIX"), "Vaccine-1", "Sample Matrix not showing correct data");
            }

            ResultSet getSampleIDAuditRow = DB_Config_DW.getStmt().executeQuery("Select Top 1 * from TVB_DATA_AUDIT where user_id = '"+getUsersId()+"' and SAMPLE_ID = '" + getTVBSampleID() + "' order by id desc");
            while (getSampleIDAuditRow.next()) {
                softAssert.assertEquals(getSampleIDAuditRow.getString("SAMPLED_BY"), "xxxxxx", "Collected by column not showing correct data in audit");
                softAssert.assertTrue(getSampleIDAuditRow.getString("COLLECTION_DATE").contains(dateYMD), "Collection date not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getInt("HOUSE_INTERNAL_ID"), getHouseIDAssignedToUser(), "Site Id not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("CUSTOMER_SAMPLE_ID"), dateYYYYMMDD + "-100", "Customer sample id not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("Asset_AGE"), "100", "Asset Age not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("HOUSE_REGION"), "255152", "House Region not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("LAB_SAMPLE_ID"), dateYYYYMMDD + "-100", "Lab Sample Id not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("LINE"), "2", "Line not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("CUSTOMER"), "1", "Organization not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("PLANT"), "2", "Plant not showing correct data in audit");
                softAssert.assertTrue(getSampleIDAuditRow.getString("DATE_RECEIVED").contains(getTomorrowDate()), "Date Received not showing correct data in audit");
                softAssert.assertEquals(getSampleIDAuditRow.getString("SAMPLE_MATRIX"), "Vaccine-1", "Sample Matrix not showing correct data in audit");
            }
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }

    public void updateTVBQuantificationDataValidations(String testcaseTitle, String sampleId, int siteId, String AssetAge, String collectionDate, String receivedDate, String errorMessage) throws SQLException, IOException {
        try {
            test = extent.createTest(testcaseTitle);
            SoftAssert softAssert = new SoftAssert();
            RestAssured.baseURI = Constants.config.webAPI() + "/AssetManagement/tvb-quantification-data/samples/" + getTVBSampleID();

            String requestBody = "{\n" +
                    "  \"collectedBy\": \"xxxxxx\",\n" +
                    "  \"collectionDate\": \"" + collectionDate + "\",\n" +
                    "  \"collectionSiteId\": " + siteId + ",\n" +
                    "  \"customerSampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"farmId\": 1000,\n" +
                    "  \"AssetAge\": \""+AssetAge+"\",\n" +
                    "  \"houseRegion\": \"255152\",\n" +
                    "  \"laboratorySampleId\": \"" + dateYYYYMMDD + "-100\",\n" +
                    "  \"line\": \"2\",\n" +
                    "  \"organization\": \"1\",\n" +
                    "  \"plant\": \"2\",\n" +
                    "  \"receivedDate\": \"" + receivedDate + "\",\n" +
                    "  \"sampleId\": \"" + sampleId + "\",\n" +
                    "  \"sampleMatrix\": \"Vaccine-1\"\n" +
                    "}";

            System.out.println(requestBody);

            String token = LoginAPI(); // Replace with the actual token
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch();

            int statusCode = response.getStatusCode();
            softAssert.assertEquals(statusCode, 400);

            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            System.out.println("ResponseBody: "+jsonPath.getString("validationErrors"));

            softAssert.assertTrue(responseBody.contains(errorMessage), "Did not receive the expected response message");
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError ex) {
            ex.printStackTrace();
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }
}




