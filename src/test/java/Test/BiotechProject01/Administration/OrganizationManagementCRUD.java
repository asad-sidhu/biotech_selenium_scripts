package Test.BiotechProject01.Administration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import LogViewFunctions.RowsPerPage;
import LogViewFunctions.VerifyColumnNames;
import MiscFunctions.*;
import org.aeonbits.owner.ConfigFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import Config.ReadPropertyFile;
import Models.OrganizationManagementModel;
import Models.ReportFilters;
import PageObjects.OrganizationManagementPage;
import PageObjects.UserManagementPage;

import com.aventstack.extentreports.gherkin.model.Scenario;

import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static LogViewFunctions.FieldAccess.FieldAccessFunctionality;
import static LogViewFunctions.FieldAccess.KeyColumnsCheck;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.DateUtil.*;
import static Models.OrganizationManagementModel.*;
import static PageObjects.OrganizationManagementPage.*;
import static PageObjects.CompanyProductsPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static LogViewFunctions.RowsPerPage.*;
import static MiscFunctions.Methods.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Constants.*;
import static LogViewFunctions.LogCSVExport.*;

public class OrganizationManagementCRUD extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
       spark = new ExtentSparkReporter("target/Reports/Administration_Organization_Management_CRUD" + DateUtil.date + ".html");
       spark.config().setReportName("Organization Management Test Report");
        initReport("Administration_Organization_Management_CRUD");

        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_organizationManagement, "Organization Management", orgTitle);
    }

        @Test(enabled = true, priority = 7, groups = {"regression"})
        public void OpenClosePopup() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-02: Verify user can open and close Create New User Popup");

                getDriver().get(url_organizationManagement);
                waitElementInvisible(loading_cursor);
                Thread.sleep(3000);

                getScreenshot();
                click(orgCreateButton);
                Thread.sleep(3000);
                Assert.assertEquals(getDriver().findElement(By.cssSelector(".pop-head")).getText(), "Create Organization");
                click(popupCloseButton);
                waitElementInvisible(loading_cursor);
                Assert.assertEquals(getDriver().findElements(popupNextButton).size(), 0);
                test.pass("Organization popup window closed successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Organization popup window did not open or closed successfully");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Organization popup window did not open or closed successfully");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


        @Test(enabled = true, priority = 8, groups = {"regression"})
        public void ResetButton() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-03: Verify user can send reset fields");

                click(orgCreateButton);
                waitElementInvisible(loading_cursor);
                type(orgNameInput, "Test Org");
                click(popupResetButton);
                waitElementInvisible(loading_cursor);
                Assert.assertEquals(getDriver().findElement(orgNameInput).getAttribute("value"), "");
                test.pass("Fields reset successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Fields failed to reset");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Fields failed to reset");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


        @Test(enabled = true, priority = 9, groups = {"regression"})
        public void testInvalidEmail() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-04: Verify user cannot create Organization with invalid email", "This test case will verify that user cannot create new organization with invalid email");

                getDriver().findElement(orgEmailInput).sendKeys("invalid@email");
                getScreenshot();
                getDriver().findElement(popupNextButton).click();

                Assert.assertEquals(getDriver().findElements(orgEmailError).size(), 1);
                test.pass("User cannot create organization with invalid email; displays validation message 'Invalid email'");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("User was able to create organization with invalid email");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("User was able to create organization with invalid email");
                saveResult(ITestResult.FAILURE, ex);
            }
        }

        @Test(enabled = true, priority = 10, groups = {"regression"})
        public void testCharacterLimit() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-05: Verify user cannot create Organization with name having character more than 100");

                type(orgNameInput, "jdflkjdslkfjsdlkfjlkdsjflksdjflksdjfkdsjfdsjflksjdflksdjflkdsjlkfjdslkfjdslkfjdslkfjdslkfjdslkfjdslkfjslkfjslkfjsklsdjfksjflkdsjfksjf");
                Thread.sleep(1000);
                getScreenshot();
                clear(orgNameInput);
                Assert.assertEquals(size(By.cssSelector("#nameId.has-error")), 1);
                test.pass("Character limit check verified successfully");
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("User was able to create organization with invalid email");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("User was able to create organization with invalid email");
                saveResult(ITestResult.FAILURE, ex);
            }
            click(popupCloseButton);
            Thread.sleep(1000);
        }


        @Test(description = "Test Case: Create New Organization", enabled = true, priority = 11, groups = {"smoke", "regression"})
        public void CreateOrganization() throws InterruptedException, IOException {
            OrganizationManagementPage.CreateOrganizationFunction(OrganizationName);
        }


        @Test(description = "Test Case: Update New Organization ", enabled = true, priority = 12, groups = {"regression"})
        public void UpdateOrganization() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-06: Verify user can update Created Organization");

                getDriver().get(url_organizationManagement);
                waitElementInvisible(loading_cursor);
                Thread.sleep(3000);

                searchCreatedOrg(OrganizationName, organizationEditButton);
                Thread.sleep(2000);
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertNotEquals(getDriver().findElement(By.id("nmeOrgID")).getAttribute("value"), "", "Organization ID should not be empty in edit organization popup");
                getScreenshot();
                Thread.sleep(1000);
                clear(orgEmailInput);
                type(orgEmailInput, "testorg@anc.com");
                Thread.sleep(500);
                click(popupNextButton);
                Thread.sleep(500);
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(1));
                softAssert.assertAll();
                test.pass("Organization updated successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Failed to update the Organization");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Failed to update the Organization");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


        @Test(description = "Test Case: Verify Updated Organization ", enabled = true, priority = 13, groups = {"regression"})
        public void VerifyUpdateOrganization() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-07: Verify Organization remained updated after updating it");

                if (size(alertClose) != 0) {
                    click(alertClose);
                }

                //openEditOrgPopup(OrganizationName);
                searchCreatedOrg(OrganizationName, organizationEditButton);
                Assert.assertEquals(getDriver().findElement(orgEmailInput).getAttribute("value"), "testorg@anc.com");
                click(popupCloseButton);
                test.pass("Organization updation verified successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Organization updation failed");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Organization was not updated successfully; changes did not remained saved");
                saveResult(ITestResult.FAILURE, ex);
            }
        }

        @Test(priority = 14, enabled = true, groups = {"regression"})
        public void VerifyAudit() throws IOException, InterruptedException {
            test = extent.createTest("AN-OM-08: Verify data in audit log for created org");

            getDriver().get(url_organizationManagement);
            waitElementInvisible(loading_cursor);
            SoftAssert softAssert = new SoftAssert();
            try {
                softAssert.assertTrue(getDriver().findElement(auditIcon).isDisplayed(), "Audit icon is displayed");
                click(By.id(ResetFilters));
                waitElementInvisible(loading_cursor);
                click(organizationColumnFilter);
                waitElementInvisible(loading_cursor);
                type(organizationColumnSearchField, OrganizationName);
                Thread.sleep(1000);
                waitElementVisible(By.id("orgnName_cust-cb-lst-txt_" + OrganizationName));
                click(By.id("orgnName_cust-cb-lst-txt_" + OrganizationName));
                click(organizationColumnApplyButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(2000);
                click(auditIcon);
                waitElementInvisible(loading_cursor);
                Thread.sleep(2000);
                By[] columnNames = {auditChangedDateCol, auditOrganizationCol, auditActionCol, auditChangedByCol, auditPhoneCol, auditCityCol, auditEmailCol, auditMaxUsersCol, auditActiveCol, auditReportRolesCol, auditSystemRolesCol, auditRoleCategoryCol, auditAllowedDomainsCol};

                for (By columnName : columnNames) {
                    WebElement column = getDriver().findElement(columnName);
                    if (column.isDisplayed()) {
                        System.out.println("Column: " + columnName + " is present in the popup.");
                        Assert.assertTrue(column.isDisplayed(), "Column: " + columnName + " is present in the popup.");
                    } else {
                        System.out.println("Column: " + columnName + " is NOT present in the popup.");
                    }
                }

                // Verify that the new row is added in audit popup
                auditUpdatedRow = size(auditGetRowCount);
                System.out.println("Updated number of rows: " + auditUpdatedRow);

                softAssert.assertEquals(auditRowCount, auditUpdatedRow, "A new row is not added in audit");
                softAssert.assertAll();
                test.pass("Audit verified successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Audit test failed");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("The columns were not present on the audit popup, hence test case failed");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


        @Test(description = "Test Case: Organization Site Check", enabled = true, priority = 15, groups = {"regression"})
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

                searchCreatedOrg(OrganizationName, organizationEditSitesButton);
                click(orgNameSiteHierarchy);
                waitElementInvisible(loading_cursor);
                clear((orgExtSiteField));
                type(orgExtSiteField, date0 + "0" + dateYYYYMMDD);
                String externalSiteId = getDriver().findElement(orgExtSiteField).getAttribute("value");
                System.out.println(externalSiteId);
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Site details updated.");
                Thread.sleep(1000);
                click(orgNameSiteHierarchy);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                softAssert.assertEquals(externalSiteId, date0 + "0" + dateYYYYMMDD, "External site id value did not get saved1");

                steps.createNode("3. Click on + icon to create new site and verify Site Type is Region");
                waitElementClickable(orgAddSite1);
                click(orgAddSite1);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                click(orgSiteTypeInput);
                String regionType = getDriver().findElement(orgSiteTypeDropDownValue).getText();
                softAssert.assertEquals(regionType, "Region");
                getDriver().findElement(orgSiteNameInput).sendKeys("Test Region");
                type(orgExtSiteField, externalSiteId);
                Thread.sleep(250);
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                softAssert.assertEquals(getText(alertMessage), "External SiteId already exists in this organization.", "1");
                clear(orgExtSiteField);
                Thread.sleep(1000);
                type(orgExtSiteField, date0 + "1" + dateYYYYMMDD);
                Thread.sleep(1000);
                externalSiteId = getDriver().findElement(orgExtSiteField).getAttribute("value");
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                steps.createNode("4. Verify Region Site can be saved");
                softAssert.assertEquals(getText(alertMessage), "New site created.", "1a");
                click(By.xpath("(" + orgSiteHierarchy + ")" + "[2]"));
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                softAssert.assertEquals(externalSiteId, date0 + "1" + dateYYYYMMDD, "External site id value did not get saved2");

                // Verify that if the site is deleted, the system allows the use of the previously deleted external site id
                click(orgDelete);
                waitElementVisible(popupYesButton);
                click(popupYesButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                click(orgAddSite1);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                click(orgSiteTypeInput);
                getDriver().findElement(orgSiteNameInput).sendKeys("Test Region");
                type(orgExtSiteField, date0 + "1" + dateYYYYMMDD);
                Thread.sleep(250);
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.", "2b");

                steps.createNode("5. Click on + icon to create new site and verify Site Type is Sub Region");
                click(orgAddSite2);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                click(orgSiteTypeInput);
                String subregionType = getDriver().findElement(orgSiteTypeDropDownValue).getText();
                softAssert.assertEquals(subregionType, "Sub Region");
                getDriver().findElement(orgSiteNameInput).sendKeys("Test Sub Region");
                type(orgExtSiteField, externalSiteId);
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                softAssert.assertEquals(getText(alertMessage), "External SiteId already exists in this organization.", "3a");
                clear(orgExtSiteField);
                type(orgExtSiteField, date0 + "2" + dateYYYYMMDD);
                externalSiteId = getDriver().findElement(orgExtSiteField).getAttribute("value");
                Thread.sleep(250);
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                steps.createNode("6. Verify Sub Region Site can be saved");
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.", "3");
                click(By.xpath("(" + orgSiteHierarchy + ")" + "[3]"));
                waitElementInvisible(loading_cursor);
                Thread.sleep(1500);
                softAssert.assertEquals(externalSiteId, date0 + "2" + dateYYYYMMDD, "External site id value did not get saved4");

                steps.createNode("7. Click on + icon to create new site and verify Site Type as Complex, Processing PLant, Testing Lab");
                getDriver().findElement(orgAddSite3).click();
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                getDriver().findElement(orgSiteTypeInputChild).click();
                Thread.sleep(1000);

                String ele1 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
                String ele2 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(2)")).getText();
                String ele3 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(3)")).getText();

                softAssert.assertEquals(ele1, "Complex");
                softAssert.assertEquals(ele2, "Processing Plant");
                softAssert.assertEquals(ele3, "Testing Lab");

                click(By.cssSelector("div .ng-option:nth-child(1)"));
                type(orgSiteNameInput, "Test Complex Site");
                type(orgExtSiteField, externalSiteId);
                type(By.id("num-costPerPointOfAfcrId"), "1");
                type(By.id("num-costPerPointOfFcrId"), "1");
                type(By.id("num-targetAfcrId"), "1.2");
                type(By.id("num-targetFcrId"), "1.2");
                Thread.sleep(250);
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "External SiteId already exists in this organization.", "4a");
                //waitElementInvisible("message");
                clear(orgExtSiteField);
                type(orgExtSiteField, date0 + "3" + dateYYYYMMDD);
                externalSiteId = getDriver().findElement(orgExtSiteField).getAttribute("value");
                Thread.sleep(250);
                WebElement saveButton = getDriver().findElement(popupSaveButton);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", saveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                steps.createNode("8. Verify Complex Site can be saved");
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.", "4");
                click(By.xpath("(" + orgSiteHierarchy + ")" + "[4]"));
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                softAssert.assertEquals(externalSiteId, date0 + "3" + dateYYYYMMDD, "External site id value did not get saved5");

                steps.createNode("9. Click on + icon to create new site and verify Site Type as Farm");
                waitElementVisible(orgAddSite4);
                getDriver().findElement(orgAddSite4).click();
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                click(orgSiteFarmDropdown);
                softAssert.assertEquals(size(orgSiteFarmDropdownFarmSelect), 1, "Farm not found in Site Type dropdown");
                softAssert.assertEquals(size(orgSiteFarmDropdownHatcherySelect), 1, "Hatchery not found in Site Type dropdown");
                click(orgSiteFarmDropdownFarmSelect);

                getDriver().findElement(orgSiteNameInput).sendKeys("Test Farm");
                String[] expectedFarmTypes = {"Broiler", "Breeder", "Pullet"};
                click(farmFarmTypeDropdown);
                List<WebElement> actualFarmTypes = getDriver().findElements(farmFarmTypeDropdownItems);
                for (int i = 0; i < actualFarmTypes.size(); i++) {
                    softAssert.assertEquals(expectedFarmTypes[i],actualFarmTypes.get(i).getText(), "Farm Types don't Match");
                }
                enterKey(farmFarmTypeDropdown);
                type(orgExtSiteField, externalSiteId);
                Thread.sleep(250);
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                softAssert.assertEquals(getText(alertMessage), "External SiteId already exists in this organization.", "5a");
                clear(orgExtSiteField);
                type(orgExtSiteField, date0 + "4" + dateYYYYMMDD);
                externalSiteId = getDriver().findElement(orgExtSiteField).getAttribute("value");
                Thread.sleep(250);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", saveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                steps.createNode("10. Verify Farm Site can be saved");
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.", "5");

                steps.createNode("11. Click on + icon to create new site and verify Site Type as House");
                waitElementVisible(orgAddSite5);
                getDriver().findElement(orgAddSite5).click();
                Thread.sleep(2000);
                getDriver().findElement(orgSiteTypeInputChild).click();
                String HouseType = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
                softAssert.assertEquals(HouseType, "House");
                getDriver().findElement(orgSiteNameInput).sendKeys("Test House");
                type(orgExtSiteField, externalSiteId);
                Thread.sleep(250);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", saveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "External SiteId already exists in this organization.", "6a");
                click(houseFloorTypeDropdown);
                clear(houseFloorTypefield);
                type(houseFloorTypefield, "Floor Type " +date0 + dateYYYYMMDD);
                enterKey(houseFloorTypefield);
                click(houseFloorTypeClearField);
                click(houseLitterTypeDropdown);
                clear(houseLitterTypefield);
                type(houseLitterTypefield, "Litter Type " +date0 + dateYYYYMMDD);
                enterKey(houseLitterTypefield);
                click(houseLitterTypeClearField);
                clear(orgExtSiteField);
                type(orgExtSiteField, date0 + "5" + dateYYYYMMDD);
                externalSiteId = getDriver().findElement(orgExtSiteField).getAttribute("value");
                Thread.sleep(1000);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", saveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                steps.createNode("12. Verify House Site can be saved");
                softAssert.assertEquals(getText(alertMessage), "New site created.", "6");

                Thread.sleep(2000);
                getDriver().findElement(orgAddSite3).click();
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                getDriver().findElement(orgSiteTypeInputChild).click();
                getDriver().findElement(By.cssSelector("div .ng-option:nth-child(2)")).click();
                getDriver().findElement(orgSiteNameInput).sendKeys("Test Processing Plant Site");
                type(orgExtSiteField, externalSiteId);
                Thread.sleep(250);
                getDriver().findElement(popupSaveButton).click();
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                Thread.sleep(1500);
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "External SiteId already exists in this organization.", "7a");
                clear(orgExtSiteField);
                type(orgExtSiteField, date0 + "6" + dateYYYYMMDD);
                externalSiteId = getDriver().findElement(orgExtSiteField).getAttribute("value");
                Thread.sleep(250);
                getDriver().findElement(popupSaveButton).click();
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                steps.createNode("13. Create Processing Plant Site");
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.", "7");

                steps.createNode("14. Click on + icon to create new site and verify Site Type as Rehang, animal Wash, animal Rinse, Chiller, Wing Dip");
                waitElementVisible(orgAddSite6);
                getDriver().findElement(orgAddSite6).click();
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                getDriver().findElement(orgSiteTypeInputChild).click();
                Thread.sleep(1000);

                String elem1 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
                String elem2 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(2)")).getText();
                String elem3 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(3)")).getText();
                String elem4 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(4)")).getText();
                String elem5 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(5)")).getText();

                softAssert.assertEquals(elem1, "Rehang");
                softAssert.assertEquals(elem2, "animal Wash");
                softAssert.assertEquals(elem3, "animal Rinse");
                softAssert.assertEquals(elem4, "Chiller");
                softAssert.assertEquals(elem5, "Wing Dip");

                getDriver().findElement(By.cssSelector("div .ng-option:nth-child(4)")).click();
                getDriver().findElement(orgSiteNameInput).sendKeys("Test Chiller Site");
                type(orgExtSiteField, externalSiteId);
                Thread.sleep(250);
                getDriver().findElement(popupSaveButton).click();
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "External SiteId already exists in this organization.", "8a");
                clear(orgExtSiteField);
                type(orgExtSiteField, date0 + "7" + dateYYYYMMDD);
                externalSiteId = getDriver().findElement(orgExtSiteField).getAttribute("value");
                Thread.sleep(250);
                getDriver().findElement(popupSaveButton).click();
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);

                steps.createNode("15. Verify Site can be saved");
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.", "8");

                getDriver().findElement(orgAddSite3).click();
                Thread.sleep(2000);
                getDriver().findElement(orgSiteTypeInputChild).click();
                getDriver().findElement(By.cssSelector("div .ng-option:nth-child(3)")).click();
                getDriver().findElement(orgSiteNameInput).sendKeys("Test Testing Lab Site");
                type(orgExtSiteField, externalSiteId);
                Thread.sleep(250);
                getDriver().findElement(popupSaveButton).click();
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "External SiteId already exists in this organization.", "9a");
                clear(orgExtSiteField);
                type(orgExtSiteField, date0 + "8" + dateYYYYMMDD);
                externalSiteId = getDriver().findElement(orgExtSiteField).getAttribute("value");
                Thread.sleep(250);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", saveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                steps.createNode("16. Create Testing Lab Site");
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.", "9");
                Thread.sleep(2000);

                steps.createNode("17. Click on + icon to create new site and verify Site Type as Lab-Sub Division");
                waitElementVisible(orgAddSite7);
                getDriver().findElement(orgAddSite7).click();
                Thread.sleep(2000);
                waitElementVisible(orgSiteTypeInputChild);
                getDriver().findElement(orgSiteTypeInputChild).click();
                String subDivisionType = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
                softAssert.assertEquals(subDivisionType, "Lab-Sub Division");
                getDriver().findElement(orgSiteNameInput).sendKeys("Test Sub Division Site");
                type(orgExtSiteField, externalSiteId);
                Thread.sleep(250);
                getDriver().findElement(popupSaveButton).click();
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                Thread.sleep(1500);
                softAssert.assertEquals(getText(alertMessage), "External SiteId already exists in this organization.", "10a");
                clear(orgExtSiteField);
                type(orgExtSiteField, date0 + "9" + dateYYYYMMDD);
                externalSiteId = getDriver().findElement(orgExtSiteField).getAttribute("value");
                softAssert.assertEquals(externalSiteId, date0 + "9" + dateYYYYMMDD, "External site Id did not got saved");
                Thread.sleep(250);
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                steps.createNode("18. Verify Lab Sub Division Site can be saved");
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.", "10");
                getScreenshot();
                click(orgDeleteSite1);
                Thread.sleep(1000);
                click(confirmationYes);
                waitElementInvisible(loading_cursor);
                Thread.sleep(2000);
                softAssert.assertEquals(getText(alertMessage), "Site details deleted successfully.");
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

        @Test(description = "Test Case: Organization Site Mandatory Check", enabled = true, priority = 16, groups = {"regression"})
        public void OrganizationSiteMandatoryCheck() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-17: Verify Organization Site mandatory checks");

                getDriver().get(url_organizationManagement);
                waitElementInvisible(loading_cursor);
                Thread.sleep(3000);

                searchCreatedOrg(OrganizationName, organizationEditSitesButton);

                if (size(orgDeleteSite1) != 0) {
                    click(orgDeleteSite1);
                    Thread.sleep(1000);
                    click(confirmationYes);
                    waitElementInvisible(loading_cursor);
                }

                click(orgAddSite1);
                waitElementInvisible(loading_cursor);
                click(popupSaveButton);
                Thread.sleep(500);

                Assert.assertEquals(getDriver().findElements(orgSiteNameError).size(), 1);
                test.pass("Site Mandatory fields check verified successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Site Mandatory fields check verification failed");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Site Mandatory fields check verification failed");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


        @Test(description = "Exceptional Flow: Site Reset fields", enabled = true, priority = 17, groups = {"regression"})
        public void SiteResetButton() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-18: Verify Organization Site Reset fields check");

                type(orgSiteNameInput, "Lab");
                getScreenshot();
                click(popupResetButton);
                Thread.sleep(1000);

                String orgSiteNameReset = getDriver().findElement(orgSiteNameInput).getAttribute("value");
                Assert.assertEquals(orgSiteNameReset, "");
                test.pass("Fields reset successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Fields reset failed");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Fields reset failed");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


        @Test(description = "Test Case: Create Organization Site", enabled = true, priority = 18, groups = {"smoke", "regression"})
        public void CreateOrganizationSite() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-19: Verify Organization Site can be created");
                click(orgSiteTypeInputChild);
                Thread.sleep(500);
                click(orgSiteTypeDropDownValue);
                type(orgSiteNameInput, "Region");
                Thread.sleep(500);
                SoftAssert softAssert = new SoftAssert();
                WebElement stAddress = getDriver().findElement(orgSiteAddressInput);
                clear(orgSiteAddressInput);
                type(orgSiteAddressInput, "Lahore Pakistan");
                Thread.sleep(2000);
                stAddress.sendKeys(Keys.DOWN);
                stAddress.sendKeys(Keys.ENTER);
                Thread.sleep(3000);
                softAssert.assertEquals(getDriver().findElement(By.cssSelector(".confirmation-message label")).getText(), "The specified address is outside USA, Canada, Egypt. Please enter a valid address.");
                getDriver().findElement(popupOKButton).click();
                Thread.sleep(1000);

                getDriver().findElement(orgSiteLongitudeInput).sendKeys("-65.32");
                softAssert.assertEquals(getDriver().findElement(orgSiteLongitudeInput).getAttribute("value"), "-65.32", "Longitude did not filled");
                getDriver().findElement(orgSiteLatitudeInput).sendKeys("-70.32");
                softAssert.assertEquals(getDriver().findElement(orgSiteLatitudeInput).getAttribute("value"), "-70.32", "Latitude did not filled");

                stAddress.clear();
                stAddress.sendKeys("new york");
                Thread.sleep(1000);
                stAddress.sendKeys(Keys.DOWN);
                stAddress.sendKeys(Keys.ENTER);
                waitElementInvisible(loading_cursor);
                Thread.sleep(6000);
                getDriver().findElement(orgSiteNameInput).click();
                Thread.sleep(2000);

                click(orgSiteCountryDropdownExpand);
                Thread.sleep(1000);
                softAssert.assertEquals(size(orgSiteFarmDropdownCanadaSelect), 1, "Canada not listed in country dropdown");
                softAssert.assertEquals(size(orgSiteFarmDropdownEgyptSelect), 1, "Egypt not listed in country dropdown");
                softAssert.assertEquals(size(orgSiteFarmDropdownUnitedStatesSelect), 1, "United States not listed in country dropdown");

                System.out.println("size "+size(orgSiteCountrySelect));
              //  click(orgSiteCountrySelect);
              //  softAssert.assertEquals(getAttribute(orgSiteCountryInput), "United States");
                softAssert.assertEquals(getText(orgSiteStateInput), "New York");
                softAssert.assertEquals(getText(orgSiteCityInput), "New York");
                softAssert.assertEquals(getAttribute(orgSiteLatitudeInput), "40.7127753", "Latitude did not autofilled");
                softAssert.assertEquals(getAttribute(orgSiteLongitudeInput), "-74.0059728", "Longitude did not autofilled");

                getScreenshot();
                getDriver().findElement(popupSaveButton).click();
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                String actual = getDriver().findElement(alertMessage).getText();
                String expected = lstOrgAlertMessages.get(2);
                getDriver().findElement(alertMessageClose).click();

                softAssert.assertEquals(actual, expected);
                softAssert.assertAll();
                test.pass("New Organization site created successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Organization site failed to create");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Organization site failed to create");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


        @Test(description = "Test Case: Update Organization Sites ", enabled = true, priority = 19, groups = {"regression"})
        public void UpdateOrganizationSites() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-20: Verify Organization Site can be updated");

                SoftAssert softAssert = new SoftAssert();
                Thread.sleep(2000);
                getDriver().findElement(orgSite1Click).click();
                waitElementInvisible(loading_cursor);
                Thread.sleep(4000);
                getScreenshot();

                //#countryId .ng-placeholder
                //#countryId .ng-value-label

                getDriver().findElement(orgSiteNameInput).clear();
                getDriver().findElement(orgSiteNameInput).sendKeys("Region Updated");
                softAssert.assertEquals(getText(orgSiteCountryPlaceholder), "Country");
             //   softAssert.assertEquals(getAttribute(orgSiteCountryInput), "United States");
                softAssert.assertEquals(getText(orgSiteStatePlaceholder), "State");
                softAssert.assertEquals(getText(orgSiteStateInput), "New York");
                softAssert.assertEquals(getText(orgSiteCityPlaceholder), "City");
                softAssert.assertEquals(getText(orgSiteCityInput), "New York");
                softAssert.assertTrue(getDriver().findElement(orgSiteLatitudeInput).getAttribute("value").startsWith("40."), "Latitude did not autofilled");
                softAssert.assertTrue(getDriver().findElement(orgSiteLongitudeInput).getAttribute("value").startsWith("-74."), "Longitude did not autofilled");
                getDriver().findElement(popupSaveButton).click();
                waitElementInvisible(loading_cursor);
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(3));
                softAssert.assertAll();
                test.pass("Organization site updated successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Organization site failed to update");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Organization site failed to update");
                saveResult(ITestResult.FAILURE, ex);
            }
            getDriver().findElement(popupCloseButton).click();
            Thread.sleep(1000);
        }


        @Test(description = "Test Case: Verify Update Organization Sites ", enabled = true, priority = 20, groups = {"regression"})
        public void VerifyUpdateOrganizationSites() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-21: Verify Organization Site remains updated on reopening");

                //openEditOrgSitesPopup(OrganizationName);
                searchCreatedOrg(OrganizationName, organizationEditSitesButton);
                Thread.sleep(3000);
                getDriver().findElement(orgSite1Click).click();
                waitElementInvisible(loading_cursor);
                Thread.sleep(8000);
                Assert.assertEquals(getDriver().findElement(orgSiteNameInput).getAttribute("value"), "Region Updated");
                test.pass("New Organization site updation verified successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Organization site updation verification failed");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Organization site updation verification failed");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


        @Test(description = "Test Case: Delete Organization Sites ", enabled = true, priority = 21, groups = {"smoke", "regression"})
        public void DeleteOrganizationSites() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-22: Verify Organization Site can be deleted");

                getDriver().findElement(orgSite1Delete).click();
                Thread.sleep(750);
                getDriver().findElement(confirmationYes).click();
                waitElementVisible(alertMessage);
                Thread.sleep(1000);

                Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), lstOrgAlertMessages.get(4));
                test.pass("New Organization site deleted successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Organization site failed to delete");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Organization site failed to delete");
                saveResult(ITestResult.FAILURE, ex);
            }
            getDriver().findElement(popupCloseButton).click();
        }


    @Test(enabled = false, priority = 22, groups = {"regression"})
    public void BulkUploadInvalidFile() throws IOException {
        try {
            test = extent.createTest("AN-OM-23: Verify user is not able to upload empty bulk site file");

            getDriver().get(url_organizationManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            //openEditOrgSitesPopup(OrganizationName);
            searchCreatedOrg(OrganizationName, organizationEditSitesButton);
            waitElementInvisible(loading_cursor);

            click(addFileToUploadButton);
            waitElementInvisible(loading_cursor);
            List<WebElement> elements = getDriver().findElements(fileInputButton);
            WebElement secondElement = elements.get(0); // Index 1 corresponds to the second element
            secondElement.sendKeys(FrameworkConstants.OrganizationBulkSiteUploadInvalidFile);

            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);
            Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Invalid file provided.");
            test.pass("User was not able to upload an invalid file successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User was able to upload an invalid file");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User was able to upload an invalid file");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(enabled = true, priority = 25, groups = {"regression"})
    public void ComplexSitesBulkUpload() throws IOException {
        try {
            test = extent.createTest("Verify user is able to upload house sites file");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_organizationManagement);
            waitElementInvisible(loading_cursor);
            searchCreatedOrg(OrganizationName, organizationEditSitesButton);
            waitElementInvisible(loading_cursor);

            if (size(orgDeleteSite1) != 0) {
                click(orgDeleteSite1);
                Thread.sleep(1000);
                click(confirmationYes);
                waitElementInvisible(loading_cursor);
            }

            Thread.sleep(1000);
            click(orgAddSite1);
            Thread.sleep(1000);
            click(orgSiteTypeInput);
            type(orgSiteNameInput, "Test Region");
            click(popupSaveButton);

            click(orgAddSite2);
            Thread.sleep(1000);
            getDriver().findElement(orgSiteNameInput).sendKeys("Test Sub Region");
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            List<WebElement> elements = getDriver().findElements(siteslist);
            elements.get(2).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);
            String SiteID = getAttribute(orgSiteIDField);
            System.out.println("ParentSiteID: " + SiteID);

            Row row;
            Cell cell;
            FileInputStream fsIP = new FileInputStream(new File(FrameworkConstants.OrganizationBulkSiteUploadComplexFile));
            @SuppressWarnings("resource")
            XSSFWorkbook wb = new XSSFWorkbook(fsIP);
            XSSFSheet worksheet = wb.getSheetAt(0);

            row = worksheet.getRow(1);
            cell = row.createCell(1);
            cell.setCellValue(SiteID);

            FileOutputStream output_file = new FileOutputStream(new File(FrameworkConstants.OrganizationBulkSiteUploadComplexFile));
            wb.write(output_file);
            output_file.close();
            click(addFileToUploadButton);
            waitElementInvisible(loading_cursor);
            List<WebElement> dropdownValues = getDriver().findElements(fileInputButton);
            WebElement secondElement = dropdownValues.get(1);
            System.out.println(">>>" + secondElement.getText());
            secondElement.sendKeys(FrameworkConstants.OrganizationBulkSiteUploadComplexFile);

            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "SiteComplexTemplate.xlsx loaded successfully.");
            Thread.sleep(1000);
            click(fileUploadSubmitButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "SiteComplexTemplate.xlsx saved successfully.");
            Thread.sleep(1000);
            softAssert.assertAll();
            test.pass("User was not able to upload house sites file successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User was able to upload house sites file");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User was able to upload house sites file");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 26, groups = {"regression"})
    public void FarmSitesBulkUpload() throws IOException {
        try {
            test = extent.createTest("Verify user is able to upload farm sites file");
            SoftAssert softAssert = new SoftAssert();
            // getDriver().get(url_organizationManagement);
            //  waitElementInvisible(loading_cursor);
            searchCreatedOrg(OrganizationName, organizationEditSitesButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            List<WebElement> elements = getDriver().findElements(siteslist);
            elements.get(3).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            String SiteID = getAttribute(orgSiteIDField);
            System.out.println("SiteID: " + SiteID);

            Row row;
            Cell cell;
            FileInputStream fsIP = new FileInputStream(new File(FrameworkConstants.OrganizationBulkSiteUploadFarmFile));
            @SuppressWarnings("resource")
            XSSFWorkbook wb = new XSSFWorkbook(fsIP);
            XSSFSheet worksheet = wb.getSheetAt(0);

            row = worksheet.getRow(1);
            cell = row.createCell(1);
            cell.setCellValue(SiteID);

            FileOutputStream output_file = new FileOutputStream(new File(FrameworkConstants.OrganizationBulkSiteUploadFarmFile));
            wb.write(output_file);
            output_file.close();
            click(addFileToUploadButton);
            waitElementInvisible(loading_cursor);
            List<WebElement> dropdownValues = getDriver().findElements(fileInputButton);
            WebElement secondElement = dropdownValues.get(2);
            secondElement.sendKeys(FrameworkConstants.OrganizationBulkSiteUploadFarmFile);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "SiteFarmTemplate.xlsx loaded successfully.");
            Thread.sleep(1000);
            click(fileUploadSubmitButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "SiteFarmTemplate.xlsx saved successfully.");
            Thread.sleep(1000);
            softAssert.assertAll();
            test.pass("User was not able to upload farm sites file successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User was able to upload farm sites file");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User was able to upload farm sites file");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(enabled = true, priority = 27, groups = {"regression"})
    public void HatcherySitesBulkUpload() throws IOException {
        try {
            test = extent.createTest("Verify user is able to upload hatchery sites file");
            SoftAssert softAssert = new SoftAssert();
            getDriver().get(url_organizationManagement);
            waitElementInvisible(loading_cursor);
            searchCreatedOrg(OrganizationName, organizationEditSitesButton);
            waitElementInvisible(loading_cursor);

            List<WebElement> elements = getDriver().findElements(siteslist);
            elements.get(3).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);
            String SiteID = getAttribute(orgSiteIDField);
            System.out.println("SiteID: " + SiteID);

            Row row;
            Cell cell;
            FileInputStream fsIP = new FileInputStream(new File(FrameworkConstants.OrganizationBulkSiteUploadHatcheryFile));
            @SuppressWarnings("resource")
            XSSFWorkbook wb = new XSSFWorkbook(fsIP);
            XSSFSheet worksheet = wb.getSheetAt(0);

            row = worksheet.getRow(1);
            cell = row.createCell(1);
            cell.setCellValue(SiteID);

            FileOutputStream output_file = new FileOutputStream(new File(FrameworkConstants.OrganizationBulkSiteUploadHatcheryFile));
            wb.write(output_file);
            output_file.close();
            click(addFileToUploadButton);
            waitElementInvisible(loading_cursor);
            List<WebElement> dropdownValues = getDriver().findElements(fileInputButton);
            WebElement secondElement = dropdownValues.get(3);
            System.out.println(">>>" + secondElement.getText());
            secondElement.sendKeys(FrameworkConstants.OrganizationBulkSiteUploadHatcheryFile);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "SiteHatcheryTemplate.xlsx loaded successfully.");
            Thread.sleep(1000);
            click(fileUploadSubmitButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "SiteHatcheryTemplate.xlsx saved successfully.");
            Thread.sleep(1000);
            softAssert.assertAll();
            test.pass("User was not able to upload hatchery sites file successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User was able to upload hatchery sites file");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User was able to upload hatchery sites file");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(enabled = true, priority = 28, groups = {"regression"})
    public void HouseSitesBulkUpload() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify user is able to upload house sites file");
            SoftAssert softAssert = new SoftAssert();
//          getDriver().get(url_organizationManagement);
//          waitElementInvisible(loading_cursor);
            searchCreatedOrg(OrganizationName, organizationEditSitesButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            List<WebElement> elements = getDriver().findElements(siteslist);
            elements.get(4).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);
            String SiteID = getAttribute(orgSiteIDField);
            System.out.println("SiteID: " + SiteID);

            Row row;
            Cell cell;
            FileInputStream fsIP = new FileInputStream(new File(FrameworkConstants.OrganizationBulkSiteUploadHouseFile));
            @SuppressWarnings("resource")
            XSSFWorkbook wb = new XSSFWorkbook(fsIP);
            XSSFSheet worksheet = wb.getSheetAt(0);

            row = worksheet.getRow(1);
            cell = row.createCell(1);
            cell.setCellValue(SiteID);

            FileOutputStream output_file = new FileOutputStream(new File(FrameworkConstants.OrganizationBulkSiteUploadHouseFile));
            wb.write(output_file);
            output_file.close();
            click(addFileToUploadButton);
            waitElementInvisible(loading_cursor);
            List<WebElement> dropdownValues = getDriver().findElements(fileInputButton);
            WebElement secondElement = dropdownValues.get(4);
            System.out.println(">>>" + secondElement.getText());
            secondElement.sendKeys(FrameworkConstants.OrganizationBulkSiteUploadHouseFile);

            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "SiteHouseTemplate.xlsx loaded successfully.");
            Thread.sleep(1000);
            click(fileUploadSubmitButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "SiteHouseTemplate.xlsx saved successfully.");
            Thread.sleep(1000);
            softAssert.assertAll();
            test.pass("User was not able to upload house sites file successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User was able to upload house sites file");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User was able to upload house sites file");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


        @Test(description = "Test Case: Assign Agreement to Organization", enabled = true, priority = 29, groups = {"regression"})
        public void AssignAgreement() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-36: Verify Organization can be assigned Agreement");

                getDriver().get(url_organizationManagement);
                waitElementInvisible(loading_cursor);
                Thread.sleep(4000);

                //openEditOrgPopup(OrganizationName);
                searchCreatedOrg(OrganizationName, organizationEditButton);
                Thread.sleep(2000);
                getDriver().findElement(popupNextButton).click();
                Thread.sleep(1000);
                getDriver().findElement(orgAgreementDropDownExpand).click();
                Thread.sleep(1000);
                getDriver().findElement(orgAgreementDropDownSelect).click();
                getDriver().findElement(popupSaveButton).click();
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                Assert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(1));
                test.pass("Organization was assigned agreeement successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Organization failed to assigned agreeement");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Organization failed to assigned agreeement");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


        @Test(description = "Test Case: InActive Organization", enabled = true, priority = 30, groups = {"regression"})
        public void InActiveOrganization() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-37: Verify Organization Site can be made inactive", "This test case will verify that organization can be made inactive");

                getDriver().get(url_organizationManagement);
                waitElementInvisible(loading_cursor);
                Thread.sleep(3000);

                //openEditOrgPopup(OrganizationName);
                searchCreatedOrg(OrganizationName, organizationEditButton);
                Thread.sleep(1000);
                click(popupNextButton);
                Thread.sleep(1000);

                ClickElement.clickById(getDriver(), "yes_radio");
                click(popupSaveButton);
                waitElementVisible(alertMessage);
                Thread.sleep(1000);
                Assert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(1));
                test.pass("Organization inactivated successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Organization failed to inactivated");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Organization failed to inactivated");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


        @Test(description = "Test Case: Verify InActive Organization", enabled = true, priority = 31, groups = {"regression"})
        public void VerifyInActiveOrganization() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-38: Verify inactive Organization Site is not displayed in create user popup");

                ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
                UserManagementPage.openEditUserPopup(config.ie_username());
                getDriver().findElement(UserManagementPage.userOrgInput).sendKeys(OrganizationName);
                waitElementVisible(By.cssSelector(".ng-option-disabled"));
                Assert.assertTrue(getDriver().findElement(By.cssSelector(".ng-option-disabled")).isDisplayed());
                test.pass("Inactivated Organization was not found in dropdown successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Inactivated Organization was found in dropdown");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Inactivated Organization was found in dropdown");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


        @Test(description = "Test Case: Delete Organization", enabled = true, priority = 35, groups = {"smoke", "regression"})
        public void DeleteOrganization() throws InterruptedException, IOException {
            try {
                test = extent.createTest("AN-OM-42: Verify Organization can be deleted and verify it from table and user dropdown as well");

                getDriver().get(url_organizationManagement);
                waitElementInvisible(loading_cursor);
                waitElementVisible(orgTitle);
                Thread.sleep(4000);

                String recordsCountBefore = getDriver().findElement(By.id(ResultsCount)).getText();

                searchCreatedOrg(OrganizationName, organizationDeleteButton);
                Thread.sleep(1000);
                getDriver().findElement(confirmationYes).click();
                waitElementVisible(alertMessage);
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(5));
                Thread.sleep(3500);
                String recordsCountAfter = getDriver().findElement(By.id(ResultsCount)).getText();
                softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter);
                softAssert.assertAll();
                test.pass("Organization deleted and verified successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Organization failed to delete");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Organization failed to delete");
                saveResult(ITestResult.FAILURE, ex);
            }
        }


    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}

