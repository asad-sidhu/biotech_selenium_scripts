package Test.BiotechProject01.Administration;

import Config.BaseTest;
import MiscFunctions.DB_Config_DB;
import MiscFunctions.NavigateToScreen;
import PageObjects.ProductManagementPage;
import Test.BiotechProject01.Login.LoginTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.Constants.url_productCatalog;
import static MiscFunctions.DateUtil.date;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;
import static PageObjects.ProductManagementPage.productManagementTitle;


public class ProductManagementLogView extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Product_Management_LogView" + date + ".html");
//        spark.config().setReportName("Product Management Test Report");
        initReport("Administration_Product_Management_LogView");
        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_productCatalog, "Product Management", productManagementTitle);
    }


    @Test(priority = 1, enabled = true, groups = {"smoke", "regression"})
    public void VerifyFieldAccessFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.verifyFieldAccess();
    }

    @Test(priority = 2, enabled = true, groups = {"regression"})
    public void VerifyFieldAccessKeyColumnsCheck() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.verifyKeyColumnsCheck();
    }

    @Test(priority = 3, enabled = true, groups = {"smoke", "regression"})
    public void VerifyLockFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.verifyLockFilterFunctionality();
    }

    @Test(priority = 4, enabled = true, groups = {"regression"})
    public void VerifyWildCardFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.verifyWildCardFunctionality();
    }

    @Test(priority = 5, enabled = true, groups = {"regression"})
    public void VerifyPaginationFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.paginationFunctionality();
    }

    @Test(priority = 6, enabled = true, groups = {"regression"})
    public void VerifyAllColumnsFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.verifyAllColumns();
    }

    @Test(priority = 7, enabled = true, groups = {"smoke", "regression"})
    public void VerifySortingFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.sortingFunctionality();
    }

    @Test(priority = 8, enabled = true, groups = {"regression"})
    public void VerifyExportCSVFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.exportCSVFunctionality();
    }

    @Test(priority = 9, enabled = true, groups = {"regression"})
    public void VerifyExportAuditCSVFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.exportCSVAuditFunctionality();
    }

    @AfterTest(groups = {"smoke", "regression"})
    public static void endReport() {
        extent.flush();
    }

}
