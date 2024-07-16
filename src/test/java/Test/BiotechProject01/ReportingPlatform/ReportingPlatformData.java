package Test.BiotechProject01.ReportingPlatform;

import Config.BaseTest;
import MiscFunctions.*;
import Models.*;
import PageObjects.*;
import Test.BiotechProject01.Login.LoginTest;
import com.aventstack.extentreports.gherkin.model.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.Constants.*;
import static MiscFunctions.Constants.config;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.DateUtil.dateYYYY;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Queries.*;
import static Models.AssetManagementModel.*;
import static Models.AssetManagementModel.AssetanimalSex;
import static Models.OrganizationManagementModel.*;
import static Models.ProgramManagementModel.lstProgramManagementSearch;
import static Models.UserManagementModel.*;
import static Models.UserManagementModel.createUserPassword;
import static PageObjects.BasePage.*;
import static PageObjects.AssetManagementPage.*;
import static PageObjects.AssetManagementPage.monitoringPlanSaveButton;
import static PageObjects.LoginPage.*;
import static PageObjects.LoginPage.loginButton;
import static PageObjects.MonitoringStrategyPage.*;
import static PageObjects.OrganizationManagementPage.*;
import static PageObjects.ProgramManagementPage.*;
import static PageObjects.ProgramManagementPage.fieldAccessIcon;
import static PageObjects.ReportingPlatformPage.*;
import static PageObjects.UserManagementPage.*;
import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class ReportingPlatformData extends BaseTest {
    @BeforeTest
    public void extent() throws InterruptedException, IOException {
        initReport("Reporting_Platform_Data");
        DB_Config_DB.test();
        DB_Config_DW.test();
    }

    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
    }

    @Test(priority = 1, enabled = true)
    public void CreateOrganization() throws IOException, InterruptedException {
        OrganizationManagementPage.CreateOrganizationFunction(ReportingOrganizationName);
    }

    @Test(description = "Test Case: Organization Site Check", enabled = true, priority = 2, groups = {"regression"})
    public void OrganizationSitesHierarchyCheck() throws InterruptedException, IOException {
        try {
            test = extent.createTest("AN-OM-08-16: Verify Complete Organization Site Hierarchy", "This test case will verify complete site hierarchy");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            steps.createNode("1. Click on site button next to created Organization; Organization Site popup appears");
            steps.createNode("2. Verify Site Type is Organization");

            SoftAssert softAssert = new SoftAssert();

            getDriver().get(url_organizationManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            searchCreatedOrg(ReportingOrganizationName, organizationEditSitesButton);

            steps.createNode("3. Create A Region");
            waitElementClickable(orgAddSite1);
            click(orgAddSite1);
            Thread.sleep(1000);
            click(orgSiteTypeInput);
            String regionType = getText(orgSiteTypeDropDownValue);
            softAssert.assertEquals(regionType, "Region");
            type(orgSiteNameInput, "Reporting Region");
            click(popupSaveButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "New site created.", "Region failed to create");

            steps.createNode("4. Create A Sub Region");
            waitElementClickable(orgAddSite2);
            click(orgAddSite2);
            Thread.sleep(1000);
            click(orgSiteTypeInput);
            String subregionType = getText(orgSiteTypeDropDownValue);
            softAssert.assertEquals(subregionType, "Sub Region");
            type(orgSiteNameInput, "Reporting Sub Region");
            click(popupSaveButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "New site created.", "Sub Region failed to create");

            steps.createNode("5. Create A Complex");
            waitElementClickable(orgAddSite3);
            click(orgAddSite3);
            Thread.sleep(1000);
            click(orgSiteTypeInput);
            Thread.sleep(1000);
            click(By.cssSelector("div .ng-option:nth-child(1)"));
            type(orgSiteNameInput, "Reporting Complex");
            Thread.sleep(250);
            click(popupSaveButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "New site created.", "Complex failed to create");


            steps.createNode("6. Create A Farm");
            waitElementClickable(orgAddSite4);
            click(orgAddSite4);
            Thread.sleep(1000);
            click(orgSiteTypeInput);
            click(By.cssSelector("div .ng-option:nth-child(1)"));
            type(orgSiteNameInput, "Reporting Farm");
            Thread.sleep(250);
            click(popupSaveButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "New site created.", "Farm failed to create");

            steps.createNode("7. Create Houses");

            for (int i = 0; i < 4; i++) {
                waitElementClickable(orgAddSite5);
                Thread.sleep(2000);
                click(orgAddSite5);
                Thread.sleep(1000);
                click(orgSiteTypeInput);
                String HouseType = getText(By.cssSelector("div .ng-option:nth-child(1)"));
                softAssert.assertEquals(HouseType, "House");
                type(orgSiteNameInput, "Reporting House " + (i + 1) + " (" + date0 + ")");
                Thread.sleep(250);
                click(popupSaveButton);
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                softAssert.assertEquals(getText(alertMessage), "New site created.", "Houses failed to create");
            }
            softAssert.assertAll();
            test.pass("Site hierarchy verified successfully");
            results.createNode("Site hierarchy verified successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Site hierarchy failed to verify successfully");
            results.createNode("Site hierarchy failed to verify successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Site hierarchy failed to verify successfully");
            results.createNode("Site hierarchy failed to verify successfully");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 3, groups = {"smoke", "regression"})
    public void CreateUser() throws InterruptedException, IOException {
        try {
            test = ExtentVariables.extent.createTest("AN-UM-03: Verify user can create a user");
            steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
            results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

            steps.createNode("1. Enter valid data in all fields and click on Save button");
            SoftAssert softAssert = new SoftAssert();

            getDriver().get(url_userManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(By.id(userEmail + "" + ShowFilter));
            type(By.id(userEmail + "" + SearchInput), createUserEmail);
            Thread.sleep(1500);

            if (size(By.xpath("//b[text() = '" + createUserEmail + "']")) != 0) {
                click(By.id(userEmail + "" + SelectAll));
                click(By.id(userEmail + "" + ApplyFilter));
                waitElementInvisible(loading_cursor);
                Thread.sleep(6000);
                if (getText(By.cssSelector("tr:nth-child(1) td:nth-child(5) label")).equals(createUserEmail)) {
                    scroll(agreeementSearchedUser);
                    waitElementClickable(deleteSearchedUser);
                    Thread.sleep(1000);
                    try {
                        click(deleteSearchedUser);
                    } catch (StaleElementReferenceException ex) {
                        click(deleteSearchedUser);
                    }
                }
                Thread.sleep(1500);
                click(popupYesButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
            } else {
                click(By.id(userEmail + "" + ShowFilter));
            }

            click(usercreateButton);
            waitElementClickable(userOrgTypeInput);
            Thread.sleep(3000);
            type(userOrgTypeInput, "BiotechProject01");
            enterKey(userOrgTypeInput);
            click(userOrgInput);
            type(userOrgInput, ReportingOrganizationName);
            enterKey(userOrgInput);
            Thread.sleep(500);
            Thread.sleep(1000);
            click(popupNextButton);
            Thread.sleep(1000);

            type(userFirstNameInput, "AP Test");
            type(userLastNameInput, "User");
            type(userEmailInput, reportingPlatformUser);
            click(popupNextButton);


            click(reportRoleExpand);
            type(reportRoleSelect, "BiotechProject01 Admin Report Role");
            enterKey(reportRoleSelect);

            click(systemRolesExpand);
            type(systemRolesSelect, "Admin");
            enterKey(systemRolesSelect);
            click(systemRolesExpand);


            click(openUserSites);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            type(By.cssSelector(".col-12 .form-control.ng-untouched.ng-pristine.ng-valid"), ReportingOrganizationName);
            Thread.sleep(3000);
            click(By.cssSelector(".form-check-label"));
            Thread.sleep(1000);
            click(saveUserSites);
            Thread.sleep(1000);
            getScreenshot();
            click(popupSaveButton);

            waitElementVisible(alertMessage);
            Thread.sleep(1000);
            softAssert.assertEquals(getText(alertMessage), "New user created.");
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);

            ResultSet getLink = DB_Config_DB.getStmt().executeQuery("select top 1 userGUID from dbo.[User] where userEmail='" + reportingPlatformUser + "' order by createdOn desc");
            String resetPasswordLink = null;
            while (getLink.next()) {
                resetPasswordLink = getLink.getString("userGUID");
                System.out.println("Link: " + resetPasswordLink);
            }
            getDriver().get(config.url() + "/auth/reset-password?param=" + resetPasswordLink);

            waitElementVisible(enterNewPassword);
            type(enterNewPassword, createUserPassword);
            type(enterConfirmPassword, createUserPassword);
            click(clickPasswordButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);

            hover(sideBar);
            Thread.sleep(1000);
            click(logoutButton);
            waitElementVisible(loginEmail);
            type(loginEmail, reportingPlatformUser);
            type(loginPassword, createUserPassword);
            click(loginButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertAll();
            test.pass("New User created successfully");
            results.createNode("New User created successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Failed to create a new user");
            results.createNode("Failed to create a new user");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Failed to create a new user");
            results.createNode("Failed to create a new user");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 4, groups = {"smoke", "regression"})
    public void CreatePrograms() throws InterruptedException, IOException, SQLException {
        try {
            test = ExtentVariables.extent.createTest("AN-PM-40: Verify user can create a Program");
            SoftAssert softAssert = new SoftAssert();

            getDriver().get(url_programAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            System.out.println("sdd"+size(programCreateButton));
            scroll(programCreateButton);
            click(programCreateButton);
waitElementVisible(programDisplayName);

            //Program DisplayName
            type(programDisplayName, reportingFeedProgramName);
            System.out.println("Program Display Name is " + reportingFeedProgramName);
            Thread.sleep(500);

            //Program Type
            type(programProgramType, "Feed");
            Thread.sleep(500);
            enterKey(programProgramType);
            Thread.sleep(500);

            //Complex
            click(programComplexList);
            type(programComplexSearch, "Reporting Complex");
            Thread.sleep(250);
            click(By.xpath("//tr[@class='selectable ng-star-inserted']//label/b"));

            //Start Date
            click(programStartDateIcon);
            Methods method = new Methods();
            WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
            List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
            clickGivenDay(columns1, getFirstDay());
            Thread.sleep(500);

            //End Date
            click(programEndDateIcon);
            WebElement dateWidgetToEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
            List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
            clickGivenDay(columns2, getDay("30"));
            Thread.sleep(700);
            softAssert.assertEquals(size(popupSaveButtonDisabled), 2, "Mandatory check failed");

            //Target Pathogen
            List<String> selectedValues = new ArrayList<>();

            click(programTargetPathogen);
            Thread.sleep(500);
            // IE-10773
            softAssert.assertEquals(size(targetPathogenSelectAllButton), 1, "Select all button is not present on target pathogen dropdown");

            type(programTargetPathogen, "Patho_1");
            enterKey(programTargetPathogen);
            click(programTargetPathogenDropdownExpand);

            click(programTradeNameInput);
            enterKey(programTradeNameInput);
            Thread.sleep(1000);

            click(programTradeNameInput);
            List<WebElement> tradeNames = getDriver().findElements(By.cssSelector("[name='tradeName'] .ng-option-label"));
            String programNameValue = getAttribute(programName);
            softAssert.assertEquals(tradeNames.get(0).getText(), programNameValue, "Trade name value is not same as program name value in Vaccine program");

            click(programFeedTypeDropdown);
            Thread.sleep(500);
            enterKey(programFeedTypeDropdown);
            type(programAssetDayStart, "1");
            WebElement EndDay = getDriver().findElement(programAssetDayStart);
            getDriver().findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");
            Thread.sleep(1000);

            click(popupSaveButtonXpath);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);
            softAssert.assertEquals(getText(alertMessage), "New program has been created successfully");

            //Feed Verification
            waitElementVisible(By.cssSelector("tr:nth-child(1) " + programFeedProgramNameCol));
            softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedProgramDisplayNameCol)).getText(), ProgramManagementModel.FeedProgramName);
            softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedTypesCol)).getText(), "Feed Type 1");
            softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
            softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
            softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedTargetPathogenCol)).getText(), "Patho_1");
            //     softAssert.assertEquals(size(By.cssSelector("tr:nth-child(1) " + programFeedTargetPathogenMoreCol)), 1, "+ more functionality is not added to target pathogen column");

            softAssert.assertAll();
            test.pass("New Program created successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("New Program failed to create");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("New Program failed to create");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(priority = 5, enabled = true, groups = {"smoke", "regression"})
    public void CreateMonitoringPlan() throws IOException {
        try {
            test = extent.createTest("AN-Template-20: Verify user can create a Monitoring Plan");
            SoftAssert softAssert = new SoftAssert();

            getDriver().get(url_strategyManagement);
            Thread.sleep(2000);
            click(monitoringCreateNewTemplateButton);

            type(monitoringTemplateName, reportingTemplateName);
            Thread.sleep(500);

            clear(monitoringTemplateType);
            type(monitoringTemplateType, "CSM");
            Thread.sleep(750);
            click(monitoringTemplateTypeCSMSelect);
            click(monitoringTemplatePerformanceCriteriaToggle);

            click(popupSubmitButton);
            softAssert.assertEquals(getText(alertMessage), "Add at least one day");

            MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
            msp.addDays("Day 17", "17", "14", "18", "50%", "50%", "10000", "20000");
            msp.addDays("Day 28", "28", "25", "29", "50%", "50%", "20000", "30000");
            msp.addDays("Day 37", "37", "34", "38", "60%", "40%", "30000", "40000");

            click(popupSubmitButton);
            softAssert.assertEquals(getText(alertMessage), "Monitoring Strategy has been created successfully");

            String tableRowsCountTemplate = getText(monitoringTemplateGetTableRowsCount);
            for (int i = 0; i < Integer.parseInt(tableRowsCountTemplate); i++) {
                if (getText(By.cssSelector("#" + monitoringStrategyTemplateTable + " #row-" + i + " " + monitoringTemplateNameColumn)).equals(reportingTemplateName)) {
                    softAssert.assertTrue(true);
                    break;
                } else if (!getText(By.cssSelector("#" + monitoringStrategyTemplateTable + " #row-" + i + " " + monitoringTemplateNameColumn)).equals(reportingTemplateName) && i == Integer.parseInt(tableRowsCountTemplate)) {
                    softAssert.assertTrue(false, "Created template is not founded in table");
                    break;
                }
            }

            softAssert.assertAll();
            test.pass("New Template created successfully");

            String tableRowsCountPlan = getText(monitoringTemplateGetTableRowsCount);
            System.out.println(tableRowsCountPlan);

            for (int i = 0; i <= Integer.parseInt(tableRowsCountPlan); i++) {
                if (getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(reportingTemplateName)) {
                    System.out.println(getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)));

                    String collectionIntervalCount = getText(By.cssSelector("#row-" + i + " " + monitoringCollectionIntervalsColumn));

                    int j = i + 1;
                    click(By.id(monitoringTemplateCreatePlanIcon + j));
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(2000);

                    type(monitoringPlanName, reportingPlanName);
                    Thread.sleep(1000);

                    click(monitoringPlanComplexDropdownExpand);
                    Thread.sleep(2000);
                    type(programComplexSearch, "Reporting Complex");
                    Thread.sleep(2000);
                    scroll(By.xpath("//tr[contains(@class, 'selectable')]//b"));
                    Thread.sleep(2000);
                    click(By.xpath("//tr[contains(@class, 'selectable')]//b"));

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


                    int newRowAddedIndex = Integer.parseInt(collectionIntervalCount);
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
                } else if (!getText(By.cssSelector("#row-" + i + " " + monitoringTemplateNameColumn)).equals(reportingTemplateName) && i == Integer.parseInt(tableRowsCountPlan)) {
                    test.skip("Created template is not founded in table");
                    break;
                }
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            }
        } catch (AssertionError er) {
            test.fail("New Template failed to create");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("New Template failed to create");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 6, groups = {"smoke", "regression"})
    public void CreateAsset() throws IOException {
        try {
            test = extent.createTest("AN-FM-01: Verify user can create a Asset");
            SoftAssert softAssert = new SoftAssert();

            for (int i = 0; i < 3; i++) {
                type(By.tagName("html"), Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            }
            Thread.sleep(3000);
            NavigateToScreen.navigate(url_animalManagement, "Asset Management", AssetManagementTitle);
            String PreResultsCountValues = getText(By.id(ResultsCount));
            int PreResultsCount = Integer.parseInt(PreResultsCountValues.replace(",", ""));

            unLockFilter(AssetdeploymentTable);

            click(fieldAccessIcon);
            click(resetDefaultButton);
            click(popupSaveButton);

            click(AssetCreateButton);
            Thread.sleep(3000);

            type(AssetIntegratorAssetID, reportingAssetIntegratorID);
            if (size(AssetIntegratorAssetAddNew) != 0) {
                click(AssetIntegratorAssetAddNew);
            } else {
                enterKey(AssetIntegratorAssetID);
            }

            type(AssetanimalSizeInput, AssetanimalSize);
            enterKey(AssetanimalSizeInput);
            Thread.sleep(500);

            type(AssetanimalSexInput, AssetanimalSex);
            enterKey(AssetanimalSexInput);
            Thread.sleep(2000);

            clear(AssetProductionTypeList);
            type(AssetProductionTypeList, "Breeder");
            Thread.sleep(750);
            List<WebElement> productionTypeList = getDriver().findElements(AssetProductionTypeListItems);
            productionTypeList.get(0).click();

//          type(AssetHatcherySourceInput, AssetHatcheryName);

            scroll(AssetFarmDropdownExpand);
            click(AssetFarmDropdownExpand);
            type(AssetFarmDropdownSearch, "Reporting Farm");
            waitElementClickable(By.cssSelector("label b"));
            Thread.sleep(2000);
            click(By.cssSelector("label b"));

            scroll(AssetdeploymentDateCalendar);
            click(AssetdeploymentDateCalendar);
            List<WebElement> list = getDriver().findElements(By.cssSelector(".dp-current-day"));
            scroll(By.xpath("//label[contains(text(), 'Asset Information')]"));
            Thread.sleep(1000);
            list.get(2).click();
            Thread.sleep(1000);

            scroll(monitoringStrategyButton);
            click(monitoringStrategyButton);
            Thread.sleep(1000);
            click(selectMonitoringPlan);

            if (size(noValues) == 0) {
                type(selectMonitoringPlanInput, reportingPlanName);
                click(By.xpath("//*[@id = 'collectionPlan-0-0']//label[contains(text(),'" + reportingPlanName + "')]"));
                Thread.sleep(1000);
            } else {
                System.out.println("No plan is associated.");
            }

            click(monitoringPlanSaveButton);
            Thread.sleep(1000);

            scroll(AssetAddNewProgram);
            click(AssetAddNewProgram);
            click(AssetAddNewProgramTypeInput);
            click(selectProgramFeedType);
            click(AssetAddNewProgramNameInput);
            System.out.println(reportingFeedProgramName);
            type(AssetAddNewProgramNameInput, ProgramManagementModel.FeedProgramName);
            Thread.sleep(1500);
            enterKey(AssetAddNewProgramNameInput);

            waitElementVisible(popupSubmitButton);
            click(popupSubmitButton);
            waitElementVisible(alertMessage);
            Thread.sleep(4000);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            System.out.println("Asset created successfully");
            getScreenshot();

            String PostResultsCountValues = getText(By.id(ResultsCount));
            int PostResultsCount = Integer.parseInt(PostResultsCountValues.replace(",", ""));
            softAssert.assertEquals(PostResultsCount, PreResultsCount + 1);
            softAssert.assertAll();
            getScreenshot();
            test.pass("Asset was created successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Asset failed to create");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Asset failed to create");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(enabled = true, priority = 7, groups = {"smoke", "regression"})
    public void Patho_1Ingestion1() throws IOException, SQLException {
        Patho_1RIIngestion(true, runIDValue1, "1", "L01S01", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20200820101434_PSN0024_EIM-3320-B0039_L07S01.txt", "7197adc9b0c93b1aefb11c81f431a68ac53db8e11dd071ca34d8546818b0ec42");
        Patho_1RIIngestion(false, runIDValue1, "2", "L02S02", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20201120125644_PSN0024_EIM-3420-B0377_L12S01.txt", "3cfcf607992fbce65f29744e2c2f2fcba15cbc6016be29d36ed2208b229b4145");
        Patho_1RIIngestion(false, runIDValue1, "3", "L03S03", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20201120140329_PSN0024_EIM-3420-B0300_L01S01.txt", "ffb858a7f7a3058773e5f989ec888ba88f1dc7f8fe0c0fb1e0d017335b24dbc9");
        Patho_1RIIngestion(false, runIDValue1, "4", "L04S04", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20201120140329_PSN0024_EIM-3420-B0300_L07S01.txt", "45a856c5d167855314c71fb870bdb134a14755eab19a9a90975f554bd7f0047f");
        Patho_1RIIngestion(false, runIDValue1, "5", "L05S05", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20210216162031_PSN0021_EIM-5320-B0430_L01S01.txt", "87759e350ab8b47119df80e6adc56ad3529baa20a2298a20fb8002eb0b85f24b");
        Patho_1RIIngestion(false, runIDValue1, "6", "L06S06", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20210303100431_PSN0026_EIM-4820-B0056_L02S01.txt", "69130a115e41e8370dd424b86ac348446c7bacbec05452f098a884511ec0d457");
        Patho_1RIIngestion(false, runIDValue1, "7", "L07S07", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20210308123206_PSN0024_EIM-3420-B0295_L05S01.txt", "09f8b7545f0ad4da4f59b744afc3628710b1028214d1ccbd3f2d4f4182153af5");
        Patho_1RIIngestion(false, runIDValue1, "8", "L08S08", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20210308123206_PSN0024_EIM-3420-B0295_L11S01.txt", "fce63b9895fbbc83230f6138473136bbaf5f67e2e1747153ca6fa46e0b8b23f9");
        Patho_1RIIngestion(false, runIDValue1, "9", "L09S09", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20210323151112_PSN0033_EIM-3420-B0162_L11S01.txt", "46396dfce09ea68c01ca60d07e6734e6837ebdb7ce203f6aaeba4dfe84350bc2");
        Patho_1RIIngestion(false, runIDValue1, "10", "L10S10", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20210414181131_PSN0025_EIM-0421-B0013_L12S01.txt", "250c085c09612a4bd8a4a5f0e1e678ebf489d71dc0a816226860dab7a78eeba2");
        Patho_1RIIngestion(false, runIDValue1, "11", "L11S11", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20210414185106_PSN0028_EIM-5120-B0111_L01S01.txt", "a22697d557374b0ecbe7457f2298ed2aef8cd6eedf1465e2ae1166c6cfa38779");
        Patho_1RIIngestion(false, runIDValue1, "12", "L12S12", getCreatedHouseUniqueSiteID(ReportingHouse1Name), "20210415125050_PSN0028_EIM-5120-B0139_L10S01.txt", "098ed63c6eaa13fc5cbd2ef3f84e7ad77dcc8acd51a5fca5b00fb216f3d844a1");
    }

    @Test(enabled = true, priority = 8, groups = {"smoke", "regression"})
    public void Patho_1Ingestion2() throws IOException, SQLException {
        Patho_1RIIngestion(true, runIDValue2, "1", "L01S01", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20200820101434_PSN0024_EIM-3320-B0039_L07S01.txt", "7197adc9b0c93b1aefb11c81f431a68ac53db8e11dd071ca34d8546818b0ec42");
        Patho_1RIIngestion(false, runIDValue2, "2", "L02S02", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20201120125644_PSN0024_EIM-3420-B0377_L12S01.txt", "3cfcf607992fbce65f29744e2c2f2fcba15cbc6016be29d36ed2208b229b4145");
        Patho_1RIIngestion(false, runIDValue2, "3", "L03S03", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20201120140329_PSN0024_EIM-3420-B0300_L01S01.txt", "ffb858a7f7a3058773e5f989ec888ba88f1dc7f8fe0c0fb1e0d017335b24dbc9");
        Patho_1RIIngestion(false, runIDValue2, "4", "L04S04", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20201120140329_PSN0024_EIM-3420-B0300_L07S01.txt", "45a856c5d167855314c71fb870bdb134a14755eab19a9a90975f554bd7f0047f");
        Patho_1RIIngestion(false, runIDValue2, "5", "L05S05", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20210216162031_PSN0021_EIM-5320-B0430_L01S01.txt", "87759e350ab8b47119df80e6adc56ad3529baa20a2298a20fb8002eb0b85f24b");
        Patho_1RIIngestion(false, runIDValue2, "6", "L06S06", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20210303100431_PSN0026_EIM-4820-B0056_L02S01.txt", "69130a115e41e8370dd424b86ac348446c7bacbec05452f098a884511ec0d457");
        Patho_1RIIngestion(false, runIDValue2, "7", "L07S07", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20210308123206_PSN0024_EIM-3420-B0295_L05S01.txt", "09f8b7545f0ad4da4f59b744afc3628710b1028214d1ccbd3f2d4f4182153af5");
        Patho_1RIIngestion(false, runIDValue2, "8", "L08S08", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20210308123206_PSN0024_EIM-3420-B0295_L11S01.txt", "fce63b9895fbbc83230f6138473136bbaf5f67e2e1747153ca6fa46e0b8b23f9");
        Patho_1RIIngestion(false, runIDValue2, "9", "L09S09", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20210323151112_PSN0033_EIM-3420-B0162_L11S01.txt", "46396dfce09ea68c01ca60d07e6734e6837ebdb7ce203f6aaeba4dfe84350bc2");
        Patho_1RIIngestion(false, runIDValue2, "10", "L10S10", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20210414181131_PSN0025_EIM-0421-B0013_L12S01.txt", "250c085c09612a4bd8a4a5f0e1e678ebf489d71dc0a816226860dab7a78eeba2");
        Patho_1RIIngestion(false, runIDValue2, "11", "L11S11", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20210414185106_PSN0028_EIM-5120-B0111_L01S01.txt", "a22697d557374b0ecbe7457f2298ed2aef8cd6eedf1465e2ae1166c6cfa38779");
        Patho_1RIIngestion(false, runIDValue2, "12", "L12S12", getCreatedHouseUniqueSiteID(ReportingHouse2Name), "20210415125050_PSN0028_EIM-5120-B0139_L10S01.txt", "098ed63c6eaa13fc5cbd2ef3f84e7ad77dcc8acd51a5fca5b00fb216f3d844a1");
    }

    @Test(enabled = true, priority = 9, groups = {"smoke", "regression"})
    public void Patho_1Ingestion3() throws IOException, SQLException {
        Patho_1RIIngestion(true, runIDValue3, "1", "L01S01", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20200820101434_PSN0024_EIM-3320-B0039_L07S01.txt", "7197adc9b0c93b1aefb11c81f431a68ac53db8e11dd071ca34d8546818b0ec42");
        Patho_1RIIngestion(false, runIDValue3, "2", "L02S02", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20201120125644_PSN0024_EIM-3420-B0377_L12S01.txt", "3cfcf607992fbce65f29744e2c2f2fcba15cbc6016be29d36ed2208b229b4145");
        Patho_1RIIngestion(false, runIDValue3, "3", "L03S03", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20201120140329_PSN0024_EIM-3420-B0300_L01S01.txt", "ffb858a7f7a3058773e5f989ec888ba88f1dc7f8fe0c0fb1e0d017335b24dbc9");
        Patho_1RIIngestion(false, runIDValue3, "4", "L04S04", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20201120140329_PSN0024_EIM-3420-B0300_L07S01.txt", "45a856c5d167855314c71fb870bdb134a14755eab19a9a90975f554bd7f0047f");
        Patho_1RIIngestion(false, runIDValue3, "5", "L05S05", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20210216162031_PSN0021_EIM-5320-B0430_L01S01.txt", "87759e350ab8b47119df80e6adc56ad3529baa20a2298a20fb8002eb0b85f24b");
        Patho_1RIIngestion(false, runIDValue3, "6", "L06S06", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20210303100431_PSN0026_EIM-4820-B0056_L02S01.txt", "69130a115e41e8370dd424b86ac348446c7bacbec05452f098a884511ec0d457");
        Patho_1RIIngestion(false, runIDValue3, "7", "L07S07", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20210308123206_PSN0024_EIM-3420-B0295_L05S01.txt", "09f8b7545f0ad4da4f59b744afc3628710b1028214d1ccbd3f2d4f4182153af5");
        Patho_1RIIngestion(false, runIDValue3, "8", "L08S08", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20210308123206_PSN0024_EIM-3420-B0295_L11S01.txt", "fce63b9895fbbc83230f6138473136bbaf5f67e2e1747153ca6fa46e0b8b23f9");
        Patho_1RIIngestion(false, runIDValue3, "9", "L09S09", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20210323151112_PSN0033_EIM-3420-B0162_L11S01.txt", "46396dfce09ea68c01ca60d07e6734e6837ebdb7ce203f6aaeba4dfe84350bc2");
        Patho_1RIIngestion(false, runIDValue3, "10", "L10S10", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20210414181131_PSN0025_EIM-0421-B0013_L12S01.txt", "250c085c09612a4bd8a4a5f0e1e678ebf489d71dc0a816226860dab7a78eeba2");
        Patho_1RIIngestion(false, runIDValue3, "11", "L11S11", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20210414185106_PSN0028_EIM-5120-B0111_L01S01.txt", "a22697d557374b0ecbe7457f2298ed2aef8cd6eedf1465e2ae1166c6cfa38779");
        Patho_1RIIngestion(false, runIDValue3, "12", "L12S12", getCreatedHouseUniqueSiteID(ReportingHouse3Name), "20210415125050_PSN0028_EIM-5120-B0139_L10S01.txt", "098ed63c6eaa13fc5cbd2ef3f84e7ad77dcc8acd51a5fca5b00fb216f3d844a1");
    }

    @Test(enabled = true, priority = 10, groups = {"smoke", "regression"})
    public void MetaDataUploadRun1() throws IOException, SQLException {
        UploadMetaDataFunction(runIDValue1, ReportingHouse1Name, 17);
    }

    @Test(enabled = true, priority = 11, groups = {"smoke", "regression"})
    public void MetaDataUploadRun2() throws IOException, SQLException {
        UploadMetaDataFunction(runIDValue2, ReportingHouse2Name, 28);
    }

    @Test(enabled = true, priority = 12, groups = {"smoke", "regression"})
    public void MetaDataUploadRun3() throws IOException, SQLException {
        UploadMetaDataFunction(runIDValue3, ReportingHouse3Name, 37);
    }

    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}
