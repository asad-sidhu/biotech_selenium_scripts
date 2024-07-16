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
import static PageObjects.SSMPage.*;

public class DeepSerotypingLog extends BaseTest {

    @BeforeTest (groups = {"smoke","regression"})
    public void extent() throws InterruptedException, IOException {
     //   spark = new ExtentSparkReporter("target/Reports/DeepSerotyping_Log" + DateUtil.date + ".html");
      //  spark.config().setReportName("Deep Serotyping Log Test Report");
        initReport("DeepSerotyping_Log");

    }

    @BeforeClass (groups = {"smoke","regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        openSSMPage();
    }

    @Test (priority = 2, enabled = true, groups = {"smoke","regression"})
    public void LockFilterDeepSerotype() throws InterruptedException, IOException {
        clickDeepSerotypingTab();
        getScreenshot();
        Lock(ssmDeepSerotypingLogTable, "Patho_2 System Monitoring Log", 0);
    }

    @Test(priority = 3, groups = "regression")
    public void WildcardSSM() throws InterruptedException, IOException {
        clickDeepSerotypingTab();
        Wildcard(ssmDeepSerotypingLogTable, "Patho_2 System Monitoring Log", 0);
    }

    @Test(priority= 4, groups = {"smoke","regression"})
    public void FilterSorting() throws InterruptedException, IOException {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        clickDeepSerotypingTab();
        Sorting(ssmDeepSerotypingLogTable, "Patho_2 System Monitoring Log", 0);
    }

    @Test(priority = 5, enabled = true, groups = "regression")
    public void RowsPerPage() throws InterruptedException, IOException {
        RowsPerPage1(ssmDeepSerotypingLogTable);
    }

    @Test(priority = 6, enabled = true, groups = "regression")
    public void PaginationSSM() throws InterruptedException, IOException {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        clickDeepSerotypingTab();
        Pagination(ssmDeepSerotypingLogTable, "Patho_2 System Monitoring Log");
    }

    @Test(priority = 7, enabled = true, groups = "regression")
    public void FieldAccessSSM() throws InterruptedException, IOException {
        SSMPage.openSSMPage();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        clickDeepSerotypingTab();
        FieldAccessFunctionality(ssmDeepSerotypingLogTable);
    }

    @Test(priority = 8, enabled = true, groups = "regression")
    public void FieldAccessKeyColumns() throws InterruptedException, IOException {
        KeyColumnsCheck(ssmDeepSerotypingLogTable, new String[]{"Laboratory Sample ID", "Collection Date", "Farm Name", "Farm ID", "Farm External Site ID", "House", "House ID", "House External Site ID", "Analyzed By"});
    }

    @Test(priority = 9, enabled = true, groups = "regression")
    public void VerifyColumns() throws IOException {
        VerifyAllColumns(ssmDeepSerotypingLogTable, new String[]{"Laboratory Sample ID", "Work Order", "Source Sample Matrix", "Sample Matrix", "Sample Type", "Collection Date", "Sampled By", "Requested Assay", "Sample Status", "Farm Name", "Farm ID","Farm External ID", "House", "House ID", "House External ID", "Pen", "Spacer", "Perfect Score", "Truncated Scores", "SNP Score", "Shared", "Sequence", "Sequence Overflow", "Full Serotype Name", "Ratio % Serotype", "Analyzed By", "Result Date", "Type", "Asset Age", "Customer Sample ID", "Date Received", "Collected By", "Organization", "House Region", "Plant", "Line", "Hatchery", "Hatchery ID", "Hatchery External ID", "Asset ID", "deployment Date"});
    }

    @Test(priority = 10, enabled = true, groups = "regression")
    public void VerifyExportCSVFunctionality() throws IOException, InterruptedException {
        SSMPage.openSSMPage();
        clickDeepSerotypingTab();
        CSVExport1("Patho_2 System Monitoring Log", deepSerotypeCSVFileName, ssmDeepSerotypingLogTable, 1, 0);
    }


    @AfterTest (groups = {"smoke","regression"})
    public static void endreport() {
        extent.flush();
    }
}


