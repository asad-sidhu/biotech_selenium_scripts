package Test.BiotechProject01.Administration;

import MiscFunctions.*;
import PageObjects.FormManagementPage;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.*;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Config.BaseTest;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.Constants.url_surveyAdmin;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;
import static PageObjects.FormManagementPage.*;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.loading_cursor;

public class FormManagement extends BaseTest {


    @BeforeTest
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Survey_Management" + date + ".html");
//        spark.config().setReportName("Survey Management Test Report");
        initReport("Administration_Survey_Management");
        DB_Config_DB.test();
    }

    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_surveyAdmin, "Form Management", SurveyManagementPageTitle);
    }

    @Test(description = "Test Case IE-7341: Verify that administration rights are working on survey management ", enabled = true, priority = 1)
    public void VerifyAdministrationRights() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.administrationRights();
        FormManagementPage.verifyAdminRights();
    }

    @Test(description = "Test Case: Verify toolTip is replaced with Field Access icon", enabled = true, priority = 2)
    public void testTooltip() throws IOException, InterruptedException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.verifyTooltip();
    }

    @Test(description = "Test Case: Verify wildCard functionality", enabled = true, priority = 3)
    public void wildCardFunctionality() throws InterruptedException, IOException {
        FormManagementPage surveyManagement = new FormManagementPage(getDriver());
        surveyManagement.wildCardFunctionality();
    }

    @Test(description = "Test Case: Verify verifyAllColumns functionality", enabled = false, priority = 4)
    public void verifyAllColumnsFunctionality() throws InterruptedException, IOException {
        FormManagementPage surveyManagement = new FormManagementPage(getDriver());
        surveyManagement.verifyAllColumns();
    }

    @Test(description = "Test Case: Verify RowsPerPage functionality", enabled = true, priority = 5)
    public void verifyRowsPerPageFunctionality() throws InterruptedException, IOException {
        FormManagementPage surveyManagement = new FormManagementPage(getDriver());
        surveyManagement.rowsPerPageFunctionality();
    }


    @Test(description = "Test Case IE-7465: Verify log view will have a delete icon", enabled = true, priority = 6)
    public void testPresenceOfDeleteIcon() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.presenceOfDeleteIcon();
    }


    @Test(description = "Test Case IE-7451: Verify cancel button of Editing popup", enabled = true, priority = 7)
    public void testCancelButtonOfEditingPopup() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.cancelButtonOfEditingPopup();
    }

    @Test(description = "Test Case IE-7450: Verify next button of Editing popup", enabled = false, priority = 8)
    public void testNextButtonOfEditingPopup() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.nextButtonOfEditingPopup();
    }

    @Test(description = "Test Case IE-7422: Verify back button of New Form when enabled", enabled = false, priority = 9)
    public void testBackButtonNewForm() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.backButtonNewForm();
    }

    @Test(description = "Test Case IE-7409: Verify next button of New Form when disabled", enabled = false, priority = 10)
    public void testDisabledNextButtonNewForm() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.disabledNextButtonNewForm();
    }

    @Test(description = "Test Case IE-7411: Verify exclude result toggle button is added", enabled = true, priority = 11)
    public void testExcludeResultToggleButtonPresence() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.excludeResultToggleButtonPresence();
    }

    @Test(description = "Test Case IE-7410: Verify clicking on next button will redirect to next screen", enabled = false, priority = 12)
    public void testNextButtonFunctionalityNewForm() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.nextButtonFunctionalityNewForm();
    }

    @Test(description = "Test Case IE-7408: Verify Cancel Button functionality on New Form", enabled = true, priority = 13)
    public void testCancelButtonOfNewForm() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.cancelButtonOfNewForm();
    }

    @Test(description = "Test Case IE-7407: Verify Add Category can be deleted on New Form", enabled = true, priority = 14)
    public void testDeleteAddCategoryNewForm() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.deleteAddCategoryNewForm();
    }

    @Test(description = "Test Case IE-7406: Verify that, add category will have an input field", enabled = true, priority = 15)
    public void testAddCategoryInputFieldsCheck() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.addCategoryInputFieldsCheck();
    }

    @Test(description = "Test Case IE-7399: Verify that, categories will be added on clicking new category", enabled = true, priority = 16)
    public void testAddCategories() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.addCategoriesNewForm();
    }

    @Test(description = "Test Case IE-7403: Verify that, category should have a weight factor of 100%", enabled = false, priority = 17)
    public void testCategoryWeightFactor() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.categoryWeightFactor();
    }

    @Test(description = "Test Case IE-7372: Verify that, General and Survey tabs will be shown", enabled = false, priority = 18)
    public void testTabsNewForm() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.tabsNewForm();
    }

    @Test(description = "Test Case IE-7339: Verify that, Profile icon is displayed and is enabled", enabled = true, priority = 19)
    public void testProfileSettingsIcon() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.profileSettingsIcon();
    }

    @Test(description = "Test Case IE-7430: Verify that the user has the ability to set form active/inactive", enabled = false, priority = 20)
    public void testActiveToggleButtonFunctionality() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.activeToggleButtonFunctionality();
    }

    @Test(description = "Test Case IE-7336: Verify reset default button functionality for Column Reordering", enabled = false, priority = 21)
    public void testFieldAccessColumnReordering() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.fieldAccessColumnReordering();
    }

    @Test(description = "Test Case IE-7371: Verify question will be asked to create what kind of form?", enabled = true, priority = 22)
    public void testVerifyTextQuestionForm() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.verifyTextQuestionForm();
    }

    @Test(description = "Test Case IE-7006: Verify that log view has all icons under Action Header", enabled = true, priority = 23)
    public void testActionHeaderIcons() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.actionHeaderIcons();
    }

    @Test(description = "Test Case IE-7370: Verify form will have two tabs", enabled = false, priority = 24)
    public void testVerifySurveyTabs() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.verifySurveyTabs();
    }

    @Test(description = "Test Case IE-7367: Verify that survey management is under IE administrator", enabled = true, priority = 25)
    public void testSurveyManagementPageScreenUnderIE() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.FormManagementScreenUnderIE();
    }

    @Test(description = "Test Case IE-7332: Verify lock/ unlock filter feature icon functionality", enabled = false, priority = 26)
    public void testLockAndUnlockFunctionality() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.lockAndUnlockFunctionality();
    }

    @Test(description = "Test Case IE-7328: Verify ascending/descending sorting on survey management screen", enabled = true, priority = 27)
    public void VerifySortingOnColumns() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.sortingOnColumns();
    }

    @Test(description = "Test Case IE-7328: Verify pagination on survey management screen", enabled = true, priority = 28)
    public void VerifyPagination() throws InterruptedException, IOException {
        FormManagementPage FormManagementPage = new FormManagementPage(getDriver());
        FormManagementPage.verifyPagination();
    }

    @Test(description = "Test Case IE-7415: Verify form created successfully on survey management screen", enabled = true, priority = 29)
    public void VerifyFormCreation() throws InterruptedException, IOException {
        FormManagementPage surveyManagement = new FormManagementPage(getDriver());
        surveyManagement.formCreation();
    }

    @Test(enabled = false, priority = 30)
    public void VerifyActiveToggleButton() throws InterruptedException, IOException {
        FormManagementPage surveyManagement = new FormManagementPage(getDriver());
        surveyManagement.formActiveToggleButton();
    }

    @Test(enabled = false, priority = 31)
    public void VerifyFormView() throws InterruptedException, IOException {
        FormManagementPage surveyManagement = new FormManagementPage(getDriver());
        surveyManagement.formView();
    }

    @Test(description = "Test Case IE-7415: Verify form updated successfully on survey management screen", enabled = true, priority = 32)
    public void VerifyFormUpdate() throws InterruptedException, IOException {
        FormManagementPage surveyManagement = new FormManagementPage(getDriver());
        surveyManagement.formUpdate();
    }

    @Test(description = "Test Case IE-7415: Verify form deleted successfully on survey management screen", enabled = true, priority = 33)
    public void VerifyFormDelete() throws InterruptedException, IOException {
        FormManagementPage surveyManagement = new FormManagementPage(getDriver());
        surveyManagement.formDelete();
    }

    @AfterTest
    public static void endreport() {
        extent.flush();
    }
}


