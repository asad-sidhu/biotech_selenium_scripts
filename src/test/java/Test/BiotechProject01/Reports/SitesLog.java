package Test.BiotechProject01.Reports;

import Config.BaseTest;
import PageObjects.SitesLogPage;
import Test.BiotechProject01.Login.LoginTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static ExtentReports.ExtentReport.initReport;
import static LogViewFunctions.FieldAccess.FieldAccessFunctionality;
import static LogViewFunctions.FieldAccess.KeyColumnsCheck;
import static LogViewFunctions.FilterLock.Lock;
import static LogViewFunctions.FilterSort.Sorting;
import static LogViewFunctions.FilterWildcard.Wildcard;
import static LogViewFunctions.LogCSVExport.*;
import static LogViewFunctions.Pagination.Pagination;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.DateUtil.date;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.loading_cursor;
import static PageObjects.SitesLogPage.*;

public class SitesLog extends BaseTest {

    @BeforeTest (groups = {"smoke","regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Sites_Log" + date + ".html");
//        spark.config().setReportName("Sites Log Test Report");
        initReport("Sites_Log");

    }

    @BeforeClass (groups = {"smoke","regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        navigateSitesLogScreen();
    }

    @Test(priority = 2, enabled = true, groups = {"smoke","regression"})
    public void LockFilterComplex() throws InterruptedException, IOException {
        Lock(complexLogTable, "Complex Log", 2);
    }

    @Test(enabled = true, priority = 3, groups = "regression")
    public void WildcardFilterComplex() throws InterruptedException, IOException {
        Wildcard(complexLogTable, "Complex Log", 2);
    }

    @Test(priority = 4, groups = {"smoke","regression"})
    public void FilterSortingComplex() throws InterruptedException, IOException {
        Sorting(complexLogTable, "Complex Log", 2);
    }

    @Test(priority = 5, enabled = true, groups = "regression")
    public void RowsPerPageComplex() throws InterruptedException, IOException {
        RowsPerPage1(complexLogTable);
    }

    @Test(priority = 6, enabled = true, groups = "regression")
    public void PaginationSitesComplex() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Pagination(complexLogTable, "Sites Log");
    }
    @Test(priority = 7, enabled = true, groups = "regression")
    public void VerifyAuditComplex() throws InterruptedException, IOException {
        checkAuditLog(complexLogTable);
    }

    @Test(priority = 8, enabled = true, groups = {"smoke","regression"})
    public void LockFilterFarm() throws InterruptedException, IOException {
        SitesLogPage.openSitesLogPage();
        clickFarmTab();
        Lock(farmLogTable, "Sites Log", 2);
    }

    @Test(priority = 9, groups = "regression")
    public void WildcardFilterFarm() throws InterruptedException, IOException {
        SitesLogPage.openSitesLogPage();
        clickFarmTab();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Wildcard(farmLogTable, "Sites Log", 2);
    }

    @Test(priority = 10, groups = {"smoke","regression"})
    public void FilterSortingFarm() throws InterruptedException, IOException {
        clickFarmTab();
        Sorting(farmLogTable, "Sites Log", 2);
    }

    @Test(priority = 11, enabled = true, groups = "regression")
    public void RowsPerPageFarm() throws InterruptedException, IOException {
        clickFarmTab();
        RowsPerPage1(farmLogTable);
    }

