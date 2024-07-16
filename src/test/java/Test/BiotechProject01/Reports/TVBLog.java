package Test.BiotechProject01.Reports;

import java.io.IOException;

import PageObjects.Patho_1LogPage;
import PageObjects.TVBLogPage;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
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
import static MiscFunctions.DateUtil.date;
import static PageObjects.SSMPage.ssmDeepSerotypingLogTable;
import static PageObjects.TVBLogPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;

public class TVBLog extends BaseTest{

    @BeforeTest (groups = {"smoke","regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/TVB_Log"+ date+".html");
//        spark.config().setReportName("TVB Log Test Report");
        initReport("TVB_Log");
    }

    @BeforeClass (groups = {"smoke","regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        openTVBLogPage();
    }


    @Test (priority = 2, enabled = true, groups = {"smoke","regression"})
    public void LockFilter() throws InterruptedException, IOException {
        Lock(tvbLogTable, "TVB Log", 2);
    }


    @Test (priority = 3, groups = "regression")
    public void WildcardTVB() throws InterruptedException, IOException {
        Wildcard(tvbLogTable, "TVB Log", 2);
    }


    @Test(priority= 4, groups = {"smoke","regression"})
    public void FilterSorting() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Sorting(tvbLogTable, "TVB Log", 2);
    }

    @Test(priority= 5, enabled = true, groups = "regression")
    public void RowsPerPage() throws InterruptedException, IOException {
        RowsPerPage1(tvbLogTable);
    }

    @Test(priority= 6, enabled = true, groups = "regression")
    public void PaginationTVB() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Pagination(tvbLogTable, "TVB Log");
    }

    @Test(priority= 7, enabled = true, groups = "regression")
    public void FieldAccessTVB() throws InterruptedException, IOException {
        FieldAccessFunctionality(tvbLogTable);
    }

    @Test(priority= 8, enabled = true, groups = "regression")
    public void FieldAccessKeyColumns() throws InterruptedException, IOException {
        KeyColumnsCheck(tvbLogTable, new String[]{"Result ID", "Result Date"});
    }

    @Test(priority = 9, enabled = true, groups = "regression")
    public void VerifyColumns() throws IOException, InterruptedException {
        VerifyAllColumns(tvbLogTable, new String[]{"Lane", "Lab Sample ID", "QC Code", "Result Status", "Result", "Assay", "Result ID", "Result Date", "Result Time", "Collection Site Name", "Collection Site ID", "Collection Site Type", "Sample Matrix", "Dilution Factor", "Date Received", "Cartridge ID", "Instrument ID", "W2 Cell Count", "W2 Mean Intensity", "PIPER User", "Improc Version", "Test Site ID", "Test Site Name", "Requested Assay", "Run Type", "Collection Date", "Collection Time", "Farm", "Complex", "Customer", "Asset Age", "Asset Age Unit", "Sampled By", "Cells Per mL", "Plant", "Line", "Work Order", "Result Unit"});
    }

    @Test(enabled = true, priority = 10, groups = "regression")
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
    public void TvbSiteNameFilter() throws IOException {
        click(By.id(ResetFilters));
        SiteName();
    }

    @Test(priority = 13, enabled = true, groups = "regression")
    public void TvbPNGExport() throws IOException, InterruptedException {
        click(By.id(ResetFilters));
        PNGExport("#dc-bar-chart-salm-png img", tvbPNGFileName);
    }

    @Test(priority = 14, enabled = true, groups = "regression")
    public void TvbCSVExport() throws IOException, InterruptedException {
        click(By.id(ResetFilters));
        CSVExport1("Tvb Log", tvbCSVFileName, tvbLogTable, 4, 0);
    }

    @Test(priority = 14, enabled = true, groups = "regression")
    public void TvbCSVAuditExport() throws IOException, InterruptedException {
        CSVAuditExport("Tvb Log", tvbCSVAuditFileName, tvbLogTable, true);
    }

    @Test(priority = 15, enabled = true, groups = "regression")
    public void TVBTemplateExport() throws InterruptedException, IOException {
        TemplateExport(TVBLogPage.tvbTemplateFileName);
    }

    @AfterTest (groups = {"smoke","regression"})
    public static void endreport() {
        extent.flush();
    }
}
