package Config;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import MiscFunctions.DB_Config_DW;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;

import MiscFunctions.Constants;
import MiscFunctions.ExtentVariables;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    private static ThreadLocal<ChromeDriver> driver = new ThreadLocal<>();


    @BeforeClass(groups = {"smoke", "regression"})
    public void config() {

        WebDriverManager.chromedriver().setup();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
        prefs.put("download.prompt_for_download", false);
        prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-infobars");
        options.addArguments("disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//     options.addArguments("--headless");
//     options.addArguments("--disable-gpu");
//     options.addArguments("--disable-extensions");
//     options.addArguments("--disable-dev-shm-usage");
//     options.addArguments("--no-sandbox");
        driver.set(new ChromeDriver(options));
        BaseTest driver = new BaseTest();

//     if (ConfigFactory.create(ReadPropertyFile.class).runmode().equalsIgnoreCase("remote")) {
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setBrowserName("CHROME");
//     // cap.setBrowserName(BrowserType.CHROME);
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
//     }

    //    driver.getDriver().manage().window().maximize();
        driver.getDriver().manage().window().setSize(new Dimension(1920, 1080));
        driver.getDriver().get(Constants.url_loginPage);

//        ExtentVariables.spark.config().setDocumentTitle("Project123 Test Report");
//        ExtentVariables.spark.config().setTheme(Theme.DARK);
//        ExtentVariables.extent = new ExtentReports();
//        ExtentVariables.extent.attachReporter(ExtentVariables.spark);

    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public static void saveResult(int testResult, Exception e) throws IOException {
        BaseTest base = new BaseTest();
        ITestResult objResult = Reporter.getCurrentTestResult();
        if (testResult == ITestResult.SUCCESS) {
            objResult.setStatus(ITestResult.SUCCESS);
            ExtentVariables.test.log(Status.PASS, "Test Case Passed");
        } else if (testResult == ITestResult.FAILURE) {
            objResult.setStatus(ITestResult.FAILURE);
            objResult.setThrowable(e);
            ExtentVariables.test.log(Status.FAIL, "Test Case Failed");
            ExtentVariables.test.log(Status.FAIL, "Issue -> " + e);
            ExtentVariables.test.addScreenCaptureFromPath(base.get_Screenshot());
        } else if (testResult == ITestResult.SKIP) {
            ExtentVariables.test.log(Status.SKIP, "Test Case Skipped ");
            Markup m = MarkupHelper.createLabel("Skipped", ExtentColor.YELLOW);
            ExtentVariables.test.skip(m);
            ExtentVariables.test.addScreenCaptureFromPath(base.get_Screenshot());
        }
    }

    public static void saveResultNoScreenshot(int testResult, Exception e) throws IOException {
        BaseTest base = new BaseTest();
        ITestResult objResult = Reporter.getCurrentTestResult();
        if (testResult == ITestResult.SUCCESS) {
            objResult.setStatus(ITestResult.SUCCESS);
            ExtentVariables.test.log(Status.PASS, "Test Case Passed");
        } else if (testResult == ITestResult.FAILURE) {
            objResult.setStatus(ITestResult.FAILURE);
            objResult.setThrowable(e);
            ExtentVariables.test.log(Status.FAIL, "Test Case Failed");
            ExtentVariables.test.log(Status.FAIL, "Issue -> " + e);
        } else if (testResult == ITestResult.SKIP) {
            ExtentVariables.test.log(Status.SKIP, "Test Case Skipped ");
            Markup m = MarkupHelper.createLabel("Skipped", ExtentColor.YELLOW);
            ExtentVariables.test.skip(m);

        }
    }

    public String get_Screenshot() throws IOException {
        String dateName = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(new Date());
        BaseTest drive = new BaseTest();
        TakesScreenshot ts = (TakesScreenshot) drive.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = Constants.reportDirectory + Constants.screenshotDirectory + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return "." + Constants.screenshotDirectory + dateName + ".png";
    }

//    public static void zoomOut() {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.body.style.zoom='80%'");
//    }


    @AfterClass(groups = {"smoke", "regression"})
    public void tearDown() throws Exception {
        getDriver().quit();
        DB_Config_DW.tearDown();
    }
}