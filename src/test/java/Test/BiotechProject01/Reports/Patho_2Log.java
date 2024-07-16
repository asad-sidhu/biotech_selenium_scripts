package Test.BiotechProject01.Reports;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import MiscFunctions.DateUtil;
import PageObjects.Patho_2LogPage;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static LogViewFunctions.FieldAccess.*;
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
import static PageObjects.SSMPage.ssmDeepSerotypingLogTable;
import static PageObjects.Patho_2LogPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import java.io.*;

public class Patho_2Log extends BaseTest {

    @BeforeTest (groups = {"smoke","regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Patho_2_Log" + DateUtil.date + ".html");
//        spark.config().setReportName("Patho_2 Log Test Report");
        initReport("Patho_2_Log");

    }

    @BeforeClass (groups = {"smoke","regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        openPatho_2LogPage();
    }


    @Test (priority = 2, enabled = true, groups = {"smoke","regression"})
    public void LockFilter() throws InterruptedException, IOException {
        Lock(Patho_2LogTable, "Patho_2 Log", 2);
    }


    @Test(priority = 3, enabled = true, groups = "regression")
    public void WildcardSalm() throws InterruptedException, IOException {
        Wildcard(Patho_2LogTable, "Patho_2 Log", 2);
    }


    @Test(priority= 4, groups = {"smoke","regression"})
    public void FilterSorting() throws InterruptedException, IOException {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Sorting(Patho_2LogTable, "Patho_2 Log", 2);
    }


    @Test(priority = 5, enabled = true, groups = "regression")
    public void RowsPerPage() throws InterruptedException, IOException {
        RowsPerPage1(Patho_2LogTable);
    }


    @Test(priority = 6, enabled = true, groups = "regression")
    public void PaginationSalm() throws InterruptedException, IOException {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Pagination(Patho_2LogTable, "Patho_2 Log");
    }

    @Test(priority = 7, enabled = true, groups = "regression")
    public void FieldAccessSalm() throws InterruptedException, IOException {
        Patho_2LogPage.openPatho_2LogPage();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        FieldAccessFunctionality(Patho_2LogTable);
    }

    @Test(priority = 8, enabled = true, groups = "regression")
    public void FieldAccessKeyColumns() throws InterruptedException, IOException {
        KeyColumnsCheck(Patho_2LogTable, new String[]{"Result ID", "Result Date"});
    }

    @Test(priority = 9, enabled = true, groups = "regression")
    public void VerifyColumns() throws IOException, InterruptedException {
        VerifyAllColumns(Patho_2LogTable, new String[]{"Lane", "Lab Sample ID", "QC Code", "Result Status", "Result", "Assay", "Result ID", "Result Date", "Result Time", "Collection Site Name", "Collection Site ID", "Collection Site Type", "Sample Matrix", "Dilution Factor", "Customer Sample Id", "Date Received", "Cartridge ID", "Instrument ID", "W1 Cell Count", "W1 PC Count", "W1 Mean Intensity", "W2 Cell Count", "W2 PC Count", "W2 Mean Intensity", "PIPER User", "Kit Lot", "Improc Version", "Test Site ID", "Test Site Name", "Requested Assay", "Asset ID", "Run Type", "Collection Date", "Farm", "Complex"});
    }


    @Test(enabled = true, priority = 10, groups = "regression")
    public void VerifyResultDateFilter() throws IOException, InterruptedException {
        DateFilter();
    }

    @Test(priority = 11, enabled = true, groups = "regression")
    public void ResultDateFilterLock() throws IOException, InterruptedException {
        DateFilterLock();
    }

    @Test(priority = 12, enabled = true, groups = "regression")
    public void SalmSiteNameFilter() throws IOException, InterruptedException {
        SiteName();
    }

    @Test(priority = 15, enabled = true, groups = "regression")
    public void SalmPNGExport() throws IOException, InterruptedException {
        PNGExport("#dc-bar-chart-salm-png img", slPNGFileName);
    }

    @Test(priority = 16, enabled = true, groups = "regression")
    public void SalmCSVExport() throws IOException, InterruptedException {
        CSVExport1("Patho_2 Log", slCSVFileName, Patho_2LogTable, 4, 3);
    }

    @Test(priority = 17, enabled = true, groups = "regression")
    public void SalmCSVAuditExport() throws IOException, InterruptedException {
        CSVAuditExport("Patho_2 Log", slCSVAuditFileName, Patho_2LogTable, true);
    }

    @Test(priority = 18, enabled = true, groups = "regression")
    public void SalmTemplateExport() throws InterruptedException, IOException {
        TemplateExport(slSampleMetaData);
    }

    @AfterTest (groups = {"smoke","regression"})
    public static void endreport() {
        extent.flush();
    }
}
