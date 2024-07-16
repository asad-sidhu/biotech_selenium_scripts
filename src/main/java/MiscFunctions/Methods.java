package MiscFunctions;

import static MiscFunctions.ExtentVariables.test;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import Config.BaseTest;
import PageObjects.BasePage;

public class Methods {

    BaseTest drive = new BaseTest();
    public WebDriverWait wait = new WebDriverWait(drive.getDriver(), Duration.ofSeconds(100, 1));

    public static void click(By locator) {
            waitElementClickable(locator);
            BaseTest drive = new BaseTest();
            drive.getDriver().findElement(locator).click();
            waitElementInvisible(BasePage.loading_cursor);
            ExtentVariables.test.log(Status.INFO, "Clicking on " + locator);
    }

    public static void type(By locator, String text) {
        waitElementClickable(locator);
        BaseTest drive = new BaseTest();
        drive.getDriver().findElement(locator).sendKeys(text);
        waitElementInvisible(BasePage.loading_cursor);
        ExtentVariables.test.log(Status.INFO, "Typing " + text + " in " + locator);
    }

    public static boolean isDisplayed(By locator) throws InterruptedException {
        Thread.sleep(1000);
        BaseTest drive = new BaseTest();
        boolean isDisplayed = drive.getDriver().findElement(locator).isDisplayed();
        waitElementInvisible(BasePage.loading_cursor);
        ExtentVariables.test.log(Status.INFO, "Verifying " + locator + " is Displayed ");
        return isDisplayed;
    }

    public static void enterKey(By locator) {
        waitElementVisible(locator);
        BaseTest drive = new BaseTest();
        drive.getDriver().findElement(locator).sendKeys(Keys.ENTER);
        ExtentVariables.test.log(Status.INFO, "Press enter key in " + locator);
    }

    public static String getText(By locator) {
        waitElementVisible(locator);
        BaseTest drive = new BaseTest();
        return drive.getDriver().findElement(locator).getText();
    }

    public static String getAttribute(By locator) {
        BaseTest drive = new BaseTest();
        return drive.getDriver().findElement(locator).getAttribute("value");
    }

    public static void clear(By locator) {
        waitElementVisible(locator);
        BaseTest drive = new BaseTest();
        drive.getDriver().findElement(locator).clear();
    }

    public static int size(By locator) {
        BaseTest drive = new BaseTest();
        int elementSize = drive.getDriver().findElements(locator).size();
        return elementSize;
    }

    public static void waitElementVisible(By locator) {
        Methods methods = new Methods();
        methods.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitElementInvisible(String locator) {
        Methods methods = new Methods();
        methods.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));
    }

    public static void waitElementClickable(By locator) {
        Methods methods = new Methods();
        methods.wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public static void scroll(By locator) {
        BaseTest drive = new BaseTest();
        WebElement scroll = drive.getDriver().findElement(locator);
        ((JavascriptExecutor) drive.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scroll);
    }

    public static void scrollToTopOfPage() {
        BaseTest drive = new BaseTest();
        JavascriptExecutor js = (JavascriptExecutor) drive.getDriver();
        js.executeScript("window.scrollTo(0, 0)");
    }

    public static void hover(By locator) {
        BaseTest drive = new BaseTest();
        WebElement hover = drive.getDriver().findElement(locator);
        Actions action = new Actions(drive.getDriver());
        action.moveToElement(hover).perform();
    }

    public static void getScreenshot() throws IOException {
        BaseTest base = new BaseTest();
        test.addScreenCaptureFromPath(base.get_Screenshot());
    }

    public static String getJsonValue(WebDriver driver, String jsonString, String key) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String script = String.format("return JSON.parse(arguments[0]).%s;", key);
        return (String) jsExecutor.executeScript(script, jsonString);
    }

    public static void unLockFilter(String tablename) {
        if (size(By.cssSelector("#" + tablename + " #save-filters.d-none")) != 0) {
            click(By.cssSelector("#" + tablename + " #remove-filters"));
            click(By.cssSelector("#" + tablename + " #reset-all-filters"));
            waitElementInvisible(loading_cursor);
        }
    }

}
