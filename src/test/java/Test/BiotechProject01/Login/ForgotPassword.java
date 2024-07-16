package Test.BiotechProject01.Login;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.ReadPropertyFile;
import MiscFunctions.*;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import PageObjects.LoginPage;

import static ExtentReports.ExtentReport.initReport;
import static PageObjects.BasePage.*;
import static PageObjects.ForgotPasswordPage.*;
import static MiscFunctions.ExtentVariables.*;
import static Models.ForgotPasswordModel.*;
import static MiscFunctions.Methods.*;


public class ForgotPassword extends BaseTest {

    public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);

    @BeforeTest
    public void extent() throws MalformedURLException {
        initReport("Forgot_Password");
        DB_Config_DB.test();
    }

    @Test(enabled = false, priority = 1)
    public void DeleteEmail() throws InterruptedException, IOException {
        test = extent.createTest("AN-FP-00: Delete all emails from gmail");

        ((JavascriptExecutor) getDriver()).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));

        getDriver().get(Constants.url_gmailLogin);
        type(gmailEmail, forgotPassword_email);
        Thread.sleep(1500);
        enterKey(gmailEmail);
        Thread.sleep(2500);

        type(gmailPassword, forgotPassword_password);
        Thread.sleep(1500);
        enterKey(gmailPassword);
        Thread.sleep(3000);

        if (size(gmailSecurityCheck) != 0) {
            click(gmailSecurityCheck);
            Thread.sleep(3000);
            type(gmailSecurityEmail, forgotPasswordSecurityEmail);
            enterKey(gmailSecurityEmail);

            if (size(gmailNotNow) != 0) {
                click(gmailNotNow);
            }
        }

        if (size(By.xpath("//*[@id=\":1y\"]/div[1]/span")) != 0) {
            click(By.xpath("//*[@id=\":1y\"]/div[1]/span"));
        } else if (size(By.xpath("//*[@id=\":2k\"]")) != 0) {
            click(By.xpath("//*[@id=\":2k\"]"));
        }
        Thread.sleep(1500);
        if (size(By.cssSelector("div[data-tooltip='Delete']")) != 0) {
            getDriver().findElement(By.cssSelector("div[data-tooltip='Delete']")).click();
        }
        Thread.sleep(1500);

        ArrayList<String> tabs2 = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs2.get(1));
        getDriver().close();
        getDriver().switchTo().window(tabs2.get(0));

    }


    @Test(enabled = false, priority = 2)
    public void SendEmailLink() throws InterruptedException, IOException {
        test = extent.createTest("AN-FP-01: Send forgot password ");
        waitElementClickable(By.id("forgot-password-1"));
        click(By.id("forgot-password-1"));

        for (int i = 0; i < lstFpEmail.size(); i++) {
            try {
                test = extent.createTest(lstFpTestCase.get(i));
                waitElementVisible(LoginPage.loginEmail);
                Thread.sleep(1000);
                clear(LoginPage.loginEmail);
                type(LoginPage.loginEmail, lstFpEmail.get(i));
                Thread.sleep(1000);
                getScreenshot();
                click(forgotPasswordButton);

                waitElementVisible(alertMessage);
                String actual = getDriver().findElement(alertMessage).getText();
                String expected = lstFpAlertMessages.get(i);

                Assert.assertEquals(actual, expected);
                test.pass(lstFpPassMessage.get(i));
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail(lstFpFailMessage.get(i));
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail(lstFpFailMessage.get(i));
                saveResult(ITestResult.FAILURE, ex);
            }
            Thread.sleep(1000);
            click(alertClose);
        }
    }

    @Test(enabled = false, priority = 3)
    public void VerifyEmailReceived() throws InterruptedException, IOException {
        try {
            test = extent.createTest("AN-FP-03: Verify user receives reset password link");

            getDriver().get(Constants.url_gmailLogin);
			/*
			type(By.xpath(gmailEmail), forgotPassword_email);
			Thread.sleep(1500);
			enterKey(By.xpath(gmailEmail));
			
			type(By.xpath(gmailPassword), forgotPassword_password);
			Thread.sleep(1500);
			enterKey(By.xpath(gmailPassword));
			Thread.sleep(3000);
			
			if (size(gmailSecurityCheck) != 0) { 
				click(gmailSecurityCheck);
				Thread.sleep(3000);
				type(gmailSecurityEmail, forgotPasswordSecurityEmail);
				enterKey(gmailSecurityEmail);
				
				if (size(gmailNotNow) != 0) {
					click(gmailNotNow);
				}	
			}


 */
            waitElementVisible(By.xpath("//*[@class='yW']/span"));
            getScreenshot();
            Thread.sleep(3000);
            List<WebElement> a = getDriver().findElements(By.xpath("//*[@class='yW']/span"));
            Thread.sleep(1000);
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i).getText().equals("BiotechProject01.org.dev") || a.get(i).getText().equals("support")) {
                    Thread.sleep(1000);
                    a.get(i).click();
                }
            }

            Thread.sleep(3000);
            //	Assert.assertTrue(getDriver().findElement(By.xpath(("//*[text()='Reset Password']"))).isDisplayed());
            getDriver().findElement(By.xpath(("//*[text()='Reset Password']"))).click();

            test.pass("Email with reset password link received successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Did not receive an email");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Did not receive an email");
            saveResult(ITestResult.FAILURE, ex);
        }

        Thread.sleep(1500);
        getDriver().findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")).click();
