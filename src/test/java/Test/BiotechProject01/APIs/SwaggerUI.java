package Test.BiotechProject01.APIs;

import Config.BaseTest;
import Config.ReadPropertyFile;
import MiscFunctions.Constants;
import MiscFunctions.DB_Config_DB;
import MiscFunctions.DB_Config_DW;
import MiscFunctions.DateUtil;
import PageObjects.SwaggerUIPage;
import Test.BiotechProject01.Login.LoginTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;

public class SwaggerUI extends BaseTest {

    public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
    BaseTest driver = new BaseTest();

    @BeforeTest
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/SwaggerUI" + DateUtil.date + ".html");
//        spark.config().setReportName("Swagger Test Report");
        initReport("SwaggerUI");
        DB_Config_DW.test();
        DB_Config_DB.test();
    }

    @BeforeClass
    public void navigateSwagger() throws InterruptedException, IOException {
        getDriver().get(config.swaggerUI());
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void authorize() throws InterruptedException, IOException {
        SwaggerUIPage sp = new SwaggerUIPage(getDriver());
        sp.authorizeUser();
    }

    @Test(priority = 2, enabled = true, dependsOnMethods = {"authorize"})
    public void getAssetIDAPI() throws InterruptedException, IOException, SQLException {
        SwaggerUIPage sp = new SwaggerUIPage(getDriver());
        sp.getAssetIDAPI();
    }

    @Test(priority = 3, dependsOnMethods = {"authorize"})
    public void createAssetAPI() throws InterruptedException, IOException, SQLException {
        SwaggerUIPage sp = new SwaggerUIPage(getDriver());
        sp.createAssetAPI();
    }

    @Test(priority = 4, dependsOnMethods = {"authorize"})
    public void createAssetSettlementAPI() throws InterruptedException, IOException, SQLException {
        SwaggerUIPage sp = new SwaggerUIPage(getDriver());
        sp.createAssetSettlementAPI();
    }


    @AfterTest
    public static void endreport() {
        extent.flush();
    }

}


