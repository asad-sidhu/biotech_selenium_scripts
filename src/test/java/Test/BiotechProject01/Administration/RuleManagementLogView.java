package Test.BiotechProject01.Administration;

import Config.BaseTest;
import MiscFunctions.DB_Config_DB;
import MiscFunctions.DB_Config_DW;
import MiscFunctions.NavigateToScreen;
import PageObjects.RuleManagementPage;
import Test.BiotechProject01.Login.LoginTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.Constants.url_ruleAdmin;
import static MiscFunctions.DateUtil.date;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;
import static PageObjects.RuleManagementPage.ruleManagementTitle;

public class RuleManagementLogView extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Rule_Management_LogView_" + date + ".html");
//        spark.config().setReportName("Rule Management Test Report");
        initReport("Administration_Rule_Management_LogView");

        DB_Config_DW.test();
        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_ruleAdmin, "Rule Management", ruleManagementTitle);
    }


    @Test(priority = 2, enabled = true, groups = {"smoke", "regression"})
    public void VerifyLockFilterFunctionality() throws InterruptedException, IOException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.lockFilterFunctionality();
    }


    @Test(priority = 3, enabled = true, groups = {"regression"})
    public void VerifyWildcardFunctionality() throws IOException, InterruptedException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.wildcardFunctionality();
    }


    @Test(priority = 4, enabled = true, groups = {"smoke","regression"})
    public void VerifySortingFunctionality() throws IOException, InterruptedException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.sortingFunctionality();
    }


    @Test(priority = 5, enabled = true, groups = {"regression"})
    public void VerifyPaginationFunctionality() throws IOException, InterruptedException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.paginationFunctionality();
    }


    @Test(priority = 6, enabled = true, groups = {"regression"})
    public void VerifyRowsPerPageFunctionality() throws IOException, InterruptedException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.rowsPerPageFunctionality();
    }


    @Test(priority = 7, enabled = true, groups = {"smoke", "regression"})
    public void VerifyColumns() throws IOException, InterruptedException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.verifyColumnsFunctionality(new String[]{"Rule Name", "Rule Type", "Rule", "Logic", "Description", "Sites", "Start Date", "End Date", "Rule Start Date", "Rule End Date"});
    }


    @Test(priority = 8, enabled = true, groups = {"regression"})
    public void VerifyFieldAccessFunctionality() throws IOException, InterruptedException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.verifyFieldAccessFunctionality();
    }


    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}
