package LogViewFunctions;

import Config.BaseTest;
import PageObjects.BasePage;
import com.aventstack.extentreports.gherkin.model.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static Config.BaseTest.saveResult;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.*;
import static PageObjects.BasePage.loading_cursor;
import static PageObjects.InterventionManagementPage.*;

public class FieldAccess {



//    public static String convertToTitleCase(String input) {
//        if (input == null || input.isEmpty()) {
//            return input;
//        }
//
//        String[] words = input.toLowerCase().split(" ");
//        StringBuilder result = new StringBuilder();
//
//        for (String word : words) {
//            if (!word.isEmpty()) {
//                String firstLetter = word.substring(0, 1).toUpperCase();
//                String remainingLetters = word.substring(1);
//                result.append(firstLetter).append(remainingLetters).append(" ");
//            }
//        }
//
//        return result.toString().trim();
//    }

    @Test(enabled = true)
    public static void FieldAccessFunctionality(String tableName) throws InterruptedException, IOException {
        test = extent.createTest("Verify Field Access Functionality");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            BaseTest driver = new BaseTest();
            SoftAssert softAssert = new SoftAssert();
            By fieldAccessIcon = By.cssSelector("#"+tableName+" #edit-field-access");
            if (tableName == "complex-data-log" ||tableName == "farm-data-log" || tableName == "house-data-log" || tableName == "hatchery-data-log" ) {
                String siteType = tableName.substring(0, tableName.length() - 9);
                fieldAccessIcon = By.cssSelector("#"+tableName+" #edit-"+siteType+"-field-access");
            }
            steps.createNode("Verify tooltip for Field Access Column");
            String tooltipText = driver.getDriver().findElement(fieldAccessIcon).getAttribute("title");
            softAssert.assertTrue(tooltipText.equals("Field Access"), "Tooltip for field access icon failed");

            steps.createNode("Verify test case that Field Access column is Clickable or not");
            if (driver.getDriver().findElement(fieldAccessIcon).isEnabled()) {
                softAssert.assertTrue(true, "Field Access Icon is clickable");
            } else {
                softAssert.assertTrue(false, "Field access Icon is not clickable");
            }

            click(fieldAccessIcon);
            if (driver.getDriver().findElement(fieldOrderHeaderColumn).isDisplayed()) {
                softAssert.assertTrue(true, "Field Access column is displayed");
            } else {
                softAssert.assertTrue(false, "Field Access column is not displayed");
            }

            steps.createNode("Verify reset default functionality for field access column");
            Thread.sleep(1000);
            click(popupResetButton);
            Thread.sleep(1000);

            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            softAssert.assertEquals(getText(alertMessage), fieldAccessSuccessMessage);

            click(fieldAccessIcon);
            Thread.sleep(1000);

            List<WebElement> fieldNameColumn = driver.getDriver().findElements(fieldNameColumnElements);
            List<WebElement> fieldAccessElements = driver.getDriver().findElements(fieldAccessColumnElements);
            List<WebElement> fieldAccessElementsInput = driver.getDriver().findElements(fieldAccessColumnElementsInput);
            List<WebElement> enabledCheckboxes = new ArrayList<>();
            List<WebElement> enabledFieldAccessElements = new ArrayList<>();

            for (int i = 0; i < fieldAccessElementsInput.size(); i++) {
                String disabledAttr = fieldAccessElementsInput.get(i).getAttribute("disabled");
                if (disabledAttr == null || !disabledAttr.equals("true")) {
                    enabledCheckboxes.add(fieldAccessElements.get(i));
                    enabledFieldAccessElements.add(fieldNameColumn.get(i));
                }
            }

            List<String> fieldNamesToCheck = new ArrayList<>();

            for (int i = 0; i < enabledCheckboxes.size(); i++) {
                WebElement checkbox = enabledCheckboxes.get(i);
                WebElement fieldNameElement = enabledFieldAccessElements.get(i);
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(3));
                wait.until(ExpectedConditions.elementToBeClickable(checkbox));
                checkbox.click();
                fieldNamesToCheck.add(fieldNameElement.getText());
            }

            steps.createNode("Verify deselected column is hidden in the logview");

            Thread.sleep(1000);
            click(popupSaveButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.getDriver().findElement(alertMessage).getText(), fieldAccessSuccessMessage);

            for (String fieldNameToCheck : fieldNamesToCheck) {
                String modifiedFieldName;
                if (fieldNameToCheck.contains("NO.")) {
                    modifiedFieldName = fieldNameToCheck.replace("NO.", "number");
                } else {
                    modifiedFieldName = fieldNameToCheck;
                }
                //  String xpathExpression = "//div[@class='log-header']//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + modifiedFieldName.toLowerCase() + "')]";
                String xpathExpression = "//div[@class='log-header']//label[text() = ' "+modifiedFieldName+ " ']";
                By matchingElements = By.xpath("//*[@id='" + tableName + "']" + xpathExpression);

                try {
                    WebElement element = driver.getDriver().findElement(matchingElements);
                    softAssert.assertFalse(element.isDisplayed(), element + " column is not hidden, TC failed");
                }
                catch (NoSuchElementException e)
                {
                    System.out.println("TC passed: Column '" + fieldNameToCheck + "' is hidden.");
                }
            }


