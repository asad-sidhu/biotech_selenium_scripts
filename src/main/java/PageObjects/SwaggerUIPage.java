package PageObjects;

import MiscFunctions.DB_Config_DW;
import MiscFunctions.DateUtil;
import MiscFunctions.Methods;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Config.BaseTest.saveResult;
import static MiscFunctions.DateUtil.dateYMD;
import static MiscFunctions.DateUtil.getTomorrowDate;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Queries.*;
import static PageObjects.WebApiPage.*;
import static PageObjects.WebApiPage.getProcessingSiteIDAssignedToUserOld;

public class SwaggerUIPage {

    public static By authorizeButton = By.cssSelector(".authorize");
    public static By autorizeInputField = By.cssSelector(".wrapper input");
    public static By autorizeTokenButton = By.cssSelector(".authorize.button");
    public static By autorizeTokenCloseButton = By.cssSelector(".btn-done");

    public static String loginApiID = "#operations-AdminManagement-post_api_v1_AdminManagement_Login";
    public static By loginApiExpand = By.cssSelector(loginApiID + " .opblock-summary-path");
    public static By loginApiTryOutButton = By.cssSelector(loginApiID + " .try-out__btn");
    public static By loginApiInputField = By.cssSelector(loginApiID + " .body-param__text");
    public static By loginApiExecuteButton = By.cssSelector(loginApiID + " .execute");
    public static By loginApiResponseBody = By.cssSelector(loginApiID + " .highlight-code");
    public static By loginApiResponseCodeStatus = By.cssSelector(loginApiID + " .response-col_status");
    public static By loginApiResponseCodeDescription = By.cssSelector(loginApiID + " .response_current");

    public static String getAssetApiID = "#operations-AssetManagement-get_api_v1_AssetManagement_Assets__AssetId_";
    public static By getAssetApiExpand = By.cssSelector(getAssetApiID + " .opblock-summary-path");
    public static By getAssetApiTryOutButton = By.cssSelector(getAssetApiID + " .try-out__btn");
    public static By getAssetApiInputField = By.cssSelector(getAssetApiID + " input");
    public static By getAssetApiExecuteButton = By.cssSelector(getAssetApiID + " .execute");
    public static By getAssetApiResponseBody = By.cssSelector(getAssetApiID + " .highlight-code");
    public static By getAssetApiResponseCodeStatus = By.cssSelector(getAssetApiID + " .response-col_status");
    public static By getAssetApiResponseCodeDescription = By.cssSelector(getAssetApiID + " .response_current");

    public static String createAssetApiID = "#operations-AssetManagement-post_api_v1_AssetManagement_Assets";
    public static By createAssetApiExpand = By.cssSelector(createAssetApiID + " .opblock-summary-path");
    public static By createAssetApiTryOutButton = By.cssSelector(createAssetApiID + " .try-out__btn");
    public static By createAssetApiInputField = By.cssSelector(createAssetApiID + " .body-param__text");
    public static By createAssetApiExecuteButton = By.cssSelector(createAssetApiID + " .execute");
    public static By createAssetApiResponseBody = By.cssSelector(createAssetApiID + " .highlight-code");
    public static By createAssetApiResponseCodeStatus = By.cssSelector(createAssetApiID + " .response-col_status");
    public static By createAssetApiResponseCodeDescription = By.cssSelector(createAssetApiID + " .response_current");

    public static String createAssetSettlementApiID = "#operations-AssetManagement-patch_api_v1_AssetManagement_Assets__AssetId__settlements";
    public static By createAssetSettlementApiExpand = By.cssSelector(createAssetSettlementApiID + " .opblock-summary-path");
    public static By createAssetSettlementApiTryOutButton = By.cssSelector(createAssetSettlementApiID + " .try-out__btn");
    public static By createAssetSettlementApiAssetIDInputField = By.cssSelector(createAssetSettlementApiID + " input");
    public static By createAssetSettlementApiInputField = By.cssSelector(createAssetSettlementApiID + " .body-param__text");
    public static By createAssetSettlementApiExecuteButton = By.cssSelector(createAssetSettlementApiID + " .execute");
    public static By createAssetSettlementApiResponseBody = By.cssSelector(createAssetSettlementApiID + " .highlight-code");
    public static By createAssetSettlementApiResponseCodeStatus = By.cssSelector(createAssetSettlementApiID + " .response-col_status");
    public static By createAssetSettlementApiResponseCodeDescription = By.cssSelector(createAssetSettlementApiID + " .response_current");

    private static WebDriver driver;

    public SwaggerUIPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static String loginAPIBody = "{\n" +
            "  \"userEmail\": \"xxxxxxxxxxxxxxxx\",\n" +
            "  \"userPassword\": \"xxxxxx12345\"\n" +
            "}";

    public static String createAssetRequestBody;

