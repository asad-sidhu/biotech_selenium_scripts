package Test.BiotechProject01.Login;

import java.io.IOException;
import java.net.MalformedURLException;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import Config.ReadPropertyFile;
import MiscFunctions.DateUtil;
import org.testng.asserts.SoftAssert;

import static ExtentReports.ExtentReport.initReport;
import static PageObjects.BasePage.*;
import static PageObjects.LoginPage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static Models.LoginModel.*;
import static MiscFunctions.Methods.*;


public class LoginTest extends BaseTest {

    public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);


    @BeforeTest(groups = {"smoke", "regression"})
    public static void setUp() {
        initReport("Login");
    }


    @Test(enabled = true, priority = 1, groups = {"smoke", "regression"})
    public static void login() throws InterruptedException, IOException {
        BaseTest base = new BaseTest();
        try {
            System.out.println("Login: " + Thread.currentThread().getId());
            test = extent.createTest("Verify user can login into the IE portal");
            type(loginEmail, config.ie_username());
            type(loginPassword, config.ie_password());
            click(loginButton);
            waitElementVisible(By.id("Home"));
            getScreenshot();
            Assert.assertTrue(base.getDriver().findElement(By.id("Home")).isDisplayed());
            test.pass("User logged in successfully");
            base.saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User login failed");
            base.saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User login failed");
            base.saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(enabled = true, priority = 2, groups = {"smoke", "regression"})
    public void logout() throws IOException {
        try {
            test = extent.createTest("AN-LO-01: Verify user can Logout");
            SoftAssert softAssert = new SoftAssert();
            Thread.sleep(1000);
            hover(sideBar);
            Thread.sleep(1000);
            waitElementVisible(getVersion);
            String version = getText(getVersion);
            if (config.url().equals("qa")) {
                softAssert.assertTrue(version.startsWith("Version: 6."), "Version not in side menu bar");
            }
            if (config.url().equals("uat")) {
                softAssert.assertTrue(version.startsWith("Version: 8."), "Version not in side menu bar");
            }
            getScreenshot();
            click(logoutButton);
            waitElementVisible(loginEmail);
            softAssert.assertTrue(getDriver().findElement(loginEmail).isDisplayed());
            softAssert.assertAll();
            test.pass("User logout successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User logout failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User logout failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(enabled = true, priority = 3, groups = {"smoke", "regression"})
    public void verifyLogout() throws IOException {
        try {
            test = extent.createTest("AN-LO-02: Verify clicking on back button does not take user back to application after logging out");
            getDriver().navigate().back();
            Assert.assertEquals(getDriver().getCurrentUrl(), url_loginPage);
            test.pass("User remained logout successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User remained logout failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User remained logout failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(enabled = true, priority = 4, groups = {"regression"})
    public void loginNegativeCases() throws IOException {

        for (int i = 0; i < lstLogin.size(); i++) {
            try {
                test = extent.createTest(lstLogin.get(i).testCaseName);
                clear(loginEmail);
                type(loginEmail, lstLogin.get(i).email);
                clear(loginPassword);
                type(loginPassword, lstLogin.get(i).password);
                click(loginButton);
                waitElementVisible(alertMessage);
                Thread.sleep(700);
                getScreenshot();
                Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Sorry, we don’t recognize these credentials.", "Alert message did not appear");
                click(alertClose);
                test.pass("User receives an alert message 'Sorry, we don't recognize these credentials.'");
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Login test failed");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Login test failed");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }


    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }
}