package PageObjects;

import Config.BaseTest;
import MiscFunctions.*;
import Models.ProgramManagementModel;
import com.aventstack.extentreports.gherkin.model.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import static Config.BaseTest.saveResult;


import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static Config.BaseTest.saveResult;
import static LogViewFunctions.FieldAccess.FieldAccessFunctionality;
import static LogViewFunctions.FieldAccess.KeyColumnsCheck;
import static LogViewFunctions.FilterLock.Lock;
import static LogViewFunctions.FilterSort.Sorting;
import static LogViewFunctions.FilterWildcard.Wildcard;
import static LogViewFunctions.Pagination.Pagination;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;

import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Queries.*;
import static MiscFunctions.Queries.getFarmNameAssignedToUser;
import static PageObjects.BasePage.*;
import static PageObjects.AssetManagementPage.*;
import static PageObjects.LoginPage.*;
import static PageObjects.ProgramManagementPage.*;

public class MonitoringStrategyPage {

    public static WebDriver driver;
    public static By monitoringStrategyTitle = By.id("Monitoring Strategy");
    public static String monitoringStrategyTemplateTable = "intervention-management-log";
    public static String monitoringStrategyPlanTable = "intervention-management-plans-log";

    public static By monitoringTemplateTab = By.cssSelector(".template-tab-px");
    public static By monitoringPlanTab = By.cssSelector(".plan-tab-px");
    public static By monitoringCreateNewTemplateButton = By.id("create-monitoring-strategy");
    public static By monitoringCreateNewPlanButton = By.id("create-treatment");
    public static By monitoringTemplateFieldAccessIcon = By.cssSelector("#" + monitoringStrategyTemplateTable + " #edit-field-access");
    public static By monitoringPlanFieldAccessIcon = By.cssSelector("#" + monitoringStrategyPlanTable + " #edit-field-access");

    public static By monitoringTemplateName = By.id("samplingPlanNameId");
    public static By monitoringTemplateNameMandatoryCheck = By.cssSelector("#samplingPlanNameId.has-error");
    public static By monitoringTemplateType = By.id("monitoringType-0");
    public static By monitoringTemplateTypeAddNew = By.xpath("//li[text() = 'Add New + ']");
    public static By monitoringTemplateTypeMandatoryCheck = By.cssSelector("#monitoringType-0.has-error");
    public static By monitoringTemplateTypeCSMSelect = By.xpath("//b[text() = 'CSM']");
    public static By monitoringTemplatePerformanceCriteriaToggle = By.cssSelector(".slider");
    public static By monitoringTemplateLabel = By.id("FieldLabelID-1");
    public static By monitoringTemplateLabelSelect = By.cssSelector("ul li.show");
    public static By monitoringTemplateAgeUnitSelect = By.cssSelector("#ageUnit .ng-arrow-wrapper");
    public static By monitoringTemplateAgeUnitSelectDay = By.xpath("//*[text() = 'Day' and @class = 'ng-option-label ng-star-inserted']");
    public static By monitoringTemplateTargetAge = By.id("num-targetDay-1");
    public static By monitoringTemplateMinAge = By.id("num-targetDayRangeMin-1");
    public static By monitoringTemplateMaxAge = By.id("num-targetDayRangeMax-1");

    public static By monitoringTemplatePlanMinAge = By.id("num-targetAgeRangeMin-1");
    public static By monitoringTemplatePlanMaxAge = By.id("num-targetAgeRangeMax-1");
    public static By monitoringTemplateMaxAgeValidationCheck = By.cssSelector("#num-targetDayRangeMax-1.has-error");
    public static By monitoringTemplateSamples = By.id("num-numSamples-1");
    public static By monitoringTemplateHouseThresholdTotalOPG = By.id("num-houseThresholdTotalOPG-1");
    public static By monitoringTemplateHouseThresholdTotalOPGValidationCheck = By.cssSelector("#num-houseThresholdTotalOPG-1.has-error");
    public static By monitoringTemplateComplexThresholdTotalOPG = By.id("num-complexThresholdTotalOPG-1");
    public static By monitoringTemplateComplexThresholdTotalOPGValidationCheck = By.cssSelector("#num-complexThresholdTotalOPG-1.has-error");
    public static By monitoringTemplateLowerLimitTotalOPG = By.id("num-lowerLimitTotalOPG-1");
    public static By monitoringTemplateUpperLimitTotalOPG = By.id("num-upperLimitTotalOPG-1");
    public static By monitoringTemplateUpperLimitTotalOPGValidationCheck = By.cssSelector("#num-upperLimitTotalOPG-1.has-error");
    public static By monitoringTemplateHouseThresholdLargeOPG = By.id("num-houseThresholdLargeOPG-1");
    public static By monitoringTemplateHouseThresholdLargeOPGValidationCheck = By.cssSelector("#num-houseThresholdLargeOPG-1.has-error");
    public static By monitoringTemplateComplexThresholdLargeOPG = By.id("num-complexThresholdLargeOPG-1");
    public static By monitoringTemplateComplexThresholdLargeOPGValidationCheck = By.cssSelector("#num-complexThresholdLargeOPG-1.has-error");
    public static By monitoringTemplateLowerLimitLargeOPG = By.id("num-lowerLimitLargeOPG-1");
    public static By monitoringTemplateUpperLimitLargeOPG = By.id("num-upperLimitLargeOPG-1");
    public static By monitoringTemplateUpperLimitLargeOPGValidationCheck = By.cssSelector("#num-upperLimitLargeOPG-1.has-error");
    public static By monitoringTemplateAddDaysButton = By.cssSelector(".monitoring-btn");
    public static By monitoringTemplateGetTableRowsCount = By.cssSelector("#" + monitoringStrategyTemplateTable + " #" + ResultsCount);
    public static By monitoringPlanGetTableRowsCount = By.cssSelector("#" + monitoringStrategyPlanTable + " #" + ResultsCount);
    public static String monitoringTemplateNameColumn = "#col-0 label";
    public static String monitoringMonitoringTypeColumn = "#col-1 label";
    public static String monitoringCollectionIntervalsColumn = "#col-3 label";
    public static String monitoringTemplatePerformanceCriteriaColumn = "#col-3 label";
    public static String monitoringTemplateEditIconPre = ".ml-1 #edit-intervention-gram-";
    public static String monitoringTemplateEditIconPost = "-monitoringStrategy";
    public static String monitoringTemplateCreatePlanIcon = "create-plan-monitoring-strategy-";
    public static String monitoringTemplateViewPlanIcon = "view-plan-monitoring-strategy-";
    public static String monitoringTemplateCopyIcon = "copy-monitoring-strategy-";
    public static String monitoringTemplateViewIconPre = "view-intervention-gram-";
    public static String monitoringTemplateViewIconPost = "-monitoringStrategy";
    public static String monitoringTemplateDeleteIconPre = "delete-intervention-management-";
    public static String monitoringTemplateDeleteIconPost = "-monitoringStrategy";
    public static By monitoringTemplatePopupDaysRowsCount = By.cssSelector(".popup-body tr.bg-box");
    public static String monitoringTemplateDaysPopupRowEditIconPre = ".popup-body tr:nth-child(";
    public static String monitoringTemplateDaysPopupRowEditIconPost = ") td:nth-child(6) img";
    public static String monitoringTemplateDaysPopupRowDeleteIconPre = ".popup-body tr:nth-child(";
    public static String monitoringTemplateDaysPopupRowDeleteIconPost = ") td:nth-child(6) i";

    //create plan locators
    public static By monitoringPlanName = By.id("samplingPlanNameId");
    public static By monitoringPlanNameMandatoryCheck = By.cssSelector("#samplingPlanNameId.has-error");
    public static By monitoringPlanTemplateTextField = By.cssSelector("#samplingPlanTemplateId input");
    public static By monitoringPlanTemplateSelect = By.cssSelector("#samplingPlanTemplateId .ng-option-label");
    public static By monitoringPlanTemplateDropDownExpand = By.cssSelector("#samplingPlanTemplateId .ng-arrow-wrapper");
    public static By monitoringPlanTemplateDropDownList = By.cssSelector(".ng-dropdown-panel-items .ng-option");
    public static By monitoringPlanTemplateNameGetText = By.cssSelector("#samplingPlanTemplateId .ng-value-label");
    public static By monitoringPlanComplexGetText = By.cssSelector("#compleSiteId .value-text");
    public static By monitoringPlanComplexDropdownExpand = By.cssSelector("#compleSiteId .toggle-list");

