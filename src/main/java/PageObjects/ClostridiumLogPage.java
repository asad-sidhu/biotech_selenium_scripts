package PageObjects;

import Config.BaseTest;
import MiscFunctions.Methods;
import org.openqa.selenium.By;
import org.testng.Assert;

import static MiscFunctions.Constants.url_reportManagement;
import static PageObjects.BasePage.loading_cursor;

public class ClostridiumLogPage {

        private static By ClostridiumLogBox = By.cssSelector("img[alt='Clostridium Log']");
        private static By ClostridiumLogTitle = By.id("Clostridium Log");
        public static String ClostridiumLogTable = "clostridium-data-log";
//        public static String clShowFilter = "_show-filter";
//        public static String clSortFilter = "sort-";
//        public static String clResultDate = "scanDateTime";

        public static String clPNGFileName = "clostridium Run Timeline - ";
        public static String clCSVFileName = "clostridium Log - ";
        public static String clCSVAuditFileName = "clostridium Audit Log - ";
        public static String clSampleMetaData = "Clostridium Template - ";

        public static void openClostridiumLogPage() {
            BaseTest driver = new BaseTest();
            driver.getDriver().get(url_reportManagement);
            Methods.waitElementInvisible(loading_cursor);
            Methods.click(ClostridiumLogBox);
            Methods.waitElementInvisible(loading_cursor);
            Assert.assertEquals(Methods.getText(ClostridiumLogTitle), "Clostridium Log");
        }

    }
    
