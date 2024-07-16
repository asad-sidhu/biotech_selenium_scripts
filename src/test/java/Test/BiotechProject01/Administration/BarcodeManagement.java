package Test.BiotechProject01.Administration;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import Config.ReadPropertyFile;
import MiscFunctions.DateUtil;
import MiscFunctions.NavigateToScreen;
import PageObjects.UserManagementPage;
import Test.BiotechProject01.Login.LoginTest;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static PageObjects.BarCodeManagementPage.*;
import static PageObjects.BasePage.*;

public class BarcodeManagement extends BaseTest{

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
	//	spark = new ExtentSparkReporter("target/Reports/Administration_Barcode_Management"+DateUtil.date+".html");
	//	spark.config().setReportName("Barcode Management Test Report");
		initReport("Administration_Barcode_Management");
	}
	
	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
		NavigateToScreen.navigate(url_qrCodeManagement, "Barcode Management",  barcodeManagmentTitle);
	}
	
	@Test (description="Test Case: Assign Sites Appearance",enabled= true, priority = 1)
	public void AssignedSiteAppearance() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Barcode-01: Verify only those sites appear which are assigned to user");
			steps = test.createNode(Scenario.class, Steps);

			steps.createNode("1. Verify only those sites appear which are assigned to user");
			ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
			getDriver().get(url_qrCodeManagement);
			waitElementInvisible(loading_cursor);
			waitElementVisible(UserManagementPage.usercreateButton);
			Thread.sleep(1000);
			
			UserManagementPage.openEditUserPopup(config.ie_username());
			click(popupNextButton);
			Thread.sleep(500);
			click(popupNextButton);
			Thread.sleep(500);

			int collectionSitesSize = 0;
			for (int i=1;i<=getDriver().findElements(By.cssSelector(".site-tree-card")).size();i++) {
				if (!getDriver().findElement(By.xpath("//*[@id=\"select-sites\"]//div["+i+"]/div/p[2]")).getText().equals("Collection Sites: 0")) {
					collectionSitesSize = collectionSitesSize+1;

					if (i == size(By.cssSelector(".site-tree-card"))) {
						getDriver().get(url_qrCodeManagement);
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);
						int sitesCountBarcode = size(By.cssSelector(".site-icon"));

						Assert.assertEquals(sitesCountBarcode, collectionSitesSize);
						test.pass("Only those sites appeared  which are assigned to user successfully");
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);	
					}
				}
			}
		}
		catch(AssertionError er) {
			test.fail("Those sites did not appeared which are assigned to user");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Those sites did not appeared which are assigned to user");
			saveResult(ITestResult.FAILURE, ex);
		}
	}
	
	
	@Test (description="Test Case: Filter Functionality",enabled= true, priority = 2)
	public void FilterFunctionality() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Barcode-02/03/04/05/06: Verify filter, lock and reset button functionality");

			getDriver().get(url_qrCodeManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			if (getDriver().findElements(By.cssSelector("#save-filters.d-none")).size() == 1) {
				click(By.id(LockFilter));
			}

			click(By.id(barcodeManagementSiteId+""+ShowFilter));
			Thread.sleep(1000);
			if (size(By.cssSelector("tr:nth-child(3) td:nth-child(2) .fas.fa-check-double")) != 0) {
			getDriver().findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) .fas.fa-check-double")).click();
			getDriver().findElement(By.id("list-title_apply")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			int rows_beforelock = getDriver().findElements(By.cssSelector("tr td:nth-child(3) label")).size();
			
			click(By.id(LockFilter));
			getDriver().navigate().refresh();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			int rows_afterlock = getDriver().findElements(By.cssSelector("tr td:nth-child(3) label")).size();
			click(By.id(UnlockFilter));
			Assert.assertEquals(rows_afterlock, rows_beforelock);
			click(By.id(ResetFilters));
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			Assert.assertNotEquals(getDriver().findElements(By.cssSelector("tr td:nth-child(3) label")).size(), rows_afterlock);
			test.pass("Filter and lock functionality verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
			}
			else {
				test.skip("Filter and lock functionality cannot be assigned because only 1 site is assigned to the user");
				getScreenshot();
				saveResult(ITestResult.SKIP, null);	
			}
			
			
		}
		catch(AssertionError er) {
			test.fail("Filter and lock functionality failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Filter and lock functionality failed");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Error Opening Print Screen",enabled= true, priority = 3 )
	public void ErrorOpeningPrintScreen() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Barcode-07: Verify user cannot open print screen without selecting a site");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on Print button without selecting a site");
			steps.createNode("2. Verify error message appears");

			click(By.id("print-barcode"));
			waitElementVisible(alertMessage);
			Thread.sleep(800);
			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Please select a collection site");

			test.pass("Error message appeared successfully on clicking print icon");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Error message failed to appear on clicking print icon");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Error message failed to appear on clicking print icon");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Open print screen",enabled= true, priority = 4)
	public void OpenPrintScreen() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Barcode-08: Verify user can open print screen on clicking print icon after selecting a site");
			steps = test.createNode(Scenario.class, Steps);

			steps.createNode("1. Select a site and then click on Print button");

			click(By.cssSelector("tr:nth-child(1) td:nth-child(2) .vertical-align-middle"));
			Thread.sleep(1000);
			click(By.id("print-barcode"));
			Thread.sleep(3000);
			
			String parent=getDriver().getWindowHandle();
			Set<String>s=getDriver().getWindowHandles();
			Iterator<String> I1= s.iterator();

			steps.createNode("2. Verify print window opens with selected site barcodes");

			while(I1.hasNext())
			{
				String child_window=I1.next();

				if(!parent.equals(child_window))
				{
					getDriver().switchTo().window(child_window);
					//System.out.println(getDriver().switchTo().window(child_window).getTitle());
					Thread.sleep(2000);

					Robot r = new Robot();
					r.keyPress(KeyEvent.VK_ENTER);
				}
			}

			getDriver().switchTo().window(parent);

			test.pass("Print screen opened successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Print screen failed to open");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Print screen failed to open");
			saveResult(ITestResult.FAILURE, ex);
		}
	}
	
	
	@AfterTest
	public static void endreport() {
		extent.flush();
	}
	
}
