package LogViewFunctions;

import Config.BaseTest;
import MiscFunctions.ClickElement;
import PageObjects.ClostridiumLogPage;
import com.aventstack.extentreports.gherkin.model.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Config.BaseTest.saveResult;
import static MiscFunctions.DateUtil.dateMMDDYYYY1;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Methods.getAttribute;
import static PageObjects.BasePage.*;
import static PageObjects.ClostridiumLogPage.*;

public class ResultDateFilter {

    public static String clShowFilter = "_show-filter";
    public static String clSortFilter = "sort-";
    public static String clResultDate = "scanDateTime";
    public static String clToday = "#list-title_range-0";
    public static String clLast24Hours = "#list-title_range-1";
    public static String clLast7Days = "#list-title_range-2";
    public static String clLast30Days = "#list-title_range-3";

    public static void DateFilter() throws InterruptedException, IOException {
        try {
            test = extent.createTest("AN-Date-15: Verify lock filter functionality on date filter");
            SoftAssert softAssert = new SoftAssert();
            BaseTest driver = new BaseTest();
            String recordBefore = getText(By.id(ResultsCount));
            scroll(By.id(clResultDate + "" + clShowFilter));
            click(By.id("scanDateTime_show-filter"));
            Thread.sleep(2000);

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            WebElement selectLabel = driver.getDriver().findElement(By.xpath("//label[@id='list-title_date-selection']"));
            Actions actions = new Actions(driver.getDriver());
            actions.moveToElement(selectLabel, 1, 2).click().build().perform();
            Thread.sleep(2000);
            if (driver.getDriver().findElement(By.cssSelector(clToday)).isEnabled()) {
                actions.moveToElement(driver.getDriver().findElement(By.cssSelector(clToday))).click().perform();
                waitElementInvisible(loading_cursor);
                Thread.sleep(750);
                click(By.id(clResultDate + "" + clShowFilter));
                Thread.sleep(700);
                getScreenshot();
                String fromDateField = getAttribute(By.cssSelector("input[placeholder='Start Date']"));
                String toDateField = getAttribute(By.cssSelector("input[placeholder='End Date']"));
                System.out.println(fromDateField + ">>" + toDateField);
                softAssert.assertEquals(fromDateField, dateMMDDYYYY1, "Failed for today");
                softAssert.assertEquals(toDateField, dateMMDDYYYY1, "Failed for today");
                String recordAfter = driver.getDriver().findElement(By.id(ResultsCount)).getText();
                softAssert.assertNotEquals(recordBefore, recordAfter);
            }

            actions.moveToElement(selectLabel, 1, 2).click().build().perform();
            if (driver.getDriver().findElement(By.cssSelector(clLast24Hours)).isEnabled()) {
                actions.moveToElement(driver.getDriver().findElement(By.cssSelector(clLast24Hours))).click().perform();
                waitElementInvisible(loading_cursor);
                Thread.sleep(750);
                click(By.id(clResultDate + "" + clShowFilter));
                Thread.sleep(700);
                getScreenshot();
                String fromDateField = getAttribute(By.cssSelector("input[placeholder='Start Date']"));
                String toDateField = getAttribute(By.cssSelector("input[placeholder='End Date']"));
                System.out.println(fromDateField + ">>" + toDateField);
                LocalDate yesterday = today.minusDays(1);
                String formattedYesterday = yesterday.format(formatter);
                softAssert.assertEquals(fromDateField, formattedYesterday, "Failed for last 24 hours");
                softAssert.assertEquals(toDateField, formattedYesterday, "Failed for last 24 hours");
                String recordAfter = driver.getDriver().findElement(By.id(ResultsCount)).getText();
                softAssert.assertNotEquals(recordBefore, recordAfter);
            }
            actions.moveToElement(selectLabel, 1, 2).click().build().perform();
            if (driver.getDriver().findElement(By.cssSelector(clLast7Days)).isEnabled()) {
                actions.moveToElement(driver.getDriver().findElement(By.cssSelector(clLast7Days))).click().perform();
                waitElementInvisible(loading_cursor);
                Thread.sleep(750);
                click(By.id(clResultDate + "" + clShowFilter));
                Thread.sleep(700);
                getScreenshot();
                String fromDateField = getAttribute(By.cssSelector("input[placeholder='Start Date']"));
                String toDateField = getAttribute(By.cssSelector("input[placeholder='End Date']"));
                System.out.println(fromDateField + ">>" + toDateField);
                LocalDate last7Days = today.minusDays(7);
                String formattedLast7Days = last7Days.format(formatter);
                softAssert.assertEquals(fromDateField, formattedLast7Days, "Failed for last 7 days");
                softAssert.assertEquals(toDateField, dateMMDDYYYY1, "Failed for last 7 days");
                String recordAfter = driver.getDriver().findElement(By.id(ResultsCount)).getText();
                softAssert.assertNotEquals(recordBefore, recordAfter);
            }
            actions.moveToElement(selectLabel, 1, 2).click().build().perform();
            if (driver.getDriver().findElement(By.cssSelector(clLast30Days)).isEnabled()) {
                actions.moveToElement(driver.getDriver().findElement(By.cssSelector(clLast30Days))).click().perform();
                waitElementInvisible(loading_cursor);
                Thread.sleep(750);
                click(By.id(clResultDate + "" + clShowFilter));
                Thread.sleep(700);
                getScreenshot();
                String fromDateField = getAttribute(By.cssSelector("input[placeholder='Start Date']"));
                String toDateField = getAttribute(By.cssSelector("input[placeholder='End Date']"));
                System.out.println(fromDateField + ">>" + toDateField);
                LocalDate last30Days = today.minusDays(30);
                String formattedLast30Days = last30Days.format(formatter);
                softAssert.assertEquals(fromDateField, formattedLast30Days, "Failed for last 30 days");
                softAssert.assertEquals(toDateField, dateMMDDYYYY1, "Failed for last 7 days");
                String recordAfter = driver.getDriver().findElement(By.id(ResultsCount)).getText();
                softAssert.assertNotEquals(recordBefore, recordAfter);
            }

            softAssert.assertAll();
            test.pass("Filter locked functionality verified successfully on date filter");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Filer lock functionality failed on date filter");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Filer lock functionality failed on date filter");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public static void DateFilterLock() throws InterruptedException, IOException {
        try {
            test = extent.createTest("AN-Date-16: Verify lock filter functionality on date filter");
            BaseTest driver = new BaseTest();
            driver.getDriver().navigate().refresh();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            waitElementInvisible(loading_cursor);
            waitElementVisible(By.id("sort-scanDateTime"));
            SoftAssert softAssert = new SoftAssert();
            scroll(By.id(clSortFilter + "" + clResultDate));
            driver.getDriver().findElement(By.id(clResultDate + "" + clShowFilter)).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            ClickElement.clickByCss(driver.getDriver(), ".fa-chevron-down");
            Thread.sleep(1000);

            driver.getDriver().findElement(By.cssSelector(clLast30Days)).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            click(By.id(LockFilter));
            waitElementInvisible(loading_cursor);

            String recordsbeforefilter = driver.getDriver().findElement(By.id(ResultsCount)).getText();
            Thread.sleep(500);
            getScreenshot();
            driver.getDriver().navigate().refresh();
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            String recordsafterfilter = driver.getDriver().findElement(By.id(ResultsCount)).getText();

            softAssert.assertEquals(recordsafterfilter, recordsbeforefilter);

            waitElementInvisible(loading_cursor);
            driver.getDriver().findElement(By.id(UnlockFilter)).click();
            driver.getDriver().findElement(By.id(ResetFilters)).click();
            waitElementInvisible(loading_cursor);

            softAssert.assertAll();
            test.pass("Filter locked functionality verified successfully on date filter");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Filer lock functionality failed on date filter");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Filer lock functionality failed on date filter");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

}
