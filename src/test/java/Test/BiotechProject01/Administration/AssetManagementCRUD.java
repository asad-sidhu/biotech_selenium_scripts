package Test.BiotechProject01.Administration;

import java.util.List;

import MiscFunctions.*;
import PageObjects.OrganizationManagementPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Config.BaseTest;
import MiscFunctions.DB_Config_DW;
import Models.AssetManagementModel;
import PageObjects.AssetManagementPage;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Queries.*;
import static Models.AssetManagementModel.*;
import static Models.AssetManagementModel.AssetanimalSex;
import static Models.AssetManagementModel.AssetIntegratorID;
import static Models.OrganizationManagementModel.OrganizationName;
import static PageObjects.AssetManagementPage.*;
import static PageObjects.OrganizationManagementPage.*;
import static PageObjects.ProgramManagementPage.*;
import static PageObjects.BasePage.*;
import static PageObjects.ProgramManagementPage.fieldAccessIcon;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssetManagementCRUD extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Asset_Management_CRUD" + date + ".html");
//        spark.config().setReportName("Asset Management Test Report");
        initReport("Administration_Asset_Management_CRUD");
        DB_Config_DB.test();
        DB_Config_DW.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_assetAdmin, "Asset Management", AssetManagementTitle);
    }

    @Test(enabled = true, priority = 1, groups = {"smoke", "regression"})
    public void CreateAsset() throws IOException {
        try {
            test = extent.createTest("AN-FM-01: Verify user can create a Asset");
            SoftAssert softAssert = new SoftAssert();
            for (int i = 0; i < 3; i++) {
                type(By.tagName("html"), Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            }
            Thread.sleep(3000);
            String PreResultsCountValues = getText(By.id(ResultsCount));
            int PreResultsCount = Integer.parseInt(PreResultsCountValues.replace(",", ""));
            unLockFilter(AssetdeploymentTable);
            click(fieldAccessIcon);
            click(resetDefaultButton);
            click(popupSaveButton);
            click(AssetCreateButton);
            Thread.sleep(3000);
            //******************IE-8441****************//
            softAssert.assertEquals(size(popupSubmitButtonDisable), 1, "Submit button is not disabled");
            softAssert.assertEquals(size(addNewAssetButton), 1, "Add new Asset button is not present");
            click(addNewAssetButton);
            click(newAssetCollapseIcon);
            click(AssetDeleteIcon);
            type(AssetIntegratorAssetID, AssetIntegratorID);
            if (size(AssetIntegratorAssetAddNew) != 0) {
                click(AssetIntegratorAssetAddNew);
            } else {
                enterKey(AssetIntegratorAssetID);
            }
            scroll(monitoringStrategyButton);
            click(monitoringStrategyButton);
            Thread.sleep(2000);
            softAssert.assertEquals(getText(alertMessage), "Before proceeding with the association of monitoring plans with the Asset, please ensure that you have selected the farm, deployment date, and house for each deployment event.", "Required validation error did not appeared on collection plan");
            click(AssetanimalSizeInput);
            List<WebElement> animalSizeDropdownValues = getDriver().findElements(AssetanimalSizeDropDownOptions);
            softAssert.assertEquals(animalSizeDropdownValues.get(0).getText(), "Small");
            softAssert.assertEquals(animalSizeDropdownValues.get(1).getText(), "Medium");
            softAssert.assertEquals(animalSizeDropdownValues.get(2).getText(), "Large");
            softAssert.assertEquals(animalSizeDropdownValues.get(3).getText(), "Pullets");
            softAssert.assertEquals(animalSizeDropdownValues.get(4).getText(), "Broilers");
            softAssert.assertEquals(animalSizeDropdownValues.get(5).getText(), "Big Broilers");
            type(AssetanimalSizeInput, AssetManagementModel.AssetanimalSize);
            enterKey(AssetanimalSizeInput);
            Thread.sleep(500);
            click(AssetanimalSexInput);
            List<WebElement> animalSexDropdownValues = getDriver().findElements(AssetanimalSexDropDownOptions);
            softAssert.assertEquals(animalSexDropdownValues.get(0).getText(), "Male");
            softAssert.assertEquals(animalSexDropdownValues.get(1).getText(), "Female");
            softAssert.assertEquals(animalSexDropdownValues.get(2).getText(), "Mixed");
            enterKey(AssetanimalSexInput);
            click(AssetMarketingProgramInput);
            List<WebElement> AssetMarketingProgramdropdownValues = getDriver().findElements(AssetMarketingProgramDropDownOptions);
            softAssert.assertEquals(AssetMarketingProgramdropdownValues.get(0).getText(), "Conventional");
            softAssert.assertEquals(AssetMarketingProgramdropdownValues.get(1).getText(), "No Human Antibiotics");
            softAssert.assertEquals(AssetMarketingProgramdropdownValues.get(2).getText(), "No Antibiotics Ever");
            softAssert.assertEquals(AssetMarketingProgramdropdownValues.get(3).getText(), "Organic");
            softAssert.assertEquals(AssetMarketingProgramdropdownValues.get(4).getText(), "Pastured");
            softAssert.assertEquals(AssetMarketingProgramdropdownValues.get(5).getText(), "Other");
            softAssert.assertEquals(AssetMarketingProgramdropdownValues.get(6).getText(), "NGMO");
            enterKey(AssetMarketingProgramInput);

            type(AssetanimalBreedInput, AssetBreedName);
            Thread.sleep(1000);
            if (size(addNewItemFromDropdownButton) == 1) {
                click(addNewItemFromDropdownButton);
            } else if (size(AssetanimalBreedValueSelect) == 1) {
                click(AssetanimalBreedValueSelect);
            }
            Thread.sleep(1000);
            click(AssetProductionTypeList);
            List<WebElement> productionTypeList = getDriver().findElements(AssetProductionTypeListItems);
            productionTypeList.get(1).click();
            type(AssetHatcherySourceInput, AssetHatcheryName);
            type(AssetTargetWeightInput, "10");
            type(AssetTargetSlaughterAgeInput, "15");
            type(AssetAgeOfdeploymentInput, "alpha"); //ASD-226
            softAssert.assertEquals(getAttribute(AssetAgeOfdeploymentInput), "", "User is able to enter alpha in Asset age field");
            type(AssetAgeOfdeploymentInput, "2");
            click(AssetUnitDropdownExpand);
            click(AssetUnitDaysSelect);
            Thread.sleep(1000);

            scroll(AssetFarmDropdownExpand);
            click(AssetFarmDropdownExpand);
            type(AssetFarmDropdownSearch, Queries.getFarmName());
            waitElementClickable(By.cssSelector("label b"));
            Thread.sleep(2000);
            click(By.cssSelector("label b"));
            softAssert.assertTrue(isDisplayed(AssetParentAssetInfoContainer), "Parent Asset Info not showing");
            softAssert.assertTrue(isDisplayed(AssetChildAssetInfoContainer), "Child Asset Info not showing");
            click(AssetParentAssetInfoAddBtn);
            waitElementVisible(AssetAddParentChildSearchAssetID);
            type(AssetAddParentChildSearchAssetID, "A01500");
            click(AssetAddParentChildSearchAssetIDSearchBtn);
            waitElementInvisible(loading_cursor);
            List<WebElement> ParentAssetdeployments = getDriver().findElements(AssetParentChildRelationTableRows);
            softAssert.assertNotEquals(ParentAssetdeployments.size(), 0, "Parent deployments not showing");
            for (int i = 0; i < ParentAssetdeployments.size(); i++) {
                ParentAssetdeployments.get(i).click();
                Thread.sleep(200);
            }
            click(AssetParentChildAssetInfoSaveBtn);
            waitElementVisible(popupSubmitButton);
            softAssert.assertTrue(isDisplayed(AssetParentChildRelationTable), "Parent info not showing");
            String[] AssetExpectedDetails = new String[]{"Production Type", "Integrator Asset ID", "Asset ID", "Asset deployment Date", "Location", "Child Location"};
            List<WebElement> ParentAssetActualDetails = getDriver().findElements(AssetParentChildRelationTable).get(0).findElements(By.cssSelector("th"));
            for (int i = 0; i < ParentAssetActualDetails.size() - 1; i++) {
                softAssert.assertEquals(ParentAssetActualDetails.get(i).getText(), AssetExpectedDetails[i], "Parent Asset Details do not match");
            }
            click(AssetChildAssetInfoAddBtn);
            waitElementVisible(AssetAddParentChildSearchAssetID);
            type(AssetAddParentChildSearchAssetID, "A01499");
            click(AssetAddParentChildSearchAssetIDSearchBtn);
            waitElementInvisible(loading_cursor);
            List<WebElement> ChildAssetdeployments = getDriver().findElements(AssetParentChildRelationTableRows);
            softAssert.assertNotEquals(ChildAssetdeployments.size(), 0, "Child deployments not showing");
            for (int i = 0; i < ChildAssetdeployments.size(); i++) {
                ChildAssetdeployments.get(i).click();
                Thread.sleep(200);
            }
            click(AssetParentChildAssetInfoSaveBtn);
            waitElementVisible(popupSubmitButton);
            softAssert.assertTrue(getDriver().findElements(AssetParentChildRelationTable).get(1).isDisplayed(), "Child info not showing");
            List<WebElement> ChildAssetActualDetails = getDriver().findElements(AssetParentChildRelationTable).get(1).findElements(By.cssSelector("th"));
            for (int i = 0; i < ChildAssetActualDetails.size() - 1; i++) {
                softAssert.assertEquals(ChildAssetActualDetails.get(i).getText(), AssetExpectedDetails[i], "Child Asset Details do not match");
            }
            scroll(AssetdeploymentDateCalendar);
            click(AssetdeploymentDateCalendar);
            List<WebElement> list = getDriver().findElements(By.cssSelector(".dp-current-day"));
            scroll(By.xpath("//label[contains(text(), 'Asset Information')]"));
            Thread.sleep(1000);
            list.get(2).click();
            Thread.sleep(1000);
            /////////////IE-9594/////////////////
            softAssert.assertEquals(size(By.xpath("//*[text()='Estimated Processing Date']")), 1, "Estimated Processing Date is not displayed");
            /////////////IE-9594/////////////////
            scroll(monitoringStrategyButton);
            click(monitoringStrategyButton);
            Thread.sleep(1000);
            click(selectMonitoringPlan);
            if (size(noValues) == 0) {
                String getSamplingPlanName = getSamplingPlanName();
                type(selectMonitoringPlanInput, getSamplingPlanName);
                click(By.xpath("//*[@id = 'collectionPlan-0-0']//label[contains(text(),'" + getSamplingPlanName + "')]"));
                Thread.sleep(1000);
            } else {
                System.out.println("No plan is associated.");
            }
            click(monitoringPlanSaveButton);
            Thread.sleep(1000);
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

            List<WebElement> columns = getDriver().findElements(By.cssSelector("#" + AssetdeploymentTable + " th"));
            for (int i = 0; i < columns.size(); i++) {
                int j = i + 1;
                //  System.out.println("--> "+columns.get(i).getText());
                if (columns.get(i).getText().equals("Integrator Asset ID")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), AssetIntegratorID, "Integrator Asset ID not displayed");
                }

                if (columns.get(i).getText().equals("animal Size")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), AssetanimalSize, "animal Size not displayed");
                }

                if (columns.get(i).getText().equals("animal Sex")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), AssetanimalSex, "animal Sex not displayed");
                }

                if (columns.get(i).getText().equals("Marketing Program")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), "Conventional", "Marketing Program not displayed");
                }

                if (columns.get(i).getText().equals("Target Weight (Lbs)")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), "10", "Marketing Program not displayed");
                }

                if (columns.get(i).getText().equals("animal Breed")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), AssetBreedName, "Breed Name not displayed");
                }

                if (columns.get(i).getText().equals("Hatchery Source")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), AssetHatcheryName, "Hatchery Source not displayed");
                }

                if (columns.get(i).getText().equals("Monitoring Plan")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), getSamplingPlanName(), "Monitoring Plan not displayed");
                }

                if (columns.get(i).getText().equals("Age At deployment")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), "2", "Age of deployment not displayed");
                }

                if (columns.get(i).getText().equals("Unit")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), "Days", "Unit not displayed");
                }
            }

            AssetManagementPage.openAssetAudit();
            softAssert.assertEquals(size(auditGetRowCount), 2, "Audit Log not displaying a row for Asset creation");
            softAssert.assertEquals(getText(getauditActionRow1), "Created", "Audit Log not displaying a row for with Action created");
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

    @Test(enabled = true, priority = 2, groups = "regression")
    public void AttachProgramWithEndDateGreaterThendeploymentDate() throws IOException {
        try {
            test = extent.createTest("AN-FM-02: Verify user can attach created program with created Asset with End Date greater than deployment Date");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_programAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            scroll(programCreateButton);
            click(programCreateButton);
            //Program DisplayName
            type(programDisplayName, AssetProgramName);
            Thread.sleep(500);
            //Program Type
            type(programProgramType, "Feed");
            Thread.sleep(500);
            enterKey(programProgramType);
            Thread.sleep(500);
            //Complex
            click(programComplexList);
            type(programComplexSearch, Queries.getComplexName());
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
            //Target Pathogen
            click(programTargetPathogen);
            Thread.sleep(500);
            type(programTargetPathogen, "Patho_1");
            enterKey(programTargetPathogen);
            click(programTargetPathogenDropdownExpand);
            click(programTradeNameFeed);
            enterKey(programTradeNameFeed);
            Thread.sleep(1000);
            click(programFeedTypeDropdown);
            Thread.sleep(500);
            enterKey(programFeedTypeDropdown);
            type(programAssetDayStart, "1");
            WebElement EndDay = getDriver().findElement(programAssetDayStart);
            getDriver().findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");
            Thread.sleep(1000);
            click(popupSaveButtonXpath);
            Thread.sleep(1500);
            softAssert.assertEquals(getText(alertMessage), "New program has been created successfully");
            getDriver().get(url_assetAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(fieldAccessIcon);
            waitElementInvisible(loading_cursor);
            click(resetDefaultButton);
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            AssetManagementPage.openEditAsset();
            waitElementInvisible(loading_cursor);
            Thread.sleep(4000);
            scroll(AssetAddNewProgram);
            click(AssetAddNewProgram);
            click(AssetAddNewProgramTypeInput);
            click(selectProgramFeedType);
            click(AssetAddNewProgramNameInput);
            type(AssetAddNewProgramNameInput, AssetProgramName);
            // Verify that program name is shown in dropdown
            List<WebElement> dropdownValues = getDriver().findElements(AssetSelectProgramDropdownValues);
            softAssert.assertEquals(dropdownValues.get(0).getText(), AssetProgramName);
            enterKey(AssetAddNewProgramNameInput);
            Thread.sleep(250);
            getScreenshot();
            click(savaAndCloseButton);
            // Disassociate the program from the Asset
            AssetManagementPage.openEditAsset();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            scroll(programDeleteIcon);
            click(programDeleteIcon);
            Thread.sleep(250);
            click(savaAndCloseButton);
            softAssert.assertAll();
            test.pass("Programs with End Date greater than the deployment date of the Asset appeared in program dropdown successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Programs with End Date greater than the deployment date of the Asset failed to appear in the program dropdown");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Programs with End Date greater than the deployment date of the Asset failed to appear in the program dropdown");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 3, groups = "regression")
    public void AttachProgramWithEndDateLessThendeploymentDate() throws IOException {
        try {
            test = extent.createTest("AN-FM-03: Verify user is not able to attach a program with Asset whose end date is less than the deployment date of Asset");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_programAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(programColumnFilter);
            type(programColumnSearchField, AssetProgramName);
            Thread.sleep(250);
            click(By.id(programSearch + "" + AssetProgramName));
            click(programColumnApplyField);
            Thread.sleep(3000);
            click(programEditButton);
            Thread.sleep(3000);
            //End Date
            click(programEndDateIcon);
            waitElementInvisible(loading_cursor);
            Methods method = new Methods();
            WebElement dateWidgetToEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
            List<WebElement> columns1 = dateWidgetToEnd.findElements(By.tagName("button"));
            clickGivenDay(columns1, getFirstDay());
            Thread.sleep(1000);
            getScreenshot();
            click(popupSaveButtonXpath);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);
            softAssert.assertEquals(getText(alertMessage), "Program details updated");
            getDriver().get(url_assetAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(fieldAccessIcon);
            click(resetDefaultButton);
            click(popupSaveButton);
            AssetManagementPage.openEditAsset();
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);
            scroll(AssetAddNewProgram);
            click(AssetAddNewProgram);
            click(AssetAddNewProgramTypeInput);
            click(selectProgramFeedType);
            click(AssetAddNewProgramNameInput);
            type(AssetAddNewProgramNameInput, AssetProgramName);
            // Verify that program name is not shown in dropdown
            int programIsPresent = size(By.cssSelector(".ng-option-disabled"));
            System.out.println(programIsPresent);
            softAssert.assertEquals(programIsPresent, 1, "Program is present in dropdown, test case failed");
            Thread.sleep(250);
            getScreenshot();
            softAssert.assertAll();
            test.pass("Programs with End Date less than the deployment date of the Asset did not appear in the program dropdown successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Programs with End Date less than the deployment date of the Asset appeared in the program dropdown");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Programs with End Date less than the deployment date of the Asset appeared in the program dropdown");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 4, groups = "regression")
    public void AttachProgramWithEndDateEmpty() throws IOException {
        try {
            test = extent.createTest("AN-FM-04: Verify user can attach Program with End Date empty with Asset");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_programAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(programColumnFilter);
            type(programColumnSearchField, AssetProgramName);
            Thread.sleep(250);
            click(By.id(programSearch + "" + AssetProgramName));
            click(programColumnApplyField);
            Thread.sleep(1000);
            click(programEditButton);
            //End Date
            click(programEndDateCrossSign);
            Thread.sleep(500);
            click(popupSaveButtonXpath);
            Thread.sleep(1500);
            softAssert.assertEquals(getText(alertMessage), "Program details updated");
            getDriver().get(url_assetAdmin);
            Thread.sleep(2000);
            click(fieldAccessIcon);
            click(resetDefaultButton);
            click(popupSaveButton);
            AssetManagementPage.openEditAsset();
            Thread.sleep(1000);
            scroll(AssetAddNewProgram);
            click(AssetAddNewProgram);
            click(AssetAddNewProgramTypeInput);
            click(selectProgramFeedType);
            click(AssetAddNewProgramNameInput);
            type(AssetAddNewProgramNameInput, AssetProgramName);
            // Verify that program name is shown in dropdown
            List<WebElement> dropdownValues = getDriver().findElements(AssetSelectProgramDropdownValues);
            softAssert.assertEquals(dropdownValues.get(0).getText(), AssetProgramName);
            enterKey(AssetAddNewProgramNameInput);
            Thread.sleep(250);
            getScreenshot();
            click(savaAndCloseButton);
            waitElementInvisible(loading_cursor);
            softAssert.assertAll();
            // Disassociate the program from the Asset
            AssetManagementPage.openEditAsset();
            Thread.sleep(1000);
            scroll(programDeleteIcon);
            click(programDeleteIcon);
            Thread.sleep(250);
            click(savaAndCloseButton);
            softAssert.assertAll();
            test.pass("Programs with End Date empty appeared in the program dropdown successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Programs with End Date empty did not appear in the program dropdown");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Programs with End Date empty did not appear in the program dropdown");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 5, groups = "regression")
    public void AttachProgramWithEndDateEqualTodeploymentDate() throws IOException {
        try {
            test = extent.createTest("AN-FM-05: Verify user can attach Program with End Date equal to deployment Date");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_programAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(4000);
            click(programColumnFilter);
            type(programColumnSearchField, AssetProgramName);
            Thread.sleep(250);
            click(By.id(programSearch + "" + AssetProgramName));
            click(programColumnApplyField);
            Thread.sleep(1000);
            click(programEditButton);
            Thread.sleep(2000);
            //End Date
            click(programEndDateIcon);
            waitElementInvisible(loading_cursor);
            List<WebElement> list = getDriver().findElements(By.cssSelector(".dp-current-day"));
            list.get(1).click();
            getScreenshot();
            click(popupSaveButtonXpath);
            Thread.sleep(1500);
            softAssert.assertEquals(getText(alertMessage), "Program details updated");
            click(resetFilterButton);
            getDriver().get(url_assetAdmin);
            Thread.sleep(2000);
            click(fieldAccessIcon);
            click(resetDefaultButton);
            click(popupSaveButton);
            AssetManagementPage.openEditAsset();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            scroll(AssetAddNewProgram);
            click(AssetAddNewProgram);
            click(AssetAddNewProgramTypeInput);
            Thread.sleep(1000);
            click(selectProgramFeedType);
            click(AssetAddNewProgramNameInput);
            type(AssetAddNewProgramNameInput, AssetProgramName);
            // Verify that program name is shown in dropdown
            List<WebElement> dropdownValues = getDriver().findElements(AssetSelectProgramDropdownValues);
            softAssert.assertEquals(dropdownValues.get(0).getText(), AssetProgramName);
            enterKey(AssetAddNewProgramNameInput);
            Thread.sleep(250);
            getScreenshot();
            click(savaAndCloseButton);
            softAssert.assertAll();
            // Disassociate the program from the Asset
            AssetManagementPage.openEditAsset();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            scroll(programDeleteIcon);
            click(programDeleteIcon);
            Thread.sleep(250);
            click(savaAndCloseButton);
            // Delete the program
            getDriver().get(url_programAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(programColumnFilter);
            waitElementInvisible(loading_cursor);
            type(programColumnSearchField, AssetProgramName);
            Thread.sleep(250);
            click(By.id(programSearch + "" + AssetProgramName));
            click(programColumnApplyField);
            Thread.sleep(3000);
            if (getText(AssetProgramDisplayName).equals(AssetProgramName)) {
                click(programDeleteButton);
                click(popupYesButton);
                waitElementVisible(alertMessage);
                softAssert.assertEquals(getText(alertMessage), "Program details deleted");
            } else {
                test.fail("Program not deleted");
            }
            softAssert.assertAll();
            test.pass("Verify Program association with End Date equal to deployment date test case passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify Program association with End Date equal to deployment date test case failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify Program association with End Date equal to deployment date test case failed.");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(description = "IE-3595", enabled = true, priority = 6, groups = "regression")
    public void DuplicateAssetWithSameSiteAnddeploymentDate() throws IOException {
        try {
            test = extent.createTest("AN-FR-06: Verify that Asset cannot be duplicated with same Site ID and deployment Date.");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_assetAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);
            click(AssetCreateButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);
            type(AssetanimalSizeInput, AssetManagementModel.AssetanimalSize);
            Thread.sleep(1000);
            getDriver().findElement(AssetanimalSizeInput).sendKeys(Keys.ENTER);
            click(AssetProductionTypeList);
            List<WebElement> productionTypeList = getDriver().findElements(AssetProductionTypeListItems);
            productionTypeList.get(1).click();
            click(AssetFarmDropdownExpand);
            type(AssetFarmDropdownSearch, Queries.getFarmName());
            waitElementInvisible(loading_cursor);
            waitElementClickable(By.cssSelector("label b"));
            Thread.sleep(2000);
            click(By.cssSelector("label b"));
            click(AssetdeploymentDateCalendar);
            List<WebElement> list = getDriver().findElements(By.cssSelector(".dp-current-day"));
            Thread.sleep(1000);
            list.get(2).click();
            Thread.sleep(1000);
            getScreenshot();
            if (size(By.cssSelector("#btn-submit.disabled-v2")) == 0) {
                click(By.id("btn-submit"));
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                if (getText(alertMessage).contains("Data saved")) {
                    Assert.assertTrue(false, "Asset created with same deployment date");
                }
            } else {
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                softAssert.assertTrue(getDriver().findElement(alertMessage).getText().contains("already exists at"));
            }
            softAssert.assertAll();
            test.pass("Duplicate Asset failed to create successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Duplicate Asset created");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Duplicate Asset created");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 7, groups = "regression")
    public void EditAsset() throws IOException {
        try {
            test = extent.createTest("AN-FR-07: Verify user can edit Asset");
            getDriver().get(url_assetAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();
            click(fieldAccessIcon);
            waitElementInvisible(loading_cursor);
            click(resetDefaultButton);
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            AssetManagementPage.openAssetAudit();
            int getRowsPre = size(AssetAuditRowCount);
            Thread.sleep(1000);
            click(popupCloseButton);
            AssetManagementPage.openEditAsset();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            clear(AssetanimalSexInput);
            type(AssetanimalSexInput, "Female");
            getDriver().findElement(AssetanimalSexInput).sendKeys(Keys.ENTER);

            /////////////////////////////IE-10928///////////////////////////////////
            softAssert.assertEquals(size(By.xpath("//label[text()= 'deployment Density ']")), 1, "deployment Density field not shown");

            /////////////////////////////IE-13189///////////////////////////////////
            scroll(monitoringStrategyButton);
            click(monitoringStrategyButton);
            Thread.sleep(1000);
            if (size(clearSelectedMonitoringPlan) == 1) {
                click(clearSelectedMonitoringPlan);
            }
            click(selectMonitoringPlan);

            if (size(noValues) == 0) {
                String getSamplingPlanName = getSamplingPlanName();
                type(selectMonitoringPlanInput, getSamplingPlanName);
                click(By.xpath("//*[@id = 'collectionPlan-0-0']//label[contains(text(),'" + getSamplingPlanName + "')]"));
                Thread.sleep(1000);
            } else {
                System.out.println("No plan is associated.");
            }
            softAssert.assertEquals(size(alertMessage), 0, "Error occured");

            click(monitoringPlanSaveButton);
            Thread.sleep(1000);
            waitElementVisible(popupSubmitButton);

            /////////////IE-9594/////////////////
            softAssert.assertEquals(size(By.xpath("//*[text()='Estimated Processing Date']")), 1, "Estimated Processing Date is not displayed");
            /////////////IE-9594/////////////////
            //////////////////////////IE-10351 Case 1///////////////////////////////
            click(AssetEditMortalityTab);
            click(popupNoButton);
            softAssert.assertEquals(size(AssetHousePlacedDropdownExpand), 1, "Did not stayed on Asset deployment tab");
            click(AssetEditMortalityTab);
            click(popupYesButton);
            Thread.sleep(1000);
            softAssert.assertEquals(size(By.cssSelector("#num-week_1_Mortality")), 1, "Mortality tab not displayed");
            click(AssetEditdeploymentTab);
            //////////////////////////IE-10351 Case 1///////////////////////////////
            click(By.xpath("(//*[@id = \"btn-save\"])[1]"));
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");

            click(AssetEditMortalityTab);
            waitElementInvisible(loading_cursor);
            for (int i = 1; i <= 9; i++) {
                By mortalityField = By.cssSelector("#num-week_" + i + "_Mortality");
                scroll(mortalityField);
                type(mortalityField, Integer.toString(i));
            }
            //////////////////////////IE-10351 Case 2///////////////////////////////
            click(AssetEditSettlementTab);
            click(popupNoButton);
            softAssert.assertEquals(size(By.cssSelector("#num-week_1_Mortality")), 1, "Did not stayed on Asset mortality tab");
            click(AssetEditSettlementTab);
            click(popupYesButton);
            Thread.sleep(1000);
            softAssert.assertEquals(size(AssetWeeklyFarmRank), 1, "Settlement tab not displayed");
            click(AssetEditMortalityTab);
            //////////////////////////IE-10351 Case 2///////////////////////////////
            click(By.xpath("(//*[@id = 'btn-save'])[2]"));
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");

            click(AssetEditSettlementTab);
            waitElementInvisible(loading_cursor);
            type(AssetWeeklyFarmRank, "1");
            type(AssetHistoricalFarmCostVariance, "1");
            type(AssetWeeklyFarmCostVariance, "1");
            type(AssetdeploymentDensityInput, "1.5");
            softAssert.assertEquals(getAttribute(AssetdeploymentDensityInput), "1.5", "Not able to write decimal value in Asset density field");
            //////////////////////////IE-10351 Case 3///////////////////////////////
            click(AssetEditCondemnationTab);
            click(popupNoButton);
            softAssert.assertEquals(size(AssetWeeklyFarmRank), 1, "Did not stayed on Asset settlement tab");
            click(AssetEditCondemnationTab);
            click(popupYesButton);
            Thread.sleep(1000);
            softAssert.assertEquals(size(AssetNumanimalsDOAPlant), 1, "Condemnation tab not displayed");
            click(AssetEditSettlementTab);
            //////////////////////////IE-10351 Case 3///////////////////////////////
            click(By.xpath("(//*[@id = 'btn-save'])[3]"));
            waitElementVisible(alertMessage);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");
            click(AssetEditCondemnationTab);
            type(AssetNumanimalsDOAPlant, "1");
            //////////////////////////IE-10351 Case 4///////////////////////////////
            click(AssetEditSettlementTab);
            click(popupNoButton);
            softAssert.assertEquals(size(AssetNumanimalsDOAPlant), 1, "Did not stayed on Asset condemnation tab");
            click(AssetEditSettlementTab);
            click(popupYesButton);
            Thread.sleep(1000);
            softAssert.assertEquals(size(AssetWeeklyFarmRank), 1, "Settlement tab not displayed");
            click(AssetEditCondemnationTab);
            //////////////////////////IE-10351 Case 4///////////////////////////////
            click(By.xpath("(//*[@id = 'btn-save'])[4]"));
            waitElementVisible(alertMessage);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");
            click(popupCloseButton);

            AssetManagementPage.openAssetAudit();
            int getRowsPost = size(AssetAuditRowCount);
            click(popupCloseButton);
            softAssert.assertEquals(getRowsPost, getRowsPre + 1);
            softAssert.assertAll();
            test.pass("Asset was edited successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Asset failed to edit");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Asset failed to edit");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 8, groups = "regression")
    public void GetdeploymentHouses() throws IOException {
        try {
            test = extent.createTest("AN-FR-8: Verify House deployment popup from log and audit view");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_assetAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(fieldAccessIcon);
            click(resetDefaultButton);
            click(popupSaveButton);
            for (int i = 1; i < size(By.cssSelector("tr")); i++) {
                if (size(By.cssSelector("tr:nth-child(" + i + ") #col-" + AssetProgramHousedeploymentCol + " label")) == 2) {
                    String getHouseNames = getText(By.cssSelector("tr:nth-child(" + i + ") #col-" + AssetProgramHousedeploymentCol + " label[title]:nth-child(2)"));
                    click(By.cssSelector("tr:nth-child(1) #col-" + AssetProgramHousedeploymentCol));
                    Thread.sleep(2000);
                    int totalHouses = Integer.parseInt(getHouseNames.substring(1, getHouseNames.length() - 5)) + 2;
                    softAssert.assertEquals(size(popupTotalRows), totalHouses);
                    softAssert.assertEquals(size(alertMessage), 0, "Exception occurred");
                    click(popupCloseButton);
                    int j = i - 1;
                    click(By.id("audit-trial-" + j));
                    Thread.sleep(3000);
                    int auditRowsCount = size(popupTotalRows);
                    scroll(By.cssSelector(".popup-card tr:nth-child(1) #col-" + deploymentAuditHousedeploymentCol));
                    Thread.sleep(1000);
                    click(By.cssSelector(".popup-card tr:nth-child(1) #col-" + deploymentAuditHousedeploymentCol));
                    softAssert.assertEquals(size(popupTotalRows) - auditRowsCount, totalHouses);
                    softAssert.assertEquals(size(alertMessage), 0, "Exception occurred");
                    click(popupCloseButton);
                    test.pass("House deployment popup tested successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                    break;
                }
            }
        } catch (AssertionError er) {
            test.fail("House deployment log not displaying all houses");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("House deployment log not displaying all houses");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 9, groups = "regression")
    public void InlineEditAsset() throws IOException {
        try {
            test = extent.createTest("AN-FM-09: Verify Inline Edit functionality");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_assetAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            click(fieldAccessIcon);
            click(resetDefaultButton);
            click(popupSaveButton);
            click(By.id(AssetIntegratorIDFilter + AssetShowFilter));
            type(By.id(AssetIntegratorIDFilter + AssetSearchFilter), AssetIntegratorID);
            click(By.cssSelector("#ul-integratorAssetId li:nth-child(1) label"));  //Select All
            click(By.id(AssetIntegratorIDFilter + AssetApplyFilter));
            hover(By.cssSelector("#" + AssetdeploymentTable + " #" + AssetInlineButton));
            //  softAssert.assertEquals(size(By.cssSelector("#" + AssetdeploymentTable + " #" + AssetInlineButtonTooltip)), 1, "Tooltip is not applied on in line edit button");
            click(By.id(AssetInlineButton));
            waitElementInvisible(loading_cursor);
            //verify fields should not be editable
            softAssert.assertEquals(size(By.cssSelector("#" + AssetdeploymentTable + " #col-" + AssetIDdeploymentCol + " input")), 0, "Asset ID is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetdeploymentTable + " #col-" + AssetNumofanimalsPlaceddeploymentCol + " input")), 0, "Number of animals placed is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetdeploymentTable + " #col-" + AssetComplexdeploymentCol + " input")), 0, "Complex is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetdeploymentTable + " #col-" + AssetIntegratordeploymentCol + " input")), 0, "Integrator is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetdeploymentTable + " #col-" + AssetFarmSiteIDdeploymentCol + " input")), 0, "Farm Site ID is editable field");
            click(AssetInlineNewProgramIcon);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            if (size(AssetProgramDeleteButton) != 0) {
                click(AssetProgramDeleteButton);
            }
            click(AssetInlineAddNewProgramPopup);
            waitElementVisible(AssetInlineProgramName);
//       type(AssetInlineProgramName, AssetManagementModel.AssetProgramName);
            click(AssetInlineProgramName);
            if (size(noValues) == 0) {
                Thread.sleep(500);
                enterKey(AssetInlineProgramName);
                type(AssetDoseInput, "1.52");
                click(AssetProgramSaveButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
            } else {
                System.out.println("no program exists in dropdown");
                click(popupCloseButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(500);
            }
            clear(AssetanimalBreed);
            type(AssetanimalBreed, "Female");
            enterKey(AssetanimalBreed);
            waitElementInvisible(loading_cursor);
            Thread.sleep(250);
            click(By.cssSelector("#" + AssetdeploymentTable + " #" + AssetInlineButtonSave));
            waitElementInvisible(loading_cursor);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            getScreenshot();
            click(AssetMortalityTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            //    softAssert.assertEquals(size(By.cssSelector("#"+AssetMortalityTable+" #"+AssetInlineButtonTooltip)), 1, "Tooltip is not applied on in line edit button");
            try {
                waitElementClickable(By.cssSelector("#" + AssetMortalityTable + " #" + AssetInlineButton));
                click(By.cssSelector("#" + AssetMortalityTable + " #" + AssetInlineButton));
            } catch (ElementNotInteractableException ex) {
                click(By.cssSelector("#" + AssetMortalityTable + " #" + AssetInlineButton));
            }
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            softAssert.assertEquals(size(By.cssSelector("#" + AssetMortalityTable + " #col-" + AssetAssetIDCol + " input")), 0, "Asset ID is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetMortalityTable + " #col-" + AssetIntegratorAssetIDCol + " input")), 0, "Integrator Asset ID is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetMortalityTable + " #col-" + AssetIntegratorCol + " input")), 0, "Integrator placed is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetMortalityTable + " #col-" + AssetComplexCol + " input")), 0, "Complex is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetMortalityTable + " #col-" + AssetFarmCol + " input")), 0, "Farm is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetMortalityTable + " #col-" + AssetFarmSiteIDCol + " input")), 0, "Farm Site ID is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetMortalityTable + " #col-" + AssetMortalitydeploymentDateCol + " input")), 0, "deployment Date is editable field");
            clear(AssetInlineMortality1Input);
            type(AssetInlineMortality1Input, "2.5");
            click(By.cssSelector("#" + AssetMortalityTable + " #" + AssetInlineButtonSave));
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            getScreenshot();
            click(AssetSettlementTab);
            Thread.sleep(2500);
            // softAssert.assertEquals(size(By.cssSelector("#"+AssetSettlementTable+" #"+AssetInlineButtonTooltip)), 1, "Tooltip is not applied on in line edit button");
            try {
                waitElementClickable(By.cssSelector("#" + AssetSettlementTable + " #" + AssetInlineButton));
                click(By.cssSelector("#" + AssetSettlementTable + " #" + AssetInlineButton));
            } catch (ElementNotInteractableException ex) {
                click(By.cssSelector("#" + AssetSettlementTable + " #" + AssetInlineButton));
            }
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(size(By.cssSelector("#" + AssetSettlementTable + " #col-" + AssetAssetIDCol + " input")), 0, "Asset ID is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetSettlementTable + " #col-" + AssetIntegratorAssetIDCol + " input")), 0, "Integrator Asset ID is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetSettlementTable + " #col-" + AssetIntegratorCol + " input")), 0, "Integrator placed is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetSettlementTable + " #col-" + AssetComplexCol + " input")), 0, "Complex is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetSettlementTable + " #col-" + AssetFarmCol + " input")), 0, "Farm is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetSettlementTable + " #col-" + AssetFarmSiteIDCol + " input")), 0, "Farm Site ID is editable field");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetSettlementTable + " #col-" + AssetSettlementdeploymentDateCol + " input")), 0, "deployment Date is editable field");
            type(AssetInlineSettlementWeeklyFarmRank, "1");
            type(AssetInlineSettlementHistoricalFarmCostVariance, "2");
            type(AssetInlineSettlementWeeklyFarmCostVariance, "3");
            type(AssetInlineSettlementhatchDate, dateMMDDYYYY1);
            type(AssetInlineSettlementDaysOut, "4");
            type(AssetInlineSettlementAgeofLitter, "5");
            type(AssetInlineSettlementAverageSoldAge, "6");
            type(AssetInlineSettlementNumanimalsSold, "7");
            clear(AssetInlineSettlementdeploymentDensity);
            type(AssetInlineSettlementdeploymentDensity, "8.5");
            softAssert.assertEquals(getAttribute(AssetInlineSettlementdeploymentDensity), "8.5", "deployment Density is not accepting decimal value");
            type(AssetInlineSettlementProcessingDate, dateMMDDYYYY1);
            type(AssetInlineSettlementProcessingSiteID, "9");
            type(AssetInlineSettlementUSDAPlantID, "10");
            type(AssetInlineSettlementPlantLocation, "11");
            type(AssetInlineSettlementNumOfBridsProcessed, "12");
            type(AssetInlineSettlementAvganimalWeightLB, "13");
            type(AssetInlineSettlementAvganimalWeightKG, "14");
            type(AssetInlineSettlementTotalWeightProcessedLB, "15");
            type(AssetInlineSettlementTotalWeightProcessedKG, "16");
            type(AssetInlineSettlementTotalFeedWeightLB, "17");
            type(AssetInlineSettlementTotalFeedWeightKG, "18");
            type(AssetInlineSettlementFCR, "19");
            type(AssetInlineSettlementAdjustedFCR, "20");
            type(AssetInlineSettlementFeedCostPerLivePound, "21");
            type(AssetInlineSettlementMedicationCostPerLivePound, "22");
            type(AssetInlineSettlementGrowerCostPerLivePound, "23");
            type(AssetInlineSettlementLivabilityPercentage, "24");
            type(AssetInlineSettlementOverallMortalityPercentage, "25");
            click(By.cssSelector("#" + AssetSettlementTable + " #" + AssetInlineButtonSave));
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            getScreenshot();
            click(AssetCondemnationTab);
            Thread.sleep(2500);
            // softAssert.assertEquals(size(By.cssSelector("#"+AssetCondemnationTable+" #"+AssetInlineButtonTooltip)), 1, "Tooltip is not applied on in line edit button");
            try {
                waitElementClickable(By.cssSelector("#" + AssetCondemnationTable + " #" + AssetInlineButton));
                click(By.cssSelector("#" + AssetCondemnationTable + " #" + AssetInlineButton));
            } catch (ElementNotInteractableException ex) {
                click(By.cssSelector("#" + AssetCondemnationTable + " #" + AssetInlineButton));
            }
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(size(By.cssSelector("#" + AssetCondemnationTable + " #col-" + AssetAssetIDCol + " input")), 0, "Asset ID is editable field in Condemnation Tab");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetCondemnationTable + " #col-" + AssetIntegratorAssetIDCol + " input")), 0, "Integrator Asset ID is editable field in Condemnation Tab");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetCondemnationTable + " #col-" + AssetIntegratorCol + " input")), 0, "Integrator placed is editable field in Condemnation Tab");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetCondemnationTable + " #col-" + AssetComplexCol + " input")), 0, "Complex is editable field in Condemnation Tab");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetCondemnationTable + " #col-" + AssetFarmCol + " input")), 0, "Farm is editable field in Condemnation Tab");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetCondemnationTable + " #col-" + AssetFarmSiteIDCol + " input")), 0, "Farm Site ID is editable field in Condemnation Tab");
            softAssert.assertEquals(size(By.cssSelector("#" + AssetCondemnationTable + " #col-" + AssetCondemnationdeploymentDateCol + " input")), 0, "deployment Date is editable field in Condemnation Tab");
            type(AssetInlineNumanimalsDOAPlantCondemnation, "1");
            type(AssetInlineTotalWeightCondemnedLBCondemnation, "2");
            type(AssetInlineTotalWeightCondemnedKGCondemnation, "3");
            type(AssetInlineNumanimalsCondemnedWholeCondemnation, "4");
            type(AssetInlinePartsWeightCondemnedLBCondemnation, "5");
            type(AssetInlinePartsWeightCondemnedKGCondemnation, "6");
            type(AssetInlineKCalLBCondemnation, "7");
            type(AssetInlineGradeAPawsPercentageCondemnation, "8");
            type(AssetInlineIPPercentageCondemnation, "9");
            type(AssetInlineLeukosisPercentageCondemnation, "10");
            type(AssetInlineSeptoxPercentageCondemnation, "11");
            type(AssetInlineIPPercentageCondemnation, "12");
            type(AssetInlineTumorPercentageCondemnation, "13");
            click(By.cssSelector("#" + AssetCondemnationTable + " #" + AssetInlineButtonSave));
            click(popupYesButton);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            softAssert.assertAll();
            test.pass("Asset inline tested successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Asset inline failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Asset inline failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public static int getSitesAssigned() throws SQLException {
        ResultSet getSitesAssigned = DB_Config_DB.getStmt().executeQuery(getSitesAssignedtoUserCount(getUsersId()));
        int siteAssigned = 0;
        while (getSitesAssigned.next()) {
            siteAssigned = getSitesAssigned.getInt("sitesassigned");
            System.out.println("Sites Assigned Count: " + siteAssigned);
        }
        return siteAssigned;
    }

    @Test(description = "IE-4153", enabled = true, priority = 10, groups = "regression")
    public void VerifyUserSitesInFarmDropDown() throws InterruptedException, IOException {
        try {
            test = extent.createTest("AN-FR-10: Verify that all sites assigned to user are displayed in Asset Farm dropdown");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_assetAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);
            click(AssetCreateButton);
            Thread.sleep(2000);
            scroll(AssetFarmDropdownExpand);
            click(AssetFarmDropdownExpand);
            int sitesCountFarmDropdown = size(AssetFarmDropdownGetAllSites);
            click(popupCloseButton);
            softAssert.assertEquals(sitesCountFarmDropdown, getSitesAssigned());
            softAssert.assertAll();
            test.pass("Only those sites appeared  which are assigned to user successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Sites not appeared  which are assigned to user");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Sites not appeared  which are assigned to user");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 12, description = "IE-10489", groups = "regression")
    public void CreateFarmExternalID() throws IOException {
        try {
            test = extent.createTest("AN-FR-11: Verify farm external id populates when user selects farm from dropdown");
            SoftAssert softAssert = new SoftAssert();
            click(AssetCreateButton);
            Thread.sleep(5000);
            scroll(AssetFarmDropdownExpand);
            String beforeFarmFieldValue = getAttribute(farmExternalSiteId);
            System.out.println("Farm field value should come empty: " + beforeFarmFieldValue);
            softAssert.assertEquals(size(farmExternalSiteId), 1, "Farm external site id not present");
            System.out.println(Queries.getFarmNameWithExternalIDAssignedToUser());
            scroll(AssetFarmDropdownExpand);
            click(AssetFarmDropdownExpand);
            type(AssetFarmDropdownSearch, Queries.getFarmNameWithExternalIDAssignedToUser());
            waitElementInvisible(loading_cursor);
            waitElementClickable(By.cssSelector("label b"));
            Thread.sleep(2000);
            click(By.cssSelector("label b"));
            // Check if the field is non-editable
            String disabledAttribute = getDriver().findElement(farmExternalSiteId).getAttribute("disabled");
            if (disabledAttribute != null && disabledAttribute.equalsIgnoreCase("true")) {
                softAssert.assertTrue(true, "Farm External Site ID field is not editable.");
            } else {
                softAssert.assertTrue(false, "Farm External Site ID field is editable.");
            }
            // Verify value is populated
            String afterFarmSelectedFarmFieldValue = getDriver().findElement(farmExternalSiteId).getAttribute("value");
            // Print the retrieved value
            System.out.println("Farm field value after farm selected: " + afterFarmSelectedFarmFieldValue);
            softAssert.assertNotEquals(beforeFarmFieldValue, afterFarmSelectedFarmFieldValue, "Farm field value did not got populated");
            softAssert.assertAll();
            getScreenshot();
            test.pass("Verify farm external id populates when user selects farm from dropdown test case passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify farm external id populates when user selects farm from dropdown test case failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify farm external id populates when user selects farm from dropdown test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = false, priority = 13, description = "IE-10924", groups = "regression")
    public void VerifydeploymentDensityFunctionality() throws IOException {
        try {
            test = extent.createTest("AN-FM-12: Verify farm deployment density functionality");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_organizationManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            OrganizationManagementPage.CreateOrganizationFunction(OrganizationName);
            waitElementClickable(orgAddSite1);
            click(orgAddSite1);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(orgSiteTypeInput);
            String regionType = getDriver().findElement(orgSiteTypeDropDownValue).getText();
            softAssert.assertEquals(regionType, "Region");
            getDriver().findElement(orgSiteNameInput).sendKeys("Test Region");
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");
            click(orgAddSite2);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(orgSiteTypeInput);
            String subregionType = getDriver().findElement(orgSiteTypeDropDownValue).getText();
            softAssert.assertEquals(subregionType, "Sub Region");
            getDriver().findElement(orgSiteNameInput).sendKeys("Test Sub Region");
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");
            getDriver().findElement(orgAddSite3).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getDriver().findElement(orgSiteTypeInputChild).click();
            Thread.sleep(1000);

            click(By.cssSelector("div .ng-option:nth-child(1)"));
            type(orgSiteNameInput, "Test Complex Site");
            type(By.id("num-costPerPointOfAfcrId"), "1");
            type(By.id("num-costPerPointOfFcrId"), "1");
            type(By.id("num-targetAfcrId"), "1.2");
            type(By.id("num-targetFcrId"), "1.2");
            Thread.sleep(250);
            WebElement saveButton = getDriver().findElement(popupSaveButton);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", saveButton);
            //getDriver().findElement(popupSaveButton).click();
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");
            waitElementVisible(orgAddSite4);
            getDriver().findElement(orgAddSite4).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getDriver().findElement(orgSiteTypeInputChild).click();
            String farmType = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
            softAssert.assertEquals(farmType, "Farm");
            getDriver().findElement(orgSiteNameInput).sendKeys("Test Farm");
            Thread.sleep(250);
            //getDriver().findElement(popupSaveButton).click();
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", saveButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");
            waitElementVisible(orgAddSite5);
            getDriver().findElement(orgAddSite5).click();
            Thread.sleep(2000);
            getDriver().findElement(orgSiteTypeInputChild).click();
            String HouseType = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
            softAssert.assertEquals(HouseType, "House");
            getDriver().findElement(orgSiteNameInput).sendKeys("Test House");
            type(squareFootage, "5");
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", saveButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");
            getDriver().get(url_assetAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(4000);
            click(AssetCreateButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);
            scroll(AssetFarmDropdownExpand);
            click(AssetFarmDropdownExpand);
            type(AssetFarmDropdownSearch, OrganizationName);
            waitElementInvisible(loading_cursor);
            waitElementClickable(By.cssSelector("label b"));
            Thread.sleep(2000);
            click(By.cssSelector("label b"));
            waitElementInvisible(loading_cursor);
            type(numOfanimalsField, "2");
            String disabledAttribute = getDriver().findElement(deploymentDensityField).getAttribute("disabled");
            if (disabledAttribute != null && disabledAttribute.equalsIgnoreCase("true")) {
                softAssert.assertTrue(true, "Farm deployment density field is not editable.");
            } else {
                softAssert.assertTrue(false, "Farm deployment density field is editable.");
            }
            // Verify value is populated
            String deploymentDensityValue = getDriver().findElement(deploymentDensityField).getAttribute("value");
            softAssert.assertEquals(deploymentDensityValue, "0.4", "deployment density value is not correct");
            softAssert.assertAll();
            getScreenshot();
            test.pass("Verify farms deployment density functionality test case passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify farms deployment density functionality test case failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify farms deployment density functionality test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 14, description = "IE-12299", groups = "regression")
    public void CreateAssetHierarchy() throws IOException {
        try {
            test = extent.createTest("Verify that user is able to create Asset hierarchy by putting in valid info.");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_assetAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(AssetProductionTypeShowFilter);
            waitElementInvisible(loading_cursor);
            type(AssetProductionTypeSearchFilter, "Breeder");
            Thread.sleep(2000);
            click(AssetProductionSelectAllFilter);
            click(AssetProductionTypeApplyFilter);
            waitElementInvisible(loading_cursor);
            Thread.sleep(8000);
            String breederAssetID = getDriver().findElement(By.cssSelector("#row-0 #col-0")).getText();
            System.out.println(breederAssetID);
            click(AssetProductionTypeClearFilter);
            waitElementInvisible(loading_cursor);
            click(AssetProductionTypeShowFilter);
            waitElementInvisible(loading_cursor);
            type(AssetProductionTypeSearchFilter, "Broiler");
            Thread.sleep(3000);
            click(AssetProductionSelectAllFilter);
            click(AssetProductionTypeApplyFilter);
            waitElementInvisible(loading_cursor);
            Thread.sleep(8000);
            String broilerAssetID = getDriver().findElement(By.cssSelector("#row-0 #col-0")).getText();
            System.out.println(broilerAssetID);
            getDriver().get(url_assetAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);
            String results = getDriver().findElement(By.xpath("//*[contains(text(), 'results found')]/preceding-sibling::span")).getText();
            System.out.println(results);
            Thread.sleep(10000);
            waitElementVisible(By.id(ResultsCount));
            String PreResultsCountValues = getText(By.id(ResultsCount));
            int PreResultsCount = Integer.parseInt(PreResultsCountValues.replace(",", ""));
            click(AssetCreateAssetHierarchyButton);
            waitElementInvisible(loading_cursor);
            softAssert.assertFalse(getDriver().findElement(AssetHierarchySubmit).isEnabled(), "Submit button is not disabled");
            type(AssetHierarchyChildAssetID, broilerAssetID);
            enterKey(AssetHierarchyChildAssetID);
            click(AssetHierarchyChildAssetdeployment);  //child deployment date
            enterKey(AssetHierarchyChildAssetdeployment);
            softAssert.assertFalse(getDriver().findElement(AssetHierarchySubmit).isEnabled(), "Submit button is not disabled");
            type(AssetHierarchyParentAssetID, breederAssetID);
            enterKey(AssetHierarchyParentAssetID);
            click(AssetHierarchyParentAssetdeployment);
            enterKey(AssetHierarchyParentAssetdeployment);
            softAssert.assertFalse(getDriver().findElement(AssetHierarchySubmit).isEnabled(), "Submit button is not disabled");
            type(AssetHierarchyNumOfanimals, "20");
            softAssert.assertTrue(getDriver().findElement(AssetHierarchySubmit).isEnabled(), "Submit button is not enabled");
            click(AssetHierarchyAddHierarchyButton);
            softAssert.assertEquals(getDriver().findElements(AssetHierarchyChildAssetdeployment).size(), 2, "deployment hierarchy not added");
            getDriver().findElements(AssetHierarchyChildAssetdeployment).get(1).click();
            getDriver().findElements(AssetHierarchyChildAssetdeployment).get(1).sendKeys(Keys.ENTER);
            getDriver().findElements(AssetHierarchyParentAssetID).get(1).click();
            getDriver().findElements(AssetHierarchyParentAssetID).get(1).sendKeys(Keys.ENTER);
            getDriver().findElements(AssetHierarchyParentAssetdeployment).get(1).click();
            getDriver().findElements(AssetHierarchyParentAssetdeployment).get(1).sendKeys(Keys.ENTER);
            getDriver().findElements(AssetHierarchyNumOfanimals).get(1).sendKeys("20");
            getDriver().findElements(AssetHierarchyDeleteHierarchyButton).get(1).click();
            softAssert.assertEquals(getDriver().findElements(AssetHierarchyChildAssetdeployment).size(), 1, "deployment hierarchy not deleted");
            click(AssetHierarchySubmit);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            Thread.sleep(5000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");
            System.out.println("Asset hierarchy created successfully");
            getScreenshot();
            softAssert.assertAll();
            getScreenshot();
            test.pass("Verify create Asset hierarchy functionality test case passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify create Asset hierarchy functionality test case failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify create Asset hierarchy functionality test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 15, description = "IE-12299", groups = "regression")
    public void EditAssetHierarchy() throws IOException {
        try {
            test = extent.createTest("Verify that user is able to Edit Asset hierarchy.");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_assetHierarchyAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);
            waitElementVisible(By.id(ResultsCount));
            String Results = getText(By.id(ResultsCount));
            String PreEditChildAssetID = getText(By.cssSelector("#dc-table-graph_Edit #row-" + (Integer.parseInt(Results) - 1) + " #col-0"));
            String PreEditParentAssetID = getText(By.cssSelector("#dc-table-graph_Edit #row-" + (Integer.parseInt(Results) - 1) + " #col-2"));
            String PreEditParentAssetdeployment = getText(By.cssSelector("#dc-table-graph_Edit #row-" + (Integer.parseInt(Results) - 1) + " #col-3"));
            String PreEditanimalsPlaced = getText(By.cssSelector("#dc-table-graph_Edit #row-" + (Integer.parseInt(Results) - 1) + " #col-4"));
            System.out.println(PreEditParentAssetID);
            System.out.println(PreEditParentAssetdeployment);
            System.out.println(PreEditanimalsPlaced);
            click(By.id("childAssetId_show-filter"));
            waitElementInvisible(loading_cursor);
            type(By.id("condmnation_childAssetId_search-input"), PreEditChildAssetID);
            Thread.sleep(2000);
            click(By.id("condmnation_childAssetId_cust-cb-lst-txt_selectAll"));
            click(By.id("condmnation_childAssetId_apply"));
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(By.id("parentAssetId_show-filter"));
            waitElementInvisible(loading_cursor);
            type(By.id("condmnation_parentAssetId_search-input"), PreEditParentAssetID);
            Thread.sleep(2000);
            click(By.id("condmnation_parentAssetId_cust-cb-lst-txt_selectAll"));
            click(By.id("condmnation_parentAssetId_apply"));
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);

            click(By.id("edit-Asset-hierarchy-1"));
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
//            type(AssetHierarchyParentAssetID, "A");
//            getDriver().findElement(AssetHierarchyParentAssetID).sendKeys(Keys.ARROW_DOWN);
//            enterKey(AssetHierarchyParentAssetID);
//            click(AssetHierarchyParentAssetdeployment);
//            enterKey(AssetHierarchyParentAssetdeployment);
            clear(AssetHierarchyNumOfanimals);
            type(AssetHierarchyNumOfanimals, "20");
            click(AssetHierarchySubmit);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            Thread.sleep(5000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");
            System.out.println("Asset Hierarchy Edited successfully");
            click(By.id("reset-all-filters"));
            waitElementInvisible(loading_cursor);
            softAssert.assertAll();
            getScreenshot();
            test.pass("Verify Edit Asset hierarchy functionality test case passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify Edit Asset hierarchy functionality test case failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify Edit Asset hierarchy functionality test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(enabled = true, priority = 1, groups = {"regression"})
    public void CreateAssetWithHatcheryValidationsChecks() throws IOException {
        try {
            test = extent.createTest("AN-FM-01: Verify user can create a Asset");
            SoftAssert softAssert = new SoftAssert();
            for (int i = 0; i < 3; i++) {
                type(By.tagName("html"), Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            }
            String PreResultsCountValues = getText(By.id(ResultsCount));
            int PreResultsCount = Integer.parseInt(PreResultsCountValues.replace(",", ""));
            Thread.sleep(3000);
            click(AssetCreateButton);
            Thread.sleep(3000);
            click(addNewAssetButton);
            click(newAssetCollapseIcon);
            click(AssetDeleteIcon);
            type(AssetIntegratorAssetID, AssetIntegratorID);
            if (size(AssetIntegratorAssetAddNew) != 0) {
                click(AssetIntegratorAssetAddNew);
            } else {
                enterKey(AssetIntegratorAssetID);
            }
            click(AssetanimalSizeInput);
            type(AssetanimalSizeInput, AssetManagementModel.AssetanimalSize);
            enterKey(AssetanimalSizeInput);
            Thread.sleep(1000);

            click(AssetProductionTypeList);
            List<WebElement> productionTypeList = getDriver().findElements(AssetProductionTypeListItems);
            productionTypeList.get(1).click();

            scroll(AssetFarmDropdownExpand);
            click(AssetFarmDropdownExpand);
            if (Queries.getHatcheryAssignedToUserHavingSamplingPlan() != null) {
                type(AssetFarmDropdownSearch, getHatcheryAssignedToUserHavingSamplingPlan());
                waitElementClickable(By.cssSelector("label b"));
                Thread.sleep(2000);
                //IE-12821
                softAssert.assertEquals(size(By.cssSelector("label b")), 1, "Hatchery location not able to select");
                scroll(By.cssSelector("label b"));
                Thread.sleep(1000);
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
                Thread.sleep(2000);
                click(selectMonitoringPlan);
                //IE-13188
                softAssert.assertEquals(size(By.cssSelector("#collectionPlan label")), getCountOfHatcherySamplingPlan() + 1, "All complex assigned to user did not displayed");
                if (size(noValues) == 0) {
                    click(By.xpath("(//*[@id='collectionPlan']//label)[2]"));
                    Thread.sleep(1000);
                } else {
                    System.out.println("No plan is associated.");
                }
                click(monitoringPlanSaveButton);
                Thread.sleep(1000);
            } else if (Queries.getHatcheryAssignedToUserHavingSamplingPlan() == null & Queries.getHatcheryAssignedToUser() != null) {
                scroll(AssetFarmDropdownExpand);
                click(AssetFarmDropdownExpand);
                type(AssetFarmDropdownSearch, getHatcheryAssignedToUser());
                waitElementClickable(By.cssSelector("label b"));
                Thread.sleep(2000);
                //IE-12821
                softAssert.assertEquals(size(By.cssSelector("label b")), 1, "Hatchery location not able to select");
                click(By.cssSelector("label b"));
            }
            scroll(AssetdeploymentDateCalendar);
            click(AssetdeploymentDateCalendar);
            List<WebElement> list = getDriver().findElements(By.cssSelector(".dp-current-day"));
            scroll(By.xpath("//label[contains(text(), 'Asset Information')]"));
            Thread.sleep(1000);
            list.get(2).click();
            Thread.sleep(1000);

            //IE-12822
            softAssert.assertEquals(size(AssetNumberOfEggsPlaced), 1, "Number of Eggs placed field is not displayed on selecting hatchery");
            type(AssetNumberOfEggsPlaced, "10");
            softAssert.assertEquals(getAttribute(AssetNumberOfEggsPlaced), "10", "Not able to enter in Number of Eggs placed field");

            //IE-13112
            softAssert.assertEquals(size(AssetHousePlacedDropdownExpand), 0, "Number of House Placed field is shown for hatchery as well ");

            //IE-9594
            softAssert.assertEquals(size(By.xpath("//*[text()='Estimated Processing Date']")), 1, "Estimated Processing Date is not displayed");

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

            List<WebElement> columns = getDriver().findElements(By.cssSelector("#" + AssetdeploymentTable + " th"));
            for (int i = 0; i < columns.size(); i++) {
                int j = i + 1;
                //  System.out.println("--> "+columns.get(i).getText());
                if (columns.get(i).getText().equals("Integrator Asset ID")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), AssetIntegratorID, "Integrator Asset ID not displayed");
                }

                if (columns.get(i).getText().equals("animal Size")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), AssetanimalSize, "animal Size not displayed");
                }

                if (columns.get(i).getText().equals("animal Sex")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), AssetanimalSex, "animal Sex not displayed");
                }

                if (columns.get(i).getText().equals("Marketing Program")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), "Conventional", "Marketing Program not displayed");
                }

                if (columns.get(i).getText().equals("Target Weight (Lbs)")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), "10", "Marketing Program not displayed");
                }

                if (columns.get(i).getText().equals("animal Breed")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), AssetBreedName, "Breed Name not displayed");
                }

                if (columns.get(i).getText().equals("Hatchery Source")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), AssetHatcheryName, "Hatchery Source not displayed");
                }

                if (columns.get(i).getText().equals("Monitoring Plan")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), getSamplingPlanName(), "Monitoring Plan not displayed");
                }

                if (columns.get(i).getText().equals("Age At deployment")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), "2", "Age of deployment not displayed");
                }

                if (columns.get(i).getText().equals("Unit")) {
                    softAssert.assertEquals(getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")), "Days", "Unit not displayed");
                }

                if (columns.get(i).getText().equals("Hatchery Location")) {
                    softAssert.assertTrue(!getText(By.cssSelector("#" + AssetdeploymentTable + " tr:nth-child(1)  td:nth-child(" + j + ")")).isEmpty(), "Hatchery Location not displayed");
                }
            }

      //      AssetManagementPage.openAssetAudit();
      //      softAssert.assertEquals(size(auditGetRowCount), 2, "Audit Log not displaying a row for Asset creation");
      //      softAssert.assertEquals(getText(getauditActionRow1), "Created", "Audit Log not displaying a row for with Action created");
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


    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }
}