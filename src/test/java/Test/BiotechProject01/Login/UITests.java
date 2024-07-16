//package Test.BiotechProject01.Login;
//
//import com.applitools.eyes.BatchInfo;
//import com.applitools.eyes.RectangleSize;
//import com.applitools.eyes.TestResultsSummary;
//import com.applitools.eyes.selenium.BrowserType;
//import com.applitools.eyes.selenium.Configuration;
//import com.applitools.eyes.selenium.Eyes;
//import com.applitools.eyes.selenium.fluent.Target;
//import com.applitools.eyes.visualgrid.model.DeviceName;
//import com.applitools.eyes.visualgrid.model.ScreenOrientation;
//import com.applitools.eyes.visualgrid.services.VisualGridRunner;
//
//import MiscFunctions.DateUtil;
//import MiscFunctions.ExtentVariables;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
///**
// * Unit test for simple App.
// */
//public class UITests {
//	public static String projectPath;
//	public static void main(String[] args) {
//		// Create a new chrome web driver
//	  	projectPath = System.getProperty("user.dir");
//		System.setProperty("webdriver.chrome.driver", projectPath+"/ChromeDriver/chromedriver.exe");
//		WebDriver webDriver = new ChromeDriver();
//
//		// Create a runner with concurrency of 1
//		VisualGridRunner runner = new VisualGridRunner(1);
//
//		// Create Eyes object with the runner, meaning it'll be a Visual Grid eyes.
//		Eyes eyes = new Eyes(runner);
//
//		setUp(eyes);
//
//		try {
//			// ⭐️ Note to see visual bugs, run the test using the above URL for the 1st run.
//			// but then change the above URL to https://demo.applitools.com/index_v2.html
//			// (for the 2nd run)
//			ultraFastTest(webDriver, eyes);
//
//		} finally {
//			tearDown(webDriver, runner);
//		}
//
//	}
//
//	public static void setUp(Eyes eyes) {
//
//		Configuration config = new Configuration();
//		config.setApiKey("A0Qy042gX10cRrq971zTqlObLtrOSGpixyOUYClFQmzo110");
//
//		// create a new batch info instance and set it to the configuration
//		config.setBatch(new BatchInfo("BiotechProject01 UI Tests"+DateUtil.date));
//		eyes.setHostOS("Windows 10"); //
//		// Add browsers with different viewports
//		config.addBrowser(1440, 1214, BrowserType.CHROME);
////		config.addBrowser(700, 500, BrowserType.FIREFOX);
////		config.addBrowser(1600, 1200, BrowserType.IE_11);
////		config.addBrowser(1024, 768, BrowserType.EDGE_CHROMIUM);
////		config.addBrowser(800, 600, BrowserType.SAFARI);
//
//		// Add mobile emulation devices in Portrait mode
//		config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
////		config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);
//
//		// Set the configuration object to eyes
//		eyes.setConfiguration(config);
//
//	}
//
//	public static void ultraFastTest(WebDriver webDriver, Eyes eyes) {
//
//		try {
//			eyes.setHostOS("Windows 10");
//		//	webDriver.get(Constants.url);
//
//			eyes.open(webDriver, "BiotechProject01 IE", "BiotechProject01 UI Tests", new RectangleSize(1440, 1214));
//
//			eyes.check(Target.window().fully().withName("Login page"));
//
//
//
//
//			webDriver.findElement(By.id("forgot-password-1")).click();
//
//			eyes.check(Target.window().fully().withName("FP page"));
//
//			eyes.closeAsync();
//
//		} finally  {
//			eyes.abortAsync();
//		}
//
//	}
//
//	private static void tearDown(WebDriver webDriver, VisualGridRunner runner) {
//
//		webDriver.quit();
//		TestResultsSummary allTestResults = runner.getAllTestResults(false);
//		System.out.println(allTestResults);
//	}
//
//}