//
        Thread.sleep(2000);
        String currentTabHandle = getDriver().getWindowHandle();
        String newTabHandle = getDriver().getWindowHandles()
                .stream()
                .filter(handle -> !handle.equals(currentTabHandle))
                .findFirst()
                .get();
        getDriver().switchTo().window(newTabHandle);
        Thread.sleep(2000);

    }


    @Test(enabled = true, priority = 4)
    public void VerifyForgotPasswordUsingLink() throws IOException {
        try {
            test = extent.createTest("AN-FP-04: Verify user can reset password and login with new credentials");
            click(By.id("forgot-password-1"));
            clear(LoginPage.loginEmail);
            type(LoginPage.loginEmail, forgotPassword_email);
            getScreenshot();
            click(forgotPasswordButton);
            waitElementVisible(alertMessage);
            String actual = getDriver().findElement(alertMessage).getText();
            String expected = "Please check your e-mail for instructions.";
            Assert.assertEquals(actual, expected);
            ResultSet getLink = DB_Config_DB.getStmt().executeQuery("select userGUID from dbo.[User] where userEmail='" + forgotPassword_email + "'");
            String resetPasswordLink = null;
            while (getLink.next()) {
                resetPasswordLink = getLink.getString("userGUID");
                System.out.println("Link: " + resetPasswordLink);
            }

            getDriver().get(config.url() + "/auth/reset-password?param=" + resetPasswordLink);

            waitElementVisible(By.id("passwordId"));
            Thread.sleep(1000);
            getDriver().findElement(By.id(("passwordId"))).sendKeys(forgotPassword_resetPassword);
            getDriver().findElement(By.id(("rePassordId"))).sendKeys(forgotPassword_resetPassword);
            getDriver().findElement(By.xpath(("/html/body/app-root/div/app-reset-password/div/div[3]/form/button"))).click();
            Thread.sleep(1500);

            if (getDriver().findElements(alertMessage).size() != 0) {
                click(alertClose);
            }

            waitElementVisible(By.id("email"));
            Thread.sleep(2000);
            type(By.id("email"), forgotPassword_email);
            clear(By.id("pwd"));
            type(By.id("pwd"),forgotPassword_resetPassword);
            getScreenshot();
            click(By.id("btn-sign-in"));
            Thread.sleep(1500);

            if (size(By.cssSelector("div button.footer__btn-main")) != 0) {
                click(By.cssSelector("div button.footer__btn-main"));
            }

            waitElementVisible(By.id("BiotechProject01 Intelligence Engine"));
            Thread.sleep(2000);
            Assert.assertTrue(getDriver().findElement(By.id("open-profile")).isDisplayed());
            test.pass("User successfully logged into the account with new credentials");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User not able to log into the account with new credentials");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User not able to log into the account with new credentials");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @AfterTest
    public static void endreport() {
        extent.flush();
    }
}

