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

public class OrganizationEngagementCRUD extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Organization_Engagement_CRUD" + DateUtil.date + ".html");
//        spark.config().setReportName("Organization Engagement Management Test Report");
        initReport("Administration_Organization_Engagement_CRUD");
        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_organizationManagement, "Organization Management", orgTitle);
    }

    @Test(enabled = true, priority = 1, groups = {"smoke", "regression"})
    public void CreateOrganization() throws InterruptedException, IOException {
        OrganizationManagementPage.CreateOrganizationFunction(EngagementOrganizationName);
    }

    @Test(enabled = true, priority = 2, groups = {"smoke", "regression"})
    public void CreateEngagement() throws IOException {
        try {
            test = extent.createTest("Verify user can create an engagement");
            SoftAssert softAssert = new SoftAssert();
            searchCreatedOrg(EngagementOrganizationName, organizationEditSitesButton);

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

            click(orgAddSite3);
            waitElementInvisible(loading_cursor);
            click(orgSiteTypeInputChild);
            Thread.sleep(1000);
            click(By.cssSelector("div .ng-option:nth-child(1)"));
            type(orgSiteNameInput, "Test Complex Site");
            click(popupSaveButton);
            click(popupCloseButton);

            searchCreatedOrg(EngagementOrganizationName, organizationEngagementButton);
            Thread.sleep(3000);

            softAssert.assertEquals(getText(popupHeaderTitle2), "Create Engagement");
            softAssert.assertEquals(getAttribute(engagementOrganizationField), EngagementOrganizationName, "Organization Name not auto populated");

            click(engagementComplexDropdownExpand);
            Thread.sleep(1000);
            click(engagementComplexSelect);
            click(engagementComplexDropdownExpand);

            softAssert.assertEquals(size(By.cssSelector(".required")), 4, "There should be 4 mandatory fields");

            click(engagementStartDateDropdown);
            clickDay("01");

            click(engagementEndDateDropdown);
            Thread.sleep(2000);
            click(By.xpath("//*[@formcontrolname='fcEndDate']//*[text()='20']"));  //click on 20

            click(engagementProductListExpand);
            Thread.sleep(1000);
            List<WebElement> element = getDriver().findElements(By.cssSelector(".ng-option label"));
            WebElement ssm = element.get(1);
            ssm.click();

            //IE-12113
            softAssert.assertEquals(element.get(1).getText(), "SSM");
            softAssert.assertEquals(element.get(2).getText(), "CSM");
            softAssert.assertEquals(element.get(3).getText(), "CpSM");
            softAssert.assertEquals(element.get(4).getText(), "ACM");

            click(engagementSubmitButton);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            softAssert.assertAll();
            test.pass("Engagement created successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Engagement failed to create");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Engagement failed to create");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 3, groups = {"smoke", "regression"})
    public void EditEngagement() throws IOException {
        try {
            test = extent.createTest("Verify user can edit an engagement");
            SoftAssert softAssert = new SoftAssert();
            click(orgManagementEngagementTab);
            click(engagementEditButton);
            softAssert.assertEquals(getText(popupHeaderTitle2), "Edit Engagement");

            click(engagementProductListExpand);
            Thread.sleep(1000);
            List<WebElement> element = getDriver().findElements(By.cssSelector(".ng-option label"));
            WebElement ssm = element.get(2);
            ssm.click();
            getScreenshot();
            click(engagementSubmitButton);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");

            String engagementComplexMappingQuery = "Select * from EngagementComplexMapping where " +
                    "engagementId = (Select engagementId from engagement where " +
                    "orgnId = (Select orgnID from organization where " +
                    "orgnName = '" + EngagementOrganizationName + "' and isDeleted = 0 and isActive= 1))";

            ResultSet getEngagementStartDate = DB_Config_DB.getStmt().executeQuery(engagementComplexMappingQuery);
            String startDate = null;
            String endDate = null;
            while (getEngagementStartDate.next()) {
                startDate = getEngagementStartDate.getString("engagementStartDate");
                endDate = getEngagementStartDate.getString("engagementEndDate");
                System.out.println("Start Date: " + startDate);
                System.out.println("End Date: " + endDate);
                softAssert.assertEquals(startDate, dateYYYYMM + "-01 00:00:00.0");
                softAssert.assertEquals(endDate, dateYYYYMM + "-20 00:00:00.0");
            }

            String engagementProductMappingQuery = "Select productId from EngagementComplexProductMapping  where " +
                    "engagementComplexMappingId = (Select engagementComplexMappingId from EngagementComplexMapping where " +
                    "engagementId = (Select engagementId from engagement where orgnId = (Select orgnID from organization where " +
                    "orgnName = '" + EngagementOrganizationName + "' and isDeleted = 0 and isActive= 1))) and isActive = 1";

            ResultSet getEngagementProductID = DB_Config_DB.getStmt().executeQuery(engagementProductMappingQuery);
            String productId = null;
            int i = 1;
            while (getEngagementProductID.next()) {
                productId = getEngagementProductID.getString("productId");
                System.out.println("Product Id: " + productId);
                softAssert.assertEquals(Integer.parseInt(productId), i);
                i = i + 1;
            }

            softAssert.assertAll();
            test.pass("Engagement edited successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Engagement failed to edit");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Engagement failed to edit");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 4, groups = {"smoke", "regression"})
    public void DeleteEngagement() throws IOException {
        try {
            test = extent.createTest("Verify user can delete an engagement");
            SoftAssert softAssert = new SoftAssert();

            click(orgManagementEngagementTab);
            click(engagementDeleteButton);
            click(popupYesButton);
            softAssert.assertEquals(getText(alertMessage), "Engagement Deleted Successfully");

            String engagementQuery = "Select * from engagement where orgnId = (Select orgnID from organization where " +
                    "orgnName = '" + EngagementOrganizationName + "' and isDeleted = 0 and isActive= 1)";

            ResultSet getEngagement = DB_Config_DB.getStmt().executeQuery(engagementQuery);
            String isActive = null;
            String isDelete = null;
            while (getEngagement.next()) {
                isActive = getEngagement.getString("isActive");
                isDelete = getEngagement.getString("isDeleted");
                softAssert.assertEquals(Integer.parseInt(isActive), 0);
                softAssert.assertEquals(Integer.parseInt(isDelete), 1);
            }

            softAssert.assertAll();
            test.pass("Engagement edited successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Engagement failed to edit");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Engagement failed to edit");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 5, groups = {"smoke", "regression"})
    public void DeleteOrganization() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify Organization can be deleted and verify it from table and user dropdown as well");
            getDriver().get(url_organizationManagement);
            waitElementInvisible(loading_cursor);
            waitElementVisible(orgTitle);
            Thread.sleep(4000);

            String recordsCountBefore = getDriver().findElement(By.id(ResultsCount)).getText();

            searchCreatedOrg(EngagementOrganizationName, organizationDeleteButton);
            Thread.sleep(1000);
            click(confirmationYes);
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

