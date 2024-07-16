package Test.BiotechProject01.Administration;

import Config.BaseTest;
import MiscFunctions.DB_Config_DB;
import PageObjects.InterventionManagementPage;
import Test.BiotechProject01.Login.LoginTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.DateUtil.date;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;

public class InterventionManagementLogView extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
      //  spark = new ExtentSparkReporter("target/Reports/Administration_Intervention_Management" + date + ".html");
      //  spark.config().setReportName("Intervention Management Test Report");
        initReport("Administration_Intervention_Management_LogView");
        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        InterventionManagementPage.navigateInterventionMgtScreen();
    }

    @Test(priority = 1, enabled = true, groups = {"smoke", "regression"})
    public void VerifyLockFilterFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.lockFilterFunctionality();
    }

    @Test(priority = 2, enabled = true, groups = {"regression"})
    public void VerifyWildcardFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.wildcardFunctionality();
    }

    @Test(priority = 3, enabled = true, description = "TC:9252, 9407", groups = {"smoke", "regression"})
    public void VerifySortingFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.sortingFunctionality();
    }
    @Test(priority = 4, enabled = true, description = "TC:9253", groups = {"regression"})
    public void VerifyPaginationFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyPaginationFunctionality();
    }

    @Test(priority = 5, enabled = true, description = "TC:9253", groups = {"regression"})
    public void VerifyRowsPerPageFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyRowsPerPageFunctionality();
    }


    @Test(priority = 6, enabled = true, description = "TC:9468", groups = {"regression"})
    public void VerifyFieldAccessFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyFieldAccessFunctionality();
    }

    @Test(priority = 7, enabled = true, description = "TC:9250", groups = {"regression"})
    public void VerifyTooltipFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyToolTipFunctionality();
    }

    @Test(priority = 8, enabled = true, description = "TC:9278, 9279", groups = {"regression"})
    public void VerifyViewInterventionDropdownFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.viewInterventionDropdownFunctionality();
    }


    @Test(priority = 9, enabled = true, description = "TC: IE-9468, TC: IE-9402", groups = {"regression"})
    public void VerifyIconsPresenceOnInlineEditFunctionality() throws IOException, InterruptedException, SQLException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.iconsPresenceOnInlineEditFunctionality();
    }

    @Test(priority = 10, enabled = true, groups = {"regression"})
    public void VerifyAllColumnsInterventionLog() throws IOException, InterruptedException, SQLException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyAllInterventionLogColumns();
    }

    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}