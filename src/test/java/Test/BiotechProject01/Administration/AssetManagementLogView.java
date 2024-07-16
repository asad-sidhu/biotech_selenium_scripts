package Test.BiotechProject01.Administration;

import MiscFunctions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Config.BaseTest;
import MiscFunctions.DB_Config_DW;
import Test.BiotechProject01.Login.LoginTest;
import org.testng.asserts.SoftAssert;

import static ExtentReports.ExtentReport.initReport;
import static LogViewFunctions.FieldAccess.FieldAccessFunctionality;
import static LogViewFunctions.FieldAccess.KeyColumnsCheck;
import static LogViewFunctions.FilterLock.Lock;
import static LogViewFunctions.FilterSort.Sorting;
import static LogViewFunctions.FilterWildcard.Wildcard;
import static LogViewFunctions.LogTemplateExport.TemplateExport;
import static LogViewFunctions.Pagination.Pagination;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.dateMMDDYYYY1;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static LogViewFunctions.LogCSVExport.*;
import static PageObjects.AssetManagementPage.*;
import static PageObjects.BasePage.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;


public class AssetManagementLogView extends BaseTest {


    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
        initReport("Administration_Asset_Management_LogView");
        DB_Config_DB.test();
        DB_Config_DW.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_animalManagement, "Asset Management", AssetManagementTitle);
    }

    @Test(priority = 2, groups = {"smoke", "regression"})
    public void Lockdeployment() throws InterruptedException, IOException {
        Lock(AssetdeploymentTable, "Asset Management", 1);
    }

    @Test(priority = 3, groups = {"smoke", "regression"})
    public void LockMortality() throws InterruptedException, IOException {
        click(AssetMortalityTab);
        Thread.sleep(1000);
        Lock(AssetMortalityTable, "Asset Management", 1);
    }


    @Test(priority = 4, groups = {"smoke", "regression"})
    public void LockSettlement() throws InterruptedException, IOException {
        click(AssetSettlementTab);
        Thread.sleep(1000);
        Lock(AssetSettlementTable, "Asset Management", 1);
    }


    @Test(priority = 5, groups = {"smoke", "regression"})
    public void LockCondemnation() throws InterruptedException, IOException {
        click(AssetCondemnationTab);
        Thread.sleep(1000);
        Lock(AssetCondemnationTable, "Asset Management", 1);
    }

    @Test(priority = 6, groups = {"smoke", "regression"})
    public void LockAssetHierarchy() throws InterruptedException, IOException {
        click(AssetHierarchyTab);
        Thread.sleep(1000);
        Lock(AssetHierarchyTable, "Asset Management", 1);
    }


    @Test(priority = 7, groups = "regression")
    public void Wildcarddeployment() throws InterruptedException, IOException {
        getDriver().get(url_animalManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Wildcard(AssetdeploymentTable, "Asset Management", 1);
    }


    @Test(priority = 8, groups = "regression")
    public void WildcardMortality() throws InterruptedException, IOException {
        click(AssetMortalityTab);
        Thread.sleep(1000);
        Wildcard(AssetMortalityTable, "Asset Management", 1);
    }


    @Test(priority = 9, groups = "regression")
    public void WildcardSettlement() throws InterruptedException, IOException {
        click(AssetSettlementTab);
        Thread.sleep(1000);
        Wildcard(AssetSettlementTable, "Asset Management", 1);
    }


    @Test(priority = 10, groups = "regression")
    public void WildcardCondemnation() throws InterruptedException, IOException {
        click(AssetCondemnationTab);
        Thread.sleep(1000);
        Wildcard(AssetCondemnationTable, "Asset Management", 1);
    }

    @Test(priority = 11, groups = "regression")
    public void WildcardAssetHierarchy() throws InterruptedException, IOException {
        click(AssetHierarchyTab);
        Thread.sleep(1000);
        Wildcard(AssetHierarchyTable, "Asset Management", 1);
    }


    @Test(priority = 12, groups = {"smoke", "regression"})
    public void Sortingdeployment() throws InterruptedException, IOException {
        getDriver().get(url_animalManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(2000);
        Sorting(AssetdeploymentTable, "Asset Management", 1);
    }


    @Test(priority = 13, groups = {"smoke", "regression"})
    public void SortingMortality() throws InterruptedException, IOException {
        click(AssetMortalityTab);
        Thread.sleep(1000);
        Sorting(AssetMortalityTable, "Asset Management", 1);
    }


    @Test(priority = 14, groups = {"smoke", "regression"})
    public void SortingSettlement() throws InterruptedException, IOException {
        click(AssetSettlementTab);
        Thread.sleep(1000);
        Sorting(AssetSettlementTable, "Asset Management", 1);
    }


    @Test(priority = 15, groups = {"smoke", "regression"})
    public void SortingCondemnation() throws InterruptedException, IOException {
        click(AssetCondemnationTab);
        Thread.sleep(1000);
        Sorting(AssetCondemnationTable, "Asset Management", 1);
    }

    @Test(priority = 16, groups = {"smoke", "regression"})
    public void SortingHierarchy() throws InterruptedException, IOException {
        click(AssetHierarchyTab);
        Thread.sleep(1000);
        Sorting(AssetHierarchyTable, "Asset Management", 1);
    }


    @Test(priority = 17, enabled = true, groups = "regression")
    public void RowsPerPagedeployment() throws InterruptedException, IOException {
        getDriver().get(url_animalManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        RowsPerPage1(AssetdeploymentTable);
    }


    @Test(priority = 18, enabled = true, groups = "regression")
    public void RowsPerPageMortality() throws InterruptedException, IOException {
        click(AssetMortalityTab);
        Thread.sleep(1000);
        RowsPerPage1(AssetMortalityTable);
    }


    @Test(priority = 19, enabled = true, groups = "regression")
    public void RowsPerPageSettlement() throws InterruptedException, IOException {
        click(AssetSettlementTab);
        Thread.sleep(1000);
        RowsPerPage1(AssetSettlementTable);
    }


    @Test(priority = 20, enabled = true, groups = "regression")
    public void RowsPerPageCondemnation() throws InterruptedException, IOException {
        click(AssetCondemnationTab);
        Thread.sleep(1000);
        RowsPerPage1(AssetCondemnationTable);
    }

    @Test(priority = 21, enabled = true, groups = "regression")
    public void RowsPerPageHierarchy() throws InterruptedException, IOException {
        click(AssetHierarchyTab);
        Thread.sleep(1000);
        RowsPerPage1(AssetHierarchyTable);
    }


    @Test(priority = 22, groups = "regression")
    public void Paginationdeployment() throws InterruptedException, IOException {
        getDriver().get(url_animalManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Pagination(AssetdeploymentTable, "Asset Management");
    }


    @Test(priority = 23, groups = "regression")
    public void PaginationMortality() throws InterruptedException, IOException {
        click(AssetMortalityTab);
        Thread.sleep(1000);
        Pagination(AssetMortalityTable, "Asset Management");
    }


    @Test(priority = 24, groups = "regression")
    public void PaginationSettlement() throws InterruptedException, IOException {
        click(AssetSettlementTab);
        Thread.sleep(1000);
        Pagination(AssetSettlementTable, "Asset Management");
    }


    @Test(priority = 25, groups = "regression")
    public void PaginationCondemnation() throws InterruptedException, IOException {
        click(AssetCondemnationTab);
        Thread.sleep(1000);
        Pagination(AssetCondemnationTable, "Asset Management");
    }

    @Test(priority = 26, groups = "regression")
    public void PaginationHierarchy() throws InterruptedException, IOException {
        click(AssetHierarchyTab);
        Thread.sleep(1000);
        Pagination(AssetHierarchyTable, "Asset Management");
    }


    @Test(priority = 27, enabled = true, groups = "regression")
    public void VerifyColumnsdeploymentTab() throws IOException, InterruptedException {
        getDriver().get(url_animalManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        click(AssetdeploymentTab);
        VerifyAllColumns(AssetdeploymentTable, new String[]{"Asset ID", "Integrator Asset ID", "animal Size", "animal Sex", "Marketing Program", "animal Breed", "deployment Date", "No Of animals Placed", "Program Information", "deployment Information", "Complex", "Farm", "Integrator", "Farm Site ID", "Estimated Processing Date", "Production Type", "Hatchery Source", "Target Weight (Lbs)", "Avg. Target Slaughter Age (Days)", "Monitoring Plan", "OPG Group", "External Farm ID", "Hatchery Location", "Number Of Eggs", "Location Intervention", "Age At deployment", "Unit"});
    }

    @Test(priority = 28, enabled = true, groups = "regression")
    public void VerifyColumnsMortalityTab() throws IOException {
        click(AssetMortalityTab);
        VerifyAllColumns(AssetMortalityTable, new String[]{"Asset ID", "Integrator Asset ID", "Integrator", "Complex", "Farm", "Farm Site ID", "deployment Date", "Week 1 Mortality", "Week 2 Mortality", "Week 3 Mortality", "Week 4 Mortality", "Week 5 Mortality", "Week 6 Mortality", "Week 7 Mortality", "Week 8 Mortality", "Week 9 Mortality"});
    }

    @Test(priority = 29, enabled = true, groups = "regression")
    public void VerifyColumnsSettlementTab() throws IOException {
        click(AssetSettlementTab);
        VerifyAllColumns(AssetSettlementTable, new String[]{"Asset ID", "Integrator Asset ID", "Integrator", "Complex", "Farm", "Farm Site ID", "deployment Date", "Weekly Farm Rank", "Historical Farm Cost Variance", "Weekly Farm Cost Variance", "Hatch Date", "Days Out", "Age Of Litter", "Average Sold Age", "Num animals Sold", "deployment Density", "Processing Date", "Processing Site ID", "USDA Plant ID", "Plant Location", "animals Processed", "Avg animal Weight (Lb)", "Avg animal Weight (Kg)", "Total Weight Processed (Lb)", "Total Weight Processed (Kg)", "Total Feed Weight (Lb)", "Total Feed Weight (Kg)", "FCR", "Adjusted FCR", "Feed Cost Per Live (Lb)", "Medication Cost Per Live (Lb)", "Grower Cost Per Live (Lb)", "Livability Percentage", "Overall Mortality Percentage", "Avg. Daily Weight Gain (Lb)", "Avg. Daily Weight Gain (Kg)"});
    }

    @Test(priority = 30, enabled = true, groups = "regression")
    public void VerifyColumnsCondemnationTab() throws IOException {
        click(AssetCondemnationTab);
        VerifyAllColumns(AssetCondemnationTable, new String[]{"Asset ID", "Integrator Asset ID", "Integrator", "Complex", "Farm", "Farm Site ID", "deployment Date", "Num animals DOA Plant", "Total Weight Condemned (Lb)", "Total Weight Condemned (Kg)", "Num animals Condemned Whole", "Parts Weight Condemned (Lb)", "Parts Weight Condemned (Kg)", "KCal/LB", "Grade A Paws Percentage", "IP Percentage", "Leukosis Percentage", "Septox Percentage", "Tumor Percentage", "Airsac Percentage"});
    }

    @Test(priority = 31, enabled = true, groups = "regression")
    public void VerifyColumnsHierarchyTab() throws IOException {
        click(AssetHierarchyTab);
        VerifyAllColumns(AssetHierarchyTable, new String[]{"Child Asset ID", "Child deployment Date", "Parent Asset ID", "Parent deployment Date", "Number Of animals Placed"});
    }

    @Test(priority = 32, enabled = true, groups = "regression")
    public void FieldAccessdeployment() throws InterruptedException, IOException {
        getDriver().get(url_animalManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        click(AssetdeploymentTab);
        FieldAccessFunctionality(AssetdeploymentTable);
    }

    @Test(priority = 33, enabled = true, groups = "regression")
    public void FieldAccessKeyColumnsdeployment() throws InterruptedException, IOException {
        KeyColumnsCheck(AssetdeploymentTable, new String[]{"Asset ID", "deployment INFORMATION", "FARM"});
    }

    @Test(priority = 34, enabled = true, groups = "regression")
    public void FieldAccessMortality() throws InterruptedException, IOException {
        click(AssetMortalityTab);
        FieldAccessFunctionality(AssetMortalityTable);
    }

    @Test(priority = 35, enabled = true, groups = "regression")
    public void FieldAccessKeyColumnsMortality() throws InterruptedException, IOException {
        KeyColumnsCheck(AssetMortalityTable, new String[]{"Asset ID"});
    }

    @Test(priority = 36, enabled = true, groups = "regression")
    public void FieldAccessSettlement() throws InterruptedException, IOException {
        click(AssetSettlementTab);
        FieldAccessFunctionality(AssetSettlementTable);
    }

    @Test(priority = 37, enabled = true, groups = "regression")
    public void FieldAccessKeyColumnsSettlement() throws InterruptedException, IOException {
        KeyColumnsCheck(AssetSettlementTable, new String[]{"Asset ID"});
    }

    @Test(priority = 38, enabled = true, groups = "regression")
    public void FieldAccessCondemnation() throws InterruptedException, IOException {
        click(AssetCondemnationTab);
        FieldAccessFunctionality(AssetCondemnationTable);
    }

    @Test(priority = 39, enabled = true, groups = "regression")
    public void FieldAccessKeyColumnsCondemnation() throws InterruptedException, IOException {
        KeyColumnsCheck(AssetCondemnationTable, new String[]{"Asset ID"});
    }

    @Test(priority = 40, enabled = true, groups = "regression")
    public void FieldAccessHierarchy() throws InterruptedException, IOException {
        click(AssetHierarchyTab);
        FieldAccessFunctionality(AssetHierarchyTable);
    }

    @Test(priority = 41, enabled = true, groups = "regression")
    public void FieldAccessKeyColumnsHierarchy() throws InterruptedException, IOException {
        KeyColumnsCheck(AssetHierarchyTable, new String[]{"CHILD Asset ID", "CHILD Asset deployment DATE", "PARENT Asset ID", "PARENT Asset deployment DATE", "NO. OF animalS PLACED"});
    }

    @Test(priority = 42, groups = "regression")
    public void ExportdeploymentCSV() throws InterruptedException, IOException {
        getDriver().get(url_animalManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        CSVExport1("Asset Management", AssetdeploymentCSVFileName, AssetdeploymentTable, 2, 0);
    }

    @Test(priority = 43, groups = "regression")
    public void ExportdeploymentCSVAudit() throws InterruptedException, IOException {
        CSVAuditExport("Asset Management", AssetdeploymentAuditFileName, AssetdeploymentTable, false);
    }

    @Test(priority = 44, groups = "regression")
    public void ExportdeploymentTemplate() throws InterruptedException, IOException {
        TemplateExport(AssetEOFTemplateFileName);
    }

    @Test(priority = 45, groups = "regression")
    public void ExportMortalityCSV() throws InterruptedException, IOException {
        click(AssetMortalityTab);
        CSVExport1("Asset Management", AssetMortalityCSVFileName, AssetMortalityTable, 2, 0);
    }

    @Test(priority = 46, groups = "regression")
    public void ExportMortalityCSVAudit() throws InterruptedException, IOException {
        CSVAuditExport("Asset Management", AssetMortalityAuditFileName, AssetMortalityTable, false);
    }

    @Test(priority = 47, groups = "regression")
    public void ExportMortalityTemplate() throws InterruptedException, IOException {
        TemplateExport(AssetEOFTemplateFileName);
    }

    @Test(priority = 48, groups = "regression")
    public void ExportSettlementCSV() throws InterruptedException, IOException {
        click(AssetSettlementTab);
        CSVExport1("Asset Management", AssetSettlementCSVFileName, AssetSettlementTable, 2, 0);
    }

    @Test(priority = 49, groups = "regression")
    public void ExportSettlementCSVAudit() throws InterruptedException, IOException {
        CSVAuditExport("Asset Management", AssetSettlementAuditFileName, AssetSettlementTable, false);
    }

    @Test(priority = 50, groups = "regression")
    public void ExportSettlementTemplate() throws InterruptedException, IOException {
        TemplateExport(AssetEOFTemplateFileName);
    }

    @Test(priority = 51, groups = "regression")
    public void ExportCondemnationCSV() throws InterruptedException, IOException {
        click(AssetSettlementTab);
        CSVExport1("Asset Management", AssetSettlementCSVFileName, AssetSettlementTable, 2, 0);
    }

    @Test(priority = 52, groups = "regression")
    public void ExportCondemnationCSVAudit() throws InterruptedException, IOException {
        CSVAuditExport("Asset Management", AssetCondemnationAuditFileName, AssetCondemnationTable, false);
    }

    @Test(priority = 53, groups = "regression")
    public void ExportCondemnationTemplate() throws InterruptedException, IOException {
        TemplateExport(AssetEOFTemplateFileName);
    }

    @Test(priority = 54, groups = "regression")
    public void ExportHierarchyCSV() throws InterruptedException, IOException {
        click(AssetHierarchyTab);
        CSVExport1("Asset Management", AssetHierarchyCSVFileName, AssetHierarchyTable, 2, 0);
    }



    @Test(priority = 54, groups = "regression")
    public static void DateFilter() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify lock filter functionality on deployment date filter");
            SoftAssert softAssert = new SoftAssert();
            BaseTest driver = new BaseTest();
            String recordBefore = getText(By.id(ResultsCount));
            scroll(By.id("deploymentDate_show-filter"));
            click(By.id("deploymentDate_show-filter"));
            Thread.sleep(2000);

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            WebElement selectLabel = driver.getDriver().findElement(By.cssSelector(".fa-chevron-down"));
            Actions actions = new Actions(driver.getDriver());
            actions.moveToElement(selectLabel, 1, 2).click().build().perform();
            Thread.sleep(2000);
            if (driver.getDriver().findElement(By.id("deploymentDate_range-0")).isEnabled()) {
                actions.moveToElement(driver.getDriver().findElement(By.id("deploymentDate_range-0"))).click().perform();
                waitElementInvisible(loading_cursor);
                Thread.sleep(750);
                click(By.id("deploymentDate_show-filter"));
                Thread.sleep(700);
                getScreenshot();
                String fromDateField = getAttribute(By.cssSelector("input[placeholder='Start Date']"));
                String toDateField = getAttribute(By.cssSelector("input[placeholder='End Date']"));
                System.out.println(fromDateField + ">>" + toDateField);
                LocalDate last7Days = today.minusDays(7);
                String formattedLast7Days = last7Days.format(formatter);
                softAssert.assertEquals(fromDateField, formattedLast7Days, "Failed for last Week");
                softAssert.assertEquals(toDateField, dateMMDDYYYY1, "Failed for last week");
                String recordAfter = driver.getDriver().findElement(By.id(ResultsCount)).getText();
                softAssert.assertNotEquals(recordBefore, recordAfter);
                softAssert.assertEquals(size(alertMessage), 0, "Error occured");
            }

            actions.moveToElement(selectLabel, 1, 2).click().build().perform();
            if (driver.getDriver().findElement(By.id("deploymentDate_range-1")).isEnabled()) {
                actions.moveToElement(driver.getDriver().findElement(By.id("deploymentDate_range-1"))).click().perform();
                waitElementInvisible(loading_cursor);
                Thread.sleep(750);
                click(By.id("deploymentDate_show-filter"));
                Thread.sleep(700);
                getScreenshot();
                String fromDateField = getAttribute(By.cssSelector("input[placeholder='Start Date']"));
                String toDateField = getAttribute(By.cssSelector("input[placeholder='End Date']"));
                System.out.println(fromDateField + ">>" + toDateField);

                YearMonth lastMonth = YearMonth.from(today).minusMonths(1);
                LocalDate firstDateOfLastMonth = lastMonth.atDay(1);
                LocalDate lastDateOfLastMonth = lastMonth.atEndOfMonth();
                String firstDate = firstDateOfLastMonth.format(formatter);
                String lastDate = lastDateOfLastMonth.format(formatter);
                softAssert.assertEquals(fromDateField, firstDate, "Failed for last Month");
                softAssert.assertEquals(toDateField, lastDate, "Failed for last Month");
                String recordAfter = driver.getDriver().findElement(By.id(ResultsCount)).getText();
                softAssert.assertNotEquals(recordBefore, recordAfter);
                softAssert.assertEquals(size(alertMessage), 0, "Error occured");
            }

            actions.moveToElement(selectLabel, 1, 2).click().build().perform();
            if (driver.getDriver().findElement(By.id("deploymentDate_range-2")).isEnabled()) {
                actions.moveToElement(driver.getDriver().findElement(By.id("deploymentDate_range-2"))).click().perform();
                waitElementInvisible(loading_cursor);
                Thread.sleep(750);
                click(By.id("deploymentDate_show-filter"));
                Thread.sleep(700);
                getScreenshot();
                String fromDateField = getAttribute(By.cssSelector("input[placeholder='Start Date']"));
                String toDateField = getAttribute(By.cssSelector("input[placeholder='End Date']"));
                System.out.println(fromDateField + ">>" + toDateField);
                Year lastYear = Year.from(today).minusYears(1);
                LocalDate firstDateOfLastYear = lastYear.atDay(1);
                LocalDate lastDateOfLastYear = lastYear.atDay(lastYear.length());
                String firstDate = firstDateOfLastYear.format(formatter);
                String lastDate = lastDateOfLastYear.format(formatter);
                softAssert.assertEquals(fromDateField, firstDate, "Failed for last Year");
                softAssert.assertEquals(toDateField, lastDate, "Failed for last Year");
                String recordAfter = driver.getDriver().findElement(By.id(ResultsCount)).getText();
                softAssert.assertNotEquals(recordBefore, recordAfter);
                softAssert.assertEquals(size(alertMessage), 0, "Error occured");
            }

            softAssert.assertAll();
            test.pass("Filter functionality verified successfully on deployment date filter");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Filer lock functionality failed on deployment date filter");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Filer lock functionality failed on deployment date filter");
            saveResult(ITestResult.FAILURE, ex);
        }
    }




    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }
}

