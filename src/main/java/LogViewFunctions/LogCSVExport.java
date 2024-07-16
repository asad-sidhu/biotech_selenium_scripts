package LogViewFunctions;

import static Config.BaseTest.saveResult;
import static MiscFunctions.Constants.url_loginPage;
import static MiscFunctions.ExtentVariables.PreConditions;
import static MiscFunctions.ExtentVariables.Results;
import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.fileDownloadPath;
import static MiscFunctions.ExtentVariables.preconditions;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.*;
import static PageObjects.SitesLogPage.clickFarmTab;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import MiscFunctions.ExtentVariables;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Config.BaseTest;
import MiscFunctions.ClickElement;
import MiscFunctions.DownloadFileCheck;

public class LogCSVExport {

    @SuppressWarnings({"unused", "resource"})
    @Test(enabled = true)
    public static void CSVExport(String name, String CSVFileName, String tablename, int filterNumber) throws InterruptedException, IOException {
        BaseTest driver = new BaseTest();
        try {
            test = extent.createTest("AN-CSVExport: Verify user can download CSV file and verify the records", "This test case will verify that user can download CSV file");
            preconditions = test.createNode(Scenario.class, PreConditions);
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            preconditions.createNode("1. Go to url " + url_loginPage);
            preconditions.createNode("2. Login with valid credentials; user navigates to home page");
            preconditions.createNode("3. Hover to sidebar to expand the menu");
            preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
            preconditions.createNode("5. Click on " + name + "; " + name + " screen opens");

            steps.createNode("1. Hover mouse towards table; Export file button becomes visible");
            steps.createNode("2. Click on the button; Dropdown cloud pop ups");
            steps.createNode("3. Verify the columns are same in table and CSV");
            SoftAssert softAssert = new SoftAssert();

            String resultsCount = getText(By.cssSelector("#"+tablename+" #"+ResultsCount));   //get result count

            if (NumberFormat.getNumberInstance(Locale.US).parse(resultsCount).intValue() > 1) {

                driver.getDriver().findElement(By.cssSelector("#" + tablename + " th:nth-child(" + filterNumber + ") .log-header__filter-icon")).click();
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                ClickElement.clickByCss(driver.getDriver(), "#" + tablename + " th:nth-child(" + filterNumber + ") li:nth-child(3) label");

                ClickElement.clickByCss(driver.getDriver(), "#" + tablename + " th:nth-child(" + filterNumber + ") .filter-popup__footer--apply");
                waitElementInvisible(loading_cursor);
                Thread.sleep(2000);
                String getRowText = driver.getDriver().findElement(By.id(ResultsCount)).getText();

                driver.getDriver().findElement(By.cssSelector("#" + tablename + " #csv-action img")).click();
                Thread.sleep(1000);
                getScreenshot();
                ClickElement.clickById(driver.getDriver(), "export-csv");
                waitElementInvisible(loading_cursor);
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
                Date date1 = new Date();
                String date = dateFormat.format(date1);
                Thread.sleep(1500);


                File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
                String filename = newfile.getName();
                softAssert.assertEquals(filename, CSVFileName + date + ".csv");
                test.pass("CSV file downloaded successfully");
                results.createNode("CSV file downloads successfully");
                saveResult(ITestResult.SUCCESS, null);

                File file = new File(fileDownloadPath + "\\" + filename);
                if (file.exists()) {
                    System.out.println("File Exists");
                }


                FileReader filereader = new FileReader(file);
                CSVReader reader = new CSVReader(filereader);
                reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
                StringBuffer buffer = new StringBuffer();
                String data[];

                ////////////////////////////////////Verify columns of Log View with Excel////////////////////////////////////////
                List<WebElement> headerCells = driver.getDriver().findElements(By.cssSelector("#" + tablename + " th .log-header:nth-child(2) label"));
                List<String> columnNamesLogView = new ArrayList<String>();
                for (WebElement headerCell : headerCells) {
                    columnNamesLogView.add(headerCell.getText());
                }

                List<String> columnNames = null;
                String file1 = fileDownloadPath + "\\" + filename;
                String line;
                try (BufferedReader br =
                             new BufferedReader(new FileReader(file1))) {
                    while ((line = br.readLine()) != null) {
                        System.out.println("1:" + line);
                        List<String> newList = Arrays.asList(line.split(","));
                        for (int i = 0; i < columnNamesLogView.size(); i++) {
                            if (!columnNamesLogView.get(i).equals("Product Image")) {
                                softAssert.assertEquals(newList.get(i).toLowerCase(), "\"" + columnNamesLogView.get(i) + "\"".toLowerCase());
                            }
                        }
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

                ////////////////////////////////////////////////////////////////////////////

                int columnsCountTotal = 0;
                int rowsCount = 1;
                while ((data = reader.readNext()) != null) {
                    for (int i = 0; i < data.length; i++) {
                        int rows = driver.getDriver().findElements(By.cssSelector("tr")).size();
                        if (rowsCount < rows) {
                            int totalColumns = driver.getDriver().findElements(By.cssSelector("#" + tablename + " tr:nth-child(1) td")).size() - 1;
                            int columnsCount = columnsCountTotal + 1;
                            if (driver.getDriver().findElements(By.cssSelector("#" + tablename + " tr:nth-child(" + rowsCount + ") td:nth-child(" + rowsCount + ")")).size() != 0 && columnsCount <= totalColumns) {
                                softAssert.assertEquals(data[i].trim(), driver.getDriver().findElement(By.cssSelector("#" + tablename + " tr:nth-child(" + rowsCount + ") td:nth-child(" + columnsCount + ")")).getText().trim(), "data not matched");
                            } else {
                                rowsCount = rowsCount + 1;
                                columnsCount = 0;
                                columnsCountTotal = 0;
                            }
                            columnsCountTotal++;
                        }
                    }
                    //System.out.println(" ");
                }

                Path path = Paths.get(fileDownloadPath + "\\" + filename);
                long lines = 0;
                try {
                    lines = Files.lines(path).count();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                long excludeHeader = lines - 1;
                String s = String.valueOf(excludeHeader);

                String str = getRowText;
                str = str.replace(",", "");
                softAssert.assertEquals(s, str);

                if (file.delete()) {
                    System.out.println("CSV file deleted");
                }


                softAssert.assertAll();
                test.pass("Column data exported successfully");
                results.createNode("Column data exported successfully");
                saveResult(ITestResult.SUCCESS, null);
            }
            else {
                test.skip("No rows to test");
                ExtentVariables.results.createNode("Records are 0, cannot be tested");
                getScreenshot();
                driver.saveResult(ITestResult.SKIP, null);
            }
        } catch (AssertionError er) {
            test.fail("CSV file failed to download");
            results.createNode("CSV file failed to download");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            System.out.println("Failure");
            test.fail("CSV file failed to download");
            results.createNode("CSV file failed to download");
            saveResult(ITestResult.FAILURE, ex);
        }
        Thread.sleep(1000);
    }


    @SuppressWarnings({"unused", "resource"})
    @Test(enabled = true)
    public static void CSVExport1(String name, String CSVFileName, String tablename, int filterNumber, int skipColumns) throws InterruptedException, IOException {
        BaseTest driver = new BaseTest();
        try {
            test = extent.createTest("AN-CSVExport: Verify user can download CSV file and verify the records");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            SoftAssert softAssert = new SoftAssert();

            click(By.cssSelector("#" + tablename + " th:nth-child(" + filterNumber + ") .log-header__filter-icon"));    //open filter at index 'filternumber'
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            ClickElement.clickByCss(driver.getDriver(), "#" + tablename + " th:nth-child(" + filterNumber + ") li:nth-child(3) label");    //select checkbox
            ClickElement.clickByCss(driver.getDriver(), "#" + tablename + " th:nth-child(" + filterNumber + ") .filter-popup__footer--apply");   //click on apply button
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            String getRowsCount = driver.getDriver().findElement(By.cssSelector("#" + tablename + " #" + ResultsCount)).getText();    //get results count after applying filter

            driver.getDriver().findElement(By.cssSelector("#" + tablename + " #csv-action img")).click();    //click on csv export popup icon
            Thread.sleep(1000);
            getScreenshot();
            ClickElement.clickById(driver.getDriver(), "export-csv");    //click on export csv icon
            waitElementInvisible(loading_cursor);

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");    //get date in this format (date concats in downloaded filename)
            Date date1 = new Date();
            String date = dateFormat.format(date1);
            Thread.sleep(6000);

            File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
            String filename = newfile.getName();                             //get the name of downloaded file

            softAssert.assertEquals(filename, CSVFileName + date + ".csv");     //compare the actual and expected file name
            test.pass("CSV file downloaded successfully");
            saveResult(ITestResult.SUCCESS, null);

            File file = new File(fileDownloadPath + "\\" + filename);
            System.out.println("Download Path: " + fileDownloadPath + "\\" + filename);
            if (file.exists()) {       //check if file exists
                System.out.println("File Exists");
            }


            FileReader filereader = new FileReader(file);   //read content of downloaded file
            CSVReader reader = new CSVReader(filereader);
            reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();    //skip header in excel file
            StringBuffer buffer = new StringBuffer();
            String data[];


            ////////////////////////////////////Verify columns of Log View with Excel////////////////////////////////////////
            List<WebElement> headerCells = driver.getDriver().findElements(By.cssSelector("#" + tablename + " th .log-header:nth-child(2) label"));
            List<String> columnNamesLogView = new ArrayList<String>();
            for (WebElement headerCell : headerCells) {
                if (!headerCell.getText().equals("Product Image")) {
                    columnNamesLogView.add(headerCell.getText());
                }
            }

            List<String> columnNames = null;
            String file1 = fileDownloadPath + "\\" + filename;
            String line;
            try (BufferedReader br =
                         new BufferedReader(new FileReader(file1))) {
                while ((line = br.readLine()) != null) {
                    System.out.println("1:" + line);
                    List<String> newList = Arrays.asList(line.split(","));
                    for (int i = 0; i < columnNamesLogView.size(); i++) {
                        //softAssert.assertEquals(newList.get(i).toLowerCase(), "\"" + columnNamesLogView.get(i) + "\"".toLowerCase());
                        softAssert.assertTrue(newList.get(i).equalsIgnoreCase("\"" + columnNamesLogView.get(i) + "\""));
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            ////////////////////////////////////////////////////////////////////////////

            int columnsCountTotal = 0;           //start from first column in IE
            int rowsCount = 1;               //start from second row (skip header row in IE)
            int csvColumns = 0;
            int columnsCount;

            while ((data = reader.readNext()) != null) {
                for (int i = 0; i < data.length; i++) {
                    int rows = driver.getDriver().findElements(By.cssSelector("tr")).size();   //get total rows in IE

                    if (rowsCount < rows) {
                        int totalColumns = driver.getDriver().findElements(By.cssSelector("#" + tablename + " tr:nth-child(1) td")).size() - 1;  //get total columns (- 1  is done for skipping last column (Actions))

                        columnsCount = columnsCountTotal + skipColumns;   //skip the initial columns (audit view and/or checkbox)

//                        System.out.println("1: "+columnsCount);
//                        System.out.println("2: "+columnsCountTotal);
//                        System.out.println("3: "+totalColumns);
//                        System.out.println("4: "+rowsCount);

                        if (size(By.cssSelector("#" + tablename + " tr:nth-child(" + rowsCount + ") td:nth-child(" + columnsCount + ")")) != 0 && columnsCount < totalColumns) {
                            System.out.println(data[csvColumns].trim() + " ------> " + driver.getDriver().findElement(By.cssSelector("#" + tablename + " tr:nth-child(" + rowsCount + ") td:nth-child(" + columnsCount + ")")).getText().trim());
                            softAssert.assertEquals(data[csvColumns].trim().replace("-", "").replace(",", "").replace("/", ""), driver.getDriver().findElement(By.cssSelector("#" + tablename + " tr:nth-child(" + rowsCount + ") td:nth-child(" + columnsCount + ")")).getText().trim().replace("/", "").replace(",", "").replace("-", ""), "data not matched");
                        } else {
                            rowsCount = rowsCount + 1;   //move to next row
                            columnsCount = 0;     //move to first column again
                            csvColumns = -1;
                            columnsCountTotal = -1;   //for Patho_2 and Patho_1 -1 else 0
                        }
                        columnsCountTotal++;
                        csvColumns++;
                    }
                }
            }

            Path path = Paths.get(fileDownloadPath + "\\" + filename);
            long lines = 0;
            try {
                lines = Files.lines(path).count();
            } catch (IOException e) {
                e.printStackTrace();
            }

            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            if (methodName.equals("exportCSVFunctionality")) {
                long excludeHeader = lines - 2;
                String getExcelFileRowsCount = String.valueOf(excludeHeader);
                String getIERowsCount = getRowsCount.replace(",", "");   //remove comma from results count
                softAssert.assertEquals(getExcelFileRowsCount, getIERowsCount, "Rows does not match in IE and downloaded Excel file");
            } else {
                long excludeHeader = lines - 1;
                String getExcelFileRowsCount = String.valueOf(excludeHeader);
                String getIERowsCount = getRowsCount.replace(",", "");   //remove comma from results count
                softAssert.assertEquals(getExcelFileRowsCount, getIERowsCount, "Rows does not match in IE and downloaded Excel file");
            }


            if (file.delete()) {
                System.out.println("CSV file deleted");
            }

            softAssert.assertAll();
            test.pass("Column data exported successfully");
            results.createNode("Column data exported successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("CSV file failed to download");
            results.createNode("CSV file failed to download");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            System.out.println("Failure");
            test.fail("CSV file failed to download");
            results.createNode("CSV file failed to download");
            saveResult(ITestResult.FAILURE, ex);
        }
        Thread.sleep(1000);
    }


    @Test(enabled = true)
    public static void CSVAuditExport(String name, String CSVAuditFileName, String tablename, boolean clickCheckbox) throws InterruptedException, IOException {
        BaseTest driver = new BaseTest();
        try {
            test = extent.createTest("AN-CSVExport: Verify user can download CSV Audit file for " + name + " and verify the records");
            SoftAssert softAssert = new SoftAssert();

            if (clickCheckbox == true) {
                ClickElement.clickByCss(driver.getDriver(), "#" + tablename + " tr:nth-child(1) td:nth-child(1) .data-log-checkbox .custom-checkbox");
                Thread.sleep(1000);
            }

            click(By.cssSelector("#" + tablename + " #csv-action img"));
            getScreenshot();
            Thread.sleep(1000);
            ClickElement.clickByCss(driver.getDriver(), "#" + tablename + " #export-audit-csv");
            waitElementInvisible(loading_cursor);
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
            Date date1 = new Date();
            String date = dateFormat.format(date1);
            Thread.sleep(5000);


            File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
            String filename = newfile.getName();
            softAssert.assertEquals(filename, CSVAuditFileName + date + ".csv");     //compare the actual and expected file name
            test.pass("CSV Audit file downloaded successfully");
            saveResult(ITestResult.SUCCESS, null);

            File file = new File(fileDownloadPath + "\\" + filename);
            if (file.exists()) {       //check if file exists
                System.out.println("File Exists");
            }

           // String resultsCount = getText(By.id(ResultsCount)).replace(",", "");
           // int count = Integer.parseInt(resultsCount) - 1;

            click(By.cssSelector("#" + tablename + " #audit-trial-0"));   //open audit trail popup
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            FileReader filereader = new FileReader(file);   //read content of downloaded file
            CSVReader reader = new CSVReader(filereader);
            reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();    //skip header in excel file
            StringBuffer buffer = new StringBuffer();
            String data[];

            /////////////////////Verify columns of logview with excel
            List<WebElement> headerCells = driver.getDriver().findElements(By.cssSelector("#table-header-audit th"));
            List<String> columnNamesLogView = new ArrayList<String>();
            for (WebElement headerCell : headerCells) {
                if (!headerCell.getText().equals("Product Image")) {
                    columnNamesLogView.add(headerCell.getText());
                }
            }

            List<String> columnNames = null;
            String file1 = fileDownloadPath + "\\" + filename;
            String line;
            try (BufferedReader br =
                         new BufferedReader(new FileReader(file1))) {
                while ((line = br.readLine()) != null) {
                    System.out.println("1:" + line);
                    List<String> newList = Arrays.asList(line.split(","));
                    for (int i = 0; i < columnNamesLogView.size(); i++) {
                        softAssert.assertTrue(newList.get(i).equalsIgnoreCase("\"" + columnNamesLogView.get(i) + "\""), "Expected "+columnNamesLogView.get(i)+ " but found "+newList.get(i));
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            ///////////////////////////////////////////////////////////////////

            int columnsCount = 1;           //start from first column in IE
            int rowsCount = 1;               //start from second row (skip header row in IE)
            int auditColumn = 0;
            int j = 0;                       //this is used for looping through column names; renews to 0 after moving to new row
            while ((data = reader.readNext()) != null) {
                for (int i = 0; i < data.length; i++) {
                    int rows = driver.getDriver().findElements(By.cssSelector(".popup-card tr")).size();   //get total rows in Audit popup

                    if (rowsCount < rows) {
                        int totalColumns = driver.getDriver().findElements(By.cssSelector(".popup-card tr:nth-child(1) td")).size();

//                        if (driver.getDriver().findElements(By.cssSelector(".popup-card tr:nth-child(" + rowsCount + ") td:nth-child(" + columnsCount + ")")).size() != 0 && columnsCount <= totalColumns) {
                        if (driver.getDriver().findElements(By.cssSelector(".popup-card tr:nth-child(" + rowsCount + ") td:nth-child(" + columnsCount + ")")).size() != 0 && columnsCount <= data.length) {
                            System.out.println(data[auditColumn].trim() + "--->" + driver.getDriver().findElement(By.cssSelector(".popup-card tr:nth-child(" + rowsCount + ") td:nth-child(" + columnsCount + ")")).getText().trim());
                            System.out.println("Name"+headerCells.get(j).getText());
                            if (!headerCells.get(j).getText().equals("Changed Date")) {
                              softAssert.assertEquals(data[auditColumn].trim().replace("-", "").replace(",", "").replace("/", ""), driver.getDriver().findElement(By.cssSelector(".popup-card tr:nth-child(" + rowsCount + ") td:nth-child(" + columnsCount + ")")).getText().trim().replace("/", "").replace(",", "").replace("-", ""), "data not matched");
                          }
                          else {
                              softAssert.assertEquals(data[auditColumn].trim().replace("-", "").replace(",", "").replace("/", "").substring(0,8), driver.getDriver().findElement(By.cssSelector(".popup-card tr:nth-child(" + rowsCount + ") td:nth-child(" + columnsCount + ")")).getText().trim().replace("/", "").replace(",", "").replace("-", ""), "data not matched");
                          }
                          j=j+1;
                        } else {
                            rowsCount = rowsCount + 1;   //move to next row
                            columnsCount = 0;     //move to first column again
                            auditColumn = -1;
                            j=0;
                        }
                        columnsCount++;
                        auditColumn++;

                    }
                }
            }

            if (file.delete()) {
                System.out.println("CSV file deleted");
            }

            if (size(popupCloseButton) != 0) {
                click(popupCloseButton);
            }

            if (size(popupCloseButton2) != 0) {
                click(popupCloseButton2);
            }

            softAssert.assertAll();
            test.pass("Column data exported successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("CSV file failed to download");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            System.out.println("Failure");
            test.fail("CSV file failed to download");
            saveResult(ITestResult.FAILURE, ex);
        }
    }
}
