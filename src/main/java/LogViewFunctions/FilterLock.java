package LogViewFunctions;

import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Methods.waitElementInvisible;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.gherkin.model.Scenario;
import Config.BaseTest;

import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.*;
import static PageObjects.SSMPage.clickDeepSerotypingTab;
import static PageObjects.SSMPage.clickMPNTab;
import static PageObjects.SitesLogPage.clickFarmTab;
import static PageObjects.SitesLogPage.clickHouseTab;

import PageObjects.BasePage;


public class FilterLock {

    @Test(enabled = true)
    public static void Lock(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
        BaseTest driver = new BaseTest();

        waitElementVisible(By.cssSelector("#" + tablename + " th .log-header .mb-0"));
        Thread.sleep(1000);

        if (size(By.cssSelector("#" + tablename + " #save-filters.d-none")) != 0) {
            click(By.cssSelector("#" + tablename + " #remove-filters"));
            click(By.cssSelector("#" + tablename + " #reset-all-filters"));
            waitElementInvisible(loading_cursor);
        }

        int totalNumberofColumns = size(By.cssSelector("#" + tablename + " th .log-header .mb-0")) + skipColumns;  //get number of columns in table and skip the audit,checkbox and action column if exists
        //     System.out.println("Col: "+totalNumberofColumns);


        for (int i = 1; i <= totalNumberofColumns; i++) {
            try {
                SoftAssert softAssert = new SoftAssert();

                String recordBefore = getText(By.cssSelector("#" + tablename + " #" + ResultsCount)); //get total count of records in table

                if (size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .log-header__filter-icon")) != 0) {  //check if filter icon is applied with the column

                    WebElement column = driver.getDriver().findElement(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterIcon));
                    WebElement columnName = driver.getDriver().findElement(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .log-header .mb-0"));
                    String columnNameText = columnName.getText();

                    test = extent.createTest("AN-Lock-" + i + ": Verify user can apply filter on " + columnName.getText() + " column");
                    steps = test.createNode(Scenario.class, Steps);

                    WebElement filter_scroll = column;
                    ((JavascriptExecutor) driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); //scroll to filter
                    getScreenshot();

                    click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterIcon));  //click on filter icon to expand it
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(1000);

                    if (size(alertMessage)!= 0) {
                        softAssert.assertTrue(false, "Exception E129 occured on opening filter popup");
                        test.fail("Exception occured on clicking apply button");
                    }

                   else if (size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.footerCount)) != 0) {   //check if 'Showing x-x results' if displayed
                        if (getText(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.footerCount)).equals("Showing 1 - 1 Results") || getText(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.footerCount)).equals("Showing 0 - 0 Results")) {  //check if there is more than 1 checkbox to verify if filter functionality is working
                            test.skip("Values not enough to test lock filter functionality");
                            driver.saveResult(ITestResult.SKIP, null);
                            click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterIcon));
                        } else {
                            if (size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .data-log-radio")) != 0) {  //check if toggle is selected or not
                                click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .filter-popup__action--wildcard"));  //click on toggle button if not selected
                                waitElementInvisible(loading_cursor);
                            }

                            String getFilterItemName = getText(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") li:nth-child(3) label"));   //get name of filter item to be selected
                            click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") li:nth-child(3) label"));  //click on checkbox
                            Thread.sleep(500);
                            steps.createNode("1. Select any filter and click on apply filter button");

                            click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterApplyButton));  //click on apply button
                            waitElementInvisible(loading_cursor);

                            if (size(alertMessage)!= 0) {
                                softAssert.assertTrue(false, "Exception occured on clicking apply button");
                                test.fail("Exception occured on clicking apply button");
                            }

                            steps.createNode("2. Click on lock button");
                            if (size(By.cssSelector("#" + tablename + " #" + LockFilter + ".d-none")) == 1) {
                                click(By.cssSelector("#" + tablename + " #" + UnlockFilter));   //unlock
                                Thread.sleep(1000);
                            }
                            click(By.cssSelector("#" + tablename + " #" + LockFilter));   //click on lock icon
                            waitElementInvisible(loading_cursor);
                            getScreenshot();
                            Thread.sleep(1000);

                            String recordsafterfilter = getText(By.cssSelector("#" + tablename + " #" + ResultsCount));  //get records after applying filter
                            softAssert.assertTrue(!recordsafterfilter.equals(0), "Result found count cannot be zero");
                            softAssert.assertNotEquals(recordsafterfilter, recordBefore, "Filter failed to apply");

                            if (size(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label.vertical-align-middle.ng-star-inserted")) == 1) {
                                List<WebElement> items = driver.getDriver().findElements(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label.vertical-align-middle.ng-star-inserted"));  //get list of items in table
                                for (int x = 0; x < items.size(); x++) {
                                    System.out.println("Name: "+columnName.getText());
                                    if (!columnName.getText().equals("W2 Cell Count") || !columnName.getText().equals("Sites") || !columnName.getText().equals("Target Pathogen")) {
                                       System.out.println("1");
                                        softAssert.assertTrue(items.get(x).getText().contains(getFilterItemName.trim()), "Expected " + getFilterItemName.trim() + " but found " + items.get(x).getText()+" for column "+columnName.getText());
                                    }
                                }
                            } else if (size(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label.vertical-align-middle")) == 1) {
                                List<WebElement> items = driver.getDriver().findElements(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label.vertical-align-middle"));  //get list of items in table
                                for (int x = 0; x < items.size(); x++) {
                                    if (!columnName.getText().equals("W2 Cell Count") || !columnName.getText().equals("Sites") || !columnName.getText().equals("Target Pathogen")) {
                                        System.out.println("2");
                                        softAssert.assertTrue(items.get(x).getText().contains(getFilterItemName.trim()), "Expected " + getFilterItemName.trim() + " but found " + items.get(x).getText()+" for column "+columnName.getText());
                                    }
                                }
                            } else {
                                List<WebElement> items = driver.getDriver().findElements(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label.vertical-align-middle.max-w-150px"));  //get list of items in table
                                for (int x = 0; x < items.size(); x++) {
                                    if (!columnName.getText().equals("W2 Cell Count") || !columnName.getText().equals("Sites") || !columnName.getText().equals("Target Pathogen")) {
                                        System.out.println("3");
                                        softAssert.assertTrue(items.get(x).getText().contains(getFilterItemName.trim()), "Expected " + getFilterItemName.trim() + " but found " + items.get(x).getText()+" for column "+columnName.getText());
                                    }
                                }
                            }

                            steps.createNode("3. Close " + name + " screen");
                            steps.createNode("4. Reopen " + name + " screen");

                            driver.getDriver().navigate().refresh();
                            waitElementInvisible(loading_cursor);
                            Thread.sleep(3000);
                            if (size(By.cssSelector("#" + tablename + " #" + ResultsCount)) != 1) {
                                driver.getDriver().navigate().refresh();
                                waitElementInvisible(loading_cursor);
                                Thread.sleep(3000);
                            }

                            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                            StackTraceElement element = stackTraceElements[2];
                            String methodName = element.getMethodName();

                            if (methodName.equals("LockFilterFarm")) {
                                clickFarmTab();
                                waitElementInvisible(loading_cursor);
                            }

                            if (methodName.equals("LockFilterHouse")) {
                                clickHouseTab();
                                waitElementInvisible(loading_cursor);
                            }

                            if (methodName.equals("LockFilterDeepSerotype")) {
                                clickDeepSerotypingTab();
                                waitElementInvisible(loading_cursor);
                            }

                            if (methodName.equals("LockFilterMPN")) {
                                clickMPNTab();
                                waitElementInvisible(loading_cursor);
                            }

                            if (size(alertMessage)!=0) {
                                softAssert.assertTrue(false, "Exception occured on revisiting the page after applying lock on filter");
                                test.fail("Exception occured on revisiting the page after applying lock on filter");
                           }

                            steps.createNode("5. Verify lock filter remains applied");
                            softAssert.assertEquals(getText(By.cssSelector("#" + tablename + " #" + ResultsCount)), recordsafterfilter, "Lock functionality failed for " + columnNameText);  //verify records remain save after refreshing the page
                            getScreenshot();
                            click(By.cssSelector("#" + tablename + " #" + UnlockFilter));  //unlock filter
                            waitElementInvisible(loading_cursor);
                            Thread.sleep(2000);
                            if (size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterClearButton)) == 1) {
                                click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterClearButton)); //clear applied filter
                                waitElementInvisible(loading_cursor);
                            } else {
                                test.fail("Clear Filter icon not displaying");
                            }
                        }
                    } else {
                        test.skip("Hierarchy filter cannot be tested with this method");    //there are few filter having heirarchy instead of checkbox; to skip them we used this condition
                        driver.saveResult(ITestResult.SKIP, null);
                        click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterIcon));
                    }

                    softAssert.assertAll();
                    test.pass("Lock functionality verified successfully");
                    driver.saveResult(ITestResult.SUCCESS, null);
                }
            } catch (AssertionError er) {
                test.fail("Column failed to Lock");
                driver.saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Column failed to Lock");
                driver.saveResult(ITestResult.FAILURE, new Exception(ex));
            }
        }
    }
}
