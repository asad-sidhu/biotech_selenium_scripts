package PageObjects;

import MiscFunctions.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import Config.BaseTest;
import MiscFunctions.Methods;
import org.testng.ITestResult;

import java.io.IOException;

import static Config.BaseTest.saveResult;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.loading_cursor;

public class SSMPage {

    private static By ssmLogBox = By.cssSelector("img[alt='Patho_2 System Monitoring Log']");
    private static By ssmLogTitle = By.id("Patho_2 System Monitoring");
    private static By ssmLogDeepSerotypeTab = By.xpath("//*[@class = 'tabs-list']//*[text() = ' Deep Serotyping ']");
    private static By ssmLogMPNTab = By.xpath("//*[@class = 'tabs-list']//*[text() = ' MPN ']");
    public static String ssmCSVFileName = "Patho_2 Monitoring Log - ";
    public static String deepSerotypeCSVFileName = "Deep Serotyping  Log - ";
    public static String ssmMPNCSVFileName = "Patho_2 Monitoring MPN Log - ";
    public static String ssmLogTable = "Patho_2-data-log";
    public static String ssmDeepSerotypingLogTable = "ds-data-log";
    public static String ssmMPNLogTable = "Patho_2-mpn-log";


    public static void openSSMPage() throws IOException {
        try {
            BaseTest driver = new BaseTest();
            driver.getDriver().get(url_reportManagement);
            waitElementInvisible(loading_cursor);
            click(ssmLogBox);
            waitElementInvisible(loading_cursor);
            System.out.println("??? "+size(By.id("Patho_2 System Monitoring")));
            Assert.assertEquals(getText(ssmLogTitle), "Patho_2 System Monitoring");
            getScreenshot();
            test.pass("Navigated Successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Failed to navigate");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Failed to navigate");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public static void clickDeepSerotypingTab() throws InterruptedException {
    //    scroll(ssmLogDeepSerotypeTab);
        scrollToTopOfPage();
        Thread.sleep(2000);
        click(ssmLogDeepSerotypeTab);
    }

    public static void clickMPNTab() throws InterruptedException {
    //    scroll(ssmLogDeepSerotypeTab);
        scrollToTopOfPage();
        Thread.sleep(2000);
        click(ssmLogMPNTab);
    }
}