    public static String monitoringPlanViewIconPre = "#" + monitoringStrategyPlanTable + " #view-intervention-gram-";
    public static String monitoringPlanEditIconPre = "#" + monitoringStrategyPlanTable + " #edit-intervention-gram-";
    public static String monitoringPlanDeleteIconPre = "#" + monitoringStrategyPlanTable + " #delete-intervention-management-";
    public static String monitoringPlanIconPost = "-monitoringStrategy";
    public static String monitoringPlanNameColumn = "#col-0 label";
    public static String monitoringPlanCollectionIntervalColumn = "#col-2 label";
    public static String monitoringPlanTemplateNameColumn = "#col-7 label";
    public static String templateName = "TestComplexSite_" + dateYYYYMMDD + "_" + date0;

    // public String templateName = "TestComplexSite_20240216_4857";
    public static String planName = "TestPlan_" + dateYYYYMMDD + "_" + date0;
//    public static String reportingPlanName = "ReportingPlan";
    //  public String planName = "TestPlan_20230615_0726";
    public static String getTemplateQuery = "Select Count(samplingPlanTemplateName) as count from [dbo].[SamplingPlanTemplate] where samplingPlanTemplateName = '" + templateName + "' and isActive = 1";
    public static String getTemplateIDQuery = "Select samplingPlantemplateid from [dbo].[SamplingPlanTemplate] where samplingPlanTemplateName = '" + templateName + "' and isActive = 1";
    public static By rowsRecord = By.xpath("//table/tbody/tr");
    String targetRowText = "Admin";
    private By editRights = By.xpath(".//span[@title='Edit Rights']");

    @FindBy(how = How.CSS, using = "tr:nth-child(30) td:nth-child(2) input")
    private WebElement createRightsInput;
    private static By createRightsCheckbox = By.cssSelector("tr:nth-child(30) td:nth-child(2) .custom-checkbox");
    @FindBy(how = How.CSS, using = "tr:nth-child(30) td:nth-child(3) input")
    private WebElement updateRightsInput;
    private static By updateRightsCheckbox = By.cssSelector("tr:nth-child(30) td:nth-child(3) .custom-checkbox");
    @FindBy(how = How.CSS, using = "tr:nth-child(30) td:nth-child(4) input")
    private WebElement viewRightsInput;
    private static By viewRightsCheckbox = By.cssSelector("tr:nth-child(30) td:nth-child(4) .custom-checkbox");

    @FindBy(how = How.CSS, using = "tr:nth-child(31) td:nth-child(2) input")
    private WebElement createRightsInputPlanTab;
    private static By createRightsCheckboxPlanTab = By.cssSelector("tr:nth-child(31) td:nth-child(2) .custom-checkbox");
    @FindBy(how = How.CSS, using = "tr:nth-child(31) td:nth-child(3) input")
    private WebElement updateRightsInputPlanTab;
    private static By updateRightsCheckboxPlanTab = By.cssSelector("tr:nth-child(31) td:nth-child(3) .custom-checkbox");
    @FindBy(how = How.CSS, using = "tr:nth-child(31) td:nth-child(4) input")
    private WebElement viewRightsInputPlanTab;
    private static By viewRightsCheckboxPlanTab = By.cssSelector("tr:nth-child(31) td:nth-child(4) .custom-checkbox");
    private static By saveButtonEditRights = By.cssSelector(".fa-save");
    public static By templateViewIcon = By.id("view-intervention-gram-1-monitoringStrategy");
    public static By templateEditIcon = By.id("edit-intervention-gram-1-monitoringStrategy");

    public static By element = By.xpath("//*[contains(@class, 'selectable')][1]\n");


    public String returnTemplateIntervalsCount(String templateId) {
        String getTemplateIntervalsCount = "Select count(samplingPlantemplateid) as count from [dbo].[SamplingPlanIntervalTemplate] where samplingPlantemplateid = '" + templateId + "'";

        return getTemplateIntervalsCount;
    }

    public String getPlanQuery = "Select Count(samplingPlanId) as count from [dbo].[SamplingPlan] where samplingPlanName = '" + planName + "' and isActive = 1";
    public String getPlanIDQuery = "Select samplingPlanId from [dbo].[SamplingPlan] where samplingPlanName = '" + planName + "' and isActive = 1";

    public String returnPlanIntervalsCount(String planId) {
        String getPlanIntervalsCount = "Select count(samplingPlanId) as count from [dbo].[SamplingPlanInterval] where samplingPlanId = '" + planId + "'";
        return getPlanIntervalsCount;
    }


    public MonitoringStrategyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateMonitoringStrategyScreen() throws IOException, InterruptedException {
        NavigateToScreen.navigate(url_strategyManagement, "Monitoring Strategy", monitoringStrategyTitle);
    }

    public void templateLockFilterFunctionality() throws IOException, InterruptedException {
        click(monitoringTemplateTab);
        Lock(monitoringStrategyTemplateTable, "Monitoring Strategy", 0);
    }

    public void templateWildcardFunctionality() throws InterruptedException, IOException {
        click(monitoringTemplateTab);
        Wildcard(monitoringStrategyTemplateTable, "Monitoring Strategy", 0);
    }

    public void templateSortingFunctionality() throws InterruptedException, IOException {
        click(monitoringTemplateTab);
        Sorting(monitoringStrategyTemplateTable, "Monitoring Strategy", 0);
    }

    public void templatePaginationFunctionality() throws InterruptedException, IOException {
        click(monitoringTemplateTab);
        Pagination(monitoringStrategyTemplateTable, "Monitoring Strategy");
    }

    public void templateRowsPerPageFunctionality() throws InterruptedException, IOException {
        click(monitoringTemplateTab);
        RowsPerPage1(monitoringStrategyTemplateTable);
    }

    public void templateVerifyColumnsFunctionality(String[] columnNamesExpected) throws InterruptedException, IOException {
        click(monitoringTemplateTab);
        VerifyAllColumns(monitoringStrategyTemplateTable, columnNamesExpected);
    }

    public void verifyTemplateFieldAccessFunctionality() throws InterruptedException, IOException {
        FieldAccessFunctionality(monitoringStrategyTemplateTable);
        KeyColumnsCheck(monitoringStrategyTemplateTable, new String[]{"MONITORING TEMPLATE NAME", "MONITORING TYPE"});
    }

    public void planLockFilterFunctionality() throws IOException, InterruptedException {
        click(monitoringPlanTab);
        Lock(monitoringStrategyPlanTable, "Monitoring Strategy", 0);
    }

    public void planWildcardFunctionality() throws InterruptedException, IOException {
        click(monitoringPlanTab);
        Wildcard(monitoringStrategyPlanTable, "Monitoring Strategy", 0);
    }

    public void planSortingFunctionality() throws InterruptedException, IOException {
        click(monitoringPlanTab);
        Sorting(monitoringStrategyPlanTable, "Monitoring Strategy", 0);
    }

    public void planPaginationFunctionality() throws InterruptedException, IOException {
        click(monitoringPlanTab);
        Pagination(monitoringStrategyPlanTable, "Monitoring Strategy");
    }

    public void planRowsPerPageFunctionality() throws InterruptedException, IOException {
        click(monitoringPlanTab);
        RowsPerPage1(monitoringStrategyPlanTable);
    }

    public void planVerifyColumnsFunctionality(String[] columnNamesExpected) throws InterruptedException, IOException {
        click(monitoringPlanTab);
        VerifyAllColumns(monitoringStrategyPlanTable, columnNamesExpected);
    }

    public void verifyPlanFieldAccessFunctionality() throws InterruptedException, IOException {
        click(monitoringPlanTab);
        FieldAccessFunctionality(monitoringStrategyPlanTable);
        KeyColumnsCheck(monitoringStrategyPlanTable, new String[]{"MONITORING PLAN NAME", "COMPLEX", "MONITORING TEMPLATE NAME"});
    }


