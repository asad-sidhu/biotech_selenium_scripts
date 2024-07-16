package Test.BiotechProject01.Administration;

import Config.BaseTest;
import MiscFunctions.*;
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
import static LogViewFunctions.RowsPerPage.RowsPerPage_;
import static MiscFunctions.Constants.url_userManagement;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;
import static MiscFunctions.Methods.waitElementInvisible;
import static MiscFunctions.Methods.waitElementVisible;
import static PageObjects.BasePage.loading_cursor;
import static PageObjects.UserManagementPage.*;

public class UserManagementLogView extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_User_Management_LogView" + DateUtil.date + ".html");
//        spark.config().setReportName("User Management Test Report");
        initReport("Administration_User_Management_LogView");
        DB_Config_DB.test();
        DB_Config_DW.test();
    }


    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_userManagement, "User Management", userManagementTitle);
    }

    @Test(priority= 1, enabled = true, groups = {"smoke"})
    public void VerifyFieldAccess() throws IOException, InterruptedException {
        FieldAccessFunctionality(userManagementTable);
    }

    @Test(priority= 2, enabled = true, groups = {"smoke"})
    public void VerifyKeyColumnsCheck() throws IOException, InterruptedException {
        KeyColumnsCheck(userManagementTable, new String[]{"Email", "Organization Type", "Organization", "Role"});
    }


    @Test(priority = 3, groups = {"smoke", "regression"})
    public void LockFilter() throws InterruptedException, IOException {
//        getDriver().get(Constants.url_userManagement);
//        waitElementInvisible(loading_cursor);
        waitElementVisible(usercreateButton);
        Thread.sleep(3000);
        Lock(userManagementTable, "User Management", 0);
    }


    @Test(priority = 4, groups = {"smoke"})
    public void WildcardUser() throws InterruptedException, IOException {
        getDriver().get(Constants.url_userManagement);
        waitElementInvisible(loading_cursor);
        waitElementVisible(usercreateButton);
        Thread.sleep(3000);
        Wildcard(userManagementTable, "User Management", 0);
    }


    @Test(priority = 5, groups = {"smoke", "regression"})
    public void sorting() throws InterruptedException, IOException {
        getDriver().get(Constants.url_userManagement);
        waitElementInvisible(loading_cursor);
        waitElementVisible(usercreateButton);
        Thread.sleep(3000);
        Sorting(userManagementTable, "User Management", 0);
    }

    @Test(priority = 6, groups = {"smoke"})
    public void pagination() throws InterruptedException, IOException {
        Pagination(userManagementTable, "User Management");
    }

    @Test(priority = 7, groups = {"smoke"})
    public void rowsperPage() throws InterruptedException, IOException {
        getDriver().get(Constants.url_userManagement);
        waitElementInvisible(loading_cursor);
        waitElementVisible(usercreateButton);
        Thread.sleep(3000);
        RowsPerPage_();
    }

    @Test(priority = 8, groups = {"smoke"})
    public void ExportCSV() throws InterruptedException, IOException {
        CSVExport1("User Management", userCSVFileName, userManagementTable, 5, 2);
    }

    @Test(priority = 9, groups = {"smoke"})
    public void ExportAuditCSV() throws InterruptedException, IOException {
        CSVAuditExport("User Management", userCSVAuditFileName, userManagementTable, false);
    }

    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}
