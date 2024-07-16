package Test.BiotechProject01.Administration;

import Config.BaseTest;
import MiscFunctions.DB_Config_DB;
import MiscFunctions.DB_Config_DW;
import MiscFunctions.NavigateToScreen;
import PageObjects.MonitoringStrategyPage;
import Test.BiotechProject01.Login.LoginTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.Constants.url_strategyManagement;
import static MiscFunctions.DateUtil.date;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;
import static PageObjects.MonitoringStrategyPage.monitoringStrategyTitle;

public class MonitoringStrategyCRUD extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Monitoring_Strategy_CRUD" + date + ".html");
//        spark.config().setReportName("Monitoring Strategy Test Report");
        initReport("Administration_Monitoring_Strategy_CRUD");
        DB_Config_DW.test();
        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_strategyManagement, "Monitoring Strategy", monitoringStrategyTitle);
    }

    @Test(priority = 1, enabled = true, groups = {"smoke", "regression"})
    public void VerifyCreateTemplate() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.createNewTemplate();
    }


    @Test(priority = 2, enabled = true, groups = {"regression"})
    public void VerifyCreateTemplateFromDB() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyCreatedTemplateFromDB();
    }

    @Test(priority = 3, enabled = true, groups = {"regression"})
    public void ViewCreatedTemplate() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.viewCreatedTemplate();
    }

    @Test(priority = 4, enabled = true, groups = {"regression"})
    public void EditCreatedTemplate() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.editCreatedTemplate();
    }

    @Test(priority = 5, enabled = true, description = "IE-9620, IE-9624", groups = {"regression"})
    public void VerifyTemplateConfigurationDisplays() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifySelectingTemplateDisplaysConfiguration();
    }


    @Test(priority = 6, enabled = true, groups = {"smoke", "regression"})
    public void VerifyCreatePlanFromTemplate() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.createPlanFromTemplate();
    }

    @Test(priority = 7, enabled = true, groups = {"regression"})
    public void VerifyCreatePlanFromDB() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyCreatedPlanFromDB();
    }

    @Test(priority = 8, enabled = false, description = "IE-9813", groups = {"regression"})
    public void VerifyPlanPresentInDropdownAfterChange() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planPresentInDropdownAfterChange();
    }

    @Test(priority = 9, enabled = true, groups = {"regression"})
    public void VerifyViewPlanFromListIcon() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyClickListIconFunctionality();
    }


    @Test(priority = 10, enabled = true, groups = {"regression"})
    public void CopyCreatedTemplate() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.copyCreatedTemplate();
    }

    @Test(priority = 11, enabled = true, groups = {"regression"})
    public void ViewCreatedPlan() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.viewCreatedPlan();
    }

    @Test(priority = 12, enabled = true, groups = {"regression"})
    public void EditCreatedPlan() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.editCreatedPlan();
    }

    @Test(priority = 13, enabled = true, groups = {"smoke", "regression"})
    public void DeleteCreatedPlan() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.deleteCreatedPlan();
    }

    @Test(priority = 14, enabled = true, groups = {"smoke", "regression"})
    public void DeleteCreatedTemplate() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.deleteCreatedTemplate();
    }

    @Test(priority = 15, enabled = true, description = "IE-9616", groups = {"regression"})
    public void VerifyAllTemplatesInPlanTemplateDropdown() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyTemplatesInPlanDropdown();
    }


    @Test(priority = 16, enabled = true, description = "IE-", groups = {"regression"})
    public void VerifyAllValidationsOnAddDaysFields() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyValidationsOnDaysFields();
    }

    @Test(priority = 17, enabled = true, description = "IE-9629", groups = {"regression"})
    public void VerifyAllComplexAssignedToUserDisplays() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.complexAssignedToUserDisplays();
    }

    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}
