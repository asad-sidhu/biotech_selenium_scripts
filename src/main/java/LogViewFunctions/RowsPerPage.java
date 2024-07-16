package LogViewFunctions;

import static MiscFunctions.ExtentVariables.Results;
import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.ResultsCount;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;

import Config.BaseTest;
import MiscFunctions.ClickElement;
import MiscFunctions.Methods;
import PageObjects.BasePage;

public class RowsPerPage {

	@Test (description="Test Case: Test Table Rows") 
	public static void RowsPerPage_() throws InterruptedException, IOException {
		BaseTest driver = new BaseTest();
		int[] tableRows = {100, 250, 500};

		for (int i=0; i<tableRows.length; i++) {
			try {
				test = extent.createTest("Verify user can apply "+tableRows[i]+" rows per page");
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				waitElementInvisible(loading_cursor);
				Methods.waitElementVisible(By.id(ResultsCount));
				Thread.sleep(500);	

				Actions actions = new Actions(driver.getDriver());
				SoftAssert softAssert = new SoftAssert();
				steps.createNode("1. Select "+tableRows[i]+" from dropdown below");
				String results1 = driver.getDriver().findElement(By.id(ResultsCount)).getText();

				if (NumberFormat.getNumberInstance(Locale.US).parse(results1).intValue() > tableRows[i]) {
					WebElement expandFilter = driver.getDriver().findElement(By.id("rows"));
					actions.moveToElement(expandFilter).click().perform();				
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					getScreenshot();
					int j = i+1;
					driver.getDriver().findElement(By.cssSelector("option:nth-child("+j+")")).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					List<WebElement> rows = driver.getDriver().findElements(By.cssSelector("tr td:nth-child(3)"));
					int count = rows.size();
					//	int new_count = count - 4;

					softAssert.assertEquals(count, tableRows[i]);
					softAssert.assertAll();
					test.pass(tableRows[i]+" displayed succcessfully");
					results.createNode(tableRows[i]+" displayed succcessfully");
					getScreenshot();
					driver.saveResult(ITestResult.SUCCESS, null);
				}
				else {
					softAssert.assertTrue(true, "Records are less then "+tableRows[i]);
					test.pass("Records are less then "+tableRows[i]);
					results.createNode("Rcords are less then "+tableRows[i]);
					getScreenshot();
					driver.saveResult(ITestResult.SUCCESS, null);	
				}

				test = extent.createTest("Verify "+tableRows[i]+" rows per page remained apply on moving to next page");
				steps.createNode("1. Select "+tableRows[i]+" from dropdown below");
				steps.createNode("2. Go to next page from pagination");
				steps.createNode("3. Verify that still "+tableRows[i]+" is selected");

				String results2 = driver.getDriver().findElement(By.id(ResultsCount)).getText();
				int sum = tableRows[i] + tableRows[i];

				if (NumberFormat.getNumberInstance(Locale.US).parse(results2).intValue() > sum) {

					ClickElement.clickById(driver.getDriver(), "next-page");

					Methods.waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(1000);
					List<WebElement> rows = driver.getDriver().findElements(By.cssSelector("tr td:nth-child(3)"));
					int count = rows.size();
					//		int new_count = count - 4;
					//System.out.println("ROW COUNT : "+new_count);
					softAssert.assertEquals(count, tableRows[i]);
					test.pass(tableRows[i]+"records displayed succcessfully on next page");
					results.createNode(tableRows[i]+"records displayed succcessfully on next page");
					getScreenshot();
					driver.saveResult(ITestResult.SUCCESS, null);	
				}

				else {
					softAssert.assertTrue(true, "Records are less then "+sum);
					test.skip("Records are less then "+sum);
					results.createNode("Records are less then "+sum);
					getScreenshot();
					driver.saveResult(ITestResult.SKIP, null);
				}
				softAssert.assertAll();
			}

			catch(AssertionError er) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				driver.saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				driver.saveResult(ITestResult.FAILURE, ex);
			}	
		}
	}


	@Test (description="Test Case: Test Table Rows") 
	public static void RowsPerPage1(String tablename) throws InterruptedException, IOException {
		BaseTest driver = new BaseTest();
		int[] tableRows = {100, 250, 500};
		for (int i=0; i<tableRows.length; i++) {
			try {
				test = extent.createTest("Verify user can apply "+tableRows[i]+" rows per page");
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				waitElementInvisible(loading_cursor);
				Methods.waitElementVisible(By.cssSelector("#"+tablename+" #"+ResultsCount));
				Thread.sleep(500);	

				Actions actions = new Actions(driver.getDriver());
				SoftAssert softAssert = new SoftAssert();
				steps.createNode("1. Select "+tableRows[i]+" from dropdown below");
				String results1 = driver.getDriver().findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();

				if (NumberFormat.getNumberInstance(Locale.US).parse(results1).intValue() > tableRows[i]) {
					WebElement expandFilter = driver.getDriver().findElement(By.cssSelector("#"+tablename+" #rows"));
					actions.moveToElement(expandFilter).click().perform();				
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					getScreenshot();
					int j = i+1;
					driver.getDriver().findElement(By.cssSelector("#"+tablename+" option:nth-child("+j+")")).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					List<WebElement> rows = driver.getDriver().findElements(By.cssSelector("#"+tablename+" tr td:nth-child(3)"));
					int count = rows.size();
					//	int new_count = count - 4;

					softAssert.assertEquals(count, tableRows[i]);
					softAssert.assertAll();
					test.pass(tableRows[i]+" displayed succcessfully");
					results.createNode(tableRows[i]+" displayed succcessfully");
					getScreenshot();
					driver.saveResult(ITestResult.SUCCESS, null);
				}
				else {
					softAssert.assertTrue(true, "Records are less then "+tableRows[i]);
					test.skip("Records are less then "+tableRows[i]);
					results.createNode("Records are less then "+tableRows[i]);
					getScreenshot();
					driver.saveResult(ITestResult.SKIP, null);	
				}

				test = extent.createTest("Verify "+tableRows[i]+" rows per page remained apply on moving to next page");
				steps.createNode("1. Select "+tableRows[i]+" from dropdown below");
				steps.createNode("2. Go to next page from pagination");
				steps.createNode("3. Verify that still "+tableRows[i]+" is selected");

				String results2 = driver.getDriver().findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();
				int sum = tableRows[i] + tableRows[i];

				if (NumberFormat.getNumberInstance(Locale.US).parse(results2).intValue() > sum) {

					ClickElement.clickByCss(driver.getDriver(), "#"+tablename+" #next-page");
					waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(1000);
					List<WebElement> rows = driver.getDriver().findElements(By.cssSelector("#"+tablename+" tr td:nth-child(3)"));
					int count = rows.size();
					//		int new_count = count - 4;
					//System.out.println("ROW COUNT : "+new_count);
					softAssert.assertEquals(count, tableRows[i]);
					test.pass(tableRows[i]+"records displayed succcessfully on next page");
					results.createNode(tableRows[i]+"records displayed succcessfully on next page");
					getScreenshot();
					driver.saveResult(ITestResult.SUCCESS, null);	
				}

				else {
					softAssert.assertTrue(true, "Records are less then "+sum);
					test.skip("Records are less then "+sum);
					results.createNode("Records are less then "+sum);
					getScreenshot();
					driver.saveResult(ITestResult.SKIP, null);	
				}
				softAssert.assertAll();
			}

			catch(AssertionError er) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				driver.saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				driver.saveResult(ITestResult.FAILURE, ex);
			}	
		}
	}

}
