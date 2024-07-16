package LogViewFunctions;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;

import java.io.IOException;

import static Config.BaseTest.saveResult;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.ResultsCount;

public class SiteNameFilter {

    public static By collectionSiteNameShowFilter = By.id("site_id_show-filter");
    public static By footerCount = By.cssSelector("#sort-site_id .filter-popup__footer--count");
    public static By clickCheckbox = By.cssSelector("#sort-site_id tr:nth-child(1) td:nth-child(1) .custom-control.custom-checkbox");
    public static By clickApplyButton = By.cssSelector("#sort-site_id #list-title_apply");

    public static void SiteName() throws IOException {
        try {
            test = extent.createTest("AN-SiteName-57: Verify Site Name Filter Functionality");
            String recordsBefore = getText(By.id(ResultsCount));

            scroll(collectionSiteNameShowFilter);
            Thread.sleep(800);
            if (size(footerCount)!=0) {
               click(collectionSiteNameShowFilter);
                Thread.sleep(1000);
                click(clickCheckbox);
                Thread.sleep(1000);
                click(clickApplyButton);
                Thread.sleep(1000);
                Assert.assertNotEquals(getText(By.id(ResultsCount)), recordsBefore);
                test.pass("Site Name filter verified successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            }
            else {
                test.skip("No site assigned to the user");
            }
        } catch (AssertionError er) {
            test.fail("Site Name filter failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Site Name filter failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

}
