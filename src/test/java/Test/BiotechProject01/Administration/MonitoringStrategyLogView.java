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

public class MonitoringStrategyLogView extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
        initReport("Administration_Monitoring_Strategy_LogView");
        DB_Config_DW.test();
        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_strategyManagement, "Monitoring Strategy", monitoringStrategyTitle);
    }


    @Test(priority = 1, enabled = true, groups = {"smoke", "regression"})
    public void VerifyTemplateLockFilterFunctionality() throws InterruptedException, IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateLockFilterFunctionality();
    }

    @Test(priority = 2, enabled = true, groups = {"regression"})
    public void VerifyTemplateWildcardFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateWildcardFunctionality();
    }

    @Test(priority = 3, enabled = true, groups = {"smoke", "regression"})
    public void VerifyTemplateSortingFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateSortingFunctionality();
    }

    @Test(priority = 4, enabled = true, groups = {"regression"})
    public void VerifyTemplatePaginaitonFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templatePaginationFunctionality();
    }

    @Test(priority = 5, enabled = true, groups = {"regression"})
    public void VerifyTemplateRowsPerPageFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateRowsPerPageFunctionality();
    }

    @Test(priority = 6, enabled = true, groups = {"regression"})
    public void VerifyColumnsTemplateTab() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateVerifyColumnsFunctionality(new String[]{"Monitoring Template Name", "Monitoring Type", "# Collection Intervals", "Performance Criteria"});
    }


    @Test(priority = 7, enabled = true, groups = {"regression"})
    public void VerifyTemplateFieldAccessFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyTemplateFieldAccessFunctionality();
    }


    @Test(priority = 8, enabled = true, groups = {"smoke", "regression"})
    public void VerifyPlanLockFilterFunctionality() throws InterruptedException, IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planLockFilterFunctionality();
    }

    @Test(priority = 9, enabled = true, groups = {"regression"})
    public void VerifyPlanWildcardFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planWildcardFunctionality();
    }

    @Test(priority = 10, enabled = true, groups = {"smoke", "regression"})
    public void VerifyPlanSortingFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planSortingFunctionality();
    }

    @Test(priority = 11, enabled = true, groups = {"regression"})
    public void VerifyPlanPaginaitonFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planPaginationFunctionality();
    }

    @Test(priority = 12, enabled = true, groups = {"regression"})
    public void VerifyPlanRowsPerPageFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planRowsPerPageFunctionality();
    }

    @Test(priority = 13, enabled = true, groups = {"regression"})
    public void VerifyColumnsPlanTab() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planVerifyColumnsFunctionality(new String[]{"Monitoring Plan Name", "Monitoring Type", "# Collection Intervals", "Performance Criteria", "Complex", "Start Date", "End Date", "Monitoring Template Name"});
    }


    @Test(priority = 14, enabled = true, groups = {"regression"})
    public void VerifyPlanFieldAccessFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyPlanFieldAccessFunctionality();
    }

    @Test(priority = 15, enabled = false, groups = {"regression"})
    public void VerifyTemplateTabAccessRightsFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyTemplateTabAccessRightsFunctionality();
        msp.verifyTemplateTabAdminRightsFunctionality();
    }

    @Test(priority = 16, enabled = false, groups = {"regression"})
    public void VerifyPlanTabAccessRightsFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyPlanTabAccessRightsFunctionality();
        msp.verifyPlanTabAdminRightsFunctionality();
    }

    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}
