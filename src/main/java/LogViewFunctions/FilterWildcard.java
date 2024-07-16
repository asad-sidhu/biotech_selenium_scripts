package LogViewFunctions;

import static Config.BaseTest.saveResult;
import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static PageObjects.BasePage.*;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.ResultsCount;
import static PageObjects.BasePage.loading_cursor;
import static MiscFunctions.Methods.*;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;

import Config.BaseTest;

public class FilterWildcard {

    @Test(enabled = true)
    public static void Wildcard(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
        try {
            waitElementVisible(By.cssSelector("#" + tablename + " th .log-header .mb-0"));
            Thread.sleep(1000);

            if (size(By.cssSelector("#" + tablename + " #save-filters.d-none")) != 0) {
                click(By.cssSelector("#" + tablename + " #remove-filters"));
                click(By.cssSelector("#" + tablename + " #reset-all-filters"));
                waitElementInvisible(loading_cursor);
            }

            int totalNumberofColumns = size(By.cssSelector("#" + tablename + " th .log-header .mb-0")) + skipColumns;   //get total columns and skip irrelevant columns
            BaseTest driver = new BaseTest();

            for (int i = 1; i <= totalNumberofColumns; i++) {
                if (size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + filterIcon)) != 0) {     //check column has filter icon
                    WebElement columnName = driver.getDriver().findElement(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .log-header .mb-0"));  //store name of filter
                    test = extent.createTest("AN_Wildcard-" + i + ": Verify user can apply wildcard on " + columnName.getText() + " filter");   //create testcase in report of that filter
                    steps = test.createNode(Scenario.class, Steps);

                    String recordBefore = getText(By.cssSelector("#" + tablename + " #" + ResultsCount));   //get result count

                    if (recordBefore.equals("0")) {
                        getScreenshot();
                        test.skip("No record available on page on test wildcard functionality");
                        saveResult(ITestResult.SKIP, null);
                    } else {
                        SoftAssert softAssert = new SoftAssert();

                        WebElement filter_scroll = columnName;    //scroll to filter
                        ((JavascriptExecutor) driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
                        click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + filterIcon));   //open filter popup
                        waitElementInvisible(loading_cursor);
                        Thread.sleep(500);

                        if (size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + filterWildcardActionToggle)) != 0) {
                            if (driver.getDriver().findElement(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + filterWildcardActionToggle)).isDisplayed()) {  //check if filter has wildcard option
                                if (size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .data-log-radio")) == 0) {  //check if toggle is selected or not
                                    click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .filter-popup__action--wildcard"));  //click on toggle button if not selected
                                    waitElementInvisible(loading_cursor);
                                }

                                type(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + filterSearchInput), "a");  //type in wildcard search box
                                waitElementInvisible(loading_cursor);
                                Thread.sleep(800);
                                click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .filter-popup__footer--apply"));  //click on apply filter button
                                waitElementInvisible(loading_cursor);
                                Thread.sleep(800);
                                getScreenshot();

                                String getResultCount = getText(By.cssSelector("#" + tablename + " #" + ResultsCount));   //get results after apply wildcard filter
                                String recordAfter = getResultCount.replace(",", "");

                                if (!recordAfter.equals("0")) {
                                    int rows1 = size(By.cssSelector("#" + tablename + "#col-0"));  //get rows returned in log view

                                    Thread.sleep(800);
                                    for (int j = 0; j < rows1; j++) {
                                        int k = i - 1;
                                        int l = k - skipColumns;
                                        if (Integer.parseInt(recordAfter) > 0 && recordBefore != recordAfter) {
                                            String str = getText(By.cssSelector("#" + tablename + " #row-" + j + " #col-" + l + " label"));
                                            softAssert.assertTrue(str.startsWith("a") || str.startsWith("A"), "WildCard Starts With failed");
                                        }
                                    }

							/*
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") "+filterIcon));
							waitElementInvisible(loading_cursor);	
							Thread.sleep(1500);

							click(By.xpath("//*[text() = ' Ends With ']"));
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply"));
							waitElementInvisible(loading_cursor);
							Thread.sleep(800);
							getScreenshot();

							int rows2 = size(By.id("col-0"));
							for (int j = 0; j<rows2; j++) {
								int k = i-1;
								int l = k-skipColumns;
								if (Integer.parseInt(recordAfter) > 0 && recordBefore != recordAfter) {
								String str = getText(By.cssSelector("#"+tablename+" #row-"+j+" #col-"+l+" label"));
								softAssert.assertTrue(str.endsWith("h") || str.endsWith("H"), "WildCard Ends With failed");
								}
							}
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+")  "+filterIcon));
							waitElementInvisible(loading_cursor);	
							Thread.sleep(800);
							click(By.xpath("//*[text() = ' Contains ']"));
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply"));
							waitElementInvisible(loading_cursor);
							Thread.sleep(800);
							getScreenshot();
							int rows3 = size(By.id("col-0"));
							Thread.sleep(1000);
							for (int j = 0; j<rows3; j++) {
								int k = i-1;
								int l = k-skipColumns;
								if (Integer.parseInt(recordAfter) > 0 && recordBefore != recordAfter) {
								String str = getText(By.cssSelector("#"+tablename+" #row-"+j+" #col-"+l+" label"));
								softAssert.assertTrue(str.contains("h") || str.contains("H"), "WildCard Contains failed");
								}
							}
							
							*/
                                    //  softAssert.assertNotEquals(recordAfter, recordBefore);
                                }
                                click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .log-header__clear-filter span"));
                                waitElementInvisible(loading_cursor);
                            } else {
                                getScreenshot();
                                test.skip("Filter does not have wildcard option");
                                saveResult(ITestResult.SKIP, null);
                            }
                        }
                        Thread.sleep(700);
                        softAssert.assertAll();
                        test.pass("Wildcards tested successfully");
                        saveResult(ITestResult.SUCCESS, null);
                    }
                }
            }
        } catch (AssertionError er) {
            test.fail("Wildcards failed to test successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Wildcards failed to test successfully");
            saveResult(ITestResult.FAILURE, new Exception(ex));
        }
    }
}
