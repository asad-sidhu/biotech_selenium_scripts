package LogViewFunctions;

import static MiscFunctions.ExtentVariables.Results;
import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.*;

import java.io.IOException;
import java.text.Collator;
import java.util.*;
import java.util.logging.Filter;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;

import Config.BaseTest;

public class FilterSort {


    String names[];
    int length;

    void sort(String array[]) {
        if (array == null || array.length == 0) {
            return;
        }
        this.names = array;
        this.length = array.length;
        quickSort(0, length - 1);
    }

    void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        String pivot = this.names[lowerIndex + (higherIndex - lowerIndex) / 2];

        while (i <= j) {
            while (this.names[i].compareToIgnoreCase(pivot) < 0) {
                i++;
            }

            while (this.names[j].compareToIgnoreCase(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                exchangeNames(i, j);
                i++;
                j--;
            }
        }
        //call quickSort recursively
        if (lowerIndex < j) {
            quickSort(lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSort(i, higherIndex);
        }
    }

    void exchangeNames(int i, int j) {
        String temp = this.names[i];
        this.names[i] = this.names[j];
        this.names[j] = temp;
    }



    private static Comparator<String> getCustomComparator() {
        return (s1, s2) -> {
            boolean s1HasSpace = s1.contains("M ");
            boolean s2HasSpace = s2.contains("M ");

            if (s1HasSpace && !s2HasSpace) {
                return -1; // s1 comes before s2
            } else if (!s1HasSpace && s2HasSpace) {
                return 1; // s2 comes before s1
            } else {
                return s1.compareTo(s2); // regular lexicographical comparison
            }
        };
    }

    // Custom comparator for ascending order
    static Comparator<String> ascendingComparator = (s1, s2) -> {
        // Treat empty strings as smaller
        if (s1.isEmpty()) {
            return s2.isEmpty() ? 0 : -1;
        } else if (s2.isEmpty()) {
            return 1;
        } else {
            // Remove commas and check if strings are numeric
            String num1 = s1.replaceAll(",", "");
            String num2 = s2.replaceAll(",", "");
            boolean isNumeric1 = isNumeric(num1);
            boolean isNumeric2 = isNumeric(num2);

            if (isNumeric1 && isNumeric2) {
                // Both strings are numeric, compare them numerically
                return compareNumeric(num1, num2);
            } else if (isNumeric1 || isNumeric2) {
                // One string is numeric and the other is non-numeric
                // Treat numeric string as smaller
                return isNumeric1 ? -1 : 1;
            } else {
                // Both strings are non-numeric, perform lexicographical comparison
                return s1.compareToIgnoreCase(s2);
            }
        }
    };

    // Custom comparator for descending order
    static Comparator<String> descendingComparator = ascendingComparator.reversed();

    // Method to check if a string is numeric
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    // Method to compare numeric strings
    private static int compareNumeric(String s1, String s2) {
        double num1 = Double.parseDouble(s1);
        double num2 = Double.parseDouble(s2);
        return Double.compare(num1, num2);
    }


    @Test(enabled = true)
    public static void Sorting(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
        BaseTest driver = new BaseTest();

        if (size(By.cssSelector("#"+tablename+" #save-filters.d-none"))==1) {
            click(By.cssSelector("#"+tablename+" #save-filters"));
            click(By.cssSelector("#"+tablename+" #reset-all-filters"));
            waitElementInvisible(loading_cursor);
        }


        int totalNumberofColumns = size(By.cssSelector("#" + tablename + " th .log-header .mb-0")) + skipColumns;   //get total columns and skip irrelevant columns
        for (int i = 1; i <= totalNumberofColumns; i++) {
            try {
                if (size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .log-header .mb-0")) != 0) {    //check if column has sorting
                    WebElement column = driver.getDriver().findElement(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .log-header .mb-0"));  //get name of column
                    SoftAssert softAssert = new SoftAssert();
                    test = extent.createTest("AN-Sorting-" + i + ": Apply Sorting on " + column.getText() + " column");
                    steps = test.createNode(Scenario.class, Steps);
                    results = test.createNode(Scenario.class, Results);

                    if (column.getText().contains("Time") || column.getText().toLowerCase().contains("date") || column.getText().contains("Created") ||
                            column.getText().contains("Current") || column.getText().contains("Feed Types") || column.getText().contains("Trade Name") ||
                            column.getText().toLowerCase().contains("product description") || column.getText().contains("Sites") ||
                            column.getText().contains("Products") || column.getText().contains("Target Pathogen") ||
                            column.getText().contains("Program Information") || column.getText().contains("deployment Information") ||
                            column.getText().contains("Monitoring Plan") || column.getText().equals("Role") || column.getText().contains("Ingredient") ||
                            column.getText().contains("Feed Type Categories")) {
                        test.skip("Sorting cannot be applied on this column");
                        results.createNode("Sorting cannot be applied on this column");
                        driver.saveResult(ITestResult.SKIP, null);
                    } else {
                        WebElement filter_scroll = column;
                        ((JavascriptExecutor) driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);  //scroll to column
                        steps.createNode("1. Click on " + column.getText() + " column header");

                        column.click();   //click on column to apply sort filter
                        waitElementInvisible(loading_cursor);
                        Thread.sleep(500);

                        if (size(By.cssSelector("#" + tablename + " .fa-sort-amount-down")) != 0) {
                            softAssert.assertEquals(size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ").sort_desc .log-header .mb-0")), 1, "Did not sorted in descending order on "+column.getText());
                            softAssert.assertEquals(size(alertMessage), 0, "Exception message occured");
                            getScreenshot();
                        }

                        List<WebElement> elementsList1 = driver.getDriver().findElements(By.cssSelector("#"+ tablename + " tr td.ng-star-inserted:nth-child("+i+")"));
                        List<String> originalList1 = elementsList1.stream().map(s -> s.getText()).collect(Collectors.toList());

                        FilterSort s1 = new FilterSort();
                        String[] words1 = new String[originalList1.size()];
//						s1.sort(originalList1.toArray(words1));
                        List<String> duplicatedList = new ArrayList<>(originalList1);
                        Collections.sort(duplicatedList, descendingComparator);

                        System.out.println("Expected Descending Order: " + duplicatedList);
                        System.out.println("Actual Descending Order: " + originalList1);

                        int z1 = 0;

//						Collections.reverse(originalList1);

                        for (String x : duplicatedList) {
                            if (!column.getText().contains("Email")) {
                                softAssert.assertTrue(originalList1.get(z1).equalsIgnoreCase(x), "Expected: " + x + " but found: " + originalList1.get(z1) + " in descending order for column "+column.getText());
                            }
                            z1++;
                        }

                        column.click();
                        waitElementInvisible(loading_cursor);
                        Thread.sleep(500);
                        if (size(By.cssSelector("#" + tablename + " .fa-sort-amount-down")) != 0) {
                            softAssert.assertEquals(size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ").sort_asc .log-header .mb-0")), 1, "Did not sorted in descending order on "+column.getText());
                            softAssert.assertEquals(size(alertMessage), 0, "Exception message occured");
                            getScreenshot();
                        }

                        List<WebElement> elementsList = driver.getDriver().findElements(By.cssSelector("#"+ tablename + " tr td.ng-star-inserted:nth-child("+i+")"));
                        List<String> originalList = elementsList.stream().map(s -> s.getText()).collect(Collectors.toList());
                        System.out.println("Actual Ascending Order: " + originalList);

//                        FilterSort s = new FilterSort();
//                        String[] words = new String[originalList.size()];
//                        s.sort(originalList.toArray(words));
                        List<String> duplicatedList1 = new ArrayList<>(originalList);
                        System.out.println("Expected Ascending Order: " + duplicatedList1);
                        duplicatedList1.sort(ascendingComparator);



                        int z = 0;

                        for (String x : duplicatedList1) {
                            // System.out.println("z: "+originalList.get(z) + " ----> " + x +" for column ---> "+column.getText());
                            softAssert.assertTrue(originalList.get(z).equalsIgnoreCase(x), "Expected: " + x + " but Found: " + originalList.get(z) + " in ascending order for column "+column.getText());
                            z++;
                        }

                        softAssert.assertAll();
                        test.pass("Column sorted successfully");
                        results.createNode("Column sorted successfully");
                        driver.saveResult(ITestResult.SUCCESS, null);
                    }
                }
            } catch (AssertionError er) {
                test.fail("Column failed to sort");
                results.createNode("Column failed to sort");
                driver.saveResult(ITestResult.FAILURE, new Exception(er));
            }
        }
    }

    public static void verifyExcelSorting() throws InterruptedException, IOException {
        test = extent.createTest("AN-Sorting-: Apply Sorting on  column");
        BaseTest driver = new BaseTest();
        // Find the column element

        click(By.id("userEmail_sort"));

        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        click(By.id("userEmail_sort"));

        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        //     WebElement columnElement = driver.getDriver().findElement(columnLocator);

        // Extract the text values from the column
        List<String> extractedValues = new ArrayList<>();
        List<WebElement> cells = driver.getDriver().findElements(By.cssSelector("td:nth-child(4) label"));
        for (WebElement cell : cells) {
            extractedValues.add(cell.getText());
        }
        getScreenshot();
        // Sort a copy of the extracted values using the same format as Excel
        List<String> sortedValues = new ArrayList<>(extractedValues);
        Collections.sort(sortedValues, Collator.getInstance(Locale.US)); // Use the appropriate Locale for your case


        System.out.println(extractedValues + " --------------> " + sortedValues);
        Assert.assertTrue(extractedValues.equals(sortedValues));


        // Compare the sorted values with the original extracted values
    //    return extractedValues.equals(sortedValues);
    }




    public static boolean compareColumns(String column1, String column2) {
        List<String> normalizedColumn1 = normalizeColumn(column1);
        List<String> normalizedColumn2 = normalizeColumn(column2);

        // Compare the normalized columns
        return normalizedColumn1.equals(normalizedColumn2);
    }

    public static List<String> normalizeColumn(String column) {
        List<String> normalizedColumn = new ArrayList<>();

        // Split the column data into rows
        String[] rows = column.split("\n");

        // Remove leading and trailing whitespace, and filter out empty rows
        for (String row : rows) {
            String normalizedRow = row.trim();
            if (!normalizedRow.isEmpty()) {
                normalizedColumn.add(normalizedRow);
            }
        }

        return normalizedColumn;
    }



}