    public void addDays(String label, String targetAge, String minAge, String maxAge, String houseThreshold, String complexThreshold, String lowerLimit, String upperLimit) throws InterruptedException {
        clear(monitoringTemplateLabel);
        type(monitoringTemplateLabel, label);
        Thread.sleep(700);
        click(monitoringTemplateLabelSelect);
        click(monitoringTemplateAgeUnitSelect);
        click(monitoringTemplateAgeUnitSelectDay);
        clear(monitoringTemplateTargetAge);
        type(monitoringTemplateTargetAge, targetAge);
        Thread.sleep(2000);
        if (size(monitoringTemplateMinAge)!=0) {
            clear(monitoringTemplateMinAge);
            type(monitoringTemplateMinAge, minAge);
            clear(monitoringTemplateMaxAge);
            type(monitoringTemplateMaxAge, maxAge);
        }
        else {
            clear(monitoringTemplatePlanMinAge);
            type(monitoringTemplatePlanMinAge, minAge);
            Thread.sleep(2000);
            clear(monitoringTemplatePlanMaxAge);
            type(monitoringTemplatePlanMaxAge, maxAge);
        }
        Thread.sleep(2000);
        clear(monitoringTemplateSamples);
        type(monitoringTemplateSamples, "10");

        clear(monitoringTemplateHouseThresholdTotalOPG);
        type(monitoringTemplateHouseThresholdTotalOPG, houseThreshold);
        clear(monitoringTemplateComplexThresholdTotalOPG);
        type(monitoringTemplateComplexThresholdTotalOPG, complexThreshold);
        clear(monitoringTemplateLowerLimitTotalOPG);
        type(monitoringTemplateLowerLimitTotalOPG, lowerLimit);
        clear(monitoringTemplateUpperLimitTotalOPG);
        type(monitoringTemplateUpperLimitTotalOPG, upperLimit);

        clear(monitoringTemplateHouseThresholdLargeOPG);
        type(monitoringTemplateHouseThresholdLargeOPG, houseThreshold);
        clear(monitoringTemplateComplexThresholdLargeOPG);
        type(monitoringTemplateComplexThresholdLargeOPG, complexThreshold);
        clear(monitoringTemplateLowerLimitLargeOPG);
        type(monitoringTemplateLowerLimitLargeOPG, lowerLimit);
        clear(monitoringTemplateUpperLimitLargeOPG);
        type(monitoringTemplateUpperLimitLargeOPG, upperLimit);

        driver.findElement(monitoringTemplateAddDaysButton).click();
    }


