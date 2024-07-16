package LogViewFunctions;

import static MiscFunctions.ExtentVariables.PreConditions;
import static MiscFunctions.ExtentVariables.Results;
import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.preconditions;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.ResultsCount;
import static PageObjects.BasePage.firstPagePagination;
import static PageObjects.BasePage.lastPagePagination;
import static PageObjects.BasePage.loading_cursor;
import static PageObjects.BasePage.nextPagePagination;
import static PageObjects.BasePage.previousPagePagination;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;

import Config.BaseTest;
import MiscFunctions.ExtentVariables;

public class Pagination {

	@Test 
	public static void Pagination(String tablename, String name) throws InterruptedException, IOException {
		BaseTest driver = new BaseTest();
		for (int i=0; i<=3;i++) {
			try {
				String[] paginationButtons = {lastPagePagination, previousPagePagination, firstPagePagination, nextPagePagination};
				test = extent.createTest("AN-Pagination-"+name+"-"+i+": Verify pagination functionality on clicking "+paginationButtons[i]);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
 
				SoftAssert softAssert = new SoftAssert();
				String recordBefore = getText(By.cssSelector("#"+tablename+" #"+ResultsCount));   //get result count
				getScreenshot();

				String removeComma = recordBefore.replace(",", "");
				double x = Double.parseDouble(removeComma);
				double y = 100;
				double divide = Math.ceil(Math.abs(x/y));
				final int totalPages = (int)divide;
				waitElementInvisible(loading_cursor);
				String results = getText(By.cssSelector("#"+tablename+" #"+ResultsCount));   //get result count

				waitElementInvisible(loading_cursor);	
				steps.createNode("1. Verify pagination exists");

				if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() != 0) {
					Assert.assertTrue(size(By.cssSelector("#" + tablename + " #activePageNumber")) != 0, "Pagination not displaying");
				}

				if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > 100) {
					driver.getDriver().findElement(By.cssSelector("#"+tablename+" #"+paginationButtons[i])).click();
					waitElementInvisible(loading_cursor);
					String pageCount =	driver.getDriver().findElement(By.cssSelector("#"+tablename+" #activePageNumber")).getText();

					if (driver.getDriver().findElements(By.id("message")).size() !=0) {
						Thread.sleep(500);
						getScreenshot();	
						softAssert.fail("An error alert message displayed");
					}	

					if (paginationButtons[i].equals(lastPagePagination)) {
						steps.createNode("1. Click on '>>' icon in pagination");
						softAssert.assertEquals(pageCount, totalPages+"/"+totalPages);
					}

					if (paginationButtons[i].equals(previousPagePagination)) {	
						steps.createNode("1. Click on '<' icon in pagination");
						softAssert.assertEquals(pageCount, (totalPages-1)+"/"+totalPages);
					}

					if (paginationButtons[i].equals(firstPagePagination)) {	
						steps.createNode("1. Click on '<<' icon in pagination");
						softAssert.assertEquals(pageCount, 1+"/"+totalPages);
					}

					if (paginationButtons[i].equals(nextPagePagination)) {	
						steps.createNode("1. Click on '>' icon in pagination");
						softAssert.assertEquals(pageCount, 2+"/"+totalPages);
					}

					softAssert.assertAll();
					test.pass("Navigated to next page successfully");
					ExtentVariables.results.createNode("Navigated to next page successfully");
					getScreenshot();
					driver.saveResult(ITestResult.SUCCESS, null);
				}
				else {
					Assert.assertTrue(true, "Records are less then 100; pagination cannot be tested");
					test.skip("Records are less then 100; pagination cannot be tested");
					ExtentVariables.results.createNode("Records are less then 100; pagination cannot be tested");
					getScreenshot();
					driver.saveResult(ITestResult.SKIP, null);	
				}
			}
			catch(AssertionError er) {
				test.fail("Failed to get desired results on clicking button");
				results.createNode("Failed to get desired results on clicking button");
				driver.saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Failed to get desired results on clicking button");
				results.createNode("Failed to get desired results on clicking button");
				driver.saveResult(ITestResult.FAILURE, ex);
			}
		}
	}
}
