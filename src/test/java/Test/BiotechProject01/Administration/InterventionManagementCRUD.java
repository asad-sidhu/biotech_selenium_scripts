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

public class InterventionManagementCRUD extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
        initReport("Administration_Intervention_Management_CRUD");
        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        InterventionManagementPage.navigateInterventionMgtScreen();
    }

    @Test(priority = 2, enabled = true, description = "TC: IE-9397, 9281, 9282", groups = {"smoke", "regression"})
    public void VerifyCreateInterventionFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.createIntervention();
    }

    @Test(priority = 3, enabled = true, description = "TC: IE-9397, 9281, 9282", groups = {"smoke", "regression"})
    public void VerifyUpdateInterventionFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.updateIntervention();
    }

    @Test(priority = 4, enabled = true, description = "TC: IE-9397, 9281, 9282")
    public void VerifyDeleteInterventionFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.deleteIntervention();
    }


    @Test(priority = 5, enabled = true, description = "TC: IE-9397, 9281, 9282", groups = {"regression"})
    public void VerifyCreateInterventionLogFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.createInterventionLog();

    }

    @Test(priority = 6, enabled = true, description = "TC: IE-9397, 9281, 9282", groups = {"regression"})
    public void VerifyUpdateInterventionLogFunctionality() throws IOException, InterruptedException, SQLException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.updateInterventionLog();
    }

    @Test(priority = 7, enabled = true, description = "TC: IE-9395", groups = {"regression"})
    public void VerifySameNameInterventionFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.sameNameIntervention();
    }

    @Test(priority = 8, enabled = true, description = "TC: IE-9416", groups = {"regression"})
    public void VerifyInlineEditInterventionFunctionality() throws IOException, InterruptedException, SQLException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.inlineEditIntervention();
    }

    @Test(priority = 9, enabled = true, description = "TC: IE-9467", groups = {"regression"})
    public void VerifyInlineEditInterventionCheckActions() throws IOException, InterruptedException, SQLException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.inlineEditInterventionCheckActions();
    }

    @Test(priority = 10, enabled = false, description = "TC: IE-9430", groups = {"regression"})
    public void VerifyInlineEditNavigateToScreen() throws IOException, InterruptedException, SQLException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.inlineEditNavigateToScreen();
    }


    @Test(priority = 11, enabled = false, description = "TC: IE-9446", groups = {"regression"})
    public void VerifyInlineEditAudit() throws IOException, InterruptedException, SQLException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.inlineEditAudit();
    }

    @Test(priority = 12, enabled = true, description = "TC:9272, TC:9273", groups = {"regression"})
    public void VerifyMandatoryChecksFunctionality() throws IOException, InterruptedException, SQLException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyMandatoryChecksFunctionality();
    }

    @Test(priority = 13, enabled = true, description = "TC:9167, 9168, 9170, 9171", groups = {"regression"})
    public void VerifyToggleButtonFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyToggleButtonFunctionality();
    }

    @Test(priority = 14, enabled = true, description = "TC:9166", groups = {"regression"})
    public void VerifyMultipleAttributesIntervention() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyMultipleAttributesIntervention();
    }

    @Test(priority = 15, enabled = true, description = "TC:9172", groups = {"smoke", "regression"})
    public void VerifyCreateInterventionAllAttributes() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.createInterventionWithAllAttributes();
    }

    @Test(priority = 16, enabled = true, description = "TC:9173, 9174, 9179", groups = {"regression"})
    public void VerifyRequiredValidationsInterventionType() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.requiredValidationsInterventionType();
    }

    @Test(priority = 17, enabled = true, description = "TC:9175, 9176", groups = {"regression"})
    public void VerifyDragAndDropInterventionType() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.dragAndDropInterventionType();
    }

    @Test(priority = 18, enabled = false, description = "TC: IE-9411, 9412", groups = {"smoke", "regression"})
    public void VerifyInlineEditAccessRights() throws IOException, InterruptedException, SQLException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.AccessRights();
        LoginTest.login();
        imp.inlineEditAccessRights();
        imp.adminAccessRights();
        LoginTest.login();
    }

    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}