    public void createNewTemplate() throws IOException {
        try {
            test = extent.createTest("AN-Template-20: Verify user can create a template");
            SoftAssert softAssert = new SoftAssert();

            BaseTest driver = new BaseTest();
            click(monitoringTemplateTab);
            click(monitoringCreateNewTemplateButton);

            //header title check
            softAssert.assertEquals(getText(popupHeaderTitle), "Create Monitoring Strategy Template");

            //mandatory fields check
            click(monitoringTemplateName);
            Thread.sleep(750);
            click(monitoringTemplateType);
            Thread.sleep(750);
            click(monitoringTemplateName);
            Thread.sleep(750);
            softAssert.assertEquals(size(monitoringTemplateNameMandatoryCheck), 1, "Mandatory field error not displayed for Name field");
            softAssert.assertEquals(size(monitoringTemplateTypeMandatoryCheck), 1, "Mandatory field error not displayed for Type field");
            softAssert.assertEquals(size(popupSubmitButtonDisabled), 1, "Submit button is disabled when mandatory fields are empty");

            //enter valid data
            type(monitoringTemplateName, templateName);
            Thread.sleep(500);

            //IE-9535
            type(monitoringTemplateType, "csm" + dateYYYYMMDD + "_" + date0);
            Thread.sleep(700);
            softAssert.assertEquals(size(monitoringTemplateTypeAddNew), 1, "Add new list item not displayed");

            clear(monitoringTemplateType);
            type(monitoringTemplateType, "CSM");
            Thread.sleep(750);
            click(monitoringTemplateTypeCSMSelect);

            //IE-9534
            softAssert.assertEquals(size(monitoringTemplatePerformanceCriteriaToggle), 1, "Performance Criteria toggle not displayed");

            //   System.out.println("After: "+driver.getDriver().findElement(By.cssSelector(".switch input.ng-untouched")).isEnabled());

            //verify user cannot save template without adding at least 1 day
            click(popupSubmitButton);
            softAssert.assertEquals(getText(alertMessage), "Add at least one day");

            addDays("Day 7", "7", "4", "10", "50%", "50%", "10000", "20000");
            addDays("Day 14", "14", "7", "20", "50%", "50%", "20000", "30000");
            addDays("Day 21", "21", "10", "30", "60%", "40%", "30000", "40000");
            addDays("Day 28", "28", "14", "40", "67%", "33%", "40000", "50000");
            addDays("Day 35", "35", "18", "50", "67%", "33%", "50000", "60000");
            addDays("Day 49", "49", "24", "60", "67%", "33%", "50000", "60000");

            click(popupSubmitButton);
            softAssert.assertEquals(getText(alertMessage), "Monitoring Strategy has been created successfully");

            String tableRowsCount = getText(monitoringTemplateGetTableRowsCount);
            for (int i = 0; i < Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#" + monitoringStrategyTemplateTable + " #row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName)) {
                    softAssert.assertTrue(true);
                    softAssert.assertEquals(getText(By.cssSelector("#" + monitoringStrategyTemplateTable + " #row-" + i + " " + monitoringTemplatePerformanceCriteriaColumn)), "Yes", "Performance Criteria should be Yes in case of CSM");
                    break;
                } else if (!getText(By.cssSelector("#" + monitoringStrategyTemplateTable + " #row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName) && i == Integer.parseInt(tableRowsCount)) {
                    softAssert.assertTrue(false, "Created template is not founded in table");
                    break;
                }
            }

            softAssert.assertAll();
            test.pass("New Template created successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("New Template failed to create");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("New Template failed to create");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void verifyCreatedTemplateFromDB() throws IOException {
        try {
            test = extent.createTest("AN-Template-21: Verify created template from DB");
            SoftAssert softAssert = new SoftAssert();

            if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev")) {
                ResultSet getCreatedTemplateResults = DB_Config_DB.getStmt().executeQuery(getTemplateQuery);
                while (getCreatedTemplateResults.next()) {
                    String templateCount = getCreatedTemplateResults.getString("count");
                    softAssert.assertEquals(templateCount, "1", "Template not displayed in DB");
                }

                ResultSet getCreatedTemplateIDResults = DB_Config_DB.getStmt().executeQuery(getTemplateIDQuery);
                while (getCreatedTemplateIDResults.next()) {
                    String templateID = getCreatedTemplateIDResults.getString("samplingPlantemplateid");
                    // ResultSet getCreatedTemplateIntervalResults = DB_Config_DB.getStmt().executeQuery("Select count(samplingPlantemplateid) as count from [dbo].[SamplingPlanIntervalTemplate] where samplingPlantemplateid = '" + templateID + "'");
                    ResultSet getCreatedTemplateIntervalResults = DB_Config_DB.getStmt().executeQuery(returnTemplateIntervalsCount(templateID));
                    while (getCreatedTemplateIntervalResults.next()) {
                        String templateIntervalCount = getCreatedTemplateIntervalResults.getString("count");
                        softAssert.assertEquals(templateIntervalCount, "6", "Template Intervals not displayed in DB");
                        break;
                    }
                    break;
                }
                softAssert.assertAll();
                test.pass("Created Template and Template Intervals saved successfully in DB");
                saveResult(ITestResult.SUCCESS, null);
            }
            if (Constants.config.url().contains("uat")) {
                test.skip("UAT DB cannot be accessed");
            }
        } catch (AssertionError er) {
            test.fail("New Template failed to create in DB");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("New Template failed to create in DB");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void viewCreatedTemplate() throws IOException {
        try {
            test = extent.createTest("AN-Template-21: Verify user can view created template from view icon");
            SoftAssert softAssert = new SoftAssert();

            String tableRowsCount = getText(monitoringTemplateGetTableRowsCount);
            for (int i = 0; i <= Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName)) {

                    String collectionIntervalCount = getText(By.cssSelector("#row-" + i + " " + monitoringCollectionIntervalsColumn));
                    int j = i + 1;
                    click(By.cssSelector("#" + monitoringStrategyTemplateTable + " #" + monitoringTemplateViewIconPre + j + monitoringTemplateViewIconPost));
                    //    click(By.id(monitoringTemplateViewIcon + j));
                    waitElementInvisible(loading_cursor);

                    softAssert.assertEquals(getText(popupHeaderTitle), "View Monitoring Strategy Template");
                    softAssert.assertEquals(size(monitoringTemplatePopupDaysRowsCount), Integer.parseInt(collectionIntervalCount), "Rows in view popup not equal to that shown in log view");
                    getScreenshot();
                    click(popupCloseButton);
                    softAssert.assertAll();
                    test.pass("Created Template View popup verified successfully");

                    saveResult(ITestResult.SUCCESS, null);
                    break;
                } else if (!getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName) && i == Integer.parseInt(tableRowsCount)) {
                    test.skip("Created template is not founded in table");
                    break;
                }
            }
        } catch (AssertionError er) {
            test.fail("View created template failed to verify");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("View created template failed to verify");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void editCreatedTemplate() throws IOException {
        try {
            test = extent.createTest("AN-Template-21: Verify user can edit created template");
            SoftAssert softAssert = new SoftAssert();

            String tableRowsCount = getText(monitoringTemplateGetTableRowsCount);

            for (int i = 0; i <= Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName)) {

                    String collectionIntervalCount = getText(By.cssSelector("#row-" + i + " " + monitoringCollectionIntervalsColumn));

                    int j = i + 1;
                    click(By.cssSelector(monitoringTemplateEditIconPre + j + monitoringTemplateEditIconPost));
                    waitElementInvisible(loading_cursor);

                    softAssert.assertEquals(getText(popupHeaderTitle), "Update Monitoring Strategy Template");
                    softAssert.assertEquals(size(monitoringTemplatePopupDaysRowsCount), Integer.parseInt(collectionIntervalCount), "Rows in view popup not equal to that shown in log view");

                    addDays("Day 63", "63", "36", "63", "67%", "33%", "60000", "70000");

                    int newRowAddedIndex = Integer.parseInt(collectionIntervalCount) + 1;

                    click(By.cssSelector(monitoringTemplateDaysPopupRowEditIconPre + newRowAddedIndex + monitoringTemplateDaysPopupRowEditIconPost));


                    addDays("Day 63", "61", "34", "61", "60%", "40%", "65000", "75000");

                    softAssert.assertEquals(size(alertMessage), 0, "Error Alert Message occuered");

                    //  System.out.println(monitoringTemplateDaysPopupRowDeleteIconPre + newRowAddedIndex + monitoringTemplateDaysPopupRowDeleteIconPost);
                    scroll(By.cssSelector(monitoringTemplateDaysPopupRowDeleteIconPre + newRowAddedIndex + monitoringTemplateDaysPopupRowDeleteIconPost));
                    click(By.cssSelector(monitoringTemplateDaysPopupRowDeleteIconPre + newRowAddedIndex + monitoringTemplateDaysPopupRowDeleteIconPost));
                    Thread.sleep(2000);
                    softAssert.assertEquals(size(alertMessage), 0, "Error Alert Message occuered");
                    softAssert.assertEquals(size(monitoringTemplatePopupDaysRowsCount), Integer.parseInt(collectionIntervalCount), "Days row deleted successfully");

                    //IE-9539 and IE-9540
                    click(popupCloseButton);
                    Thread.sleep(500);
                    softAssert.assertEquals(getText(popupConfirmationTitle), "Confirmation", "Confirmation Popup not displayed on clicking cross icon");
                    click(popupNoButton);
                    Thread.sleep(500);
                    click((popupCancelButton));
                    Thread.sleep(500);
                    softAssert.assertEquals(getText(popupConfirmationTitle), "Confirmation", "Confirmation Popup not displayed on clicking cancel button");
                    click(popupNoButton);
                    Thread.sleep(500);

                    click(popupSubmitButton);
                    waitElementInvisible(loading_cursor);
                    softAssert.assertEquals(getText(alertMessage), "Monitoring Strategy has been updated successfully");
                    softAssert.assertAll();
                    test.pass("Created Template edited successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                    break;
                } else if (!getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName) && i == Integer.parseInt(tableRowsCount)) {
                    test.skip("Created template is not founded in table");
                    break;
                }
            }
        } catch (AssertionError er) {
            test.fail("Template failed to edit");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Template failed to edit");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void complexAssignedToUserDisplays() throws IOException {
        try {
            test = extent.createTest("AN-MS-21: Verify all complex assigned to user are displayed in complex dropdown field");
            SoftAssert softAssert = new SoftAssert();
            BaseTest driver = new BaseTest();

            driver.getDriver().get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            click(monitoringCreateNewPlanButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            click(monitoringPlanComplexDropdownExpand);
            ResultSet getFarmNameResults = DB_Config_DB.getStmt().executeQuery(Queries.getComplexNameAssignedToUser(getUsersId()));

            String complexName = null;
            while (getFarmNameResults.next()) {
                complexName = getFarmNameResults.getString("siteName");

                clear(programComplexSearch);
                Thread.sleep(600);
                type(programComplexSearch, complexName);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1800);
                softAssert.assertNotEquals(size(By.cssSelector("#compleSiteId .selectable label b")), 0, complexName + " is assigned to user but not displayed in hierarchy");
                System.out.println("Complex Name: " + complexName);
            }
            softAssert.assertAll();
            test.pass("All complex assigned to user displayed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Complex assigned to user failed to display");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Complex assigned to user failed to display");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void verifySelectingTemplateDisplaysConfiguration() throws IOException {
        try {
            test = extent.createTest("AN-Template-21: Verify that selecting the template should then display the collection sample configuration for that template");
            SoftAssert softAssert = new SoftAssert();
            BaseTest driver = new BaseTest();

//            driver.getDriver().get(url_strategyManagement);
//            waitElementInvisible(loading_cursor);
//            Thread.sleep(3000);
            click(monitoringTemplateTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);

            String tableRowsCount = getText(monitoringTemplateGetTableRowsCount);

            for (int i = 0; i <= Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName)) {

                    String collectionIntervalCount = getText(By.cssSelector("#row-" + i + " " + monitoringCollectionIntervalsColumn));

                    click(monitoringCreateNewPlanButton);
                    waitElementInvisible(loading_cursor);

                    type(monitoringPlanTemplateTextField, templateName);
                    click(monitoringPlanTemplateSelect);
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(1500);
                    int configurationListSize = Integer.parseInt(collectionIntervalCount);
                    softAssert.assertEquals(size(alertMessage), 0, "Error Alert Message occuered");

                    softAssert.assertEquals(size(monitoringTemplatePopupDaysRowsCount), configurationListSize);


                    //Start Date
                    click(startDateField);
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(500);
                    Methods method = new Methods();
                    WebElement dateWidgetStart = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
                    List<WebElement> columnsStart = dateWidgetStart.findElements(By.tagName("button"));
                    clickGivenDay(columnsStart, getDay("28"));
                    Thread.sleep(500);

                    //End Date
                    click(endDateField);
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(500);
                    WebElement dateWidgetEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
                    List<WebElement> columnsEnd = dateWidgetEnd.findElements(By.tagName("button"));
                    clickGivenDay(columnsEnd, getDay("21"));
                    Thread.sleep(500);

                    softAssert.assertEquals(size(By.cssSelector("#endDate .dp-open")), 1, "User was able to select end date less than start date");
                    getScreenshot();
                    click(popupCloseButton);
                    softAssert.assertAll();
                    test.pass("Collection Configuration displayed successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                    break;
                } else if (!getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName) && i == Integer.parseInt(tableRowsCount)) {
                    test.skip("Created template is not founded in table");
                    break;
                }
            }
        } catch (AssertionError er) {
            test.fail("Collection Configuration failed to display");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Collection Configuration failed to display");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void createPlanFromTemplate() throws IOException {
        try {
            test = extent.createTest("AN-Template-21: Verify user can create Plan from created template");
            SoftAssert softAssert = new SoftAssert();
            BaseTest driver = new BaseTest();

            driver.getDriver().get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(monitoringTemplateTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);

            String tableRowsCount = getText(monitoringTemplateGetTableRowsCount);
            System.out.println(tableRowsCount);

            for (int i = 0; i <= Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName)) {

                    String collectionIntervalCount = getText(By.cssSelector("#row-" + i + " " + monitoringCollectionIntervalsColumn));

                    int j = i + 1;
                    click(By.id(monitoringTemplateCreatePlanIcon + j));
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(2000);
                    softAssert.assertEquals(getText(popupHeaderTitle), "Create Plan");

                    //IE-9618 - this testcase is marked as obsolete now
                /*    type(monitoringPlanName, planName + "[]");
                    Thread.sleep(800);
                    Thread.sleep(1200);
                    softAssert.assertEquals(size(monitoringPlanNameMandatoryCheck), 1, "User was able to enter invalid character");
                    clear(monitoringPlanName);
                    Thread.sleep(2000);*/

                    type(monitoringPlanName, planName);
                    Thread.sleep(1000);
                    //Complex
                    //	softAssert.assertEquals(size(programComplexMandatoryCheck), 1, "Complex should be mandatory field");
                    click(monitoringPlanComplexDropdownExpand);
                    Thread.sleep(2000);
                    type(programComplexSearch, getComplexName());
                    Thread.sleep(2000);
                    scroll(By.xpath("//tr[contains(@class, 'selectable')]//b"));
                    Thread.sleep(2000);
                    click(By.xpath("//tr[contains(@class, 'selectable')]//b"));
//                    scroll(By.xpath("//tr[contains(@class, 'selectable')][2]"));
//                    Thread.sleep(1000);
//                    click(By.xpath("//tr[contains(@class, 'selectable')][2]"));

                    //Start Date
                    click(startDateField);
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(500);
                    Methods method = new Methods();
                    WebElement dateWidgetStart = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
                    List<WebElement> columnsStart = dateWidgetStart.findElements(By.tagName("button"));
                    clickGivenDay(columnsStart, getFirstDay());
                    Thread.sleep(500);

                    //End Date
                    click(endDateField);
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(500);
                    WebElement dateWidgetEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
                    List<WebElement> columnsEnd = dateWidgetEnd.findElements(By.tagName("button"));
                    clickGivenDay(columnsEnd, getDay("28"));
                    Thread.sleep(500);

                    addDays("Day 56", "50", "36", "63", "67%", "33%", "60000", "70000");

                    int newRowAddedIndex = Integer.parseInt(collectionIntervalCount) + 1;
                    softAssert.assertEquals(size(alertMessage), 0, "Error Alert Message occuered");

                    softAssert.assertEquals(size(monitoringTemplatePopupDaysRowsCount), newRowAddedIndex, "Days row deleted successfully");

                    click(popupSubmitButton);
                    waitElementInvisible(loading_cursor);
                    softAssert.assertEquals(getText(alertMessage), "Sampling Plan has been created successfully");
                    softAssert.assertAll();
                    test.pass("Created Plan edited successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                    break;
                } else if (!getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName) && i == Integer.parseInt(tableRowsCount)) {
                    test.skip("Created template is not founded in table");
                    break;
                }
            }
        } catch (AssertionError er) {
            test.fail("Template failed to edit");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Template failed to edit");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void verifyCreatedPlanFromDB() throws IOException {
        try {
            test = extent.createTest("AN-MS-21: Verify created plan from DB");
            SoftAssert softAssert = new SoftAssert();

            if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev")) {
                ResultSet getCreatedPlanResults = DB_Config_DB.getStmt().executeQuery(getPlanQuery);
                while (getCreatedPlanResults.next()) {
                    String planCount = getCreatedPlanResults.getString("count");
                    softAssert.assertEquals(planCount, "1", "Plan not displayed in DB");
                }

                ResultSet getCreatedPlanIDResults = DB_Config_DB.getStmt().executeQuery(getPlanIDQuery);
                while (getCreatedPlanIDResults.next()) {
                    String planID = getCreatedPlanIDResults.getString("samplingPlanId");
                    System.out.println("Plan ID" + planID);
                    ResultSet getCreatedPlanIntervalResults = DB_Config_DB.getStmt().executeQuery(returnPlanIntervalsCount(planID));
                    while (getCreatedPlanIntervalResults.next()) {
                        String planIntervalCount = getCreatedPlanIntervalResults.getString("count");
                        softAssert.assertEquals(planIntervalCount, "7", "Plan Intervals not displayed in DB");
                        break;
                    }
                    break;
                }
                softAssert.assertAll();
                test.pass("Created Plan and Plan Intervals saved successfully in DB");
                saveResult(ITestResult.SUCCESS, null);
            }
            if (Constants.config.url().contains("uat")) {
                test.skip("UAT DB cannot be accessed");
            }
        } catch (AssertionError er) {
            test.fail("New Plan failed to create in DB");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("New Plan failed to create in DB");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void verifyClickListIconFunctionality() throws IOException {
        try {
            test = extent.createTest("AN-Template-26: Verify user navigates to Plan screen on clicking list icon next to created template");
            SoftAssert softAssert = new SoftAssert();

            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(monitoringTemplateTab);

            String tableRowsCount = getText(monitoringTemplateGetTableRowsCount);

            for (int i = 0; i <= Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName)) {

                    int j = i + 1;
                    click(By.id(monitoringTemplateViewPlanIcon + j));
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(6000);


                    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
                    driver.switchTo().window(tabs.get(1));
                    Thread.sleep(1000);
                    System.out.println(getText(By.id(ResultsCount)));
                    softAssert.assertEquals(size(alertMessage), 0, "Error Alert Message occuered");
                    softAssert.assertEquals(getText(By.id(ResultsCount)), "1");
                    softAssert.assertEquals(getText(By.cssSelector("#intervention-management-plans-log #row-0 " + monitoringPlanTemplateNameColumn)), templateName);

                    driver.switchTo().window(tabs.get(0));
                    Thread.sleep(1000);

                    softAssert.assertAll();
                    test.pass("List icon functionality verified successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                    break;
                } else if (!getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName) && i == Integer.parseInt(tableRowsCount)) {
                    test.skip("Created template is not founded in table");
                    break;
                }
            }
        } catch (AssertionError er) {
            test.fail("List icon functionality failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("List icon functionality failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void planPresentInDropdownAfterChange() throws IOException {
        try {
            test = extent.createTest("AN-Template-26: Verify that the Plan from the plan tab screen will be present in the dropdowns if the attached plan's complex is changed from the Plan tab");
            SoftAssert softAssert = new SoftAssert();

            driver.get(url_assetAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            click(AssetCreateButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);

            click(AssetFarmDropdownExpand);
            type(AssetFarmDropdownSearch, getComplexName());

            waitElementInvisible(loading_cursor);
            waitElementClickable(By.cssSelector("label b"));
            Thread.sleep(2000);
            click(By.cssSelector("label b"));

            type(AssetIntegratorAssetID, "IntegratorID_" + dateYYYYMMDD + "_" + date0);
            if (size(AssetIntegratorAssetAddNew) != 0) {
                click(AssetIntegratorAssetAddNew);
            } else {
                enterKey(AssetIntegratorAssetID);
            }

            type(AssetanimalSizeInput, "Broilers");
            enterKey(AssetanimalSizeInput);

            scroll(AssetdeploymentDateCalendar);
            click(AssetdeploymentDateCalendar);

            List<WebElement> list = driver.findElements(By.cssSelector(".dp-current-day"));
            scroll(By.xpath("//label[contains(text(), 'Asset Information')]"));
            Thread.sleep(1000);
            list.get(2).click();
            Thread.sleep(1000);

            scroll(AssetCollectionPlanInputField);

            //////////////////IE-9640////////////////
            int x = 0;

            ResultSet getActivePlans = DB_Config_DB.getStmt().executeQuery(Queries.getActivePlansQuery(dateYYYYMM + "-01"));
            while (getActivePlans.next()) {
                String activePlans = getActivePlans.getString("samplingPlanName");
                click(AssetCollectionPlanInputField);
                Thread.sleep(2000);
                getScreenshot();
                List<WebElement> plans = driver.findElements(By.cssSelector("#collectionPlan-0-0"));
                softAssert.assertEquals(plans.get(x).getText(), activePlans);
                x = x + 1;
            }
            //////////////////IE-9640////////////////

            type(AssetCollectionPlanInputField, planName);
            Thread.sleep(1500);
            enterKey(AssetCollectionPlanInputField);
            waitElementInvisible(loading_cursor);
            getScreenshot();

            waitElementClickable(popupSubmitButton);
            Thread.sleep(500);
            click(popupSubmitButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            Thread.sleep(2000);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Data saved successfully.");
            getScreenshot();


            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            click(monitoringPlanTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            String tableRowsCount = getText(monitoringPlanGetTableRowsCount);

            for (int i = 0; i <= Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#" + monitoringStrategyPlanTable + " #row-" + i + " " + monitoringPlanNameColumn)).equals(planName)) {
                    int j = i + 1;

                    System.out.println("Size: " + size(By.cssSelector(monitoringPlanDeleteIconPre + j + monitoringPlanIconPost + ".low-opacity")));
                    softAssert.assertEquals(size(By.cssSelector(monitoringPlanDeleteIconPre + j + monitoringPlanIconPost + ".low-opacity")), 1, "Delete icon should be disabled");
                    click(By.cssSelector(monitoringPlanEditIconPre + j + monitoringPlanIconPost));

                    click(monitoringPlanComplexDropdownExpand);

                    //          if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev")) {
                    ResultSet getComplexNameResults = DB_Config_DB.getStmt().executeQuery(Queries.getFarmNameAssignedToUserAtIndex2(getUsersId()));
                    while (getComplexNameResults.next()) {
                        String complexName = getComplexNameResults.getString("siteName");
                        System.out.println("Complex Name: " + complexName);
                        type(programComplexSearch, complexName);
                        Thread.sleep(1000);
                        scroll(By.cssSelector(".selectable"));
                        click(By.cssSelector(".selectable"));
                    }
                   /* }
                    if (Constants.config.url().contains("uat")) {
                        driver.findElement(programComplexSearch).sendKeys(ProgramManagementModel.ComplexNameUAT);
                    }
*/
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(1000);
                    softAssert.assertEquals(getText(alertMessage), "The sampling plan is linked to a Asset, which restricts the ability to make changes to the following fields: Name,Template,Complex,Start Date, and End Date.");
                    break;
                }

            }
/*
            driver.get(url_AssetManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            for (int i = 1; i < size(By.cssSelector("tr")); i++) {
                if (getText(By.cssSelector("tr:nth-child(" + i + ") #col-" + AssetIntegratorAssetIDCol + " label")).equals("IntegratorID_" + dateYYYYMMDD+"_"+date0)) {

                    int j = i - 1;
                    softAssert.assertTrue(!getText(By.cssSelector("#row-" + j + " #col-" + AssetFarmdeploymentCol + " label")).isEmpty(), "Farm assigned to Asset should not be empty");

                    click(By.id("edit-feed-program-" + i));
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(1500);
                    break;
                }
            }
            scroll(AssetCollectionPlanInputField);
            softAssert.assertEquals(driver.findElement(AssetCollectionPlanInputField).getAttribute("value"), planName);
 */
            softAssert.assertAll();
            test.pass("Complex did not get empty on changing plan complex");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Complex get empty on changing plan complex");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Complex get empty on changing plan complex");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void copyCreatedTemplate() throws IOException {
        try {
            test = extent.createTest("AN-Template-28: Verify user can create duplicate template");
            SoftAssert softAssert = new SoftAssert();

            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            click(monitoringTemplateTab);

            String tableRowsCount = getText(monitoringTemplateGetTableRowsCount);
            for (int i = 0; i <= Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName)) {
                    int j = i = 1;

                    click(By.id(monitoringTemplateCopyIcon + j));
                    waitElementInvisible(loading_cursor);
                    type(monitoringTemplateName, templateName + "_Copy");
                    waitElementInvisible(loading_cursor);
                    click(popupSubmitButton);
                    waitElementInvisible(loading_cursor);
                    getScreenshot();
                    softAssert.assertEquals(getText(alertMessage), "Monitoring Strategy has been created successfully");
                    click(By.id(monitoringTemplateDeleteIconPre + j + monitoringTemplateDeleteIconPost));
                    waitElementInvisible(loading_cursor);
                    click(popupYesButton);
                    waitElementInvisible(loading_cursor);
                    softAssert.assertEquals(getText(alertMessage), "Monitoring Strategy Deleted Successfully");
                    softAssert.assertAll();
                    test.pass("Template copied successfully");
                    saveResult(ITestResult.SUCCESS, null);
                    break;
                } else if (!getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName) && i == Integer.parseInt(tableRowsCount)) {
                    test.skip("Created template is not founded in table");
                    break;
                }
            }
        } catch (AssertionError er) {
            test.fail("Template failed to copy");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Template failed to copy");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void viewCreatedPlan() throws IOException {
        try {
            test = extent.createTest("AN-Template-21: Verify user can view created plan");
            SoftAssert softAssert = new SoftAssert();

            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            click(monitoringPlanTab);
            waitElementInvisible(loading_cursor);

            String tableRowsCount = getText(monitoringPlanGetTableRowsCount);

            for (int i = 0; i <= Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#" + monitoringStrategyPlanTable + " #row-" + i + " " + monitoringPlanNameColumn)).equals(planName)) {

                    String collectionIntervalCount = getText(By.cssSelector("#" + monitoringStrategyPlanTable + " #row-" + i + " " + monitoringPlanCollectionIntervalColumn));

                    int j = i + 1;
                    click(By.cssSelector(monitoringPlanViewIconPre + j + monitoringPlanIconPost));
                    waitElementInvisible(loading_cursor);

                    //   softAssert.assertEquals(size(alertMessage), 0, "Alert message displayed");
                    softAssert.assertEquals(getText(popupHeaderTitle), "View Plan");
                    softAssert.assertEquals(getAttribute(monitoringPlanName), planName);
                    softAssert.assertEquals(getText(monitoringPlanTemplateNameGetText), templateName, "Template Name not appearing in View Plan popup");
                    softAssert.assertEquals(getText(monitoringPlanComplexGetText).isEmpty(), false, "Complex Name not appearing in View Plan popup");
                    softAssert.assertEquals(size(monitoringTemplatePopupDaysRowsCount), Integer.parseInt(collectionIntervalCount), "Days row deleted successfully");

                    click((popupCloseButton));
                    softAssert.assertAll();
                    test.pass("Created Template edited successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                    break;
                } else if (!getText(By.cssSelector("#" + monitoringStrategyPlanTable + " #row-" + i + " " + monitoringPlanNameColumn)).equals(planName) && i == Integer.parseInt(tableRowsCount)) {
                    test.skip("Created plan is not founded in table");
                    break;
                }
            }
        } catch (AssertionError er) {
            test.fail("Plan failed to edit");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Plan failed to edit");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void editCreatedPlan() throws IOException {
        try {
            test = extent.createTest("AN-Template-21: Verify user can edit created plan");
            SoftAssert softAssert = new SoftAssert();

            click(monitoringPlanTab);
            waitElementInvisible(loading_cursor);

            String tableRowsCount = getText(monitoringPlanGetTableRowsCount);

            System.out.println("tablerow" + tableRowsCount);
            for (int i = 0; i <= Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#" + monitoringStrategyPlanTable + " #row-" + i + " " + monitoringPlanNameColumn)).equals(planName)) {

                    String collectionIntervalCount = getText(By.cssSelector("#" + monitoringStrategyPlanTable + " #row-" + i + " " + monitoringPlanCollectionIntervalColumn));
                    System.out.println("collection:" + collectionIntervalCount);

                    int j = i + 1;
                    click(By.cssSelector(monitoringPlanEditIconPre + j + monitoringPlanIconPost));
                    waitElementInvisible(loading_cursor);

                    softAssert.assertEquals(getText(popupHeaderTitle), "Update Plan");

                    addDays("Day 70", "70", "34", "63", "67%", "33%", "60000", "70000");

                    int newRowAddedIndex = Integer.parseInt(collectionIntervalCount) + 1;

                    WebElement editIcon = driver.findElement(By.cssSelector(monitoringTemplateDaysPopupRowEditIconPre + newRowAddedIndex + monitoringTemplateDaysPopupRowEditIconPost));

                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", editIcon);
                    addDays("Day 63", "61", "34", "61", "60%", "40%", "65000", "75000");
                    softAssert.assertEquals(size(alertMessage), 0, "Error Alert Message occurred");

                    scroll(By.cssSelector(monitoringTemplateDaysPopupRowDeleteIconPre + newRowAddedIndex + monitoringTemplateDaysPopupRowDeleteIconPost));
                    click(By.cssSelector(monitoringTemplateDaysPopupRowDeleteIconPre + newRowAddedIndex + monitoringTemplateDaysPopupRowDeleteIconPost));
                    Thread.sleep(2000);
                    softAssert.assertEquals(size(alertMessage), 0, "Error Alert Message occurred");
                    softAssert.assertEquals(size(monitoringTemplatePopupDaysRowsCount), Integer.parseInt(collectionIntervalCount), "Days row deleted successfully");

                    //IE-9626 and IE-9625
                    click(popupCloseButton);
                    Thread.sleep(700);
                    softAssert.assertEquals(getText(popupConfirmationTitle), "Confirmation", "Confirmation Popup not displayed on clicking cross icon");
                    click(popupNoButton);
                    Thread.sleep(500);
                    click((popupCancelButton));
                    Thread.sleep(700);
                    softAssert.assertEquals(getText(popupConfirmationTitle), "Confirmation", "Confirmation Popup not displayed on clicking cancel button");
                    click(popupNoButton);
                    Thread.sleep(500);

                    click(popupSubmitButton);
                    waitElementInvisible(loading_cursor);
                    softAssert.assertEquals(getText(alertMessage), "Sampling has been updated successfully");
                    softAssert.assertAll();
                    test.pass("Created Template edited successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                    break;
                } else if (!getText(By.cssSelector("#" + monitoringStrategyPlanTable + " #row-" + i + " " + monitoringPlanNameColumn)).equals(planName) && i == Integer.parseInt(tableRowsCount)) {
                    test.skip("Created plan is not founded in table");
                    break;
                }
            }
        } catch (AssertionError er) {
            test.fail("Plan failed to edit");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Plan failed to edit");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void deleteCreatedPlan() throws IOException {
        try {
            test = extent.createTest("AN-Template-21: Verify user can delete created plan");
            SoftAssert softAssert = new SoftAssert();

            click(monitoringPlanTab);
            waitElementInvisible(loading_cursor);

            String tableRowsCount = getText(monitoringPlanGetTableRowsCount);

            for (int i = 0; i <= Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#" + monitoringStrategyPlanTable + " #row-" + i + " " + monitoringPlanNameColumn)).equals(planName)) {
                    int j = i + 1;
                    click(By.xpath("(//*[@id='" + monitoringTemplateDeleteIconPre + j + monitoringTemplateDeleteIconPost + "'])[2]"));
                    waitElementInvisible(loading_cursor);
                    click(popupYesButton);
                    waitElementInvisible(loading_cursor);
                    softAssert.assertEquals(getText(alertMessage), "Sampling Plan Deleted Successfully");
                    softAssert.assertAll();
                    test.pass("Sampling Plan Deleted Successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                    break;
                } else if (!getText(By.cssSelector("#" + monitoringStrategyPlanTable + " #row-" + i + " " + monitoringPlanNameColumn)).equals(planName) && i == Integer.parseInt(tableRowsCount)) {
                    test.skip("Created plan is not founded in table");
                    break;
                }
            }
        } catch (AssertionError er) {
            test.fail("Plan failed to delete");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Plan failed to delete");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void deleteCreatedTemplate() throws IOException {
        try {
            test = extent.createTest("AN-Template-24: Verify user can delete created template");
            SoftAssert softAssert = new SoftAssert();

            click(monitoringTemplateTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);

            String tableRowsCount = getText(monitoringTemplateGetTableRowsCount);

            for (int i = 0; i <= Integer.parseInt(tableRowsCount); i++) {
                if (getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName)) {

                    int j = i = 1;

                    click(By.id(monitoringTemplateDeleteIconPre + j + monitoringTemplateDeleteIconPost));
                    waitElementInvisible(loading_cursor);
                    click(popupYesButton);
                    waitElementInvisible(loading_cursor);
                    softAssert.assertEquals(getText(alertMessage), "Monitoring Strategy Deleted Successfully");
                    softAssert.assertAll();
                    test.pass("Created Template Deleted Successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                    break;
                } else if (!getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(templateName) && i == Integer.parseInt(tableRowsCount)) {
                    test.skip("Created template is not founded in table");
                    break;
                }
            }
        } catch (AssertionError er) {
            test.fail("Template failed to delete");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Template failed to delete");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void verifyTemplatesInPlanDropdown() throws IOException {
        try {
            test = extent.createTest("AN-Template-24: Verify all templates are displayed in create plan popup ");
            SoftAssert softAssert = new SoftAssert();

            click(monitoringTemplateTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);

            String tableRowsCount = getText(monitoringTemplateGetTableRowsCount);

            click(monitoringCreateNewPlanButton);
            click(monitoringPlanTemplateDropDownExpand);

            Thread.sleep(1000);
            softAssert.assertEquals(size(monitoringPlanTemplateDropDownList), Integer.parseInt(tableRowsCount));
            softAssert.assertAll();
            test.pass("All templates displayed in dropdown Successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);


        } catch (AssertionError er) {
            test.fail("All Template failed to display in plan dropdown list");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("All Templates failed to display in plan dropdown list");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void verifyValidationsOnDaysFields() throws IOException {
        try {
            test = extent.createTest("AN-Template-24: Verify all validations are working on Add Days Fields");
            SoftAssert softAssert = new SoftAssert();

            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            click(monitoringTemplateTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);

            click(monitoringCreateNewTemplateButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);
            type(monitoringTemplateType, "CSM");
            Thread.sleep(1500);
            click(monitoringTemplateTypeCSMSelect);

            System.out.println("Before" + size(By.cssSelector("#houseThresholdTotalOPG-1.has-error")));

            addDays("Day 7", "10", "15", "10", "150", "150", "40", "20");

            System.out.println("After: " + size(By.cssSelector("#houseThresholdTotalOPG-1.has-error")));

            softAssert.assertEquals(size(monitoringTemplateMaxAgeValidationCheck), 1, "Max age should be more then min age");
            softAssert.assertEquals(size(monitoringTemplateHouseThresholdTotalOPGValidationCheck), 1, "House Threshold should be less than 100");
            softAssert.assertEquals(size(monitoringTemplateComplexThresholdTotalOPGValidationCheck), 1, "Complex Threshold should be less than 100");
            softAssert.assertEquals(size(monitoringTemplateUpperLimitTotalOPG), 1, "Upper limit should be more than lower limit");
            softAssert.assertEquals(size(monitoringTemplateHouseThresholdLargeOPGValidationCheck), 1, "House Threshold should be less than 100");
            softAssert.assertEquals(size(monitoringTemplateComplexThresholdLargeOPGValidationCheck), 1, "Complex Threshold should be less than 100");
            softAssert.assertEquals(size(monitoringTemplateUpperLimitLargeOPG), 1, "Upper limit should be more than lower limit");

            softAssert.assertAll();
            test.pass("All templates displayed in dropdown Successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);


        } catch (AssertionError er) {
            test.fail("All Template failed to display in plan dropdown list");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("All Templates failed to display in plan dropdown list");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void verifyTemplateTabAccessRightsFunctionality() throws IOException, InterruptedException {
        test = extent.createTest("Verify that the user access right functionality works on the Monitoring Strategy Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            List<WebElement> rows = driver.findElements(rowsRecord);
            // Loop through each row and click on the "Edit Rights" icon of the Admin row
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (!createRightsInput.isSelected()) {
                click(createRightsCheckbox);
                Thread.sleep(1000);
            }
            if (updateRightsInput.isSelected()) {
                click(updateRightsCheckbox);
                Thread.sleep(1000);
            }
            if (viewRightsInput.isSelected()) {
                click(viewRightsCheckbox);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify create rights are displayed
            type(loginEmail, Constants.config.ie_username());
            type(loginPassword, Constants.config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(templateViewIcon), 0, "View icon is not displayed");
            softAssert.assertEquals(size(templateEditIcon), 0, "Edit icon is not displayed");
            softAssert.assertTrue(driver.findElement(monitoringCreateNewTemplateButton).isDisplayed(), "Create icon is not displayed");

            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            rows = driver.findElements(rowsRecord);
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (createRightsInput.isSelected()) {
                click(createRightsCheckbox);
                Thread.sleep(1000);
            }
            if (!updateRightsInput.isSelected()) {
                click(updateRightsCheckbox);
                Thread.sleep(1000);
            }
            if (viewRightsInput.isSelected()) {
                click(viewRightsCheckbox);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify all action icons are displayed
            type(loginEmail, Constants.config.ie_username());
            type(loginPassword, Constants.config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(templateViewIcon), 0, "View icon is not displayed");
            softAssert.assertEquals(size(templateEditIcon), 1, "Edit icon is not displayed");
            softAssert.assertEquals(size(monitoringCreateNewTemplateButton), 0, "Create icon is not displayed");

            // Verify view rights now
            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            rows = driver.findElements(rowsRecord);
            // Loop through each row and click on the "Edit Rights" icon of the Admin row
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (createRightsInput.isSelected()) {
                click(createRightsCheckbox);
                Thread.sleep(1000);
            }
            if (updateRightsInput.isSelected()) {
                click(updateRightsCheckbox);
                Thread.sleep(1000);
            }
            if (!viewRightsInput.isSelected()) {
                click(viewRightsCheckbox);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify create rights are displayed
            type(loginEmail, Constants.config.ie_username());
            type(loginPassword, Constants.config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(templateViewIcon), 1, "View icon is not displayed");
            softAssert.assertEquals(size(templateEditIcon), 0, "Edit icon is not displayed");
            softAssert.assertEquals(size(monitoringCreateNewTemplateButton), 0, "Create icon is not displayed");

            softAssert.assertAll();
            test.pass("Access rights passed successfully");
            results.createNode("Access rights passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Access rights for for the monitoring strategy screen failed");
            results.createNode("Access rights for the monitoring strategy screen failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("failed to verify Access rights for the monitoring strategy screen");
            results.createNode("failed to verify Access rights for for the monitoring strategy screen");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void verifyTemplateTabAdminRightsFunctionality() throws IOException {
        try {
            test = extent.createTest("Verify administration rights are working on the Monitoring Strategy Screen");
            SoftAssert softAssert = new SoftAssert();

            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            List<WebElement> rows = driver.findElements(rowsRecord);
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (!createRightsInput.isSelected()) {
                click(createRightsCheckbox);
                Thread.sleep(1000);
            }
            if (!updateRightsInput.isSelected()) {
                click(updateRightsCheckbox);
                Thread.sleep(1000);
            }
            if (!viewRightsInput.isSelected()) {
                click(viewRightsCheckbox);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify all action icons are displayed
            type(loginEmail, Constants.config.ie_username());
            type(loginPassword, Constants.config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(templateViewIcon), 1, "View icon is not displayed");
            softAssert.assertEquals(size(templateEditIcon), 4, "Edit icon is not displayed");
            softAssert.assertEquals(size(monitoringCreateNewTemplateButton), 1, "Create icon is not displayed");

            softAssert.assertAll();
            test.pass("Admin rights passed successfully");
            results.createNode("Admin rights passed successfully");
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Administration rights are not working on the Monitoring Strategy screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Administration rights are not working on the Monitoring Strategy screen");
            saveResult(ITestResult.FAILURE, ex);
        }

    }

    public void verifyPlanTabAccessRightsFunctionality() throws IOException, InterruptedException {
        test = extent.createTest("Verify that the user access right functionality works on the Monitoring Strategy Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            List<WebElement> rows = driver.findElements(rowsRecord);
            // Loop through each row and click on the "Edit Rights" icon of the Admin row
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (!createRightsInputPlanTab.isSelected()) {
                click(createRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }
            if (updateRightsInputPlanTab.isSelected()) {
                click(updateRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }
            if (viewRightsInputPlanTab.isSelected()) {
                click(viewRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify create rights are displayed
            type(loginEmail, Constants.config.ie_username());
            type(loginPassword, Constants.config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertTrue(driver.findElement(monitoringCreateNewPlanButton).isDisplayed(), "Create icon is not displayed");
            click(monitoringPlanTab);
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(size(templateViewIcon), 0, "View icon is not displayed");
            softAssert.assertEquals(size(templateEditIcon), 0, "Edit icon is not displayed");

            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            rows = driver.findElements(rowsRecord);
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (createRightsInputPlanTab.isSelected()) {
                click(createRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }
            if (!updateRightsInputPlanTab.isSelected()) {
                click(updateRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }
            if (viewRightsInputPlanTab.isSelected()) {
                click(viewRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify all action icons are displayed
            type(loginEmail, Constants.config.ie_username());
            type(loginPassword, Constants.config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(monitoringCreateNewPlanButton), 0, "Create icon is not displayed");
            click(monitoringPlanTab);
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(size(templateViewIcon), 0, "View icon is not displayed");
            softAssert.assertEquals(size(templateEditIcon), 0, "Edit icon is not displayed");

            // Verify view rights now
            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            rows = driver.findElements(rowsRecord);
            // Loop through each row and click on the "Edit Rights" icon of the Admin row
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (createRightsInputPlanTab.isSelected()) {
                click(createRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }
            if (updateRightsInputPlanTab.isSelected()) {
                click(updateRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }
            if (!viewRightsInputPlanTab.isSelected()) {
                click(viewRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify create rights are displayed
            type(loginEmail, Constants.config.ie_username());
            type(loginPassword, Constants.config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(monitoringCreateNewPlanButton), 0, "Create icon is not displayed");
            click(monitoringPlanTab);
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(size(templateViewIcon), 0, "View icon is not displayed");
            softAssert.assertEquals(size(templateEditIcon), 0, "Edit icon is not displayed");


            softAssert.assertAll();
            test.pass("Access rights passed successfully");
            results.createNode("Access rights passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Access rights for for the monitoring strategy screen failed");
            results.createNode("Access rights for the monitoring strategy screen failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("failed to verify Access rights for the monitoring strategy screen");
            results.createNode("failed to verify Access rights for for the monitoring strategy screen");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void verifyPlanTabAdminRightsFunctionality() throws IOException {
        try {
            test = extent.createTest("Verify administration rights are working on the Plan tab on Monitoring Strategy Screen");
            SoftAssert softAssert = new SoftAssert();

            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            List<WebElement> rows = driver.findElements(rowsRecord);
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (!createRightsInputPlanTab.isSelected()) {
                click(createRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }
            if (!updateRightsInputPlanTab.isSelected()) {
                click(updateRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }
            if (!viewRightsInputPlanTab.isSelected()) {
                click(viewRightsCheckboxPlanTab);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify all action icons are displayed
            type(loginEmail, Constants.config.ie_username());
            type(loginPassword, Constants.config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_strategyManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(monitoringCreateNewPlanButton), 1, "Create icon is not displayed");
            click(monitoringPlanTab);
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(size(templateViewIcon), 2, "View icon is not displayed");
            softAssert.assertEquals(size(templateEditIcon), 5, "Edit icon is not displayed");


            softAssert.assertAll();
            test.pass("Admin rights passed successfully");
            results.createNode("Admin rights passed successfully");
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Administration rights are not working on the Plan tab on Monitoring Strategy screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Administration rights are not working on the Plan tab on Monitoring Strategy screen");
            saveResult(ITestResult.FAILURE, ex);
        }

    }
}
