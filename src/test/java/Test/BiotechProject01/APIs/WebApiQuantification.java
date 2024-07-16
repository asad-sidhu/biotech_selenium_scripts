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

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.DateUtil.getTomorrowDate;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.Queries.getHouseIDAssignedToUser;
import static PageObjects.WebApiPage.*;

public class WebApiQuantification {

    @BeforeSuite
    public static void setUp() {
        initReport("WEB_API_Quantification_Report");
    }

    @BeforeTest
    public void dbConnect() {
        DB_Config_DB.test();
        DB_Config_DW.test();
    }

    @Test(enabled = true, priority = 1)
    public static void UpdatePatho_1QuantificationDataUsingValidSampleIDAPI() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updatePatho_1QuantificationDataWithValidSampleID();
    }

    @Test(enabled = true, priority = 2)
    public static void UpdatePatho_1QuantificationDataValidationInvalidSampleID() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updatePatho_1QuantificationDataValidations("IE-13245: Verify validation check when user send /clostridium-quantification-data api with sample id that does not exist", "invalid_sampleId", getHouseIDAssignedToUser(), "100", dateYMD, getTomorrowDate(), "Sample with identifier invalid_sampleId does not exist in the system");
    }

    @Test(enabled = true, priority = 3)
    public static void UpdatePatho_1QuantificationDataValidationAlphaAssetAge() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updatePatho_1QuantificationDataValidations("IE-13250: Verify validation check when user send /clostridium-quantification-data api with sample id that does not exist", getSampleID(), getHouseIDAssignedToUser(), "alpha", dateYMD, getTomorrowDate(), "alpha is not a valid number");
    }

    @Test(enabled = true, priority = 4)
    public static void UpdatePatho_1QuantificationDataValidationInvalidHouseID() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updatePatho_1QuantificationDataValidations("IE-13254: Verify validation check when user send /clostridium-quantification-data api with sample id that does not exist", getSampleID(), 48963, "10", dateYMD, getTomorrowDate(), "CollectionSiteId contains invalid house IDs");
    }

    @Test(enabled = true, priority = 5)
    public static void UpdatePatho_1QuantificationDataValidationInvalidDateFormat() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updatePatho_1QuantificationDataValidations("IE-13256: Verify validation check when user send /clostridium-quantification-data api with sample id that does not exist", getSampleID(), getHouseIDAssignedToUser(), "10", "2024/04/12", getTomorrowDate(), "Collection date is not in Format (yyyy-MM-dd)");
    }

    @Test(enabled = true, priority = 6)
    public static void UpdateClostridiumQuantificationDataUsingValidSampleIDAPI() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateClostridiumQuantificationDataWithValidSampleID();
    }

    @Test(enabled = true, priority = 7)
    public static void UpdateClostridiumQuantificationDataValidationInvalidSampleID() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateClostridiumQuantificationDataValidations("IE-13252: Verify validation check when user send /clostridium-quantification-data api with sample id that does not exist",
                "invalid_sampleId", getHouseIDAssignedToUser(), "100", dateYMD, getTomorrowDate(), "Sample with identifier invalid_sampleId does not exist in the system");
    }

    @Test(enabled = true, priority = 8)
    public static void UpdateClostridiumQuantificationDataValidationInvalidHouseID() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateClostridiumQuantificationDataValidations("IE-13254: Verify the validation on site id on /clostridium-quantification-data Webapi",
                getClostridiumSampleID(), 48963, "10", dateYMD, getTomorrowDate(), "CollectionSiteId contains invalid house IDs");
    }

    @Test(enabled = true, priority = 9)
    public static void UpdateClostridiumQuantificationDataValidationAlphaAssetAge() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateClostridiumQuantificationDataValidations("IE-13259: Verify that Asset Age accepts only numeric data /clostridium-quantification-data Webapi",
                getClostridiumSampleID(), getHouseIDAssignedToUser(), "alpha", dateYMD, getTomorrowDate(), "alpha is not a valid number");
    }

    @Test(enabled = true, priority = 10)
    public static void UpdateClostridiumQuantificationDataValidationInvalidDateFormat() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateClostridiumQuantificationDataValidations("IE-13267: Verify the validation on date format for /clostridium-quantification-data WebApiVerify the validation on date format for /tvb-quantification-data WebApi",
                getClostridiumSampleID(), getHouseIDAssignedToUser(), "10", "2024/04/12", getTomorrowDate(), "Collection date is not in Format (yyyy-MM-dd)");
    }

    @Test(enabled = true, priority = 11)
    public static void UpdateTVBQuantificationDataUsingValidSampleIDAPI() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateTVBQuantificationDataWithValidSampleID();
    }

    @Test(enabled = true, priority = 12)
    public static void UpdateTVBQuantificationDataValidationInvalidSampleID() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateTVBQuantificationDataValidations("IE-13260: Verify validation check when user send /tvb-quantification-data api with sample id that does not exist",
                "invalid_sampleId", getHouseIDAssignedToUser(), "100", dateYMD, getTomorrowDate(), "Sample with identifier invalid_sampleId does not exist in the system");
    }

    @Test(enabled = true, priority = 13)
    public static void UpdateTVBQuantificationDataValidationInvalidHouseID() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateTVBQuantificationDataValidations("IE-13262: Verify the validation on site id on /tvb-quantification-data Webapi",
                getTVBSampleID(), 48963, "10", dateYMD, getTomorrowDate(), "CollectionSiteId contains invalid house IDs");
    }

    @Test(enabled = true, priority = 14)
    public static void UpdateTVBQuantificationDataValidationAlphaAssetAge() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateTVBQuantificationDataValidations("IE-13265: Verify that Asset Age accepts only numeric data /tvb-quantification-data Webapi",
                getTVBSampleID(), getHouseIDAssignedToUser(), "alpha", dateYMD, getTomorrowDate(), "alpha is not a valid number");
    }

    @Test(enabled = true, priority = 15)
    public static void UpdateTVBQuantificationDataValidationInvalidDateFormat() throws IOException, SQLException {
        WebApiPage wap = new WebApiPage();
        wap.updateTVBQuantificationDataValidations("IE-13268: Verify the validation on date format for /tvb-quantification-data WebApiVerify the validation on date format for /tvb-quantification-data WebApi",
                getTVBSampleID(), getHouseIDAssignedToUser(), "10", "2024/04/12", getTomorrowDate(), "Collection date is not in Format (yyyy-MM-dd)");
    }

    @AfterTest
    public void endreport() throws Exception {
        extent.flush();
    }
}
