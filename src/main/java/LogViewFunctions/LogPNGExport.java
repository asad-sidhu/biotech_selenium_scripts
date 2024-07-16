package LogViewFunctions;

import Config.BaseTest;
import MiscFunctions.DownloadFileCheck;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Config.BaseTest.saveResult;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.loading_cursor;

public class LogPNGExport {

    public static void PNGExport(String elementID, String pngFilename) throws InterruptedException, IOException {
        try {
            test = extent.createTest("AN-PNG-215: Verify user can download PNG file");
            BaseTest driver = new BaseTest();
            driver.getDriver().findElement(By.id("sampleId_show-filter")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            driver.getDriver().findElement(By.cssSelector("#sort-sampleId li:nth-child(3) label")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            driver.getDriver().findElement(By.id("sampleId_apply")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            getScreenshot();
            hover(By.cssSelector(".run-timeline-bar-chart__download"));
            waitElementClickable(By.cssSelector(elementID));
            WebElement clickDownload = driver.getDriver().findElement(By.cssSelector(elementID));
            Actions actions = new Actions(driver.getDriver());
            actions.moveToElement(clickDownload).click().perform();
            waitElementInvisible(loading_cursor);

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
            Date date1 = new Date();
            String date= dateFormat.format(date1);
            Thread.sleep(10000);
            File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "png");
            String filename= newfile.getName();
            Assert.assertEquals(filename, pngFilename+date+".png");
            test.pass("PNG downloaded successfully");
            saveResult(ITestResult.SUCCESS, null);
        }
        catch(AssertionError er) {
            test.fail("PNG failed to download");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex) {
            test.fail("PNG failed to download");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public static void PNGExportEnterococcus(String elementID, String pngFilename) throws IOException {
        try {
            test = extent.createTest("AN-PNG-215: Verify user can download PNG file");
            BaseTest driver = new BaseTest();
            click(By.id("customer_sample_id_show-filter"));
            Thread.sleep(1000);
            click(By.cssSelector("#sort-customer_sample_id li:nth-child(3) label"));
            Thread.sleep(1000);
            click(By.id("customer_sample_id_apply"));
            Thread.sleep(1000);

            getScreenshot();
            hover(By.cssSelector(".run-timeline-bar-chart__download"));
            waitElementClickable(By.cssSelector(elementID));
            WebElement clickDownload = driver.getDriver().findElement(By.cssSelector(elementID));
            Actions actions = new Actions(driver.getDriver());
            actions.moveToElement(clickDownload).click().perform();
            waitElementInvisible(loading_cursor);

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
            Date date1 = new Date();
            String date= dateFormat.format(date1);
            Thread.sleep(10000);
            File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "png");
            String filename= newfile.getName();
            Assert.assertEquals(filename, pngFilename+date+".png");
            test.pass("PNG downloaded successfully");
            saveResult(ITestResult.SUCCESS, null);
        }
        catch(AssertionError er) {
            test.fail("PNG failed to download");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex) {
            test.fail("PNG failed to download");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

}
