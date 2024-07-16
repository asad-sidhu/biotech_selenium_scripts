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
import static MiscFunctions.Constants.url_ruleAdmin;
import static MiscFunctions.DateUtil.date;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;
import static PageObjects.RuleManagementPage.ruleManagementTitle;

public class RuleManagementCRUD extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
      //  spark = new ExtentSparkReporter("target/Reports/Administration_Rule_Management_CRUD_" + date + ".html");
     //   spark.config().setReportName("Rule Management Test Report");
        initReport("Administration_Rule_Management_CRUD");
        DB_Config_DW.test();
        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_ruleAdmin, "Rule Management", ruleManagementTitle);
    }

    @Test(priority = 1, enabled = true, groups = {"smoke", "regression"})
    public void VerifyNavigateRuleManagementPage() throws IOException, InterruptedException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.navigateRuleManagementScreen();
    }

    @Test(priority = 2, enabled = true, groups = {"smoke", "regression"})
    public void CreateRule() throws IOException, InterruptedException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.createRule();
    }

    @Test(priority = 3, enabled = true, groups = {"smoke", "regression"})
    public void EditRule() throws IOException, InterruptedException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.editRule();
    }

    @Test(priority = 4, enabled = true, groups = {"regression"})
    public void ViewRule() throws IOException, InterruptedException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.viewRule();
    }

    @Test(priority = 5, enabled = true, groups = {"smoke", "regression"})
    public void DeleteRule() throws IOException, InterruptedException {
        RuleManagementPage rmp = new RuleManagementPage(getDriver());
        rmp.deleteRule();
    }

    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}
