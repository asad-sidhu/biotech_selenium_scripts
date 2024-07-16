package Test.BiotechProject01.Reports;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import PageObjects.ClostridiumLogPage;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static LogViewFunctions.FieldAccess.FieldAccessFunctionality;
import static LogViewFunctions.FieldAccess.KeyColumnsCheck;
import static LogViewFunctions.LogCSVExport.CSVAuditExport;
import static LogViewFunctions.LogCSVExport.CSVExport1;
import static LogViewFunctions.LogPNGExport.PNGExport;
import static LogViewFunctions.LogTemplateExport.TemplateExport;
import static LogViewFunctions.ResultDateFilter.DateFilter;
import static LogViewFunctions.ResultDateFilter.DateFilterLock;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;
import static LogViewFunctions.RowsPerPage.RowsPerPage_;
import static LogViewFunctions.SiteNameFilter.SiteName;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.DateUtil.date;
import static PageObjects.ClostridiumLogPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static PageObjects.SSMPage.ssmDeepSerotypingLogTable;

public class ClostridiumLog extends BaseTest {

    @BeforeTest (groups = {"smoke","regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Clostridium_Log" + date + ".html");
//        spark.config().setReportName("Clostridium Log Test Report");
        initReport("Clostridium_Log");

    }

    @BeforeClass (groups = {"smoke","regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        openClostridiumLogPage();
    }


    @Test (priority = 2, enabled = true, groups = {"smoke","regression"})
    public void LockFilterClostridium() throws InterruptedException, IOException {
        click(By.id(ResetFilters));
        Lock(ClostridiumLogTable, "Clostridium Log", 2);
    }

    @Test(priority = 3, enabled = true, groups = "regression")
    public void WildcardClostridium() throws InterruptedException, IOException {
        ClostridiumLogPage.openClostridiumLogPage();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(By.id(ResetFilters));
        Wildcard(ClostridiumLogTable, "Clostridium Log", 2);
    }

    @Test(priority= 4, groups = {"smoke","regression"})
    public void FilterSorting() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(By.id(ResetFilters));
        Sorting(ClostridiumLogTable, "Clostridium Log", 2);
    }

    @Test(priority = 5, enabled = true, groups = "regression")
    public void RowsPerPage() throws InterruptedException, IOException {
        click(By.id(ResetFilters));
        RowsPerPage1(ClostridiumLogTable);
    }

    @Test(priority = 6, enabled = true, groups = "regression")
    public void PaginationClostridium() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(By.id(ResetFilters));
        Pagination(ClostridiumLogTable, "Clostridium Log");
    }

    @Test(priority = 7, enabled = true, groups = "regression")
    public void FieldAccessClostridium() throws InterruptedException, IOException {
        FieldAccessFunctionality( ClostridiumLogTable);
    }

    @Test(priority = 8, enabled = true, groups = "regression")
    public void FieldAccessKeyColumns() throws InterruptedException, IOException {
        KeyColumnsCheck( ClostridiumLogTable, new String[]{"Result ID", "Result Date"});
    }


    @Test(priority = 9, enabled = true, groups = "regression")
    public void VerifyColumns() throws IOException, InterruptedException {
        VerifyAllColumns(ClostridiumLogTable, new String[]{"Lane", "Lab Sample ID", "QC Code", "Result Status", "Result", "Assay", "Result ID", "Result Date", "Result Time", "Collection Site Name", "Collection Site ID", "Collection Site Type", "Sample Matrix", "Dilution Factor", "Date Received", "Cartridge ID", "Instrument ID", "W2 Cell Count", "W2 Mean Intensity", "PIPER User", "Improc Version", "Test Site ID", "Test Site Name", "Requested Assay", "Run Type", "Collection Date", "Collection Time", "Farm", "Complex", "Sampled By", "Asset Age", "Asset Age Unit", "Customer", "Analyzed By", "CFU Per Gram", "Work Order", "Result Unit"});
    }

    @Test(priority = 10, enabled = true, groups = "regression")
    public void ResultDateFilter() throws IOException, InterruptedException {
        click(By.id(ResetFilters));
        DateFilter();
    }


    @Test(priority = 11, enabled = true, groups = "regression")
    public void ResultDateFilterLock() throws IOException, InterruptedException {
        click(By.id(ResetFilters));
        DateFilterLock();
    }

    @Test(priority = 12, enabled = true, groups = "regression")
    public void ClosSiteNameFilter() throws IOException {
        click(By.id(ResetFilters));
        SiteName();
    }

    @Test(priority = 13, enabled = true, groups = "regression")
    public void ClosPNGExport() throws IOException, InterruptedException {
        click(By.id(ResetFilters));
        PNGExport("#dc-bar-chart-salm-png img", clPNGFileName);
    }

    @Test(priority = 14, enabled = true, groups = "regression")
    public void CSVExport() throws IOException, InterruptedException {
        click(By.id(ResetFilters));
        CSVExport1("Clostridium Log", clCSVFileName, ClostridiumLogTable, 4, 0);
    }

    @Test(priority = 14, enabled = true, groups = "regression")
    public void ClosCSVAuditExport() throws IOException, InterruptedException {
        CSVAuditExport("Clostridium Log", clCSVAuditFileName, ClostridiumLogTable, true);
    }

    @Test(priority = 15, enabled = true, groups = "regression")
    public void ClosTemplateExport() throws InterruptedException, IOException {
        TemplateExport(clSampleMetaData);
    }


    @AfterTest (groups = {"smoke","regression"})
    public static void endreport() {
        extent.flush();
    }
}
