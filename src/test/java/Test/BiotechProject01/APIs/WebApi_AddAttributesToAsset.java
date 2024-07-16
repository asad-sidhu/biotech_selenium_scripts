package Test.BiotechProject01.APIs;

import MiscFunctions.DB_Config_DB;
import MiscFunctions.DB_Config_DW;
import PageObjects.WebApiPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.DateUtil.getTomorrowDate;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.Queries.*;
import static PageObjects.WebApiPage.*;
import static PageObjects.WebApiPage.animalName;

public class WebApi_AddAttributesToAsset {

    @BeforeSuite
    public static void setUp() {
        initReport("WEB_API_Report");
    }

    @BeforeTest
    public void dbConnect() {
        DB_Config_DB.test();
        DB_Config_DW.test();
    }

    @Test(enabled = true, priority = 13)
    public static void CreateAssetAPI() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.CreateAsset(getanimalSizeID(animalName), animalName, dateYMD);
    }


    @Test(enabled = true, priority = 14)
    public static void AddAssetdeploymentProcessingDateLessThandeploymentDateAPI() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.AssetdeploymentAPI("IE-10273: Verify user cannot create new deployment when estimated processing date is less than deployment date",
                getAssetId(), getHouseSiteIDAssignedToUser(getAssetId()), getTomorrowDate(), "2020-08-01", getSamplingPlanIDWithNullEndDate(),
                "Estimated date should be greater than deployment", 400);
    }

    @Test(enabled = true, priority = 15)
    public static void AddSameAssetdeploymentDateSiteIDAPI() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.AssetdeploymentAPI("IE-10274: Verify user cannot create new deployment with already created Asset deployment date and siteID",
                getAssetId(), getHouseSiteIDAssignedToUser(getAssetId()), dateYMD, getTomorrowDate(), getSamplingPlanIDWithNullEndDate(),
                "deployment Date and House Ids should be unique for a specific Asset", 400);
    }

    @Test(enabled = true, priority = 16)
    public static void AddAssetdeploymentWithInvalidHouse() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.AssetdeploymentAPI("IE-10274: Verify user cannot create a deployment with invalid house id",
                getAssetId(), 125896, getTomorrowDate(), getDayAfterTomorrowDate(), getSamplingPlanIDWithNullEndDate(),
                "Site does not exists.", 400);
    }

    @Test(enabled = true, priority = 17)
    public static void AddAssetdeploymentWithPlanComplexNotSameAsAsset() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        String planEndDate = getSamplingPlanEndDate();
        LocalDate originalDate = LocalDate.parse(planEndDate);
        LocalDate updatedDate = originalDate.minusDays(1);
        String deploymentDate = updatedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        wap.AssetdeploymentAPI("IE-10274: Verify user cannot create a deployment with sampling plan complex not same as Asset complex",
                getAssetId(), getHouseSiteIDAssignedToUser(getAssetId()), deploymentDate, getTomorrowDate(), getSamplingPlanWithComplexNotSameAsAsset(),
                "MONITORING_STRATEGY_NOT_ACTIVE_AT_Asset_LEVEL", 400);
    }

    @Test(enabled = true, priority = 18)
    public static void AddAssetdeploymentWithdeploymentDateGreaterThanEndDate() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.AssetdeploymentAPI("IE-10274: Verify user cannot create a deployment with sampling plan complex not same as Asset complex",
                getAssetId(), getHouseSiteIDAssignedToUser(getAssetId()), getTomorrowDate(), getDayAfterTomorrowDate(), getSamplingPlanWithComplexNotSameAsAsset(),
                "SamplingPlan EndDate should be greater than deploymentDate.", 400);
    }

    @Test(enabled = true, priority = 19)
    public static void AddAssetdeploymentWithTwoSamplingPlanOfSameMonitoringType() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        String planEndDate = getSamplingPlanEndDate();
        LocalDate originalDate = LocalDate.parse(planEndDate);
        LocalDate updatedDate = originalDate.minusDays(10);
        String deploymentDate = updatedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        wap.AssetdeploymentWithTwoSamplingPlanAPI("IE-10274: Verify user cannot create a deployment with sampling plan complex not same as Asset complex",
                getAssetId(), getHouseSiteIDAssignedToUser(getAssetId()), getTomorrowDate(), getDayAfterTomorrowDate(), getSamplingPlanWithComplexNotSameAsAsset(),
                "One or more House contains multiple Sampling Plans with same monitoring type.", 400);
    }

    @Test(enabled = true, priority = 20)
    public static void AddAssetdeploymentAPI() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.AssetdeploymentAPI("IE-10272: Verify user can create new deployment by running on \"/Assets/Assetid/deployment\" API and verify the response from db",
                getAssetId(), getHouseSiteIDAssignedToUser(getAssetId()), getTomorrowDate(), getDayAfterTomorrowDate(), getSamplingPlanIDWithNullEndDate(),
                String.valueOf(getAssetId()), 200);
    }


    @Test(enabled = true, priority = 22)
    public static void AddSamplingPlanTOExistingAssetdeploymentAPI() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.AssetAddSamplingPlanToExistingdeploymentAPI("IE-10272: Verify user can create new deployment by running on \"/Assets/Assetid/deployment\" API and verify the response from db",
                getAssetId(), getHouseSiteIDAssignedToUser(getAssetId()), "2023-11-02", getSamplingPlanIDWithNullEndDate(), true,
                String.valueOf(getAssetId()), 200);
    }


    @Test(enabled = true, priority = 23)
    public static void RemoveSamplingPlanAPI() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.DeleteSamplingPlanAPI("IE-11527, IE=11529: Verify user can delete a sampling Plan and verify it from database deployment by running on 'Assets/{AssetId}/deployments/{deploymentId}/samplingPlans/{samplingPlanId}' API and verify the response from db",
                getAssetId(), getdeploymentID(), getSamplingPlanIDWithNullEndDate(), "Success", 200);
    }


    @Test(enabled = true, priority = 24)
    public static void RemoveSamplingPlanWithInvaliddeploymentIDAPI() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.DeleteSamplingPlanAPI("IE-11528: Verify user cannot delete a sampling Plan with invalid deploymentID on running 'Assets/{AssetId}/deployments/{deploymentId}/samplingPlans/{samplingPlanId}' API and verify the response from db",
                12558, 1231121, getSamplingPlanIDWithNullEndDate(), "deployment does not exists.", 400);
    }


    @Test(enabled = true, priority = 25)
    public static void AddProgramAPI() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.AddProgramAPI("IE-11282: Verify user can add a Program and verify it from database by running on 'Assets/{AssetId}/programs}' API and verify the response from db",
                getAssetId(), getProgramID(), "Success", 200);
    }


    @Test(enabled = true, priority = 26)
    public static void RemoveProgramAPI() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.DeleteProgramAPI("IE-11525: Verify user can delete a sampling Plan and verify it from database by running on 'Assets/{AssetId}/programs}' API and verify the response from db",
                getAssetId(), getProgramID(), "Success", 200);
    }


    @Test(enabled = true, priority = 27)
    public static void UpdateAssetSettlementAPI() throws SQLException, IOException {
        WebApiPage wap = new WebApiPage();
        wap.AssetSettlementAPIValidationChecks("IE-10278/10281: Verify user can update settlement by running on \"/Assets/Assetid/settlements\" API and verify the response from db",
                getAssetId(), getHouseSiteIDAssignedToUser(getAssetId()), "2023-01-02", getTomorrowDate(),
                String.valueOf(getAssetId()), 200);
    }


    @Test(enabled = true, priority = 30)
    public static void GetSampleFromPatho_1DataAPI() throws IOException {
        WebApiPage wap = new WebApiPage();
        wap.getSampleFromPatho_1Data();
    }


    @Test(enabled = true, priority = 31)
    public static void GetInvalidSampleFromPatho_1DataAPI() throws IOException {
        WebApiPage wap = new WebApiPage();
        wap.getInvalidSampleFromPatho_1Data();
    }


    @Test(enabled = true, priority = 32)
    public static void UpdateSampleInPatho_1DataAPI() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateSampleInPatho_1Data();
    }


    @Test(enabled = true, priority = 33)
    public static void UpdateSampleInPatho_1DataWithInvalidSiteIDAPI() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateSampleInPatho_1DataWithInvalidSiteID();
    }


    @AfterTest
    public void endreport() throws Exception {
        extent.flush();
    }
}
