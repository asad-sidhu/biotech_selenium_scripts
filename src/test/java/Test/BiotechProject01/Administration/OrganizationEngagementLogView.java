package Test.BiotechProject01.Administration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import LogViewFunctions.RowsPerPage;
import LogViewFunctions.VerifyColumnNames;
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
import MiscFunctions.FrameworkConstants;
import MiscFunctions.ClickElement;
import MiscFunctions.DateUtil;
import MiscFunctions.NavigateToScreen;
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

public class OrganizationEngagementLogView extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Organization_Engagement_LogView" + DateUtil.date + ".html");
//        spark.config().setReportName("Organization Engagement Test Report");
        initReport("Administration_Organization_Engagement_LogView");

    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_organizationManagement, "Organization Management", orgTitle);
    }

    @Test(priority = 1, enabled = false, groups = {"smoke", "regression"})
    public void Navigate() throws InterruptedException, IOException {
        NavigateToScreen.navigate(url_organizationManagement, "Organization Management", orgTitle);
    }

    @Test(priority = 2, enabled = true, groups = {"smoke", "regression"})
    public void LockFilter() throws InterruptedException, IOException {
        click(orgManagementEngagementTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(2000);
        Lock(orgManagementTable, "Organization Management", 1);
    }

    @Test(priority = 3, enabled = true, groups = {"regression"})
    public void WildcardOrg() throws InterruptedException, IOException {
        Wildcard(orgManagementTable, "Organization Management", 1);
    }

    @Test(priority = 4, enabled = true, groups = {"smoke", "regression"})
    public void SortingOrg() throws InterruptedException, IOException {
        Sorting(orgManagementTable, "Organization Management", 1);
    }

    @Test(priority = 5, enabled = true, groups = {"regression"})
    public void PaginationOrg() throws InterruptedException, IOException {
        Pagination(orgManagementTable, "Organization Management");
    }

    @Test(priority = 6, enabled = true, groups = {"regression"})
    public void RowsPerPageOrg() throws InterruptedException, IOException {
        RowsPerPage_();
    }

        @Test(priority = 7, enabled = true, groups = {"regression"})
    public void verifyColumns() throws IOException, InterruptedException {
        click(orgManagementEngagementTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(2000);
        VerifyAllColumns(orgManagementTable, new String[]{"Organization Name", "Complex", "Engagement Start Date", "Engagement End Date", "Products"});
    }

    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}

