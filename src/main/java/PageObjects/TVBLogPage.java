package PageObjects;

import static MiscFunctions.Constants.url_reportManagement;
import static MiscFunctions.Constants.url_reportManagement;
import static PageObjects.BasePage.loading_cursor;
import org.openqa.selenium.By;
import org.testng.Assert;

import Config.BaseTest;
import MiscFunctions.Methods;

public class TVBLogPage {

    private static By tvbLogBox = By.cssSelector("img[alt='TVB Log' i]");
    private static By tvbLogTitle = By.cssSelector(".screen-title p.data-log");
    public static String tvbLogTable = "tvb-data-log";
    public static String tvbPNGFileName = "tvb Run Timeline - ";
    public static String tvbCSVFileName = "TVB Log - ";
    public static String tvbCSVAuditFileName = "TVB Audit Log - ";
    public static String tvbTemplateFileName = "TVB Template - ";

    public static void openTVBLogPage() {
        BaseTest driver = new BaseTest();
        driver.getDriver().get(url_reportManagement);
        Methods.waitElementInvisible(loading_cursor);
        Methods.click(tvbLogBox);
        Methods.waitElementInvisible(loading_cursor);
        Assert.assertTrue(Methods.getText(tvbLogTitle).equalsIgnoreCase("TVB Log"), "Title not matched");
    }
}
