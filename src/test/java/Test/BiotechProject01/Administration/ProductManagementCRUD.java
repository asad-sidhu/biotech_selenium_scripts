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


public class ProductManagementCRUD extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Product_Management" + date + ".html");
//        spark.config().setReportName("Product Management Test Report");
        initReport("Administration_Product_Management_CRUD");
        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_productCatalog, "Product Management", productManagementTitle);
    }

    @Test(priority = 1, enabled = true, groups = {"smoke", "regression"})
    public void VerifyCreateProductFunctionality() throws IOException, InterruptedException, SQLException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.createProductFunctionality();
    }


    @Test(priority = 2, enabled = true, groups = {"regression"})
    public void VerifyViewProductFunctionality() throws IOException, InterruptedException, SQLException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.viewProductFunctionality();
    }

    @Test(priority = 3, enabled = true, groups = {"regression"})
    public void VerifyUpdateProductFunctionality() throws IOException, InterruptedException, SQLException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.updateProductFunctionality();
    }

    @Test(priority = 4, enabled = true, groups = {"smoke", "regression"})
    public void VerifyDeleteProductFunctionality() throws IOException, InterruptedException, SQLException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.deleteProductFunctionality();
    }

    @Test(priority = 5, enabled = true, groups = {"regression"})
    public void VerifyResetProductFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.resetProductFunctionality();
    }

    @Test(priority = 6, enabled = true, groups = {"regression"})
    public void VerifyConfirmationPopupFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.confirmationPopupFunctionality();
    }

    @Test(priority = 7, enabled = true, groups = {"regression"})
    public void VerifyTooltipFunctionalityFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.tooltipFunctionality();
    }

    @Test(priority = 8, enabled = true, groups = {"regression"})
    public void VerifyProfileNavigation() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.profileSettingsFunctionality();
    }

    @Test(priority = 9, enabled = true, groups = {"regression"})
    public void VerifySubmitAndAddNewFunctionality() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.submitAndAddNewFunctionality();
    }

    @Test(priority = 10, enabled = false)
    public void VerifyAccessRights() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.verifyAccessRights();
        pm.verifyAdminRights();
    }

    @Test(priority = 11, enabled = true, groups = {"regression"})
    public void VerifyCreateProductWithImage() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.createProductWithImageFunctionality();
    }

    @Test(priority = 12, enabled = false, groups = {"regression"})
    public void VerifyProgramProduct() throws IOException, InterruptedException {
        ProductManagementPage pm = new ProductManagementPage(getDriver());
        pm.programProductFunctionality();
    }

    @AfterTest(groups = {"smoke", "regression"})
    public static void endReport() {
        extent.flush();
    }
}