    static {
        try {
            createAssetRequestBody = "{\n" +
                    "  \"farm\": {\n" +
                    "    \"siteId\": " + getHouseParentSiteId() + "\n" +
                    "  },\n" +
                    "  \"animalSize\": {\n" +
                    "    \"id\": " + getanimalSizeID(animalName) + ",\n" +
                    "    \"name\": \"" + animalName + "\"\n" +
                    "  },\n" +
                    "  \"animalSex\": {\n" +
                    "    \"id\": \"\",\n" +
                    "    \"name\": \"\"\n" +
                    "  },\n" +
                    "  \"Assetdeployments\": [\n" +
                    "    {\n" +
                    "      \"house\": {\n" +
                    "        \"siteId\": " + getHouseIDAssignedToUser() + "\n" +
                    "      },\n" +
                    "      \"deploymentDate\": \"" + dateYMD + "\",\n" +
                    "      \"estimatedProcessingDate\": \"\",\n" +
                    "      \"animalsPlaced\": 100\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String createAssetSettlementRequestBody;

    static {
        try {
            createAssetSettlementRequestBody = "{\n" +
                    "  \"AssetId\": " + getAssetId() + ",\n" +
                    "  \"AssetSettlements\": [\n" +
                    "    {\n" +
                    "      \"processingSite\": {\n" +
                    "        \"siteId\": " + getProcessingSiteIDAssignedToUser() + "\n" +
                    "      },\n" +
                    "      \"processingDate\": \"" + dateYMD + "\",\n" +
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
                    "      \"hatchDate\": \"" + getTomorrowDate() + "\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void authorizeUser() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Swagger-01: Verify user can login and authorize the user");
            SoftAssert softAssert = new SoftAssert();
            driver.findElement(loginApiExpand).click();
            Thread.sleep(1000);
            driver.findElement(loginApiTryOutButton).click();
            driver.findElement(loginApiInputField).clear();
            driver.findElement(loginApiInputField).sendKeys(loginAPIBody);
            Methods.waitElementClickable(loginApiExecuteButton);
            driver.findElement(loginApiExecuteButton).click();
            Methods.waitElementVisible(loginApiResponseCodeDescription);
            System.out.println(driver.findElement(loginApiResponseBody).getText());
            System.out.println(driver.findElement(loginApiResponseBody).getText().substring(23, 515));
            String token = driver.findElement(loginApiResponseBody).getText().substring(23, 515);
            softAssert.assertEquals(driver.findElement(loginApiResponseCodeDescription).getText(), "200\n" +
                    "Success\n" +
                    "No links");

            driver.findElement(authorizeButton).click();
            Thread.sleep(1000);
            driver.findElement(autorizeInputField).sendKeys(token);
            driver.findElement(autorizeTokenButton).click();
            Thread.sleep(750);
            driver.findElement(autorizeTokenCloseButton).click();
            softAssert.assertAll();
            test.pass("Login Api worked and user was authorized successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User failed to authorize");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User failed to authorize");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public static void getAssetIDAPI() throws InterruptedException, SQLException, IOException {
        try {
            test = extent.createTest("Swagger-02: Verify user can run Get Asset ID api");
            SoftAssert softAssert = new SoftAssert();
            scroll(getAssetApiExpand);
            Thread.sleep(1500);
            driver.findElement(getAssetApiExpand).click();
            waitElementClickable(getAssetApiTryOutButton);
            driver.findElement(getAssetApiTryOutButton).click();
            driver.findElement(getAssetApiInputField).clear();

            String AssetID = String.valueOf(getAssetId());
            driver.findElement(getAssetApiInputField).sendKeys(AssetID);

            Methods.waitElementClickable(getAssetApiExecuteButton);
            Thread.sleep(1000);
            driver.findElement(getAssetApiExecuteButton).click();
            Methods.waitElementVisible(getAssetApiResponseCodeDescription);
            System.out.println(driver.findElement(getAssetApiResponseBody).getText());


            String responseBody = driver.findElement(getAssetApiResponseBody).getText().substring(8);

            ResultSet getAssetRow = DB_Config_DW.getStmt().executeQuery(getAssetRowQuery);
            while (getAssetRow.next()) {
                softAssert.assertEquals(getJsonValue(driver, responseBody, "uniqueAssetId"), getAssetRow.getString("UNIQUE_Asset_ID"));
                softAssert.assertEquals(getJsonValue(driver, responseBody, "farmId"), getAssetRow.getString("FARM_INTERNAL_ID"));
                softAssert.assertEquals(getJsonValue(driver, responseBody, "animalSizeId"), getAssetRow.getString("animal_SIZE_ID"));
                softAssert.assertEquals(getJsonValue(driver, responseBody, "animalSizeName"), getAssetRow.getString("animal_SIZE"));
                softAssert.assertEquals(getJsonValue(driver, responseBody, "animalSexId"), getAssetRow.getString("animal_SEX_ID"));
                softAssert.assertEquals(getJsonValue(driver, responseBody, "animalSexName"), getAssetRow.getString("animal_SEX"));
            }

            softAssert.assertEquals(driver.findElement(getAssetApiResponseCodeDescription).getText(), "200\n" +
                    "Success\n" +
                    "No links");

            softAssert.assertAll();
            test.pass("Get Asset ID Api ran successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Get Asset ID API failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Get Asset ID API failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public static void createAssetAPI() throws InterruptedException, SQLException, IOException {
        try {
            test = extent.createTest("Swagger-03: Verify user can run create Asset api");
            SoftAssert softAssert = new SoftAssert();
            scroll(createAssetApiExpand);
            Thread.sleep(1500);
            driver.findElement(createAssetApiExpand).click();
            waitElementClickable(createAssetApiTryOutButton);
            driver.findElement(createAssetApiTryOutButton).click();
            driver.findElement(createAssetApiInputField).clear();
            driver.findElement(createAssetApiInputField).sendKeys(createAssetRequestBody);

            Methods.waitElementClickable(createAssetApiExecuteButton);
            Thread.sleep(1000);
            driver.findElement(createAssetApiExecuteButton).click();
            Methods.waitElementVisible(createAssetApiResponseCodeDescription);
            System.out.println(driver.findElement(createAssetApiResponseBody).getText());

            String responseBody = driver.findElement(createAssetApiResponseBody).getText().substring(8);

            Assert.assertEquals(driver.findElement(createAssetApiResponseCodeDescription).getText(), "200\n" +
                    "Success\n" +
                    "No links");

            ResultSet getCreatedAsset = DB_Config_DW.getStmt().executeQuery(getCreatedAssetQuery + responseBody);
            boolean hasRow = getCreatedAsset.next();
            softAssert.assertEquals(hasRow, true, "Created Asset did not save in database");

            softAssert.assertAll();
            test.pass("Create Asset Api ran successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Create Asset API failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Create Asset API failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public static void createAssetSettlementAPI() throws InterruptedException, SQLException, IOException {
        try {
            test = extent.createTest("Swagger-04: Verify user can run create Asset api");
            SoftAssert softAssert = new SoftAssert();
            scroll(createAssetSettlementApiExpand);
            Thread.sleep(1500);
            driver.findElement(createAssetSettlementApiExpand).click();
            waitElementClickable(createAssetSettlementApiTryOutButton);
            driver.findElement(createAssetSettlementApiTryOutButton).click();

            driver.findElement(createAssetSettlementApiAssetIDInputField).clear();
            String AssetID = String.valueOf(getAssetId());
            driver.findElement(createAssetSettlementApiAssetIDInputField).sendKeys(AssetID);

            driver.findElement(createAssetSettlementApiInputField).clear();
            driver.findElement(createAssetSettlementApiInputField).sendKeys(createAssetSettlementRequestBody);

            Methods.waitElementClickable(createAssetSettlementApiExecuteButton);
            Thread.sleep(1000);
            driver.findElement(createAssetSettlementApiExecuteButton).click();
           Methods.waitElementVisible(createAssetSettlementApiResponseCodeDescription);
            System.out.println(driver.findElement(createAssetSettlementApiResponseBody).getText());

            String responseBody = driver.findElement(createAssetSettlementApiResponseBody).getText().substring(8);

          //  System.out.println("New: "+driver.findElement(createAssetSettlementApiResponseCodeStatus).getText());

            Assert.assertEquals(driver.findElement(createAssetSettlementApiResponseCodeDescription).getText(), "200\n" +
                    "Success\n" +
                    "No links");

            softAssert.assertEquals(responseBody, "\n"+getAssetId(), "Response body did not show Asset id");

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
                softAssert.assertEquals(getAddedSettlementRow.getString("PROCESSING_DATE"), DateUtil.dateYMD, "Processing Date not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("PROCESSING_SITE_ID"), getProcessingSiteIDAssignedToUser(), "Processing Site Id not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("USDA_PLANT_ID"), 1, "USDA plant id not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("PLANT_LOCATION"), 1, "Plant location not showing correct value in db");
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
                softAssert.assertEquals(getAddedSettlementRow.getInt("Is_DELETED"), 0, "Is deleted not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("IS_ACTIVE"), 1, "Is active not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getString("HATCH_DATE"), DateUtil.getTomorrowDate(), "Hatch date not showing correct value in db");
                softAssert.assertEquals(getAddedSettlementRow.getInt("IS_DATA_MIGRATED"), 0, "Is data migrated not showing correct value in db");
            }



            softAssert.assertAll();
            test.pass("Create Asset Settlement Api ran successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Create Asset Settlement API failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Create Asset Settlement API failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

}