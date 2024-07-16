package PageObjects;

import Config.BaseTest;
import MiscFunctions.Methods;
import org.openqa.selenium.By;
import org.testng.Assert;

import static MiscFunctions.Constants.url_reportManagement;
import static PageObjects.BasePage.loading_cursor;

public class EnterococcusLogPage {

    private static By enterococcusLogBox = By.cssSelector("img[alt='Enterococcus Cecorum Log']");
    private static By enterococcusLogTitle = By.id("Enterococcus Cecorum Log");
    public static String enterococcusLogTable = "Patho_1-data-log";
    public static String enteroPNGFileName = "Enterococcus Cecorum Run Timeline - ";
    public static String enteroCSVFileName = "Enterococcus Log - ";

    public static void openEnterococcusLogPage() {
        BaseTest driver = new BaseTest();
        driver.getDriver().get(url_reportManagement);
        Methods.waitElementInvisible(loading_cursor);
        Methods.click(enterococcusLogBox);
        Methods.waitElementInvisible(loading_cursor);
        Assert.assertEquals(Methods.getText(enterococcusLogTitle), "Enterococcus Cecorum Log");
    }

}

