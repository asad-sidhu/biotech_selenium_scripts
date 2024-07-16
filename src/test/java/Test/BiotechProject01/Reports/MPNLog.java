package Test.BiotechProject01.Reports;

import Config.BaseTest;
import MiscFunctions.DateUtil;
import PageObjects.SSMPage;
import Test.BiotechProject01.Login.LoginTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static ExtentReports.ExtentReport.initReport;
import static LogViewFunctions.FieldAccess.*;
import static LogViewFunctions.FilterLock.Lock;
import static LogViewFunctions.FilterSort.Sorting;
import static LogViewFunctions.FilterWildcard.Wildcard;
import static LogViewFunctions.LogCSVExport.CSVExport1;
import static LogViewFunctions.Pagination.Pagination;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;
import static LogViewFunctions.RowsPerPage.RowsPerPage_;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.loading_cursor;
import static PageObjects.AssetManagementPage.AssetSettlementTable;
import static PageObjects.SSMPage.*;

public class MPNLog extends BaseTest {

    @BeforeTest (groups = {"smoke","regression"})
    public void extent() throws InterruptedException, IOException {
        spark = new ExtentSparkReporter("target/Reports/MPN_Log" + DateUtil.date + ".html");
        spark.config().setReportName("MPN Log Test Report");
        initReport("MPN_Log");

    }

    @BeforeClass (groups = {"smoke","regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        openSSMPage();
        clickMPNTab();

    }

    @Test (priority = 2, enabled = false, groups = {"smoke","regression"})
    public void LockFilterMPN() throws InterruptedException, IOException {
        clickMPNTab();
        getScreenshot();
        Lock(ssmMPNLogTable, "Patho_2 System Monitoring Log", 0);
    }

    @Test(priority = 3, enabled = false, groups = "regression")
    public void WildcardMPN() throws InterruptedException, IOException {
        clickMPNTab();
        Wildcard(ssmMPNLogTable, "Patho_2 System Monitoring Log", 0);
    }

    @Test(priority= 4, enabled = false, groups = {"smoke","regression"})
    public void FilterSorting() throws InterruptedException, IOException {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        clickMPNTab();
        Sorting(ssmMPNLogTable, "Patho_2 System Monitoring Log", 0);
    }

    @Test(priority = 5, enabled = true, groups = "regression")
    public void RowsPerPage() throws InterruptedException, IOException {
        RowsPerPage1(ssmMPNLogTable);
    }

    @Test(priority = 6, enabled = true, groups = "regression")
    public void PaginationSSM() throws InterruptedException, IOException {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        clickMPNTab();
        Pagination(ssmMPNLogTable, "Patho_2 System Monitoring Log");
    }

    @Test(priority = 7, enabled = true, groups = "regression")
    public void FieldAccessSSM() throws InterruptedException, IOException {
        SSMPage.openSSMPage();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        clickMPNTab();
        FieldAccessFunctionality(ssmMPNLogTable);
    }

    @Test(priority = 8, enabled = true, groups = "regression")
    public void FieldAccessKeyColumns() throws InterruptedException, IOException {
        KeyColumnsCheck(ssmMPNLogTable, new String[]{"Laboratory Sample ID", "Collection Date", "Farm Name", "Farm ID", "Farm External ID", "House", "House ID", "House External ID"});
    }

    @Test(priority = 9, enabled = true, groups = "regression")
    public void VerifyColumns() throws IOException, InterruptedException {
        clickMPNTab();
        VerifyAllColumns(ssmMPNLogTable, new String[]{"Laboratory Sample ID", "Work Order", "Source Sample Matrix", "Sample Matrix", "Sample Type", "Collection Date", "Sampled By", "Requested Assay", "Sample Status", "Farm Name", "Farm ID", "Farm External ID", "House", "House ID", "House External ID", "Pen", "Sample Analysis", "Qualifier", "Result", "Dilution", "Type", "Asset Age", "Serotype", "Result Date", "Date Received", "House Region", "Plant", "Line"});
    }

    @Test(priority = 10, enabled = true, groups = "regression")
    public void VerifyExportCSVFunctionality() throws IOException, InterruptedException {
        SSMPage.openSSMPage();
        clickMPNTab();
        CSVExport1("Patho_2 System Monitoring Log", ssmMPNCSVFileName, ssmMPNLogTable, 1, 0);
    }


    @AfterTest (groups = {"smoke","regression"})
    public static void endreport() {
        extent.flush();
    }
}


