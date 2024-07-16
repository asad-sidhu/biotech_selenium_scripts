package PageObjects;

import static LogViewFunctions.FilterLock.Lock;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.*;

import MiscFunctions.NavigateToScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Config.BaseTest;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class SitesLogPage {

    public static WebDriver driver;

    private static By sitesLogBox = By.cssSelector("img[alt='Sites Log']");
    private static By sitesLogTitle = By.id("Sites Log");

    public static String complexLogTable = "complex-data-log";
    public static String farmLogTable = "farm-data-log";
    public static String hatcheryLogTable = "hatchery-data-log";
    public static String houseLogTable = "house-data-log";

    public static By sitesLogTabComplex = By.cssSelector(".tabs-list li:nth-child(1)");
    public static By sitesLogTabFarm = By.cssSelector(".tabs-list li:nth-child(2)");
    public static By sitesLogTabHatchery = By.cssSelector(".tabs-list li:nth-child(3)");
    public static By sitesLogTabHouse = By.cssSelector(".tabs-list li:nth-child(4)");

    public static String sitesSiteIDCol = "0";
    public static String sitesSiteNameCol = "1";
    public static String sitesSiteTypeCol = "2";
    public static String sitesStreetAddressCol = "3";
    public static String sitesStateCol = "4";
    public static String sitesZipCodeCol = "5";
    public static String sitesCountryCol = "6";
    public static String sitesLatitudeCol = "7";
    public static String sitesLongitudeCol = "8";
    public static String sitesDateCreatedCol = "9";
    public static String sitesCreatedByCol = "10";
    public static String sitesFieldAccessCol = "11";

    public static String sitesAuditSiteIDCol = "0";
    public static String sitesAuditSiteNameCol = "0";
    public static String sitesAuditSiteTypeCol = "1";
    public static String sitesAuditStreetAddressCol = "2";
    public static String sitesAuditStateCol = "3";
    public static String sitesAuditZipCodeCol = "4";
    public static String sitesAuditCountryCol = "5";
    public static String sitesAuditLatitudeCol = "6";
    public static String sitesAuditLongitudeCol = "7";
    public static String sitesAuditDateCreatedCol = "8";
    public static String sitesAuditCreatedByCol = "9";

    public static String sitesShowFilter = "_show-filter";
    public static String sitesSortFilter = "sort-";
    public static String sitesApplyFilter = "_apply";
    public static String sitesClearFilter = "_clear-filter";

    public static String sitesSiteID = "siteId";
    public static String sitesSiteName = "siteName";
    public static String sitesSiteType = "siteType";
    public static String sitesStreetAddress = "streetAddress";
    public static String sitesState = "state";
    public static String sitesZipCode = "zipCode";
    public static String sitesCountry = "country";
    public static String sitesLatitude = "latitude";
    public static String sitesLongitude = "longitude";
    public static String sitesDateCreated = "createdDate";
    public static String sitesCreatedBy = "createdBy";

    public static String sitesTotalPages = "/html/body/app-root/div/app-Patho_2-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[15]/p";

    public static String sitesComplexTabCSVFileName = "Complex Log - ";
    public static String sitesFarmTabCSVFileName = "Farm Log - ";
    public static String sitesHatcheryTabCSVFileName = "Hatchery Log - ";
    public static String sitesHouseTabCSVFileName = "House Log - ";
    public static String sitesComplexTabCSVAuditFileName = "Complex Audit Log - ";
    public static String sitesFarmTabCSVAuditFileName = "Farm Audit Log - ";
    public static String sitesHatcheryTabCSVAuditFileName = "Hatchery Audit Log - ";
    public static String sitesHouseTabCSVAuditFileName = "House Audit Log - ";

    public static By fieldAccessIconComplexTab = By.id("edit-complex-field-access");
    public static By fieldAccessIconFarmTab = By.id("edit-farm-field-access");
    public static By fieldAccessIconHatcheryTab = By.id("edit-hatchery-field-access");
    public static By fieldAccessIconHouseTab = By.id("edit-house-field-access");
    public static By complexTabAuditIcon = By.cssSelector("#complex-data-log #audit-trial-0");
    public static By farmTabAuditIcon = By.cssSelector("#farm-data-log #audit-trial-0");
    public static By houseTabAuditIcon = By.cssSelector("#house-data-log #audit-trial-0");
    public static By auditChangedDateColumn = By.cssSelector("#audit-changed-date");
    public static By auditActionColumn = By.cssSelector("#audit-action");
    public static By auditChangedByColumn = By.cssSelector("#audit-changed-by");

    public SitesLogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void navigateSitesLogScreen() throws IOException, InterruptedException {
        test = extent.createTest("SL-AN-01: Verify user can navigate to Sites Log Screen");
        SitesLogPage.openSitesLogPage();
    }

    public static void openSitesLogPage() {
        try {
            BaseTest driver = new BaseTest();
            driver.getDriver().get(url_reportManagement);
            waitElementInvisible(loading_cursor);
            click(sitesLogBox);
            waitElementInvisible(loading_cursor);
            Thread.sleep(5000);
            Assert.assertEquals(getText(sitesLogTitle), "Sites Log");
            test.pass("Title is correct");
        } catch (AssertionError ex) {
            test.fail("Title not correct");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickComplexTab() {
        scroll(sitesLogTabComplex);
        click(sitesLogTabComplex);
    }

    public static void clickFarmTab() {
        scroll(sitesLogTabFarm);
        click(sitesLogTabFarm);
    }

    public static void clickHatcheryTab() {
        scroll(sitesLogTabHatchery);
        click(sitesLogTabHatchery);
    }

    public static void clickHouseTab() {
        scroll(sitesLogTabHouse);
        click(sitesLogTabHouse);
    }

    public static void checkAuditLog(String tablename) throws InterruptedException, IOException {
        try {
            List<WebElement> a = driver.findElements(By.cssSelector("#" + tablename + " tr:nth-child(1) td"));
            hover(By.cssSelector("#" + tablename + " #audit-trial-0 img"));
            Thread.sleep(1000);
            click(By.cssSelector("#" + tablename + " #audit-trial-0 img"));
            Thread.sleep(3000);
            List<WebElement> b = driver.findElements(By.cssSelector(".popup-body tr:nth-child(1) td"));
            click(popupCloseButton2);

            for (int i = 4; i < a.size() - 1; i++) {
                int j = i + 1;
                System.out.println(a.get(i).getText() + ">>>" + b.get(j).getText());
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(b.get(j).getText(), a.get(i).getText());
                softAssert.assertAll();
                test.pass("Audit data verified successfully");
                BaseTest.saveResult(ITestResult.SUCCESS, null);
            }
        } catch (AssertionError er) {
            test.fail("Audit data failed");
            BaseTest.saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Audit data failed");
            BaseTest.saveResult(ITestResult.FAILURE, ex);
        }
    }


}
	

