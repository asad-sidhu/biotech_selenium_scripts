package Test.BiotechProject01.Reports;

import java.io.IOException;

import PageObjects.EnterococcusLogPage;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static LogViewFunctions.FieldAccess.FieldAccessFunctionality;
import static LogViewFunctions.FieldAccess.KeyColumnsCheck;
import static LogViewFunctions.LogCSVExport.CSVExport1;
import static LogViewFunctions.LogPNGExport.PNGExport;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;
import static LogViewFunctions.RowsPerPage.RowsPerPage_;
import static LogViewFunctions.SiteNameFilter.SiteName;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.DateUtil.date;
import static PageObjects.EnterococcusLogPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static PageObjects.SSMPage.ssmDeepSerotypingLogTable;

public class EnterococcusLog extends BaseTest {

    @BeforeTest (groups = {"smoke","regression"})
    public void extent() throws InterruptedException, IOException {
      //  spark = new ExtentSparkReporter("target/Reports/Enterococcus_Log" + date + ".html");
      //  spark.config().setReportName("Enterococcus Log Test Report");
        initReport("Enterococcus_Log");

    }

    @BeforeClass (groups = {"smoke","regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        openEnterococcusLogPage();
    }


    @Test (priority = 2, enabled = true, groups = {"smoke","regression"})
    public void LockFilter() throws InterruptedException, IOException {
        click(By.id(ResetFilters));
        Lock(enterococcusLogTable, "Enterococcus Log", 2);
    }

    @Test(priority = 3, enabled = true, groups = "regression")
    public void WildcardEnterococcus() throws InterruptedException, IOException {
        EnterococcusLogPage.openEnterococcusLogPage();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(By.id(ResetFilters));
        Wildcard(enterococcusLogTable, "Enterococcus Log", 2);
    }

    @Test(priority= 4, groups = {"smoke","regression"})
    public void FilterSorting() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(By.id(ResetFilters));
        Sorting(enterococcusLogTable, "Enterococcus Log", 2);
    }

    @Test(priority = 5, enabled = true, groups = "regression")
    public void RowsPerPage() throws InterruptedException, IOException {
        click(By.id(ResetFilters));
        RowsPerPage1(enterococcusLogTable);
    }

    @Test(priority = 6, enabled = true, groups = "regression")
    public void PaginationEnterococcus() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(By.id(ResetFilters));
        Pagination(enterococcusLogTable, "Enterococcus Log");
    }

    @Test(priority = 7, enabled = true, groups = "regression")
    public void FieldAccessEnterococcus() throws InterruptedException, IOException {
        FieldAccessFunctionality( enterococcusLogTable);
    }

    @Test(priority = 8, enabled = true, groups = "regression")
    public void FieldAccessKeyColumns() throws InterruptedException, IOException {
        KeyColumnsCheck( enterococcusLogTable, new String[]{"CUSTOMER SAMPLE ID", "LABORATORY SAMPLE ID", "SAMPLE MATRIX", "DATE RECEIVED", "COLLECTION DATE"});
    }

    @Test(priority = 9, enabled = true, groups = "regression")
    public void VerifyColumns() throws IOException {
        VerifyAllColumns(enterococcusLogTable, new String[]{"Customer Sample ID", "Laboratory Sample ID", "Sample Matrix", "Date Received", "Collection Date", "Collection Time", "Asset Age", "Asset Age Unit", "Collected By", "Organization", "Farm Name", "House", "House Region", "Requested Assay", "Result", "Result Date", "Work Order", "Result Unit"});
    }

    @Test(priority = 13, enabled = true, groups = "regression")
    public void EnteroPNGExport() throws IOException, InterruptedException {
        click(By.id(ResetFilters));
        PNGExport("#dc-bar-chart-salm-png img", enteroPNGFileName);
    }

    @Test(priority = 14, enabled = true, groups = "regression")
    public void EnteroCSVExport() throws IOException, InterruptedException {
        click(By.id(ResetFilters));
        CSVExport1("Enterococcus Log", enteroCSVFileName, enterococcusLogTable, 4, 0);
    }

    @AfterTest (groups = {"smoke","regression"})
    public static void endreport() {
        extent.flush();
    }
}
