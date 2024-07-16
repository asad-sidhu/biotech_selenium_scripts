package LogViewFunctions;

import Config.BaseTest;
import MiscFunctions.DownloadFileCheck;
import PageObjects.BasePage;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static Config.BaseTest.saveResult;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.*;

public class VerifyColumnNames {

    public static void VerifyAllColumns(String tableName, String[] columnNamesExpected) throws IOException {
        try {
            BaseTest driver = new BaseTest();
            SoftAssert softAssert = new SoftAssert();

            if (size(By.cssSelector("#"+tableName+ " #"+BasePage.FieldAccess)) != 0) {
                click(By.cssSelector("#" + tableName + " #" + BasePage.FieldAccess));
                waitElementInvisible(loading_cursor);
                click(popupResetButton);
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
            }

            List<WebElement> headerCells = driver.getDriver().findElements(By.cssSelector("#" + tableName + " th .log-header:nth-child(2) label"));
            List<String> columnNames = new ArrayList<String>();
            for (WebElement headerCell : headerCells) {
                columnNames.add(headerCell.getText());
            }
            for (int i = 0; i < columnNamesExpected.length; i++) {
                if (!columnNamesExpected[i].equals("")) {
                    test = extent.createTest("AN-VerifyColumns: Verify that " + columnNamesExpected[i] + " column is displayed in the table");
                    System.out.println(columnNames.get(i) + " ---> " + columnNamesExpected[i]);
                    softAssert.assertEquals(columnNames.get(i), columnNamesExpected[i]);
                }
            }

            if (size(By.cssSelector("#"+tableName+ " #"+BasePage.FieldAccess)) != 0) {
                click(By.cssSelector("#" + tableName + " #" + BasePage.FieldAccess));
                List<WebElement> popupCells = driver.getDriver().findElements(By.cssSelector(".popup-card td:nth-child(3) label"));
                List<String> columnNamesFieldAccess = new ArrayList<String>();
                for (WebElement headerCell : popupCells) {
                    columnNamesFieldAccess.add(headerCell.getText());
                }

                for (int j = 0; j < columnNamesExpected.length; j++) {
                    if (!columnNamesExpected[j].equals("") && !columnNamesExpected[j].equals("Product Image")) {
                        test = extent.createTest("AN-VerifyColumns: Verify that " + columnNamesExpected[j] + " column is displayed in the field access popup");
                        System.out.println(columnNames.get(j) + " ---> " + columnNamesExpected[j]);
                        softAssert.assertEquals(columnNames.get(j), columnNamesExpected[j]);
                    }
                }
            }
            if (size(popupCloseButton) !=0) {
                click(popupCloseButton);
            }
            if (size(By.id("close-gen-modal"))!=0) {
                click(By.id("close-gen-modal"));
            }
                softAssert.assertAll();
                test.pass("Columns are displayed in the table successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Columns are not displayed in the table");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Columns are not displayed in the table");
            saveResult(ITestResult.FAILURE, ex);
        }
    }
}