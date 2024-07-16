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
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.loading_cursor;
import static PageObjects.SSMPage.*;

public class SSMLog extends BaseTest {

    @BeforeTest (groups = {"smoke","regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/SSM_Log" + DateUtil.date + ".html");
//        spark.config().setReportName("SSM Log Test Report");
        initReport("SSM_Log");
    }

    @BeforeClass (groups = {"smoke","regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        openSSMPage();
    }

    @Test(priority = 2, enabled = true, groups = {"smoke","regression"})
    public void LockFilter() throws InterruptedException, IOException {
        Lock(ssmLogTable, "Patho_2 System Monitoring", 1);
    }

    @Test(priority = 3, groups = "regression")
    public void WildcardSSM() throws InterruptedException, IOException {
        Wildcard(ssmLogTable, "Patho_2 System Monitoring", 2);
    }

    @Test(priority = 4, groups = {"smoke","regression"})
    public void FilterSorting() throws InterruptedException, IOException {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Sorting(ssmLogTable, "Patho_2 System Monitoring", 2);
    }

    @Test(priority = 5, enabled = true, groups = "regression")
    public void RowsPerPage() throws InterruptedException, IOException {
        RowsPerPage1(ssmLogTable);
    }

    @Test(priority = 6, enabled = true, groups = "regression")
    public void PaginationSSM() throws InterruptedException, IOException {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Pagination(ssmLogTable, "Patho_2 System Monitoring");
    }

    @Test(priority = 7, enabled = true, groups = "regression")
    public void FieldAccessSSM() throws InterruptedException, IOException {
        SSMPage.openSSMPage();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        FieldAccessFunctionality(ssmLogTable);
    }

    @Test(priority = 8, enabled = true, groups = "regression")
    public void FieldAccessKeyColumns() throws InterruptedException, IOException {
        KeyColumnsCheck(ssmLogTable, new String[]{"Laboratory Sample ID", "Collection Date", "File ID", "Analyzed By", "Farm ID", "Farm External ID", "House ID", "House External ID", "House ID", "House External ID", "Complex", "Complex External ID", "Complex ID"});
    }

    @Test(priority = 9, enabled = true, groups = "regression")
    public void VerifyColumns() throws IOException {
        VerifyAllColumns(ssmLogTable, new String[]{"Laboratory Sample ID", "Work Order", "Source Sample Matrix", "Sample Matrix", "Sample Type", "Collection Date", "Result Date", "Sampled By", "Requested Assay", "Sample Status", "Farm Name", "House", "Pen", "Result", "Instrument", "File ID", "Analyzed By", "Farm ID", "Farm External ID", "House ID", "House External ID", "Complex", "Complex External ID", "Complex ID", "Type", "Asset Age", "Customer Sample ID", "Date Received", "Collected By", "Organization", "House Region", "Plant", "Line", "Hatchery", "Hatchery ID", "Hatchery External ID", "Asset ID", "deployment Date"});
    }

    @Test(priority = 10, enabled = true, groups = "regression")
    public void VerifyExportCSVFunctionality() throws IOException, InterruptedException {
        SSMPage.openSSMPage();
        CSVExport1("Patho_2 System Monitoring", ssmCSVFileName, ssmLogTable, 1, 0);
    }


    @AfterTest (groups = {"smoke","regression"})
    public static void endreport() {
        extent.flush();
    }
}