    @Test(priority = 12, enabled = true, groups = "regression")
    public void PaginationSitesFarm() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        clickFarmTab();
        Pagination(farmLogTable, "Sites Log");
    }

    @Test(priority = 13, enabled = true, groups = "regression")
    public void VerifyAuditFarm() throws InterruptedException, IOException {
        checkAuditLog(farmLogTable);
    }

    @Test(priority = 14, enabled = true, groups = {"smoke","regression"})
    public void LockFilterHatchery() throws InterruptedException, IOException {
        SitesLogPage.openSitesLogPage();
        clickFarmTab();
        Lock(hatcheryLogTable, "Sites Log", 2);
    }

    @Test(priority = 15, groups = "regression")
    public void WildcardFilterHatchery() throws InterruptedException, IOException {
        SitesLogPage.openSitesLogPage();
        clickHatcheryTab();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Wildcard(hatcheryLogTable, "Sites Log", 2);
    }

    @Test(priority = 16, groups = {"smoke","regression"})
    public void FilterSortingHatchery() throws InterruptedException, IOException {
        clickHatcheryTab();
        Sorting(hatcheryLogTable, "Sites Log", 2);
    }

    @Test(priority = 17, enabled = true, groups = "regression")
    public void RowsPerPageHatchery() throws InterruptedException, IOException {
        clickHatcheryTab();
        RowsPerPage1(hatcheryLogTable);
    }

    @Test(priority = 18, enabled = true, groups = "regression")
    public void PaginationSitesHatchery() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        clickHatcheryTab();
        Pagination(hatcheryLogTable, "Sites Log");
    }

    @Test(priority = 19, enabled = true, groups = "regression")
    public void VerifyAuditHatchery() throws InterruptedException, IOException {
        checkAuditLog(hatcheryLogTable);
    }

    @Test(priority = 20, enabled = true, groups = {"smoke","regression"})
    public void LockFilterHouse() throws InterruptedException, IOException {
        clickHouseTab();
        Lock(houseLogTable, "Sites Log", 2);
    }

    @Test(priority = 21, groups = "regression")
    public void WildcardFilterHouse() throws InterruptedException, IOException {
        SitesLogPage.openSitesLogPage();
        clickHouseTab();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Wildcard(houseLogTable, "Sites Log", 2);
    }

    @Test(priority = 22, groups = {"smoke","regression"})
    public void FilterSortingHouse() throws InterruptedException, IOException {
        clickHouseTab();
        Sorting(houseLogTable, "Sites Log", 2);
    }

    @Test(priority = 23, enabled = true, groups = "regression")
    public void RowsPerPageHouse() throws InterruptedException, IOException {
        clickHouseTab();
        RowsPerPage1(houseLogTable);
    }

    @Test(priority = 24, enabled = true, groups = "regression")
    public void PaginationSitesHouse() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        clickHouseTab();
        Pagination(houseLogTable, "Sites Log");
    }

    @Test(priority = 25, enabled = true, groups = "regression")
    public void VerifyAuditHouse() throws InterruptedException, IOException {
        checkAuditLog(houseLogTable);
    }

    @Test(enabled = true, priority = 26, groups = "regression")
    public void VerifyColumnsComplexTab() throws IOException, InterruptedException {
        SitesLogPage.openSitesLogPage();
        VerifyAllColumns(complexLogTable, new String[]{"", "", "Site ID", "Site Name", "Site Barcode", "Created Date", "Created By", "Organization", "Region", "Sub Region", "Number Of Farms Under Complex", "Current Program", "Planned Date To Change Program", "Number Of Houses", "Complex Manager", "Complex Manager Email", "Complex Manager Phone Number", "Street Address", "City", "State", "Zip Code", "Country", "Latitude", "Longitude", ""});
    }

    @Test(enabled = true, priority = 27, groups = "regression")
    public void VerifyColumnsFarmTab() throws IOException, InterruptedException {
        SitesLogPage.openSitesLogPage();
        VerifyAllColumns(farmLogTable, new String[]{"", "", "Site ID", "Site Name", "Site Barcode", "Created Date", "Created By", "Organization", "Region", "Sub Region", "Complex", "Farm Type", "Six Asset Rank", "Number Of Houses", "Total Head Capacity", "Street Address", "City", "State", "Zip Code", "Country", "Latitude", "Longitude", "Contract Grower Name", "Contract Grower Phone Number", "Contract Grower Email", "Farm External Site ID", ""});
    }
    @Test(enabled = true, priority = 28, groups = "regression")
    public void VerifyColumnsHatcheryTab() throws IOException, InterruptedException {
        SitesLogPage.openSitesLogPage();
        VerifyAllColumns(hatcheryLogTable, new String[]{"", "", "Site ID", "Site Name", "Site Barcode", "Created Date", "Created By", "Organization", "Region", "Sub Region", "Complex", "Farm Type", "Six Asset Rank", "Number Of Houses", "Total Head Capacity", "Street Address", "City", "State", "Zip Code", "Country", "Latitude", "Longitude", "Contract Grower Name", "Contract Grower Phone Number", "Contract Grower Email", "Hatchery External Site ID", ""});
    }

    @Test(enabled = true, priority = 29, groups = "regression")
    public void VerifyColumnsHouseTab() throws IOException, InterruptedException {
        clickHouseTab();
        VerifyAllColumns(houseLogTable, new String[]{"", "", "Site ID", "Site Name", "Site Barcode", "Created Date", "Created By", "Organization", "Region", "Sub Region", "Complex", "Farm", "House Classification", "Year Built", "Age Of Building", "Dimensions", "Square Footage", "Capacity", "Number Of Feed Bins", "Feeder Type", "Drinker Type", "Ventilation Type", "Litter Type", "Number Of Runs On Litter", "Floor Type", "Street Address", "City", "State", "Zip Code", "Country", "Latitude", "Longitude", ""});
    }

    @Test(enabled = true, priority = 30, groups = "regression")
    public void ExportCSVComplexTab() throws IOException, InterruptedException {
        SitesLogPage.openSitesLogPage();
        CSVExport1("Complex Log", sitesComplexTabCSVFileName, complexLogTable, 3, 3);
    }

    @Test(enabled = true, priority = 31, groups = "regression")
    public void ExportCSVFarmTab() throws IOException, InterruptedException {
        clickFarmTab();
        CSVExport1("Farm Log", sitesFarmTabCSVFileName, farmLogTable, 4, 3);
    }

    @Test(enabled = true, priority = 32, groups = "regression")
    public void ExportCSVHatcheryTab() throws IOException, InterruptedException {
        clickFarmTab();
        CSVExport1("Hatchery Log", sitesHatcheryTabCSVFileName, hatcheryLogTable, 4, 3);
    }

    @Test(enabled = true, priority = 33, groups = "regression")
    public void ExportCSVHouseTab() throws IOException, InterruptedException {
        clickHouseTab();
        CSVExport1("House Log", sitesHouseTabCSVFileName, houseLogTable, 3, 3);
    }

    @Test(enabled = true, priority = 34, groups = "regression")
    public void ExportCSVAuditComplexTab() throws IOException, InterruptedException {
        clickComplexTab();
        CSVAuditExport("Complex Log", sitesComplexTabCSVAuditFileName, complexLogTable, true);
    }

    @Test(enabled = true, priority = 35, groups = "regression")
    public void ExportCSVAuditFarmTab() throws IOException, InterruptedException {
        clickFarmTab();
        CSVAuditExport("Farm Log", sitesFarmTabCSVAuditFileName, farmLogTable, true);
    }

    @Test(enabled = true, priority = 36, groups = "regression")
    public void ExportCSVAuditHatcheryTab() throws IOException, InterruptedException {
        clickFarmTab();
        CSVAuditExport("Hatchery Log", sitesHatcheryTabCSVAuditFileName, hatcheryLogTable, true);
    }

    @Test(enabled = true, priority = 37, groups = "regression")
    public void ExportCSVAuditHouseTab() throws IOException, InterruptedException {
        clickHouseTab();
        CSVAuditExport("House Log", sitesHouseTabCSVAuditFileName, houseLogTable, true);
    }

    @Test(enabled = true, priority = 38, groups = "regression")
    public void FieldAccessComplexTab() throws IOException, InterruptedException {
        clickComplexTab();
        FieldAccessFunctionality(complexLogTable);
    }

    @Test(enabled = true, priority = 39, groups = "regression")
    public void FieldAccessFarmTab() throws IOException, InterruptedException {
        SitesLogPage.openSitesLogPage();
        clickFarmTab();
        FieldAccessFunctionality(farmLogTable);
    }

    @Test(enabled = true, priority = 40, groups = "regression")
    public void FieldAccessHatcheryTab() throws IOException, InterruptedException {
        SitesLogPage.openSitesLogPage();
        clickHatcheryTab();
        FieldAccessFunctionality(hatcheryLogTable);
    }

    @Test(enabled = true, priority = 41, groups = "regression")
    public void FieldAccessHouseTab() throws IOException, InterruptedException {
        clickHouseTab();
        FieldAccessFunctionality(houseLogTable);
    }

    @Test(enabled = true, priority = 42, groups = "regression")
    public void KeyColumnsCheckComplexTab() throws IOException, InterruptedException {
        clickComplexTab();
        KeyColumnsCheck(complexLogTable, new String[]{"Site ID", "Site Name", "Created Date", "Created By", "Organization"});
    }

    @Test(enabled = true, priority = 43, groups = "regression")
    public void KeyColumnsCheckFarmTab() throws IOException, InterruptedException {
        SitesLogPage.openSitesLogPage();
        clickFarmTab();
        KeyColumnsCheck(farmLogTable, new String[]{"Site ID", "Site Name", "Created Date", "Created By", "Organization"});
    }

    @Test(enabled = true, priority = 44, groups = "regression")
    public void KeyColumnsCheckHatcheryTab() throws IOException, InterruptedException {
        SitesLogPage.openSitesLogPage();
        clickHatcheryTab();
        KeyColumnsCheck(hatcheryLogTable, new String[]{"Site ID", "Site Name", "Created Date", "Created By", "Organization"});
    }

    @Test(enabled = true, priority = 45, groups = "regression")
    public void KeyColumnsCheckHouseTab() throws IOException, InterruptedException {
        clickHouseTab();
        KeyColumnsCheck(houseLogTable, new String[]{"Site ID", "Site Name", "Created Date", "Created By", "Organization"});
    }

    @AfterTest (groups = {"smoke","regression"})
    public static void endreport() {
        extent.flush();
    }
}