            /*if (size(auditIconFirstRow) == 1) {
                    try {
                        for (String fieldNameToCheck : fieldNamesToCheck) {
                            click(auditIconFirstRow);
                            waitElementInvisible(loading_cursor);
                            WebElement checkElement = driver.getDriver().findElement(By.xpath("//label[contains(text(),'" + fieldNameToCheck + "')]"));
                            softAssert.assertFalse(checkElement.isDisplayed(), fieldNameToCheck + " audit column is not hidden");
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("Column '" + fieldNamesToCheck + "' is hidden.");
                    }
                driver.getDriver().navigate().refresh();
            }
            else {
                System.out.println("Audit icon is not present");
            }*/

            click(fieldAccessIcon);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            List<WebElement> fieldNameColumns = driver.getDriver().findElements(fieldNameColumnElements);
            List<WebElement> fieldAccessElement = driver.getDriver().findElements(fieldAccessColumnElements);
            List<WebElement> fieldAccessElementInput = driver.getDriver().findElements(fieldAccessColumnElementsInput);
            List<WebElement> enabledCheckbox = new ArrayList<>();
            List<WebElement> enabledFieldAccessElement = new ArrayList<>();
            List<String> fieldNamesCheck = new ArrayList<>();

            for (int i = 0; i < fieldAccessElementInput.size(); i++) {
                String disabledAttr = fieldAccessElementInput.get(i).getAttribute("disabled");
                if (disabledAttr == null || !disabledAttr.equals("true")) {
                    enabledCheckbox.add(fieldAccessElement.get(i));
                    enabledFieldAccessElement.add(fieldNameColumns.get(i));
                }
            }

