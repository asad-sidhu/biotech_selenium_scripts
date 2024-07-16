package Test.BiotechProject01.Administration;

import java.io.IOException;

import MiscFunctions.*;
import Models.ProgramManagementModel;
import com.aventstack.extentreports.gherkin.model.Scenario;
import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import Test.BiotechProject01.Login.LoginTest;
import org.testng.asserts.SoftAssert;

import static ExtentReports.ExtentReport.initReport;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static Models.ProgramManagementModel.lstProgramManagementSearch;
import static PageObjects.AssetManagementPage.AssetdeploymentTab;
import static PageObjects.AssetManagementPage.AssetdeploymentTable;
import static PageObjects.ProgramManagementPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static LogViewFunctions.RowsPerPage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static LogViewFunctions.LogCSVExport.*;


public class ProgramManagementLogView extends BaseTest {


    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Program_Management" + date + ".html");
//        spark.config().setReportName("Program Management Test Report");
        initReport("Administration_Program_Management_LogView");

        DB_Config_DW.test();
        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_programAdmin, "Program Management", programManagementTitle);
    }


    @Test(priority = 1, enabled = true, groups = {"smoke", "regression"})
    public void LockFeed() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programFeedProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Lock(programFeedTable, "Program Management", 0);
    }


    @Test(priority = 2, enabled = true, groups = {"smoke", "regression"})
    public void LockVaccine() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programVaccineProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Lock(programVaccineTable, "Program Management", 0);
    }


    @Test(priority = 3, enabled = true, groups = {"smoke", "regression"})
    public void LockBioshuttle() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programBioshuttleProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Lock(programBioshuttleTable, "Program Management", 1);
    }


    @Test(priority = 4, enabled = true, groups = {"smoke", "regression"})
    public void LockUtilization() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programProgramUtilizationTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Lock(programUtilizationTable, "Program Management", 0);
    }


    @Test(priority = 5, enabled = true, groups = {"regression"})
    public void WildcardFeed() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programFeedProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Wildcard(programFeedTable, "Program Management", 0);
    }


    @Test(priority = 6, enabled = true, groups = {"regression"})
    public void WildcardVaccine() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programVaccineProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Wildcard(programVaccineTable, "Program Management", 0);
    }


    @Test(priority = 7, enabled = true, groups = {"regression"})
    public void WildcardBioshuttle() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programBioshuttleProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Wildcard(programBioshuttleTable, "Program Management", 1);
    }


    @Test(priority = 8, enabled = true, groups = {"regression"})
    public void WildcardUtilization() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programProgramUtilizationTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Wildcard(programUtilizationTable, "Program Management", 0);
    }


    @Test(priority = 9, enabled = true, groups = {"regression"})
    public void sortingFeed() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programFeedProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Sorting(programFeedTable, "Program Management", 0);
    }


    @Test(priority = 10, enabled = true, groups = {"regression"})
    public void sortingVaccine() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programVaccineProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Sorting(programVaccineTable, "Program Management", 0);
    }


    @Test(priority = 11, enabled = true, groups = {"regression"})
    public void sortingBioshuttle() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programBioshuttleProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Sorting(programBioshuttleTable, "Program Management", 1);
    }


    @Test(priority = 12, enabled = true, groups = {"regression"})
    public void sortingUtilization() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programProgramUtilizationTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Sorting(programUtilizationTable, "Program Management", 0);
    }


    @Test(priority = 13, enabled = true, groups = {"regression"})
    public void RowsPerPageFeed() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
       click(programFeedProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        RowsPerPage1(programFeedTable);
    }



    @Test(priority = 14, enabled = true, groups = {"regression"})
    public void RowsPerPageVaccine() throws InterruptedException, IOException {
        click(programVaccineProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        RowsPerPage1(programVaccineTable);
    }


    @Test(priority = 15, enabled = true, groups = {"regression"})
    public void RowsPerPageBioshuttle() throws InterruptedException, IOException {
        click(programBioshuttleProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        RowsPerPage1(programBioshuttleTable);
    }


    @Test(priority = 16, enabled = true, groups = {"regression"})
    public void RowsPerPageUtilization() throws InterruptedException, IOException {
        click(programProgramUtilizationTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        RowsPerPage1(programUtilizationTable);
    }


    @Test(priority = 17, enabled = true, groups = {"regression"})
    public void PaginationFeed() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programFeedProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Pagination(programFeedTable, "Program Management");
    }


    @Test(priority = 18, enabled = true, groups = {"regression"})
    public void PaginationVaccine() throws InterruptedException, IOException {
        click(programVaccineProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Pagination(programVaccineTable, "Program Management");
    }

    @Test(priority = 19, enabled = true)
    public void PaginationBioshuttle() throws InterruptedException, IOException {
        click(programBioshuttleProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Pagination(programBioshuttleTable, "Program Management");
    }

    @Test(priority = 20, enabled = true, groups = {"regression"})
    public void PaginationUtilization() throws InterruptedException, IOException {
        click(programProgramUtilizationTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Pagination(programUtilizationTable, "Program Management");
    }

    @Test(priority = 21, enabled = true, groups = {"regression"})
    public void VerifyColumnsFeedTab() throws IOException, InterruptedException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programFeedProgramTab);
        VerifyAllColumns(programFeedTable, new String[]{"Program Name", "Feed Types", "Start Date", "End Date", "Complex", "Target Pathogen", "Trade Name", "Program Display Name"});
    }


    @Test(priority = 22, enabled = true, groups = {"regression"})
    public void VerifyColumnsVaccineTab() throws IOException, InterruptedException {
        click(programVaccineProgramTab);
        VerifyAllColumns(programVaccineTable, new String[]{"Program Name", "Start Date", "End Date", "Complex", "Target Pathogen", "Trade Name", "Program Display Name"});
    }

    @Test(priority = 23, enabled = true, groups = {"regression"})
    public void VerifyColumnsBioshuttleTab() throws IOException, InterruptedException {
        click(programBioshuttleProgramTab);
        VerifyAllColumns(programBioshuttleTable, new String[]{"Program Name", "Program Type", "Target Pathogen", "Complex", "Start Date", "End Date", "Feed Types", "Feed Type Categories", "Vaccine Trade Name", "Feed Trade Name", "Program Display Name"});
    }

    @Test(priority = 24, enabled = true, groups = {"regression"})
    public void VerifyColumnsProgramUtilizationTab() throws IOException, InterruptedException {
        click(programProgramUtilizationTab);
        VerifyAllColumns(programUtilizationTable, new String[]{"Asset ID", "Integrator Asset ID", "Program Type", "Program Display Name", "Complex", "Farm", "deployment Information", "Start Date", "End Date"});
    }

    @Test(priority = 25, enabled = true, groups = {"regression"})
    public void ExportCSVFeed() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programFeedProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        CSVExport1("Program Management", programFeedCSVFileName, programFeedTable, 9, 0);
    }


    @Test(priority = 26, enabled = true, groups = {"regression"})
    public void ExportCSVVaccine() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programVaccineProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        CSVExport1("Program Management", programVaccineCSVFileName, programVaccineTable, 8, 0);
    }

    @Test(priority = 27, enabled = true, groups = {"regression"})
    public void ExportCSVBioshuttle() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(programBioshuttleProgramTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        CSVExport1("Program Management", programBioshuttleCSVFileName, programBioshuttleTable, 12, 2);
    }

    @Test(priority = 28, enabled = true, groups = {"regression"})
    public void ExportCSVUtilization() throws InterruptedException, IOException {
        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
       click(programProgramUtilizationTab);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        CSVExport1("Program Management", programUtilizationCSVFileName, programUtilizationTable, 5, 0);
    }


    @Test(enabled = true, priority = 29, groups = {"regression"})
    public void ComplexFilterTest() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();


        SoftAssert softAssert = new SoftAssert();

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest("AN-Program: Verify Site Name Filter Functionality");
                results = test.createNode(Scenario.class, Results);

                getDriver().get(Constants.url_programAdmin);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                click(By.xpath("//*[text()= '" + objModel.Program + " ']"));
                waitElementInvisible(loading_cursor);

                String recordsBefore = getDriver().findElement(By.cssSelector("#" + objModel.ProgramTable + " #" + ResultsCount)).getText();

                scroll(By.cssSelector("#" + objModel.ProgramTable + " #complex" + "" + ShowFilter));
                Thread.sleep(500);
                click(By.cssSelector("#" + objModel.ProgramTable + " #complex" + "" + ShowFilter));
                waitElementInvisible(loading_cursor);
                Thread.sleep(700);

                if (getDriver().findElements(By.cssSelector("#sort-complex-" + objModel.ButtonPost + " tr")).size() >= 2) {
                    scroll(By.cssSelector("#sort-complex-" + objModel.ButtonPost + " tr:nth-child(2) label:nth-child(1)"));
                    click(By.cssSelector("#sort-complex-" + objModel.ButtonPost + " tr:nth-child(2) label:nth-child(1)"));
                    waitElementInvisible(loading_cursor);
                    scroll(By.cssSelector("#" + objModel.ProgramTable + " #list-title_apply"));
                    click(By.cssSelector("#" + objModel.ProgramTable + " #list-title_apply"));

                    waitElementInvisible(loading_cursor);
                    Thread.sleep(1000);
                    softAssert.assertTrue(size(alertMessage)==0, "Alert Message displayed on applying filter");
                    softAssert.assertNotEquals(getDriver().findElement(By.cssSelector("#" + objModel.ProgramTable + " #" + ResultsCount)).getText(), recordsBefore);

                    softAssert.assertAll();
                    test.pass("Checkbox selected successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                }

            } catch (AssertionError er) {
                test.fail("Filer lock functionality failed");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Filer lock functionality failed");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }

    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}