            for (int i = 0; i < enabledCheckbox.size(); i++) {
                WebElement checkbox = enabledCheckbox.get(i);
                WebElement fieldNameElement = enabledFieldAccessElement.get(i);
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(checkbox));
                checkbox.click();
                fieldNamesCheck.add(fieldNameElement.getText());
            }

            Thread.sleep(1000);
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.getDriver().findElement(alertMessage).getText(), fieldAccessSuccessMessage);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);
            steps.createNode("Verify selected column is shown on the table");

            for (String fieldNameLogViewCheck : fieldNamesCheck) {
                try {
                    String modifiedFieldName;
                    if (fieldNameLogViewCheck.contains("NO.")) {
                        modifiedFieldName = fieldNameLogViewCheck.replace("NO.", "Number");
                    } else {
                        modifiedFieldName = fieldNameLogViewCheck;
                    }
                    //  String xpathExpression = "//div[@class='log-header']//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + modifiedFieldName.toLowerCase() + "')]";
                    String xpathExpression = "//div[@class='log-header']//label[text() = ' "+modifiedFieldName+ " ']";
                    By matchingElement = By.xpath("//*[@id='" + tableName + "']" + xpathExpression);

                    WebElement element = driver.getDriver().findElement(matchingElement);
                    boolean isDisplayed = element.isDisplayed();

                    softAssert.assertTrue(isDisplayed, "The column is not shown on page, TC: failed");
                    System.out.println("TC passed: Column '" + modifiedFieldName + "' is shown.");
                } catch (NoSuchElementException e) {
                    System.out.println("TC failed: Column '" + fieldNameLogViewCheck + "' is not found on the page.");
                }
            }

           /* if (size(auditIconFirstRow) == 1) {
                    try {
                        for (String fieldNameToCheck : fieldNamesCheck) {
                            click(auditIconFirstRow);
                            waitElementInvisible(loading_cursor);
                            WebElement checkElement = driver.getDriver().findElement(By.xpath("//label[contains(text(),'" + fieldNameToCheck + "')]"));
                            if (checkElement.isDisplayed()) {
                                System.out.println("Audit Column '" + fieldNameToCheck + "' is found on the page.");
                            }
                        }
                    }catch (NoSuchElementException e) {
                        System.out.println("Audit Column '" + fieldNamesCheck + "' is not found on the page.");
                    }
                driver.getDriver().navigate().refresh();
            }
             else {
                System.out.println("Audit icon is not present");
            }
*/
            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Field Access Functionality passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Field Access Functionality failed");
            results.createNode("Field Access Functionality failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Field Access Functionality failed");
            results.createNode("Field Access Functionality failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true)
    public static void KeyColumnsCheck(String tableName, String[] keyColumnsExpected) throws InterruptedException, IOException {
        test = extent.createTest("Verify Key columns are disabled on Field Access Form");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        By fieldAccessIcon = By.cssSelector("#"+tableName+" #edit-field-access");
        if (tableName == "complex-data-log" ||tableName == "farm-data-log" || tableName == "house-data-log" || tableName == "hatchery-data-log" ) {
            String siteType = tableName.substring(0, tableName.length() - 9);
            fieldAccessIcon = By.cssSelector("#"+tableName+" #edit-"+siteType+"-field-access");
        }

        try {
            BaseTest driver = new BaseTest();
            SoftAssert softAssert = new SoftAssert();

            click(fieldAccessIcon);
            Thread.sleep(1500);

            List<WebElement> fieldAccessColumns = driver.getDriver().findElements(fieldNameColumnElements);
            List<WebElement> keyColumns = driver.getDriver().findElements(keyColumnElements);


            // Iterate over the expected key columns
            for (String expectedColumn : keyColumnsExpected) {
                boolean found = false;

                // Iterate over the field access columns
                for (int i = 0; i < fieldAccessColumns.size(); i++) {
                    WebElement fieldAccessColumn = fieldAccessColumns.get(i);
                    WebElement keyColumn = keyColumns.get(i);
                    String fieldName = fieldAccessColumn.getText().trim();

                    if (fieldName.equalsIgnoreCase(expectedColumn)) {
                        WebElement inputElement = keyColumn.findElement(By.tagName("input"));
                        boolean isDisabled = inputElement.getAttribute("disabled") != null;
                        softAssert.assertTrue(isDisabled, "The key column '" + expectedColumn + "' is not disabled");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    softAssert.fail("The key column '" + expectedColumn + "' is not found in the field access columns");
                }
            }

            click(popupSaveButton);
            waitElementInvisible(loading_cursor);

            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Field Access Functionality for key column check passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Field Access Functionality for key column check failed");
            results.createNode("Field Access Functionality for key column check failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Field Access Functionality for key column check failed");
            results.createNode("Field Access Functionality for key column check failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

//    @Test(enabled = true)
//    public static void s(By fieldAccessIcon, String[] keyColumnsExpected) throws InterruptedException, IOException {
//        test = extent.createTest("Verify Key columns are disabled on Field Access Form");
//        steps = test.createNode(Scenario.class, Steps);
//        results = test.createNode(Scenario.class, Results);
//        try {
//            BaseTest driver = new BaseTest();
//            SoftAssert softAssert = new SoftAssert();
//
//            click(fieldAccessIcon);
//            waitElementInvisible(loading_cursor);
//            Thread.sleep(1500);
//
//            List<WebElement> fieldAccessColumns = driver.getDriver().findElements(fieldNameColumnElements);
//            List<WebElement> keyColumns = driver.getDriver().findElements(keyColumnElements);
//
//
//            // Iterate over the expected key columns
//            for (String expectedColumn : keyColumnsExpected) {
//                boolean found = false;
//
//                // Iterate over the field access columns
//                for (int i = 0; i < fieldAccessColumns.size(); i++) {
//                    WebElement fieldAccessColumn = fieldAccessColumns.get(i);
//                    WebElement keyColumn = keyColumns.get(i);
//                    String fieldName = fieldAccessColumn.getText().trim();
//
//                    if (fieldName.equalsIgnoreCase(expectedColumn)) {
//                        WebElement inputElement = keyColumn.findElement(By.tagName("input"));
//                        boolean isDisabled = inputElement.getAttribute("disabled") != null;
//                        softAssert.assertTrue(isDisabled, "The key column '" + expectedColumn + "' is not disabled");
//                        found = true;
//                        break;
//                    }
//                }
//
//                if (!found) {
//                    softAssert.fail("The key column '" + expectedColumn + "' is not found in the field access columns");
//                }
//            }
//
//            click(popupSaveButton);
//            waitElementInvisible(loading_cursor);
//
//            softAssert.assertAll();
//            getScreenshot();
//            test.pass("Test case passed successfully");
//            results.createNode("Field Access Functionality for key column check passed successfully");
//            saveResult(ITestResult.SUCCESS, null);
//        } catch (AssertionError er) {
//            test.fail("Field Access Functionality for key column check failed");
//            results.createNode("Field Access Functionality for key column check failed");
//            saveResult(ITestResult.FAILURE, new Exception(er));
//        } catch (Exception ex) {
//            test.fail("Field Access Functionality for key column check failed");
//            results.createNode("Field Access Functionality for key column check failed");
//            saveResult(ITestResult.FAILURE, ex);
//        }
//    }


    public static void fieldLevelReset() throws InterruptedException, IOException {
        waitElementInvisible(BasePage.loading_cursor);
        Thread.sleep(1000);
        click(By.id(BasePage.FieldAccess));
        waitElementInvisible(BasePage.loading_cursor);
        Thread.sleep(1000);
        click(BasePage.popupResetButton);
        Thread.sleep(1000);
        click(BasePage.popupSaveButton);
        waitElementInvisible(BasePage.loading_cursor);
        Thread.sleep(1000);
    }

}
